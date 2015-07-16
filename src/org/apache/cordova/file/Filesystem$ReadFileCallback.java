package org.apache.cordova.file;

import java.io.IOException;
import java.io.InputStream;

public abstract interface Filesystem$ReadFileCallback
{
  public abstract void handleData(InputStream paramInputStream, String paramString)
    throws IOException;
}

/* Location:
 * Qualified Name:     org.apache.cordova.file.Filesystem.ReadFileCallback
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */