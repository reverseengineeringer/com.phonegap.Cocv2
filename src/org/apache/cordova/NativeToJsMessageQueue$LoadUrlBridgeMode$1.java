package org.apache.cordova;

class NativeToJsMessageQueue$LoadUrlBridgeMode$1
  implements Runnable
{
  NativeToJsMessageQueue$LoadUrlBridgeMode$1(NativeToJsMessageQueue.LoadUrlBridgeMode paramLoadUrlBridgeMode) {}
  
  public void run()
  {
    String str = NativeToJsMessageQueue.access$0(NativeToJsMessageQueue.LoadUrlBridgeMode.access$1(this$1));
    if (str != null) {
      NativeToJsMessageQueue.access$1(NativeToJsMessageQueue.LoadUrlBridgeMode.access$1(this$1)).loadUrlNow("javascript:" + str);
    }
  }
}

/* Location:
 * Qualified Name:     org.apache.cordova.NativeToJsMessageQueue.LoadUrlBridgeMode.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */