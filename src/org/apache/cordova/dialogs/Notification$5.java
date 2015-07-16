package org.apache.cordova.dialogs;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import org.apache.cordova.CordovaInterface;

class Notification$5
  implements Runnable
{
  Notification$5(Notification paramNotification1, Notification paramNotification2, CordovaInterface paramCordovaInterface, String paramString1, String paramString2) {}
  
  public void run()
  {
    val$notification.spinnerDialog = Notification.access$200(this$0, val$cordova);
    val$notification.spinnerDialog.setTitle(val$title);
    val$notification.spinnerDialog.setMessage(val$message);
    val$notification.spinnerDialog.setCancelable(true);
    val$notification.spinnerDialog.setIndeterminate(true);
    val$notification.spinnerDialog.setOnCancelListener(new DialogInterface.OnCancelListener()
    {
      public void onCancel(DialogInterface paramAnonymousDialogInterface)
      {
        val$notification.spinnerDialog = null;
      }
    });
    val$notification.spinnerDialog.show();
  }
}

/* Location:
 * Qualified Name:     org.apache.cordova.dialogs.Notification.5
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */