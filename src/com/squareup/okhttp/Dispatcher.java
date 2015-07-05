package com.squareup.okhttp;

import com.squareup.okhttp.internal.http.ResponseHeaders;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

final class Dispatcher
{
  private final Map<Object, List<Job>> enqueuedJobs = new LinkedHashMap();
  private final ThreadPoolExecutor executorService = new ThreadPoolExecutor(8, 8, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue());
  
  /* Error */
  public void cancel(Object paramObject)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 39	com/squareup/okhttp/Dispatcher:enqueuedJobs	Ljava/util/Map;
    //   6: aload_1
    //   7: invokeinterface 48 2 0
    //   12: checkcast 50	java/util/List
    //   15: astore_1
    //   16: aload_1
    //   17: ifnonnull +6 -> 23
    //   20: aload_0
    //   21: monitorexit
    //   22: return
    //   23: aload_1
    //   24: invokeinterface 54 1 0
    //   29: astore_1
    //   30: aload_1
    //   31: invokeinterface 60 1 0
    //   36: ifeq -16 -> 20
    //   39: aload_1
    //   40: invokeinterface 64 1 0
    //   45: checkcast 66	com/squareup/okhttp/Job
    //   48: astore_2
    //   49: aload_0
    //   50: getfield 34	com/squareup/okhttp/Dispatcher:executorService	Ljava/util/concurrent/ThreadPoolExecutor;
    //   53: aload_2
    //   54: invokevirtual 69	java/util/concurrent/ThreadPoolExecutor:remove	(Ljava/lang/Runnable;)Z
    //   57: pop
    //   58: goto -28 -> 30
    //   61: astore_1
    //   62: aload_0
    //   63: monitorexit
    //   64: aload_1
    //   65: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	66	0	this	Dispatcher
    //   0	66	1	paramObject	Object
    //   48	6	2	localJob	Job
    // Exception table:
    //   from	to	target	type
    //   2	16	61	finally
    //   23	30	61	finally
    //   30	58	61	finally
  }
  
  public void enqueue(OkHttpClient paramOkHttpClient, Request paramRequest, Response.Receiver paramReceiver)
  {
    try
    {
      Job localJob = new Job(this, paramOkHttpClient, paramRequest, paramReceiver);
      paramReceiver = (List)enqueuedJobs.get(paramRequest.tag());
      paramOkHttpClient = paramReceiver;
      if (paramReceiver == null)
      {
        paramOkHttpClient = new ArrayList(2);
        enqueuedJobs.put(paramRequest.tag(), paramOkHttpClient);
      }
      paramOkHttpClient.add(localJob);
      executorService.execute(localJob);
      return;
    }
    finally {}
  }
  
  void finished(Job paramJob)
  {
    try
    {
      List localList = (List)enqueuedJobs.get(paramJob.tag());
      if (localList != null) {
        localList.remove(paramJob);
      }
      return;
    }
    finally {}
  }
  
  static class RealResponseBody
    extends Response.Body
  {
    private final InputStream in;
    private final ResponseHeaders responseHeaders;
    
    RealResponseBody(ResponseHeaders paramResponseHeaders, InputStream paramInputStream)
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
}

/* Location:
 * Qualified Name:     com.squareup.okhttp.Dispatcher
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */