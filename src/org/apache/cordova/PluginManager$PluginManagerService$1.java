package org.apache.cordova;

import java.util.concurrent.atomic.AtomicInteger;

class PluginManager$PluginManagerService$1
  implements Runnable
{
  PluginManager$PluginManagerService$1(PluginManager.PluginManagerService paramPluginManagerService) {}
  
  public void run()
  {
    PluginManager.access$0(PluginManager.PluginManagerService.access$1(this$1)).getAndDecrement();
  }
}

/* Location:
 * Qualified Name:     org.apache.cordova.PluginManager.PluginManagerService.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */