package com.squareup.okhttp.internal.http;

import com.squareup.okhttp.Connection;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Route;
import com.squareup.okhttp.internal.Platform;
import com.squareup.okhttp.internal.Util;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpRetryException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.SocketPermission;
import java.net.URL;
import java.security.Permission;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLHandshakeException;

public class HttpURLConnectionImpl
  extends HttpURLConnection
  implements Policy
{
  public static final int HTTP_TEMP_REDIRECT = 307;
  private static final int MAX_REDIRECTS = 20;
  final OkHttpClient client;
  private long fixedContentLength = -1L;
  protected HttpEngine httpEngine;
  protected IOException httpEngineFailure;
  private final RawHeaders rawRequestHeaders = new RawHeaders();
  private int redirectionCount;
  private Proxy selectedProxy;
  
  public HttpURLConnectionImpl(URL paramURL, OkHttpClient paramOkHttpClient)
  {
    super(paramURL);
    client = paramOkHttpClient;
  }
  
  private boolean execute(boolean paramBoolean)
    throws IOException
  {
    try
    {
      httpEngine.sendRequest();
      if (paramBoolean) {
        httpEngine.readResponse();
      }
      return true;
    }
    catch (IOException localIOException)
    {
      if (handleFailure(localIOException)) {
        return false;
      }
      throw localIOException;
    }
  }
  
  private HttpEngine getResponse()
    throws IOException
  {
    initHttpEngine();
    if (httpEngine.hasResponse()) {
      return httpEngine;
    }
    for (;;)
    {
      if (execute(true))
      {
        Retry localRetry = processResponseHeaders();
        if (localRetry == Retry.NONE)
        {
          httpEngine.automaticallyReleaseConnectionToPool();
          return httpEngine;
        }
        String str = method;
        OutputStream localOutputStream = httpEngine.getRequestBody();
        int i = httpEngine.getResponseCode();
        if ((i == 300) || (i == 301) || (i == 302) || (i == 303))
        {
          str = "GET";
          localOutputStream = null;
        }
        if ((localOutputStream != null) && (!(localOutputStream instanceof RetryableOutputStream))) {
          throw new HttpRetryException("Cannot retry streamed HTTP body", i);
        }
        if (localRetry == Retry.DIFFERENT_CONNECTION) {
          httpEngine.automaticallyReleaseConnectionToPool();
        }
        httpEngine.release(false);
        httpEngine = newHttpEngine(str, rawRequestHeaders, httpEngine.getConnection(), (RetryableOutputStream)localOutputStream);
        if (localOutputStream == null) {
          httpEngine.getRequestHeaders().removeContentLength();
        }
      }
    }
  }
  
  private boolean handleFailure(IOException paramIOException)
    throws IOException
  {
    RouteSelector localRouteSelector = httpEngine.routeSelector;
    if ((localRouteSelector != null) && (httpEngine.connection != null)) {
      localRouteSelector.connectFailed(httpEngine.connection, paramIOException);
    }
    OutputStream localOutputStream = httpEngine.getRequestBody();
    if ((localOutputStream != null) && (!(localOutputStream instanceof RetryableOutputStream))) {}
    for (int i = 0; ((localRouteSelector == null) && (httpEngine.connection == null)) || ((localRouteSelector != null) && (!localRouteSelector.hasNext())) || (!isRecoverable(paramIOException)) || (i == 0); i = 1)
    {
      httpEngineFailure = paramIOException;
      return false;
    }
    httpEngine.release(true);
    paramIOException = (RetryableOutputStream)localOutputStream;
    httpEngine = newHttpEngine(method, rawRequestHeaders, null, paramIOException);
    httpEngine.routeSelector = localRouteSelector;
    return true;
  }
  
  private void initHttpEngine()
    throws IOException
  {
    if (httpEngineFailure != null) {
      throw httpEngineFailure;
    }
    if (httpEngine != null) {
      return;
    }
    connected = true;
    do
    {
      try
      {
        if (doOutput)
        {
          if (method.equals("GET")) {
            method = "POST";
          }
        }
        else
        {
          httpEngine = newHttpEngine(method, rawRequestHeaders, null, null);
          return;
        }
      }
      catch (IOException localIOException)
      {
        httpEngineFailure = localIOException;
        throw localIOException;
      }
    } while ((method.equals("POST")) || (method.equals("PUT")) || (method.equals("PATCH")));
    throw new ProtocolException(method + " does not support writing");
  }
  
  private boolean isRecoverable(IOException paramIOException)
  {
    if (((paramIOException instanceof SSLHandshakeException)) && ((paramIOException.getCause() instanceof CertificateException))) {}
    for (int i = 1;; i = 0)
    {
      boolean bool = paramIOException instanceof ProtocolException;
      if ((i != 0) || (bool)) {
        break;
      }
      return true;
    }
    return false;
  }
  
  private static boolean isValidNonDirectProxy(Proxy paramProxy)
  {
    return (paramProxy != null) && (paramProxy.type() != Proxy.Type.DIRECT);
  }
  
  private HttpEngine newHttpEngine(String paramString, RawHeaders paramRawHeaders, Connection paramConnection, RetryableOutputStream paramRetryableOutputStream)
    throws IOException
  {
    if (url.getProtocol().equals("http")) {
      return new HttpEngine(client, this, paramString, paramRawHeaders, paramConnection, paramRetryableOutputStream);
    }
    if (url.getProtocol().equals("https")) {
      return new HttpsEngine(client, this, paramString, paramRawHeaders, paramConnection, paramRetryableOutputStream);
    }
    throw new AssertionError();
  }
  
  private Retry processResponseHeaders()
    throws IOException
  {
    if (httpEngine.connection != null) {}
    for (Object localObject = httpEngine.connection.getRoute().getProxy();; localObject = client.getProxy())
    {
      i = getResponseCode();
      switch (i)
      {
      default: 
        return Retry.NONE;
      }
    }
    if (((Proxy)localObject).type() != Proxy.Type.HTTP) {
      throw new ProtocolException("Received HTTP_PROXY_AUTH (407) code while not using proxy");
    }
    if (HttpAuthenticator.processAuthHeader(client.getAuthenticator(), getResponseCode(), httpEngine.getResponseHeaders().getHeaders(), rawRequestHeaders, (Proxy)localObject, url)) {
      return Retry.SAME_CONNECTION;
    }
    return Retry.NONE;
    if (!getInstanceFollowRedirects()) {
      return Retry.NONE;
    }
    int j = redirectionCount + 1;
    redirectionCount = j;
    if (j > 20) {
      throw new ProtocolException("Too many redirects: " + redirectionCount);
    }
    if ((i == 307) && (!method.equals("GET")) && (!method.equals("HEAD"))) {
      return Retry.NONE;
    }
    localObject = getHeaderField("Location");
    if (localObject == null) {
      return Retry.NONE;
    }
    URL localURL = url;
    url = new URL(localURL, (String)localObject);
    if ((!url.getProtocol().equals("https")) && (!url.getProtocol().equals("http"))) {
      return Retry.NONE;
    }
    boolean bool1 = localURL.getProtocol().equals(url.getProtocol());
    if ((!bool1) && (!client.getFollowProtocolRedirects())) {
      return Retry.NONE;
    }
    boolean bool2 = localURL.getHost().equals(url.getHost());
    if (Util.getEffectivePort(localURL) == Util.getEffectivePort(url)) {}
    for (int i = 1; (bool2) && (i != 0) && (bool1); i = 0) {
      return Retry.SAME_CONNECTION;
    }
    return Retry.DIFFERENT_CONNECTION;
  }
  
  private void setTransports(String paramString, boolean paramBoolean)
  {
    ArrayList localArrayList = new ArrayList();
    if (paramBoolean) {
      localArrayList.addAll(client.getTransports());
    }
    paramString = paramString.split(",", -1);
    int j = paramString.length;
    int i = 0;
    for (;;)
    {
      if (i >= j)
      {
        client.setTransports(localArrayList);
        return;
      }
      localArrayList.add(paramString[i]);
      i += 1;
    }
  }
  
  public final void addRequestProperty(String paramString1, String paramString2)
  {
    if (connected) {
      throw new IllegalStateException("Cannot add request property after connection is made");
    }
    if (paramString1 == null) {
      throw new NullPointerException("field == null");
    }
    if (paramString2 == null)
    {
      Platform.get().logW("Ignoring header " + paramString1 + " because its value was null.");
      return;
    }
    if ("X-Android-Transports".equals(paramString1))
    {
      setTransports(paramString2, true);
      return;
    }
    rawRequestHeaders.add(paramString1, paramString2);
  }
  
  public final void connect()
    throws IOException
  {
    initHttpEngine();
    while (!execute(false)) {}
  }
  
  public final void disconnect()
  {
    if (httpEngine != null)
    {
      if (httpEngine.hasResponse()) {
        Util.closeQuietly(httpEngine.getResponseBody());
      }
      httpEngine.release(true);
    }
  }
  
  public final int getChunkLength()
  {
    return chunkLength;
  }
  
  public int getConnectTimeout()
  {
    return client.getConnectTimeout();
  }
  
  public final InputStream getErrorStream()
  {
    Object localObject2 = null;
    try
    {
      HttpEngine localHttpEngine = getResponse();
      Object localObject1 = localObject2;
      if (localHttpEngine.hasResponseBody())
      {
        localObject1 = localObject2;
        if (localHttpEngine.getResponseCode() >= 400) {
          localObject1 = localHttpEngine.getResponseBody();
        }
      }
      return (InputStream)localObject1;
    }
    catch (IOException localIOException) {}
    return null;
  }
  
  public final long getFixedContentLength()
  {
    return fixedContentLength;
  }
  
  public final String getHeaderField(int paramInt)
  {
    try
    {
      String str = getResponse().getResponseHeaders().getHeaders().getValue(paramInt);
      return str;
    }
    catch (IOException localIOException) {}
    return null;
  }
  
  public final String getHeaderField(String paramString)
  {
    try
    {
      RawHeaders localRawHeaders = getResponse().getResponseHeaders().getHeaders();
      if (paramString == null) {
        return localRawHeaders.getStatusLine();
      }
      paramString = localRawHeaders.get(paramString);
      return paramString;
    }
    catch (IOException paramString) {}
    return null;
  }
  
  public final String getHeaderFieldKey(int paramInt)
  {
    try
    {
      String str = getResponse().getResponseHeaders().getHeaders().getFieldName(paramInt);
      return str;
    }
    catch (IOException localIOException) {}
    return null;
  }
  
  public final Map<String, List<String>> getHeaderFields()
  {
    try
    {
      Map localMap = getResponse().getResponseHeaders().getHeaders().toMultimap(true);
      return localMap;
    }
    catch (IOException localIOException) {}
    return Collections.emptyMap();
  }
  
  public HttpURLConnection getHttpConnectionToCache()
  {
    return this;
  }
  
  public HttpEngine getHttpEngine()
  {
    return httpEngine;
  }
  
  public final InputStream getInputStream()
    throws IOException
  {
    if (!doInput) {
      throw new ProtocolException("This protocol does not support input");
    }
    Object localObject = getResponse();
    if (getResponseCode() >= 400) {
      throw new FileNotFoundException(url.toString());
    }
    localObject = ((HttpEngine)localObject).getResponseBody();
    if (localObject == null) {
      throw new ProtocolException("No response body exists; responseCode=" + getResponseCode());
    }
    return (InputStream)localObject;
  }
  
  public final OutputStream getOutputStream()
    throws IOException
  {
    connect();
    OutputStream localOutputStream = httpEngine.getRequestBody();
    if (localOutputStream == null) {
      throw new ProtocolException("method does not support a request body: " + method);
    }
    if (httpEngine.hasResponse()) {
      throw new ProtocolException("cannot write request body after response has been read");
    }
    return localOutputStream;
  }
  
  public final Permission getPermission()
    throws IOException
  {
    String str = getURL().getHost();
    int i = Util.getEffectivePort(getURL());
    if (usingProxy())
    {
      InetSocketAddress localInetSocketAddress = (InetSocketAddress)client.getProxy().address();
      str = localInetSocketAddress.getHostName();
      i = localInetSocketAddress.getPort();
    }
    return new SocketPermission(str + ":" + i, "connect, resolve");
  }
  
  public int getReadTimeout()
  {
    return client.getReadTimeout();
  }
  
  public final Map<String, List<String>> getRequestProperties()
  {
    if (connected) {
      throw new IllegalStateException("Cannot access request header fields after connection is set");
    }
    return rawRequestHeaders.toMultimap(false);
  }
  
  public final String getRequestProperty(String paramString)
  {
    if (paramString == null) {
      return null;
    }
    return rawRequestHeaders.get(paramString);
  }
  
  public final int getResponseCode()
    throws IOException
  {
    return getResponse().getResponseCode();
  }
  
  public String getResponseMessage()
    throws IOException
  {
    return getResponse().getResponseHeaders().getHeaders().getResponseMessage();
  }
  
  public void setConnectTimeout(int paramInt)
  {
    client.setConnectTimeout(paramInt, TimeUnit.MILLISECONDS);
  }
  
  public void setFixedLengthStreamingMode(int paramInt)
  {
    setFixedLengthStreamingMode(paramInt);
  }
  
  public void setFixedLengthStreamingMode(long paramLong)
  {
    if (connected) {
      throw new IllegalStateException("Already connected");
    }
    if (chunkLength > 0) {
      throw new IllegalStateException("Already in chunked mode");
    }
    if (paramLong < 0L) {
      throw new IllegalArgumentException("contentLength < 0");
    }
    fixedContentLength = paramLong;
    fixedContentLength = ((int)Math.min(paramLong, 2147483647L));
  }
  
  public void setReadTimeout(int paramInt)
  {
    client.setReadTimeout(paramInt, TimeUnit.MILLISECONDS);
  }
  
  public final void setRequestProperty(String paramString1, String paramString2)
  {
    if (connected) {
      throw new IllegalStateException("Cannot set request property after connection is made");
    }
    if (paramString1 == null) {
      throw new NullPointerException("field == null");
    }
    if (paramString2 == null)
    {
      Platform.get().logW("Ignoring header " + paramString1 + " because its value was null.");
      return;
    }
    if ("X-Android-Transports".equals(paramString1))
    {
      setTransports(paramString2, false);
      return;
    }
    rawRequestHeaders.set(paramString1, paramString2);
  }
  
  public final void setSelectedProxy(Proxy paramProxy)
  {
    selectedProxy = paramProxy;
  }
  
  public final boolean usingProxy()
  {
    if (selectedProxy != null) {
      return isValidNonDirectProxy(selectedProxy);
    }
    return isValidNonDirectProxy(client.getProxy());
  }
  
  static enum Retry
  {
    NONE,  SAME_CONNECTION,  DIFFERENT_CONNECTION;
  }
}

/* Location:
 * Qualified Name:     com.squareup.okhttp.internal.http.HttpURLConnectionImpl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */