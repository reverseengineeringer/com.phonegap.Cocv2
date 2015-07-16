package org.apache.cordova.file;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;

class FileUtils$10
  implements FileUtils.FileOp
{
  FileUtils$10(FileUtils paramFileUtils, String paramString, int paramInt, CallbackContext paramCallbackContext) {}
  
  public void run()
    throws FileNotFoundException, IOException, NoModificationAllowedException
  {
    long l = FileUtils.access$000(this$0, val$fname, val$offset);
    val$callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, (float)l));
  }
}

/* Location:
 * Qualified Name:     org.apache.cordova.file.FileUtils.10
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */