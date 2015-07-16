package org.apache.cordova;

import android.webkit.JavascriptInterface;
import org.json.JSONException;

class ExposedJsApi
{
  private CordovaBridge bridge;
  
  public ExposedJsApi(CordovaBridge paramCordovaBridge)
  {
    bridge = paramCordovaBridge;
  }
  
  @JavascriptInterface
  public String exec(int paramInt, String paramString1, String paramString2, String paramString3, String paramString4)
    throws JSONException, IllegalAccessException
  {
    return bridge.jsExec(paramInt, paramString1, paramString2, paramString3, paramString4);
  }
  
  @JavascriptInterface
  public String retrieveJsMessages(int paramInt, boolean paramBoolean)
    throws IllegalAccessException
  {
    return bridge.jsRetrieveJsMessages(paramInt, paramBoolean);
  }
  
  @JavascriptInterface
  public void setNativeToJsBridgeMode(int paramInt1, int paramInt2)
    throws IllegalAccessException
  {
    bridge.jsSetNativeToJsBridgeMode(paramInt1, paramInt2);
  }
}

/* Location:
 * Qualified Name:     org.apache.cordova.ExposedJsApi
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */