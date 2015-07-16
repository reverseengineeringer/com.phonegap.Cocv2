package com.squareup.okhttp.internal.spdy;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

final class Http20Draft06$Writer
  implements FrameWriter
{
  private final boolean client;
  private final ByteArrayOutputStream hpackBuffer;
  private final Hpack.Writer hpackWriter;
  private final DataOutputStream out;
  
  Http20Draft06$Writer(OutputStream paramOutputStream, boolean paramBoolean)
  {
    out = new DataOutputStream(paramOutputStream);
    client = paramBoolean;
    hpackBuffer = new ByteArrayOutputStream();
    hpackWriter = new Hpack.Writer(hpackBuffer);
  }
  
  private void headers(boolean paramBoolean, int paramInt1, int paramInt2, List<String> paramList)
    throws IOException
  {
    hpackBuffer.reset();
    hpackWriter.writeHeaders(paramList);
    int k = hpackBuffer.size();
    int i = 4;
    if (paramBoolean) {
      i = 0x4 | 0x1;
    }
    int j = i;
    if (paramInt2 != -1) {
      j = i | 0x8;
    }
    out.writeInt((0xFFFF & k) << 16 | 0x100 | j & 0xFF);
    out.writeInt(paramInt1 & 0x7FFFFFFF);
    if (paramInt2 != -1) {
      out.writeInt(paramInt2 & 0x7FFFFFFF);
    }
    hpackBuffer.writeTo(out);
  }
  
  public void close()
    throws IOException
  {
    out.close();
  }
  
  /* Error */
  public void connectionHeader()
    throws IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 31	com/squareup/okhttp/internal/spdy/Http20Draft06$Writer:client	Z
    //   6: istore_1
    //   7: iload_1
    //   8: ifne +6 -> 14
    //   11: aload_0
    //   12: monitorexit
    //   13: return
    //   14: aload_0
    //   15: getfield 29	com/squareup/okhttp/internal/spdy/Http20Draft06$Writer:out	Ljava/io/DataOutputStream;
    //   18: invokestatic 77	com/squareup/okhttp/internal/spdy/Http20Draft06:access$000	()[B
    //   21: invokevirtual 81	java/io/DataOutputStream:write	([B)V
    //   24: goto -13 -> 11
    //   27: astore_2
    //   28: aload_0
    //   29: monitorexit
    //   30: aload_2
    //   31: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	32	0	this	Writer
    //   6	2	1	bool	boolean
    //   27	4	2	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	7	27	finally
    //   14	24	27	finally
  }
  
  public void data(boolean paramBoolean, int paramInt, byte[] paramArrayOfByte)
    throws IOException
  {
    data(paramBoolean, paramInt, paramArrayOfByte, 0, paramArrayOfByte.length);
  }
  
  public void data(boolean paramBoolean, int paramInt1, byte[] paramArrayOfByte, int paramInt2, int paramInt3)
    throws IOException
  {
    int i = 0;
    if (paramBoolean) {
      i = 0x0 | 0x1;
    }
    try
    {
      out.writeInt((0xFFFF & paramInt3) << 16 | 0x0 | i & 0xFF);
      out.writeInt(0x7FFFFFFF & paramInt1);
      out.write(paramArrayOfByte, paramInt2, paramInt3);
      return;
    }
    finally
    {
      paramArrayOfByte = finally;
      throw paramArrayOfByte;
    }
  }
  
  public void flush()
    throws IOException
  {
    try
    {
      out.flush();
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public void goAway(int paramInt, ErrorCode paramErrorCode)
    throws IOException
  {}
  
  public void headers(int paramInt, List<String> paramList)
    throws IOException
  {
    try
    {
      headers(false, paramInt, -1, paramList);
      return;
    }
    finally
    {
      paramList = finally;
      throw paramList;
    }
  }
  
  public void noop()
    throws IOException
  {
    try
    {
      throw new UnsupportedOperationException();
    }
    finally {}
  }
  
  public void ping(boolean paramBoolean, int paramInt1, int paramInt2)
    throws IOException
  {}
  
  public void rstStream(int paramInt, ErrorCode paramErrorCode)
    throws IOException
  {
    try
    {
      throw new UnsupportedOperationException("TODO");
    }
    finally {}
  }
  
  public void settings(Settings paramSettings)
    throws IOException
  {
    for (;;)
    {
      int i;
      try
      {
        i = paramSettings.size();
        out.writeInt((0xFFFF & i * 8) << 16 | 0x400 | 0x0);
        out.writeInt(0);
        i = 0;
        if (i < 10)
        {
          if (!paramSettings.isSet(i)) {
            break label91;
          }
          out.writeInt(0xFFFFFF & i);
          out.writeInt(paramSettings.get(i));
        }
      }
      finally {}
      return;
      label91:
      i += 1;
    }
  }
  
  public void synReply(boolean paramBoolean, int paramInt, List<String> paramList)
    throws IOException
  {
    try
    {
      headers(paramBoolean, paramInt, -1, paramList);
      return;
    }
    finally
    {
      paramList = finally;
      throw paramList;
    }
  }
  
  public void synStream(boolean paramBoolean1, boolean paramBoolean2, int paramInt1, int paramInt2, int paramInt3, int paramInt4, List<String> paramList)
    throws IOException
  {
    if (paramBoolean2) {
      try
      {
        throw new UnsupportedOperationException();
      }
      finally {}
    }
    headers(paramBoolean1, paramInt1, paramInt3, paramList);
  }
  
  public void windowUpdate(int paramInt1, int paramInt2)
    throws IOException
  {}
}

/* Location:
 * Qualified Name:     com.squareup.okhttp.internal.spdy.Http20Draft06.Writer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */