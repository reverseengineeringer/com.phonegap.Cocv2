package org.apache.cordova;

import android.app.Activity;
import java.util.LinkedList;

class NativeToJsMessageQueue$OnlineEventsBridgeMode
  extends NativeToJsMessageQueue.BridgeMode
{
  private boolean online;
  final Runnable runnable = new Runnable()
  {
    public void run()
    {
      if (!NativeToJsMessageQueue.access$3(this$0).isEmpty()) {
        NativeToJsMessageQueue.access$1(this$0).setNetworkAvailable(online);
      }
    }
  };
  
  private NativeToJsMessageQueue$OnlineEventsBridgeMode(NativeToJsMessageQueue paramNativeToJsMessageQueue)
  {
    super(paramNativeToJsMessageQueue, null);
  }
  
  void notifyOfFlush(boolean paramBoolean)
  {
    if (paramBoolean) {
      if (!online) {
        break label19;
      }
    }
    label19:
    for (paramBoolean = false;; paramBoolean = true)
    {
      online = paramBoolean;
      return;
    }
  }
  
  void onNativeToJsMessageAvailable()
  {
    NativeToJsMessageQueue.access$2(this$0).getActivity().runOnUiThread(runnable);
  }
  
  void reset()
  {
    online = false;
    NativeToJsMessageQueue.access$1(this$0).setNetworkAvailable(true);
  }
}

/* Location:
 * Qualified Name:     org.apache.cordova.NativeToJsMessageQueue.OnlineEventsBridgeMode
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */