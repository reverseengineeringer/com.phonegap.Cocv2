package org.apache.cordova;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class CordovaActivity$4$1
  implements DialogInterface.OnClickListener
{
  CordovaActivity$4$1(CordovaActivity.4 param4, boolean paramBoolean, CordovaActivity paramCordovaActivity) {}
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    paramDialogInterface.dismiss();
    if (val$exit) {
      val$me.endActivity();
    }
  }
}

/* Location:
 * Qualified Name:     org.apache.cordova.CordovaActivity.4.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */