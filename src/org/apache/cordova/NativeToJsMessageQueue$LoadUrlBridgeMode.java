package org.apache.cordova;

import android.app.Activity;

class NativeToJsMessageQueue$LoadUrlBridgeMode
  extends NativeToJsMessageQueue.BridgeMode
{
  final Runnable runnable = new Runnable()
  {
    public void run()
    {
      String str = NativeToJsMessageQueue.access$500(this$0);
      if (str != null) {
        NativeToJsMessageQueue.access$600(this$0).loadUrlNow("javascript:" + str);
      }
    }
  };
  
  private NativeToJsMessageQueue$LoadUrlBridgeMode(NativeToJsMessageQueue paramNativeToJsMessageQueue)
  {
    super(paramNativeToJsMessageQueue, null);
  }
  
  void onNativeToJsMessageAvailable()
  {
    NativeToJsMessageQueue.access$700(this$0).getActivity().runOnUiThread(runnable);
  }
}

/* Location:
 * Qualified Name:     org.apache.cordova.NativeToJsMessageQueue.LoadUrlBridgeMode
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */