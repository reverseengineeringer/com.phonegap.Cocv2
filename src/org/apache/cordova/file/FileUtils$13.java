package org.apache.cordova.file;

import java.io.IOException;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;
import org.json.JSONException;
import org.json.JSONObject;

class FileUtils$13
  implements FileUtils.FileOp
{
  FileUtils$13(FileUtils paramFileUtils, long paramLong, CallbackContext paramCallbackContext, int paramInt) {}
  
  public void run()
    throws IOException, JSONException
  {
    if ((val$size != 0L) && (val$size > DirectoryManager.getFreeDiskSpace(true) * 1024L))
    {
      val$callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, FileUtils.QUOTA_EXCEEDED_ERR));
      return;
    }
    JSONObject localJSONObject = FileUtils.access$300(this$0, val$fstype);
    val$callbackContext.success(localJSONObject);
  }
}

/* Location:
 * Qualified Name:     org.apache.cordova.file.FileUtils.13
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */