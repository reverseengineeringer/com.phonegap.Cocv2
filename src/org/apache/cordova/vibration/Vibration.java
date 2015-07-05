package org.apache.cordova.vibration;

import android.app.Activity;
import android.os.Vibrator;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

public class Vibration
  extends CordovaPlugin
{
  public boolean execute(String paramString, JSONArray paramJSONArray, CallbackContext paramCallbackContext)
    throws JSONException
  {
    boolean bool = false;
    if (paramString.equals("vibrate"))
    {
      vibrate(paramJSONArray.getLong(0));
      paramCallbackContext.success();
      bool = true;
    }
    return bool;
  }
  
  public void vibrate(long paramLong)
  {
    long l = paramLong;
    if (paramLong == 0L) {
      l = 500L;
    }
    ((Vibrator)cordova.getActivity().getSystemService("vibrator")).vibrate(l);
  }
}

/* Location:
 * Qualified Name:     org.apache.cordova.vibration.Vibration
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */