package org.apache.cordova.file;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;

class FileUtils$4
  implements FileUtils.FileOp
{
  FileUtils$4(FileUtils paramFileUtils, String paramString, CallbackContext paramCallbackContext) {}
  
  public void run()
  {
    boolean bool = DirectoryManager.testFileExists(val$fname);
    val$callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, bool));
  }
}

/* Location:
 * Qualified Name:     org.apache.cordova.file.FileUtils.4
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */