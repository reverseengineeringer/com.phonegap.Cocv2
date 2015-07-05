package com.squareup.okhttp.internal.http;

import com.squareup.okhttp.Address;
import com.squareup.okhttp.Connection;
import com.squareup.okhttp.ConnectionPool;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.OkResponseCache;
import com.squareup.okhttp.ResponseSource;
import com.squareup.okhttp.Route;
import com.squareup.okhttp.RouteDatabase;
import com.squareup.okhttp.TunnelRequest;
import com.squareup.okhttp.internal.Dns;
import com.squareup.okhttp.internal.Platform;
import com.squareup.okhttp.internal.Util;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.CacheRequest;
import java.net.CacheResponse;
import java.net.CookieHandler;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;

public class HttpEngine
{
  private static final CacheResponse GATEWAY_TIMEOUT_RESPONSE = new CacheResponse()
  {
    public InputStream getBody()
      throws IOException
    {
      return new ByteArrayInputStream(Util.EMPTY_BYTE_ARRAY);
    }
    
    public Map<String, List<String>> getHeaders()
      throws IOException
    {
      HashMap localHashMap = new HashMap();
      localHashMap.put(null, Collections.singletonList("HTTP/1.1 504 Gateway Timeout"));
      return localHashMap;
    }
  };
  public static final int HTTP_CONTINUE = 100;
  private boolean automaticallyReleaseConnectionToPool;
  private CacheRequest cacheRequest;
  private CacheResponse cacheResponse;
  private InputStream cachedResponseBody;
  private ResponseHeaders cachedResponseHeaders;
  protected final OkHttpClient client;
  boolean connected;
  protected Connection connection;
  private boolean connectionReleased;
  protected final String method;
  protected final Policy policy;
  private OutputStream requestBodyOut;
  final RequestHeaders requestHeaders;
  private InputStream responseBodyIn;
  ResponseHeaders responseHeaders;
  private ResponseSource responseSource;
  private InputStream responseTransferIn;
  protected RouteSelector routeSelector;
  long sentRequestMillis = -1L;
  private boolean transparentGzip;
  private Transport transport;
  final URI uri;
  
  public HttpEngine(OkHttpClient paramOkHttpClient, Policy paramPolicy, String paramString, RawHeaders paramRawHeaders, Connection paramConnection, RetryableOutputStream paramRetryableOutputStream)
    throws IOException
  {
    client = paramOkHttpClient;
    policy = paramPolicy;
    method = paramString;
    connection = paramConnection;
    requestBodyOut = paramRetryableOutputStream;
    try
    {
      uri = Platform.get().toUriLenient(paramPolicy.getURL());
      requestHeaders = new RequestHeaders(uri, new RawHeaders(paramRawHeaders));
      return;
    }
    catch (URISyntaxException paramOkHttpClient)
    {
      throw new IOException(paramOkHttpClient.getMessage());
    }
  }
  
  public static String getDefaultUserAgent()
  {
    String str = System.getProperty("http.agent");
    if (str != null) {
      return str;
    }
    return "Java" + System.getProperty("java.version");
  }
  
  public static String getOriginAddress(URL paramURL)
  {
    int i = paramURL.getPort();
    String str2 = paramURL.getHost();
    String str1 = str2;
    if (i > 0)
    {
      str1 = str2;
      if (i != Util.getDefaultPort(paramURL.getProtocol())) {
        str1 = str2 + ":" + i;
      }
    }
    return str1;
  }
  
  private void initContentStream(InputStream paramInputStream)
    throws IOException
  {
    responseTransferIn = paramInputStream;
    if ((transparentGzip) && (responseHeaders.isContentEncodingGzip()))
    {
      responseHeaders.stripContentEncoding();
      responseHeaders.stripContentLength();
      responseBodyIn = new GZIPInputStream(paramInputStream);
      return;
    }
    responseBodyIn = paramInputStream;
  }
  
  private void initResponseSource()
    throws IOException
  {
    responseSource = ResponseSource.NETWORK;
    if (!policy.getUseCaches()) {}
    Object localObject1;
    do
    {
      do
      {
        return;
        localObject1 = client.getOkResponseCache();
      } while (localObject1 == null);
      localObject1 = ((OkResponseCache)localObject1).get(uri, method, requestHeaders.getHeaders().toMultimap(false));
    } while (localObject1 == null);
    Object localObject2 = ((CacheResponse)localObject1).getHeaders();
    cachedResponseBody = ((CacheResponse)localObject1).getBody();
    if ((!acceptCacheResponseType((CacheResponse)localObject1)) || (localObject2 == null) || (cachedResponseBody == null))
    {
      Util.closeQuietly(cachedResponseBody);
      return;
    }
    localObject2 = RawHeaders.fromMultimap((Map)localObject2, true);
    cachedResponseHeaders = new ResponseHeaders(uri, (RawHeaders)localObject2);
    long l = System.currentTimeMillis();
    responseSource = cachedResponseHeaders.chooseResponseSource(l, requestHeaders);
    if (responseSource == ResponseSource.CACHE)
    {
      cacheResponse = ((CacheResponse)localObject1);
      setResponse(cachedResponseHeaders, cachedResponseBody);
      return;
    }
    if (responseSource == ResponseSource.CONDITIONAL_CACHE)
    {
      cacheResponse = ((CacheResponse)localObject1);
      return;
    }
    if (responseSource == ResponseSource.NETWORK)
    {
      Util.closeQuietly(cachedResponseBody);
      return;
    }
    throw new AssertionError();
  }
  
  private void maybeCache()
    throws IOException
  {
    if (!policy.getUseCaches()) {}
    OkResponseCache localOkResponseCache;
    do
    {
      return;
      localOkResponseCache = client.getOkResponseCache();
    } while (localOkResponseCache == null);
    HttpURLConnection localHttpURLConnection = policy.getHttpConnectionToCache();
    if (!responseHeaders.isCacheable(requestHeaders))
    {
      localOkResponseCache.maybeRemove(localHttpURLConnection.getRequestMethod(), uri);
      return;
    }
    cacheRequest = localOkResponseCache.put(uri, localHttpURLConnection);
  }
  
  private void prepareRawRequestHeaders()
    throws IOException
  {
    requestHeaders.getHeaders().setRequestLine(getRequestLine());
    if (requestHeaders.getUserAgent() == null) {
      requestHeaders.setUserAgent(getDefaultUserAgent());
    }
    if (requestHeaders.getHost() == null) {
      requestHeaders.setHost(getOriginAddress(policy.getURL()));
    }
    if (((connection == null) || (connection.getHttpMinorVersion() != 0)) && (requestHeaders.getConnection() == null)) {
      requestHeaders.setConnection("Keep-Alive");
    }
    if (requestHeaders.getAcceptEncoding() == null)
    {
      transparentGzip = true;
      requestHeaders.setAcceptEncoding("gzip");
    }
    if ((hasRequestBody()) && (requestHeaders.getContentType() == null)) {
      requestHeaders.setContentType("application/x-www-form-urlencoded");
    }
    long l = policy.getIfModifiedSince();
    if (l != 0L) {
      requestHeaders.setIfModifiedSince(new Date(l));
    }
    CookieHandler localCookieHandler = client.getCookieHandler();
    if (localCookieHandler != null) {
      requestHeaders.addCookies(localCookieHandler.get(uri, requestHeaders.getHeaders().toMultimap(false)));
    }
  }
  
  public static String requestPath(URL paramURL)
  {
    String str = paramURL.getFile();
    if (str == null) {
      paramURL = "/";
    }
    do
    {
      return paramURL;
      paramURL = str;
    } while (str.startsWith("/"));
    return "/" + str;
  }
  
  private String requestString()
  {
    URL localURL = policy.getURL();
    if (includeAuthorityInRequestLine()) {
      return localURL.toString();
    }
    return requestPath(localURL);
  }
  
  private void sendSocketRequest()
    throws IOException
  {
    if (connection == null) {
      connect();
    }
    if (transport != null) {
      throw new IllegalStateException();
    }
    transport = ((Transport)connection.newTransport(this));
    if ((hasRequestBody()) && (requestBodyOut == null)) {
      requestBodyOut = transport.createRequestBody();
    }
  }
  
  private void setResponse(ResponseHeaders paramResponseHeaders, InputStream paramInputStream)
    throws IOException
  {
    if (responseBodyIn != null) {
      throw new IllegalStateException();
    }
    responseHeaders = paramResponseHeaders;
    if (paramInputStream != null) {
      initContentStream(paramInputStream);
    }
  }
  
  protected boolean acceptCacheResponseType(CacheResponse paramCacheResponse)
  {
    return true;
  }
  
  public final void automaticallyReleaseConnectionToPool()
  {
    automaticallyReleaseConnectionToPool = true;
    if ((connection != null) && (connectionReleased))
    {
      client.getConnectionPool().recycle(connection);
      connection = null;
    }
  }
  
  protected final void connect()
    throws IOException
  {
    if (connection != null) {
      return;
    }
    if (routeSelector == null)
    {
      String str = uri.getHost();
      if (str == null) {
        throw new UnknownHostException(uri.toString());
      }
      SSLSocketFactory localSSLSocketFactory = null;
      HostnameVerifier localHostnameVerifier = null;
      if (uri.getScheme().equalsIgnoreCase("https"))
      {
        localSSLSocketFactory = client.getSslSocketFactory();
        localHostnameVerifier = client.getHostnameVerifier();
      }
      routeSelector = new RouteSelector(new Address(str, Util.getEffectivePort(uri), localSSLSocketFactory, localHostnameVerifier, client.getAuthenticator(), client.getProxy(), client.getTransports()), uri, client.getProxySelector(), client.getConnectionPool(), Dns.DEFAULT, client.getRoutesDatabase());
    }
    connection = routeSelector.next(method);
    if (!connection.isConnected())
    {
      connection.connect(client.getConnectTimeout(), client.getReadTimeout(), getTunnelConfig());
      client.getConnectionPool().maybeShare(connection);
      client.getRoutesDatabase().connected(connection.getRoute());
    }
    for (;;)
    {
      connected(connection);
      if (connection.getRoute().getProxy() == client.getProxy()) {
        break;
      }
      requestHeaders.getHeaders().setRequestLine(getRequestLine());
      return;
      if (!connection.isSpdy()) {
        connection.updateReadTimeout(client.getReadTimeout());
      }
    }
  }
  
  protected void connected(Connection paramConnection)
  {
    policy.setSelectedProxy(paramConnection.getRoute().getProxy());
    connected = true;
  }
  
  public final CacheResponse getCacheResponse()
  {
    return cacheResponse;
  }
  
  public final Connection getConnection()
  {
    return connection;
  }
  
  public final OutputStream getRequestBody()
  {
    if (responseSource == null) {
      throw new IllegalStateException();
    }
    return requestBodyOut;
  }
  
  public final RequestHeaders getRequestHeaders()
  {
    return requestHeaders;
  }
  
  String getRequestLine()
  {
    if ((connection == null) || (connection.getHttpMinorVersion() != 0)) {}
    for (String str = "HTTP/1.1";; str = "HTTP/1.0") {
      return method + " " + requestString() + " " + str;
    }
  }
  
  public final InputStream getResponseBody()
  {
    if (responseHeaders == null) {
      throw new IllegalStateException();
    }
    return responseBodyIn;
  }
  
  public final int getResponseCode()
  {
    if (responseHeaders == null) {
      throw new IllegalStateException();
    }
    return responseHeaders.getHeaders().getResponseCode();
  }
  
  public final ResponseHeaders getResponseHeaders()
  {
    if (responseHeaders == null) {
      throw new IllegalStateException();
    }
    return responseHeaders;
  }
  
  protected TunnelRequest getTunnelConfig()
  {
    return null;
  }
  
  public URI getUri()
  {
    return uri;
  }
  
  boolean hasRequestBody()
  {
    return (method.equals("POST")) || (method.equals("PUT")) || (method.equals("PATCH"));
  }
  
  public final boolean hasResponse()
  {
    return responseHeaders != null;
  }
  
  public final boolean hasResponseBody()
  {
    int i = responseHeaders.getHeaders().getResponseCode();
    if (method.equals("HEAD")) {}
    do
    {
      return false;
      if (((i < 100) || (i >= 200)) && (i != 204) && (i != 304)) {
        return true;
      }
    } while ((responseHeaders.getContentLength() == -1L) && (!responseHeaders.isChunked()));
    return true;
  }
  
  protected boolean includeAuthorityInRequestLine()
  {
    if (connection == null) {
      return policy.usingProxy();
    }
    return connection.getRoute().getProxy().type() == Proxy.Type.HTTP;
  }
  
  public final void readResponse()
    throws IOException
  {
    if (hasResponse()) {
      responseHeaders.setResponseSource(responseSource);
    }
    do
    {
      return;
      if (responseSource == null) {
        throw new IllegalStateException("readResponse() without sendRequest()");
      }
    } while (!responseSource.requiresConnection());
    if (sentRequestMillis == -1L)
    {
      if ((requestBodyOut instanceof RetryableOutputStream))
      {
        int i = ((RetryableOutputStream)requestBodyOut).contentLength();
        requestHeaders.setContentLength(i);
      }
      transport.writeRequestHeaders();
    }
    if (requestBodyOut != null)
    {
      requestBodyOut.close();
      if ((requestBodyOut instanceof RetryableOutputStream)) {
        transport.writeRequestBody((RetryableOutputStream)requestBodyOut);
      }
    }
    transport.flushRequest();
    responseHeaders = transport.readResponseHeaders();
    responseHeaders.setLocalTimestamps(sentRequestMillis, System.currentTimeMillis());
    responseHeaders.setResponseSource(responseSource);
    if (responseSource == ResponseSource.CONDITIONAL_CACHE)
    {
      if (cachedResponseHeaders.validate(responseHeaders))
      {
        release(false);
        responseHeaders = cachedResponseHeaders.combine(responseHeaders);
        OkResponseCache localOkResponseCache = client.getOkResponseCache();
        localOkResponseCache.trackConditionalCacheHit();
        localOkResponseCache.update(cacheResponse, policy.getHttpConnectionToCache());
        initContentStream(cachedResponseBody);
        return;
      }
      Util.closeQuietly(cachedResponseBody);
    }
    if (hasResponseBody()) {
      maybeCache();
    }
    initContentStream(transport.getTransferStream(cacheRequest));
  }
  
  public void receiveHeaders(RawHeaders paramRawHeaders)
    throws IOException
  {
    CookieHandler localCookieHandler = client.getCookieHandler();
    if (localCookieHandler != null) {
      localCookieHandler.put(uri, paramRawHeaders.toMultimap(true));
    }
  }
  
  public final void release(boolean paramBoolean)
  {
    if (responseBodyIn == cachedResponseBody) {
      Util.closeQuietly(responseBodyIn);
    }
    if ((!connectionReleased) && (connection != null))
    {
      connectionReleased = true;
      if ((transport != null) && (transport.makeReusable(paramBoolean, requestBodyOut, responseTransferIn))) {
        break label78;
      }
      Util.closeQuietly(connection);
      connection = null;
    }
    label78:
    while (!automaticallyReleaseConnectionToPool) {
      return;
    }
    client.getConnectionPool().recycle(connection);
    connection = null;
  }
  
  public final void sendRequest()
    throws IOException
  {
    if (responseSource != null) {}
    do
    {
      return;
      prepareRawRequestHeaders();
      initResponseSource();
      Object localObject = client.getOkResponseCache();
      if (localObject != null) {
        ((OkResponseCache)localObject).trackResponse(responseSource);
      }
      if ((requestHeaders.isOnlyIfCached()) && (responseSource.requiresConnection()))
      {
        if (responseSource == ResponseSource.CONDITIONAL_CACHE) {
          Util.closeQuietly(cachedResponseBody);
        }
        responseSource = ResponseSource.CACHE;
        cacheResponse = GATEWAY_TIMEOUT_RESPONSE;
        localObject = RawHeaders.fromMultimap(cacheResponse.getHeaders(), true);
        setResponse(new ResponseHeaders(uri, (RawHeaders)localObject), cacheResponse.getBody());
      }
      if (responseSource.requiresConnection())
      {
        sendSocketRequest();
        return;
      }
    } while (connection == null);
    client.getConnectionPool().recycle(connection);
    connection = null;
  }
  
  public void writingRequestHeaders()
  {
    if (sentRequestMillis != -1L) {
      throw new IllegalStateException();
    }
    sentRequestMillis = System.currentTimeMillis();
  }
}

/* Location:
 * Qualified Name:     com.squareup.okhttp.internal.http.HttpEngine
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */