package com.wezka.nativecamera;

import android.content.Intent;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

class CameraActivity$6
  implements Camera.PictureCallback
{
  CameraActivity$6(CameraActivity paramCameraActivity) {}
  
  public void onPictureTaken(byte[] paramArrayOfByte, Camera paramCamera)
  {
    paramCamera = new File(((Uri)this$0.getIntent().getExtras().get("output")).getPath());
    try
    {
      paramCamera = new FileOutputStream(paramCamera);
      paramCamera.write(paramArrayOfByte);
      paramCamera.close();
      this$0.setResult(-1);
      CameraActivity.access$902(this$0, false);
      this$0.finish();
      return;
    }
    catch (FileNotFoundException paramArrayOfByte)
    {
      for (;;)
      {
        Log.d("CameraActivity", "File not found: " + paramArrayOfByte.getMessage());
      }
    }
    catch (IOException paramArrayOfByte)
    {
      for (;;)
      {
        Log.d("CameraActivity", "Error accessing file: " + paramArrayOfByte.getMessage());
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.wezka.nativecamera.CameraActivity.6
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */