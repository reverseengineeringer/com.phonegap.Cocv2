package org.apache.cordova.file;

import java.io.IOException;
import org.apache.cordova.CallbackContext;
import org.json.JSONException;
import org.json.JSONObject;

class FileUtils$16
  implements FileUtils.FileOp
{
  FileUtils$16(FileUtils paramFileUtils, String paramString, CallbackContext paramCallbackContext) {}
  
  public void run()
    throws JSONException, IOException
  {
    JSONObject localJSONObject = FileUtils.access$600(this$0, val$fname);
    val$callbackContext.success(localJSONObject);
  }
}

/* Location:
 * Qualified Name:     org.apache.cordova.file.FileUtils.16
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */