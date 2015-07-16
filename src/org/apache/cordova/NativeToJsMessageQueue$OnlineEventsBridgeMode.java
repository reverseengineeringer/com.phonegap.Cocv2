package org.apache.cordova;

import android.app.Activity;
import java.util.LinkedList;

class NativeToJsMessageQueue$OnlineEventsBridgeMode
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
      NativeToJsMessageQueue.access$600(this$0).setNetworkAvailable(true);
    }
  };
  final Runnable toggleNetworkRunnable = new Runnable()
  {
    public void run()
    {
      if (!NativeToJsMessageQueue.access$800(this$0).isEmpty())
      {
        NativeToJsMessageQueue.OnlineEventsBridgeMode.access$902(NativeToJsMessageQueue.OnlineEventsBridgeMode.this, false);
        NativeToJsMessageQueue.access$600(this$0).setNetworkAvailable(online);
      }
    }
  };
  
  private NativeToJsMessageQueue$OnlineEventsBridgeMode(NativeToJsMessageQueue paramNativeToJsMessageQueue)
  {
    super(paramNativeToJsMessageQueue, null);
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
    NativeToJsMessageQueue.access$700(this$0).getActivity().runOnUiThread(toggleNetworkRunnable);
  }
  
  void reset()
  {
    NativeToJsMessageQueue.access$700(this$0).getActivity().runOnUiThread(resetNetworkRunnable);
  }
}

/* Location:
 * Qualified Name:     org.apache.cordova.NativeToJsMessageQueue.OnlineEventsBridgeMode
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */