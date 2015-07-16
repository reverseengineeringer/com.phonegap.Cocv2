package org.apache.cordova.file;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import org.apache.cordova.CallbackContext;

class FileUtils$25
  implements Runnable
{
  FileUtils$25(FileUtils paramFileUtils, FileUtils.FileOp paramFileOp, CallbackContext paramCallbackContext) {}
  
  public void run()
  {
    try
    {
      val$f.run();
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      if ((localException instanceof EncodingException))
      {
        val$callbackContext.error(FileUtils.ENCODING_ERR);
        return;
      }
      if ((localException instanceof FileNotFoundException))
      {
        val$callbackContext.error(FileUtils.NOT_FOUND_ERR);
        return;
      }
      if ((localException instanceof FileExistsException))
      {
        val$callbackContext.error(FileUtils.PATH_EXISTS_ERR);
        return;
      }
      if ((localException instanceof NoModificationAllowedException))
      {
        val$callbackContext.error(FileUtils.NO_MODIFICATION_ALLOWED_ERR);
        return;
      }
      if ((localException instanceof InvalidModificationException))
      {
        val$callbackContext.error(FileUtils.INVALID_MODIFICATION_ERR);
        return;
      }
      if ((localException instanceof MalformedURLException))
      {
        val$callbackContext.error(FileUtils.ENCODING_ERR);
        return;
      }
      if ((localException instanceof IOException))
      {
        val$callbackContext.error(FileUtils.INVALID_MODIFICATION_ERR);
        return;
      }
      if ((localException instanceof EncodingException))
      {
        val$callbackContext.error(FileUtils.ENCODING_ERR);
        return;
      }
      if ((localException instanceof TypeMismatchException))
      {
        val$callbackContext.error(FileUtils.TYPE_MISMATCH_ERR);
        return;
      }
      val$callbackContext.error(FileUtils.UNKNOWN_ERR);
    }
  }
}

/* Location:
 * Qualified Name:     org.apache.cordova.file.FileUtils.25
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */