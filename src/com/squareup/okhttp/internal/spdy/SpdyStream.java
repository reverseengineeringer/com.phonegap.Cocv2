package com.squareup.okhttp.internal.spdy;

import com.squareup.okhttp.internal.Util;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

public final class SpdyStream
{
  public static final int WINDOW_UPDATE_THRESHOLD = 32768;
  private final SpdyConnection connection;
  private ErrorCode errorCode = null;
  private final int id;
  private final SpdyDataInputStream in = new SpdyDataInputStream(null);
  private final SpdyDataOutputStream out = new SpdyDataOutputStream(null);
  private final int priority;
  private long readTimeoutMillis = 0L;
  private final List<String> requestHeaders;
  private List<String> responseHeaders;
  private int writeWindowSize;
  
  static
  {
    if (!SpdyStream.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }
  
  SpdyStream(int paramInt1, SpdyConnection paramSpdyConnection, boolean paramBoolean1, boolean paramBoolean2, int paramInt2, List<String> paramList, Settings paramSettings)
  {
    if (paramSpdyConnection == null) {
      throw new NullPointerException("connection == null");
    }
    if (paramList == null) {
      throw new NullPointerException("requestHeaders == null");
    }
    id = paramInt1;
    connection = paramSpdyConnection;
    in.finished = paramBoolean2;
    out.finished = paramBoolean1;
    priority = paramInt2;
    requestHeaders = paramList;
    setSettings(paramSettings);
  }
  
  private void cancelStreamIfNecessary()
    throws IOException
  {
    assert (!Thread.holdsLock(this));
    for (;;)
    {
      try
      {
        boolean bool;
        if ((!in.finished) && (in.closed))
        {
          if (out.finished) {
            break label112;
          }
          if (out.closed)
          {
            break label112;
            bool = isOpen();
            if (i == 0) {
              break label95;
            }
            close(ErrorCode.CANCEL);
            return;
          }
        }
        i = 0;
        continue;
        if (bool) {
          continue;
        }
      }
      finally {}
      label95:
      connection.removeStream(id);
      return;
      label112:
      int i = 1;
    }
  }
  
  private boolean closeInternal(ErrorCode paramErrorCode)
  {
    assert (!Thread.holdsLock(this));
    try
    {
      if (errorCode != null) {
        return false;
      }
      if ((in.finished) && (out.finished)) {
        return false;
      }
    }
    finally {}
    errorCode = paramErrorCode;
    notifyAll();
    connection.removeStream(id);
    return true;
  }
  
  private void setSettings(Settings paramSettings)
  {
    int i = 65536;
    assert (Thread.holdsLock(connection));
    if (paramSettings != null) {
      i = paramSettings.getInitialWindowSize(65536);
    }
    writeWindowSize = i;
  }
  
  public void close(ErrorCode paramErrorCode)
    throws IOException
  {
    if (!closeInternal(paramErrorCode)) {
      return;
    }
    connection.writeSynReset(id, paramErrorCode);
  }
  
  public void closeLater(ErrorCode paramErrorCode)
  {
    if (!closeInternal(paramErrorCode)) {
      return;
    }
    connection.writeSynResetLater(id, paramErrorCode);
  }
  
  public SpdyConnection getConnection()
  {
    return connection;
  }
  
  public ErrorCode getErrorCode()
  {
    try
    {
      ErrorCode localErrorCode = errorCode;
      return localErrorCode;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public InputStream getInputStream()
  {
    return in;
  }
  
  public OutputStream getOutputStream()
  {
    try
    {
      if ((responseHeaders == null) && (!isLocallyInitiated())) {
        throw new IllegalStateException("reply before requesting the output stream");
      }
    }
    finally {}
    return out;
  }
  
  int getPriority()
  {
    return priority;
  }
  
  public long getReadTimeoutMillis()
  {
    return readTimeoutMillis;
  }
  
  public List<String> getRequestHeaders()
  {
    return requestHeaders;
  }
  
  public List<String> getResponseHeaders()
    throws IOException
  {
    long l1 = 0L;
    long l2 = 0L;
    for (;;)
    {
      try
      {
        if (readTimeoutMillis != 0L)
        {
          l2 = System.nanoTime() / 1000000L;
          l1 = readTimeoutMillis;
        }
        try
        {
          if ((responseHeaders != null) || (errorCode != null))
          {
            if (responseHeaders == null) {
              break label161;
            }
            List localList = responseHeaders;
            return localList;
          }
          if (readTimeoutMillis == 0L)
          {
            wait();
            continue;
            InterruptedIOException localInterruptedIOException;
            localObject = finally;
          }
        }
        catch (InterruptedException localInterruptedException)
        {
          localInterruptedIOException = new InterruptedIOException();
          localInterruptedIOException.initCause(localInterruptedException);
          throw localInterruptedIOException;
        }
        if (l1 <= 0L) {
          break;
        }
      }
      finally {}
      wait(l1);
      l1 = readTimeoutMillis + l2 - System.nanoTime() / 1000000L;
    }
    throw new SocketTimeoutException("Read response header timeout. readTimeoutMillis: " + readTimeoutMillis);
    label161:
    throw new IOException("stream was reset: " + errorCode);
  }
  
  public boolean isLocallyInitiated()
  {
    if (id % 2 == 1) {}
    for (int i = 1; connection.client == i; i = 0) {
      return true;
    }
    return false;
  }
  
  /* Error */
  public boolean isOpen()
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_1
    //   2: aload_0
    //   3: monitorenter
    //   4: aload_0
    //   5: getfield 61	com/squareup/okhttp/internal/spdy/SpdyStream:errorCode	Lcom/squareup/okhttp/internal/spdy/ErrorCode;
    //   8: astore_2
    //   9: aload_2
    //   10: ifnull +7 -> 17
    //   13: aload_0
    //   14: monitorexit
    //   15: iload_1
    //   16: ireturn
    //   17: aload_0
    //   18: getfield 54	com/squareup/okhttp/internal/spdy/SpdyStream:in	Lcom/squareup/okhttp/internal/spdy/SpdyStream$SpdyDataInputStream;
    //   21: invokestatic 123	com/squareup/okhttp/internal/spdy/SpdyStream$SpdyDataInputStream:access$2	(Lcom/squareup/okhttp/internal/spdy/SpdyStream$SpdyDataInputStream;)Z
    //   24: ifne +13 -> 37
    //   27: aload_0
    //   28: getfield 54	com/squareup/okhttp/internal/spdy/SpdyStream:in	Lcom/squareup/okhttp/internal/spdy/SpdyStream$SpdyDataInputStream;
    //   31: invokestatic 125	com/squareup/okhttp/internal/spdy/SpdyStream$SpdyDataInputStream:access$3	(Lcom/squareup/okhttp/internal/spdy/SpdyStream$SpdyDataInputStream;)Z
    //   34: ifeq +32 -> 66
    //   37: aload_0
    //   38: getfield 59	com/squareup/okhttp/internal/spdy/SpdyStream:out	Lcom/squareup/okhttp/internal/spdy/SpdyStream$SpdyDataOutputStream;
    //   41: invokestatic 128	com/squareup/okhttp/internal/spdy/SpdyStream$SpdyDataOutputStream:access$2	(Lcom/squareup/okhttp/internal/spdy/SpdyStream$SpdyDataOutputStream;)Z
    //   44: ifne +13 -> 57
    //   47: aload_0
    //   48: getfield 59	com/squareup/okhttp/internal/spdy/SpdyStream:out	Lcom/squareup/okhttp/internal/spdy/SpdyStream$SpdyDataOutputStream;
    //   51: invokestatic 130	com/squareup/okhttp/internal/spdy/SpdyStream$SpdyDataOutputStream:access$3	(Lcom/squareup/okhttp/internal/spdy/SpdyStream$SpdyDataOutputStream;)Z
    //   54: ifeq +12 -> 66
    //   57: aload_0
    //   58: getfield 180	com/squareup/okhttp/internal/spdy/SpdyStream:responseHeaders	Ljava/util/List;
    //   61: astore_2
    //   62: aload_2
    //   63: ifnonnull -50 -> 13
    //   66: iconst_1
    //   67: istore_1
    //   68: goto -55 -> 13
    //   71: astore_2
    //   72: aload_0
    //   73: monitorexit
    //   74: aload_2
    //   75: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	76	0	this	SpdyStream
    //   1	67	1	bool	boolean
    //   8	55	2	localObject1	Object
    //   71	4	2	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   4	9	71	finally
    //   17	37	71	finally
    //   37	57	71	finally
    //   57	62	71	finally
  }
  
  void receiveData(InputStream paramInputStream, int paramInt)
    throws IOException
  {
    assert (!Thread.holdsLock(this));
    in.receive(paramInputStream, paramInt);
  }
  
  void receiveFin()
  {
    assert (!Thread.holdsLock(this));
    try
    {
      in.finished = true;
      boolean bool = isOpen();
      notifyAll();
      if (!bool) {
        connection.removeStream(id);
      }
      return;
    }
    finally {}
  }
  
  void receiveHeaders(List<String> paramList, HeadersMode paramHeadersMode)
  {
    assert (!Thread.holdsLock(this));
    Object localObject = null;
    boolean bool = true;
    label97:
    do
    {
      for (;;)
      {
        try
        {
          if (responseHeaders == null)
          {
            if (paramHeadersMode.failIfHeadersAbsent())
            {
              paramList = ErrorCode.PROTOCOL_ERROR;
              if (paramList == null) {
                break;
              }
              closeLater(paramList);
              return;
            }
            responseHeaders = paramList;
            bool = isOpen();
            notifyAll();
            paramList = (List<String>)localObject;
            continue;
          }
          if (!paramHeadersMode.failIfHeadersPresent()) {
            break label97;
          }
        }
        finally {}
        paramList = ErrorCode.STREAM_IN_USE;
        continue;
        paramHeadersMode = new ArrayList();
        paramHeadersMode.addAll(responseHeaders);
        paramHeadersMode.addAll(paramList);
        responseHeaders = paramHeadersMode;
        paramList = (List<String>)localObject;
      }
    } while (bool);
    connection.removeStream(id);
  }
  
  void receiveRstStream(ErrorCode paramErrorCode)
  {
    try
    {
      if (errorCode == null)
      {
        errorCode = paramErrorCode;
        notifyAll();
      }
      return;
    }
    finally
    {
      paramErrorCode = finally;
      throw paramErrorCode;
    }
  }
  
  void receiveSettings(Settings paramSettings)
  {
    assert (Thread.holdsLock(this));
    setSettings(paramSettings);
    notifyAll();
  }
  
  void receiveWindowUpdate(int paramInt)
  {
    try
    {
      SpdyDataOutputStream localSpdyDataOutputStream = out;
      unacknowledgedBytes -= paramInt;
      notifyAll();
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public void reply(List<String> paramList, boolean paramBoolean)
    throws IOException
  {
    assert (!Thread.holdsLock(this));
    boolean bool = false;
    if (paramList == null) {
      try
      {
        throw new NullPointerException("responseHeaders == null");
      }
      finally {}
    }
    if (isLocallyInitiated()) {
      throw new IllegalStateException("cannot reply to a locally initiated stream");
    }
    if (responseHeaders != null) {
      throw new IllegalStateException("reply already sent");
    }
    responseHeaders = paramList;
    if (!paramBoolean)
    {
      out.finished = true;
      bool = true;
    }
    connection.writeSynReply(id, bool, paramList);
  }
  
  public void setReadTimeout(long paramLong)
  {
    readTimeoutMillis = paramLong;
  }
  
  private final class SpdyDataInputStream
    extends InputStream
  {
    private final byte[] buffer = new byte[65536];
    private boolean closed;
    private boolean finished;
    private int limit;
    private int pos = -1;
    private int unacknowledgedBytes = 0;
    
    static
    {
      if (!SpdyStream.class.desiredAssertionStatus()) {}
      for (boolean bool = true;; bool = false)
      {
        $assertionsDisabled = bool;
        return;
      }
    }
    
    private SpdyDataInputStream() {}
    
    private void checkNotClosed()
      throws IOException
    {
      if (closed) {
        throw new IOException("stream closed");
      }
      if (errorCode != null) {
        throw new IOException("stream was reset: " + errorCode);
      }
    }
    
    private void waitUntilReadable()
      throws IOException
    {
      long l2 = 0L;
      long l1 = 0L;
      if (readTimeoutMillis != 0L) {
        l2 = System.nanoTime() / 1000000L;
      }
      for (l1 = readTimeoutMillis;; l1 = readTimeoutMillis + l2 - System.nanoTime() / 1000000L)
      {
        try
        {
          for (;;)
          {
            if ((pos != -1) || (finished) || (closed)) {
              return;
            }
            if (errorCode != null) {
              return;
            }
            if (readTimeoutMillis != 0L) {
              break;
            }
            wait();
          }
          if (l1 <= 0L) {
            break;
          }
        }
        catch (InterruptedException localInterruptedException)
        {
          throw new InterruptedIOException();
        }
        wait(l1);
      }
      throw new SocketTimeoutException();
    }
    
    public int available()
      throws IOException
    {
      synchronized (SpdyStream.this)
      {
        checkNotClosed();
        if (pos == -1) {
          return 0;
        }
        if (limit > pos)
        {
          i = limit;
          j = pos;
          return i - j;
        }
      }
      int i = limit;
      int j = buffer.length;
      int k = pos;
      return i + (j - k);
    }
    
    public void close()
      throws IOException
    {
      synchronized (SpdyStream.this)
      {
        closed = true;
        notifyAll();
        SpdyStream.this.cancelStreamIfNecessary();
        return;
      }
    }
    
    public int read()
      throws IOException
    {
      return Util.readSingleByte(this);
    }
    
    public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
      throws IOException
    {
      synchronized (SpdyStream.this)
      {
        Util.checkOffsetAndCount(paramArrayOfByte.length, paramInt1, paramInt2);
        waitUntilReadable();
        checkNotClosed();
        if (pos == -1) {
          return -1;
        }
        int i = 0;
        if (limit <= pos)
        {
          i = Math.min(paramInt2, buffer.length - pos);
          System.arraycopy(buffer, pos, paramArrayOfByte, paramInt1, i);
          pos += i;
          j = 0 + i;
          i = j;
          if (pos == buffer.length)
          {
            pos = 0;
            i = j;
          }
        }
        int j = i;
        if (i < paramInt2)
        {
          paramInt2 = Math.min(limit - pos, paramInt2 - i);
          System.arraycopy(buffer, pos, paramArrayOfByte, paramInt1 + i, paramInt2);
          pos += paramInt2;
          j = i + paramInt2;
        }
        unacknowledgedBytes += j;
        if (unacknowledgedBytes >= 32768)
        {
          connection.writeWindowUpdateLater(id, unacknowledgedBytes);
          unacknowledgedBytes = 0;
        }
        if (pos == limit)
        {
          pos = -1;
          limit = 0;
        }
        return j;
      }
    }
    
    void receive(InputStream arg1, int paramInt)
      throws IOException
    {
      assert (!Thread.holdsLock(SpdyStream.this));
      if (paramInt == 0) {
        return;
      }
      boolean bool;
      int n;
      int m;
      int k;
      synchronized (SpdyStream.this)
      {
        bool = finished;
        n = pos;
        m = limit;
        k = limit;
        if (paramInt > buffer.length - available())
        {
          i = 1;
          if (i != 0)
          {
            Util.skipByReading(???, paramInt);
            closeLater(ErrorCode.FLOW_CONTROL_ERROR);
          }
        }
        else
        {
          i = 0;
        }
      }
      if (bool)
      {
        Util.skipByReading(???, paramInt);
        return;
      }
      int i = k;
      int j = paramInt;
      if (n < k)
      {
        i = Math.min(paramInt, buffer.length - k);
        Util.readFully(???, buffer, k, i);
        k += i;
        paramInt -= i;
        i = k;
        j = paramInt;
        if (k == buffer.length)
        {
          i = 0;
          j = paramInt;
        }
      }
      paramInt = i;
      if (j > 0)
      {
        Util.readFully(???, buffer, i, j);
        paramInt = i + j;
      }
      synchronized (SpdyStream.this)
      {
        limit = paramInt;
        if (pos == -1)
        {
          pos = m;
          notifyAll();
        }
        return;
      }
    }
  }
  
  private final class SpdyDataOutputStream
    extends OutputStream
  {
    private final byte[] buffer = new byte[' '];
    private boolean closed;
    private boolean finished;
    private int pos = 0;
    private int unacknowledgedBytes = 0;
    
    static
    {
      if (!SpdyStream.class.desiredAssertionStatus()) {}
      for (boolean bool = true;; bool = false)
      {
        $assertionsDisabled = bool;
        return;
      }
    }
    
    private SpdyDataOutputStream() {}
    
    private void checkNotClosed()
      throws IOException
    {
      synchronized (SpdyStream.this)
      {
        if (closed) {
          throw new IOException("stream closed");
        }
      }
      if (finished) {
        throw new IOException("stream finished");
      }
      if (errorCode != null) {
        throw new IOException("stream was reset: " + errorCode);
      }
    }
    
    private void waitUntilWritable(int paramInt, boolean paramBoolean)
      throws IOException
    {
      do
      {
        try
        {
          if (unacknowledgedBytes + paramInt < writeWindowSize) {
            return;
          }
          wait();
          if ((!paramBoolean) && (closed)) {
            throw new IOException("stream closed");
          }
        }
        catch (InterruptedException localInterruptedException)
        {
          throw new InterruptedIOException();
        }
        if (finished) {
          throw new IOException("stream finished");
        }
      } while (errorCode == null);
      throw new IOException("stream was reset: " + errorCode);
    }
    
    private void writeFrame(boolean paramBoolean)
      throws IOException
    {
      assert (!Thread.holdsLock(SpdyStream.this));
      int i = pos;
      synchronized (SpdyStream.this)
      {
        waitUntilWritable(i, paramBoolean);
        unacknowledgedBytes += i;
        connection.writeData(id, paramBoolean, buffer, 0, pos);
        pos = 0;
        return;
      }
    }
    
    public void close()
      throws IOException
    {
      assert (!Thread.holdsLock(SpdyStream.this));
      synchronized (SpdyStream.this)
      {
        if (closed) {
          return;
        }
        closed = true;
        if (!out.finished) {
          writeFrame(true);
        }
        connection.flush();
        SpdyStream.this.cancelStreamIfNecessary();
        return;
      }
    }
    
    public void flush()
      throws IOException
    {
      assert (!Thread.holdsLock(SpdyStream.this));
      checkNotClosed();
      if (pos > 0)
      {
        writeFrame(false);
        connection.flush();
      }
    }
    
    public void write(int paramInt)
      throws IOException
    {
      Util.writeSingleByte(this, paramInt);
    }
    
    public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
      throws IOException
    {
      assert (!Thread.holdsLock(SpdyStream.this));
      Util.checkOffsetAndCount(paramArrayOfByte.length, paramInt1, paramInt2);
      checkNotClosed();
      for (;;)
      {
        if (paramInt2 <= 0) {
          return;
        }
        if (pos == buffer.length) {
          writeFrame(false);
        }
        int i = Math.min(paramInt2, buffer.length - pos);
        System.arraycopy(paramArrayOfByte, paramInt1, buffer, pos, i);
        pos += i;
        paramInt1 += i;
        paramInt2 -= i;
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.squareup.okhttp.internal.spdy.SpdyStream
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */