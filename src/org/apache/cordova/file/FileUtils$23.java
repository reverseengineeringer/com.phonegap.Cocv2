package org.apache.cordova.file;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import org.json.JSONException;

class FileUtils$23
  implements FileUtils.FileOp
{
  FileUtils$23(FileUtils paramFileUtils, String paramString, CallbackContext paramCallbackContext) {}
  
  public void run()
    throws FileNotFoundException, JSONException, MalformedURLException
  {
    JSONArray localJSONArray = FileUtils.access$1100(this$0, val$fname);
    val$callbackContext.success(localJSONArray);
  }
}

/* Location:
 * Qualified Name:     org.apache.cordova.file.FileUtils.23
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */