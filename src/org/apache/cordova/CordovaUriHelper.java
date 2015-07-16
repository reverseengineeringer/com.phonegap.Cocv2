package org.apache.cordova;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Build.VERSION;
import android.webkit.WebView;

class CordovaUriHelper
{
  private static final String TAG = "CordovaUriHelper";
  private CordovaWebView appView;
  private CordovaInterface cordova;
  
  CordovaUriHelper(CordovaInterface paramCordovaInterface, CordovaWebView paramCordovaWebView)
  {
    appView = paramCordovaWebView;
    cordova = paramCordovaInterface;
  }
  
  @TargetApi(15)
  boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
  {
    if (appView.pluginManager.onOverrideUrlLoading(paramString)) {}
    do
    {
      return true;
      if ((paramString.startsWith("file://") | paramString.startsWith("data:"))) {
        return paramString.contains("app_webview");
      }
      if (appView.getWhitelist().isUrlWhiteListed(paramString)) {
        return false;
      }
    } while (!appView.getExternalWhitelist().isUrlWhiteListed(paramString));
    try
    {
      paramWebView = new Intent("android.intent.action.VIEW");
      paramWebView.setData(Uri.parse(paramString));
      paramWebView.addCategory("android.intent.category.BROWSABLE");
      paramWebView.setComponent(null);
      if (Build.VERSION.SDK_INT >= 15) {
        paramWebView.setSelector(null);
      }
      cordova.getActivity().startActivity(paramWebView);
      return true;
    }
    catch (ActivityNotFoundException paramWebView)
    {
      LOG.e("CordovaUriHelper", "Error loading url " + paramString, paramWebView);
    }
    return true;
  }
}

/* Location:
 * Qualified Name:     org.apache.cordova.CordovaUriHelper
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */