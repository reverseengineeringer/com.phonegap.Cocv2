package com.squareup.okhttp.internal.spdy;

import com.squareup.okhttp.internal.Util;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;

class NameValueBlockReader
  implements Closeable
{
  private int compressedLimit;
  private final FillableInflaterInputStream fillableInflaterInputStream = new FillableInflaterInputStream(new InputStream()new Inflater
  {
    public void close()
      throws IOException
    {
      paramInputStream.close();
    }
    
    public int read()
      throws IOException
    {
      return Util.readSingleByte(this);
    }
    
    public int read(byte[] paramAnonymousArrayOfByte, int paramAnonymousInt1, int paramAnonymousInt2)
      throws IOException
    {
      paramAnonymousInt2 = Math.min(paramAnonymousInt2, compressedLimit);
      paramAnonymousInt1 = paramInputStream.read(paramAnonymousArrayOfByte, paramAnonymousInt1, paramAnonymousInt2);
      paramAnonymousArrayOfByte = NameValueBlockReader.this;
      compressedLimit -= paramAnonymousInt1;
      return paramAnonymousInt1;
    }
  }, new Inflater()
  {
    public int inflate(byte[] paramAnonymousArrayOfByte, int paramAnonymousInt1, int paramAnonymousInt2)
      throws DataFormatException
    {
      int j = super.inflate(paramAnonymousArrayOfByte, paramAnonymousInt1, paramAnonymousInt2);
      int i = j;
      if (j == 0)
      {
        i = j;
        if (needsDictionary())
        {
          setDictionary(Spdy3.DICTIONARY);
          i = super.inflate(paramAnonymousArrayOfByte, paramAnonymousInt1, paramAnonymousInt2);
        }
      }
      return i;
    }
  });
  private final DataInputStream nameValueBlockIn = new DataInputStream(fillableInflaterInputStream);
  
  NameValueBlockReader(final InputStream paramInputStream) {}
  
  private void doneReading()
    throws IOException
  {
    if (compressedLimit == 0) {}
    do
    {
      return;
      fillableInflaterInputStream.fill();
    } while (compressedLimit == 0);
    throw new IOException("compressedLimit > 0: " + compressedLimit);
  }
  
  private String readString()
    throws DataFormatException, IOException
  {
    int i = nameValueBlockIn.readInt();
    byte[] arrayOfByte = new byte[i];
    Util.readFully(nameValueBlockIn, arrayOfByte);
    return new String(arrayOfByte, 0, i, "UTF-8");
  }
  
  public void close()
    throws IOException
  {
    nameValueBlockIn.close();
  }
  
  public List<String> readNameValueBlock(int paramInt)
    throws IOException
  {
    compressedLimit += paramInt;
    int i;
    try
    {
      i = nameValueBlockIn.readInt();
      if (i < 0) {
        throw new IOException("numberOfPairs < 0: " + i);
      }
    }
    catch (DataFormatException localDataFormatException)
    {
      throw new IOException(localDataFormatException.getMessage());
    }
    if (i > 1024) {
      throw new IOException("numberOfPairs > 1024: " + i);
    }
    ArrayList localArrayList = new ArrayList(i * 2);
    paramInt = 0;
    for (;;)
    {
      if (paramInt >= i)
      {
        doneReading();
        return localArrayList;
      }
      String str1 = readString();
      String str2 = readString();
      if (str1.length() == 0) {
        throw new IOException("name.length == 0");
      }
      localArrayList.add(str1);
      localArrayList.add(str2);
      paramInt += 1;
    }
  }
  
  static class FillableInflaterInputStream
    extends InflaterInputStream
  {
    public FillableInflaterInputStream(InputStream paramInputStream, Inflater paramInflater)
    {
      super(paramInflater);
    }
    
    public void fill()
      throws IOException
    {
      super.fill();
    }
  }
}

/* Location:
 * Qualified Name:     com.squareup.okhttp.internal.spdy.NameValueBlockReader
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */