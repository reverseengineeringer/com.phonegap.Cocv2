package com.squareup.okhttp.internal;

import java.util.concurrent.ThreadFactory;

class Util$1
  implements ThreadFactory
{
  Util$1(String paramString) {}
  
  public Thread newThread(Runnable paramRunnable)
  {
    paramRunnable = new Thread(paramRunnable, val$name);
    paramRunnable.setDaemon(true);
    return paramRunnable;
  }
}

/* Location:
 * Qualified Name:     com.squareup.okhttp.internal.Util.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */