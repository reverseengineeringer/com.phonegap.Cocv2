package com.squareup.okhttp.internal;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.Socket;
import javax.net.ssl.SSLSocket;

class Platform$Android23
  extends Platform
{
  protected final Class<?> openSslSocketClass;
  private final Method setHostname;
  private final Method setUseSessionTickets;
  
  private Platform$Android23(Class<?> paramClass, Method paramMethod1, Method paramMethod2)
  {
    openSslSocketClass = paramClass;
    setUseSessionTickets = paramMethod1;
    setHostname = paramMethod2;
  }
  
  public void connectSocket(Socket paramSocket, InetSocketAddress paramInetSocketAddress, int paramInt)
    throws IOException
  {
    try
    {
      paramSocket.connect(paramInetSocketAddress, paramInt);
      return;
    }
    catch (SecurityException paramSocket)
    {
      paramInetSocketAddress = new IOException("Exception in connect");
      paramInetSocketAddress.initCause(paramSocket);
      throw paramInetSocketAddress;
    }
  }
  
  public void enableTlsExtensions(SSLSocket paramSSLSocket, String paramString)
  {
    super.enableTlsExtensions(paramSSLSocket, paramString);
    if (openSslSocketClass.isInstance(paramSSLSocket)) {}
    try
    {
      setUseSessionTickets.invoke(paramSSLSocket, new Object[] { Boolean.valueOf(true) });
      setHostname.invoke(paramSSLSocket, new Object[] { paramString });
      return;
    }
    catch (InvocationTargetException paramSSLSocket)
    {
      throw new RuntimeException(paramSSLSocket);
    }
    catch (IllegalAccessException paramSSLSocket)
    {
      throw new AssertionError(paramSSLSocket);
    }
  }
}

/* Location:
 * Qualified Name:     com.squareup.okhttp.internal.Platform.Android23
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */