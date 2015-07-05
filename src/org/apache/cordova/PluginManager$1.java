package org.apache.cordova;

import java.util.concurrent.atomic.AtomicInteger;

class PluginManager$1
  implements Runnable
{
  PluginManager$1(PluginManager paramPluginManager, String paramString1, String paramString2, String paramString3, String paramString4) {}
  
  public void run()
  {
    PluginManager.access$2(this$0, val$service, val$action, val$callbackId, val$rawArgs);
    PluginManager.access$0(this$0).getAndDecrement();
  }
}

/* Location:
 * Qualified Name:     org.apache.cordova.PluginManager.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */