package com.wezka.nativecamera;

import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.Parameters;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

class CameraActivity$4
  implements View.OnClickListener
{
  CameraActivity$4(CameraActivity paramCameraActivity) {}
  
  public void onClick(View paramView)
  {
    if ((CameraActivity.access$900(this$0)) || (CameraActivity.access$200(this$0) == null)) {
      return;
    }
    paramView = CameraActivity.access$200(this$0).getParameters();
    paramView.setRotation(CameraActivity.access$1000(this$0));
    CameraActivity.access$200(this$0).setParameters(paramView);
    CameraActivity.access$902(this$0, true);
    try
    {
      CameraActivity.access$200(this$0).autoFocus(new Camera.AutoFocusCallback()
      {
        public void onAutoFocus(boolean paramAnonymousBoolean, Camera paramAnonymousCamera)
        {
          try
          {
            paramAnonymousCamera.takePicture(null, null, CameraActivity.access$1100(this$0));
            return;
          }
          catch (RuntimeException paramAnonymousCamera)
          {
            Toast.makeText(this$0.getApplicationContext(), "Error taking picture", 0).show();
            Log.e("CameraActivity", "Auto-focus crash");
          }
        }
      });
      return;
    }
    catch (RuntimeException paramView)
    {
      Toast.makeText(this$0.getApplicationContext(), "Error focusing", 0).show();
      Log.e("CameraActivity", "Auto-focus crash");
    }
  }
}

/* Location:
 * Qualified Name:     com.wezka.nativecamera.CameraActivity.4
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */