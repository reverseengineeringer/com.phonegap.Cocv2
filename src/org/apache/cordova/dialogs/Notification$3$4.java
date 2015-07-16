package org.apache.cordova.dialogs;

import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;

class Notification$3$4
  implements DialogInterface.OnCancelListener
{
  Notification$3$4(Notification.3 param3) {}
  
  public void onCancel(DialogInterface paramDialogInterface)
  {
    paramDialogInterface.dismiss();
    this$1.val$callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, 0));
  }
}

/* Location:
 * Qualified Name:     org.apache.cordova.dialogs.Notification.3.4
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */