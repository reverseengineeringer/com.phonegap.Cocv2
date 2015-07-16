package com.squareup.okhttp;

import com.squareup.okhttp.internal.Util;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ConnectionPool
{
  private static final long DEFAULT_KEEP_ALIVE_DURATION_MS = 300000L;
  private static final int MAX_CONNECTIONS_TO_CLEANUP = 2;
  private static final ConnectionPool systemDefault;
  private final LinkedList<Connection> connections = new LinkedList();
  private final Callable<Void> connectionsCleanupCallable = new Callable()
  {
    public Void call()
      throws Exception
    {
      ArrayList localArrayList = new ArrayList(2);
      int i = 0;
      synchronized (ConnectionPool.this)
      {
        ListIterator localListIterator = connections.listIterator(connections.size());
        for (;;)
        {
          Connection localConnection;
          if (localListIterator.hasPrevious())
          {
            localConnection = (Connection)localListIterator.previous();
            if ((!localConnection.isAlive()) || (localConnection.isExpired(keepAliveDurationNs)))
            {
              localListIterator.remove();
              localArrayList.add(localConnection);
              if (localArrayList.size() != 2) {
                continue;
              }
            }
          }
          else
          {
            localListIterator = connections.listIterator(connections.size());
            while ((localListIterator.hasPrevious()) && (i > maxIdleConnections))
            {
              localConnection = (Connection)localListIterator.previous();
              if (localConnection.isIdle())
              {
                localArrayList.add(localConnection);
                localListIterator.remove();
                i -= 1;
              }
            }
          }
          if (localConnection.isIdle()) {
            i += 1;
          }
        }
        ??? = localArrayList.iterator();
        if (((Iterator)???).hasNext()) {
          Util.closeQuietly((Connection)((Iterator)???).next());
        }
      }
      return null;
    }
  };
  private final ExecutorService executorService = new ThreadPoolExecutor(0, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), Util.daemonThreadFactory("OkHttp ConnectionPool"));
  private final long keepAliveDurationNs;
  private final int maxIdleConnections;
  
  static
  {
    String str1 = System.getProperty("http.keepAlive");
    String str2 = System.getProperty("http.keepAliveDuration");
    String str3 = System.getProperty("http.maxConnections");
    if (str2 != null) {}
    for (long l = Long.parseLong(str2); (str1 != null) && (!Boolean.parseBoolean(str1)); l = 300000L)
    {
      systemDefault = new ConnectionPool(0, l);
      return;
    }
    if (str3 != null)
    {
      systemDefault = new ConnectionPool(Integer.parseInt(str3), l);
      return;
    }
    systemDefault = new ConnectionPool(5, l);
  }
  
  public ConnectionPool(int paramInt, long paramLong)
  {
    maxIdleConnections = paramInt;
    keepAliveDurationNs = (paramLong * 1000L * 1000L);
  }
  
  public static ConnectionPool getDefault()
  {
    return systemDefault;
  }
  
  private void waitForCleanupCallableToRun()
  {
    try
    {
      executorService.submit(new Runnable()
      {
        public void run() {}
      }).get();
      return;
    }
    catch (Exception localException)
    {
      throw new AssertionError();
    }
  }
  
  public void evictAll()
  {
    try
    {
      Object localObject1 = new ArrayList(connections);
      connections.clear();
      localObject1 = ((List)localObject1).iterator();
      while (((Iterator)localObject1).hasNext()) {
        Util.closeQuietly((Connection)((Iterator)localObject1).next());
      }
      return;
    }
    finally {}
  }
  
  /* Error */
  public Connection get(Address paramAddress)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aconst_null
    //   3: astore 4
    //   5: aload_0
    //   6: getfield 73	com/squareup/okhttp/ConnectionPool:connections	Ljava/util/LinkedList;
    //   9: aload_0
    //   10: getfield 73	com/squareup/okhttp/ConnectionPool:connections	Ljava/util/LinkedList;
    //   13: invokevirtual 174	java/util/LinkedList:size	()I
    //   16: invokevirtual 178	java/util/LinkedList:listIterator	(I)Ljava/util/ListIterator;
    //   19: astore 5
    //   21: aload 4
    //   23: astore_3
    //   24: aload 5
    //   26: invokeinterface 183 1 0
    //   31: ifeq +77 -> 108
    //   34: aload 5
    //   36: invokeinterface 186 1 0
    //   41: checkcast 163	com/squareup/okhttp/Connection
    //   44: astore_3
    //   45: aload_3
    //   46: invokevirtual 190	com/squareup/okhttp/Connection:getRoute	()Lcom/squareup/okhttp/Route;
    //   49: invokevirtual 196	com/squareup/okhttp/Route:getAddress	()Lcom/squareup/okhttp/Address;
    //   52: aload_1
    //   53: invokevirtual 202	com/squareup/okhttp/Address:equals	(Ljava/lang/Object;)Z
    //   56: ifeq -35 -> 21
    //   59: aload_3
    //   60: invokevirtual 205	com/squareup/okhttp/Connection:isAlive	()Z
    //   63: ifeq -42 -> 21
    //   66: invokestatic 209	java/lang/System:nanoTime	()J
    //   69: aload_3
    //   70: invokevirtual 212	com/squareup/okhttp/Connection:getIdleStartTimeNs	()J
    //   73: lsub
    //   74: aload_0
    //   75: getfield 110	com/squareup/okhttp/ConnectionPool:keepAliveDurationNs	J
    //   78: lcmp
    //   79: ifge -58 -> 21
    //   82: aload 5
    //   84: invokeinterface 215 1 0
    //   89: aload_3
    //   90: invokevirtual 218	com/squareup/okhttp/Connection:isSpdy	()Z
    //   93: istore_2
    //   94: iload_2
    //   95: ifne +13 -> 108
    //   98: invokestatic 223	com/squareup/okhttp/internal/Platform:get	()Lcom/squareup/okhttp/internal/Platform;
    //   101: aload_3
    //   102: invokevirtual 227	com/squareup/okhttp/Connection:getSocket	()Ljava/net/Socket;
    //   105: invokevirtual 231	com/squareup/okhttp/internal/Platform:tagSocket	(Ljava/net/Socket;)V
    //   108: aload_3
    //   109: ifnull +18 -> 127
    //   112: aload_3
    //   113: invokevirtual 218	com/squareup/okhttp/Connection:isSpdy	()Z
    //   116: ifeq +11 -> 127
    //   119: aload_0
    //   120: getfield 73	com/squareup/okhttp/ConnectionPool:connections	Ljava/util/LinkedList;
    //   123: aload_3
    //   124: invokevirtual 235	java/util/LinkedList:addFirst	(Ljava/lang/Object;)V
    //   127: aload_0
    //   128: getfield 99	com/squareup/okhttp/ConnectionPool:executorService	Ljava/util/concurrent/ExecutorService;
    //   131: aload_0
    //   132: getfield 104	com/squareup/okhttp/ConnectionPool:connectionsCleanupCallable	Ljava/util/concurrent/Callable;
    //   135: invokeinterface 238 2 0
    //   140: pop
    //   141: aload_0
    //   142: monitorexit
    //   143: aload_3
    //   144: areturn
    //   145: astore 6
    //   147: aload_3
    //   148: invokestatic 167	com/squareup/okhttp/internal/Util:closeQuietly	(Ljava/io/Closeable;)V
    //   151: invokestatic 223	com/squareup/okhttp/internal/Platform:get	()Lcom/squareup/okhttp/internal/Platform;
    //   154: new 240	java/lang/StringBuilder
    //   157: dup
    //   158: invokespecial 241	java/lang/StringBuilder:<init>	()V
    //   161: ldc -13
    //   163: invokevirtual 247	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   166: aload 6
    //   168: invokevirtual 250	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   171: invokevirtual 254	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   174: invokevirtual 258	com/squareup/okhttp/internal/Platform:logW	(Ljava/lang/String;)V
    //   177: goto -156 -> 21
    //   180: astore_1
    //   181: aload_0
    //   182: monitorexit
    //   183: aload_1
    //   184: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	185	0	this	ConnectionPool
    //   0	185	1	paramAddress	Address
    //   93	2	2	bool	boolean
    //   23	125	3	localObject1	Object
    //   3	19	4	localObject2	Object
    //   19	64	5	localListIterator	ListIterator
    //   145	22	6	localSocketException	java.net.SocketException
    // Exception table:
    //   from	to	target	type
    //   98	108	145	java/net/SocketException
    //   5	21	180	finally
    //   24	94	180	finally
    //   98	108	180	finally
    //   112	127	180	finally
    //   127	141	180	finally
    //   147	177	180	finally
  }
  
  public int getConnectionCount()
  {
    try
    {
      int i = connections.size();
      return i;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  List<Connection> getConnections()
  {
    waitForCleanupCallableToRun();
    try
    {
      ArrayList localArrayList = new ArrayList(connections);
      return localArrayList;
    }
    finally {}
  }
  
  public int getHttpConnectionCount()
  {
    int i = 0;
    try
    {
      Iterator localIterator = connections.iterator();
      while (localIterator.hasNext())
      {
        boolean bool = ((Connection)localIterator.next()).isSpdy();
        if (!bool) {
          i += 1;
        }
      }
      return i;
    }
    finally {}
  }
  
  public int getSpdyConnectionCount()
  {
    int i = 0;
    try
    {
      Iterator localIterator = connections.iterator();
      while (localIterator.hasNext())
      {
        boolean bool = ((Connection)localIterator.next()).isSpdy();
        if (bool) {
          i += 1;
        }
      }
      return i;
    }
    finally {}
  }
  
  public void maybeShare(Connection paramConnection)
  {
    executorService.submit(connectionsCleanupCallable);
    if (!paramConnection.isSpdy()) {}
    while (!paramConnection.isAlive()) {
      return;
    }
    try
    {
      connections.addFirst(paramConnection);
      return;
    }
    finally {}
  }
  
  /* Error */
  public void recycle(Connection paramConnection)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual 218	com/squareup/okhttp/Connection:isSpdy	()Z
    //   4: ifeq +4 -> 8
    //   7: return
    //   8: aload_1
    //   9: invokevirtual 205	com/squareup/okhttp/Connection:isAlive	()Z
    //   12: ifne +8 -> 20
    //   15: aload_1
    //   16: invokestatic 167	com/squareup/okhttp/internal/Util:closeQuietly	(Ljava/io/Closeable;)V
    //   19: return
    //   20: invokestatic 223	com/squareup/okhttp/internal/Platform:get	()Lcom/squareup/okhttp/internal/Platform;
    //   23: aload_1
    //   24: invokevirtual 227	com/squareup/okhttp/Connection:getSocket	()Ljava/net/Socket;
    //   27: invokevirtual 274	com/squareup/okhttp/internal/Platform:untagSocket	(Ljava/net/Socket;)V
    //   30: aload_0
    //   31: monitorenter
    //   32: aload_0
    //   33: getfield 73	com/squareup/okhttp/ConnectionPool:connections	Ljava/util/LinkedList;
    //   36: aload_1
    //   37: invokevirtual 235	java/util/LinkedList:addFirst	(Ljava/lang/Object;)V
    //   40: aload_1
    //   41: invokevirtual 277	com/squareup/okhttp/Connection:resetIdleStartTime	()V
    //   44: aload_0
    //   45: monitorexit
    //   46: aload_0
    //   47: getfield 99	com/squareup/okhttp/ConnectionPool:executorService	Ljava/util/concurrent/ExecutorService;
    //   50: aload_0
    //   51: getfield 104	com/squareup/okhttp/ConnectionPool:connectionsCleanupCallable	Ljava/util/concurrent/Callable;
    //   54: invokeinterface 238 2 0
    //   59: pop
    //   60: return
    //   61: astore_2
    //   62: invokestatic 223	com/squareup/okhttp/internal/Platform:get	()Lcom/squareup/okhttp/internal/Platform;
    //   65: new 240	java/lang/StringBuilder
    //   68: dup
    //   69: invokespecial 241	java/lang/StringBuilder:<init>	()V
    //   72: ldc_w 279
    //   75: invokevirtual 247	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   78: aload_2
    //   79: invokevirtual 250	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   82: invokevirtual 254	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   85: invokevirtual 258	com/squareup/okhttp/internal/Platform:logW	(Ljava/lang/String;)V
    //   88: aload_1
    //   89: invokestatic 167	com/squareup/okhttp/internal/Util:closeQuietly	(Ljava/io/Closeable;)V
    //   92: return
    //   93: astore_1
    //   94: aload_0
    //   95: monitorexit
    //   96: aload_1
    //   97: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	98	0	this	ConnectionPool
    //   0	98	1	paramConnection	Connection
    //   61	18	2	localSocketException	java.net.SocketException
    // Exception table:
    //   from	to	target	type
    //   20	30	61	java/net/SocketException
    //   32	46	93	finally
    //   94	96	93	finally
  }
}

/* Location:
 * Qualified Name:     com.squareup.okhttp.ConnectionPool
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */