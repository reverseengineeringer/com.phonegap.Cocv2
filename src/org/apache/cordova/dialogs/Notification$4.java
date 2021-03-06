package org.apache.cordova.dialogs;

import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.widget.EditText;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class Notification$4
  implements Runnable
{
  Notification$4(Notification paramNotification, CordovaInterface paramCordovaInterface, String paramString1, String paramString2, String paramString3, JSONArray paramJSONArray, CallbackContext paramCallbackContext) {}
  
  public void run()
  {
    final EditText localEditText = new EditText(val$cordova.getActivity());
    localEditText.setHint(val$defaultText);
    AlertDialog.Builder localBuilder = Notification.access$000(this$0, val$cordova);
    localBuilder.setMessage(val$message);
    localBuilder.setTitle(val$title);
    localBuilder.setCancelable(true);
    localBuilder.setView(localEditText);
    final JSONObject localJSONObject = new JSONObject();
    if (val$buttonLabels.length() > 0) {}
    try
    {
      localBuilder.setNegativeButton(val$buttonLabels.getString(0), new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          paramAnonymousDialogInterface.dismiss();
          for (;;)
          {
            try
            {
              localJSONObject.put("buttonIndex", 1);
              JSONObject localJSONObject = localJSONObject;
              if (localEditText.getText().toString().trim().length() != 0) {
                continue;
              }
              paramAnonymousDialogInterface = val$defaultText;
              localJSONObject.put("input1", paramAnonymousDialogInterface);
            }
            catch (JSONException paramAnonymousDialogInterface)
            {
              paramAnonymousDialogInterface.printStackTrace();
              continue;
            }
            val$callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, localJSONObject));
            return;
            paramAnonymousDialogInterface = localEditText.getText();
          }
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
            for (;;)
            {
              try
              {
                localJSONObject.put("buttonIndex", 2);
                JSONObject localJSONObject = localJSONObject;
                if (localEditText.getText().toString().trim().length() != 0) {
                  continue;
                }
                paramAnonymousDialogInterface = val$defaultText;
                localJSONObject.put("input1", paramAnonymousDialogInterface);
              }
              catch (JSONException paramAnonymousDialogInterface)
              {
                paramAnonymousDialogInterface.printStackTrace();
                continue;
              }
              val$callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, localJSONObject));
              return;
              paramAnonymousDialogInterface = localEditText.getText();
            }
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
              for (;;)
              {
                try
                {
                  localJSONObject.put("buttonIndex", 3);
                  JSONObject localJSONObject = localJSONObject;
                  if (localEditText.getText().toString().trim().length() != 0) {
                    continue;
                  }
                  paramAnonymousDialogInterface = val$defaultText;
                  localJSONObject.put("input1", paramAnonymousDialogInterface);
                }
                catch (JSONException paramAnonymousDialogInterface)
                {
                  paramAnonymousDialogInterface.printStackTrace();
                  continue;
                }
                val$callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, localJSONObject));
                return;
                paramAnonymousDialogInterface = localEditText.getText();
              }
            }
          });
          localBuilder.setOnCancelListener(new DialogInterface.OnCancelListener()
          {
            public void onCancel(DialogInterface paramAnonymousDialogInterface)
            {
              paramAnonymousDialogInterface.dismiss();
              for (;;)
              {
                try
                {
                  localJSONObject.put("buttonIndex", 0);
                  JSONObject localJSONObject = localJSONObject;
                  if (localEditText.getText().toString().trim().length() != 0) {
                    continue;
                  }
                  paramAnonymousDialogInterface = val$defaultText;
                  localJSONObject.put("input1", paramAnonymousDialogInterface);
                }
                catch (JSONException paramAnonymousDialogInterface)
                {
                  paramAnonymousDialogInterface.printStackTrace();
                  continue;
                }
                val$callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, localJSONObject));
                return;
                paramAnonymousDialogInterface = localEditText.getText();
              }
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
 * Qualified Name:     org.apache.cordova.dialogs.Notification.4
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */