package org.apache.cordova.filetransfer;

import android.net.Uri;
import android.util.Log;
import java.io.Closeable;
import java.io.File;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URLConnection;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.zip.GZIPInputStream;
import java.util.zip.Inflater;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.Config;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaResourceApi;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FileTransfer
  extends CordovaPlugin
{
  public static int ABORTED_ERR = 0;
  private static final String BOUNDARY = "+++++";
  public static int CONNECTION_ERR = 0;
  private static final HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier()
  {
    public boolean verify(String paramAnonymousString, SSLSession paramAnonymousSSLSession)
    {
      return true;
    }
  };
  public static int FILE_NOT_FOUND_ERR = 1;
  public static int INVALID_URL_ERR = 2;
  private static final String LINE_END = "\r\n";
  private static final String LINE_START = "--";
  private static final String LOG_TAG = "FileTransfer";
  private static final int MAX_BUFFER_SIZE = 16384;
  public static int NOT_MODIFIED_ERR;
  private static HashMap<String, RequestContext> activeRequests;
  private static final TrustManager[] trustAllCerts = { new X509TrustManager()
  {
    public void checkClientTrusted(X509Certificate[] paramAnonymousArrayOfX509Certificate, String paramAnonymousString)
      throws CertificateException
    {}
    
    public void checkServerTrusted(X509Certificate[] paramAnonymousArrayOfX509Certificate, String paramAnonymousString)
      throws CertificateException
    {}
    
    public X509Certificate[] getAcceptedIssuers()
    {
      return new X509Certificate[0];
    }
  } };
  
  static
  {
    CONNECTION_ERR = 3;
    ABORTED_ERR = 4;
    NOT_MODIFIED_ERR = 5;
    activeRequests = new HashMap();
  }
  
  private void abort(final String paramString)
  {
    synchronized (activeRequests)
    {
      paramString = (RequestContext)activeRequests.remove(paramString);
      if (paramString != null) {
        cordova.getThreadPool().execute(new Runnable()
        {
          public void run()
          {
            synchronized (paramString)
            {
              Object localObject1 = paramStringtargetFile;
              if (localObject1 != null) {
                ((File)localObject1).delete();
              }
              localObject1 = FileTransfer.createFileTransferError(FileTransfer.ABORTED_ERR, paramStringsource, paramStringtarget, null, Integer.valueOf(-1), null);
              paramString.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, (JSONObject)localObject1));
              paramStringaborted = true;
              if (paramStringconnection != null) {
                paramStringconnection.disconnect();
              }
              return;
            }
          }
        });
      }
      return;
    }
  }
  
  private static void addHeadersToRequest(URLConnection paramURLConnection, JSONObject paramJSONObject)
  {
    try
    {
      Iterator localIterator = paramJSONObject.keys();
      while (localIterator.hasNext())
      {
        String str = localIterator.next().toString();
        JSONArray localJSONArray2 = paramJSONObject.optJSONArray(str);
        JSONArray localJSONArray1 = localJSONArray2;
        if (localJSONArray2 == null)
        {
          localJSONArray1 = new JSONArray();
          localJSONArray1.put(paramJSONObject.getString(str));
        }
        paramURLConnection.setRequestProperty(str, localJSONArray1.getString(0));
        int i = 1;
        while (i < localJSONArray1.length())
        {
          paramURLConnection.addRequestProperty(str, localJSONArray1.getString(i));
          i += 1;
        }
      }
      return;
    }
    catch (JSONException paramURLConnection) {}
  }
  
  private static JSONObject createFileTransferError(int paramInt, String paramString1, String paramString2, String paramString3, Integer paramInteger, Throwable paramThrowable)
  {
    Object localObject = null;
    try
    {
      localJSONObject = new JSONObject();
      Log.e("FileTransfer", paramString1.getMessage(), paramString1);
    }
    catch (JSONException paramString1)
    {
      try
      {
        localJSONObject.put("code", paramInt);
        localJSONObject.put("source", paramString1);
        localJSONObject.put("target", paramString2);
        if (paramString3 != null) {
          localJSONObject.put("body", paramString3);
        }
        if (paramInteger != null) {
          localJSONObject.put("http_status", paramInteger);
        }
        if (paramThrowable != null)
        {
          paramString2 = paramThrowable.getMessage();
          if (paramString2 != null)
          {
            paramString1 = paramString2;
            if (!"".equals(paramString2)) {}
          }
          else
          {
            paramString1 = paramThrowable.toString();
          }
          localJSONObject.put("exception", paramString1);
        }
        return localJSONObject;
      }
      catch (JSONException paramString1)
      {
        for (;;)
        {
          JSONObject localJSONObject;
          paramString2 = localJSONObject;
        }
      }
      paramString1 = paramString1;
      paramString2 = (String)localObject;
    }
    return paramString2;
  }
  
  /* Error */
  private static JSONObject createFileTransferError(int paramInt, String paramString1, String paramString2, URLConnection paramURLConnection, Throwable paramThrowable)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore 8
    //   3: iconst_0
    //   4: istore 7
    //   6: new 245	java/lang/StringBuilder
    //   9: dup
    //   10: invokespecial 246	java/lang/StringBuilder:<init>	()V
    //   13: astore 13
    //   15: aconst_null
    //   16: astore 12
    //   18: aconst_null
    //   19: astore 11
    //   21: aload 12
    //   23: astore 10
    //   25: iload 8
    //   27: istore 6
    //   29: aload_3
    //   30: ifnull +188 -> 218
    //   33: aload 11
    //   35: astore 9
    //   37: iload 7
    //   39: istore 5
    //   41: aload 12
    //   43: astore 10
    //   45: iload 8
    //   47: istore 6
    //   49: aload_3
    //   50: instanceof 248
    //   53: ifeq +165 -> 218
    //   56: aload 11
    //   58: astore 9
    //   60: iload 7
    //   62: istore 5
    //   64: aload_3
    //   65: checkcast 248	java/net/HttpURLConnection
    //   68: invokevirtual 251	java/net/HttpURLConnection:getResponseCode	()I
    //   71: istore 7
    //   73: aload 11
    //   75: astore 9
    //   77: iload 7
    //   79: istore 5
    //   81: aload_3
    //   82: checkcast 248	java/net/HttpURLConnection
    //   85: invokevirtual 255	java/net/HttpURLConnection:getErrorStream	()Ljava/io/InputStream;
    //   88: astore_3
    //   89: aload 12
    //   91: astore 10
    //   93: iload 7
    //   95: istore 6
    //   97: aload_3
    //   98: ifnull +120 -> 218
    //   101: aload 11
    //   103: astore 9
    //   105: iload 7
    //   107: istore 5
    //   109: new 257	java/io/BufferedReader
    //   112: dup
    //   113: new 259	java/io/InputStreamReader
    //   116: dup
    //   117: aload_3
    //   118: ldc_w 261
    //   121: invokespecial 264	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;Ljava/lang/String;)V
    //   124: invokespecial 267	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   127: astore 12
    //   129: aload 12
    //   131: invokevirtual 270	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   134: astore_3
    //   135: aload_3
    //   136: ifnull +98 -> 234
    //   139: aload 13
    //   141: aload_3
    //   142: invokevirtual 274	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   145: pop
    //   146: aload 12
    //   148: invokevirtual 270	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   151: astore 9
    //   153: aload 9
    //   155: astore_3
    //   156: aload 9
    //   158: ifnull -23 -> 135
    //   161: aload 13
    //   163: bipush 10
    //   165: invokevirtual 277	java/lang/StringBuilder:append	(C)Ljava/lang/StringBuilder;
    //   168: pop
    //   169: aload 9
    //   171: astore_3
    //   172: goto -37 -> 135
    //   175: astore_3
    //   176: aload 11
    //   178: astore 9
    //   180: iload 7
    //   182: istore 5
    //   184: aload 12
    //   186: invokevirtual 280	java/io/BufferedReader:close	()V
    //   189: aload 11
    //   191: astore 9
    //   193: iload 7
    //   195: istore 5
    //   197: aload_3
    //   198: athrow
    //   199: astore_3
    //   200: ldc 50
    //   202: ldc_w 282
    //   205: aload_3
    //   206: invokestatic 285	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   209: pop
    //   210: iload 5
    //   212: istore 6
    //   214: aload 9
    //   216: astore 10
    //   218: iload_0
    //   219: aload_1
    //   220: aload_2
    //   221: aload 10
    //   223: iload 6
    //   225: invokestatic 291	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   228: aload 4
    //   230: invokestatic 148	org/apache/cordova/filetransfer/FileTransfer:createFileTransferError	(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Throwable;)Lorg/json/JSONObject;
    //   233: areturn
    //   234: aload 13
    //   236: invokevirtual 292	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   239: astore 10
    //   241: aload 10
    //   243: astore 9
    //   245: iload 7
    //   247: istore 5
    //   249: aload 12
    //   251: invokevirtual 280	java/io/BufferedReader:close	()V
    //   254: iload 7
    //   256: istore 6
    //   258: goto -40 -> 218
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	261	0	paramInt	int
    //   0	261	1	paramString1	String
    //   0	261	2	paramString2	String
    //   0	261	3	paramURLConnection	URLConnection
    //   0	261	4	paramThrowable	Throwable
    //   39	209	5	i	int
    //   27	230	6	j	int
    //   4	251	7	k	int
    //   1	45	8	m	int
    //   35	209	9	localObject1	Object
    //   23	219	10	localObject2	Object
    //   19	171	11	localObject3	Object
    //   16	234	12	localBufferedReader	java.io.BufferedReader
    //   13	222	13	localStringBuilder	StringBuilder
    // Exception table:
    //   from	to	target	type
    //   129	135	175	finally
    //   139	153	175	finally
    //   161	169	175	finally
    //   234	241	175	finally
    //   49	56	199	java/lang/Throwable
    //   64	73	199	java/lang/Throwable
    //   81	89	199	java/lang/Throwable
    //   109	129	199	java/lang/Throwable
    //   184	189	199	java/lang/Throwable
    //   197	199	199	java/lang/Throwable
    //   249	254	199	java/lang/Throwable
  }
  
  private void download(final String paramString1, final String paramString2, final JSONArray paramJSONArray, CallbackContext arg4)
    throws JSONException
  {
    Log.d("FileTransfer", "download " + paramString1 + " to " + paramString2);
    final CordovaResourceApi localCordovaResourceApi = webView.getResourceApi();
    final boolean bool3 = paramJSONArray.optBoolean(2);
    final String str = paramJSONArray.getString(3);
    final JSONObject localJSONObject = paramJSONArray.optJSONObject(4);
    final Uri localUri = localCordovaResourceApi.remapUri(Uri.parse(paramString1));
    paramJSONArray = Uri.parse(paramString2);
    int i;
    final boolean bool1;
    if (paramJSONArray.getScheme() != null)
    {
      paramJSONArray = localCordovaResourceApi.remapUri(paramJSONArray);
      i = CordovaResourceApi.getUriType(localUri);
      if (i != 6) {
        break label206;
      }
      bool1 = true;
      label113:
      if ((bool1) || (i == 5)) {
        break label212;
      }
    }
    label206:
    label212:
    for (final boolean bool2 = true;; bool2 = false)
    {
      if (i != -1) {
        break label218;
      }
      paramString1 = createFileTransferError(INVALID_URL_ERR, paramString1, paramString2, null, Integer.valueOf(0), null);
      Log.e("FileTransfer", "Unsupported URI: " + paramJSONArray);
      ???.sendPluginResult(new PluginResult(PluginResult.Status.IO_EXCEPTION, paramString1));
      return;
      paramJSONArray = Uri.fromFile(new File(paramString2));
      break;
      bool1 = false;
      break label113;
    }
    label218:
    if ((!bool2) && (!Config.isUrlWhiteListed(paramString1)))
    {
      Log.w("FileTransfer", "Source URL is not in white list: '" + paramString1 + "'");
      paramString1 = createFileTransferError(CONNECTION_ERR, paramString1, paramString2, null, Integer.valueOf(401), null);
      ???.sendPluginResult(new PluginResult(PluginResult.Status.IO_EXCEPTION, paramString1));
      return;
    }
    final RequestContext localRequestContext = new RequestContext(paramString1, paramString2, ???);
    synchronized (activeRequests)
    {
      activeRequests.put(str, localRequestContext);
      cordova.getThreadPool().execute(new Runnable()
      {
        /* Error */
        public void run()
        {
          // Byte code:
          //   0: aload_0
          //   1: getfield 36	org/apache/cordova/filetransfer/FileTransfer$4:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
          //   4: getfield 82	org/apache/cordova/filetransfer/FileTransfer$RequestContext:aborted	Z
          //   7: ifeq +4 -> 11
          //   10: return
          //   11: aconst_null
          //   12: astore 13
          //   14: aconst_null
          //   15: astore 52
          //   17: aconst_null
          //   18: astore 53
          //   20: aconst_null
          //   21: astore 54
          //   23: aconst_null
          //   24: astore 55
          //   26: aconst_null
          //   27: astore 16
          //   29: aconst_null
          //   30: astore 50
          //   32: aconst_null
          //   33: astore 40
          //   35: aconst_null
          //   36: astore 41
          //   38: aconst_null
          //   39: astore 42
          //   41: aconst_null
          //   42: astore 43
          //   44: aconst_null
          //   45: astore 44
          //   47: aconst_null
          //   48: astore 39
          //   50: aconst_null
          //   51: astore 51
          //   53: aconst_null
          //   54: astore 45
          //   56: aconst_null
          //   57: astore 46
          //   59: aconst_null
          //   60: astore 47
          //   62: aconst_null
          //   63: astore 48
          //   65: aconst_null
          //   66: astore 49
          //   68: aconst_null
          //   69: astore 14
          //   71: aconst_null
          //   72: astore 30
          //   74: aconst_null
          //   75: astore 32
          //   77: aconst_null
          //   78: astore 33
          //   80: aconst_null
          //   81: astore 34
          //   83: aconst_null
          //   84: astore 29
          //   86: aconst_null
          //   87: astore 37
          //   89: aconst_null
          //   90: astore 38
          //   92: iconst_0
          //   93: istore 7
          //   95: iconst_0
          //   96: istore 8
          //   98: iconst_0
          //   99: istore 9
          //   101: iconst_0
          //   102: istore 10
          //   104: iconst_0
          //   105: istore 11
          //   107: iconst_0
          //   108: istore 6
          //   110: aconst_null
          //   111: astore 36
          //   113: iload 7
          //   115: istore_1
          //   116: aload 13
          //   118: astore 26
          //   120: aload 40
          //   122: astore 15
          //   124: aload 45
          //   126: astore 17
          //   128: iload 8
          //   130: istore_2
          //   131: aload 52
          //   133: astore 27
          //   135: aload 41
          //   137: astore 18
          //   139: aload 46
          //   141: astore 19
          //   143: iload 9
          //   145: istore_3
          //   146: aload 53
          //   148: astore 28
          //   150: aload 42
          //   152: astore 20
          //   154: aload 47
          //   156: astore 22
          //   158: iload 10
          //   160: istore 4
          //   162: aload 54
          //   164: astore 31
          //   166: aload 43
          //   168: astore 23
          //   170: aload 48
          //   172: astore 25
          //   174: iload 11
          //   176: istore 5
          //   178: aload 55
          //   180: astore 35
          //   182: aload 44
          //   184: astore 24
          //   186: aload 49
          //   188: astore 21
          //   190: aload_0
          //   191: getfield 38	org/apache/cordova/filetransfer/FileTransfer$4:val$resourceApi	Lorg/apache/cordova/CordovaResourceApi;
          //   194: aload_0
          //   195: getfield 40	org/apache/cordova/filetransfer/FileTransfer$4:val$targetUri	Landroid/net/Uri;
          //   198: invokevirtual 88	org/apache/cordova/CordovaResourceApi:mapUriToFile	(Landroid/net/Uri;)Ljava/io/File;
          //   201: astore 12
          //   203: iload 7
          //   205: istore_1
          //   206: aload 13
          //   208: astore 26
          //   210: aload 12
          //   212: astore 29
          //   214: aload 40
          //   216: astore 15
          //   218: aload 45
          //   220: astore 17
          //   222: iload 8
          //   224: istore_2
          //   225: aload 52
          //   227: astore 27
          //   229: aload 12
          //   231: astore 30
          //   233: aload 41
          //   235: astore 18
          //   237: aload 46
          //   239: astore 19
          //   241: iload 9
          //   243: istore_3
          //   244: aload 53
          //   246: astore 28
          //   248: aload 12
          //   250: astore 32
          //   252: aload 42
          //   254: astore 20
          //   256: aload 47
          //   258: astore 22
          //   260: iload 10
          //   262: istore 4
          //   264: aload 54
          //   266: astore 31
          //   268: aload 12
          //   270: astore 33
          //   272: aload 43
          //   274: astore 23
          //   276: aload 48
          //   278: astore 25
          //   280: iload 11
          //   282: istore 5
          //   284: aload 55
          //   286: astore 35
          //   288: aload 12
          //   290: astore 34
          //   292: aload 44
          //   294: astore 24
          //   296: aload 49
          //   298: astore 21
          //   300: aload_0
          //   301: getfield 36	org/apache/cordova/filetransfer/FileTransfer$4:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
          //   304: aload 12
          //   306: putfield 92	org/apache/cordova/filetransfer/FileTransfer$RequestContext:targetFile	Ljava/io/File;
          //   309: iload 7
          //   311: istore_1
          //   312: aload 13
          //   314: astore 26
          //   316: aload 12
          //   318: astore 29
          //   320: aload 40
          //   322: astore 15
          //   324: aload 45
          //   326: astore 17
          //   328: iload 8
          //   330: istore_2
          //   331: aload 52
          //   333: astore 27
          //   335: aload 12
          //   337: astore 30
          //   339: aload 41
          //   341: astore 18
          //   343: aload 46
          //   345: astore 19
          //   347: iload 9
          //   349: istore_3
          //   350: aload 53
          //   352: astore 28
          //   354: aload 12
          //   356: astore 32
          //   358: aload 42
          //   360: astore 20
          //   362: aload 47
          //   364: astore 22
          //   366: iload 10
          //   368: istore 4
          //   370: aload 54
          //   372: astore 31
          //   374: aload 12
          //   376: astore 33
          //   378: aload 43
          //   380: astore 23
          //   382: aload 48
          //   384: astore 25
          //   386: iload 11
          //   388: istore 5
          //   390: aload 55
          //   392: astore 35
          //   394: aload 12
          //   396: astore 34
          //   398: aload 44
          //   400: astore 24
          //   402: aload 49
          //   404: astore 21
          //   406: ldc 94
          //   408: new 96	java/lang/StringBuilder
          //   411: dup
          //   412: invokespecial 97	java/lang/StringBuilder:<init>	()V
          //   415: ldc 99
          //   417: invokevirtual 103	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   420: aload_0
          //   421: getfield 42	org/apache/cordova/filetransfer/FileTransfer$4:val$sourceUri	Landroid/net/Uri;
          //   424: invokevirtual 106	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
          //   427: invokevirtual 110	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   430: invokestatic 116	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
          //   433: pop
          //   434: iload 7
          //   436: istore_1
          //   437: aload 13
          //   439: astore 26
          //   441: aload 12
          //   443: astore 29
          //   445: aload 40
          //   447: astore 15
          //   449: aload 45
          //   451: astore 17
          //   453: iload 8
          //   455: istore_2
          //   456: aload 52
          //   458: astore 27
          //   460: aload 12
          //   462: astore 30
          //   464: aload 41
          //   466: astore 18
          //   468: aload 46
          //   470: astore 19
          //   472: iload 9
          //   474: istore_3
          //   475: aload 53
          //   477: astore 28
          //   479: aload 12
          //   481: astore 32
          //   483: aload 42
          //   485: astore 20
          //   487: aload 47
          //   489: astore 22
          //   491: iload 10
          //   493: istore 4
          //   495: aload 54
          //   497: astore 31
          //   499: aload 12
          //   501: astore 33
          //   503: aload 43
          //   505: astore 23
          //   507: aload 48
          //   509: astore 25
          //   511: iload 11
          //   513: istore 5
          //   515: aload 55
          //   517: astore 35
          //   519: aload 12
          //   521: astore 34
          //   523: aload 44
          //   525: astore 24
          //   527: aload 49
          //   529: astore 21
          //   531: new 118	org/apache/cordova/filetransfer/FileProgressResult
          //   534: dup
          //   535: invokespecial 119	org/apache/cordova/filetransfer/FileProgressResult:<init>	()V
          //   538: astore 56
          //   540: iload 7
          //   542: istore_1
          //   543: aload 13
          //   545: astore 26
          //   547: aload 12
          //   549: astore 29
          //   551: aload 40
          //   553: astore 15
          //   555: aload 45
          //   557: astore 17
          //   559: iload 8
          //   561: istore_2
          //   562: aload 52
          //   564: astore 27
          //   566: aload 12
          //   568: astore 30
          //   570: aload 41
          //   572: astore 18
          //   574: aload 46
          //   576: astore 19
          //   578: iload 9
          //   580: istore_3
          //   581: aload 53
          //   583: astore 28
          //   585: aload 12
          //   587: astore 32
          //   589: aload 42
          //   591: astore 20
          //   593: aload 47
          //   595: astore 22
          //   597: iload 10
          //   599: istore 4
          //   601: aload 54
          //   603: astore 31
          //   605: aload 12
          //   607: astore 33
          //   609: aload 43
          //   611: astore 23
          //   613: aload 48
          //   615: astore 25
          //   617: iload 11
          //   619: istore 5
          //   621: aload 55
          //   623: astore 35
          //   625: aload 12
          //   627: astore 34
          //   629: aload 44
          //   631: astore 24
          //   633: aload 49
          //   635: astore 21
          //   637: aload_0
          //   638: getfield 44	org/apache/cordova/filetransfer/FileTransfer$4:val$isLocalTransfer	Z
          //   641: ifeq +915 -> 1556
          //   644: iload 7
          //   646: istore_1
          //   647: aload 13
          //   649: astore 26
          //   651: aload 12
          //   653: astore 29
          //   655: aload 40
          //   657: astore 15
          //   659: aload 45
          //   661: astore 17
          //   663: iload 8
          //   665: istore_2
          //   666: aload 52
          //   668: astore 27
          //   670: aload 12
          //   672: astore 30
          //   674: aload 41
          //   676: astore 18
          //   678: aload 46
          //   680: astore 19
          //   682: iload 9
          //   684: istore_3
          //   685: aload 53
          //   687: astore 28
          //   689: aload 12
          //   691: astore 32
          //   693: aload 42
          //   695: astore 20
          //   697: aload 47
          //   699: astore 22
          //   701: iload 10
          //   703: istore 4
          //   705: aload 54
          //   707: astore 31
          //   709: aload 12
          //   711: astore 33
          //   713: aload 43
          //   715: astore 23
          //   717: aload 48
          //   719: astore 25
          //   721: iload 11
          //   723: istore 5
          //   725: aload 55
          //   727: astore 35
          //   729: aload 12
          //   731: astore 34
          //   733: aload 44
          //   735: astore 24
          //   737: aload 49
          //   739: astore 21
          //   741: aload_0
          //   742: getfield 38	org/apache/cordova/filetransfer/FileTransfer$4:val$resourceApi	Lorg/apache/cordova/CordovaResourceApi;
          //   745: aload_0
          //   746: getfield 42	org/apache/cordova/filetransfer/FileTransfer$4:val$sourceUri	Landroid/net/Uri;
          //   749: invokevirtual 123	org/apache/cordova/CordovaResourceApi:openForRead	(Landroid/net/Uri;)Lorg/apache/cordova/CordovaResourceApi$OpenForReadResult;
          //   752: astore 38
          //   754: iload 7
          //   756: istore_1
          //   757: aload 13
          //   759: astore 26
          //   761: aload 12
          //   763: astore 29
          //   765: aload 40
          //   767: astore 15
          //   769: aload 45
          //   771: astore 17
          //   773: iload 8
          //   775: istore_2
          //   776: aload 52
          //   778: astore 27
          //   780: aload 12
          //   782: astore 30
          //   784: aload 41
          //   786: astore 18
          //   788: aload 46
          //   790: astore 19
          //   792: iload 9
          //   794: istore_3
          //   795: aload 53
          //   797: astore 28
          //   799: aload 12
          //   801: astore 32
          //   803: aload 42
          //   805: astore 20
          //   807: aload 47
          //   809: astore 22
          //   811: iload 10
          //   813: istore 4
          //   815: aload 54
          //   817: astore 31
          //   819: aload 12
          //   821: astore 33
          //   823: aload 43
          //   825: astore 23
          //   827: aload 48
          //   829: astore 25
          //   831: iload 11
          //   833: istore 5
          //   835: aload 55
          //   837: astore 35
          //   839: aload 12
          //   841: astore 34
          //   843: aload 44
          //   845: astore 24
          //   847: aload 49
          //   849: astore 21
          //   851: aload 38
          //   853: getfield 129	org/apache/cordova/CordovaResourceApi$OpenForReadResult:length	J
          //   856: ldc2_w 130
          //   859: lcmp
          //   860: ifeq +213 -> 1073
          //   863: iload 7
          //   865: istore_1
          //   866: aload 13
          //   868: astore 26
          //   870: aload 12
          //   872: astore 29
          //   874: aload 40
          //   876: astore 15
          //   878: aload 45
          //   880: astore 17
          //   882: iload 8
          //   884: istore_2
          //   885: aload 52
          //   887: astore 27
          //   889: aload 12
          //   891: astore 30
          //   893: aload 41
          //   895: astore 18
          //   897: aload 46
          //   899: astore 19
          //   901: iload 9
          //   903: istore_3
          //   904: aload 53
          //   906: astore 28
          //   908: aload 12
          //   910: astore 32
          //   912: aload 42
          //   914: astore 20
          //   916: aload 47
          //   918: astore 22
          //   920: iload 10
          //   922: istore 4
          //   924: aload 54
          //   926: astore 31
          //   928: aload 12
          //   930: astore 33
          //   932: aload 43
          //   934: astore 23
          //   936: aload 48
          //   938: astore 25
          //   940: iload 11
          //   942: istore 5
          //   944: aload 55
          //   946: astore 35
          //   948: aload 12
          //   950: astore 34
          //   952: aload 44
          //   954: astore 24
          //   956: aload 49
          //   958: astore 21
          //   960: aload 56
          //   962: iconst_1
          //   963: invokevirtual 135	org/apache/cordova/filetransfer/FileProgressResult:setLengthComputable	(Z)V
          //   966: iload 7
          //   968: istore_1
          //   969: aload 13
          //   971: astore 26
          //   973: aload 12
          //   975: astore 29
          //   977: aload 40
          //   979: astore 15
          //   981: aload 45
          //   983: astore 17
          //   985: iload 8
          //   987: istore_2
          //   988: aload 52
          //   990: astore 27
          //   992: aload 12
          //   994: astore 30
          //   996: aload 41
          //   998: astore 18
          //   1000: aload 46
          //   1002: astore 19
          //   1004: iload 9
          //   1006: istore_3
          //   1007: aload 53
          //   1009: astore 28
          //   1011: aload 12
          //   1013: astore 32
          //   1015: aload 42
          //   1017: astore 20
          //   1019: aload 47
          //   1021: astore 22
          //   1023: iload 10
          //   1025: istore 4
          //   1027: aload 54
          //   1029: astore 31
          //   1031: aload 12
          //   1033: astore 33
          //   1035: aload 43
          //   1037: astore 23
          //   1039: aload 48
          //   1041: astore 25
          //   1043: iload 11
          //   1045: istore 5
          //   1047: aload 55
          //   1049: astore 35
          //   1051: aload 12
          //   1053: astore 34
          //   1055: aload 44
          //   1057: astore 24
          //   1059: aload 49
          //   1061: astore 21
          //   1063: aload 56
          //   1065: aload 38
          //   1067: getfield 129	org/apache/cordova/CordovaResourceApi$OpenForReadResult:length	J
          //   1070: invokevirtual 139	org/apache/cordova/filetransfer/FileProgressResult:setTotal	(J)V
          //   1073: iload 7
          //   1075: istore_1
          //   1076: aload 13
          //   1078: astore 26
          //   1080: aload 12
          //   1082: astore 29
          //   1084: aload 40
          //   1086: astore 15
          //   1088: aload 45
          //   1090: astore 17
          //   1092: iload 8
          //   1094: istore_2
          //   1095: aload 52
          //   1097: astore 27
          //   1099: aload 12
          //   1101: astore 30
          //   1103: aload 41
          //   1105: astore 18
          //   1107: aload 46
          //   1109: astore 19
          //   1111: iload 9
          //   1113: istore_3
          //   1114: aload 53
          //   1116: astore 28
          //   1118: aload 12
          //   1120: astore 32
          //   1122: aload 42
          //   1124: astore 20
          //   1126: aload 47
          //   1128: astore 22
          //   1130: iload 10
          //   1132: istore 4
          //   1134: aload 54
          //   1136: astore 31
          //   1138: aload 12
          //   1140: astore 33
          //   1142: aload 43
          //   1144: astore 23
          //   1146: aload 48
          //   1148: astore 25
          //   1150: iload 11
          //   1152: istore 5
          //   1154: aload 55
          //   1156: astore 35
          //   1158: aload 12
          //   1160: astore 34
          //   1162: aload 44
          //   1164: astore 24
          //   1166: aload 49
          //   1168: astore 21
          //   1170: new 141	org/apache/cordova/filetransfer/FileTransfer$SimpleTrackingInputStream
          //   1173: dup
          //   1174: aload 38
          //   1176: getfield 145	org/apache/cordova/CordovaResourceApi$OpenForReadResult:inputStream	Ljava/io/InputStream;
          //   1179: invokespecial 148	org/apache/cordova/filetransfer/FileTransfer$SimpleTrackingInputStream:<init>	(Ljava/io/InputStream;)V
          //   1182: astore 13
          //   1184: aload 13
          //   1186: astore 26
          //   1188: aconst_null
          //   1189: astore 13
          //   1191: aload 39
          //   1193: astore 15
          //   1195: iload 6
          //   1197: istore_1
          //   1198: iload_1
          //   1199: ifne +6090 -> 7289
          //   1202: aload 36
          //   1204: astore 27
          //   1206: aload_0
          //   1207: getfield 36	org/apache/cordova/filetransfer/FileTransfer$4:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
          //   1210: astore 17
          //   1212: aload 36
          //   1214: astore 27
          //   1216: aload 17
          //   1218: monitorenter
          //   1219: aload_0
          //   1220: getfield 36	org/apache/cordova/filetransfer/FileTransfer$4:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
          //   1223: getfield 82	org/apache/cordova/filetransfer/FileTransfer$RequestContext:aborted	Z
          //   1226: ifeq +3093 -> 4319
          //   1229: aload 17
          //   1231: monitorexit
          //   1232: aload 12
          //   1234: astore 22
          //   1236: aload 12
          //   1238: astore 23
          //   1240: aload 12
          //   1242: astore 24
          //   1244: aload 12
          //   1246: astore 25
          //   1248: iload_1
          //   1249: istore_2
          //   1250: aload 16
          //   1252: astore 20
          //   1254: aload 12
          //   1256: astore 17
          //   1258: aload 15
          //   1260: astore 19
          //   1262: aload 14
          //   1264: astore 18
          //   1266: aload 13
          //   1268: astore 21
          //   1270: aload_0
          //   1271: getfield 36	org/apache/cordova/filetransfer/FileTransfer$4:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
          //   1274: astore 27
          //   1276: aload 12
          //   1278: astore 22
          //   1280: aload 12
          //   1282: astore 23
          //   1284: aload 12
          //   1286: astore 24
          //   1288: aload 12
          //   1290: astore 25
          //   1292: iload_1
          //   1293: istore_2
          //   1294: aload 16
          //   1296: astore 20
          //   1298: aload 12
          //   1300: astore 17
          //   1302: aload 15
          //   1304: astore 19
          //   1306: aload 14
          //   1308: astore 18
          //   1310: aload 13
          //   1312: astore 21
          //   1314: aload 27
          //   1316: monitorenter
          //   1317: aload_0
          //   1318: getfield 36	org/apache/cordova/filetransfer/FileTransfer$4:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
          //   1321: aconst_null
          //   1322: putfield 152	org/apache/cordova/filetransfer/FileTransfer$RequestContext:connection	Ljava/net/HttpURLConnection;
          //   1325: aload 27
          //   1327: monitorexit
          //   1328: aload 12
          //   1330: astore 22
          //   1332: aload 12
          //   1334: astore 23
          //   1336: aload 12
          //   1338: astore 24
          //   1340: aload 12
          //   1342: astore 25
          //   1344: iload_1
          //   1345: istore_2
          //   1346: aload 16
          //   1348: astore 20
          //   1350: aload 12
          //   1352: astore 17
          //   1354: aload 15
          //   1356: astore 19
          //   1358: aload 14
          //   1360: astore 18
          //   1362: aload 13
          //   1364: astore 21
          //   1366: aload 26
          //   1368: invokestatic 156	org/apache/cordova/filetransfer/FileTransfer:access$300	(Ljava/io/Closeable;)V
          //   1371: aload 12
          //   1373: astore 22
          //   1375: aload 12
          //   1377: astore 23
          //   1379: aload 12
          //   1381: astore 24
          //   1383: aload 12
          //   1385: astore 25
          //   1387: iload_1
          //   1388: istore_2
          //   1389: aload 16
          //   1391: astore 20
          //   1393: aload 12
          //   1395: astore 17
          //   1397: aload 15
          //   1399: astore 19
          //   1401: aload 14
          //   1403: astore 18
          //   1405: aload 13
          //   1407: astore 21
          //   1409: aconst_null
          //   1410: invokestatic 156	org/apache/cordova/filetransfer/FileTransfer:access$300	(Ljava/io/Closeable;)V
          //   1413: invokestatic 160	org/apache/cordova/filetransfer/FileTransfer:access$600	()Ljava/util/HashMap;
          //   1416: astore 17
          //   1418: aload 17
          //   1420: monitorenter
          //   1421: invokestatic 160	org/apache/cordova/filetransfer/FileTransfer:access$600	()Ljava/util/HashMap;
          //   1424: aload_0
          //   1425: getfield 56	org/apache/cordova/filetransfer/FileTransfer$4:val$objectId	Ljava/lang/String;
          //   1428: invokevirtual 166	java/util/HashMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
          //   1431: pop
          //   1432: aload 17
          //   1434: monitorexit
          //   1435: aload 16
          //   1437: ifnull +38 -> 1475
          //   1440: aload_0
          //   1441: getfield 48	org/apache/cordova/filetransfer/FileTransfer$4:val$trustEveryone	Z
          //   1444: ifeq +31 -> 1475
          //   1447: aload_0
          //   1448: getfield 46	org/apache/cordova/filetransfer/FileTransfer$4:val$useHttps	Z
          //   1451: ifeq +24 -> 1475
          //   1454: aload 16
          //   1456: checkcast 168	javax/net/ssl/HttpsURLConnection
          //   1459: astore 17
          //   1461: aload 17
          //   1463: aload 15
          //   1465: invokevirtual 172	javax/net/ssl/HttpsURLConnection:setHostnameVerifier	(Ljavax/net/ssl/HostnameVerifier;)V
          //   1468: aload 17
          //   1470: aload 14
          //   1472: invokevirtual 176	javax/net/ssl/HttpsURLConnection:setSSLSocketFactory	(Ljavax/net/ssl/SSLSocketFactory;)V
          //   1475: aload 13
          //   1477: ifnonnull +5805 -> 7282
          //   1480: new 178	org/apache/cordova/PluginResult
          //   1483: dup
          //   1484: getstatic 184	org/apache/cordova/PluginResult$Status:ERROR	Lorg/apache/cordova/PluginResult$Status;
          //   1487: getstatic 188	org/apache/cordova/filetransfer/FileTransfer:CONNECTION_ERR	I
          //   1490: aload_0
          //   1491: getfield 52	org/apache/cordova/filetransfer/FileTransfer$4:val$source	Ljava/lang/String;
          //   1494: aload_0
          //   1495: getfield 54	org/apache/cordova/filetransfer/FileTransfer$4:val$target	Ljava/lang/String;
          //   1498: aload 16
          //   1500: aconst_null
          //   1501: invokestatic 192	org/apache/cordova/filetransfer/FileTransfer:access$500	(ILjava/lang/String;Ljava/lang/String;Ljava/net/URLConnection;Ljava/lang/Throwable;)Lorg/json/JSONObject;
          //   1504: invokespecial 195	org/apache/cordova/PluginResult:<init>	(Lorg/apache/cordova/PluginResult$Status;Lorg/json/JSONObject;)V
          //   1507: astore 14
          //   1509: iload_1
          //   1510: ifne +28 -> 1538
          //   1513: aload 14
          //   1515: invokevirtual 199	org/apache/cordova/PluginResult:getStatus	()I
          //   1518: getstatic 202	org/apache/cordova/PluginResult$Status:OK	Lorg/apache/cordova/PluginResult$Status;
          //   1521: invokevirtual 205	org/apache/cordova/PluginResult$Status:ordinal	()I
          //   1524: if_icmpeq +14 -> 1538
          //   1527: aload 12
          //   1529: ifnull +9 -> 1538
          //   1532: aload 12
          //   1534: invokevirtual 211	java/io/File:delete	()Z
          //   1537: pop
          //   1538: aload_0
          //   1539: getfield 36	org/apache/cordova/filetransfer/FileTransfer$4:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
          //   1542: astore 13
          //   1544: aload 14
          //   1546: astore 12
          //   1548: aload 13
          //   1550: aload 12
          //   1552: invokevirtual 215	org/apache/cordova/filetransfer/FileTransfer$RequestContext:sendPluginResult	(Lorg/apache/cordova/PluginResult;)V
          //   1555: return
          //   1556: iload 7
          //   1558: istore_1
          //   1559: aload 13
          //   1561: astore 26
          //   1563: aload 12
          //   1565: astore 29
          //   1567: aload 40
          //   1569: astore 15
          //   1571: aload 45
          //   1573: astore 17
          //   1575: iload 8
          //   1577: istore_2
          //   1578: aload 52
          //   1580: astore 27
          //   1582: aload 12
          //   1584: astore 30
          //   1586: aload 41
          //   1588: astore 18
          //   1590: aload 46
          //   1592: astore 19
          //   1594: iload 9
          //   1596: istore_3
          //   1597: aload 53
          //   1599: astore 28
          //   1601: aload 12
          //   1603: astore 32
          //   1605: aload 42
          //   1607: astore 20
          //   1609: aload 47
          //   1611: astore 22
          //   1613: iload 10
          //   1615: istore 4
          //   1617: aload 54
          //   1619: astore 31
          //   1621: aload 12
          //   1623: astore 33
          //   1625: aload 43
          //   1627: astore 23
          //   1629: aload 48
          //   1631: astore 25
          //   1633: iload 11
          //   1635: istore 5
          //   1637: aload 55
          //   1639: astore 35
          //   1641: aload 12
          //   1643: astore 34
          //   1645: aload 44
          //   1647: astore 24
          //   1649: aload 49
          //   1651: astore 21
          //   1653: aload_0
          //   1654: getfield 38	org/apache/cordova/filetransfer/FileTransfer$4:val$resourceApi	Lorg/apache/cordova/CordovaResourceApi;
          //   1657: aload_0
          //   1658: getfield 42	org/apache/cordova/filetransfer/FileTransfer$4:val$sourceUri	Landroid/net/Uri;
          //   1661: invokevirtual 219	org/apache/cordova/CordovaResourceApi:createHttpConnection	(Landroid/net/Uri;)Ljava/net/HttpURLConnection;
          //   1664: astore 16
          //   1666: aload 50
          //   1668: astore 14
          //   1670: aload 51
          //   1672: astore 13
          //   1674: iload 7
          //   1676: istore_1
          //   1677: aload 16
          //   1679: astore 26
          //   1681: aload 12
          //   1683: astore 29
          //   1685: aload 40
          //   1687: astore 15
          //   1689: aload 45
          //   1691: astore 17
          //   1693: iload 8
          //   1695: istore_2
          //   1696: aload 16
          //   1698: astore 27
          //   1700: aload 12
          //   1702: astore 30
          //   1704: aload 41
          //   1706: astore 18
          //   1708: aload 46
          //   1710: astore 19
          //   1712: iload 9
          //   1714: istore_3
          //   1715: aload 16
          //   1717: astore 28
          //   1719: aload 12
          //   1721: astore 32
          //   1723: aload 42
          //   1725: astore 20
          //   1727: aload 47
          //   1729: astore 22
          //   1731: iload 10
          //   1733: istore 4
          //   1735: aload 16
          //   1737: astore 31
          //   1739: aload 12
          //   1741: astore 33
          //   1743: aload 43
          //   1745: astore 23
          //   1747: aload 48
          //   1749: astore 25
          //   1751: iload 11
          //   1753: istore 5
          //   1755: aload 16
          //   1757: astore 35
          //   1759: aload 12
          //   1761: astore 34
          //   1763: aload 44
          //   1765: astore 24
          //   1767: aload 49
          //   1769: astore 21
          //   1771: aload_0
          //   1772: getfield 46	org/apache/cordova/filetransfer/FileTransfer$4:val$useHttps	Z
          //   1775: ifeq +532 -> 2307
          //   1778: aload 50
          //   1780: astore 14
          //   1782: aload 51
          //   1784: astore 13
          //   1786: iload 7
          //   1788: istore_1
          //   1789: aload 16
          //   1791: astore 26
          //   1793: aload 12
          //   1795: astore 29
          //   1797: aload 40
          //   1799: astore 15
          //   1801: aload 45
          //   1803: astore 17
          //   1805: iload 8
          //   1807: istore_2
          //   1808: aload 16
          //   1810: astore 27
          //   1812: aload 12
          //   1814: astore 30
          //   1816: aload 41
          //   1818: astore 18
          //   1820: aload 46
          //   1822: astore 19
          //   1824: iload 9
          //   1826: istore_3
          //   1827: aload 16
          //   1829: astore 28
          //   1831: aload 12
          //   1833: astore 32
          //   1835: aload 42
          //   1837: astore 20
          //   1839: aload 47
          //   1841: astore 22
          //   1843: iload 10
          //   1845: istore 4
          //   1847: aload 16
          //   1849: astore 31
          //   1851: aload 12
          //   1853: astore 33
          //   1855: aload 43
          //   1857: astore 23
          //   1859: aload 48
          //   1861: astore 25
          //   1863: iload 11
          //   1865: istore 5
          //   1867: aload 16
          //   1869: astore 35
          //   1871: aload 12
          //   1873: astore 34
          //   1875: aload 44
          //   1877: astore 24
          //   1879: aload 49
          //   1881: astore 21
          //   1883: aload_0
          //   1884: getfield 48	org/apache/cordova/filetransfer/FileTransfer$4:val$trustEveryone	Z
          //   1887: ifeq +420 -> 2307
          //   1890: iload 7
          //   1892: istore_1
          //   1893: aload 16
          //   1895: astore 26
          //   1897: aload 12
          //   1899: astore 29
          //   1901: aload 40
          //   1903: astore 15
          //   1905: aload 45
          //   1907: astore 17
          //   1909: iload 8
          //   1911: istore_2
          //   1912: aload 16
          //   1914: astore 27
          //   1916: aload 12
          //   1918: astore 30
          //   1920: aload 41
          //   1922: astore 18
          //   1924: aload 46
          //   1926: astore 19
          //   1928: iload 9
          //   1930: istore_3
          //   1931: aload 16
          //   1933: astore 28
          //   1935: aload 12
          //   1937: astore 32
          //   1939: aload 42
          //   1941: astore 20
          //   1943: aload 47
          //   1945: astore 22
          //   1947: iload 10
          //   1949: istore 4
          //   1951: aload 16
          //   1953: astore 31
          //   1955: aload 12
          //   1957: astore 33
          //   1959: aload 43
          //   1961: astore 23
          //   1963: aload 48
          //   1965: astore 25
          //   1967: iload 11
          //   1969: istore 5
          //   1971: aload 16
          //   1973: astore 35
          //   1975: aload 12
          //   1977: astore 34
          //   1979: aload 44
          //   1981: astore 24
          //   1983: aload 49
          //   1985: astore 21
          //   1987: aload 16
          //   1989: checkcast 168	javax/net/ssl/HttpsURLConnection
          //   1992: astore 39
          //   1994: iload 7
          //   1996: istore_1
          //   1997: aload 16
          //   1999: astore 26
          //   2001: aload 12
          //   2003: astore 29
          //   2005: aload 40
          //   2007: astore 15
          //   2009: aload 45
          //   2011: astore 17
          //   2013: iload 8
          //   2015: istore_2
          //   2016: aload 16
          //   2018: astore 27
          //   2020: aload 12
          //   2022: astore 30
          //   2024: aload 41
          //   2026: astore 18
          //   2028: aload 46
          //   2030: astore 19
          //   2032: iload 9
          //   2034: istore_3
          //   2035: aload 16
          //   2037: astore 28
          //   2039: aload 12
          //   2041: astore 32
          //   2043: aload 42
          //   2045: astore 20
          //   2047: aload 47
          //   2049: astore 22
          //   2051: iload 10
          //   2053: istore 4
          //   2055: aload 16
          //   2057: astore 31
          //   2059: aload 12
          //   2061: astore 33
          //   2063: aload 43
          //   2065: astore 23
          //   2067: aload 48
          //   2069: astore 25
          //   2071: iload 11
          //   2073: istore 5
          //   2075: aload 16
          //   2077: astore 35
          //   2079: aload 12
          //   2081: astore 34
          //   2083: aload 44
          //   2085: astore 24
          //   2087: aload 49
          //   2089: astore 21
          //   2091: aload 39
          //   2093: invokestatic 223	org/apache/cordova/filetransfer/FileTransfer:access$000	(Ljavax/net/ssl/HttpsURLConnection;)Ljavax/net/ssl/SSLSocketFactory;
          //   2096: astore 13
          //   2098: iload 7
          //   2100: istore_1
          //   2101: aload 16
          //   2103: astore 26
          //   2105: aload 12
          //   2107: astore 29
          //   2109: aload 40
          //   2111: astore 15
          //   2113: aload 13
          //   2115: astore 17
          //   2117: iload 8
          //   2119: istore_2
          //   2120: aload 16
          //   2122: astore 27
          //   2124: aload 12
          //   2126: astore 30
          //   2128: aload 41
          //   2130: astore 18
          //   2132: aload 13
          //   2134: astore 19
          //   2136: iload 9
          //   2138: istore_3
          //   2139: aload 16
          //   2141: astore 28
          //   2143: aload 12
          //   2145: astore 32
          //   2147: aload 42
          //   2149: astore 20
          //   2151: aload 13
          //   2153: astore 22
          //   2155: iload 10
          //   2157: istore 4
          //   2159: aload 16
          //   2161: astore 31
          //   2163: aload 12
          //   2165: astore 33
          //   2167: aload 43
          //   2169: astore 23
          //   2171: aload 13
          //   2173: astore 25
          //   2175: iload 11
          //   2177: istore 5
          //   2179: aload 16
          //   2181: astore 35
          //   2183: aload 12
          //   2185: astore 34
          //   2187: aload 44
          //   2189: astore 24
          //   2191: aload 13
          //   2193: astore 21
          //   2195: aload 39
          //   2197: invokevirtual 227	javax/net/ssl/HttpsURLConnection:getHostnameVerifier	()Ljavax/net/ssl/HostnameVerifier;
          //   2200: astore 14
          //   2202: iload 7
          //   2204: istore_1
          //   2205: aload 16
          //   2207: astore 26
          //   2209: aload 12
          //   2211: astore 29
          //   2213: aload 14
          //   2215: astore 15
          //   2217: aload 13
          //   2219: astore 17
          //   2221: iload 8
          //   2223: istore_2
          //   2224: aload 16
          //   2226: astore 27
          //   2228: aload 12
          //   2230: astore 30
          //   2232: aload 14
          //   2234: astore 18
          //   2236: aload 13
          //   2238: astore 19
          //   2240: iload 9
          //   2242: istore_3
          //   2243: aload 16
          //   2245: astore 28
          //   2247: aload 12
          //   2249: astore 32
          //   2251: aload 14
          //   2253: astore 20
          //   2255: aload 13
          //   2257: astore 22
          //   2259: iload 10
          //   2261: istore 4
          //   2263: aload 16
          //   2265: astore 31
          //   2267: aload 12
          //   2269: astore 33
          //   2271: aload 14
          //   2273: astore 23
          //   2275: aload 13
          //   2277: astore 25
          //   2279: iload 11
          //   2281: istore 5
          //   2283: aload 16
          //   2285: astore 35
          //   2287: aload 12
          //   2289: astore 34
          //   2291: aload 14
          //   2293: astore 24
          //   2295: aload 13
          //   2297: astore 21
          //   2299: aload 39
          //   2301: invokestatic 230	org/apache/cordova/filetransfer/FileTransfer:access$100	()Ljavax/net/ssl/HostnameVerifier;
          //   2304: invokevirtual 172	javax/net/ssl/HttpsURLConnection:setHostnameVerifier	(Ljavax/net/ssl/HostnameVerifier;)V
          //   2307: iload 7
          //   2309: istore_1
          //   2310: aload 16
          //   2312: astore 26
          //   2314: aload 12
          //   2316: astore 29
          //   2318: aload 14
          //   2320: astore 15
          //   2322: aload 13
          //   2324: astore 17
          //   2326: iload 8
          //   2328: istore_2
          //   2329: aload 16
          //   2331: astore 27
          //   2333: aload 12
          //   2335: astore 30
          //   2337: aload 14
          //   2339: astore 18
          //   2341: aload 13
          //   2343: astore 19
          //   2345: iload 9
          //   2347: istore_3
          //   2348: aload 16
          //   2350: astore 28
          //   2352: aload 12
          //   2354: astore 32
          //   2356: aload 14
          //   2358: astore 20
          //   2360: aload 13
          //   2362: astore 22
          //   2364: iload 10
          //   2366: istore 4
          //   2368: aload 16
          //   2370: astore 31
          //   2372: aload 12
          //   2374: astore 33
          //   2376: aload 14
          //   2378: astore 23
          //   2380: aload 13
          //   2382: astore 25
          //   2384: iload 11
          //   2386: istore 5
          //   2388: aload 16
          //   2390: astore 35
          //   2392: aload 12
          //   2394: astore 34
          //   2396: aload 14
          //   2398: astore 24
          //   2400: aload 13
          //   2402: astore 21
          //   2404: aload 16
          //   2406: ldc -24
          //   2408: invokevirtual 238	java/net/HttpURLConnection:setRequestMethod	(Ljava/lang/String;)V
          //   2411: iload 7
          //   2413: istore_1
          //   2414: aload 16
          //   2416: astore 26
          //   2418: aload 12
          //   2420: astore 29
          //   2422: aload 14
          //   2424: astore 15
          //   2426: aload 13
          //   2428: astore 17
          //   2430: iload 8
          //   2432: istore_2
          //   2433: aload 16
          //   2435: astore 27
          //   2437: aload 12
          //   2439: astore 30
          //   2441: aload 14
          //   2443: astore 18
          //   2445: aload 13
          //   2447: astore 19
          //   2449: iload 9
          //   2451: istore_3
          //   2452: aload 16
          //   2454: astore 28
          //   2456: aload 12
          //   2458: astore 32
          //   2460: aload 14
          //   2462: astore 20
          //   2464: aload 13
          //   2466: astore 22
          //   2468: iload 10
          //   2470: istore 4
          //   2472: aload 16
          //   2474: astore 31
          //   2476: aload 12
          //   2478: astore 33
          //   2480: aload 14
          //   2482: astore 23
          //   2484: aload 13
          //   2486: astore 25
          //   2488: iload 11
          //   2490: istore 5
          //   2492: aload 16
          //   2494: astore 35
          //   2496: aload 12
          //   2498: astore 34
          //   2500: aload 14
          //   2502: astore 24
          //   2504: aload 13
          //   2506: astore 21
          //   2508: invokestatic 244	android/webkit/CookieManager:getInstance	()Landroid/webkit/CookieManager;
          //   2511: aload_0
          //   2512: getfield 42	org/apache/cordova/filetransfer/FileTransfer$4:val$sourceUri	Landroid/net/Uri;
          //   2515: invokevirtual 247	android/net/Uri:toString	()Ljava/lang/String;
          //   2518: invokevirtual 251	android/webkit/CookieManager:getCookie	(Ljava/lang/String;)Ljava/lang/String;
          //   2521: astore 39
          //   2523: aload 39
          //   2525: ifnull +109 -> 2634
          //   2528: iload 7
          //   2530: istore_1
          //   2531: aload 16
          //   2533: astore 26
          //   2535: aload 12
          //   2537: astore 29
          //   2539: aload 14
          //   2541: astore 15
          //   2543: aload 13
          //   2545: astore 17
          //   2547: iload 8
          //   2549: istore_2
          //   2550: aload 16
          //   2552: astore 27
          //   2554: aload 12
          //   2556: astore 30
          //   2558: aload 14
          //   2560: astore 18
          //   2562: aload 13
          //   2564: astore 19
          //   2566: iload 9
          //   2568: istore_3
          //   2569: aload 16
          //   2571: astore 28
          //   2573: aload 12
          //   2575: astore 32
          //   2577: aload 14
          //   2579: astore 20
          //   2581: aload 13
          //   2583: astore 22
          //   2585: iload 10
          //   2587: istore 4
          //   2589: aload 16
          //   2591: astore 31
          //   2593: aload 12
          //   2595: astore 33
          //   2597: aload 14
          //   2599: astore 23
          //   2601: aload 13
          //   2603: astore 25
          //   2605: iload 11
          //   2607: istore 5
          //   2609: aload 16
          //   2611: astore 35
          //   2613: aload 12
          //   2615: astore 34
          //   2617: aload 14
          //   2619: astore 24
          //   2621: aload 13
          //   2623: astore 21
          //   2625: aload 16
          //   2627: ldc -3
          //   2629: aload 39
          //   2631: invokevirtual 257	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
          //   2634: iload 7
          //   2636: istore_1
          //   2637: aload 16
          //   2639: astore 26
          //   2641: aload 12
          //   2643: astore 29
          //   2645: aload 14
          //   2647: astore 15
          //   2649: aload 13
          //   2651: astore 17
          //   2653: iload 8
          //   2655: istore_2
          //   2656: aload 16
          //   2658: astore 27
          //   2660: aload 12
          //   2662: astore 30
          //   2664: aload 14
          //   2666: astore 18
          //   2668: aload 13
          //   2670: astore 19
          //   2672: iload 9
          //   2674: istore_3
          //   2675: aload 16
          //   2677: astore 28
          //   2679: aload 12
          //   2681: astore 32
          //   2683: aload 14
          //   2685: astore 20
          //   2687: aload 13
          //   2689: astore 22
          //   2691: iload 10
          //   2693: istore 4
          //   2695: aload 16
          //   2697: astore 31
          //   2699: aload 12
          //   2701: astore 33
          //   2703: aload 14
          //   2705: astore 23
          //   2707: aload 13
          //   2709: astore 25
          //   2711: iload 11
          //   2713: istore 5
          //   2715: aload 16
          //   2717: astore 35
          //   2719: aload 12
          //   2721: astore 34
          //   2723: aload 14
          //   2725: astore 24
          //   2727: aload 13
          //   2729: astore 21
          //   2731: aload 16
          //   2733: ldc_w 259
          //   2736: ldc_w 261
          //   2739: invokevirtual 257	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
          //   2742: iload 7
          //   2744: istore_1
          //   2745: aload 16
          //   2747: astore 26
          //   2749: aload 12
          //   2751: astore 29
          //   2753: aload 14
          //   2755: astore 15
          //   2757: aload 13
          //   2759: astore 17
          //   2761: iload 8
          //   2763: istore_2
          //   2764: aload 16
          //   2766: astore 27
          //   2768: aload 12
          //   2770: astore 30
          //   2772: aload 14
          //   2774: astore 18
          //   2776: aload 13
          //   2778: astore 19
          //   2780: iload 9
          //   2782: istore_3
          //   2783: aload 16
          //   2785: astore 28
          //   2787: aload 12
          //   2789: astore 32
          //   2791: aload 14
          //   2793: astore 20
          //   2795: aload 13
          //   2797: astore 22
          //   2799: iload 10
          //   2801: istore 4
          //   2803: aload 16
          //   2805: astore 31
          //   2807: aload 12
          //   2809: astore 33
          //   2811: aload 14
          //   2813: astore 23
          //   2815: aload 13
          //   2817: astore 25
          //   2819: iload 11
          //   2821: istore 5
          //   2823: aload 16
          //   2825: astore 35
          //   2827: aload 12
          //   2829: astore 34
          //   2831: aload 14
          //   2833: astore 24
          //   2835: aload 13
          //   2837: astore 21
          //   2839: aload_0
          //   2840: getfield 50	org/apache/cordova/filetransfer/FileTransfer$4:val$headers	Lorg/json/JSONObject;
          //   2843: ifnull +109 -> 2952
          //   2846: iload 7
          //   2848: istore_1
          //   2849: aload 16
          //   2851: astore 26
          //   2853: aload 12
          //   2855: astore 29
          //   2857: aload 14
          //   2859: astore 15
          //   2861: aload 13
          //   2863: astore 17
          //   2865: iload 8
          //   2867: istore_2
          //   2868: aload 16
          //   2870: astore 27
          //   2872: aload 12
          //   2874: astore 30
          //   2876: aload 14
          //   2878: astore 18
          //   2880: aload 13
          //   2882: astore 19
          //   2884: iload 9
          //   2886: istore_3
          //   2887: aload 16
          //   2889: astore 28
          //   2891: aload 12
          //   2893: astore 32
          //   2895: aload 14
          //   2897: astore 20
          //   2899: aload 13
          //   2901: astore 22
          //   2903: iload 10
          //   2905: istore 4
          //   2907: aload 16
          //   2909: astore 31
          //   2911: aload 12
          //   2913: astore 33
          //   2915: aload 14
          //   2917: astore 23
          //   2919: aload 13
          //   2921: astore 25
          //   2923: iload 11
          //   2925: istore 5
          //   2927: aload 16
          //   2929: astore 35
          //   2931: aload 12
          //   2933: astore 34
          //   2935: aload 14
          //   2937: astore 24
          //   2939: aload 13
          //   2941: astore 21
          //   2943: aload 16
          //   2945: aload_0
          //   2946: getfield 50	org/apache/cordova/filetransfer/FileTransfer$4:val$headers	Lorg/json/JSONObject;
          //   2949: invokestatic 265	org/apache/cordova/filetransfer/FileTransfer:access$200	(Ljava/net/URLConnection;Lorg/json/JSONObject;)V
          //   2952: iload 7
          //   2954: istore_1
          //   2955: aload 16
          //   2957: astore 26
          //   2959: aload 12
          //   2961: astore 29
          //   2963: aload 14
          //   2965: astore 15
          //   2967: aload 13
          //   2969: astore 17
          //   2971: iload 8
          //   2973: istore_2
          //   2974: aload 16
          //   2976: astore 27
          //   2978: aload 12
          //   2980: astore 30
          //   2982: aload 14
          //   2984: astore 18
          //   2986: aload 13
          //   2988: astore 19
          //   2990: iload 9
          //   2992: istore_3
          //   2993: aload 16
          //   2995: astore 28
          //   2997: aload 12
          //   2999: astore 32
          //   3001: aload 14
          //   3003: astore 20
          //   3005: aload 13
          //   3007: astore 22
          //   3009: iload 10
          //   3011: istore 4
          //   3013: aload 16
          //   3015: astore 31
          //   3017: aload 12
          //   3019: astore 33
          //   3021: aload 14
          //   3023: astore 23
          //   3025: aload 13
          //   3027: astore 25
          //   3029: iload 11
          //   3031: istore 5
          //   3033: aload 16
          //   3035: astore 35
          //   3037: aload 12
          //   3039: astore 34
          //   3041: aload 14
          //   3043: astore 24
          //   3045: aload 13
          //   3047: astore 21
          //   3049: aload 16
          //   3051: invokevirtual 268	java/net/HttpURLConnection:connect	()V
          //   3054: iload 7
          //   3056: istore_1
          //   3057: aload 16
          //   3059: astore 26
          //   3061: aload 12
          //   3063: astore 29
          //   3065: aload 14
          //   3067: astore 15
          //   3069: aload 13
          //   3071: astore 17
          //   3073: iload 8
          //   3075: istore_2
          //   3076: aload 16
          //   3078: astore 27
          //   3080: aload 12
          //   3082: astore 30
          //   3084: aload 14
          //   3086: astore 18
          //   3088: aload 13
          //   3090: astore 19
          //   3092: iload 9
          //   3094: istore_3
          //   3095: aload 16
          //   3097: astore 28
          //   3099: aload 12
          //   3101: astore 32
          //   3103: aload 14
          //   3105: astore 20
          //   3107: aload 13
          //   3109: astore 22
          //   3111: iload 10
          //   3113: istore 4
          //   3115: aload 16
          //   3117: astore 31
          //   3119: aload 12
          //   3121: astore 33
          //   3123: aload 14
          //   3125: astore 23
          //   3127: aload 13
          //   3129: astore 25
          //   3131: iload 11
          //   3133: istore 5
          //   3135: aload 16
          //   3137: astore 35
          //   3139: aload 12
          //   3141: astore 34
          //   3143: aload 14
          //   3145: astore 24
          //   3147: aload 13
          //   3149: astore 21
          //   3151: aload 16
          //   3153: invokevirtual 271	java/net/HttpURLConnection:getResponseCode	()I
          //   3156: sipush 304
          //   3159: if_icmpne +498 -> 3657
          //   3162: iconst_1
          //   3163: istore 7
          //   3165: iconst_1
          //   3166: istore 8
          //   3168: iconst_1
          //   3169: istore 9
          //   3171: iconst_1
          //   3172: istore 10
          //   3174: iconst_1
          //   3175: istore 11
          //   3177: iconst_1
          //   3178: istore 6
          //   3180: iload 7
          //   3182: istore_1
          //   3183: aload 16
          //   3185: astore 26
          //   3187: aload 12
          //   3189: astore 29
          //   3191: aload 14
          //   3193: astore 15
          //   3195: aload 13
          //   3197: astore 17
          //   3199: iload 8
          //   3201: istore_2
          //   3202: aload 16
          //   3204: astore 27
          //   3206: aload 12
          //   3208: astore 30
          //   3210: aload 14
          //   3212: astore 18
          //   3214: aload 13
          //   3216: astore 19
          //   3218: iload 9
          //   3220: istore_3
          //   3221: aload 16
          //   3223: astore 28
          //   3225: aload 12
          //   3227: astore 32
          //   3229: aload 14
          //   3231: astore 20
          //   3233: aload 13
          //   3235: astore 22
          //   3237: iload 10
          //   3239: istore 4
          //   3241: aload 16
          //   3243: astore 31
          //   3245: aload 12
          //   3247: astore 33
          //   3249: aload 14
          //   3251: astore 23
          //   3253: aload 13
          //   3255: astore 25
          //   3257: iload 11
          //   3259: istore 5
          //   3261: aload 16
          //   3263: astore 35
          //   3265: aload 12
          //   3267: astore 34
          //   3269: aload 14
          //   3271: astore 24
          //   3273: aload 13
          //   3275: astore 21
          //   3277: aload 16
          //   3279: invokevirtual 274	java/net/HttpURLConnection:disconnect	()V
          //   3282: iload 7
          //   3284: istore_1
          //   3285: aload 16
          //   3287: astore 26
          //   3289: aload 12
          //   3291: astore 29
          //   3293: aload 14
          //   3295: astore 15
          //   3297: aload 13
          //   3299: astore 17
          //   3301: iload 8
          //   3303: istore_2
          //   3304: aload 16
          //   3306: astore 27
          //   3308: aload 12
          //   3310: astore 30
          //   3312: aload 14
          //   3314: astore 18
          //   3316: aload 13
          //   3318: astore 19
          //   3320: iload 9
          //   3322: istore_3
          //   3323: aload 16
          //   3325: astore 28
          //   3327: aload 12
          //   3329: astore 32
          //   3331: aload 14
          //   3333: astore 20
          //   3335: aload 13
          //   3337: astore 22
          //   3339: iload 10
          //   3341: istore 4
          //   3343: aload 16
          //   3345: astore 31
          //   3347: aload 12
          //   3349: astore 33
          //   3351: aload 14
          //   3353: astore 23
          //   3355: aload 13
          //   3357: astore 25
          //   3359: iload 11
          //   3361: istore 5
          //   3363: aload 16
          //   3365: astore 35
          //   3367: aload 12
          //   3369: astore 34
          //   3371: aload 14
          //   3373: astore 24
          //   3375: aload 13
          //   3377: astore 21
          //   3379: ldc 94
          //   3381: new 96	java/lang/StringBuilder
          //   3384: dup
          //   3385: invokespecial 97	java/lang/StringBuilder:<init>	()V
          //   3388: ldc_w 276
          //   3391: invokevirtual 103	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   3394: aload_0
          //   3395: getfield 52	org/apache/cordova/filetransfer/FileTransfer$4:val$source	Ljava/lang/String;
          //   3398: invokevirtual 103	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   3401: invokevirtual 110	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   3404: invokestatic 116	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
          //   3407: pop
          //   3408: iload 7
          //   3410: istore_1
          //   3411: aload 16
          //   3413: astore 26
          //   3415: aload 12
          //   3417: astore 29
          //   3419: aload 14
          //   3421: astore 15
          //   3423: aload 13
          //   3425: astore 17
          //   3427: iload 8
          //   3429: istore_2
          //   3430: aload 16
          //   3432: astore 27
          //   3434: aload 12
          //   3436: astore 30
          //   3438: aload 14
          //   3440: astore 18
          //   3442: aload 13
          //   3444: astore 19
          //   3446: iload 9
          //   3448: istore_3
          //   3449: aload 16
          //   3451: astore 28
          //   3453: aload 12
          //   3455: astore 32
          //   3457: aload 14
          //   3459: astore 20
          //   3461: aload 13
          //   3463: astore 22
          //   3465: iload 10
          //   3467: istore 4
          //   3469: aload 16
          //   3471: astore 31
          //   3473: aload 12
          //   3475: astore 33
          //   3477: aload 14
          //   3479: astore 23
          //   3481: aload 13
          //   3483: astore 25
          //   3485: iload 11
          //   3487: istore 5
          //   3489: aload 16
          //   3491: astore 35
          //   3493: aload 12
          //   3495: astore 34
          //   3497: aload 14
          //   3499: astore 24
          //   3501: aload 13
          //   3503: astore 21
          //   3505: getstatic 279	org/apache/cordova/filetransfer/FileTransfer:NOT_MODIFIED_ERR	I
          //   3508: aload_0
          //   3509: getfield 52	org/apache/cordova/filetransfer/FileTransfer$4:val$source	Ljava/lang/String;
          //   3512: aload_0
          //   3513: getfield 54	org/apache/cordova/filetransfer/FileTransfer$4:val$target	Ljava/lang/String;
          //   3516: aload 16
          //   3518: aconst_null
          //   3519: invokestatic 192	org/apache/cordova/filetransfer/FileTransfer:access$500	(ILjava/lang/String;Ljava/lang/String;Ljava/net/URLConnection;Ljava/lang/Throwable;)Lorg/json/JSONObject;
          //   3522: astore 39
          //   3524: iload 7
          //   3526: istore_1
          //   3527: aload 16
          //   3529: astore 26
          //   3531: aload 12
          //   3533: astore 29
          //   3535: aload 14
          //   3537: astore 15
          //   3539: aload 13
          //   3541: astore 17
          //   3543: iload 8
          //   3545: istore_2
          //   3546: aload 16
          //   3548: astore 27
          //   3550: aload 12
          //   3552: astore 30
          //   3554: aload 14
          //   3556: astore 18
          //   3558: aload 13
          //   3560: astore 19
          //   3562: iload 9
          //   3564: istore_3
          //   3565: aload 16
          //   3567: astore 28
          //   3569: aload 12
          //   3571: astore 32
          //   3573: aload 14
          //   3575: astore 20
          //   3577: aload 13
          //   3579: astore 22
          //   3581: iload 10
          //   3583: istore 4
          //   3585: aload 16
          //   3587: astore 31
          //   3589: aload 12
          //   3591: astore 33
          //   3593: aload 14
          //   3595: astore 23
          //   3597: aload 13
          //   3599: astore 25
          //   3601: iload 11
          //   3603: istore 5
          //   3605: aload 16
          //   3607: astore 35
          //   3609: aload 12
          //   3611: astore 34
          //   3613: aload 14
          //   3615: astore 24
          //   3617: aload 13
          //   3619: astore 21
          //   3621: new 178	org/apache/cordova/PluginResult
          //   3624: dup
          //   3625: getstatic 184	org/apache/cordova/PluginResult$Status:ERROR	Lorg/apache/cordova/PluginResult$Status;
          //   3628: aload 39
          //   3630: invokespecial 195	org/apache/cordova/PluginResult:<init>	(Lorg/apache/cordova/PluginResult$Status;Lorg/json/JSONObject;)V
          //   3633: astore 39
          //   3635: iload 6
          //   3637: istore_1
          //   3638: aload 38
          //   3640: astore 26
          //   3642: aload 14
          //   3644: astore 15
          //   3646: aload 13
          //   3648: astore 14
          //   3650: aload 39
          //   3652: astore 13
          //   3654: goto -2456 -> 1198
          //   3657: iload 7
          //   3659: istore_1
          //   3660: aload 16
          //   3662: astore 26
          //   3664: aload 12
          //   3666: astore 29
          //   3668: aload 14
          //   3670: astore 15
          //   3672: aload 13
          //   3674: astore 17
          //   3676: iload 8
          //   3678: istore_2
          //   3679: aload 16
          //   3681: astore 27
          //   3683: aload 12
          //   3685: astore 30
          //   3687: aload 14
          //   3689: astore 18
          //   3691: aload 13
          //   3693: astore 19
          //   3695: iload 9
          //   3697: istore_3
          //   3698: aload 16
          //   3700: astore 28
          //   3702: aload 12
          //   3704: astore 32
          //   3706: aload 14
          //   3708: astore 20
          //   3710: aload 13
          //   3712: astore 22
          //   3714: iload 10
          //   3716: istore 4
          //   3718: aload 16
          //   3720: astore 31
          //   3722: aload 12
          //   3724: astore 33
          //   3726: aload 14
          //   3728: astore 23
          //   3730: aload 13
          //   3732: astore 25
          //   3734: iload 11
          //   3736: istore 5
          //   3738: aload 16
          //   3740: astore 35
          //   3742: aload 12
          //   3744: astore 34
          //   3746: aload 14
          //   3748: astore 24
          //   3750: aload 13
          //   3752: astore 21
          //   3754: aload 16
          //   3756: invokevirtual 282	java/net/HttpURLConnection:getContentEncoding	()Ljava/lang/String;
          //   3759: ifnull +114 -> 3873
          //   3762: iload 7
          //   3764: istore_1
          //   3765: aload 16
          //   3767: astore 26
          //   3769: aload 12
          //   3771: astore 29
          //   3773: aload 14
          //   3775: astore 15
          //   3777: aload 13
          //   3779: astore 17
          //   3781: iload 8
          //   3783: istore_2
          //   3784: aload 16
          //   3786: astore 27
          //   3788: aload 12
          //   3790: astore 30
          //   3792: aload 14
          //   3794: astore 18
          //   3796: aload 13
          //   3798: astore 19
          //   3800: iload 9
          //   3802: istore_3
          //   3803: aload 16
          //   3805: astore 28
          //   3807: aload 12
          //   3809: astore 32
          //   3811: aload 14
          //   3813: astore 20
          //   3815: aload 13
          //   3817: astore 22
          //   3819: iload 10
          //   3821: istore 4
          //   3823: aload 16
          //   3825: astore 31
          //   3827: aload 12
          //   3829: astore 33
          //   3831: aload 14
          //   3833: astore 23
          //   3835: aload 13
          //   3837: astore 25
          //   3839: iload 11
          //   3841: istore 5
          //   3843: aload 16
          //   3845: astore 35
          //   3847: aload 12
          //   3849: astore 34
          //   3851: aload 14
          //   3853: astore 24
          //   3855: aload 13
          //   3857: astore 21
          //   3859: aload 16
          //   3861: invokevirtual 282	java/net/HttpURLConnection:getContentEncoding	()Ljava/lang/String;
          //   3864: ldc_w 261
          //   3867: invokevirtual 288	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
          //   3870: ifeq +320 -> 4190
          //   3873: iload 7
          //   3875: istore_1
          //   3876: aload 16
          //   3878: astore 26
          //   3880: aload 12
          //   3882: astore 29
          //   3884: aload 14
          //   3886: astore 15
          //   3888: aload 13
          //   3890: astore 17
          //   3892: iload 8
          //   3894: istore_2
          //   3895: aload 16
          //   3897: astore 27
          //   3899: aload 12
          //   3901: astore 30
          //   3903: aload 14
          //   3905: astore 18
          //   3907: aload 13
          //   3909: astore 19
          //   3911: iload 9
          //   3913: istore_3
          //   3914: aload 16
          //   3916: astore 28
          //   3918: aload 12
          //   3920: astore 32
          //   3922: aload 14
          //   3924: astore 20
          //   3926: aload 13
          //   3928: astore 22
          //   3930: iload 10
          //   3932: istore 4
          //   3934: aload 16
          //   3936: astore 31
          //   3938: aload 12
          //   3940: astore 33
          //   3942: aload 14
          //   3944: astore 23
          //   3946: aload 13
          //   3948: astore 25
          //   3950: iload 11
          //   3952: istore 5
          //   3954: aload 16
          //   3956: astore 35
          //   3958: aload 12
          //   3960: astore 34
          //   3962: aload 14
          //   3964: astore 24
          //   3966: aload 13
          //   3968: astore 21
          //   3970: aload 16
          //   3972: invokevirtual 291	java/net/HttpURLConnection:getContentLength	()I
          //   3975: iconst_m1
          //   3976: if_icmpeq +214 -> 4190
          //   3979: iload 7
          //   3981: istore_1
          //   3982: aload 16
          //   3984: astore 26
          //   3986: aload 12
          //   3988: astore 29
          //   3990: aload 14
          //   3992: astore 15
          //   3994: aload 13
          //   3996: astore 17
          //   3998: iload 8
          //   4000: istore_2
          //   4001: aload 16
          //   4003: astore 27
          //   4005: aload 12
          //   4007: astore 30
          //   4009: aload 14
          //   4011: astore 18
          //   4013: aload 13
          //   4015: astore 19
          //   4017: iload 9
          //   4019: istore_3
          //   4020: aload 16
          //   4022: astore 28
          //   4024: aload 12
          //   4026: astore 32
          //   4028: aload 14
          //   4030: astore 20
          //   4032: aload 13
          //   4034: astore 22
          //   4036: iload 10
          //   4038: istore 4
          //   4040: aload 16
          //   4042: astore 31
          //   4044: aload 12
          //   4046: astore 33
          //   4048: aload 14
          //   4050: astore 23
          //   4052: aload 13
          //   4054: astore 25
          //   4056: iload 11
          //   4058: istore 5
          //   4060: aload 16
          //   4062: astore 35
          //   4064: aload 12
          //   4066: astore 34
          //   4068: aload 14
          //   4070: astore 24
          //   4072: aload 13
          //   4074: astore 21
          //   4076: aload 56
          //   4078: iconst_1
          //   4079: invokevirtual 135	org/apache/cordova/filetransfer/FileProgressResult:setLengthComputable	(Z)V
          //   4082: iload 7
          //   4084: istore_1
          //   4085: aload 16
          //   4087: astore 26
          //   4089: aload 12
          //   4091: astore 29
          //   4093: aload 14
          //   4095: astore 15
          //   4097: aload 13
          //   4099: astore 17
          //   4101: iload 8
          //   4103: istore_2
          //   4104: aload 16
          //   4106: astore 27
          //   4108: aload 12
          //   4110: astore 30
          //   4112: aload 14
          //   4114: astore 18
          //   4116: aload 13
          //   4118: astore 19
          //   4120: iload 9
          //   4122: istore_3
          //   4123: aload 16
          //   4125: astore 28
          //   4127: aload 12
          //   4129: astore 32
          //   4131: aload 14
          //   4133: astore 20
          //   4135: aload 13
          //   4137: astore 22
          //   4139: iload 10
          //   4141: istore 4
          //   4143: aload 16
          //   4145: astore 31
          //   4147: aload 12
          //   4149: astore 33
          //   4151: aload 14
          //   4153: astore 23
          //   4155: aload 13
          //   4157: astore 25
          //   4159: iload 11
          //   4161: istore 5
          //   4163: aload 16
          //   4165: astore 35
          //   4167: aload 12
          //   4169: astore 34
          //   4171: aload 14
          //   4173: astore 24
          //   4175: aload 13
          //   4177: astore 21
          //   4179: aload 56
          //   4181: aload 16
          //   4183: invokevirtual 291	java/net/HttpURLConnection:getContentLength	()I
          //   4186: i2l
          //   4187: invokevirtual 139	org/apache/cordova/filetransfer/FileProgressResult:setTotal	(J)V
          //   4190: iload 7
          //   4192: istore_1
          //   4193: aload 16
          //   4195: astore 26
          //   4197: aload 12
          //   4199: astore 29
          //   4201: aload 14
          //   4203: astore 15
          //   4205: aload 13
          //   4207: astore 17
          //   4209: iload 8
          //   4211: istore_2
          //   4212: aload 16
          //   4214: astore 27
          //   4216: aload 12
          //   4218: astore 30
          //   4220: aload 14
          //   4222: astore 18
          //   4224: aload 13
          //   4226: astore 19
          //   4228: iload 9
          //   4230: istore_3
          //   4231: aload 16
          //   4233: astore 28
          //   4235: aload 12
          //   4237: astore 32
          //   4239: aload 14
          //   4241: astore 20
          //   4243: aload 13
          //   4245: astore 22
          //   4247: iload 10
          //   4249: istore 4
          //   4251: aload 16
          //   4253: astore 31
          //   4255: aload 12
          //   4257: astore 33
          //   4259: aload 14
          //   4261: astore 23
          //   4263: aload 13
          //   4265: astore 25
          //   4267: iload 11
          //   4269: istore 5
          //   4271: aload 16
          //   4273: astore 35
          //   4275: aload 12
          //   4277: astore 34
          //   4279: aload 14
          //   4281: astore 24
          //   4283: aload 13
          //   4285: astore 21
          //   4287: aload 16
          //   4289: invokestatic 295	org/apache/cordova/filetransfer/FileTransfer:access$400	(Ljava/net/URLConnection;)Lorg/apache/cordova/filetransfer/FileTransfer$TrackingInputStream;
          //   4292: astore 38
          //   4294: aload 38
          //   4296: astore 26
          //   4298: aconst_null
          //   4299: astore 17
          //   4301: iload 6
          //   4303: istore_1
          //   4304: aload 14
          //   4306: astore 15
          //   4308: aload 13
          //   4310: astore 14
          //   4312: aload 17
          //   4314: astore 13
          //   4316: goto -3118 -> 1198
          //   4319: aload_0
          //   4320: getfield 36	org/apache/cordova/filetransfer/FileTransfer$4:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
          //   4323: aload 16
          //   4325: putfield 152	org/apache/cordova/filetransfer/FileTransfer$RequestContext:connection	Ljava/net/HttpURLConnection;
          //   4328: aload 17
          //   4330: monitorexit
          //   4331: aload 36
          //   4333: astore 27
          //   4335: sipush 16384
          //   4338: newarray <illegal type>
          //   4340: astore 17
          //   4342: aload 36
          //   4344: astore 27
          //   4346: aload_0
          //   4347: getfield 38	org/apache/cordova/filetransfer/FileTransfer$4:val$resourceApi	Lorg/apache/cordova/CordovaResourceApi;
          //   4350: aload_0
          //   4351: getfield 40	org/apache/cordova/filetransfer/FileTransfer$4:val$targetUri	Landroid/net/Uri;
          //   4354: invokevirtual 299	org/apache/cordova/CordovaResourceApi:openOutputStream	(Landroid/net/Uri;)Ljava/io/OutputStream;
          //   4357: astore 28
          //   4359: aload 28
          //   4361: astore 27
          //   4363: aload 26
          //   4365: aload 17
          //   4367: invokevirtual 305	org/apache/cordova/filetransfer/FileTransfer$TrackingInputStream:read	([B)I
          //   4370: istore_2
          //   4371: iload_2
          //   4372: ifle +567 -> 4939
          //   4375: aload 28
          //   4377: astore 27
          //   4379: aload 28
          //   4381: aload 17
          //   4383: iconst_0
          //   4384: iload_2
          //   4385: invokevirtual 311	java/io/OutputStream:write	([BII)V
          //   4388: aload 28
          //   4390: astore 27
          //   4392: aload 56
          //   4394: aload 26
          //   4396: invokevirtual 315	org/apache/cordova/filetransfer/FileTransfer$TrackingInputStream:getTotalRawBytesRead	()J
          //   4399: invokevirtual 318	org/apache/cordova/filetransfer/FileProgressResult:setLoaded	(J)V
          //   4402: aload 28
          //   4404: astore 27
          //   4406: new 178	org/apache/cordova/PluginResult
          //   4409: dup
          //   4410: getstatic 202	org/apache/cordova/PluginResult$Status:OK	Lorg/apache/cordova/PluginResult$Status;
          //   4413: aload 56
          //   4415: invokevirtual 322	org/apache/cordova/filetransfer/FileProgressResult:toJSONObject	()Lorg/json/JSONObject;
          //   4418: invokespecial 195	org/apache/cordova/PluginResult:<init>	(Lorg/apache/cordova/PluginResult$Status;Lorg/json/JSONObject;)V
          //   4421: astore 18
          //   4423: aload 28
          //   4425: astore 27
          //   4427: aload 18
          //   4429: iconst_1
          //   4430: invokevirtual 325	org/apache/cordova/PluginResult:setKeepCallback	(Z)V
          //   4433: aload 28
          //   4435: astore 27
          //   4437: aload_0
          //   4438: getfield 36	org/apache/cordova/filetransfer/FileTransfer$4:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
          //   4441: aload 18
          //   4443: invokevirtual 215	org/apache/cordova/filetransfer/FileTransfer$RequestContext:sendPluginResult	(Lorg/apache/cordova/PluginResult;)V
          //   4446: goto -87 -> 4359
          //   4449: astore 29
          //   4451: aload 12
          //   4453: astore 22
          //   4455: aload 12
          //   4457: astore 23
          //   4459: aload 12
          //   4461: astore 24
          //   4463: aload 12
          //   4465: astore 25
          //   4467: iload_1
          //   4468: istore_2
          //   4469: aload 16
          //   4471: astore 20
          //   4473: aload 12
          //   4475: astore 17
          //   4477: aload 15
          //   4479: astore 19
          //   4481: aload 14
          //   4483: astore 18
          //   4485: aload 13
          //   4487: astore 21
          //   4489: aload_0
          //   4490: getfield 36	org/apache/cordova/filetransfer/FileTransfer$4:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
          //   4493: astore 28
          //   4495: aload 12
          //   4497: astore 22
          //   4499: aload 12
          //   4501: astore 23
          //   4503: aload 12
          //   4505: astore 24
          //   4507: aload 12
          //   4509: astore 25
          //   4511: iload_1
          //   4512: istore_2
          //   4513: aload 16
          //   4515: astore 20
          //   4517: aload 12
          //   4519: astore 17
          //   4521: aload 15
          //   4523: astore 19
          //   4525: aload 14
          //   4527: astore 18
          //   4529: aload 13
          //   4531: astore 21
          //   4533: aload 28
          //   4535: monitorenter
          //   4536: aload_0
          //   4537: getfield 36	org/apache/cordova/filetransfer/FileTransfer$4:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
          //   4540: aconst_null
          //   4541: putfield 152	org/apache/cordova/filetransfer/FileTransfer$RequestContext:connection	Ljava/net/HttpURLConnection;
          //   4544: aload 28
          //   4546: monitorexit
          //   4547: aload 12
          //   4549: astore 22
          //   4551: aload 12
          //   4553: astore 23
          //   4555: aload 12
          //   4557: astore 24
          //   4559: aload 12
          //   4561: astore 25
          //   4563: iload_1
          //   4564: istore_2
          //   4565: aload 16
          //   4567: astore 20
          //   4569: aload 12
          //   4571: astore 17
          //   4573: aload 15
          //   4575: astore 19
          //   4577: aload 14
          //   4579: astore 18
          //   4581: aload 13
          //   4583: astore 21
          //   4585: aload 26
          //   4587: invokestatic 156	org/apache/cordova/filetransfer/FileTransfer:access$300	(Ljava/io/Closeable;)V
          //   4590: aload 12
          //   4592: astore 22
          //   4594: aload 12
          //   4596: astore 23
          //   4598: aload 12
          //   4600: astore 24
          //   4602: aload 12
          //   4604: astore 25
          //   4606: iload_1
          //   4607: istore_2
          //   4608: aload 16
          //   4610: astore 20
          //   4612: aload 12
          //   4614: astore 17
          //   4616: aload 15
          //   4618: astore 19
          //   4620: aload 14
          //   4622: astore 18
          //   4624: aload 13
          //   4626: astore 21
          //   4628: aload 27
          //   4630: invokestatic 156	org/apache/cordova/filetransfer/FileTransfer:access$300	(Ljava/io/Closeable;)V
          //   4633: aload 12
          //   4635: astore 22
          //   4637: aload 12
          //   4639: astore 23
          //   4641: aload 12
          //   4643: astore 24
          //   4645: aload 12
          //   4647: astore 25
          //   4649: iload_1
          //   4650: istore_2
          //   4651: aload 16
          //   4653: astore 20
          //   4655: aload 12
          //   4657: astore 17
          //   4659: aload 15
          //   4661: astore 19
          //   4663: aload 14
          //   4665: astore 18
          //   4667: aload 13
          //   4669: astore 21
          //   4671: aload 29
          //   4673: athrow
          //   4674: astore 12
          //   4676: iload_1
          //   4677: istore_2
          //   4678: aload 16
          //   4680: astore 20
          //   4682: aload 22
          //   4684: astore 17
          //   4686: aload 15
          //   4688: astore 19
          //   4690: aload 14
          //   4692: astore 18
          //   4694: aload 13
          //   4696: astore 21
          //   4698: getstatic 328	org/apache/cordova/filetransfer/FileTransfer:FILE_NOT_FOUND_ERR	I
          //   4701: aload_0
          //   4702: getfield 52	org/apache/cordova/filetransfer/FileTransfer$4:val$source	Ljava/lang/String;
          //   4705: aload_0
          //   4706: getfield 54	org/apache/cordova/filetransfer/FileTransfer$4:val$target	Ljava/lang/String;
          //   4709: aload 16
          //   4711: aload 12
          //   4713: invokestatic 192	org/apache/cordova/filetransfer/FileTransfer:access$500	(ILjava/lang/String;Ljava/lang/String;Ljava/net/URLConnection;Ljava/lang/Throwable;)Lorg/json/JSONObject;
          //   4716: astore 23
          //   4718: iload_1
          //   4719: istore_2
          //   4720: aload 16
          //   4722: astore 20
          //   4724: aload 22
          //   4726: astore 17
          //   4728: aload 15
          //   4730: astore 19
          //   4732: aload 14
          //   4734: astore 18
          //   4736: aload 13
          //   4738: astore 21
          //   4740: ldc 94
          //   4742: aload 23
          //   4744: invokevirtual 331	org/json/JSONObject:toString	()Ljava/lang/String;
          //   4747: aload 12
          //   4749: invokestatic 335	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
          //   4752: pop
          //   4753: iload_1
          //   4754: istore_2
          //   4755: aload 16
          //   4757: astore 20
          //   4759: aload 22
          //   4761: astore 17
          //   4763: aload 15
          //   4765: astore 19
          //   4767: aload 14
          //   4769: astore 18
          //   4771: aload 13
          //   4773: astore 21
          //   4775: new 178	org/apache/cordova/PluginResult
          //   4778: dup
          //   4779: getstatic 338	org/apache/cordova/PluginResult$Status:IO_EXCEPTION	Lorg/apache/cordova/PluginResult$Status;
          //   4782: aload 23
          //   4784: invokespecial 195	org/apache/cordova/PluginResult:<init>	(Lorg/apache/cordova/PluginResult$Status;Lorg/json/JSONObject;)V
          //   4787: astore 13
          //   4789: invokestatic 160	org/apache/cordova/filetransfer/FileTransfer:access$600	()Ljava/util/HashMap;
          //   4792: astore 12
          //   4794: aload 12
          //   4796: monitorenter
          //   4797: invokestatic 160	org/apache/cordova/filetransfer/FileTransfer:access$600	()Ljava/util/HashMap;
          //   4800: aload_0
          //   4801: getfield 56	org/apache/cordova/filetransfer/FileTransfer$4:val$objectId	Ljava/lang/String;
          //   4804: invokevirtual 166	java/util/HashMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
          //   4807: pop
          //   4808: aload 12
          //   4810: monitorexit
          //   4811: aload 16
          //   4813: ifnull +38 -> 4851
          //   4816: aload_0
          //   4817: getfield 48	org/apache/cordova/filetransfer/FileTransfer$4:val$trustEveryone	Z
          //   4820: ifeq +31 -> 4851
          //   4823: aload_0
          //   4824: getfield 46	org/apache/cordova/filetransfer/FileTransfer$4:val$useHttps	Z
          //   4827: ifeq +24 -> 4851
          //   4830: aload 16
          //   4832: checkcast 168	javax/net/ssl/HttpsURLConnection
          //   4835: astore 12
          //   4837: aload 12
          //   4839: aload 15
          //   4841: invokevirtual 172	javax/net/ssl/HttpsURLConnection:setHostnameVerifier	(Ljavax/net/ssl/HostnameVerifier;)V
          //   4844: aload 12
          //   4846: aload 14
          //   4848: invokevirtual 176	javax/net/ssl/HttpsURLConnection:setSSLSocketFactory	(Ljavax/net/ssl/SSLSocketFactory;)V
          //   4851: aload 13
          //   4853: astore 12
          //   4855: aload 13
          //   4857: ifnonnull +32 -> 4889
          //   4860: new 178	org/apache/cordova/PluginResult
          //   4863: dup
          //   4864: getstatic 184	org/apache/cordova/PluginResult$Status:ERROR	Lorg/apache/cordova/PluginResult$Status;
          //   4867: getstatic 188	org/apache/cordova/filetransfer/FileTransfer:CONNECTION_ERR	I
          //   4870: aload_0
          //   4871: getfield 52	org/apache/cordova/filetransfer/FileTransfer$4:val$source	Ljava/lang/String;
          //   4874: aload_0
          //   4875: getfield 54	org/apache/cordova/filetransfer/FileTransfer$4:val$target	Ljava/lang/String;
          //   4878: aload 16
          //   4880: aconst_null
          //   4881: invokestatic 192	org/apache/cordova/filetransfer/FileTransfer:access$500	(ILjava/lang/String;Ljava/lang/String;Ljava/net/URLConnection;Ljava/lang/Throwable;)Lorg/json/JSONObject;
          //   4884: invokespecial 195	org/apache/cordova/PluginResult:<init>	(Lorg/apache/cordova/PluginResult$Status;Lorg/json/JSONObject;)V
          //   4887: astore 12
          //   4889: iload_1
          //   4890: ifne +28 -> 4918
          //   4893: aload 12
          //   4895: invokevirtual 199	org/apache/cordova/PluginResult:getStatus	()I
          //   4898: getstatic 202	org/apache/cordova/PluginResult$Status:OK	Lorg/apache/cordova/PluginResult$Status;
          //   4901: invokevirtual 205	org/apache/cordova/PluginResult$Status:ordinal	()I
          //   4904: if_icmpeq +14 -> 4918
          //   4907: aload 22
          //   4909: ifnull +9 -> 4918
          //   4912: aload 22
          //   4914: invokevirtual 211	java/io/File:delete	()Z
          //   4917: pop
          //   4918: aload_0
          //   4919: getfield 36	org/apache/cordova/filetransfer/FileTransfer$4:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
          //   4922: astore 13
          //   4924: goto -3376 -> 1548
          //   4927: astore 18
          //   4929: aload 17
          //   4931: monitorexit
          //   4932: aload 36
          //   4934: astore 27
          //   4936: aload 18
          //   4938: athrow
          //   4939: aload 12
          //   4941: astore 22
          //   4943: aload 12
          //   4945: astore 23
          //   4947: aload 12
          //   4949: astore 24
          //   4951: aload 12
          //   4953: astore 25
          //   4955: iload_1
          //   4956: istore_2
          //   4957: aload 16
          //   4959: astore 20
          //   4961: aload 12
          //   4963: astore 17
          //   4965: aload 15
          //   4967: astore 19
          //   4969: aload 14
          //   4971: astore 18
          //   4973: aload 13
          //   4975: astore 21
          //   4977: aload_0
          //   4978: getfield 36	org/apache/cordova/filetransfer/FileTransfer$4:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
          //   4981: astore 27
          //   4983: aload 12
          //   4985: astore 22
          //   4987: aload 12
          //   4989: astore 23
          //   4991: aload 12
          //   4993: astore 24
          //   4995: aload 12
          //   4997: astore 25
          //   4999: iload_1
          //   5000: istore_2
          //   5001: aload 16
          //   5003: astore 20
          //   5005: aload 12
          //   5007: astore 17
          //   5009: aload 15
          //   5011: astore 19
          //   5013: aload 14
          //   5015: astore 18
          //   5017: aload 13
          //   5019: astore 21
          //   5021: aload 27
          //   5023: monitorenter
          //   5024: aload_0
          //   5025: getfield 36	org/apache/cordova/filetransfer/FileTransfer$4:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
          //   5028: aconst_null
          //   5029: putfield 152	org/apache/cordova/filetransfer/FileTransfer$RequestContext:connection	Ljava/net/HttpURLConnection;
          //   5032: aload 27
          //   5034: monitorexit
          //   5035: aload 12
          //   5037: astore 22
          //   5039: aload 12
          //   5041: astore 23
          //   5043: aload 12
          //   5045: astore 24
          //   5047: aload 12
          //   5049: astore 25
          //   5051: iload_1
          //   5052: istore_2
          //   5053: aload 16
          //   5055: astore 20
          //   5057: aload 12
          //   5059: astore 17
          //   5061: aload 15
          //   5063: astore 19
          //   5065: aload 14
          //   5067: astore 18
          //   5069: aload 13
          //   5071: astore 21
          //   5073: aload 26
          //   5075: invokestatic 156	org/apache/cordova/filetransfer/FileTransfer:access$300	(Ljava/io/Closeable;)V
          //   5078: aload 12
          //   5080: astore 22
          //   5082: aload 12
          //   5084: astore 23
          //   5086: aload 12
          //   5088: astore 24
          //   5090: aload 12
          //   5092: astore 25
          //   5094: iload_1
          //   5095: istore_2
          //   5096: aload 16
          //   5098: astore 20
          //   5100: aload 12
          //   5102: astore 17
          //   5104: aload 15
          //   5106: astore 19
          //   5108: aload 14
          //   5110: astore 18
          //   5112: aload 13
          //   5114: astore 21
          //   5116: aload 28
          //   5118: invokestatic 156	org/apache/cordova/filetransfer/FileTransfer:access$300	(Ljava/io/Closeable;)V
          //   5121: aload 12
          //   5123: astore 22
          //   5125: aload 12
          //   5127: astore 23
          //   5129: aload 12
          //   5131: astore 24
          //   5133: aload 12
          //   5135: astore 25
          //   5137: iload_1
          //   5138: istore_2
          //   5139: aload 16
          //   5141: astore 20
          //   5143: aload 12
          //   5145: astore 17
          //   5147: aload 15
          //   5149: astore 19
          //   5151: aload 14
          //   5153: astore 18
          //   5155: aload 13
          //   5157: astore 21
          //   5159: ldc 94
          //   5161: new 96	java/lang/StringBuilder
          //   5164: dup
          //   5165: invokespecial 97	java/lang/StringBuilder:<init>	()V
          //   5168: ldc_w 340
          //   5171: invokevirtual 103	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   5174: aload_0
          //   5175: getfield 54	org/apache/cordova/filetransfer/FileTransfer$4:val$target	Ljava/lang/String;
          //   5178: invokevirtual 103	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   5181: invokevirtual 110	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   5184: invokestatic 116	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
          //   5187: pop
          //   5188: aload 12
          //   5190: astore 22
          //   5192: aload 12
          //   5194: astore 23
          //   5196: aload 12
          //   5198: astore 24
          //   5200: aload 12
          //   5202: astore 25
          //   5204: iload_1
          //   5205: istore_2
          //   5206: aload 16
          //   5208: astore 20
          //   5210: aload 12
          //   5212: astore 17
          //   5214: aload 15
          //   5216: astore 19
          //   5218: aload 14
          //   5220: astore 18
          //   5222: aload 13
          //   5224: astore 21
          //   5226: aload_0
          //   5227: getfield 34	org/apache/cordova/filetransfer/FileTransfer$4:this$0	Lorg/apache/cordova/filetransfer/FileTransfer;
          //   5230: getfield 344	org/apache/cordova/filetransfer/FileTransfer:webView	Lorg/apache/cordova/CordovaWebView;
          //   5233: invokevirtual 348	java/lang/Object:getClass	()Ljava/lang/Class;
          //   5236: astore 28
          //   5238: aconst_null
          //   5239: astore 26
          //   5241: aload 12
          //   5243: astore 22
          //   5245: aload 12
          //   5247: astore 23
          //   5249: aload 12
          //   5251: astore 24
          //   5253: aload 12
          //   5255: astore 25
          //   5257: iload_1
          //   5258: istore_2
          //   5259: aload 16
          //   5261: astore 20
          //   5263: aload 12
          //   5265: astore 17
          //   5267: aload 15
          //   5269: astore 19
          //   5271: aload 14
          //   5273: astore 18
          //   5275: aload 13
          //   5277: astore 21
          //   5279: aload 28
          //   5281: ldc_w 350
          //   5284: iconst_0
          //   5285: anewarray 352	java/lang/Class
          //   5288: invokevirtual 356	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
          //   5291: aload_0
          //   5292: getfield 34	org/apache/cordova/filetransfer/FileTransfer$4:this$0	Lorg/apache/cordova/filetransfer/FileTransfer;
          //   5295: getfield 344	org/apache/cordova/filetransfer/FileTransfer:webView	Lorg/apache/cordova/CordovaWebView;
          //   5298: iconst_0
          //   5299: anewarray 4	java/lang/Object
          //   5302: invokevirtual 362	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
          //   5305: checkcast 364	org/apache/cordova/PluginManager
          //   5308: astore 27
          //   5310: aload 27
          //   5312: astore 26
          //   5314: aload 26
          //   5316: astore 27
          //   5318: aload 26
          //   5320: ifnonnull +64 -> 5384
          //   5323: aload 12
          //   5325: astore 22
          //   5327: aload 12
          //   5329: astore 23
          //   5331: aload 12
          //   5333: astore 24
          //   5335: aload 12
          //   5337: astore 25
          //   5339: iload_1
          //   5340: istore_2
          //   5341: aload 16
          //   5343: astore 20
          //   5345: aload 12
          //   5347: astore 17
          //   5349: aload 15
          //   5351: astore 19
          //   5353: aload 14
          //   5355: astore 18
          //   5357: aload 13
          //   5359: astore 21
          //   5361: aload 28
          //   5363: ldc_w 366
          //   5366: invokevirtual 370	java/lang/Class:getField	(Ljava/lang/String;)Ljava/lang/reflect/Field;
          //   5369: aload_0
          //   5370: getfield 34	org/apache/cordova/filetransfer/FileTransfer$4:this$0	Lorg/apache/cordova/filetransfer/FileTransfer;
          //   5373: getfield 344	org/apache/cordova/filetransfer/FileTransfer:webView	Lorg/apache/cordova/CordovaWebView;
          //   5376: invokevirtual 375	java/lang/reflect/Field:get	(Ljava/lang/Object;)Ljava/lang/Object;
          //   5379: checkcast 364	org/apache/cordova/PluginManager
          //   5382: astore 27
          //   5384: aload 12
          //   5386: astore 22
          //   5388: aload 12
          //   5390: astore 23
          //   5392: aload 12
          //   5394: astore 24
          //   5396: aload 12
          //   5398: astore 25
          //   5400: iload_1
          //   5401: istore_2
          //   5402: aload 16
          //   5404: astore 20
          //   5406: aload 12
          //   5408: astore 17
          //   5410: aload 15
          //   5412: astore 19
          //   5414: aload 14
          //   5416: astore 18
          //   5418: aload 13
          //   5420: astore 21
          //   5422: aload_0
          //   5423: getfield 38	org/apache/cordova/filetransfer/FileTransfer$4:val$resourceApi	Lorg/apache/cordova/CordovaResourceApi;
          //   5426: aload_0
          //   5427: getfield 40	org/apache/cordova/filetransfer/FileTransfer$4:val$targetUri	Landroid/net/Uri;
          //   5430: invokevirtual 88	org/apache/cordova/CordovaResourceApi:mapUriToFile	(Landroid/net/Uri;)Ljava/io/File;
          //   5433: astore 12
          //   5435: aload 12
          //   5437: astore 22
          //   5439: aload 12
          //   5441: astore 23
          //   5443: aload 12
          //   5445: astore 24
          //   5447: aload 12
          //   5449: astore 25
          //   5451: iload_1
          //   5452: istore_2
          //   5453: aload 16
          //   5455: astore 20
          //   5457: aload 12
          //   5459: astore 17
          //   5461: aload 15
          //   5463: astore 19
          //   5465: aload 14
          //   5467: astore 18
          //   5469: aload 13
          //   5471: astore 21
          //   5473: aload_0
          //   5474: getfield 36	org/apache/cordova/filetransfer/FileTransfer$4:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
          //   5477: aload 12
          //   5479: putfield 92	org/apache/cordova/filetransfer/FileTransfer$RequestContext:targetFile	Ljava/io/File;
          //   5482: aload 12
          //   5484: astore 22
          //   5486: aload 12
          //   5488: astore 23
          //   5490: aload 12
          //   5492: astore 24
          //   5494: aload 12
          //   5496: astore 25
          //   5498: iload_1
          //   5499: istore_2
          //   5500: aload 16
          //   5502: astore 20
          //   5504: aload 12
          //   5506: astore 17
          //   5508: aload 15
          //   5510: astore 19
          //   5512: aload 14
          //   5514: astore 18
          //   5516: aload 13
          //   5518: astore 21
          //   5520: aload 27
          //   5522: ldc_w 377
          //   5525: invokevirtual 381	org/apache/cordova/PluginManager:getPlugin	(Ljava/lang/String;)Lorg/apache/cordova/CordovaPlugin;
          //   5528: checkcast 383	org/apache/cordova/file/FileUtils
          //   5531: astore 26
          //   5533: aload 26
          //   5535: ifnull +420 -> 5955
          //   5538: aload 12
          //   5540: astore 22
          //   5542: aload 12
          //   5544: astore 23
          //   5546: aload 12
          //   5548: astore 24
          //   5550: aload 12
          //   5552: astore 25
          //   5554: iload_1
          //   5555: istore_2
          //   5556: aload 16
          //   5558: astore 20
          //   5560: aload 12
          //   5562: astore 17
          //   5564: aload 15
          //   5566: astore 19
          //   5568: aload 14
          //   5570: astore 18
          //   5572: aload 13
          //   5574: astore 21
          //   5576: aload 26
          //   5578: aload 12
          //   5580: invokevirtual 387	org/apache/cordova/file/FileUtils:getEntryForFile	(Ljava/io/File;)Lorg/json/JSONObject;
          //   5583: astore 26
          //   5585: aload 26
          //   5587: ifnull +201 -> 5788
          //   5590: aload 12
          //   5592: astore 22
          //   5594: aload 12
          //   5596: astore 23
          //   5598: aload 12
          //   5600: astore 24
          //   5602: aload 12
          //   5604: astore 25
          //   5606: iload_1
          //   5607: istore_2
          //   5608: aload 16
          //   5610: astore 20
          //   5612: aload 12
          //   5614: astore 17
          //   5616: aload 15
          //   5618: astore 19
          //   5620: aload 14
          //   5622: astore 18
          //   5624: aload 13
          //   5626: astore 21
          //   5628: new 178	org/apache/cordova/PluginResult
          //   5631: dup
          //   5632: getstatic 202	org/apache/cordova/PluginResult$Status:OK	Lorg/apache/cordova/PluginResult$Status;
          //   5635: aload 26
          //   5637: invokespecial 195	org/apache/cordova/PluginResult:<init>	(Lorg/apache/cordova/PluginResult$Status;Lorg/json/JSONObject;)V
          //   5640: astore 26
          //   5642: aload 26
          //   5644: astore 13
          //   5646: aload 12
          //   5648: astore 17
          //   5650: invokestatic 160	org/apache/cordova/filetransfer/FileTransfer:access$600	()Ljava/util/HashMap;
          //   5653: astore 12
          //   5655: aload 12
          //   5657: monitorenter
          //   5658: invokestatic 160	org/apache/cordova/filetransfer/FileTransfer:access$600	()Ljava/util/HashMap;
          //   5661: aload_0
          //   5662: getfield 56	org/apache/cordova/filetransfer/FileTransfer$4:val$objectId	Ljava/lang/String;
          //   5665: invokevirtual 166	java/util/HashMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
          //   5668: pop
          //   5669: aload 12
          //   5671: monitorexit
          //   5672: aload 16
          //   5674: ifnull +38 -> 5712
          //   5677: aload_0
          //   5678: getfield 48	org/apache/cordova/filetransfer/FileTransfer$4:val$trustEveryone	Z
          //   5681: ifeq +31 -> 5712
          //   5684: aload_0
          //   5685: getfield 46	org/apache/cordova/filetransfer/FileTransfer$4:val$useHttps	Z
          //   5688: ifeq +24 -> 5712
          //   5691: aload 16
          //   5693: checkcast 168	javax/net/ssl/HttpsURLConnection
          //   5696: astore 12
          //   5698: aload 12
          //   5700: aload 15
          //   5702: invokevirtual 172	javax/net/ssl/HttpsURLConnection:setHostnameVerifier	(Ljavax/net/ssl/HostnameVerifier;)V
          //   5705: aload 12
          //   5707: aload 14
          //   5709: invokevirtual 176	javax/net/ssl/HttpsURLConnection:setSSLSocketFactory	(Ljavax/net/ssl/SSLSocketFactory;)V
          //   5712: aload 13
          //   5714: astore 12
          //   5716: aload 13
          //   5718: ifnonnull +32 -> 5750
          //   5721: new 178	org/apache/cordova/PluginResult
          //   5724: dup
          //   5725: getstatic 184	org/apache/cordova/PluginResult$Status:ERROR	Lorg/apache/cordova/PluginResult$Status;
          //   5728: getstatic 188	org/apache/cordova/filetransfer/FileTransfer:CONNECTION_ERR	I
          //   5731: aload_0
          //   5732: getfield 52	org/apache/cordova/filetransfer/FileTransfer$4:val$source	Ljava/lang/String;
          //   5735: aload_0
          //   5736: getfield 54	org/apache/cordova/filetransfer/FileTransfer$4:val$target	Ljava/lang/String;
          //   5739: aload 16
          //   5741: aconst_null
          //   5742: invokestatic 192	org/apache/cordova/filetransfer/FileTransfer:access$500	(ILjava/lang/String;Ljava/lang/String;Ljava/net/URLConnection;Ljava/lang/Throwable;)Lorg/json/JSONObject;
          //   5745: invokespecial 195	org/apache/cordova/PluginResult:<init>	(Lorg/apache/cordova/PluginResult$Status;Lorg/json/JSONObject;)V
          //   5748: astore 12
          //   5750: iload_1
          //   5751: ifne +28 -> 5779
          //   5754: aload 12
          //   5756: invokevirtual 199	org/apache/cordova/PluginResult:getStatus	()I
          //   5759: getstatic 202	org/apache/cordova/PluginResult$Status:OK	Lorg/apache/cordova/PluginResult$Status;
          //   5762: invokevirtual 205	org/apache/cordova/PluginResult$Status:ordinal	()I
          //   5765: if_icmpeq +14 -> 5779
          //   5768: aload 17
          //   5770: ifnull +9 -> 5779
          //   5773: aload 17
          //   5775: invokevirtual 211	java/io/File:delete	()Z
          //   5778: pop
          //   5779: aload_0
          //   5780: getfield 36	org/apache/cordova/filetransfer/FileTransfer$4:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
          //   5783: astore 13
          //   5785: goto -4237 -> 1548
          //   5788: aload 12
          //   5790: astore 22
          //   5792: aload 12
          //   5794: astore 23
          //   5796: aload 12
          //   5798: astore 24
          //   5800: aload 12
          //   5802: astore 25
          //   5804: iload_1
          //   5805: istore_2
          //   5806: aload 16
          //   5808: astore 20
          //   5810: aload 12
          //   5812: astore 17
          //   5814: aload 15
          //   5816: astore 19
          //   5818: aload 14
          //   5820: astore 18
          //   5822: aload 13
          //   5824: astore 21
          //   5826: getstatic 188	org/apache/cordova/filetransfer/FileTransfer:CONNECTION_ERR	I
          //   5829: aload_0
          //   5830: getfield 52	org/apache/cordova/filetransfer/FileTransfer$4:val$source	Ljava/lang/String;
          //   5833: aload_0
          //   5834: getfield 54	org/apache/cordova/filetransfer/FileTransfer$4:val$target	Ljava/lang/String;
          //   5837: aload 16
          //   5839: aconst_null
          //   5840: invokestatic 192	org/apache/cordova/filetransfer/FileTransfer:access$500	(ILjava/lang/String;Ljava/lang/String;Ljava/net/URLConnection;Ljava/lang/Throwable;)Lorg/json/JSONObject;
          //   5843: astore 26
          //   5845: aload 12
          //   5847: astore 22
          //   5849: aload 12
          //   5851: astore 23
          //   5853: aload 12
          //   5855: astore 24
          //   5857: aload 12
          //   5859: astore 25
          //   5861: iload_1
          //   5862: istore_2
          //   5863: aload 16
          //   5865: astore 20
          //   5867: aload 12
          //   5869: astore 17
          //   5871: aload 15
          //   5873: astore 19
          //   5875: aload 14
          //   5877: astore 18
          //   5879: aload 13
          //   5881: astore 21
          //   5883: ldc 94
          //   5885: ldc_w 389
          //   5888: invokestatic 391	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
          //   5891: pop
          //   5892: aload 12
          //   5894: astore 22
          //   5896: aload 12
          //   5898: astore 23
          //   5900: aload 12
          //   5902: astore 24
          //   5904: aload 12
          //   5906: astore 25
          //   5908: iload_1
          //   5909: istore_2
          //   5910: aload 16
          //   5912: astore 20
          //   5914: aload 12
          //   5916: astore 17
          //   5918: aload 15
          //   5920: astore 19
          //   5922: aload 14
          //   5924: astore 18
          //   5926: aload 13
          //   5928: astore 21
          //   5930: new 178	org/apache/cordova/PluginResult
          //   5933: dup
          //   5934: getstatic 338	org/apache/cordova/PluginResult$Status:IO_EXCEPTION	Lorg/apache/cordova/PluginResult$Status;
          //   5937: aload 26
          //   5939: invokespecial 195	org/apache/cordova/PluginResult:<init>	(Lorg/apache/cordova/PluginResult$Status;Lorg/json/JSONObject;)V
          //   5942: astore 26
          //   5944: aload 12
          //   5946: astore 17
          //   5948: aload 26
          //   5950: astore 13
          //   5952: goto -302 -> 5650
          //   5955: aload 12
          //   5957: astore 22
          //   5959: aload 12
          //   5961: astore 23
          //   5963: aload 12
          //   5965: astore 24
          //   5967: aload 12
          //   5969: astore 25
          //   5971: iload_1
          //   5972: istore_2
          //   5973: aload 16
          //   5975: astore 20
          //   5977: aload 12
          //   5979: astore 17
          //   5981: aload 15
          //   5983: astore 19
          //   5985: aload 14
          //   5987: astore 18
          //   5989: aload 13
          //   5991: astore 21
          //   5993: ldc 94
          //   5995: ldc_w 393
          //   5998: invokestatic 391	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
          //   6001: pop
          //   6002: aload 12
          //   6004: astore 22
          //   6006: aload 12
          //   6008: astore 23
          //   6010: aload 12
          //   6012: astore 24
          //   6014: aload 12
          //   6016: astore 25
          //   6018: iload_1
          //   6019: istore_2
          //   6020: aload 16
          //   6022: astore 20
          //   6024: aload 12
          //   6026: astore 17
          //   6028: aload 15
          //   6030: astore 19
          //   6032: aload 14
          //   6034: astore 18
          //   6036: aload 13
          //   6038: astore 21
          //   6040: new 178	org/apache/cordova/PluginResult
          //   6043: dup
          //   6044: getstatic 184	org/apache/cordova/PluginResult$Status:ERROR	Lorg/apache/cordova/PluginResult$Status;
          //   6047: ldc_w 393
          //   6050: invokespecial 396	org/apache/cordova/PluginResult:<init>	(Lorg/apache/cordova/PluginResult$Status;Ljava/lang/String;)V
          //   6053: astore 26
          //   6055: aload 12
          //   6057: astore 17
          //   6059: aload 26
          //   6061: astore 13
          //   6063: goto -413 -> 5650
          //   6066: astore 12
          //   6068: aconst_null
          //   6069: astore 13
          //   6071: aload 17
          //   6073: astore 14
          //   6075: aload 29
          //   6077: astore 23
          //   6079: aload 26
          //   6081: astore 16
          //   6083: iload_1
          //   6084: istore_2
          //   6085: aload 16
          //   6087: astore 20
          //   6089: aload 23
          //   6091: astore 17
          //   6093: aload 15
          //   6095: astore 19
          //   6097: aload 14
          //   6099: astore 18
          //   6101: aload 13
          //   6103: astore 21
          //   6105: getstatic 188	org/apache/cordova/filetransfer/FileTransfer:CONNECTION_ERR	I
          //   6108: aload_0
          //   6109: getfield 52	org/apache/cordova/filetransfer/FileTransfer$4:val$source	Ljava/lang/String;
          //   6112: aload_0
          //   6113: getfield 54	org/apache/cordova/filetransfer/FileTransfer$4:val$target	Ljava/lang/String;
          //   6116: aload 16
          //   6118: aload 12
          //   6120: invokestatic 192	org/apache/cordova/filetransfer/FileTransfer:access$500	(ILjava/lang/String;Ljava/lang/String;Ljava/net/URLConnection;Ljava/lang/Throwable;)Lorg/json/JSONObject;
          //   6123: astore 22
          //   6125: iload_1
          //   6126: istore_2
          //   6127: aload 16
          //   6129: astore 20
          //   6131: aload 23
          //   6133: astore 17
          //   6135: aload 15
          //   6137: astore 19
          //   6139: aload 14
          //   6141: astore 18
          //   6143: aload 13
          //   6145: astore 21
          //   6147: ldc 94
          //   6149: aload 22
          //   6151: invokevirtual 331	org/json/JSONObject:toString	()Ljava/lang/String;
          //   6154: aload 12
          //   6156: invokestatic 335	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
          //   6159: pop
          //   6160: iload_1
          //   6161: istore_2
          //   6162: aload 16
          //   6164: astore 20
          //   6166: aload 23
          //   6168: astore 17
          //   6170: aload 15
          //   6172: astore 19
          //   6174: aload 14
          //   6176: astore 18
          //   6178: aload 13
          //   6180: astore 21
          //   6182: new 178	org/apache/cordova/PluginResult
          //   6185: dup
          //   6186: getstatic 338	org/apache/cordova/PluginResult$Status:IO_EXCEPTION	Lorg/apache/cordova/PluginResult$Status;
          //   6189: aload 22
          //   6191: invokespecial 195	org/apache/cordova/PluginResult:<init>	(Lorg/apache/cordova/PluginResult$Status;Lorg/json/JSONObject;)V
          //   6194: astore 13
          //   6196: invokestatic 160	org/apache/cordova/filetransfer/FileTransfer:access$600	()Ljava/util/HashMap;
          //   6199: astore 12
          //   6201: aload 12
          //   6203: monitorenter
          //   6204: invokestatic 160	org/apache/cordova/filetransfer/FileTransfer:access$600	()Ljava/util/HashMap;
          //   6207: aload_0
          //   6208: getfield 56	org/apache/cordova/filetransfer/FileTransfer$4:val$objectId	Ljava/lang/String;
          //   6211: invokevirtual 166	java/util/HashMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
          //   6214: pop
          //   6215: aload 12
          //   6217: monitorexit
          //   6218: aload 16
          //   6220: ifnull +38 -> 6258
          //   6223: aload_0
          //   6224: getfield 48	org/apache/cordova/filetransfer/FileTransfer$4:val$trustEveryone	Z
          //   6227: ifeq +31 -> 6258
          //   6230: aload_0
          //   6231: getfield 46	org/apache/cordova/filetransfer/FileTransfer$4:val$useHttps	Z
          //   6234: ifeq +24 -> 6258
          //   6237: aload 16
          //   6239: checkcast 168	javax/net/ssl/HttpsURLConnection
          //   6242: astore 12
          //   6244: aload 12
          //   6246: aload 15
          //   6248: invokevirtual 172	javax/net/ssl/HttpsURLConnection:setHostnameVerifier	(Ljavax/net/ssl/HostnameVerifier;)V
          //   6251: aload 12
          //   6253: aload 14
          //   6255: invokevirtual 176	javax/net/ssl/HttpsURLConnection:setSSLSocketFactory	(Ljavax/net/ssl/SSLSocketFactory;)V
          //   6258: aload 13
          //   6260: astore 12
          //   6262: aload 13
          //   6264: ifnonnull +32 -> 6296
          //   6267: new 178	org/apache/cordova/PluginResult
          //   6270: dup
          //   6271: getstatic 184	org/apache/cordova/PluginResult$Status:ERROR	Lorg/apache/cordova/PluginResult$Status;
          //   6274: getstatic 188	org/apache/cordova/filetransfer/FileTransfer:CONNECTION_ERR	I
          //   6277: aload_0
          //   6278: getfield 52	org/apache/cordova/filetransfer/FileTransfer$4:val$source	Ljava/lang/String;
          //   6281: aload_0
          //   6282: getfield 54	org/apache/cordova/filetransfer/FileTransfer$4:val$target	Ljava/lang/String;
          //   6285: aload 16
          //   6287: aconst_null
          //   6288: invokestatic 192	org/apache/cordova/filetransfer/FileTransfer:access$500	(ILjava/lang/String;Ljava/lang/String;Ljava/net/URLConnection;Ljava/lang/Throwable;)Lorg/json/JSONObject;
          //   6291: invokespecial 195	org/apache/cordova/PluginResult:<init>	(Lorg/apache/cordova/PluginResult$Status;Lorg/json/JSONObject;)V
          //   6294: astore 12
          //   6296: iload_1
          //   6297: ifne +28 -> 6325
          //   6300: aload 12
          //   6302: invokevirtual 199	org/apache/cordova/PluginResult:getStatus	()I
          //   6305: getstatic 202	org/apache/cordova/PluginResult$Status:OK	Lorg/apache/cordova/PluginResult$Status;
          //   6308: invokevirtual 205	org/apache/cordova/PluginResult$Status:ordinal	()I
          //   6311: if_icmpeq +14 -> 6325
          //   6314: aload 23
          //   6316: ifnull +9 -> 6325
          //   6319: aload 23
          //   6321: invokevirtual 211	java/io/File:delete	()Z
          //   6324: pop
          //   6325: aload_0
          //   6326: getfield 36	org/apache/cordova/filetransfer/FileTransfer$4:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
          //   6329: astore 13
          //   6331: goto -4783 -> 1548
          //   6334: astore 12
          //   6336: aconst_null
          //   6337: astore 13
          //   6339: aload 19
          //   6341: astore 14
          //   6343: aload 18
          //   6345: astore 15
          //   6347: aload 30
          //   6349: astore 24
          //   6351: aload 27
          //   6353: astore 16
          //   6355: iload_2
          //   6356: istore_1
          //   6357: iload_1
          //   6358: istore_2
          //   6359: aload 16
          //   6361: astore 20
          //   6363: aload 24
          //   6365: astore 17
          //   6367: aload 15
          //   6369: astore 19
          //   6371: aload 14
          //   6373: astore 18
          //   6375: aload 13
          //   6377: astore 21
          //   6379: ldc 94
          //   6381: aload 12
          //   6383: invokevirtual 399	org/json/JSONException:getMessage	()Ljava/lang/String;
          //   6386: aload 12
          //   6388: invokestatic 335	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
          //   6391: pop
          //   6392: iload_1
          //   6393: istore_2
          //   6394: aload 16
          //   6396: astore 20
          //   6398: aload 24
          //   6400: astore 17
          //   6402: aload 15
          //   6404: astore 19
          //   6406: aload 14
          //   6408: astore 18
          //   6410: aload 13
          //   6412: astore 21
          //   6414: new 178	org/apache/cordova/PluginResult
          //   6417: dup
          //   6418: getstatic 402	org/apache/cordova/PluginResult$Status:JSON_EXCEPTION	Lorg/apache/cordova/PluginResult$Status;
          //   6421: invokespecial 405	org/apache/cordova/PluginResult:<init>	(Lorg/apache/cordova/PluginResult$Status;)V
          //   6424: astore 13
          //   6426: invokestatic 160	org/apache/cordova/filetransfer/FileTransfer:access$600	()Ljava/util/HashMap;
          //   6429: astore 12
          //   6431: aload 12
          //   6433: monitorenter
          //   6434: invokestatic 160	org/apache/cordova/filetransfer/FileTransfer:access$600	()Ljava/util/HashMap;
          //   6437: aload_0
          //   6438: getfield 56	org/apache/cordova/filetransfer/FileTransfer$4:val$objectId	Ljava/lang/String;
          //   6441: invokevirtual 166	java/util/HashMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
          //   6444: pop
          //   6445: aload 12
          //   6447: monitorexit
          //   6448: aload 16
          //   6450: ifnull +38 -> 6488
          //   6453: aload_0
          //   6454: getfield 48	org/apache/cordova/filetransfer/FileTransfer$4:val$trustEveryone	Z
          //   6457: ifeq +31 -> 6488
          //   6460: aload_0
          //   6461: getfield 46	org/apache/cordova/filetransfer/FileTransfer$4:val$useHttps	Z
          //   6464: ifeq +24 -> 6488
          //   6467: aload 16
          //   6469: checkcast 168	javax/net/ssl/HttpsURLConnection
          //   6472: astore 12
          //   6474: aload 12
          //   6476: aload 15
          //   6478: invokevirtual 172	javax/net/ssl/HttpsURLConnection:setHostnameVerifier	(Ljavax/net/ssl/HostnameVerifier;)V
          //   6481: aload 12
          //   6483: aload 14
          //   6485: invokevirtual 176	javax/net/ssl/HttpsURLConnection:setSSLSocketFactory	(Ljavax/net/ssl/SSLSocketFactory;)V
          //   6488: aload 13
          //   6490: astore 12
          //   6492: aload 13
          //   6494: ifnonnull +32 -> 6526
          //   6497: new 178	org/apache/cordova/PluginResult
          //   6500: dup
          //   6501: getstatic 184	org/apache/cordova/PluginResult$Status:ERROR	Lorg/apache/cordova/PluginResult$Status;
          //   6504: getstatic 188	org/apache/cordova/filetransfer/FileTransfer:CONNECTION_ERR	I
          //   6507: aload_0
          //   6508: getfield 52	org/apache/cordova/filetransfer/FileTransfer$4:val$source	Ljava/lang/String;
          //   6511: aload_0
          //   6512: getfield 54	org/apache/cordova/filetransfer/FileTransfer$4:val$target	Ljava/lang/String;
          //   6515: aload 16
          //   6517: aconst_null
          //   6518: invokestatic 192	org/apache/cordova/filetransfer/FileTransfer:access$500	(ILjava/lang/String;Ljava/lang/String;Ljava/net/URLConnection;Ljava/lang/Throwable;)Lorg/json/JSONObject;
          //   6521: invokespecial 195	org/apache/cordova/PluginResult:<init>	(Lorg/apache/cordova/PluginResult$Status;Lorg/json/JSONObject;)V
          //   6524: astore 12
          //   6526: iload_1
          //   6527: ifne +28 -> 6555
          //   6530: aload 12
          //   6532: invokevirtual 199	org/apache/cordova/PluginResult:getStatus	()I
          //   6535: getstatic 202	org/apache/cordova/PluginResult$Status:OK	Lorg/apache/cordova/PluginResult$Status;
          //   6538: invokevirtual 205	org/apache/cordova/PluginResult$Status:ordinal	()I
          //   6541: if_icmpeq +14 -> 6555
          //   6544: aload 24
          //   6546: ifnull +9 -> 6555
          //   6549: aload 24
          //   6551: invokevirtual 211	java/io/File:delete	()Z
          //   6554: pop
          //   6555: aload_0
          //   6556: getfield 36	org/apache/cordova/filetransfer/FileTransfer$4:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
          //   6559: astore 13
          //   6561: goto -5013 -> 1548
          //   6564: astore 12
          //   6566: aconst_null
          //   6567: astore 13
          //   6569: aload 22
          //   6571: astore 14
          //   6573: aload 20
          //   6575: astore 15
          //   6577: aload 32
          //   6579: astore 25
          //   6581: aload 28
          //   6583: astore 16
          //   6585: iload_3
          //   6586: istore_1
          //   6587: iload_1
          //   6588: istore_2
          //   6589: aload 16
          //   6591: astore 20
          //   6593: aload 25
          //   6595: astore 17
          //   6597: aload 15
          //   6599: astore 19
          //   6601: aload 14
          //   6603: astore 18
          //   6605: aload 13
          //   6607: astore 21
          //   6609: getstatic 188	org/apache/cordova/filetransfer/FileTransfer:CONNECTION_ERR	I
          //   6612: aload_0
          //   6613: getfield 52	org/apache/cordova/filetransfer/FileTransfer$4:val$source	Ljava/lang/String;
          //   6616: aload_0
          //   6617: getfield 54	org/apache/cordova/filetransfer/FileTransfer$4:val$target	Ljava/lang/String;
          //   6620: aload 16
          //   6622: aload 12
          //   6624: invokestatic 192	org/apache/cordova/filetransfer/FileTransfer:access$500	(ILjava/lang/String;Ljava/lang/String;Ljava/net/URLConnection;Ljava/lang/Throwable;)Lorg/json/JSONObject;
          //   6627: astore 22
          //   6629: iload_1
          //   6630: istore_2
          //   6631: aload 16
          //   6633: astore 20
          //   6635: aload 25
          //   6637: astore 17
          //   6639: aload 15
          //   6641: astore 19
          //   6643: aload 14
          //   6645: astore 18
          //   6647: aload 13
          //   6649: astore 21
          //   6651: ldc 94
          //   6653: aload 22
          //   6655: invokevirtual 331	org/json/JSONObject:toString	()Ljava/lang/String;
          //   6658: aload 12
          //   6660: invokestatic 335	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
          //   6663: pop
          //   6664: iload_1
          //   6665: istore_2
          //   6666: aload 16
          //   6668: astore 20
          //   6670: aload 25
          //   6672: astore 17
          //   6674: aload 15
          //   6676: astore 19
          //   6678: aload 14
          //   6680: astore 18
          //   6682: aload 13
          //   6684: astore 21
          //   6686: new 178	org/apache/cordova/PluginResult
          //   6689: dup
          //   6690: getstatic 338	org/apache/cordova/PluginResult$Status:IO_EXCEPTION	Lorg/apache/cordova/PluginResult$Status;
          //   6693: aload 22
          //   6695: invokespecial 195	org/apache/cordova/PluginResult:<init>	(Lorg/apache/cordova/PluginResult$Status;Lorg/json/JSONObject;)V
          //   6698: astore 13
          //   6700: invokestatic 160	org/apache/cordova/filetransfer/FileTransfer:access$600	()Ljava/util/HashMap;
          //   6703: astore 12
          //   6705: aload 12
          //   6707: monitorenter
          //   6708: invokestatic 160	org/apache/cordova/filetransfer/FileTransfer:access$600	()Ljava/util/HashMap;
          //   6711: aload_0
          //   6712: getfield 56	org/apache/cordova/filetransfer/FileTransfer$4:val$objectId	Ljava/lang/String;
          //   6715: invokevirtual 166	java/util/HashMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
          //   6718: pop
          //   6719: aload 12
          //   6721: monitorexit
          //   6722: aload 16
          //   6724: ifnull +38 -> 6762
          //   6727: aload_0
          //   6728: getfield 48	org/apache/cordova/filetransfer/FileTransfer$4:val$trustEveryone	Z
          //   6731: ifeq +31 -> 6762
          //   6734: aload_0
          //   6735: getfield 46	org/apache/cordova/filetransfer/FileTransfer$4:val$useHttps	Z
          //   6738: ifeq +24 -> 6762
          //   6741: aload 16
          //   6743: checkcast 168	javax/net/ssl/HttpsURLConnection
          //   6746: astore 12
          //   6748: aload 12
          //   6750: aload 15
          //   6752: invokevirtual 172	javax/net/ssl/HttpsURLConnection:setHostnameVerifier	(Ljavax/net/ssl/HostnameVerifier;)V
          //   6755: aload 12
          //   6757: aload 14
          //   6759: invokevirtual 176	javax/net/ssl/HttpsURLConnection:setSSLSocketFactory	(Ljavax/net/ssl/SSLSocketFactory;)V
          //   6762: aload 13
          //   6764: astore 12
          //   6766: aload 13
          //   6768: ifnonnull +32 -> 6800
          //   6771: new 178	org/apache/cordova/PluginResult
          //   6774: dup
          //   6775: getstatic 184	org/apache/cordova/PluginResult$Status:ERROR	Lorg/apache/cordova/PluginResult$Status;
          //   6778: getstatic 188	org/apache/cordova/filetransfer/FileTransfer:CONNECTION_ERR	I
          //   6781: aload_0
          //   6782: getfield 52	org/apache/cordova/filetransfer/FileTransfer$4:val$source	Ljava/lang/String;
          //   6785: aload_0
          //   6786: getfield 54	org/apache/cordova/filetransfer/FileTransfer$4:val$target	Ljava/lang/String;
          //   6789: aload 16
          //   6791: aconst_null
          //   6792: invokestatic 192	org/apache/cordova/filetransfer/FileTransfer:access$500	(ILjava/lang/String;Ljava/lang/String;Ljava/net/URLConnection;Ljava/lang/Throwable;)Lorg/json/JSONObject;
          //   6795: invokespecial 195	org/apache/cordova/PluginResult:<init>	(Lorg/apache/cordova/PluginResult$Status;Lorg/json/JSONObject;)V
          //   6798: astore 12
          //   6800: iload_1
          //   6801: ifne +28 -> 6829
          //   6804: aload 12
          //   6806: invokevirtual 199	org/apache/cordova/PluginResult:getStatus	()I
          //   6809: getstatic 202	org/apache/cordova/PluginResult$Status:OK	Lorg/apache/cordova/PluginResult$Status;
          //   6812: invokevirtual 205	org/apache/cordova/PluginResult$Status:ordinal	()I
          //   6815: if_icmpeq +14 -> 6829
          //   6818: aload 25
          //   6820: ifnull +9 -> 6829
          //   6823: aload 25
          //   6825: invokevirtual 211	java/io/File:delete	()Z
          //   6828: pop
          //   6829: aload_0
          //   6830: getfield 36	org/apache/cordova/filetransfer/FileTransfer$4:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
          //   6833: astore 13
          //   6835: goto -5287 -> 1548
          //   6838: astore 13
          //   6840: aload 37
          //   6842: astore 12
          //   6844: aload 25
          //   6846: astore 18
          //   6848: aload 23
          //   6850: astore 19
          //   6852: aload 33
          //   6854: astore 17
          //   6856: aload 31
          //   6858: astore 20
          //   6860: iload 4
          //   6862: istore_2
          //   6863: invokestatic 160	org/apache/cordova/filetransfer/FileTransfer:access$600	()Ljava/util/HashMap;
          //   6866: astore 14
          //   6868: aload 14
          //   6870: monitorenter
          //   6871: invokestatic 160	org/apache/cordova/filetransfer/FileTransfer:access$600	()Ljava/util/HashMap;
          //   6874: aload_0
          //   6875: getfield 56	org/apache/cordova/filetransfer/FileTransfer$4:val$objectId	Ljava/lang/String;
          //   6878: invokevirtual 166	java/util/HashMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
          //   6881: pop
          //   6882: aload 14
          //   6884: monitorexit
          //   6885: aload 20
          //   6887: ifnull +38 -> 6925
          //   6890: aload_0
          //   6891: getfield 48	org/apache/cordova/filetransfer/FileTransfer$4:val$trustEveryone	Z
          //   6894: ifeq +31 -> 6925
          //   6897: aload_0
          //   6898: getfield 46	org/apache/cordova/filetransfer/FileTransfer$4:val$useHttps	Z
          //   6901: ifeq +24 -> 6925
          //   6904: aload 20
          //   6906: checkcast 168	javax/net/ssl/HttpsURLConnection
          //   6909: astore 14
          //   6911: aload 14
          //   6913: aload 19
          //   6915: invokevirtual 172	javax/net/ssl/HttpsURLConnection:setHostnameVerifier	(Ljavax/net/ssl/HostnameVerifier;)V
          //   6918: aload 14
          //   6920: aload 18
          //   6922: invokevirtual 176	javax/net/ssl/HttpsURLConnection:setSSLSocketFactory	(Ljavax/net/ssl/SSLSocketFactory;)V
          //   6925: aload 12
          //   6927: astore 14
          //   6929: aload 12
          //   6931: ifnonnull +32 -> 6963
          //   6934: new 178	org/apache/cordova/PluginResult
          //   6937: dup
          //   6938: getstatic 184	org/apache/cordova/PluginResult$Status:ERROR	Lorg/apache/cordova/PluginResult$Status;
          //   6941: getstatic 188	org/apache/cordova/filetransfer/FileTransfer:CONNECTION_ERR	I
          //   6944: aload_0
          //   6945: getfield 52	org/apache/cordova/filetransfer/FileTransfer$4:val$source	Ljava/lang/String;
          //   6948: aload_0
          //   6949: getfield 54	org/apache/cordova/filetransfer/FileTransfer$4:val$target	Ljava/lang/String;
          //   6952: aload 20
          //   6954: aconst_null
          //   6955: invokestatic 192	org/apache/cordova/filetransfer/FileTransfer:access$500	(ILjava/lang/String;Ljava/lang/String;Ljava/net/URLConnection;Ljava/lang/Throwable;)Lorg/json/JSONObject;
          //   6958: invokespecial 195	org/apache/cordova/PluginResult:<init>	(Lorg/apache/cordova/PluginResult$Status;Lorg/json/JSONObject;)V
          //   6961: astore 14
          //   6963: iload_2
          //   6964: ifne +28 -> 6992
          //   6967: aload 14
          //   6969: invokevirtual 199	org/apache/cordova/PluginResult:getStatus	()I
          //   6972: getstatic 202	org/apache/cordova/PluginResult$Status:OK	Lorg/apache/cordova/PluginResult$Status;
          //   6975: invokevirtual 205	org/apache/cordova/PluginResult$Status:ordinal	()I
          //   6978: if_icmpeq +14 -> 6992
          //   6981: aload 17
          //   6983: ifnull +9 -> 6992
          //   6986: aload 17
          //   6988: invokevirtual 211	java/io/File:delete	()Z
          //   6991: pop
          //   6992: aload_0
          //   6993: getfield 36	org/apache/cordova/filetransfer/FileTransfer$4:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
          //   6996: aload 14
          //   6998: invokevirtual 215	org/apache/cordova/filetransfer/FileTransfer$RequestContext:sendPluginResult	(Lorg/apache/cordova/PluginResult;)V
          //   7001: aload 13
          //   7003: athrow
          //   7004: astore 12
          //   7006: aload 14
          //   7008: monitorexit
          //   7009: aload 12
          //   7011: athrow
          //   7012: astore 13
          //   7014: aload 12
          //   7016: monitorexit
          //   7017: aload 13
          //   7019: athrow
          //   7020: astore 13
          //   7022: aload 12
          //   7024: monitorexit
          //   7025: aload 13
          //   7027: athrow
          //   7028: astore 13
          //   7030: aload 12
          //   7032: monitorexit
          //   7033: aload 13
          //   7035: athrow
          //   7036: astore 13
          //   7038: aload 12
          //   7040: monitorexit
          //   7041: aload 13
          //   7043: athrow
          //   7044: astore 26
          //   7046: aload 28
          //   7048: monitorexit
          //   7049: aload 12
          //   7051: astore 22
          //   7053: aload 12
          //   7055: astore 23
          //   7057: aload 12
          //   7059: astore 24
          //   7061: aload 12
          //   7063: astore 25
          //   7065: iload_1
          //   7066: istore_2
          //   7067: aload 16
          //   7069: astore 20
          //   7071: aload 12
          //   7073: astore 17
          //   7075: aload 15
          //   7077: astore 19
          //   7079: aload 14
          //   7081: astore 18
          //   7083: aload 13
          //   7085: astore 21
          //   7087: aload 26
          //   7089: athrow
          //   7090: astore 12
          //   7092: goto -1009 -> 6083
          //   7095: astore 26
          //   7097: aload 27
          //   7099: monitorexit
          //   7100: aload 12
          //   7102: astore 22
          //   7104: aload 12
          //   7106: astore 23
          //   7108: aload 12
          //   7110: astore 24
          //   7112: aload 12
          //   7114: astore 25
          //   7116: iload_1
          //   7117: istore_2
          //   7118: aload 16
          //   7120: astore 20
          //   7122: aload 12
          //   7124: astore 17
          //   7126: aload 15
          //   7128: astore 19
          //   7130: aload 14
          //   7132: astore 18
          //   7134: aload 13
          //   7136: astore 21
          //   7138: aload 26
          //   7140: athrow
          //   7141: astore 12
          //   7143: goto -786 -> 6357
          //   7146: astore 12
          //   7148: aload 17
          //   7150: monitorexit
          //   7151: aload 12
          //   7153: athrow
          //   7154: astore 26
          //   7156: aload 27
          //   7158: monitorexit
          //   7159: aload 12
          //   7161: astore 22
          //   7163: aload 12
          //   7165: astore 23
          //   7167: aload 12
          //   7169: astore 24
          //   7171: aload 12
          //   7173: astore 25
          //   7175: iload_1
          //   7176: istore_2
          //   7177: aload 16
          //   7179: astore 20
          //   7181: aload 12
          //   7183: astore 17
          //   7185: aload 15
          //   7187: astore 19
          //   7189: aload 14
          //   7191: astore 18
          //   7193: aload 13
          //   7195: astore 21
          //   7197: aload 26
          //   7199: athrow
          //   7200: astore 12
          //   7202: goto -615 -> 6587
          //   7205: astore 13
          //   7207: aload 12
          //   7209: monitorexit
          //   7210: aload 13
          //   7212: athrow
          //   7213: astore 13
          //   7215: aload 21
          //   7217: astore 12
          //   7219: goto -356 -> 6863
          //   7222: astore 12
          //   7224: aconst_null
          //   7225: astore 13
          //   7227: iload 5
          //   7229: istore_1
          //   7230: aload 35
          //   7232: astore 16
          //   7234: aload 34
          //   7236: astore 22
          //   7238: aload 24
          //   7240: astore 15
          //   7242: aload 21
          //   7244: astore 14
          //   7246: goto -2570 -> 4676
          //   7249: astore 17
          //   7251: aload 26
          //   7253: astore 27
          //   7255: goto -1871 -> 5384
          //   7258: astore 17
          //   7260: aload 26
          //   7262: astore 27
          //   7264: goto -1880 -> 5384
          //   7267: astore 17
          //   7269: goto -1955 -> 5314
          //   7272: astore 17
          //   7274: goto -1960 -> 5314
          //   7277: astore 17
          //   7279: goto -1965 -> 5314
          //   7282: aload 13
          //   7284: astore 14
          //   7286: goto -5777 -> 1509
          //   7289: aload 12
          //   7291: astore 17
          //   7293: goto -1643 -> 5650
          // Local variable table:
          //   start	length	slot	name	signature
          //   0	7296	0	this	4
          //   115	7115	1	i	int
          //   130	7047	2	j	int
          //   145	6441	3	k	int
          //   160	6701	4	m	int
          //   176	7052	5	n	int
          //   108	4194	6	i1	int
          //   93	4098	7	i2	int
          //   96	4114	8	i3	int
          //   99	4130	9	i4	int
          //   102	4146	10	i5	int
          //   105	4163	11	i6	int
          //   201	4455	12	localObject1	Object
          //   4674	74	12	localFileNotFoundException1	java.io.FileNotFoundException
          //   6066	89	12	localIOException1	IOException
          //   6334	53	12	localJSONException1	JSONException
          //   6564	95	12	localThrowable1	Throwable
          //   7004	68	12	localObject6	Object
          //   7090	33	12	localIOException2	IOException
          //   7141	1	12	localJSONException2	JSONException
          //   7146	36	12	localObject7	Object
          //   7200	8	12	localThrowable2	Throwable
          //   7217	1	12	localObject8	Object
          //   7222	68	12	localFileNotFoundException2	java.io.FileNotFoundException
          //   12	6822	13	localObject9	Object
          //   6838	164	13	localObject10	Object
          //   7012	6	13	localObject11	Object
          //   7020	6	13	localObject12	Object
          //   7028	6	13	localObject13	Object
          //   7036	158	13	localObject14	Object
          //   7205	6	13	localObject15	Object
          //   7213	1	13	localObject16	Object
          //   7225	58	13	localObject17	Object
          //   122	7119	15	localObject19	Object
          //   27	7206	16	localObject20	Object
          //   126	7058	17	localObject21	Object
          //   7249	1	17	localIllegalAccessException1	IllegalAccessException
          //   7258	1	17	localNoSuchFieldException	NoSuchFieldException
          //   7267	1	17	localInvocationTargetException	java.lang.reflect.InvocationTargetException
          //   7272	1	17	localIllegalAccessException2	IllegalAccessException
          //   7277	1	17	localNoSuchMethodException	NoSuchMethodException
          //   7291	1	17	localObject22	Object
          //   137	4633	18	localObject23	Object
          //   4927	10	18	localObject24	Object
          //   4971	2221	18	localObject25	Object
          //   141	7047	19	localObject26	Object
          //   152	7028	20	localObject27	Object
          //   188	7055	21	localObject28	Object
          //   156	7081	22	localObject29	Object
          //   168	6998	23	localObject30	Object
          //   184	7055	24	localObject31	Object
          //   172	7002	25	localObject32	Object
          //   118	5962	26	localObject33	Object
          //   7044	44	26	localObject34	Object
          //   7095	44	26	localObject35	Object
          //   7154	107	26	localObject36	Object
          //   133	7130	27	localObject37	Object
          //   148	6899	28	localObject38	Object
          //   84	4116	29	localObject39	Object
          //   4449	1627	29	localObject40	Object
          //   72	6276	30	localObject41	Object
          //   164	6693	31	localObject42	Object
          //   75	6503	32	localObject43	Object
          //   78	6775	33	localObject44	Object
          //   81	7154	34	localObject45	Object
          //   180	7051	35	localObject46	Object
          //   111	4822	36	localObject47	Object
          //   87	6754	37	localObject48	Object
          //   90	4205	38	localObject49	Object
          //   48	3603	39	localObject50	Object
          //   33	2077	40	localObject51	Object
          //   36	2093	41	localObject52	Object
          //   39	2109	42	localObject53	Object
          //   42	2126	43	localObject54	Object
          //   45	2143	44	localObject55	Object
          //   54	1956	45	localObject56	Object
          //   57	1972	46	localObject57	Object
          //   60	1988	47	localObject58	Object
          //   63	2005	48	localObject59	Object
          //   66	2022	49	localObject60	Object
          //   30	1749	50	localObject61	Object
          //   51	1732	51	localObject62	Object
          //   15	1564	52	localObject63	Object
          //   18	1580	53	localObject64	Object
          //   21	1597	54	localObject65	Object
          //   24	1614	55	localObject66	Object
          //   538	3876	56	localFileProgressResult	FileProgressResult
          // Exception table:
          //   from	to	target	type
          //   1206	1212	4449	finally
          //   1216	1219	4449	finally
          //   4335	4342	4449	finally
          //   4346	4359	4449	finally
          //   4363	4371	4449	finally
          //   4379	4388	4449	finally
          //   4392	4402	4449	finally
          //   4406	4423	4449	finally
          //   4427	4433	4449	finally
          //   4437	4446	4449	finally
          //   4936	4939	4449	finally
          //   1270	1276	4674	java/io/FileNotFoundException
          //   1314	1317	4674	java/io/FileNotFoundException
          //   1366	1371	4674	java/io/FileNotFoundException
          //   1409	1413	4674	java/io/FileNotFoundException
          //   4489	4495	4674	java/io/FileNotFoundException
          //   4533	4536	4674	java/io/FileNotFoundException
          //   4585	4590	4674	java/io/FileNotFoundException
          //   4628	4633	4674	java/io/FileNotFoundException
          //   4671	4674	4674	java/io/FileNotFoundException
          //   4977	4983	4674	java/io/FileNotFoundException
          //   5021	5024	4674	java/io/FileNotFoundException
          //   5073	5078	4674	java/io/FileNotFoundException
          //   5116	5121	4674	java/io/FileNotFoundException
          //   5159	5188	4674	java/io/FileNotFoundException
          //   5226	5238	4674	java/io/FileNotFoundException
          //   5279	5310	4674	java/io/FileNotFoundException
          //   5361	5384	4674	java/io/FileNotFoundException
          //   5422	5435	4674	java/io/FileNotFoundException
          //   5473	5482	4674	java/io/FileNotFoundException
          //   5520	5533	4674	java/io/FileNotFoundException
          //   5576	5585	4674	java/io/FileNotFoundException
          //   5628	5642	4674	java/io/FileNotFoundException
          //   5826	5845	4674	java/io/FileNotFoundException
          //   5883	5892	4674	java/io/FileNotFoundException
          //   5930	5944	4674	java/io/FileNotFoundException
          //   5993	6002	4674	java/io/FileNotFoundException
          //   6040	6055	4674	java/io/FileNotFoundException
          //   7087	7090	4674	java/io/FileNotFoundException
          //   7138	7141	4674	java/io/FileNotFoundException
          //   7197	7200	4674	java/io/FileNotFoundException
          //   1219	1232	4927	finally
          //   4319	4331	4927	finally
          //   4929	4932	4927	finally
          //   190	203	6066	java/io/IOException
          //   300	309	6066	java/io/IOException
          //   406	434	6066	java/io/IOException
          //   531	540	6066	java/io/IOException
          //   637	644	6066	java/io/IOException
          //   741	754	6066	java/io/IOException
          //   851	863	6066	java/io/IOException
          //   960	966	6066	java/io/IOException
          //   1063	1073	6066	java/io/IOException
          //   1170	1184	6066	java/io/IOException
          //   1653	1666	6066	java/io/IOException
          //   1771	1778	6066	java/io/IOException
          //   1883	1890	6066	java/io/IOException
          //   1987	1994	6066	java/io/IOException
          //   2091	2098	6066	java/io/IOException
          //   2195	2202	6066	java/io/IOException
          //   2299	2307	6066	java/io/IOException
          //   2404	2411	6066	java/io/IOException
          //   2508	2523	6066	java/io/IOException
          //   2625	2634	6066	java/io/IOException
          //   2731	2742	6066	java/io/IOException
          //   2839	2846	6066	java/io/IOException
          //   2943	2952	6066	java/io/IOException
          //   3049	3054	6066	java/io/IOException
          //   3151	3162	6066	java/io/IOException
          //   3277	3282	6066	java/io/IOException
          //   3379	3408	6066	java/io/IOException
          //   3505	3524	6066	java/io/IOException
          //   3621	3635	6066	java/io/IOException
          //   3754	3762	6066	java/io/IOException
          //   3859	3873	6066	java/io/IOException
          //   3970	3979	6066	java/io/IOException
          //   4076	4082	6066	java/io/IOException
          //   4179	4190	6066	java/io/IOException
          //   4287	4294	6066	java/io/IOException
          //   190	203	6334	org/json/JSONException
          //   300	309	6334	org/json/JSONException
          //   406	434	6334	org/json/JSONException
          //   531	540	6334	org/json/JSONException
          //   637	644	6334	org/json/JSONException
          //   741	754	6334	org/json/JSONException
          //   851	863	6334	org/json/JSONException
          //   960	966	6334	org/json/JSONException
          //   1063	1073	6334	org/json/JSONException
          //   1170	1184	6334	org/json/JSONException
          //   1653	1666	6334	org/json/JSONException
          //   1771	1778	6334	org/json/JSONException
          //   1883	1890	6334	org/json/JSONException
          //   1987	1994	6334	org/json/JSONException
          //   2091	2098	6334	org/json/JSONException
          //   2195	2202	6334	org/json/JSONException
          //   2299	2307	6334	org/json/JSONException
          //   2404	2411	6334	org/json/JSONException
          //   2508	2523	6334	org/json/JSONException
          //   2625	2634	6334	org/json/JSONException
          //   2731	2742	6334	org/json/JSONException
          //   2839	2846	6334	org/json/JSONException
          //   2943	2952	6334	org/json/JSONException
          //   3049	3054	6334	org/json/JSONException
          //   3151	3162	6334	org/json/JSONException
          //   3277	3282	6334	org/json/JSONException
          //   3379	3408	6334	org/json/JSONException
          //   3505	3524	6334	org/json/JSONException
          //   3621	3635	6334	org/json/JSONException
          //   3754	3762	6334	org/json/JSONException
          //   3859	3873	6334	org/json/JSONException
          //   3970	3979	6334	org/json/JSONException
          //   4076	4082	6334	org/json/JSONException
          //   4179	4190	6334	org/json/JSONException
          //   4287	4294	6334	org/json/JSONException
          //   190	203	6564	java/lang/Throwable
          //   300	309	6564	java/lang/Throwable
          //   406	434	6564	java/lang/Throwable
          //   531	540	6564	java/lang/Throwable
          //   637	644	6564	java/lang/Throwable
          //   741	754	6564	java/lang/Throwable
          //   851	863	6564	java/lang/Throwable
          //   960	966	6564	java/lang/Throwable
          //   1063	1073	6564	java/lang/Throwable
          //   1170	1184	6564	java/lang/Throwable
          //   1653	1666	6564	java/lang/Throwable
          //   1771	1778	6564	java/lang/Throwable
          //   1883	1890	6564	java/lang/Throwable
          //   1987	1994	6564	java/lang/Throwable
          //   2091	2098	6564	java/lang/Throwable
          //   2195	2202	6564	java/lang/Throwable
          //   2299	2307	6564	java/lang/Throwable
          //   2404	2411	6564	java/lang/Throwable
          //   2508	2523	6564	java/lang/Throwable
          //   2625	2634	6564	java/lang/Throwable
          //   2731	2742	6564	java/lang/Throwable
          //   2839	2846	6564	java/lang/Throwable
          //   2943	2952	6564	java/lang/Throwable
          //   3049	3054	6564	java/lang/Throwable
          //   3151	3162	6564	java/lang/Throwable
          //   3277	3282	6564	java/lang/Throwable
          //   3379	3408	6564	java/lang/Throwable
          //   3505	3524	6564	java/lang/Throwable
          //   3621	3635	6564	java/lang/Throwable
          //   3754	3762	6564	java/lang/Throwable
          //   3859	3873	6564	java/lang/Throwable
          //   3970	3979	6564	java/lang/Throwable
          //   4076	4082	6564	java/lang/Throwable
          //   4179	4190	6564	java/lang/Throwable
          //   4287	4294	6564	java/lang/Throwable
          //   190	203	6838	finally
          //   300	309	6838	finally
          //   406	434	6838	finally
          //   531	540	6838	finally
          //   637	644	6838	finally
          //   741	754	6838	finally
          //   851	863	6838	finally
          //   960	966	6838	finally
          //   1063	1073	6838	finally
          //   1170	1184	6838	finally
          //   1653	1666	6838	finally
          //   1771	1778	6838	finally
          //   1883	1890	6838	finally
          //   1987	1994	6838	finally
          //   2091	2098	6838	finally
          //   2195	2202	6838	finally
          //   2299	2307	6838	finally
          //   2404	2411	6838	finally
          //   2508	2523	6838	finally
          //   2625	2634	6838	finally
          //   2731	2742	6838	finally
          //   2839	2846	6838	finally
          //   2943	2952	6838	finally
          //   3049	3054	6838	finally
          //   3151	3162	6838	finally
          //   3277	3282	6838	finally
          //   3379	3408	6838	finally
          //   3505	3524	6838	finally
          //   3621	3635	6838	finally
          //   3754	3762	6838	finally
          //   3859	3873	6838	finally
          //   3970	3979	6838	finally
          //   4076	4082	6838	finally
          //   4179	4190	6838	finally
          //   4287	4294	6838	finally
          //   6871	6885	7004	finally
          //   7006	7009	7004	finally
          //   4797	4811	7012	finally
          //   7014	7017	7012	finally
          //   6204	6218	7020	finally
          //   7022	7025	7020	finally
          //   6434	6448	7028	finally
          //   7030	7033	7028	finally
          //   6708	6722	7036	finally
          //   7038	7041	7036	finally
          //   4536	4547	7044	finally
          //   7046	7049	7044	finally
          //   1270	1276	7090	java/io/IOException
          //   1314	1317	7090	java/io/IOException
          //   1366	1371	7090	java/io/IOException
          //   1409	1413	7090	java/io/IOException
          //   4489	4495	7090	java/io/IOException
          //   4533	4536	7090	java/io/IOException
          //   4585	4590	7090	java/io/IOException
          //   4628	4633	7090	java/io/IOException
          //   4671	4674	7090	java/io/IOException
          //   4977	4983	7090	java/io/IOException
          //   5021	5024	7090	java/io/IOException
          //   5073	5078	7090	java/io/IOException
          //   5116	5121	7090	java/io/IOException
          //   5159	5188	7090	java/io/IOException
          //   5226	5238	7090	java/io/IOException
          //   5279	5310	7090	java/io/IOException
          //   5361	5384	7090	java/io/IOException
          //   5422	5435	7090	java/io/IOException
          //   5473	5482	7090	java/io/IOException
          //   5520	5533	7090	java/io/IOException
          //   5576	5585	7090	java/io/IOException
          //   5628	5642	7090	java/io/IOException
          //   5826	5845	7090	java/io/IOException
          //   5883	5892	7090	java/io/IOException
          //   5930	5944	7090	java/io/IOException
          //   5993	6002	7090	java/io/IOException
          //   6040	6055	7090	java/io/IOException
          //   7087	7090	7090	java/io/IOException
          //   7138	7141	7090	java/io/IOException
          //   7197	7200	7090	java/io/IOException
          //   1317	1328	7095	finally
          //   7097	7100	7095	finally
          //   1270	1276	7141	org/json/JSONException
          //   1314	1317	7141	org/json/JSONException
          //   1366	1371	7141	org/json/JSONException
          //   1409	1413	7141	org/json/JSONException
          //   4489	4495	7141	org/json/JSONException
          //   4533	4536	7141	org/json/JSONException
          //   4585	4590	7141	org/json/JSONException
          //   4628	4633	7141	org/json/JSONException
          //   4671	4674	7141	org/json/JSONException
          //   4977	4983	7141	org/json/JSONException
          //   5021	5024	7141	org/json/JSONException
          //   5073	5078	7141	org/json/JSONException
          //   5116	5121	7141	org/json/JSONException
          //   5159	5188	7141	org/json/JSONException
          //   5226	5238	7141	org/json/JSONException
          //   5279	5310	7141	org/json/JSONException
          //   5361	5384	7141	org/json/JSONException
          //   5422	5435	7141	org/json/JSONException
          //   5473	5482	7141	org/json/JSONException
          //   5520	5533	7141	org/json/JSONException
          //   5576	5585	7141	org/json/JSONException
          //   5628	5642	7141	org/json/JSONException
          //   5826	5845	7141	org/json/JSONException
          //   5883	5892	7141	org/json/JSONException
          //   5930	5944	7141	org/json/JSONException
          //   5993	6002	7141	org/json/JSONException
          //   6040	6055	7141	org/json/JSONException
          //   7087	7090	7141	org/json/JSONException
          //   7138	7141	7141	org/json/JSONException
          //   7197	7200	7141	org/json/JSONException
          //   1421	1435	7146	finally
          //   7148	7151	7146	finally
          //   5024	5035	7154	finally
          //   7156	7159	7154	finally
          //   1270	1276	7200	java/lang/Throwable
          //   1314	1317	7200	java/lang/Throwable
          //   1366	1371	7200	java/lang/Throwable
          //   1409	1413	7200	java/lang/Throwable
          //   4489	4495	7200	java/lang/Throwable
          //   4533	4536	7200	java/lang/Throwable
          //   4585	4590	7200	java/lang/Throwable
          //   4628	4633	7200	java/lang/Throwable
          //   4671	4674	7200	java/lang/Throwable
          //   4977	4983	7200	java/lang/Throwable
          //   5021	5024	7200	java/lang/Throwable
          //   5073	5078	7200	java/lang/Throwable
          //   5116	5121	7200	java/lang/Throwable
          //   5159	5188	7200	java/lang/Throwable
          //   5226	5238	7200	java/lang/Throwable
          //   5279	5310	7200	java/lang/Throwable
          //   5361	5384	7200	java/lang/Throwable
          //   5422	5435	7200	java/lang/Throwable
          //   5473	5482	7200	java/lang/Throwable
          //   5520	5533	7200	java/lang/Throwable
          //   5576	5585	7200	java/lang/Throwable
          //   5628	5642	7200	java/lang/Throwable
          //   5826	5845	7200	java/lang/Throwable
          //   5883	5892	7200	java/lang/Throwable
          //   5930	5944	7200	java/lang/Throwable
          //   5993	6002	7200	java/lang/Throwable
          //   6040	6055	7200	java/lang/Throwable
          //   7087	7090	7200	java/lang/Throwable
          //   7138	7141	7200	java/lang/Throwable
          //   7197	7200	7200	java/lang/Throwable
          //   5658	5672	7205	finally
          //   7207	7210	7205	finally
          //   1270	1276	7213	finally
          //   1314	1317	7213	finally
          //   1366	1371	7213	finally
          //   1409	1413	7213	finally
          //   4489	4495	7213	finally
          //   4533	4536	7213	finally
          //   4585	4590	7213	finally
          //   4628	4633	7213	finally
          //   4671	4674	7213	finally
          //   4698	4718	7213	finally
          //   4740	4753	7213	finally
          //   4775	4789	7213	finally
          //   4977	4983	7213	finally
          //   5021	5024	7213	finally
          //   5073	5078	7213	finally
          //   5116	5121	7213	finally
          //   5159	5188	7213	finally
          //   5226	5238	7213	finally
          //   5279	5310	7213	finally
          //   5361	5384	7213	finally
          //   5422	5435	7213	finally
          //   5473	5482	7213	finally
          //   5520	5533	7213	finally
          //   5576	5585	7213	finally
          //   5628	5642	7213	finally
          //   5826	5845	7213	finally
          //   5883	5892	7213	finally
          //   5930	5944	7213	finally
          //   5993	6002	7213	finally
          //   6040	6055	7213	finally
          //   6105	6125	7213	finally
          //   6147	6160	7213	finally
          //   6182	6196	7213	finally
          //   6379	6392	7213	finally
          //   6414	6426	7213	finally
          //   6609	6629	7213	finally
          //   6651	6664	7213	finally
          //   6686	6700	7213	finally
          //   7087	7090	7213	finally
          //   7138	7141	7213	finally
          //   7197	7200	7213	finally
          //   190	203	7222	java/io/FileNotFoundException
          //   300	309	7222	java/io/FileNotFoundException
          //   406	434	7222	java/io/FileNotFoundException
          //   531	540	7222	java/io/FileNotFoundException
          //   637	644	7222	java/io/FileNotFoundException
          //   741	754	7222	java/io/FileNotFoundException
          //   851	863	7222	java/io/FileNotFoundException
          //   960	966	7222	java/io/FileNotFoundException
          //   1063	1073	7222	java/io/FileNotFoundException
          //   1170	1184	7222	java/io/FileNotFoundException
          //   1653	1666	7222	java/io/FileNotFoundException
          //   1771	1778	7222	java/io/FileNotFoundException
          //   1883	1890	7222	java/io/FileNotFoundException
          //   1987	1994	7222	java/io/FileNotFoundException
          //   2091	2098	7222	java/io/FileNotFoundException
          //   2195	2202	7222	java/io/FileNotFoundException
          //   2299	2307	7222	java/io/FileNotFoundException
          //   2404	2411	7222	java/io/FileNotFoundException
          //   2508	2523	7222	java/io/FileNotFoundException
          //   2625	2634	7222	java/io/FileNotFoundException
          //   2731	2742	7222	java/io/FileNotFoundException
          //   2839	2846	7222	java/io/FileNotFoundException
          //   2943	2952	7222	java/io/FileNotFoundException
          //   3049	3054	7222	java/io/FileNotFoundException
          //   3151	3162	7222	java/io/FileNotFoundException
          //   3277	3282	7222	java/io/FileNotFoundException
          //   3379	3408	7222	java/io/FileNotFoundException
          //   3505	3524	7222	java/io/FileNotFoundException
          //   3621	3635	7222	java/io/FileNotFoundException
          //   3754	3762	7222	java/io/FileNotFoundException
          //   3859	3873	7222	java/io/FileNotFoundException
          //   3970	3979	7222	java/io/FileNotFoundException
          //   4076	4082	7222	java/io/FileNotFoundException
          //   4179	4190	7222	java/io/FileNotFoundException
          //   4287	4294	7222	java/io/FileNotFoundException
          //   5361	5384	7249	java/lang/IllegalAccessException
          //   5361	5384	7258	java/lang/NoSuchFieldException
          //   5279	5310	7267	java/lang/reflect/InvocationTargetException
          //   5279	5310	7272	java/lang/IllegalAccessException
          //   5279	5310	7277	java/lang/NoSuchMethodException
        }
      });
      return;
    }
  }
  
  private static String getArgument(JSONArray paramJSONArray, int paramInt, String paramString)
  {
    Object localObject = paramString;
    if (paramJSONArray.length() > paramInt)
    {
      paramJSONArray = paramJSONArray.optString(paramInt);
      if (paramJSONArray != null)
      {
        localObject = paramJSONArray;
        if (!"null".equals(paramJSONArray)) {}
      }
      else
      {
        localObject = paramString;
      }
    }
    return (String)localObject;
  }
  
  private static TrackingInputStream getInputStream(URLConnection paramURLConnection)
    throws IOException
  {
    String str = paramURLConnection.getContentEncoding();
    if ((str != null) && (str.equalsIgnoreCase("gzip"))) {
      return new TrackingGZIPInputStream(new ExposedGZIPInputStream(paramURLConnection.getInputStream()));
    }
    return new SimpleTrackingInputStream(paramURLConnection.getInputStream());
  }
  
  private static void safeClose(Closeable paramCloseable)
  {
    if (paramCloseable != null) {}
    try
    {
      paramCloseable.close();
      return;
    }
    catch (IOException paramCloseable) {}
  }
  
  private static SSLSocketFactory trustAllHosts(HttpsURLConnection paramHttpsURLConnection)
  {
    SSLSocketFactory localSSLSocketFactory = paramHttpsURLConnection.getSSLSocketFactory();
    try
    {
      SSLContext localSSLContext = SSLContext.getInstance("TLS");
      localSSLContext.init(null, trustAllCerts, new SecureRandom());
      paramHttpsURLConnection.setSSLSocketFactory(localSSLContext.getSocketFactory());
      return localSSLSocketFactory;
    }
    catch (Exception paramHttpsURLConnection)
    {
      Log.e("FileTransfer", paramHttpsURLConnection.getMessage(), paramHttpsURLConnection);
    }
    return localSSLSocketFactory;
  }
  
  private void upload(final String paramString1, final String paramString2, final JSONArray paramJSONArray, CallbackContext arg4)
    throws JSONException
  {
    Log.d("FileTransfer", "upload " + paramString1 + " to " + paramString2);
    final String str1 = getArgument(paramJSONArray, 2, "file");
    final String str2 = getArgument(paramJSONArray, 3, "image.jpg");
    final String str3 = getArgument(paramJSONArray, 4, "image/jpeg");
    final JSONObject localJSONObject1;
    final boolean bool3;
    final boolean bool1;
    label112:
    final JSONObject localJSONObject2;
    label131:
    final String str4;
    final String str5;
    final CordovaResourceApi localCordovaResourceApi;
    final Uri localUri;
    label425:
    int i;
    if (paramJSONArray.optJSONObject(5) == null)
    {
      localJSONObject1 = new JSONObject();
      bool3 = paramJSONArray.optBoolean(6);
      if ((!paramJSONArray.optBoolean(7)) && (!paramJSONArray.isNull(7))) {
        break label529;
      }
      bool1 = true;
      if (paramJSONArray.optJSONObject(8) != null) {
        break label535;
      }
      localJSONObject2 = localJSONObject1.optJSONObject("headers");
      str4 = paramJSONArray.getString(9);
      str5 = getArgument(paramJSONArray, 10, "POST");
      localCordovaResourceApi = webView.getResourceApi();
      Log.d("FileTransfer", "fileKey: " + str1);
      Log.d("FileTransfer", "fileName: " + str2);
      Log.d("FileTransfer", "mimeType: " + str3);
      Log.d("FileTransfer", "params: " + localJSONObject1);
      Log.d("FileTransfer", "trustEveryone: " + bool3);
      Log.d("FileTransfer", "chunkedMode: " + bool1);
      Log.d("FileTransfer", "headers: " + localJSONObject2);
      Log.d("FileTransfer", "objectId: " + str4);
      Log.d("FileTransfer", "httpMethod: " + str5);
      localUri = localCordovaResourceApi.remapUri(Uri.parse(paramString2));
      paramJSONArray = Uri.parse(paramString1);
      if (paramJSONArray.getScheme() == null) {
        break label546;
      }
      paramJSONArray = localCordovaResourceApi.remapUri(paramJSONArray);
      i = CordovaResourceApi.getUriType(localUri);
      if (i != 6) {
        break label561;
      }
    }
    label529:
    label535:
    label546:
    label561:
    for (final boolean bool2 = true;; bool2 = false)
    {
      if ((i == 5) || (bool2)) {
        break label567;
      }
      paramString1 = createFileTransferError(INVALID_URL_ERR, paramString1, paramString2, null, Integer.valueOf(0), null);
      Log.e("FileTransfer", "Unsupported URI: " + localUri);
      ???.sendPluginResult(new PluginResult(PluginResult.Status.IO_EXCEPTION, paramString1));
      return;
      localJSONObject1 = paramJSONArray.optJSONObject(5);
      break;
      bool1 = false;
      break label112;
      localJSONObject2 = paramJSONArray.optJSONObject(8);
      break label131;
      paramJSONArray = Uri.fromFile(new File(paramString1));
      break label425;
    }
    label567:
    final RequestContext localRequestContext = new RequestContext(paramString1, paramString2, ???);
    synchronized (activeRequests)
    {
      activeRequests.put(str4, localRequestContext);
      cordova.getThreadPool().execute(new Runnable()
      {
        /* Error */
        public void run()
        {
          // Byte code:
          //   0: aload_0
          //   1: getfield 41	org/apache/cordova/filetransfer/FileTransfer$1:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
          //   4: getfield 89	org/apache/cordova/filetransfer/FileTransfer$RequestContext:aborted	Z
          //   7: ifeq +4 -> 11
          //   10: return
          //   11: aconst_null
          //   12: astore 16
          //   14: aconst_null
          //   15: astore 18
          //   17: aconst_null
          //   18: astore 43
          //   20: aconst_null
          //   21: astore 44
          //   23: aconst_null
          //   24: astore 15
          //   26: aconst_null
          //   27: astore 31
          //   29: aconst_null
          //   30: astore 32
          //   32: aconst_null
          //   33: astore 33
          //   35: aconst_null
          //   36: astore 34
          //   38: aconst_null
          //   39: astore 35
          //   41: aconst_null
          //   42: astore 41
          //   44: aconst_null
          //   45: astore 36
          //   47: aconst_null
          //   48: astore 37
          //   50: aconst_null
          //   51: astore 38
          //   53: aconst_null
          //   54: astore 39
          //   56: aconst_null
          //   57: astore 40
          //   59: aconst_null
          //   60: astore 42
          //   62: iconst_0
          //   63: istore 6
          //   65: iconst_0
          //   66: istore 5
          //   68: iconst_m1
          //   69: istore_1
          //   70: aload 15
          //   72: astore 27
          //   74: aload 31
          //   76: astore 19
          //   78: aload 36
          //   80: astore 20
          //   82: aload 16
          //   84: astore 28
          //   86: iload_1
          //   87: istore_3
          //   88: aload 32
          //   90: astore 21
          //   92: aload 37
          //   94: astore 22
          //   96: iload 6
          //   98: istore 4
          //   100: aload 18
          //   102: astore 30
          //   104: aload 33
          //   106: astore 25
          //   108: aload 38
          //   110: astore 26
          //   112: aload 43
          //   114: astore 29
          //   116: aload 34
          //   118: astore 23
          //   120: aload 39
          //   122: astore 24
          //   124: aload 44
          //   126: astore 17
          //   128: aload 35
          //   130: astore 13
          //   132: aload 40
          //   134: astore 14
          //   136: new 91	org/apache/cordova/filetransfer/FileUploadResult
          //   139: dup
          //   140: invokespecial 92	org/apache/cordova/filetransfer/FileUploadResult:<init>	()V
          //   143: astore 45
          //   145: aload 15
          //   147: astore 27
          //   149: aload 31
          //   151: astore 19
          //   153: aload 36
          //   155: astore 20
          //   157: aload 16
          //   159: astore 28
          //   161: iload_1
          //   162: istore_3
          //   163: aload 32
          //   165: astore 21
          //   167: aload 37
          //   169: astore 22
          //   171: iload 6
          //   173: istore 4
          //   175: aload 18
          //   177: astore 30
          //   179: aload 33
          //   181: astore 25
          //   183: aload 38
          //   185: astore 26
          //   187: aload 43
          //   189: astore 29
          //   191: aload 34
          //   193: astore 23
          //   195: aload 39
          //   197: astore 24
          //   199: aload 44
          //   201: astore 17
          //   203: aload 35
          //   205: astore 13
          //   207: aload 40
          //   209: astore 14
          //   211: new 94	org/apache/cordova/filetransfer/FileProgressResult
          //   214: dup
          //   215: invokespecial 95	org/apache/cordova/filetransfer/FileProgressResult:<init>	()V
          //   218: astore 46
          //   220: aload 15
          //   222: astore 27
          //   224: aload 31
          //   226: astore 19
          //   228: aload 36
          //   230: astore 20
          //   232: aload 16
          //   234: astore 28
          //   236: iload_1
          //   237: istore_3
          //   238: aload 32
          //   240: astore 21
          //   242: aload 37
          //   244: astore 22
          //   246: iload 6
          //   248: istore 4
          //   250: aload 18
          //   252: astore 30
          //   254: aload 33
          //   256: astore 25
          //   258: aload 38
          //   260: astore 26
          //   262: aload 43
          //   264: astore 29
          //   266: aload 34
          //   268: astore 23
          //   270: aload 39
          //   272: astore 24
          //   274: aload 44
          //   276: astore 17
          //   278: aload 35
          //   280: astore 13
          //   282: aload 40
          //   284: astore 14
          //   286: aload_0
          //   287: getfield 43	org/apache/cordova/filetransfer/FileTransfer$1:val$resourceApi	Lorg/apache/cordova/CordovaResourceApi;
          //   290: aload_0
          //   291: getfield 45	org/apache/cordova/filetransfer/FileTransfer$1:val$targetUri	Landroid/net/Uri;
          //   294: invokevirtual 101	org/apache/cordova/CordovaResourceApi:createHttpConnection	(Landroid/net/Uri;)Ljava/net/HttpURLConnection;
          //   297: astore 18
          //   299: aload 41
          //   301: astore 16
          //   303: aload 42
          //   305: astore 15
          //   307: aload 18
          //   309: astore 27
          //   311: aload 31
          //   313: astore 19
          //   315: aload 36
          //   317: astore 20
          //   319: aload 18
          //   321: astore 28
          //   323: iload_1
          //   324: istore_3
          //   325: aload 32
          //   327: astore 21
          //   329: aload 37
          //   331: astore 22
          //   333: iload 6
          //   335: istore 4
          //   337: aload 18
          //   339: astore 30
          //   341: aload 33
          //   343: astore 25
          //   345: aload 38
          //   347: astore 26
          //   349: aload 18
          //   351: astore 29
          //   353: aload 34
          //   355: astore 23
          //   357: aload 39
          //   359: astore 24
          //   361: aload 18
          //   363: astore 17
          //   365: aload 35
          //   367: astore 13
          //   369: aload 40
          //   371: astore 14
          //   373: aload_0
          //   374: getfield 47	org/apache/cordova/filetransfer/FileTransfer$1:val$useHttps	Z
          //   377: ifeq +377 -> 754
          //   380: aload 41
          //   382: astore 16
          //   384: aload 42
          //   386: astore 15
          //   388: aload 18
          //   390: astore 27
          //   392: aload 31
          //   394: astore 19
          //   396: aload 36
          //   398: astore 20
          //   400: aload 18
          //   402: astore 28
          //   404: iload_1
          //   405: istore_3
          //   406: aload 32
          //   408: astore 21
          //   410: aload 37
          //   412: astore 22
          //   414: iload 6
          //   416: istore 4
          //   418: aload 18
          //   420: astore 30
          //   422: aload 33
          //   424: astore 25
          //   426: aload 38
          //   428: astore 26
          //   430: aload 18
          //   432: astore 29
          //   434: aload 34
          //   436: astore 23
          //   438: aload 39
          //   440: astore 24
          //   442: aload 18
          //   444: astore 17
          //   446: aload 35
          //   448: astore 13
          //   450: aload 40
          //   452: astore 14
          //   454: aload_0
          //   455: getfield 49	org/apache/cordova/filetransfer/FileTransfer$1:val$trustEveryone	Z
          //   458: ifeq +296 -> 754
          //   461: aload 18
          //   463: astore 27
          //   465: aload 31
          //   467: astore 19
          //   469: aload 36
          //   471: astore 20
          //   473: aload 18
          //   475: astore 28
          //   477: iload_1
          //   478: istore_3
          //   479: aload 32
          //   481: astore 21
          //   483: aload 37
          //   485: astore 22
          //   487: iload 6
          //   489: istore 4
          //   491: aload 18
          //   493: astore 30
          //   495: aload 33
          //   497: astore 25
          //   499: aload 38
          //   501: astore 26
          //   503: aload 18
          //   505: astore 29
          //   507: aload 34
          //   509: astore 23
          //   511: aload 39
          //   513: astore 24
          //   515: aload 18
          //   517: astore 17
          //   519: aload 35
          //   521: astore 13
          //   523: aload 40
          //   525: astore 14
          //   527: aload 18
          //   529: checkcast 103	javax/net/ssl/HttpsURLConnection
          //   532: astore 41
          //   534: aload 18
          //   536: astore 27
          //   538: aload 31
          //   540: astore 19
          //   542: aload 36
          //   544: astore 20
          //   546: aload 18
          //   548: astore 28
          //   550: iload_1
          //   551: istore_3
          //   552: aload 32
          //   554: astore 21
          //   556: aload 37
          //   558: astore 22
          //   560: iload 6
          //   562: istore 4
          //   564: aload 18
          //   566: astore 30
          //   568: aload 33
          //   570: astore 25
          //   572: aload 38
          //   574: astore 26
          //   576: aload 18
          //   578: astore 29
          //   580: aload 34
          //   582: astore 23
          //   584: aload 39
          //   586: astore 24
          //   588: aload 18
          //   590: astore 17
          //   592: aload 35
          //   594: astore 13
          //   596: aload 40
          //   598: astore 14
          //   600: aload 41
          //   602: invokestatic 107	org/apache/cordova/filetransfer/FileTransfer:access$000	(Ljavax/net/ssl/HttpsURLConnection;)Ljavax/net/ssl/SSLSocketFactory;
          //   605: astore 15
          //   607: aload 18
          //   609: astore 27
          //   611: aload 31
          //   613: astore 19
          //   615: aload 15
          //   617: astore 20
          //   619: aload 18
          //   621: astore 28
          //   623: iload_1
          //   624: istore_3
          //   625: aload 32
          //   627: astore 21
          //   629: aload 15
          //   631: astore 22
          //   633: iload 6
          //   635: istore 4
          //   637: aload 18
          //   639: astore 30
          //   641: aload 33
          //   643: astore 25
          //   645: aload 15
          //   647: astore 26
          //   649: aload 18
          //   651: astore 29
          //   653: aload 34
          //   655: astore 23
          //   657: aload 15
          //   659: astore 24
          //   661: aload 18
          //   663: astore 17
          //   665: aload 35
          //   667: astore 13
          //   669: aload 15
          //   671: astore 14
          //   673: aload 41
          //   675: invokevirtual 111	javax/net/ssl/HttpsURLConnection:getHostnameVerifier	()Ljavax/net/ssl/HostnameVerifier;
          //   678: astore 16
          //   680: aload 18
          //   682: astore 27
          //   684: aload 16
          //   686: astore 19
          //   688: aload 15
          //   690: astore 20
          //   692: aload 18
          //   694: astore 28
          //   696: iload_1
          //   697: istore_3
          //   698: aload 16
          //   700: astore 21
          //   702: aload 15
          //   704: astore 22
          //   706: iload 6
          //   708: istore 4
          //   710: aload 18
          //   712: astore 30
          //   714: aload 16
          //   716: astore 25
          //   718: aload 15
          //   720: astore 26
          //   722: aload 18
          //   724: astore 29
          //   726: aload 16
          //   728: astore 23
          //   730: aload 15
          //   732: astore 24
          //   734: aload 18
          //   736: astore 17
          //   738: aload 16
          //   740: astore 13
          //   742: aload 15
          //   744: astore 14
          //   746: aload 41
          //   748: invokestatic 114	org/apache/cordova/filetransfer/FileTransfer:access$100	()Ljavax/net/ssl/HostnameVerifier;
          //   751: invokevirtual 118	javax/net/ssl/HttpsURLConnection:setHostnameVerifier	(Ljavax/net/ssl/HostnameVerifier;)V
          //   754: aload 18
          //   756: astore 27
          //   758: aload 16
          //   760: astore 19
          //   762: aload 15
          //   764: astore 20
          //   766: aload 18
          //   768: astore 28
          //   770: iload_1
          //   771: istore_3
          //   772: aload 16
          //   774: astore 21
          //   776: aload 15
          //   778: astore 22
          //   780: iload 6
          //   782: istore 4
          //   784: aload 18
          //   786: astore 30
          //   788: aload 16
          //   790: astore 25
          //   792: aload 15
          //   794: astore 26
          //   796: aload 18
          //   798: astore 29
          //   800: aload 16
          //   802: astore 23
          //   804: aload 15
          //   806: astore 24
          //   808: aload 18
          //   810: astore 17
          //   812: aload 16
          //   814: astore 13
          //   816: aload 15
          //   818: astore 14
          //   820: aload 18
          //   822: iconst_1
          //   823: invokevirtual 124	java/net/HttpURLConnection:setDoInput	(Z)V
          //   826: aload 18
          //   828: astore 27
          //   830: aload 16
          //   832: astore 19
          //   834: aload 15
          //   836: astore 20
          //   838: aload 18
          //   840: astore 28
          //   842: iload_1
          //   843: istore_3
          //   844: aload 16
          //   846: astore 21
          //   848: aload 15
          //   850: astore 22
          //   852: iload 6
          //   854: istore 4
          //   856: aload 18
          //   858: astore 30
          //   860: aload 16
          //   862: astore 25
          //   864: aload 15
          //   866: astore 26
          //   868: aload 18
          //   870: astore 29
          //   872: aload 16
          //   874: astore 23
          //   876: aload 15
          //   878: astore 24
          //   880: aload 18
          //   882: astore 17
          //   884: aload 16
          //   886: astore 13
          //   888: aload 15
          //   890: astore 14
          //   892: aload 18
          //   894: iconst_1
          //   895: invokevirtual 127	java/net/HttpURLConnection:setDoOutput	(Z)V
          //   898: aload 18
          //   900: astore 27
          //   902: aload 16
          //   904: astore 19
          //   906: aload 15
          //   908: astore 20
          //   910: aload 18
          //   912: astore 28
          //   914: iload_1
          //   915: istore_3
          //   916: aload 16
          //   918: astore 21
          //   920: aload 15
          //   922: astore 22
          //   924: iload 6
          //   926: istore 4
          //   928: aload 18
          //   930: astore 30
          //   932: aload 16
          //   934: astore 25
          //   936: aload 15
          //   938: astore 26
          //   940: aload 18
          //   942: astore 29
          //   944: aload 16
          //   946: astore 23
          //   948: aload 15
          //   950: astore 24
          //   952: aload 18
          //   954: astore 17
          //   956: aload 16
          //   958: astore 13
          //   960: aload 15
          //   962: astore 14
          //   964: aload 18
          //   966: iconst_0
          //   967: invokevirtual 130	java/net/HttpURLConnection:setUseCaches	(Z)V
          //   970: aload 18
          //   972: astore 27
          //   974: aload 16
          //   976: astore 19
          //   978: aload 15
          //   980: astore 20
          //   982: aload 18
          //   984: astore 28
          //   986: iload_1
          //   987: istore_3
          //   988: aload 16
          //   990: astore 21
          //   992: aload 15
          //   994: astore 22
          //   996: iload 6
          //   998: istore 4
          //   1000: aload 18
          //   1002: astore 30
          //   1004: aload 16
          //   1006: astore 25
          //   1008: aload 15
          //   1010: astore 26
          //   1012: aload 18
          //   1014: astore 29
          //   1016: aload 16
          //   1018: astore 23
          //   1020: aload 15
          //   1022: astore 24
          //   1024: aload 18
          //   1026: astore 17
          //   1028: aload 16
          //   1030: astore 13
          //   1032: aload 15
          //   1034: astore 14
          //   1036: aload 18
          //   1038: aload_0
          //   1039: getfield 51	org/apache/cordova/filetransfer/FileTransfer$1:val$httpMethod	Ljava/lang/String;
          //   1042: invokevirtual 134	java/net/HttpURLConnection:setRequestMethod	(Ljava/lang/String;)V
          //   1045: aload 18
          //   1047: astore 27
          //   1049: aload 16
          //   1051: astore 19
          //   1053: aload 15
          //   1055: astore 20
          //   1057: aload 18
          //   1059: astore 28
          //   1061: iload_1
          //   1062: istore_3
          //   1063: aload 16
          //   1065: astore 21
          //   1067: aload 15
          //   1069: astore 22
          //   1071: iload 6
          //   1073: istore 4
          //   1075: aload 18
          //   1077: astore 30
          //   1079: aload 16
          //   1081: astore 25
          //   1083: aload 15
          //   1085: astore 26
          //   1087: aload 18
          //   1089: astore 29
          //   1091: aload 16
          //   1093: astore 23
          //   1095: aload 15
          //   1097: astore 24
          //   1099: aload 18
          //   1101: astore 17
          //   1103: aload 16
          //   1105: astore 13
          //   1107: aload 15
          //   1109: astore 14
          //   1111: aload 18
          //   1113: ldc -120
          //   1115: ldc -118
          //   1117: invokevirtual 142	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
          //   1120: aload 18
          //   1122: astore 27
          //   1124: aload 16
          //   1126: astore 19
          //   1128: aload 15
          //   1130: astore 20
          //   1132: aload 18
          //   1134: astore 28
          //   1136: iload_1
          //   1137: istore_3
          //   1138: aload 16
          //   1140: astore 21
          //   1142: aload 15
          //   1144: astore 22
          //   1146: iload 6
          //   1148: istore 4
          //   1150: aload 18
          //   1152: astore 30
          //   1154: aload 16
          //   1156: astore 25
          //   1158: aload 15
          //   1160: astore 26
          //   1162: aload 18
          //   1164: astore 29
          //   1166: aload 16
          //   1168: astore 23
          //   1170: aload 15
          //   1172: astore 24
          //   1174: aload 18
          //   1176: astore 17
          //   1178: aload 16
          //   1180: astore 13
          //   1182: aload 15
          //   1184: astore 14
          //   1186: invokestatic 148	android/webkit/CookieManager:getInstance	()Landroid/webkit/CookieManager;
          //   1189: aload_0
          //   1190: getfield 53	org/apache/cordova/filetransfer/FileTransfer$1:val$target	Ljava/lang/String;
          //   1193: invokevirtual 152	android/webkit/CookieManager:getCookie	(Ljava/lang/String;)Ljava/lang/String;
          //   1196: astore 31
          //   1198: aload 31
          //   1200: ifnull +78 -> 1278
          //   1203: aload 18
          //   1205: astore 27
          //   1207: aload 16
          //   1209: astore 19
          //   1211: aload 15
          //   1213: astore 20
          //   1215: aload 18
          //   1217: astore 28
          //   1219: iload_1
          //   1220: istore_3
          //   1221: aload 16
          //   1223: astore 21
          //   1225: aload 15
          //   1227: astore 22
          //   1229: iload 6
          //   1231: istore 4
          //   1233: aload 18
          //   1235: astore 30
          //   1237: aload 16
          //   1239: astore 25
          //   1241: aload 15
          //   1243: astore 26
          //   1245: aload 18
          //   1247: astore 29
          //   1249: aload 16
          //   1251: astore 23
          //   1253: aload 15
          //   1255: astore 24
          //   1257: aload 18
          //   1259: astore 17
          //   1261: aload 16
          //   1263: astore 13
          //   1265: aload 15
          //   1267: astore 14
          //   1269: aload 18
          //   1271: ldc -102
          //   1273: aload 31
          //   1275: invokevirtual 142	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
          //   1278: aload 18
          //   1280: astore 27
          //   1282: aload 16
          //   1284: astore 19
          //   1286: aload 15
          //   1288: astore 20
          //   1290: aload 18
          //   1292: astore 28
          //   1294: iload_1
          //   1295: istore_3
          //   1296: aload 16
          //   1298: astore 21
          //   1300: aload 15
          //   1302: astore 22
          //   1304: iload 6
          //   1306: istore 4
          //   1308: aload 18
          //   1310: astore 30
          //   1312: aload 16
          //   1314: astore 25
          //   1316: aload 15
          //   1318: astore 26
          //   1320: aload 18
          //   1322: astore 29
          //   1324: aload 16
          //   1326: astore 23
          //   1328: aload 15
          //   1330: astore 24
          //   1332: aload 18
          //   1334: astore 17
          //   1336: aload 16
          //   1338: astore 13
          //   1340: aload 15
          //   1342: astore 14
          //   1344: aload_0
          //   1345: getfield 55	org/apache/cordova/filetransfer/FileTransfer$1:val$headers	Lorg/json/JSONObject;
          //   1348: ifnull +78 -> 1426
          //   1351: aload 18
          //   1353: astore 27
          //   1355: aload 16
          //   1357: astore 19
          //   1359: aload 15
          //   1361: astore 20
          //   1363: aload 18
          //   1365: astore 28
          //   1367: iload_1
          //   1368: istore_3
          //   1369: aload 16
          //   1371: astore 21
          //   1373: aload 15
          //   1375: astore 22
          //   1377: iload 6
          //   1379: istore 4
          //   1381: aload 18
          //   1383: astore 30
          //   1385: aload 16
          //   1387: astore 25
          //   1389: aload 15
          //   1391: astore 26
          //   1393: aload 18
          //   1395: astore 29
          //   1397: aload 16
          //   1399: astore 23
          //   1401: aload 15
          //   1403: astore 24
          //   1405: aload 18
          //   1407: astore 17
          //   1409: aload 16
          //   1411: astore 13
          //   1413: aload 15
          //   1415: astore 14
          //   1417: aload 18
          //   1419: aload_0
          //   1420: getfield 55	org/apache/cordova/filetransfer/FileTransfer$1:val$headers	Lorg/json/JSONObject;
          //   1423: invokestatic 158	org/apache/cordova/filetransfer/FileTransfer:access$200	(Ljava/net/URLConnection;Lorg/json/JSONObject;)V
          //   1426: aload 18
          //   1428: astore 27
          //   1430: aload 16
          //   1432: astore 19
          //   1434: aload 15
          //   1436: astore 20
          //   1438: aload 18
          //   1440: astore 28
          //   1442: iload_1
          //   1443: istore_3
          //   1444: aload 16
          //   1446: astore 21
          //   1448: aload 15
          //   1450: astore 22
          //   1452: iload 6
          //   1454: istore 4
          //   1456: aload 18
          //   1458: astore 30
          //   1460: aload 16
          //   1462: astore 25
          //   1464: aload 15
          //   1466: astore 26
          //   1468: aload 18
          //   1470: astore 29
          //   1472: aload 16
          //   1474: astore 23
          //   1476: aload 15
          //   1478: astore 24
          //   1480: aload 18
          //   1482: astore 17
          //   1484: aload 16
          //   1486: astore 13
          //   1488: aload 15
          //   1490: astore 14
          //   1492: new 160	java/lang/StringBuilder
          //   1495: dup
          //   1496: invokespecial 161	java/lang/StringBuilder:<init>	()V
          //   1499: astore 31
          //   1501: aload 18
          //   1503: astore 27
          //   1505: aload 16
          //   1507: astore 19
          //   1509: aload 15
          //   1511: astore 20
          //   1513: aload 18
          //   1515: astore 28
          //   1517: iload_1
          //   1518: istore_3
          //   1519: aload 16
          //   1521: astore 21
          //   1523: aload 15
          //   1525: astore 22
          //   1527: iload 6
          //   1529: istore 4
          //   1531: aload 18
          //   1533: astore 29
          //   1535: aload 16
          //   1537: astore 23
          //   1539: aload 15
          //   1541: astore 24
          //   1543: aload 18
          //   1545: astore 17
          //   1547: aload 16
          //   1549: astore 13
          //   1551: aload 15
          //   1553: astore 14
          //   1555: aload_0
          //   1556: getfield 57	org/apache/cordova/filetransfer/FileTransfer$1:val$params	Lorg/json/JSONObject;
          //   1559: invokevirtual 167	org/json/JSONObject:keys	()Ljava/util/Iterator;
          //   1562: astore 25
          //   1564: aload 18
          //   1566: astore 27
          //   1568: aload 16
          //   1570: astore 19
          //   1572: aload 15
          //   1574: astore 20
          //   1576: aload 18
          //   1578: astore 28
          //   1580: iload_1
          //   1581: istore_3
          //   1582: aload 16
          //   1584: astore 21
          //   1586: aload 15
          //   1588: astore 22
          //   1590: iload 6
          //   1592: istore 4
          //   1594: aload 18
          //   1596: astore 29
          //   1598: aload 16
          //   1600: astore 23
          //   1602: aload 15
          //   1604: astore 24
          //   1606: aload 18
          //   1608: astore 17
          //   1610: aload 16
          //   1612: astore 13
          //   1614: aload 15
          //   1616: astore 14
          //   1618: aload 25
          //   1620: invokeinterface 173 1 0
          //   1625: ifeq +565 -> 2190
          //   1628: aload 18
          //   1630: astore 27
          //   1632: aload 16
          //   1634: astore 19
          //   1636: aload 15
          //   1638: astore 20
          //   1640: aload 18
          //   1642: astore 28
          //   1644: iload_1
          //   1645: istore_3
          //   1646: aload 16
          //   1648: astore 21
          //   1650: aload 15
          //   1652: astore 22
          //   1654: iload 6
          //   1656: istore 4
          //   1658: aload 18
          //   1660: astore 29
          //   1662: aload 16
          //   1664: astore 23
          //   1666: aload 15
          //   1668: astore 24
          //   1670: aload 18
          //   1672: astore 17
          //   1674: aload 16
          //   1676: astore 13
          //   1678: aload 15
          //   1680: astore 14
          //   1682: aload 25
          //   1684: invokeinterface 177 1 0
          //   1689: astore 26
          //   1691: aload 18
          //   1693: astore 27
          //   1695: aload 16
          //   1697: astore 19
          //   1699: aload 15
          //   1701: astore 20
          //   1703: aload 18
          //   1705: astore 28
          //   1707: iload_1
          //   1708: istore_3
          //   1709: aload 16
          //   1711: astore 21
          //   1713: aload 15
          //   1715: astore 22
          //   1717: iload 6
          //   1719: istore 4
          //   1721: aload 18
          //   1723: astore 29
          //   1725: aload 16
          //   1727: astore 23
          //   1729: aload 15
          //   1731: astore 24
          //   1733: aload 18
          //   1735: astore 17
          //   1737: aload 16
          //   1739: astore 13
          //   1741: aload 15
          //   1743: astore 14
          //   1745: aload 26
          //   1747: invokestatic 183	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
          //   1750: ldc -71
          //   1752: invokevirtual 189	java/lang/String:equals	(Ljava/lang/Object;)Z
          //   1755: ifne -191 -> 1564
          //   1758: aload 18
          //   1760: astore 27
          //   1762: aload 16
          //   1764: astore 19
          //   1766: aload 15
          //   1768: astore 20
          //   1770: aload 18
          //   1772: astore 28
          //   1774: iload_1
          //   1775: istore_3
          //   1776: aload 16
          //   1778: astore 21
          //   1780: aload 15
          //   1782: astore 22
          //   1784: iload 6
          //   1786: istore 4
          //   1788: aload 18
          //   1790: astore 29
          //   1792: aload 16
          //   1794: astore 23
          //   1796: aload 15
          //   1798: astore 24
          //   1800: aload 18
          //   1802: astore 17
          //   1804: aload 16
          //   1806: astore 13
          //   1808: aload 15
          //   1810: astore 14
          //   1812: aload 31
          //   1814: ldc -65
          //   1816: invokevirtual 195	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   1819: ldc -59
          //   1821: invokevirtual 195	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   1824: ldc -57
          //   1826: invokevirtual 195	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   1829: pop
          //   1830: aload 18
          //   1832: astore 27
          //   1834: aload 16
          //   1836: astore 19
          //   1838: aload 15
          //   1840: astore 20
          //   1842: aload 18
          //   1844: astore 28
          //   1846: iload_1
          //   1847: istore_3
          //   1848: aload 16
          //   1850: astore 21
          //   1852: aload 15
          //   1854: astore 22
          //   1856: iload 6
          //   1858: istore 4
          //   1860: aload 18
          //   1862: astore 29
          //   1864: aload 16
          //   1866: astore 23
          //   1868: aload 15
          //   1870: astore 24
          //   1872: aload 18
          //   1874: astore 17
          //   1876: aload 16
          //   1878: astore 13
          //   1880: aload 15
          //   1882: astore 14
          //   1884: aload 31
          //   1886: ldc -55
          //   1888: invokevirtual 195	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   1891: aload 26
          //   1893: invokevirtual 205	java/lang/Object:toString	()Ljava/lang/String;
          //   1896: invokevirtual 195	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   1899: bipush 34
          //   1901: invokevirtual 208	java/lang/StringBuilder:append	(C)Ljava/lang/StringBuilder;
          //   1904: pop
          //   1905: aload 18
          //   1907: astore 27
          //   1909: aload 16
          //   1911: astore 19
          //   1913: aload 15
          //   1915: astore 20
          //   1917: aload 18
          //   1919: astore 28
          //   1921: iload_1
          //   1922: istore_3
          //   1923: aload 16
          //   1925: astore 21
          //   1927: aload 15
          //   1929: astore 22
          //   1931: iload 6
          //   1933: istore 4
          //   1935: aload 18
          //   1937: astore 29
          //   1939: aload 16
          //   1941: astore 23
          //   1943: aload 15
          //   1945: astore 24
          //   1947: aload 18
          //   1949: astore 17
          //   1951: aload 16
          //   1953: astore 13
          //   1955: aload 15
          //   1957: astore 14
          //   1959: aload 31
          //   1961: ldc -57
          //   1963: invokevirtual 195	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   1966: ldc -57
          //   1968: invokevirtual 195	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   1971: pop
          //   1972: aload 18
          //   1974: astore 27
          //   1976: aload 16
          //   1978: astore 19
          //   1980: aload 15
          //   1982: astore 20
          //   1984: aload 18
          //   1986: astore 28
          //   1988: iload_1
          //   1989: istore_3
          //   1990: aload 16
          //   1992: astore 21
          //   1994: aload 15
          //   1996: astore 22
          //   1998: iload 6
          //   2000: istore 4
          //   2002: aload 18
          //   2004: astore 29
          //   2006: aload 16
          //   2008: astore 23
          //   2010: aload 15
          //   2012: astore 24
          //   2014: aload 18
          //   2016: astore 17
          //   2018: aload 16
          //   2020: astore 13
          //   2022: aload 15
          //   2024: astore 14
          //   2026: aload 31
          //   2028: aload_0
          //   2029: getfield 57	org/apache/cordova/filetransfer/FileTransfer$1:val$params	Lorg/json/JSONObject;
          //   2032: aload 26
          //   2034: invokevirtual 205	java/lang/Object:toString	()Ljava/lang/String;
          //   2037: invokevirtual 211	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
          //   2040: invokevirtual 195	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   2043: pop
          //   2044: aload 18
          //   2046: astore 27
          //   2048: aload 16
          //   2050: astore 19
          //   2052: aload 15
          //   2054: astore 20
          //   2056: aload 18
          //   2058: astore 28
          //   2060: iload_1
          //   2061: istore_3
          //   2062: aload 16
          //   2064: astore 21
          //   2066: aload 15
          //   2068: astore 22
          //   2070: iload 6
          //   2072: istore 4
          //   2074: aload 18
          //   2076: astore 29
          //   2078: aload 16
          //   2080: astore 23
          //   2082: aload 15
          //   2084: astore 24
          //   2086: aload 18
          //   2088: astore 17
          //   2090: aload 16
          //   2092: astore 13
          //   2094: aload 15
          //   2096: astore 14
          //   2098: aload 31
          //   2100: ldc -57
          //   2102: invokevirtual 195	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   2105: pop
          //   2106: goto -542 -> 1564
          //   2109: astore 32
          //   2111: aload 18
          //   2113: astore 27
          //   2115: aload 16
          //   2117: astore 19
          //   2119: aload 15
          //   2121: astore 20
          //   2123: aload 18
          //   2125: astore 28
          //   2127: iload_1
          //   2128: istore_3
          //   2129: aload 16
          //   2131: astore 21
          //   2133: aload 15
          //   2135: astore 22
          //   2137: iload 6
          //   2139: istore 4
          //   2141: aload 18
          //   2143: astore 30
          //   2145: aload 16
          //   2147: astore 25
          //   2149: aload 15
          //   2151: astore 26
          //   2153: aload 18
          //   2155: astore 29
          //   2157: aload 16
          //   2159: astore 23
          //   2161: aload 15
          //   2163: astore 24
          //   2165: aload 18
          //   2167: astore 17
          //   2169: aload 16
          //   2171: astore 13
          //   2173: aload 15
          //   2175: astore 14
          //   2177: ldc -43
          //   2179: aload 32
          //   2181: invokevirtual 216	org/json/JSONException:getMessage	()Ljava/lang/String;
          //   2184: aload 32
          //   2186: invokestatic 222	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
          //   2189: pop
          //   2190: aload 18
          //   2192: astore 27
          //   2194: aload 16
          //   2196: astore 19
          //   2198: aload 15
          //   2200: astore 20
          //   2202: aload 18
          //   2204: astore 28
          //   2206: iload_1
          //   2207: istore_3
          //   2208: aload 16
          //   2210: astore 21
          //   2212: aload 15
          //   2214: astore 22
          //   2216: iload 6
          //   2218: istore 4
          //   2220: aload 18
          //   2222: astore 30
          //   2224: aload 16
          //   2226: astore 25
          //   2228: aload 15
          //   2230: astore 26
          //   2232: aload 18
          //   2234: astore 29
          //   2236: aload 16
          //   2238: astore 23
          //   2240: aload 15
          //   2242: astore 24
          //   2244: aload 18
          //   2246: astore 17
          //   2248: aload 16
          //   2250: astore 13
          //   2252: aload 15
          //   2254: astore 14
          //   2256: aload 31
          //   2258: ldc -65
          //   2260: invokevirtual 195	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   2263: ldc -59
          //   2265: invokevirtual 195	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   2268: ldc -57
          //   2270: invokevirtual 195	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   2273: pop
          //   2274: aload 18
          //   2276: astore 27
          //   2278: aload 16
          //   2280: astore 19
          //   2282: aload 15
          //   2284: astore 20
          //   2286: aload 18
          //   2288: astore 28
          //   2290: iload_1
          //   2291: istore_3
          //   2292: aload 16
          //   2294: astore 21
          //   2296: aload 15
          //   2298: astore 22
          //   2300: iload 6
          //   2302: istore 4
          //   2304: aload 18
          //   2306: astore 30
          //   2308: aload 16
          //   2310: astore 25
          //   2312: aload 15
          //   2314: astore 26
          //   2316: aload 18
          //   2318: astore 29
          //   2320: aload 16
          //   2322: astore 23
          //   2324: aload 15
          //   2326: astore 24
          //   2328: aload 18
          //   2330: astore 17
          //   2332: aload 16
          //   2334: astore 13
          //   2336: aload 15
          //   2338: astore 14
          //   2340: aload 31
          //   2342: ldc -55
          //   2344: invokevirtual 195	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   2347: aload_0
          //   2348: getfield 59	org/apache/cordova/filetransfer/FileTransfer$1:val$fileKey	Ljava/lang/String;
          //   2351: invokevirtual 195	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   2354: ldc -32
          //   2356: invokevirtual 195	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   2359: pop
          //   2360: aload 18
          //   2362: astore 27
          //   2364: aload 16
          //   2366: astore 19
          //   2368: aload 15
          //   2370: astore 20
          //   2372: aload 18
          //   2374: astore 28
          //   2376: iload_1
          //   2377: istore_3
          //   2378: aload 16
          //   2380: astore 21
          //   2382: aload 15
          //   2384: astore 22
          //   2386: iload 6
          //   2388: istore 4
          //   2390: aload 18
          //   2392: astore 30
          //   2394: aload 16
          //   2396: astore 25
          //   2398: aload 15
          //   2400: astore 26
          //   2402: aload 18
          //   2404: astore 29
          //   2406: aload 16
          //   2408: astore 23
          //   2410: aload 15
          //   2412: astore 24
          //   2414: aload 18
          //   2416: astore 17
          //   2418: aload 16
          //   2420: astore 13
          //   2422: aload 15
          //   2424: astore 14
          //   2426: aload 31
          //   2428: ldc -30
          //   2430: invokevirtual 195	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   2433: aload_0
          //   2434: getfield 61	org/apache/cordova/filetransfer/FileTransfer$1:val$fileName	Ljava/lang/String;
          //   2437: invokevirtual 195	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   2440: bipush 34
          //   2442: invokevirtual 208	java/lang/StringBuilder:append	(C)Ljava/lang/StringBuilder;
          //   2445: ldc -57
          //   2447: invokevirtual 195	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   2450: pop
          //   2451: aload 18
          //   2453: astore 27
          //   2455: aload 16
          //   2457: astore 19
          //   2459: aload 15
          //   2461: astore 20
          //   2463: aload 18
          //   2465: astore 28
          //   2467: iload_1
          //   2468: istore_3
          //   2469: aload 16
          //   2471: astore 21
          //   2473: aload 15
          //   2475: astore 22
          //   2477: iload 6
          //   2479: istore 4
          //   2481: aload 18
          //   2483: astore 30
          //   2485: aload 16
          //   2487: astore 25
          //   2489: aload 15
          //   2491: astore 26
          //   2493: aload 18
          //   2495: astore 29
          //   2497: aload 16
          //   2499: astore 23
          //   2501: aload 15
          //   2503: astore 24
          //   2505: aload 18
          //   2507: astore 17
          //   2509: aload 16
          //   2511: astore 13
          //   2513: aload 15
          //   2515: astore 14
          //   2517: aload 31
          //   2519: ldc -28
          //   2521: invokevirtual 195	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   2524: aload_0
          //   2525: getfield 63	org/apache/cordova/filetransfer/FileTransfer$1:val$mimeType	Ljava/lang/String;
          //   2528: invokevirtual 195	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   2531: ldc -57
          //   2533: invokevirtual 195	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   2536: ldc -57
          //   2538: invokevirtual 195	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   2541: pop
          //   2542: aload 18
          //   2544: astore 27
          //   2546: aload 16
          //   2548: astore 19
          //   2550: aload 15
          //   2552: astore 20
          //   2554: aload 18
          //   2556: astore 28
          //   2558: iload_1
          //   2559: istore_3
          //   2560: aload 16
          //   2562: astore 21
          //   2564: aload 15
          //   2566: astore 22
          //   2568: iload 6
          //   2570: istore 4
          //   2572: aload 18
          //   2574: astore 30
          //   2576: aload 16
          //   2578: astore 25
          //   2580: aload 15
          //   2582: astore 26
          //   2584: aload 18
          //   2586: astore 29
          //   2588: aload 16
          //   2590: astore 23
          //   2592: aload 15
          //   2594: astore 24
          //   2596: aload 18
          //   2598: astore 17
          //   2600: aload 16
          //   2602: astore 13
          //   2604: aload 15
          //   2606: astore 14
          //   2608: aload 31
          //   2610: invokevirtual 229	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   2613: ldc -25
          //   2615: invokevirtual 235	java/lang/String:getBytes	(Ljava/lang/String;)[B
          //   2618: astore 35
          //   2620: aload 18
          //   2622: astore 27
          //   2624: aload 16
          //   2626: astore 19
          //   2628: aload 15
          //   2630: astore 20
          //   2632: aload 18
          //   2634: astore 28
          //   2636: iload_1
          //   2637: istore_3
          //   2638: aload 16
          //   2640: astore 21
          //   2642: aload 15
          //   2644: astore 22
          //   2646: iload 6
          //   2648: istore 4
          //   2650: aload 18
          //   2652: astore 30
          //   2654: aload 16
          //   2656: astore 25
          //   2658: aload 15
          //   2660: astore 26
          //   2662: aload 18
          //   2664: astore 29
          //   2666: aload 16
          //   2668: astore 23
          //   2670: aload 15
          //   2672: astore 24
          //   2674: aload 18
          //   2676: astore 17
          //   2678: aload 16
          //   2680: astore 13
          //   2682: aload 15
          //   2684: astore 14
          //   2686: ldc -19
          //   2688: ldc -25
          //   2690: invokevirtual 235	java/lang/String:getBytes	(Ljava/lang/String;)[B
          //   2693: astore 34
          //   2695: aload 18
          //   2697: astore 27
          //   2699: aload 16
          //   2701: astore 19
          //   2703: aload 15
          //   2705: astore 20
          //   2707: aload 18
          //   2709: astore 28
          //   2711: iload_1
          //   2712: istore_3
          //   2713: aload 16
          //   2715: astore 21
          //   2717: aload 15
          //   2719: astore 22
          //   2721: iload 6
          //   2723: istore 4
          //   2725: aload 18
          //   2727: astore 30
          //   2729: aload 16
          //   2731: astore 25
          //   2733: aload 15
          //   2735: astore 26
          //   2737: aload 18
          //   2739: astore 29
          //   2741: aload 16
          //   2743: astore 23
          //   2745: aload 15
          //   2747: astore 24
          //   2749: aload 18
          //   2751: astore 17
          //   2753: aload 16
          //   2755: astore 13
          //   2757: aload 15
          //   2759: astore 14
          //   2761: aload_0
          //   2762: getfield 43	org/apache/cordova/filetransfer/FileTransfer$1:val$resourceApi	Lorg/apache/cordova/CordovaResourceApi;
          //   2765: aload_0
          //   2766: getfield 65	org/apache/cordova/filetransfer/FileTransfer$1:val$sourceUri	Landroid/net/Uri;
          //   2769: invokevirtual 241	org/apache/cordova/CordovaResourceApi:openForRead	(Landroid/net/Uri;)Lorg/apache/cordova/CordovaResourceApi$OpenForReadResult;
          //   2772: astore 33
          //   2774: aload 18
          //   2776: astore 27
          //   2778: aload 16
          //   2780: astore 19
          //   2782: aload 15
          //   2784: astore 20
          //   2786: aload 18
          //   2788: astore 28
          //   2790: iload_1
          //   2791: istore_3
          //   2792: aload 16
          //   2794: astore 21
          //   2796: aload 15
          //   2798: astore 22
          //   2800: iload 6
          //   2802: istore 4
          //   2804: aload 18
          //   2806: astore 30
          //   2808: aload 16
          //   2810: astore 25
          //   2812: aload 15
          //   2814: astore 26
          //   2816: aload 18
          //   2818: astore 29
          //   2820: aload 16
          //   2822: astore 23
          //   2824: aload 15
          //   2826: astore 24
          //   2828: aload 18
          //   2830: astore 17
          //   2832: aload 16
          //   2834: astore 13
          //   2836: aload 15
          //   2838: astore 14
          //   2840: aload 35
          //   2842: arraylength
          //   2843: istore 7
          //   2845: aload 18
          //   2847: astore 27
          //   2849: aload 16
          //   2851: astore 19
          //   2853: aload 15
          //   2855: astore 20
          //   2857: aload 18
          //   2859: astore 28
          //   2861: iload_1
          //   2862: istore_3
          //   2863: aload 16
          //   2865: astore 21
          //   2867: aload 15
          //   2869: astore 22
          //   2871: iload 6
          //   2873: istore 4
          //   2875: aload 18
          //   2877: astore 30
          //   2879: aload 16
          //   2881: astore 25
          //   2883: aload 15
          //   2885: astore 26
          //   2887: aload 18
          //   2889: astore 29
          //   2891: aload 16
          //   2893: astore 23
          //   2895: aload 15
          //   2897: astore 24
          //   2899: aload 18
          //   2901: astore 17
          //   2903: aload 16
          //   2905: astore 13
          //   2907: aload 15
          //   2909: astore 14
          //   2911: aload 34
          //   2913: arraylength
          //   2914: istore 8
          //   2916: iload_1
          //   2917: istore_2
          //   2918: aload 18
          //   2920: astore 27
          //   2922: aload 16
          //   2924: astore 19
          //   2926: aload 15
          //   2928: astore 20
          //   2930: aload 18
          //   2932: astore 28
          //   2934: iload_1
          //   2935: istore_3
          //   2936: aload 16
          //   2938: astore 21
          //   2940: aload 15
          //   2942: astore 22
          //   2944: iload 6
          //   2946: istore 4
          //   2948: aload 18
          //   2950: astore 30
          //   2952: aload 16
          //   2954: astore 25
          //   2956: aload 15
          //   2958: astore 26
          //   2960: aload 18
          //   2962: astore 29
          //   2964: aload 16
          //   2966: astore 23
          //   2968: aload 15
          //   2970: astore 24
          //   2972: aload 18
          //   2974: astore 17
          //   2976: aload 16
          //   2978: astore 13
          //   2980: aload 15
          //   2982: astore 14
          //   2984: aload 33
          //   2986: getfield 247	org/apache/cordova/CordovaResourceApi$OpenForReadResult:length	J
          //   2989: lconst_0
          //   2990: lcmp
          //   2991: iflt +227 -> 3218
          //   2994: aload 18
          //   2996: astore 27
          //   2998: aload 16
          //   3000: astore 19
          //   3002: aload 15
          //   3004: astore 20
          //   3006: aload 18
          //   3008: astore 28
          //   3010: iload_1
          //   3011: istore_3
          //   3012: aload 16
          //   3014: astore 21
          //   3016: aload 15
          //   3018: astore 22
          //   3020: iload 6
          //   3022: istore 4
          //   3024: aload 18
          //   3026: astore 30
          //   3028: aload 16
          //   3030: astore 25
          //   3032: aload 15
          //   3034: astore 26
          //   3036: aload 18
          //   3038: astore 29
          //   3040: aload 16
          //   3042: astore 23
          //   3044: aload 15
          //   3046: astore 24
          //   3048: aload 18
          //   3050: astore 17
          //   3052: aload 16
          //   3054: astore 13
          //   3056: aload 15
          //   3058: astore 14
          //   3060: aload 33
          //   3062: getfield 247	org/apache/cordova/CordovaResourceApi$OpenForReadResult:length	J
          //   3065: l2i
          //   3066: iload 7
          //   3068: iload 8
          //   3070: iadd
          //   3071: iadd
          //   3072: istore_2
          //   3073: aload 18
          //   3075: astore 27
          //   3077: aload 16
          //   3079: astore 19
          //   3081: aload 15
          //   3083: astore 20
          //   3085: aload 18
          //   3087: astore 28
          //   3089: iload_2
          //   3090: istore_3
          //   3091: aload 16
          //   3093: astore 21
          //   3095: aload 15
          //   3097: astore 22
          //   3099: iload 6
          //   3101: istore 4
          //   3103: aload 18
          //   3105: astore 30
          //   3107: aload 16
          //   3109: astore 25
          //   3111: aload 15
          //   3113: astore 26
          //   3115: aload 18
          //   3117: astore 29
          //   3119: aload 16
          //   3121: astore 23
          //   3123: aload 15
          //   3125: astore 24
          //   3127: aload 18
          //   3129: astore 17
          //   3131: aload 16
          //   3133: astore 13
          //   3135: aload 15
          //   3137: astore 14
          //   3139: aload 46
          //   3141: iconst_1
          //   3142: invokevirtual 250	org/apache/cordova/filetransfer/FileProgressResult:setLengthComputable	(Z)V
          //   3145: aload 18
          //   3147: astore 27
          //   3149: aload 16
          //   3151: astore 19
          //   3153: aload 15
          //   3155: astore 20
          //   3157: aload 18
          //   3159: astore 28
          //   3161: iload_2
          //   3162: istore_3
          //   3163: aload 16
          //   3165: astore 21
          //   3167: aload 15
          //   3169: astore 22
          //   3171: iload 6
          //   3173: istore 4
          //   3175: aload 18
          //   3177: astore 30
          //   3179: aload 16
          //   3181: astore 25
          //   3183: aload 15
          //   3185: astore 26
          //   3187: aload 18
          //   3189: astore 29
          //   3191: aload 16
          //   3193: astore 23
          //   3195: aload 15
          //   3197: astore 24
          //   3199: aload 18
          //   3201: astore 17
          //   3203: aload 16
          //   3205: astore 13
          //   3207: aload 15
          //   3209: astore 14
          //   3211: aload 46
          //   3213: iload_2
          //   3214: i2l
          //   3215: invokevirtual 254	org/apache/cordova/filetransfer/FileProgressResult:setTotal	(J)V
          //   3218: aload 18
          //   3220: astore 27
          //   3222: aload 16
          //   3224: astore 19
          //   3226: aload 15
          //   3228: astore 20
          //   3230: aload 18
          //   3232: astore 28
          //   3234: iload_2
          //   3235: istore_3
          //   3236: aload 16
          //   3238: astore 21
          //   3240: aload 15
          //   3242: astore 22
          //   3244: iload 6
          //   3246: istore 4
          //   3248: aload 18
          //   3250: astore 30
          //   3252: aload 16
          //   3254: astore 25
          //   3256: aload 15
          //   3258: astore 26
          //   3260: aload 18
          //   3262: astore 29
          //   3264: aload 16
          //   3266: astore 23
          //   3268: aload 15
          //   3270: astore 24
          //   3272: aload 18
          //   3274: astore 17
          //   3276: aload 16
          //   3278: astore 13
          //   3280: aload 15
          //   3282: astore 14
          //   3284: ldc -43
          //   3286: new 160	java/lang/StringBuilder
          //   3289: dup
          //   3290: invokespecial 161	java/lang/StringBuilder:<init>	()V
          //   3293: ldc_w 256
          //   3296: invokevirtual 195	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   3299: iload_2
          //   3300: invokevirtual 259	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
          //   3303: invokevirtual 229	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   3306: invokestatic 263	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
          //   3309: pop
          //   3310: aload 18
          //   3312: astore 27
          //   3314: aload 16
          //   3316: astore 19
          //   3318: aload 15
          //   3320: astore 20
          //   3322: aload 18
          //   3324: astore 28
          //   3326: iload_2
          //   3327: istore_3
          //   3328: aload 16
          //   3330: astore 21
          //   3332: aload 15
          //   3334: astore 22
          //   3336: iload 6
          //   3338: istore 4
          //   3340: aload 18
          //   3342: astore 30
          //   3344: aload 16
          //   3346: astore 25
          //   3348: aload 15
          //   3350: astore 26
          //   3352: aload 18
          //   3354: astore 29
          //   3356: aload 16
          //   3358: astore 23
          //   3360: aload 15
          //   3362: astore 24
          //   3364: aload 18
          //   3366: astore 17
          //   3368: aload 16
          //   3370: astore 13
          //   3372: aload 15
          //   3374: astore 14
          //   3376: aload_0
          //   3377: getfield 67	org/apache/cordova/filetransfer/FileTransfer$1:val$chunkedMode	Z
          //   3380: ifeq +636 -> 4016
          //   3383: aload 18
          //   3385: astore 27
          //   3387: aload 16
          //   3389: astore 19
          //   3391: aload 15
          //   3393: astore 20
          //   3395: aload 18
          //   3397: astore 28
          //   3399: iload_2
          //   3400: istore_3
          //   3401: aload 16
          //   3403: astore 21
          //   3405: aload 15
          //   3407: astore 22
          //   3409: iload 6
          //   3411: istore 4
          //   3413: aload 18
          //   3415: astore 30
          //   3417: aload 16
          //   3419: astore 25
          //   3421: aload 15
          //   3423: astore 26
          //   3425: aload 18
          //   3427: astore 29
          //   3429: aload 16
          //   3431: astore 23
          //   3433: aload 15
          //   3435: astore 24
          //   3437: aload 18
          //   3439: astore 17
          //   3441: aload 16
          //   3443: astore 13
          //   3445: aload 15
          //   3447: astore 14
          //   3449: getstatic 269	android/os/Build$VERSION:SDK_INT	I
          //   3452: bipush 8
          //   3454: if_icmplt +4357 -> 7811
          //   3457: aload 18
          //   3459: astore 27
          //   3461: aload 16
          //   3463: astore 19
          //   3465: aload 15
          //   3467: astore 20
          //   3469: aload 18
          //   3471: astore 28
          //   3473: iload_2
          //   3474: istore_3
          //   3475: aload 16
          //   3477: astore 21
          //   3479: aload 15
          //   3481: astore 22
          //   3483: iload 6
          //   3485: istore 4
          //   3487: aload 18
          //   3489: astore 30
          //   3491: aload 16
          //   3493: astore 25
          //   3495: aload 15
          //   3497: astore 26
          //   3499: aload 18
          //   3501: astore 29
          //   3503: aload 16
          //   3505: astore 23
          //   3507: aload 15
          //   3509: astore 24
          //   3511: aload 18
          //   3513: astore 17
          //   3515: aload 16
          //   3517: astore 13
          //   3519: aload 15
          //   3521: astore 14
          //   3523: aload_0
          //   3524: getfield 47	org/apache/cordova/filetransfer/FileTransfer$1:val$useHttps	Z
          //   3527: ifeq +489 -> 4016
          //   3530: goto +4281 -> 7811
          //   3533: iload_1
          //   3534: ifeq +492 -> 4026
          //   3537: aload 18
          //   3539: astore 27
          //   3541: aload 16
          //   3543: astore 19
          //   3545: aload 15
          //   3547: astore 20
          //   3549: aload 18
          //   3551: astore 28
          //   3553: iload_2
          //   3554: istore_3
          //   3555: aload 16
          //   3557: astore 21
          //   3559: aload 15
          //   3561: astore 22
          //   3563: iload 6
          //   3565: istore 4
          //   3567: aload 18
          //   3569: astore 30
          //   3571: aload 16
          //   3573: astore 25
          //   3575: aload 15
          //   3577: astore 26
          //   3579: aload 18
          //   3581: astore 29
          //   3583: aload 16
          //   3585: astore 23
          //   3587: aload 15
          //   3589: astore 24
          //   3591: aload 18
          //   3593: astore 17
          //   3595: aload 16
          //   3597: astore 13
          //   3599: aload 15
          //   3601: astore 14
          //   3603: aload 18
          //   3605: sipush 16384
          //   3608: invokevirtual 273	java/net/HttpURLConnection:setChunkedStreamingMode	(I)V
          //   3611: aload 18
          //   3613: astore 27
          //   3615: aload 16
          //   3617: astore 19
          //   3619: aload 15
          //   3621: astore 20
          //   3623: aload 18
          //   3625: astore 28
          //   3627: iload_2
          //   3628: istore_3
          //   3629: aload 16
          //   3631: astore 21
          //   3633: aload 15
          //   3635: astore 22
          //   3637: iload 6
          //   3639: istore 4
          //   3641: aload 18
          //   3643: astore 30
          //   3645: aload 16
          //   3647: astore 25
          //   3649: aload 15
          //   3651: astore 26
          //   3653: aload 18
          //   3655: astore 29
          //   3657: aload 16
          //   3659: astore 23
          //   3661: aload 15
          //   3663: astore 24
          //   3665: aload 18
          //   3667: astore 17
          //   3669: aload 16
          //   3671: astore 13
          //   3673: aload 15
          //   3675: astore 14
          //   3677: aload 18
          //   3679: ldc_w 275
          //   3682: ldc_w 277
          //   3685: invokevirtual 142	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
          //   3688: aload 18
          //   3690: astore 27
          //   3692: aload 16
          //   3694: astore 19
          //   3696: aload 15
          //   3698: astore 20
          //   3700: aload 18
          //   3702: astore 28
          //   3704: iload_2
          //   3705: istore_3
          //   3706: aload 16
          //   3708: astore 21
          //   3710: aload 15
          //   3712: astore 22
          //   3714: iload 6
          //   3716: istore 4
          //   3718: aload 18
          //   3720: astore 30
          //   3722: aload 16
          //   3724: astore 25
          //   3726: aload 15
          //   3728: astore 26
          //   3730: aload 18
          //   3732: astore 29
          //   3734: aload 16
          //   3736: astore 23
          //   3738: aload 15
          //   3740: astore 24
          //   3742: aload 18
          //   3744: astore 17
          //   3746: aload 16
          //   3748: astore 13
          //   3750: aload 15
          //   3752: astore 14
          //   3754: aload 18
          //   3756: invokevirtual 280	java/net/HttpURLConnection:connect	()V
          //   3759: aconst_null
          //   3760: astore 31
          //   3762: iload 5
          //   3764: istore_1
          //   3765: aload 18
          //   3767: invokevirtual 284	java/net/HttpURLConnection:getOutputStream	()Ljava/io/OutputStream;
          //   3770: astore 32
          //   3772: aload 32
          //   3774: astore 31
          //   3776: iload 5
          //   3778: istore_1
          //   3779: aload_0
          //   3780: getfield 41	org/apache/cordova/filetransfer/FileTransfer$1:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
          //   3783: astore 13
          //   3785: aload 32
          //   3787: astore 31
          //   3789: iload 5
          //   3791: istore_1
          //   3792: aload 13
          //   3794: monitorenter
          //   3795: aload_0
          //   3796: getfield 41	org/apache/cordova/filetransfer/FileTransfer$1:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
          //   3799: getfield 89	org/apache/cordova/filetransfer/FileTransfer$RequestContext:aborted	Z
          //   3802: ifeq +460 -> 4262
          //   3805: aload 13
          //   3807: monitorexit
          //   3808: aload 18
          //   3810: astore 27
          //   3812: aload 16
          //   3814: astore 19
          //   3816: aload 15
          //   3818: astore 20
          //   3820: aload 18
          //   3822: astore 28
          //   3824: iload_2
          //   3825: istore_3
          //   3826: aload 16
          //   3828: astore 21
          //   3830: aload 15
          //   3832: astore 22
          //   3834: iload 6
          //   3836: istore 4
          //   3838: aload 18
          //   3840: astore 30
          //   3842: aload 16
          //   3844: astore 25
          //   3846: aload 15
          //   3848: astore 26
          //   3850: aload 18
          //   3852: astore 29
          //   3854: aload 16
          //   3856: astore 23
          //   3858: aload 15
          //   3860: astore 24
          //   3862: aload 18
          //   3864: astore 17
          //   3866: aload 16
          //   3868: astore 13
          //   3870: aload 15
          //   3872: astore 14
          //   3874: aload 33
          //   3876: getfield 288	org/apache/cordova/CordovaResourceApi$OpenForReadResult:inputStream	Ljava/io/InputStream;
          //   3879: invokestatic 292	org/apache/cordova/filetransfer/FileTransfer:access$300	(Ljava/io/Closeable;)V
          //   3882: aload 18
          //   3884: astore 27
          //   3886: aload 16
          //   3888: astore 19
          //   3890: aload 15
          //   3892: astore 20
          //   3894: aload 18
          //   3896: astore 28
          //   3898: iload_2
          //   3899: istore_3
          //   3900: aload 16
          //   3902: astore 21
          //   3904: aload 15
          //   3906: astore 22
          //   3908: iload 6
          //   3910: istore 4
          //   3912: aload 18
          //   3914: astore 30
          //   3916: aload 16
          //   3918: astore 25
          //   3920: aload 15
          //   3922: astore 26
          //   3924: aload 18
          //   3926: astore 29
          //   3928: aload 16
          //   3930: astore 23
          //   3932: aload 15
          //   3934: astore 24
          //   3936: aload 18
          //   3938: astore 17
          //   3940: aload 16
          //   3942: astore 13
          //   3944: aload 15
          //   3946: astore 14
          //   3948: aload 32
          //   3950: invokestatic 292	org/apache/cordova/filetransfer/FileTransfer:access$300	(Ljava/io/Closeable;)V
          //   3953: invokestatic 296	org/apache/cordova/filetransfer/FileTransfer:access$600	()Ljava/util/HashMap;
          //   3956: astore 13
          //   3958: aload 13
          //   3960: monitorenter
          //   3961: invokestatic 296	org/apache/cordova/filetransfer/FileTransfer:access$600	()Ljava/util/HashMap;
          //   3964: aload_0
          //   3965: getfield 71	org/apache/cordova/filetransfer/FileTransfer$1:val$objectId	Ljava/lang/String;
          //   3968: invokevirtual 302	java/util/HashMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
          //   3971: pop
          //   3972: aload 13
          //   3974: monitorexit
          //   3975: aload 18
          //   3977: ifnull -3967 -> 10
          //   3980: aload_0
          //   3981: getfield 49	org/apache/cordova/filetransfer/FileTransfer$1:val$trustEveryone	Z
          //   3984: ifeq -3974 -> 10
          //   3987: aload_0
          //   3988: getfield 47	org/apache/cordova/filetransfer/FileTransfer$1:val$useHttps	Z
          //   3991: ifeq -3981 -> 10
          //   3994: aload 18
          //   3996: checkcast 103	javax/net/ssl/HttpsURLConnection
          //   3999: astore 13
          //   4001: aload 13
          //   4003: aload 16
          //   4005: invokevirtual 118	javax/net/ssl/HttpsURLConnection:setHostnameVerifier	(Ljavax/net/ssl/HostnameVerifier;)V
          //   4008: aload 13
          //   4010: aload 15
          //   4012: invokevirtual 306	javax/net/ssl/HttpsURLConnection:setSSLSocketFactory	(Ljavax/net/ssl/SSLSocketFactory;)V
          //   4015: return
          //   4016: iconst_0
          //   4017: istore_1
          //   4018: goto +3795 -> 7813
          //   4021: iconst_0
          //   4022: istore_1
          //   4023: goto -490 -> 3533
          //   4026: aload 18
          //   4028: astore 27
          //   4030: aload 16
          //   4032: astore 19
          //   4034: aload 15
          //   4036: astore 20
          //   4038: aload 18
          //   4040: astore 28
          //   4042: iload_2
          //   4043: istore_3
          //   4044: aload 16
          //   4046: astore 21
          //   4048: aload 15
          //   4050: astore 22
          //   4052: iload 6
          //   4054: istore 4
          //   4056: aload 18
          //   4058: astore 30
          //   4060: aload 16
          //   4062: astore 25
          //   4064: aload 15
          //   4066: astore 26
          //   4068: aload 18
          //   4070: astore 29
          //   4072: aload 16
          //   4074: astore 23
          //   4076: aload 15
          //   4078: astore 24
          //   4080: aload 18
          //   4082: astore 17
          //   4084: aload 16
          //   4086: astore 13
          //   4088: aload 15
          //   4090: astore 14
          //   4092: aload 18
          //   4094: iload_2
          //   4095: invokevirtual 309	java/net/HttpURLConnection:setFixedLengthStreamingMode	(I)V
          //   4098: goto -410 -> 3688
          //   4101: astore 15
          //   4103: aload 27
          //   4105: astore 17
          //   4107: aload 19
          //   4109: astore 13
          //   4111: aload 20
          //   4113: astore 14
          //   4115: getstatic 312	org/apache/cordova/filetransfer/FileTransfer:FILE_NOT_FOUND_ERR	I
          //   4118: aload_0
          //   4119: getfield 69	org/apache/cordova/filetransfer/FileTransfer$1:val$source	Ljava/lang/String;
          //   4122: aload_0
          //   4123: getfield 53	org/apache/cordova/filetransfer/FileTransfer$1:val$target	Ljava/lang/String;
          //   4126: aload 27
          //   4128: aload 15
          //   4130: invokestatic 316	org/apache/cordova/filetransfer/FileTransfer:access$500	(ILjava/lang/String;Ljava/lang/String;Ljava/net/URLConnection;Ljava/lang/Throwable;)Lorg/json/JSONObject;
          //   4133: astore 16
          //   4135: aload 27
          //   4137: astore 17
          //   4139: aload 19
          //   4141: astore 13
          //   4143: aload 20
          //   4145: astore 14
          //   4147: ldc -43
          //   4149: aload 16
          //   4151: invokevirtual 317	org/json/JSONObject:toString	()Ljava/lang/String;
          //   4154: aload 15
          //   4156: invokestatic 222	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
          //   4159: pop
          //   4160: aload 27
          //   4162: astore 17
          //   4164: aload 19
          //   4166: astore 13
          //   4168: aload 20
          //   4170: astore 14
          //   4172: aload_0
          //   4173: getfield 41	org/apache/cordova/filetransfer/FileTransfer$1:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
          //   4176: new 319	org/apache/cordova/PluginResult
          //   4179: dup
          //   4180: getstatic 325	org/apache/cordova/PluginResult$Status:IO_EXCEPTION	Lorg/apache/cordova/PluginResult$Status;
          //   4183: aload 16
          //   4185: invokespecial 328	org/apache/cordova/PluginResult:<init>	(Lorg/apache/cordova/PluginResult$Status;Lorg/json/JSONObject;)V
          //   4188: invokevirtual 332	org/apache/cordova/filetransfer/FileTransfer$RequestContext:sendPluginResult	(Lorg/apache/cordova/PluginResult;)V
          //   4191: invokestatic 296	org/apache/cordova/filetransfer/FileTransfer:access$600	()Ljava/util/HashMap;
          //   4194: astore 13
          //   4196: aload 13
          //   4198: monitorenter
          //   4199: invokestatic 296	org/apache/cordova/filetransfer/FileTransfer:access$600	()Ljava/util/HashMap;
          //   4202: aload_0
          //   4203: getfield 71	org/apache/cordova/filetransfer/FileTransfer$1:val$objectId	Ljava/lang/String;
          //   4206: invokevirtual 302	java/util/HashMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
          //   4209: pop
          //   4210: aload 13
          //   4212: monitorexit
          //   4213: aload 27
          //   4215: ifnull -4205 -> 10
          //   4218: aload_0
          //   4219: getfield 49	org/apache/cordova/filetransfer/FileTransfer$1:val$trustEveryone	Z
          //   4222: ifeq -4212 -> 10
          //   4225: aload_0
          //   4226: getfield 47	org/apache/cordova/filetransfer/FileTransfer$1:val$useHttps	Z
          //   4229: ifeq -4219 -> 10
          //   4232: aload 27
          //   4234: checkcast 103	javax/net/ssl/HttpsURLConnection
          //   4237: astore 13
          //   4239: aload 13
          //   4241: aload 19
          //   4243: invokevirtual 118	javax/net/ssl/HttpsURLConnection:setHostnameVerifier	(Ljavax/net/ssl/HostnameVerifier;)V
          //   4246: aload 13
          //   4248: aload 20
          //   4250: invokevirtual 306	javax/net/ssl/HttpsURLConnection:setSSLSocketFactory	(Ljavax/net/ssl/SSLSocketFactory;)V
          //   4253: return
          //   4254: astore 14
          //   4256: aload 13
          //   4258: monitorexit
          //   4259: aload 14
          //   4261: athrow
          //   4262: aload_0
          //   4263: getfield 41	org/apache/cordova/filetransfer/FileTransfer$1:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
          //   4266: aload 18
          //   4268: putfield 336	org/apache/cordova/filetransfer/FileTransfer$RequestContext:connection	Ljava/net/HttpURLConnection;
          //   4271: aload 13
          //   4273: monitorexit
          //   4274: aload 32
          //   4276: astore 31
          //   4278: iload 5
          //   4280: istore_1
          //   4281: aload 32
          //   4283: aload 35
          //   4285: invokevirtual 342	java/io/OutputStream:write	([B)V
          //   4288: aload 32
          //   4290: astore 31
          //   4292: iload 5
          //   4294: istore_1
          //   4295: iconst_0
          //   4296: aload 35
          //   4298: arraylength
          //   4299: iadd
          //   4300: istore_3
          //   4301: aload 32
          //   4303: astore 31
          //   4305: iload_3
          //   4306: istore_1
          //   4307: aload 33
          //   4309: getfield 288	org/apache/cordova/CordovaResourceApi$OpenForReadResult:inputStream	Ljava/io/InputStream;
          //   4312: invokevirtual 348	java/io/InputStream:available	()I
          //   4315: sipush 16384
          //   4318: invokestatic 354	java/lang/Math:min	(II)I
          //   4321: istore 4
          //   4323: aload 32
          //   4325: astore 31
          //   4327: iload_3
          //   4328: istore_1
          //   4329: iload 4
          //   4331: newarray <illegal type>
          //   4333: astore 13
          //   4335: aload 32
          //   4337: astore 31
          //   4339: iload_3
          //   4340: istore_1
          //   4341: aload 33
          //   4343: getfield 288	org/apache/cordova/CordovaResourceApi$OpenForReadResult:inputStream	Ljava/io/InputStream;
          //   4346: aload 13
          //   4348: iconst_0
          //   4349: iload 4
          //   4351: invokevirtual 358	java/io/InputStream:read	([BII)I
          //   4354: istore 4
          //   4356: lconst_0
          //   4357: lstore 9
          //   4359: iload 4
          //   4361: ifle +654 -> 5015
          //   4364: aload 32
          //   4366: astore 31
          //   4368: iload_3
          //   4369: istore_1
          //   4370: aload 45
          //   4372: iload_3
          //   4373: i2l
          //   4374: invokevirtual 361	org/apache/cordova/filetransfer/FileUploadResult:setBytesSent	(J)V
          //   4377: aload 32
          //   4379: astore 31
          //   4381: iload_3
          //   4382: istore_1
          //   4383: aload 32
          //   4385: aload 13
          //   4387: iconst_0
          //   4388: iload 4
          //   4390: invokevirtual 364	java/io/OutputStream:write	([BII)V
          //   4393: iload_3
          //   4394: iload 4
          //   4396: iadd
          //   4397: istore_3
          //   4398: lload 9
          //   4400: lstore 11
          //   4402: iload_3
          //   4403: i2l
          //   4404: ldc2_w 365
          //   4407: lload 9
          //   4409: ladd
          //   4410: lcmp
          //   4411: ifle +55 -> 4466
          //   4414: iload_3
          //   4415: i2l
          //   4416: lstore 11
          //   4418: aload 32
          //   4420: astore 31
          //   4422: iload_3
          //   4423: istore_1
          //   4424: ldc -43
          //   4426: new 160	java/lang/StringBuilder
          //   4429: dup
          //   4430: invokespecial 161	java/lang/StringBuilder:<init>	()V
          //   4433: ldc_w 368
          //   4436: invokevirtual 195	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   4439: iload_3
          //   4440: invokevirtual 259	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
          //   4443: ldc_w 370
          //   4446: invokevirtual 195	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   4449: iload_2
          //   4450: invokevirtual 259	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
          //   4453: ldc_w 372
          //   4456: invokevirtual 195	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   4459: invokevirtual 229	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   4462: invokestatic 263	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
          //   4465: pop
          //   4466: aload 32
          //   4468: astore 31
          //   4470: iload_3
          //   4471: istore_1
          //   4472: aload 33
          //   4474: getfield 288	org/apache/cordova/CordovaResourceApi$OpenForReadResult:inputStream	Ljava/io/InputStream;
          //   4477: invokevirtual 348	java/io/InputStream:available	()I
          //   4480: sipush 16384
          //   4483: invokestatic 354	java/lang/Math:min	(II)I
          //   4486: istore 4
          //   4488: aload 32
          //   4490: astore 31
          //   4492: iload_3
          //   4493: istore_1
          //   4494: aload 33
          //   4496: getfield 288	org/apache/cordova/CordovaResourceApi$OpenForReadResult:inputStream	Ljava/io/InputStream;
          //   4499: aload 13
          //   4501: iconst_0
          //   4502: iload 4
          //   4504: invokevirtual 358	java/io/InputStream:read	([BII)I
          //   4507: istore 4
          //   4509: aload 32
          //   4511: astore 31
          //   4513: iload_3
          //   4514: istore_1
          //   4515: aload 46
          //   4517: iload_3
          //   4518: i2l
          //   4519: invokevirtual 375	org/apache/cordova/filetransfer/FileProgressResult:setLoaded	(J)V
          //   4522: aload 32
          //   4524: astore 31
          //   4526: iload_3
          //   4527: istore_1
          //   4528: new 319	org/apache/cordova/PluginResult
          //   4531: dup
          //   4532: getstatic 378	org/apache/cordova/PluginResult$Status:OK	Lorg/apache/cordova/PluginResult$Status;
          //   4535: aload 46
          //   4537: invokevirtual 382	org/apache/cordova/filetransfer/FileProgressResult:toJSONObject	()Lorg/json/JSONObject;
          //   4540: invokespecial 328	org/apache/cordova/PluginResult:<init>	(Lorg/apache/cordova/PluginResult$Status;Lorg/json/JSONObject;)V
          //   4543: astore 14
          //   4545: aload 32
          //   4547: astore 31
          //   4549: iload_3
          //   4550: istore_1
          //   4551: aload 14
          //   4553: iconst_1
          //   4554: invokevirtual 385	org/apache/cordova/PluginResult:setKeepCallback	(Z)V
          //   4557: aload 32
          //   4559: astore 31
          //   4561: iload_3
          //   4562: istore_1
          //   4563: aload_0
          //   4564: getfield 41	org/apache/cordova/filetransfer/FileTransfer$1:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
          //   4567: aload 14
          //   4569: invokevirtual 332	org/apache/cordova/filetransfer/FileTransfer$RequestContext:sendPluginResult	(Lorg/apache/cordova/PluginResult;)V
          //   4572: lload 11
          //   4574: lstore 9
          //   4576: goto -217 -> 4359
          //   4579: astore 32
          //   4581: aload 18
          //   4583: astore 27
          //   4585: aload 16
          //   4587: astore 19
          //   4589: aload 15
          //   4591: astore 20
          //   4593: aload 18
          //   4595: astore 28
          //   4597: iload_2
          //   4598: istore_3
          //   4599: aload 16
          //   4601: astore 21
          //   4603: aload 15
          //   4605: astore 22
          //   4607: iload_1
          //   4608: istore 4
          //   4610: aload 18
          //   4612: astore 30
          //   4614: aload 16
          //   4616: astore 25
          //   4618: aload 15
          //   4620: astore 26
          //   4622: aload 18
          //   4624: astore 29
          //   4626: aload 16
          //   4628: astore 23
          //   4630: aload 15
          //   4632: astore 24
          //   4634: aload 18
          //   4636: astore 17
          //   4638: aload 16
          //   4640: astore 13
          //   4642: aload 15
          //   4644: astore 14
          //   4646: aload 33
          //   4648: getfield 288	org/apache/cordova/CordovaResourceApi$OpenForReadResult:inputStream	Ljava/io/InputStream;
          //   4651: invokestatic 292	org/apache/cordova/filetransfer/FileTransfer:access$300	(Ljava/io/Closeable;)V
          //   4654: aload 18
          //   4656: astore 27
          //   4658: aload 16
          //   4660: astore 19
          //   4662: aload 15
          //   4664: astore 20
          //   4666: aload 18
          //   4668: astore 28
          //   4670: iload_2
          //   4671: istore_3
          //   4672: aload 16
          //   4674: astore 21
          //   4676: aload 15
          //   4678: astore 22
          //   4680: iload_1
          //   4681: istore 4
          //   4683: aload 18
          //   4685: astore 30
          //   4687: aload 16
          //   4689: astore 25
          //   4691: aload 15
          //   4693: astore 26
          //   4695: aload 18
          //   4697: astore 29
          //   4699: aload 16
          //   4701: astore 23
          //   4703: aload 15
          //   4705: astore 24
          //   4707: aload 18
          //   4709: astore 17
          //   4711: aload 16
          //   4713: astore 13
          //   4715: aload 15
          //   4717: astore 14
          //   4719: aload 31
          //   4721: invokestatic 292	org/apache/cordova/filetransfer/FileTransfer:access$300	(Ljava/io/Closeable;)V
          //   4724: aload 18
          //   4726: astore 27
          //   4728: aload 16
          //   4730: astore 19
          //   4732: aload 15
          //   4734: astore 20
          //   4736: aload 18
          //   4738: astore 28
          //   4740: iload_2
          //   4741: istore_3
          //   4742: aload 16
          //   4744: astore 21
          //   4746: aload 15
          //   4748: astore 22
          //   4750: iload_1
          //   4751: istore 4
          //   4753: aload 18
          //   4755: astore 30
          //   4757: aload 16
          //   4759: astore 25
          //   4761: aload 15
          //   4763: astore 26
          //   4765: aload 18
          //   4767: astore 29
          //   4769: aload 16
          //   4771: astore 23
          //   4773: aload 15
          //   4775: astore 24
          //   4777: aload 18
          //   4779: astore 17
          //   4781: aload 16
          //   4783: astore 13
          //   4785: aload 15
          //   4787: astore 14
          //   4789: aload 32
          //   4791: athrow
          //   4792: astore 15
          //   4794: aload 28
          //   4796: astore 17
          //   4798: aload 21
          //   4800: astore 13
          //   4802: aload 22
          //   4804: astore 14
          //   4806: getstatic 388	org/apache/cordova/filetransfer/FileTransfer:CONNECTION_ERR	I
          //   4809: aload_0
          //   4810: getfield 69	org/apache/cordova/filetransfer/FileTransfer$1:val$source	Ljava/lang/String;
          //   4813: aload_0
          //   4814: getfield 53	org/apache/cordova/filetransfer/FileTransfer$1:val$target	Ljava/lang/String;
          //   4817: aload 28
          //   4819: aload 15
          //   4821: invokestatic 316	org/apache/cordova/filetransfer/FileTransfer:access$500	(ILjava/lang/String;Ljava/lang/String;Ljava/net/URLConnection;Ljava/lang/Throwable;)Lorg/json/JSONObject;
          //   4824: astore 16
          //   4826: aload 28
          //   4828: astore 17
          //   4830: aload 21
          //   4832: astore 13
          //   4834: aload 22
          //   4836: astore 14
          //   4838: ldc -43
          //   4840: aload 16
          //   4842: invokevirtual 317	org/json/JSONObject:toString	()Ljava/lang/String;
          //   4845: aload 15
          //   4847: invokestatic 222	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
          //   4850: pop
          //   4851: aload 28
          //   4853: astore 17
          //   4855: aload 21
          //   4857: astore 13
          //   4859: aload 22
          //   4861: astore 14
          //   4863: ldc -43
          //   4865: new 160	java/lang/StringBuilder
          //   4868: dup
          //   4869: invokespecial 161	java/lang/StringBuilder:<init>	()V
          //   4872: ldc_w 390
          //   4875: invokevirtual 195	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   4878: iload 4
          //   4880: invokevirtual 259	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
          //   4883: ldc_w 370
          //   4886: invokevirtual 195	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   4889: iload_3
          //   4890: invokevirtual 259	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
          //   4893: ldc_w 392
          //   4896: invokevirtual 195	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   4899: invokevirtual 229	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   4902: invokestatic 394	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
          //   4905: pop
          //   4906: aload 28
          //   4908: astore 17
          //   4910: aload 21
          //   4912: astore 13
          //   4914: aload 22
          //   4916: astore 14
          //   4918: aload_0
          //   4919: getfield 41	org/apache/cordova/filetransfer/FileTransfer$1:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
          //   4922: new 319	org/apache/cordova/PluginResult
          //   4925: dup
          //   4926: getstatic 325	org/apache/cordova/PluginResult$Status:IO_EXCEPTION	Lorg/apache/cordova/PluginResult$Status;
          //   4929: aload 16
          //   4931: invokespecial 328	org/apache/cordova/PluginResult:<init>	(Lorg/apache/cordova/PluginResult$Status;Lorg/json/JSONObject;)V
          //   4934: invokevirtual 332	org/apache/cordova/filetransfer/FileTransfer$RequestContext:sendPluginResult	(Lorg/apache/cordova/PluginResult;)V
          //   4937: invokestatic 296	org/apache/cordova/filetransfer/FileTransfer:access$600	()Ljava/util/HashMap;
          //   4940: astore 13
          //   4942: aload 13
          //   4944: monitorenter
          //   4945: invokestatic 296	org/apache/cordova/filetransfer/FileTransfer:access$600	()Ljava/util/HashMap;
          //   4948: aload_0
          //   4949: getfield 71	org/apache/cordova/filetransfer/FileTransfer$1:val$objectId	Ljava/lang/String;
          //   4952: invokevirtual 302	java/util/HashMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
          //   4955: pop
          //   4956: aload 13
          //   4958: monitorexit
          //   4959: aload 28
          //   4961: ifnull -4951 -> 10
          //   4964: aload_0
          //   4965: getfield 49	org/apache/cordova/filetransfer/FileTransfer$1:val$trustEveryone	Z
          //   4968: ifeq -4958 -> 10
          //   4971: aload_0
          //   4972: getfield 47	org/apache/cordova/filetransfer/FileTransfer$1:val$useHttps	Z
          //   4975: ifeq -4965 -> 10
          //   4978: aload 28
          //   4980: checkcast 103	javax/net/ssl/HttpsURLConnection
          //   4983: astore 13
          //   4985: aload 13
          //   4987: aload 21
          //   4989: invokevirtual 118	javax/net/ssl/HttpsURLConnection:setHostnameVerifier	(Ljavax/net/ssl/HostnameVerifier;)V
          //   4992: aload 13
          //   4994: aload 22
          //   4996: invokevirtual 306	javax/net/ssl/HttpsURLConnection:setSSLSocketFactory	(Ljavax/net/ssl/SSLSocketFactory;)V
          //   4999: return
          //   5000: astore 14
          //   5002: aload 13
          //   5004: monitorexit
          //   5005: aload 32
          //   5007: astore 31
          //   5009: iload 5
          //   5011: istore_1
          //   5012: aload 14
          //   5014: athrow
          //   5015: aload 32
          //   5017: astore 31
          //   5019: iload_3
          //   5020: istore_1
          //   5021: aload 32
          //   5023: aload 34
          //   5025: invokevirtual 342	java/io/OutputStream:write	([B)V
          //   5028: aload 32
          //   5030: astore 31
          //   5032: iload_3
          //   5033: istore_1
          //   5034: iload_3
          //   5035: aload 34
          //   5037: arraylength
          //   5038: iadd
          //   5039: istore 5
          //   5041: aload 32
          //   5043: astore 31
          //   5045: iload 5
          //   5047: istore_1
          //   5048: aload 32
          //   5050: invokevirtual 397	java/io/OutputStream:flush	()V
          //   5053: aload 18
          //   5055: astore 27
          //   5057: aload 16
          //   5059: astore 19
          //   5061: aload 15
          //   5063: astore 20
          //   5065: aload 18
          //   5067: astore 28
          //   5069: iload_2
          //   5070: istore_3
          //   5071: aload 16
          //   5073: astore 21
          //   5075: aload 15
          //   5077: astore 22
          //   5079: iload 5
          //   5081: istore 4
          //   5083: aload 18
          //   5085: astore 30
          //   5087: aload 16
          //   5089: astore 25
          //   5091: aload 15
          //   5093: astore 26
          //   5095: aload 18
          //   5097: astore 29
          //   5099: aload 16
          //   5101: astore 23
          //   5103: aload 15
          //   5105: astore 24
          //   5107: aload 18
          //   5109: astore 17
          //   5111: aload 16
          //   5113: astore 13
          //   5115: aload 15
          //   5117: astore 14
          //   5119: aload 33
          //   5121: getfield 288	org/apache/cordova/CordovaResourceApi$OpenForReadResult:inputStream	Ljava/io/InputStream;
          //   5124: invokestatic 292	org/apache/cordova/filetransfer/FileTransfer:access$300	(Ljava/io/Closeable;)V
          //   5127: aload 18
          //   5129: astore 27
          //   5131: aload 16
          //   5133: astore 19
          //   5135: aload 15
          //   5137: astore 20
          //   5139: aload 18
          //   5141: astore 28
          //   5143: iload_2
          //   5144: istore_3
          //   5145: aload 16
          //   5147: astore 21
          //   5149: aload 15
          //   5151: astore 22
          //   5153: iload 5
          //   5155: istore 4
          //   5157: aload 18
          //   5159: astore 30
          //   5161: aload 16
          //   5163: astore 25
          //   5165: aload 15
          //   5167: astore 26
          //   5169: aload 18
          //   5171: astore 29
          //   5173: aload 16
          //   5175: astore 23
          //   5177: aload 15
          //   5179: astore 24
          //   5181: aload 18
          //   5183: astore 17
          //   5185: aload 16
          //   5187: astore 13
          //   5189: aload 15
          //   5191: astore 14
          //   5193: aload 32
          //   5195: invokestatic 292	org/apache/cordova/filetransfer/FileTransfer:access$300	(Ljava/io/Closeable;)V
          //   5198: aload 18
          //   5200: astore 27
          //   5202: aload 16
          //   5204: astore 19
          //   5206: aload 15
          //   5208: astore 20
          //   5210: aload 18
          //   5212: astore 28
          //   5214: iload_2
          //   5215: istore_3
          //   5216: aload 16
          //   5218: astore 21
          //   5220: aload 15
          //   5222: astore 22
          //   5224: iload 5
          //   5226: istore 4
          //   5228: aload 18
          //   5230: astore 30
          //   5232: aload 16
          //   5234: astore 25
          //   5236: aload 15
          //   5238: astore 26
          //   5240: aload 18
          //   5242: astore 29
          //   5244: aload 16
          //   5246: astore 23
          //   5248: aload 15
          //   5250: astore 24
          //   5252: aload 18
          //   5254: astore 17
          //   5256: aload 16
          //   5258: astore 13
          //   5260: aload 15
          //   5262: astore 14
          //   5264: aload_0
          //   5265: getfield 41	org/apache/cordova/filetransfer/FileTransfer$1:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
          //   5268: astore 31
          //   5270: aload 18
          //   5272: astore 27
          //   5274: aload 16
          //   5276: astore 19
          //   5278: aload 15
          //   5280: astore 20
          //   5282: aload 18
          //   5284: astore 28
          //   5286: iload_2
          //   5287: istore_3
          //   5288: aload 16
          //   5290: astore 21
          //   5292: aload 15
          //   5294: astore 22
          //   5296: iload 5
          //   5298: istore 4
          //   5300: aload 18
          //   5302: astore 30
          //   5304: aload 16
          //   5306: astore 25
          //   5308: aload 15
          //   5310: astore 26
          //   5312: aload 18
          //   5314: astore 29
          //   5316: aload 16
          //   5318: astore 23
          //   5320: aload 15
          //   5322: astore 24
          //   5324: aload 18
          //   5326: astore 17
          //   5328: aload 16
          //   5330: astore 13
          //   5332: aload 15
          //   5334: astore 14
          //   5336: aload 31
          //   5338: monitorenter
          //   5339: aload_0
          //   5340: getfield 41	org/apache/cordova/filetransfer/FileTransfer$1:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
          //   5343: aconst_null
          //   5344: putfield 336	org/apache/cordova/filetransfer/FileTransfer$RequestContext:connection	Ljava/net/HttpURLConnection;
          //   5347: aload 31
          //   5349: monitorexit
          //   5350: aload 18
          //   5352: astore 27
          //   5354: aload 16
          //   5356: astore 19
          //   5358: aload 15
          //   5360: astore 20
          //   5362: aload 18
          //   5364: astore 28
          //   5366: iload_2
          //   5367: istore_3
          //   5368: aload 16
          //   5370: astore 21
          //   5372: aload 15
          //   5374: astore 22
          //   5376: iload 5
          //   5378: istore 4
          //   5380: aload 18
          //   5382: astore 30
          //   5384: aload 16
          //   5386: astore 25
          //   5388: aload 15
          //   5390: astore 26
          //   5392: aload 18
          //   5394: astore 29
          //   5396: aload 16
          //   5398: astore 23
          //   5400: aload 15
          //   5402: astore 24
          //   5404: aload 18
          //   5406: astore 17
          //   5408: aload 16
          //   5410: astore 13
          //   5412: aload 15
          //   5414: astore 14
          //   5416: ldc -43
          //   5418: new 160	java/lang/StringBuilder
          //   5421: dup
          //   5422: invokespecial 161	java/lang/StringBuilder:<init>	()V
          //   5425: ldc_w 399
          //   5428: invokevirtual 195	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   5431: iload 5
          //   5433: invokevirtual 259	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
          //   5436: ldc_w 370
          //   5439: invokevirtual 195	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   5442: iload_2
          //   5443: invokevirtual 259	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
          //   5446: invokevirtual 229	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   5449: invokestatic 263	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
          //   5452: pop
          //   5453: aload 18
          //   5455: astore 27
          //   5457: aload 16
          //   5459: astore 19
          //   5461: aload 15
          //   5463: astore 20
          //   5465: aload 18
          //   5467: astore 28
          //   5469: iload_2
          //   5470: istore_3
          //   5471: aload 16
          //   5473: astore 21
          //   5475: aload 15
          //   5477: astore 22
          //   5479: iload 5
          //   5481: istore 4
          //   5483: aload 18
          //   5485: astore 30
          //   5487: aload 16
          //   5489: astore 25
          //   5491: aload 15
          //   5493: astore 26
          //   5495: aload 18
          //   5497: astore 29
          //   5499: aload 16
          //   5501: astore 23
          //   5503: aload 15
          //   5505: astore 24
          //   5507: aload 18
          //   5509: astore 17
          //   5511: aload 16
          //   5513: astore 13
          //   5515: aload 15
          //   5517: astore 14
          //   5519: aload 18
          //   5521: invokevirtual 402	java/net/HttpURLConnection:getResponseCode	()I
          //   5524: istore_1
          //   5525: aload 18
          //   5527: astore 27
          //   5529: aload 16
          //   5531: astore 19
          //   5533: aload 15
          //   5535: astore 20
          //   5537: aload 18
          //   5539: astore 28
          //   5541: iload_2
          //   5542: istore_3
          //   5543: aload 16
          //   5545: astore 21
          //   5547: aload 15
          //   5549: astore 22
          //   5551: iload 5
          //   5553: istore 4
          //   5555: aload 18
          //   5557: astore 30
          //   5559: aload 16
          //   5561: astore 25
          //   5563: aload 15
          //   5565: astore 26
          //   5567: aload 18
          //   5569: astore 29
          //   5571: aload 16
          //   5573: astore 23
          //   5575: aload 15
          //   5577: astore 24
          //   5579: aload 18
          //   5581: astore 17
          //   5583: aload 16
          //   5585: astore 13
          //   5587: aload 15
          //   5589: astore 14
          //   5591: ldc -43
          //   5593: new 160	java/lang/StringBuilder
          //   5596: dup
          //   5597: invokespecial 161	java/lang/StringBuilder:<init>	()V
          //   5600: ldc_w 404
          //   5603: invokevirtual 195	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   5606: iload_1
          //   5607: invokevirtual 259	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
          //   5610: invokevirtual 229	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   5613: invokestatic 263	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
          //   5616: pop
          //   5617: aload 18
          //   5619: astore 27
          //   5621: aload 16
          //   5623: astore 19
          //   5625: aload 15
          //   5627: astore 20
          //   5629: aload 18
          //   5631: astore 28
          //   5633: iload_2
          //   5634: istore_3
          //   5635: aload 16
          //   5637: astore 21
          //   5639: aload 15
          //   5641: astore 22
          //   5643: iload 5
          //   5645: istore 4
          //   5647: aload 18
          //   5649: astore 30
          //   5651: aload 16
          //   5653: astore 25
          //   5655: aload 15
          //   5657: astore 26
          //   5659: aload 18
          //   5661: astore 29
          //   5663: aload 16
          //   5665: astore 23
          //   5667: aload 15
          //   5669: astore 24
          //   5671: aload 18
          //   5673: astore 17
          //   5675: aload 16
          //   5677: astore 13
          //   5679: aload 15
          //   5681: astore 14
          //   5683: ldc -43
          //   5685: new 160	java/lang/StringBuilder
          //   5688: dup
          //   5689: invokespecial 161	java/lang/StringBuilder:<init>	()V
          //   5692: ldc_w 406
          //   5695: invokevirtual 195	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   5698: aload 18
          //   5700: invokevirtual 410	java/net/HttpURLConnection:getHeaderFields	()Ljava/util/Map;
          //   5703: invokevirtual 413	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
          //   5706: invokevirtual 229	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   5709: invokestatic 263	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
          //   5712: pop
          //   5713: aconst_null
          //   5714: astore 31
          //   5716: aload 18
          //   5718: invokestatic 417	org/apache/cordova/filetransfer/FileTransfer:access$400	(Ljava/net/URLConnection;)Lorg/apache/cordova/filetransfer/FileTransfer$TrackingInputStream;
          //   5721: astore 32
          //   5723: aload 32
          //   5725: astore 31
          //   5727: aload_0
          //   5728: getfield 41	org/apache/cordova/filetransfer/FileTransfer$1:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
          //   5731: astore 13
          //   5733: aload 32
          //   5735: astore 31
          //   5737: aload 13
          //   5739: monitorenter
          //   5740: aload_0
          //   5741: getfield 41	org/apache/cordova/filetransfer/FileTransfer$1:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
          //   5744: getfield 89	org/apache/cordova/filetransfer/FileTransfer$RequestContext:aborted	Z
          //   5747: ifeq +720 -> 6467
          //   5750: aload 13
          //   5752: monitorexit
          //   5753: aload 18
          //   5755: astore 27
          //   5757: aload 16
          //   5759: astore 19
          //   5761: aload 15
          //   5763: astore 20
          //   5765: aload 18
          //   5767: astore 28
          //   5769: iload_2
          //   5770: istore_3
          //   5771: aload 16
          //   5773: astore 21
          //   5775: aload 15
          //   5777: astore 22
          //   5779: iload 5
          //   5781: istore 4
          //   5783: aload 18
          //   5785: astore 30
          //   5787: aload 16
          //   5789: astore 25
          //   5791: aload 15
          //   5793: astore 26
          //   5795: aload 18
          //   5797: astore 29
          //   5799: aload 16
          //   5801: astore 23
          //   5803: aload 15
          //   5805: astore 24
          //   5807: aload 18
          //   5809: astore 17
          //   5811: aload 16
          //   5813: astore 13
          //   5815: aload 15
          //   5817: astore 14
          //   5819: aload_0
          //   5820: getfield 41	org/apache/cordova/filetransfer/FileTransfer$1:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
          //   5823: astore 31
          //   5825: aload 18
          //   5827: astore 27
          //   5829: aload 16
          //   5831: astore 19
          //   5833: aload 15
          //   5835: astore 20
          //   5837: aload 18
          //   5839: astore 28
          //   5841: iload_2
          //   5842: istore_3
          //   5843: aload 16
          //   5845: astore 21
          //   5847: aload 15
          //   5849: astore 22
          //   5851: iload 5
          //   5853: istore 4
          //   5855: aload 18
          //   5857: astore 30
          //   5859: aload 16
          //   5861: astore 25
          //   5863: aload 15
          //   5865: astore 26
          //   5867: aload 18
          //   5869: astore 29
          //   5871: aload 16
          //   5873: astore 23
          //   5875: aload 15
          //   5877: astore 24
          //   5879: aload 18
          //   5881: astore 17
          //   5883: aload 16
          //   5885: astore 13
          //   5887: aload 15
          //   5889: astore 14
          //   5891: aload 31
          //   5893: monitorenter
          //   5894: aload_0
          //   5895: getfield 41	org/apache/cordova/filetransfer/FileTransfer$1:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
          //   5898: aconst_null
          //   5899: putfield 336	org/apache/cordova/filetransfer/FileTransfer$RequestContext:connection	Ljava/net/HttpURLConnection;
          //   5902: aload 31
          //   5904: monitorexit
          //   5905: aload 18
          //   5907: astore 27
          //   5909: aload 16
          //   5911: astore 19
          //   5913: aload 15
          //   5915: astore 20
          //   5917: aload 18
          //   5919: astore 28
          //   5921: iload_2
          //   5922: istore_3
          //   5923: aload 16
          //   5925: astore 21
          //   5927: aload 15
          //   5929: astore 22
          //   5931: iload 5
          //   5933: istore 4
          //   5935: aload 18
          //   5937: astore 30
          //   5939: aload 16
          //   5941: astore 25
          //   5943: aload 15
          //   5945: astore 26
          //   5947: aload 18
          //   5949: astore 29
          //   5951: aload 16
          //   5953: astore 23
          //   5955: aload 15
          //   5957: astore 24
          //   5959: aload 18
          //   5961: astore 17
          //   5963: aload 16
          //   5965: astore 13
          //   5967: aload 15
          //   5969: astore 14
          //   5971: aload 32
          //   5973: invokestatic 292	org/apache/cordova/filetransfer/FileTransfer:access$300	(Ljava/io/Closeable;)V
          //   5976: invokestatic 296	org/apache/cordova/filetransfer/FileTransfer:access$600	()Ljava/util/HashMap;
          //   5979: astore 13
          //   5981: aload 13
          //   5983: monitorenter
          //   5984: invokestatic 296	org/apache/cordova/filetransfer/FileTransfer:access$600	()Ljava/util/HashMap;
          //   5987: aload_0
          //   5988: getfield 71	org/apache/cordova/filetransfer/FileTransfer$1:val$objectId	Ljava/lang/String;
          //   5991: invokevirtual 302	java/util/HashMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
          //   5994: pop
          //   5995: aload 13
          //   5997: monitorexit
          //   5998: aload 18
          //   6000: ifnull -5990 -> 10
          //   6003: aload_0
          //   6004: getfield 49	org/apache/cordova/filetransfer/FileTransfer$1:val$trustEveryone	Z
          //   6007: ifeq -5997 -> 10
          //   6010: aload_0
          //   6011: getfield 47	org/apache/cordova/filetransfer/FileTransfer$1:val$useHttps	Z
          //   6014: ifeq -6004 -> 10
          //   6017: aload 18
          //   6019: checkcast 103	javax/net/ssl/HttpsURLConnection
          //   6022: astore 13
          //   6024: aload 13
          //   6026: aload 16
          //   6028: invokevirtual 118	javax/net/ssl/HttpsURLConnection:setHostnameVerifier	(Ljavax/net/ssl/HostnameVerifier;)V
          //   6031: aload 13
          //   6033: aload 15
          //   6035: invokevirtual 306	javax/net/ssl/HttpsURLConnection:setSSLSocketFactory	(Ljavax/net/ssl/SSLSocketFactory;)V
          //   6038: return
          //   6039: astore 32
          //   6041: aload 31
          //   6043: monitorexit
          //   6044: aload 18
          //   6046: astore 27
          //   6048: aload 16
          //   6050: astore 19
          //   6052: aload 15
          //   6054: astore 20
          //   6056: aload 18
          //   6058: astore 28
          //   6060: iload_2
          //   6061: istore_3
          //   6062: aload 16
          //   6064: astore 21
          //   6066: aload 15
          //   6068: astore 22
          //   6070: iload 5
          //   6072: istore 4
          //   6074: aload 18
          //   6076: astore 30
          //   6078: aload 16
          //   6080: astore 25
          //   6082: aload 15
          //   6084: astore 26
          //   6086: aload 18
          //   6088: astore 29
          //   6090: aload 16
          //   6092: astore 23
          //   6094: aload 15
          //   6096: astore 24
          //   6098: aload 18
          //   6100: astore 17
          //   6102: aload 16
          //   6104: astore 13
          //   6106: aload 15
          //   6108: astore 14
          //   6110: aload 32
          //   6112: athrow
          //   6113: astore 15
          //   6115: aload 30
          //   6117: astore 17
          //   6119: aload 25
          //   6121: astore 13
          //   6123: aload 26
          //   6125: astore 14
          //   6127: ldc -43
          //   6129: aload 15
          //   6131: invokevirtual 216	org/json/JSONException:getMessage	()Ljava/lang/String;
          //   6134: aload 15
          //   6136: invokestatic 222	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
          //   6139: pop
          //   6140: aload 30
          //   6142: astore 17
          //   6144: aload 25
          //   6146: astore 13
          //   6148: aload 26
          //   6150: astore 14
          //   6152: aload_0
          //   6153: getfield 41	org/apache/cordova/filetransfer/FileTransfer$1:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
          //   6156: new 319	org/apache/cordova/PluginResult
          //   6159: dup
          //   6160: getstatic 420	org/apache/cordova/PluginResult$Status:JSON_EXCEPTION	Lorg/apache/cordova/PluginResult$Status;
          //   6163: invokespecial 423	org/apache/cordova/PluginResult:<init>	(Lorg/apache/cordova/PluginResult$Status;)V
          //   6166: invokevirtual 332	org/apache/cordova/filetransfer/FileTransfer$RequestContext:sendPluginResult	(Lorg/apache/cordova/PluginResult;)V
          //   6169: invokestatic 296	org/apache/cordova/filetransfer/FileTransfer:access$600	()Ljava/util/HashMap;
          //   6172: astore 13
          //   6174: aload 13
          //   6176: monitorenter
          //   6177: invokestatic 296	org/apache/cordova/filetransfer/FileTransfer:access$600	()Ljava/util/HashMap;
          //   6180: aload_0
          //   6181: getfield 71	org/apache/cordova/filetransfer/FileTransfer$1:val$objectId	Ljava/lang/String;
          //   6184: invokevirtual 302	java/util/HashMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
          //   6187: pop
          //   6188: aload 13
          //   6190: monitorexit
          //   6191: aload 30
          //   6193: ifnull -6183 -> 10
          //   6196: aload_0
          //   6197: getfield 49	org/apache/cordova/filetransfer/FileTransfer$1:val$trustEveryone	Z
          //   6200: ifeq -6190 -> 10
          //   6203: aload_0
          //   6204: getfield 47	org/apache/cordova/filetransfer/FileTransfer$1:val$useHttps	Z
          //   6207: ifeq -6197 -> 10
          //   6210: aload 30
          //   6212: checkcast 103	javax/net/ssl/HttpsURLConnection
          //   6215: astore 13
          //   6217: aload 13
          //   6219: aload 25
          //   6221: invokevirtual 118	javax/net/ssl/HttpsURLConnection:setHostnameVerifier	(Ljavax/net/ssl/HostnameVerifier;)V
          //   6224: aload 13
          //   6226: aload 26
          //   6228: invokevirtual 306	javax/net/ssl/HttpsURLConnection:setSSLSocketFactory	(Ljavax/net/ssl/SSLSocketFactory;)V
          //   6231: return
          //   6232: astore 32
          //   6234: aload 31
          //   6236: monitorexit
          //   6237: aload 18
          //   6239: astore 27
          //   6241: aload 16
          //   6243: astore 19
          //   6245: aload 15
          //   6247: astore 20
          //   6249: aload 18
          //   6251: astore 28
          //   6253: iload_2
          //   6254: istore_3
          //   6255: aload 16
          //   6257: astore 21
          //   6259: aload 15
          //   6261: astore 22
          //   6263: iload 5
          //   6265: istore 4
          //   6267: aload 18
          //   6269: astore 30
          //   6271: aload 16
          //   6273: astore 25
          //   6275: aload 15
          //   6277: astore 26
          //   6279: aload 18
          //   6281: astore 29
          //   6283: aload 16
          //   6285: astore 23
          //   6287: aload 15
          //   6289: astore 24
          //   6291: aload 18
          //   6293: astore 17
          //   6295: aload 16
          //   6297: astore 13
          //   6299: aload 15
          //   6301: astore 14
          //   6303: aload 32
          //   6305: athrow
          //   6306: astore 15
          //   6308: aload 29
          //   6310: astore 17
          //   6312: aload 23
          //   6314: astore 13
          //   6316: aload 24
          //   6318: astore 14
          //   6320: getstatic 388	org/apache/cordova/filetransfer/FileTransfer:CONNECTION_ERR	I
          //   6323: aload_0
          //   6324: getfield 69	org/apache/cordova/filetransfer/FileTransfer$1:val$source	Ljava/lang/String;
          //   6327: aload_0
          //   6328: getfield 53	org/apache/cordova/filetransfer/FileTransfer$1:val$target	Ljava/lang/String;
          //   6331: aload 29
          //   6333: aload 15
          //   6335: invokestatic 316	org/apache/cordova/filetransfer/FileTransfer:access$500	(ILjava/lang/String;Ljava/lang/String;Ljava/net/URLConnection;Ljava/lang/Throwable;)Lorg/json/JSONObject;
          //   6338: astore 16
          //   6340: aload 29
          //   6342: astore 17
          //   6344: aload 23
          //   6346: astore 13
          //   6348: aload 24
          //   6350: astore 14
          //   6352: ldc -43
          //   6354: aload 16
          //   6356: invokevirtual 317	org/json/JSONObject:toString	()Ljava/lang/String;
          //   6359: aload 15
          //   6361: invokestatic 222	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
          //   6364: pop
          //   6365: aload 29
          //   6367: astore 17
          //   6369: aload 23
          //   6371: astore 13
          //   6373: aload 24
          //   6375: astore 14
          //   6377: aload_0
          //   6378: getfield 41	org/apache/cordova/filetransfer/FileTransfer$1:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
          //   6381: new 319	org/apache/cordova/PluginResult
          //   6384: dup
          //   6385: getstatic 325	org/apache/cordova/PluginResult$Status:IO_EXCEPTION	Lorg/apache/cordova/PluginResult$Status;
          //   6388: aload 16
          //   6390: invokespecial 328	org/apache/cordova/PluginResult:<init>	(Lorg/apache/cordova/PluginResult$Status;Lorg/json/JSONObject;)V
          //   6393: invokevirtual 332	org/apache/cordova/filetransfer/FileTransfer$RequestContext:sendPluginResult	(Lorg/apache/cordova/PluginResult;)V
          //   6396: invokestatic 296	org/apache/cordova/filetransfer/FileTransfer:access$600	()Ljava/util/HashMap;
          //   6399: astore 13
          //   6401: aload 13
          //   6403: monitorenter
          //   6404: invokestatic 296	org/apache/cordova/filetransfer/FileTransfer:access$600	()Ljava/util/HashMap;
          //   6407: aload_0
          //   6408: getfield 71	org/apache/cordova/filetransfer/FileTransfer$1:val$objectId	Ljava/lang/String;
          //   6411: invokevirtual 302	java/util/HashMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
          //   6414: pop
          //   6415: aload 13
          //   6417: monitorexit
          //   6418: aload 29
          //   6420: ifnull -6410 -> 10
          //   6423: aload_0
          //   6424: getfield 49	org/apache/cordova/filetransfer/FileTransfer$1:val$trustEveryone	Z
          //   6427: ifeq -6417 -> 10
          //   6430: aload_0
          //   6431: getfield 47	org/apache/cordova/filetransfer/FileTransfer$1:val$useHttps	Z
          //   6434: ifeq -6424 -> 10
          //   6437: aload 29
          //   6439: checkcast 103	javax/net/ssl/HttpsURLConnection
          //   6442: astore 13
          //   6444: aload 13
          //   6446: aload 23
          //   6448: invokevirtual 118	javax/net/ssl/HttpsURLConnection:setHostnameVerifier	(Ljavax/net/ssl/HostnameVerifier;)V
          //   6451: aload 13
          //   6453: aload 24
          //   6455: invokevirtual 306	javax/net/ssl/HttpsURLConnection:setSSLSocketFactory	(Ljavax/net/ssl/SSLSocketFactory;)V
          //   6458: return
          //   6459: astore 14
          //   6461: aload 13
          //   6463: monitorexit
          //   6464: aload 14
          //   6466: athrow
          //   6467: aload_0
          //   6468: getfield 41	org/apache/cordova/filetransfer/FileTransfer$1:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
          //   6471: aload 18
          //   6473: putfield 336	org/apache/cordova/filetransfer/FileTransfer$RequestContext:connection	Ljava/net/HttpURLConnection;
          //   6476: aload 13
          //   6478: monitorexit
          //   6479: aload 32
          //   6481: astore 31
          //   6483: new 425	java/io/ByteArrayOutputStream
          //   6486: dup
          //   6487: sipush 1024
          //   6490: aload 18
          //   6492: invokevirtual 428	java/net/HttpURLConnection:getContentLength	()I
          //   6495: invokestatic 431	java/lang/Math:max	(II)I
          //   6498: invokespecial 433	java/io/ByteArrayOutputStream:<init>	(I)V
          //   6501: astore 13
          //   6503: aload 32
          //   6505: astore 31
          //   6507: sipush 1024
          //   6510: newarray <illegal type>
          //   6512: astore 14
          //   6514: aload 32
          //   6516: astore 31
          //   6518: aload 32
          //   6520: aload 14
          //   6522: invokevirtual 438	org/apache/cordova/filetransfer/FileTransfer$TrackingInputStream:read	([B)I
          //   6525: istore_3
          //   6526: iload_3
          //   6527: ifle +392 -> 6919
          //   6530: aload 32
          //   6532: astore 31
          //   6534: aload 13
          //   6536: aload 14
          //   6538: iconst_0
          //   6539: iload_3
          //   6540: invokevirtual 439	java/io/ByteArrayOutputStream:write	([BII)V
          //   6543: goto -29 -> 6514
          //   6546: astore 33
          //   6548: aload 18
          //   6550: astore 27
          //   6552: aload 16
          //   6554: astore 19
          //   6556: aload 15
          //   6558: astore 20
          //   6560: aload 18
          //   6562: astore 28
          //   6564: iload_2
          //   6565: istore_3
          //   6566: aload 16
          //   6568: astore 21
          //   6570: aload 15
          //   6572: astore 22
          //   6574: iload 5
          //   6576: istore 4
          //   6578: aload 18
          //   6580: astore 30
          //   6582: aload 16
          //   6584: astore 25
          //   6586: aload 15
          //   6588: astore 26
          //   6590: aload 18
          //   6592: astore 29
          //   6594: aload 16
          //   6596: astore 23
          //   6598: aload 15
          //   6600: astore 24
          //   6602: aload 18
          //   6604: astore 17
          //   6606: aload 16
          //   6608: astore 13
          //   6610: aload 15
          //   6612: astore 14
          //   6614: aload_0
          //   6615: getfield 41	org/apache/cordova/filetransfer/FileTransfer$1:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
          //   6618: astore 32
          //   6620: aload 18
          //   6622: astore 27
          //   6624: aload 16
          //   6626: astore 19
          //   6628: aload 15
          //   6630: astore 20
          //   6632: aload 18
          //   6634: astore 28
          //   6636: iload_2
          //   6637: istore_3
          //   6638: aload 16
          //   6640: astore 21
          //   6642: aload 15
          //   6644: astore 22
          //   6646: iload 5
          //   6648: istore 4
          //   6650: aload 18
          //   6652: astore 30
          //   6654: aload 16
          //   6656: astore 25
          //   6658: aload 15
          //   6660: astore 26
          //   6662: aload 18
          //   6664: astore 29
          //   6666: aload 16
          //   6668: astore 23
          //   6670: aload 15
          //   6672: astore 24
          //   6674: aload 18
          //   6676: astore 17
          //   6678: aload 16
          //   6680: astore 13
          //   6682: aload 15
          //   6684: astore 14
          //   6686: aload 32
          //   6688: monitorenter
          //   6689: aload_0
          //   6690: getfield 41	org/apache/cordova/filetransfer/FileTransfer$1:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
          //   6693: aconst_null
          //   6694: putfield 336	org/apache/cordova/filetransfer/FileTransfer$RequestContext:connection	Ljava/net/HttpURLConnection;
          //   6697: aload 32
          //   6699: monitorexit
          //   6700: aload 18
          //   6702: astore 27
          //   6704: aload 16
          //   6706: astore 19
          //   6708: aload 15
          //   6710: astore 20
          //   6712: aload 18
          //   6714: astore 28
          //   6716: iload_2
          //   6717: istore_3
          //   6718: aload 16
          //   6720: astore 21
          //   6722: aload 15
          //   6724: astore 22
          //   6726: iload 5
          //   6728: istore 4
          //   6730: aload 18
          //   6732: astore 30
          //   6734: aload 16
          //   6736: astore 25
          //   6738: aload 15
          //   6740: astore 26
          //   6742: aload 18
          //   6744: astore 29
          //   6746: aload 16
          //   6748: astore 23
          //   6750: aload 15
          //   6752: astore 24
          //   6754: aload 18
          //   6756: astore 17
          //   6758: aload 16
          //   6760: astore 13
          //   6762: aload 15
          //   6764: astore 14
          //   6766: aload 31
          //   6768: invokestatic 292	org/apache/cordova/filetransfer/FileTransfer:access$300	(Ljava/io/Closeable;)V
          //   6771: aload 18
          //   6773: astore 27
          //   6775: aload 16
          //   6777: astore 19
          //   6779: aload 15
          //   6781: astore 20
          //   6783: aload 18
          //   6785: astore 28
          //   6787: iload_2
          //   6788: istore_3
          //   6789: aload 16
          //   6791: astore 21
          //   6793: aload 15
          //   6795: astore 22
          //   6797: iload 5
          //   6799: istore 4
          //   6801: aload 18
          //   6803: astore 30
          //   6805: aload 16
          //   6807: astore 25
          //   6809: aload 15
          //   6811: astore 26
          //   6813: aload 18
          //   6815: astore 29
          //   6817: aload 16
          //   6819: astore 23
          //   6821: aload 15
          //   6823: astore 24
          //   6825: aload 18
          //   6827: astore 17
          //   6829: aload 16
          //   6831: astore 13
          //   6833: aload 15
          //   6835: astore 14
          //   6837: aload 33
          //   6839: athrow
          //   6840: astore 15
          //   6842: invokestatic 296	org/apache/cordova/filetransfer/FileTransfer:access$600	()Ljava/util/HashMap;
          //   6845: astore 16
          //   6847: aload 16
          //   6849: monitorenter
          //   6850: invokestatic 296	org/apache/cordova/filetransfer/FileTransfer:access$600	()Ljava/util/HashMap;
          //   6853: aload_0
          //   6854: getfield 71	org/apache/cordova/filetransfer/FileTransfer$1:val$objectId	Ljava/lang/String;
          //   6857: invokevirtual 302	java/util/HashMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
          //   6860: pop
          //   6861: aload 16
          //   6863: monitorexit
          //   6864: aload 17
          //   6866: ifnull +38 -> 6904
          //   6869: aload_0
          //   6870: getfield 49	org/apache/cordova/filetransfer/FileTransfer$1:val$trustEveryone	Z
          //   6873: ifeq +31 -> 6904
          //   6876: aload_0
          //   6877: getfield 47	org/apache/cordova/filetransfer/FileTransfer$1:val$useHttps	Z
          //   6880: ifeq +24 -> 6904
          //   6883: aload 17
          //   6885: checkcast 103	javax/net/ssl/HttpsURLConnection
          //   6888: astore 16
          //   6890: aload 16
          //   6892: aload 13
          //   6894: invokevirtual 118	javax/net/ssl/HttpsURLConnection:setHostnameVerifier	(Ljavax/net/ssl/HostnameVerifier;)V
          //   6897: aload 16
          //   6899: aload 14
          //   6901: invokevirtual 306	javax/net/ssl/HttpsURLConnection:setSSLSocketFactory	(Ljavax/net/ssl/SSLSocketFactory;)V
          //   6904: aload 15
          //   6906: athrow
          //   6907: astore 14
          //   6909: aload 13
          //   6911: monitorexit
          //   6912: aload 32
          //   6914: astore 31
          //   6916: aload 14
          //   6918: athrow
          //   6919: aload 32
          //   6921: astore 31
          //   6923: aload 13
          //   6925: ldc -25
          //   6927: invokevirtual 441	java/io/ByteArrayOutputStream:toString	(Ljava/lang/String;)Ljava/lang/String;
          //   6930: astore 33
          //   6932: aload 18
          //   6934: astore 27
          //   6936: aload 16
          //   6938: astore 19
          //   6940: aload 15
          //   6942: astore 20
          //   6944: aload 18
          //   6946: astore 28
          //   6948: iload_2
          //   6949: istore_3
          //   6950: aload 16
          //   6952: astore 21
          //   6954: aload 15
          //   6956: astore 22
          //   6958: iload 5
          //   6960: istore 4
          //   6962: aload 18
          //   6964: astore 30
          //   6966: aload 16
          //   6968: astore 25
          //   6970: aload 15
          //   6972: astore 26
          //   6974: aload 18
          //   6976: astore 29
          //   6978: aload 16
          //   6980: astore 23
          //   6982: aload 15
          //   6984: astore 24
          //   6986: aload 18
          //   6988: astore 17
          //   6990: aload 16
          //   6992: astore 13
          //   6994: aload 15
          //   6996: astore 14
          //   6998: aload_0
          //   6999: getfield 41	org/apache/cordova/filetransfer/FileTransfer$1:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
          //   7002: astore 31
          //   7004: aload 18
          //   7006: astore 27
          //   7008: aload 16
          //   7010: astore 19
          //   7012: aload 15
          //   7014: astore 20
          //   7016: aload 18
          //   7018: astore 28
          //   7020: iload_2
          //   7021: istore_3
          //   7022: aload 16
          //   7024: astore 21
          //   7026: aload 15
          //   7028: astore 22
          //   7030: iload 5
          //   7032: istore 4
          //   7034: aload 18
          //   7036: astore 30
          //   7038: aload 16
          //   7040: astore 25
          //   7042: aload 15
          //   7044: astore 26
          //   7046: aload 18
          //   7048: astore 29
          //   7050: aload 16
          //   7052: astore 23
          //   7054: aload 15
          //   7056: astore 24
          //   7058: aload 18
          //   7060: astore 17
          //   7062: aload 16
          //   7064: astore 13
          //   7066: aload 15
          //   7068: astore 14
          //   7070: aload 31
          //   7072: monitorenter
          //   7073: aload_0
          //   7074: getfield 41	org/apache/cordova/filetransfer/FileTransfer$1:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
          //   7077: aconst_null
          //   7078: putfield 336	org/apache/cordova/filetransfer/FileTransfer$RequestContext:connection	Ljava/net/HttpURLConnection;
          //   7081: aload 31
          //   7083: monitorexit
          //   7084: aload 18
          //   7086: astore 27
          //   7088: aload 16
          //   7090: astore 19
          //   7092: aload 15
          //   7094: astore 20
          //   7096: aload 18
          //   7098: astore 28
          //   7100: iload_2
          //   7101: istore_3
          //   7102: aload 16
          //   7104: astore 21
          //   7106: aload 15
          //   7108: astore 22
          //   7110: iload 5
          //   7112: istore 4
          //   7114: aload 18
          //   7116: astore 30
          //   7118: aload 16
          //   7120: astore 25
          //   7122: aload 15
          //   7124: astore 26
          //   7126: aload 18
          //   7128: astore 29
          //   7130: aload 16
          //   7132: astore 23
          //   7134: aload 15
          //   7136: astore 24
          //   7138: aload 18
          //   7140: astore 17
          //   7142: aload 16
          //   7144: astore 13
          //   7146: aload 15
          //   7148: astore 14
          //   7150: aload 32
          //   7152: invokestatic 292	org/apache/cordova/filetransfer/FileTransfer:access$300	(Ljava/io/Closeable;)V
          //   7155: aload 18
          //   7157: astore 27
          //   7159: aload 16
          //   7161: astore 19
          //   7163: aload 15
          //   7165: astore 20
          //   7167: aload 18
          //   7169: astore 28
          //   7171: iload_2
          //   7172: istore_3
          //   7173: aload 16
          //   7175: astore 21
          //   7177: aload 15
          //   7179: astore 22
          //   7181: iload 5
          //   7183: istore 4
          //   7185: aload 18
          //   7187: astore 30
          //   7189: aload 16
          //   7191: astore 25
          //   7193: aload 15
          //   7195: astore 26
          //   7197: aload 18
          //   7199: astore 29
          //   7201: aload 16
          //   7203: astore 23
          //   7205: aload 15
          //   7207: astore 24
          //   7209: aload 18
          //   7211: astore 17
          //   7213: aload 16
          //   7215: astore 13
          //   7217: aload 15
          //   7219: astore 14
          //   7221: ldc -43
          //   7223: ldc_w 443
          //   7226: invokestatic 263	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
          //   7229: pop
          //   7230: aload 18
          //   7232: astore 27
          //   7234: aload 16
          //   7236: astore 19
          //   7238: aload 15
          //   7240: astore 20
          //   7242: aload 18
          //   7244: astore 28
          //   7246: iload_2
          //   7247: istore_3
          //   7248: aload 16
          //   7250: astore 21
          //   7252: aload 15
          //   7254: astore 22
          //   7256: iload 5
          //   7258: istore 4
          //   7260: aload 18
          //   7262: astore 30
          //   7264: aload 16
          //   7266: astore 25
          //   7268: aload 15
          //   7270: astore 26
          //   7272: aload 18
          //   7274: astore 29
          //   7276: aload 16
          //   7278: astore 23
          //   7280: aload 15
          //   7282: astore 24
          //   7284: aload 18
          //   7286: astore 17
          //   7288: aload 16
          //   7290: astore 13
          //   7292: aload 15
          //   7294: astore 14
          //   7296: ldc -43
          //   7298: aload 33
          //   7300: iconst_0
          //   7301: sipush 256
          //   7304: aload 33
          //   7306: invokevirtual 445	java/lang/String:length	()I
          //   7309: invokestatic 354	java/lang/Math:min	(II)I
          //   7312: invokevirtual 449	java/lang/String:substring	(II)Ljava/lang/String;
          //   7315: invokestatic 263	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
          //   7318: pop
          //   7319: aload 18
          //   7321: astore 27
          //   7323: aload 16
          //   7325: astore 19
          //   7327: aload 15
          //   7329: astore 20
          //   7331: aload 18
          //   7333: astore 28
          //   7335: iload_2
          //   7336: istore_3
          //   7337: aload 16
          //   7339: astore 21
          //   7341: aload 15
          //   7343: astore 22
          //   7345: iload 5
          //   7347: istore 4
          //   7349: aload 18
          //   7351: astore 30
          //   7353: aload 16
          //   7355: astore 25
          //   7357: aload 15
          //   7359: astore 26
          //   7361: aload 18
          //   7363: astore 29
          //   7365: aload 16
          //   7367: astore 23
          //   7369: aload 15
          //   7371: astore 24
          //   7373: aload 18
          //   7375: astore 17
          //   7377: aload 16
          //   7379: astore 13
          //   7381: aload 15
          //   7383: astore 14
          //   7385: aload 45
          //   7387: iload_1
          //   7388: invokevirtual 452	org/apache/cordova/filetransfer/FileUploadResult:setResponseCode	(I)V
          //   7391: aload 18
          //   7393: astore 27
          //   7395: aload 16
          //   7397: astore 19
          //   7399: aload 15
          //   7401: astore 20
          //   7403: aload 18
          //   7405: astore 28
          //   7407: iload_2
          //   7408: istore_3
          //   7409: aload 16
          //   7411: astore 21
          //   7413: aload 15
          //   7415: astore 22
          //   7417: iload 5
          //   7419: istore 4
          //   7421: aload 18
          //   7423: astore 30
          //   7425: aload 16
          //   7427: astore 25
          //   7429: aload 15
          //   7431: astore 26
          //   7433: aload 18
          //   7435: astore 29
          //   7437: aload 16
          //   7439: astore 23
          //   7441: aload 15
          //   7443: astore 24
          //   7445: aload 18
          //   7447: astore 17
          //   7449: aload 16
          //   7451: astore 13
          //   7453: aload 15
          //   7455: astore 14
          //   7457: aload 45
          //   7459: aload 33
          //   7461: invokevirtual 455	org/apache/cordova/filetransfer/FileUploadResult:setResponse	(Ljava/lang/String;)V
          //   7464: aload 18
          //   7466: astore 27
          //   7468: aload 16
          //   7470: astore 19
          //   7472: aload 15
          //   7474: astore 20
          //   7476: aload 18
          //   7478: astore 28
          //   7480: iload_2
          //   7481: istore_3
          //   7482: aload 16
          //   7484: astore 21
          //   7486: aload 15
          //   7488: astore 22
          //   7490: iload 5
          //   7492: istore 4
          //   7494: aload 18
          //   7496: astore 30
          //   7498: aload 16
          //   7500: astore 25
          //   7502: aload 15
          //   7504: astore 26
          //   7506: aload 18
          //   7508: astore 29
          //   7510: aload 16
          //   7512: astore 23
          //   7514: aload 15
          //   7516: astore 24
          //   7518: aload 18
          //   7520: astore 17
          //   7522: aload 16
          //   7524: astore 13
          //   7526: aload 15
          //   7528: astore 14
          //   7530: aload_0
          //   7531: getfield 41	org/apache/cordova/filetransfer/FileTransfer$1:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
          //   7534: new 319	org/apache/cordova/PluginResult
          //   7537: dup
          //   7538: getstatic 378	org/apache/cordova/PluginResult$Status:OK	Lorg/apache/cordova/PluginResult$Status;
          //   7541: aload 45
          //   7543: invokevirtual 456	org/apache/cordova/filetransfer/FileUploadResult:toJSONObject	()Lorg/json/JSONObject;
          //   7546: invokespecial 328	org/apache/cordova/PluginResult:<init>	(Lorg/apache/cordova/PluginResult$Status;Lorg/json/JSONObject;)V
          //   7549: invokevirtual 332	org/apache/cordova/filetransfer/FileTransfer$RequestContext:sendPluginResult	(Lorg/apache/cordova/PluginResult;)V
          //   7552: invokestatic 296	org/apache/cordova/filetransfer/FileTransfer:access$600	()Ljava/util/HashMap;
          //   7555: astore 13
          //   7557: aload 13
          //   7559: monitorenter
          //   7560: invokestatic 296	org/apache/cordova/filetransfer/FileTransfer:access$600	()Ljava/util/HashMap;
          //   7563: aload_0
          //   7564: getfield 71	org/apache/cordova/filetransfer/FileTransfer$1:val$objectId	Ljava/lang/String;
          //   7567: invokevirtual 302	java/util/HashMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
          //   7570: pop
          //   7571: aload 13
          //   7573: monitorexit
          //   7574: aload 18
          //   7576: ifnull -7566 -> 10
          //   7579: aload_0
          //   7580: getfield 49	org/apache/cordova/filetransfer/FileTransfer$1:val$trustEveryone	Z
          //   7583: ifeq -7573 -> 10
          //   7586: aload_0
          //   7587: getfield 47	org/apache/cordova/filetransfer/FileTransfer$1:val$useHttps	Z
          //   7590: ifeq -7580 -> 10
          //   7593: aload 18
          //   7595: checkcast 103	javax/net/ssl/HttpsURLConnection
          //   7598: astore 13
          //   7600: aload 13
          //   7602: aload 16
          //   7604: invokevirtual 118	javax/net/ssl/HttpsURLConnection:setHostnameVerifier	(Ljavax/net/ssl/HostnameVerifier;)V
          //   7607: aload 13
          //   7609: aload 15
          //   7611: invokevirtual 306	javax/net/ssl/HttpsURLConnection:setSSLSocketFactory	(Ljavax/net/ssl/SSLSocketFactory;)V
          //   7614: return
          //   7615: astore 32
          //   7617: aload 31
          //   7619: monitorexit
          //   7620: aload 18
          //   7622: astore 27
          //   7624: aload 16
          //   7626: astore 19
          //   7628: aload 15
          //   7630: astore 20
          //   7632: aload 18
          //   7634: astore 28
          //   7636: iload_2
          //   7637: istore_3
          //   7638: aload 16
          //   7640: astore 21
          //   7642: aload 15
          //   7644: astore 22
          //   7646: iload 5
          //   7648: istore 4
          //   7650: aload 18
          //   7652: astore 30
          //   7654: aload 16
          //   7656: astore 25
          //   7658: aload 15
          //   7660: astore 26
          //   7662: aload 18
          //   7664: astore 29
          //   7666: aload 16
          //   7668: astore 23
          //   7670: aload 15
          //   7672: astore 24
          //   7674: aload 18
          //   7676: astore 17
          //   7678: aload 16
          //   7680: astore 13
          //   7682: aload 15
          //   7684: astore 14
          //   7686: aload 32
          //   7688: athrow
          //   7689: astore 31
          //   7691: aload 32
          //   7693: monitorexit
          //   7694: aload 18
          //   7696: astore 27
          //   7698: aload 16
          //   7700: astore 19
          //   7702: aload 15
          //   7704: astore 20
          //   7706: aload 18
          //   7708: astore 28
          //   7710: iload_2
          //   7711: istore_3
          //   7712: aload 16
          //   7714: astore 21
          //   7716: aload 15
          //   7718: astore 22
          //   7720: iload 5
          //   7722: istore 4
          //   7724: aload 18
          //   7726: astore 30
          //   7728: aload 16
          //   7730: astore 25
          //   7732: aload 15
          //   7734: astore 26
          //   7736: aload 18
          //   7738: astore 29
          //   7740: aload 16
          //   7742: astore 23
          //   7744: aload 15
          //   7746: astore 24
          //   7748: aload 18
          //   7750: astore 17
          //   7752: aload 16
          //   7754: astore 13
          //   7756: aload 15
          //   7758: astore 14
          //   7760: aload 31
          //   7762: athrow
          //   7763: astore 14
          //   7765: aload 13
          //   7767: monitorexit
          //   7768: aload 14
          //   7770: athrow
          //   7771: astore 14
          //   7773: aload 13
          //   7775: monitorexit
          //   7776: aload 14
          //   7778: athrow
          //   7779: astore 14
          //   7781: aload 13
          //   7783: monitorexit
          //   7784: aload 14
          //   7786: athrow
          //   7787: astore 14
          //   7789: aload 13
          //   7791: monitorexit
          //   7792: aload 14
          //   7794: athrow
          //   7795: astore 14
          //   7797: aload 13
          //   7799: monitorexit
          //   7800: aload 14
          //   7802: athrow
          //   7803: astore 13
          //   7805: aload 16
          //   7807: monitorexit
          //   7808: aload 13
          //   7810: athrow
          //   7811: iconst_1
          //   7812: istore_1
          //   7813: iload_1
          //   7814: ifne +8 -> 7822
          //   7817: iload_2
          //   7818: iconst_m1
          //   7819: if_icmpne -3798 -> 4021
          //   7822: iconst_1
          //   7823: istore_1
          //   7824: goto -4291 -> 3533
          // Local variable table:
          //   start	length	slot	name	signature
          //   0	7827	0	this	1
          //   69	7755	1	i	int
          //   2917	4903	2	j	int
          //   87	7625	3	k	int
          //   98	7625	4	m	int
          //   66	7655	5	n	int
          //   63	3990	6	i1	int
          //   2843	228	7	i2	int
          //   2914	157	8	i3	int
          //   4357	218	9	l1	long
          //   4400	173	11	l2	long
          //   7803	6	13	localObject2	Object
          //   134	4037	14	localObject3	Object
          //   4254	6	14	localObject4	Object
          //   4543	374	14	localObject5	Object
          //   5000	13	14	localObject6	Object
          //   5117	1259	14	localObject7	Object
          //   6459	6	14	localObject8	Object
          //   6512	388	14	localObject9	Object
          //   6907	10	14	localObject10	Object
          //   6996	763	14	localSSLSocketFactory1	SSLSocketFactory
          //   7763	6	14	localObject11	Object
          //   7771	6	14	localObject12	Object
          //   7779	6	14	localObject13	Object
          //   7787	6	14	localObject14	Object
          //   7795	6	14	localObject15	Object
          //   24	4065	15	localObject16	Object
          //   4101	685	15	localFileNotFoundException	java.io.FileNotFoundException
          //   4792	1315	15	localIOException	IOException
          //   6113	187	15	localJSONException1	JSONException
          //   6306	528	15	localThrowable	Throwable
          //   6840	917	15	localSSLSocketFactory2	SSLSocketFactory
          //   12	7794	16	localObject17	Object
          //   126	7625	17	localObject18	Object
          //   15	7734	18	localHttpURLConnection1	HttpURLConnection
          //   76	7625	19	localObject19	Object
          //   80	7625	20	localObject20	Object
          //   90	7625	21	localObject21	Object
          //   94	7625	22	localObject22	Object
          //   118	7625	23	localObject23	Object
          //   122	7625	24	localObject24	Object
          //   106	7625	25	localObject25	Object
          //   110	7625	26	localObject26	Object
          //   72	7625	27	localObject27	Object
          //   84	7625	28	localObject28	Object
          //   114	7625	29	localObject29	Object
          //   102	7625	30	localHttpURLConnection2	HttpURLConnection
          //   27	7591	31	localObject30	Object
          //   7689	72	31	localObject31	Object
          //   30	596	32	localObject32	Object
          //   2109	76	32	localJSONException2	JSONException
          //   3770	788	32	localOutputStream	java.io.OutputStream
          //   4579	615	32	localCloseable	Closeable
          //   5721	251	32	localTrackingInputStream	FileTransfer.TrackingInputStream
          //   6039	72	32	localObject33	Object
          //   6232	299	32	localObject34	Object
          //   6618	533	32	localRequestContext	FileTransfer.RequestContext
          //   7615	77	32	localObject35	Object
          //   33	5087	33	localOpenForReadResult	org.apache.cordova.CordovaResourceApi.OpenForReadResult
          //   6546	292	33	localObject36	Object
          //   6930	530	33	str	String
          //   36	5000	34	arrayOfByte1	byte[]
          //   39	4258	35	arrayOfByte2	byte[]
          //   45	498	36	localObject37	Object
          //   48	509	37	localObject38	Object
          //   51	522	38	localObject39	Object
          //   54	531	39	localObject40	Object
          //   57	540	40	localObject41	Object
          //   42	705	41	localHttpsURLConnection	HttpsURLConnection
          //   60	325	42	localObject42	Object
          //   18	245	43	localObject43	Object
          //   21	254	44	localObject44	Object
          //   143	7399	45	localFileUploadResult	FileUploadResult
          //   218	4318	46	localFileProgressResult	FileProgressResult
          // Exception table:
          //   from	to	target	type
          //   1555	1564	2109	org/json/JSONException
          //   1618	1628	2109	org/json/JSONException
          //   1682	1691	2109	org/json/JSONException
          //   1745	1758	2109	org/json/JSONException
          //   1812	1830	2109	org/json/JSONException
          //   1884	1905	2109	org/json/JSONException
          //   1959	1972	2109	org/json/JSONException
          //   2026	2044	2109	org/json/JSONException
          //   2098	2106	2109	org/json/JSONException
          //   136	145	4101	java/io/FileNotFoundException
          //   211	220	4101	java/io/FileNotFoundException
          //   286	299	4101	java/io/FileNotFoundException
          //   373	380	4101	java/io/FileNotFoundException
          //   454	461	4101	java/io/FileNotFoundException
          //   527	534	4101	java/io/FileNotFoundException
          //   600	607	4101	java/io/FileNotFoundException
          //   673	680	4101	java/io/FileNotFoundException
          //   746	754	4101	java/io/FileNotFoundException
          //   820	826	4101	java/io/FileNotFoundException
          //   892	898	4101	java/io/FileNotFoundException
          //   964	970	4101	java/io/FileNotFoundException
          //   1036	1045	4101	java/io/FileNotFoundException
          //   1111	1120	4101	java/io/FileNotFoundException
          //   1186	1198	4101	java/io/FileNotFoundException
          //   1269	1278	4101	java/io/FileNotFoundException
          //   1344	1351	4101	java/io/FileNotFoundException
          //   1417	1426	4101	java/io/FileNotFoundException
          //   1492	1501	4101	java/io/FileNotFoundException
          //   1555	1564	4101	java/io/FileNotFoundException
          //   1618	1628	4101	java/io/FileNotFoundException
          //   1682	1691	4101	java/io/FileNotFoundException
          //   1745	1758	4101	java/io/FileNotFoundException
          //   1812	1830	4101	java/io/FileNotFoundException
          //   1884	1905	4101	java/io/FileNotFoundException
          //   1959	1972	4101	java/io/FileNotFoundException
          //   2026	2044	4101	java/io/FileNotFoundException
          //   2098	2106	4101	java/io/FileNotFoundException
          //   2177	2190	4101	java/io/FileNotFoundException
          //   2256	2274	4101	java/io/FileNotFoundException
          //   2340	2360	4101	java/io/FileNotFoundException
          //   2426	2451	4101	java/io/FileNotFoundException
          //   2517	2542	4101	java/io/FileNotFoundException
          //   2608	2620	4101	java/io/FileNotFoundException
          //   2686	2695	4101	java/io/FileNotFoundException
          //   2761	2774	4101	java/io/FileNotFoundException
          //   2840	2845	4101	java/io/FileNotFoundException
          //   2911	2916	4101	java/io/FileNotFoundException
          //   2984	2994	4101	java/io/FileNotFoundException
          //   3060	3073	4101	java/io/FileNotFoundException
          //   3139	3145	4101	java/io/FileNotFoundException
          //   3211	3218	4101	java/io/FileNotFoundException
          //   3284	3310	4101	java/io/FileNotFoundException
          //   3376	3383	4101	java/io/FileNotFoundException
          //   3449	3457	4101	java/io/FileNotFoundException
          //   3523	3530	4101	java/io/FileNotFoundException
          //   3603	3611	4101	java/io/FileNotFoundException
          //   3677	3688	4101	java/io/FileNotFoundException
          //   3754	3759	4101	java/io/FileNotFoundException
          //   3874	3882	4101	java/io/FileNotFoundException
          //   3948	3953	4101	java/io/FileNotFoundException
          //   4092	4098	4101	java/io/FileNotFoundException
          //   4646	4654	4101	java/io/FileNotFoundException
          //   4719	4724	4101	java/io/FileNotFoundException
          //   4789	4792	4101	java/io/FileNotFoundException
          //   5119	5127	4101	java/io/FileNotFoundException
          //   5193	5198	4101	java/io/FileNotFoundException
          //   5264	5270	4101	java/io/FileNotFoundException
          //   5336	5339	4101	java/io/FileNotFoundException
          //   5416	5453	4101	java/io/FileNotFoundException
          //   5519	5525	4101	java/io/FileNotFoundException
          //   5591	5617	4101	java/io/FileNotFoundException
          //   5683	5713	4101	java/io/FileNotFoundException
          //   5819	5825	4101	java/io/FileNotFoundException
          //   5891	5894	4101	java/io/FileNotFoundException
          //   5971	5976	4101	java/io/FileNotFoundException
          //   6110	6113	4101	java/io/FileNotFoundException
          //   6303	6306	4101	java/io/FileNotFoundException
          //   6614	6620	4101	java/io/FileNotFoundException
          //   6686	6689	4101	java/io/FileNotFoundException
          //   6766	6771	4101	java/io/FileNotFoundException
          //   6837	6840	4101	java/io/FileNotFoundException
          //   6998	7004	4101	java/io/FileNotFoundException
          //   7070	7073	4101	java/io/FileNotFoundException
          //   7150	7155	4101	java/io/FileNotFoundException
          //   7221	7230	4101	java/io/FileNotFoundException
          //   7296	7319	4101	java/io/FileNotFoundException
          //   7385	7391	4101	java/io/FileNotFoundException
          //   7457	7464	4101	java/io/FileNotFoundException
          //   7530	7552	4101	java/io/FileNotFoundException
          //   7686	7689	4101	java/io/FileNotFoundException
          //   7760	7763	4101	java/io/FileNotFoundException
          //   3961	3975	4254	finally
          //   4256	4259	4254	finally
          //   3765	3772	4579	finally
          //   3779	3785	4579	finally
          //   3792	3795	4579	finally
          //   4281	4288	4579	finally
          //   4295	4301	4579	finally
          //   4307	4323	4579	finally
          //   4329	4335	4579	finally
          //   4341	4356	4579	finally
          //   4370	4377	4579	finally
          //   4383	4393	4579	finally
          //   4424	4466	4579	finally
          //   4472	4488	4579	finally
          //   4494	4509	4579	finally
          //   4515	4522	4579	finally
          //   4528	4545	4579	finally
          //   4551	4557	4579	finally
          //   4563	4572	4579	finally
          //   5012	5015	4579	finally
          //   5021	5028	4579	finally
          //   5034	5041	4579	finally
          //   5048	5053	4579	finally
          //   136	145	4792	java/io/IOException
          //   211	220	4792	java/io/IOException
          //   286	299	4792	java/io/IOException
          //   373	380	4792	java/io/IOException
          //   454	461	4792	java/io/IOException
          //   527	534	4792	java/io/IOException
          //   600	607	4792	java/io/IOException
          //   673	680	4792	java/io/IOException
          //   746	754	4792	java/io/IOException
          //   820	826	4792	java/io/IOException
          //   892	898	4792	java/io/IOException
          //   964	970	4792	java/io/IOException
          //   1036	1045	4792	java/io/IOException
          //   1111	1120	4792	java/io/IOException
          //   1186	1198	4792	java/io/IOException
          //   1269	1278	4792	java/io/IOException
          //   1344	1351	4792	java/io/IOException
          //   1417	1426	4792	java/io/IOException
          //   1492	1501	4792	java/io/IOException
          //   1555	1564	4792	java/io/IOException
          //   1618	1628	4792	java/io/IOException
          //   1682	1691	4792	java/io/IOException
          //   1745	1758	4792	java/io/IOException
          //   1812	1830	4792	java/io/IOException
          //   1884	1905	4792	java/io/IOException
          //   1959	1972	4792	java/io/IOException
          //   2026	2044	4792	java/io/IOException
          //   2098	2106	4792	java/io/IOException
          //   2177	2190	4792	java/io/IOException
          //   2256	2274	4792	java/io/IOException
          //   2340	2360	4792	java/io/IOException
          //   2426	2451	4792	java/io/IOException
          //   2517	2542	4792	java/io/IOException
          //   2608	2620	4792	java/io/IOException
          //   2686	2695	4792	java/io/IOException
          //   2761	2774	4792	java/io/IOException
          //   2840	2845	4792	java/io/IOException
          //   2911	2916	4792	java/io/IOException
          //   2984	2994	4792	java/io/IOException
          //   3060	3073	4792	java/io/IOException
          //   3139	3145	4792	java/io/IOException
          //   3211	3218	4792	java/io/IOException
          //   3284	3310	4792	java/io/IOException
          //   3376	3383	4792	java/io/IOException
          //   3449	3457	4792	java/io/IOException
          //   3523	3530	4792	java/io/IOException
          //   3603	3611	4792	java/io/IOException
          //   3677	3688	4792	java/io/IOException
          //   3754	3759	4792	java/io/IOException
          //   3874	3882	4792	java/io/IOException
          //   3948	3953	4792	java/io/IOException
          //   4092	4098	4792	java/io/IOException
          //   4646	4654	4792	java/io/IOException
          //   4719	4724	4792	java/io/IOException
          //   4789	4792	4792	java/io/IOException
          //   5119	5127	4792	java/io/IOException
          //   5193	5198	4792	java/io/IOException
          //   5264	5270	4792	java/io/IOException
          //   5336	5339	4792	java/io/IOException
          //   5416	5453	4792	java/io/IOException
          //   5519	5525	4792	java/io/IOException
          //   5591	5617	4792	java/io/IOException
          //   5683	5713	4792	java/io/IOException
          //   5819	5825	4792	java/io/IOException
          //   5891	5894	4792	java/io/IOException
          //   5971	5976	4792	java/io/IOException
          //   6110	6113	4792	java/io/IOException
          //   6303	6306	4792	java/io/IOException
          //   6614	6620	4792	java/io/IOException
          //   6686	6689	4792	java/io/IOException
          //   6766	6771	4792	java/io/IOException
          //   6837	6840	4792	java/io/IOException
          //   6998	7004	4792	java/io/IOException
          //   7070	7073	4792	java/io/IOException
          //   7150	7155	4792	java/io/IOException
          //   7221	7230	4792	java/io/IOException
          //   7296	7319	4792	java/io/IOException
          //   7385	7391	4792	java/io/IOException
          //   7457	7464	4792	java/io/IOException
          //   7530	7552	4792	java/io/IOException
          //   7686	7689	4792	java/io/IOException
          //   7760	7763	4792	java/io/IOException
          //   3795	3808	5000	finally
          //   4262	4274	5000	finally
          //   5002	5005	5000	finally
          //   5339	5350	6039	finally
          //   6041	6044	6039	finally
          //   136	145	6113	org/json/JSONException
          //   211	220	6113	org/json/JSONException
          //   286	299	6113	org/json/JSONException
          //   373	380	6113	org/json/JSONException
          //   454	461	6113	org/json/JSONException
          //   527	534	6113	org/json/JSONException
          //   600	607	6113	org/json/JSONException
          //   673	680	6113	org/json/JSONException
          //   746	754	6113	org/json/JSONException
          //   820	826	6113	org/json/JSONException
          //   892	898	6113	org/json/JSONException
          //   964	970	6113	org/json/JSONException
          //   1036	1045	6113	org/json/JSONException
          //   1111	1120	6113	org/json/JSONException
          //   1186	1198	6113	org/json/JSONException
          //   1269	1278	6113	org/json/JSONException
          //   1344	1351	6113	org/json/JSONException
          //   1417	1426	6113	org/json/JSONException
          //   1492	1501	6113	org/json/JSONException
          //   2177	2190	6113	org/json/JSONException
          //   2256	2274	6113	org/json/JSONException
          //   2340	2360	6113	org/json/JSONException
          //   2426	2451	6113	org/json/JSONException
          //   2517	2542	6113	org/json/JSONException
          //   2608	2620	6113	org/json/JSONException
          //   2686	2695	6113	org/json/JSONException
          //   2761	2774	6113	org/json/JSONException
          //   2840	2845	6113	org/json/JSONException
          //   2911	2916	6113	org/json/JSONException
          //   2984	2994	6113	org/json/JSONException
          //   3060	3073	6113	org/json/JSONException
          //   3139	3145	6113	org/json/JSONException
          //   3211	3218	6113	org/json/JSONException
          //   3284	3310	6113	org/json/JSONException
          //   3376	3383	6113	org/json/JSONException
          //   3449	3457	6113	org/json/JSONException
          //   3523	3530	6113	org/json/JSONException
          //   3603	3611	6113	org/json/JSONException
          //   3677	3688	6113	org/json/JSONException
          //   3754	3759	6113	org/json/JSONException
          //   3874	3882	6113	org/json/JSONException
          //   3948	3953	6113	org/json/JSONException
          //   4092	4098	6113	org/json/JSONException
          //   4646	4654	6113	org/json/JSONException
          //   4719	4724	6113	org/json/JSONException
          //   4789	4792	6113	org/json/JSONException
          //   5119	5127	6113	org/json/JSONException
          //   5193	5198	6113	org/json/JSONException
          //   5264	5270	6113	org/json/JSONException
          //   5336	5339	6113	org/json/JSONException
          //   5416	5453	6113	org/json/JSONException
          //   5519	5525	6113	org/json/JSONException
          //   5591	5617	6113	org/json/JSONException
          //   5683	5713	6113	org/json/JSONException
          //   5819	5825	6113	org/json/JSONException
          //   5891	5894	6113	org/json/JSONException
          //   5971	5976	6113	org/json/JSONException
          //   6110	6113	6113	org/json/JSONException
          //   6303	6306	6113	org/json/JSONException
          //   6614	6620	6113	org/json/JSONException
          //   6686	6689	6113	org/json/JSONException
          //   6766	6771	6113	org/json/JSONException
          //   6837	6840	6113	org/json/JSONException
          //   6998	7004	6113	org/json/JSONException
          //   7070	7073	6113	org/json/JSONException
          //   7150	7155	6113	org/json/JSONException
          //   7221	7230	6113	org/json/JSONException
          //   7296	7319	6113	org/json/JSONException
          //   7385	7391	6113	org/json/JSONException
          //   7457	7464	6113	org/json/JSONException
          //   7530	7552	6113	org/json/JSONException
          //   7686	7689	6113	org/json/JSONException
          //   7760	7763	6113	org/json/JSONException
          //   5894	5905	6232	finally
          //   6234	6237	6232	finally
          //   136	145	6306	java/lang/Throwable
          //   211	220	6306	java/lang/Throwable
          //   286	299	6306	java/lang/Throwable
          //   373	380	6306	java/lang/Throwable
          //   454	461	6306	java/lang/Throwable
          //   527	534	6306	java/lang/Throwable
          //   600	607	6306	java/lang/Throwable
          //   673	680	6306	java/lang/Throwable
          //   746	754	6306	java/lang/Throwable
          //   820	826	6306	java/lang/Throwable
          //   892	898	6306	java/lang/Throwable
          //   964	970	6306	java/lang/Throwable
          //   1036	1045	6306	java/lang/Throwable
          //   1111	1120	6306	java/lang/Throwable
          //   1186	1198	6306	java/lang/Throwable
          //   1269	1278	6306	java/lang/Throwable
          //   1344	1351	6306	java/lang/Throwable
          //   1417	1426	6306	java/lang/Throwable
          //   1492	1501	6306	java/lang/Throwable
          //   1555	1564	6306	java/lang/Throwable
          //   1618	1628	6306	java/lang/Throwable
          //   1682	1691	6306	java/lang/Throwable
          //   1745	1758	6306	java/lang/Throwable
          //   1812	1830	6306	java/lang/Throwable
          //   1884	1905	6306	java/lang/Throwable
          //   1959	1972	6306	java/lang/Throwable
          //   2026	2044	6306	java/lang/Throwable
          //   2098	2106	6306	java/lang/Throwable
          //   2177	2190	6306	java/lang/Throwable
          //   2256	2274	6306	java/lang/Throwable
          //   2340	2360	6306	java/lang/Throwable
          //   2426	2451	6306	java/lang/Throwable
          //   2517	2542	6306	java/lang/Throwable
          //   2608	2620	6306	java/lang/Throwable
          //   2686	2695	6306	java/lang/Throwable
          //   2761	2774	6306	java/lang/Throwable
          //   2840	2845	6306	java/lang/Throwable
          //   2911	2916	6306	java/lang/Throwable
          //   2984	2994	6306	java/lang/Throwable
          //   3060	3073	6306	java/lang/Throwable
          //   3139	3145	6306	java/lang/Throwable
          //   3211	3218	6306	java/lang/Throwable
          //   3284	3310	6306	java/lang/Throwable
          //   3376	3383	6306	java/lang/Throwable
          //   3449	3457	6306	java/lang/Throwable
          //   3523	3530	6306	java/lang/Throwable
          //   3603	3611	6306	java/lang/Throwable
          //   3677	3688	6306	java/lang/Throwable
          //   3754	3759	6306	java/lang/Throwable
          //   3874	3882	6306	java/lang/Throwable
          //   3948	3953	6306	java/lang/Throwable
          //   4092	4098	6306	java/lang/Throwable
          //   4646	4654	6306	java/lang/Throwable
          //   4719	4724	6306	java/lang/Throwable
          //   4789	4792	6306	java/lang/Throwable
          //   5119	5127	6306	java/lang/Throwable
          //   5193	5198	6306	java/lang/Throwable
          //   5264	5270	6306	java/lang/Throwable
          //   5336	5339	6306	java/lang/Throwable
          //   5416	5453	6306	java/lang/Throwable
          //   5519	5525	6306	java/lang/Throwable
          //   5591	5617	6306	java/lang/Throwable
          //   5683	5713	6306	java/lang/Throwable
          //   5819	5825	6306	java/lang/Throwable
          //   5891	5894	6306	java/lang/Throwable
          //   5971	5976	6306	java/lang/Throwable
          //   6110	6113	6306	java/lang/Throwable
          //   6303	6306	6306	java/lang/Throwable
          //   6614	6620	6306	java/lang/Throwable
          //   6686	6689	6306	java/lang/Throwable
          //   6766	6771	6306	java/lang/Throwable
          //   6837	6840	6306	java/lang/Throwable
          //   6998	7004	6306	java/lang/Throwable
          //   7070	7073	6306	java/lang/Throwable
          //   7150	7155	6306	java/lang/Throwable
          //   7221	7230	6306	java/lang/Throwable
          //   7296	7319	6306	java/lang/Throwable
          //   7385	7391	6306	java/lang/Throwable
          //   7457	7464	6306	java/lang/Throwable
          //   7530	7552	6306	java/lang/Throwable
          //   7686	7689	6306	java/lang/Throwable
          //   7760	7763	6306	java/lang/Throwable
          //   5984	5998	6459	finally
          //   6461	6464	6459	finally
          //   5716	5723	6546	finally
          //   5727	5733	6546	finally
          //   5737	5740	6546	finally
          //   6483	6503	6546	finally
          //   6507	6514	6546	finally
          //   6518	6526	6546	finally
          //   6534	6543	6546	finally
          //   6916	6919	6546	finally
          //   6923	6932	6546	finally
          //   136	145	6840	finally
          //   211	220	6840	finally
          //   286	299	6840	finally
          //   373	380	6840	finally
          //   454	461	6840	finally
          //   527	534	6840	finally
          //   600	607	6840	finally
          //   673	680	6840	finally
          //   746	754	6840	finally
          //   820	826	6840	finally
          //   892	898	6840	finally
          //   964	970	6840	finally
          //   1036	1045	6840	finally
          //   1111	1120	6840	finally
          //   1186	1198	6840	finally
          //   1269	1278	6840	finally
          //   1344	1351	6840	finally
          //   1417	1426	6840	finally
          //   1492	1501	6840	finally
          //   1555	1564	6840	finally
          //   1618	1628	6840	finally
          //   1682	1691	6840	finally
          //   1745	1758	6840	finally
          //   1812	1830	6840	finally
          //   1884	1905	6840	finally
          //   1959	1972	6840	finally
          //   2026	2044	6840	finally
          //   2098	2106	6840	finally
          //   2177	2190	6840	finally
          //   2256	2274	6840	finally
          //   2340	2360	6840	finally
          //   2426	2451	6840	finally
          //   2517	2542	6840	finally
          //   2608	2620	6840	finally
          //   2686	2695	6840	finally
          //   2761	2774	6840	finally
          //   2840	2845	6840	finally
          //   2911	2916	6840	finally
          //   2984	2994	6840	finally
          //   3060	3073	6840	finally
          //   3139	3145	6840	finally
          //   3211	3218	6840	finally
          //   3284	3310	6840	finally
          //   3376	3383	6840	finally
          //   3449	3457	6840	finally
          //   3523	3530	6840	finally
          //   3603	3611	6840	finally
          //   3677	3688	6840	finally
          //   3754	3759	6840	finally
          //   3874	3882	6840	finally
          //   3948	3953	6840	finally
          //   4092	4098	6840	finally
          //   4115	4135	6840	finally
          //   4147	4160	6840	finally
          //   4172	4191	6840	finally
          //   4646	4654	6840	finally
          //   4719	4724	6840	finally
          //   4789	4792	6840	finally
          //   4806	4826	6840	finally
          //   4838	4851	6840	finally
          //   4863	4906	6840	finally
          //   4918	4937	6840	finally
          //   5119	5127	6840	finally
          //   5193	5198	6840	finally
          //   5264	5270	6840	finally
          //   5336	5339	6840	finally
          //   5416	5453	6840	finally
          //   5519	5525	6840	finally
          //   5591	5617	6840	finally
          //   5683	5713	6840	finally
          //   5819	5825	6840	finally
          //   5891	5894	6840	finally
          //   5971	5976	6840	finally
          //   6110	6113	6840	finally
          //   6127	6140	6840	finally
          //   6152	6169	6840	finally
          //   6303	6306	6840	finally
          //   6320	6340	6840	finally
          //   6352	6365	6840	finally
          //   6377	6396	6840	finally
          //   6614	6620	6840	finally
          //   6686	6689	6840	finally
          //   6766	6771	6840	finally
          //   6837	6840	6840	finally
          //   6998	7004	6840	finally
          //   7070	7073	6840	finally
          //   7150	7155	6840	finally
          //   7221	7230	6840	finally
          //   7296	7319	6840	finally
          //   7385	7391	6840	finally
          //   7457	7464	6840	finally
          //   7530	7552	6840	finally
          //   7686	7689	6840	finally
          //   7760	7763	6840	finally
          //   5740	5753	6907	finally
          //   6467	6479	6907	finally
          //   6909	6912	6907	finally
          //   7073	7084	7615	finally
          //   7617	7620	7615	finally
          //   6689	6700	7689	finally
          //   7691	7694	7689	finally
          //   7560	7574	7763	finally
          //   7765	7768	7763	finally
          //   4199	4213	7771	finally
          //   7773	7776	7771	finally
          //   4945	4959	7779	finally
          //   7781	7784	7779	finally
          //   6177	6191	7787	finally
          //   7789	7792	7787	finally
          //   6404	6418	7795	finally
          //   7797	7800	7795	finally
          //   6850	6864	7803	finally
          //   7805	7808	7803	finally
        }
      });
      return;
    }
  }
  
  public boolean execute(String paramString, JSONArray paramJSONArray, CallbackContext paramCallbackContext)
    throws JSONException
  {
    if ((paramString.equals("upload")) || (paramString.equals("download")))
    {
      String str1 = paramJSONArray.getString(0);
      String str2 = paramJSONArray.getString(1);
      if (paramString.equals("upload"))
      {
        upload(str1, str2, paramJSONArray, paramCallbackContext);
        return true;
      }
      download(str1, str2, paramJSONArray, paramCallbackContext);
      return true;
    }
    if (paramString.equals("abort"))
    {
      abort(paramJSONArray.getString(0));
      paramCallbackContext.success();
      return true;
    }
    return false;
  }
  
  private static class ExposedGZIPInputStream
    extends GZIPInputStream
  {
    public ExposedGZIPInputStream(InputStream paramInputStream)
      throws IOException
    {
      super();
    }
    
    public Inflater getInflater()
    {
      return inf;
    }
  }
  
  private static final class RequestContext
  {
    boolean aborted;
    CallbackContext callbackContext;
    HttpURLConnection connection;
    String source;
    String target;
    File targetFile;
    
    RequestContext(String paramString1, String paramString2, CallbackContext paramCallbackContext)
    {
      source = paramString1;
      target = paramString2;
      callbackContext = paramCallbackContext;
    }
    
    void sendPluginResult(PluginResult paramPluginResult)
    {
      try
      {
        if (!aborted) {
          callbackContext.sendPluginResult(paramPluginResult);
        }
        return;
      }
      finally {}
    }
  }
  
  private static class SimpleTrackingInputStream
    extends FileTransfer.TrackingInputStream
  {
    private long bytesRead = 0L;
    
    public SimpleTrackingInputStream(InputStream paramInputStream)
    {
      super();
    }
    
    private int updateBytesRead(int paramInt)
    {
      if (paramInt != -1) {
        bytesRead += paramInt;
      }
      return paramInt;
    }
    
    public long getTotalRawBytesRead()
    {
      return bytesRead;
    }
    
    public int read()
      throws IOException
    {
      return updateBytesRead(super.read());
    }
    
    public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
      throws IOException
    {
      return updateBytesRead(super.read(paramArrayOfByte, paramInt1, paramInt2));
    }
  }
  
  private static class TrackingGZIPInputStream
    extends FileTransfer.TrackingInputStream
  {
    private FileTransfer.ExposedGZIPInputStream gzin;
    
    public TrackingGZIPInputStream(FileTransfer.ExposedGZIPInputStream paramExposedGZIPInputStream)
      throws IOException
    {
      super();
      gzin = paramExposedGZIPInputStream;
    }
    
    public long getTotalRawBytesRead()
    {
      return gzin.getInflater().getBytesRead();
    }
  }
  
  private static abstract class TrackingInputStream
    extends FilterInputStream
  {
    public TrackingInputStream(InputStream paramInputStream)
    {
      super();
    }
    
    public abstract long getTotalRawBytesRead();
  }
}

/* Location:
 * Qualified Name:     org.apache.cordova.filetransfer.FileTransfer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */