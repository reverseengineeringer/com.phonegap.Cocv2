package org.apache.cordova.inappbrowser;

import android.webkit.WebView;
import android.webkit.WebViewClient;

class InAppBrowser$4$1
  extends WebViewClient
{
  InAppBrowser$4$1(InAppBrowser.4 param4) {}
  
  public void onPageFinished(WebView paramWebView, String paramString)
  {
    if (InAppBrowser.access$000(this$1.this$0) != null) {
      InAppBrowser.access$000(this$1.this$0).dismiss();
    }
  }
}

/* Location:
 * Qualified Name:     org.apache.cordova.inappbrowser.InAppBrowser.4.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */