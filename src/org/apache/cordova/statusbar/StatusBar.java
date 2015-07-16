package org.apache.cordova.statusbar;

import android.app.Activity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaArgs;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;
import org.json.JSONException;

public class StatusBar
  extends CordovaPlugin
{
  private static final String TAG = "StatusBar";
  
  public boolean execute(String paramString, final CordovaArgs paramCordovaArgs, CallbackContext paramCallbackContext)
    throws JSONException
  {
    Log.v("StatusBar", "Executing action: " + paramString);
    paramCordovaArgs = cordova.getActivity().getWindow();
    if ("_ready".equals(paramString)) {
      if ((getAttributesflags & 0x400) != 0) {
        break label112;
      }
    }
    label112:
    for (boolean bool = true;; bool = false)
    {
      paramCallbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, bool));
      if (!"show".equals(paramString)) {
        break;
      }
      cordova.getActivity().runOnUiThread(new Runnable()
      {
        public void run()
        {
          paramCordovaArgs.clearFlags(1024);
        }
      });
      return true;
    }
    if ("hide".equals(paramString))
    {
      cordova.getActivity().runOnUiThread(new Runnable()
      {
        public void run()
        {
          paramCordovaArgs.addFlags(1024);
        }
      });
      return true;
    }
    return false;
  }
  
  public void initialize(final CordovaInterface paramCordovaInterface, CordovaWebView paramCordovaWebView)
  {
    Log.v("StatusBar", "StatusBar: initialization");
    super.initialize(paramCordovaInterface, paramCordovaWebView);
    cordova.getActivity().runOnUiThread(new Runnable()
    {
      public void run()
      {
        paramCordovaInterface.getActivity().getWindow().clearFlags(2048);
      }
    });
  }
}

/* Location:
 * Qualified Name:     org.apache.cordova.statusbar.StatusBar
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */