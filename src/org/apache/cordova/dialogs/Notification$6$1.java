package org.apache.cordova.dialogs;

import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;

class Notification$6$1
  implements DialogInterface.OnCancelListener
{
  Notification$6$1(Notification.6 param6) {}
  
  public void onCancel(DialogInterface paramDialogInterface)
  {
    this$1.val$notification.progressDialog = null;
  }
}

/* Location:
 * Qualified Name:     org.apache.cordova.dialogs.Notification.6.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */