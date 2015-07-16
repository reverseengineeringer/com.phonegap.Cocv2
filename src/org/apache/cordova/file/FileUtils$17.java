package org.apache.cordova.file;

import java.io.IOException;
import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class FileUtils$17
  implements FileUtils.FileOp
{
  FileUtils$17(FileUtils paramFileUtils, String paramString1, String paramString2, JSONArray paramJSONArray, CallbackContext paramCallbackContext) {}
  
  public void run()
    throws FileExistsException, IOException, TypeMismatchException, EncodingException, JSONException
  {
    JSONObject localJSONObject = FileUtils.access$700(this$0, val$dirname, val$path, val$args.optJSONObject(2), true);
    val$callbackContext.success(localJSONObject);
  }
}

/* Location:
 * Qualified Name:     org.apache.cordova.file.FileUtils.17
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */