package com.squareup.okhttp.internal;

import java.util.concurrent.Callable;

class DiskLruCache$1
  implements Callable<Void>
{
  DiskLruCache$1(DiskLruCache paramDiskLruCache) {}
  
  public Void call()
    throws Exception
  {
    synchronized (this$0)
    {
      if (DiskLruCache.access$0(this$0) == null) {
        return null;
      }
      DiskLruCache.access$1(this$0);
      if (DiskLruCache.access$2(this$0))
      {
        DiskLruCache.access$3(this$0);
        DiskLruCache.access$4(this$0, 0);
      }
      return null;
    }
  }
}

/* Location:
 * Qualified Name:     com.squareup.okhttp.internal.DiskLruCache.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */