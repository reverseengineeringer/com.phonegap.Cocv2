package org.apache.cordova.file;

import java.net.MalformedURLException;
import org.apache.cordova.CallbackContext;

class FileUtils$19
  implements FileUtils.FileOp
{
  FileUtils$19(FileUtils paramFileUtils, String paramString, CallbackContext paramCallbackContext) {}
  
  public void run()
    throws NoModificationAllowedException, InvalidModificationException, MalformedURLException
  {
    if (FileUtils.access$800(this$0, val$fname))
    {
      val$callbackContext.success();
      return;
    }
    val$callbackContext.error(FileUtils.NO_MODIFICATION_ALLOWED_ERR);
  }
}

/* Location:
 * Qualified Name:     org.apache.cordova.file.FileUtils.19
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */