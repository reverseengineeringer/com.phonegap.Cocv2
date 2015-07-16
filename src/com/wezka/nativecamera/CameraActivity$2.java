package com.wezka.nativecamera;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

class CameraActivity$2
  implements View.OnClickListener
{
  CameraActivity$2(CameraActivity paramCameraActivity, ImageView paramImageView, Button paramButton, int paramInt) {}
  
  public void onClick(View paramView)
  {
    if (CameraActivity.access$500(this$0) == 0)
    {
      CameraActivity.access$502(this$0, 1);
      CameraActivity.access$602(this$0, 0);
      val$viewfinder.setVisibility(4);
      if (CameraActivity.access$700(this$0)) {
        val$flashButton.setVisibility(4);
      }
      if (CameraActivity.access$700(this$0)) {
        val$flashButton.setBackgroundResource(val$imgFlashNo);
      }
    }
    for (;;)
    {
      CameraActivity.access$802(this$0, false);
      this$0.restartPreview(CameraActivity.access$500(this$0));
      return;
      CameraActivity.access$502(this$0, 0);
      CameraActivity.access$602(this$0, 0);
      val$viewfinder.setVisibility(0);
      val$viewfinder.setX(CameraActivity.access$000(this$0) / 2 - CameraActivity.access$300(this$0));
      val$viewfinder.setY(CameraActivity.access$100(this$0) / 2 - CameraActivity.access$300(this$0) * 3.0F);
      if (CameraActivity.access$700(this$0)) {
        val$flashButton.setVisibility(0);
      }
      if (CameraActivity.access$700(this$0)) {
        val$flashButton.setBackgroundResource(val$imgFlashNo);
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.wezka.nativecamera.CameraActivity.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */