package org.apache.cordova;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.telephony.TelephonyManager;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class App
  extends CordovaPlugin
{
  protected static final String TAG = "CordovaApp";
  private BroadcastReceiver telephonyReceiver;
  
  private void initTelephonyReceiver()
  {
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("android.intent.action.PHONE_STATE");
    telephonyReceiver = new BroadcastReceiver()
    {
      public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
      {
        if ((paramAnonymousIntent != null) && (paramAnonymousIntent.getAction().equals("android.intent.action.PHONE_STATE")) && (paramAnonymousIntent.hasExtra("state")))
        {
          paramAnonymousContext = paramAnonymousIntent.getStringExtra("state");
          if (!paramAnonymousContext.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
            break label64;
          }
          LOG.i("CordovaApp", "Telephone RINGING");
          webView.postMessage("telephone", "ringing");
        }
        label64:
        do
        {
          return;
          if (paramAnonymousContext.equals(TelephonyManager.EXTRA_STATE_OFFHOOK))
          {
            LOG.i("CordovaApp", "Telephone OFFHOOK");
            webView.postMessage("telephone", "offhook");
            return;
          }
        } while (!paramAnonymousContext.equals(TelephonyManager.EXTRA_STATE_IDLE));
        LOG.i("CordovaApp", "Telephone IDLE");
        webView.postMessage("telephone", "idle");
      }
    };
    cordova.getActivity().registerReceiver(telephonyReceiver, localIntentFilter);
  }
  
  public void backHistory()
  {
    cordova.getActivity().runOnUiThread(new Runnable()
    {
      public void run()
      {
        webView.backHistory();
      }
    });
  }
  
  public void clearCache()
  {
    cordova.getActivity().runOnUiThread(new Runnable()
    {
      public void run()
      {
        webView.clearCache(true);
      }
    });
  }
  
  public void clearHistory()
  {
    webView.clearHistory();
  }
  
  public boolean execute(String paramString, JSONArray paramJSONArray, CallbackContext paramCallbackContext)
    throws JSONException
  {
    PluginResult.Status localStatus = PluginResult.Status.OK;
    for (;;)
    {
      try
      {
        if (paramString.equals("clearCache"))
        {
          clearCache();
          paramCallbackContext.sendPluginResult(new PluginResult(localStatus, ""));
          return true;
        }
        if (paramString.equals("show"))
        {
          cordova.getActivity().runOnUiThread(new Runnable()
          {
            public void run()
            {
              webView.postMessage("spinner", "stop");
            }
          });
          continue;
        }
        if (!paramString.equals("loadUrl")) {
          break label110;
        }
      }
      catch (JSONException paramString)
      {
        paramCallbackContext.sendPluginResult(new PluginResult(PluginResult.Status.JSON_EXCEPTION));
        return false;
      }
      loadUrl(paramJSONArray.getString(0), paramJSONArray.optJSONObject(1));
      continue;
      label110:
      if (!paramString.equals("cancelLoadUrl")) {
        if (paramString.equals("clearHistory")) {
          clearHistory();
        } else if (paramString.equals("backHistory")) {
          backHistory();
        } else if (paramString.equals("overrideButton")) {
          overrideButton(paramJSONArray.getString(0), paramJSONArray.getBoolean(1));
        } else if (paramString.equals("overrideBackbutton")) {
          overrideBackbutton(paramJSONArray.getBoolean(0));
        } else if (paramString.equals("exitApp")) {
          exitApp();
        }
      }
    }
  }
  
  public void exitApp()
  {
    webView.postMessage("exit", null);
  }
  
  public void initialize(CordovaInterface paramCordovaInterface, CordovaWebView paramCordovaWebView)
  {
    super.initialize(paramCordovaInterface, paramCordovaWebView);
    initTelephonyReceiver();
  }
  
  public boolean isBackbuttonOverridden()
  {
    return webView.isBackButtonBound();
  }
  
  public void loadUrl(String paramString, JSONObject paramJSONObject)
    throws JSONException
  {
    LOG.d("App", "App.loadUrl(" + paramString + "," + paramJSONObject + ")");
    int i = 0;
    int j = 0;
    boolean bool2 = false;
    boolean bool4 = false;
    boolean bool1 = false;
    boolean bool3 = false;
    HashMap localHashMap = new HashMap();
    JSONArray localJSONArray;
    int k;
    if (paramJSONObject != null)
    {
      localJSONArray = paramJSONObject.names();
      k = 0;
      i = j;
      bool2 = bool4;
      j = k;
      bool1 = bool3;
      if (j < localJSONArray.length()) {}
    }
    else if (i <= 0) {}
    for (;;)
    {
      try
      {
        l = i;
      }
      catch (InterruptedException paramJSONObject)
      {
        long l;
        String str;
        paramJSONObject.printStackTrace();
        continue;
      }
      try
      {
        wait(l);
        webView.showWebPage(paramString, bool2, bool1, localHashMap);
        return;
      }
      finally {}
    }
    str = localJSONArray.getString(j);
    if (str.equals("wait"))
    {
      k = paramJSONObject.getInt(str);
      bool4 = bool2;
      bool3 = bool1;
    }
    for (;;)
    {
      j += 1;
      bool1 = bool3;
      bool2 = bool4;
      i = k;
      break;
      if (str.equalsIgnoreCase("openexternal"))
      {
        bool4 = paramJSONObject.getBoolean(str);
        bool3 = bool1;
        k = i;
      }
      else if (str.equalsIgnoreCase("clearhistory"))
      {
        bool3 = paramJSONObject.getBoolean(str);
        bool4 = bool2;
        k = i;
      }
      else
      {
        Object localObject = paramJSONObject.get(str);
        bool3 = bool1;
        bool4 = bool2;
        k = i;
        if (localObject != null) {
          if (localObject.getClass().equals(String.class))
          {
            localHashMap.put(str, (String)localObject);
            bool3 = bool1;
            bool4 = bool2;
            k = i;
          }
          else if (localObject.getClass().equals(Boolean.class))
          {
            localHashMap.put(str, (Boolean)localObject);
            bool3 = bool1;
            bool4 = bool2;
            k = i;
          }
          else
          {
            bool3 = bool1;
            bool4 = bool2;
            k = i;
            if (localObject.getClass().equals(Integer.class))
            {
              localHashMap.put(str, (Integer)localObject);
              bool3 = bool1;
              bool4 = bool2;
              k = i;
            }
          }
        }
      }
    }
  }
  
  public void onDestroy()
  {
    cordova.getActivity().unregisterReceiver(telephonyReceiver);
  }
  
  public void overrideBackbutton(boolean paramBoolean)
  {
    LOG.i("App", "WARNING: Back Button Default Behavior will be overridden.  The backbutton event will be fired!");
    webView.bindButton(paramBoolean);
  }
  
  public void overrideButton(String paramString, boolean paramBoolean)
  {
    LOG.i("App", "WARNING: Volume Button Default Behavior will be overridden.  The volume event will be fired!");
    webView.bindButton(paramString, paramBoolean);
  }
}

/* Location:
 * Qualified Name:     org.apache.cordova.App
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */