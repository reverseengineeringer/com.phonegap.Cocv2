package com.wezka.nativecamera;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore.Images.Media;
import android.util.Log;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.ExifHelper;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;
import org.json.JSONArray;
import org.json.JSONException;

public class NativeCameraLauncher
  extends CordovaPlugin
{
  private static final String LOG_TAG = "NativeCameraLauncher";
  private static final String _DATA = "_data";
  private CallbackContext callbackContext;
  private String date = null;
  private Uri imageUri;
  private int mQuality;
  private File photo;
  private int targetHeight;
  private int targetWidth;
  
  private File createCaptureFile()
  {
    Object localObject = new File(getTempDirectoryPath(cordova.getActivity().getApplicationContext()), "Pic-" + date + ".jpg");
    if (((File)localObject).exists()) {
      ((File)localObject).delete();
    }
    localObject = Calendar.getInstance();
    date = ("" + ((Calendar)localObject).get(5) + ((Calendar)localObject).get(2) + ((Calendar)localObject).get(1) + ((Calendar)localObject).get(11) + ((Calendar)localObject).get(12) + ((Calendar)localObject).get(13));
    return new File(getTempDirectoryPath(cordova.getActivity().getApplicationContext()), "Pic-" + date + ".jpg");
  }
  
  private Bitmap getRotatedBitmap(int paramInt, Bitmap paramBitmap, ExifHelper paramExifHelper)
  {
    Matrix localMatrix = new Matrix();
    localMatrix.setRotate(paramInt);
    Bitmap localBitmap = paramBitmap;
    try
    {
      paramBitmap = Bitmap.createBitmap(paramBitmap, 0, 0, paramBitmap.getWidth(), paramBitmap.getHeight(), localMatrix, true);
      localBitmap = paramBitmap;
      paramExifHelper.resetOrientation();
      return paramBitmap;
    }
    catch (OutOfMemoryError paramBitmap) {}
    return localBitmap;
  }
  
  private String getTempDirectoryPath(Context paramContext)
  {
    if (Environment.getExternalStorageState().equals("mounted")) {}
    for (paramContext = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Android/data/" + paramContext.getPackageName() + "/cache/");; paramContext = paramContext.getCacheDir())
    {
      if (!paramContext.exists()) {
        paramContext.mkdirs();
      }
      return paramContext.getAbsolutePath();
    }
  }
  
  public boolean execute(String paramString, JSONArray paramJSONArray, CallbackContext paramCallbackContext)
    throws JSONException
  {
    PluginResult.Status localStatus = PluginResult.Status.OK;
    callbackContext = paramCallbackContext;
    try
    {
      if (paramString.equals("takePicture"))
      {
        targetHeight = 0;
        targetWidth = 0;
        mQuality = 80;
        targetHeight = paramJSONArray.getInt(4);
        targetWidth = paramJSONArray.getInt(3);
        mQuality = paramJSONArray.getInt(0);
        takePicture();
        paramString = new PluginResult(PluginResult.Status.NO_RESULT);
        paramString.setKeepCallback(true);
        paramCallbackContext.sendPluginResult(paramString);
        return true;
      }
      return false;
    }
    catch (JSONException paramString)
    {
      paramString.printStackTrace();
      paramCallbackContext.sendPluginResult(new PluginResult(PluginResult.Status.JSON_EXCEPTION));
    }
    return true;
  }
  
  void failPicture(String paramString)
  {
    callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, paramString));
  }
  
  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if (paramInt2 == -1) {
      try
      {
        ExifHelper localExifHelper = new ExifHelper();
        localExifHelper.createInFile(getTempDirectoryPath(cordova.getActivity().getApplicationContext()) + "/Pic-" + date + ".jpg");
        localExifHelper.readExifData();
        paramInt1 = localExifHelper.getOrientation();
        try
        {
          Bitmap localBitmap = MediaStore.Images.Media.getBitmap(cordova.getActivity().getContentResolver(), imageUri);
          paramIntent = localBitmap;
        }
        catch (FileNotFoundException localFileNotFoundException)
        {
          for (;;)
          {
            paramIntent = paramIntent.getData();
            paramIntent = BitmapFactory.decodeStream(cordova.getActivity().getContentResolver().openInputStream(paramIntent));
          }
          paramIntent = getRotatedBitmap(paramInt1, scaleBitmap(paramIntent), localExifHelper);
          Log.i("NativeCameraLauncher", "URI: " + imageUri.toString());
          OutputStream localOutputStream = cordova.getActivity().getContentResolver().openOutputStream(imageUri);
          paramIntent.compress(Bitmap.CompressFormat.JPEG, mQuality, localOutputStream);
          localOutputStream.close();
          localExifHelper.createOutFile(imageUri.getPath());
          localExifHelper.writeExifData();
          callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, imageUri.toString()));
          paramIntent.recycle();
          System.gc();
          return;
        }
        if (paramIntent == null)
        {
          failPicture("Error decoding image.");
          return;
        }
        if (paramInt2 != 0) {
          break label300;
        }
      }
      catch (IOException paramIntent)
      {
        paramIntent.printStackTrace();
        failPicture("Error capturing image.");
        return;
      }
    }
    failPicture("Camera cancelled.");
    return;
    label300:
    failPicture("Did not complete!");
  }
  
  public Bitmap scaleBitmap(Bitmap paramBitmap)
  {
    int j = targetWidth;
    int k = targetHeight;
    int m = paramBitmap.getWidth();
    int n = paramBitmap.getHeight();
    if ((j <= 0) && (k <= 0)) {
      return paramBitmap;
    }
    int i;
    if ((j > 0) && (k <= 0)) {
      i = j * n / m;
    }
    for (;;)
    {
      return Bitmap.createScaledBitmap(paramBitmap, j, i, true);
      if ((j <= 0) && (k > 0))
      {
        j = k * m / n;
        i = k;
      }
      else
      {
        double d1 = j / k;
        double d2 = m / n;
        if (d2 > d1)
        {
          i = j * n / m;
        }
        else
        {
          i = k;
          if (d2 < d1)
          {
            j = k * m / n;
            i = k;
          }
        }
      }
    }
  }
  
  public void takePicture()
  {
    Intent localIntent = new Intent(cordova.getActivity().getApplicationContext(), CameraActivity.class);
    photo = createCaptureFile();
    imageUri = Uri.fromFile(photo);
    localIntent.putExtra("output", imageUri);
    cordova.startActivityForResult(this, localIntent, 1);
  }
}

/* Location:
 * Qualified Name:     com.wezka.nativecamera.NativeCameraLauncher
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */