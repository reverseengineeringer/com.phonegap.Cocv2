package com.wezka.nativecamera;

import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

class CameraActivity$3
  implements View.OnClickListener
{
  CameraActivity$3(CameraActivity paramCameraActivity, Button paramButton, int paramInt1, int paramInt2, int paramInt3) {}
  
  public void onClick(View paramView)
  {
    paramView = CameraActivity.access$200(this$0).getParameters();
    if (CameraActivity.access$600(this$0) == 0)
    {
      paramView.setFlashMode("auto");
      val$flashButton.setBackgroundResource(val$imgFlashAuto);
      CameraActivity.access$602(this$0, 1);
    }
    for (;;)
    {
      CameraActivity.access$200(this$0).setParameters(paramView);
      CameraActivity.access$200(this$0).startPreview();
      return;
      if (CameraActivity.access$600(this$0) == 1)
      {
        paramView.setFlashMode("torch");
        val$flashButton.setBackgroundResource(val$imgFlashOn);
        CameraActivity.access$602(this$0, 2);
      }
      else if (CameraActivity.access$600(this$0) == 2)
      {
        paramView.setFlashMode("off");
        val$flashButton.setBackgroundResource(val$imgFlashNo);
        CameraActivity.access$602(this$0, 0);
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.wezka.nativecamera.CameraActivity.3
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */