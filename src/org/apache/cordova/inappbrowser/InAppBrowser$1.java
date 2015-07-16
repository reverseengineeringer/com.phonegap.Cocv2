package org.apache.cordova.inappbrowser;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.Config;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.LOG;
import org.apache.cordova.PluginManager;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;

class InAppBrowser$1
  implements Runnable
{
  InAppBrowser$1(InAppBrowser paramInAppBrowser, String paramString1, String paramString2, HashMap paramHashMap, CallbackContext paramCallbackContext) {}
  
  public void run()
  {
    String str2 = "";
    Object localObject5;
    Object localObject1;
    if ("_self".equals(val$target))
    {
      Log.d("InAppBrowser", "in self");
      localObject5 = null;
      if (val$url.startsWith("javascript:")) {
        localObject5 = Boolean.valueOf(true);
      }
      localObject1 = localObject5;
      if (localObject5 != null) {}
    }
    try
    {
      localObject1 = (Boolean)Config.class.getMethod("isUrlWhiteListed", new Class[] { String.class }).invoke(null, new Object[] { val$url });
      localObject5 = localObject1;
      if (localObject1 == null) {}
      try
      {
        localObject5 = (PluginManager)this$0.webView.getClass().getMethod("getPluginManager", new Class[0]).invoke(this$0.webView, new Object[0]);
        localObject5 = (Boolean)localObject5.getClass().getMethod("shouldAllowNavigation", new Class[] { String.class }).invoke(localObject5, new Object[] { val$url });
        if (Boolean.TRUE.equals(localObject5))
        {
          Log.d("InAppBrowser", "loading in webview");
          this$0.webView.loadUrl(val$url);
          localObject1 = str2;
        }
        for (;;)
        {
          localObject1 = new PluginResult(PluginResult.Status.OK, (String)localObject1);
          ((PluginResult)localObject1).setKeepCallback(true);
          val$callbackContext.sendPluginResult((PluginResult)localObject1);
          return;
          if (val$url.startsWith("tel:"))
          {
            try
            {
              Log.d("InAppBrowser", "loading in dialer");
              localObject1 = new Intent("android.intent.action.DIAL");
              ((Intent)localObject1).setData(Uri.parse(val$url));
              this$0.cordova.getActivity().startActivity((Intent)localObject1);
              localObject1 = str2;
            }
            catch (ActivityNotFoundException localActivityNotFoundException)
            {
              LOG.e("InAppBrowser", "Error dialing " + val$url + ": " + localActivityNotFoundException.toString());
              str1 = str2;
            }
          }
          else
          {
            Log.d("InAppBrowser", "loading in InAppBrowser");
            str1 = this$0.showWebPage(val$url, val$features);
            continue;
            if ("_system".equals(val$target))
            {
              Log.d("InAppBrowser", "in system");
              str1 = this$0.openExternal(val$url);
            }
            else
            {
              Log.d("InAppBrowser", "in blank");
              str1 = this$0.showWebPage(val$url, val$features);
            }
          }
        }
      }
      catch (InvocationTargetException localInvocationTargetException2)
      {
        for (;;)
        {
          Object localObject6 = str1;
        }
      }
      catch (IllegalAccessException localIllegalAccessException2)
      {
        for (;;)
        {
          Object localObject7 = str1;
        }
      }
      catch (NoSuchMethodException localNoSuchMethodException2)
      {
        for (;;)
        {
          String str1;
          localObject8 = str1;
        }
      }
    }
    catch (InvocationTargetException localInvocationTargetException1)
    {
      for (;;)
      {
        Object localObject2 = localObject8;
      }
    }
    catch (IllegalAccessException localIllegalAccessException1)
    {
      for (;;)
      {
        Object localObject3 = localObject8;
      }
    }
    catch (NoSuchMethodException localNoSuchMethodException1)
    {
      for (;;)
      {
        Object localObject8;
        Object localObject4 = localObject8;
      }
    }
  }
}

/* Location:
 * Qualified Name:     org.apache.cordova.inappbrowser.InAppBrowser.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */