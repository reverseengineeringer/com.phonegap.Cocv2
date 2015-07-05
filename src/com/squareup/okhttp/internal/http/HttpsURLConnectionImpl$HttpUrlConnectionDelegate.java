package com.squareup.okhttp.internal.http;

import com.squareup.okhttp.OkHttpClient;
import java.net.HttpURLConnection;
import java.net.SecureCacheResponse;
import java.net.URL;

final class HttpsURLConnectionImpl$HttpUrlConnectionDelegate
  extends HttpURLConnectionImpl
{
  private HttpsURLConnectionImpl$HttpUrlConnectionDelegate(HttpsURLConnectionImpl paramHttpsURLConnectionImpl, URL paramURL, OkHttpClient paramOkHttpClient)
  {
    super(paramURL, paramOkHttpClient);
  }
  
  public HttpURLConnection getHttpConnectionToCache()
  {
    return this$0;
  }
  
  public SecureCacheResponse getSecureCacheResponse()
  {
    if ((httpEngine instanceof HttpsEngine)) {
      return (SecureCacheResponse)httpEngine.getCacheResponse();
    }
    return null;
  }
}

/* Location:
 * Qualified Name:     com.squareup.okhttp.internal.http.HttpsURLConnectionImpl.HttpUrlConnectionDelegate
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */