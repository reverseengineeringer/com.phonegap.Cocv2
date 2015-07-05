package org.apache.cordova.mediacapture;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Environment;
import android.provider.MediaStore.Images.Media;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaResourceApi;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Capture
  extends CordovaPlugin
{
  private static final String AUDIO_3GPP = "audio/3gpp";
  private static final int CAPTURE_AUDIO = 0;
  private static final int CAPTURE_IMAGE = 1;
  private static final int CAPTURE_INTERNAL_ERR = 0;
  private static final int CAPTURE_NO_MEDIA_FILES = 3;
  private static final int CAPTURE_VIDEO = 2;
  private static final String IMAGE_JPEG = "image/jpeg";
  private static final String LOG_TAG = "Capture";
  private static final String VIDEO_3GPP = "video/3gpp";
  private static final String VIDEO_MP4 = "video/mp4";
  private CallbackContext callbackContext;
  private int duration;
  private long limit;
  private int numPics;
  private JSONArray results;
  
  private void captureAudio()
  {
    Intent localIntent = new Intent("android.provider.MediaStore.RECORD_SOUND");
    cordova.startActivityForResult(this, localIntent, 0);
  }
  
  private void captureImage()
  {
    numPics = queryImgDB(whichContentStore()).getCount();
    Intent localIntent = new Intent("android.media.action.IMAGE_CAPTURE");
    localIntent.putExtra("output", Uri.fromFile(new File(getTempDirectoryPath(), "Capture.jpg")));
    cordova.startActivityForResult(this, localIntent, 1);
  }
  
  private void captureVideo(int paramInt)
  {
    Intent localIntent = new Intent("android.media.action.VIDEO_CAPTURE");
    if (Build.VERSION.SDK_INT > 7) {
      localIntent.putExtra("android.intent.extra.durationLimit", paramInt);
    }
    cordova.startActivityForResult(this, localIntent, 2);
  }
  
  private void checkForDuplicateImage()
  {
    Uri localUri = whichContentStore();
    Cursor localCursor = queryImgDB(localUri);
    if (localCursor.getCount() - numPics == 2)
    {
      localCursor.moveToLast();
      int i = Integer.valueOf(localCursor.getString(localCursor.getColumnIndex("_id"))).intValue();
      localUri = Uri.parse(localUri + "/" + (i - 1));
      cordova.getActivity().getContentResolver().delete(localUri, null, null);
    }
  }
  
  private JSONObject createErrorObject(int paramInt, String paramString)
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("code", paramInt);
      localJSONObject.put("message", paramString);
      return localJSONObject;
    }
    catch (JSONException paramString) {}
    return localJSONObject;
  }
  
  private JSONObject createMediaFile(Uri paramUri)
  {
    File localFile = webView.getResourceApi().mapUriToFile(paramUri);
    JSONObject localJSONObject = new JSONObject();
    for (;;)
    {
      try
      {
        localJSONObject.put("name", localFile.getName());
        localJSONObject.put("fullPath", localFile.toURI().toString());
        if ((localFile.getAbsoluteFile().toString().endsWith(".3gp")) || (localFile.getAbsoluteFile().toString().endsWith(".3gpp")))
        {
          if (paramUri.toString().contains("/audio/"))
          {
            localJSONObject.put("type", "audio/3gpp");
            localJSONObject.put("lastModifiedDate", localFile.lastModified());
            localJSONObject.put("size", localFile.length());
            return localJSONObject;
          }
          localJSONObject.put("type", "video/3gpp");
          continue;
        }
        localJSONObject.put("type", FileHelper.getMimeType(Uri.fromFile(localFile), cordova));
      }
      catch (JSONException paramUri)
      {
        paramUri.printStackTrace();
        return localJSONObject;
      }
    }
  }
  
  private JSONObject getAudioVideoData(String paramString, JSONObject paramJSONObject, boolean paramBoolean)
    throws JSONException
  {
    MediaPlayer localMediaPlayer = new MediaPlayer();
    try
    {
      localMediaPlayer.setDataSource(paramString);
      localMediaPlayer.prepare();
      paramJSONObject.put("duration", localMediaPlayer.getDuration() / 1000);
      if (paramBoolean)
      {
        paramJSONObject.put("height", localMediaPlayer.getVideoHeight());
        paramJSONObject.put("width", localMediaPlayer.getVideoWidth());
      }
      return paramJSONObject;
    }
    catch (IOException paramString)
    {
      Log.d("Capture", "Error: loading video file");
    }
    return paramJSONObject;
  }
  
  private JSONObject getFormatData(String paramString1, String paramString2)
    throws JSONException
  {
    Uri localUri;
    JSONObject localJSONObject;
    String str;
    if (paramString1.startsWith("file:"))
    {
      localUri = Uri.parse(paramString1);
      localJSONObject = new JSONObject();
      localJSONObject.put("height", 0);
      localJSONObject.put("width", 0);
      localJSONObject.put("bitrate", 0);
      localJSONObject.put("duration", 0);
      localJSONObject.put("codecs", "");
      if ((paramString2 != null) && (!paramString2.equals("")))
      {
        str = paramString2;
        if (!"null".equals(paramString2)) {}
      }
      else
      {
        str = FileHelper.getMimeType(localUri, cordova);
      }
      Log.d("Capture", "Mime type = " + str);
      if ((!str.equals("image/jpeg")) && (!paramString1.endsWith(".jpg"))) {
        break label182;
      }
      paramString2 = getImageData(localUri, localJSONObject);
    }
    label182:
    do
    {
      return paramString2;
      localUri = Uri.fromFile(new File(paramString1));
      break;
      if (str.endsWith("audio/3gpp")) {
        return getAudioVideoData(paramString1, localJSONObject, false);
      }
      if (str.equals("video/3gpp")) {
        break label224;
      }
      paramString2 = localJSONObject;
    } while (!str.equals("video/mp4"));
    label224:
    return getAudioVideoData(paramString1, localJSONObject, true);
  }
  
  private JSONObject getImageData(Uri paramUri, JSONObject paramJSONObject)
    throws JSONException
  {
    BitmapFactory.Options localOptions = new BitmapFactory.Options();
    inJustDecodeBounds = true;
    BitmapFactory.decodeFile(paramUri.getPath(), localOptions);
    paramJSONObject.put("height", outHeight);
    paramJSONObject.put("width", outWidth);
    return paramJSONObject;
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
  
  private Cursor queryImgDB(Uri paramUri)
  {
    return cordova.getActivity().getContentResolver().query(paramUri, new String[] { "_id" }, null, null, null);
  }
  
  private Uri whichContentStore()
  {
    if (Environment.getExternalStorageState().equals("mounted")) {
      return MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
    }
    return MediaStore.Images.Media.INTERNAL_CONTENT_URI;
  }
  
  public boolean execute(String paramString, JSONArray paramJSONArray, CallbackContext paramCallbackContext)
    throws JSONException
  {
    callbackContext = paramCallbackContext;
    limit = 1L;
    duration = 0;
    results = new JSONArray();
    JSONObject localJSONObject = paramJSONArray.optJSONObject(0);
    if (localJSONObject != null)
    {
      limit = localJSONObject.optLong("limit", 1L);
      duration = localJSONObject.optInt("duration", 0);
    }
    if (paramString.equals("getFormatData"))
    {
      paramCallbackContext.success(getFormatData(paramJSONArray.getString(0), paramJSONArray.getString(1)));
      return true;
    }
    if (paramString.equals("captureAudio"))
    {
      captureAudio();
      return true;
    }
    if (paramString.equals("captureImage"))
    {
      captureImage();
      return true;
    }
    if (paramString.equals("captureVideo"))
    {
      captureVideo(duration);
      return true;
    }
    return false;
  }
  
  public void fail(JSONObject paramJSONObject)
  {
    callbackContext.error(paramJSONObject);
  }
  
  /* Error */
  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    // Byte code:
    //   0: iload_2
    //   1: iconst_m1
    //   2: if_icmpne +430 -> 432
    //   5: iload_1
    //   6: ifne +64 -> 70
    //   9: aload_3
    //   10: invokevirtual 463	android/content/Intent:getData	()Landroid/net/Uri;
    //   13: astore_3
    //   14: aload_0
    //   15: getfield 420	org/apache/cordova/mediacapture/Capture:results	Lorg/json/JSONArray;
    //   18: aload_0
    //   19: aload_3
    //   20: invokespecial 465	org/apache/cordova/mediacapture/Capture:createMediaFile	(Landroid/net/Uri;)Lorg/json/JSONObject;
    //   23: invokevirtual 468	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   26: pop
    //   27: aload_0
    //   28: getfield 420	org/apache/cordova/mediacapture/Capture:results	Lorg/json/JSONArray;
    //   31: invokevirtual 470	org/json/JSONArray:length	()I
    //   34: i2l
    //   35: aload_0
    //   36: getfield 413	org/apache/cordova/mediacapture/Capture:limit	J
    //   39: lcmp
    //   40: iflt +25 -> 65
    //   43: aload_0
    //   44: getfield 411	org/apache/cordova/mediacapture/Capture:callbackContext	Lorg/apache/cordova/CallbackContext;
    //   47: new 472	org/apache/cordova/PluginResult
    //   50: dup
    //   51: getstatic 478	org/apache/cordova/PluginResult$Status:OK	Lorg/apache/cordova/PluginResult$Status;
    //   54: aload_0
    //   55: getfield 420	org/apache/cordova/mediacapture/Capture:results	Lorg/json/JSONArray;
    //   58: invokespecial 481	org/apache/cordova/PluginResult:<init>	(Lorg/apache/cordova/PluginResult$Status;Lorg/json/JSONArray;)V
    //   61: invokevirtual 485	org/apache/cordova/CallbackContext:sendPluginResult	(Lorg/apache/cordova/PluginResult;)V
    //   64: return
    //   65: aload_0
    //   66: invokespecial 446	org/apache/cordova/mediacapture/Capture:captureAudio	()V
    //   69: return
    //   70: iload_1
    //   71: iconst_1
    //   72: if_icmpne +273 -> 345
    //   75: new 487	android/content/ContentValues
    //   78: dup
    //   79: invokespecial 488	android/content/ContentValues:<init>	()V
    //   82: astore 4
    //   84: aload 4
    //   86: ldc_w 490
    //   89: ldc 21
    //   91: invokevirtual 492	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   94: aload_0
    //   95: getfield 55	org/apache/cordova/mediacapture/Capture:cordova	Lorg/apache/cordova/CordovaInterface;
    //   98: invokeinterface 167 1 0
    //   103: invokevirtual 173	android/app/Activity:getContentResolver	()Landroid/content/ContentResolver;
    //   106: getstatic 404	android/provider/MediaStore$Images$Media:EXTERNAL_CONTENT_URI	Landroid/net/Uri;
    //   109: aload 4
    //   111: invokevirtual 496	android/content/ContentResolver:insert	(Landroid/net/Uri;Landroid/content/ContentValues;)Landroid/net/Uri;
    //   114: astore_3
    //   115: new 498	java/io/FileInputStream
    //   118: dup
    //   119: new 143	java/lang/StringBuilder
    //   122: dup
    //   123: aload_0
    //   124: invokespecial 88	org/apache/cordova/mediacapture/Capture:getTempDirectoryPath	()Ljava/lang/String;
    //   127: invokestatic 381	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   130: invokespecial 334	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   133: ldc_w 500
    //   136: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   139: invokevirtual 159	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   142: invokespecial 501	java/io/FileInputStream:<init>	(Ljava/lang/String;)V
    //   145: astore 4
    //   147: aload_0
    //   148: getfield 55	org/apache/cordova/mediacapture/Capture:cordova	Lorg/apache/cordova/CordovaInterface;
    //   151: invokeinterface 167 1 0
    //   156: invokevirtual 173	android/app/Activity:getContentResolver	()Landroid/content/ContentResolver;
    //   159: aload_3
    //   160: invokevirtual 505	android/content/ContentResolver:openOutputStream	(Landroid/net/Uri;)Ljava/io/OutputStream;
    //   163: astore 5
    //   165: sipush 4096
    //   168: newarray <illegal type>
    //   170: astore 6
    //   172: aload 4
    //   174: aload 6
    //   176: invokevirtual 509	java/io/FileInputStream:read	([B)I
    //   179: istore_1
    //   180: iload_1
    //   181: iconst_m1
    //   182: if_icmpne +146 -> 328
    //   185: aload 5
    //   187: invokevirtual 514	java/io/OutputStream:flush	()V
    //   190: aload 5
    //   192: invokevirtual 517	java/io/OutputStream:close	()V
    //   195: aload 4
    //   197: invokevirtual 518	java/io/FileInputStream:close	()V
    //   200: aload_0
    //   201: getfield 420	org/apache/cordova/mediacapture/Capture:results	Lorg/json/JSONArray;
    //   204: aload_0
    //   205: aload_3
    //   206: invokespecial 465	org/apache/cordova/mediacapture/Capture:createMediaFile	(Landroid/net/Uri;)Lorg/json/JSONObject;
    //   209: invokevirtual 468	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   212: pop
    //   213: aload_0
    //   214: invokespecial 520	org/apache/cordova/mediacapture/Capture:checkForDuplicateImage	()V
    //   217: aload_0
    //   218: getfield 420	org/apache/cordova/mediacapture/Capture:results	Lorg/json/JSONArray;
    //   221: invokevirtual 470	org/json/JSONArray:length	()I
    //   224: i2l
    //   225: aload_0
    //   226: getfield 413	org/apache/cordova/mediacapture/Capture:limit	J
    //   229: lcmp
    //   230: iflt +110 -> 340
    //   233: aload_0
    //   234: getfield 411	org/apache/cordova/mediacapture/Capture:callbackContext	Lorg/apache/cordova/CallbackContext;
    //   237: new 472	org/apache/cordova/PluginResult
    //   240: dup
    //   241: getstatic 478	org/apache/cordova/PluginResult$Status:OK	Lorg/apache/cordova/PluginResult$Status;
    //   244: aload_0
    //   245: getfield 420	org/apache/cordova/mediacapture/Capture:results	Lorg/json/JSONArray;
    //   248: invokespecial 481	org/apache/cordova/PluginResult:<init>	(Lorg/apache/cordova/PluginResult$Status;Lorg/json/JSONArray;)V
    //   251: invokevirtual 485	org/apache/cordova/CallbackContext:sendPluginResult	(Lorg/apache/cordova/PluginResult;)V
    //   254: return
    //   255: astore_3
    //   256: aload_3
    //   257: invokevirtual 521	java/io/IOException:printStackTrace	()V
    //   260: aload_0
    //   261: aload_0
    //   262: iconst_0
    //   263: ldc_w 523
    //   266: invokespecial 525	org/apache/cordova/mediacapture/Capture:createErrorObject	(ILjava/lang/String;)Lorg/json/JSONObject;
    //   269: invokevirtual 527	org/apache/cordova/mediacapture/Capture:fail	(Lorg/json/JSONObject;)V
    //   272: return
    //   273: astore_3
    //   274: ldc 24
    //   276: ldc_w 529
    //   279: invokestatic 533	org/apache/cordova/LOG:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   282: aload_0
    //   283: getfield 55	org/apache/cordova/mediacapture/Capture:cordova	Lorg/apache/cordova/CordovaInterface;
    //   286: invokeinterface 167 1 0
    //   291: invokevirtual 173	android/app/Activity:getContentResolver	()Landroid/content/ContentResolver;
    //   294: getstatic 407	android/provider/MediaStore$Images$Media:INTERNAL_CONTENT_URI	Landroid/net/Uri;
    //   297: aload 4
    //   299: invokevirtual 496	android/content/ContentResolver:insert	(Landroid/net/Uri;Landroid/content/ContentValues;)Landroid/net/Uri;
    //   302: astore_3
    //   303: goto -188 -> 115
    //   306: astore_3
    //   307: ldc 24
    //   309: ldc_w 535
    //   312: invokestatic 533	org/apache/cordova/LOG:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   315: aload_0
    //   316: aload_0
    //   317: iconst_0
    //   318: ldc_w 537
    //   321: invokespecial 525	org/apache/cordova/mediacapture/Capture:createErrorObject	(ILjava/lang/String;)Lorg/json/JSONObject;
    //   324: invokevirtual 527	org/apache/cordova/mediacapture/Capture:fail	(Lorg/json/JSONObject;)V
    //   327: return
    //   328: aload 5
    //   330: aload 6
    //   332: iconst_0
    //   333: iload_1
    //   334: invokevirtual 541	java/io/OutputStream:write	([BII)V
    //   337: goto -165 -> 172
    //   340: aload_0
    //   341: invokespecial 449	org/apache/cordova/mediacapture/Capture:captureImage	()V
    //   344: return
    //   345: iload_1
    //   346: iconst_2
    //   347: if_icmpne -283 -> 64
    //   350: aload_3
    //   351: invokevirtual 463	android/content/Intent:getData	()Landroid/net/Uri;
    //   354: astore_3
    //   355: aload_3
    //   356: ifnonnull +16 -> 372
    //   359: aload_0
    //   360: aload_0
    //   361: iconst_3
    //   362: ldc_w 543
    //   365: invokespecial 525	org/apache/cordova/mediacapture/Capture:createErrorObject	(ILjava/lang/String;)Lorg/json/JSONObject;
    //   368: invokevirtual 527	org/apache/cordova/mediacapture/Capture:fail	(Lorg/json/JSONObject;)V
    //   371: return
    //   372: aload_0
    //   373: getfield 420	org/apache/cordova/mediacapture/Capture:results	Lorg/json/JSONArray;
    //   376: aload_0
    //   377: aload_3
    //   378: invokespecial 465	org/apache/cordova/mediacapture/Capture:createMediaFile	(Landroid/net/Uri;)Lorg/json/JSONObject;
    //   381: invokevirtual 468	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   384: pop
    //   385: aload_0
    //   386: getfield 420	org/apache/cordova/mediacapture/Capture:results	Lorg/json/JSONArray;
    //   389: invokevirtual 470	org/json/JSONArray:length	()I
    //   392: i2l
    //   393: aload_0
    //   394: getfield 413	org/apache/cordova/mediacapture/Capture:limit	J
    //   397: lcmp
    //   398: iflt +25 -> 423
    //   401: aload_0
    //   402: getfield 411	org/apache/cordova/mediacapture/Capture:callbackContext	Lorg/apache/cordova/CallbackContext;
    //   405: new 472	org/apache/cordova/PluginResult
    //   408: dup
    //   409: getstatic 478	org/apache/cordova/PluginResult$Status:OK	Lorg/apache/cordova/PluginResult$Status;
    //   412: aload_0
    //   413: getfield 420	org/apache/cordova/mediacapture/Capture:results	Lorg/json/JSONArray;
    //   416: invokespecial 481	org/apache/cordova/PluginResult:<init>	(Lorg/apache/cordova/PluginResult$Status;Lorg/json/JSONArray;)V
    //   419: invokevirtual 485	org/apache/cordova/CallbackContext:sendPluginResult	(Lorg/apache/cordova/PluginResult;)V
    //   422: return
    //   423: aload_0
    //   424: aload_0
    //   425: getfield 415	org/apache/cordova/mediacapture/Capture:duration	I
    //   428: invokespecial 452	org/apache/cordova/mediacapture/Capture:captureVideo	(I)V
    //   431: return
    //   432: iload_2
    //   433: ifne +48 -> 481
    //   436: aload_0
    //   437: getfield 420	org/apache/cordova/mediacapture/Capture:results	Lorg/json/JSONArray;
    //   440: invokevirtual 470	org/json/JSONArray:length	()I
    //   443: ifle +25 -> 468
    //   446: aload_0
    //   447: getfield 411	org/apache/cordova/mediacapture/Capture:callbackContext	Lorg/apache/cordova/CallbackContext;
    //   450: new 472	org/apache/cordova/PluginResult
    //   453: dup
    //   454: getstatic 478	org/apache/cordova/PluginResult$Status:OK	Lorg/apache/cordova/PluginResult$Status;
    //   457: aload_0
    //   458: getfield 420	org/apache/cordova/mediacapture/Capture:results	Lorg/json/JSONArray;
    //   461: invokespecial 481	org/apache/cordova/PluginResult:<init>	(Lorg/apache/cordova/PluginResult$Status;Lorg/json/JSONArray;)V
    //   464: invokevirtual 485	org/apache/cordova/CallbackContext:sendPluginResult	(Lorg/apache/cordova/PluginResult;)V
    //   467: return
    //   468: aload_0
    //   469: aload_0
    //   470: iconst_3
    //   471: ldc_w 545
    //   474: invokespecial 525	org/apache/cordova/mediacapture/Capture:createErrorObject	(ILjava/lang/String;)Lorg/json/JSONObject;
    //   477: invokevirtual 527	org/apache/cordova/mediacapture/Capture:fail	(Lorg/json/JSONObject;)V
    //   480: return
    //   481: aload_0
    //   482: getfield 420	org/apache/cordova/mediacapture/Capture:results	Lorg/json/JSONArray;
    //   485: invokevirtual 470	org/json/JSONArray:length	()I
    //   488: ifle +25 -> 513
    //   491: aload_0
    //   492: getfield 411	org/apache/cordova/mediacapture/Capture:callbackContext	Lorg/apache/cordova/CallbackContext;
    //   495: new 472	org/apache/cordova/PluginResult
    //   498: dup
    //   499: getstatic 478	org/apache/cordova/PluginResult$Status:OK	Lorg/apache/cordova/PluginResult$Status;
    //   502: aload_0
    //   503: getfield 420	org/apache/cordova/mediacapture/Capture:results	Lorg/json/JSONArray;
    //   506: invokespecial 481	org/apache/cordova/PluginResult:<init>	(Lorg/apache/cordova/PluginResult$Status;Lorg/json/JSONArray;)V
    //   509: invokevirtual 485	org/apache/cordova/CallbackContext:sendPluginResult	(Lorg/apache/cordova/PluginResult;)V
    //   512: return
    //   513: aload_0
    //   514: aload_0
    //   515: iconst_3
    //   516: ldc_w 547
    //   519: invokespecial 525	org/apache/cordova/mediacapture/Capture:createErrorObject	(ILjava/lang/String;)Lorg/json/JSONObject;
    //   522: invokevirtual 527	org/apache/cordova/mediacapture/Capture:fail	(Lorg/json/JSONObject;)V
    //   525: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	526	0	this	Capture
    //   0	526	1	paramInt1	int
    //   0	526	2	paramInt2	int
    //   0	526	3	paramIntent	Intent
    //   82	216	4	localObject	Object
    //   163	166	5	localOutputStream	java.io.OutputStream
    //   170	161	6	arrayOfByte	byte[]
    // Exception table:
    //   from	to	target	type
    //   75	94	255	java/io/IOException
    //   94	115	255	java/io/IOException
    //   115	172	255	java/io/IOException
    //   172	180	255	java/io/IOException
    //   185	254	255	java/io/IOException
    //   274	282	255	java/io/IOException
    //   282	303	255	java/io/IOException
    //   307	327	255	java/io/IOException
    //   328	337	255	java/io/IOException
    //   340	344	255	java/io/IOException
    //   94	115	273	java/lang/UnsupportedOperationException
    //   282	303	306	java/lang/UnsupportedOperationException
  }
}

/* Location:
 * Qualified Name:     org.apache.cordova.mediacapture.Capture
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */