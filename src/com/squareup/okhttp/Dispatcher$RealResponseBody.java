package com.squareup.okhttp;

import com.squareup.okhttp.internal.http.ResponseHeaders;
import java.io.IOException;
import java.io.InputStream;

class Dispatcher$RealResponseBody
  extends Response.Body
{
  private final InputStream in;
  private final ResponseHeaders responseHeaders;
  
  Dispatcher$RealResponseBody(ResponseHeaders paramResponseHeaders, InputStream paramInputStream)
  {
    responseHeaders = paramResponseHeaders;
    in = paramInputStream;
  }
  
  public InputStream byteStream()
    throws IOException
  {
    return in;
  }
  
  public long contentLength()
  {
    return responseHeaders.getContentLength();
  }
  
  public MediaType contentType()
  {
    String str = responseHeaders.getContentType();
    if (str != null) {
      return MediaType.parse(str);
    }
    return null;
  }
  
  public boolean ready()
    throws IOException
  {
    return true;
  }
}

/* Location:
 * Qualified Name:     com.squareup.okhttp.Dispatcher.RealResponseBody
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */