package org.apache.cordova.dialogs;

import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;

class Notification$2$4
  implements DialogInterface.OnCancelListener
{
  Notification$2$4(Notification.2 param2, CallbackContext paramCallbackContext) {}
  
  public void onCancel(DialogInterface paramDialogInterface)
  {
    paramDialogInterface.dismiss();
    val$callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, 0));
  }
}

/* Location:
 * Qualified Name:     org.apache.cordova.dialogs.Notification.2.4
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */