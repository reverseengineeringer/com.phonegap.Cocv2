package com.squareup.okhttp;

import com.squareup.okhttp.internal.DiskLruCache.Editor;
import com.squareup.okhttp.internal.Util;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.CacheRequest;

final class HttpResponseCache$CacheRequestImpl
  extends CacheRequest
{
  private OutputStream body;
  private OutputStream cacheOut;
  private boolean done;
  private final DiskLruCache.Editor editor;
  
  public HttpResponseCache$CacheRequestImpl(HttpResponseCache paramHttpResponseCache, final DiskLruCache.Editor paramEditor)
    throws IOException
  {
    editor = paramEditor;
    cacheOut = paramEditor.newOutputStream(1);
    body = new FilterOutputStream(cacheOut)
    {
      public void close()
        throws IOException
      {
        synchronized (this$0)
        {
          if (done) {
            return;
          }
          done = true;
          HttpResponseCache localHttpResponseCache2 = this$0;
          HttpResponseCache.access$5(localHttpResponseCache2, HttpResponseCache.access$4(localHttpResponseCache2) + 1);
          super.close();
          paramEditor.commit();
          return;
        }
      }
      
      public void write(byte[] paramAnonymousArrayOfByte, int paramAnonymousInt1, int paramAnonymousInt2)
        throws IOException
      {
        out.write(paramAnonymousArrayOfByte, paramAnonymousInt1, paramAnonymousInt2);
      }
    };
  }
  
  public void abort()
  {
    synchronized (this$0)
    {
      if (done) {
        return;
      }
      done = true;
      HttpResponseCache localHttpResponseCache2 = this$0;
      HttpResponseCache.access$7(localHttpResponseCache2, HttpResponseCache.access$6(localHttpResponseCache2) + 1);
      Util.closeQuietly(cacheOut);
      try
      {
        editor.abort();
        return;
      }
      catch (IOException localIOException) {}
    }
  }
  
  public OutputStream getBody()
    throws IOException
  {
    return body;
  }
}

/* Location:
 * Qualified Name:     com.squareup.okhttp.HttpResponseCache.CacheRequestImpl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */