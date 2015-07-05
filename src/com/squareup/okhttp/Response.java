package com.squareup.okhttp;

import com.squareup.okhttp.internal.Util;
import com.squareup.okhttp.internal.http.RawHeaders;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Set;

final class Response
{
  private final Body body;
  private final int code;
  private final RawHeaders headers;
  private final Response redirectedBy;
  private final Request request;
  
  private Response(Builder paramBuilder)
  {
    request = request;
    code = code;
    headers = new RawHeaders(headers);
    body = body;
    redirectedBy = redirectedBy;
  }
  
  public Body body()
  {
    return body;
  }
  
  public int code()
  {
    return code;
  }
  
  public String header(String paramString)
  {
    return header(paramString, null);
  }
  
  public String header(String paramString1, String paramString2)
  {
    paramString1 = headers.get(paramString1);
    if (paramString1 != null) {
      return paramString1;
    }
    return paramString2;
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
  
  RawHeaders rawHeaders()
  {
    return new RawHeaders(headers);
  }
  
  public Response redirectedBy()
  {
    return redirectedBy;
  }
  
  public Request request()
  {
    return request;
  }
  
  public static abstract class Body
  {
    private Reader reader;
    
    private Charset charset()
    {
      MediaType localMediaType = contentType();
      if (localMediaType != null) {
        return localMediaType.charset(Util.UTF_8);
      }
      return Util.UTF_8;
    }
    
    public abstract InputStream byteStream()
      throws IOException;
    
    public final byte[] bytes()
      throws IOException
    {
      long l = contentLength();
      if (l > 2147483647L) {
        throw new IOException("Cannot buffer entire body for content length: " + l);
      }
      Object localObject;
      if (l != -1L)
      {
        localObject = new byte[(int)l];
        InputStream localInputStream = byteStream();
        Util.readFully(localInputStream, (byte[])localObject);
        if (localInputStream.read() != -1) {
          throw new IOException("Content-Length and stream length disagree");
        }
      }
      else
      {
        localObject = new ByteArrayOutputStream();
        Util.copy(byteStream(), (OutputStream)localObject);
        localObject = ((ByteArrayOutputStream)localObject).toByteArray();
      }
      return (byte[])localObject;
    }
    
    public final Reader charStream()
      throws IOException
    {
      if (reader == null) {
        reader = new InputStreamReader(byteStream(), charset());
      }
      return reader;
    }
    
    public abstract long contentLength();
    
    public abstract MediaType contentType();
    
    public abstract boolean ready()
      throws IOException;
    
    public final String string()
      throws IOException
    {
      return new String(bytes(), charset().name());
    }
  }
  
  public static class Builder
  {
    private Response.Body body;
    private final int code;
    private RawHeaders headers = new RawHeaders();
    private Response redirectedBy;
    private final Request request;
    
    public Builder(Request paramRequest, int paramInt)
    {
      if (paramRequest == null) {
        throw new IllegalArgumentException("request == null");
      }
      if (paramInt <= 0) {
        throw new IllegalArgumentException("code <= 0");
      }
      request = paramRequest;
      code = paramInt;
    }
    
    public Builder addHeader(String paramString1, String paramString2)
    {
      headers.add(paramString1, paramString2);
      return this;
    }
    
    public Builder body(Response.Body paramBody)
    {
      body = paramBody;
      return this;
    }
    
    public Response build()
    {
      if (request == null) {
        throw new IllegalStateException("Response has no request.");
      }
      if (code == -1) {
        throw new IllegalStateException("Response has no code.");
      }
      return new Response(this, null);
    }
    
    public Builder header(String paramString1, String paramString2)
    {
      headers.set(paramString1, paramString2);
      return this;
    }
    
    Builder rawHeaders(RawHeaders paramRawHeaders)
    {
      headers = new RawHeaders(paramRawHeaders);
      return this;
    }
    
    public Builder redirectedBy(Response paramResponse)
    {
      redirectedBy = paramResponse;
      return this;
    }
  }
  
  public static abstract interface Receiver
  {
    public abstract void onFailure(Failure paramFailure);
    
    public abstract boolean onResponse(Response paramResponse)
      throws IOException;
  }
}

/* Location:
 * Qualified Name:     com.squareup.okhttp.Response
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */