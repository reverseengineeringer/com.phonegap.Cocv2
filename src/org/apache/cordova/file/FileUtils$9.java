package org.apache.cordova.file;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;

class FileUtils$9
  implements FileUtils.FileOp
{
  FileUtils$9(FileUtils paramFileUtils, String paramString1, String paramString2, int paramInt, Boolean paramBoolean, CallbackContext paramCallbackContext) {}
  
  public void run()
    throws FileNotFoundException, IOException, NoModificationAllowedException
  {
    long l = this$0.write(val$fname, val$data, val$offset, val$isBinary.booleanValue());
    val$callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, (float)l));
  }
}

/* Location:
 * Qualified Name:     org.apache.cordova.file.FileUtils.9
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */