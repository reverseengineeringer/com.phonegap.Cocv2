package org.apache.cordova.device;

import android.app.Activity;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import java.util.TimeZone;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Device
  extends CordovaPlugin
{
  private static final String AMAZON_DEVICE = "Amazon";
  private static final String AMAZON_PLATFORM = "amazon-fireos";
  private static final String ANDROID_PLATFORM = "Android";
  public static final String TAG = "Device";
  public static String platform;
  public static String uuid;
  
  public boolean execute(String paramString, JSONArray paramJSONArray, CallbackContext paramCallbackContext)
    throws JSONException
  {
    if (paramString.equals("getDeviceInfo"))
    {
      paramString = new JSONObject();
      paramString.put("uuid", uuid);
      paramString.put("version", getOSVersion());
      paramString.put("platform", getPlatform());
      paramString.put("model", getModel());
      paramCallbackContext.success(paramString);
      return true;
    }
    return false;
  }
  
  public String getModel()
  {
    return Build.MODEL;
  }
  
  public String getOSVersion()
  {
    return Build.VERSION.RELEASE;
  }
  
  public String getPlatform()
  {
    if (isAmazonDevice()) {
      return "amazon-fireos";
    }
    return "Android";
  }
  
  public String getProductName()
  {
    return Build.PRODUCT;
  }
  
  public String getSDKVersion()
  {
    return Build.VERSION.SDK;
  }
  
  public String getTimeZoneID()
  {
    return TimeZone.getDefault().getID();
  }
  
  public String getUuid()
  {
    return Settings.Secure.getString(cordova.getActivity().getContentResolver(), "android_id");
  }
  
  public void initialize(CordovaInterface paramCordovaInterface, CordovaWebView paramCordovaWebView)
  {
    super.initialize(paramCordovaInterface, paramCordovaWebView);
    uuid = getUuid();
  }
  
  public boolean isAmazonDevice()
  {
    return Build.MANUFACTURER.equals("Amazon");
  }
}

/* Location:
 * Qualified Name:     org.apache.cordova.device.Device
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */