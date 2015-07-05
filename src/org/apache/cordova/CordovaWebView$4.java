package org.apache.cordova;

import java.util.concurrent.ExecutorService;

class CordovaWebView$4
  implements Runnable
{
  CordovaWebView$4(CordovaWebView paramCordovaWebView1, Runnable paramRunnable, CordovaWebView paramCordovaWebView2, String paramString) {}
  
  public void run()
  {
    CordovaWebView.access$1(this$0).getThreadPool().execute(val$timeoutCheck);
    val$me.loadUrlNow(val$url);
  }
}

/* Location:
 * Qualified Name:     org.apache.cordova.CordovaWebView.4
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */