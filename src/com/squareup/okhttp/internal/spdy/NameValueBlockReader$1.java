package com.squareup.okhttp.internal.spdy;

import com.squareup.okhttp.internal.Util;
import java.io.IOException;
import java.io.InputStream;

class NameValueBlockReader$1
  extends InputStream
{
  NameValueBlockReader$1(NameValueBlockReader paramNameValueBlockReader, InputStream paramInputStream) {}
  
  public void close()
    throws IOException
  {
    val$in.close();
  }
  
  public int read()
    throws IOException
  {
    return Util.readSingleByte(this);
  }
  
  public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    paramInt2 = Math.min(paramInt2, NameValueBlockReader.access$0(this$0));
    paramInt1 = val$in.read(paramArrayOfByte, paramInt1, paramInt2);
    paramArrayOfByte = this$0;
    NameValueBlockReader.access$1(paramArrayOfByte, NameValueBlockReader.access$0(paramArrayOfByte) - paramInt1);
    return paramInt1;
  }
}

/* Location:
 * Qualified Name:     com.squareup.okhttp.internal.spdy.NameValueBlockReader.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */