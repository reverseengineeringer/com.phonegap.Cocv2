package org.apache.cordova.file;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;

class FileUtils$1
  implements FileUtils.FileOp
{
  FileUtils$1(FileUtils paramFileUtils, CallbackContext paramCallbackContext) {}
  
  public void run()
  {
    boolean bool = DirectoryManager.testSaveLocationExists();
    val$callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, bool));
  }
}

/* Location:
 * Qualified Name:     org.apache.cordova.file.FileUtils.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */