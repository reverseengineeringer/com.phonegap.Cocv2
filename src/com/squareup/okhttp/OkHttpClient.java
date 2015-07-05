package com.squareup.okhttp;

import com.squareup.okhttp.internal.Util;
import com.squareup.okhttp.internal.http.HttpAuthenticator;
import com.squareup.okhttp.internal.http.HttpURLConnectionImpl;
import com.squareup.okhttp.internal.http.HttpsURLConnectionImpl;
import com.squareup.okhttp.internal.http.OkResponseCacheAdapter;
import com.squareup.okhttp.internal.tls.OkHostnameVerifier;
import java.net.CookieHandler;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.ResponseCache;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;

public final class OkHttpClient
  implements URLStreamHandlerFactory
{
  private static final List<String> DEFAULT_TRANSPORTS = Util.immutableList(Arrays.asList(new String[] { "spdy/3", "http/1.1" }));
  private OkAuthenticator authenticator;
  private int connectTimeout;
  private ConnectionPool connectionPool;
  private CookieHandler cookieHandler;
  private final Dispatcher dispatcher;
  private boolean followProtocolRedirects = true;
  private HostnameVerifier hostnameVerifier;
  private Proxy proxy;
  private ProxySelector proxySelector;
  private int readTimeout;
  private ResponseCache responseCache;
  private final RouteDatabase routeDatabase;
  private SSLSocketFactory sslSocketFactory;
  private List<String> transports;
  
  public OkHttpClient()
  {
    routeDatabase = new RouteDatabase();
    dispatcher = new Dispatcher();
  }
  
  private OkHttpClient(OkHttpClient paramOkHttpClient)
  {
    routeDatabase = routeDatabase;
    dispatcher = dispatcher;
  }
  
  private OkHttpClient copyWithDefaults()
  {
    OkHttpClient localOkHttpClient = new OkHttpClient(this);
    proxy = proxy;
    if (proxySelector != null)
    {
      localObject = proxySelector;
      proxySelector = ((ProxySelector)localObject);
      if (cookieHandler == null) {
        break label186;
      }
      localObject = cookieHandler;
      label46:
      cookieHandler = ((CookieHandler)localObject);
      if (responseCache == null) {
        break label193;
      }
      localObject = responseCache;
      label63:
      responseCache = ((ResponseCache)localObject);
      if (sslSocketFactory == null) {
        break label200;
      }
      localObject = sslSocketFactory;
      label80:
      sslSocketFactory = ((SSLSocketFactory)localObject);
      if (hostnameVerifier == null) {
        break label207;
      }
      localObject = hostnameVerifier;
      label97:
      hostnameVerifier = ((HostnameVerifier)localObject);
      if (authenticator == null) {
        break label214;
      }
      localObject = authenticator;
      label114:
      authenticator = ((OkAuthenticator)localObject);
      if (connectionPool == null) {
        break label221;
      }
      localObject = connectionPool;
      label131:
      connectionPool = ((ConnectionPool)localObject);
      followProtocolRedirects = followProtocolRedirects;
      if (transports == null) {
        break label228;
      }
    }
    label186:
    label193:
    label200:
    label207:
    label214:
    label221:
    label228:
    for (Object localObject = transports;; localObject = DEFAULT_TRANSPORTS)
    {
      transports = ((List)localObject);
      connectTimeout = connectTimeout;
      readTimeout = readTimeout;
      return localOkHttpClient;
      localObject = ProxySelector.getDefault();
      break;
      localObject = CookieHandler.getDefault();
      break label46;
      localObject = ResponseCache.getDefault();
      break label63;
      localObject = HttpsURLConnection.getDefaultSSLSocketFactory();
      break label80;
      localObject = OkHostnameVerifier.INSTANCE;
      break label97;
      localObject = HttpAuthenticator.SYSTEM_DEFAULT;
      break label114;
      localObject = ConnectionPool.getDefault();
      break label131;
    }
  }
  
  void cancel(Object paramObject)
  {
    dispatcher.cancel(paramObject);
  }
  
  public URLStreamHandler createURLStreamHandler(final String paramString)
  {
    if ((!paramString.equals("http")) && (!paramString.equals("https"))) {
      return null;
    }
    new URLStreamHandler()
    {
      protected int getDefaultPort()
      {
        if (paramString.equals("http")) {
          return 80;
        }
        if (paramString.equals("https")) {
          return 443;
        }
        throw new AssertionError();
      }
      
      protected URLConnection openConnection(URL paramAnonymousURL)
      {
        return open(paramAnonymousURL);
      }
      
      protected URLConnection openConnection(URL paramAnonymousURL, Proxy paramAnonymousProxy)
      {
        return open(paramAnonymousURL, paramAnonymousProxy);
      }
    };
  }
  
  void enqueue(Request paramRequest, Response.Receiver paramReceiver)
  {
    dispatcher.enqueue(copyWithDefaults(), paramRequest, paramReceiver);
  }
  
  public OkAuthenticator getAuthenticator()
  {
    return authenticator;
  }
  
  public int getConnectTimeout()
  {
    return connectTimeout;
  }
  
  public ConnectionPool getConnectionPool()
  {
    return connectionPool;
  }
  
  public CookieHandler getCookieHandler()
  {
    return cookieHandler;
  }
  
  public boolean getFollowProtocolRedirects()
  {
    return followProtocolRedirects;
  }
  
  public HostnameVerifier getHostnameVerifier()
  {
    return hostnameVerifier;
  }
  
  public OkResponseCache getOkResponseCache()
  {
    if ((responseCache instanceof HttpResponseCache)) {
      return responseCache).okResponseCache;
    }
    if (responseCache != null) {
      return new OkResponseCacheAdapter(responseCache);
    }
    return null;
  }
  
  public Proxy getProxy()
  {
    return proxy;
  }
  
  public ProxySelector getProxySelector()
  {
    return proxySelector;
  }
  
  public int getReadTimeout()
  {
    return readTimeout;
  }
  
  public ResponseCache getResponseCache()
  {
    return responseCache;
  }
  
  public RouteDatabase getRoutesDatabase()
  {
    return routeDatabase;
  }
  
  public SSLSocketFactory getSslSocketFactory()
  {
    return sslSocketFactory;
  }
  
  public List<String> getTransports()
  {
    return transports;
  }
  
  public HttpURLConnection open(URL paramURL)
  {
    return open(paramURL, proxy);
  }
  
  HttpURLConnection open(URL paramURL, Proxy paramProxy)
  {
    String str = paramURL.getProtocol();
    OkHttpClient localOkHttpClient = copyWithDefaults();
    proxy = paramProxy;
    if (str.equals("http")) {
      return new HttpURLConnectionImpl(paramURL, localOkHttpClient);
    }
    if (str.equals("https")) {
      return new HttpsURLConnectionImpl(paramURL, localOkHttpClient);
    }
    throw new IllegalArgumentException("Unexpected protocol: " + str);
  }
  
  public OkHttpClient setAuthenticator(OkAuthenticator paramOkAuthenticator)
  {
    authenticator = paramOkAuthenticator;
    return this;
  }
  
  public void setConnectTimeout(long paramLong, TimeUnit paramTimeUnit)
  {
    if (paramLong < 0L) {
      throw new IllegalArgumentException("timeout < 0");
    }
    if (paramTimeUnit == null) {
      throw new IllegalArgumentException("unit == null");
    }
    paramLong = paramTimeUnit.toMillis(paramLong);
    if (paramLong > 2147483647L) {
      throw new IllegalArgumentException("Timeout too large.");
    }
    connectTimeout = ((int)paramLong);
  }
  
  public OkHttpClient setConnectionPool(ConnectionPool paramConnectionPool)
  {
    connectionPool = paramConnectionPool;
    return this;
  }
  
  public OkHttpClient setCookieHandler(CookieHandler paramCookieHandler)
  {
    cookieHandler = paramCookieHandler;
    return this;
  }
  
  public OkHttpClient setFollowProtocolRedirects(boolean paramBoolean)
  {
    followProtocolRedirects = paramBoolean;
    return this;
  }
  
  public OkHttpClient setHostnameVerifier(HostnameVerifier paramHostnameVerifier)
  {
    hostnameVerifier = paramHostnameVerifier;
    return this;
  }
  
  public OkHttpClient setProxy(Proxy paramProxy)
  {
    proxy = paramProxy;
    return this;
  }
  
  public OkHttpClient setProxySelector(ProxySelector paramProxySelector)
  {
    proxySelector = paramProxySelector;
    return this;
  }
  
  public void setReadTimeout(long paramLong, TimeUnit paramTimeUnit)
  {
    if (paramLong < 0L) {
      throw new IllegalArgumentException("timeout < 0");
    }
    if (paramTimeUnit == null) {
      throw new IllegalArgumentException("unit == null");
    }
    paramLong = paramTimeUnit.toMillis(paramLong);
    if (paramLong > 2147483647L) {
      throw new IllegalArgumentException("Timeout too large.");
    }
    readTimeout = ((int)paramLong);
  }
  
  public OkHttpClient setResponseCache(ResponseCache paramResponseCache)
  {
    responseCache = paramResponseCache;
    return this;
  }
  
  public OkHttpClient setSslSocketFactory(SSLSocketFactory paramSSLSocketFactory)
  {
    sslSocketFactory = paramSSLSocketFactory;
    return this;
  }
  
  public OkHttpClient setTransports(List<String> paramList)
  {
    paramList = Util.immutableList(paramList);
    if (!paramList.contains("http/1.1")) {
      throw new IllegalArgumentException("transports doesn't contain http/1.1: " + paramList);
    }
    if (paramList.contains(null)) {
      throw new IllegalArgumentException("transports must not contain null");
    }
    if (paramList.contains("")) {
      throw new IllegalArgumentException("transports contains an empty string");
    }
    transports = paramList;
    return this;
  }
}

/* Location:
 * Qualified Name:     com.squareup.okhttp.OkHttpClient
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */