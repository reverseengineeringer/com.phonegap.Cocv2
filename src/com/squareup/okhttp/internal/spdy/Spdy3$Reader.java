package com.squareup.okhttp.internal.spdy;

import com.squareup.okhttp.internal.Util;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ProtocolException;
import java.util.List;

final class Spdy3$Reader
  implements FrameReader
{
  private final boolean client;
  private final DataInputStream in;
  private final NameValueBlockReader nameValueBlockReader;
  
  Spdy3$Reader(InputStream paramInputStream, boolean paramBoolean)
  {
    in = new DataInputStream(paramInputStream);
    nameValueBlockReader = new NameValueBlockReader(paramInputStream);
    client = paramBoolean;
  }
  
  private static IOException ioException(String paramString, Object... paramVarArgs)
    throws IOException
  {
    throw new IOException(String.format(paramString, paramVarArgs));
  }
  
  private void readGoAway(FrameReader.Handler paramHandler, int paramInt1, int paramInt2)
    throws IOException
  {
    if (paramInt2 != 8) {
      throw ioException("TYPE_GOAWAY length: %d != 8", new Object[] { Integer.valueOf(paramInt2) });
    }
    paramInt1 = in.readInt();
    paramInt2 = in.readInt();
    ErrorCode localErrorCode = ErrorCode.fromSpdyGoAway(paramInt2);
    if (localErrorCode == null) {
      throw ioException("TYPE_GOAWAY unexpected error code: %d", new Object[] { Integer.valueOf(paramInt2) });
    }
    paramHandler.goAway(paramInt1 & 0x7FFFFFFF, localErrorCode);
  }
  
  private void readHeaders(FrameReader.Handler paramHandler, int paramInt1, int paramInt2)
    throws IOException
  {
    paramHandler.headers(false, false, in.readInt() & 0x7FFFFFFF, -1, -1, nameValueBlockReader.readNameValueBlock(paramInt2 - 4), HeadersMode.SPDY_HEADERS);
  }
  
  private void readPing(FrameReader.Handler paramHandler, int paramInt1, int paramInt2)
    throws IOException
  {
    boolean bool2 = true;
    if (paramInt2 != 4) {
      throw ioException("TYPE_PING length: %d != 4", new Object[] { Integer.valueOf(paramInt2) });
    }
    paramInt1 = in.readInt();
    boolean bool3 = client;
    if (paramInt1 % 2 == 1)
    {
      bool1 = true;
      if (bool3 != bool1) {
        break label77;
      }
    }
    label77:
    for (boolean bool1 = bool2;; bool1 = false)
    {
      paramHandler.ping(bool1, paramInt1, 0);
      return;
      bool1 = false;
      break;
    }
  }
  
  private void readRstStream(FrameReader.Handler paramHandler, int paramInt1, int paramInt2)
    throws IOException
  {
    if (paramInt2 != 8) {
      throw ioException("TYPE_RST_STREAM length: %d != 8", new Object[] { Integer.valueOf(paramInt2) });
    }
    paramInt1 = in.readInt();
    paramInt2 = in.readInt();
    ErrorCode localErrorCode = ErrorCode.fromSpdy3Rst(paramInt2);
    if (localErrorCode == null) {
      throw ioException("TYPE_RST_STREAM unexpected error code: %d", new Object[] { Integer.valueOf(paramInt2) });
    }
    paramHandler.rstStream(paramInt1 & 0x7FFFFFFF, localErrorCode);
  }
  
  private void readSettings(FrameReader.Handler paramHandler, int paramInt1, int paramInt2)
    throws IOException
  {
    boolean bool = true;
    int i = in.readInt();
    if (paramInt2 != i * 8 + 4) {
      throw ioException("TYPE_SETTINGS length: %d != 4 + 8 * %d", new Object[] { Integer.valueOf(paramInt2), Integer.valueOf(i) });
    }
    Settings localSettings = new Settings();
    paramInt2 = 0;
    if (paramInt2 >= i) {
      if ((paramInt1 & 0x1) == 0) {
        break label123;
      }
    }
    for (;;)
    {
      paramHandler.settings(bool, localSettings);
      return;
      int j = in.readInt();
      localSettings.set(j & 0xFFFFFF, (0xFF000000 & j) >>> 24, in.readInt());
      paramInt2 += 1;
      break;
      label123:
      bool = false;
    }
  }
  
  private void readSynReply(FrameReader.Handler paramHandler, int paramInt1, int paramInt2)
    throws IOException
  {
    int i = in.readInt();
    List localList = nameValueBlockReader.readNameValueBlock(paramInt2 - 4);
    if ((paramInt1 & 0x1) != 0) {}
    for (boolean bool = true;; bool = false)
    {
      paramHandler.headers(false, bool, i & 0x7FFFFFFF, -1, -1, localList, HeadersMode.SPDY_REPLY);
      return;
    }
  }
  
  private void readSynStream(FrameReader.Handler paramHandler, int paramInt1, int paramInt2)
    throws IOException
  {
    int i = in.readInt();
    int j = in.readInt();
    int k = in.readShort();
    List localList = nameValueBlockReader.readNameValueBlock(paramInt2 - 10);
    boolean bool1;
    if ((paramInt1 & 0x1) != 0)
    {
      bool1 = true;
      if ((paramInt1 & 0x2) == 0) {
        break label98;
      }
    }
    label98:
    for (boolean bool2 = true;; bool2 = false)
    {
      paramHandler.headers(bool2, bool1, i & 0x7FFFFFFF, j & 0x7FFFFFFF, (0xE000 & k) >>> 13, localList, HeadersMode.SPDY_SYN_STREAM);
      return;
      bool1 = false;
      break;
    }
  }
  
  private void readWindowUpdate(FrameReader.Handler paramHandler, int paramInt1, int paramInt2)
    throws IOException
  {
    if (paramInt2 != 8) {
      throw ioException("TYPE_WINDOW_UPDATE length: %d != 8", new Object[] { Integer.valueOf(paramInt2) });
    }
    paramHandler.windowUpdate(in.readInt() & 0x7FFFFFFF, in.readInt() & 0x7FFFFFFF, false);
  }
  
  public void close()
    throws IOException
  {
    Util.closeAll(in, nameValueBlockReader);
  }
  
  public boolean nextFrame(FrameReader.Handler paramHandler)
    throws IOException
  {
    boolean bool = false;
    int j;
    int k;
    int m;
    for (;;)
    {
      try
      {
        j = in.readInt();
        k = in.readInt();
        if ((0x80000000 & j) != 0)
        {
          i = 1;
          m = (0xFF000000 & k) >>> 24;
          k &= 0xFFFFFF;
          if (i == 0) {
            break label326;
          }
          i = (0x7FFF0000 & j) >>> 16;
          if (i == 3) {
            break;
          }
          throw new ProtocolException("version != 3: " + i);
        }
      }
      catch (IOException paramHandler)
      {
        return false;
      }
      int i = 0;
    }
    switch (j & 0xFFFF)
    {
    case 10: 
    case 11: 
    case 12: 
    case 13: 
    case 14: 
    case 15: 
    default: 
      throw new IOException("Unexpected frame");
    case 1: 
      readSynStream(paramHandler, m, k);
      return true;
    case 2: 
      readSynReply(paramHandler, m, k);
      return true;
    case 3: 
      readRstStream(paramHandler, m, k);
      return true;
    case 4: 
      readSettings(paramHandler, m, k);
      return true;
    case 5: 
      if (k != 0) {
        throw ioException("TYPE_NOOP length: %d != 0", new Object[] { Integer.valueOf(k) });
      }
      paramHandler.noop();
      return true;
    case 6: 
      readPing(paramHandler, m, k);
      return true;
    case 7: 
      readGoAway(paramHandler, m, k);
      return true;
    case 8: 
      readHeaders(paramHandler, m, k);
      return true;
    case 9: 
      readWindowUpdate(paramHandler, m, k);
      return true;
    }
    Util.skipByReading(in, k);
    throw new UnsupportedOperationException("TODO");
    label326:
    if ((m & 0x1) != 0) {
      bool = true;
    }
    paramHandler.data(bool, j & 0x7FFFFFFF, in, k);
    return true;
  }
  
  public void readConnectionHeader() {}
}

/* Location:
 * Qualified Name:     com.squareup.okhttp.internal.spdy.Spdy3.Reader
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */