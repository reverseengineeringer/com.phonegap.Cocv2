package org.apache.cordova.dialogs;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;

class Notification$2$2
  implements DialogInterface.OnClickListener
{
  Notification$2$2(Notification.2 param2, CallbackContext paramCallbackContext) {}
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    paramDialogInterface.dismiss();
    val$callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, 2));
  }
}

/* Location:
 * Qualified Name:     org.apache.cordova.dialogs.Notification.2.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */