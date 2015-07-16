package org.apache.cordova;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.webkit.WebSettings;

class CordovaWebView$1
  extends BroadcastReceiver
{
  CordovaWebView$1(CordovaWebView paramCordovaWebView) {}
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    this$0.getSettings().getUserAgentString();
  }
}

/* Location:
 * Qualified Name:     org.apache.cordova.CordovaWebView.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */