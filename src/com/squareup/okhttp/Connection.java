package com.squareup.okhttp;

import com.squareup.okhttp.internal.Platform;
import com.squareup.okhttp.internal.http.HttpAuthenticator;
import com.squareup.okhttp.internal.http.HttpEngine;
import com.squareup.okhttp.internal.http.HttpTransport;
import com.squareup.okhttp.internal.http.RawHeaders;
import com.squareup.okhttp.internal.http.SpdyTransport;
import com.squareup.okhttp.internal.spdy.SpdyConnection;
import com.squareup.okhttp.internal.spdy.SpdyConnection.Builder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public final class Connection
  implements Closeable
{
  private static final byte[] HTTP_11 = { 104, 116, 116, 112, 47, 49, 46, 49 };
  private static final byte[] NPN_PROTOCOLS = { 6, 115, 112, 100, 121, 47, 51, 8, 104, 116, 116, 112, 47, 49, 46, 49 };
  private static final byte[] SPDY3 = { 115, 112, 100, 121, 47, 51 };
  private boolean connected = false;
  private int httpMinorVersion = 1;
  private long idleStartTimeNs;
  private InputStream in;
  private OutputStream out;
  private final Route route;
  private Socket socket;
  private SpdyConnection spdyConnection;
  
  public Connection(Route paramRoute)
  {
    route = paramRoute;
  }
  
  private void makeTunnel(TunnelRequest paramTunnelRequest)
    throws IOException
  {
    RawHeaders localRawHeaders1 = paramTunnelRequest.getRequestHeaders();
    RawHeaders localRawHeaders2;
    URL localURL;
    do
    {
      out.write(localRawHeaders1.toBytes());
      localRawHeaders2 = RawHeaders.fromBytes(in);
      switch (localRawHeaders2.getResponseCode())
      {
      default: 
        throw new IOException("Unexpected response code for CONNECT: " + localRawHeaders2.getResponseCode());
      case 407: 
        localRawHeaders1 = new RawHeaders(localRawHeaders1);
        localURL = new URL("https", host, port, "/");
      }
    } while (HttpAuthenticator.processAuthHeader(route.address.authenticator, 407, localRawHeaders2, localRawHeaders1, route.proxy, localURL));
    throw new IOException("Failed to authenticate with proxy");
  }
  
  private void streamWrapper()
    throws IOException
  {
    in = new BufferedInputStream(in, 4096);
    out = new BufferedOutputStream(out, 256);
  }
  
  private void upgradeToTls(TunnelRequest paramTunnelRequest)
    throws IOException
  {
    Object localObject = Platform.get();
    if (requiresTunnel()) {
      makeTunnel(paramTunnelRequest);
    }
    socket = route.address.sslSocketFactory.createSocket(socket, route.address.uriHost, route.address.uriPort, true);
    paramTunnelRequest = (SSLSocket)socket;
    if (route.modernTls)
    {
      ((Platform)localObject).enableTlsExtensions(paramTunnelRequest, route.address.uriHost);
      if ((!route.modernTls) || (!route.address.transports.contains("spdy/3"))) {
        break label220;
      }
    }
    label220:
    for (int i = 1;; i = 0)
    {
      if (i != 0) {
        ((Platform)localObject).setNpnProtocols(paramTunnelRequest, NPN_PROTOCOLS);
      }
      paramTunnelRequest.startHandshake();
      if (route.address.hostnameVerifier.verify(route.address.uriHost, paramTunnelRequest.getSession())) {
        break label225;
      }
      throw new IOException("Hostname '" + route.address.uriHost + "' was not verified");
      ((Platform)localObject).supportTlsIntolerantServer(paramTunnelRequest);
      break;
    }
    label225:
    out = paramTunnelRequest.getOutputStream();
    in = paramTunnelRequest.getInputStream();
    streamWrapper();
    if (i != 0)
    {
      localObject = ((Platform)localObject).getNpnSelectedProtocol(paramTunnelRequest);
      if (localObject != null)
      {
        if (!Arrays.equals((byte[])localObject, SPDY3)) {
          break label315;
        }
        paramTunnelRequest.setSoTimeout(0);
        spdyConnection = new SpdyConnection.Builder(route.address.getUriHost(), true, in, out).build();
        spdyConnection.sendConnectionHeader();
      }
    }
    label315:
    while (Arrays.equals((byte[])localObject, HTTP_11)) {
      return;
    }
    throw new IOException("Unexpected NPN transport " + new String((byte[])localObject, "ISO-8859-1"));
  }
  
  public void close()
    throws IOException
  {
    socket.close();
  }
  
  public void connect(int paramInt1, int paramInt2, TunnelRequest paramTunnelRequest)
    throws IOException
  {
    if (connected) {
      throw new IllegalStateException("already connected");
    }
    Socket localSocket;
    if (route.proxy.type() != Proxy.Type.HTTP)
    {
      localSocket = new Socket(route.proxy);
      socket = localSocket;
      Platform.get().connectSocket(socket, route.inetSocketAddress, paramInt1);
      socket.setSoTimeout(paramInt2);
      in = socket.getInputStream();
      out = socket.getOutputStream();
      if (route.address.sslSocketFactory == null) {
        break label140;
      }
      upgradeToTls(paramTunnelRequest);
    }
    for (;;)
    {
      connected = true;
      return;
      localSocket = new Socket();
      break;
      label140:
      streamWrapper();
    }
  }
  
  public int getHttpMinorVersion()
  {
    return httpMinorVersion;
  }
  
  public long getIdleStartTimeNs()
  {
    if (spdyConnection == null) {
      return idleStartTimeNs;
    }
    return spdyConnection.getIdleStartTimeNs();
  }
  
  public Route getRoute()
  {
    return route;
  }
  
  public Socket getSocket()
  {
    return socket;
  }
  
  public SpdyConnection getSpdyConnection()
  {
    return spdyConnection;
  }
  
  public boolean isAlive()
  {
    return (!socket.isClosed()) && (!socket.isInputShutdown()) && (!socket.isOutputShutdown());
  }
  
  public boolean isConnected()
  {
    return connected;
  }
  
  public boolean isExpired(long paramLong)
  {
    return getIdleStartTimeNs() < System.nanoTime() - paramLong;
  }
  
  public boolean isIdle()
  {
    return (spdyConnection == null) || (spdyConnection.isIdle());
  }
  
  public boolean isReadable()
  {
    if (!(in instanceof BufferedInputStream)) {}
    while (isSpdy()) {
      return true;
    }
    BufferedInputStream localBufferedInputStream = (BufferedInputStream)in;
    try
    {
      int i = socket.getSoTimeout();
      try
      {
        socket.setSoTimeout(1);
        localBufferedInputStream.mark(1);
        int j = localBufferedInputStream.read();
        if (j == -1) {
          return false;
        }
        localBufferedInputStream.reset();
        return true;
      }
      finally
      {
        socket.setSoTimeout(i);
      }
      return true;
    }
    catch (IOException localIOException)
    {
      return false;
    }
    catch (SocketTimeoutException localSocketTimeoutException) {}
  }
  
  public boolean isSpdy()
  {
    return spdyConnection != null;
  }
  
  public Object newTransport(HttpEngine paramHttpEngine)
    throws IOException
  {
    if (spdyConnection != null) {
      return new SpdyTransport(paramHttpEngine, spdyConnection);
    }
    return new HttpTransport(paramHttpEngine, out, in);
  }
  
  public boolean requiresTunnel()
  {
    return (route.address.sslSocketFactory != null) && (route.proxy.type() == Proxy.Type.HTTP);
  }
  
  public void resetIdleStartTime()
  {
    if (spdyConnection != null) {
      throw new IllegalStateException("spdyConnection != null");
    }
    idleStartTimeNs = System.nanoTime();
  }
  
  public void setHttpMinorVersion(int paramInt)
  {
    httpMinorVersion = paramInt;
  }
  
  public void updateReadTimeout(int paramInt)
    throws IOException
  {
    if (!connected) {
      throw new IllegalStateException("updateReadTimeout - not connected");
    }
    socket.setSoTimeout(paramInt);
  }
}

/* Location:
 * Qualified Name:     com.squareup.okhttp.Connection
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */