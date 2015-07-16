package org.apache.cordova.file;

import java.io.IOException;
import org.apache.cordova.CallbackContext;
import org.json.JSONException;
import org.json.JSONObject;

class FileUtils$21
  implements FileUtils.FileOp
{
  FileUtils$21(FileUtils paramFileUtils, String paramString1, String paramString2, String paramString3, CallbackContext paramCallbackContext) {}
  
  public void run()
    throws JSONException, NoModificationAllowedException, IOException, InvalidModificationException, EncodingException, FileExistsException
  {
    JSONObject localJSONObject = FileUtils.access$1000(this$0, val$fname, val$newParent, val$newName, true);
    val$callbackContext.success(localJSONObject);
  }
}

/* Location:
 * Qualified Name:     org.apache.cordova.file.FileUtils.21
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */