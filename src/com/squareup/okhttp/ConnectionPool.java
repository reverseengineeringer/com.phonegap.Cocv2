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
      for (;;)
      {
        synchronized (ConnectionPool.this)
        {
          ListIterator localListIterator = connections.listIterator(connections.size());
          if (!localListIterator.hasPrevious())
          {
            localListIterator = connections.listIterator(connections.size());
            if ((!localListIterator.hasPrevious()) || (i <= maxIdleConnections))
            {
              ??? = localArrayList.iterator();
              if (((Iterator)???).hasNext()) {
                break label240;
              }
              return null;
            }
          }
          else
          {
            localConnection = (Connection)localListIterator.previous();
            if ((!localConnection.isAlive()) || (localConnection.isExpired(keepAliveDurationNs)))
            {
              localListIterator.remove();
              localArrayList.add(localConnection);
              if (localArrayList.size() != 2) {
                continue;
              }
              continue;
            }
            if (!localConnection.isIdle()) {
              continue;
            }
            i += 1;
            continue;
          }
          Connection localConnection = (Connection)localListIterator.previous();
          if (!localConnection.isIdle()) {
            continue;
          }
          localArrayList.add(localConnection);
          localListIterator.remove();
          i -= 1;
        }
        label240:
        Util.closeQuietly((Connection)((Iterator)???).next());
      }
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
    for (;;)
    {
      try
      {
        Object localObject1 = new ArrayList(connections);
        connections.clear();
        localObject1 = ((List)localObject1).iterator();
        if (!((Iterator)localObject1).hasNext()) {
          return;
        }
      }
      finally {}
      Util.closeQuietly((Connection)((Iterator)localObject2).next());
    }
  }
  
  /* Error */
  public Connection get(Address paramAddress)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aconst_null
    //   3: astore_3
    //   4: aload_0
    //   5: getfield 73	com/squareup/okhttp/ConnectionPool:connections	Ljava/util/LinkedList;
    //   8: aload_0
    //   9: getfield 73	com/squareup/okhttp/ConnectionPool:connections	Ljava/util/LinkedList;
    //   12: invokevirtual 174	java/util/LinkedList:size	()I
    //   15: invokevirtual 178	java/util/LinkedList:listIterator	(I)Ljava/util/ListIterator;
    //   18: astore 5
    //   20: aload 5
    //   22: invokeinterface 183 1 0
    //   27: ifne +42 -> 69
    //   30: aload_3
    //   31: astore_1
    //   32: aload_1
    //   33: ifnull +18 -> 51
    //   36: aload_1
    //   37: invokevirtual 186	com/squareup/okhttp/Connection:isSpdy	()Z
    //   40: ifeq +11 -> 51
    //   43: aload_0
    //   44: getfield 73	com/squareup/okhttp/ConnectionPool:connections	Ljava/util/LinkedList;
    //   47: aload_1
    //   48: invokevirtual 190	java/util/LinkedList:addFirst	(Ljava/lang/Object;)V
    //   51: aload_0
    //   52: getfield 99	com/squareup/okhttp/ConnectionPool:executorService	Ljava/util/concurrent/ExecutorService;
    //   55: aload_0
    //   56: getfield 104	com/squareup/okhttp/ConnectionPool:connectionsCleanupCallable	Ljava/util/concurrent/Callable;
    //   59: invokeinterface 193 2 0
    //   64: pop
    //   65: aload_0
    //   66: monitorexit
    //   67: aload_1
    //   68: areturn
    //   69: aload 5
    //   71: invokeinterface 196 1 0
    //   76: checkcast 163	com/squareup/okhttp/Connection
    //   79: astore 4
    //   81: aload 4
    //   83: invokevirtual 200	com/squareup/okhttp/Connection:getRoute	()Lcom/squareup/okhttp/Route;
    //   86: invokevirtual 206	com/squareup/okhttp/Route:getAddress	()Lcom/squareup/okhttp/Address;
    //   89: aload_1
    //   90: invokevirtual 212	com/squareup/okhttp/Address:equals	(Ljava/lang/Object;)Z
    //   93: ifeq -73 -> 20
    //   96: aload 4
    //   98: invokevirtual 215	com/squareup/okhttp/Connection:isAlive	()Z
    //   101: ifeq -81 -> 20
    //   104: invokestatic 219	java/lang/System:nanoTime	()J
    //   107: aload 4
    //   109: invokevirtual 222	com/squareup/okhttp/Connection:getIdleStartTimeNs	()J
    //   112: lsub
    //   113: aload_0
    //   114: getfield 110	com/squareup/okhttp/ConnectionPool:keepAliveDurationNs	J
    //   117: lcmp
    //   118: ifge -98 -> 20
    //   121: aload 5
    //   123: invokeinterface 225 1 0
    //   128: aload 4
    //   130: invokevirtual 186	com/squareup/okhttp/Connection:isSpdy	()Z
    //   133: istore_2
    //   134: iload_2
    //   135: ifne +14 -> 149
    //   138: invokestatic 230	com/squareup/okhttp/internal/Platform:get	()Lcom/squareup/okhttp/internal/Platform;
    //   141: aload 4
    //   143: invokevirtual 234	com/squareup/okhttp/Connection:getSocket	()Ljava/net/Socket;
    //   146: invokevirtual 238	com/squareup/okhttp/internal/Platform:tagSocket	(Ljava/net/Socket;)V
    //   149: aload 4
    //   151: astore_1
    //   152: goto -120 -> 32
    //   155: astore 6
    //   157: aload 4
    //   159: invokestatic 167	com/squareup/okhttp/internal/Util:closeQuietly	(Ljava/io/Closeable;)V
    //   162: invokestatic 230	com/squareup/okhttp/internal/Platform:get	()Lcom/squareup/okhttp/internal/Platform;
    //   165: new 240	java/lang/StringBuilder
    //   168: dup
    //   169: ldc -14
    //   171: invokespecial 245	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   174: aload 6
    //   176: invokevirtual 249	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   179: invokevirtual 253	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   182: invokevirtual 256	com/squareup/okhttp/internal/Platform:logW	(Ljava/lang/String;)V
    //   185: goto -165 -> 20
    //   188: astore_1
    //   189: aload_0
    //   190: monitorexit
    //   191: aload_1
    //   192: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	193	0	this	ConnectionPool
    //   0	193	1	paramAddress	Address
    //   133	2	2	bool	boolean
    //   3	28	3	localObject	Object
    //   79	79	4	localConnection	Connection
    //   18	104	5	localListIterator	ListIterator
    //   155	20	6	localSocketException	java.net.SocketException
    // Exception table:
    //   from	to	target	type
    //   138	149	155	java/net/SocketException
    //   4	20	188	finally
    //   20	30	188	finally
    //   36	51	188	finally
    //   51	65	188	finally
    //   69	134	188	finally
    //   138	149	188	finally
    //   157	185	188	finally
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
  
  /* Error */
  public int getHttpConnectionCount()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: iconst_0
    //   3: istore_1
    //   4: aload_0
    //   5: getfield 73	com/squareup/okhttp/ConnectionPool:connections	Ljava/util/LinkedList;
    //   8: invokevirtual 265	java/util/LinkedList:iterator	()Ljava/util/Iterator;
    //   11: astore_3
    //   12: aload_3
    //   13: invokeinterface 158 1 0
    //   18: istore_2
    //   19: iload_2
    //   20: ifne +7 -> 27
    //   23: aload_0
    //   24: monitorexit
    //   25: iload_1
    //   26: ireturn
    //   27: aload_3
    //   28: invokeinterface 161 1 0
    //   33: checkcast 163	com/squareup/okhttp/Connection
    //   36: invokevirtual 186	com/squareup/okhttp/Connection:isSpdy	()Z
    //   39: istore_2
    //   40: iload_2
    //   41: ifne -29 -> 12
    //   44: iload_1
    //   45: iconst_1
    //   46: iadd
    //   47: istore_1
    //   48: goto -36 -> 12
    //   51: astore_3
    //   52: aload_0
    //   53: monitorexit
    //   54: aload_3
    //   55: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	56	0	this	ConnectionPool
    //   3	45	1	i	int
    //   18	23	2	bool	boolean
    //   11	17	3	localIterator	Iterator
    //   51	4	3	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   4	12	51	finally
    //   12	19	51	finally
    //   27	40	51	finally
  }
  
  /* Error */
  public int getSpdyConnectionCount()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: iconst_0
    //   3: istore_1
    //   4: aload_0
    //   5: getfield 73	com/squareup/okhttp/ConnectionPool:connections	Ljava/util/LinkedList;
    //   8: invokevirtual 265	java/util/LinkedList:iterator	()Ljava/util/Iterator;
    //   11: astore_3
    //   12: aload_3
    //   13: invokeinterface 158 1 0
    //   18: istore_2
    //   19: iload_2
    //   20: ifne +7 -> 27
    //   23: aload_0
    //   24: monitorexit
    //   25: iload_1
    //   26: ireturn
    //   27: aload_3
    //   28: invokeinterface 161 1 0
    //   33: checkcast 163	com/squareup/okhttp/Connection
    //   36: invokevirtual 186	com/squareup/okhttp/Connection:isSpdy	()Z
    //   39: istore_2
    //   40: iload_2
    //   41: ifeq -29 -> 12
    //   44: iload_1
    //   45: iconst_1
    //   46: iadd
    //   47: istore_1
    //   48: goto -36 -> 12
    //   51: astore_3
    //   52: aload_0
    //   53: monitorexit
    //   54: aload_3
    //   55: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	56	0	this	ConnectionPool
    //   3	45	1	i	int
    //   18	23	2	bool	boolean
    //   11	17	3	localIterator	Iterator
    //   51	4	3	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   4	12	51	finally
    //   12	19	51	finally
    //   27	40	51	finally
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
    //   1: invokevirtual 186	com/squareup/okhttp/Connection:isSpdy	()Z
    //   4: ifeq +4 -> 8
    //   7: return
    //   8: aload_1
    //   9: invokevirtual 215	com/squareup/okhttp/Connection:isAlive	()Z
    //   12: ifne +8 -> 20
    //   15: aload_1
    //   16: invokestatic 167	com/squareup/okhttp/internal/Util:closeQuietly	(Ljava/io/Closeable;)V
    //   19: return
    //   20: invokestatic 230	com/squareup/okhttp/internal/Platform:get	()Lcom/squareup/okhttp/internal/Platform;
    //   23: aload_1
    //   24: invokevirtual 234	com/squareup/okhttp/Connection:getSocket	()Ljava/net/Socket;
    //   27: invokevirtual 272	com/squareup/okhttp/internal/Platform:untagSocket	(Ljava/net/Socket;)V
    //   30: aload_0
    //   31: monitorenter
    //   32: aload_0
    //   33: getfield 73	com/squareup/okhttp/ConnectionPool:connections	Ljava/util/LinkedList;
    //   36: aload_1
    //   37: invokevirtual 190	java/util/LinkedList:addFirst	(Ljava/lang/Object;)V
    //   40: aload_1
    //   41: invokevirtual 275	com/squareup/okhttp/Connection:resetIdleStartTime	()V
    //   44: aload_0
    //   45: monitorexit
    //   46: aload_0
    //   47: getfield 99	com/squareup/okhttp/ConnectionPool:executorService	Ljava/util/concurrent/ExecutorService;
    //   50: aload_0
    //   51: getfield 104	com/squareup/okhttp/ConnectionPool:connectionsCleanupCallable	Ljava/util/concurrent/Callable;
    //   54: invokeinterface 193 2 0
    //   59: pop
    //   60: return
    //   61: astore_2
    //   62: invokestatic 230	com/squareup/okhttp/internal/Platform:get	()Lcom/squareup/okhttp/internal/Platform;
    //   65: new 240	java/lang/StringBuilder
    //   68: dup
    //   69: ldc_w 277
    //   72: invokespecial 245	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   75: aload_2
    //   76: invokevirtual 249	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   79: invokevirtual 253	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   82: invokevirtual 256	com/squareup/okhttp/internal/Platform:logW	(Ljava/lang/String;)V
    //   85: aload_1
    //   86: invokestatic 167	com/squareup/okhttp/internal/Util:closeQuietly	(Ljava/io/Closeable;)V
    //   89: return
    //   90: astore_1
    //   91: aload_0
    //   92: monitorexit
    //   93: aload_1
    //   94: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	95	0	this	ConnectionPool
    //   0	95	1	paramConnection	Connection
    //   61	15	2	localSocketException	java.net.SocketException
    // Exception table:
    //   from	to	target	type
    //   20	30	61	java/net/SocketException
    //   32	46	90	finally
    //   91	93	90	finally
  }
}

/* Location:
 * Qualified Name:     com.squareup.okhttp.ConnectionPool
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */