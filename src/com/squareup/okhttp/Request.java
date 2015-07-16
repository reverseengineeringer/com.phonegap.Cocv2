package com.squareup.okhttp;

import com.squareup.okhttp.internal.Util;
import com.squareup.okhttp.internal.http.RawHeaders;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Set;

final class Request
{
  private final Body body;
  private final RawHeaders headers;
  private final String method;
  private final Object tag;
  private final URL url;
  
  private Request(Builder paramBuilder)
  {
    url = url;
    method = method;
    headers = new RawHeaders(headers);
    body = body;
    if (tag != null) {}
    for (paramBuilder = tag;; paramBuilder = this)
    {
      tag = paramBuilder;
      return;
    }
  }
  
  public Body body()
  {
    return body;
  }
  
  public String header(String paramString)
  {
    return headers.get(paramString);
  }
  
  public int headerCount()
  {
    return headers.length();
  }
  
  public String headerName(int paramInt)
  {
    return headers.getFieldName(paramInt);
  }
  
  public Set<String> headerNames()
  {
    return headers.names();
  }
  
  public String headerValue(int paramInt)
  {
    return headers.getValue(paramInt);
  }
  
  public List<String> headers(String paramString)
  {
    return headers.values(paramString);
  }
  
  public String method()
  {
    return method;
  }
  
  Builder newBuilder()
  {
    return new Builder(url).method(method, body).rawHeaders(headers).tag(tag);
  }
  
  RawHeaders rawHeaders()
  {
    return new RawHeaders(headers);
  }
  
  public Object tag()
  {
    return tag;
  }
  
  public URL url()
  {
    return url;
  }
  
  public String urlString()
  {
    return url.toString();
  }
  
  public static abstract class Body
  {
    public static Body create(MediaType paramMediaType, final File paramFile)
    {
      if (paramMediaType == null) {
        throw new NullPointerException("contentType == null");
      }
      if (paramFile == null) {
        throw new NullPointerException("content == null");
      }
      new Body()
      {
        public long contentLength()
        {
          return paramFile.length();
        }
        
        public MediaType contentType()
        {
          return val$contentType;
        }
        
        public void writeTo(OutputStream paramAnonymousOutputStream)
          throws IOException
        {
          long l = contentLength();
          if (l == 0L) {
            return;
          }
          byte[] arrayOfByte = null;
          try
          {
            localObject1 = new FileInputStream(paramFile);
            try
            {
              arrayOfByte = new byte[(int)Math.min(8192L, l)];
              for (;;)
              {
                int i = ((InputStream)localObject1).read(arrayOfByte);
                if (i == -1) {
                  break;
                }
                paramAnonymousOutputStream.write(arrayOfByte, 0, i);
              }
              Util.closeQuietly(paramAnonymousOutputStream);
            }
            finally
            {
              paramAnonymousOutputStream = (OutputStream)localObject1;
              localObject1 = localObject3;
            }
          }
          finally
          {
            for (;;)
            {
              Object localObject1;
              paramAnonymousOutputStream = (OutputStream)localObject3;
            }
          }
          throw ((Throwable)localObject1);
          Util.closeQuietly((Closeable)localObject1);
        }
      };
    }
    
    public static Body create(MediaType paramMediaType, String paramString)
    {
      if (paramMediaType.charset() != null) {}
      for (;;)
      {
        try
        {
          paramMediaType = create(paramMediaType, paramString.getBytes(paramMediaType.charset().name()));
          return paramMediaType;
        }
        catch (UnsupportedEncodingException paramMediaType)
        {
          throw new AssertionError();
        }
        paramMediaType = MediaType.parse(paramMediaType + "; charset=utf-8");
      }
    }
    
    public static Body create(MediaType paramMediaType, final byte[] paramArrayOfByte)
    {
      if (paramMediaType == null) {
        throw new NullPointerException("contentType == null");
      }
      if (paramArrayOfByte == null) {
        throw new NullPointerException("content == null");
      }
      new Body()
      {
        public long contentLength()
        {
          return paramArrayOfByte.length;
        }
        
        public MediaType contentType()
        {
          return val$contentType;
        }
        
        public void writeTo(OutputStream paramAnonymousOutputStream)
          throws IOException
        {
          paramAnonymousOutputStream.write(paramArrayOfByte);
        }
      };
    }
    
    public long contentLength()
    {
      return -1L;
    }
    
    public abstract MediaType contentType();
    
    public abstract void writeTo(OutputStream paramOutputStream)
      throws IOException;
  }
  
  public static class Builder
  {
    private Request.Body body;
    private RawHeaders headers = new RawHeaders();
    private String method = "GET";
    private Object tag;
    private URL url;
    
    public Builder(String paramString)
    {
      url(paramString);
    }
    
    public Builder(URL paramURL)
    {
      url(paramURL);
    }
    
    public Builder addHeader(String paramString1, String paramString2)
    {
      headers.add(paramString1, paramString2);
      return this;
    }
    
    public Request build()
    {
      return new Request(this, null);
    }
    
    public Builder get()
    {
      return method("GET", null);
    }
    
    public Builder head()
    {
      return method("HEAD", null);
    }
    
    public Builder header(String paramString1, String paramString2)
    {
      headers.set(paramString1, paramString2);
      return this;
    }
    
    public Builder method(String paramString, Request.Body paramBody)
    {
      if ((paramString == null) || (paramString.length() == 0)) {
        throw new IllegalArgumentException("method == null || method.length() == 0");
      }
      method = paramString;
      body = paramBody;
      return this;
    }
    
    public Builder post(Request.Body paramBody)
    {
      return method("POST", paramBody);
    }
    
    public Builder put(Request.Body paramBody)
    {
      return method("PUT", paramBody);
    }
    
    Builder rawHeaders(RawHeaders paramRawHeaders)
    {
      headers = new RawHeaders(paramRawHeaders);
      return this;
    }
    
    public Builder tag(Object paramObject)
    {
      tag = paramObject;
      return this;
    }
    
    public Builder url(String paramString)
    {
      try
      {
        url = new URL(paramString);
        return this;
      }
      catch (MalformedURLException localMalformedURLException)
      {
        throw new IllegalArgumentException("Malformed URL: " + paramString);
      }
    }
    
    public Builder url(URL paramURL)
    {
      if (paramURL == null) {
        throw new IllegalStateException("url == null");
      }
      url = paramURL;
      return this;
    }
  }
}

/* Location:
 * Qualified Name:     com.squareup.okhttp.Request
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */