package org.apache.cordova.file;

import java.net.MalformedURLException;
import org.apache.cordova.CallbackContext;

class FileUtils$20
  implements FileUtils.FileOp
{
  FileUtils$20(FileUtils paramFileUtils, String paramString, CallbackContext paramCallbackContext) {}
  
  public void run()
    throws FileExistsException, MalformedURLException, NoModificationAllowedException
  {
    if (FileUtils.access$900(this$0, val$fname))
    {
      val$callbackContext.success();
      return;
    }
    val$callbackContext.error(FileUtils.NO_MODIFICATION_ALLOWED_ERR);
  }
}

/* Location:
 * Qualified Name:     org.apache.cordova.file.FileUtils.20
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */