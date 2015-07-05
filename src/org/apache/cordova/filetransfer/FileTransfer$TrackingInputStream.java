package org.apache.cordova.filetransfer;

import java.io.FilterInputStream;
import java.io.InputStream;

abstract class FileTransfer$TrackingInputStream
  extends FilterInputStream
{
  public FileTransfer$TrackingInputStream(InputStream paramInputStream)
  {
    super(paramInputStream);
  }
  
  public abstract long getTotalRawBytesRead();
}

/* Location:
 * Qualified Name:     org.apache.cordova.filetransfer.FileTransfer.TrackingInputStream
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */