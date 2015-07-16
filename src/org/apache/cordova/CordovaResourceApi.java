package org.apache.cordova;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Looper;
import android.util.Base64;
import android.webkit.MimeTypeMap;
import com.squareup.okhttp.OkHttpClient;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.util.Locale;
import org.apache.http.util.EncodingUtils;

public class CordovaResourceApi
{
  private static final String[] LOCAL_FILE_PROJECTION = { "_data" };
  private static final String LOG_TAG = "CordovaResourceApi";
  public static final int URI_TYPE_ASSET = 1;
  public static final int URI_TYPE_CONTENT = 2;
  public static final int URI_TYPE_DATA = 4;
  public static final int URI_TYPE_FILE = 0;
  public static final int URI_TYPE_HTTP = 5;
  public static final int URI_TYPE_HTTPS = 6;
  public static final int URI_TYPE_RESOURCE = 3;
  public static final int URI_TYPE_UNKNOWN = -1;
  private static OkHttpClient httpClient = new OkHttpClient();
  static Thread jsThread;
  private final AssetManager assetManager;
  private final ContentResolver contentResolver;
  private final PluginManager pluginManager;
  private boolean threadCheckingEnabled = true;
  
  public CordovaResourceApi(Context paramContext, PluginManager paramPluginManager)
  {
    contentResolver = paramContext.getContentResolver();
    assetManager = paramContext.getAssets();
    pluginManager = paramPluginManager;
  }
  
  private void assertBackgroundThread()
  {
    if (threadCheckingEnabled)
    {
      Thread localThread = Thread.currentThread();
      if (localThread == Looper.getMainLooper().getThread()) {
        throw new IllegalStateException("Do not perform IO operations on the UI thread. Use CordovaInterface.getThreadPool() instead.");
      }
      if (localThread == jsThread) {
        throw new IllegalStateException("Tried to perform an IO operation on the WebCore thread. Use CordovaInterface.getThreadPool() instead.");
      }
    }
  }
  
  private static void assertNonRelative(Uri paramUri)
  {
    if (!paramUri.isAbsolute()) {
      throw new IllegalArgumentException("Relative URIs are not supported.");
    }
  }
  
  private String getDataUriMimeType(Uri paramUri)
  {
    paramUri = paramUri.getSchemeSpecificPart();
    int i = paramUri.indexOf(',');
    if (i == -1) {}
    do
    {
      return null;
      paramUri = paramUri.substring(0, i).split(";");
    } while (paramUri.length <= 0);
    return paramUri[0];
  }
  
  private String getMimeTypeFromPath(String paramString)
  {
    int i = paramString.lastIndexOf('.');
    String str = paramString;
    if (i != -1) {
      str = paramString.substring(i + 1);
    }
    paramString = str.toLowerCase(Locale.getDefault());
    if (paramString.equals("3ga")) {
      return "audio/3gpp";
    }
    if (paramString.equals("js")) {
      return "text/javascript";
    }
    return MimeTypeMap.getSingleton().getMimeTypeFromExtension(paramString);
  }
  
  public static int getUriType(Uri paramUri)
  {
    assertNonRelative(paramUri);
    String str = paramUri.getScheme();
    if ("content".equals(str)) {
      return 2;
    }
    if ("android.resource".equals(str)) {
      return 3;
    }
    if ("file".equals(str))
    {
      if (paramUri.getPath().startsWith("/android_asset/")) {
        return 1;
      }
      return 0;
    }
    if ("data".equals(str)) {
      return 4;
    }
    if ("http".equals(str)) {
      return 5;
    }
    if ("https".equals(str)) {
      return 6;
    }
    return -1;
  }
  
  private OpenForReadResult readDataUri(Uri paramUri)
  {
    Object localObject = paramUri.getSchemeSpecificPart();
    int k = ((String)localObject).indexOf(',');
    if (k == -1) {
      return null;
    }
    String[] arrayOfString = ((String)localObject).substring(0, k).split(";");
    String str = null;
    int j = 0;
    if (arrayOfString.length > 0) {
      str = arrayOfString[0];
    }
    int i = 1;
    while (i < arrayOfString.length)
    {
      if ("base64".equalsIgnoreCase(arrayOfString[i])) {
        j = 1;
      }
      i += 1;
    }
    localObject = ((String)localObject).substring(k + 1);
    if (j != 0) {}
    for (localObject = Base64.decode((String)localObject, 0);; localObject = EncodingUtils.getBytes((String)localObject, "UTF-8")) {
      return new OpenForReadResult(paramUri, new ByteArrayInputStream((byte[])localObject), str, localObject.length, null);
    }
  }
  
  public void copyResource(Uri paramUri1, Uri paramUri2)
    throws IOException
  {
    copyResource(openForRead(paramUri1), openOutputStream(paramUri2));
  }
  
  public void copyResource(Uri paramUri, OutputStream paramOutputStream)
    throws IOException
  {
    copyResource(openForRead(paramUri), paramOutputStream);
  }
  
  /* Error */
  public void copyResource(OpenForReadResult paramOpenForReadResult, OutputStream paramOutputStream)
    throws IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: invokespecial 252	org/apache/cordova/CordovaResourceApi:assertBackgroundThread	()V
    //   4: aload_1
    //   5: getfield 256	org/apache/cordova/CordovaResourceApi$OpenForReadResult:inputStream	Ljava/io/InputStream;
    //   8: astore 8
    //   10: aload 8
    //   12: instanceof 258
    //   15: ifeq +84 -> 99
    //   18: aload_2
    //   19: instanceof 260
    //   22: ifeq +77 -> 99
    //   25: aload_1
    //   26: getfield 256	org/apache/cordova/CordovaResourceApi$OpenForReadResult:inputStream	Ljava/io/InputStream;
    //   29: checkcast 258	java/io/FileInputStream
    //   32: invokevirtual 264	java/io/FileInputStream:getChannel	()Ljava/nio/channels/FileChannel;
    //   35: astore 8
    //   37: aload_2
    //   38: checkcast 260	java/io/FileOutputStream
    //   41: invokevirtual 265	java/io/FileOutputStream:getChannel	()Ljava/nio/channels/FileChannel;
    //   44: astore 9
    //   46: lconst_0
    //   47: lstore 4
    //   49: aload_1
    //   50: getfield 269	org/apache/cordova/CordovaResourceApi$OpenForReadResult:length	J
    //   53: lstore 6
    //   55: aload_1
    //   56: getfield 273	org/apache/cordova/CordovaResourceApi$OpenForReadResult:assetFd	Landroid/content/res/AssetFileDescriptor;
    //   59: ifnull +12 -> 71
    //   62: aload_1
    //   63: getfield 273	org/apache/cordova/CordovaResourceApi$OpenForReadResult:assetFd	Landroid/content/res/AssetFileDescriptor;
    //   66: invokevirtual 279	android/content/res/AssetFileDescriptor:getStartOffset	()J
    //   69: lstore 4
    //   71: aload 9
    //   73: aload 8
    //   75: lload 4
    //   77: lload 6
    //   79: invokevirtual 285	java/nio/channels/FileChannel:transferFrom	(Ljava/nio/channels/ReadableByteChannel;JJ)J
    //   82: pop2
    //   83: aload_1
    //   84: getfield 256	org/apache/cordova/CordovaResourceApi$OpenForReadResult:inputStream	Ljava/io/InputStream;
    //   87: invokevirtual 290	java/io/InputStream:close	()V
    //   90: aload_2
    //   91: ifnull +7 -> 98
    //   94: aload_2
    //   95: invokevirtual 293	java/io/OutputStream:close	()V
    //   98: return
    //   99: sipush 8192
    //   102: newarray <illegal type>
    //   104: astore 9
    //   106: aload 8
    //   108: aload 9
    //   110: iconst_0
    //   111: sipush 8192
    //   114: invokevirtual 297	java/io/InputStream:read	([BII)I
    //   117: istore_3
    //   118: iload_3
    //   119: ifle -36 -> 83
    //   122: aload_2
    //   123: aload 9
    //   125: iconst_0
    //   126: iload_3
    //   127: invokevirtual 301	java/io/OutputStream:write	([BII)V
    //   130: goto -24 -> 106
    //   133: astore 8
    //   135: aload_1
    //   136: getfield 256	org/apache/cordova/CordovaResourceApi$OpenForReadResult:inputStream	Ljava/io/InputStream;
    //   139: invokevirtual 290	java/io/InputStream:close	()V
    //   142: aload_2
    //   143: ifnull +7 -> 150
    //   146: aload_2
    //   147: invokevirtual 293	java/io/OutputStream:close	()V
    //   150: aload 8
    //   152: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	153	0	this	CordovaResourceApi
    //   0	153	1	paramOpenForReadResult	OpenForReadResult
    //   0	153	2	paramOutputStream	OutputStream
    //   117	10	3	i	int
    //   47	29	4	l1	long
    //   53	25	6	l2	long
    //   8	99	8	localObject1	Object
    //   133	18	8	localObject2	Object
    //   44	80	9	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   4	46	133	finally
    //   49	55	133	finally
    //   55	71	133	finally
    //   71	83	133	finally
    //   99	106	133	finally
    //   106	118	133	finally
    //   122	130	133	finally
  }
  
  public HttpURLConnection createHttpConnection(Uri paramUri)
    throws IOException
  {
    assertBackgroundThread();
    return httpClient.open(new URL(paramUri.toString()));
  }
  
  public String getMimeType(Uri paramUri)
  {
    switch (getUriType(paramUri))
    {
    }
    for (;;)
    {
      return null;
      return getMimeTypeFromPath(paramUri.getPath());
      return contentResolver.getType(paramUri);
      return getDataUriMimeType(paramUri);
      try
      {
        paramUri = httpClient.open(new URL(paramUri.toString()));
        paramUri.setDoInput(false);
        paramUri.setRequestMethod("HEAD");
        paramUri = paramUri.getHeaderField("Content-Type");
        return paramUri;
      }
      catch (IOException paramUri) {}
    }
  }
  
  public boolean isThreadCheckingEnabled()
  {
    return threadCheckingEnabled;
  }
  
  public File mapUriToFile(Uri paramUri)
  {
    assertBackgroundThread();
    switch (getUriType(paramUri))
    {
    }
    do
    {
      return null;
      return new File(paramUri.getPath());
      paramUri = contentResolver.query(paramUri, LOCAL_FILE_PROJECTION, null, null, null);
    } while (paramUri == null);
    try
    {
      int i = paramUri.getColumnIndex(LOCAL_FILE_PROJECTION[0]);
      if ((i != -1) && (paramUri.getCount() > 0))
      {
        paramUri.moveToFirst();
        Object localObject1 = paramUri.getString(i);
        if (localObject1 != null)
        {
          localObject1 = new File((String)localObject1);
          return (File)localObject1;
        }
      }
      return null;
    }
    finally
    {
      paramUri.close();
    }
  }
  
  public OpenForReadResult openForRead(Uri paramUri)
    throws IOException
  {
    return openForRead(paramUri, false);
  }
  
  public OpenForReadResult openForRead(Uri paramUri, boolean paramBoolean)
    throws IOException
  {
    if (!paramBoolean) {
      assertBackgroundThread();
    }
    switch (getUriType(paramUri))
    {
    default: 
    case 0: 
    case 1: 
    case 2: 
    case 3: 
    case 4: 
      do
      {
        throw new FileNotFoundException("URI not supported by CordovaResourceApi: " + paramUri);
        localObject1 = new FileInputStream(paramUri.getPath());
        return new OpenForReadResult(paramUri, (InputStream)localObject1, getMimeTypeFromPath(paramUri.getPath()), ((FileInputStream)localObject1).getChannel().size(), null);
        String str = paramUri.getPath().substring(15);
        localObject1 = null;
        long l1 = -1L;
        try
        {
          localObject2 = assetManager.openFd(str);
          localObject1 = localObject2;
          FileInputStream localFileInputStream = ((AssetFileDescriptor)localObject2).createInputStream();
          localObject1 = localObject2;
          long l2 = ((AssetFileDescriptor)localObject2).getLength();
          l1 = l2;
          localObject1 = localObject2;
          localObject2 = localFileInputStream;
        }
        catch (FileNotFoundException localFileNotFoundException)
        {
          for (;;)
          {
            Object localObject2;
            localObject3 = assetManager.open(str);
          }
        }
        return new OpenForReadResult(paramUri, (InputStream)localObject2, getMimeTypeFromPath(str), l1, (AssetFileDescriptor)localObject1);
        localObject1 = contentResolver.getType(paramUri);
        localObject3 = contentResolver.openAssetFileDescriptor(paramUri, "r");
        return new OpenForReadResult(paramUri, ((AssetFileDescriptor)localObject3).createInputStream(), (String)localObject1, ((AssetFileDescriptor)localObject3).getLength(), (AssetFileDescriptor)localObject3);
        localObject1 = readDataUri(paramUri);
      } while (localObject1 == null);
      return (OpenForReadResult)localObject1;
    }
    Object localObject1 = httpClient.open(new URL(paramUri.toString()));
    ((HttpURLConnection)localObject1).setDoInput(true);
    Object localObject3 = ((HttpURLConnection)localObject1).getHeaderField("Content-Type");
    int i = ((HttpURLConnection)localObject1).getContentLength();
    return new OpenForReadResult(paramUri, ((HttpURLConnection)localObject1).getInputStream(), (String)localObject3, i, null);
  }
  
  public OutputStream openOutputStream(Uri paramUri)
    throws IOException
  {
    return openOutputStream(paramUri, false);
  }
  
  public OutputStream openOutputStream(Uri paramUri, boolean paramBoolean)
    throws IOException
  {
    assertBackgroundThread();
    switch (getUriType(paramUri))
    {
    case 1: 
    default: 
      throw new FileNotFoundException("URI not supported by CordovaResourceApi: " + paramUri);
    case 0: 
      paramUri = new File(paramUri.getPath());
      localObject = paramUri.getParentFile();
      if (localObject != null) {
        ((File)localObject).mkdirs();
      }
      return new FileOutputStream(paramUri, paramBoolean);
    }
    ContentResolver localContentResolver = contentResolver;
    if (paramBoolean) {}
    for (Object localObject = "wa";; localObject = "w") {
      return localContentResolver.openAssetFileDescriptor(paramUri, (String)localObject).createOutputStream();
    }
  }
  
  public String remapPath(String paramString)
  {
    return remapUri(Uri.fromFile(new File(paramString))).getPath();
  }
  
  public Uri remapUri(Uri paramUri)
  {
    assertNonRelative(paramUri);
    Uri localUri = pluginManager.remapUri(paramUri);
    if (localUri != null) {
      return localUri;
    }
    return paramUri;
  }
  
  public void setThreadCheckingEnabled(boolean paramBoolean)
  {
    threadCheckingEnabled = paramBoolean;
  }
  
  public static final class OpenForReadResult
  {
    public final AssetFileDescriptor assetFd;
    public final InputStream inputStream;
    public final long length;
    public final String mimeType;
    public final Uri uri;
    
    OpenForReadResult(Uri paramUri, InputStream paramInputStream, String paramString, long paramLong, AssetFileDescriptor paramAssetFileDescriptor)
    {
      uri = paramUri;
      inputStream = paramInputStream;
      mimeType = paramString;
      length = paramLong;
      assetFd = paramAssetFileDescriptor;
    }
  }
}

/* Location:
 * Qualified Name:     org.apache.cordova.CordovaResourceApi
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */