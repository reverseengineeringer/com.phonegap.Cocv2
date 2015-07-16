package org.apache.cordova;

import java.util.List;

public class PluginEntry
{
  public boolean onload;
  public CordovaPlugin plugin;
  public String pluginClass;
  public String service;
  private List<String> urlFilters;
  
  public PluginEntry(String paramString1, String paramString2, boolean paramBoolean)
  {
    this(paramString1, paramString2, paramBoolean, null, null);
  }
  
  @Deprecated
  public PluginEntry(String paramString1, String paramString2, boolean paramBoolean, List<String> paramList)
  {
    service = paramString1;
    pluginClass = paramString2;
    onload = paramBoolean;
    urlFilters = paramList;
    plugin = null;
  }
  
  private PluginEntry(String paramString1, String paramString2, boolean paramBoolean, CordovaPlugin paramCordovaPlugin, List<String> paramList)
  {
    service = paramString1;
    pluginClass = paramString2;
    onload = paramBoolean;
    urlFilters = paramList;
    plugin = paramCordovaPlugin;
  }
  
  public PluginEntry(String paramString, CordovaPlugin paramCordovaPlugin)
  {
    this(paramString, paramCordovaPlugin.getClass().getName(), true, paramCordovaPlugin, null);
  }
  
  public List<String> getUrlFilters()
  {
    return urlFilters;
  }
}

/* Location:
 * Qualified Name:     org.apache.cordova.PluginEntry
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */