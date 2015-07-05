package org.apache.cordova.camera;

import android.app.Activity;
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
import android.os.Environment;
import android.provider.MediaStore.Images.Media;
import android.util.Base64;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
  public CallbackContext callbackContext;
  private MediaScannerConnection conn;
  private boolean correctOrientation;
  private int encodingType;
  private Uri imageUri;
  private int mQuality;
  private int mediaType;
  private int numPics;
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
    paramUri = cordova.getActivity().getContentResolver().query(paramUri, new String[] { "orientation" }, null, null, null);
    int i = 0;
    if (paramUri != null)
    {
      paramUri.moveToPosition(0);
      i = paramUri.getInt(0);
      paramUri.close();
    }
    return i;
  }
  
  private Bitmap getRotatedBitmap(int paramInt, Bitmap paramBitmap, ExifHelper paramExifHelper)
  {
    Matrix localMatrix = new Matrix();
    if (paramInt == 180) {
      localMatrix.setRotate(paramInt);
    }
    for (;;)
    {
      paramBitmap = Bitmap.createBitmap(paramBitmap, 0, 0, paramBitmap.getWidth(), paramBitmap.getHeight(), localMatrix, true);
      paramExifHelper.resetOrientation();
      return paramBitmap;
      localMatrix.setRotate(paramInt, paramBitmap.getWidth() / 2.0F, paramBitmap.getHeight() / 2.0F);
    }
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
    catch (UnsupportedOperationException localUnsupportedOperationException2)
    {
      LOG.d("CameraLauncher", "Can't write to external media storage.");
      try
      {
        localObject = cordova.getActivity().getContentResolver().insert(MediaStore.Images.Media.INTERNAL_CONTENT_URI, (ContentValues)localObject);
        return (Uri)localObject;
      }
      catch (UnsupportedOperationException localUnsupportedOperationException1)
      {
        LOG.d("CameraLauncher", "Can't write to internal media storage.");
      }
    }
    return null;
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
      if (i == -1)
      {
        paramUri.flush();
        paramUri.close();
        localFileInputStream.close();
        return;
      }
      paramUri.write(arrayOfByte, 0, i);
    }
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
          getImage(j, i);
        }
      }
    }
    return false;
  }
  
  public void failPicture(String paramString)
  {
    callbackContext.error(paramString);
  }
  
  public void getImage(int paramInt1, int paramInt2)
  {
    Intent localIntent = new Intent();
    String str = "Get Picture";
    if (mediaType == 0) {
      localIntent.setType("image/*");
    }
    for (;;)
    {
      localIntent.setAction("android.intent.action.GET_CONTENT");
      localIntent.addCategory("android.intent.category.OPENABLE");
      if (cordova != null) {
        cordova.startActivityForResult(this, Intent.createChooser(localIntent, new String(str)), (paramInt1 + 1) * 16 + paramInt2 + 1);
      }
      return;
      if (mediaType == 1)
      {
        localIntent.setType("video/*");
        str = "Get Video";
      }
      else if (mediaType == 2)
      {
        localIntent.setType("*/*");
        str = "Get All";
      }
    }
  }
  
  /* Error */
  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    // Byte code:
    //   0: iload_1
    //   1: bipush 16
    //   3: idiv
    //   4: iconst_1
    //   5: isub
    //   6: istore 6
    //   8: iload_1
    //   9: bipush 16
    //   11: irem
    //   12: iconst_1
    //   13: isub
    //   14: istore 5
    //   16: iconst_0
    //   17: istore 4
    //   19: iload 6
    //   21: iconst_1
    //   22: if_icmpne +535 -> 557
    //   25: iload_2
    //   26: iconst_m1
    //   27: if_icmpne +510 -> 537
    //   30: new 239	org/apache/cordova/camera/ExifHelper
    //   33: dup
    //   34: invokespecial 501	org/apache/cordova/camera/ExifHelper:<init>	()V
    //   37: astore 10
    //   39: iload 4
    //   41: istore_1
    //   42: aload_0
    //   43: getfield 416	org/apache/cordova/camera/CameraLauncher:encodingType	I
    //   46: ifne +42 -> 88
    //   49: aload 10
    //   51: new 102	java/lang/StringBuilder
    //   54: dup
    //   55: aload_0
    //   56: invokespecial 187	org/apache/cordova/camera/CameraLauncher:getTempDirectoryPath	()Ljava/lang/String;
    //   59: invokestatic 313	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   62: invokespecial 199	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   65: ldc_w 503
    //   68: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   71: invokevirtual 119	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   74: invokevirtual 506	org/apache/cordova/camera/ExifHelper:createInFile	(Ljava/lang/String;)V
    //   77: aload 10
    //   79: invokevirtual 509	org/apache/cordova/camera/ExifHelper:readExifData	()V
    //   82: aload 10
    //   84: invokevirtual 512	org/apache/cordova/camera/ExifHelper:getOrientation	()I
    //   87: istore_1
    //   88: aconst_null
    //   89: astore 9
    //   91: aconst_null
    //   92: astore 7
    //   94: aconst_null
    //   95: astore 8
    //   97: iload 5
    //   99: ifne +984 -> 1083
    //   102: aload_0
    //   103: aload_0
    //   104: getfield 386	org/apache/cordova/camera/CameraLauncher:imageUri	Landroid/net/Uri;
    //   107: invokevirtual 160	android/net/Uri:toString	()Ljava/lang/String;
    //   110: invokestatic 166	org/apache/cordova/camera/FileHelper:stripFileProtocol	(Ljava/lang/String;)Ljava/lang/String;
    //   113: invokespecial 514	org/apache/cordova/camera/CameraLauncher:getScaledBitmap	(Ljava/lang/String;)Landroid/graphics/Bitmap;
    //   116: astore 9
    //   118: aload 9
    //   120: astore 7
    //   122: aload 9
    //   124: ifnonnull +18 -> 142
    //   127: aload_3
    //   128: invokevirtual 518	android/content/Intent:getExtras	()Landroid/os/Bundle;
    //   131: ldc_w 520
    //   134: invokevirtual 526	android/os/Bundle:get	(Ljava/lang/String;)Ljava/lang/Object;
    //   137: checkcast 154	android/graphics/Bitmap
    //   140: astore 7
    //   142: aload 7
    //   144: ifnonnull +46 -> 190
    //   147: ldc 28
    //   149: ldc_w 528
    //   152: invokestatic 533	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   155: pop
    //   156: aload_0
    //   157: ldc_w 535
    //   160: invokevirtual 537	org/apache/cordova/camera/CameraLauncher:failPicture	(Ljava/lang/String;)V
    //   163: return
    //   164: astore 7
    //   166: aload 7
    //   168: invokevirtual 540	java/io/IOException:printStackTrace	()V
    //   171: iload 4
    //   173: istore_1
    //   174: goto -86 -> 88
    //   177: astore_3
    //   178: aload_3
    //   179: invokevirtual 540	java/io/IOException:printStackTrace	()V
    //   182: aload_0
    //   183: ldc_w 542
    //   186: invokevirtual 537	org/apache/cordova/camera/CameraLauncher:failPicture	(Ljava/lang/String;)V
    //   189: return
    //   190: aload 7
    //   192: astore_3
    //   193: iload_1
    //   194: ifeq +23 -> 217
    //   197: aload 7
    //   199: astore_3
    //   200: aload_0
    //   201: getfield 428	org/apache/cordova/camera/CameraLauncher:correctOrientation	Z
    //   204: ifeq +13 -> 217
    //   207: aload_0
    //   208: iload_1
    //   209: aload 7
    //   211: aload 10
    //   213: invokespecial 544	org/apache/cordova/camera/CameraLauncher:getRotatedBitmap	(ILandroid/graphics/Bitmap;Lorg/apache/cordova/camera/ExifHelper;)Landroid/graphics/Bitmap;
    //   216: astore_3
    //   217: aload_0
    //   218: aload_3
    //   219: invokevirtual 548	org/apache/cordova/camera/CameraLauncher:processPicture	(Landroid/graphics/Bitmap;)V
    //   222: aload_0
    //   223: iconst_0
    //   224: invokespecial 173	org/apache/cordova/camera/CameraLauncher:checkForDuplicateImage	(I)V
    //   227: aload_3
    //   228: astore 7
    //   230: aload_0
    //   231: iconst_1
    //   232: aload_0
    //   233: getfield 386	org/apache/cordova/camera/CameraLauncher:imageUri	Landroid/net/Uri;
    //   236: aload 8
    //   238: aload 7
    //   240: invokespecial 550	org/apache/cordova/camera/CameraLauncher:cleanup	(ILandroid/net/Uri;Landroid/net/Uri;Landroid/graphics/Bitmap;)V
    //   243: return
    //   244: aload_0
    //   245: getfield 75	org/apache/cordova/camera/CameraLauncher:saveToPhotoAlbum	Z
    //   248: ifeq +109 -> 357
    //   251: new 159	java/io/File
    //   254: dup
    //   255: aload_0
    //   256: invokespecial 552	org/apache/cordova/camera/CameraLauncher:getUriFromMediaStore	()Landroid/net/Uri;
    //   259: aload_0
    //   260: getfield 129	org/apache/cordova/camera/CameraLauncher:cordova	Lorg/apache/cordova/CordovaInterface;
    //   263: invokestatic 556	org/apache/cordova/camera/FileHelper:getRealPath	(Landroid/net/Uri;Lorg/apache/cordova/CordovaInterface;)Ljava/lang/String;
    //   266: invokespecial 169	java/io/File:<init>	(Ljava/lang/String;)V
    //   269: invokestatic 560	android/net/Uri:fromFile	(Ljava/io/File;)Landroid/net/Uri;
    //   272: astore_3
    //   273: aload_3
    //   274: ifnonnull +10 -> 284
    //   277: aload_0
    //   278: ldc_w 562
    //   281: invokevirtual 537	org/apache/cordova/camera/CameraLauncher:failPicture	(Ljava/lang/String;)V
    //   284: aload_0
    //   285: getfield 253	org/apache/cordova/camera/CameraLauncher:targetHeight	I
    //   288: iconst_m1
    //   289: if_icmpne +108 -> 397
    //   292: aload_0
    //   293: getfield 251	org/apache/cordova/camera/CameraLauncher:targetWidth	I
    //   296: iconst_m1
    //   297: if_icmpne +100 -> 397
    //   300: aload_0
    //   301: getfield 420	org/apache/cordova/camera/CameraLauncher:mQuality	I
    //   304: bipush 100
    //   306: if_icmpne +91 -> 397
    //   309: aload_0
    //   310: getfield 428	org/apache/cordova/camera/CameraLauncher:correctOrientation	Z
    //   313: ifne +84 -> 397
    //   316: aload_0
    //   317: aload_3
    //   318: invokespecial 564	org/apache/cordova/camera/CameraLauncher:writeUncompressedImage	(Landroid/net/Uri;)V
    //   321: aload_0
    //   322: getfield 412	org/apache/cordova/camera/CameraLauncher:callbackContext	Lorg/apache/cordova/CallbackContext;
    //   325: aload_3
    //   326: invokevirtual 160	android/net/Uri:toString	()Ljava/lang/String;
    //   329: invokevirtual 567	org/apache/cordova/CallbackContext:success	(Ljava/lang/String;)V
    //   332: aload 9
    //   334: astore 8
    //   336: aload_0
    //   337: getfield 412	org/apache/cordova/camera/CameraLauncher:callbackContext	Lorg/apache/cordova/CallbackContext;
    //   340: aload_3
    //   341: invokevirtual 160	android/net/Uri:toString	()Ljava/lang/String;
    //   344: invokevirtual 567	org/apache/cordova/CallbackContext:success	(Ljava/lang/String;)V
    //   347: aload 8
    //   349: astore 7
    //   351: aload_3
    //   352: astore 8
    //   354: goto -124 -> 230
    //   357: new 159	java/io/File
    //   360: dup
    //   361: aload_0
    //   362: invokespecial 187	org/apache/cordova/camera/CameraLauncher:getTempDirectoryPath	()Ljava/lang/String;
    //   365: new 102	java/lang/StringBuilder
    //   368: dup
    //   369: invokestatic 571	java/lang/System:currentTimeMillis	()J
    //   372: invokestatic 574	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   375: invokespecial 199	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   378: ldc_w 576
    //   381: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   384: invokevirtual 119	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   387: invokespecial 192	java/io/File:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   390: invokestatic 560	android/net/Uri:fromFile	(Ljava/io/File;)Landroid/net/Uri;
    //   393: astore_3
    //   394: goto -121 -> 273
    //   397: aload_0
    //   398: aload_0
    //   399: getfield 386	org/apache/cordova/camera/CameraLauncher:imageUri	Landroid/net/Uri;
    //   402: invokevirtual 160	android/net/Uri:toString	()Ljava/lang/String;
    //   405: invokestatic 166	org/apache/cordova/camera/FileHelper:stripFileProtocol	(Ljava/lang/String;)Ljava/lang/String;
    //   408: invokespecial 514	org/apache/cordova/camera/CameraLauncher:getScaledBitmap	(Ljava/lang/String;)Landroid/graphics/Bitmap;
    //   411: astore 8
    //   413: aload 8
    //   415: astore 7
    //   417: iload_1
    //   418: ifeq +25 -> 443
    //   421: aload 8
    //   423: astore 7
    //   425: aload_0
    //   426: getfield 428	org/apache/cordova/camera/CameraLauncher:correctOrientation	Z
    //   429: ifeq +14 -> 443
    //   432: aload_0
    //   433: iload_1
    //   434: aload 8
    //   436: aload 10
    //   438: invokespecial 544	org/apache/cordova/camera/CameraLauncher:getRotatedBitmap	(ILandroid/graphics/Bitmap;Lorg/apache/cordova/camera/ExifHelper;)Landroid/graphics/Bitmap;
    //   441: astore 7
    //   443: aload_0
    //   444: getfield 129	org/apache/cordova/camera/CameraLauncher:cordova	Lorg/apache/cordova/CordovaInterface;
    //   447: invokeinterface 135 1 0
    //   452: invokevirtual 141	android/app/Activity:getContentResolver	()Landroid/content/ContentResolver;
    //   455: aload_3
    //   456: invokevirtual 391	android/content/ContentResolver:openOutputStream	(Landroid/net/Uri;)Ljava/io/OutputStream;
    //   459: astore 8
    //   461: aload 7
    //   463: getstatic 581	android/graphics/Bitmap$CompressFormat:JPEG	Landroid/graphics/Bitmap$CompressFormat;
    //   466: aload_0
    //   467: getfield 420	org/apache/cordova/camera/CameraLauncher:mQuality	I
    //   470: aload 8
    //   472: invokevirtual 585	android/graphics/Bitmap:compress	(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z
    //   475: pop
    //   476: aload 8
    //   478: invokevirtual 401	java/io/OutputStream:close	()V
    //   481: aload 7
    //   483: astore 8
    //   485: aload_0
    //   486: getfield 416	org/apache/cordova/camera/CameraLauncher:encodingType	I
    //   489: ifne -153 -> 336
    //   492: aload_0
    //   493: getfield 75	org/apache/cordova/camera/CameraLauncher:saveToPhotoAlbum	Z
    //   496: ifeq +32 -> 528
    //   499: aload_3
    //   500: aload_0
    //   501: getfield 129	org/apache/cordova/camera/CameraLauncher:cordova	Lorg/apache/cordova/CordovaInterface;
    //   504: invokestatic 556	org/apache/cordova/camera/FileHelper:getRealPath	(Landroid/net/Uri;Lorg/apache/cordova/CordovaInterface;)Ljava/lang/String;
    //   507: astore 8
    //   509: aload 10
    //   511: aload 8
    //   513: invokevirtual 588	org/apache/cordova/camera/ExifHelper:createOutFile	(Ljava/lang/String;)V
    //   516: aload 10
    //   518: invokevirtual 591	org/apache/cordova/camera/ExifHelper:writeExifData	()V
    //   521: aload 7
    //   523: astore 8
    //   525: goto -189 -> 336
    //   528: aload_3
    //   529: invokevirtual 594	android/net/Uri:getPath	()Ljava/lang/String;
    //   532: astore 8
    //   534: goto -25 -> 509
    //   537: iload_2
    //   538: ifne +11 -> 549
    //   541: aload_0
    //   542: ldc_w 596
    //   545: invokevirtual 537	org/apache/cordova/camera/CameraLauncher:failPicture	(Ljava/lang/String;)V
    //   548: return
    //   549: aload_0
    //   550: ldc_w 598
    //   553: invokevirtual 537	org/apache/cordova/camera/CameraLauncher:failPicture	(Ljava/lang/String;)V
    //   556: return
    //   557: iload 6
    //   559: ifeq +9 -> 568
    //   562: iload 6
    //   564: iconst_2
    //   565: if_icmpne +517 -> 1082
    //   568: iload_2
    //   569: iconst_m1
    //   570: if_icmpne +493 -> 1063
    //   573: aload_3
    //   574: invokevirtual 601	android/content/Intent:getData	()Landroid/net/Uri;
    //   577: astore 8
    //   579: aload_0
    //   580: getfield 418	org/apache/cordova/camera/CameraLauncher:mediaType	I
    //   583: ifeq +16 -> 599
    //   586: aload_0
    //   587: getfield 412	org/apache/cordova/camera/CameraLauncher:callbackContext	Lorg/apache/cordova/CallbackContext;
    //   590: aload 8
    //   592: invokevirtual 160	android/net/Uri:toString	()Ljava/lang/String;
    //   595: invokevirtual 567	org/apache/cordova/CallbackContext:success	(Ljava/lang/String;)V
    //   598: return
    //   599: aload_0
    //   600: getfield 253	org/apache/cordova/camera/CameraLauncher:targetHeight	I
    //   603: iconst_m1
    //   604: if_icmpne +43 -> 647
    //   607: aload_0
    //   608: getfield 251	org/apache/cordova/camera/CameraLauncher:targetWidth	I
    //   611: iconst_m1
    //   612: if_icmpne +35 -> 647
    //   615: iload 5
    //   617: iconst_1
    //   618: if_icmpeq +9 -> 627
    //   621: iload 5
    //   623: iconst_2
    //   624: if_icmpne +23 -> 647
    //   627: aload_0
    //   628: getfield 428	org/apache/cordova/camera/CameraLauncher:correctOrientation	Z
    //   631: ifne +16 -> 647
    //   634: aload_0
    //   635: getfield 412	org/apache/cordova/camera/CameraLauncher:callbackContext	Lorg/apache/cordova/CallbackContext;
    //   638: aload 8
    //   640: invokevirtual 160	android/net/Uri:toString	()Ljava/lang/String;
    //   643: invokevirtual 567	org/apache/cordova/CallbackContext:success	(Ljava/lang/String;)V
    //   646: return
    //   647: aload 8
    //   649: invokevirtual 160	android/net/Uri:toString	()Ljava/lang/String;
    //   652: astore 7
    //   654: aload 7
    //   656: aload_0
    //   657: getfield 129	org/apache/cordova/camera/CameraLauncher:cordova	Lorg/apache/cordova/CordovaInterface;
    //   660: invokestatic 605	org/apache/cordova/camera/FileHelper:getMimeType	(Ljava/lang/String;Lorg/apache/cordova/CordovaInterface;)Ljava/lang/String;
    //   663: astore_3
    //   664: ldc_w 336
    //   667: aload_3
    //   668: invokevirtual 609	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   671: ifne +30 -> 701
    //   674: ldc_w 611
    //   677: aload_3
    //   678: invokevirtual 609	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   681: ifne +20 -> 701
    //   684: ldc 28
    //   686: ldc_w 528
    //   689: invokestatic 533	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   692: pop
    //   693: aload_0
    //   694: ldc_w 613
    //   697: invokevirtual 537	org/apache/cordova/camera/CameraLauncher:failPicture	(Ljava/lang/String;)V
    //   700: return
    //   701: aconst_null
    //   702: astore_3
    //   703: aload_0
    //   704: aload 7
    //   706: invokespecial 514	org/apache/cordova/camera/CameraLauncher:getScaledBitmap	(Ljava/lang/String;)Landroid/graphics/Bitmap;
    //   709: astore 7
    //   711: aload 7
    //   713: astore_3
    //   714: aload_3
    //   715: ifnonnull +30 -> 745
    //   718: ldc 28
    //   720: ldc_w 528
    //   723: invokestatic 533	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   726: pop
    //   727: aload_0
    //   728: ldc_w 535
    //   731: invokevirtual 537	org/apache/cordova/camera/CameraLauncher:failPicture	(Ljava/lang/String;)V
    //   734: return
    //   735: astore 7
    //   737: aload 7
    //   739: invokevirtual 540	java/io/IOException:printStackTrace	()V
    //   742: goto -28 -> 714
    //   745: aload_3
    //   746: astore 7
    //   748: aload_0
    //   749: getfield 428	org/apache/cordova/camera/CameraLauncher:correctOrientation	Z
    //   752: ifeq +52 -> 804
    //   755: aload_0
    //   756: aload 8
    //   758: invokespecial 615	org/apache/cordova/camera/CameraLauncher:getImageOrientation	(Landroid/net/Uri;)I
    //   761: istore_1
    //   762: aload_3
    //   763: astore 7
    //   765: iload_1
    //   766: ifeq +38 -> 804
    //   769: new 222	android/graphics/Matrix
    //   772: dup
    //   773: invokespecial 223	android/graphics/Matrix:<init>	()V
    //   776: astore 7
    //   778: aload 7
    //   780: iload_1
    //   781: i2f
    //   782: invokevirtual 227	android/graphics/Matrix:setRotate	(F)V
    //   785: aload_3
    //   786: iconst_0
    //   787: iconst_0
    //   788: aload_3
    //   789: invokevirtual 230	android/graphics/Bitmap:getWidth	()I
    //   792: aload_3
    //   793: invokevirtual 233	android/graphics/Bitmap:getHeight	()I
    //   796: aload 7
    //   798: iconst_1
    //   799: invokestatic 237	android/graphics/Bitmap:createBitmap	(Landroid/graphics/Bitmap;IIIILandroid/graphics/Matrix;Z)Landroid/graphics/Bitmap;
    //   802: astore 7
    //   804: iload 5
    //   806: ifne +23 -> 829
    //   809: aload_0
    //   810: aload 7
    //   812: invokevirtual 548	org/apache/cordova/camera/CameraLauncher:processPicture	(Landroid/graphics/Bitmap;)V
    //   815: aload 7
    //   817: ifnull +8 -> 825
    //   820: aload 7
    //   822: invokevirtual 157	android/graphics/Bitmap:recycle	()V
    //   825: invokestatic 182	java/lang/System:gc	()V
    //   828: return
    //   829: iload 5
    //   831: iconst_1
    //   832: if_icmpeq +9 -> 841
    //   835: iload 5
    //   837: iconst_2
    //   838: if_icmpne -23 -> 815
    //   841: aload_0
    //   842: getfield 253	org/apache/cordova/camera/CameraLauncher:targetHeight	I
    //   845: ifle +203 -> 1048
    //   848: aload_0
    //   849: getfield 251	org/apache/cordova/camera/CameraLauncher:targetWidth	I
    //   852: ifle +196 -> 1048
    //   855: new 102	java/lang/StringBuilder
    //   858: dup
    //   859: aload_0
    //   860: invokespecial 187	org/apache/cordova/camera/CameraLauncher:getTempDirectoryPath	()Ljava/lang/String;
    //   863: invokestatic 313	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   866: invokespecial 199	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   869: ldc_w 617
    //   872: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   875: invokevirtual 119	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   878: astore_3
    //   879: aload 8
    //   881: aload_0
    //   882: getfield 129	org/apache/cordova/camera/CameraLauncher:cordova	Lorg/apache/cordova/CordovaInterface;
    //   885: invokestatic 556	org/apache/cordova/camera/FileHelper:getRealPath	(Landroid/net/Uri;Lorg/apache/cordova/CordovaInterface;)Ljava/lang/String;
    //   888: astore 8
    //   890: new 239	org/apache/cordova/camera/ExifHelper
    //   893: dup
    //   894: invokespecial 501	org/apache/cordova/camera/ExifHelper:<init>	()V
    //   897: astore 9
    //   899: aload 8
    //   901: ifnull +30 -> 931
    //   904: aload_0
    //   905: getfield 416	org/apache/cordova/camera/CameraLauncher:encodingType	I
    //   908: istore_1
    //   909: iload_1
    //   910: ifne +21 -> 931
    //   913: aload 9
    //   915: aload 8
    //   917: invokevirtual 506	org/apache/cordova/camera/ExifHelper:createInFile	(Ljava/lang/String;)V
    //   920: aload 9
    //   922: invokevirtual 509	org/apache/cordova/camera/ExifHelper:readExifData	()V
    //   925: aload 9
    //   927: invokevirtual 512	org/apache/cordova/camera/ExifHelper:getOrientation	()I
    //   930: pop
    //   931: new 619	java/io/FileOutputStream
    //   934: dup
    //   935: aload_3
    //   936: invokespecial 620	java/io/FileOutputStream:<init>	(Ljava/lang/String;)V
    //   939: astore 10
    //   941: aload 7
    //   943: getstatic 581	android/graphics/Bitmap$CompressFormat:JPEG	Landroid/graphics/Bitmap$CompressFormat;
    //   946: aload_0
    //   947: getfield 420	org/apache/cordova/camera/CameraLauncher:mQuality	I
    //   950: aload 10
    //   952: invokevirtual 585	android/graphics/Bitmap:compress	(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z
    //   955: pop
    //   956: aload 10
    //   958: invokevirtual 401	java/io/OutputStream:close	()V
    //   961: aload 8
    //   963: ifnull +21 -> 984
    //   966: aload_0
    //   967: getfield 416	org/apache/cordova/camera/CameraLauncher:encodingType	I
    //   970: ifne +14 -> 984
    //   973: aload 9
    //   975: aload_3
    //   976: invokevirtual 588	org/apache/cordova/camera/ExifHelper:createOutFile	(Ljava/lang/String;)V
    //   979: aload 9
    //   981: invokevirtual 591	org/apache/cordova/camera/ExifHelper:writeExifData	()V
    //   984: aload_0
    //   985: getfield 412	org/apache/cordova/camera/CameraLauncher:callbackContext	Lorg/apache/cordova/CallbackContext;
    //   988: new 102	java/lang/StringBuilder
    //   991: dup
    //   992: ldc_w 622
    //   995: invokespecial 199	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   998: aload_3
    //   999: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1002: ldc_w 624
    //   1005: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1008: invokestatic 571	java/lang/System:currentTimeMillis	()J
    //   1011: invokevirtual 627	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   1014: invokevirtual 119	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1017: invokevirtual 567	org/apache/cordova/CallbackContext:success	(Ljava/lang/String;)V
    //   1020: goto -205 -> 815
    //   1023: astore_3
    //   1024: aload_3
    //   1025: invokevirtual 628	java/lang/Exception:printStackTrace	()V
    //   1028: aload_0
    //   1029: ldc_w 630
    //   1032: invokevirtual 537	org/apache/cordova/camera/CameraLauncher:failPicture	(Ljava/lang/String;)V
    //   1035: goto -220 -> 815
    //   1038: astore 10
    //   1040: aload 10
    //   1042: invokevirtual 540	java/io/IOException:printStackTrace	()V
    //   1045: goto -114 -> 931
    //   1048: aload_0
    //   1049: getfield 412	org/apache/cordova/camera/CameraLauncher:callbackContext	Lorg/apache/cordova/CallbackContext;
    //   1052: aload 8
    //   1054: invokevirtual 160	android/net/Uri:toString	()Ljava/lang/String;
    //   1057: invokevirtual 567	org/apache/cordova/CallbackContext:success	(Ljava/lang/String;)V
    //   1060: goto -245 -> 815
    //   1063: iload_2
    //   1064: ifne +11 -> 1075
    //   1067: aload_0
    //   1068: ldc_w 632
    //   1071: invokevirtual 537	org/apache/cordova/camera/CameraLauncher:failPicture	(Ljava/lang/String;)V
    //   1074: return
    //   1075: aload_0
    //   1076: ldc_w 634
    //   1079: invokevirtual 537	org/apache/cordova/camera/CameraLauncher:failPicture	(Ljava/lang/String;)V
    //   1082: return
    //   1083: iload 5
    //   1085: iconst_1
    //   1086: if_icmpeq -842 -> 244
    //   1089: iload 5
    //   1091: iconst_2
    //   1092: if_icmpne -862 -> 230
    //   1095: goto -851 -> 244
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1098	0	this	CameraLauncher
    //   0	1098	1	paramInt1	int
    //   0	1098	2	paramInt2	int
    //   0	1098	3	paramIntent	Intent
    //   17	155	4	i	int
    //   14	1079	5	j	int
    //   6	560	6	k	int
    //   92	51	7	localObject1	Object
    //   164	46	7	localIOException1	IOException
    //   228	484	7	localObject2	Object
    //   735	3	7	localIOException2	IOException
    //   746	196	7	localObject3	Object
    //   95	958	8	localObject4	Object
    //   89	891	9	localObject5	Object
    //   37	920	10	localObject6	Object
    //   1038	3	10	localIOException3	IOException
    // Exception table:
    //   from	to	target	type
    //   42	88	164	java/io/IOException
    //   30	39	177	java/io/IOException
    //   102	118	177	java/io/IOException
    //   127	142	177	java/io/IOException
    //   147	163	177	java/io/IOException
    //   166	171	177	java/io/IOException
    //   200	217	177	java/io/IOException
    //   217	227	177	java/io/IOException
    //   230	243	177	java/io/IOException
    //   244	273	177	java/io/IOException
    //   277	284	177	java/io/IOException
    //   284	332	177	java/io/IOException
    //   336	347	177	java/io/IOException
    //   357	394	177	java/io/IOException
    //   397	413	177	java/io/IOException
    //   425	443	177	java/io/IOException
    //   443	481	177	java/io/IOException
    //   485	509	177	java/io/IOException
    //   509	521	177	java/io/IOException
    //   528	534	177	java/io/IOException
    //   703	711	735	java/io/IOException
    //   855	899	1023	java/lang/Exception
    //   904	909	1023	java/lang/Exception
    //   913	931	1023	java/lang/Exception
    //   931	961	1023	java/lang/Exception
    //   966	984	1023	java/lang/Exception
    //   984	1020	1023	java/lang/Exception
    //   1040	1045	1023	java/lang/Exception
    //   913	931	1038	java/io/IOException
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