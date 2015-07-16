package org.apache.cordova;

import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;

public class CordovaBridge
{
  private static final String LOG_TAG = "CordovaBridge";
  private volatile int expectedBridgeSecret = -1;
  private NativeToJsMessageQueue jsMessageQueue;
  private String loadedUrl;
  private PluginManager pluginManager;
  
  public CordovaBridge(PluginManager paramPluginManager, NativeToJsMessageQueue paramNativeToJsMessageQueue)
  {
    pluginManager = paramPluginManager;
    jsMessageQueue = paramNativeToJsMessageQueue;
  }
  
  private boolean verifySecret(String paramString, int paramInt)
    throws IllegalAccessException
  {
    if (!jsMessageQueue.isBridgeEnabled())
    {
      if (paramInt == -1) {
        Log.d("CordovaBridge", paramString + " call made before bridge was enabled.");
      }
      for (;;)
      {
        return false;
        Log.d("CordovaBridge", "Ignoring " + paramString + " from previous page load.");
      }
    }
    if ((expectedBridgeSecret < 0) || (paramInt != expectedBridgeSecret)) {
      throw new IllegalAccessException();
    }
    return true;
  }
  
  void clearBridgeSecret()
  {
    expectedBridgeSecret = -1;
  }
  
  int generateBridgeSecret()
  {
    expectedBridgeSecret = ((int)(Math.random() * 2.147483647E9D));
    return expectedBridgeSecret;
  }
  
  public NativeToJsMessageQueue getMessageQueue()
  {
    return jsMessageQueue;
  }
  
  public String jsExec(int paramInt, String paramString1, String paramString2, String paramString3, String paramString4)
    throws JSONException, IllegalAccessException
  {
    if (!verifySecret("exec()", paramInt)) {
      return null;
    }
    if (paramString4 == null) {
      return "@Null arguments.";
    }
    jsMessageQueue.setPaused(true);
    try
    {
      CordovaResourceApi.jsThread = Thread.currentThread();
      pluginManager.exec(paramString1, paramString2, paramString3, paramString4);
      paramString1 = jsMessageQueue.popAndEncode(false);
      return paramString1;
    }
    catch (Throwable paramString1)
    {
      paramString1.printStackTrace();
      return "";
    }
    finally
    {
      jsMessageQueue.setPaused(false);
    }
  }
  
  public String jsRetrieveJsMessages(int paramInt, boolean paramBoolean)
    throws IllegalAccessException
  {
    if (!verifySecret("retrieveJsMessages()", paramInt)) {
      return null;
    }
    return jsMessageQueue.popAndEncode(paramBoolean);
  }
  
  public void jsSetNativeToJsBridgeMode(int paramInt1, int paramInt2)
    throws IllegalAccessException
  {
    if (!verifySecret("setNativeToJsBridgeMode()", paramInt1)) {
      return;
    }
    jsMessageQueue.setBridgeMode(paramInt2);
  }
  
  public String promptOnJsPrompt(String paramString1, String paramString2, String paramString3)
  {
    if ((paramString3 != null) && (paramString3.length() > 3) && (paramString3.startsWith("gap:"))) {}
    int i;
    for (;;)
    {
      try
      {
        paramString1 = new JSONArray(paramString3.substring(4));
        paramString2 = jsExec(paramString1.getInt(0), paramString1.getString(1), paramString1.getString(2), paramString1.getString(3), paramString2);
        paramString1 = paramString2;
        if (paramString2 == null) {
          paramString1 = "";
        }
        return paramString1;
      }
      catch (JSONException paramString1)
      {
        paramString1.printStackTrace();
        return "";
      }
      catch (IllegalAccessException paramString1)
      {
        paramString1.printStackTrace();
        continue;
      }
      if ((paramString3 != null) && (paramString3.startsWith("gap_bridge_mode:"))) {
        try
        {
          jsSetNativeToJsBridgeMode(Integer.parseInt(paramString3.substring(16)), Integer.parseInt(paramString2));
          return "";
        }
        catch (NumberFormatException paramString1)
        {
          for (;;)
          {
            paramString1.printStackTrace();
          }
        }
        catch (IllegalAccessException paramString1)
        {
          for (;;)
          {
            paramString1.printStackTrace();
          }
        }
      }
      if ((paramString3 != null) && (paramString3.startsWith("gap_poll:")))
      {
        i = Integer.parseInt(paramString3.substring(9));
        try
        {
          paramString2 = jsRetrieveJsMessages(i, "1".equals(paramString2));
          paramString1 = paramString2;
          if (paramString2 == null) {
            return "";
          }
        }
        catch (IllegalAccessException paramString1)
        {
          paramString1.printStackTrace();
          return "";
        }
      }
    }
    if ((paramString3 != null) && (paramString3.startsWith("gap_init:")))
    {
      if ((paramString1.startsWith("file:")) || ((paramString1.startsWith("http")) && (loadedUrl.startsWith(paramString1))))
      {
        i = Integer.parseInt(paramString3.substring(9));
        jsMessageQueue.setBridgeMode(i);
        i = generateBridgeSecret();
        return "" + i;
      }
      Log.e("CordovaBridge", "gap_init called from restricted origin: " + paramString1);
      return "";
    }
    return null;
  }
  
  public void reset(String paramString)
  {
    jsMessageQueue.reset();
    clearBridgeSecret();
    loadedUrl = paramString;
  }
}

/* Location:
 * Qualified Name:     org.apache.cordova.CordovaBridge
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */