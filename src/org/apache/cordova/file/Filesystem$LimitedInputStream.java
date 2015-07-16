package org.apache.cordova.file;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Filesystem$LimitedInputStream
  extends FilterInputStream
{
  long numBytesToRead;
  
  public Filesystem$LimitedInputStream(Filesystem paramFilesystem, InputStream paramInputStream, long paramLong)
  {
    super(paramInputStream);
    numBytesToRead = paramLong;
  }
  
  public int read()
    throws IOException
  {
    if (numBytesToRead <= 0L) {
      return -1;
    }
    numBytesToRead -= 1L;
    return in.read();
  }
  
  public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    if (numBytesToRead <= 0L) {
      return -1;
    }
    int i = paramInt2;
    if (paramInt2 > numBytesToRead) {
      i = (int)numBytesToRead;
    }
    paramInt1 = in.read(paramArrayOfByte, paramInt1, i);
    numBytesToRead -= paramInt1;
    return paramInt1;
  }
}

/* Location:
 * Qualified Name:     org.apache.cordova.file.Filesystem.LimitedInputStream
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */