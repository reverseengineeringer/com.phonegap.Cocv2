package org.apache.cordova;

import android.app.Activity;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONException;

class PluginManager$PluginManagerService
  extends CordovaPlugin
{
  private PluginManager$PluginManagerService(PluginManager paramPluginManager) {}
  
  public boolean execute(String paramString, CordovaArgs paramCordovaArgs, CallbackContext paramCallbackContext)
    throws JSONException
  {
    if ("startup".equals(paramString))
    {
      PluginManager.access$0(this$0).getAndIncrement();
      PluginManager.access$1(this$0).getActivity().runOnUiThread(new Runnable()
      {
        public void run()
        {
          PluginManager.access$0(this$0).getAndDecrement();
        }
      });
      return true;
    }
    return false;
  }
}

/* Location:
 * Qualified Name:     org.apache.cordova.PluginManager.PluginManagerService
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */