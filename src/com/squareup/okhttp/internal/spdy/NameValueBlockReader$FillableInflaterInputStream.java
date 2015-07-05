package com.squareup.okhttp.internal.spdy;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;

class NameValueBlockReader$FillableInflaterInputStream
  extends InflaterInputStream
{
  public NameValueBlockReader$FillableInflaterInputStream(InputStream paramInputStream, Inflater paramInflater)
  {
    super(paramInputStream, paramInflater);
  }
  
  public void fill()
    throws IOException
  {
    super.fill();
  }
}

/* Location:
 * Qualified Name:     com.squareup.okhttp.internal.spdy.NameValueBlockReader.FillableInflaterInputStream
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */