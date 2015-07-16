package org.apache.cordova;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public class CordovaPreferences
{
  private Bundle preferencesBundleExtras;
  private HashMap<String, String> prefs = new HashMap(20);
  
  public void copyIntoIntentExtras(Activity paramActivity)
  {
    Iterator localIterator = prefs.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str1 = (String)localIterator.next();
      String str2 = (String)prefs.get(str1);
      if (str2 != null) {
        if (str1.equals("loglevel"))
        {
          LOG.setLogLevel(str2);
        }
        else
        {
          int i;
          if (str1.equals("splashscreen"))
          {
            i = paramActivity.getResources().getIdentifier(str2, "drawable", paramActivity.getClass().getPackage().getName());
            paramActivity.getIntent().putExtra(str1, i);
          }
          else if (str1.equals("backgroundcolor"))
          {
            i = (int)Long.decode(str2).longValue();
            paramActivity.getIntent().putExtra(str1, i);
          }
          else if (str1.equals("loadurltimeoutvalue"))
          {
            i = Integer.decode(str2).intValue();
            paramActivity.getIntent().putExtra(str1, i);
          }
          else if (str1.equals("splashscreendelay"))
          {
            i = Integer.decode(str2).intValue();
            paramActivity.getIntent().putExtra(str1, i);
          }
          else
          {
            boolean bool;
            if (str1.equals("keeprunning"))
            {
              bool = Boolean.parseBoolean(str2);
              paramActivity.getIntent().putExtra(str1, bool);
            }
            else if (str1.equals("inappbrowserstorageenabled"))
            {
              bool = Boolean.parseBoolean(str2);
              paramActivity.getIntent().putExtra(str1, bool);
            }
            else if (str1.equals("disallowoverscroll"))
            {
              bool = Boolean.parseBoolean(str2);
              paramActivity.getIntent().putExtra(str1, bool);
            }
            else
            {
              paramActivity.getIntent().putExtra(str1, str2);
            }
          }
        }
      }
    }
    if (preferencesBundleExtras == null) {
      preferencesBundleExtras = paramActivity.getIntent().getExtras();
    }
  }
  
  public Map<String, String> getAll()
  {
    return prefs;
  }
  
  public boolean getBoolean(String paramString, boolean paramBoolean)
  {
    paramString = paramString.toLowerCase(Locale.ENGLISH);
    Object localObject = (String)prefs.get(paramString);
    boolean bool;
    if (localObject != null) {
      bool = Boolean.parseBoolean((String)localObject);
    }
    do
    {
      return bool;
      bool = paramBoolean;
    } while (preferencesBundleExtras == null);
    localObject = preferencesBundleExtras.get(paramString);
    if ((localObject instanceof String)) {
      return "true".equals(localObject);
    }
    return preferencesBundleExtras.getBoolean(paramString, paramBoolean);
  }
  
  public double getDouble(String paramString, double paramDouble)
  {
    paramString = paramString.toLowerCase(Locale.ENGLISH);
    Object localObject = (String)prefs.get(paramString);
    double d;
    if (localObject != null) {
      d = Double.valueOf((String)localObject).doubleValue();
    }
    do
    {
      return d;
      d = paramDouble;
    } while (preferencesBundleExtras == null);
    localObject = preferencesBundleExtras.get(paramString);
    if ((localObject instanceof String)) {
      return Double.valueOf((String)localObject).doubleValue();
    }
    return preferencesBundleExtras.getDouble(paramString, paramDouble);
  }
  
  public int getInteger(String paramString, int paramInt)
  {
    paramString = paramString.toLowerCase(Locale.ENGLISH);
    Object localObject = (String)prefs.get(paramString);
    int i;
    if (localObject != null) {
      i = (int)Long.decode((String)localObject).longValue();
    }
    do
    {
      return i;
      i = paramInt;
    } while (preferencesBundleExtras == null);
    localObject = preferencesBundleExtras.get(paramString);
    if ((localObject instanceof String)) {
      return Integer.valueOf((String)localObject).intValue();
    }
    return preferencesBundleExtras.getInt(paramString, paramInt);
  }
  
  public String getString(String paramString1, String paramString2)
  {
    paramString1 = paramString1.toLowerCase(Locale.ENGLISH);
    String str = (String)prefs.get(paramString1);
    if (str != null) {
      return str;
    }
    if ((preferencesBundleExtras != null) && (!"errorurl".equals(paramString1)))
    {
      paramString1 = preferencesBundleExtras.get(paramString1);
      if (paramString1 != null) {
        return paramString1.toString();
      }
    }
    return paramString2;
  }
  
  public void set(String paramString, double paramDouble)
  {
    set(paramString, "" + paramDouble);
  }
  
  public void set(String paramString, int paramInt)
  {
    set(paramString, "" + paramInt);
  }
  
  public void set(String paramString1, String paramString2)
  {
    prefs.put(paramString1.toLowerCase(Locale.ENGLISH), paramString2);
  }
  
  public void set(String paramString, boolean paramBoolean)
  {
    set(paramString, "" + paramBoolean);
  }
  
  public void setPreferencesBundle(Bundle paramBundle)
  {
    preferencesBundleExtras = paramBundle;
  }
}

/* Location:
 * Qualified Name:     org.apache.cordova.CordovaPreferences
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */