package com.squareup.okhttp;

import com.squareup.okhttp.internal.Util;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

public abstract class Request$Body
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

/* Location:
 * Qualified Name:     com.squareup.okhttp.Request.Body
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */