package org.apache.cordova.file;

import java.io.IOException;
import org.apache.cordova.CallbackContext;
import org.json.JSONException;

class FileUtils$11
  implements FileUtils.FileOp
{
  FileUtils$11(FileUtils paramFileUtils, CallbackContext paramCallbackContext) {}
  
  public void run()
    throws IOException, JSONException
  {
    val$callbackContext.success(FileUtils.access$100(this$0));
  }
}

/* Location:
 * Qualified Name:     org.apache.cordova.file.FileUtils.11
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */