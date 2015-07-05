package com.squareup.okhttp.internal.spdy;

import com.squareup.okhttp.internal.NamedRunnable;
import com.squareup.okhttp.internal.Util;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ExecutorService;

class SpdyConnection$Reader
  implements Runnable, FrameReader.Handler
{
  private SpdyConnection$Reader(SpdyConnection paramSpdyConnection) {}
  
  public void data(boolean paramBoolean, int paramInt1, InputStream paramInputStream, int paramInt2)
    throws IOException
  {
    SpdyStream localSpdyStream = SpdyConnection.access$3(this$0, paramInt1);
    if (localSpdyStream == null)
    {
      this$0.writeSynResetLater(paramInt1, ErrorCode.INVALID_STREAM);
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
    synchronized (this$0)
    {
      SpdyConnection.access$14(this$0, true);
      Iterator localIterator = SpdyConnection.access$8(this$0).entrySet().iterator();
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
    synchronized (this$0)
    {
      if (SpdyConnection.access$4(this$0)) {
        return;
      }
      localSpdyStream = SpdyConnection.access$3(this$0, paramInt1);
      if (localSpdyStream != null) {
        break label203;
      }
      if (paramHeadersMode.failIfStreamAbsent())
      {
        this$0.writeSynResetLater(paramInt1, ErrorCode.INVALID_STREAM);
        return;
      }
    }
    if (paramInt1 <= SpdyConnection.access$5(this$0)) {
      return;
    }
    if (paramInt1 % 2 == SpdyConnection.access$6(this$0) % 2) {
      return;
    }
    paramList = new SpdyStream(paramInt1, this$0, paramBoolean1, paramBoolean2, paramInt3, paramList, this$0.settings);
    SpdyConnection.access$7(this$0, paramInt1);
    SpdyConnection.access$8(this$0).put(Integer.valueOf(paramInt1), paramList);
    SpdyConnection.access$9().submit(new NamedRunnable("OkHttp Callback %s stream %d", new Object[] { SpdyConnection.access$10(this$0), Integer.valueOf(paramInt1) })
    {
      public void execute()
      {
        try
        {
          SpdyConnection.access$11(this$0).receive(paramList);
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
      this$0.removeStream(paramInt1);
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
      Ping localPing = SpdyConnection.access$12(this$0, paramInt1);
      if (localPing != null) {
        localPing.receive();
      }
      return;
    }
    SpdyConnection.access$13(this$0, true, paramInt1, paramInt2, null);
  }
  
  public void priority(int paramInt1, int paramInt2) {}
  
  public void rstStream(int paramInt, ErrorCode paramErrorCode)
  {
    SpdyStream localSpdyStream = this$0.removeStream(paramInt);
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
    synchronized (this$0)
    {
      if ((this$0.settings == null) || (paramBoolean))
      {
        this$0.settings = paramSettings;
        paramSettings = (Settings)???;
        if (!SpdyConnection.access$8(this$0).isEmpty()) {
          paramSettings = (SpdyStream[])SpdyConnection.access$8(this$0).values().toArray(new SpdyStream[SpdyConnection.access$8(this$0).size()]);
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
        this$0.settings.merge(paramSettings);
      }
    }
    label128:
    synchronized (paramSettings[i])
    {
      synchronized (this$0)
      {
        ((SpdyStream)???).receiveSettings(this$0.settings);
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
      localSpdyStream = SpdyConnection.access$3(this$0, paramInt1);
    } while (localSpdyStream == null);
    localSpdyStream.receiveWindowUpdate(paramInt2);
  }
}

/* Location:
 * Qualified Name:     com.squareup.okhttp.internal.spdy.SpdyConnection.Reader
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */