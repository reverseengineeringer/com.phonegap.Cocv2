package com.squareup.okhttp;

import com.squareup.okhttp.internal.Util;
import com.squareup.okhttp.internal.http.HttpAuthenticator;
import com.squareup.okhttp.internal.http.HttpEngine;
import com.squareup.okhttp.internal.http.HttpsEngine;
import com.squareup.okhttp.internal.http.Policy;
import com.squareup.okhttp.internal.http.RawHeaders;
import com.squareup.okhttp.internal.http.RequestHeaders;
import com.squareup.okhttp.internal.http.ResponseHeaders;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.URL;

final class Job
  implements Runnable, Policy
{
  private final OkHttpClient client;
  private final Dispatcher dispatcher;
  private Request request;
  private final Response.Receiver responseReceiver;
  
  public Job(Dispatcher paramDispatcher, OkHttpClient paramOkHttpClient, Request paramRequest, Response.Receiver paramReceiver)
  {
    dispatcher = paramDispatcher;
    client = paramOkHttpClient;
    request = paramRequest;
    responseReceiver = paramReceiver;
  }
  
  private Response execute()
    throws IOException
  {
    Object localObject3 = null;
    Object localObject1 = null;
    localObject3 = newEngine((Connection)localObject3);
    Object localObject2 = request.body();
    if (localObject2 != null)
    {
      localObject4 = ((Request.Body)localObject2).contentType();
      if (localObject4 == null) {
        throw new IllegalStateException("contentType == null");
      }
      if (((HttpEngine)localObject3).getRequestHeaders().getContentType() == null) {
        ((HttpEngine)localObject3).getRequestHeaders().setContentType(((MediaType)localObject4).toString());
      }
    }
    ((HttpEngine)localObject3).sendRequest();
    if (localObject2 != null) {
      ((Request.Body)localObject2).writeTo(((HttpEngine)localObject3).getRequestBody());
    }
    ((HttpEngine)localObject3).readResponse();
    int i = ((HttpEngine)localObject3).getResponseCode();
    localObject2 = new Dispatcher.RealResponseBody(((HttpEngine)localObject3).getResponseHeaders(), ((HttpEngine)localObject3).getResponseBody());
    localObject2 = new Response.Builder(request, i).rawHeaders(((HttpEngine)localObject3).getResponseHeaders().getHeaders()).body((Response.Body)localObject2).redirectedBy((Response)localObject1).build();
    Object localObject4 = processResponse((HttpEngine)localObject3, (Response)localObject2);
    if (localObject4 == null)
    {
      ((HttpEngine)localObject3).automaticallyReleaseConnectionToPool();
      return (Response)localObject2;
    }
    if (sameConnection(request, (Request)localObject4)) {}
    for (localObject1 = ((HttpEngine)localObject3).getConnection();; localObject1 = null)
    {
      request = ((Request)localObject4);
      localObject3 = localObject1;
      localObject1 = localObject2;
      break;
    }
  }
  
  private Request processResponse(HttpEngine paramHttpEngine, Response paramResponse)
    throws IOException
  {
    Request localRequest = paramResponse.request();
    int i;
    if (paramHttpEngine.getConnection() != null)
    {
      paramHttpEngine = paramHttpEngine.getConnection().getRoute().getProxy();
      i = paramResponse.code();
      switch (i)
      {
      }
    }
    do
    {
      do
      {
        do
        {
          return null;
          paramHttpEngine = client.getProxy();
          break;
          if (paramHttpEngine.type() != Proxy.Type.HTTP) {
            throw new ProtocolException("Received HTTP_PROXY_AUTH (407) code while not using proxy");
          }
          RawHeaders localRawHeaders = localRequest.rawHeaders();
          if (HttpAuthenticator.processAuthHeader(client.getAuthenticator(), paramResponse.code(), paramResponse.rawHeaders(), localRawHeaders, paramHttpEngine, request.url())) {}
          for (paramHttpEngine = localRequest.newBuilder().rawHeaders(localRawHeaders).build();; paramHttpEngine = null) {
            return paramHttpEngine;
          }
          paramHttpEngine = localRequest.method();
        } while ((i == 307) && (!paramHttpEngine.equals("GET")) && (!paramHttpEngine.equals("HEAD")));
        paramHttpEngine = paramResponse.header("Location");
      } while (paramHttpEngine == null);
      paramHttpEngine = new URL(localRequest.url(), paramHttpEngine);
    } while ((!paramHttpEngine.getProtocol().equals("https")) && (!paramHttpEngine.getProtocol().equals("http")));
    return request.newBuilder().url(paramHttpEngine).build();
  }
  
  private boolean sameConnection(Request paramRequest1, Request paramRequest2)
  {
    return (paramRequest1.url().getHost().equals(paramRequest2.url().getHost())) && (Util.getEffectivePort(paramRequest1.url()) == Util.getEffectivePort(paramRequest2.url())) && (paramRequest1.url().getProtocol().equals(paramRequest2.url().getProtocol()));
  }
  
  public int getChunkLength()
  {
    if (request.body().contentLength() == -1L) {
      return 1024;
    }
    return -1;
  }
  
  public long getFixedContentLength()
  {
    return request.body().contentLength();
  }
  
  public HttpURLConnection getHttpConnectionToCache()
  {
    return null;
  }
  
  public long getIfModifiedSince()
  {
    return 0L;
  }
  
  public URL getURL()
  {
    return request.url();
  }
  
  public boolean getUseCaches()
  {
    return false;
  }
  
  HttpEngine newEngine(Connection paramConnection)
    throws IOException
  {
    String str = request.url().getProtocol();
    RawHeaders localRawHeaders = request.rawHeaders();
    if (str.equals("http")) {
      return new HttpEngine(client, this, request.method(), localRawHeaders, paramConnection, null);
    }
    if (str.equals("https")) {
      return new HttpsEngine(client, this, request.method(), localRawHeaders, paramConnection, null);
    }
    throw new AssertionError();
  }
  
  public void run()
  {
    try
    {
      Response localResponse = execute();
      responseReceiver.onResponse(localResponse);
      return;
    }
    catch (IOException localIOException)
    {
      responseReceiver.onFailure(new Failure.Builder().request(request).exception(localIOException).build());
      return;
    }
    finally
    {
      dispatcher.finished(this);
    }
  }
  
  public void setSelectedProxy(Proxy paramProxy) {}
  
  Object tag()
  {
    return request.tag();
  }
  
  public boolean usingProxy()
  {
    return false;
  }
}

/* Location:
 * Qualified Name:     com.squareup.okhttp.Job
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */