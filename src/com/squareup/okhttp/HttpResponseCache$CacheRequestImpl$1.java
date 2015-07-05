package com.squareup.okhttp;

import com.squareup.okhttp.internal.DiskLruCache.Editor;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

class HttpResponseCache$CacheRequestImpl$1
  extends FilterOutputStream
{
  HttpResponseCache$CacheRequestImpl$1(HttpResponseCache.CacheRequestImpl paramCacheRequestImpl, OutputStream paramOutputStream, DiskLruCache.Editor paramEditor)
  {
    super(paramOutputStream);
  }
  
  public void close()
    throws IOException
  {
    synchronized (HttpResponseCache.CacheRequestImpl.access$2(this$1))
    {
      if (HttpResponseCache.CacheRequestImpl.access$0(this$1)) {
        return;
      }
      HttpResponseCache.CacheRequestImpl.access$1(this$1, true);
      HttpResponseCache localHttpResponseCache2 = HttpResponseCache.CacheRequestImpl.access$2(this$1);
      HttpResponseCache.access$5(localHttpResponseCache2, HttpResponseCache.access$4(localHttpResponseCache2) + 1);
      super.close();
      val$editor.commit();
      return;
    }
  }
  
  public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    out.write(paramArrayOfByte, paramInt1, paramInt2);
  }
}

/* Location:
 * Qualified Name:     com.squareup.okhttp.HttpResponseCache.CacheRequestImpl.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */