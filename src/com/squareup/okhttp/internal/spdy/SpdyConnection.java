package com.squareup.okhttp.internal.spdy;

import com.squareup.okhttp.internal.NamedRunnable;
import com.squareup.okhttp.internal.Util;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public final class SpdyConnection
  implements Closeable
{
  private static final ExecutorService executor;
  final boolean client;
  private final FrameReader frameReader;
  private final FrameWriter frameWriter;
  private final IncomingStreamHandler handler;
  private final String hostName;
  private long idleStartTimeNs = System.nanoTime();
  private int lastGoodStreamId;
  private int nextPingId;
  private int nextStreamId;
  private Map<Integer, Ping> pings;
  Settings settings;
  private boolean shutdown;
  private final Map<Integer, SpdyStream> streams = new HashMap();
  final Variant variant;
  
  static
  {
    if (!SpdyConnection.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      executor = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue(), Util.daemonThreadFactory("OkHttp SpdyConnection"));
      return;
    }
  }
  
  private SpdyConnection(Builder paramBuilder)
  {
    variant = variant;
    client = client;
    handler = handler;
    frameReader = variant.newReader(in, client);
    frameWriter = variant.newWriter(out, client);
    if (client)
    {
      i = 1;
      nextStreamId = i;
      if (!client) {
        break label173;
      }
    }
    label173:
    for (int i = j;; i = 2)
    {
      nextPingId = i;
      hostName = hostName;
      new Thread(new Reader(null), "Spdy Reader " + hostName).start();
      return;
      i = 2;
      break;
    }
  }
  
  private void close(ErrorCode paramErrorCode1, ErrorCode paramErrorCode2)
    throws IOException
  {
    assert (!Thread.holdsLock(this));
    Object localObject = null;
    try
    {
      shutdown(paramErrorCode1);
      paramErrorCode1 = (ErrorCode)localObject;
    }
    catch (IOException paramErrorCode1)
    {
      SpdyStream[] arrayOfSpdyStream;
      for (;;) {}
    }
    arrayOfSpdyStream = null;
    Ping[] arrayOfPing = null;
    int j;
    int i;
    for (;;)
    {
      try
      {
        if (!streams.isEmpty())
        {
          arrayOfSpdyStream = (SpdyStream[])streams.values().toArray(new SpdyStream[streams.size()]);
          streams.clear();
          setIdle(false);
        }
        if (pings != null)
        {
          arrayOfPing = (Ping[])pings.values().toArray(new Ping[pings.size()]);
          pings = null;
        }
        localObject = paramErrorCode1;
        if (arrayOfSpdyStream == null) {
          break label216;
        }
        j = arrayOfSpdyStream.length;
        i = 0;
        localObject = paramErrorCode1;
        if (i >= j) {
          break label216;
        }
        localObject = arrayOfSpdyStream[i];
      }
      finally {}
      try
      {
        ((SpdyStream)localObject).close(paramErrorCode2);
        localObject = paramErrorCode1;
      }
      catch (IOException localIOException2)
      {
        localObject = paramErrorCode1;
        if (paramErrorCode1 == null) {
          continue;
        }
        localObject = localIOException2;
        continue;
      }
      i += 1;
      paramErrorCode1 = (ErrorCode)localObject;
    }
    label216:
    if (arrayOfPing != null)
    {
      j = arrayOfPing.length;
      i = 0;
      while (i < j)
      {
        arrayOfPing[i].cancel();
        i += 1;
      }
    }
    try
    {
      frameReader.close();
      try
      {
        frameWriter.close();
        paramErrorCode1 = (ErrorCode)localObject;
      }
      catch (IOException paramErrorCode2)
      {
        for (;;)
        {
          paramErrorCode1 = localIOException1;
          if (localIOException1 == null) {
            paramErrorCode1 = paramErrorCode2;
          }
        }
      }
      if (paramErrorCode1 != null) {
        throw paramErrorCode1;
      }
    }
    catch (IOException localIOException1)
    {
      for (;;) {}
    }
  }
  
  private SpdyStream getStream(int paramInt)
  {
    try
    {
      SpdyStream localSpdyStream = (SpdyStream)streams.get(Integer.valueOf(paramInt));
      return localSpdyStream;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  /* Error */
  private Ping removePing(int paramInt)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 277	com/squareup/okhttp/internal/spdy/SpdyConnection:pings	Ljava/util/Map;
    //   6: ifnull +24 -> 30
    //   9: aload_0
    //   10: getfield 277	com/squareup/okhttp/internal/spdy/SpdyConnection:pings	Ljava/util/Map;
    //   13: iload_1
    //   14: invokestatic 299	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   17: invokeinterface 306 2 0
    //   22: checkcast 279	com/squareup/okhttp/internal/spdy/Ping
    //   25: astore_2
    //   26: aload_0
    //   27: monitorexit
    //   28: aload_2
    //   29: areturn
    //   30: aconst_null
    //   31: astore_2
    //   32: goto -6 -> 26
    //   35: astore_2
    //   36: aload_0
    //   37: monitorexit
    //   38: aload_2
    //   39: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	40	0	this	SpdyConnection
    //   0	40	1	paramInt	int
    //   25	7	2	localPing	Ping
    //   35	4	2	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	26	35	finally
  }
  
  private void setIdle(boolean paramBoolean)
  {
    if (paramBoolean) {}
    for (;;)
    {
      try
      {
        l = System.nanoTime();
        idleStartTimeNs = l;
        return;
      }
      finally {}
      long l = Long.MAX_VALUE;
    }
  }
  
  private void writePing(boolean paramBoolean, int paramInt1, int paramInt2, Ping paramPing)
    throws IOException
  {
    FrameWriter localFrameWriter = frameWriter;
    if (paramPing != null) {}
    try
    {
      paramPing.send();
      frameWriter.ping(paramBoolean, paramInt1, paramInt2);
      return;
    }
    finally {}
  }
  
  private void writePingLater(final boolean paramBoolean, final int paramInt1, final int paramInt2, final Ping paramPing)
  {
    executor.submit(new NamedRunnable("OkHttp SPDY Writer %s ping %08x%08x", new Object[] { hostName, Integer.valueOf(paramInt1), Integer.valueOf(paramInt2) })
    {
      public void execute()
      {
        try
        {
          SpdyConnection.this.writePing(paramBoolean, paramInt1, paramInt2, paramPing);
          return;
        }
        catch (IOException localIOException) {}
      }
    });
  }
  
  public void close()
    throws IOException
  {
    close(ErrorCode.NO_ERROR, ErrorCode.CANCEL);
  }
  
  public void flush()
    throws IOException
  {
    frameWriter.flush();
  }
  
  public long getIdleStartTimeNs()
  {
    try
    {
      long l = idleStartTimeNs;
      return l;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  /* Error */
  public boolean isIdle()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 104	com/squareup/okhttp/internal/spdy/SpdyConnection:idleStartTimeNs	J
    //   6: lstore_1
    //   7: lload_1
    //   8: ldc2_w 307
    //   11: lcmp
    //   12: ifeq +9 -> 21
    //   15: iconst_1
    //   16: istore_3
    //   17: aload_0
    //   18: monitorexit
    //   19: iload_3
    //   20: ireturn
    //   21: iconst_0
    //   22: istore_3
    //   23: goto -6 -> 17
    //   26: astore 4
    //   28: aload_0
    //   29: monitorexit
    //   30: aload 4
    //   32: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	33	0	this	SpdyConnection
    //   6	2	1	l	long
    //   16	7	3	bool	boolean
    //   26	5	4	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	7	26	finally
  }
  
  public SpdyStream newStream(List<String> paramList, boolean paramBoolean1, boolean paramBoolean2)
    throws IOException
  {
    if (!paramBoolean1)
    {
      paramBoolean1 = true;
      if (paramBoolean2) {
        break label57;
      }
    }
    label57:
    for (paramBoolean2 = true;; paramBoolean2 = false)
    {
      synchronized (frameWriter)
      {
        try
        {
          if (!shutdown) {
            break label62;
          }
          throw new IOException("shutdown");
        }
        finally {}
      }
      paramBoolean1 = false;
      break;
    }
    label62:
    int i = nextStreamId;
    nextStreamId += 2;
    SpdyStream localSpdyStream = new SpdyStream(i, this, paramBoolean1, paramBoolean2, 0, paramList, settings);
    if (localSpdyStream.isOpen())
    {
      streams.put(Integer.valueOf(i), localSpdyStream);
      setIdle(false);
    }
    frameWriter.synStream(paramBoolean1, paramBoolean2, i, 0, 0, 0, paramList);
    return localSpdyStream;
  }
  
  public void noop()
    throws IOException
  {
    frameWriter.noop();
  }
  
  public int openStreamCount()
  {
    try
    {
      int i = streams.size();
      return i;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public Ping ping()
    throws IOException
  {
    Ping localPing = new Ping();
    try
    {
      if (shutdown) {
        throw new IOException("shutdown");
      }
    }
    finally {}
    int i = nextPingId;
    nextPingId += 2;
    if (pings == null) {
      pings = new HashMap();
    }
    pings.put(Integer.valueOf(i), localObject);
    writePing(false, i, 1330343787, (Ping)localObject);
    return (Ping)localObject;
  }
  
  public void readConnectionHeader()
    throws IOException
  {
    frameReader.readConnectionHeader();
  }
  
  SpdyStream removeStream(int paramInt)
  {
    try
    {
      SpdyStream localSpdyStream = (SpdyStream)streams.remove(Integer.valueOf(paramInt));
      if ((localSpdyStream != null) && (streams.isEmpty())) {
        setIdle(true);
      }
      return localSpdyStream;
    }
    finally {}
  }
  
  public void sendConnectionHeader()
    throws IOException
  {
    frameWriter.connectionHeader();
    frameWriter.settings(new Settings());
  }
  
  public void shutdown(ErrorCode paramErrorCode)
    throws IOException
  {
    int i;
    synchronized (frameWriter) {}
  }
  
  public void writeData(int paramInt1, boolean paramBoolean, byte[] paramArrayOfByte, int paramInt2, int paramInt3)
    throws IOException
  {
    frameWriter.data(paramBoolean, paramInt1, paramArrayOfByte, paramInt2, paramInt3);
  }
  
  void writeSynReply(int paramInt, boolean paramBoolean, List<String> paramList)
    throws IOException
  {
    frameWriter.synReply(paramBoolean, paramInt, paramList);
  }
  
  void writeSynReset(int paramInt, ErrorCode paramErrorCode)
    throws IOException
  {
    frameWriter.rstStream(paramInt, paramErrorCode);
  }
  
  void writeSynResetLater(final int paramInt, final ErrorCode paramErrorCode)
  {
    executor.submit(new NamedRunnable("OkHttp SPDY Writer %s stream %d", new Object[] { hostName, Integer.valueOf(paramInt) })
    {
      public void execute()
      {
        try
        {
          writeSynReset(paramInt, paramErrorCode);
          return;
        }
        catch (IOException localIOException) {}
      }
    });
  }
  
  void writeWindowUpdate(int paramInt1, int paramInt2)
    throws IOException
  {
    frameWriter.windowUpdate(paramInt1, paramInt2);
  }
  
  void writeWindowUpdateLater(final int paramInt1, final int paramInt2)
  {
    executor.submit(new NamedRunnable("OkHttp SPDY Writer %s stream %d", new Object[] { hostName, Integer.valueOf(paramInt1) })
    {
      public void execute()
      {
        try
        {
          writeWindowUpdate(paramInt1, paramInt2);
          return;
        }
        catch (IOException localIOException) {}
      }
    });
  }
  
  public static class Builder
  {
    private boolean client;
    private IncomingStreamHandler handler = IncomingStreamHandler.REFUSE_INCOMING_STREAMS;
    private String hostName;
    private InputStream in;
    private OutputStream out;
    private Variant variant = Variant.SPDY3;
    
    public Builder(String paramString, boolean paramBoolean, InputStream paramInputStream, OutputStream paramOutputStream)
    {
      hostName = paramString;
      client = paramBoolean;
      in = paramInputStream;
      out = paramOutputStream;
    }
    
    public Builder(String paramString, boolean paramBoolean, Socket paramSocket)
      throws IOException
    {
      this(paramString, paramBoolean, paramSocket.getInputStream(), paramSocket.getOutputStream());
    }
    
    public Builder(boolean paramBoolean, InputStream paramInputStream, OutputStream paramOutputStream)
    {
      this("", paramBoolean, paramInputStream, paramOutputStream);
    }
    
    public Builder(boolean paramBoolean, Socket paramSocket)
      throws IOException
    {
      this("", paramBoolean, paramSocket.getInputStream(), paramSocket.getOutputStream());
    }
    
    public SpdyConnection build()
    {
      return new SpdyConnection(this, null);
    }
    
    public Builder handler(IncomingStreamHandler paramIncomingStreamHandler)
    {
      handler = paramIncomingStreamHandler;
      return this;
    }
    
    public Builder http20Draft06()
    {
      variant = Variant.HTTP_20_DRAFT_06;
      return this;
    }
    
    public Builder spdy3()
    {
      variant = Variant.SPDY3;
      return this;
    }
  }
  
  private class Reader
    implements Runnable, FrameReader.Handler
  {
    private Reader() {}
    
    public void data(boolean paramBoolean, int paramInt1, InputStream paramInputStream, int paramInt2)
      throws IOException
    {
      SpdyStream localSpdyStream = SpdyConnection.this.getStream(paramInt1);
      if (localSpdyStream == null)
      {
        writeSynResetLater(paramInt1, ErrorCode.INVALID_STREAM);
        Util.skipByReading(paramInputStream, paramInt2);
      }
      do
      {
        return;
        localSpdyStream.receiveData(paramInputStream, paramInt2);
      } while (!paramBoolean);
      localSpdyStream.receiveFin();
    }
    
    public void goAway(int paramInt, ErrorCode arg2)
    {
      synchronized (SpdyConnection.this)
      {
        SpdyConnection.access$1202(SpdyConnection.this, true);
        Iterator localIterator = streams.entrySet().iterator();
        while (localIterator.hasNext())
        {
          Map.Entry localEntry = (Map.Entry)localIterator.next();
          if ((((Integer)localEntry.getKey()).intValue() > paramInt) && (((SpdyStream)localEntry.getValue()).isLocallyInitiated()))
          {
            ((SpdyStream)localEntry.getValue()).receiveRstStream(ErrorCode.REFUSED_STREAM);
            localIterator.remove();
          }
        }
      }
    }
    
    public void headers(boolean paramBoolean1, boolean paramBoolean2, int paramInt1, int paramInt2, int paramInt3, final List<String> paramList, HeadersMode paramHeadersMode)
    {
      SpdyStream localSpdyStream;
      synchronized (SpdyConnection.this)
      {
        if (shutdown) {
          return;
        }
        localSpdyStream = SpdyConnection.this.getStream(paramInt1);
        if (localSpdyStream != null) {
          break label204;
        }
        if (paramHeadersMode.failIfStreamAbsent())
        {
          writeSynResetLater(paramInt1, ErrorCode.INVALID_STREAM);
          return;
        }
      }
      if (paramInt1 <= lastGoodStreamId) {
        return;
      }
      if (paramInt1 % 2 == nextStreamId % 2) {
        return;
      }
      paramList = new SpdyStream(paramInt1, SpdyConnection.this, paramBoolean1, paramBoolean2, paramInt3, paramList, settings);
      SpdyConnection.access$1302(SpdyConnection.this, paramInt1);
      streams.put(Integer.valueOf(paramInt1), paramList);
      SpdyConnection.executor.submit(new NamedRunnable("OkHttp Callback %s stream %d", new Object[] { hostName, Integer.valueOf(paramInt1) })
      {
        public void execute()
        {
          try
          {
            handler.receive(paramList);
            return;
          }
          catch (IOException localIOException)
          {
            throw new RuntimeException(localIOException);
          }
        }
      });
      return;
      label204:
      if (paramHeadersMode.failIfStreamPresent())
      {
        localSpdyStream.closeLater(ErrorCode.PROTOCOL_ERROR);
        removeStream(paramInt1);
        return;
      }
      localSpdyStream.receiveHeaders(paramList, paramHeadersMode);
      if (paramBoolean2) {
        localSpdyStream.receiveFin();
      }
    }
    
    public void noop() {}
    
    public void ping(boolean paramBoolean, int paramInt1, int paramInt2)
    {
      if (paramBoolean)
      {
        Ping localPing = SpdyConnection.this.removePing(paramInt1);
        if (localPing != null) {
          localPing.receive();
        }
        return;
      }
      SpdyConnection.this.writePingLater(true, paramInt1, paramInt2, null);
    }
    
    public void priority(int paramInt1, int paramInt2) {}
    
    public void rstStream(int paramInt, ErrorCode paramErrorCode)
    {
      SpdyStream localSpdyStream = removeStream(paramInt);
      if (localSpdyStream != null) {
        localSpdyStream.receiveRstStream(paramErrorCode);
      }
    }
    
    public void run()
    {
      ErrorCode localErrorCode4 = ErrorCode.INTERNAL_ERROR;
      ErrorCode localErrorCode5 = ErrorCode.INTERNAL_ERROR;
      for (;;)
      {
        ErrorCode localErrorCode3 = localErrorCode4;
        ErrorCode localErrorCode1 = localErrorCode4;
        try
        {
          if (frameReader.nextFrame(this)) {
            continue;
          }
          localErrorCode3 = localErrorCode4;
          localErrorCode1 = localErrorCode4;
          localErrorCode4 = ErrorCode.NO_ERROR;
          localErrorCode3 = localErrorCode4;
          localErrorCode1 = localErrorCode4;
          ErrorCode localErrorCode6 = ErrorCode.CANCEL;
          ErrorCode localErrorCode2;
          return;
        }
        catch (IOException localIOException1)
        {
          localIOException1 = localIOException1;
          localErrorCode2 = localErrorCode3;
          localErrorCode3 = ErrorCode.PROTOCOL_ERROR;
          localErrorCode2 = localErrorCode3;
          localErrorCode4 = ErrorCode.PROTOCOL_ERROR;
          try
          {
            SpdyConnection.this.close(localErrorCode3, localErrorCode4);
            return;
          }
          catch (IOException localIOException2)
          {
            return;
          }
        }
        finally
        {
          try
          {
            SpdyConnection.this.close(localIOException2, localErrorCode5);
            throw ((Throwable)localObject);
          }
          catch (IOException localIOException3)
          {
            for (;;) {}
          }
        }
      }
    }
    
    public void settings(boolean paramBoolean, Settings paramSettings)
    {
      ??? = null;
      for (;;)
      {
        int i;
        synchronized (SpdyConnection.this)
        {
          if ((settings == null) || (paramBoolean))
          {
            settings = paramSettings;
            paramSettings = (Settings)???;
            if (!streams.isEmpty()) {
              paramSettings = (SpdyStream[])streams.values().toArray(new SpdyStream[streams.size()]);
            }
            if (paramSettings == null) {
              break;
            }
            int j = paramSettings.length;
            i = 0;
            if (i >= j) {
              break;
            }
          }
        }
        synchronized (paramSettings[i])
        {
          synchronized (SpdyConnection.this)
          {
            ((SpdyStream)???).receiveSettings(settings);
            i += 1;
            continue;
            settings.merge(paramSettings);
            continue;
            paramSettings = finally;
            throw paramSettings;
          }
        }
      }
    }
    
    public void windowUpdate(int paramInt1, int paramInt2, boolean paramBoolean)
    {
      if (paramInt1 == 0) {}
      SpdyStream localSpdyStream;
      do
      {
        return;
        localSpdyStream = SpdyConnection.this.getStream(paramInt1);
      } while (localSpdyStream == null);
      localSpdyStream.receiveWindowUpdate(paramInt2);
    }
  }
}

/* Location:
 * Qualified Name:     com.squareup.okhttp.internal.spdy.SpdyConnection
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */