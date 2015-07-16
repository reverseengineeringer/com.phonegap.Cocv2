package com.wezka.nativecamera;

import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.util.Log;
import android.widget.Toast;

class CameraActivity$4$1
  implements Camera.AutoFocusCallback
{
  CameraActivity$4$1(CameraActivity.4 param4) {}
  
  public void onAutoFocus(boolean paramBoolean, Camera paramCamera)
  {
    try
    {
      paramCamera.takePicture(null, null, CameraActivity.access$1100(this$1.this$0));
      return;
    }
    catch (RuntimeException paramCamera)
    {
      Toast.makeText(this$1.this$0.getApplicationContext(), "Error taking picture", 0).show();
      Log.e("CameraActivity", "Auto-focus crash");
    }
  }
}

/* Location:
 * Qualified Name:     com.wezka.nativecamera.CameraActivity.4.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */