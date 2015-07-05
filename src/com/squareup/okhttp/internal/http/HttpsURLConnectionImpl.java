package com.squareup.okhttp.internal.http;

import android.annotation.SuppressLint;
import com.squareup.okhttp.OkHttpClient;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.SecureCacheResponse;
import java.net.URL;
import java.security.Permission;
import java.security.Principal;
import java.security.cert.Certificate;
import java.util.List;
import java.util.Map;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public final class HttpsURLConnectionImpl
  extends HttpsURLConnection
{
  private final HttpUrlConnectionDelegate delegate;
  
  public HttpsURLConnectionImpl(URL paramURL, OkHttpClient paramOkHttpClient)
  {
    super(paramURL);
    delegate = new HttpUrlConnectionDelegate(paramURL, paramOkHttpClient, null);
  }
  
  private SSLSocket getSslSocket()
  {
    if ((delegate.httpEngine == null) || (!delegate.httpEngine.connected)) {
      throw new IllegalStateException("Connection has not yet been established");
    }
    if ((delegate.httpEngine instanceof HttpsEngine)) {
      return ((HttpsEngine)delegate.httpEngine).getSslSocket();
    }
    return null;
  }
  
  public void addRequestProperty(String paramString1, String paramString2)
  {
    delegate.addRequestProperty(paramString1, paramString2);
  }
  
  public void connect()
    throws IOException
  {
    connected = true;
    delegate.connect();
  }
  
  public void disconnect()
  {
    delegate.disconnect();
  }
  
  public boolean getAllowUserInteraction()
  {
    return delegate.getAllowUserInteraction();
  }
  
  public String getCipherSuite()
  {
    Object localObject = delegate.getSecureCacheResponse();
    if (localObject != null) {
      return ((SecureCacheResponse)localObject).getCipherSuite();
    }
    localObject = getSslSocket();
    if (localObject != null) {
      return ((SSLSocket)localObject).getSession().getCipherSuite();
    }
    return null;
  }
  
  public int getConnectTimeout()
  {
    return delegate.getConnectTimeout();
  }
  
  public Object getContent()
    throws IOException
  {
    return delegate.getContent();
  }
  
  public Object getContent(Class[] paramArrayOfClass)
    throws IOException
  {
    return delegate.getContent(paramArrayOfClass);
  }
  
  public String getContentEncoding()
  {
    return delegate.getContentEncoding();
  }
  
  public int getContentLength()
  {
    return delegate.getContentLength();
  }
  
  public String getContentType()
  {
    return delegate.getContentType();
  }
  
  public long getDate()
  {
    return delegate.getDate();
  }
  
  public boolean getDefaultUseCaches()
  {
    return delegate.getDefaultUseCaches();
  }
  
  public boolean getDoInput()
  {
    return delegate.getDoInput();
  }
  
  public boolean getDoOutput()
  {
    return delegate.getDoOutput();
  }
  
  public InputStream getErrorStream()
  {
    return delegate.getErrorStream();
  }
  
  public long getExpiration()
  {
    return delegate.getExpiration();
  }
  
  public String getHeaderField(int paramInt)
  {
    return delegate.getHeaderField(paramInt);
  }
  
  public String getHeaderField(String paramString)
  {
    return delegate.getHeaderField(paramString);
  }
  
  public long getHeaderFieldDate(String paramString, long paramLong)
  {
    return delegate.getHeaderFieldDate(paramString, paramLong);
  }
  
  public int getHeaderFieldInt(String paramString, int paramInt)
  {
    return delegate.getHeaderFieldInt(paramString, paramInt);
  }
  
  public String getHeaderFieldKey(int paramInt)
  {
    return delegate.getHeaderFieldKey(paramInt);
  }
  
  public Map<String, List<String>> getHeaderFields()
  {
    return delegate.getHeaderFields();
  }
  
  public HostnameVerifier getHostnameVerifier()
  {
    return delegate.client.getHostnameVerifier();
  }
  
  public HttpEngine getHttpEngine()
  {
    return delegate.getHttpEngine();
  }
  
  public long getIfModifiedSince()
  {
    return delegate.getIfModifiedSince();
  }
  
  public InputStream getInputStream()
    throws IOException
  {
    return delegate.getInputStream();
  }
  
  public boolean getInstanceFollowRedirects()
  {
    return delegate.getInstanceFollowRedirects();
  }
  
  public long getLastModified()
  {
    return delegate.getLastModified();
  }
  
  public Certificate[] getLocalCertificates()
  {
    Certificate[] arrayOfCertificate = null;
    Object localObject = delegate.getSecureCacheResponse();
    if (localObject != null)
    {
      localObject = ((SecureCacheResponse)localObject).getLocalCertificateChain();
      if (localObject != null) {
        arrayOfCertificate = (Certificate[])((List)localObject).toArray(new Certificate[((List)localObject).size()]);
      }
    }
    do
    {
      return arrayOfCertificate;
      localObject = getSslSocket();
    } while (localObject == null);
    return ((SSLSocket)localObject).getSession().getLocalCertificates();
  }
  
  public Principal getLocalPrincipal()
  {
    Object localObject = delegate.getSecureCacheResponse();
    if (localObject != null) {
      return ((SecureCacheResponse)localObject).getLocalPrincipal();
    }
    localObject = getSslSocket();
    if (localObject != null) {
      return ((SSLSocket)localObject).getSession().getLocalPrincipal();
    }
    return null;
  }
  
  public OutputStream getOutputStream()
    throws IOException
  {
    return delegate.getOutputStream();
  }
  
  public Principal getPeerPrincipal()
    throws SSLPeerUnverifiedException
  {
    Object localObject = delegate.getSecureCacheResponse();
    if (localObject != null) {
      return ((SecureCacheResponse)localObject).getPeerPrincipal();
    }
    localObject = getSslSocket();
    if (localObject != null) {
      return ((SSLSocket)localObject).getSession().getPeerPrincipal();
    }
    return null;
  }
  
  public Permission getPermission()
    throws IOException
  {
    return delegate.getPermission();
  }
  
  public int getReadTimeout()
  {
    return delegate.getReadTimeout();
  }
  
  public String getRequestMethod()
  {
    return delegate.getRequestMethod();
  }
  
  public Map<String, List<String>> getRequestProperties()
  {
    return delegate.getRequestProperties();
  }
  
  public String getRequestProperty(String paramString)
  {
    return delegate.getRequestProperty(paramString);
  }
  
  public int getResponseCode()
    throws IOException
  {
    return delegate.getResponseCode();
  }
  
  public String getResponseMessage()
    throws IOException
  {
    return delegate.getResponseMessage();
  }
  
  public SSLSocketFactory getSSLSocketFactory()
  {
    return delegate.client.getSslSocketFactory();
  }
  
  public Certificate[] getServerCertificates()
    throws SSLPeerUnverifiedException
  {
    Certificate[] arrayOfCertificate = null;
    Object localObject = delegate.getSecureCacheResponse();
    if (localObject != null)
    {
      localObject = ((SecureCacheResponse)localObject).getServerCertificateChain();
      if (localObject != null) {
        arrayOfCertificate = (Certificate[])((List)localObject).toArray(new Certificate[((List)localObject).size()]);
      }
    }
    do
    {
      return arrayOfCertificate;
      localObject = getSslSocket();
    } while (localObject == null);
    return ((SSLSocket)localObject).getSession().getPeerCertificates();
  }
  
  public URL getURL()
  {
    return delegate.getURL();
  }
  
  public boolean getUseCaches()
  {
    return delegate.getUseCaches();
  }
  
  public void setAllowUserInteraction(boolean paramBoolean)
  {
    delegate.setAllowUserInteraction(paramBoolean);
  }
  
  public void setChunkedStreamingMode(int paramInt)
  {
    delegate.setChunkedStreamingMode(paramInt);
  }
  
  public void setConnectTimeout(int paramInt)
  {
    delegate.setConnectTimeout(paramInt);
  }
  
  public void setDefaultUseCaches(boolean paramBoolean)
  {
    delegate.setDefaultUseCaches(paramBoolean);
  }
  
  public void setDoInput(boolean paramBoolean)
  {
    delegate.setDoInput(paramBoolean);
  }
  
  public void setDoOutput(boolean paramBoolean)
  {
    delegate.setDoOutput(paramBoolean);
  }
  
  public void setFixedLengthStreamingMode(int paramInt)
  {
    delegate.setFixedLengthStreamingMode(paramInt);
  }
  
  @SuppressLint({"NewApi"})
  public void setFixedLengthStreamingMode(long paramLong)
  {
    delegate.setFixedLengthStreamingMode(paramLong);
  }
  
  public void setHostnameVerifier(HostnameVerifier paramHostnameVerifier)
  {
    delegate.client.setHostnameVerifier(paramHostnameVerifier);
  }
  
  public void setIfModifiedSince(long paramLong)
  {
    delegate.setIfModifiedSince(paramLong);
  }
  
  public void setInstanceFollowRedirects(boolean paramBoolean)
  {
    delegate.setInstanceFollowRedirects(paramBoolean);
  }
  
  public void setReadTimeout(int paramInt)
  {
    delegate.setReadTimeout(paramInt);
  }
  
  public void setRequestMethod(String paramString)
    throws ProtocolException
  {
    delegate.setRequestMethod(paramString);
  }
  
  public void setRequestProperty(String paramString1, String paramString2)
  {
    delegate.setRequestProperty(paramString1, paramString2);
  }
  
  public void setSSLSocketFactory(SSLSocketFactory paramSSLSocketFactory)
  {
    delegate.client.setSslSocketFactory(paramSSLSocketFactory);
  }
  
  public void setUseCaches(boolean paramBoolean)
  {
    delegate.setUseCaches(paramBoolean);
  }
  
  public String toString()
  {
    return delegate.toString();
  }
  
  public boolean usingProxy()
  {
    return delegate.usingProxy();
  }
  
  private final class HttpUrlConnectionDelegate
    extends HttpURLConnectionImpl
  {
    private HttpUrlConnectionDelegate(URL paramURL, OkHttpClient paramOkHttpClient)
    {
      super(paramOkHttpClient);
    }
    
    public HttpURLConnection getHttpConnectionToCache()
    {
      return HttpsURLConnectionImpl.this;
    }
    
    public SecureCacheResponse getSecureCacheResponse()
    {
      if ((httpEngine instanceof HttpsEngine)) {
        return (SecureCacheResponse)httpEngine.getCacheResponse();
      }
      return null;
    }
  }
}

/* Location:
 * Qualified Name:     com.squareup.okhttp.internal.http.HttpsURLConnectionImpl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */