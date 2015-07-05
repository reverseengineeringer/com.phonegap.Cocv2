package com.squareup.okhttp.internal.spdy;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

class Hpack$Writer
{
  private final OutputStream out;
  
  Hpack$Writer(OutputStream paramOutputStream)
  {
    out = paramOutputStream;
  }
  
  public void writeHeaders(List<String> paramList)
    throws IOException
  {
    int i = 0;
    int j = paramList.size();
    for (;;)
    {
      if (i >= j) {
        return;
      }
      out.write(96);
      writeString((String)paramList.get(i));
      writeString((String)paramList.get(i + 1));
      i += 2;
    }
  }
  
  public void writeInt(int paramInt1, int paramInt2, int paramInt3)
    throws IOException
  {
    if (paramInt1 < paramInt2)
    {
      out.write(paramInt3 | paramInt1);
      return;
    }
    out.write(paramInt3 | paramInt2);
    paramInt1 -= paramInt2;
    for (;;)
    {
      if (paramInt1 < 128)
      {
        out.write(paramInt1);
        return;
      }
      out.write(paramInt1 & 0x7F | 0x80);
      paramInt1 >>>= 7;
    }
  }
  
  public void writeString(String paramString)
    throws IOException
  {
    paramString = paramString.getBytes("UTF-8");
    writeInt(paramString.length, 255, 0);
    out.write(paramString);
  }
}

/* Location:
 * Qualified Name:     com.squareup.okhttp.internal.spdy.Hpack.Writer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */