package org.apache.cordova;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;

class App$5
  extends BroadcastReceiver
{
  App$5(App paramApp) {}
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    if ((paramIntent != null) && (paramIntent.getAction().equals("android.intent.action.PHONE_STATE")) && (paramIntent.hasExtra("state")))
    {
      paramContext = paramIntent.getStringExtra("state");
      if (!paramContext.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
        break label64;
      }
      LOG.i("CordovaApp", "Telephone RINGING");
      this$0.webView.postMessage("telephone", "ringing");
    }
    label64:
    do
    {
      return;
      if (paramContext.equals(TelephonyManager.EXTRA_STATE_OFFHOOK))
      {
        LOG.i("CordovaApp", "Telephone OFFHOOK");
        this$0.webView.postMessage("telephone", "offhook");
        return;
      }
    } while (!paramContext.equals(TelephonyManager.EXTRA_STATE_IDLE));
    LOG.i("CordovaApp", "Telephone IDLE");
    this$0.webView.postMessage("telephone", "idle");
  }
}

/* Location:
 * Qualified Name:     org.apache.cordova.App.5
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */