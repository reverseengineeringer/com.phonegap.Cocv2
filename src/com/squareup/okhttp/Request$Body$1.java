package com.squareup.okhttp;

import java.io.IOException;
import java.io.OutputStream;

class Request$Body$1
  extends Request.Body
{
  Request$Body$1(MediaType paramMediaType, byte[] paramArrayOfByte) {}
  
  public long contentLength()
  {
    return val$content.length;
  }
  
  public MediaType contentType()
  {
    return val$contentType;
  }
  
  public void writeTo(OutputStream paramOutputStream)
    throws IOException
  {
    paramOutputStream.write(val$content);
  }
}

/* Location:
 * Qualified Name:     com.squareup.okhttp.Request.Body.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */