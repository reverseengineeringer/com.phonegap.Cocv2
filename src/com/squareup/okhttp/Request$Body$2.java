package com.squareup.okhttp;

import com.squareup.okhttp.internal.Util;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

final class Request$Body$2
  extends Request.Body
{
  Request$Body$2(MediaType paramMediaType, File paramFile) {}
  
  public long contentLength()
  {
    return val$file.length();
  }
  
  public MediaType contentType()
  {
    return val$contentType;
  }
  
  public void writeTo(OutputStream paramOutputStream)
    throws IOException
  {
    long l = contentLength();
    if (l == 0L) {
      return;
    }
    byte[] arrayOfByte = null;
    try
    {
      localObject1 = new FileInputStream(val$file);
      try
      {
        arrayOfByte = new byte[(int)Math.min(8192L, l)];
        for (;;)
        {
          int i = ((InputStream)localObject1).read(arrayOfByte);
          if (i == -1) {
            break;
          }
          paramOutputStream.write(arrayOfByte, 0, i);
        }
        Util.closeQuietly(paramOutputStream);
      }
      finally
      {
        paramOutputStream = (OutputStream)localObject1;
        localObject1 = localObject3;
      }
    }
    finally
    {
      for (;;)
      {
        Object localObject1;
        paramOutputStream = (OutputStream)localObject3;
      }
    }
    throw ((Throwable)localObject1);
    Util.closeQuietly((Closeable)localObject1);
  }
}

/* Location:
 * Qualified Name:     com.squareup.okhttp.Request.Body.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */