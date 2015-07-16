package org.apache.cordova.file;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import org.apache.cordova.CallbackContext;
import org.json.JSONException;

class FileUtils$24
  implements FileUtils.FileOp
{
  FileUtils$24(FileUtils paramFileUtils, String paramString, CallbackContext paramCallbackContext) {}
  
  public void run()
    throws FileNotFoundException, JSONException, MalformedURLException
  {
    String str = this$0.filesystemPathForURL(val$localURLstr);
    val$callbackContext.success(str);
  }
}

/* Location:
 * Qualified Name:     org.apache.cordova.file.FileUtils.24
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */