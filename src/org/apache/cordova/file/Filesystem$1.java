package org.apache.cordova.file;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

class Filesystem$1
  implements Filesystem.ReadFileCallback
{
  Filesystem$1(Filesystem paramFilesystem, LocalFilesystemURL paramLocalFilesystemURL) {}
  
  public void handleData(InputStream paramInputStream, String paramString)
    throws IOException
  {
    if (paramInputStream != null)
    {
      paramString = this$0.getOutputStreamForURL(val$destination);
      byte[] arrayOfByte = new byte[' '];
      for (;;)
      {
        int i = paramInputStream.read(arrayOfByte, 0, 8192);
        if (i <= 0)
        {
          paramString.close();
          return;
        }
        paramString.write(arrayOfByte, 0, i);
      }
    }
    throw new IOException("Cannot read file at source URL");
  }
}

/* Location:
 * Qualified Name:     org.apache.cordova.file.Filesystem.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */