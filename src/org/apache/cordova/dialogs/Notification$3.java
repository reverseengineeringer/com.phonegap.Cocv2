package org.apache.cordova.dialogs;

import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;
import org.json.JSONArray;
import org.json.JSONException;

class Notification$3
  implements Runnable
{
  Notification$3(Notification paramNotification, CordovaInterface paramCordovaInterface, String paramString1, String paramString2, JSONArray paramJSONArray, CallbackContext paramCallbackContext) {}
  
  public void run()
  {
    AlertDialog.Builder localBuilder = Notification.access$000(this$0, val$cordova);
    localBuilder.setMessage(val$message);
    localBuilder.setTitle(val$title);
    localBuilder.setCancelable(true);
    if (val$buttonLabels.length() > 0) {}
    try
    {
      localBuilder.setNegativeButton(val$buttonLabels.getString(0), new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          paramAnonymousDialogInterface.dismiss();
          val$callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, 1));
        }
      });
      if (val$buttonLabels.length() > 1) {}
      try
      {
        localBuilder.setNeutralButton(val$buttonLabels.getString(1), new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
          {
            paramAnonymousDialogInterface.dismiss();
            val$callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, 2));
          }
        });
        if (val$buttonLabels.length() > 2) {}
        try
        {
          localBuilder.setPositiveButton(val$buttonLabels.getString(2), new DialogInterface.OnClickListener()
          {
            public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
            {
              paramAnonymousDialogInterface.dismiss();
              val$callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, 3));
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
          Notification.access$100(this$0, localBuilder);
          return;
        }
        catch (JSONException localJSONException1)
        {
          for (;;) {}
        }
      }
      catch (JSONException localJSONException2)
      {
        for (;;) {}
      }
    }
    catch (JSONException localJSONException3)
    {
      for (;;) {}
    }
  }
}

/* Location:
 * Qualified Name:     org.apache.cordova.dialogs.Notification.3
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */