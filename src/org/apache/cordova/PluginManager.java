package org.apache.cordova;

import android.content.Intent;
import android.net.Uri;
import android.os.Debug;
import android.util.Log;
import java.io.PrintStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;

public class PluginManager
{
  private static final int SLOW_EXEC_WARNING_THRESHOLD;
  private static String TAG = "PluginManager";
  private final CordovaWebView app;
  private final CordovaInterface ctx;
  private final HashMap<String, PluginEntry> entryMap = new HashMap();
  private final HashMap<String, CordovaPlugin> pluginMap = new HashMap();
  protected HashMap<String, List<String>> urlMap = new HashMap();
  
  static
  {
    if (Debug.isDebuggerConnected()) {}
    for (int i = 60;; i = 16)
    {
      SLOW_EXEC_WARNING_THRESHOLD = i;
      return;
    }
  }
  
  @Deprecated
  PluginManager(CordovaWebView paramCordovaWebView, CordovaInterface paramCordovaInterface)
  {
    this(paramCordovaWebView, paramCordovaInterface, null);
  }
  
  PluginManager(CordovaWebView paramCordovaWebView, CordovaInterface paramCordovaInterface, List<PluginEntry> paramList)
  {
    ctx = paramCordovaInterface;
    app = paramCordovaWebView;
    paramCordovaWebView = paramList;
    if (paramList == null)
    {
      paramCordovaWebView = new ConfigXmlParser();
      paramCordovaWebView.parse(ctx.getActivity());
      paramCordovaWebView = paramCordovaWebView.getPluginEntries();
    }
    setPluginEntries(paramCordovaWebView);
  }
  
  private CordovaPlugin instantiatePlugin(String paramString)
  {
    CordovaPlugin localCordovaPlugin = null;
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if (paramString != null) {
      localObject1 = localObject2;
    }
    for (;;)
    {
      try
      {
        if ("".equals(paramString)) {
          break label98;
        }
        localObject1 = Class.forName(paramString);
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        System.out.println("Error adding plugin " + paramString + ".");
        return null;
      }
      if ((i & CordovaPlugin.class.isAssignableFrom((Class)localObject1)) != 0) {
        localCordovaPlugin = (CordovaPlugin)((Class)localObject1).newInstance();
      }
      return localCordovaPlugin;
      int i = 0;
      continue;
      label98:
      if (localException != null) {
        i = 1;
      }
    }
  }
  
  public void addService(String paramString1, String paramString2)
  {
    addService(new PluginEntry(paramString1, paramString2, false));
  }
  
  public void addService(PluginEntry paramPluginEntry)
  {
    entryMap.put(service, paramPluginEntry);
    List localList = paramPluginEntry.getUrlFilters();
    if (localList != null) {
      urlMap.put(service, localList);
    }
    if (plugin != null)
    {
      plugin.privateInitialize(ctx, app, app.getPreferences());
      pluginMap.put(service, plugin);
    }
  }
  
  @Deprecated
  public void clearPluginObjects()
  {
    pluginMap.clear();
  }
  
  public void exec(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    CordovaPlugin localCordovaPlugin = getPlugin(paramString1);
    if (localCordovaPlugin == null)
    {
      Log.d(TAG, "exec() call to unknown plugin: " + paramString1);
      paramString1 = new PluginResult(PluginResult.Status.CLASS_NOT_FOUND_EXCEPTION);
      app.sendPluginResult(paramString1, paramString3);
    }
    for (;;)
    {
      return;
      paramString3 = new CallbackContext(paramString3, app);
      try
      {
        long l = System.currentTimeMillis();
        boolean bool = localCordovaPlugin.execute(paramString2, paramString4, paramString3);
        l = System.currentTimeMillis() - l;
        if (l > SLOW_EXEC_WARNING_THRESHOLD) {
          Log.w(TAG, "THREAD WARNING: exec() call to " + paramString1 + "." + paramString2 + " blocked the main thread for " + l + "ms. Plugin should use CordovaInterface.getThreadPool().");
        }
        if (!bool)
        {
          paramString3.sendPluginResult(new PluginResult(PluginResult.Status.INVALID_ACTION));
          return;
        }
      }
      catch (JSONException paramString1)
      {
        paramString3.sendPluginResult(new PluginResult(PluginResult.Status.JSON_EXCEPTION));
        return;
      }
      catch (Exception paramString1)
      {
        Log.e(TAG, "Uncaught exception from plugin", paramString1);
        paramString3.error(paramString1.getMessage());
      }
    }
  }
  
  @Deprecated
  public void exec(String paramString1, String paramString2, String paramString3, String paramString4, boolean paramBoolean)
  {
    exec(paramString1, paramString2, paramString3, paramString4);
  }
  
  public CordovaPlugin getPlugin(String paramString)
  {
    CordovaPlugin localCordovaPlugin = (CordovaPlugin)pluginMap.get(paramString);
    Object localObject = localCordovaPlugin;
    if (localCordovaPlugin == null)
    {
      localObject = (PluginEntry)entryMap.get(paramString);
      if (localObject == null) {
        return null;
      }
      if (plugin == null) {
        break label79;
      }
    }
    label79:
    for (localObject = plugin;; localObject = instantiatePlugin(pluginClass))
    {
      ((CordovaPlugin)localObject).privateInitialize(ctx, app, app.getPreferences());
      pluginMap.put(paramString, localObject);
      return (CordovaPlugin)localObject;
    }
  }
  
  public void init()
  {
    LOG.d(TAG, "init()");
    onPause(false);
    onDestroy();
    pluginMap.clear();
    startupPlugins();
  }
  
  @Deprecated
  public void loadPlugins() {}
  
  public void onDestroy()
  {
    Iterator localIterator = pluginMap.values().iterator();
    while (localIterator.hasNext()) {
      ((CordovaPlugin)localIterator.next()).onDestroy();
    }
  }
  
  public void onNewIntent(Intent paramIntent)
  {
    Iterator localIterator = pluginMap.values().iterator();
    while (localIterator.hasNext()) {
      ((CordovaPlugin)localIterator.next()).onNewIntent(paramIntent);
    }
  }
  
  public boolean onOverrideUrlLoading(String paramString)
  {
    Iterator localIterator = entryMap.values().iterator();
    for (;;)
    {
      if (localIterator.hasNext())
      {
        Object localObject1 = (PluginEntry)localIterator.next();
        Object localObject2 = (List)urlMap.get(service);
        if (localObject2 != null)
        {
          localObject2 = ((List)localObject2).iterator();
          if (!((Iterator)localObject2).hasNext()) {
            continue;
          }
          if (!paramString.startsWith((String)((Iterator)localObject2).next())) {
            break;
          }
          return getPlugin(service).onOverrideUrlLoading(paramString);
        }
        localObject1 = (CordovaPlugin)pluginMap.get(service);
        if ((localObject1 != null) && (((CordovaPlugin)localObject1).onOverrideUrlLoading(paramString))) {
          return true;
        }
      }
    }
    return false;
  }
  
  public void onPause(boolean paramBoolean)
  {
    Iterator localIterator = pluginMap.values().iterator();
    while (localIterator.hasNext()) {
      ((CordovaPlugin)localIterator.next()).onPause(paramBoolean);
    }
  }
  
  public void onReset()
  {
    Iterator localIterator = pluginMap.values().iterator();
    while (localIterator.hasNext()) {
      ((CordovaPlugin)localIterator.next()).onReset();
    }
  }
  
  public void onResume(boolean paramBoolean)
  {
    Iterator localIterator = pluginMap.values().iterator();
    while (localIterator.hasNext()) {
      ((CordovaPlugin)localIterator.next()).onResume(paramBoolean);
    }
  }
  
  public Object postMessage(String paramString, Object paramObject)
  {
    Object localObject1 = ctx.onMessage(paramString, paramObject);
    if (localObject1 != null) {
      return localObject1;
    }
    localObject1 = pluginMap.values().iterator();
    while (((Iterator)localObject1).hasNext())
    {
      Object localObject2 = ((CordovaPlugin)((Iterator)localObject1).next()).onMessage(paramString, paramObject);
      if (localObject2 != null) {
        return localObject2;
      }
    }
    return null;
  }
  
  Uri remapUri(Uri paramUri)
  {
    Iterator localIterator = pluginMap.values().iterator();
    while (localIterator.hasNext())
    {
      Uri localUri = ((CordovaPlugin)localIterator.next()).remapUri(paramUri);
      if (localUri != null) {
        return localUri;
      }
    }
    return null;
  }
  
  public void setPluginEntries(List<PluginEntry> paramList)
  {
    onPause(false);
    onDestroy();
    pluginMap.clear();
    urlMap.clear();
    paramList = paramList.iterator();
    while (paramList.hasNext()) {
      addService((PluginEntry)paramList.next());
    }
  }
  
  @Deprecated
  public void startupPlugins()
  {
    Iterator localIterator = entryMap.values().iterator();
    while (localIterator.hasNext())
    {
      PluginEntry localPluginEntry = (PluginEntry)localIterator.next();
      if (onload) {
        getPlugin(service);
      }
    }
  }
}

/* Location:
 * Qualified Name:     org.apache.cordova.PluginManager
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */