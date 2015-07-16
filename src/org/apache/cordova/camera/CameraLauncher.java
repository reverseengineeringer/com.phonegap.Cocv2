package org.apache.cordova.camera;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import android.media.MediaScannerConnection;
import android.media.MediaScannerConnection.MediaScannerConnectionClient;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore.Images.Media;
import android.util.Base64;
import android.util.Log;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.LOG;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;
import org.json.JSONArray;
import org.json.JSONException;

public class CameraLauncher
  extends CordovaPlugin
  implements MediaScannerConnection.MediaScannerConnectionClient
{
  private static final int ALLMEDIA = 2;
  private static final int CAMERA = 1;
  private static final int CROP_CAMERA = 100;
  private static final int DATA_URL = 0;
  private static final int FILE_URI = 1;
  private static final String GET_All = "Get All";
  private static final String GET_PICTURE = "Get Picture";
  private static final String GET_VIDEO = "Get Video";
  private static final int JPEG = 0;
  private static final String LOG_TAG = "CameraLauncher";
  private static final int NATIVE_URI = 2;
  private static final int PHOTOLIBRARY = 0;
  private static final int PICTURE = 0;
  private static final int PNG = 1;
  private static final int SAVEDPHOTOALBUM = 2;
  private static final int VIDEO = 1;
  private boolean allowEdit;
  public CallbackContext callbackContext;
  private MediaScannerConnection conn;
  private boolean correctOrientation;
  private Uri croppedUri;
  private int encodingType;
  private Uri imageUri;
  private int mQuality;
  private int mediaType;
  private int numPics;
  private boolean orientationCorrected;
  private boolean saveToPhotoAlbum;
  private Uri scanMe;
  private int targetHeight;
  private int targetWidth;
  
  public static int calculateSampleSize(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (paramInt1 / paramInt2 > paramInt3 / paramInt4) {
      return paramInt1 / paramInt3;
    }
    return paramInt2 / paramInt4;
  }
  
  private void checkForDuplicateImage(int paramInt)
  {
    int j = 1;
    Uri localUri = whichContentStore();
    Cursor localCursor = queryImgDB(localUri);
    int k = localCursor.getCount();
    int i = j;
    if (paramInt == 1)
    {
      i = j;
      if (saveToPhotoAlbum) {
        i = 2;
      }
    }
    if (k - numPics == i)
    {
      localCursor.moveToLast();
      j = Integer.valueOf(localCursor.getString(localCursor.getColumnIndex("_id"))).intValue();
      paramInt = j;
      if (i == 2) {
        paramInt = j - 1;
      }
      localUri = Uri.parse(localUri + "/" + paramInt);
      cordova.getActivity().getContentResolver().delete(localUri, null, null);
      localCursor.close();
    }
  }
  
  private void cleanup(int paramInt, Uri paramUri1, Uri paramUri2, Bitmap paramBitmap)
  {
    if (paramBitmap != null) {
      paramBitmap.recycle();
    }
    new File(FileHelper.stripFileProtocol(paramUri1.toString())).delete();
    checkForDuplicateImage(paramInt);
    if ((saveToPhotoAlbum) && (paramUri2 != null)) {
      scanForGallery(paramUri2);
    }
    System.gc();
  }
  
  private File createCaptureFile(int paramInt)
  {
    if (paramInt == 0) {
      return new File(getTempDirectoryPath(), ".Pic.jpg");
    }
    if (paramInt == 1) {
      return new File(getTempDirectoryPath(), ".Pic.png");
    }
    throw new IllegalArgumentException("Invalid Encoding Type: " + paramInt);
  }
  
  private int getImageOrientation(Uri paramUri)
  {
    int k = 0;
    int j = 0;
    int i = k;
    try
    {
      paramUri = cordova.getActivity().getContentResolver().query(paramUri, new String[] { "orientation" }, null, null, null);
      if (paramUri != null)
      {
        i = k;
        paramUri.moveToPosition(0);
        i = k;
        j = paramUri.getInt(0);
        i = j;
        paramUri.close();
      }
      return j;
    }
    catch (Exception paramUri) {}
    return i;
  }
  
  private Bitmap getRotatedBitmap(int paramInt, Bitmap paramBitmap, ExifHelper paramExifHelper)
  {
    Matrix localMatrix = new Matrix();
    if (paramInt == 180) {
      localMatrix.setRotate(paramInt);
    }
    Bitmap localBitmap;
    for (;;)
    {
      localBitmap = paramBitmap;
      try
      {
        paramBitmap = Bitmap.createBitmap(paramBitmap, 0, 0, paramBitmap.getWidth(), paramBitmap.getHeight(), localMatrix, true);
        localBitmap = paramBitmap;
        paramExifHelper.resetOrientation();
        return paramBitmap;
      }
      catch (OutOfMemoryError paramBitmap) {}
      localMatrix.setRotate(paramInt, paramBitmap.getWidth() / 2.0F, paramBitmap.getHeight() / 2.0F);
    }
    return localBitmap;
  }
  
  private Bitmap getScaledBitmap(String paramString)
    throws IOException
  {
    Object localObject2 = null;
    Object localObject1;
    if ((targetWidth <= 0) && (targetHeight <= 0)) {
      localObject1 = BitmapFactory.decodeStream(FileHelper.getInputStreamFromUriString(paramString, cordova));
    }
    int[] arrayOfInt;
    do
    {
      BitmapFactory.Options localOptions;
      do
      {
        do
        {
          return (Bitmap)localObject1;
          localOptions = new BitmapFactory.Options();
          inJustDecodeBounds = true;
          BitmapFactory.decodeStream(FileHelper.getInputStreamFromUriString(paramString, cordova), null, localOptions);
          localObject1 = localObject2;
        } while (outWidth == 0);
        localObject1 = localObject2;
      } while (outHeight == 0);
      arrayOfInt = calculateAspectRatio(outWidth, outHeight);
      inJustDecodeBounds = false;
      inSampleSize = calculateSampleSize(outWidth, outHeight, targetWidth, targetHeight);
      paramString = BitmapFactory.decodeStream(FileHelper.getInputStreamFromUriString(paramString, cordova), null, localOptions);
      localObject1 = localObject2;
    } while (paramString == null);
    return Bitmap.createScaledBitmap(paramString, arrayOfInt[0], arrayOfInt[1], true);
  }
  
  private String getTempDirectoryPath()
  {
    if (Environment.getExternalStorageState().equals("mounted")) {}
    for (File localFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Android/data/" + cordova.getActivity().getPackageName() + "/cache/");; localFile = cordova.getActivity().getCacheDir())
    {
      localFile.mkdirs();
      return localFile.getAbsolutePath();
    }
  }
  
  private Uri getUriFromMediaStore()
  {
    Object localObject = new ContentValues();
    ((ContentValues)localObject).put("mime_type", "image/jpeg");
    try
    {
      Uri localUri = cordova.getActivity().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, (ContentValues)localObject);
      return localUri;
    }
    catch (RuntimeException localRuntimeException2)
    {
      LOG.d("CameraLauncher", "Can't write to external media storage.");
      try
      {
        localObject = cordova.getActivity().getContentResolver().insert(MediaStore.Images.Media.INTERNAL_CONTENT_URI, (ContentValues)localObject);
        return (Uri)localObject;
      }
      catch (RuntimeException localRuntimeException1)
      {
        LOG.d("CameraLauncher", "Can't write to internal media storage.");
      }
    }
    return null;
  }
  
  private String ouputModifiedBitmap(Bitmap paramBitmap, Uri paramUri)
    throws IOException
  {
    String str = getTempDirectoryPath() + "/modified.jpg";
    FileOutputStream localFileOutputStream = new FileOutputStream(str);
    paramBitmap.compress(Bitmap.CompressFormat.JPEG, mQuality, localFileOutputStream);
    localFileOutputStream.close();
    paramBitmap = FileHelper.getRealPath(paramUri, cordova);
    paramUri = new ExifHelper();
    if ((paramBitmap != null) && (encodingType == 0)) {}
    try
    {
      paramUri.createInFile(paramBitmap);
      paramUri.readExifData();
      if ((correctOrientation) && (orientationCorrected)) {
        paramUri.resetOrientation();
      }
      paramUri.createOutFile(str);
      paramUri.writeExifData();
      return str;
    }
    catch (IOException paramBitmap)
    {
      paramBitmap.printStackTrace();
    }
    return str;
  }
  
  private void performCrop(Uri paramUri)
  {
    try
    {
      Intent localIntent = new Intent("com.android.camera.action.CROP");
      localIntent.setDataAndType(paramUri, "image/*");
      localIntent.putExtra("crop", "true");
      if (targetWidth > 0) {
        localIntent.putExtra("outputX", targetWidth);
      }
      if (targetHeight > 0) {
        localIntent.putExtra("outputY", targetHeight);
      }
      if ((targetHeight > 0) && (targetWidth > 0) && (targetWidth == targetHeight))
      {
        localIntent.putExtra("aspectX", 1);
        localIntent.putExtra("aspectY", 1);
      }
      croppedUri = Uri.fromFile(new File(getTempDirectoryPath(), System.currentTimeMillis() + ".jpg"));
      localIntent.putExtra("output", croppedUri);
      if (cordova != null) {
        cordova.startActivityForResult(this, localIntent, 100);
      }
      return;
    }
    catch (ActivityNotFoundException localActivityNotFoundException)
    {
      Log.e("CameraLauncher", "Crop operation not supported on this device");
      callbackContext.success(paramUri.toString());
    }
  }
  
  private void processResultFromCamera(int paramInt, Intent paramIntent)
    throws IOException
  {
    int j = 0;
    ExifHelper localExifHelper = new ExifHelper();
    for (;;)
    {
      int i;
      Object localObject3;
      Object localObject2;
      try
      {
        if (encodingType == 0)
        {
          localExifHelper.createInFile(getTempDirectoryPath() + "/.Pic.jpg");
          localExifHelper.readExifData();
          i = localExifHelper.getOrientation();
          Object localObject1 = null;
          localObject3 = null;
          if (paramInt != 0) {
            break label250;
          }
          Bitmap localBitmap = getScaledBitmap(FileHelper.stripFileProtocol(imageUri.toString()));
          localObject1 = localBitmap;
          if (localBitmap == null) {
            localObject1 = (Bitmap)paramIntent.getExtras().get("data");
          }
          if (localObject1 == null)
          {
            Log.d("CameraLauncher", "I either have a null image path or bitmap");
            failPicture("Unable to create bitmap!");
          }
        }
        else
        {
          i = j;
          if (encodingType != 1) {
            continue;
          }
          localExifHelper.createInFile(getTempDirectoryPath() + "/.Pic.png");
          localExifHelper.readExifData();
          i = localExifHelper.getOrientation();
          continue;
        }
      }
      catch (IOException localIOException)
      {
        localIOException.printStackTrace();
        i = j;
        continue;
        paramIntent = localIOException;
        if (i != 0)
        {
          paramIntent = localIOException;
          if (correctOrientation) {
            paramIntent = getRotatedBitmap(i, localIOException, localExifHelper);
          }
        }
        processPicture(paramIntent);
        checkForDuplicateImage(0);
        localObject2 = paramIntent;
        cleanup(1, imageUri, (Uri)localObject3, (Bitmap)localObject2);
        return;
      }
      label250:
      if ((paramInt != 1) && (paramInt != 2)) {
        break;
      }
      if (saveToPhotoAlbum) {}
      for (paramIntent = getUriFromMediaStore();; paramIntent = Uri.fromFile(new File(getTempDirectoryPath(), System.currentTimeMillis() + ".jpg"))) {
        try
        {
          paramIntent = Uri.fromFile(new File(FileHelper.getRealPath(paramIntent, cordova)));
          if (paramIntent != null) {
            break;
          }
          failPicture("Error capturing image - no media storage found.");
          return;
        }
        catch (NullPointerException paramIntent)
        {
          for (;;)
          {
            paramIntent = null;
          }
        }
      }
      if ((targetHeight == -1) && (targetWidth == -1) && (mQuality == 100) && (!correctOrientation))
      {
        writeUncompressedImage(paramIntent);
        callbackContext.success(paramIntent.toString());
        localObject3 = paramIntent;
      }
      else
      {
        localObject3 = getScaledBitmap(FileHelper.stripFileProtocol(imageUri.toString()));
        localObject2 = localObject3;
        if (i != 0)
        {
          localObject2 = localObject3;
          if (correctOrientation) {
            localObject2 = getRotatedBitmap(i, (Bitmap)localObject3, localExifHelper);
          }
        }
        localObject3 = cordova.getActivity().getContentResolver().openOutputStream(paramIntent);
        ((Bitmap)localObject2).compress(Bitmap.CompressFormat.JPEG, mQuality, (OutputStream)localObject3);
        ((OutputStream)localObject3).close();
        if (encodingType == 0) {
          if (!saveToPhotoAlbum) {
            break label541;
          }
        }
        label541:
        for (localObject3 = FileHelper.getRealPath(paramIntent, cordova);; localObject3 = paramIntent.getPath())
        {
          localExifHelper.createOutFile((String)localObject3);
          localExifHelper.writeExifData();
          if (!allowEdit) {
            break label550;
          }
          performCrop(paramIntent);
          localObject3 = paramIntent;
          break;
        }
        label550:
        callbackContext.success(paramIntent.toString());
        localObject3 = paramIntent;
      }
    }
    throw new IllegalStateException();
  }
  
  private void processResultFromGallery(int paramInt, Intent paramIntent)
  {
    paramIntent = paramIntent.getData();
    localObject2 = paramIntent;
    if (paramIntent == null)
    {
      if (croppedUri != null) {
        localObject2 = croppedUri;
      }
    }
    else
    {
      if (mediaType == 0) {
        break label53;
      }
      callbackContext.success(((Uri)localObject2).toString());
      return;
    }
    failPicture("null data from photo library");
    return;
    label53:
    if ((targetHeight == -1) && (targetWidth == -1) && ((paramInt == 1) || (paramInt == 2)) && (!correctOrientation))
    {
      callbackContext.success(((Uri)localObject2).toString());
      return;
    }
    Object localObject1 = ((Uri)localObject2).toString();
    paramIntent = FileHelper.getMimeType((String)localObject1, cordova);
    if ((!"image/jpeg".equalsIgnoreCase(paramIntent)) && (!"image/png".equalsIgnoreCase(paramIntent)))
    {
      Log.d("CameraLauncher", "I either have a null image path or bitmap");
      failPicture("Unable to retrieve path to picture!");
      return;
    }
    paramIntent = null;
    try
    {
      localObject1 = getScaledBitmap((String)localObject1);
      paramIntent = (Intent)localObject1;
    }
    catch (IOException localIOException)
    {
      for (;;)
      {
        localIOException.printStackTrace();
      }
      localIntent = paramIntent;
      if (!correctOrientation) {
        break label269;
      }
      i = getImageOrientation((Uri)localObject2);
      localIntent = paramIntent;
      if (i == 0) {
        break label269;
      }
      localMatrix = new Matrix();
      localMatrix.setRotate(i);
      localIntent = paramIntent;
    }
    if (paramIntent == null)
    {
      Log.d("CameraLauncher", "I either have a null image path or bitmap");
      failPicture("Unable to create bitmap!");
      return;
    }
    try
    {
      int i;
      Matrix localMatrix;
      paramIntent = Bitmap.createBitmap(paramIntent, 0, 0, paramIntent.getWidth(), paramIntent.getHeight(), localMatrix, true);
      localIntent = paramIntent;
      orientationCorrected = true;
      localIntent = paramIntent;
    }
    catch (OutOfMemoryError paramIntent)
    {
      for (;;)
      {
        Intent localIntent;
        label269:
        orientationCorrected = false;
        continue;
        if ((paramInt == 1) || (paramInt == 2)) {
          if (((targetHeight > 0) && (targetWidth > 0)) || ((correctOrientation) && (orientationCorrected))) {
            try
            {
              paramIntent = ouputModifiedBitmap(localIntent, (Uri)localObject2);
              callbackContext.success("file://" + paramIntent + "?" + System.currentTimeMillis());
            }
            catch (Exception paramIntent)
            {
              paramIntent.printStackTrace();
              failPicture("Error retrieving image.");
            }
          } else {
            callbackContext.success(((Uri)localObject2).toString());
          }
        }
      }
    }
    if (paramInt == 0)
    {
      processPicture(localIntent);
      if (localIntent != null) {
        localIntent.recycle();
      }
      System.gc();
      return;
    }
  }
  
  private Cursor queryImgDB(Uri paramUri)
  {
    return cordova.getActivity().getContentResolver().query(paramUri, new String[] { "_id" }, null, null, null);
  }
  
  private void scanForGallery(Uri paramUri)
  {
    scanMe = paramUri;
    if (conn != null) {
      conn.disconnect();
    }
    conn = new MediaScannerConnection(cordova.getActivity().getApplicationContext(), this);
    conn.connect();
  }
  
  private Uri whichContentStore()
  {
    if (Environment.getExternalStorageState().equals("mounted")) {
      return MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
    }
    return MediaStore.Images.Media.INTERNAL_CONTENT_URI;
  }
  
  private void writeUncompressedImage(Uri paramUri)
    throws FileNotFoundException, IOException
  {
    FileInputStream localFileInputStream = new FileInputStream(FileHelper.stripFileProtocol(imageUri.toString()));
    paramUri = cordova.getActivity().getContentResolver().openOutputStream(paramUri);
    byte[] arrayOfByte = new byte['က'];
    for (;;)
    {
      int i = localFileInputStream.read(arrayOfByte);
      if (i == -1) {
        break;
      }
      paramUri.write(arrayOfByte, 0, i);
    }
    paramUri.flush();
    paramUri.close();
    localFileInputStream.close();
  }
  
  public int[] calculateAspectRatio(int paramInt1, int paramInt2)
  {
    int j = targetWidth;
    int k = targetHeight;
    int i;
    if ((j <= 0) && (k <= 0))
    {
      j = paramInt1;
      i = paramInt2;
    }
    for (;;)
    {
      return new int[] { j, i };
      if ((j > 0) && (k <= 0))
      {
        i = j * paramInt2 / paramInt1;
      }
      else if ((j <= 0) && (k > 0))
      {
        j = k * paramInt1 / paramInt2;
        i = k;
      }
      else
      {
        double d1 = j / k;
        double d2 = paramInt1 / paramInt2;
        if (d2 > d1)
        {
          i = j * paramInt2 / paramInt1;
        }
        else
        {
          i = k;
          if (d2 < d1)
          {
            j = k * paramInt1 / paramInt2;
            i = k;
          }
        }
      }
    }
  }
  
  public boolean execute(String paramString, JSONArray paramJSONArray, CallbackContext paramCallbackContext)
    throws JSONException
  {
    callbackContext = paramCallbackContext;
    if (paramString.equals("takePicture"))
    {
      saveToPhotoAlbum = false;
      targetHeight = 0;
      targetWidth = 0;
      encodingType = 0;
      mediaType = 0;
      mQuality = 80;
      mQuality = paramJSONArray.getInt(0);
      int i = paramJSONArray.getInt(1);
      int j = paramJSONArray.getInt(2);
      targetWidth = paramJSONArray.getInt(3);
      targetHeight = paramJSONArray.getInt(4);
      encodingType = paramJSONArray.getInt(5);
      mediaType = paramJSONArray.getInt(6);
      allowEdit = paramJSONArray.getBoolean(7);
      correctOrientation = paramJSONArray.getBoolean(8);
      saveToPhotoAlbum = paramJSONArray.getBoolean(9);
      if (targetWidth < 1) {
        targetWidth = -1;
      }
      if (targetHeight < 1) {
        targetHeight = -1;
      }
      if (j == 1) {}
      for (;;)
      {
        try
        {
          takePicture(i, encodingType);
          paramString = new PluginResult(PluginResult.Status.NO_RESULT);
          paramString.setKeepCallback(true);
          paramCallbackContext.sendPluginResult(paramString);
          return true;
        }
        catch (IllegalArgumentException paramString)
        {
          paramCallbackContext.error("Illegal Argument Exception");
          paramCallbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR));
          return true;
        }
        if ((j == 0) || (j == 2)) {
          getImage(j, i, encodingType);
        }
      }
    }
    return false;
  }
  
  public void failPicture(String paramString)
  {
    callbackContext.error(paramString);
  }
  
  public void getImage(int paramInt1, int paramInt2, int paramInt3)
  {
    Intent localIntent = new Intent();
    String str = "Get Picture";
    croppedUri = null;
    if (mediaType == 0)
    {
      localIntent.setType("image/*");
      if (allowEdit)
      {
        localIntent.setAction("android.intent.action.PICK");
        localIntent.putExtra("crop", "true");
        if (targetWidth > 0) {
          localIntent.putExtra("outputX", targetWidth);
        }
        if (targetHeight > 0) {
          localIntent.putExtra("outputY", targetHeight);
        }
        if ((targetHeight > 0) && (targetWidth > 0) && (targetWidth == targetHeight))
        {
          localIntent.putExtra("aspectX", 1);
          localIntent.putExtra("aspectY", 1);
        }
        croppedUri = Uri.fromFile(createCaptureFile(paramInt3));
        localIntent.putExtra("output", croppedUri);
      }
    }
    for (;;)
    {
      if (cordova != null) {
        cordova.startActivityForResult(this, Intent.createChooser(localIntent, new String(str)), (paramInt1 + 1) * 16 + paramInt2 + 1);
      }
      return;
      localIntent.setAction("android.intent.action.GET_CONTENT");
      localIntent.addCategory("android.intent.category.OPENABLE");
      continue;
      if (mediaType == 1)
      {
        localIntent.setType("video/*");
        str = "Get Video";
        localIntent.setAction("android.intent.action.GET_CONTENT");
        localIntent.addCategory("android.intent.category.OPENABLE");
      }
      else if (mediaType == 2)
      {
        localIntent.setType("*/*");
        str = "Get All";
        localIntent.setAction("android.intent.action.GET_CONTENT");
        localIntent.addCategory("android.intent.category.OPENABLE");
      }
    }
  }
  
  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    int i = paramInt1 / 16 - 1;
    int j = paramInt1 % 16 - 1;
    if (paramInt1 == 100)
    {
      if (paramInt2 == -1)
      {
        callbackContext.success(croppedUri.toString());
        croppedUri = null;
      }
    }
    else
    {
      if (i != 1) {
        break label122;
      }
      if (paramInt2 != -1) {
        break label102;
      }
    }
    label102:
    label122:
    while ((i != 0) && (i != 2))
    {
      try
      {
        processResultFromCamera(j, paramIntent);
        return;
      }
      catch (IOException paramIntent)
      {
        paramIntent.printStackTrace();
        failPicture("Error capturing image.");
        return;
      }
      if (paramInt2 == 0)
      {
        failPicture("Camera cancelled.");
        break;
      }
      failPicture("Did not complete!");
      break;
      if (paramInt2 == 0)
      {
        failPicture("Camera cancelled.");
        return;
      }
      failPicture("Did not complete!");
      return;
    }
    if ((paramInt2 == -1) && (paramIntent != null))
    {
      processResultFromGallery(j, paramIntent);
      return;
    }
    if (paramInt2 == 0)
    {
      failPicture("Selection cancelled.");
      return;
    }
    failPicture("Selection did not complete!");
  }
  
  public void onMediaScannerConnected()
  {
    try
    {
      conn.scanFile(scanMe.toString(), "image/*");
      return;
    }
    catch (IllegalStateException localIllegalStateException)
    {
      LOG.e("CameraLauncher", "Can't scan file in MediaScanner after taking picture");
    }
  }
  
  public void onScanCompleted(String paramString, Uri paramUri)
  {
    conn.disconnect();
  }
  
  public void processPicture(Bitmap paramBitmap)
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    try
    {
      if (paramBitmap.compress(Bitmap.CompressFormat.JPEG, mQuality, localByteArrayOutputStream))
      {
        paramBitmap = new String(Base64.encode(localByteArrayOutputStream.toByteArray(), 2));
        callbackContext.success(paramBitmap);
      }
      return;
    }
    catch (Exception paramBitmap)
    {
      for (;;)
      {
        failPicture("Error compressing image.");
      }
    }
  }
  
  public void takePicture(int paramInt1, int paramInt2)
  {
    numPics = queryImgDB(whichContentStore()).getCount();
    Intent localIntent = new Intent("android.media.action.IMAGE_CAPTURE");
    File localFile = createCaptureFile(paramInt2);
    localIntent.putExtra("output", Uri.fromFile(localFile));
    imageUri = Uri.fromFile(localFile);
    if (cordova != null) {
      cordova.startActivityForResult(this, localIntent, paramInt1 + 32 + 1);
    }
  }
}

/* Location:
 * Qualified Name:     org.apache.cordova.camera.CameraLauncher
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */