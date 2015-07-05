package com.squareup.okhttp.internal.spdy;

import com.squareup.okhttp.internal.Platform;
import com.squareup.okhttp.internal.Util;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ProtocolException;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.List;
import java.util.zip.Deflater;

final class Spdy3
  implements Variant
{
  static final byte[] DICTIONARY;
  static final int FLAG_FIN = 1;
  static final int FLAG_UNIDIRECTIONAL = 2;
  static final int TYPE_CREDENTIAL = 16;
  static final int TYPE_DATA = 0;
  static final int TYPE_GOAWAY = 7;
  static final int TYPE_HEADERS = 8;
  static final int TYPE_NOOP = 5;
  static final int TYPE_PING = 6;
  static final int TYPE_RST_STREAM = 3;
  static final int TYPE_SETTINGS = 4;
  static final int TYPE_SYN_REPLY = 2;
  static final int TYPE_SYN_STREAM = 1;
  static final int TYPE_WINDOW_UPDATE = 9;
  static final int VERSION = 3;
  
  static
  {
    try
    {
      DICTIONARY = "\000\000\000\007options\000\000\000\004head\000\000\000\004post\000\000\000\003put\000\000\000\006delete\000\000\000\005trace\000\000\000\006accept\000\000\000\016accept-charset\000\000\000\017accept-encoding\000\000\000\017accept-language\000\000\000\raccept-ranges\000\000\000\003age\000\000\000\005allow\000\000\000\rauthorization\000\000\000\rcache-control\000\000\000\nconnection\000\000\000\fcontent-base\000\000\000\020content-encoding\000\000\000\020content-language\000\000\000\016content-length\000\000\000\020content-location\000\000\000\013content-md5\000\000\000\rcontent-range\000\000\000\fcontent-type\000\000\000\004date\000\000\000\004etag\000\000\000\006expect\000\000\000\007expires\000\000\000\004from\000\000\000\004host\000\000\000\bif-match\000\000\000\021if-modified-since\000\000\000\rif-none-match\000\000\000\bif-range\000\000\000\023if-unmodified-since\000\000\000\rlast-modified\000\000\000\blocation\000\000\000\fmax-forwards\000\000\000\006pragma\000\000\000\022proxy-authenticate\000\000\000\023proxy-authorization\000\000\000\005range\000\000\000\007referer\000\000\000\013retry-after\000\000\000\006server\000\000\000\002te\000\000\000\007trailer\000\000\000\021transfer-encoding\000\000\000\007upgrade\000\000\000\nuser-agent\000\000\000\004vary\000\000\000\003via\000\000\000\007warning\000\000\000\020www-authenticate\000\000\000\006method\000\000\000\003get\000\000\000\006status\000\000\000\006200 OK\000\000\000\007version\000\000\000\bHTTP/1.1\000\000\000\003url\000\000\000\006public\000\000\000\nset-cookie\000\000\000\nkeep-alive\000\000\000\006origin100101201202205206300302303304305306307402405406407408409410411412413414415416417502504505203 Non-Authoritative Information204 No Content301 Moved Permanently400 Bad Request401 Unauthorized403 Forbidden404 Not Found500 Internal Server Error501 Not Implemented503 Service UnavailableJan Feb Mar Apr May Jun Jul Aug Sept Oct Nov Dec 00:00:00 Mon, Tue, Wed, Thu, Fri, Sat, Sun, GMTchunked,text/html,image/png,image/jpg,image/gif,application/xml,application/xhtml+xml,text/plain,text/javascript,publicprivatemax-age=gzip,deflate,sdchcharset=utf-8charset=iso-8859-1,utf-,*,enq=0.".getBytes(Util.UTF_8.name());
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
    private final DataInputStream in;
    private final NameValueBlockReader nameValueBlockReader;
    
    Reader(InputStream paramInputStream, boolean paramBoolean)
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
  
  static final class Writer
    implements FrameWriter
  {
    private final boolean client;
    private final ByteArrayOutputStream nameValueBlockBuffer;
    private final DataOutputStream nameValueBlockOut;
    private final DataOutputStream out;
    
    Writer(OutputStream paramOutputStream, boolean paramBoolean)
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
}

/* Location:
 * Qualified Name:     com.squareup.okhttp.internal.spdy.Spdy3
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */