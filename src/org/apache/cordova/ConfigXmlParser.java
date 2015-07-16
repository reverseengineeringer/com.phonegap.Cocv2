package org.apache.cordova;

import android.app.Activity;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.util.Log;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.xmlpull.v1.XmlPullParserException;

public class ConfigXmlParser
{
  private static String TAG = "ConfigXmlParser";
  private Whitelist externalWhitelist = new Whitelist();
  private Whitelist internalWhitelist = new Whitelist();
  private String launchUrl = "file:///android_asset/www/index.html";
  private ArrayList<PluginEntry> pluginEntries = new ArrayList(20);
  private CordovaPreferences prefs = new CordovaPreferences();
  
  private void setStartUrl(String paramString)
  {
    if (Pattern.compile("^[a-z-]+://").matcher(paramString).find())
    {
      launchUrl = paramString;
      return;
    }
    String str = paramString;
    if (paramString.charAt(0) == '/') {
      str = paramString.substring(1);
    }
    launchUrl = ("file:///android_asset/www/" + str);
  }
  
  public Whitelist getExternalWhitelist()
  {
    return externalWhitelist;
  }
  
  public Whitelist getInternalWhitelist()
  {
    return internalWhitelist;
  }
  
  public String getLaunchUrl()
  {
    return launchUrl;
  }
  
  public ArrayList<PluginEntry> getPluginEntries()
  {
    return pluginEntries;
  }
  
  public CordovaPreferences getPreferences()
  {
    return prefs;
  }
  
  public void parse(Activity paramActivity)
  {
    int j = paramActivity.getResources().getIdentifier("config", "xml", paramActivity.getClass().getPackage().getName());
    int i = j;
    if (j == 0)
    {
      j = paramActivity.getResources().getIdentifier("config", "xml", paramActivity.getPackageName());
      i = j;
      if (j == 0)
      {
        LOG.e(TAG, "res/xml/config.xml is missing!");
        return;
      }
    }
    parse(paramActivity.getResources().getXml(i));
  }
  
  public void parse(XmlResourceParser paramXmlResourceParser)
  {
    int j = -1;
    Object localObject8 = "";
    Object localObject7 = "";
    boolean bool2 = false;
    int k = 0;
    Object localObject2 = null;
    internalWhitelist.addWhiteListEntry("file:///*", false);
    internalWhitelist.addWhiteListEntry("content:///*", false);
    internalWhitelist.addWhiteListEntry("data:*", false);
    while (j != 1)
    {
      String str1;
      Object localObject6;
      Object localObject1;
      Object localObject5;
      boolean bool1;
      int i;
      if (j == 2)
      {
        str1 = paramXmlResourceParser.getName();
        if (str1.equals("url-filter"))
        {
          Log.w(TAG, "Plugin " + (String)localObject8 + " is using deprecated tag <url-filter>");
          localObject6 = localObject2;
          if (localObject2 == null) {
            localObject6 = new ArrayList(2);
          }
          ((ArrayList)localObject6).add(paramXmlResourceParser.getAttributeValue(null, "value"));
          localObject1 = localObject8;
          localObject5 = localObject7;
          bool1 = bool2;
          i = k;
        }
      }
      try
      {
        for (;;)
        {
          k = paramXmlResourceParser.next();
          j = k;
          k = i;
          bool2 = bool1;
          localObject7 = localObject5;
          localObject8 = localObject1;
          localObject2 = localObject6;
          break;
          if (str1.equals("feature"))
          {
            i = 1;
            localObject1 = paramXmlResourceParser.getAttributeValue(null, "name");
            bool1 = bool2;
            localObject5 = localObject7;
            localObject6 = localObject2;
          }
          else if ((k != 0) && (str1.equals("param")))
          {
            str1 = paramXmlResourceParser.getAttributeValue(null, "name");
            if (str1.equals("service"))
            {
              localObject1 = paramXmlResourceParser.getAttributeValue(null, "value");
              i = k;
              bool1 = bool2;
              localObject5 = localObject7;
              localObject6 = localObject2;
            }
            else if ((str1.equals("package")) || (str1.equals("android-package")))
            {
              localObject5 = paramXmlResourceParser.getAttributeValue(null, "value");
              i = k;
              bool1 = bool2;
              localObject1 = localObject8;
              localObject6 = localObject2;
            }
            else
            {
              i = k;
              bool1 = bool2;
              localObject5 = localObject7;
              localObject1 = localObject8;
              localObject6 = localObject2;
              if (str1.equals("onload"))
              {
                bool1 = "true".equals(paramXmlResourceParser.getAttributeValue(null, "value"));
                i = k;
                localObject5 = localObject7;
                localObject1 = localObject8;
                localObject6 = localObject2;
              }
            }
          }
          else if (str1.equals("access"))
          {
            str1 = paramXmlResourceParser.getAttributeValue(null, "origin");
            String str2 = paramXmlResourceParser.getAttributeValue(null, "subdomains");
            int m;
            if (paramXmlResourceParser.getAttributeValue(null, "launch-external") != null)
            {
              m = 1;
              label453:
              i = k;
              bool1 = bool2;
              localObject5 = localObject7;
              localObject1 = localObject8;
              localObject6 = localObject2;
              if (str1 == null) {
                continue;
              }
              if (m == 0) {
                break label549;
              }
              localObject1 = externalWhitelist;
              if ((str2 == null) || (str2.compareToIgnoreCase("true") != 0)) {
                break label543;
              }
            }
            label543:
            for (bool1 = true;; bool1 = false)
            {
              ((Whitelist)localObject1).addWhiteListEntry(str1, bool1);
              i = k;
              bool1 = bool2;
              localObject5 = localObject7;
              localObject1 = localObject8;
              localObject6 = localObject2;
              break;
              m = 0;
              break label453;
            }
            label549:
            if ("*".equals(str1))
            {
              internalWhitelist.addWhiteListEntry("http://*/*", false);
              internalWhitelist.addWhiteListEntry("https://*/*", false);
              i = k;
              bool1 = bool2;
              localObject5 = localObject7;
              localObject1 = localObject8;
              localObject6 = localObject2;
            }
            else
            {
              localObject1 = internalWhitelist;
              if ((str2 != null) && (str2.compareToIgnoreCase("true") == 0)) {}
              for (bool1 = true;; bool1 = false)
              {
                ((Whitelist)localObject1).addWhiteListEntry(str1, bool1);
                i = k;
                bool1 = bool2;
                localObject5 = localObject7;
                localObject1 = localObject8;
                localObject6 = localObject2;
                break;
              }
            }
          }
          else if (str1.equals("preference"))
          {
            localObject1 = paramXmlResourceParser.getAttributeValue(null, "name").toLowerCase(Locale.ENGLISH);
            localObject5 = paramXmlResourceParser.getAttributeValue(null, "value");
            prefs.set((String)localObject1, (String)localObject5);
            i = k;
            bool1 = bool2;
            localObject5 = localObject7;
            localObject1 = localObject8;
            localObject6 = localObject2;
          }
          else
          {
            i = k;
            bool1 = bool2;
            localObject5 = localObject7;
            localObject1 = localObject8;
            localObject6 = localObject2;
            if (str1.equals("content"))
            {
              str1 = paramXmlResourceParser.getAttributeValue(null, "src");
              i = k;
              bool1 = bool2;
              localObject5 = localObject7;
              localObject1 = localObject8;
              localObject6 = localObject2;
              if (str1 != null)
              {
                setStartUrl(str1);
                i = k;
                bool1 = bool2;
                localObject5 = localObject7;
                localObject1 = localObject8;
                localObject6 = localObject2;
                continue;
                i = k;
                bool1 = bool2;
                localObject5 = localObject7;
                localObject1 = localObject8;
                localObject6 = localObject2;
                if (j == 3)
                {
                  i = k;
                  bool1 = bool2;
                  localObject5 = localObject7;
                  localObject1 = localObject8;
                  localObject6 = localObject2;
                  if (paramXmlResourceParser.getName().equals("feature"))
                  {
                    pluginEntries.add(new PluginEntry((String)localObject8, (String)localObject7, bool2, (List)localObject2));
                    localObject1 = "";
                    localObject5 = "";
                    i = 0;
                    bool1 = false;
                    localObject6 = null;
                  }
                }
              }
            }
          }
        }
      }
      catch (XmlPullParserException localXmlPullParserException)
      {
        localXmlPullParserException.printStackTrace();
        k = i;
        bool2 = bool1;
        localObject7 = localObject5;
        localObject8 = localObject1;
        Object localObject3 = localObject6;
      }
      catch (IOException localIOException)
      {
        localIOException.printStackTrace();
        k = i;
        bool2 = bool1;
        localObject7 = localObject5;
        localObject8 = localObject1;
        Object localObject4 = localObject6;
      }
    }
  }
}

/* Location:
 * Qualified Name:     org.apache.cordova.ConfigXmlParser
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */