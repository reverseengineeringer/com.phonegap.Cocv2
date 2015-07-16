package com.wezka.nativecamera;

import android.graphics.Rect;
import android.hardware.Camera;
import android.hardware.Camera.Area;
import android.hardware.Camera.Parameters;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import java.util.ArrayList;
import java.util.List;

class CameraActivity$1
  implements View.OnTouchListener
{
  CameraActivity$1(CameraActivity paramCameraActivity, ImageView paramImageView) {}
  
  public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
  {
    float f1 = (paramMotionEvent.getX() * 2000.0F / CameraActivity.access$000(this$0) - 1000.0F) * -1.0F;
    float f2 = paramMotionEvent.getY() * 2000.0F / CameraActivity.access$100(this$0) - 1000.0F;
    if (((int)f2 - 100 > 64536) && ((int)f2 + 100 < 1000) && ((int)f1 - 100 > 64536) && ((int)f1 + 100 < 1000)) {}
    for (paramView = new Rect((int)f2 - 100, (int)f1 - 100, (int)f2 + 100, (int)f1 + 100); CameraActivity.access$200(this$0) == null; paramView = new Rect(-100, -100, 100, 100)) {
      return true;
    }
    Camera.Parameters localParameters = CameraActivity.access$200(this$0).getParameters();
    if (localParameters.getMaxNumFocusAreas() > 0)
    {
      if (paramMotionEvent.getX() - CameraActivity.access$300(this$0) >= 0.0F) {
        break label281;
      }
      val$viewfinder.setX(0.0F);
      if (paramMotionEvent.getY() - CameraActivity.access$300(this$0) >= 0.0F) {
        break label355;
      }
      val$viewfinder.setY(0.0F);
    }
    for (;;)
    {
      paramMotionEvent = new ArrayList();
      paramMotionEvent.add(new Camera.Area(paramView, 750));
      localParameters.setFocusAreas(paramMotionEvent);
      if (localParameters.getMaxNumMeteringAreas() > 0) {
        localParameters.setMeteringAreas(paramMotionEvent);
      }
      CameraActivity.access$200(this$0).setParameters(localParameters);
      return true;
      label281:
      if (paramMotionEvent.getX() + CameraActivity.access$300(this$0) > CameraActivity.access$000(this$0))
      {
        val$viewfinder.setX(CameraActivity.access$000(this$0) - CameraActivity.access$300(this$0) * 2.0F);
        break;
      }
      val$viewfinder.setX(paramMotionEvent.getX() - CameraActivity.access$300(this$0));
      break;
      label355:
      if (paramMotionEvent.getY() + CameraActivity.access$300(this$0) > CameraActivity.access$100(this$0) - CameraActivity.access$400(this$0, 125.0F)) {
        val$viewfinder.setY(CameraActivity.access$100(this$0) - CameraActivity.access$400(this$0, 125.0F) - CameraActivity.access$300(this$0) * 2.0F);
      } else {
        val$viewfinder.setY(paramMotionEvent.getY() - CameraActivity.access$300(this$0));
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.wezka.nativecamera.CameraActivity.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */