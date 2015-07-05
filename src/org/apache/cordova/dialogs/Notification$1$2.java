package org.apache.cordova.dialogs;

import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;

class Notification$1$2
  implements DialogInterface.OnCancelListener
{
  Notification$1$2(Notification.1 param1, CallbackContext paramCallbackContext) {}
  
  public void onCancel(DialogInterface paramDialogInterface)
  {
    paramDialogInterface.dismiss();
    val$callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, 0));
  }
}

/* Location:
 * Qualified Name:     org.apache.cordova.dialogs.Notification.1.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */