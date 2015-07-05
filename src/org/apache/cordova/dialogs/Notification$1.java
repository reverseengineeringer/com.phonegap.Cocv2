package org.apache.cordova.dialogs;

import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;

class Notification$1
  implements Runnable
{
  Notification$1(Notification paramNotification, CordovaInterface paramCordovaInterface, String paramString1, String paramString2, String paramString3, CallbackContext paramCallbackContext) {}
  
  public void run()
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(val$cordova.getActivity());
    localBuilder.setMessage(val$message);
    localBuilder.setTitle(val$title);
    localBuilder.setCancelable(true);
    localBuilder.setPositiveButton(val$buttonLabel, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.dismiss();
        val$callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, 0));
      }
    });
    localBuilder.setOnCancelListener(new DialogInterface.OnCancelListener()
    {
      public void onCancel(DialogInterface paramAnonymousDialogInterface)
      {
        paramAnonymousDialogInterface.dismiss();
        val$callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, 0));
      }
    });
    localBuilder.create();
    localBuilder.show();
  }
}

/* Location:
 * Qualified Name:     org.apache.cordova.dialogs.Notification.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */