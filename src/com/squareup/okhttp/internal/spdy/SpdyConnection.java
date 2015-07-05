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
        break label170;
      }
    }
    label170:
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
  
  /* Error */
  private void close(ErrorCode paramErrorCode1, ErrorCode paramErrorCode2)
    throws IOException
  {
    // Byte code:
    //   0: getstatic 59	com/squareup/okhttp/internal/spdy/SpdyConnection:$assertionsDisabled	Z
    //   3: ifne +18 -> 21
    //   6: aload_0
    //   7: invokestatic 236	java/lang/Thread:holdsLock	(Ljava/lang/Object;)Z
    //   10: ifeq +11 -> 21
    //   13: new 238	java/lang/AssertionError
    //   16: dup
    //   17: invokespecial 239	java/lang/AssertionError:<init>	()V
    //   20: athrow
    //   21: aconst_null
    //   22: astore 5
    //   24: aload_0
    //   25: aload_1
    //   26: invokevirtual 242	com/squareup/okhttp/internal/spdy/SpdyConnection:shutdown	(Lcom/squareup/okhttp/internal/spdy/ErrorCode;)V
    //   29: aload 5
    //   31: astore_1
    //   32: aconst_null
    //   33: astore 6
    //   35: aconst_null
    //   36: astore 7
    //   38: aload_0
    //   39: monitorenter
    //   40: aload_0
    //   41: getfield 96	com/squareup/okhttp/internal/spdy/SpdyConnection:streams	Ljava/util/Map;
    //   44: invokeinterface 247 1 0
    //   49: ifne +48 -> 97
    //   52: aload_0
    //   53: getfield 96	com/squareup/okhttp/internal/spdy/SpdyConnection:streams	Ljava/util/Map;
    //   56: invokeinterface 251 1 0
    //   61: aload_0
    //   62: getfield 96	com/squareup/okhttp/internal/spdy/SpdyConnection:streams	Ljava/util/Map;
    //   65: invokeinterface 255 1 0
    //   70: anewarray 257	com/squareup/okhttp/internal/spdy/SpdyStream
    //   73: invokeinterface 263 2 0
    //   78: checkcast 265	[Lcom/squareup/okhttp/internal/spdy/SpdyStream;
    //   81: astore 6
    //   83: aload_0
    //   84: getfield 96	com/squareup/okhttp/internal/spdy/SpdyConnection:streams	Ljava/util/Map;
    //   87: invokeinterface 268 1 0
    //   92: aload_0
    //   93: iconst_0
    //   94: invokespecial 272	com/squareup/okhttp/internal/spdy/SpdyConnection:setIdle	(Z)V
    //   97: aload_0
    //   98: getfield 274	com/squareup/okhttp/internal/spdy/SpdyConnection:pings	Ljava/util/Map;
    //   101: ifnull +39 -> 140
    //   104: aload_0
    //   105: getfield 274	com/squareup/okhttp/internal/spdy/SpdyConnection:pings	Ljava/util/Map;
    //   108: invokeinterface 251 1 0
    //   113: aload_0
    //   114: getfield 274	com/squareup/okhttp/internal/spdy/SpdyConnection:pings	Ljava/util/Map;
    //   117: invokeinterface 255 1 0
    //   122: anewarray 276	com/squareup/okhttp/internal/spdy/Ping
    //   125: invokeinterface 263 2 0
    //   130: checkcast 278	[Lcom/squareup/okhttp/internal/spdy/Ping;
    //   133: astore 7
    //   135: aload_0
    //   136: aconst_null
    //   137: putfield 274	com/squareup/okhttp/internal/spdy/SpdyConnection:pings	Ljava/util/Map;
    //   140: aload_0
    //   141: monitorexit
    //   142: aload_1
    //   143: astore 5
    //   145: aload 6
    //   147: ifnull +19 -> 166
    //   150: aload 6
    //   152: arraylength
    //   153: istore 4
    //   155: iconst_0
    //   156: istore_3
    //   157: iload_3
    //   158: iload 4
    //   160: if_icmplt +60 -> 220
    //   163: aload_1
    //   164: astore 5
    //   166: aload 7
    //   168: ifnull +16 -> 184
    //   171: aload 7
    //   173: arraylength
    //   174: istore 4
    //   176: iconst_0
    //   177: istore_3
    //   178: iload_3
    //   179: iload 4
    //   181: if_icmplt +80 -> 261
    //   184: aload_0
    //   185: getfield 134	com/squareup/okhttp/internal/spdy/SpdyConnection:frameReader	Lcom/squareup/okhttp/internal/spdy/FrameReader;
    //   188: invokeinterface 282 1 0
    //   193: aload_0
    //   194: getfield 144	com/squareup/okhttp/internal/spdy/SpdyConnection:frameWriter	Lcom/squareup/okhttp/internal/spdy/FrameWriter;
    //   197: invokeinterface 285 1 0
    //   202: aload 5
    //   204: astore_1
    //   205: aload_1
    //   206: ifnull +88 -> 294
    //   209: aload_1
    //   210: athrow
    //   211: astore_1
    //   212: goto -180 -> 32
    //   215: astore_1
    //   216: aload_0
    //   217: monitorexit
    //   218: aload_1
    //   219: athrow
    //   220: aload 6
    //   222: iload_3
    //   223: aaload
    //   224: astore 5
    //   226: aload 5
    //   228: aload_2
    //   229: invokevirtual 287	com/squareup/okhttp/internal/spdy/SpdyStream:close	(Lcom/squareup/okhttp/internal/spdy/ErrorCode;)V
    //   232: aload_1
    //   233: astore 5
    //   235: iload_3
    //   236: iconst_1
    //   237: iadd
    //   238: istore_3
    //   239: aload 5
    //   241: astore_1
    //   242: goto -85 -> 157
    //   245: astore 8
    //   247: aload_1
    //   248: astore 5
    //   250: aload_1
    //   251: ifnull -16 -> 235
    //   254: aload 8
    //   256: astore 5
    //   258: goto -23 -> 235
    //   261: aload 7
    //   263: iload_3
    //   264: aaload
    //   265: invokevirtual 290	com/squareup/okhttp/internal/spdy/Ping:cancel	()V
    //   268: iload_3
    //   269: iconst_1
    //   270: iadd
    //   271: istore_3
    //   272: goto -94 -> 178
    //   275: astore 5
    //   277: goto -84 -> 193
    //   280: astore_2
    //   281: aload 5
    //   283: astore_1
    //   284: aload 5
    //   286: ifnonnull -81 -> 205
    //   289: aload_2
    //   290: astore_1
    //   291: goto -86 -> 205
    //   294: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	295	0	this	SpdyConnection
    //   0	295	1	paramErrorCode1	ErrorCode
    //   0	295	2	paramErrorCode2	ErrorCode
    //   156	116	3	i	int
    //   153	29	4	j	int
    //   22	235	5	localObject	Object
    //   275	10	5	localIOException1	IOException
    //   33	188	6	arrayOfSpdyStream	SpdyStream[]
    //   36	226	7	arrayOfPing	Ping[]
    //   245	10	8	localIOException2	IOException
    // Exception table:
    //   from	to	target	type
    //   24	29	211	java/io/IOException
    //   40	97	215	finally
    //   97	140	215	finally
    //   140	142	215	finally
    //   216	218	215	finally
    //   226	232	245	java/io/IOException
    //   184	193	275	java/io/IOException
    //   193	202	280	java/io/IOException
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
    //   3: getfield 274	com/squareup/okhttp/internal/spdy/SpdyConnection:pings	Ljava/util/Map;
    //   6: ifnull +24 -> 30
    //   9: aload_0
    //   10: getfield 274	com/squareup/okhttp/internal/spdy/SpdyConnection:pings	Ljava/util/Map;
    //   13: iload_1
    //   14: invokestatic 296	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   17: invokeinterface 303 2 0
    //   22: checkcast 276	com/squareup/okhttp/internal/spdy/Ping
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
    //   8: ldc2_w 304
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
    if (paramBoolean1)
    {
      paramBoolean1 = false;
      if (!paramBoolean2) {
        break label57;
      }
    }
    label57:
    for (paramBoolean2 = false;; paramBoolean2 = true)
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
      paramBoolean1 = true;
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
        shutdown = true;
        Iterator localIterator = streams.entrySet().iterator();
        Map.Entry localEntry;
        do
        {
          if (!localIterator.hasNext()) {
            return;
          }
          localEntry = (Map.Entry)localIterator.next();
        } while ((((Integer)localEntry.getKey()).intValue() <= paramInt) || (!((SpdyStream)localEntry.getValue()).isLocallyInitiated()));
        ((SpdyStream)localEntry.getValue()).receiveRstStream(ErrorCode.REFUSED_STREAM);
        localIterator.remove();
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
          break label203;
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
      lastGoodStreamId = paramInt1;
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
      label203:
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
    
    /* Error */
    public void run()
    {
      // Byte code:
      //   0: getstatic 218	com/squareup/okhttp/internal/spdy/ErrorCode:INTERNAL_ERROR	Lcom/squareup/okhttp/internal/spdy/ErrorCode;
      //   3: astore_3
      //   4: getstatic 218	com/squareup/okhttp/internal/spdy/ErrorCode:INTERNAL_ERROR	Lcom/squareup/okhttp/internal/spdy/ErrorCode;
      //   7: astore 4
      //   9: aload_3
      //   10: astore_2
      //   11: aload_3
      //   12: astore_1
      //   13: aload_0
      //   14: getfield 19	com/squareup/okhttp/internal/spdy/SpdyConnection$Reader:this$0	Lcom/squareup/okhttp/internal/spdy/SpdyConnection;
      //   17: invokestatic 222	com/squareup/okhttp/internal/spdy/SpdyConnection:access$2	(Lcom/squareup/okhttp/internal/spdy/SpdyConnection;)Lcom/squareup/okhttp/internal/spdy/FrameReader;
      //   20: aload_0
      //   21: invokeinterface 228 2 0
      //   26: ifne -17 -> 9
      //   29: aload_3
      //   30: astore_2
      //   31: aload_3
      //   32: astore_1
      //   33: getstatic 231	com/squareup/okhttp/internal/spdy/ErrorCode:NO_ERROR	Lcom/squareup/okhttp/internal/spdy/ErrorCode;
      //   36: astore_3
      //   37: aload_3
      //   38: astore_2
      //   39: aload_3
      //   40: astore_1
      //   41: getstatic 234	com/squareup/okhttp/internal/spdy/ErrorCode:CANCEL	Lcom/squareup/okhttp/internal/spdy/ErrorCode;
      //   44: astore 5
      //   46: aload_0
      //   47: getfield 19	com/squareup/okhttp/internal/spdy/SpdyConnection$Reader:this$0	Lcom/squareup/okhttp/internal/spdy/SpdyConnection;
      //   50: aload_3
      //   51: aload 5
      //   53: invokestatic 237	com/squareup/okhttp/internal/spdy/SpdyConnection:access$1	(Lcom/squareup/okhttp/internal/spdy/SpdyConnection;Lcom/squareup/okhttp/internal/spdy/ErrorCode;Lcom/squareup/okhttp/internal/spdy/ErrorCode;)V
      //   56: return
      //   57: astore_1
      //   58: aload_2
      //   59: astore_1
      //   60: getstatic 182	com/squareup/okhttp/internal/spdy/ErrorCode:PROTOCOL_ERROR	Lcom/squareup/okhttp/internal/spdy/ErrorCode;
      //   63: astore_2
      //   64: aload_2
      //   65: astore_1
      //   66: getstatic 182	com/squareup/okhttp/internal/spdy/ErrorCode:PROTOCOL_ERROR	Lcom/squareup/okhttp/internal/spdy/ErrorCode;
      //   69: astore_3
      //   70: aload_0
      //   71: getfield 19	com/squareup/okhttp/internal/spdy/SpdyConnection$Reader:this$0	Lcom/squareup/okhttp/internal/spdy/SpdyConnection;
      //   74: aload_2
      //   75: aload_3
      //   76: invokestatic 237	com/squareup/okhttp/internal/spdy/SpdyConnection:access$1	(Lcom/squareup/okhttp/internal/spdy/SpdyConnection;Lcom/squareup/okhttp/internal/spdy/ErrorCode;Lcom/squareup/okhttp/internal/spdy/ErrorCode;)V
      //   79: return
      //   80: astore_1
      //   81: return
      //   82: astore_2
      //   83: aload_0
      //   84: getfield 19	com/squareup/okhttp/internal/spdy/SpdyConnection$Reader:this$0	Lcom/squareup/okhttp/internal/spdy/SpdyConnection;
      //   87: aload_1
      //   88: aload 4
      //   90: invokestatic 237	com/squareup/okhttp/internal/spdy/SpdyConnection:access$1	(Lcom/squareup/okhttp/internal/spdy/SpdyConnection;Lcom/squareup/okhttp/internal/spdy/ErrorCode;Lcom/squareup/okhttp/internal/spdy/ErrorCode;)V
      //   93: aload_2
      //   94: athrow
      //   95: astore_1
      //   96: return
      //   97: astore_1
      //   98: goto -5 -> 93
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	101	0	this	Reader
      //   12	29	1	localErrorCode1	ErrorCode
      //   57	1	1	localIOException1	IOException
      //   59	7	1	localErrorCode2	ErrorCode
      //   80	8	1	localIOException2	IOException
      //   95	1	1	localIOException3	IOException
      //   97	1	1	localIOException4	IOException
      //   10	65	2	localErrorCode3	ErrorCode
      //   82	12	2	localObject	Object
      //   3	73	3	localErrorCode4	ErrorCode
      //   7	82	4	localErrorCode5	ErrorCode
      //   44	8	5	localErrorCode6	ErrorCode
      // Exception table:
      //   from	to	target	type
      //   13	29	57	java/io/IOException
      //   33	37	57	java/io/IOException
      //   41	46	57	java/io/IOException
      //   70	79	80	java/io/IOException
      //   13	29	82	finally
      //   33	37	82	finally
      //   41	46	82	finally
      //   60	64	82	finally
      //   66	70	82	finally
      //   46	56	95	java/io/IOException
      //   83	93	97	java/io/IOException
    }
    
    public void settings(boolean paramBoolean, Settings paramSettings)
    {
      ??? = null;
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
          if (paramSettings != null)
          {
            int j = paramSettings.length;
            i = 0;
            if (i < j) {
              break label128;
            }
          }
        }
        else
        {
          settings.merge(paramSettings);
        }
      }
      label128:
      synchronized (paramSettings[i])
      {
        synchronized (SpdyConnection.this)
        {
          ((SpdyStream)???).receiveSettings(settings);
          i += 1;
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