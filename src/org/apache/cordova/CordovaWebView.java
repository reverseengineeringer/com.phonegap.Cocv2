package org.apache.cordova;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebBackForwardList;
import android.webkit.WebChromeClient;
import android.webkit.WebChromeClient.CustomViewCallback;
import android.webkit.WebHistoryItem;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout.LayoutParams;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutorService;

public class CordovaWebView
  extends WebView
{
  public static final String CORDOVA_VERSION = "3.6.4";
  static final FrameLayout.LayoutParams COVER_SCREEN_GRAVITY_CENTER = new FrameLayout.LayoutParams(-1, -1, 17);
  public static final String TAG = "CordovaWebView";
  private HashSet<Integer> boundKeyCodes = new HashSet();
  CordovaBridge bridge;
  private CordovaChromeClient chromeClient;
  private CordovaInterface cordova;
  private Whitelist externalWhitelist;
  private Whitelist internalWhitelist;
  private long lastMenuEventTime = 0L;
  int loadUrlTimeout = 0;
  String loadedUrl;
  private View mCustomView;
  private WebChromeClient.CustomViewCallback mCustomViewCallback;
  private boolean paused;
  public PluginManager pluginManager;
  private CordovaPreferences preferences;
  private BroadcastReceiver receiver;
  private CordovaResourceApi resourceApi;
  CordovaWebViewClient viewClient;
  
  public CordovaWebView(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public CordovaWebView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  @Deprecated
  public CordovaWebView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }
  
  @Deprecated
  @TargetApi(11)
  public CordovaWebView(Context paramContext, AttributeSet paramAttributeSet, int paramInt, boolean paramBoolean)
  {
    super(paramContext, paramAttributeSet, paramInt, paramBoolean);
  }
  
  @TargetApi(19)
  private void enableRemoteDebugging()
  {
    try
    {
      WebView.setWebContentsDebuggingEnabled(true);
      return;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      Log.d("CordovaWebView", "You have one job! To turn on Remote Web Debugging! YOU HAVE FAILED! ");
      localIllegalArgumentException.printStackTrace();
    }
  }
  
  private void exposeJsInterface()
  {
    if (Build.VERSION.SDK_INT < 17)
    {
      Log.i("CordovaWebView", "Disabled addJavascriptInterface() bridge since Android version is old.");
      return;
    }
    addJavascriptInterface(new ExposedJsApi(bridge), "_cordovaNative");
  }
  
  private void initIfNecessary()
  {
    if (pluginManager == null)
    {
      Log.w("CordovaWebView", "CordovaWebView.init() was not called. This will soon be required.");
      CordovaInterface localCordovaInterface = (CordovaInterface)getContext();
      if (!Config.isInitialized()) {
        Config.init(localCordovaInterface.getActivity());
      }
      init(localCordovaInterface, makeWebViewClient(localCordovaInterface), makeWebChromeClient(localCordovaInterface), Config.getPluginEntries(), Config.getWhitelist(), Config.getExternalWhitelist(), Config.getPreferences());
    }
  }
  
  @SuppressLint({"SetJavaScriptEnabled"})
  private void initWebViewSettings()
  {
    setInitialScale(0);
    setVerticalScrollBarEnabled(false);
    if (shouldRequestFocusOnInit()) {
      requestFocusFromTouch();
    }
    Object localObject1 = getSettings();
    ((WebSettings)localObject1).setJavaScriptEnabled(true);
    ((WebSettings)localObject1).setJavaScriptCanOpenWindowsAutomatically(true);
    ((WebSettings)localObject1).setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
    try
    {
      Object localObject2 = WebSettings.class.getMethod("setNavDump", new Class[] { Boolean.TYPE });
      String str = Build.MANUFACTURER;
      Log.d("CordovaWebView", "CordovaWebView is running on device made by: " + str);
      if ((Build.VERSION.SDK_INT < 11) && (Build.MANUFACTURER.contains("HTC"))) {
        ((Method)localObject2).invoke(localObject1, new Object[] { Boolean.valueOf(true) });
      }
      ((WebSettings)localObject1).setSaveFormData(false);
      ((WebSettings)localObject1).setSavePassword(false);
      if (Build.VERSION.SDK_INT > 15) {
        Level16Apis.enableUniversalAccess((WebSettings)localObject1);
      }
      localObject2 = getContext().getApplicationContext().getDir("database", 0).getPath();
      ((WebSettings)localObject1).setDatabaseEnabled(true);
      ((WebSettings)localObject1).setDatabasePath((String)localObject2);
      if (((getContextgetApplicationContextgetApplicationInfoflags & 0x2) != 0) && (Build.VERSION.SDK_INT >= 19)) {
        enableRemoteDebugging();
      }
      ((WebSettings)localObject1).setGeolocationDatabasePath((String)localObject2);
      ((WebSettings)localObject1).setDomStorageEnabled(true);
      ((WebSettings)localObject1).setGeolocationEnabled(true);
      ((WebSettings)localObject1).setAppCacheMaxSize(5242880L);
      ((WebSettings)localObject1).setAppCachePath((String)localObject2);
      ((WebSettings)localObject1).setAppCacheEnabled(true);
      ((WebSettings)localObject1).getUserAgentString();
      localObject1 = new IntentFilter();
      ((IntentFilter)localObject1).addAction("android.intent.action.CONFIGURATION_CHANGED");
      if (receiver == null)
      {
        receiver = new BroadcastReceiver()
        {
          public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
          {
            getSettings().getUserAgentString();
          }
        };
        getContext().registerReceiver(receiver, (IntentFilter)localObject1);
      }
      return;
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      for (;;)
      {
        Log.d("CordovaWebView", "We are on a modern version of Android, we will deprecate HTC 2.3 devices in 2.8");
      }
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      for (;;)
      {
        Log.d("CordovaWebView", "Doing the NavDump failed with bad arguments");
      }
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      for (;;)
      {
        Log.d("CordovaWebView", "This should never happen: IllegalAccessException means this isn't Android anymore");
      }
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      for (;;)
      {
        Log.d("CordovaWebView", "This should never happen: InvocationTargetException means this isn't Android anymore.");
      }
    }
  }
  
  public boolean backHistory()
  {
    if (super.canGoBack())
    {
      super.goBack();
      return true;
    }
    return false;
  }
  
  @Deprecated
  public void bindButton(int paramInt, boolean paramBoolean1, boolean paramBoolean2)
  {
    setButtonPlumbedToJs(paramInt, paramBoolean2);
  }
  
  @Deprecated
  public void bindButton(String paramString, boolean paramBoolean)
  {
    if (paramString.compareTo("volumeup") == 0) {
      setButtonPlumbedToJs(24, paramBoolean);
    }
    while (paramString.compareTo("volumedown") != 0) {
      return;
    }
    setButtonPlumbedToJs(25, paramBoolean);
  }
  
  @Deprecated
  public void bindButton(boolean paramBoolean)
  {
    setButtonPlumbedToJs(4, paramBoolean);
  }
  
  public Whitelist getExternalWhitelist()
  {
    return externalWhitelist;
  }
  
  public CordovaPreferences getPreferences()
  {
    return preferences;
  }
  
  public String getProperty(String paramString1, String paramString2)
  {
    Bundle localBundle = cordova.getActivity().getIntent().getExtras();
    if (localBundle == null) {}
    do
    {
      return paramString2;
      paramString1 = localBundle.get(paramString1.toLowerCase(Locale.getDefault()));
    } while (paramString1 == null);
    return paramString1.toString();
  }
  
  public CordovaResourceApi getResourceApi()
  {
    return resourceApi;
  }
  
  public CordovaChromeClient getWebChromeClient()
  {
    return chromeClient;
  }
  
  public Whitelist getWhitelist()
  {
    return internalWhitelist;
  }
  
  @Deprecated
  public boolean hadKeyEvent()
  {
    return false;
  }
  
  public void handleDestroy()
  {
    loadUrl("javascript:try{cordova.require('cordova/channel').onDestroy.fire();}catch(e){console.log('exception firing destroy event from native');};");
    loadUrl("about:blank");
    if (pluginManager != null) {
      pluginManager.onDestroy();
    }
    if (receiver != null) {}
    try
    {
      getContext().unregisterReceiver(receiver);
      return;
    }
    catch (Exception localException)
    {
      Log.e("CordovaWebView", "Error unregistering configuration receiver: " + localException.getMessage(), localException);
    }
  }
  
  public void handlePause(boolean paramBoolean)
  {
    LOG.d("CordovaWebView", "Handle the pause");
    loadUrl("javascript:try{cordova.fireDocumentEvent('pause');}catch(e){console.log('exception firing pause event from native');};");
    if (pluginManager != null) {
      pluginManager.onPause(paramBoolean);
    }
    if (!paramBoolean) {
      pauseTimers();
    }
    paused = true;
  }
  
  public void handleResume(boolean paramBoolean1, boolean paramBoolean2)
  {
    loadUrl("javascript:try{cordova.fireDocumentEvent('resume');}catch(e){console.log('exception firing resume event from native');};");
    if (pluginManager != null) {
      pluginManager.onResume(paramBoolean1);
    }
    resumeTimers();
    paused = false;
  }
  
  public void hideCustomView()
  {
    Log.d("CordovaWebView", "Hiding Custom View");
    if (mCustomView == null) {
      return;
    }
    mCustomView.setVisibility(8);
    ((ViewGroup)getParent()).removeView(mCustomView);
    mCustomView = null;
    mCustomViewCallback.onCustomViewHidden();
    setVisibility(0);
  }
  
  public void init(CordovaInterface paramCordovaInterface, CordovaWebViewClient paramCordovaWebViewClient, CordovaChromeClient paramCordovaChromeClient, List<PluginEntry> paramList, Whitelist paramWhitelist1, Whitelist paramWhitelist2, CordovaPreferences paramCordovaPreferences)
  {
    if (cordova != null) {
      throw new IllegalStateException();
    }
    cordova = paramCordovaInterface;
    viewClient = paramCordovaWebViewClient;
    chromeClient = paramCordovaChromeClient;
    internalWhitelist = paramWhitelist1;
    externalWhitelist = paramWhitelist2;
    preferences = paramCordovaPreferences;
    super.setWebChromeClient(paramCordovaChromeClient);
    super.setWebViewClient(paramCordovaWebViewClient);
    pluginManager = new PluginManager(this, cordova, paramList);
    bridge = new CordovaBridge(pluginManager, new NativeToJsMessageQueue(this, paramCordovaInterface));
    resourceApi = new CordovaResourceApi(getContext(), pluginManager);
    pluginManager.addService("App", "org.apache.cordova.App");
    initWebViewSettings();
    exposeJsInterface();
  }
  
  @Deprecated
  public boolean isBackButtonBound()
  {
    return isButtonPlumbedToJs(4);
  }
  
  public boolean isButtonPlumbedToJs(int paramInt)
  {
    return boundKeyCodes.contains(Integer.valueOf(paramInt));
  }
  
  public boolean isCustomViewShowing()
  {
    return mCustomView != null;
  }
  
  public boolean isPaused()
  {
    return paused;
  }
  
  public void loadUrl(String paramString)
  {
    if ((paramString.equals("about:blank")) || (paramString.startsWith("javascript:")))
    {
      loadUrlNow(paramString);
      return;
    }
    loadUrlIntoView(paramString);
  }
  
  @Deprecated
  public void loadUrl(String paramString, int paramInt)
  {
    if (paramString == null)
    {
      loadUrlIntoView(Config.getStartUrl());
      return;
    }
    loadUrlIntoView(paramString);
  }
  
  public void loadUrlIntoView(String paramString)
  {
    loadUrlIntoView(paramString, true);
  }
  
  public void loadUrlIntoView(String paramString, int paramInt)
  {
    if ((paramString.startsWith("javascript:")) || (canGoBack())) {}
    for (;;)
    {
      loadUrlIntoView(paramString);
      return;
      LOG.d("CordovaWebView", "loadUrlIntoView(%s, %d)", new Object[] { paramString, Integer.valueOf(paramInt) });
      postMessage("splashscreen", "show");
    }
  }
  
  public void loadUrlIntoView(final String paramString, boolean paramBoolean)
  {
    LOG.d("CordovaWebView", ">>> loadUrl(" + paramString + ")");
    initIfNecessary();
    if (paramBoolean)
    {
      loadedUrl = paramString;
      pluginManager.init();
    }
    final int i = loadUrlTimeout;
    final Runnable local3 = new Runnable()
    {
      public void run()
      {
        jdField_this.stopLoading();
        LOG.e("CordovaWebView", "CordovaWebView: TIMEOUT ERROR!");
        if (viewClient != null) {
          viewClient.onReceivedError(jdField_this, -6, "The connection to the server was unsuccessful.", paramString);
        }
      }
    }
    {
      public void run()
      {
        for (;;)
        {
          try {}catch (InterruptedException localInterruptedException)
          {
            localInterruptedException.printStackTrace();
            continue;
          }
          try
          {
            wait(val$loadUrlTimeoutValue);
            if (jdField_thisloadUrlTimeout == i) {
              jdField_thiscordova.getActivity().runOnUiThread(val$loadError);
            }
            return;
          }
          finally {}
        }
      }
    };
    cordova.getActivity().runOnUiThread(new Runnable()
    {
      public void run()
      {
        cordova.getThreadPool().execute(local3);
        jdField_this.loadUrlNow(paramString);
      }
    });
  }
  
  void loadUrlNow(String paramString)
  {
    if ((LOG.isLoggable(3)) && (!paramString.startsWith("javascript:"))) {
      LOG.d("CordovaWebView", ">>> loadUrlNow()");
    }
    if ((paramString.startsWith("file://")) || (paramString.startsWith("javascript:")) || (internalWhitelist.isUrlWhiteListed(paramString))) {
      super.loadUrl(paramString);
    }
  }
  
  public CordovaChromeClient makeWebChromeClient(CordovaInterface paramCordovaInterface)
  {
    return new CordovaChromeClient(paramCordovaInterface, this);
  }
  
  public CordovaWebViewClient makeWebViewClient(CordovaInterface paramCordovaInterface)
  {
    if (Build.VERSION.SDK_INT < 14) {
      return new CordovaWebViewClient(paramCordovaInterface, this);
    }
    return new IceCreamCordovaWebViewClient(paramCordovaInterface, this);
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    boolean bool = false;
    if (boundKeyCodes.contains(Integer.valueOf(paramInt)))
    {
      if (paramInt == 25)
      {
        loadUrl("javascript:cordova.fireDocumentEvent('volumedownbutton');");
        return true;
      }
      if (paramInt == 24)
      {
        loadUrl("javascript:cordova.fireDocumentEvent('volumeupbutton');");
        return true;
      }
      return super.onKeyDown(paramInt, paramKeyEvent);
    }
    if (paramInt == 4)
    {
      if ((!startOfHistory()) || (isButtonPlumbedToJs(4))) {
        bool = true;
      }
      return bool;
    }
    if (paramInt == 82)
    {
      View localView = getFocusedChild();
      if (localView != null)
      {
        ((InputMethodManager)cordova.getActivity().getSystemService("input_method")).hideSoftInputFromWindow(localView.getWindowToken(), 0);
        cordova.getActivity().openOptionsMenu();
        return true;
      }
      return super.onKeyDown(paramInt, paramKeyEvent);
    }
    return super.onKeyDown(paramInt, paramKeyEvent);
  }
  
  public boolean onKeyUp(int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramInt == 4)
    {
      if (mCustomView != null) {
        hideCustomView();
      }
      do
      {
        return true;
        if (isButtonPlumbedToJs(4))
        {
          loadUrl("javascript:cordova.fireDocumentEvent('backbutton');");
          return true;
        }
      } while (backHistory());
    }
    do
    {
      return super.onKeyUp(paramInt, paramKeyEvent);
      if (paramInt == 82)
      {
        if (lastMenuEventTime < paramKeyEvent.getEventTime()) {
          loadUrl("javascript:cordova.fireDocumentEvent('menubutton');");
        }
        lastMenuEventTime = paramKeyEvent.getEventTime();
        return super.onKeyUp(paramInt, paramKeyEvent);
      }
    } while (paramInt != 84);
    loadUrl("javascript:cordova.fireDocumentEvent('searchbutton');");
    return true;
  }
  
  public void onNewIntent(Intent paramIntent)
  {
    if (pluginManager != null) {
      pluginManager.onNewIntent(paramIntent);
    }
  }
  
  public void onScrollChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onScrollChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    postMessage("onScrollChanged", new ScrollEvent(paramInt1, paramInt2, paramInt3, paramInt4, this));
  }
  
  public void postMessage(String paramString, Object paramObject)
  {
    if (pluginManager != null) {
      pluginManager.postMessage(paramString, paramObject);
    }
  }
  
  public void printBackForwardList()
  {
    WebBackForwardList localWebBackForwardList = copyBackForwardList();
    int j = localWebBackForwardList.getSize();
    int i = 0;
    while (i < j)
    {
      String str = localWebBackForwardList.getItemAtIndex(i).getUrl();
      LOG.d("CordovaWebView", "The URL at index: " + Integer.toString(i) + " is " + str);
      i += 1;
    }
  }
  
  public WebBackForwardList restoreState(Bundle paramBundle)
  {
    paramBundle = super.restoreState(paramBundle);
    Log.d("CordovaWebView", "WebView restoration crew now restoring!");
    pluginManager.init();
    return paramBundle;
  }
  
  @Deprecated
  public void sendJavascript(String paramString)
  {
    bridge.getMessageQueue().addJavaScript(paramString);
  }
  
  public void sendPluginResult(PluginResult paramPluginResult, String paramString)
  {
    bridge.getMessageQueue().addPluginResult(paramPluginResult, paramString);
  }
  
  public void setButtonPlumbedToJs(int paramInt, boolean paramBoolean)
  {
    switch (paramInt)
    {
    default: 
      throw new IllegalArgumentException("Unsupported keycode: " + paramInt);
    }
    if (paramBoolean)
    {
      boundKeyCodes.add(Integer.valueOf(paramInt));
      return;
    }
    boundKeyCodes.remove(Integer.valueOf(paramInt));
  }
  
  public void setWebChromeClient(WebChromeClient paramWebChromeClient)
  {
    chromeClient = ((CordovaChromeClient)paramWebChromeClient);
    super.setWebChromeClient(paramWebChromeClient);
  }
  
  public void setWebViewClient(WebViewClient paramWebViewClient)
  {
    viewClient = ((CordovaWebViewClient)paramWebViewClient);
    super.setWebViewClient(paramWebViewClient);
  }
  
  protected boolean shouldRequestFocusOnInit()
  {
    return true;
  }
  
  public void showCustomView(View paramView, WebChromeClient.CustomViewCallback paramCustomViewCallback)
  {
    Log.d("CordovaWebView", "showing Custom View");
    if (mCustomView != null)
    {
      paramCustomViewCallback.onCustomViewHidden();
      return;
    }
    mCustomView = paramView;
    mCustomViewCallback = paramCustomViewCallback;
    paramCustomViewCallback = (ViewGroup)getParent();
    paramCustomViewCallback.addView(paramView, COVER_SCREEN_GRAVITY_CENTER);
    setVisibility(8);
    paramCustomViewCallback.setVisibility(0);
    paramCustomViewCallback.bringToFront();
  }
  
  public void showWebPage(String paramString, boolean paramBoolean1, boolean paramBoolean2, HashMap<String, Object> paramHashMap)
  {
    LOG.d("CordovaWebView", "showWebPage(%s, %b, %b, HashMap", new Object[] { paramString, Boolean.valueOf(paramBoolean1), Boolean.valueOf(paramBoolean2) });
    if (paramBoolean2) {
      clearHistory();
    }
    if (!paramBoolean1)
    {
      if ((paramString.startsWith("file://")) || (internalWhitelist.isUrlWhiteListed(paramString)))
      {
        loadUrl(paramString);
        return;
      }
      LOG.w("CordovaWebView", "showWebPage: Cannot load URL into webview since it is not in white list.  Loading into browser instead. (URL=" + paramString + ")");
    }
    for (;;)
    {
      Uri localUri;
      try
      {
        paramHashMap = new Intent("android.intent.action.VIEW");
        localUri = Uri.parse(paramString);
        if ("file".equals(localUri.getScheme()))
        {
          paramHashMap.setDataAndType(localUri, resourceApi.getMimeType(localUri));
          cordova.getActivity().startActivity(paramHashMap);
          return;
        }
      }
      catch (ActivityNotFoundException paramHashMap)
      {
        LOG.e("CordovaWebView", "Error loading url " + paramString, paramHashMap);
        return;
      }
      paramHashMap.setData(localUri);
    }
  }
  
  public boolean startOfHistory()
  {
    boolean bool = false;
    Object localObject = copyBackForwardList().getItemAtIndex(0);
    if (localObject != null)
    {
      localObject = ((WebHistoryItem)localObject).getUrl();
      String str = getUrl();
      LOG.d("CordovaWebView", "The current URL is: " + str);
      LOG.d("CordovaWebView", "The URL at item 0 is: " + (String)localObject);
      bool = str.equals(localObject);
    }
    return bool;
  }
  
  public void stopLoading()
  {
    viewClient.isCurrentlyLoading = false;
    super.stopLoading();
  }
  
  @Deprecated
  public void storeResult(int paramInt1, int paramInt2, Intent paramIntent) {}
  
  class ActivityResult
  {
    Intent incoming;
    int request;
    int result;
    
    public ActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
    {
      request = paramInt1;
      result = paramInt2;
      incoming = paramIntent;
    }
  }
  
  @TargetApi(16)
  private static class Level16Apis
  {
    static void enableUniversalAccess(WebSettings paramWebSettings)
    {
      paramWebSettings.setAllowUniversalAccessFromFileURLs(true);
    }
  }
}

/* Location:
 * Qualified Name:     org.apache.cordova.CordovaWebView
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */