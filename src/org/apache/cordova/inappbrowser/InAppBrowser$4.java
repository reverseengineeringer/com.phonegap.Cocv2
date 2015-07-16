package org.apache.cordova.inappbrowser;

import android.webkit.WebView;
import android.webkit.WebViewClient;

class InAppBrowser$4
  implements Runnable
{
  InAppBrowser$4(InAppBrowser paramInAppBrowser, WebView paramWebView) {}
  
  public void run()
  {
    val$childView.setWebViewClient(new WebViewClient()
    {
      public void onPageFinished(WebView paramAnonymousWebView, String paramAnonymousString)
      {
        if (InAppBrowser.access$000(this$0) != null) {
          InAppBrowser.access$000(this$0).dismiss();
        }
      }
    });
    val$childView.loadUrl("about:blank");
  }
}

/* Location:
 * Qualified Name:     org.apache.cordova.inappbrowser.InAppBrowser.4
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */