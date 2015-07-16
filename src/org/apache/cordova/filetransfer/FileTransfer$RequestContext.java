package org.apache.cordova.filetransfer;

import java.io.File;
import java.net.HttpURLConnection;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.PluginResult;

final class FileTransfer$RequestContext
{
  boolean aborted;
  CallbackContext callbackContext;
  HttpURLConnection connection;
  String source;
  String target;
  File targetFile;
  
  FileTransfer$RequestContext(String paramString1, String paramString2, CallbackContext paramCallbackContext)
  {
    source = paramString1;
    target = paramString2;
    callbackContext = paramCallbackContext;
  }
  
  void sendPluginResult(PluginResult paramPluginResult)
  {
    try
    {
      if (!aborted) {
        callbackContext.sendPluginResult(paramPluginResult);
      }
      return;
    }
    finally {}
  }
}

/* Location:
 * Qualified Name:     org.apache.cordova.filetransfer.FileTransfer.RequestContext
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */