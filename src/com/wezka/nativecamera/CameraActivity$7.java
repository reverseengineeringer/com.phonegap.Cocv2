package com.wezka.nativecamera;

import android.hardware.Camera;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

class CameraActivity$7
  implements SurfaceHolder.Callback
{
  CameraActivity$7(CameraActivity paramCameraActivity) {}
  
  public void surfaceChanged(SurfaceHolder paramSurfaceHolder, int paramInt1, int paramInt2, int paramInt3)
  {
    if (CameraActivity.access$200(this$0) != null) {
      CameraActivity.access$200(this$0).setDisplayOrientation(90);
    }
    CameraActivity.access$1300(this$0, CameraActivity.access$1200(this$0).getHeight());
    CameraActivity.access$1400(this$0);
  }
  
  public void surfaceCreated(SurfaceHolder paramSurfaceHolder) {}
  
  public void surfaceDestroyed(SurfaceHolder paramSurfaceHolder)
  {
    if (CameraActivity.access$200(this$0) != null)
    {
      CameraActivity.access$200(this$0).stopPreview();
      CameraActivity.access$200(this$0).release();
      CameraActivity.access$202(this$0, null);
    }
  }
}

/* Location:
 * Qualified Name:     com.wezka.nativecamera.CameraActivity.7
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */