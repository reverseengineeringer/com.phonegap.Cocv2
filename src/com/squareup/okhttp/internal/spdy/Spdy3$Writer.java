package com.squareup.okhttp.internal.spdy;

import com.squareup.okhttp.internal.Platform;
import com.squareup.okhttp.internal.Util;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.zip.Deflater;

final class Spdy3$Writer
  implements FrameWriter
{
  private final boolean client;
  private final ByteArrayOutputStream nameValueBlockBuffer;
  private final DataOutputStream nameValueBlockOut;
  private final DataOutputStream out;
  
  Spdy3$Writer(OutputStream paramOutputStream, boolean paramBoolean)
  {
    out = new DataOutputStream(paramOutputStream);
    client = paramBoolean;
    paramOutputStream = new Deflater();
    paramOutputStream.setDictionary(Spdy3.DICTIONARY);
    nameValueBlockBuffer = new ByteArrayOutputStream();
    nameValueBlockOut = new DataOutputStream(Platform.get().newDeflaterOutputStream(nameValueBlockBuffer, paramOutputStream, true));
  }
  
  private void writeNameValueBlockToBuffer(List<String> paramList)
    throws IOException
  {
    nameValueBlockBuffer.reset();
    int i = paramList.size() / 2;
    nameValueBlockOut.writeInt(i);
    paramList = paramList.iterator();
    for (;;)
    {
      if (!paramList.hasNext())
      {
        nameValueBlockOut.flush();
        return;
      }
      String str = (String)paramList.next();
      nameValueBlockOut.writeInt(str.length());
      nameValueBlockOut.write(str.getBytes("UTF-8"));
    }
  }
  
  public void close()
    throws IOException
  {
    Util.closeAll(out, nameValueBlockOut);
  }
  
  public void connectionHeader() {}
  
  public void data(boolean paramBoolean, int paramInt, byte[] paramArrayOfByte)
    throws IOException
  {
    try
    {
      data(paramBoolean, paramInt, paramArrayOfByte, 0, paramArrayOfByte.length);
      return;
    }
    finally
    {
      paramArrayOfByte = finally;
      throw paramArrayOfByte;
    }
  }
  
  public void data(boolean paramBoolean, int paramInt1, byte[] paramArrayOfByte, int paramInt2, int paramInt3)
    throws IOException
  {
    if (paramBoolean) {}
    for (int i = 1;; i = 0) {
      try
      {
        out.writeInt(0x7FFFFFFF & paramInt1);
        out.writeInt((i & 0xFF) << 24 | 0xFFFFFF & paramInt3);
        out.write(paramArrayOfByte, paramInt2, paramInt3);
        return;
      }
      finally {}
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
  {
    try
    {
      if (spdyGoAwayCode == -1) {
        throw new IllegalArgumentException();
      }
    }
    finally {}
    out.writeInt(-2147287033);
    out.writeInt(8);
    out.writeInt(paramInt);
    out.writeInt(spdyGoAwayCode);
    out.flush();
  }
  
  public void headers(int paramInt, List<String> paramList)
    throws IOException
  {
    try
    {
      writeNameValueBlockToBuffer(paramList);
      int i = nameValueBlockBuffer.size();
      out.writeInt(-2147287032);
      out.writeInt(0xFFFFFF & i + 4 | 0x0);
      out.writeInt(0x7FFFFFFF & paramInt);
      nameValueBlockBuffer.writeTo(out);
      out.flush();
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
      out.writeInt(-2147287035);
      out.writeInt(0);
      out.flush();
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public void ping(boolean paramBoolean, int paramInt1, int paramInt2)
    throws IOException
  {
    paramInt2 = 1;
    for (;;)
    {
      try
      {
        int i = client;
        if (paramInt1 % 2 == 1)
        {
          if (paramBoolean == (i ^ paramInt2)) {
            break;
          }
          throw new IllegalArgumentException("payload != reply");
        }
      }
      finally {}
      paramInt2 = 0;
    }
    out.writeInt(-2147287034);
    out.writeInt(4);
    out.writeInt(paramInt1);
    out.flush();
  }
  
  public void rstStream(int paramInt, ErrorCode paramErrorCode)
    throws IOException
  {
    try
    {
      if (spdyRstCode == -1) {
        throw new IllegalArgumentException();
      }
    }
    finally {}
    out.writeInt(-2147287037);
    out.writeInt(8);
    out.writeInt(0x7FFFFFFF & paramInt);
    out.writeInt(spdyRstCode);
    out.flush();
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
        out.writeInt(-2147287036);
        out.writeInt(i * 8 + 4 & 0xFFFFFF | 0x0);
        out.writeInt(i);
        i = 0;
        if (i > 10)
        {
          out.flush();
          return;
        }
        if (paramSettings.isSet(i))
        {
          int j = paramSettings.flags(i);
          out.writeInt((j & 0xFF) << 24 | i & 0xFFFFFF);
          out.writeInt(paramSettings.get(i));
        }
      }
      finally {}
      i += 1;
    }
  }
  
  /* Error */
  public void synReply(boolean paramBoolean, int paramInt, List<String> paramList)
    throws IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_3
    //   4: invokespecial 144	com/squareup/okhttp/internal/spdy/Spdy3$Writer:writeNameValueBlockToBuffer	(Ljava/util/List;)V
    //   7: iload_1
    //   8: ifeq +80 -> 88
    //   11: iconst_1
    //   12: istore 4
    //   14: aload_0
    //   15: getfield 46	com/squareup/okhttp/internal/spdy/Spdy3$Writer:nameValueBlockBuffer	Ljava/io/ByteArrayOutputStream;
    //   18: invokevirtual 145	java/io/ByteArrayOutputStream:size	()I
    //   21: istore 5
    //   23: aload_0
    //   24: getfield 28	com/squareup/okhttp/internal/spdy/Spdy3$Writer:out	Ljava/io/DataOutputStream;
    //   27: ldc -72
    //   29: invokevirtual 76	java/io/DataOutputStream:writeInt	(I)V
    //   32: aload_0
    //   33: getfield 28	com/squareup/okhttp/internal/spdy/Spdy3$Writer:out	Ljava/io/DataOutputStream;
    //   36: iload 4
    //   38: sipush 255
    //   41: iand
    //   42: bipush 24
    //   44: ishl
    //   45: ldc 125
    //   47: iload 5
    //   49: iconst_4
    //   50: iadd
    //   51: iand
    //   52: ior
    //   53: invokevirtual 76	java/io/DataOutputStream:writeInt	(I)V
    //   56: aload_0
    //   57: getfield 28	com/squareup/okhttp/internal/spdy/Spdy3$Writer:out	Ljava/io/DataOutputStream;
    //   60: ldc 124
    //   62: iload_2
    //   63: iand
    //   64: invokevirtual 76	java/io/DataOutputStream:writeInt	(I)V
    //   67: aload_0
    //   68: getfield 46	com/squareup/okhttp/internal/spdy/Spdy3$Writer:nameValueBlockBuffer	Ljava/io/ByteArrayOutputStream;
    //   71: aload_0
    //   72: getfield 28	com/squareup/okhttp/internal/spdy/Spdy3$Writer:out	Ljava/io/DataOutputStream;
    //   75: invokevirtual 149	java/io/ByteArrayOutputStream:writeTo	(Ljava/io/OutputStream;)V
    //   78: aload_0
    //   79: getfield 28	com/squareup/okhttp/internal/spdy/Spdy3$Writer:out	Ljava/io/DataOutputStream;
    //   82: invokevirtual 89	java/io/DataOutputStream:flush	()V
    //   85: aload_0
    //   86: monitorexit
    //   87: return
    //   88: iconst_0
    //   89: istore 4
    //   91: goto -77 -> 14
    //   94: astore_3
    //   95: aload_0
    //   96: monitorexit
    //   97: aload_3
    //   98: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	99	0	this	Writer
    //   0	99	1	paramBoolean	boolean
    //   0	99	2	paramInt	int
    //   0	99	3	paramList	List<String>
    //   12	78	4	i	int
    //   21	30	5	j	int
    // Exception table:
    //   from	to	target	type
    //   2	7	94	finally
    //   14	85	94	finally
  }
  
  public void synStream(boolean paramBoolean1, boolean paramBoolean2, int paramInt1, int paramInt2, int paramInt3, int paramInt4, List<String> paramList)
    throws IOException
  {
    int j = 0;
    for (;;)
    {
      try
      {
        writeNameValueBlockToBuffer(paramList);
        int k = nameValueBlockBuffer.size();
        int i;
        if (paramBoolean1)
        {
          i = 1;
          break label148;
          out.writeInt(-2147287039);
          out.writeInt(((i | j) & 0xFF) << 24 | 0xFFFFFF & k + 10);
          out.writeInt(paramInt1 & 0x7FFFFFFF);
          out.writeInt(paramInt2 & 0x7FFFFFFF);
          out.writeShort((paramInt3 & 0x7) << 13 | 0x0 | paramInt4 & 0xFF);
          nameValueBlockBuffer.writeTo(out);
          out.flush();
        }
        else
        {
          i = 0;
        }
      }
      finally {}
      label148:
      if (paramBoolean2) {
        j = 2;
      }
    }
  }
  
  public void windowUpdate(int paramInt1, int paramInt2)
    throws IOException
  {
    try
    {
      out.writeInt(-2147287031);
      out.writeInt(8);
      out.writeInt(paramInt1);
      out.writeInt(paramInt2);
      out.flush();
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
}

/* Location:
 * Qualified Name:     com.squareup.okhttp.internal.spdy.Spdy3.Writer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */