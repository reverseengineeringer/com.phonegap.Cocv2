package org.apache.cordova.mediacapture;

import android.content.Intent;
import android.net.Uri;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;
import org.json.JSONArray;

class Capture$1
  implements Runnable
{
  Capture$1(Capture paramCapture1, Intent paramIntent, Capture paramCapture2) {}
  
  public void run()
  {
    Uri localUri = val$intent.getData();
    Capture.access$100(this$0).put(Capture.access$000(this$0, localUri));
    if (Capture.access$100(this$0).length() >= Capture.access$200(this$0))
    {
      Capture.access$300(val$that).sendPluginResult(new PluginResult(PluginResult.Status.OK, Capture.access$100(this$0)));
      return;
    }
    Capture.access$400(this$0);
  }
}

/* Location:
 * Qualified Name:     org.apache.cordova.mediacapture.Capture.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */