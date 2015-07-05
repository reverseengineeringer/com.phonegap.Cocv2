package org.apache.cordova.dialogs;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.text.Editable;
import android.widget.EditText;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;
import org.json.JSONException;
import org.json.JSONObject;

class Notification$3$3
  implements DialogInterface.OnClickListener
{
  Notification$3$3(Notification.3 param3, JSONObject paramJSONObject, EditText paramEditText, String paramString, CallbackContext paramCallbackContext) {}
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    paramDialogInterface.dismiss();
    for (;;)
    {
      try
      {
        val$result.put("buttonIndex", 3);
        JSONObject localJSONObject = val$result;
        if (val$promptInput.getText().toString().trim().length() != 0) {
          continue;
        }
        paramDialogInterface = val$defaultText;
        localJSONObject.put("input1", paramDialogInterface);
      }
      catch (JSONException paramDialogInterface)
      {
        paramDialogInterface.printStackTrace();
        continue;
      }
      val$callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, val$result));
      return;
      paramDialogInterface = val$promptInput.getText();
    }
  }
}

/* Location:
 * Qualified Name:     org.apache.cordova.dialogs.Notification.3.3
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */