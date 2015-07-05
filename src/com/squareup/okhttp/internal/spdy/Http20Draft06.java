package com.squareup.okhttp.internal.spdy;

import com.squareup.okhttp.internal.Util;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

final class Http20Draft06
  implements Variant
{
  private static final byte[] CONNECTION_HEADER;
  static final int FLAG_END_FLOW_CONTROL = 1;
  static final int FLAG_END_HEADERS = 4;
  static final int FLAG_END_STREAM = 1;
  static final int FLAG_PONG = 1;
  static final int FLAG_PRIORITY = 8;
  static final int TYPE_CONTINUATION = 10;
  static final int TYPE_DATA = 0;
  static final int TYPE_GOAWAY = 7;
  static final int TYPE_HEADERS = 1;
  static final int TYPE_PING = 6;
  static final int TYPE_PRIORITY = 2;
  static final int TYPE_PUSH_PROMISE = 5;
  static final int TYPE_RST_STREAM = 3;
  static final int TYPE_SETTINGS = 4;
  static final int TYPE_WINDOW_UPDATE = 9;
  
  static
  {
    try
    {
      CONNECTION_HEADER = "PRI * HTTP/2.0\r\n\r\nSM\r\n\r\n".getBytes("UTF-8");
      return;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      throw new AssertionError();
    }
  }
  
  public FrameReader newReader(InputStream paramInputStream, boolean paramBoolean)
  {
    return new Reader(paramInputStream, paramBoolean);
  }
  
  public FrameWriter newWriter(OutputStream paramOutputStream, boolean paramBoolean)
  {
    return new Writer(paramOutputStream, paramBoolean);
  }
  
  static final class Reader
    implements FrameReader
  {
    private final boolean client;
    private final Hpack.Reader hpackReader;
    private final DataInputStream in;
    
    Reader(InputStream paramInputStream, boolean paramBoolean)
    {
      in = new DataInputStream(paramInputStream);
      client = paramBoolean;
      hpackReader = new Hpack.Reader(in, paramBoolean);
    }
    
    private static IOException ioException(String paramString, Object... paramVarArgs)
      throws IOException
    {
      throw new IOException(String.format(paramString, paramVarArgs));
    }
    
    private void readData(FrameReader.Handler paramHandler, int paramInt1, int paramInt2, int paramInt3)
      throws IOException
    {
      if ((paramInt1 & 0x1) != 0) {}
      for (boolean bool = true;; bool = false)
      {
        paramHandler.data(bool, paramInt3, in, paramInt2);
        return;
      }
    }
    
    private void readGoAway(FrameReader.Handler paramHandler, int paramInt1, int paramInt2, int paramInt3)
      throws IOException
    {
      if (paramInt2 < 8) {
        throw ioException("TYPE_GOAWAY length < 8: %s", new Object[] { Integer.valueOf(paramInt2) });
      }
      paramInt1 = in.readInt();
      paramInt3 = in.readInt();
      paramInt2 -= 8;
      ErrorCode localErrorCode = ErrorCode.fromHttp2(paramInt3);
      if (localErrorCode == null) {
        throw ioException("TYPE_RST_STREAM unexpected error code: %d", new Object[] { Integer.valueOf(paramInt3) });
      }
      if (Util.skipByReading(in, paramInt2) != paramInt2) {
        throw new IOException("TYPE_GOAWAY opaque data was truncated");
      }
      paramHandler.goAway(paramInt1, localErrorCode);
    }
    
    private void readHeaders(FrameReader.Handler paramHandler, int paramInt1, int paramInt2, int paramInt3)
      throws IOException
    {
      if (paramInt3 == 0) {
        throw ioException("TYPE_HEADERS streamId == 0", new Object[0]);
      }
      boolean bool;
      if ((paramInt1 & 0x1) != 0) {
        bool = true;
      }
      int i;
      do
      {
        for (;;)
        {
          hpackReader.readHeaders(paramInt2);
          if ((paramInt1 & 0x4) == 0) {
            break;
          }
          hpackReader.emitReferenceSet();
          paramHandler.headers(false, bool, paramInt3, -1, -1, hpackReader.getAndReset(), HeadersMode.HTTP_20_HEADERS);
          return;
          bool = false;
        }
        int j = in.readInt();
        i = in.readInt();
        paramInt2 = (0xFFFF0000 & j) >> 16;
        paramInt1 = j & 0xFF;
        if ((paramInt1 & 0x1) != 0) {}
        for (bool = true; (0xFF00 & j) >> 8 != 10; bool = false) {
          throw ioException("TYPE_CONTINUATION didn't have FLAG_END_HEADERS", new Object[0]);
        }
      } while ((i & 0x7FFFFFFF) == paramInt3);
      throw ioException("TYPE_CONTINUATION streamId changed", new Object[0]);
    }
    
    private void readPing(FrameReader.Handler paramHandler, int paramInt1, int paramInt2, int paramInt3)
      throws IOException
    {
      boolean bool = true;
      if (paramInt2 != 8) {
        throw ioException("TYPE_PING length != 8: %s", new Object[] { Integer.valueOf(paramInt2) });
      }
      if (paramInt3 != 0) {
        throw ioException("TYPE_PING streamId != 0", new Object[0]);
      }
      paramInt2 = in.readInt();
      paramInt3 = in.readInt();
      if ((paramInt1 & 0x1) != 0) {}
      for (;;)
      {
        paramHandler.ping(bool, paramInt2, paramInt3);
        return;
        bool = false;
      }
    }
    
    private void readPriority(FrameReader.Handler paramHandler, int paramInt1, int paramInt2, int paramInt3)
      throws IOException
    {
      if (paramInt2 != 4) {
        throw ioException("TYPE_PRIORITY length: %d != 4", new Object[] { Integer.valueOf(paramInt2) });
      }
      if (paramInt3 == 0) {
        throw ioException("TYPE_PRIORITY streamId == 0", new Object[0]);
      }
      paramHandler.priority(paramInt3, in.readInt() & 0x7FFFFFFF);
    }
    
    private void readPushPromise(FrameReader.Handler paramHandler, int paramInt1, int paramInt2, int paramInt3) {}
    
    private void readRstStream(FrameReader.Handler paramHandler, int paramInt1, int paramInt2, int paramInt3)
      throws IOException
    {
      if (paramInt2 != 4) {
        throw ioException("TYPE_RST_STREAM length: %d != 4", new Object[] { Integer.valueOf(paramInt2) });
      }
      if (paramInt3 == 0) {
        throw ioException("TYPE_RST_STREAM streamId == 0", new Object[0]);
      }
      paramInt1 = in.readInt();
      ErrorCode localErrorCode = ErrorCode.fromHttp2(paramInt1);
      if (localErrorCode == null) {
        throw ioException("TYPE_RST_STREAM unexpected error code: %d", new Object[] { Integer.valueOf(paramInt1) });
      }
      paramHandler.rstStream(paramInt3, localErrorCode);
    }
    
    private void readSettings(FrameReader.Handler paramHandler, int paramInt1, int paramInt2, int paramInt3)
      throws IOException
    {
      if (paramInt2 % 8 != 0) {
        throw ioException("TYPE_SETTINGS length %% 8 != 0: %s", new Object[] { Integer.valueOf(paramInt2) });
      }
      if (paramInt3 != 0) {
        throw ioException("TYPE_SETTINGS streamId != 0", new Object[0]);
      }
      Settings localSettings = new Settings();
      paramInt1 = 0;
      for (;;)
      {
        if (paramInt1 >= paramInt2)
        {
          paramHandler.settings(false, localSettings);
          return;
        }
        localSettings.set(in.readInt() & 0xFFFFFF, 0, in.readInt());
        paramInt1 += 8;
      }
    }
    
    private void readWindowUpdate(FrameReader.Handler paramHandler, int paramInt1, int paramInt2, int paramInt3)
      throws IOException
    {
      paramInt2 = in.readInt();
      if ((paramInt1 & 0x1) != 0) {}
      for (boolean bool = true;; bool = false)
      {
        paramHandler.windowUpdate(paramInt3, paramInt2 & 0x7FFFFFFF, bool);
        return;
      }
    }
    
    public void close()
      throws IOException
    {
      in.close();
    }
    
    public boolean nextFrame(FrameReader.Handler paramHandler)
      throws IOException
    {
      int m;
      int j;
      int k;
      try
      {
        int i = in.readInt();
        m = in.readInt();
        j = (0xFFFF0000 & i) >> 16;
        k = i & 0xFF;
        m &= 0x7FFFFFFF;
        switch ((0xFF00 & i) >> 8)
        {
        case 8: 
        default: 
          throw new UnsupportedOperationException("TODO");
        }
      }
      catch (IOException paramHandler)
      {
        return false;
      }
      readData(paramHandler, k, j, m);
      return true;
      readHeaders(paramHandler, k, j, m);
      return true;
      readPriority(paramHandler, k, j, m);
      return true;
      readRstStream(paramHandler, k, j, m);
      return true;
      readSettings(paramHandler, k, j, m);
      return true;
      readPushPromise(paramHandler, k, j, m);
      return true;
      readPing(paramHandler, k, j, m);
      return true;
      readGoAway(paramHandler, k, j, m);
      return true;
      readWindowUpdate(paramHandler, k, j, m);
      return true;
    }
    
    public void readConnectionHeader()
      throws IOException
    {
      if (client) {}
      byte[] arrayOfByte;
      do
      {
        return;
        arrayOfByte = new byte[Http20Draft06.CONNECTION_HEADER.length];
        in.readFully(arrayOfByte);
      } while (Arrays.equals(arrayOfByte, Http20Draft06.CONNECTION_HEADER));
      throw ioException("Expected a connection header but was " + Arrays.toString(arrayOfByte), new Object[0]);
    }
  }
  
  static final class Writer
    implements FrameWriter
  {
    private final boolean client;
    private final ByteArrayOutputStream hpackBuffer;
    private final Hpack.Writer hpackWriter;
    private final DataOutputStream out;
    
    Writer(OutputStream paramOutputStream, boolean paramBoolean)
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
      //   18: invokestatic 77	com/squareup/okhttp/internal/spdy/Http20Draft06:access$0	()[B
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
          if (i >= 10) {
            return;
          }
          if (paramSettings.isSet(i))
          {
            out.writeInt(0xFFFFFF & i);
            out.writeInt(paramSettings.get(i));
          }
        }
        finally {}
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
}

/* Location:
 * Qualified Name:     com.squareup.okhttp.internal.spdy.Http20Draft06
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */