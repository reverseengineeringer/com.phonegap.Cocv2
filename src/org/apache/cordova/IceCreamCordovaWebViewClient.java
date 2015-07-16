package org.apache.cordova;

import android.annotation.TargetApi;
import android.net.Uri;
import android.os.Build.VERSION;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import java.io.FileNotFoundException;
import java.io.IOException;

@TargetApi(11)
public class IceCreamCordovaWebViewClient
  extends CordovaWebViewClient
{
  private static final String TAG = "IceCreamCordovaWebViewClient";
  private CordovaUriHelper helper;
  
  public IceCreamCordovaWebViewClient(CordovaInterface paramCordovaInterface)
  {
    super(paramCordovaInterface);
  }
  
  public IceCreamCordovaWebViewClient(CordovaInterface paramCordovaInterface, CordovaWebView paramCordovaWebView)
  {
    super(paramCordovaInterface, paramCordovaWebView);
  }
  
  private boolean isUrlHarmful(String paramString)
  {
    return ((!paramString.startsWith("http:")) && (!paramString.startsWith("https:"))) || ((!appView.getWhitelist().isUrlWhiteListed(paramString)) || (paramString.contains("app_webview")));
  }
  
  private static boolean needsKitKatContentUrlFix(Uri paramUri)
  {
    return (Build.VERSION.SDK_INT >= 19) && ("content".equals(paramUri.getScheme()));
  }
  
  private static boolean needsSpecialsInAssetUrlFix(Uri paramUri)
  {
    if (CordovaResourceApi.getUriType(paramUri) != 1) {}
    do
    {
      return false;
      if ((paramUri.getQuery() != null) || (paramUri.getFragment() != null)) {
        return true;
      }
    } while (!paramUri.toString().contains("%"));
    switch (Build.VERSION.SDK_INT)
    {
    default: 
      return false;
    }
    return true;
  }
  
  public WebResourceResponse shouldInterceptRequest(WebView paramWebView, String paramString)
  {
    try
    {
      if (isUrlHarmful(paramString))
      {
        LOG.w("IceCreamCordovaWebViewClient", "URL blocked by whitelist: " + paramString);
        return new WebResourceResponse("text/plain", "UTF-8", null);
      }
      paramWebView = appView.getResourceApi();
      paramString = Uri.parse(paramString);
      Uri localUri = paramWebView.remapUri(paramString);
      if ((!paramString.equals(localUri)) || (needsSpecialsInAssetUrlFix(paramString)) || (needsKitKatContentUrlFix(paramString)))
      {
        paramWebView = paramWebView.openForRead(localUri, true);
        paramWebView = new WebResourceResponse(mimeType, "UTF-8", inputStream);
        return paramWebView;
      }
    }
    catch (IOException paramWebView)
    {
      if (!(paramWebView instanceof FileNotFoundException)) {
        LOG.e("IceCreamCordovaWebViewClient", "Error occurred while loading a file (returning a 404).", paramWebView);
      }
      return new WebResourceResponse("text/plain", "UTF-8", null);
    }
    return null;
  }
}

/* Location:
 * Qualified Name:     org.apache.cordova.IceCreamCordovaWebViewClient
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */