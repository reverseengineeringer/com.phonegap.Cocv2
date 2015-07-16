package org.apache.cordova.filetransfer;

import java.io.File;
import java.net.HttpURLConnection;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;
import org.json.JSONObject;

class FileTransfer$5
  implements Runnable
{
  FileTransfer$5(FileTransfer paramFileTransfer, FileTransfer.RequestContext paramRequestContext) {}
  
  public void run()
  {
    synchronized (val$context)
    {
      Object localObject1 = val$context.targetFile;
      if (localObject1 != null) {
        ((File)localObject1).delete();
      }
      localObject1 = FileTransfer.access$700(FileTransfer.ABORTED_ERR, val$context.source, val$context.target, null, Integer.valueOf(-1), null);
      val$context.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, (JSONObject)localObject1));
      val$context.aborted = true;
      if (val$context.connection != null) {
        val$context.connection.disconnect();
      }
      return;
    }
  }
}

/* Location:
 * Qualified Name:     org.apache.cordova.filetransfer.FileTransfer.5
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */