package org.apache.cordova.mediacapture;

import android.content.Intent;
import android.net.Uri;
import java.io.File;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;
import org.json.JSONArray;

class Capture$3
  implements Runnable
{
  Capture$3(Capture paramCapture1, Intent paramIntent, Capture paramCapture2) {}
  
  public void run()
  {
    Uri localUri1 = null;
    if (val$intent != null) {
      localUri1 = val$intent.getData();
    }
    Uri localUri2 = localUri1;
    if (localUri1 == null) {
      localUri2 = Uri.fromFile(new File(Capture.access$600(this$0), "Capture.avi"));
    }
    if (localUri2 == null)
    {
      val$that.fail(Capture.access$500(this$0, 3, "Error: data is null"));
      return;
    }
    Capture.access$100(this$0).put(Capture.access$000(this$0, localUri2));
    if (Capture.access$100(this$0).length() >= Capture.access$200(this$0))
    {
      Capture.access$300(val$that).sendPluginResult(new PluginResult(PluginResult.Status.OK, Capture.access$100(this$0)));
      return;
    }
    Capture.access$1000(this$0, Capture.access$900(this$0));
  }
}

/* Location:
 * Qualified Name:     org.apache.cordova.mediacapture.Capture.3
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */