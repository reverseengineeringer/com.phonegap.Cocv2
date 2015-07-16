package org.apache.cordova.statusbar;

import android.app.Activity;
import android.view.Window;
import org.apache.cordova.CordovaInterface;

class StatusBar$1
  implements Runnable
{
  StatusBar$1(StatusBar paramStatusBar, CordovaInterface paramCordovaInterface) {}
  
  public void run()
  {
    val$cordova.getActivity().getWindow().clearFlags(2048);
  }
}

/* Location:
 * Qualified Name:     org.apache.cordova.statusbar.StatusBar.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */