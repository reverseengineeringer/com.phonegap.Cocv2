package org.apache.cordova.dialogs;

import android.app.Activity;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import org.apache.cordova.CordovaInterface;

class Notification$1
  implements Runnable
{
  Notification$1(Notification paramNotification, long paramLong) {}
  
  public void run()
  {
    Object localObject = RingtoneManager.getDefaultUri(2);
    localObject = RingtoneManager.getRingtone(this$0.cordova.getActivity().getBaseContext(), (Uri)localObject);
    if (localObject != null) {
      for (long l1 = 0L; l1 < val$count; l1 += 1L)
      {
        ((Ringtone)localObject).play();
        long l2 = 5000L;
        while ((((Ringtone)localObject).isPlaying()) && (l2 > 0L))
        {
          l2 -= 100L;
          try
          {
            Thread.sleep(100L);
          }
          catch (InterruptedException localInterruptedException) {}
        }
      }
    }
  }
}

/* Location:
 * Qualified Name:     org.apache.cordova.dialogs.Notification.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */