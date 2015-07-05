package com.squareup.okhttp.internal.http;

import com.squareup.okhttp.Connection;
import com.squareup.okhttp.internal.AbstractOutputStream;
import com.squareup.okhttp.internal.Util;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.CacheRequest;
import java.net.ProtocolException;
import java.net.Socket;

public final class HttpTransport
  implements Transport
{
  public static final int DEFAULT_CHUNK_LENGTH = 1024;
  private static final int DISCARD_STREAM_TIMEOUT_MILLIS = 100;
  private final HttpEngine httpEngine;
  private OutputStream requestOut;
  private final InputStream socketIn;
  private final OutputStream socketOut;
  
  public HttpTransport(HttpEngine paramHttpEngine, OutputStream paramOutputStream, InputStream paramInputStream)
  {
    httpEngine = paramHttpEngine;
    socketOut = paramOutputStream;
    requestOut = paramOutputStream;
    socketIn = paramInputStream;
  }
  
  private static boolean discardStream(HttpEngine paramHttpEngine, InputStream paramInputStream)
  {
    paramHttpEngine = connection;
    if (paramHttpEngine == null) {}
    do
    {
      return false;
      paramHttpEngine = paramHttpEngine.getSocket();
    } while (paramHttpEngine == null);
    try
    {
      int i = paramHttpEngine.getSoTimeout();
      paramHttpEngine.setSoTimeout(100);
      try
      {
        Util.skipAll(paramInputStream);
        return true;
      }
      finally
      {
        paramHttpEngine.setSoTimeout(i);
      }
      return false;
    }
    catch (IOException paramHttpEngine) {}
  }
  
  public OutputStream createRequestBody()
    throws IOException
  {
    boolean bool2 = httpEngine.requestHeaders.isChunked();
    boolean bool1 = bool2;
    if (!bool2)
    {
      bool1 = bool2;
      if (httpEngine.policy.getChunkLength() > 0)
      {
        bool1 = bool2;
        if (httpEngine.connection.getHttpMinorVersion() != 0)
        {
          httpEngine.requestHeaders.setChunked();
          bool1 = true;
        }
      }
    }
    if (bool1)
    {
      int j = httpEngine.policy.getChunkLength();
      int i = j;
      if (j == -1) {
        i = 1024;
      }
      writeRequestHeaders();
      return new ChunkedOutputStream(requestOut, i, null);
    }
    long l = httpEngine.policy.getFixedContentLength();
    if (l != -1L)
    {
      httpEngine.requestHeaders.setContentLength(l);
      writeRequestHeaders();
      return new FixedLengthOutputStream(requestOut, l, null);
    }
    l = httpEngine.requestHeaders.getContentLength();
    if (l > 2147483647L) {
      throw new IllegalArgumentException("Use setFixedLengthStreamingMode() or setChunkedStreamingMode() for requests larger than 2 GiB.");
    }
    if (l != -1L)
    {
      writeRequestHeaders();
      return new RetryableOutputStream((int)l);
    }
    return new RetryableOutputStream();
  }
  
  public void flushRequest()
    throws IOException
  {
    requestOut.flush();
    requestOut = socketOut;
  }
  
  public InputStream getTransferStream(CacheRequest paramCacheRequest)
    throws IOException
  {
    if (!httpEngine.hasResponseBody()) {
      return new FixedLengthInputStream(socketIn, paramCacheRequest, httpEngine, 0L);
    }
    if (httpEngine.responseHeaders.isChunked()) {
      return new ChunkedInputStream(socketIn, paramCacheRequest, this);
    }
    if (httpEngine.responseHeaders.getContentLength() != -1L) {
      return new FixedLengthInputStream(socketIn, paramCacheRequest, httpEngine, httpEngine.responseHeaders.getContentLength());
    }
    return new UnknownLengthHttpInputStream(socketIn, paramCacheRequest, httpEngine);
  }
  
  public boolean makeReusable(boolean paramBoolean, OutputStream paramOutputStream, InputStream paramInputStream)
  {
    if (paramBoolean) {}
    while (((paramOutputStream != null) && (!((AbstractOutputStream)paramOutputStream).isClosed())) || (httpEngine.requestHeaders.hasConnectionClose()) || ((httpEngine.responseHeaders != null) && (httpEngine.responseHeaders.hasConnectionClose())) || ((paramInputStream instanceof UnknownLengthHttpInputStream))) {
      return false;
    }
    if (paramInputStream != null) {
      return discardStream(httpEngine, paramInputStream);
    }
    return true;
  }
  
  public ResponseHeaders readResponseHeaders()
    throws IOException
  {
    Object localObject = RawHeaders.fromBytes(socketIn);
    httpEngine.connection.setHttpMinorVersion(((RawHeaders)localObject).getHttpMinorVersion());
    httpEngine.receiveHeaders((RawHeaders)localObject);
    localObject = new ResponseHeaders(httpEngine.uri, (RawHeaders)localObject);
    ((ResponseHeaders)localObject).setTransport("http/1.1");
    return (ResponseHeaders)localObject;
  }
  
  public void writeRequestBody(RetryableOutputStream paramRetryableOutputStream)
    throws IOException
  {
    paramRetryableOutputStream.writeToSocket(requestOut);
  }
  
  public void writeRequestHeaders()
    throws IOException
  {
    httpEngine.writingRequestHeaders();
    byte[] arrayOfByte = httpEngine.requestHeaders.getHeaders().toBytes();
    requestOut.write(arrayOfByte);
  }
  
  private static class ChunkedInputStream
    extends AbstractHttpInputStream
  {
    private static final int NO_CHUNK_YET = -1;
    private int bytesRemainingInChunk = -1;
    private boolean hasMoreChunks = true;
    private final HttpTransport transport;
    
    ChunkedInputStream(InputStream paramInputStream, CacheRequest paramCacheRequest, HttpTransport paramHttpTransport)
      throws IOException
    {
      super(httpEngine, paramCacheRequest);
      transport = paramHttpTransport;
    }
    
    private void readChunkSize()
      throws IOException
    {
      if (bytesRemainingInChunk != -1) {
        Util.readAsciiLine(in);
      }
      String str = Util.readAsciiLine(in);
      int i = str.indexOf(";");
      Object localObject = str;
      if (i != -1) {
        localObject = str.substring(0, i);
      }
      try
      {
        bytesRemainingInChunk = Integer.parseInt(((String)localObject).trim(), 16);
        if (bytesRemainingInChunk == 0)
        {
          hasMoreChunks = false;
          localObject = httpEngine.responseHeaders.getHeaders();
          RawHeaders.readHeaders(transport.socketIn, (RawHeaders)localObject);
          httpEngine.receiveHeaders((RawHeaders)localObject);
          endOfInput();
        }
        return;
      }
      catch (NumberFormatException localNumberFormatException)
      {
        throw new ProtocolException("Expected a hex chunk size but was " + (String)localObject);
      }
    }
    
    public int available()
      throws IOException
    {
      checkNotClosed();
      if ((!hasMoreChunks) || (bytesRemainingInChunk == -1)) {
        return 0;
      }
      return Math.min(in.available(), bytesRemainingInChunk);
    }
    
    public void close()
      throws IOException
    {
      if (closed) {
        return;
      }
      if ((hasMoreChunks) && (!HttpTransport.discardStream(httpEngine, this))) {
        unexpectedEndOfInput();
      }
      closed = true;
    }
    
    public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
      throws IOException
    {
      Util.checkOffsetAndCount(paramArrayOfByte.length, paramInt1, paramInt2);
      checkNotClosed();
      if (!hasMoreChunks) {
        return -1;
      }
      if ((bytesRemainingInChunk == 0) || (bytesRemainingInChunk == -1))
      {
        readChunkSize();
        if (!hasMoreChunks) {
          return -1;
        }
      }
      paramInt2 = in.read(paramArrayOfByte, paramInt1, Math.min(paramInt2, bytesRemainingInChunk));
      if (paramInt2 == -1)
      {
        unexpectedEndOfInput();
        throw new IOException("unexpected end of stream");
      }
      bytesRemainingInChunk -= paramInt2;
      cacheWrite(paramArrayOfByte, paramInt1, paramInt2);
      return paramInt2;
    }
  }
  
  private static final class ChunkedOutputStream
    extends AbstractOutputStream
  {
    private static final byte[] CRLF = { 13, 10 };
    private static final byte[] FINAL_CHUNK = { 48, 13, 10, 13, 10 };
    private static final byte[] HEX_DIGITS = { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102 };
    private final ByteArrayOutputStream bufferedChunk;
    private final byte[] hex;
    private final int maxChunkLength;
    private final OutputStream socketOut;
    
    private ChunkedOutputStream(OutputStream paramOutputStream, int paramInt)
    {
      byte[] arrayOfByte = new byte[10];
      arrayOfByte[8] = 13;
      arrayOfByte[9] = 10;
      hex = arrayOfByte;
      socketOut = paramOutputStream;
      maxChunkLength = Math.max(1, dataLength(paramInt));
      bufferedChunk = new ByteArrayOutputStream(paramInt);
    }
    
    private int dataLength(int paramInt)
    {
      int j = 4;
      int i = paramInt - 4;
      for (;;)
      {
        if (i <= 0) {
          return paramInt - j;
        }
        j += 1;
        i >>= 4;
      }
    }
    
    private void writeBufferedChunkToSocket()
      throws IOException
    {
      int i = bufferedChunk.size();
      if (i <= 0) {
        return;
      }
      writeHex(i);
      bufferedChunk.writeTo(socketOut);
      bufferedChunk.reset();
      socketOut.write(CRLF);
    }
    
    private void writeHex(int paramInt)
      throws IOException
    {
      int i = 8;
      int j;
      int k;
      do
      {
        byte[] arrayOfByte = hex;
        j = i - 1;
        arrayOfByte[j] = HEX_DIGITS[(paramInt & 0xF)];
        k = paramInt >>> 4;
        i = j;
        paramInt = k;
      } while (k != 0);
      socketOut.write(hex, j, hex.length - j);
    }
    
    /* Error */
    public void close()
      throws IOException
    {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: aload_0
      //   3: getfield 107	com/squareup/okhttp/internal/http/HttpTransport$ChunkedOutputStream:closed	Z
      //   6: istore_1
      //   7: iload_1
      //   8: ifeq +6 -> 14
      //   11: aload_0
      //   12: monitorexit
      //   13: return
      //   14: aload_0
      //   15: iconst_1
      //   16: putfield 107	com/squareup/okhttp/internal/http/HttpTransport$ChunkedOutputStream:closed	Z
      //   19: aload_0
      //   20: invokespecial 109	com/squareup/okhttp/internal/http/HttpTransport$ChunkedOutputStream:writeBufferedChunkToSocket	()V
      //   23: aload_0
      //   24: getfield 53	com/squareup/okhttp/internal/http/HttpTransport$ChunkedOutputStream:socketOut	Ljava/io/OutputStream;
      //   27: getstatic 44	com/squareup/okhttp/internal/http/HttpTransport$ChunkedOutputStream:FINAL_CHUNK	[B
      //   30: invokevirtual 98	java/io/OutputStream:write	([B)V
      //   33: goto -22 -> 11
      //   36: astore_2
      //   37: aload_0
      //   38: monitorexit
      //   39: aload_2
      //   40: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	41	0	this	ChunkedOutputStream
      //   6	2	1	bool	boolean
      //   36	4	2	localObject	Object
      // Exception table:
      //   from	to	target	type
      //   2	7	36	finally
      //   14	33	36	finally
    }
    
    /* Error */
    public void flush()
      throws IOException
    {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: aload_0
      //   3: getfield 107	com/squareup/okhttp/internal/http/HttpTransport$ChunkedOutputStream:closed	Z
      //   6: istore_1
      //   7: iload_1
      //   8: ifeq +6 -> 14
      //   11: aload_0
      //   12: monitorexit
      //   13: return
      //   14: aload_0
      //   15: invokespecial 109	com/squareup/okhttp/internal/http/HttpTransport$ChunkedOutputStream:writeBufferedChunkToSocket	()V
      //   18: aload_0
      //   19: getfield 53	com/squareup/okhttp/internal/http/HttpTransport$ChunkedOutputStream:socketOut	Ljava/io/OutputStream;
      //   22: invokevirtual 112	java/io/OutputStream:flush	()V
      //   25: goto -14 -> 11
      //   28: astore_2
      //   29: aload_0
      //   30: monitorexit
      //   31: aload_2
      //   32: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	33	0	this	ChunkedOutputStream
      //   6	2	1	bool	boolean
      //   28	4	2	localObject	Object
      // Exception table:
      //   from	to	target	type
      //   2	7	28	finally
      //   14	25	28	finally
    }
    
    public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
      throws IOException
    {
      for (;;)
      {
        int i;
        try
        {
          checkNotClosed();
          Util.checkOffsetAndCount(paramArrayOfByte.length, paramInt1, paramInt2);
          if (paramInt2 <= 0) {
            return;
          }
          if ((bufferedChunk.size() > 0) || (paramInt2 < maxChunkLength))
          {
            int j = Math.min(paramInt2, maxChunkLength - bufferedChunk.size());
            bufferedChunk.write(paramArrayOfByte, paramInt1, j);
            i = j;
            if (bufferedChunk.size() == maxChunkLength)
            {
              writeBufferedChunkToSocket();
              i = j;
            }
          }
          else
          {
            i = maxChunkLength;
            writeHex(i);
            socketOut.write(paramArrayOfByte, paramInt1, i);
            socketOut.write(CRLF);
          }
        }
        finally {}
        paramInt1 += i;
        paramInt2 -= i;
      }
    }
  }
  
  private static class FixedLengthInputStream
    extends AbstractHttpInputStream
  {
    private long bytesRemaining;
    
    public FixedLengthInputStream(InputStream paramInputStream, CacheRequest paramCacheRequest, HttpEngine paramHttpEngine, long paramLong)
      throws IOException
    {
      super(paramHttpEngine, paramCacheRequest);
      bytesRemaining = paramLong;
      if (bytesRemaining == 0L) {
        endOfInput();
      }
    }
    
    public int available()
      throws IOException
    {
      checkNotClosed();
      if (bytesRemaining == 0L) {
        return 0;
      }
      return (int)Math.min(in.available(), bytesRemaining);
    }
    
    public void close()
      throws IOException
    {
      if (closed) {
        return;
      }
      if ((bytesRemaining != 0L) && (!HttpTransport.discardStream(httpEngine, this))) {
        unexpectedEndOfInput();
      }
      closed = true;
    }
    
    public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
      throws IOException
    {
      Util.checkOffsetAndCount(paramArrayOfByte.length, paramInt1, paramInt2);
      checkNotClosed();
      if (bytesRemaining == 0L) {
        paramInt1 = -1;
      }
      do
      {
        return paramInt1;
        paramInt2 = in.read(paramArrayOfByte, paramInt1, (int)Math.min(paramInt2, bytesRemaining));
        if (paramInt2 == -1)
        {
          unexpectedEndOfInput();
          throw new ProtocolException("unexpected end of stream");
        }
        bytesRemaining -= paramInt2;
        cacheWrite(paramArrayOfByte, paramInt1, paramInt2);
        paramInt1 = paramInt2;
      } while (bytesRemaining != 0L);
      endOfInput();
      return paramInt2;
    }
  }
  
  private static final class FixedLengthOutputStream
    extends AbstractOutputStream
  {
    private long bytesRemaining;
    private final OutputStream socketOut;
    
    private FixedLengthOutputStream(OutputStream paramOutputStream, long paramLong)
    {
      socketOut = paramOutputStream;
      bytesRemaining = paramLong;
    }
    
    public void close()
      throws IOException
    {
      if (closed) {}
      do
      {
        return;
        closed = true;
      } while (bytesRemaining <= 0L);
      throw new ProtocolException("unexpected end of stream");
    }
    
    public void flush()
      throws IOException
    {
      if (closed) {
        return;
      }
      socketOut.flush();
    }
    
    public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
      throws IOException
    {
      checkNotClosed();
      Util.checkOffsetAndCount(paramArrayOfByte.length, paramInt1, paramInt2);
      if (paramInt2 > bytesRemaining) {
        throw new ProtocolException("expected " + bytesRemaining + " bytes but received " + paramInt2);
      }
      socketOut.write(paramArrayOfByte, paramInt1, paramInt2);
      bytesRemaining -= paramInt2;
    }
  }
}

/* Location:
 * Qualified Name:     com.squareup.okhttp.internal.http.HttpTransport
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */