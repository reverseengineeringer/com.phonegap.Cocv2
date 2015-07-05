package com.squareup.okhttp.internal.spdy;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SpdyConnection$Builder
{
  private boolean client;
  private IncomingStreamHandler handler = IncomingStreamHandler.REFUSE_INCOMING_STREAMS;
  private String hostName;
  private InputStream in;
  private OutputStream out;
  private Variant variant = Variant.SPDY3;
  
  public SpdyConnection$Builder(String paramString, boolean paramBoolean, InputStream paramInputStream, OutputStream paramOutputStream)
  {
    hostName = paramString;
    client = paramBoolean;
    in = paramInputStream;
    out = paramOutputStream;
  }
  
  public SpdyConnection$Builder(String paramString, boolean paramBoolean, Socket paramSocket)
    throws IOException
  {
    this(paramString, paramBoolean, paramSocket.getInputStream(), paramSocket.getOutputStream());
  }
  
  public SpdyConnection$Builder(boolean paramBoolean, InputStream paramInputStream, OutputStream paramOutputStream)
  {
    this("", paramBoolean, paramInputStream, paramOutputStream);
  }
  
  public SpdyConnection$Builder(boolean paramBoolean, Socket paramSocket)
    throws IOException
  {
    this("", paramBoolean, paramSocket.getInputStream(), paramSocket.getOutputStream());
  }
  
  public SpdyConnection build()
  {
    return new SpdyConnection(this, null);
  }
  
  public Builder handler(IncomingStreamHandler paramIncomingStreamHandler)
  {
    handler = paramIncomingStreamHandler;
    return this;
  }
  
  public Builder http20Draft06()
  {
    variant = Variant.HTTP_20_DRAFT_06;
    return this;
  }
  
  public Builder spdy3()
  {
    variant = Variant.SPDY3;
    return this;
  }
}

/* Location:
 * Qualified Name:     com.squareup.okhttp.internal.spdy.SpdyConnection.Builder
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */