package org.apache.cordova.file;

import org.apache.cordova.CallbackContext;
import org.json.JSONException;

class FileUtils$12
  implements Runnable
{
  FileUtils$12(FileUtils paramFileUtils, CallbackContext paramCallbackContext) {}
  
  public void run()
  {
    try
    {
      val$callbackContext.success(FileUtils.access$200(this$0));
      return;
    }
    catch (JSONException localJSONException)
    {
      localJSONException.printStackTrace();
    }
  }
}

/* Location:
 * Qualified Name:     org.apache.cordova.file.FileUtils.12
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */