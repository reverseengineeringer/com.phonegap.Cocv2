package org.apache.cordova;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.webkit.HttpAuthHandler;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.util.Hashtable;
import org.json.JSONException;
import org.json.JSONObject;

public class CordovaWebViewClient
  extends WebViewClient
{
  private static final String TAG = "CordovaWebViewClient";
  CordovaWebView appView;
  private Hashtable<String, AuthenticationToken> authenticationTokens = new Hashtable();
  CordovaInterface cordova;
  private boolean doClearHistory = false;
  CordovaUriHelper helper;
  boolean isCurrentlyLoading;
  
  @Deprecated
  public CordovaWebViewClient(CordovaInterface paramCordovaInterface)
  {
    cordova = paramCordovaInterface;
  }
  
  public CordovaWebViewClient(CordovaInterface paramCordovaInterface, CordovaWebView paramCordovaWebView)
  {
    cordova = paramCordovaInterface;
    appView = paramCordovaWebView;
    helper = new CordovaUriHelper(paramCordovaInterface, paramCordovaWebView);
  }
  
  public void clearAuthenticationTokens()
  {
    authenticationTokens.clear();
  }
  
  public AuthenticationToken getAuthenticationToken(String paramString1, String paramString2)
  {
    AuthenticationToken localAuthenticationToken = (AuthenticationToken)authenticationTokens.get(paramString1.concat(paramString2));
    Object localObject = localAuthenticationToken;
    if (localAuthenticationToken == null)
    {
      localObject = (AuthenticationToken)authenticationTokens.get(paramString1);
      paramString1 = (String)localObject;
      if (localObject == null) {
        paramString1 = (AuthenticationToken)authenticationTokens.get(paramString2);
      }
      localObject = paramString1;
      if (paramString1 == null) {
        localObject = (AuthenticationToken)authenticationTokens.get("");
      }
    }
    return (AuthenticationToken)localObject;
  }
  
  public void onPageFinished(WebView paramWebView, String paramString)
  {
    super.onPageFinished(paramWebView, paramString);
    if (!isCurrentlyLoading) {}
    do
    {
      return;
      isCurrentlyLoading = false;
      LOG.d("CordovaWebViewClient", "onPageFinished(" + paramString + ")");
      if (doClearHistory)
      {
        paramWebView.clearHistory();
        doClearHistory = false;
      }
      paramWebView = appView;
      loadUrlTimeout += 1;
      appView.postMessage("onPageFinished", paramString);
      if (appView.getVisibility() == 4) {
        new Thread(new Runnable()
        {
          public void run()
          {
            try
            {
              Thread.sleep(2000L);
              cordova.getActivity().runOnUiThread(new Runnable()
              {
                public void run()
                {
                  appView.postMessage("spinner", "stop");
                }
              });
              return;
            }
            catch (InterruptedException localInterruptedException) {}
          }
        }).start();
      }
    } while (!paramString.equals("about:blank"));
    appView.postMessage("exit", null);
  }
  
  public void onPageStarted(WebView paramWebView, String paramString, Bitmap paramBitmap)
  {
    super.onPageStarted(paramWebView, paramString, paramBitmap);
    isCurrentlyLoading = true;
    LOG.d("CordovaWebViewClient", "onPageStarted(" + paramString + ")");
    appView.bridge.reset(paramString);
    appView.postMessage("onPageStarted", paramString);
    if (appView.pluginManager != null) {
      appView.pluginManager.onReset();
    }
  }
  
  public void onReceivedError(WebView paramWebView, int paramInt, String paramString1, String paramString2)
  {
    if (!isCurrentlyLoading) {
      return;
    }
    LOG.d("CordovaWebViewClient", "CordovaWebViewClient.onReceivedError: Error code=%s Description=%s URL=%s", new Object[] { Integer.valueOf(paramInt), paramString1, paramString2 });
    CordovaWebView localCordovaWebView = appView;
    loadUrlTimeout += 1;
    if (paramInt == -10)
    {
      if (paramWebView.canGoBack())
      {
        paramWebView.goBack();
        return;
      }
      super.onReceivedError(paramWebView, paramInt, paramString1, paramString2);
    }
    paramWebView = new JSONObject();
    try
    {
      paramWebView.put("errorCode", paramInt);
      paramWebView.put("description", paramString1);
      paramWebView.put("url", paramString2);
      appView.postMessage("onReceivedError", paramWebView);
      return;
    }
    catch (JSONException paramString1)
    {
      for (;;)
      {
        paramString1.printStackTrace();
      }
    }
  }
  
  public void onReceivedHttpAuthRequest(WebView paramWebView, HttpAuthHandler paramHttpAuthHandler, String paramString1, String paramString2)
  {
    AuthenticationToken localAuthenticationToken = getAuthenticationToken(paramString1, paramString2);
    if (localAuthenticationToken != null)
    {
      paramHttpAuthHandler.proceed(localAuthenticationToken.getUserName(), localAuthenticationToken.getPassword());
      return;
    }
    super.onReceivedHttpAuthRequest(paramWebView, paramHttpAuthHandler, paramString1, paramString2);
  }
  
  @TargetApi(8)
  public void onReceivedSslError(WebView paramWebView, SslErrorHandler paramSslErrorHandler, SslError paramSslError)
  {
    String str = cordova.getActivity().getPackageName();
    PackageManager localPackageManager = cordova.getActivity().getPackageManager();
    try
    {
      if ((getApplicationInfo128flags & 0x2) != 0)
      {
        paramSslErrorHandler.proceed();
        return;
      }
      super.onReceivedSslError(paramWebView, paramSslErrorHandler, paramSslError);
      return;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      super.onReceivedSslError(paramWebView, paramSslErrorHandler, paramSslError);
    }
  }
  
  public AuthenticationToken removeAuthenticationToken(String paramString1, String paramString2)
  {
    return (AuthenticationToken)authenticationTokens.remove(paramString1.concat(paramString2));
  }
  
  public void setAuthenticationToken(AuthenticationToken paramAuthenticationToken, String paramString1, String paramString2)
  {
    String str = paramString1;
    if (paramString1 == null) {
      str = "";
    }
    paramString1 = paramString2;
    if (paramString2 == null) {
      paramString1 = "";
    }
    authenticationTokens.put(str.concat(paramString1), paramAuthenticationToken);
  }
  
  @Deprecated
  public void setWebView(CordovaWebView paramCordovaWebView)
  {
    appView = paramCordovaWebView;
    helper = new CordovaUriHelper(cordova, paramCordovaWebView);
  }
  
  public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
  {
    return helper.shouldOverrideUrlLoading(paramWebView, paramString);
  }
}

/* Location:
 * Qualified Name:     org.apache.cordova.CordovaWebViewClient
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */