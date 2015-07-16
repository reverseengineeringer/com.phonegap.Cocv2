package org.apache.cordova;

import android.app.Activity;
import android.os.Message;
import android.util.Log;
import android.webkit.WebView;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.LinkedList;

public class NativeToJsMessageQueue
{
  static final boolean DISABLE_EXEC_CHAINING = false;
  private static final boolean FORCE_ENCODE_USING_EVAL = false;
  private static final String LOG_TAG = "JsMessageQueue";
  private static int MAX_PAYLOAD_SIZE = 524288000;
  private BridgeMode activeBridgeMode;
  private final CordovaInterface cordova;
  private boolean paused;
  private final LinkedList<JsMessage> queue = new LinkedList();
  private final BridgeMode[] registeredListeners;
  private final CordovaWebView webView;
  
  public NativeToJsMessageQueue(CordovaWebView paramCordovaWebView, CordovaInterface paramCordovaInterface)
  {
    cordova = paramCordovaInterface;
    webView = paramCordovaWebView;
    registeredListeners = new BridgeMode[4];
    registeredListeners[0] = new PollingBridgeMode(null);
    registeredListeners[1] = new LoadUrlBridgeMode(null);
    registeredListeners[2] = new OnlineEventsBridgeMode(null);
    registeredListeners[3] = new PrivateApiBridgeMode(null);
    reset();
  }
  
  private int calculatePackedMessageLength(JsMessage paramJsMessage)
  {
    int i = paramJsMessage.calculateEncodedLength();
    return String.valueOf(i).length() + i + 1;
  }
  
  private void enqueueMessage(JsMessage paramJsMessage)
  {
    try
    {
      if (activeBridgeMode == null)
      {
        Log.d("JsMessageQueue", "Dropping Native->JS message due to disabled bridge");
        return;
      }
      queue.add(paramJsMessage);
      if (!paused) {
        activeBridgeMode.onNativeToJsMessageAvailable();
      }
      return;
    }
    finally {}
  }
  
  private void packMessage(JsMessage paramJsMessage, StringBuilder paramStringBuilder)
  {
    paramStringBuilder.append(paramJsMessage.calculateEncodedLength()).append(' ');
    paramJsMessage.encodeAsMessage(paramStringBuilder);
  }
  
  private String popAndEncodeAsJs()
  {
    int i;
    int j;
    int k;
    label88:
    int m;
    try
    {
      if (queue.size() == 0) {
        return null;
      }
      i = 0;
      j = 0;
      Object localObject1 = queue.iterator();
      if (((Iterator)localObject1).hasNext())
      {
        k = ((JsMessage)((Iterator)localObject1).next()).calculateEncodedLength() + 50;
        if ((j <= 0) || (i + k <= MAX_PAYLOAD_SIZE) || (MAX_PAYLOAD_SIZE <= 0)) {
          break label235;
        }
      }
      if (j != queue.size()) {
        break label246;
      }
      k = 1;
      if (k == 0) {
        break label251;
      }
      m = 0;
      label95:
      localObject1 = new StringBuilder(m + i);
      i = 0;
      label110:
      if (i < j)
      {
        JsMessage localJsMessage = (JsMessage)queue.removeFirst();
        if ((k != 0) && (i + 1 == j))
        {
          localJsMessage.encodeAsJsMessage((StringBuilder)localObject1);
        }
        else
        {
          ((StringBuilder)localObject1).append("try{");
          localJsMessage.encodeAsJsMessage((StringBuilder)localObject1);
          ((StringBuilder)localObject1).append("}finally{");
        }
      }
    }
    finally {}
    if (k == 0) {
      ((StringBuilder)localObject2).append("window.setTimeout(function(){cordova.require('cordova/plugin/android/polling').pollOnce();},0);");
    }
    for (;;)
    {
      if (i < j)
      {
        ((StringBuilder)localObject2).append('}');
        i += 1;
      }
      else
      {
        String str = ((StringBuilder)localObject2).toString();
        return str;
        i += 1;
        break label110;
        label235:
        i += k;
        j += 1;
        break;
        label246:
        k = 0;
        break label88;
        label251:
        m = 100;
        break label95;
        if (k != 0) {
          i = 1;
        } else {
          i = 0;
        }
      }
    }
  }
  
  public void addJavaScript(String paramString)
  {
    enqueueMessage(new JsMessage(paramString));
  }
  
  public void addPluginResult(PluginResult paramPluginResult, String paramString)
  {
    if (paramString == null)
    {
      Log.e("JsMessageQueue", "Got plugin result with no callbackId", new Throwable());
      return;
    }
    if (paramPluginResult.getStatus() == PluginResult.Status.NO_RESULT.ordinal()) {}
    for (int i = 1;; i = 0)
    {
      boolean bool = paramPluginResult.getKeepCallback();
      if ((i != 0) && (bool)) {
        break;
      }
      enqueueMessage(new JsMessage(paramPluginResult, paramString));
      return;
    }
  }
  
  public boolean isBridgeEnabled()
  {
    return activeBridgeMode != null;
  }
  
  public String popAndEncode(boolean paramBoolean)
  {
    try
    {
      if (activeBridgeMode == null) {
        return null;
      }
      activeBridgeMode.notifyOfFlush(paramBoolean);
      if (queue.isEmpty()) {
        return null;
      }
    }
    finally {}
    int j = 0;
    int i = 0;
    Object localObject2 = queue.iterator();
    for (;;)
    {
      int k;
      if (((Iterator)localObject2).hasNext())
      {
        k = calculatePackedMessageLength((JsMessage)((Iterator)localObject2).next());
        if ((i <= 0) || (j + k <= MAX_PAYLOAD_SIZE) || (MAX_PAYLOAD_SIZE <= 0)) {}
      }
      else
      {
        localObject2 = new StringBuilder(j);
        j = 0;
        while (j < i)
        {
          packMessage((JsMessage)queue.removeFirst(), (StringBuilder)localObject2);
          j += 1;
        }
        if (!queue.isEmpty()) {
          ((StringBuilder)localObject2).append('*');
        }
        localObject2 = ((StringBuilder)localObject2).toString();
        return (String)localObject2;
      }
      j += k;
      i += 1;
    }
  }
  
  public void reset()
  {
    try
    {
      queue.clear();
      setBridgeMode(-1);
      return;
    }
    finally {}
  }
  
  public void setBridgeMode(int paramInt)
  {
    if ((paramInt < -1) || (paramInt >= registeredListeners.length))
    {
      Log.d("JsMessageQueue", "Invalid NativeToJsBridgeMode: " + paramInt);
      return;
    }
    BridgeMode localBridgeMode1;
    label46:
    StringBuilder localStringBuilder;
    if (paramInt < 0)
    {
      localBridgeMode1 = null;
      if (localBridgeMode1 == activeBridgeMode) {
        break label142;
      }
      localStringBuilder = new StringBuilder().append("Set native->JS mode to ");
      if (localBridgeMode1 != null) {
        break label144;
      }
    }
    BridgeMode localBridgeMode2;
    label142:
    label144:
    for (String str = "null";; str = localBridgeMode2.getClass().getSimpleName())
    {
      Log.d("JsMessageQueue", str);
      try
      {
        activeBridgeMode = localBridgeMode1;
        if (localBridgeMode1 != null)
        {
          localBridgeMode1.reset();
          if ((!paused) && (!queue.isEmpty())) {
            localBridgeMode1.onNativeToJsMessageAvailable();
          }
        }
        return;
      }
      finally {}
      localBridgeMode2 = registeredListeners[paramInt];
      break label46;
      break;
    }
  }
  
  public void setPaused(boolean paramBoolean)
  {
    if ((paused) && (paramBoolean)) {
      Log.e("JsMessageQueue", "nested call to setPaused detected.", new Throwable());
    }
    paused = paramBoolean;
    if (!paramBoolean) {
      try
      {
        if ((!queue.isEmpty()) && (activeBridgeMode != null)) {
          activeBridgeMode.onNativeToJsMessageAvailable();
        }
        return;
      }
      finally {}
    }
  }
  
  private abstract class BridgeMode
  {
    private BridgeMode() {}
    
    void notifyOfFlush(boolean paramBoolean) {}
    
    abstract void onNativeToJsMessageAvailable();
    
    void reset() {}
  }
  
  private static class JsMessage
  {
    final String jsPayloadOrCallbackId;
    final PluginResult pluginResult;
    
    JsMessage(String paramString)
    {
      if (paramString == null) {
        throw new NullPointerException();
      }
      jsPayloadOrCallbackId = paramString;
      pluginResult = null;
    }
    
    JsMessage(PluginResult paramPluginResult, String paramString)
    {
      if ((paramString == null) || (paramPluginResult == null)) {
        throw new NullPointerException();
      }
      jsPayloadOrCallbackId = paramString;
      pluginResult = paramPluginResult;
    }
    
    int calculateEncodedLength()
    {
      if (pluginResult == null) {
        return jsPayloadOrCallbackId.length() + 1;
      }
      int i = String.valueOf(pluginResult.getStatus()).length() + 2 + 1 + jsPayloadOrCallbackId.length() + 1;
      switch (pluginResult.getMessageType())
      {
      case 2: 
      default: 
        return i + pluginResult.getMessage().length();
      case 4: 
      case 5: 
        return i + 1;
      case 3: 
        return i + (pluginResult.getMessage().length() + 1);
      case 1: 
        return i + (pluginResult.getStrMessage().length() + 1);
      case 7: 
        return i + (pluginResult.getMessage().length() + 1);
      }
      return i + (pluginResult.getMessage().length() + 1);
    }
    
    void encodeAsJsMessage(StringBuilder paramStringBuilder)
    {
      if (pluginResult == null)
      {
        paramStringBuilder.append(jsPayloadOrCallbackId);
        return;
      }
      int i = pluginResult.getStatus();
      boolean bool;
      if ((i == PluginResult.Status.OK.ordinal()) || (i == PluginResult.Status.NO_RESULT.ordinal()))
      {
        bool = true;
        paramStringBuilder.append("cordova.callbackFromNative('").append(jsPayloadOrCallbackId).append("',").append(bool).append(",").append(i).append(",[");
        switch (pluginResult.getMessageType())
        {
        default: 
          paramStringBuilder.append(pluginResult.getMessage());
        }
      }
      for (;;)
      {
        paramStringBuilder.append("],").append(pluginResult.getKeepCallback()).append(");");
        return;
        bool = false;
        break;
        paramStringBuilder.append("atob('").append(pluginResult.getMessage()).append("')");
        continue;
        paramStringBuilder.append("cordova.require('cordova/base64').toArrayBuffer('").append(pluginResult.getMessage()).append("')");
      }
    }
    
    void encodeAsMessage(StringBuilder paramStringBuilder)
    {
      if (pluginResult == null)
      {
        paramStringBuilder.append('J').append(jsPayloadOrCallbackId);
        return;
      }
      int k = pluginResult.getStatus();
      int i;
      label44:
      int j;
      label58:
      label79:
      StringBuilder localStringBuilder;
      if (k == PluginResult.Status.NO_RESULT.ordinal())
      {
        i = 1;
        if (k != PluginResult.Status.OK.ordinal()) {
          break label190;
        }
        j = 1;
        boolean bool = pluginResult.getKeepCallback();
        if ((i == 0) && (j == 0)) {
          break label196;
        }
        c = 'S';
        localStringBuilder = paramStringBuilder.append(c);
        if (!bool) {
          break label202;
        }
      }
      label190:
      label196:
      label202:
      for (char c = '1';; c = '0')
      {
        localStringBuilder.append(c).append(k).append(' ').append(jsPayloadOrCallbackId).append(' ');
        switch (pluginResult.getMessageType())
        {
        case 2: 
        default: 
          paramStringBuilder.append(pluginResult.getMessage());
          return;
          i = 0;
          break label44;
          j = 0;
          break label58;
          c = 'F';
          break label79;
        }
      }
      paramStringBuilder.append(pluginResult.getMessage().charAt(0));
      return;
      paramStringBuilder.append('N');
      return;
      paramStringBuilder.append('n').append(pluginResult.getMessage());
      return;
      paramStringBuilder.append('s');
      paramStringBuilder.append(pluginResult.getStrMessage());
      return;
      paramStringBuilder.append('S');
      paramStringBuilder.append(pluginResult.getMessage());
      return;
      paramStringBuilder.append('A');
      paramStringBuilder.append(pluginResult.getMessage());
    }
  }
  
  private class LoadUrlBridgeMode
    extends NativeToJsMessageQueue.BridgeMode
  {
    final Runnable runnable = new Runnable()
    {
      public void run()
      {
        String str = NativeToJsMessageQueue.this.popAndEncodeAsJs();
        if (str != null) {
          webView.loadUrlNow("javascript:" + str);
        }
      }
    };
    
    private LoadUrlBridgeMode()
    {
      super(null);
    }
    
    void onNativeToJsMessageAvailable()
    {
      cordova.getActivity().runOnUiThread(runnable);
    }
  }
  
  private class OnlineEventsBridgeMode
    extends NativeToJsMessageQueue.BridgeMode
  {
    private boolean ignoreNextFlush;
    private boolean online;
    final Runnable resetNetworkRunnable = new Runnable()
    {
      public void run()
      {
        NativeToJsMessageQueue.OnlineEventsBridgeMode.access$1002(NativeToJsMessageQueue.OnlineEventsBridgeMode.this, false);
        NativeToJsMessageQueue.OnlineEventsBridgeMode.access$902(NativeToJsMessageQueue.OnlineEventsBridgeMode.this, true);
        webView.setNetworkAvailable(true);
      }
    };
    final Runnable toggleNetworkRunnable = new Runnable()
    {
      public void run()
      {
        if (!queue.isEmpty())
        {
          NativeToJsMessageQueue.OnlineEventsBridgeMode.access$902(NativeToJsMessageQueue.OnlineEventsBridgeMode.this, false);
          webView.setNetworkAvailable(online);
        }
      }
    };
    
    private OnlineEventsBridgeMode()
    {
      super(null);
    }
    
    void notifyOfFlush(boolean paramBoolean)
    {
      if ((paramBoolean) && (!ignoreNextFlush)) {
        if (online) {
          break label26;
        }
      }
      label26:
      for (paramBoolean = true;; paramBoolean = false)
      {
        online = paramBoolean;
        return;
      }
    }
    
    void onNativeToJsMessageAvailable()
    {
      cordova.getActivity().runOnUiThread(toggleNetworkRunnable);
    }
    
    void reset()
    {
      cordova.getActivity().runOnUiThread(resetNetworkRunnable);
    }
  }
  
  private class PollingBridgeMode
    extends NativeToJsMessageQueue.BridgeMode
  {
    private PollingBridgeMode()
    {
      super(null);
    }
    
    void onNativeToJsMessageAvailable() {}
  }
  
  private class PrivateApiBridgeMode
    extends NativeToJsMessageQueue.BridgeMode
  {
    private static final int EXECUTE_JS = 194;
    boolean initFailed;
    Method sendMessageMethod;
    Object webViewCore;
    
    private PrivateApiBridgeMode()
    {
      super(null);
    }
    
    private void initReflection()
    {
      Object localObject2 = webView;
      Object localObject3 = WebView.class;
      Object localObject1 = localObject2;
      for (;;)
      {
        try
        {
          Object localObject4 = WebView.class.getDeclaredField("mProvider");
          localObject1 = localObject2;
          ((Field)localObject4).setAccessible(true);
          localObject1 = localObject2;
          localObject2 = ((Field)localObject4).get(webView);
          localObject1 = localObject2;
          localObject4 = localObject2.getClass();
          localObject3 = localObject4;
          localObject1 = localObject2;
        }
        catch (Throwable localThrowable2)
        {
          continue;
        }
        try
        {
          localObject2 = ((Class)localObject3).getDeclaredField("mWebViewCore");
          ((Field)localObject2).setAccessible(true);
          webViewCore = ((Field)localObject2).get(localObject1);
          if (webViewCore != null)
          {
            sendMessageMethod = webViewCore.getClass().getDeclaredMethod("sendMessage", new Class[] { Message.class });
            sendMessageMethod.setAccessible(true);
          }
          return;
        }
        catch (Throwable localThrowable1)
        {
          initFailed = true;
          Log.e("JsMessageQueue", "PrivateApiBridgeMode failed to find the expected APIs.", localThrowable1);
          return;
        }
      }
    }
    
    void onNativeToJsMessageAvailable()
    {
      if ((sendMessageMethod == null) && (!initFailed)) {
        initReflection();
      }
      Message localMessage;
      if (sendMessageMethod != null) {
        localMessage = Message.obtain(null, 194, NativeToJsMessageQueue.this.popAndEncodeAsJs());
      }
      try
      {
        sendMessageMethod.invoke(webViewCore, new Object[] { localMessage });
        return;
      }
      catch (Throwable localThrowable)
      {
        Log.e("JsMessageQueue", "Reflection message bridge failed.", localThrowable);
      }
    }
  }
}

/* Location:
 * Qualified Name:     org.apache.cordova.NativeToJsMessageQueue
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */