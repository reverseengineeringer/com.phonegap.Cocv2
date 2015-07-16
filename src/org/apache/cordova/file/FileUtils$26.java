package org.apache.cordova.file;

import android.util.Base64;
import android.util.Log;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;

class FileUtils$26
  implements Filesystem.ReadFileCallback
{
  FileUtils$26(FileUtils paramFileUtils, int paramInt, String paramString, CallbackContext paramCallbackContext) {}
  
  public void handleData(InputStream paramInputStream, String paramString)
  {
    for (;;)
    {
      ByteArrayOutputStream localByteArrayOutputStream;
      try
      {
        localByteArrayOutputStream = new ByteArrayOutputStream();
        byte[] arrayOfByte = new byte[' '];
        int i = paramInputStream.read(arrayOfByte, 0, 8192);
        if (i <= 0) {}
        switch (val$resultType)
        {
        case 1: 
          paramInputStream = Base64.encode(localByteArrayOutputStream.toByteArray(), 2);
          paramInputStream = "data:" + paramString + ";base64," + new String(paramInputStream, "US-ASCII");
          paramInputStream = new PluginResult(PluginResult.Status.OK, paramInputStream);
          val$callbackContext.sendPluginResult(paramInputStream);
          return;
          localByteArrayOutputStream.write(arrayOfByte, 0, i);
          continue;
          paramInputStream = new PluginResult(PluginResult.Status.OK, localByteArrayOutputStream.toString(val$encoding));
        }
      }
      catch (IOException paramInputStream)
      {
        Log.d("FileUtils", paramInputStream.getLocalizedMessage());
        val$callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.IO_EXCEPTION, FileUtils.NOT_READABLE_ERR));
        return;
      }
      continue;
      paramInputStream = new PluginResult(PluginResult.Status.OK, localByteArrayOutputStream.toByteArray());
      continue;
      paramInputStream = new PluginResult(PluginResult.Status.OK, localByteArrayOutputStream.toByteArray(), true);
    }
  }
}

/* Location:
 * Qualified Name:     org.apache.cordova.file.FileUtils.26
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */