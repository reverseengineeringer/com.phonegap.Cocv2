package com.squareup.okhttp;

import com.squareup.okhttp.internal.http.RawHeaders;

public class Response$Builder
{
  private Response.Body body;
  private final int code;
  private RawHeaders headers = new RawHeaders();
  private Response redirectedBy;
  private final Request request;
  
  public Response$Builder(Request paramRequest, int paramInt)
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

/* Location:
 * Qualified Name:     com.squareup.okhttp.Response.Builder
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */