package org.apache.cordova;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebBackForwardList;
import android.webkit.WebChromeClient.CustomViewCallback;
import android.webkit.WebHistoryItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout.LayoutParams;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.concurrent.ExecutorService;

public class CordovaWebView
  extends WebView
{
  public static final String CORDOVA_VERSION = "3.5.0";
  static final FrameLayout.LayoutParams COVER_SCREEN_GRAVITY_CENTER = new FrameLayout.LayoutParams(-1, -1, 17);
  public static final String TAG = "CordovaWebView";
  private boolean bound;
  private CordovaChromeClient chromeClient;
  private CordovaInterface cordova;
  ExposedJsApi exposedJsApi;
  private boolean handleButton = false;
  NativeToJsMessageQueue jsMessageQueue;
  private ArrayList<Integer> keyDownCodes = new ArrayList();
  private ArrayList<Integer> keyUpCodes = new ArrayList();
  private long lastMenuEventTime = 0L;
  int loadUrlTimeout = 0;
  private View mCustomView;
  private WebChromeClient.CustomViewCallback mCustomViewCallback;
  private ActivityResult mResult = null;
  private boolean paused;
  public PluginManager pluginManager;
  private BroadcastReceiver receiver;
  private CordovaResourceApi resourceApi;
  private String url;
  CordovaWebViewClient viewClient;
  
  public CordovaWebView(Context paramContext)
  {
    super(paramContext);
    if (CordovaInterface.class.isInstance(paramContext)) {
      cordova = ((CordovaInterface)paramContext);
    }
    for (;;)
    {
      loadConfiguration();
      setup();
      return;
      Log.d("CordovaWebView", "Your activity must implement CordovaInterface to work");
    }
  }
  
  public CordovaWebView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    if (CordovaInterface.class.isInstance(paramContext)) {
      cordova = ((CordovaInterface)paramContext);
    }
    for (;;)
    {
      setWebChromeClient(new CordovaChromeClient(cordova, this));
      initWebViewClient(cordova);
      loadConfiguration();
      setup();
      return;
      Log.d("CordovaWebView", "Your activity must implement CordovaInterface to work");
    }
  }
  
  public CordovaWebView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    if (CordovaInterface.class.isInstance(paramContext)) {
      cordova = ((CordovaInterface)paramContext);
    }
    for (;;)
    {
      setWebChromeClient(new CordovaChromeClient(cordova, this));
      loadConfiguration();
      setup();
      return;
      Log.d("CordovaWebView", "Your activity must implement CordovaInterface to work");
    }
  }
  
  @TargetApi(11)
  public CordovaWebView(Context paramContext, AttributeSet paramAttributeSet, int paramInt, boolean paramBoolean)
  {
    super(paramContext, paramAttributeSet, paramInt, paramBoolean);
    if (CordovaInterface.class.isInstance(paramContext)) {
      cordova = ((CordovaInterface)paramContext);
    }
    for (;;)
    {
      setWebChromeClient(new CordovaChromeClient(cordova));
      initWebViewClient(cordova);
      loadConfiguration();
      setup();
      return;
      Log.d("CordovaWebView", "Your activity must implement CordovaInterface to work");
    }
  }
  
  private void exposeJsInterface()
  {
    if (Build.VERSION.SDK_INT < 17)
    {
      Log.i("CordovaWebView", "Disabled addJavascriptInterface() bridge since Android version is old.");
      return;
    }
    addJavascriptInterface(exposedJsApi, "_cordovaNative");
  }
  
  private void initWebViewClient(CordovaInterface paramCordovaInterface)
  {
    if ((Build.VERSION.SDK_INT < 11) || (Build.VERSION.SDK_INT > 17))
    {
      setWebViewClient(new CordovaWebViewClient(cordova, this));
      return;
    }
    setWebViewClient(new IceCreamCordovaWebViewClient(cordova, this));
  }
  
  private void loadConfiguration()
  {
    if ("true".equals(getProperty("Fullscreen", "false")))
    {
      cordova.getActivity().getWindow().clearFlags(2048);
      cordova.getActivity().getWindow().setFlags(1024, 1024);
    }
  }
  
  /* Error */
  @android.annotation.SuppressLint({"NewApi"})
  private void setup()
  {
    // Byte code:
    //   0: aload_0
    //   1: iconst_0
    //   2: invokevirtual 230	org/apache/cordova/CordovaWebView:setInitialScale	(I)V
    //   5: aload_0
    //   6: iconst_0
    //   7: invokevirtual 234	org/apache/cordova/CordovaWebView:setVerticalScrollBarEnabled	(Z)V
    //   10: aload_0
    //   11: invokevirtual 238	org/apache/cordova/CordovaWebView:shouldRequestFocusOnInit	()Z
    //   14: ifeq +8 -> 22
    //   17: aload_0
    //   18: invokevirtual 241	org/apache/cordova/CordovaWebView:requestFocusFromTouch	()Z
    //   21: pop
    //   22: aload_0
    //   23: invokevirtual 245	org/apache/cordova/CordovaWebView:getSettings	()Landroid/webkit/WebSettings;
    //   26: astore_1
    //   27: aload_1
    //   28: iconst_1
    //   29: invokevirtual 250	android/webkit/WebSettings:setJavaScriptEnabled	(Z)V
    //   32: aload_1
    //   33: iconst_1
    //   34: invokevirtual 253	android/webkit/WebSettings:setJavaScriptCanOpenWindowsAutomatically	(Z)V
    //   37: aload_1
    //   38: getstatic 259	android/webkit/WebSettings$LayoutAlgorithm:NORMAL	Landroid/webkit/WebSettings$LayoutAlgorithm;
    //   41: invokevirtual 263	android/webkit/WebSettings:setLayoutAlgorithm	(Landroid/webkit/WebSettings$LayoutAlgorithm;)V
    //   44: ldc -9
    //   46: ldc_w 265
    //   49: iconst_1
    //   50: anewarray 96	java/lang/Class
    //   53: dup
    //   54: iconst_0
    //   55: getstatic 271	java/lang/Boolean:TYPE	Ljava/lang/Class;
    //   58: aastore
    //   59: invokevirtual 275	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   62: astore_2
    //   63: getstatic 280	android/os/Build:MANUFACTURER	Ljava/lang/String;
    //   66: astore_3
    //   67: ldc 27
    //   69: new 282	java/lang/StringBuilder
    //   72: dup
    //   73: ldc_w 284
    //   76: invokespecial 287	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   79: aload_3
    //   80: invokevirtual 291	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   83: invokevirtual 295	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   86: invokestatic 116	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   89: pop
    //   90: getstatic 157	android/os/Build$VERSION:SDK_INT	I
    //   93: bipush 11
    //   95: if_icmpge +32 -> 127
    //   98: getstatic 280	android/os/Build:MANUFACTURER	Ljava/lang/String;
    //   101: ldc_w 297
    //   104: invokevirtual 301	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   107: ifeq +20 -> 127
    //   110: aload_2
    //   111: aload_1
    //   112: iconst_1
    //   113: anewarray 303	java/lang/Object
    //   116: dup
    //   117: iconst_0
    //   118: iconst_1
    //   119: invokestatic 307	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   122: aastore
    //   123: invokevirtual 313	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   126: pop
    //   127: aload_1
    //   128: iconst_0
    //   129: invokevirtual 316	android/webkit/WebSettings:setSaveFormData	(Z)V
    //   132: aload_1
    //   133: iconst_0
    //   134: invokevirtual 319	android/webkit/WebSettings:setSavePassword	(Z)V
    //   137: getstatic 157	android/os/Build$VERSION:SDK_INT	I
    //   140: bipush 15
    //   142: if_icmple +7 -> 149
    //   145: aload_1
    //   146: invokestatic 323	org/apache/cordova/CordovaWebView$Level16Apis:enableUniversalAccess	(Landroid/webkit/WebSettings;)V
    //   149: aload_0
    //   150: getfield 102	org/apache/cordova/CordovaWebView:cordova	Lorg/apache/cordova/CordovaInterface;
    //   153: invokeinterface 199 1 0
    //   158: invokevirtual 327	android/app/Activity:getApplicationContext	()Landroid/content/Context;
    //   161: ldc_w 329
    //   164: iconst_0
    //   165: invokevirtual 335	android/content/Context:getDir	(Ljava/lang/String;I)Ljava/io/File;
    //   168: invokevirtual 340	java/io/File:getPath	()Ljava/lang/String;
    //   171: astore_2
    //   172: aload_1
    //   173: iconst_1
    //   174: invokevirtual 343	android/webkit/WebSettings:setDatabaseEnabled	(Z)V
    //   177: aload_1
    //   178: aload_2
    //   179: invokevirtual 346	android/webkit/WebSettings:setDatabasePath	(Ljava/lang/String;)V
    //   182: aload_0
    //   183: getfield 102	org/apache/cordova/CordovaWebView:cordova	Lorg/apache/cordova/CordovaInterface;
    //   186: invokeinterface 199 1 0
    //   191: invokevirtual 349	android/app/Activity:getPackageName	()Ljava/lang/String;
    //   194: astore_3
    //   195: aload_0
    //   196: getfield 102	org/apache/cordova/CordovaWebView:cordova	Lorg/apache/cordova/CordovaInterface;
    //   199: invokeinterface 199 1 0
    //   204: invokevirtual 353	android/app/Activity:getPackageManager	()Landroid/content/pm/PackageManager;
    //   207: aload_3
    //   208: sipush 128
    //   211: invokevirtual 359	android/content/pm/PackageManager:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
    //   214: getfield 364	android/content/pm/ApplicationInfo:flags	I
    //   217: iconst_2
    //   218: iand
    //   219: ifeq +15 -> 234
    //   222: getstatic 157	android/os/Build$VERSION:SDK_INT	I
    //   225: bipush 19
    //   227: if_icmplt +7 -> 234
    //   230: iconst_1
    //   231: invokestatic 367	org/apache/cordova/CordovaWebView:setWebContentsDebuggingEnabled	(Z)V
    //   234: aload_1
    //   235: aload_2
    //   236: invokevirtual 370	android/webkit/WebSettings:setGeolocationDatabasePath	(Ljava/lang/String;)V
    //   239: aload_1
    //   240: iconst_1
    //   241: invokevirtual 373	android/webkit/WebSettings:setDomStorageEnabled	(Z)V
    //   244: aload_1
    //   245: iconst_1
    //   246: invokevirtual 376	android/webkit/WebSettings:setGeolocationEnabled	(Z)V
    //   249: aload_1
    //   250: ldc2_w 377
    //   253: invokevirtual 382	android/webkit/WebSettings:setAppCacheMaxSize	(J)V
    //   256: aload_1
    //   257: aload_0
    //   258: getfield 102	org/apache/cordova/CordovaWebView:cordova	Lorg/apache/cordova/CordovaInterface;
    //   261: invokeinterface 199 1 0
    //   266: invokevirtual 327	android/app/Activity:getApplicationContext	()Landroid/content/Context;
    //   269: ldc_w 329
    //   272: iconst_0
    //   273: invokevirtual 335	android/content/Context:getDir	(Ljava/lang/String;I)Ljava/io/File;
    //   276: invokevirtual 340	java/io/File:getPath	()Ljava/lang/String;
    //   279: invokevirtual 385	android/webkit/WebSettings:setAppCachePath	(Ljava/lang/String;)V
    //   282: aload_1
    //   283: iconst_1
    //   284: invokevirtual 388	android/webkit/WebSettings:setAppCacheEnabled	(Z)V
    //   287: aload_0
    //   288: invokespecial 149	org/apache/cordova/CordovaWebView:updateUserAgentString	()V
    //   291: new 390	android/content/IntentFilter
    //   294: dup
    //   295: invokespecial 391	android/content/IntentFilter:<init>	()V
    //   298: astore_1
    //   299: aload_1
    //   300: ldc_w 393
    //   303: invokevirtual 396	android/content/IntentFilter:addAction	(Ljava/lang/String;)V
    //   306: aload_0
    //   307: getfield 398	org/apache/cordova/CordovaWebView:receiver	Landroid/content/BroadcastReceiver;
    //   310: ifnonnull +33 -> 343
    //   313: aload_0
    //   314: new 6	org/apache/cordova/CordovaWebView$1
    //   317: dup
    //   318: aload_0
    //   319: invokespecial 400	org/apache/cordova/CordovaWebView$1:<init>	(Lorg/apache/cordova/CordovaWebView;)V
    //   322: putfield 398	org/apache/cordova/CordovaWebView:receiver	Landroid/content/BroadcastReceiver;
    //   325: aload_0
    //   326: getfield 102	org/apache/cordova/CordovaWebView:cordova	Lorg/apache/cordova/CordovaInterface;
    //   329: invokeinterface 199 1 0
    //   334: aload_0
    //   335: getfield 398	org/apache/cordova/CordovaWebView:receiver	Landroid/content/BroadcastReceiver;
    //   338: aload_1
    //   339: invokevirtual 404	android/app/Activity:registerReceiver	(Landroid/content/BroadcastReceiver;Landroid/content/IntentFilter;)Landroid/content/Intent;
    //   342: pop
    //   343: aload_0
    //   344: new 406	org/apache/cordova/PluginManager
    //   347: dup
    //   348: aload_0
    //   349: aload_0
    //   350: getfield 102	org/apache/cordova/CordovaWebView:cordova	Lorg/apache/cordova/CordovaInterface;
    //   353: invokespecial 409	org/apache/cordova/PluginManager:<init>	(Lorg/apache/cordova/CordovaWebView;Lorg/apache/cordova/CordovaInterface;)V
    //   356: putfield 411	org/apache/cordova/CordovaWebView:pluginManager	Lorg/apache/cordova/PluginManager;
    //   359: aload_0
    //   360: new 413	org/apache/cordova/NativeToJsMessageQueue
    //   363: dup
    //   364: aload_0
    //   365: aload_0
    //   366: getfield 102	org/apache/cordova/CordovaWebView:cordova	Lorg/apache/cordova/CordovaInterface;
    //   369: invokespecial 414	org/apache/cordova/NativeToJsMessageQueue:<init>	(Lorg/apache/cordova/CordovaWebView;Lorg/apache/cordova/CordovaInterface;)V
    //   372: putfield 416	org/apache/cordova/CordovaWebView:jsMessageQueue	Lorg/apache/cordova/NativeToJsMessageQueue;
    //   375: aload_0
    //   376: new 418	org/apache/cordova/ExposedJsApi
    //   379: dup
    //   380: aload_0
    //   381: getfield 411	org/apache/cordova/CordovaWebView:pluginManager	Lorg/apache/cordova/PluginManager;
    //   384: aload_0
    //   385: getfield 416	org/apache/cordova/CordovaWebView:jsMessageQueue	Lorg/apache/cordova/NativeToJsMessageQueue;
    //   388: invokespecial 421	org/apache/cordova/ExposedJsApi:<init>	(Lorg/apache/cordova/PluginManager;Lorg/apache/cordova/NativeToJsMessageQueue;)V
    //   391: putfield 164	org/apache/cordova/CordovaWebView:exposedJsApi	Lorg/apache/cordova/ExposedJsApi;
    //   394: aload_0
    //   395: new 423	org/apache/cordova/CordovaResourceApi
    //   398: dup
    //   399: aload_0
    //   400: invokevirtual 426	org/apache/cordova/CordovaWebView:getContext	()Landroid/content/Context;
    //   403: aload_0
    //   404: getfield 411	org/apache/cordova/CordovaWebView:pluginManager	Lorg/apache/cordova/PluginManager;
    //   407: invokespecial 429	org/apache/cordova/CordovaResourceApi:<init>	(Landroid/content/Context;Lorg/apache/cordova/PluginManager;)V
    //   410: putfield 431	org/apache/cordova/CordovaWebView:resourceApi	Lorg/apache/cordova/CordovaResourceApi;
    //   413: aload_0
    //   414: invokespecial 433	org/apache/cordova/CordovaWebView:exposeJsInterface	()V
    //   417: return
    //   418: astore_2
    //   419: ldc 27
    //   421: ldc_w 435
    //   424: invokestatic 116	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   427: pop
    //   428: goto -301 -> 127
    //   431: astore_2
    //   432: ldc 27
    //   434: ldc_w 437
    //   437: invokestatic 116	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   440: pop
    //   441: goto -314 -> 127
    //   444: astore_2
    //   445: ldc 27
    //   447: ldc_w 439
    //   450: invokestatic 116	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   453: pop
    //   454: goto -327 -> 127
    //   457: astore_2
    //   458: ldc 27
    //   460: ldc_w 441
    //   463: invokestatic 116	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   466: pop
    //   467: goto -340 -> 127
    //   470: astore_3
    //   471: ldc 27
    //   473: ldc_w 443
    //   476: invokestatic 116	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   479: pop
    //   480: aload_3
    //   481: invokevirtual 446	java/lang/IllegalArgumentException:printStackTrace	()V
    //   484: goto -250 -> 234
    //   487: astore_3
    //   488: ldc 27
    //   490: ldc_w 448
    //   493: invokestatic 116	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   496: pop
    //   497: aload_3
    //   498: invokevirtual 449	android/content/pm/PackageManager$NameNotFoundException:printStackTrace	()V
    //   501: goto -267 -> 234
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	504	0	this	CordovaWebView
    //   26	313	1	localObject1	Object
    //   62	174	2	localObject2	Object
    //   418	1	2	localNoSuchMethodException	NoSuchMethodException
    //   431	1	2	localIllegalArgumentException1	IllegalArgumentException
    //   444	1	2	localIllegalAccessException	IllegalAccessException
    //   457	1	2	localInvocationTargetException	java.lang.reflect.InvocationTargetException
    //   66	142	3	str	String
    //   470	11	3	localIllegalArgumentException2	IllegalArgumentException
    //   487	11	3	localNameNotFoundException	android.content.pm.PackageManager.NameNotFoundException
    // Exception table:
    //   from	to	target	type
    //   44	127	418	java/lang/NoSuchMethodException
    //   44	127	431	java/lang/IllegalArgumentException
    //   44	127	444	java/lang/IllegalAccessException
    //   44	127	457	java/lang/reflect/InvocationTargetException
    //   182	234	470	java/lang/IllegalArgumentException
    //   182	234	487	android/content/pm/PackageManager$NameNotFoundException
  }
  
  private void updateUserAgentString()
  {
    getSettings().getUserAgentString();
  }
  
  public boolean backHistory()
  {
    if (super.canGoBack())
    {
      printBackForwardList();
      super.goBack();
      return true;
    }
    return false;
  }
  
  public void bindButton(int paramInt, boolean paramBoolean1, boolean paramBoolean2)
  {
    if (paramBoolean1)
    {
      keyDownCodes.add(Integer.valueOf(paramInt));
      return;
    }
    keyUpCodes.add(Integer.valueOf(paramInt));
  }
  
  public void bindButton(String paramString, boolean paramBoolean)
  {
    if (paramString.compareTo("volumeup") == 0) {
      keyDownCodes.add(Integer.valueOf(24));
    }
    while (paramString.compareTo("volumedown") != 0) {
      return;
    }
    keyDownCodes.add(Integer.valueOf(25));
  }
  
  public void bindButton(boolean paramBoolean)
  {
    bound = paramBoolean;
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
  
  public boolean hadKeyEvent()
  {
    return handleButton;
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
      cordova.getActivity().unregisterReceiver(receiver);
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
  
  public boolean isBackButtonBound()
  {
    return bound;
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
    String str = getProperty("url", null);
    if (str == null)
    {
      loadUrlIntoView(paramString);
      return;
    }
    loadUrlIntoView(str);
  }
  
  public void loadUrl(String paramString, int paramInt)
  {
    String str = getProperty("url", null);
    if (str == null)
    {
      loadUrlIntoView(paramString, paramInt);
      return;
    }
    loadUrlIntoView(str);
  }
  
  public void loadUrlIntoView(String paramString)
  {
    loadUrlIntoView(paramString, true);
  }
  
  public void loadUrlIntoView(String paramString, int paramInt)
  {
    if ((!paramString.startsWith("javascript:")) && (!canGoBack()))
    {
      LOG.d("CordovaWebView", "loadUrlIntoView(%s, %d)", new Object[] { paramString, Integer.valueOf(paramInt) });
      postMessage("splashscreen", "show");
    }
    loadUrlIntoView(paramString);
  }
  
  public void loadUrlIntoView(final String paramString, boolean paramBoolean)
  {
    LOG.d("CordovaWebView", ">>> loadUrl(" + paramString + ")");
    if (paramBoolean)
    {
      url = paramString;
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
    if ((paramString.startsWith("file://")) || (paramString.startsWith("javascript:")) || (Config.isUrlWhiteListed(paramString))) {
      super.loadUrl(paramString);
    }
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if (keyDownCodes.contains(Integer.valueOf(paramInt))) {
      if (paramInt == 25)
      {
        LOG.d("CordovaWebView", "Down Key Hit");
        loadUrl("javascript:cordova.fireDocumentEvent('volumedownbutton');");
      }
    }
    do
    {
      return true;
      if (paramInt == 24)
      {
        LOG.d("CordovaWebView", "Up Key Hit");
        loadUrl("javascript:cordova.fireDocumentEvent('volumeupbutton');");
        return true;
      }
      return super.onKeyDown(paramInt, paramKeyEvent);
      if (paramInt != 4) {
        break;
      }
    } while ((!startOfHistory()) || (bound));
    return false;
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
    boolean bool = true;
    if (paramInt == 4) {
      if (mCustomView != null) {
        hideCustomView();
      }
    }
    do
    {
      for (;;)
      {
        bool = super.onKeyUp(paramInt, paramKeyEvent);
        do
        {
          return bool;
          if (bound)
          {
            loadUrl("javascript:cordova.fireDocumentEvent('backbutton');");
            return true;
          }
        } while (backHistory());
        cordova.getActivity().finish();
      }
      if (paramInt == 82)
      {
        if (lastMenuEventTime < paramKeyEvent.getEventTime()) {
          loadUrl("javascript:cordova.fireDocumentEvent('menubutton');");
        }
        lastMenuEventTime = paramKeyEvent.getEventTime();
        return super.onKeyUp(paramInt, paramKeyEvent);
      }
      if (paramInt == 84)
      {
        loadUrl("javascript:cordova.fireDocumentEvent('searchbutton');");
        return true;
      }
    } while (!keyUpCodes.contains(Integer.valueOf(paramInt)));
    return super.onKeyUp(paramInt, paramKeyEvent);
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
    for (;;)
    {
      if (i >= j) {
        return;
      }
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
  
  public void sendJavascript(String paramString)
  {
    jsMessageQueue.addJavaScript(paramString);
  }
  
  public void sendPluginResult(PluginResult paramPluginResult, String paramString)
  {
    jsMessageQueue.addPluginResult(paramPluginResult, paramString);
  }
  
  public void setWebChromeClient(CordovaChromeClient paramCordovaChromeClient)
  {
    chromeClient = paramCordovaChromeClient;
    super.setWebChromeClient(paramCordovaChromeClient);
  }
  
  public void setWebViewClient(CordovaWebViewClient paramCordovaWebViewClient)
  {
    viewClient = paramCordovaWebViewClient;
    super.setWebViewClient(paramCordovaWebViewClient);
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
      if ((paramString.startsWith("file://")) || (Config.isUrlWhiteListed(paramString)))
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
  
  public void storeResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    mResult = new ActivityResult(paramInt1, paramInt2, paramIntent);
  }
  
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