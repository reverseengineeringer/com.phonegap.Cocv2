package org.apache.cordova;

class NativeToJsMessageQueue$OnlineEventsBridgeMode$2
  implements Runnable
{
  NativeToJsMessageQueue$OnlineEventsBridgeMode$2(NativeToJsMessageQueue.OnlineEventsBridgeMode paramOnlineEventsBridgeMode) {}
  
  public void run()
  {
    NativeToJsMessageQueue.OnlineEventsBridgeMode.access$1002(this$1, false);
    NativeToJsMessageQueue.OnlineEventsBridgeMode.access$902(this$1, true);
    NativeToJsMessageQueue.access$600(this$1.this$0).setNetworkAvailable(true);
  }
}

/* Location:
 * Qualified Name:     org.apache.cordova.NativeToJsMessageQueue.OnlineEventsBridgeMode.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */