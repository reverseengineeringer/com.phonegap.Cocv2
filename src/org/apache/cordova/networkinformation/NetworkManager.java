package org.apache.cordova.networkinformation;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class NetworkManager
  extends CordovaPlugin
{
  public static final String CDMA = "cdma";
  public static final String CELLULAR = "cellular";
  public static final String EDGE = "edge";
  public static final String EHRPD = "ehrpd";
  public static final String GPRS = "gprs";
  public static final String GSM = "gsm";
  public static final String HSDPA = "hsdpa";
  public static final String HSPA = "hspa";
  public static final String HSPA_PLUS = "hspa+";
  public static final String HSUPA = "hsupa";
  private static final String LOG_TAG = "NetworkManager";
  public static final String LTE = "lte";
  public static final String MOBILE = "mobile";
  public static int NOT_REACHABLE = 0;
  public static final String ONEXRTT = "1xrtt";
  public static int REACHABLE_VIA_CARRIER_DATA_NETWORK = 1;
  public static int REACHABLE_VIA_WIFI_NETWORK = 2;
  public static final String TYPE_2G = "2g";
  public static final String TYPE_3G = "3g";
  public static final String TYPE_4G = "4g";
  public static final String TYPE_ETHERNET = "ethernet";
  public static final String TYPE_NONE = "none";
  public static final String TYPE_UNKNOWN = "unknown";
  public static final String TYPE_WIFI = "wifi";
  public static final String UMB = "umb";
  public static final String UMTS = "umts";
  public static final String WIFI = "wifi";
  public static final String WIMAX = "wimax";
  private CallbackContext connectionCallbackContext;
  private JSONObject lastInfo = null;
  BroadcastReceiver receiver;
  ConnectivityManager sockMan;
  
  private JSONObject getConnectionInfo(NetworkInfo paramNetworkInfo)
  {
    Object localObject2 = "none";
    Object localObject1 = "";
    if (paramNetworkInfo != null) {
      if (paramNetworkInfo.isConnected()) {
        break label105;
      }
    }
    for (localObject1 = "none";; localObject1 = getType(paramNetworkInfo))
    {
      paramNetworkInfo = paramNetworkInfo.getExtraInfo();
      localObject2 = localObject1;
      localObject1 = paramNetworkInfo;
      Log.d("CordovaNetworkManager", "Connection Type: " + (String)localObject2);
      Log.d("CordovaNetworkManager", "Connection Extra Info: " + (String)localObject1);
      paramNetworkInfo = new JSONObject();
      label105:
      try
      {
        paramNetworkInfo.put("type", localObject2);
        paramNetworkInfo.put("extraInfo", localObject1);
        return paramNetworkInfo;
      }
      catch (JSONException localJSONException) {}
    }
    return paramNetworkInfo;
  }
  
  private String getType(NetworkInfo paramNetworkInfo)
  {
    if (paramNetworkInfo != null)
    {
      String str = paramNetworkInfo.getTypeName();
      if (str.toLowerCase().equals("wifi")) {
        return "wifi";
      }
      if ((str.toLowerCase().equals("mobile")) || (str.toLowerCase().equals("cellular")))
      {
        paramNetworkInfo = paramNetworkInfo.getSubtypeName();
        if ((paramNetworkInfo.toLowerCase().equals("gsm")) || (paramNetworkInfo.toLowerCase().equals("gprs")) || (paramNetworkInfo.toLowerCase().equals("edge"))) {
          return "2g";
        }
        if ((paramNetworkInfo.toLowerCase().startsWith("cdma")) || (paramNetworkInfo.toLowerCase().equals("umts")) || (paramNetworkInfo.toLowerCase().equals("1xrtt")) || (paramNetworkInfo.toLowerCase().equals("ehrpd")) || (paramNetworkInfo.toLowerCase().equals("hsupa")) || (paramNetworkInfo.toLowerCase().equals("hsdpa")) || (paramNetworkInfo.toLowerCase().equals("hspa"))) {
          return "3g";
        }
        if ((paramNetworkInfo.toLowerCase().equals("lte")) || (paramNetworkInfo.toLowerCase().equals("umb")) || (paramNetworkInfo.toLowerCase().equals("hspa+"))) {
          return "4g";
        }
      }
    }
    else
    {
      return "none";
    }
    return "unknown";
  }
  
  private void sendUpdate(String paramString)
  {
    if (connectionCallbackContext != null)
    {
      PluginResult localPluginResult = new PluginResult(PluginResult.Status.OK, paramString);
      localPluginResult.setKeepCallback(true);
      connectionCallbackContext.sendPluginResult(localPluginResult);
    }
    webView.postMessage("networkconnection", paramString);
  }
  
  private void updateConnectionInfo(NetworkInfo paramNetworkInfo)
  {
    JSONObject localJSONObject = getConnectionInfo(paramNetworkInfo);
    if (!localJSONObject.equals(lastInfo)) {
      paramNetworkInfo = "";
    }
    try
    {
      String str = localJSONObject.get("type").toString();
      paramNetworkInfo = str;
    }
    catch (JSONException localJSONException)
    {
      for (;;) {}
    }
    sendUpdate(paramNetworkInfo);
    lastInfo = localJSONObject;
  }
  
  public boolean execute(String paramString, JSONArray paramJSONArray, CallbackContext paramCallbackContext)
  {
    if (paramString.equals("getConnectionInfo"))
    {
      connectionCallbackContext = paramCallbackContext;
      paramJSONArray = sockMan.getActiveNetworkInfo();
      paramString = "";
    }
    try
    {
      paramJSONArray = getConnectionInfo(paramJSONArray).get("type").toString();
      paramString = paramJSONArray;
    }
    catch (JSONException paramJSONArray)
    {
      for (;;) {}
    }
    paramString = new PluginResult(PluginResult.Status.OK, paramString);
    paramString.setKeepCallback(true);
    paramCallbackContext.sendPluginResult(paramString);
    return true;
    return false;
  }
  
  public void initialize(CordovaInterface paramCordovaInterface, CordovaWebView paramCordovaWebView)
  {
    super.initialize(paramCordovaInterface, paramCordovaWebView);
    sockMan = ((ConnectivityManager)paramCordovaInterface.getActivity().getSystemService("connectivity"));
    connectionCallbackContext = null;
    paramCordovaInterface = new IntentFilter();
    paramCordovaInterface.addAction("android.net.conn.CONNECTIVITY_CHANGE");
    if (receiver == null)
    {
      receiver = new BroadcastReceiver()
      {
        public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
        {
          if (webView != null) {
            NetworkManager.this.updateConnectionInfo(sockMan.getActiveNetworkInfo());
          }
        }
      };
      paramCordovaWebView.getContext().registerReceiver(receiver, paramCordovaInterface);
    }
  }
  
  public void onDestroy()
  {
    if (receiver != null) {}
    try
    {
      webView.getContext().unregisterReceiver(receiver);
      return;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Log.e("NetworkManager", "Error unregistering network receiver: " + localException.getMessage(), localException);
      }
    }
    finally
    {
      receiver = null;
    }
  }
}

/* Location:
 * Qualified Name:     org.apache.cordova.networkinformation.NetworkManager
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */