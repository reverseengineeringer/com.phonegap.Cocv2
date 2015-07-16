package com.squareup.okhttp.internal.spdy;

import com.squareup.okhttp.internal.Util;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

final class Http20Draft06$Reader
  implements FrameReader
{
  private final boolean client;
  private final Hpack.Reader hpackReader;
  private final DataInputStream in;
  
  Http20Draft06$Reader(InputStream paramInputStream, boolean paramBoolean)
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
    while (paramInt1 < paramInt2)
    {
      localSettings.set(in.readInt() & 0xFFFFFF, 0, in.readInt());
      paramInt1 += 8;
    }
    paramHandler.settings(false, localSettings);
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
      arrayOfByte = new byte[Http20Draft06.access$000().length];
      in.readFully(arrayOfByte);
    } while (Arrays.equals(arrayOfByte, Http20Draft06.access$000()));
    throw ioException("Expected a connection header but was " + Arrays.toString(arrayOfByte), new Object[0]);
  }
}

/* Location:
 * Qualified Name:     com.squareup.okhttp.internal.spdy.Http20Draft06.Reader
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */