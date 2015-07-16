package org.apache.cordova.dialogs;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import org.apache.cordova.CordovaInterface;

class Notification$6
  implements Runnable
{
  Notification$6(Notification paramNotification1, Notification paramNotification2, CordovaInterface paramCordovaInterface, String paramString1, String paramString2) {}
  
  public void run()
  {
    val$notification.progressDialog = Notification.access$200(this$0, val$cordova);
    val$notification.progressDialog.setProgressStyle(1);
    val$notification.progressDialog.setTitle(val$title);
    val$notification.progressDialog.setMessage(val$message);
    val$notification.progressDialog.setCancelable(true);
    val$notification.progressDialog.setMax(100);
    val$notification.progressDialog.setProgress(0);
    val$notification.progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener()
    {
      public void onCancel(DialogInterface paramAnonymousDialogInterface)
      {
        val$notification.progressDialog = null;
      }
    });
    val$notification.progressDialog.show();
  }
}

/* Location:
 * Qualified Name:     org.apache.cordova.dialogs.Notification.6
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */