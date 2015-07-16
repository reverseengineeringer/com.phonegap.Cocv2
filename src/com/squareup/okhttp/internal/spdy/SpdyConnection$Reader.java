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
    SpdyStream localSpdyStream = SpdyConnection.access$1100(this$0, paramInt1);
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
      SpdyConnection.access$1202(this$0, true);
      Iterator localIterator = SpdyConnection.access$1500(this$0).entrySet().iterator();
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
    synchronized (this$0)
    {
      if (SpdyConnection.access$1200(this$0)) {
        return;
      }
      localSpdyStream = SpdyConnection.access$1100(this$0, paramInt1);
      if (localSpdyStream != null) {
        break label204;
      }
      if (paramHeadersMode.failIfStreamAbsent())
      {
        this$0.writeSynResetLater(paramInt1, ErrorCode.INVALID_STREAM);
        return;
      }
    }
    if (paramInt1 <= SpdyConnection.access$1300(this$0)) {
      return;
    }
    if (paramInt1 % 2 == SpdyConnection.access$1400(this$0) % 2) {
      return;
    }
    paramList = new SpdyStream(paramInt1, this$0, paramBoolean1, paramBoolean2, paramInt3, paramList, this$0.settings);
    SpdyConnection.access$1302(this$0, paramInt1);
    SpdyConnection.access$1500(this$0).put(Integer.valueOf(paramInt1), paramList);
    SpdyConnection.access$1800().submit(new NamedRunnable("OkHttp Callback %s stream %d", new Object[] { SpdyConnection.access$1600(this$0), Integer.valueOf(paramInt1) })
    {
      public void execute()
      {
        try
        {
          SpdyConnection.access$1700(this$0).receive(paramList);
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
      Ping localPing = SpdyConnection.access$1900(this$0, paramInt1);
      if (localPing != null) {
        localPing.receive();
      }
      return;
    }
    SpdyConnection.access$2000(this$0, true, paramInt1, paramInt2, null);
  }
  
  public void priority(int paramInt1, int paramInt2) {}
  
  public void rstStream(int paramInt, ErrorCode paramErrorCode)
  {
    SpdyStream localSpdyStream = this$0.removeStream(paramInt);
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
        if (SpdyConnection.access$900(this$0).nextFrame(this)) {
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
          SpdyConnection.access$1000(this$0, localErrorCode3, localErrorCode4);
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
          SpdyConnection.access$1000(this$0, localIOException2, localErrorCode5);
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
      synchronized (this$0)
      {
        if ((this$0.settings == null) || (paramBoolean))
        {
          this$0.settings = paramSettings;
          paramSettings = (Settings)???;
          if (!SpdyConnection.access$1500(this$0).isEmpty()) {
            paramSettings = (SpdyStream[])SpdyConnection.access$1500(this$0).values().toArray(new SpdyStream[SpdyConnection.access$1500(this$0).size()]);
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
        synchronized (this$0)
        {
          ((SpdyStream)???).receiveSettings(this$0.settings);
          i += 1;
          continue;
          this$0.settings.merge(paramSettings);
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
      localSpdyStream = SpdyConnection.access$1100(this$0, paramInt1);
    } while (localSpdyStream == null);
    localSpdyStream.receiveWindowUpdate(paramInt2);
  }
}

/* Location:
 * Qualified Name:     com.squareup.okhttp.internal.spdy.SpdyConnection.Reader
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */