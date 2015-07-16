package com.wezka.nativecamera;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Rect;
import android.hardware.Camera;
import android.hardware.Camera.Area;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.Size;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CameraActivity
  extends Activity
  implements SensorEventListener
{
  private static final String TAG = "CameraActivity";
  private static final int _DATA_X = 0;
  private static final int _DATA_Y = 1;
  private static final int _DATA_Z = 2;
  int ORIENTATION_UNKNOWN = -1;
  private int cam = 0;
  private Camera camera = null;
  private boolean cameraConfigured = false;
  private int degrees = 0;
  private boolean inPreview = false;
  private boolean isFlash = false;
  private boolean isFrontCamera = false;
  private int led = 0;
  public int mOrientationDeg;
  private Camera.PictureCallback mPicture = new Camera.PictureCallback()
  {
    public void onPictureTaken(byte[] paramAnonymousArrayOfByte, Camera paramAnonymousCamera)
    {
      paramAnonymousCamera = new File(((Uri)getIntent().getExtras().get("output")).getPath());
      try
      {
        paramAnonymousCamera = new FileOutputStream(paramAnonymousCamera);
        paramAnonymousCamera.write(paramAnonymousArrayOfByte);
        paramAnonymousCamera.close();
        setResult(-1);
        CameraActivity.access$902(CameraActivity.this, false);
        finish();
        return;
      }
      catch (FileNotFoundException paramAnonymousArrayOfByte)
      {
        for (;;)
        {
          Log.d("CameraActivity", "File not found: " + paramAnonymousArrayOfByte.getMessage());
        }
      }
      catch (IOException paramAnonymousArrayOfByte)
      {
        for (;;)
        {
          Log.d("CameraActivity", "Error accessing file: " + paramAnonymousArrayOfByte.getMessage());
        }
      }
    }
  };
  WindowManager mWindowManager;
  private boolean pressed = false;
  private SurfaceView preview;
  private SurfaceHolder previewHolder = null;
  private int screenHeight;
  private int screenWidth;
  SensorManager sm;
  SurfaceHolder.Callback surfaceCallback = new SurfaceHolder.Callback()
  {
    public void surfaceChanged(SurfaceHolder paramAnonymousSurfaceHolder, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
    {
      if (camera != null) {
        camera.setDisplayOrientation(90);
      }
      CameraActivity.this.initPreview(preview.getHeight());
      CameraActivity.this.startPreview();
    }
    
    public void surfaceCreated(SurfaceHolder paramAnonymousSurfaceHolder) {}
    
    public void surfaceDestroyed(SurfaceHolder paramAnonymousSurfaceHolder)
    {
      if (camera != null)
      {
        camera.stopPreview();
        camera.release();
        CameraActivity.access$202(CameraActivity.this, null);
      }
    }
  };
  private float viewfinderHalfPx;
  
  private Camera.Size getBestPreviewSize(int paramInt, Camera.Parameters paramParameters)
  {
    Object localObject1 = null;
    double d = Double.MAX_VALUE;
    Iterator localIterator = paramParameters.getSupportedPreviewSizes().iterator();
    while (localIterator.hasNext())
    {
      localObject2 = (Camera.Size)localIterator.next();
      if ((Math.abs(width / height - paramInt) <= 0.1D) && (Math.abs(height - paramInt) < d))
      {
        localObject1 = localObject2;
        d = Math.abs(height - paramInt);
      }
    }
    Object localObject2 = localObject1;
    if (localObject1 == null)
    {
      d = Double.MAX_VALUE;
      localIterator = paramParameters.getSupportedPreviewSizes().iterator();
      for (;;)
      {
        localObject2 = localObject1;
        if (!localIterator.hasNext()) {
          break;
        }
        paramParameters = (Camera.Size)localIterator.next();
        if (Math.abs(height - paramInt) < d)
        {
          localObject1 = paramParameters;
          d = Math.abs(height - paramInt);
        }
      }
    }
    return (Camera.Size)localObject2;
  }
  
  private Camera.Size getSmallestPictureSize(Camera.Parameters paramParameters)
  {
    Camera.Size localSize = null;
    Iterator localIterator = paramParameters.getSupportedPictureSizes().iterator();
    paramParameters = localSize;
    while (localIterator.hasNext())
    {
      localSize = (Camera.Size)localIterator.next();
      if (paramParameters == null)
      {
        paramParameters = localSize;
      }
      else
      {
        int i = width;
        int j = height;
        if (width * height > i * j) {
          paramParameters = localSize;
        }
      }
    }
    return paramParameters;
  }
  
  private void initPreview(int paramInt)
  {
    if ((camera != null) && (previewHolder.getSurface() != null)) {}
    try
    {
      camera.setPreviewDisplay(previewHolder);
      if (!cameraConfigured)
      {
        Camera.Parameters localParameters = camera.getParameters();
        Camera.Size localSize1 = getBestPreviewSize(paramInt, localParameters);
        Camera.Size localSize2 = getSmallestPictureSize(localParameters);
        if ((localSize1 != null) && (localSize2 != null))
        {
          localParameters.setPreviewSize(width, height);
          localParameters.setPictureSize(width, height);
          localParameters.setPictureFormat(256);
          if (localParameters.getSupportedFocusModes() != null)
          {
            if (!localParameters.getSupportedFocusModes().contains("continuous-picture")) {
              break label211;
            }
            localParameters.setFocusMode("continuous-picture");
          }
          if ((localParameters.getSupportedSceneModes() != null) && (localParameters.getSupportedSceneModes().contains("auto"))) {
            localParameters.setSceneMode("auto");
          }
          if ((localParameters.getSupportedWhiteBalance() != null) && (localParameters.getSupportedWhiteBalance().contains("auto"))) {
            localParameters.setWhiteBalance("auto");
          }
          cameraConfigured = true;
          camera.setParameters(localParameters);
        }
      }
      return;
    }
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        Log.e("PreviewDemo-surfaceCallback", "Exception in setPreviewDisplay()", localThrowable);
        continue;
        label211:
        if (localThrowable.getSupportedFocusModes().contains("auto")) {
          localThrowable.setFocusMode("auto");
        }
      }
    }
  }
  
  private float pxFromDp(float paramFloat)
  {
    return getResourcesgetDisplayMetricsdensity * paramFloat;
  }
  
  private void startPreview()
  {
    if ((cameraConfigured) && (camera != null))
    {
      camera.setDisplayOrientation(90);
      camera.startPreview();
      inPreview = true;
    }
  }
  
  public void onAccuracyChanged(Sensor paramSensor, int paramInt) {}
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(getResources().getIdentifier("nativecameraplugin", "layout", getPackageName()));
    preview = ((SurfaceView)findViewById(getResources().getIdentifier("preview", "id", getPackageName())));
    paramBundle = (Button)findViewById(getResources().getIdentifier("flipCamera", "id", getPackageName()));
    final Button localButton1 = (Button)findViewById(getResources().getIdentifier("flashButton", "id", getPackageName()));
    Button localButton2 = (Button)findViewById(getResources().getIdentifier("captureButton", "id", getPackageName()));
    final ImageView localImageView = (ImageView)findViewById(getResources().getIdentifier("viewfinder", "id", getPackageName()));
    RelativeLayout localRelativeLayout = (RelativeLayout)findViewById(getResources().getIdentifier("viewfinderArea", "id", getPackageName()));
    final int i = getResources().getIdentifier("@drawable/btn_flash_no", null, getPackageName());
    final int j = getResources().getIdentifier("@drawable/btn_flash_auto", null, getPackageName());
    final int k = getResources().getIdentifier("@drawable/btn_flash_on", null, getPackageName());
    viewfinderHalfPx = (pxFromDp(72.0F) / 2.0F);
    sm = ((SensorManager)getSystemService("sensor"));
    Object localObject;
    if (sm.getSensorList(1).size() != 0)
    {
      localObject = (Sensor)sm.getSensorList(1).get(0);
      sm.registerListener(this, (Sensor)localObject, 3);
    }
    mWindowManager = ((WindowManager)getSystemService("window"));
    if (getPackageManager().hasSystemFeature("android.hardware.camera.flash"))
    {
      localButton1.setVisibility(0);
      isFlash = true;
      if (Camera.getNumberOfCameras() <= 1) {
        break label483;
      }
      paramBundle.setVisibility(0);
    }
    for (isFrontCamera = true;; isFrontCamera = false)
    {
      localObject = getWindowManager().getDefaultDisplay();
      screenWidth = ((Display)localObject).getWidth();
      screenHeight = ((Display)localObject).getHeight();
      localRelativeLayout.setOnTouchListener(new View.OnTouchListener()
      {
        public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
        {
          float f1 = (paramAnonymousMotionEvent.getX() * 2000.0F / screenWidth - 1000.0F) * -1.0F;
          float f2 = paramAnonymousMotionEvent.getY() * 2000.0F / screenHeight - 1000.0F;
          if (((int)f2 - 100 > 64536) && ((int)f2 + 100 < 1000) && ((int)f1 - 100 > 64536) && ((int)f1 + 100 < 1000)) {}
          for (paramAnonymousView = new Rect((int)f2 - 100, (int)f1 - 100, (int)f2 + 100, (int)f1 + 100); camera == null; paramAnonymousView = new Rect(-100, -100, 100, 100)) {
            return true;
          }
          Camera.Parameters localParameters = camera.getParameters();
          if (localParameters.getMaxNumFocusAreas() > 0)
          {
            if (paramAnonymousMotionEvent.getX() - viewfinderHalfPx >= 0.0F) {
              break label281;
            }
            localImageView.setX(0.0F);
            if (paramAnonymousMotionEvent.getY() - viewfinderHalfPx >= 0.0F) {
              break label355;
            }
            localImageView.setY(0.0F);
          }
          for (;;)
          {
            paramAnonymousMotionEvent = new ArrayList();
            paramAnonymousMotionEvent.add(new Camera.Area(paramAnonymousView, 750));
            localParameters.setFocusAreas(paramAnonymousMotionEvent);
            if (localParameters.getMaxNumMeteringAreas() > 0) {
              localParameters.setMeteringAreas(paramAnonymousMotionEvent);
            }
            camera.setParameters(localParameters);
            return true;
            label281:
            if (paramAnonymousMotionEvent.getX() + viewfinderHalfPx > screenWidth)
            {
              localImageView.setX(screenWidth - viewfinderHalfPx * 2.0F);
              break;
            }
            localImageView.setX(paramAnonymousMotionEvent.getX() - viewfinderHalfPx);
            break;
            label355:
            if (paramAnonymousMotionEvent.getY() + viewfinderHalfPx > screenHeight - CameraActivity.this.pxFromDp(125.0F)) {
              localImageView.setY(screenHeight - CameraActivity.this.pxFromDp(125.0F) - viewfinderHalfPx * 2.0F);
            } else {
              localImageView.setY(paramAnonymousMotionEvent.getY() - viewfinderHalfPx);
            }
          }
        }
      });
      if (isFrontCamera) {
        paramBundle.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            if (cam == 0)
            {
              CameraActivity.access$502(CameraActivity.this, 1);
              CameraActivity.access$602(CameraActivity.this, 0);
              localImageView.setVisibility(4);
              if (isFlash) {
                localButton1.setVisibility(4);
              }
              if (isFlash) {
                localButton1.setBackgroundResource(i);
              }
            }
            for (;;)
            {
              CameraActivity.access$802(CameraActivity.this, false);
              restartPreview(cam);
              return;
              CameraActivity.access$502(CameraActivity.this, 0);
              CameraActivity.access$602(CameraActivity.this, 0);
              localImageView.setVisibility(0);
              localImageView.setX(screenWidth / 2 - viewfinderHalfPx);
              localImageView.setY(screenHeight / 2 - viewfinderHalfPx * 3.0F);
              if (isFlash) {
                localButton1.setVisibility(0);
              }
              if (isFlash) {
                localButton1.setBackgroundResource(i);
              }
            }
          }
        });
      }
      if (isFlash) {
        localButton1.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            paramAnonymousView = camera.getParameters();
            if (led == 0)
            {
              paramAnonymousView.setFlashMode("auto");
              localButton1.setBackgroundResource(j);
              CameraActivity.access$602(CameraActivity.this, 1);
            }
            for (;;)
            {
              camera.setParameters(paramAnonymousView);
              camera.startPreview();
              return;
              if (led == 1)
              {
                paramAnonymousView.setFlashMode("torch");
                localButton1.setBackgroundResource(k);
                CameraActivity.access$602(CameraActivity.this, 2);
              }
              else if (led == 2)
              {
                paramAnonymousView.setFlashMode("off");
                localButton1.setBackgroundResource(i);
                CameraActivity.access$602(CameraActivity.this, 0);
              }
            }
          }
        });
      }
      localButton2.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if ((pressed) || (camera == null)) {
            return;
          }
          paramAnonymousView = camera.getParameters();
          paramAnonymousView.setRotation(degrees);
          camera.setParameters(paramAnonymousView);
          CameraActivity.access$902(CameraActivity.this, true);
          try
          {
            camera.autoFocus(new Camera.AutoFocusCallback()
            {
              public void onAutoFocus(boolean paramAnonymous2Boolean, Camera paramAnonymous2Camera)
              {
                try
                {
                  paramAnonymous2Camera.takePicture(null, null, mPicture);
                  return;
                }
                catch (RuntimeException paramAnonymous2Camera)
                {
                  Toast.makeText(getApplicationContext(), "Error taking picture", 0).show();
                  Log.e("CameraActivity", "Auto-focus crash");
                }
              }
            });
            return;
          }
          catch (RuntimeException paramAnonymousView)
          {
            Toast.makeText(getApplicationContext(), "Error focusing", 0).show();
            Log.e("CameraActivity", "Auto-focus crash");
          }
        }
      });
      return;
      localButton1.setVisibility(4);
      isFlash = false;
      break;
      label483:
      paramBundle.setVisibility(4);
    }
  }
  
  protected void onDestroy()
  {
    sm.unregisterListener(this);
    super.onDestroy();
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramInt == 25)
    {
      paramKeyEvent = camera.getParameters();
      paramKeyEvent.setRotation(degrees);
      camera.setParameters(paramKeyEvent);
      if ((pressed) || (camera == null)) {
        return false;
      }
      pressed = true;
      try
      {
        camera.autoFocus(new Camera.AutoFocusCallback()
        {
          public void onAutoFocus(boolean paramAnonymousBoolean, Camera paramAnonymousCamera)
          {
            try
            {
              paramAnonymousCamera.takePicture(null, null, mPicture);
              return;
            }
            catch (RuntimeException paramAnonymousCamera)
            {
              Toast.makeText(getApplicationContext(), "Error taking picture", 0).show();
              Log.e("CameraActivity", "Auto-focus crash");
            }
          }
        });
        return true;
      }
      catch (RuntimeException paramKeyEvent)
      {
        Toast.makeText(getApplicationContext(), "Error focusing", 0).show();
        Log.e("CameraActivity", "Auto-focus crash");
        return true;
      }
    }
    return super.onKeyDown(paramInt, paramKeyEvent);
  }
  
  public void onPause()
  {
    if (inPreview) {
      camera.stopPreview();
    }
    camera.release();
    camera = null;
    inPreview = false;
    super.onPause();
  }
  
  public void onResume()
  {
    super.onResume();
    previewHolder = preview.getHolder();
    previewHolder.setType(3);
    previewHolder.addCallback(surfaceCallback);
    if (Camera.getNumberOfCameras() >= 1) {
      camera = Camera.open(cam);
    }
    if (preview.getHeight() > 0)
    {
      initPreview(preview.getHeight());
      startPreview();
    }
  }
  
  public void onSensorChanged(SensorEvent paramSensorEvent)
  {
    int j;
    if (sensor.getType() == 1)
    {
      paramSensorEvent = values;
      j = ORIENTATION_UNKNOWN;
      float f1 = -paramSensorEvent[0];
      float f2 = -paramSensorEvent[1];
      float f3 = -paramSensorEvent[2];
      if (4.0F * (f1 * f1 + f2 * f2) >= f3 * f3)
      {
        j = 90 - Math.round((float)Math.atan2(-f2, f1) * 57.29578F);
        int i;
        for (;;)
        {
          i = j;
          if (j < 360) {
            break;
          }
          j -= 360;
        }
        for (;;)
        {
          j = i;
          if (i >= 0) {
            break;
          }
          i += 360;
        }
      }
      if (j != mOrientationDeg)
      {
        mOrientationDeg = j;
        if (j != -1) {
          break label166;
        }
        if (cam != 1) {
          break label159;
        }
        degrees = 270;
      }
    }
    label159:
    label166:
    do
    {
      return;
      degrees = 90;
      return;
      if ((j <= 45) || (j > 315))
      {
        if (cam == 1)
        {
          degrees = 270;
          return;
        }
        degrees = 90;
        return;
      }
      if ((j > 45) && (j <= 135))
      {
        degrees = 180;
        return;
      }
      if ((j > 135) && (j <= 225))
      {
        if (cam == 1)
        {
          degrees = 90;
          return;
        }
        degrees = 270;
        return;
      }
    } while ((j <= 225) || (j > 315));
    degrees = 0;
  }
  
  void restartPreview(int paramInt)
  {
    if (inPreview) {
      camera.stopPreview();
    }
    camera.release();
    camera = Camera.open(paramInt);
    initPreview(preview.getHeight());
    startPreview();
  }
}

/* Location:
 * Qualified Name:     com.wezka.nativecamera.CameraActivity
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */