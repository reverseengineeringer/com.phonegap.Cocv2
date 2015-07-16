package org.apache.cordova;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.webkit.ValueCallback;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONException;
import org.json.JSONObject;

public class CordovaActivity
  extends Activity
  implements CordovaInterface
{
  private static int ACTIVITY_EXITING = 2;
  private static int ACTIVITY_RUNNING;
  private static int ACTIVITY_STARTING;
  public static String TAG = "CordovaActivity";
  protected CordovaPlugin activityResultCallback = null;
  protected boolean activityResultKeepRunning;
  private int activityState = 0;
  protected CordovaWebView appView;
  protected Whitelist externalWhitelist;
  private String initCallbackClass;
  protected Whitelist internalWhitelist;
  protected boolean keepRunning = true;
  protected String launchUrl;
  protected int loadUrlTimeoutValue = 20000;
  protected ArrayList<PluginEntry> pluginEntries;
  protected CordovaPreferences preferences;
  @Deprecated
  protected LinearLayout root;
  protected ProgressDialog spinnerDialog = null;
  protected Dialog splashDialog;
  protected int splashscreen = 0;
  protected int splashscreenTime = 3000;
  private final ExecutorService threadPool = Executors.newCachedThreadPool();
  @Deprecated
  protected CordovaWebViewClient webViewClient;
  
  static
  {
    ACTIVITY_STARTING = 0;
    ACTIVITY_RUNNING = 1;
  }
  
  @Deprecated
  public void addService(String paramString1, String paramString2)
  {
    if ((appView != null) && (appView.pluginManager != null)) {
      appView.pluginManager.addService(paramString1, paramString2);
    }
  }
  
  @Deprecated
  public boolean backHistory()
  {
    if (appView != null) {
      return appView.backHistory();
    }
    return false;
  }
  
  @Deprecated
  public void cancelLoadUrl() {}
  
  public void clearAuthenticationTokens()
  {
    if ((appView != null) && (appView.viewClient != null)) {
      appView.viewClient.clearAuthenticationTokens();
    }
  }
  
  @Deprecated
  public void clearCache()
  {
    if (appView == null) {
      init();
    }
    appView.clearCache(true);
  }
  
  @Deprecated
  public void clearHistory()
  {
    appView.clearHistory();
  }
  
  protected void createViews()
  {
    LOG.d(TAG, "CordovaActivity.createViews()");
    Object localObject = getWindowManager().getDefaultDisplay();
    root = new LinearLayoutSoftKeyboardDetect(this, ((Display)localObject).getWidth(), ((Display)localObject).getHeight());
    root.setOrientation(1);
    root.setLayoutParams(new LinearLayout.LayoutParams(-1, -1, 0.0F));
    appView.setId(100);
    appView.setLayoutParams(new LinearLayout.LayoutParams(-1, -1, 1.0F));
    appView.setVisibility(4);
    localObject = appView.getParent();
    if ((localObject != null) && (localObject != root))
    {
      LOG.d(TAG, "removing appView from existing parent");
      ((ViewGroup)localObject).removeView(appView);
    }
    root.addView(appView);
    setContentView(root);
    int i = preferences.getInteger("BackgroundColor", -16777216);
    root.setBackgroundColor(i);
  }
  
  public void displayError(final String paramString1, final String paramString2, final String paramString3, final boolean paramBoolean)
  {
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        try
        {
          AlertDialog.Builder localBuilder = new AlertDialog.Builder(jdField_this);
          localBuilder.setMessage(paramString2);
          localBuilder.setTitle(paramString1);
          localBuilder.setCancelable(false);
          localBuilder.setPositiveButton(paramString3, new DialogInterface.OnClickListener()
          {
            public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
            {
              paramAnonymous2DialogInterface.dismiss();
              if (val$exit) {
                val$me.endActivity();
              }
            }
          });
          localBuilder.create();
          localBuilder.show();
          return;
        }
        catch (Exception localException)
        {
          finish();
        }
      }
    });
  }
  
  public void endActivity()
  {
    activityState = ACTIVITY_EXITING;
    super.finish();
  }
  
  public Activity getActivity()
  {
    return this;
  }
  
  public AuthenticationToken getAuthenticationToken(String paramString1, String paramString2)
  {
    if ((appView != null) && (appView.viewClient != null)) {
      return appView.viewClient.getAuthenticationToken(paramString1, paramString2);
    }
    return null;
  }
  
  @Deprecated
  public boolean getBooleanProperty(String paramString, boolean paramBoolean)
  {
    return preferences.getBoolean(paramString, paramBoolean);
  }
  
  @Deprecated
  public Context getContext()
  {
    LOG.d(TAG, "This will be deprecated December 2012");
    return this;
  }
  
  @Deprecated
  public double getDoubleProperty(String paramString, double paramDouble)
  {
    return preferences.getDouble(paramString, paramDouble);
  }
  
  @Deprecated
  public int getIntegerProperty(String paramString, int paramInt)
  {
    return preferences.getInteger(paramString, paramInt);
  }
  
  @Deprecated
  public String getStringProperty(String paramString1, String paramString2)
  {
    return preferences.getString(paramString1, paramString2);
  }
  
  public ExecutorService getThreadPool()
  {
    return threadPool;
  }
  
  public void init()
  {
    init(appView, null, null);
  }
  
  @Deprecated
  @SuppressLint({"NewApi"})
  public void init(CordovaWebView paramCordovaWebView, CordovaWebViewClient paramCordovaWebViewClient, CordovaChromeClient paramCordovaChromeClient)
  {
    LOG.d(TAG, "CordovaActivity.init()");
    CordovaWebView localCordovaWebView;
    if (paramCordovaWebView != null)
    {
      appView = paramCordovaWebView;
      if (appView.pluginManager == null)
      {
        localCordovaWebView = appView;
        if (paramCordovaWebViewClient == null) {
          break label108;
        }
        paramCordovaWebView = paramCordovaWebViewClient;
        label40:
        if (paramCordovaChromeClient == null) {
          break label120;
        }
      }
    }
    for (;;)
    {
      localCordovaWebView.init(this, paramCordovaWebView, paramCordovaChromeClient, pluginEntries, internalWhitelist, externalWhitelist, preferences);
      if (preferences.getBoolean("DisallowOverscroll", false)) {
        appView.setOverScrollMode(2);
      }
      createViews();
      setVolumeControlStream(3);
      return;
      paramCordovaWebView = makeWebView();
      break;
      label108:
      paramCordovaWebView = makeWebViewClient(appView);
      break label40;
      label120:
      paramCordovaChromeClient = makeChromeClient(appView);
    }
  }
  
  @Deprecated
  public boolean isUrlWhiteListed(String paramString)
  {
    return internalWhitelist.isUrlWhiteListed(paramString);
  }
  
  protected void loadConfig()
  {
    ConfigXmlParser localConfigXmlParser = new ConfigXmlParser();
    localConfigXmlParser.parse(this);
    preferences = localConfigXmlParser.getPreferences();
    preferences.setPreferencesBundle(getIntent().getExtras());
    preferences.copyIntoIntentExtras(this);
    internalWhitelist = localConfigXmlParser.getInternalWhitelist();
    externalWhitelist = localConfigXmlParser.getExternalWhitelist();
    launchUrl = localConfigXmlParser.getLaunchUrl();
    pluginEntries = localConfigXmlParser.getPluginEntries();
    Config.parser = localConfigXmlParser;
  }
  
  void loadSpinner()
  {
    String str1;
    String str3;
    int i;
    if ((appView == null) || (!appView.canGoBack()))
    {
      str1 = preferences.getString("LoadingDialog", null);
      if (str1 != null)
      {
        str3 = "";
        str2 = "Loading Application...";
        if (str1.length() > 0)
        {
          i = str1.indexOf(',');
          if (i <= 0) {
            break label99;
          }
          str3 = str1.substring(0, i);
        }
      }
    }
    for (String str2 = str1.substring(i + 1);; str2 = str1)
    {
      spinnerStart(str3, str2);
      return;
      str1 = preferences.getString("LoadingPageDialog", null);
      break;
      label99:
      str3 = "";
    }
  }
  
  public void loadUrl(String paramString)
  {
    if (appView == null) {
      init();
    }
    splashscreenTime = preferences.getInteger("SplashScreenDelay", splashscreenTime);
    String str = preferences.getString("SplashScreen", null);
    if ((splashscreenTime > 0) && (str != null))
    {
      splashscreen = getResources().getIdentifier(str, "drawable", getClass().getPackage().getName());
      if (splashscreen != 0) {
        showSplashScreen(splashscreenTime);
      }
    }
    keepRunning = preferences.getBoolean("KeepRunning", true);
    if (appView.getParent() != null) {
      loadSpinner();
    }
    if (splashscreen != 0)
    {
      appView.loadUrl(paramString, splashscreenTime);
      return;
    }
    appView.loadUrl(paramString);
  }
  
  public void loadUrl(String paramString, int paramInt)
  {
    splashscreenTime = paramInt;
    loadUrl(paramString);
  }
  
  protected CordovaChromeClient makeChromeClient(CordovaWebView paramCordovaWebView)
  {
    return paramCordovaWebView.makeWebChromeClient(this);
  }
  
  protected CordovaWebView makeWebView()
  {
    return new CordovaWebView(this);
  }
  
  protected CordovaWebViewClient makeWebViewClient(CordovaWebView paramCordovaWebView)
  {
    return paramCordovaWebView.makeWebViewClient(this);
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    LOG.d(TAG, "Incoming Result");
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    Log.d(TAG, "Request code = " + paramInt1);
    Object localObject2;
    if ((appView != null) && (paramInt1 == 5173))
    {
      localObject2 = appView.getWebChromeClient().getValueCallback();
      Log.d(TAG, "did we get here?");
      if (localObject2 == null) {
        return;
      }
      if ((paramIntent != null) && (paramInt2 == -1)) {
        break label207;
      }
    }
    label207:
    for (Object localObject1 = null;; localObject1 = paramIntent.getData())
    {
      Log.d(TAG, "result = " + localObject1);
      ((ValueCallback)localObject2).onReceiveValue(localObject1);
      localObject2 = activityResultCallback;
      localObject1 = localObject2;
      if (localObject2 == null)
      {
        localObject1 = localObject2;
        if (initCallbackClass != null)
        {
          activityResultCallback = appView.pluginManager.getPlugin(initCallbackClass);
          localObject1 = activityResultCallback;
        }
      }
      if (localObject1 == null) {
        break;
      }
      LOG.d(TAG, "We have a callback to send this result to");
      ((CordovaPlugin)localObject1).onActivityResult(paramInt1, paramInt2, paramIntent);
      return;
    }
  }
  
  public void onCreate(Bundle paramBundle)
  {
    LOG.i(TAG, "Apache Cordova native platform version 3.6.4 is starting");
    LOG.d(TAG, "CordovaActivity.onCreate()");
    loadConfig();
    if (!preferences.getBoolean("ShowTitle", false)) {
      getWindow().requestFeature(1);
    }
    if (preferences.getBoolean("SetFullscreen", false))
    {
      Log.d(TAG, "The SetFullscreen configuration is deprecated in favor of Fullscreen, and will be removed in a future version.");
      getWindow().setFlags(1024, 1024);
    }
    for (;;)
    {
      super.onCreate(paramBundle);
      if (paramBundle != null) {
        initCallbackClass = paramBundle.getString("callbackClass");
      }
      return;
      if (preferences.getBoolean("Fullscreen", false)) {
        getWindow().setFlags(1024, 1024);
      } else {
        getWindow().setFlags(2048, 2048);
      }
    }
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    postMessage("onCreateOptionsMenu", paramMenu);
    return super.onCreateOptionsMenu(paramMenu);
  }
  
  public void onDestroy()
  {
    LOG.d(TAG, "CordovaActivity.onDestroy()");
    super.onDestroy();
    removeSplashScreen();
    if (appView != null)
    {
      appView.handleDestroy();
      return;
    }
    activityState = ACTIVITY_EXITING;
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if ((appView != null) && (appView.getFocusedChild() != null) && ((paramInt == 4) || (paramInt == 82))) {
      return appView.onKeyDown(paramInt, paramKeyEvent);
    }
    return super.onKeyDown(paramInt, paramKeyEvent);
  }
  
  public boolean onKeyUp(int paramInt, KeyEvent paramKeyEvent)
  {
    if ((appView != null) && ((appView.isCustomViewShowing()) || (appView.getFocusedChild() != null)) && ((paramInt == 4) || (paramInt == 82))) {
      return appView.onKeyUp(paramInt, paramKeyEvent);
    }
    return super.onKeyUp(paramInt, paramKeyEvent);
  }
  
  public Object onMessage(String paramString, Object paramObject)
  {
    if (!"onScrollChanged".equals(paramString)) {
      LOG.d(TAG, "onMessage(" + paramString + "," + paramObject + ")");
    }
    if ("splashscreen".equals(paramString)) {
      if ("hide".equals(paramObject.toString())) {
        removeSplashScreen();
      }
    }
    do
    {
      do
      {
        do
        {
          return null;
        } while ((splashDialog != null) && (splashDialog.isShowing()));
        paramString = preferences.getString("SplashScreen", null);
        if (paramString != null) {
          splashscreen = getResources().getIdentifier(paramString, "drawable", getClass().getPackage().getName());
        }
        showSplashScreen(splashscreenTime);
        return null;
        if (!"spinner".equals(paramString)) {
          break;
        }
      } while (!"stop".equals(paramObject.toString()));
      spinnerStop();
      appView.setVisibility(0);
      return null;
      if ("onReceivedError".equals(paramString))
      {
        paramString = (JSONObject)paramObject;
        try
        {
          onReceivedError(paramString.getInt("errorCode"), paramString.getString("description"), paramString.getString("url"));
          return null;
        }
        catch (JSONException paramString)
        {
          paramString.printStackTrace();
          return null;
        }
      }
    } while (!"exit".equals(paramString));
    endActivity();
    return null;
  }
  
  protected void onNewIntent(Intent paramIntent)
  {
    super.onNewIntent(paramIntent);
    if (appView != null) {
      appView.onNewIntent(paramIntent);
    }
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    postMessage("onOptionsItemSelected", paramMenuItem);
    return true;
  }
  
  protected void onPause()
  {
    super.onPause();
    LOG.d(TAG, "Paused the application!");
    if (activityState == ACTIVITY_EXITING) {}
    while (appView == null) {
      return;
    }
    appView.handlePause(keepRunning);
    removeSplashScreen();
  }
  
  public boolean onPrepareOptionsMenu(Menu paramMenu)
  {
    postMessage("onPrepareOptionsMenu", paramMenu);
    return true;
  }
  
  public void onReceivedError(int paramInt, final String paramString1, final String paramString2)
  {
    final String str = preferences.getString("errorUrl", null);
    if ((str != null) && ((str.startsWith("file://")) || (internalWhitelist.isUrlWhiteListed(str))) && (!paramString2.equals(str)))
    {
      runOnUiThread(new Runnable()
      {
        public void run()
        {
          jdField_this.spinnerStop();
          jdField_thisappView.showWebPage(str, false, true, null);
        }
      });
      return;
    }
    if (paramInt != -2) {}
    for (final boolean bool = true;; bool = false)
    {
      runOnUiThread(new Runnable()
      {
        public void run()
        {
          if (bool)
          {
            jdField_thisappView.setVisibility(8);
            jdField_this.displayError("Application Error", paramString1 + " (" + paramString2 + ")", "OK", bool);
          }
        }
      });
      return;
    }
  }
  
  protected void onResume()
  {
    super.onResume();
    LOG.d(TAG, "Resuming the App");
    if (activityState == ACTIVITY_STARTING) {
      activityState = ACTIVITY_RUNNING;
    }
    do
    {
      do
      {
        return;
      } while (appView == null);
      getWindow().getDecorView().requestFocus();
      appView.handleResume(keepRunning, activityResultKeepRunning);
    } while (((keepRunning) && (!activityResultKeepRunning)) || (!activityResultKeepRunning));
    keepRunning = activityResultKeepRunning;
    activityResultKeepRunning = false;
  }
  
  protected void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    if (activityResultCallback != null) {
      paramBundle.putString("callbackClass", activityResultCallback.getClass().getName());
    }
  }
  
  public void postMessage(String paramString, Object paramObject)
  {
    if (appView != null) {
      appView.postMessage(paramString, paramObject);
    }
  }
  
  public AuthenticationToken removeAuthenticationToken(String paramString1, String paramString2)
  {
    if ((appView != null) && (appView.viewClient != null)) {
      return appView.viewClient.removeAuthenticationToken(paramString1, paramString2);
    }
    return null;
  }
  
  public void removeSplashScreen()
  {
    if ((splashDialog != null) && (splashDialog.isShowing()))
    {
      splashDialog.dismiss();
      splashDialog = null;
    }
  }
  
  @Deprecated
  public void sendJavascript(String paramString)
  {
    if (appView != null) {
      appView.bridge.getMessageQueue().addJavaScript(paramString);
    }
  }
  
  public void setActivityResultCallback(CordovaPlugin paramCordovaPlugin)
  {
    activityResultCallback = paramCordovaPlugin;
  }
  
  public void setAuthenticationToken(AuthenticationToken paramAuthenticationToken, String paramString1, String paramString2)
  {
    if ((appView != null) && (appView.viewClient != null)) {
      appView.viewClient.setAuthenticationToken(paramAuthenticationToken, paramString1, paramString2);
    }
  }
  
  @Deprecated
  public void setBooleanProperty(String paramString, boolean paramBoolean)
  {
    Log.d(TAG, "Setting boolean properties in CordovaActivity will be deprecated in 3.0 on July 2013, please use config.xml");
    getIntent().putExtra(paramString.toLowerCase(), paramBoolean);
  }
  
  @Deprecated
  public void setDoubleProperty(String paramString, double paramDouble)
  {
    Log.d(TAG, "Setting double properties in CordovaActivity will be deprecated in 3.0 on July 2013, please use config.xml");
    getIntent().putExtra(paramString.toLowerCase(), paramDouble);
  }
  
  @Deprecated
  public void setIntegerProperty(String paramString, int paramInt)
  {
    Log.d(TAG, "Setting integer properties in CordovaActivity will be deprecated in 3.0 on July 2013, please use config.xml");
    getIntent().putExtra(paramString.toLowerCase(), paramInt);
  }
  
  @Deprecated
  public void setStringProperty(String paramString1, String paramString2)
  {
    Log.d(TAG, "Setting string properties in CordovaActivity will be deprecated in 3.0 on July 2013, please use config.xml");
    getIntent().putExtra(paramString1.toLowerCase(), paramString2);
  }
  
  protected void showSplashScreen(final int paramInt)
  {
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        Display localDisplay = getWindowManager().getDefaultDisplay();
        LinearLayout localLinearLayout = new LinearLayout(jdField_this.getActivity());
        localLinearLayout.setMinimumHeight(localDisplay.getHeight());
        localLinearLayout.setMinimumWidth(localDisplay.getWidth());
        localLinearLayout.setOrientation(1);
        localLinearLayout.setBackgroundColor(preferences.getInteger("backgroundColor", -16777216));
        localLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -1, 0.0F));
        localLinearLayout.setBackgroundResource(jdField_thissplashscreen);
        splashDialog = new Dialog(jdField_this, 16973840);
        if ((getWindow().getAttributes().flags & 0x400) == 1024) {
          splashDialog.getWindow().setFlags(1024, 1024);
        }
        splashDialog.setContentView(localLinearLayout);
        splashDialog.setCancelable(false);
        splashDialog.show();
        new Handler().postDelayed(new Runnable()
        {
          public void run()
          {
            removeSplashScreen();
          }
        }, paramInt);
      }
    });
  }
  
  @Deprecated
  public void showWebPage(String paramString, boolean paramBoolean1, boolean paramBoolean2, HashMap<String, Object> paramHashMap)
  {
    if (appView != null) {
      appView.showWebPage(paramString, paramBoolean1, paramBoolean2, paramHashMap);
    }
  }
  
  public void spinnerStart(String paramString1, String paramString2)
  {
    if (spinnerDialog != null)
    {
      spinnerDialog.dismiss();
      spinnerDialog = null;
    }
    spinnerDialog = ProgressDialog.show(this, paramString1, paramString2, true, true, new DialogInterface.OnCancelListener()
    {
      public void onCancel(DialogInterface paramAnonymousDialogInterface)
      {
        jdField_thisspinnerDialog = null;
      }
    });
  }
  
  public void spinnerStop()
  {
    if ((spinnerDialog != null) && (spinnerDialog.isShowing()))
    {
      spinnerDialog.dismiss();
      spinnerDialog = null;
    }
  }
  
  public void startActivityForResult(CordovaPlugin paramCordovaPlugin, Intent paramIntent, int paramInt)
  {
    activityResultCallback = paramCordovaPlugin;
    activityResultKeepRunning = keepRunning;
    if (paramCordovaPlugin != null) {
      keepRunning = false;
    }
    super.startActivityForResult(paramIntent, paramInt);
  }
}

/* Location:
 * Qualified Name:     org.apache.cordova.CordovaActivity
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */