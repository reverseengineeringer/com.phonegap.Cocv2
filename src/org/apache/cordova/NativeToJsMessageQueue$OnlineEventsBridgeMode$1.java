package org.apache.cordova;

import java.util.LinkedList;

class NativeToJsMessageQueue$OnlineEventsBridgeMode$1
  implements Runnable
{
  NativeToJsMessageQueue$OnlineEventsBridgeMode$1(NativeToJsMessageQueue.OnlineEventsBridgeMode paramOnlineEventsBridgeMode) {}
  
  public void run()
  {
    if (!NativeToJsMessageQueue.access$800(this$1.this$0).isEmpty())
    {
      NativeToJsMessageQueue.OnlineEventsBridgeMode.access$902(this$1, false);
      NativeToJsMessageQueue.access$600(this$1.this$0).setNetworkAvailable(NativeToJsMessageQueue.OnlineEventsBridgeMode.access$1000(this$1));
    }
  }
}

/* Location:
 * Qualified Name:     org.apache.cordova.NativeToJsMessageQueue.OnlineEventsBridgeMode.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */