package org.apache.cordova.inappbrowser;

import android.annotation.SuppressLint;
import android.os.Build.VERSION;
import android.webkit.WebView;

class InAppBrowser$3
  implements Runnable
{
  InAppBrowser$3(InAppBrowser paramInAppBrowser, String paramString) {}
  
  @SuppressLint({"NewApi"})
  public void run()
  {
    if (Build.VERSION.SDK_INT < 19)
    {
      InAppBrowser.access$100(this$0).loadUrl("javascript:" + val$finalScriptToInject);
      return;
    }
    InAppBrowser.access$100(this$0).evaluateJavascript(val$finalScriptToInject, null);
  }
}

/* Location:
 * Qualified Name:     org.apache.cordova.inappbrowser.InAppBrowser.3
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */