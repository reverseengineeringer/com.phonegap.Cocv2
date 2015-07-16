package org.apache.cordova;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import java.util.List;

@Deprecated
public class Config
{
  private static final String TAG = "Config";
  static ConfigXmlParser parser;
  
  public static void addWhiteListEntry(String paramString, boolean paramBoolean)
  {
    if (parser == null)
    {
      Log.e("Config", "Config was not initialised. Did you forget to Config.init(this)?");
      return;
    }
    parser.getInternalWhitelist().addWhiteListEntry(paramString, paramBoolean);
  }
  
  public static String getErrorUrl()
  {
    return parser.getPreferences().getString("errorurl", null);
  }
  
  public static Whitelist getExternalWhitelist()
  {
    return parser.getExternalWhitelist();
  }
  
  public static List<PluginEntry> getPluginEntries()
  {
    return parser.getPluginEntries();
  }
  
  public static CordovaPreferences getPreferences()
  {
    return parser.getPreferences();
  }
  
  public static String getStartUrl()
  {
    if (parser == null) {
      return "file:///android_asset/www/index.html";
    }
    return parser.getLaunchUrl();
  }
  
  public static Whitelist getWhitelist()
  {
    return parser.getInternalWhitelist();
  }
  
  public static void init()
  {
    if (parser == null) {
      parser = new ConfigXmlParser();
    }
  }
  
  public static void init(Activity paramActivity)
  {
    parser = new ConfigXmlParser();
    parser.parse(paramActivity);
    parser.getPreferences().setPreferencesBundle(paramActivity.getIntent().getExtras());
    parser.getPreferences().copyIntoIntentExtras(paramActivity);
  }
  
  public static boolean isInitialized()
  {
    return parser != null;
  }
  
  public static boolean isUrlExternallyWhiteListed(String paramString)
  {
    if (parser == null)
    {
      Log.e("Config", "Config was not initialised. Did you forget to Config.init(this)?");
      return false;
    }
    return parser.getExternalWhitelist().isUrlWhiteListed(paramString);
  }
  
  public static boolean isUrlWhiteListed(String paramString)
  {
    if (parser == null)
    {
      Log.e("Config", "Config was not initialised. Did you forget to Config.init(this)?");
      return false;
    }
    return parser.getInternalWhitelist().isUrlWhiteListed(paramString);
  }
}

/* Location:
 * Qualified Name:     org.apache.cordova.Config
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */