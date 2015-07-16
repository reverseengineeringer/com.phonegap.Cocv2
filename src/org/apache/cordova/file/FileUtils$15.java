package org.apache.cordova.file;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import org.apache.cordova.CallbackContext;
import org.json.JSONException;
import org.json.JSONObject;

class FileUtils$15
  implements FileUtils.FileOp
{
  FileUtils$15(FileUtils paramFileUtils, String paramString, CallbackContext paramCallbackContext) {}
  
  public void run()
    throws FileNotFoundException, JSONException, MalformedURLException
  {
    JSONObject localJSONObject = FileUtils.access$500(this$0, val$fname);
    val$callbackContext.success(localJSONObject);
  }
}

/* Location:
 * Qualified Name:     org.apache.cordova.file.FileUtils.15
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */