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
  public void cancelVibration()
  {
    ((Vibrator)cordova.getActivity().getSystemService("vibrator")).cancel();
  }
  
  public boolean execute(String paramString, JSONArray paramJSONArray, CallbackContext paramCallbackContext)
    throws JSONException
  {
    boolean bool = false;
    if (paramString.equals("vibrate")) {
      vibrate(paramJSONArray.getLong(0));
    }
    for (;;)
    {
      paramCallbackContext.success();
      bool = true;
      do
      {
        return bool;
        if (paramString.equals("vibrateWithPattern"))
        {
          paramString = paramJSONArray.getJSONArray(0);
          int j = paramJSONArray.getInt(1);
          paramJSONArray = new long[paramString.length() + 1];
          paramJSONArray[0] = 0L;
          int i = 0;
          while (i < paramString.length())
          {
            paramJSONArray[(i + 1)] = paramString.getLong(i);
            i += 1;
          }
          vibrateWithPattern(paramJSONArray, j);
          break;
        }
      } while (!paramString.equals("cancelVibration"));
      cancelVibration();
    }
  }
  
  public void vibrate(long paramLong)
  {
    long l = paramLong;
    if (paramLong == 0L) {
      l = 500L;
    }
    ((Vibrator)cordova.getActivity().getSystemService("vibrator")).vibrate(l);
  }
  
  public void vibrateWithPattern(long[] paramArrayOfLong, int paramInt)
  {
    ((Vibrator)cordova.getActivity().getSystemService("vibrator")).vibrate(paramArrayOfLong, paramInt);
  }
}

/* Location:
 * Qualified Name:     org.apache.cordova.vibration.Vibration
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */