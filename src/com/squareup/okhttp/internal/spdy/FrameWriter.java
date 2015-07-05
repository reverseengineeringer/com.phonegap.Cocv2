package com.squareup.okhttp.internal.spdy;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;

public abstract interface FrameWriter
  extends Closeable
{
  public abstract void connectionHeader()
    throws IOException;
  
  public abstract void data(boolean paramBoolean, int paramInt, byte[] paramArrayOfByte)
    throws IOException;
  
  public abstract void data(boolean paramBoolean, int paramInt1, byte[] paramArrayOfByte, int paramInt2, int paramInt3)
    throws IOException;
  
  public abstract void flush()
    throws IOException;
  
  public abstract void goAway(int paramInt, ErrorCode paramErrorCode)
    throws IOException;
  
  public abstract void headers(int paramInt, List<String> paramList)
    throws IOException;
  
  public abstract void noop()
    throws IOException;
  
  public abstract void ping(boolean paramBoolean, int paramInt1, int paramInt2)
    throws IOException;
  
  public abstract void rstStream(int paramInt, ErrorCode paramErrorCode)
    throws IOException;
  
  public abstract void settings(Settings paramSettings)
    throws IOException;
  
  public abstract void synReply(boolean paramBoolean, int paramInt, List<String> paramList)
    throws IOException;
  
  public abstract void synStream(boolean paramBoolean1, boolean paramBoolean2, int paramInt1, int paramInt2, int paramInt3, int paramInt4, List<String> paramList)
    throws IOException;
  
  public abstract void windowUpdate(int paramInt1, int paramInt2)
    throws IOException;
}

/* Location:
 * Qualified Name:     com.squareup.okhttp.internal.spdy.FrameWriter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */