package com.squareup.okhttp.internal.spdy;

import com.squareup.okhttp.internal.Util;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.OutputStream;

final class SpdyStream$SpdyDataOutputStream
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
  
  private SpdyStream$SpdyDataOutputStream(SpdyStream paramSpdyStream) {}
  
  private void checkNotClosed()
    throws IOException
  {
    synchronized (this$0)
    {
      if (closed) {
        throw new IOException("stream closed");
      }
    }
    if (finished) {
      throw new IOException("stream finished");
    }
    if (SpdyStream.access$1000(this$0) != null) {
      throw new IOException("stream was reset: " + SpdyStream.access$1000(this$0));
    }
  }
  
  private void waitUntilWritable(int paramInt, boolean paramBoolean)
    throws IOException
  {
    do
    {
      try
      {
        if (unacknowledgedBytes + paramInt < SpdyStream.access$1300(this$0)) {
          break;
        }
        this$0.wait();
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
    } while (SpdyStream.access$1000(this$0) == null);
    throw new IOException("stream was reset: " + SpdyStream.access$1000(this$0));
  }
  
  private void writeFrame(boolean paramBoolean)
    throws IOException
  {
    assert (!Thread.holdsLock(this$0));
    int i = pos;
    synchronized (this$0)
    {
      waitUntilWritable(i, paramBoolean);
      unacknowledgedBytes += i;
      SpdyStream.access$800(this$0).writeData(SpdyStream.access$700(this$0), paramBoolean, buffer, 0, pos);
      pos = 0;
      return;
    }
  }
  
  public void close()
    throws IOException
  {
    assert (!Thread.holdsLock(this$0));
    synchronized (this$0)
    {
      if (closed) {
        return;
      }
      closed = true;
      if (!access$1200this$0).finished) {
        writeFrame(true);
      }
      SpdyStream.access$800(this$0).flush();
      SpdyStream.access$1100(this$0);
      return;
    }
  }
  
  public void flush()
    throws IOException
  {
    assert (!Thread.holdsLock(this$0));
    checkNotClosed();
    if (pos > 0)
    {
      writeFrame(false);
      SpdyStream.access$800(this$0).flush();
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
    assert (!Thread.holdsLock(this$0));
    Util.checkOffsetAndCount(paramArrayOfByte.length, paramInt1, paramInt2);
    checkNotClosed();
    while (paramInt2 > 0)
    {
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

/* Location:
 * Qualified Name:     com.squareup.okhttp.internal.spdy.SpdyStream.SpdyDataOutputStream
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */