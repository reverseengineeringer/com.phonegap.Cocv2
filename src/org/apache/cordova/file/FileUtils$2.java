package org.apache.cordova.file;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;

class FileUtils$2
  implements FileUtils.FileOp
{
  FileUtils$2(FileUtils paramFileUtils, CallbackContext paramCallbackContext) {}
  
  public void run()
  {
    long l = DirectoryManager.getFreeDiskSpace(false);
    val$callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, (float)l));
  }
}

/* Location:
 * Qualified Name:     org.apache.cordova.file.FileUtils.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */