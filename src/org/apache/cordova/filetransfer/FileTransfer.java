package org.apache.cordova.filetransfer;

import android.net.Uri;
import android.util.Log;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URLConnection;
import java.net.URLDecoder;
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
    activeRequests = new HashMap();
  }
  
  private void abort(final String paramString)
  {
    synchronized (activeRequests)
    {
      paramString = (RequestContext)activeRequests.remove(paramString);
      if (paramString != null)
      {
        ??? = targetFile;
        if (??? != null) {
          ((File)???).delete();
        }
        ??? = createFileTransferError(ABORTED_ERR, source, target, null, Integer.valueOf(-1));
      }
    }
  }
  
  private static void addHeadersToRequest(URLConnection paramURLConnection, JSONObject paramJSONObject)
  {
    try
    {
      Iterator localIterator = paramJSONObject.keys();
      for (;;)
      {
        if (!localIterator.hasNext()) {
          return;
        }
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
  
  private static JSONObject createFileTransferError(int paramInt, String paramString1, String paramString2, String paramString3, Integer paramInteger)
  {
    Object localObject = null;
    try
    {
      JSONObject localJSONObject = new JSONObject();
      return localJSONObject;
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
        if (paramInteger == null) {
          break label94;
        }
        localJSONObject.put("http_status", paramInteger);
        return localJSONObject;
      }
      catch (JSONException paramString1)
      {
        for (;;)
        {
          paramString2 = localJSONObject;
        }
      }
      paramString1 = paramString1;
      paramString2 = (String)localObject;
      Log.e("FileTransfer", paramString1.getMessage(), paramString1);
      return paramString2;
    }
  }
  
  private static JSONObject createFileTransferError(int paramInt, String paramString1, String paramString2, URLConnection paramURLConnection)
  {
    int k = 0;
    j = 0;
    StringBuilder localStringBuilder = new StringBuilder();
    localObject2 = null;
    localObject1 = localObject2;
    i = j;
    if (paramURLConnection != null)
    {
      localObject1 = localObject2;
      i = j;
      j = k;
    }
    for (;;)
    {
      try
      {
        if ((paramURLConnection instanceof HttpURLConnection))
        {
          j = k;
          k = ((HttpURLConnection)paramURLConnection).getResponseCode();
          j = k;
          paramURLConnection = ((HttpURLConnection)paramURLConnection).getErrorStream();
          localObject1 = localObject2;
          i = k;
          if (paramURLConnection != null)
          {
            j = k;
            localBufferedReader = new BufferedReader(new InputStreamReader(paramURLConnection, "UTF-8"));
            j = k;
            paramURLConnection = localBufferedReader.readLine();
            if (paramURLConnection != null) {
              continue;
            }
            j = k;
            localObject1 = localStringBuilder.toString();
            i = k;
          }
        }
      }
      catch (Throwable paramURLConnection)
      {
        BufferedReader localBufferedReader;
        Log.w("FileTransfer", "Error getting HTTP status code from connection.", paramURLConnection);
        localObject1 = localObject2;
        i = j;
        continue;
      }
      return createFileTransferError(paramInt, paramString1, paramString2, (String)localObject1, Integer.valueOf(i));
      j = k;
      localStringBuilder.append(paramURLConnection);
      j = k;
      localObject1 = localBufferedReader.readLine();
      paramURLConnection = (URLConnection)localObject1;
      if (localObject1 != null)
      {
        j = k;
        localStringBuilder.append('\n');
        paramURLConnection = (URLConnection)localObject1;
      }
    }
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
        break label199;
      }
      bool1 = true;
      label110:
      if ((bool1) || (i == 5)) {
        break label205;
      }
    }
    label199:
    label205:
    for (final boolean bool2 = true;; bool2 = false)
    {
      if (i != -1) {
        break label211;
      }
      paramString1 = createFileTransferError(INVALID_URL_ERR, paramString1, paramString2, null, Integer.valueOf(0));
      Log.e("FileTransfer", "Unsupported URI: " + paramJSONArray);
      ???.sendPluginResult(new PluginResult(PluginResult.Status.IO_EXCEPTION, paramString1));
      return;
      paramJSONArray = Uri.fromFile(new File(paramString2));
      break;
      bool1 = false;
      break label110;
    }
    label211:
    if ((!bool2) && (!Config.isUrlWhiteListed(paramString1)))
    {
      Log.w("FileTransfer", "Source URL is not in white list: '" + paramString1 + "'");
      paramString1 = createFileTransferError(CONNECTION_ERR, paramString1, paramString2, null, Integer.valueOf(401));
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
          //   4: getfield 74	org/apache/cordova/filetransfer/FileTransfer$RequestContext:aborted	Z
          //   7: ifeq +4 -> 11
          //   10: return
          //   11: aconst_null
          //   12: astore 46
          //   14: aconst_null
          //   15: astore 47
          //   17: aconst_null
          //   18: astore 48
          //   20: aconst_null
          //   21: astore 49
          //   23: aconst_null
          //   24: astore 50
          //   26: aconst_null
          //   27: astore 7
          //   29: aconst_null
          //   30: astore 44
          //   32: aconst_null
          //   33: astore 32
          //   35: aconst_null
          //   36: astore 35
          //   38: aconst_null
          //   39: astore 36
          //   41: aconst_null
          //   42: astore 37
          //   44: aconst_null
          //   45: astore 38
          //   47: aconst_null
          //   48: astore 5
          //   50: aconst_null
          //   51: astore 45
          //   53: aconst_null
          //   54: astore 39
          //   56: aconst_null
          //   57: astore 40
          //   59: aconst_null
          //   60: astore 41
          //   62: aconst_null
          //   63: astore 42
          //   65: aconst_null
          //   66: astore 43
          //   68: aconst_null
          //   69: astore 4
          //   71: aconst_null
          //   72: astore 51
          //   74: aconst_null
          //   75: astore 52
          //   77: aconst_null
          //   78: astore 53
          //   80: aconst_null
          //   81: astore 54
          //   83: aconst_null
          //   84: astore 10
          //   86: aconst_null
          //   87: astore 33
          //   89: aconst_null
          //   90: astore 34
          //   92: aconst_null
          //   93: astore 29
          //   95: aconst_null
          //   96: astore 30
          //   98: aconst_null
          //   99: astore 31
          //   101: aconst_null
          //   102: astore 9
          //   104: aconst_null
          //   105: astore 28
          //   107: aload 46
          //   109: astore 20
          //   111: aload 10
          //   113: astore 24
          //   115: aload 32
          //   117: astore 11
          //   119: aload 39
          //   121: astore 12
          //   123: aload 47
          //   125: astore 21
          //   127: aload 51
          //   129: astore 25
          //   131: aload 35
          //   133: astore 13
          //   135: aload 40
          //   137: astore 14
          //   139: aload 48
          //   141: astore 22
          //   143: aload 52
          //   145: astore 26
          //   147: aload 36
          //   149: astore 15
          //   151: aload 41
          //   153: astore 16
          //   155: aload 49
          //   157: astore 23
          //   159: aload 53
          //   161: astore 27
          //   163: aload 37
          //   165: astore 17
          //   167: aload 42
          //   169: astore 18
          //   171: aload 50
          //   173: astore 6
          //   175: aload 54
          //   177: astore 8
          //   179: aload 38
          //   181: astore_2
          //   182: aload 43
          //   184: astore_3
          //   185: aload_0
          //   186: getfield 48	org/apache/cordova/filetransfer/FileTransfer$4:val$resourceApi	Lorg/apache/cordova/CordovaResourceApi;
          //   189: aload_0
          //   190: getfield 50	org/apache/cordova/filetransfer/FileTransfer$4:val$targetUri	Landroid/net/Uri;
          //   193: invokevirtual 80	org/apache/cordova/CordovaResourceApi:openOutputStream	(Landroid/net/Uri;)Ljava/io/OutputStream;
          //   196: astore 19
          //   198: aload 46
          //   200: astore 20
          //   202: aload 10
          //   204: astore 24
          //   206: aload 32
          //   208: astore 11
          //   210: aload 39
          //   212: astore 12
          //   214: aload 19
          //   216: astore 28
          //   218: aload 47
          //   220: astore 21
          //   222: aload 51
          //   224: astore 25
          //   226: aload 35
          //   228: astore 13
          //   230: aload 40
          //   232: astore 14
          //   234: aload 19
          //   236: astore 29
          //   238: aload 48
          //   240: astore 22
          //   242: aload 52
          //   244: astore 26
          //   246: aload 36
          //   248: astore 15
          //   250: aload 41
          //   252: astore 16
          //   254: aload 19
          //   256: astore 30
          //   258: aload 49
          //   260: astore 23
          //   262: aload 53
          //   264: astore 27
          //   266: aload 37
          //   268: astore 17
          //   270: aload 42
          //   272: astore 18
          //   274: aload 19
          //   276: astore 31
          //   278: aload 50
          //   280: astore 6
          //   282: aload 54
          //   284: astore 8
          //   286: aload 38
          //   288: astore_2
          //   289: aload 43
          //   291: astore_3
          //   292: aload 19
          //   294: astore 9
          //   296: aload_0
          //   297: getfield 48	org/apache/cordova/filetransfer/FileTransfer$4:val$resourceApi	Lorg/apache/cordova/CordovaResourceApi;
          //   300: aload_0
          //   301: getfield 50	org/apache/cordova/filetransfer/FileTransfer$4:val$targetUri	Landroid/net/Uri;
          //   304: invokevirtual 84	org/apache/cordova/CordovaResourceApi:mapUriToFile	(Landroid/net/Uri;)Ljava/io/File;
          //   307: astore 10
          //   309: aload 46
          //   311: astore 20
          //   313: aload 10
          //   315: astore 24
          //   317: aload 32
          //   319: astore 11
          //   321: aload 39
          //   323: astore 12
          //   325: aload 19
          //   327: astore 28
          //   329: aload 47
          //   331: astore 21
          //   333: aload 10
          //   335: astore 25
          //   337: aload 35
          //   339: astore 13
          //   341: aload 40
          //   343: astore 14
          //   345: aload 19
          //   347: astore 29
          //   349: aload 48
          //   351: astore 22
          //   353: aload 10
          //   355: astore 26
          //   357: aload 36
          //   359: astore 15
          //   361: aload 41
          //   363: astore 16
          //   365: aload 19
          //   367: astore 30
          //   369: aload 49
          //   371: astore 23
          //   373: aload 10
          //   375: astore 27
          //   377: aload 37
          //   379: astore 17
          //   381: aload 42
          //   383: astore 18
          //   385: aload 19
          //   387: astore 31
          //   389: aload 50
          //   391: astore 6
          //   393: aload 10
          //   395: astore 8
          //   397: aload 38
          //   399: astore_2
          //   400: aload 43
          //   402: astore_3
          //   403: aload 19
          //   405: astore 9
          //   407: aload_0
          //   408: getfield 36	org/apache/cordova/filetransfer/FileTransfer$4:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
          //   411: aload 10
          //   413: putfield 88	org/apache/cordova/filetransfer/FileTransfer$RequestContext:targetFile	Ljava/io/File;
          //   416: aload 46
          //   418: astore 20
          //   420: aload 10
          //   422: astore 24
          //   424: aload 32
          //   426: astore 11
          //   428: aload 39
          //   430: astore 12
          //   432: aload 19
          //   434: astore 28
          //   436: aload 47
          //   438: astore 21
          //   440: aload 10
          //   442: astore 25
          //   444: aload 35
          //   446: astore 13
          //   448: aload 40
          //   450: astore 14
          //   452: aload 19
          //   454: astore 29
          //   456: aload 48
          //   458: astore 22
          //   460: aload 10
          //   462: astore 26
          //   464: aload 36
          //   466: astore 15
          //   468: aload 41
          //   470: astore 16
          //   472: aload 19
          //   474: astore 30
          //   476: aload 49
          //   478: astore 23
          //   480: aload 10
          //   482: astore 27
          //   484: aload 37
          //   486: astore 17
          //   488: aload 42
          //   490: astore 18
          //   492: aload 19
          //   494: astore 31
          //   496: aload 50
          //   498: astore 6
          //   500: aload 10
          //   502: astore 8
          //   504: aload 38
          //   506: astore_2
          //   507: aload 43
          //   509: astore_3
          //   510: aload 19
          //   512: astore 9
          //   514: ldc 90
          //   516: new 92	java/lang/StringBuilder
          //   519: dup
          //   520: ldc 94
          //   522: invokespecial 97	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
          //   525: aload_0
          //   526: getfield 52	org/apache/cordova/filetransfer/FileTransfer$4:val$sourceUri	Landroid/net/Uri;
          //   529: invokevirtual 101	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
          //   532: invokevirtual 105	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   535: invokestatic 111	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
          //   538: pop
          //   539: aload 46
          //   541: astore 20
          //   543: aload 10
          //   545: astore 24
          //   547: aload 32
          //   549: astore 11
          //   551: aload 39
          //   553: astore 12
          //   555: aload 19
          //   557: astore 28
          //   559: aload 47
          //   561: astore 21
          //   563: aload 10
          //   565: astore 25
          //   567: aload 35
          //   569: astore 13
          //   571: aload 40
          //   573: astore 14
          //   575: aload 19
          //   577: astore 29
          //   579: aload 48
          //   581: astore 22
          //   583: aload 10
          //   585: astore 26
          //   587: aload 36
          //   589: astore 15
          //   591: aload 41
          //   593: astore 16
          //   595: aload 19
          //   597: astore 30
          //   599: aload 49
          //   601: astore 23
          //   603: aload 10
          //   605: astore 27
          //   607: aload 37
          //   609: astore 17
          //   611: aload 42
          //   613: astore 18
          //   615: aload 19
          //   617: astore 31
          //   619: aload 50
          //   621: astore 6
          //   623: aload 10
          //   625: astore 8
          //   627: aload 38
          //   629: astore_2
          //   630: aload 43
          //   632: astore_3
          //   633: aload 19
          //   635: astore 9
          //   637: new 113	org/apache/cordova/filetransfer/FileProgressResult
          //   640: dup
          //   641: invokespecial 114	org/apache/cordova/filetransfer/FileProgressResult:<init>	()V
          //   644: astore 51
          //   646: aload 46
          //   648: astore 20
          //   650: aload 10
          //   652: astore 24
          //   654: aload 32
          //   656: astore 11
          //   658: aload 39
          //   660: astore 12
          //   662: aload 19
          //   664: astore 28
          //   666: aload 47
          //   668: astore 21
          //   670: aload 10
          //   672: astore 25
          //   674: aload 35
          //   676: astore 13
          //   678: aload 40
          //   680: astore 14
          //   682: aload 19
          //   684: astore 29
          //   686: aload 48
          //   688: astore 22
          //   690: aload 10
          //   692: astore 26
          //   694: aload 36
          //   696: astore 15
          //   698: aload 41
          //   700: astore 16
          //   702: aload 19
          //   704: astore 30
          //   706: aload 49
          //   708: astore 23
          //   710: aload 10
          //   712: astore 27
          //   714: aload 37
          //   716: astore 17
          //   718: aload 42
          //   720: astore 18
          //   722: aload 19
          //   724: astore 31
          //   726: aload 50
          //   728: astore 6
          //   730: aload 10
          //   732: astore 8
          //   734: aload 38
          //   736: astore_2
          //   737: aload 43
          //   739: astore_3
          //   740: aload 19
          //   742: astore 9
          //   744: aload_0
          //   745: getfield 54	org/apache/cordova/filetransfer/FileTransfer$4:val$isLocalTransfer	Z
          //   748: ifeq +1007 -> 1755
          //   751: aload 46
          //   753: astore 20
          //   755: aload 10
          //   757: astore 24
          //   759: aload 32
          //   761: astore 11
          //   763: aload 39
          //   765: astore 12
          //   767: aload 19
          //   769: astore 28
          //   771: aload 47
          //   773: astore 21
          //   775: aload 10
          //   777: astore 25
          //   779: aload 35
          //   781: astore 13
          //   783: aload 40
          //   785: astore 14
          //   787: aload 19
          //   789: astore 29
          //   791: aload 48
          //   793: astore 22
          //   795: aload 10
          //   797: astore 26
          //   799: aload 36
          //   801: astore 15
          //   803: aload 41
          //   805: astore 16
          //   807: aload 19
          //   809: astore 30
          //   811: aload 49
          //   813: astore 23
          //   815: aload 10
          //   817: astore 27
          //   819: aload 37
          //   821: astore 17
          //   823: aload 42
          //   825: astore 18
          //   827: aload 19
          //   829: astore 31
          //   831: aload 50
          //   833: astore 6
          //   835: aload 10
          //   837: astore 8
          //   839: aload 38
          //   841: astore_2
          //   842: aload 43
          //   844: astore_3
          //   845: aload 19
          //   847: astore 9
          //   849: aload_0
          //   850: getfield 48	org/apache/cordova/filetransfer/FileTransfer$4:val$resourceApi	Lorg/apache/cordova/CordovaResourceApi;
          //   853: aload_0
          //   854: getfield 52	org/apache/cordova/filetransfer/FileTransfer$4:val$sourceUri	Landroid/net/Uri;
          //   857: invokevirtual 118	org/apache/cordova/CordovaResourceApi:openForRead	(Landroid/net/Uri;)Lorg/apache/cordova/CordovaResourceApi$OpenForReadResult;
          //   860: astore 44
          //   862: aload 46
          //   864: astore 20
          //   866: aload 10
          //   868: astore 24
          //   870: aload 32
          //   872: astore 11
          //   874: aload 39
          //   876: astore 12
          //   878: aload 19
          //   880: astore 28
          //   882: aload 47
          //   884: astore 21
          //   886: aload 10
          //   888: astore 25
          //   890: aload 35
          //   892: astore 13
          //   894: aload 40
          //   896: astore 14
          //   898: aload 19
          //   900: astore 29
          //   902: aload 48
          //   904: astore 22
          //   906: aload 10
          //   908: astore 26
          //   910: aload 36
          //   912: astore 15
          //   914: aload 41
          //   916: astore 16
          //   918: aload 19
          //   920: astore 30
          //   922: aload 49
          //   924: astore 23
          //   926: aload 10
          //   928: astore 27
          //   930: aload 37
          //   932: astore 17
          //   934: aload 42
          //   936: astore 18
          //   938: aload 19
          //   940: astore 31
          //   942: aload 50
          //   944: astore 6
          //   946: aload 10
          //   948: astore 8
          //   950: aload 38
          //   952: astore_2
          //   953: aload 43
          //   955: astore_3
          //   956: aload 19
          //   958: astore 9
          //   960: aload 44
          //   962: getfield 124	org/apache/cordova/CordovaResourceApi$OpenForReadResult:length	J
          //   965: ldc2_w 125
          //   968: lcmp
          //   969: ifeq +215 -> 1184
          //   972: aload 46
          //   974: astore 20
          //   976: aload 10
          //   978: astore 24
          //   980: aload 32
          //   982: astore 11
          //   984: aload 39
          //   986: astore 12
          //   988: aload 19
          //   990: astore 28
          //   992: aload 47
          //   994: astore 21
          //   996: aload 10
          //   998: astore 25
          //   1000: aload 35
          //   1002: astore 13
          //   1004: aload 40
          //   1006: astore 14
          //   1008: aload 19
          //   1010: astore 29
          //   1012: aload 48
          //   1014: astore 22
          //   1016: aload 10
          //   1018: astore 26
          //   1020: aload 36
          //   1022: astore 15
          //   1024: aload 41
          //   1026: astore 16
          //   1028: aload 19
          //   1030: astore 30
          //   1032: aload 49
          //   1034: astore 23
          //   1036: aload 10
          //   1038: astore 27
          //   1040: aload 37
          //   1042: astore 17
          //   1044: aload 42
          //   1046: astore 18
          //   1048: aload 19
          //   1050: astore 31
          //   1052: aload 50
          //   1054: astore 6
          //   1056: aload 10
          //   1058: astore 8
          //   1060: aload 38
          //   1062: astore_2
          //   1063: aload 43
          //   1065: astore_3
          //   1066: aload 19
          //   1068: astore 9
          //   1070: aload 51
          //   1072: iconst_1
          //   1073: invokevirtual 130	org/apache/cordova/filetransfer/FileProgressResult:setLengthComputable	(Z)V
          //   1076: aload 46
          //   1078: astore 20
          //   1080: aload 10
          //   1082: astore 24
          //   1084: aload 32
          //   1086: astore 11
          //   1088: aload 39
          //   1090: astore 12
          //   1092: aload 19
          //   1094: astore 28
          //   1096: aload 47
          //   1098: astore 21
          //   1100: aload 10
          //   1102: astore 25
          //   1104: aload 35
          //   1106: astore 13
          //   1108: aload 40
          //   1110: astore 14
          //   1112: aload 19
          //   1114: astore 29
          //   1116: aload 48
          //   1118: astore 22
          //   1120: aload 10
          //   1122: astore 26
          //   1124: aload 36
          //   1126: astore 15
          //   1128: aload 41
          //   1130: astore 16
          //   1132: aload 19
          //   1134: astore 30
          //   1136: aload 49
          //   1138: astore 23
          //   1140: aload 10
          //   1142: astore 27
          //   1144: aload 37
          //   1146: astore 17
          //   1148: aload 42
          //   1150: astore 18
          //   1152: aload 19
          //   1154: astore 31
          //   1156: aload 50
          //   1158: astore 6
          //   1160: aload 10
          //   1162: astore 8
          //   1164: aload 38
          //   1166: astore_2
          //   1167: aload 43
          //   1169: astore_3
          //   1170: aload 19
          //   1172: astore 9
          //   1174: aload 51
          //   1176: aload 44
          //   1178: getfield 124	org/apache/cordova/CordovaResourceApi$OpenForReadResult:length	J
          //   1181: invokevirtual 134	org/apache/cordova/filetransfer/FileProgressResult:setTotal	(J)V
          //   1184: aload 46
          //   1186: astore 20
          //   1188: aload 10
          //   1190: astore 24
          //   1192: aload 32
          //   1194: astore 11
          //   1196: aload 39
          //   1198: astore 12
          //   1200: aload 19
          //   1202: astore 28
          //   1204: aload 47
          //   1206: astore 21
          //   1208: aload 10
          //   1210: astore 25
          //   1212: aload 35
          //   1214: astore 13
          //   1216: aload 40
          //   1218: astore 14
          //   1220: aload 19
          //   1222: astore 29
          //   1224: aload 48
          //   1226: astore 22
          //   1228: aload 10
          //   1230: astore 26
          //   1232: aload 36
          //   1234: astore 15
          //   1236: aload 41
          //   1238: astore 16
          //   1240: aload 19
          //   1242: astore 30
          //   1244: aload 49
          //   1246: astore 23
          //   1248: aload 10
          //   1250: astore 27
          //   1252: aload 37
          //   1254: astore 17
          //   1256: aload 42
          //   1258: astore 18
          //   1260: aload 19
          //   1262: astore 31
          //   1264: aload 50
          //   1266: astore 6
          //   1268: aload 10
          //   1270: astore 8
          //   1272: aload 38
          //   1274: astore_2
          //   1275: aload 43
          //   1277: astore_3
          //   1278: aload 19
          //   1280: astore 9
          //   1282: new 136	org/apache/cordova/filetransfer/FileTransfer$SimpleTrackingInputStream
          //   1285: dup
          //   1286: aload 44
          //   1288: getfield 140	org/apache/cordova/CordovaResourceApi$OpenForReadResult:inputStream	Ljava/io/InputStream;
          //   1291: invokespecial 143	org/apache/cordova/filetransfer/FileTransfer$SimpleTrackingInputStream:<init>	(Ljava/io/InputStream;)V
          //   1294: astore 32
          //   1296: aload_0
          //   1297: getfield 36	org/apache/cordova/filetransfer/FileTransfer$4:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
          //   1300: astore_2
          //   1301: aload_2
          //   1302: monitorenter
          //   1303: aload_0
          //   1304: getfield 36	org/apache/cordova/filetransfer/FileTransfer$4:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
          //   1307: getfield 74	org/apache/cordova/filetransfer/FileTransfer$RequestContext:aborted	Z
          //   1310: ifeq +2498 -> 3808
          //   1313: aload_2
          //   1314: monitorexit
          //   1315: aload 7
          //   1317: astore 20
          //   1319: aload 10
          //   1321: astore 24
          //   1323: aload 5
          //   1325: astore 11
          //   1327: aload 4
          //   1329: astore 12
          //   1331: aload 19
          //   1333: astore 28
          //   1335: aload 7
          //   1337: astore 21
          //   1339: aload 10
          //   1341: astore 25
          //   1343: aload 5
          //   1345: astore 13
          //   1347: aload 4
          //   1349: astore 14
          //   1351: aload 19
          //   1353: astore 29
          //   1355: aload 7
          //   1357: astore 22
          //   1359: aload 10
          //   1361: astore 26
          //   1363: aload 5
          //   1365: astore 15
          //   1367: aload 4
          //   1369: astore 16
          //   1371: aload 19
          //   1373: astore 30
          //   1375: aload 7
          //   1377: astore 23
          //   1379: aload 10
          //   1381: astore 27
          //   1383: aload 5
          //   1385: astore 17
          //   1387: aload 4
          //   1389: astore 18
          //   1391: aload 19
          //   1393: astore 31
          //   1395: aload 7
          //   1397: astore 6
          //   1399: aload 10
          //   1401: astore 8
          //   1403: aload 5
          //   1405: astore_2
          //   1406: aload 4
          //   1408: astore_3
          //   1409: aload 19
          //   1411: astore 9
          //   1413: aload_0
          //   1414: getfield 36	org/apache/cordova/filetransfer/FileTransfer$4:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
          //   1417: aconst_null
          //   1418: putfield 146	org/apache/cordova/filetransfer/FileTransfer$RequestContext:currentInputStream	Ljava/io/InputStream;
          //   1421: aload 7
          //   1423: astore 20
          //   1425: aload 10
          //   1427: astore 24
          //   1429: aload 5
          //   1431: astore 11
          //   1433: aload 4
          //   1435: astore 12
          //   1437: aload 19
          //   1439: astore 28
          //   1441: aload 7
          //   1443: astore 21
          //   1445: aload 10
          //   1447: astore 25
          //   1449: aload 5
          //   1451: astore 13
          //   1453: aload 4
          //   1455: astore 14
          //   1457: aload 19
          //   1459: astore 29
          //   1461: aload 7
          //   1463: astore 22
          //   1465: aload 10
          //   1467: astore 26
          //   1469: aload 5
          //   1471: astore 15
          //   1473: aload 4
          //   1475: astore 16
          //   1477: aload 19
          //   1479: astore 30
          //   1481: aload 7
          //   1483: astore 23
          //   1485: aload 10
          //   1487: astore 27
          //   1489: aload 5
          //   1491: astore 17
          //   1493: aload 4
          //   1495: astore 18
          //   1497: aload 19
          //   1499: astore 31
          //   1501: aload 7
          //   1503: astore 6
          //   1505: aload 10
          //   1507: astore 8
          //   1509: aload 5
          //   1511: astore_2
          //   1512: aload 4
          //   1514: astore_3
          //   1515: aload 19
          //   1517: astore 9
          //   1519: aload 32
          //   1521: invokestatic 150	org/apache/cordova/filetransfer/FileTransfer:access$4	(Ljava/io/Closeable;)V
          //   1524: aload 7
          //   1526: astore 20
          //   1528: aload 10
          //   1530: astore 24
          //   1532: aload 5
          //   1534: astore 11
          //   1536: aload 4
          //   1538: astore 12
          //   1540: aload 19
          //   1542: astore 28
          //   1544: aload 7
          //   1546: astore 21
          //   1548: aload 10
          //   1550: astore 25
          //   1552: aload 5
          //   1554: astore 13
          //   1556: aload 4
          //   1558: astore 14
          //   1560: aload 19
          //   1562: astore 29
          //   1564: aload 7
          //   1566: astore 22
          //   1568: aload 10
          //   1570: astore 26
          //   1572: aload 5
          //   1574: astore 15
          //   1576: aload 4
          //   1578: astore 16
          //   1580: aload 19
          //   1582: astore 30
          //   1584: aload 7
          //   1586: astore 23
          //   1588: aload 10
          //   1590: astore 27
          //   1592: aload 5
          //   1594: astore 17
          //   1596: aload 4
          //   1598: astore 18
          //   1600: aload 19
          //   1602: astore 31
          //   1604: aload 7
          //   1606: astore 6
          //   1608: aload 10
          //   1610: astore 8
          //   1612: aload 5
          //   1614: astore_2
          //   1615: aload 4
          //   1617: astore_3
          //   1618: aload 19
          //   1620: astore 9
          //   1622: aload 19
          //   1624: invokestatic 150	org/apache/cordova/filetransfer/FileTransfer:access$4	(Ljava/io/Closeable;)V
          //   1627: aload 19
          //   1629: invokestatic 150	org/apache/cordova/filetransfer/FileTransfer:access$4	(Ljava/io/Closeable;)V
          //   1632: invokestatic 154	org/apache/cordova/filetransfer/FileTransfer:access$0	()Ljava/util/HashMap;
          //   1635: astore_2
          //   1636: aload_2
          //   1637: monitorenter
          //   1638: invokestatic 154	org/apache/cordova/filetransfer/FileTransfer:access$0	()Ljava/util/HashMap;
          //   1641: aload_0
          //   1642: getfield 38	org/apache/cordova/filetransfer/FileTransfer$4:val$objectId	Ljava/lang/String;
          //   1645: invokevirtual 160	java/util/HashMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
          //   1648: pop
          //   1649: aload_2
          //   1650: monitorexit
          //   1651: aload 7
          //   1653: ifnull +35 -> 1688
          //   1656: aload_0
          //   1657: getfield 40	org/apache/cordova/filetransfer/FileTransfer$4:val$trustEveryone	Z
          //   1660: ifeq +28 -> 1688
          //   1663: aload_0
          //   1664: getfield 42	org/apache/cordova/filetransfer/FileTransfer$4:val$useHttps	Z
          //   1667: ifeq +21 -> 1688
          //   1670: aload 7
          //   1672: checkcast 162	javax/net/ssl/HttpsURLConnection
          //   1675: astore_2
          //   1676: aload_2
          //   1677: aload 5
          //   1679: invokevirtual 166	javax/net/ssl/HttpsURLConnection:setHostnameVerifier	(Ljavax/net/ssl/HostnameVerifier;)V
          //   1682: aload_2
          //   1683: aload 4
          //   1685: invokevirtual 170	javax/net/ssl/HttpsURLConnection:setSSLSocketFactory	(Ljavax/net/ssl/SSLSocketFactory;)V
          //   1688: aload 34
          //   1690: astore_2
          //   1691: iconst_0
          //   1692: ifne +30 -> 1722
          //   1695: new 172	org/apache/cordova/PluginResult
          //   1698: dup
          //   1699: getstatic 178	org/apache/cordova/PluginResult$Status:ERROR	Lorg/apache/cordova/PluginResult$Status;
          //   1702: getstatic 182	org/apache/cordova/filetransfer/FileTransfer:CONNECTION_ERR	I
          //   1705: aload_0
          //   1706: getfield 44	org/apache/cordova/filetransfer/FileTransfer$4:val$source	Ljava/lang/String;
          //   1709: aload_0
          //   1710: getfield 46	org/apache/cordova/filetransfer/FileTransfer$4:val$target	Ljava/lang/String;
          //   1713: aload 7
          //   1715: invokestatic 186	org/apache/cordova/filetransfer/FileTransfer:access$6	(ILjava/lang/String;Ljava/lang/String;Ljava/net/URLConnection;)Lorg/json/JSONObject;
          //   1718: invokespecial 189	org/apache/cordova/PluginResult:<init>	(Lorg/apache/cordova/PluginResult$Status;Lorg/json/JSONObject;)V
          //   1721: astore_2
          //   1722: aload_2
          //   1723: invokevirtual 193	org/apache/cordova/PluginResult:getStatus	()I
          //   1726: getstatic 196	org/apache/cordova/PluginResult$Status:OK	Lorg/apache/cordova/PluginResult$Status;
          //   1729: invokevirtual 199	org/apache/cordova/PluginResult$Status:ordinal	()I
          //   1732: if_icmpeq +14 -> 1746
          //   1735: aload 10
          //   1737: ifnull +9 -> 1746
          //   1740: aload 10
          //   1742: invokevirtual 205	java/io/File:delete	()Z
          //   1745: pop
          //   1746: aload_0
          //   1747: getfield 36	org/apache/cordova/filetransfer/FileTransfer$4:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
          //   1750: aload_2
          //   1751: invokevirtual 209	org/apache/cordova/filetransfer/FileTransfer$RequestContext:sendPluginResult	(Lorg/apache/cordova/PluginResult;)V
          //   1754: return
          //   1755: aload 46
          //   1757: astore 20
          //   1759: aload 10
          //   1761: astore 24
          //   1763: aload 32
          //   1765: astore 11
          //   1767: aload 39
          //   1769: astore 12
          //   1771: aload 19
          //   1773: astore 28
          //   1775: aload 47
          //   1777: astore 21
          //   1779: aload 10
          //   1781: astore 25
          //   1783: aload 35
          //   1785: astore 13
          //   1787: aload 40
          //   1789: astore 14
          //   1791: aload 19
          //   1793: astore 29
          //   1795: aload 48
          //   1797: astore 22
          //   1799: aload 10
          //   1801: astore 26
          //   1803: aload 36
          //   1805: astore 15
          //   1807: aload 41
          //   1809: astore 16
          //   1811: aload 19
          //   1813: astore 30
          //   1815: aload 49
          //   1817: astore 23
          //   1819: aload 10
          //   1821: astore 27
          //   1823: aload 37
          //   1825: astore 17
          //   1827: aload 42
          //   1829: astore 18
          //   1831: aload 19
          //   1833: astore 31
          //   1835: aload 50
          //   1837: astore 6
          //   1839: aload 10
          //   1841: astore 8
          //   1843: aload 38
          //   1845: astore_2
          //   1846: aload 43
          //   1848: astore_3
          //   1849: aload 19
          //   1851: astore 9
          //   1853: aload_0
          //   1854: getfield 48	org/apache/cordova/filetransfer/FileTransfer$4:val$resourceApi	Lorg/apache/cordova/CordovaResourceApi;
          //   1857: aload_0
          //   1858: getfield 52	org/apache/cordova/filetransfer/FileTransfer$4:val$sourceUri	Landroid/net/Uri;
          //   1861: invokevirtual 213	org/apache/cordova/CordovaResourceApi:createHttpConnection	(Landroid/net/Uri;)Ljava/net/HttpURLConnection;
          //   1864: astore 7
          //   1866: aload 44
          //   1868: astore 5
          //   1870: aload 45
          //   1872: astore 4
          //   1874: aload 7
          //   1876: astore 20
          //   1878: aload 10
          //   1880: astore 24
          //   1882: aload 32
          //   1884: astore 11
          //   1886: aload 39
          //   1888: astore 12
          //   1890: aload 19
          //   1892: astore 28
          //   1894: aload 7
          //   1896: astore 21
          //   1898: aload 10
          //   1900: astore 25
          //   1902: aload 35
          //   1904: astore 13
          //   1906: aload 40
          //   1908: astore 14
          //   1910: aload 19
          //   1912: astore 29
          //   1914: aload 7
          //   1916: astore 22
          //   1918: aload 10
          //   1920: astore 26
          //   1922: aload 36
          //   1924: astore 15
          //   1926: aload 41
          //   1928: astore 16
          //   1930: aload 19
          //   1932: astore 30
          //   1934: aload 7
          //   1936: astore 23
          //   1938: aload 10
          //   1940: astore 27
          //   1942: aload 37
          //   1944: astore 17
          //   1946: aload 42
          //   1948: astore 18
          //   1950: aload 19
          //   1952: astore 31
          //   1954: aload 7
          //   1956: astore 6
          //   1958: aload 10
          //   1960: astore 8
          //   1962: aload 38
          //   1964: astore_2
          //   1965: aload 43
          //   1967: astore_3
          //   1968: aload 19
          //   1970: astore 9
          //   1972: aload_0
          //   1973: getfield 42	org/apache/cordova/filetransfer/FileTransfer$4:val$useHttps	Z
          //   1976: ifeq +537 -> 2513
          //   1979: aload 44
          //   1981: astore 5
          //   1983: aload 45
          //   1985: astore 4
          //   1987: aload 7
          //   1989: astore 20
          //   1991: aload 10
          //   1993: astore 24
          //   1995: aload 32
          //   1997: astore 11
          //   1999: aload 39
          //   2001: astore 12
          //   2003: aload 19
          //   2005: astore 28
          //   2007: aload 7
          //   2009: astore 21
          //   2011: aload 10
          //   2013: astore 25
          //   2015: aload 35
          //   2017: astore 13
          //   2019: aload 40
          //   2021: astore 14
          //   2023: aload 19
          //   2025: astore 29
          //   2027: aload 7
          //   2029: astore 22
          //   2031: aload 10
          //   2033: astore 26
          //   2035: aload 36
          //   2037: astore 15
          //   2039: aload 41
          //   2041: astore 16
          //   2043: aload 19
          //   2045: astore 30
          //   2047: aload 7
          //   2049: astore 23
          //   2051: aload 10
          //   2053: astore 27
          //   2055: aload 37
          //   2057: astore 17
          //   2059: aload 42
          //   2061: astore 18
          //   2063: aload 19
          //   2065: astore 31
          //   2067: aload 7
          //   2069: astore 6
          //   2071: aload 10
          //   2073: astore 8
          //   2075: aload 38
          //   2077: astore_2
          //   2078: aload 43
          //   2080: astore_3
          //   2081: aload 19
          //   2083: astore 9
          //   2085: aload_0
          //   2086: getfield 40	org/apache/cordova/filetransfer/FileTransfer$4:val$trustEveryone	Z
          //   2089: ifeq +424 -> 2513
          //   2092: aload 7
          //   2094: astore 20
          //   2096: aload 10
          //   2098: astore 24
          //   2100: aload 32
          //   2102: astore 11
          //   2104: aload 39
          //   2106: astore 12
          //   2108: aload 19
          //   2110: astore 28
          //   2112: aload 7
          //   2114: astore 21
          //   2116: aload 10
          //   2118: astore 25
          //   2120: aload 35
          //   2122: astore 13
          //   2124: aload 40
          //   2126: astore 14
          //   2128: aload 19
          //   2130: astore 29
          //   2132: aload 7
          //   2134: astore 22
          //   2136: aload 10
          //   2138: astore 26
          //   2140: aload 36
          //   2142: astore 15
          //   2144: aload 41
          //   2146: astore 16
          //   2148: aload 19
          //   2150: astore 30
          //   2152: aload 7
          //   2154: astore 23
          //   2156: aload 10
          //   2158: astore 27
          //   2160: aload 37
          //   2162: astore 17
          //   2164: aload 42
          //   2166: astore 18
          //   2168: aload 19
          //   2170: astore 31
          //   2172: aload 7
          //   2174: astore 6
          //   2176: aload 10
          //   2178: astore 8
          //   2180: aload 38
          //   2182: astore_2
          //   2183: aload 43
          //   2185: astore_3
          //   2186: aload 19
          //   2188: astore 9
          //   2190: aload 7
          //   2192: checkcast 162	javax/net/ssl/HttpsURLConnection
          //   2195: astore 44
          //   2197: aload 7
          //   2199: astore 20
          //   2201: aload 10
          //   2203: astore 24
          //   2205: aload 32
          //   2207: astore 11
          //   2209: aload 39
          //   2211: astore 12
          //   2213: aload 19
          //   2215: astore 28
          //   2217: aload 7
          //   2219: astore 21
          //   2221: aload 10
          //   2223: astore 25
          //   2225: aload 35
          //   2227: astore 13
          //   2229: aload 40
          //   2231: astore 14
          //   2233: aload 19
          //   2235: astore 29
          //   2237: aload 7
          //   2239: astore 22
          //   2241: aload 10
          //   2243: astore 26
          //   2245: aload 36
          //   2247: astore 15
          //   2249: aload 41
          //   2251: astore 16
          //   2253: aload 19
          //   2255: astore 30
          //   2257: aload 7
          //   2259: astore 23
          //   2261: aload 10
          //   2263: astore 27
          //   2265: aload 37
          //   2267: astore 17
          //   2269: aload 42
          //   2271: astore 18
          //   2273: aload 19
          //   2275: astore 31
          //   2277: aload 7
          //   2279: astore 6
          //   2281: aload 10
          //   2283: astore 8
          //   2285: aload 38
          //   2287: astore_2
          //   2288: aload 43
          //   2290: astore_3
          //   2291: aload 19
          //   2293: astore 9
          //   2295: aload 44
          //   2297: invokestatic 217	org/apache/cordova/filetransfer/FileTransfer:access$1	(Ljavax/net/ssl/HttpsURLConnection;)Ljavax/net/ssl/SSLSocketFactory;
          //   2300: astore 4
          //   2302: aload 7
          //   2304: astore 20
          //   2306: aload 10
          //   2308: astore 24
          //   2310: aload 32
          //   2312: astore 11
          //   2314: aload 4
          //   2316: astore 12
          //   2318: aload 19
          //   2320: astore 28
          //   2322: aload 7
          //   2324: astore 21
          //   2326: aload 10
          //   2328: astore 25
          //   2330: aload 35
          //   2332: astore 13
          //   2334: aload 4
          //   2336: astore 14
          //   2338: aload 19
          //   2340: astore 29
          //   2342: aload 7
          //   2344: astore 22
          //   2346: aload 10
          //   2348: astore 26
          //   2350: aload 36
          //   2352: astore 15
          //   2354: aload 4
          //   2356: astore 16
          //   2358: aload 19
          //   2360: astore 30
          //   2362: aload 7
          //   2364: astore 23
          //   2366: aload 10
          //   2368: astore 27
          //   2370: aload 37
          //   2372: astore 17
          //   2374: aload 4
          //   2376: astore 18
          //   2378: aload 19
          //   2380: astore 31
          //   2382: aload 7
          //   2384: astore 6
          //   2386: aload 10
          //   2388: astore 8
          //   2390: aload 38
          //   2392: astore_2
          //   2393: aload 4
          //   2395: astore_3
          //   2396: aload 19
          //   2398: astore 9
          //   2400: aload 44
          //   2402: invokevirtual 221	javax/net/ssl/HttpsURLConnection:getHostnameVerifier	()Ljavax/net/ssl/HostnameVerifier;
          //   2405: astore 5
          //   2407: aload 7
          //   2409: astore 20
          //   2411: aload 10
          //   2413: astore 24
          //   2415: aload 5
          //   2417: astore 11
          //   2419: aload 4
          //   2421: astore 12
          //   2423: aload 19
          //   2425: astore 28
          //   2427: aload 7
          //   2429: astore 21
          //   2431: aload 10
          //   2433: astore 25
          //   2435: aload 5
          //   2437: astore 13
          //   2439: aload 4
          //   2441: astore 14
          //   2443: aload 19
          //   2445: astore 29
          //   2447: aload 7
          //   2449: astore 22
          //   2451: aload 10
          //   2453: astore 26
          //   2455: aload 5
          //   2457: astore 15
          //   2459: aload 4
          //   2461: astore 16
          //   2463: aload 19
          //   2465: astore 30
          //   2467: aload 7
          //   2469: astore 23
          //   2471: aload 10
          //   2473: astore 27
          //   2475: aload 5
          //   2477: astore 17
          //   2479: aload 4
          //   2481: astore 18
          //   2483: aload 19
          //   2485: astore 31
          //   2487: aload 7
          //   2489: astore 6
          //   2491: aload 10
          //   2493: astore 8
          //   2495: aload 5
          //   2497: astore_2
          //   2498: aload 4
          //   2500: astore_3
          //   2501: aload 19
          //   2503: astore 9
          //   2505: aload 44
          //   2507: invokestatic 224	org/apache/cordova/filetransfer/FileTransfer:access$2	()Ljavax/net/ssl/HostnameVerifier;
          //   2510: invokevirtual 166	javax/net/ssl/HttpsURLConnection:setHostnameVerifier	(Ljavax/net/ssl/HostnameVerifier;)V
          //   2513: aload 7
          //   2515: astore 20
          //   2517: aload 10
          //   2519: astore 24
          //   2521: aload 5
          //   2523: astore 11
          //   2525: aload 4
          //   2527: astore 12
          //   2529: aload 19
          //   2531: astore 28
          //   2533: aload 7
          //   2535: astore 21
          //   2537: aload 10
          //   2539: astore 25
          //   2541: aload 5
          //   2543: astore 13
          //   2545: aload 4
          //   2547: astore 14
          //   2549: aload 19
          //   2551: astore 29
          //   2553: aload 7
          //   2555: astore 22
          //   2557: aload 10
          //   2559: astore 26
          //   2561: aload 5
          //   2563: astore 15
          //   2565: aload 4
          //   2567: astore 16
          //   2569: aload 19
          //   2571: astore 30
          //   2573: aload 7
          //   2575: astore 23
          //   2577: aload 10
          //   2579: astore 27
          //   2581: aload 5
          //   2583: astore 17
          //   2585: aload 4
          //   2587: astore 18
          //   2589: aload 19
          //   2591: astore 31
          //   2593: aload 7
          //   2595: astore 6
          //   2597: aload 10
          //   2599: astore 8
          //   2601: aload 5
          //   2603: astore_2
          //   2604: aload 4
          //   2606: astore_3
          //   2607: aload 19
          //   2609: astore 9
          //   2611: aload 7
          //   2613: ldc -30
          //   2615: invokevirtual 231	java/net/HttpURLConnection:setRequestMethod	(Ljava/lang/String;)V
          //   2618: aload 7
          //   2620: astore 20
          //   2622: aload 10
          //   2624: astore 24
          //   2626: aload 5
          //   2628: astore 11
          //   2630: aload 4
          //   2632: astore 12
          //   2634: aload 19
          //   2636: astore 28
          //   2638: aload 7
          //   2640: astore 21
          //   2642: aload 10
          //   2644: astore 25
          //   2646: aload 5
          //   2648: astore 13
          //   2650: aload 4
          //   2652: astore 14
          //   2654: aload 19
          //   2656: astore 29
          //   2658: aload 7
          //   2660: astore 22
          //   2662: aload 10
          //   2664: astore 26
          //   2666: aload 5
          //   2668: astore 15
          //   2670: aload 4
          //   2672: astore 16
          //   2674: aload 19
          //   2676: astore 30
          //   2678: aload 7
          //   2680: astore 23
          //   2682: aload 10
          //   2684: astore 27
          //   2686: aload 5
          //   2688: astore 17
          //   2690: aload 4
          //   2692: astore 18
          //   2694: aload 19
          //   2696: astore 31
          //   2698: aload 7
          //   2700: astore 6
          //   2702: aload 10
          //   2704: astore 8
          //   2706: aload 5
          //   2708: astore_2
          //   2709: aload 4
          //   2711: astore_3
          //   2712: aload 19
          //   2714: astore 9
          //   2716: invokestatic 237	android/webkit/CookieManager:getInstance	()Landroid/webkit/CookieManager;
          //   2719: aload_0
          //   2720: getfield 52	org/apache/cordova/filetransfer/FileTransfer$4:val$sourceUri	Landroid/net/Uri;
          //   2723: invokevirtual 240	android/net/Uri:toString	()Ljava/lang/String;
          //   2726: invokevirtual 244	android/webkit/CookieManager:getCookie	(Ljava/lang/String;)Ljava/lang/String;
          //   2729: astore 32
          //   2731: aload 32
          //   2733: ifnull +110 -> 2843
          //   2736: aload 7
          //   2738: astore 20
          //   2740: aload 10
          //   2742: astore 24
          //   2744: aload 5
          //   2746: astore 11
          //   2748: aload 4
          //   2750: astore 12
          //   2752: aload 19
          //   2754: astore 28
          //   2756: aload 7
          //   2758: astore 21
          //   2760: aload 10
          //   2762: astore 25
          //   2764: aload 5
          //   2766: astore 13
          //   2768: aload 4
          //   2770: astore 14
          //   2772: aload 19
          //   2774: astore 29
          //   2776: aload 7
          //   2778: astore 22
          //   2780: aload 10
          //   2782: astore 26
          //   2784: aload 5
          //   2786: astore 15
          //   2788: aload 4
          //   2790: astore 16
          //   2792: aload 19
          //   2794: astore 30
          //   2796: aload 7
          //   2798: astore 23
          //   2800: aload 10
          //   2802: astore 27
          //   2804: aload 5
          //   2806: astore 17
          //   2808: aload 4
          //   2810: astore 18
          //   2812: aload 19
          //   2814: astore 31
          //   2816: aload 7
          //   2818: astore 6
          //   2820: aload 10
          //   2822: astore 8
          //   2824: aload 5
          //   2826: astore_2
          //   2827: aload 4
          //   2829: astore_3
          //   2830: aload 19
          //   2832: astore 9
          //   2834: aload 7
          //   2836: ldc -10
          //   2838: aload 32
          //   2840: invokevirtual 250	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
          //   2843: aload 7
          //   2845: astore 20
          //   2847: aload 10
          //   2849: astore 24
          //   2851: aload 5
          //   2853: astore 11
          //   2855: aload 4
          //   2857: astore 12
          //   2859: aload 19
          //   2861: astore 28
          //   2863: aload 7
          //   2865: astore 21
          //   2867: aload 10
          //   2869: astore 25
          //   2871: aload 5
          //   2873: astore 13
          //   2875: aload 4
          //   2877: astore 14
          //   2879: aload 19
          //   2881: astore 29
          //   2883: aload 7
          //   2885: astore 22
          //   2887: aload 10
          //   2889: astore 26
          //   2891: aload 5
          //   2893: astore 15
          //   2895: aload 4
          //   2897: astore 16
          //   2899: aload 19
          //   2901: astore 30
          //   2903: aload 7
          //   2905: astore 23
          //   2907: aload 10
          //   2909: astore 27
          //   2911: aload 5
          //   2913: astore 17
          //   2915: aload 4
          //   2917: astore 18
          //   2919: aload 19
          //   2921: astore 31
          //   2923: aload 7
          //   2925: astore 6
          //   2927: aload 10
          //   2929: astore 8
          //   2931: aload 5
          //   2933: astore_2
          //   2934: aload 4
          //   2936: astore_3
          //   2937: aload 19
          //   2939: astore 9
          //   2941: aload 7
          //   2943: ldc -4
          //   2945: ldc -2
          //   2947: invokevirtual 250	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
          //   2950: aload 7
          //   2952: astore 20
          //   2954: aload 10
          //   2956: astore 24
          //   2958: aload 5
          //   2960: astore 11
          //   2962: aload 4
          //   2964: astore 12
          //   2966: aload 19
          //   2968: astore 28
          //   2970: aload 7
          //   2972: astore 21
          //   2974: aload 10
          //   2976: astore 25
          //   2978: aload 5
          //   2980: astore 13
          //   2982: aload 4
          //   2984: astore 14
          //   2986: aload 19
          //   2988: astore 29
          //   2990: aload 7
          //   2992: astore 22
          //   2994: aload 10
          //   2996: astore 26
          //   2998: aload 5
          //   3000: astore 15
          //   3002: aload 4
          //   3004: astore 16
          //   3006: aload 19
          //   3008: astore 30
          //   3010: aload 7
          //   3012: astore 23
          //   3014: aload 10
          //   3016: astore 27
          //   3018: aload 5
          //   3020: astore 17
          //   3022: aload 4
          //   3024: astore 18
          //   3026: aload 19
          //   3028: astore 31
          //   3030: aload 7
          //   3032: astore 6
          //   3034: aload 10
          //   3036: astore 8
          //   3038: aload 5
          //   3040: astore_2
          //   3041: aload 4
          //   3043: astore_3
          //   3044: aload 19
          //   3046: astore 9
          //   3048: aload_0
          //   3049: getfield 56	org/apache/cordova/filetransfer/FileTransfer$4:val$headers	Lorg/json/JSONObject;
          //   3052: ifnull +110 -> 3162
          //   3055: aload 7
          //   3057: astore 20
          //   3059: aload 10
          //   3061: astore 24
          //   3063: aload 5
          //   3065: astore 11
          //   3067: aload 4
          //   3069: astore 12
          //   3071: aload 19
          //   3073: astore 28
          //   3075: aload 7
          //   3077: astore 21
          //   3079: aload 10
          //   3081: astore 25
          //   3083: aload 5
          //   3085: astore 13
          //   3087: aload 4
          //   3089: astore 14
          //   3091: aload 19
          //   3093: astore 29
          //   3095: aload 7
          //   3097: astore 22
          //   3099: aload 10
          //   3101: astore 26
          //   3103: aload 5
          //   3105: astore 15
          //   3107: aload 4
          //   3109: astore 16
          //   3111: aload 19
          //   3113: astore 30
          //   3115: aload 7
          //   3117: astore 23
          //   3119: aload 10
          //   3121: astore 27
          //   3123: aload 5
          //   3125: astore 17
          //   3127: aload 4
          //   3129: astore 18
          //   3131: aload 19
          //   3133: astore 31
          //   3135: aload 7
          //   3137: astore 6
          //   3139: aload 10
          //   3141: astore 8
          //   3143: aload 5
          //   3145: astore_2
          //   3146: aload 4
          //   3148: astore_3
          //   3149: aload 19
          //   3151: astore 9
          //   3153: aload 7
          //   3155: aload_0
          //   3156: getfield 56	org/apache/cordova/filetransfer/FileTransfer$4:val$headers	Lorg/json/JSONObject;
          //   3159: invokestatic 258	org/apache/cordova/filetransfer/FileTransfer:access$3	(Ljava/net/URLConnection;Lorg/json/JSONObject;)V
          //   3162: aload 7
          //   3164: astore 20
          //   3166: aload 10
          //   3168: astore 24
          //   3170: aload 5
          //   3172: astore 11
          //   3174: aload 4
          //   3176: astore 12
          //   3178: aload 19
          //   3180: astore 28
          //   3182: aload 7
          //   3184: astore 21
          //   3186: aload 10
          //   3188: astore 25
          //   3190: aload 5
          //   3192: astore 13
          //   3194: aload 4
          //   3196: astore 14
          //   3198: aload 19
          //   3200: astore 29
          //   3202: aload 7
          //   3204: astore 22
          //   3206: aload 10
          //   3208: astore 26
          //   3210: aload 5
          //   3212: astore 15
          //   3214: aload 4
          //   3216: astore 16
          //   3218: aload 19
          //   3220: astore 30
          //   3222: aload 7
          //   3224: astore 23
          //   3226: aload 10
          //   3228: astore 27
          //   3230: aload 5
          //   3232: astore 17
          //   3234: aload 4
          //   3236: astore 18
          //   3238: aload 19
          //   3240: astore 31
          //   3242: aload 7
          //   3244: astore 6
          //   3246: aload 10
          //   3248: astore 8
          //   3250: aload 5
          //   3252: astore_2
          //   3253: aload 4
          //   3255: astore_3
          //   3256: aload 19
          //   3258: astore 9
          //   3260: aload 7
          //   3262: invokevirtual 261	java/net/HttpURLConnection:connect	()V
          //   3265: aload 7
          //   3267: astore 20
          //   3269: aload 10
          //   3271: astore 24
          //   3273: aload 5
          //   3275: astore 11
          //   3277: aload 4
          //   3279: astore 12
          //   3281: aload 19
          //   3283: astore 28
          //   3285: aload 7
          //   3287: astore 21
          //   3289: aload 10
          //   3291: astore 25
          //   3293: aload 5
          //   3295: astore 13
          //   3297: aload 4
          //   3299: astore 14
          //   3301: aload 19
          //   3303: astore 29
          //   3305: aload 7
          //   3307: astore 22
          //   3309: aload 10
          //   3311: astore 26
          //   3313: aload 5
          //   3315: astore 15
          //   3317: aload 4
          //   3319: astore 16
          //   3321: aload 19
          //   3323: astore 30
          //   3325: aload 7
          //   3327: astore 23
          //   3329: aload 10
          //   3331: astore 27
          //   3333: aload 5
          //   3335: astore 17
          //   3337: aload 4
          //   3339: astore 18
          //   3341: aload 19
          //   3343: astore 31
          //   3345: aload 7
          //   3347: astore 6
          //   3349: aload 10
          //   3351: astore 8
          //   3353: aload 5
          //   3355: astore_2
          //   3356: aload 4
          //   3358: astore_3
          //   3359: aload 19
          //   3361: astore 9
          //   3363: aload 7
          //   3365: invokevirtual 264	java/net/HttpURLConnection:getContentEncoding	()Ljava/lang/String;
          //   3368: ifnull +114 -> 3482
          //   3371: aload 7
          //   3373: astore 20
          //   3375: aload 10
          //   3377: astore 24
          //   3379: aload 5
          //   3381: astore 11
          //   3383: aload 4
          //   3385: astore 12
          //   3387: aload 19
          //   3389: astore 28
          //   3391: aload 7
          //   3393: astore 21
          //   3395: aload 10
          //   3397: astore 25
          //   3399: aload 5
          //   3401: astore 13
          //   3403: aload 4
          //   3405: astore 14
          //   3407: aload 19
          //   3409: astore 29
          //   3411: aload 7
          //   3413: astore 22
          //   3415: aload 10
          //   3417: astore 26
          //   3419: aload 5
          //   3421: astore 15
          //   3423: aload 4
          //   3425: astore 16
          //   3427: aload 19
          //   3429: astore 30
          //   3431: aload 7
          //   3433: astore 23
          //   3435: aload 10
          //   3437: astore 27
          //   3439: aload 5
          //   3441: astore 17
          //   3443: aload 4
          //   3445: astore 18
          //   3447: aload 19
          //   3449: astore 31
          //   3451: aload 7
          //   3453: astore 6
          //   3455: aload 10
          //   3457: astore 8
          //   3459: aload 5
          //   3461: astore_2
          //   3462: aload 4
          //   3464: astore_3
          //   3465: aload 19
          //   3467: astore 9
          //   3469: aload 7
          //   3471: invokevirtual 264	java/net/HttpURLConnection:getContentEncoding	()Ljava/lang/String;
          //   3474: ldc -2
          //   3476: invokevirtual 270	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
          //   3479: ifeq +216 -> 3695
          //   3482: aload 7
          //   3484: astore 20
          //   3486: aload 10
          //   3488: astore 24
          //   3490: aload 5
          //   3492: astore 11
          //   3494: aload 4
          //   3496: astore 12
          //   3498: aload 19
          //   3500: astore 28
          //   3502: aload 7
          //   3504: astore 21
          //   3506: aload 10
          //   3508: astore 25
          //   3510: aload 5
          //   3512: astore 13
          //   3514: aload 4
          //   3516: astore 14
          //   3518: aload 19
          //   3520: astore 29
          //   3522: aload 7
          //   3524: astore 22
          //   3526: aload 10
          //   3528: astore 26
          //   3530: aload 5
          //   3532: astore 15
          //   3534: aload 4
          //   3536: astore 16
          //   3538: aload 19
          //   3540: astore 30
          //   3542: aload 7
          //   3544: astore 23
          //   3546: aload 10
          //   3548: astore 27
          //   3550: aload 5
          //   3552: astore 17
          //   3554: aload 4
          //   3556: astore 18
          //   3558: aload 19
          //   3560: astore 31
          //   3562: aload 7
          //   3564: astore 6
          //   3566: aload 10
          //   3568: astore 8
          //   3570: aload 5
          //   3572: astore_2
          //   3573: aload 4
          //   3575: astore_3
          //   3576: aload 19
          //   3578: astore 9
          //   3580: aload 51
          //   3582: iconst_1
          //   3583: invokevirtual 130	org/apache/cordova/filetransfer/FileProgressResult:setLengthComputable	(Z)V
          //   3586: aload 7
          //   3588: astore 20
          //   3590: aload 10
          //   3592: astore 24
          //   3594: aload 5
          //   3596: astore 11
          //   3598: aload 4
          //   3600: astore 12
          //   3602: aload 19
          //   3604: astore 28
          //   3606: aload 7
          //   3608: astore 21
          //   3610: aload 10
          //   3612: astore 25
          //   3614: aload 5
          //   3616: astore 13
          //   3618: aload 4
          //   3620: astore 14
          //   3622: aload 19
          //   3624: astore 29
          //   3626: aload 7
          //   3628: astore 22
          //   3630: aload 10
          //   3632: astore 26
          //   3634: aload 5
          //   3636: astore 15
          //   3638: aload 4
          //   3640: astore 16
          //   3642: aload 19
          //   3644: astore 30
          //   3646: aload 7
          //   3648: astore 23
          //   3650: aload 10
          //   3652: astore 27
          //   3654: aload 5
          //   3656: astore 17
          //   3658: aload 4
          //   3660: astore 18
          //   3662: aload 19
          //   3664: astore 31
          //   3666: aload 7
          //   3668: astore 6
          //   3670: aload 10
          //   3672: astore 8
          //   3674: aload 5
          //   3676: astore_2
          //   3677: aload 4
          //   3679: astore_3
          //   3680: aload 19
          //   3682: astore 9
          //   3684: aload 51
          //   3686: aload 7
          //   3688: invokevirtual 273	java/net/HttpURLConnection:getContentLength	()I
          //   3691: i2l
          //   3692: invokevirtual 134	org/apache/cordova/filetransfer/FileProgressResult:setTotal	(J)V
          //   3695: aload 7
          //   3697: astore 20
          //   3699: aload 10
          //   3701: astore 24
          //   3703: aload 5
          //   3705: astore 11
          //   3707: aload 4
          //   3709: astore 12
          //   3711: aload 19
          //   3713: astore 28
          //   3715: aload 7
          //   3717: astore 21
          //   3719: aload 10
          //   3721: astore 25
          //   3723: aload 5
          //   3725: astore 13
          //   3727: aload 4
          //   3729: astore 14
          //   3731: aload 19
          //   3733: astore 29
          //   3735: aload 7
          //   3737: astore 22
          //   3739: aload 10
          //   3741: astore 26
          //   3743: aload 5
          //   3745: astore 15
          //   3747: aload 4
          //   3749: astore 16
          //   3751: aload 19
          //   3753: astore 30
          //   3755: aload 7
          //   3757: astore 23
          //   3759: aload 10
          //   3761: astore 27
          //   3763: aload 5
          //   3765: astore 17
          //   3767: aload 4
          //   3769: astore 18
          //   3771: aload 19
          //   3773: astore 31
          //   3775: aload 7
          //   3777: astore 6
          //   3779: aload 10
          //   3781: astore 8
          //   3783: aload 5
          //   3785: astore_2
          //   3786: aload 4
          //   3788: astore_3
          //   3789: aload 19
          //   3791: astore 9
          //   3793: aload 7
          //   3795: invokestatic 277	org/apache/cordova/filetransfer/FileTransfer:access$5	(Ljava/net/URLConnection;)Lorg/apache/cordova/filetransfer/FileTransfer$TrackingInputStream;
          //   3798: astore 32
          //   3800: goto -2504 -> 1296
          //   3803: astore_3
          //   3804: aload_2
          //   3805: monitorexit
          //   3806: aload_3
          //   3807: athrow
          //   3808: aload_0
          //   3809: getfield 36	org/apache/cordova/filetransfer/FileTransfer$4:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
          //   3812: aload 32
          //   3814: putfield 146	org/apache/cordova/filetransfer/FileTransfer$RequestContext:currentInputStream	Ljava/io/InputStream;
          //   3817: aload_2
          //   3818: monitorexit
          //   3819: sipush 16384
          //   3822: newarray <illegal type>
          //   3824: astore_2
          //   3825: aload 32
          //   3827: aload_2
          //   3828: invokevirtual 283	org/apache/cordova/filetransfer/FileTransfer$TrackingInputStream:read	([B)I
          //   3831: istore_1
          //   3832: iload_1
          //   3833: ifgt +1429 -> 5262
          //   3836: aload 7
          //   3838: astore 20
          //   3840: aload 10
          //   3842: astore 24
          //   3844: aload 5
          //   3846: astore 11
          //   3848: aload 4
          //   3850: astore 12
          //   3852: aload 19
          //   3854: astore 28
          //   3856: aload 7
          //   3858: astore 21
          //   3860: aload 10
          //   3862: astore 25
          //   3864: aload 5
          //   3866: astore 13
          //   3868: aload 4
          //   3870: astore 14
          //   3872: aload 19
          //   3874: astore 29
          //   3876: aload 7
          //   3878: astore 22
          //   3880: aload 10
          //   3882: astore 26
          //   3884: aload 5
          //   3886: astore 15
          //   3888: aload 4
          //   3890: astore 16
          //   3892: aload 19
          //   3894: astore 30
          //   3896: aload 7
          //   3898: astore 23
          //   3900: aload 10
          //   3902: astore 27
          //   3904: aload 5
          //   3906: astore 17
          //   3908: aload 4
          //   3910: astore 18
          //   3912: aload 19
          //   3914: astore 31
          //   3916: aload 7
          //   3918: astore 6
          //   3920: aload 10
          //   3922: astore 8
          //   3924: aload 5
          //   3926: astore_2
          //   3927: aload 4
          //   3929: astore_3
          //   3930: aload 19
          //   3932: astore 9
          //   3934: aload_0
          //   3935: getfield 36	org/apache/cordova/filetransfer/FileTransfer$4:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
          //   3938: aconst_null
          //   3939: putfield 146	org/apache/cordova/filetransfer/FileTransfer$RequestContext:currentInputStream	Ljava/io/InputStream;
          //   3942: aload 7
          //   3944: astore 20
          //   3946: aload 10
          //   3948: astore 24
          //   3950: aload 5
          //   3952: astore 11
          //   3954: aload 4
          //   3956: astore 12
          //   3958: aload 19
          //   3960: astore 28
          //   3962: aload 7
          //   3964: astore 21
          //   3966: aload 10
          //   3968: astore 25
          //   3970: aload 5
          //   3972: astore 13
          //   3974: aload 4
          //   3976: astore 14
          //   3978: aload 19
          //   3980: astore 29
          //   3982: aload 7
          //   3984: astore 22
          //   3986: aload 10
          //   3988: astore 26
          //   3990: aload 5
          //   3992: astore 15
          //   3994: aload 4
          //   3996: astore 16
          //   3998: aload 19
          //   4000: astore 30
          //   4002: aload 7
          //   4004: astore 23
          //   4006: aload 10
          //   4008: astore 27
          //   4010: aload 5
          //   4012: astore 17
          //   4014: aload 4
          //   4016: astore 18
          //   4018: aload 19
          //   4020: astore 31
          //   4022: aload 7
          //   4024: astore 6
          //   4026: aload 10
          //   4028: astore 8
          //   4030: aload 5
          //   4032: astore_2
          //   4033: aload 4
          //   4035: astore_3
          //   4036: aload 19
          //   4038: astore 9
          //   4040: aload 32
          //   4042: invokestatic 150	org/apache/cordova/filetransfer/FileTransfer:access$4	(Ljava/io/Closeable;)V
          //   4045: aload 7
          //   4047: astore 20
          //   4049: aload 10
          //   4051: astore 24
          //   4053: aload 5
          //   4055: astore 11
          //   4057: aload 4
          //   4059: astore 12
          //   4061: aload 19
          //   4063: astore 28
          //   4065: aload 7
          //   4067: astore 21
          //   4069: aload 10
          //   4071: astore 25
          //   4073: aload 5
          //   4075: astore 13
          //   4077: aload 4
          //   4079: astore 14
          //   4081: aload 19
          //   4083: astore 29
          //   4085: aload 7
          //   4087: astore 22
          //   4089: aload 10
          //   4091: astore 26
          //   4093: aload 5
          //   4095: astore 15
          //   4097: aload 4
          //   4099: astore 16
          //   4101: aload 19
          //   4103: astore 30
          //   4105: aload 7
          //   4107: astore 23
          //   4109: aload 10
          //   4111: astore 27
          //   4113: aload 5
          //   4115: astore 17
          //   4117: aload 4
          //   4119: astore 18
          //   4121: aload 19
          //   4123: astore 31
          //   4125: aload 7
          //   4127: astore 6
          //   4129: aload 10
          //   4131: astore 8
          //   4133: aload 5
          //   4135: astore_2
          //   4136: aload 4
          //   4138: astore_3
          //   4139: aload 19
          //   4141: astore 9
          //   4143: aload 19
          //   4145: invokestatic 150	org/apache/cordova/filetransfer/FileTransfer:access$4	(Ljava/io/Closeable;)V
          //   4148: aload 7
          //   4150: astore 20
          //   4152: aload 10
          //   4154: astore 24
          //   4156: aload 5
          //   4158: astore 11
          //   4160: aload 4
          //   4162: astore 12
          //   4164: aload 19
          //   4166: astore 28
          //   4168: aload 7
          //   4170: astore 21
          //   4172: aload 10
          //   4174: astore 25
          //   4176: aload 5
          //   4178: astore 13
          //   4180: aload 4
          //   4182: astore 14
          //   4184: aload 19
          //   4186: astore 29
          //   4188: aload 7
          //   4190: astore 22
          //   4192: aload 10
          //   4194: astore 26
          //   4196: aload 5
          //   4198: astore 15
          //   4200: aload 4
          //   4202: astore 16
          //   4204: aload 19
          //   4206: astore 30
          //   4208: aload 7
          //   4210: astore 23
          //   4212: aload 10
          //   4214: astore 27
          //   4216: aload 5
          //   4218: astore 17
          //   4220: aload 4
          //   4222: astore 18
          //   4224: aload 19
          //   4226: astore 31
          //   4228: aload 7
          //   4230: astore 6
          //   4232: aload 10
          //   4234: astore 8
          //   4236: aload 5
          //   4238: astore_2
          //   4239: aload 4
          //   4241: astore_3
          //   4242: aload 19
          //   4244: astore 9
          //   4246: ldc 90
          //   4248: new 92	java/lang/StringBuilder
          //   4251: dup
          //   4252: ldc_w 285
          //   4255: invokespecial 97	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
          //   4258: aload_0
          //   4259: getfield 46	org/apache/cordova/filetransfer/FileTransfer$4:val$target	Ljava/lang/String;
          //   4262: invokevirtual 288	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   4265: invokevirtual 105	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   4268: invokestatic 111	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
          //   4271: pop
          //   4272: aload 7
          //   4274: astore 20
          //   4276: aload 10
          //   4278: astore 24
          //   4280: aload 5
          //   4282: astore 11
          //   4284: aload 4
          //   4286: astore 12
          //   4288: aload 19
          //   4290: astore 28
          //   4292: aload 7
          //   4294: astore 21
          //   4296: aload 10
          //   4298: astore 25
          //   4300: aload 5
          //   4302: astore 13
          //   4304: aload 4
          //   4306: astore 14
          //   4308: aload 19
          //   4310: astore 29
          //   4312: aload 7
          //   4314: astore 22
          //   4316: aload 10
          //   4318: astore 26
          //   4320: aload 5
          //   4322: astore 15
          //   4324: aload 4
          //   4326: astore 16
          //   4328: aload 19
          //   4330: astore 30
          //   4332: aload 7
          //   4334: astore 23
          //   4336: aload 10
          //   4338: astore 27
          //   4340: aload 5
          //   4342: astore 17
          //   4344: aload 4
          //   4346: astore 18
          //   4348: aload 19
          //   4350: astore 31
          //   4352: aload 7
          //   4354: astore 6
          //   4356: aload 10
          //   4358: astore 8
          //   4360: aload 5
          //   4362: astore_2
          //   4363: aload 4
          //   4365: astore_3
          //   4366: aload 19
          //   4368: astore 9
          //   4370: aload 10
          //   4372: invokestatic 294	org/apache/cordova/file/FileUtils:getEntry	(Ljava/io/File;)Lorg/json/JSONObject;
          //   4375: astore 32
          //   4377: aload 7
          //   4379: astore 20
          //   4381: aload 10
          //   4383: astore 24
          //   4385: aload 5
          //   4387: astore 11
          //   4389: aload 4
          //   4391: astore 12
          //   4393: aload 19
          //   4395: astore 28
          //   4397: aload 7
          //   4399: astore 21
          //   4401: aload 10
          //   4403: astore 25
          //   4405: aload 5
          //   4407: astore 13
          //   4409: aload 4
          //   4411: astore 14
          //   4413: aload 19
          //   4415: astore 29
          //   4417: aload 7
          //   4419: astore 22
          //   4421: aload 10
          //   4423: astore 26
          //   4425: aload 5
          //   4427: astore 15
          //   4429: aload 4
          //   4431: astore 16
          //   4433: aload 19
          //   4435: astore 30
          //   4437: aload 7
          //   4439: astore 23
          //   4441: aload 10
          //   4443: astore 27
          //   4445: aload 5
          //   4447: astore 17
          //   4449: aload 4
          //   4451: astore 18
          //   4453: aload 19
          //   4455: astore 31
          //   4457: aload 7
          //   4459: astore 6
          //   4461: aload 10
          //   4463: astore 8
          //   4465: aload 5
          //   4467: astore_2
          //   4468: aload 4
          //   4470: astore_3
          //   4471: aload 19
          //   4473: astore 9
          //   4475: new 172	org/apache/cordova/PluginResult
          //   4478: dup
          //   4479: getstatic 196	org/apache/cordova/PluginResult$Status:OK	Lorg/apache/cordova/PluginResult$Status;
          //   4482: aload 32
          //   4484: invokespecial 189	org/apache/cordova/PluginResult:<init>	(Lorg/apache/cordova/PluginResult$Status;Lorg/json/JSONObject;)V
          //   4487: astore 32
          //   4489: aload 19
          //   4491: invokestatic 150	org/apache/cordova/filetransfer/FileTransfer:access$4	(Ljava/io/Closeable;)V
          //   4494: invokestatic 154	org/apache/cordova/filetransfer/FileTransfer:access$0	()Ljava/util/HashMap;
          //   4497: astore_2
          //   4498: aload_2
          //   4499: monitorenter
          //   4500: invokestatic 154	org/apache/cordova/filetransfer/FileTransfer:access$0	()Ljava/util/HashMap;
          //   4503: aload_0
          //   4504: getfield 38	org/apache/cordova/filetransfer/FileTransfer$4:val$objectId	Ljava/lang/String;
          //   4507: invokevirtual 160	java/util/HashMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
          //   4510: pop
          //   4511: aload_2
          //   4512: monitorexit
          //   4513: aload 7
          //   4515: ifnull +35 -> 4550
          //   4518: aload_0
          //   4519: getfield 40	org/apache/cordova/filetransfer/FileTransfer$4:val$trustEveryone	Z
          //   4522: ifeq +28 -> 4550
          //   4525: aload_0
          //   4526: getfield 42	org/apache/cordova/filetransfer/FileTransfer$4:val$useHttps	Z
          //   4529: ifeq +21 -> 4550
          //   4532: aload 7
          //   4534: checkcast 162	javax/net/ssl/HttpsURLConnection
          //   4537: astore_2
          //   4538: aload_2
          //   4539: aload 5
          //   4541: invokevirtual 166	javax/net/ssl/HttpsURLConnection:setHostnameVerifier	(Ljavax/net/ssl/HostnameVerifier;)V
          //   4544: aload_2
          //   4545: aload 4
          //   4547: invokevirtual 170	javax/net/ssl/HttpsURLConnection:setSSLSocketFactory	(Ljavax/net/ssl/SSLSocketFactory;)V
          //   4550: aload 32
          //   4552: ifnonnull +1570 -> 6122
          //   4555: new 172	org/apache/cordova/PluginResult
          //   4558: dup
          //   4559: getstatic 178	org/apache/cordova/PluginResult$Status:ERROR	Lorg/apache/cordova/PluginResult$Status;
          //   4562: getstatic 182	org/apache/cordova/filetransfer/FileTransfer:CONNECTION_ERR	I
          //   4565: aload_0
          //   4566: getfield 44	org/apache/cordova/filetransfer/FileTransfer$4:val$source	Ljava/lang/String;
          //   4569: aload_0
          //   4570: getfield 46	org/apache/cordova/filetransfer/FileTransfer$4:val$target	Ljava/lang/String;
          //   4573: aload 7
          //   4575: invokestatic 186	org/apache/cordova/filetransfer/FileTransfer:access$6	(ILjava/lang/String;Ljava/lang/String;Ljava/net/URLConnection;)Lorg/json/JSONObject;
          //   4578: invokespecial 189	org/apache/cordova/PluginResult:<init>	(Lorg/apache/cordova/PluginResult$Status;Lorg/json/JSONObject;)V
          //   4581: astore_2
          //   4582: aload_2
          //   4583: invokevirtual 193	org/apache/cordova/PluginResult:getStatus	()I
          //   4586: getstatic 196	org/apache/cordova/PluginResult$Status:OK	Lorg/apache/cordova/PluginResult$Status;
          //   4589: invokevirtual 199	org/apache/cordova/PluginResult$Status:ordinal	()I
          //   4592: if_icmpeq +14 -> 4606
          //   4595: aload 10
          //   4597: ifnull +9 -> 4606
          //   4600: aload 10
          //   4602: invokevirtual 205	java/io/File:delete	()Z
          //   4605: pop
          //   4606: aload_0
          //   4607: getfield 36	org/apache/cordova/filetransfer/FileTransfer$4:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
          //   4610: aload_2
          //   4611: invokevirtual 209	org/apache/cordova/filetransfer/FileTransfer$RequestContext:sendPluginResult	(Lorg/apache/cordova/PluginResult;)V
          //   4614: return
          //   4615: astore_3
          //   4616: aload_2
          //   4617: monitorexit
          //   4618: aload_3
          //   4619: athrow
          //   4620: astore 34
          //   4622: aload 7
          //   4624: astore 20
          //   4626: aload 10
          //   4628: astore 24
          //   4630: aload 5
          //   4632: astore 11
          //   4634: aload 4
          //   4636: astore 12
          //   4638: aload 19
          //   4640: astore 28
          //   4642: aload 7
          //   4644: astore 21
          //   4646: aload 10
          //   4648: astore 25
          //   4650: aload 5
          //   4652: astore 13
          //   4654: aload 4
          //   4656: astore 14
          //   4658: aload 19
          //   4660: astore 29
          //   4662: aload 7
          //   4664: astore 22
          //   4666: aload 10
          //   4668: astore 26
          //   4670: aload 5
          //   4672: astore 15
          //   4674: aload 4
          //   4676: astore 16
          //   4678: aload 19
          //   4680: astore 30
          //   4682: aload 7
          //   4684: astore 23
          //   4686: aload 10
          //   4688: astore 27
          //   4690: aload 5
          //   4692: astore 17
          //   4694: aload 4
          //   4696: astore 18
          //   4698: aload 19
          //   4700: astore 31
          //   4702: aload 7
          //   4704: astore 6
          //   4706: aload 10
          //   4708: astore 8
          //   4710: aload 5
          //   4712: astore_2
          //   4713: aload 4
          //   4715: astore_3
          //   4716: aload 19
          //   4718: astore 9
          //   4720: aload_0
          //   4721: getfield 36	org/apache/cordova/filetransfer/FileTransfer$4:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
          //   4724: aconst_null
          //   4725: putfield 146	org/apache/cordova/filetransfer/FileTransfer$RequestContext:currentInputStream	Ljava/io/InputStream;
          //   4728: aload 7
          //   4730: astore 20
          //   4732: aload 10
          //   4734: astore 24
          //   4736: aload 5
          //   4738: astore 11
          //   4740: aload 4
          //   4742: astore 12
          //   4744: aload 19
          //   4746: astore 28
          //   4748: aload 7
          //   4750: astore 21
          //   4752: aload 10
          //   4754: astore 25
          //   4756: aload 5
          //   4758: astore 13
          //   4760: aload 4
          //   4762: astore 14
          //   4764: aload 19
          //   4766: astore 29
          //   4768: aload 7
          //   4770: astore 22
          //   4772: aload 10
          //   4774: astore 26
          //   4776: aload 5
          //   4778: astore 15
          //   4780: aload 4
          //   4782: astore 16
          //   4784: aload 19
          //   4786: astore 30
          //   4788: aload 7
          //   4790: astore 23
          //   4792: aload 10
          //   4794: astore 27
          //   4796: aload 5
          //   4798: astore 17
          //   4800: aload 4
          //   4802: astore 18
          //   4804: aload 19
          //   4806: astore 31
          //   4808: aload 7
          //   4810: astore 6
          //   4812: aload 10
          //   4814: astore 8
          //   4816: aload 5
          //   4818: astore_2
          //   4819: aload 4
          //   4821: astore_3
          //   4822: aload 19
          //   4824: astore 9
          //   4826: aload 32
          //   4828: invokestatic 150	org/apache/cordova/filetransfer/FileTransfer:access$4	(Ljava/io/Closeable;)V
          //   4831: aload 7
          //   4833: astore 20
          //   4835: aload 10
          //   4837: astore 24
          //   4839: aload 5
          //   4841: astore 11
          //   4843: aload 4
          //   4845: astore 12
          //   4847: aload 19
          //   4849: astore 28
          //   4851: aload 7
          //   4853: astore 21
          //   4855: aload 10
          //   4857: astore 25
          //   4859: aload 5
          //   4861: astore 13
          //   4863: aload 4
          //   4865: astore 14
          //   4867: aload 19
          //   4869: astore 29
          //   4871: aload 7
          //   4873: astore 22
          //   4875: aload 10
          //   4877: astore 26
          //   4879: aload 5
          //   4881: astore 15
          //   4883: aload 4
          //   4885: astore 16
          //   4887: aload 19
          //   4889: astore 30
          //   4891: aload 7
          //   4893: astore 23
          //   4895: aload 10
          //   4897: astore 27
          //   4899: aload 5
          //   4901: astore 17
          //   4903: aload 4
          //   4905: astore 18
          //   4907: aload 19
          //   4909: astore 31
          //   4911: aload 7
          //   4913: astore 6
          //   4915: aload 10
          //   4917: astore 8
          //   4919: aload 5
          //   4921: astore_2
          //   4922: aload 4
          //   4924: astore_3
          //   4925: aload 19
          //   4927: astore 9
          //   4929: aload 19
          //   4931: invokestatic 150	org/apache/cordova/filetransfer/FileTransfer:access$4	(Ljava/io/Closeable;)V
          //   4934: aload 7
          //   4936: astore 20
          //   4938: aload 10
          //   4940: astore 24
          //   4942: aload 5
          //   4944: astore 11
          //   4946: aload 4
          //   4948: astore 12
          //   4950: aload 19
          //   4952: astore 28
          //   4954: aload 7
          //   4956: astore 21
          //   4958: aload 10
          //   4960: astore 25
          //   4962: aload 5
          //   4964: astore 13
          //   4966: aload 4
          //   4968: astore 14
          //   4970: aload 19
          //   4972: astore 29
          //   4974: aload 7
          //   4976: astore 22
          //   4978: aload 10
          //   4980: astore 26
          //   4982: aload 5
          //   4984: astore 15
          //   4986: aload 4
          //   4988: astore 16
          //   4990: aload 19
          //   4992: astore 30
          //   4994: aload 7
          //   4996: astore 23
          //   4998: aload 10
          //   5000: astore 27
          //   5002: aload 5
          //   5004: astore 17
          //   5006: aload 4
          //   5008: astore 18
          //   5010: aload 19
          //   5012: astore 31
          //   5014: aload 7
          //   5016: astore 6
          //   5018: aload 10
          //   5020: astore 8
          //   5022: aload 5
          //   5024: astore_2
          //   5025: aload 4
          //   5027: astore_3
          //   5028: aload 19
          //   5030: astore 9
          //   5032: aload 34
          //   5034: athrow
          //   5035: astore 4
          //   5037: aload 20
          //   5039: astore 6
          //   5041: aload 24
          //   5043: astore 8
          //   5045: aload 11
          //   5047: astore_2
          //   5048: aload 12
          //   5050: astore_3
          //   5051: aload 28
          //   5053: astore 9
          //   5055: getstatic 297	org/apache/cordova/filetransfer/FileTransfer:FILE_NOT_FOUND_ERR	I
          //   5058: aload_0
          //   5059: getfield 44	org/apache/cordova/filetransfer/FileTransfer$4:val$source	Ljava/lang/String;
          //   5062: aload_0
          //   5063: getfield 46	org/apache/cordova/filetransfer/FileTransfer$4:val$target	Ljava/lang/String;
          //   5066: aload 20
          //   5068: invokestatic 186	org/apache/cordova/filetransfer/FileTransfer:access$6	(ILjava/lang/String;Ljava/lang/String;Ljava/net/URLConnection;)Lorg/json/JSONObject;
          //   5071: astore 5
          //   5073: aload 20
          //   5075: astore 6
          //   5077: aload 24
          //   5079: astore 8
          //   5081: aload 11
          //   5083: astore_2
          //   5084: aload 12
          //   5086: astore_3
          //   5087: aload 28
          //   5089: astore 9
          //   5091: ldc 90
          //   5093: aload 5
          //   5095: invokevirtual 300	org/json/JSONObject:toString	()Ljava/lang/String;
          //   5098: aload 4
          //   5100: invokestatic 304	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
          //   5103: pop
          //   5104: aload 20
          //   5106: astore 6
          //   5108: aload 24
          //   5110: astore 8
          //   5112: aload 11
          //   5114: astore_2
          //   5115: aload 12
          //   5117: astore_3
          //   5118: aload 28
          //   5120: astore 9
          //   5122: new 172	org/apache/cordova/PluginResult
          //   5125: dup
          //   5126: getstatic 307	org/apache/cordova/PluginResult$Status:IO_EXCEPTION	Lorg/apache/cordova/PluginResult$Status;
          //   5129: aload 5
          //   5131: invokespecial 189	org/apache/cordova/PluginResult:<init>	(Lorg/apache/cordova/PluginResult$Status;Lorg/json/JSONObject;)V
          //   5134: astore 4
          //   5136: aload 28
          //   5138: invokestatic 150	org/apache/cordova/filetransfer/FileTransfer:access$4	(Ljava/io/Closeable;)V
          //   5141: invokestatic 154	org/apache/cordova/filetransfer/FileTransfer:access$0	()Ljava/util/HashMap;
          //   5144: astore_2
          //   5145: aload_2
          //   5146: monitorenter
          //   5147: invokestatic 154	org/apache/cordova/filetransfer/FileTransfer:access$0	()Ljava/util/HashMap;
          //   5150: aload_0
          //   5151: getfield 38	org/apache/cordova/filetransfer/FileTransfer$4:val$objectId	Ljava/lang/String;
          //   5154: invokevirtual 160	java/util/HashMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
          //   5157: pop
          //   5158: aload_2
          //   5159: monitorexit
          //   5160: aload 20
          //   5162: ifnull +35 -> 5197
          //   5165: aload_0
          //   5166: getfield 40	org/apache/cordova/filetransfer/FileTransfer$4:val$trustEveryone	Z
          //   5169: ifeq +28 -> 5197
          //   5172: aload_0
          //   5173: getfield 42	org/apache/cordova/filetransfer/FileTransfer$4:val$useHttps	Z
          //   5176: ifeq +21 -> 5197
          //   5179: aload 20
          //   5181: checkcast 162	javax/net/ssl/HttpsURLConnection
          //   5184: astore_2
          //   5185: aload_2
          //   5186: aload 11
          //   5188: invokevirtual 166	javax/net/ssl/HttpsURLConnection:setHostnameVerifier	(Ljavax/net/ssl/HostnameVerifier;)V
          //   5191: aload_2
          //   5192: aload 12
          //   5194: invokevirtual 170	javax/net/ssl/HttpsURLConnection:setSSLSocketFactory	(Ljavax/net/ssl/SSLSocketFactory;)V
          //   5197: aload 4
          //   5199: ifnonnull +947 -> 6146
          //   5202: new 172	org/apache/cordova/PluginResult
          //   5205: dup
          //   5206: getstatic 178	org/apache/cordova/PluginResult$Status:ERROR	Lorg/apache/cordova/PluginResult$Status;
          //   5209: getstatic 182	org/apache/cordova/filetransfer/FileTransfer:CONNECTION_ERR	I
          //   5212: aload_0
          //   5213: getfield 44	org/apache/cordova/filetransfer/FileTransfer$4:val$source	Ljava/lang/String;
          //   5216: aload_0
          //   5217: getfield 46	org/apache/cordova/filetransfer/FileTransfer$4:val$target	Ljava/lang/String;
          //   5220: aload 20
          //   5222: invokestatic 186	org/apache/cordova/filetransfer/FileTransfer:access$6	(ILjava/lang/String;Ljava/lang/String;Ljava/net/URLConnection;)Lorg/json/JSONObject;
          //   5225: invokespecial 189	org/apache/cordova/PluginResult:<init>	(Lorg/apache/cordova/PluginResult$Status;Lorg/json/JSONObject;)V
          //   5228: astore_2
          //   5229: aload_2
          //   5230: invokevirtual 193	org/apache/cordova/PluginResult:getStatus	()I
          //   5233: getstatic 196	org/apache/cordova/PluginResult$Status:OK	Lorg/apache/cordova/PluginResult$Status;
          //   5236: invokevirtual 199	org/apache/cordova/PluginResult$Status:ordinal	()I
          //   5239: if_icmpeq +14 -> 5253
          //   5242: aload 24
          //   5244: ifnull +9 -> 5253
          //   5247: aload 24
          //   5249: invokevirtual 205	java/io/File:delete	()Z
          //   5252: pop
          //   5253: aload_0
          //   5254: getfield 36	org/apache/cordova/filetransfer/FileTransfer$4:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
          //   5257: aload_2
          //   5258: invokevirtual 209	org/apache/cordova/filetransfer/FileTransfer$RequestContext:sendPluginResult	(Lorg/apache/cordova/PluginResult;)V
          //   5261: return
          //   5262: aload 19
          //   5264: aload_2
          //   5265: iconst_0
          //   5266: iload_1
          //   5267: invokevirtual 313	java/io/OutputStream:write	([BII)V
          //   5270: aload 51
          //   5272: aload 32
          //   5274: invokevirtual 317	org/apache/cordova/filetransfer/FileTransfer$TrackingInputStream:getTotalRawBytesRead	()J
          //   5277: invokevirtual 320	org/apache/cordova/filetransfer/FileProgressResult:setLoaded	(J)V
          //   5280: new 172	org/apache/cordova/PluginResult
          //   5283: dup
          //   5284: getstatic 196	org/apache/cordova/PluginResult$Status:OK	Lorg/apache/cordova/PluginResult$Status;
          //   5287: aload 51
          //   5289: invokevirtual 324	org/apache/cordova/filetransfer/FileProgressResult:toJSONObject	()Lorg/json/JSONObject;
          //   5292: invokespecial 189	org/apache/cordova/PluginResult:<init>	(Lorg/apache/cordova/PluginResult$Status;Lorg/json/JSONObject;)V
          //   5295: astore_3
          //   5296: aload_3
          //   5297: iconst_1
          //   5298: invokevirtual 327	org/apache/cordova/PluginResult:setKeepCallback	(Z)V
          //   5301: aload_0
          //   5302: getfield 36	org/apache/cordova/filetransfer/FileTransfer$4:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
          //   5305: aload_3
          //   5306: invokevirtual 209	org/apache/cordova/filetransfer/FileTransfer$RequestContext:sendPluginResult	(Lorg/apache/cordova/PluginResult;)V
          //   5309: goto -1484 -> 3825
          //   5312: astore_3
          //   5313: aload_2
          //   5314: monitorexit
          //   5315: aload_3
          //   5316: athrow
          //   5317: astore 4
          //   5319: aload 21
          //   5321: astore 6
          //   5323: aload 25
          //   5325: astore 8
          //   5327: aload 13
          //   5329: astore_2
          //   5330: aload 14
          //   5332: astore_3
          //   5333: aload 29
          //   5335: astore 9
          //   5337: getstatic 182	org/apache/cordova/filetransfer/FileTransfer:CONNECTION_ERR	I
          //   5340: aload_0
          //   5341: getfield 44	org/apache/cordova/filetransfer/FileTransfer$4:val$source	Ljava/lang/String;
          //   5344: aload_0
          //   5345: getfield 46	org/apache/cordova/filetransfer/FileTransfer$4:val$target	Ljava/lang/String;
          //   5348: aload 21
          //   5350: invokestatic 186	org/apache/cordova/filetransfer/FileTransfer:access$6	(ILjava/lang/String;Ljava/lang/String;Ljava/net/URLConnection;)Lorg/json/JSONObject;
          //   5353: astore 5
          //   5355: aload 21
          //   5357: astore 6
          //   5359: aload 25
          //   5361: astore 8
          //   5363: aload 13
          //   5365: astore_2
          //   5366: aload 14
          //   5368: astore_3
          //   5369: aload 29
          //   5371: astore 9
          //   5373: ldc 90
          //   5375: aload 5
          //   5377: invokevirtual 300	org/json/JSONObject:toString	()Ljava/lang/String;
          //   5380: aload 4
          //   5382: invokestatic 304	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
          //   5385: pop
          //   5386: aload 21
          //   5388: astore 6
          //   5390: aload 25
          //   5392: astore 8
          //   5394: aload 13
          //   5396: astore_2
          //   5397: aload 14
          //   5399: astore_3
          //   5400: aload 29
          //   5402: astore 9
          //   5404: new 172	org/apache/cordova/PluginResult
          //   5407: dup
          //   5408: getstatic 307	org/apache/cordova/PluginResult$Status:IO_EXCEPTION	Lorg/apache/cordova/PluginResult$Status;
          //   5411: aload 5
          //   5413: invokespecial 189	org/apache/cordova/PluginResult:<init>	(Lorg/apache/cordova/PluginResult$Status;Lorg/json/JSONObject;)V
          //   5416: astore 4
          //   5418: aload 29
          //   5420: invokestatic 150	org/apache/cordova/filetransfer/FileTransfer:access$4	(Ljava/io/Closeable;)V
          //   5423: invokestatic 154	org/apache/cordova/filetransfer/FileTransfer:access$0	()Ljava/util/HashMap;
          //   5426: astore_2
          //   5427: aload_2
          //   5428: monitorenter
          //   5429: invokestatic 154	org/apache/cordova/filetransfer/FileTransfer:access$0	()Ljava/util/HashMap;
          //   5432: aload_0
          //   5433: getfield 38	org/apache/cordova/filetransfer/FileTransfer$4:val$objectId	Ljava/lang/String;
          //   5436: invokevirtual 160	java/util/HashMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
          //   5439: pop
          //   5440: aload_2
          //   5441: monitorexit
          //   5442: aload 21
          //   5444: ifnull +35 -> 5479
          //   5447: aload_0
          //   5448: getfield 40	org/apache/cordova/filetransfer/FileTransfer$4:val$trustEveryone	Z
          //   5451: ifeq +28 -> 5479
          //   5454: aload_0
          //   5455: getfield 42	org/apache/cordova/filetransfer/FileTransfer$4:val$useHttps	Z
          //   5458: ifeq +21 -> 5479
          //   5461: aload 21
          //   5463: checkcast 162	javax/net/ssl/HttpsURLConnection
          //   5466: astore_2
          //   5467: aload_2
          //   5468: aload 13
          //   5470: invokevirtual 166	javax/net/ssl/HttpsURLConnection:setHostnameVerifier	(Ljavax/net/ssl/HostnameVerifier;)V
          //   5473: aload_2
          //   5474: aload 14
          //   5476: invokevirtual 170	javax/net/ssl/HttpsURLConnection:setSSLSocketFactory	(Ljavax/net/ssl/SSLSocketFactory;)V
          //   5479: aload 4
          //   5481: ifnonnull +659 -> 6140
          //   5484: new 172	org/apache/cordova/PluginResult
          //   5487: dup
          //   5488: getstatic 178	org/apache/cordova/PluginResult$Status:ERROR	Lorg/apache/cordova/PluginResult$Status;
          //   5491: getstatic 182	org/apache/cordova/filetransfer/FileTransfer:CONNECTION_ERR	I
          //   5494: aload_0
          //   5495: getfield 44	org/apache/cordova/filetransfer/FileTransfer$4:val$source	Ljava/lang/String;
          //   5498: aload_0
          //   5499: getfield 46	org/apache/cordova/filetransfer/FileTransfer$4:val$target	Ljava/lang/String;
          //   5502: aload 21
          //   5504: invokestatic 186	org/apache/cordova/filetransfer/FileTransfer:access$6	(ILjava/lang/String;Ljava/lang/String;Ljava/net/URLConnection;)Lorg/json/JSONObject;
          //   5507: invokespecial 189	org/apache/cordova/PluginResult:<init>	(Lorg/apache/cordova/PluginResult$Status;Lorg/json/JSONObject;)V
          //   5510: astore_2
          //   5511: aload_2
          //   5512: invokevirtual 193	org/apache/cordova/PluginResult:getStatus	()I
          //   5515: getstatic 196	org/apache/cordova/PluginResult$Status:OK	Lorg/apache/cordova/PluginResult$Status;
          //   5518: invokevirtual 199	org/apache/cordova/PluginResult$Status:ordinal	()I
          //   5521: if_icmpeq +14 -> 5535
          //   5524: aload 25
          //   5526: ifnull +9 -> 5535
          //   5529: aload 25
          //   5531: invokevirtual 205	java/io/File:delete	()Z
          //   5534: pop
          //   5535: aload_0
          //   5536: getfield 36	org/apache/cordova/filetransfer/FileTransfer$4:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
          //   5539: aload_2
          //   5540: invokevirtual 209	org/apache/cordova/filetransfer/FileTransfer$RequestContext:sendPluginResult	(Lorg/apache/cordova/PluginResult;)V
          //   5543: return
          //   5544: astore_3
          //   5545: aload_2
          //   5546: monitorexit
          //   5547: aload_3
          //   5548: athrow
          //   5549: astore 4
          //   5551: aload 22
          //   5553: astore 6
          //   5555: aload 26
          //   5557: astore 8
          //   5559: aload 15
          //   5561: astore_2
          //   5562: aload 16
          //   5564: astore_3
          //   5565: aload 30
          //   5567: astore 9
          //   5569: ldc 90
          //   5571: aload 4
          //   5573: invokevirtual 330	org/json/JSONException:getMessage	()Ljava/lang/String;
          //   5576: aload 4
          //   5578: invokestatic 304	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
          //   5581: pop
          //   5582: aload 22
          //   5584: astore 6
          //   5586: aload 26
          //   5588: astore 8
          //   5590: aload 15
          //   5592: astore_2
          //   5593: aload 16
          //   5595: astore_3
          //   5596: aload 30
          //   5598: astore 9
          //   5600: new 172	org/apache/cordova/PluginResult
          //   5603: dup
          //   5604: getstatic 333	org/apache/cordova/PluginResult$Status:JSON_EXCEPTION	Lorg/apache/cordova/PluginResult$Status;
          //   5607: invokespecial 336	org/apache/cordova/PluginResult:<init>	(Lorg/apache/cordova/PluginResult$Status;)V
          //   5610: astore 4
          //   5612: aload 30
          //   5614: invokestatic 150	org/apache/cordova/filetransfer/FileTransfer:access$4	(Ljava/io/Closeable;)V
          //   5617: invokestatic 154	org/apache/cordova/filetransfer/FileTransfer:access$0	()Ljava/util/HashMap;
          //   5620: astore_2
          //   5621: aload_2
          //   5622: monitorenter
          //   5623: invokestatic 154	org/apache/cordova/filetransfer/FileTransfer:access$0	()Ljava/util/HashMap;
          //   5626: aload_0
          //   5627: getfield 38	org/apache/cordova/filetransfer/FileTransfer$4:val$objectId	Ljava/lang/String;
          //   5630: invokevirtual 160	java/util/HashMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
          //   5633: pop
          //   5634: aload_2
          //   5635: monitorexit
          //   5636: aload 22
          //   5638: ifnull +35 -> 5673
          //   5641: aload_0
          //   5642: getfield 40	org/apache/cordova/filetransfer/FileTransfer$4:val$trustEveryone	Z
          //   5645: ifeq +28 -> 5673
          //   5648: aload_0
          //   5649: getfield 42	org/apache/cordova/filetransfer/FileTransfer$4:val$useHttps	Z
          //   5652: ifeq +21 -> 5673
          //   5655: aload 22
          //   5657: checkcast 162	javax/net/ssl/HttpsURLConnection
          //   5660: astore_2
          //   5661: aload_2
          //   5662: aload 15
          //   5664: invokevirtual 166	javax/net/ssl/HttpsURLConnection:setHostnameVerifier	(Ljavax/net/ssl/HostnameVerifier;)V
          //   5667: aload_2
          //   5668: aload 16
          //   5670: invokevirtual 170	javax/net/ssl/HttpsURLConnection:setSSLSocketFactory	(Ljavax/net/ssl/SSLSocketFactory;)V
          //   5673: aload 4
          //   5675: ifnonnull +459 -> 6134
          //   5678: new 172	org/apache/cordova/PluginResult
          //   5681: dup
          //   5682: getstatic 178	org/apache/cordova/PluginResult$Status:ERROR	Lorg/apache/cordova/PluginResult$Status;
          //   5685: getstatic 182	org/apache/cordova/filetransfer/FileTransfer:CONNECTION_ERR	I
          //   5688: aload_0
          //   5689: getfield 44	org/apache/cordova/filetransfer/FileTransfer$4:val$source	Ljava/lang/String;
          //   5692: aload_0
          //   5693: getfield 46	org/apache/cordova/filetransfer/FileTransfer$4:val$target	Ljava/lang/String;
          //   5696: aload 22
          //   5698: invokestatic 186	org/apache/cordova/filetransfer/FileTransfer:access$6	(ILjava/lang/String;Ljava/lang/String;Ljava/net/URLConnection;)Lorg/json/JSONObject;
          //   5701: invokespecial 189	org/apache/cordova/PluginResult:<init>	(Lorg/apache/cordova/PluginResult$Status;Lorg/json/JSONObject;)V
          //   5704: astore_2
          //   5705: aload_2
          //   5706: invokevirtual 193	org/apache/cordova/PluginResult:getStatus	()I
          //   5709: getstatic 196	org/apache/cordova/PluginResult$Status:OK	Lorg/apache/cordova/PluginResult$Status;
          //   5712: invokevirtual 199	org/apache/cordova/PluginResult$Status:ordinal	()I
          //   5715: if_icmpeq +14 -> 5729
          //   5718: aload 26
          //   5720: ifnull +9 -> 5729
          //   5723: aload 26
          //   5725: invokevirtual 205	java/io/File:delete	()Z
          //   5728: pop
          //   5729: aload_0
          //   5730: getfield 36	org/apache/cordova/filetransfer/FileTransfer$4:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
          //   5733: aload_2
          //   5734: invokevirtual 209	org/apache/cordova/filetransfer/FileTransfer$RequestContext:sendPluginResult	(Lorg/apache/cordova/PluginResult;)V
          //   5737: return
          //   5738: astore_3
          //   5739: aload_2
          //   5740: monitorexit
          //   5741: aload_3
          //   5742: athrow
          //   5743: astore 4
          //   5745: aload 23
          //   5747: astore 6
          //   5749: aload 27
          //   5751: astore 8
          //   5753: aload 17
          //   5755: astore_2
          //   5756: aload 18
          //   5758: astore_3
          //   5759: aload 31
          //   5761: astore 9
          //   5763: getstatic 182	org/apache/cordova/filetransfer/FileTransfer:CONNECTION_ERR	I
          //   5766: aload_0
          //   5767: getfield 44	org/apache/cordova/filetransfer/FileTransfer$4:val$source	Ljava/lang/String;
          //   5770: aload_0
          //   5771: getfield 46	org/apache/cordova/filetransfer/FileTransfer$4:val$target	Ljava/lang/String;
          //   5774: aload 23
          //   5776: invokestatic 186	org/apache/cordova/filetransfer/FileTransfer:access$6	(ILjava/lang/String;Ljava/lang/String;Ljava/net/URLConnection;)Lorg/json/JSONObject;
          //   5779: astore 5
          //   5781: aload 23
          //   5783: astore 6
          //   5785: aload 27
          //   5787: astore 8
          //   5789: aload 17
          //   5791: astore_2
          //   5792: aload 18
          //   5794: astore_3
          //   5795: aload 31
          //   5797: astore 9
          //   5799: ldc 90
          //   5801: aload 5
          //   5803: invokevirtual 300	org/json/JSONObject:toString	()Ljava/lang/String;
          //   5806: aload 4
          //   5808: invokestatic 304	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
          //   5811: pop
          //   5812: aload 23
          //   5814: astore 6
          //   5816: aload 27
          //   5818: astore 8
          //   5820: aload 17
          //   5822: astore_2
          //   5823: aload 18
          //   5825: astore_3
          //   5826: aload 31
          //   5828: astore 9
          //   5830: new 172	org/apache/cordova/PluginResult
          //   5833: dup
          //   5834: getstatic 307	org/apache/cordova/PluginResult$Status:IO_EXCEPTION	Lorg/apache/cordova/PluginResult$Status;
          //   5837: aload 5
          //   5839: invokespecial 189	org/apache/cordova/PluginResult:<init>	(Lorg/apache/cordova/PluginResult$Status;Lorg/json/JSONObject;)V
          //   5842: astore 4
          //   5844: aload 31
          //   5846: invokestatic 150	org/apache/cordova/filetransfer/FileTransfer:access$4	(Ljava/io/Closeable;)V
          //   5849: invokestatic 154	org/apache/cordova/filetransfer/FileTransfer:access$0	()Ljava/util/HashMap;
          //   5852: astore_2
          //   5853: aload_2
          //   5854: monitorenter
          //   5855: invokestatic 154	org/apache/cordova/filetransfer/FileTransfer:access$0	()Ljava/util/HashMap;
          //   5858: aload_0
          //   5859: getfield 38	org/apache/cordova/filetransfer/FileTransfer$4:val$objectId	Ljava/lang/String;
          //   5862: invokevirtual 160	java/util/HashMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
          //   5865: pop
          //   5866: aload_2
          //   5867: monitorexit
          //   5868: aload 23
          //   5870: ifnull +35 -> 5905
          //   5873: aload_0
          //   5874: getfield 40	org/apache/cordova/filetransfer/FileTransfer$4:val$trustEveryone	Z
          //   5877: ifeq +28 -> 5905
          //   5880: aload_0
          //   5881: getfield 42	org/apache/cordova/filetransfer/FileTransfer$4:val$useHttps	Z
          //   5884: ifeq +21 -> 5905
          //   5887: aload 23
          //   5889: checkcast 162	javax/net/ssl/HttpsURLConnection
          //   5892: astore_2
          //   5893: aload_2
          //   5894: aload 17
          //   5896: invokevirtual 166	javax/net/ssl/HttpsURLConnection:setHostnameVerifier	(Ljavax/net/ssl/HostnameVerifier;)V
          //   5899: aload_2
          //   5900: aload 18
          //   5902: invokevirtual 170	javax/net/ssl/HttpsURLConnection:setSSLSocketFactory	(Ljavax/net/ssl/SSLSocketFactory;)V
          //   5905: aload 4
          //   5907: ifnonnull +221 -> 6128
          //   5910: new 172	org/apache/cordova/PluginResult
          //   5913: dup
          //   5914: getstatic 178	org/apache/cordova/PluginResult$Status:ERROR	Lorg/apache/cordova/PluginResult$Status;
          //   5917: getstatic 182	org/apache/cordova/filetransfer/FileTransfer:CONNECTION_ERR	I
          //   5920: aload_0
          //   5921: getfield 44	org/apache/cordova/filetransfer/FileTransfer$4:val$source	Ljava/lang/String;
          //   5924: aload_0
          //   5925: getfield 46	org/apache/cordova/filetransfer/FileTransfer$4:val$target	Ljava/lang/String;
          //   5928: aload 23
          //   5930: invokestatic 186	org/apache/cordova/filetransfer/FileTransfer:access$6	(ILjava/lang/String;Ljava/lang/String;Ljava/net/URLConnection;)Lorg/json/JSONObject;
          //   5933: invokespecial 189	org/apache/cordova/PluginResult:<init>	(Lorg/apache/cordova/PluginResult$Status;Lorg/json/JSONObject;)V
          //   5936: astore_2
          //   5937: aload_2
          //   5938: invokevirtual 193	org/apache/cordova/PluginResult:getStatus	()I
          //   5941: getstatic 196	org/apache/cordova/PluginResult$Status:OK	Lorg/apache/cordova/PluginResult$Status;
          //   5944: invokevirtual 199	org/apache/cordova/PluginResult$Status:ordinal	()I
          //   5947: if_icmpeq +14 -> 5961
          //   5950: aload 27
          //   5952: ifnull +9 -> 5961
          //   5955: aload 27
          //   5957: invokevirtual 205	java/io/File:delete	()Z
          //   5960: pop
          //   5961: aload_0
          //   5962: getfield 36	org/apache/cordova/filetransfer/FileTransfer$4:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
          //   5965: aload_2
          //   5966: invokevirtual 209	org/apache/cordova/filetransfer/FileTransfer$RequestContext:sendPluginResult	(Lorg/apache/cordova/PluginResult;)V
          //   5969: return
          //   5970: astore_3
          //   5971: aload_2
          //   5972: monitorexit
          //   5973: aload_3
          //   5974: athrow
          //   5975: astore 4
          //   5977: aload 9
          //   5979: invokestatic 150	org/apache/cordova/filetransfer/FileTransfer:access$4	(Ljava/io/Closeable;)V
          //   5982: invokestatic 154	org/apache/cordova/filetransfer/FileTransfer:access$0	()Ljava/util/HashMap;
          //   5985: astore 5
          //   5987: aload 5
          //   5989: monitorenter
          //   5990: invokestatic 154	org/apache/cordova/filetransfer/FileTransfer:access$0	()Ljava/util/HashMap;
          //   5993: aload_0
          //   5994: getfield 38	org/apache/cordova/filetransfer/FileTransfer$4:val$objectId	Ljava/lang/String;
          //   5997: invokevirtual 160	java/util/HashMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
          //   6000: pop
          //   6001: aload 5
          //   6003: monitorexit
          //   6004: aload 6
          //   6006: ifnull +36 -> 6042
          //   6009: aload_0
          //   6010: getfield 40	org/apache/cordova/filetransfer/FileTransfer$4:val$trustEveryone	Z
          //   6013: ifeq +29 -> 6042
          //   6016: aload_0
          //   6017: getfield 42	org/apache/cordova/filetransfer/FileTransfer$4:val$useHttps	Z
          //   6020: ifeq +22 -> 6042
          //   6023: aload 6
          //   6025: checkcast 162	javax/net/ssl/HttpsURLConnection
          //   6028: astore 5
          //   6030: aload 5
          //   6032: aload_2
          //   6033: invokevirtual 166	javax/net/ssl/HttpsURLConnection:setHostnameVerifier	(Ljavax/net/ssl/HostnameVerifier;)V
          //   6036: aload 5
          //   6038: aload_3
          //   6039: invokevirtual 170	javax/net/ssl/HttpsURLConnection:setSSLSocketFactory	(Ljavax/net/ssl/SSLSocketFactory;)V
          //   6042: aload 33
          //   6044: astore_2
          //   6045: iconst_0
          //   6046: ifne +30 -> 6076
          //   6049: new 172	org/apache/cordova/PluginResult
          //   6052: dup
          //   6053: getstatic 178	org/apache/cordova/PluginResult$Status:ERROR	Lorg/apache/cordova/PluginResult$Status;
          //   6056: getstatic 182	org/apache/cordova/filetransfer/FileTransfer:CONNECTION_ERR	I
          //   6059: aload_0
          //   6060: getfield 44	org/apache/cordova/filetransfer/FileTransfer$4:val$source	Ljava/lang/String;
          //   6063: aload_0
          //   6064: getfield 46	org/apache/cordova/filetransfer/FileTransfer$4:val$target	Ljava/lang/String;
          //   6067: aload 6
          //   6069: invokestatic 186	org/apache/cordova/filetransfer/FileTransfer:access$6	(ILjava/lang/String;Ljava/lang/String;Ljava/net/URLConnection;)Lorg/json/JSONObject;
          //   6072: invokespecial 189	org/apache/cordova/PluginResult:<init>	(Lorg/apache/cordova/PluginResult$Status;Lorg/json/JSONObject;)V
          //   6075: astore_2
          //   6076: aload_2
          //   6077: invokevirtual 193	org/apache/cordova/PluginResult:getStatus	()I
          //   6080: getstatic 196	org/apache/cordova/PluginResult$Status:OK	Lorg/apache/cordova/PluginResult$Status;
          //   6083: invokevirtual 199	org/apache/cordova/PluginResult$Status:ordinal	()I
          //   6086: if_icmpeq +14 -> 6100
          //   6089: aload 8
          //   6091: ifnull +9 -> 6100
          //   6094: aload 8
          //   6096: invokevirtual 205	java/io/File:delete	()Z
          //   6099: pop
          //   6100: aload_0
          //   6101: getfield 36	org/apache/cordova/filetransfer/FileTransfer$4:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
          //   6104: aload_2
          //   6105: invokevirtual 209	org/apache/cordova/filetransfer/FileTransfer$RequestContext:sendPluginResult	(Lorg/apache/cordova/PluginResult;)V
          //   6108: aload 4
          //   6110: athrow
          //   6111: astore_2
          //   6112: aload 5
          //   6114: monitorexit
          //   6115: aload_2
          //   6116: athrow
          //   6117: astore_3
          //   6118: aload_2
          //   6119: monitorexit
          //   6120: aload_3
          //   6121: athrow
          //   6122: aload 32
          //   6124: astore_2
          //   6125: goto -1543 -> 4582
          //   6128: aload 4
          //   6130: astore_2
          //   6131: goto -194 -> 5937
          //   6134: aload 4
          //   6136: astore_2
          //   6137: goto -432 -> 5705
          //   6140: aload 4
          //   6142: astore_2
          //   6143: goto -632 -> 5511
          //   6146: aload 4
          //   6148: astore_2
          //   6149: goto -920 -> 5229
          // Local variable table:
          //   start	length	slot	name	signature
          //   0	6152	0	this	4
          //   3831	1436	1	i	int
          //   6111	8	2	localObject2	Object
          //   6124	25	2	localObject3	Object
          //   184	3605	3	localObject4	Object
          //   3803	4	3	localObject5	Object
          //   3929	542	3	localObject6	Object
          //   4615	4	3	localObject7	Object
          //   4715	591	3	localObject8	Object
          //   5312	4	3	localObject9	Object
          //   5332	68	3	localObject10	Object
          //   5544	4	3	localObject11	Object
          //   5564	32	3	localObject12	Object
          //   5738	4	3	localObject13	Object
          //   5758	68	3	localObject14	Object
          //   5970	69	3	localSSLSocketFactory	SSLSocketFactory
          //   6117	4	3	localObject15	Object
          //   69	4957	4	localObject16	Object
          //   5035	64	4	localFileNotFoundException	java.io.FileNotFoundException
          //   5134	64	4	localPluginResult1	PluginResult
          //   5317	64	4	localIOException	IOException
          //   5416	64	4	localPluginResult2	PluginResult
          //   5549	28	4	localJSONException	JSONException
          //   5610	64	4	localPluginResult3	PluginResult
          //   5743	64	4	localThrowable	Throwable
          //   5842	64	4	localPluginResult4	PluginResult
          //   5975	172	4	localObject17	Object
          //   173	5895	6	localObject19	Object
          //   27	4988	7	localObject20	Object
          //   177	5918	8	localObject21	Object
          //   102	5876	9	localObject22	Object
          //   84	4935	10	localFile1	File
          //   117	5070	11	localObject23	Object
          //   121	5072	12	localObject24	Object
          //   133	5336	13	localObject25	Object
          //   137	5338	14	localObject26	Object
          //   149	5514	15	localObject27	Object
          //   153	5516	16	localObject28	Object
          //   165	5730	17	localObject29	Object
          //   169	5732	18	localObject30	Object
          //   196	5067	19	localOutputStream	OutputStream
          //   109	5112	20	localObject31	Object
          //   125	5378	21	localObject32	Object
          //   141	5556	22	localObject33	Object
          //   157	5772	23	localObject34	Object
          //   113	5135	24	localFile2	File
          //   129	5401	25	localObject35	Object
          //   145	5579	26	localObject36	Object
          //   161	5795	27	localObject37	Object
          //   105	5032	28	localObject38	Object
          //   93	5326	29	localObject39	Object
          //   96	5517	30	localObject40	Object
          //   99	5746	31	localObject41	Object
          //   33	6090	32	localObject42	Object
          //   87	5956	33	localObject43	Object
          //   90	1599	34	localObject44	Object
          //   4620	413	34	localObject45	Object
          //   36	2295	35	localObject46	Object
          //   39	2312	36	localObject47	Object
          //   42	2329	37	localObject48	Object
          //   45	2346	38	localObject49	Object
          //   54	2156	39	localObject50	Object
          //   57	2173	40	localObject51	Object
          //   60	2190	41	localObject52	Object
          //   63	2207	42	localObject53	Object
          //   66	2223	43	localObject54	Object
          //   30	2476	44	localObject55	Object
          //   51	1933	45	localObject56	Object
          //   12	1744	46	localObject57	Object
          //   15	1761	47	localObject58	Object
          //   18	1778	48	localObject59	Object
          //   21	1795	49	localObject60	Object
          //   24	1812	50	localObject61	Object
          //   72	5216	51	localFileProgressResult	FileProgressResult
          //   75	168	52	localObject62	Object
          //   78	185	53	localObject63	Object
          //   81	202	54	localObject64	Object
          // Exception table:
          //   from	to	target	type
          //   1638	1651	3803	finally
          //   3804	3806	3803	finally
          //   1303	1315	4615	finally
          //   3808	3819	4615	finally
          //   4616	4618	4615	finally
          //   1296	1303	4620	finally
          //   3819	3825	4620	finally
          //   3825	3832	4620	finally
          //   4618	4620	4620	finally
          //   5262	5309	4620	finally
          //   185	198	5035	java/io/FileNotFoundException
          //   296	309	5035	java/io/FileNotFoundException
          //   407	416	5035	java/io/FileNotFoundException
          //   514	539	5035	java/io/FileNotFoundException
          //   637	646	5035	java/io/FileNotFoundException
          //   744	751	5035	java/io/FileNotFoundException
          //   849	862	5035	java/io/FileNotFoundException
          //   960	972	5035	java/io/FileNotFoundException
          //   1070	1076	5035	java/io/FileNotFoundException
          //   1174	1184	5035	java/io/FileNotFoundException
          //   1282	1296	5035	java/io/FileNotFoundException
          //   1413	1421	5035	java/io/FileNotFoundException
          //   1519	1524	5035	java/io/FileNotFoundException
          //   1622	1627	5035	java/io/FileNotFoundException
          //   1853	1866	5035	java/io/FileNotFoundException
          //   1972	1979	5035	java/io/FileNotFoundException
          //   2085	2092	5035	java/io/FileNotFoundException
          //   2190	2197	5035	java/io/FileNotFoundException
          //   2295	2302	5035	java/io/FileNotFoundException
          //   2400	2407	5035	java/io/FileNotFoundException
          //   2505	2513	5035	java/io/FileNotFoundException
          //   2611	2618	5035	java/io/FileNotFoundException
          //   2716	2731	5035	java/io/FileNotFoundException
          //   2834	2843	5035	java/io/FileNotFoundException
          //   2941	2950	5035	java/io/FileNotFoundException
          //   3048	3055	5035	java/io/FileNotFoundException
          //   3153	3162	5035	java/io/FileNotFoundException
          //   3260	3265	5035	java/io/FileNotFoundException
          //   3363	3371	5035	java/io/FileNotFoundException
          //   3469	3482	5035	java/io/FileNotFoundException
          //   3580	3586	5035	java/io/FileNotFoundException
          //   3684	3695	5035	java/io/FileNotFoundException
          //   3793	3800	5035	java/io/FileNotFoundException
          //   3934	3942	5035	java/io/FileNotFoundException
          //   4040	4045	5035	java/io/FileNotFoundException
          //   4143	4148	5035	java/io/FileNotFoundException
          //   4246	4272	5035	java/io/FileNotFoundException
          //   4370	4377	5035	java/io/FileNotFoundException
          //   4475	4489	5035	java/io/FileNotFoundException
          //   4720	4728	5035	java/io/FileNotFoundException
          //   4826	4831	5035	java/io/FileNotFoundException
          //   4929	4934	5035	java/io/FileNotFoundException
          //   5032	5035	5035	java/io/FileNotFoundException
          //   5147	5160	5312	finally
          //   5313	5315	5312	finally
          //   185	198	5317	java/io/IOException
          //   296	309	5317	java/io/IOException
          //   407	416	5317	java/io/IOException
          //   514	539	5317	java/io/IOException
          //   637	646	5317	java/io/IOException
          //   744	751	5317	java/io/IOException
          //   849	862	5317	java/io/IOException
          //   960	972	5317	java/io/IOException
          //   1070	1076	5317	java/io/IOException
          //   1174	1184	5317	java/io/IOException
          //   1282	1296	5317	java/io/IOException
          //   1413	1421	5317	java/io/IOException
          //   1519	1524	5317	java/io/IOException
          //   1622	1627	5317	java/io/IOException
          //   1853	1866	5317	java/io/IOException
          //   1972	1979	5317	java/io/IOException
          //   2085	2092	5317	java/io/IOException
          //   2190	2197	5317	java/io/IOException
          //   2295	2302	5317	java/io/IOException
          //   2400	2407	5317	java/io/IOException
          //   2505	2513	5317	java/io/IOException
          //   2611	2618	5317	java/io/IOException
          //   2716	2731	5317	java/io/IOException
          //   2834	2843	5317	java/io/IOException
          //   2941	2950	5317	java/io/IOException
          //   3048	3055	5317	java/io/IOException
          //   3153	3162	5317	java/io/IOException
          //   3260	3265	5317	java/io/IOException
          //   3363	3371	5317	java/io/IOException
          //   3469	3482	5317	java/io/IOException
          //   3580	3586	5317	java/io/IOException
          //   3684	3695	5317	java/io/IOException
          //   3793	3800	5317	java/io/IOException
          //   3934	3942	5317	java/io/IOException
          //   4040	4045	5317	java/io/IOException
          //   4143	4148	5317	java/io/IOException
          //   4246	4272	5317	java/io/IOException
          //   4370	4377	5317	java/io/IOException
          //   4475	4489	5317	java/io/IOException
          //   4720	4728	5317	java/io/IOException
          //   4826	4831	5317	java/io/IOException
          //   4929	4934	5317	java/io/IOException
          //   5032	5035	5317	java/io/IOException
          //   5429	5442	5544	finally
          //   5545	5547	5544	finally
          //   185	198	5549	org/json/JSONException
          //   296	309	5549	org/json/JSONException
          //   407	416	5549	org/json/JSONException
          //   514	539	5549	org/json/JSONException
          //   637	646	5549	org/json/JSONException
          //   744	751	5549	org/json/JSONException
          //   849	862	5549	org/json/JSONException
          //   960	972	5549	org/json/JSONException
          //   1070	1076	5549	org/json/JSONException
          //   1174	1184	5549	org/json/JSONException
          //   1282	1296	5549	org/json/JSONException
          //   1413	1421	5549	org/json/JSONException
          //   1519	1524	5549	org/json/JSONException
          //   1622	1627	5549	org/json/JSONException
          //   1853	1866	5549	org/json/JSONException
          //   1972	1979	5549	org/json/JSONException
          //   2085	2092	5549	org/json/JSONException
          //   2190	2197	5549	org/json/JSONException
          //   2295	2302	5549	org/json/JSONException
          //   2400	2407	5549	org/json/JSONException
          //   2505	2513	5549	org/json/JSONException
          //   2611	2618	5549	org/json/JSONException
          //   2716	2731	5549	org/json/JSONException
          //   2834	2843	5549	org/json/JSONException
          //   2941	2950	5549	org/json/JSONException
          //   3048	3055	5549	org/json/JSONException
          //   3153	3162	5549	org/json/JSONException
          //   3260	3265	5549	org/json/JSONException
          //   3363	3371	5549	org/json/JSONException
          //   3469	3482	5549	org/json/JSONException
          //   3580	3586	5549	org/json/JSONException
          //   3684	3695	5549	org/json/JSONException
          //   3793	3800	5549	org/json/JSONException
          //   3934	3942	5549	org/json/JSONException
          //   4040	4045	5549	org/json/JSONException
          //   4143	4148	5549	org/json/JSONException
          //   4246	4272	5549	org/json/JSONException
          //   4370	4377	5549	org/json/JSONException
          //   4475	4489	5549	org/json/JSONException
          //   4720	4728	5549	org/json/JSONException
          //   4826	4831	5549	org/json/JSONException
          //   4929	4934	5549	org/json/JSONException
          //   5032	5035	5549	org/json/JSONException
          //   5623	5636	5738	finally
          //   5739	5741	5738	finally
          //   185	198	5743	java/lang/Throwable
          //   296	309	5743	java/lang/Throwable
          //   407	416	5743	java/lang/Throwable
          //   514	539	5743	java/lang/Throwable
          //   637	646	5743	java/lang/Throwable
          //   744	751	5743	java/lang/Throwable
          //   849	862	5743	java/lang/Throwable
          //   960	972	5743	java/lang/Throwable
          //   1070	1076	5743	java/lang/Throwable
          //   1174	1184	5743	java/lang/Throwable
          //   1282	1296	5743	java/lang/Throwable
          //   1413	1421	5743	java/lang/Throwable
          //   1519	1524	5743	java/lang/Throwable
          //   1622	1627	5743	java/lang/Throwable
          //   1853	1866	5743	java/lang/Throwable
          //   1972	1979	5743	java/lang/Throwable
          //   2085	2092	5743	java/lang/Throwable
          //   2190	2197	5743	java/lang/Throwable
          //   2295	2302	5743	java/lang/Throwable
          //   2400	2407	5743	java/lang/Throwable
          //   2505	2513	5743	java/lang/Throwable
          //   2611	2618	5743	java/lang/Throwable
          //   2716	2731	5743	java/lang/Throwable
          //   2834	2843	5743	java/lang/Throwable
          //   2941	2950	5743	java/lang/Throwable
          //   3048	3055	5743	java/lang/Throwable
          //   3153	3162	5743	java/lang/Throwable
          //   3260	3265	5743	java/lang/Throwable
          //   3363	3371	5743	java/lang/Throwable
          //   3469	3482	5743	java/lang/Throwable
          //   3580	3586	5743	java/lang/Throwable
          //   3684	3695	5743	java/lang/Throwable
          //   3793	3800	5743	java/lang/Throwable
          //   3934	3942	5743	java/lang/Throwable
          //   4040	4045	5743	java/lang/Throwable
          //   4143	4148	5743	java/lang/Throwable
          //   4246	4272	5743	java/lang/Throwable
          //   4370	4377	5743	java/lang/Throwable
          //   4475	4489	5743	java/lang/Throwable
          //   4720	4728	5743	java/lang/Throwable
          //   4826	4831	5743	java/lang/Throwable
          //   4929	4934	5743	java/lang/Throwable
          //   5032	5035	5743	java/lang/Throwable
          //   5855	5868	5970	finally
          //   5971	5973	5970	finally
          //   185	198	5975	finally
          //   296	309	5975	finally
          //   407	416	5975	finally
          //   514	539	5975	finally
          //   637	646	5975	finally
          //   744	751	5975	finally
          //   849	862	5975	finally
          //   960	972	5975	finally
          //   1070	1076	5975	finally
          //   1174	1184	5975	finally
          //   1282	1296	5975	finally
          //   1413	1421	5975	finally
          //   1519	1524	5975	finally
          //   1622	1627	5975	finally
          //   1853	1866	5975	finally
          //   1972	1979	5975	finally
          //   2085	2092	5975	finally
          //   2190	2197	5975	finally
          //   2295	2302	5975	finally
          //   2400	2407	5975	finally
          //   2505	2513	5975	finally
          //   2611	2618	5975	finally
          //   2716	2731	5975	finally
          //   2834	2843	5975	finally
          //   2941	2950	5975	finally
          //   3048	3055	5975	finally
          //   3153	3162	5975	finally
          //   3260	3265	5975	finally
          //   3363	3371	5975	finally
          //   3469	3482	5975	finally
          //   3580	3586	5975	finally
          //   3684	3695	5975	finally
          //   3793	3800	5975	finally
          //   3934	3942	5975	finally
          //   4040	4045	5975	finally
          //   4143	4148	5975	finally
          //   4246	4272	5975	finally
          //   4370	4377	5975	finally
          //   4475	4489	5975	finally
          //   4720	4728	5975	finally
          //   4826	4831	5975	finally
          //   4929	4934	5975	finally
          //   5032	5035	5975	finally
          //   5055	5073	5975	finally
          //   5091	5104	5975	finally
          //   5122	5136	5975	finally
          //   5337	5355	5975	finally
          //   5373	5386	5975	finally
          //   5404	5418	5975	finally
          //   5569	5582	5975	finally
          //   5600	5612	5975	finally
          //   5763	5781	5975	finally
          //   5799	5812	5975	finally
          //   5830	5844	5975	finally
          //   5990	6004	6111	finally
          //   6112	6115	6111	finally
          //   4500	4513	6117	finally
          //   6118	6120	6117	finally
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
    label109:
    final JSONObject localJSONObject2;
    label128:
    final String str4;
    final String str5;
    final CordovaResourceApi localCordovaResourceApi;
    final Uri localUri;
    label395:
    int i;
    if (paramJSONArray.optJSONObject(5) == null)
    {
      localJSONObject1 = new JSONObject();
      bool3 = paramJSONArray.optBoolean(6);
      if ((paramJSONArray.optBoolean(7)) || (paramJSONArray.isNull(7))) {
        break label495;
      }
      bool1 = false;
      if (paramJSONArray.optJSONObject(8) != null) {
        break label501;
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
        break label512;
      }
      paramJSONArray = localCordovaResourceApi.remapUri(paramJSONArray);
      i = CordovaResourceApi.getUriType(localUri);
      if (i != 6) {
        break label527;
      }
    }
    label495:
    label501:
    label512:
    label527:
    for (final boolean bool2 = true;; bool2 = false)
    {
      if ((i == 5) || (bool2)) {
        break label533;
      }
      paramString1 = createFileTransferError(INVALID_URL_ERR, paramString1, paramString2, null, Integer.valueOf(0));
      Log.e("FileTransfer", "Unsupported URI: " + localUri);
      ???.sendPluginResult(new PluginResult(PluginResult.Status.IO_EXCEPTION, paramString1));
      return;
      localJSONObject1 = paramJSONArray.optJSONObject(5);
      break;
      bool1 = true;
      break label109;
      localJSONObject2 = paramJSONArray.optJSONObject(8);
      break label128;
      paramJSONArray = Uri.fromFile(new File(paramString1));
      break label395;
    }
    label533:
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
          //   1: getfield 41	org/apache/cordova/filetransfer/FileTransfer$3:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
          //   4: getfield 89	org/apache/cordova/filetransfer/FileTransfer$RequestContext:aborted	Z
          //   7: ifeq +4 -> 11
          //   10: return
          //   11: aconst_null
          //   12: astore 17
          //   14: aconst_null
          //   15: astore 19
          //   17: aconst_null
          //   18: astore 44
          //   20: aconst_null
          //   21: astore 45
          //   23: aconst_null
          //   24: astore 16
          //   26: aconst_null
          //   27: astore 32
          //   29: aconst_null
          //   30: astore 33
          //   32: aconst_null
          //   33: astore 34
          //   35: aconst_null
          //   36: astore 35
          //   38: aconst_null
          //   39: astore 36
          //   41: aconst_null
          //   42: astore 42
          //   44: aconst_null
          //   45: astore 37
          //   47: aconst_null
          //   48: astore 38
          //   50: aconst_null
          //   51: astore 39
          //   53: aconst_null
          //   54: astore 40
          //   56: aconst_null
          //   57: astore 41
          //   59: aconst_null
          //   60: astore 43
          //   62: iconst_0
          //   63: istore 6
          //   65: iconst_0
          //   66: istore 5
          //   68: iconst_m1
          //   69: istore_1
          //   70: aload 16
          //   72: astore 28
          //   74: aload 32
          //   76: astore 20
          //   78: aload 37
          //   80: astore 21
          //   82: aload 17
          //   84: astore 29
          //   86: iload_1
          //   87: istore_3
          //   88: aload 33
          //   90: astore 22
          //   92: aload 38
          //   94: astore 23
          //   96: iload 5
          //   98: istore 4
          //   100: aload 19
          //   102: astore 31
          //   104: aload 34
          //   106: astore 26
          //   108: aload 39
          //   110: astore 27
          //   112: aload 44
          //   114: astore 30
          //   116: aload 35
          //   118: astore 24
          //   120: aload 40
          //   122: astore 25
          //   124: aload 45
          //   126: astore 18
          //   128: aload 36
          //   130: astore 14
          //   132: aload 41
          //   134: astore 15
          //   136: new 91	org/apache/cordova/filetransfer/FileUploadResult
          //   139: dup
          //   140: invokespecial 92	org/apache/cordova/filetransfer/FileUploadResult:<init>	()V
          //   143: astore 46
          //   145: aload 16
          //   147: astore 28
          //   149: aload 32
          //   151: astore 20
          //   153: aload 37
          //   155: astore 21
          //   157: aload 17
          //   159: astore 29
          //   161: iload_1
          //   162: istore_3
          //   163: aload 33
          //   165: astore 22
          //   167: aload 38
          //   169: astore 23
          //   171: iload 5
          //   173: istore 4
          //   175: aload 19
          //   177: astore 31
          //   179: aload 34
          //   181: astore 26
          //   183: aload 39
          //   185: astore 27
          //   187: aload 44
          //   189: astore 30
          //   191: aload 35
          //   193: astore 24
          //   195: aload 40
          //   197: astore 25
          //   199: aload 45
          //   201: astore 18
          //   203: aload 36
          //   205: astore 14
          //   207: aload 41
          //   209: astore 15
          //   211: new 94	org/apache/cordova/filetransfer/FileProgressResult
          //   214: dup
          //   215: invokespecial 95	org/apache/cordova/filetransfer/FileProgressResult:<init>	()V
          //   218: astore 47
          //   220: aload 16
          //   222: astore 28
          //   224: aload 32
          //   226: astore 20
          //   228: aload 37
          //   230: astore 21
          //   232: aload 17
          //   234: astore 29
          //   236: iload_1
          //   237: istore_3
          //   238: aload 33
          //   240: astore 22
          //   242: aload 38
          //   244: astore 23
          //   246: iload 5
          //   248: istore 4
          //   250: aload 19
          //   252: astore 31
          //   254: aload 34
          //   256: astore 26
          //   258: aload 39
          //   260: astore 27
          //   262: aload 44
          //   264: astore 30
          //   266: aload 35
          //   268: astore 24
          //   270: aload 40
          //   272: astore 25
          //   274: aload 45
          //   276: astore 18
          //   278: aload 36
          //   280: astore 14
          //   282: aload 41
          //   284: astore 15
          //   286: aload_0
          //   287: getfield 49	org/apache/cordova/filetransfer/FileTransfer$3:val$resourceApi	Lorg/apache/cordova/CordovaResourceApi;
          //   290: aload_0
          //   291: getfield 51	org/apache/cordova/filetransfer/FileTransfer$3:val$targetUri	Landroid/net/Uri;
          //   294: invokevirtual 101	org/apache/cordova/CordovaResourceApi:createHttpConnection	(Landroid/net/Uri;)Ljava/net/HttpURLConnection;
          //   297: astore 19
          //   299: aload 42
          //   301: astore 17
          //   303: aload 43
          //   305: astore 16
          //   307: aload 19
          //   309: astore 28
          //   311: aload 32
          //   313: astore 20
          //   315: aload 37
          //   317: astore 21
          //   319: aload 19
          //   321: astore 29
          //   323: iload_1
          //   324: istore_3
          //   325: aload 33
          //   327: astore 22
          //   329: aload 38
          //   331: astore 23
          //   333: iload 5
          //   335: istore 4
          //   337: aload 19
          //   339: astore 31
          //   341: aload 34
          //   343: astore 26
          //   345: aload 39
          //   347: astore 27
          //   349: aload 19
          //   351: astore 30
          //   353: aload 35
          //   355: astore 24
          //   357: aload 40
          //   359: astore 25
          //   361: aload 19
          //   363: astore 18
          //   365: aload 36
          //   367: astore 14
          //   369: aload 41
          //   371: astore 15
          //   373: aload_0
          //   374: getfield 47	org/apache/cordova/filetransfer/FileTransfer$3:val$useHttps	Z
          //   377: ifeq +377 -> 754
          //   380: aload 42
          //   382: astore 17
          //   384: aload 43
          //   386: astore 16
          //   388: aload 19
          //   390: astore 28
          //   392: aload 32
          //   394: astore 20
          //   396: aload 37
          //   398: astore 21
          //   400: aload 19
          //   402: astore 29
          //   404: iload_1
          //   405: istore_3
          //   406: aload 33
          //   408: astore 22
          //   410: aload 38
          //   412: astore 23
          //   414: iload 5
          //   416: istore 4
          //   418: aload 19
          //   420: astore 31
          //   422: aload 34
          //   424: astore 26
          //   426: aload 39
          //   428: astore 27
          //   430: aload 19
          //   432: astore 30
          //   434: aload 35
          //   436: astore 24
          //   438: aload 40
          //   440: astore 25
          //   442: aload 19
          //   444: astore 18
          //   446: aload 36
          //   448: astore 14
          //   450: aload 41
          //   452: astore 15
          //   454: aload_0
          //   455: getfield 45	org/apache/cordova/filetransfer/FileTransfer$3:val$trustEveryone	Z
          //   458: ifeq +296 -> 754
          //   461: aload 19
          //   463: astore 28
          //   465: aload 32
          //   467: astore 20
          //   469: aload 37
          //   471: astore 21
          //   473: aload 19
          //   475: astore 29
          //   477: iload_1
          //   478: istore_3
          //   479: aload 33
          //   481: astore 22
          //   483: aload 38
          //   485: astore 23
          //   487: iload 5
          //   489: istore 4
          //   491: aload 19
          //   493: astore 31
          //   495: aload 34
          //   497: astore 26
          //   499: aload 39
          //   501: astore 27
          //   503: aload 19
          //   505: astore 30
          //   507: aload 35
          //   509: astore 24
          //   511: aload 40
          //   513: astore 25
          //   515: aload 19
          //   517: astore 18
          //   519: aload 36
          //   521: astore 14
          //   523: aload 41
          //   525: astore 15
          //   527: aload 19
          //   529: checkcast 103	javax/net/ssl/HttpsURLConnection
          //   532: astore 42
          //   534: aload 19
          //   536: astore 28
          //   538: aload 32
          //   540: astore 20
          //   542: aload 37
          //   544: astore 21
          //   546: aload 19
          //   548: astore 29
          //   550: iload_1
          //   551: istore_3
          //   552: aload 33
          //   554: astore 22
          //   556: aload 38
          //   558: astore 23
          //   560: iload 5
          //   562: istore 4
          //   564: aload 19
          //   566: astore 31
          //   568: aload 34
          //   570: astore 26
          //   572: aload 39
          //   574: astore 27
          //   576: aload 19
          //   578: astore 30
          //   580: aload 35
          //   582: astore 24
          //   584: aload 40
          //   586: astore 25
          //   588: aload 19
          //   590: astore 18
          //   592: aload 36
          //   594: astore 14
          //   596: aload 41
          //   598: astore 15
          //   600: aload 42
          //   602: invokestatic 107	org/apache/cordova/filetransfer/FileTransfer:access$1	(Ljavax/net/ssl/HttpsURLConnection;)Ljavax/net/ssl/SSLSocketFactory;
          //   605: astore 16
          //   607: aload 19
          //   609: astore 28
          //   611: aload 32
          //   613: astore 20
          //   615: aload 16
          //   617: astore 21
          //   619: aload 19
          //   621: astore 29
          //   623: iload_1
          //   624: istore_3
          //   625: aload 33
          //   627: astore 22
          //   629: aload 16
          //   631: astore 23
          //   633: iload 5
          //   635: istore 4
          //   637: aload 19
          //   639: astore 31
          //   641: aload 34
          //   643: astore 26
          //   645: aload 16
          //   647: astore 27
          //   649: aload 19
          //   651: astore 30
          //   653: aload 35
          //   655: astore 24
          //   657: aload 16
          //   659: astore 25
          //   661: aload 19
          //   663: astore 18
          //   665: aload 36
          //   667: astore 14
          //   669: aload 16
          //   671: astore 15
          //   673: aload 42
          //   675: invokevirtual 111	javax/net/ssl/HttpsURLConnection:getHostnameVerifier	()Ljavax/net/ssl/HostnameVerifier;
          //   678: astore 17
          //   680: aload 19
          //   682: astore 28
          //   684: aload 17
          //   686: astore 20
          //   688: aload 16
          //   690: astore 21
          //   692: aload 19
          //   694: astore 29
          //   696: iload_1
          //   697: istore_3
          //   698: aload 17
          //   700: astore 22
          //   702: aload 16
          //   704: astore 23
          //   706: iload 5
          //   708: istore 4
          //   710: aload 19
          //   712: astore 31
          //   714: aload 17
          //   716: astore 26
          //   718: aload 16
          //   720: astore 27
          //   722: aload 19
          //   724: astore 30
          //   726: aload 17
          //   728: astore 24
          //   730: aload 16
          //   732: astore 25
          //   734: aload 19
          //   736: astore 18
          //   738: aload 17
          //   740: astore 14
          //   742: aload 16
          //   744: astore 15
          //   746: aload 42
          //   748: invokestatic 114	org/apache/cordova/filetransfer/FileTransfer:access$2	()Ljavax/net/ssl/HostnameVerifier;
          //   751: invokevirtual 118	javax/net/ssl/HttpsURLConnection:setHostnameVerifier	(Ljavax/net/ssl/HostnameVerifier;)V
          //   754: aload 19
          //   756: astore 28
          //   758: aload 17
          //   760: astore 20
          //   762: aload 16
          //   764: astore 21
          //   766: aload 19
          //   768: astore 29
          //   770: iload_1
          //   771: istore_3
          //   772: aload 17
          //   774: astore 22
          //   776: aload 16
          //   778: astore 23
          //   780: iload 5
          //   782: istore 4
          //   784: aload 19
          //   786: astore 31
          //   788: aload 17
          //   790: astore 26
          //   792: aload 16
          //   794: astore 27
          //   796: aload 19
          //   798: astore 30
          //   800: aload 17
          //   802: astore 24
          //   804: aload 16
          //   806: astore 25
          //   808: aload 19
          //   810: astore 18
          //   812: aload 17
          //   814: astore 14
          //   816: aload 16
          //   818: astore 15
          //   820: aload 19
          //   822: iconst_1
          //   823: invokevirtual 124	java/net/HttpURLConnection:setDoInput	(Z)V
          //   826: aload 19
          //   828: astore 28
          //   830: aload 17
          //   832: astore 20
          //   834: aload 16
          //   836: astore 21
          //   838: aload 19
          //   840: astore 29
          //   842: iload_1
          //   843: istore_3
          //   844: aload 17
          //   846: astore 22
          //   848: aload 16
          //   850: astore 23
          //   852: iload 5
          //   854: istore 4
          //   856: aload 19
          //   858: astore 31
          //   860: aload 17
          //   862: astore 26
          //   864: aload 16
          //   866: astore 27
          //   868: aload 19
          //   870: astore 30
          //   872: aload 17
          //   874: astore 24
          //   876: aload 16
          //   878: astore 25
          //   880: aload 19
          //   882: astore 18
          //   884: aload 17
          //   886: astore 14
          //   888: aload 16
          //   890: astore 15
          //   892: aload 19
          //   894: iconst_1
          //   895: invokevirtual 127	java/net/HttpURLConnection:setDoOutput	(Z)V
          //   898: aload 19
          //   900: astore 28
          //   902: aload 17
          //   904: astore 20
          //   906: aload 16
          //   908: astore 21
          //   910: aload 19
          //   912: astore 29
          //   914: iload_1
          //   915: istore_3
          //   916: aload 17
          //   918: astore 22
          //   920: aload 16
          //   922: astore 23
          //   924: iload 5
          //   926: istore 4
          //   928: aload 19
          //   930: astore 31
          //   932: aload 17
          //   934: astore 26
          //   936: aload 16
          //   938: astore 27
          //   940: aload 19
          //   942: astore 30
          //   944: aload 17
          //   946: astore 24
          //   948: aload 16
          //   950: astore 25
          //   952: aload 19
          //   954: astore 18
          //   956: aload 17
          //   958: astore 14
          //   960: aload 16
          //   962: astore 15
          //   964: aload 19
          //   966: iconst_0
          //   967: invokevirtual 130	java/net/HttpURLConnection:setUseCaches	(Z)V
          //   970: aload 19
          //   972: astore 28
          //   974: aload 17
          //   976: astore 20
          //   978: aload 16
          //   980: astore 21
          //   982: aload 19
          //   984: astore 29
          //   986: iload_1
          //   987: istore_3
          //   988: aload 17
          //   990: astore 22
          //   992: aload 16
          //   994: astore 23
          //   996: iload 5
          //   998: istore 4
          //   1000: aload 19
          //   1002: astore 31
          //   1004: aload 17
          //   1006: astore 26
          //   1008: aload 16
          //   1010: astore 27
          //   1012: aload 19
          //   1014: astore 30
          //   1016: aload 17
          //   1018: astore 24
          //   1020: aload 16
          //   1022: astore 25
          //   1024: aload 19
          //   1026: astore 18
          //   1028: aload 17
          //   1030: astore 14
          //   1032: aload 16
          //   1034: astore 15
          //   1036: aload 19
          //   1038: aload_0
          //   1039: getfield 53	org/apache/cordova/filetransfer/FileTransfer$3:val$httpMethod	Ljava/lang/String;
          //   1042: invokevirtual 134	java/net/HttpURLConnection:setRequestMethod	(Ljava/lang/String;)V
          //   1045: aload 19
          //   1047: astore 28
          //   1049: aload 17
          //   1051: astore 20
          //   1053: aload 16
          //   1055: astore 21
          //   1057: aload 19
          //   1059: astore 29
          //   1061: iload_1
          //   1062: istore_3
          //   1063: aload 17
          //   1065: astore 22
          //   1067: aload 16
          //   1069: astore 23
          //   1071: iload 5
          //   1073: istore 4
          //   1075: aload 19
          //   1077: astore 31
          //   1079: aload 17
          //   1081: astore 26
          //   1083: aload 16
          //   1085: astore 27
          //   1087: aload 19
          //   1089: astore 30
          //   1091: aload 17
          //   1093: astore 24
          //   1095: aload 16
          //   1097: astore 25
          //   1099: aload 19
          //   1101: astore 18
          //   1103: aload 17
          //   1105: astore 14
          //   1107: aload 16
          //   1109: astore 15
          //   1111: aload 19
          //   1113: ldc -120
          //   1115: ldc -118
          //   1117: invokevirtual 142	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
          //   1120: aload 19
          //   1122: astore 28
          //   1124: aload 17
          //   1126: astore 20
          //   1128: aload 16
          //   1130: astore 21
          //   1132: aload 19
          //   1134: astore 29
          //   1136: iload_1
          //   1137: istore_3
          //   1138: aload 17
          //   1140: astore 22
          //   1142: aload 16
          //   1144: astore 23
          //   1146: iload 5
          //   1148: istore 4
          //   1150: aload 19
          //   1152: astore 31
          //   1154: aload 17
          //   1156: astore 26
          //   1158: aload 16
          //   1160: astore 27
          //   1162: aload 19
          //   1164: astore 30
          //   1166: aload 17
          //   1168: astore 24
          //   1170: aload 16
          //   1172: astore 25
          //   1174: aload 19
          //   1176: astore 18
          //   1178: aload 17
          //   1180: astore 14
          //   1182: aload 16
          //   1184: astore 15
          //   1186: invokestatic 148	android/webkit/CookieManager:getInstance	()Landroid/webkit/CookieManager;
          //   1189: aload_0
          //   1190: getfield 55	org/apache/cordova/filetransfer/FileTransfer$3:val$target	Ljava/lang/String;
          //   1193: invokevirtual 152	android/webkit/CookieManager:getCookie	(Ljava/lang/String;)Ljava/lang/String;
          //   1196: astore 32
          //   1198: aload 32
          //   1200: ifnull +78 -> 1278
          //   1203: aload 19
          //   1205: astore 28
          //   1207: aload 17
          //   1209: astore 20
          //   1211: aload 16
          //   1213: astore 21
          //   1215: aload 19
          //   1217: astore 29
          //   1219: iload_1
          //   1220: istore_3
          //   1221: aload 17
          //   1223: astore 22
          //   1225: aload 16
          //   1227: astore 23
          //   1229: iload 5
          //   1231: istore 4
          //   1233: aload 19
          //   1235: astore 31
          //   1237: aload 17
          //   1239: astore 26
          //   1241: aload 16
          //   1243: astore 27
          //   1245: aload 19
          //   1247: astore 30
          //   1249: aload 17
          //   1251: astore 24
          //   1253: aload 16
          //   1255: astore 25
          //   1257: aload 19
          //   1259: astore 18
          //   1261: aload 17
          //   1263: astore 14
          //   1265: aload 16
          //   1267: astore 15
          //   1269: aload 19
          //   1271: ldc -102
          //   1273: aload 32
          //   1275: invokevirtual 142	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
          //   1278: aload 19
          //   1280: astore 28
          //   1282: aload 17
          //   1284: astore 20
          //   1286: aload 16
          //   1288: astore 21
          //   1290: aload 19
          //   1292: astore 29
          //   1294: iload_1
          //   1295: istore_3
          //   1296: aload 17
          //   1298: astore 22
          //   1300: aload 16
          //   1302: astore 23
          //   1304: iload 5
          //   1306: istore 4
          //   1308: aload 19
          //   1310: astore 31
          //   1312: aload 17
          //   1314: astore 26
          //   1316: aload 16
          //   1318: astore 27
          //   1320: aload 19
          //   1322: astore 30
          //   1324: aload 17
          //   1326: astore 24
          //   1328: aload 16
          //   1330: astore 25
          //   1332: aload 19
          //   1334: astore 18
          //   1336: aload 17
          //   1338: astore 14
          //   1340: aload 16
          //   1342: astore 15
          //   1344: aload_0
          //   1345: getfield 57	org/apache/cordova/filetransfer/FileTransfer$3:val$headers	Lorg/json/JSONObject;
          //   1348: ifnull +78 -> 1426
          //   1351: aload 19
          //   1353: astore 28
          //   1355: aload 17
          //   1357: astore 20
          //   1359: aload 16
          //   1361: astore 21
          //   1363: aload 19
          //   1365: astore 29
          //   1367: iload_1
          //   1368: istore_3
          //   1369: aload 17
          //   1371: astore 22
          //   1373: aload 16
          //   1375: astore 23
          //   1377: iload 5
          //   1379: istore 4
          //   1381: aload 19
          //   1383: astore 31
          //   1385: aload 17
          //   1387: astore 26
          //   1389: aload 16
          //   1391: astore 27
          //   1393: aload 19
          //   1395: astore 30
          //   1397: aload 17
          //   1399: astore 24
          //   1401: aload 16
          //   1403: astore 25
          //   1405: aload 19
          //   1407: astore 18
          //   1409: aload 17
          //   1411: astore 14
          //   1413: aload 16
          //   1415: astore 15
          //   1417: aload 19
          //   1419: aload_0
          //   1420: getfield 57	org/apache/cordova/filetransfer/FileTransfer$3:val$headers	Lorg/json/JSONObject;
          //   1423: invokestatic 158	org/apache/cordova/filetransfer/FileTransfer:access$3	(Ljava/net/URLConnection;Lorg/json/JSONObject;)V
          //   1426: aload 19
          //   1428: astore 28
          //   1430: aload 17
          //   1432: astore 20
          //   1434: aload 16
          //   1436: astore 21
          //   1438: aload 19
          //   1440: astore 29
          //   1442: iload_1
          //   1443: istore_3
          //   1444: aload 17
          //   1446: astore 22
          //   1448: aload 16
          //   1450: astore 23
          //   1452: iload 5
          //   1454: istore 4
          //   1456: aload 19
          //   1458: astore 31
          //   1460: aload 17
          //   1462: astore 26
          //   1464: aload 16
          //   1466: astore 27
          //   1468: aload 19
          //   1470: astore 30
          //   1472: aload 17
          //   1474: astore 24
          //   1476: aload 16
          //   1478: astore 25
          //   1480: aload 19
          //   1482: astore 18
          //   1484: aload 17
          //   1486: astore 14
          //   1488: aload 16
          //   1490: astore 15
          //   1492: new 160	java/lang/StringBuilder
          //   1495: dup
          //   1496: invokespecial 161	java/lang/StringBuilder:<init>	()V
          //   1499: astore 32
          //   1501: aload 19
          //   1503: astore 28
          //   1505: aload 17
          //   1507: astore 20
          //   1509: aload 16
          //   1511: astore 21
          //   1513: aload 19
          //   1515: astore 29
          //   1517: iload_1
          //   1518: istore_3
          //   1519: aload 17
          //   1521: astore 22
          //   1523: aload 16
          //   1525: astore 23
          //   1527: iload 5
          //   1529: istore 4
          //   1531: aload 19
          //   1533: astore 30
          //   1535: aload 17
          //   1537: astore 24
          //   1539: aload 16
          //   1541: astore 25
          //   1543: aload 19
          //   1545: astore 18
          //   1547: aload 17
          //   1549: astore 14
          //   1551: aload 16
          //   1553: astore 15
          //   1555: aload_0
          //   1556: getfield 59	org/apache/cordova/filetransfer/FileTransfer$3:val$params	Lorg/json/JSONObject;
          //   1559: invokevirtual 167	org/json/JSONObject:keys	()Ljava/util/Iterator;
          //   1562: astore 26
          //   1564: aload 19
          //   1566: astore 28
          //   1568: aload 17
          //   1570: astore 20
          //   1572: aload 16
          //   1574: astore 21
          //   1576: aload 19
          //   1578: astore 29
          //   1580: iload_1
          //   1581: istore_3
          //   1582: aload 17
          //   1584: astore 22
          //   1586: aload 16
          //   1588: astore 23
          //   1590: iload 5
          //   1592: istore 4
          //   1594: aload 19
          //   1596: astore 30
          //   1598: aload 17
          //   1600: astore 24
          //   1602: aload 16
          //   1604: astore 25
          //   1606: aload 19
          //   1608: astore 18
          //   1610: aload 17
          //   1612: astore 14
          //   1614: aload 16
          //   1616: astore 15
          //   1618: aload 26
          //   1620: invokeinterface 173 1 0
          //   1625: istore 9
          //   1627: iload 9
          //   1629: ifne +1823 -> 3452
          //   1632: aload 19
          //   1634: astore 28
          //   1636: aload 17
          //   1638: astore 20
          //   1640: aload 16
          //   1642: astore 21
          //   1644: aload 19
          //   1646: astore 29
          //   1648: iload_1
          //   1649: istore_3
          //   1650: aload 17
          //   1652: astore 22
          //   1654: aload 16
          //   1656: astore 23
          //   1658: iload 5
          //   1660: istore 4
          //   1662: aload 19
          //   1664: astore 31
          //   1666: aload 17
          //   1668: astore 26
          //   1670: aload 16
          //   1672: astore 27
          //   1674: aload 19
          //   1676: astore 30
          //   1678: aload 17
          //   1680: astore 24
          //   1682: aload 16
          //   1684: astore 25
          //   1686: aload 19
          //   1688: astore 18
          //   1690: aload 17
          //   1692: astore 14
          //   1694: aload 16
          //   1696: astore 15
          //   1698: aload 32
          //   1700: ldc -81
          //   1702: invokevirtual 179	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   1705: ldc -75
          //   1707: invokevirtual 179	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   1710: ldc -73
          //   1712: invokevirtual 179	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   1715: pop
          //   1716: aload 19
          //   1718: astore 28
          //   1720: aload 17
          //   1722: astore 20
          //   1724: aload 16
          //   1726: astore 21
          //   1728: aload 19
          //   1730: astore 29
          //   1732: iload_1
          //   1733: istore_3
          //   1734: aload 17
          //   1736: astore 22
          //   1738: aload 16
          //   1740: astore 23
          //   1742: iload 5
          //   1744: istore 4
          //   1746: aload 19
          //   1748: astore 31
          //   1750: aload 17
          //   1752: astore 26
          //   1754: aload 16
          //   1756: astore 27
          //   1758: aload 19
          //   1760: astore 30
          //   1762: aload 17
          //   1764: astore 24
          //   1766: aload 16
          //   1768: astore 25
          //   1770: aload 19
          //   1772: astore 18
          //   1774: aload 17
          //   1776: astore 14
          //   1778: aload 16
          //   1780: astore 15
          //   1782: aload 32
          //   1784: ldc -71
          //   1786: invokevirtual 179	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   1789: aload_0
          //   1790: getfield 61	org/apache/cordova/filetransfer/FileTransfer$3:val$fileKey	Ljava/lang/String;
          //   1793: invokevirtual 179	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   1796: ldc -69
          //   1798: invokevirtual 179	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   1801: pop
          //   1802: aload 19
          //   1804: astore 28
          //   1806: aload 17
          //   1808: astore 20
          //   1810: aload 16
          //   1812: astore 21
          //   1814: aload 19
          //   1816: astore 29
          //   1818: iload_1
          //   1819: istore_3
          //   1820: aload 17
          //   1822: astore 22
          //   1824: aload 16
          //   1826: astore 23
          //   1828: iload 5
          //   1830: istore 4
          //   1832: aload 19
          //   1834: astore 31
          //   1836: aload 17
          //   1838: astore 26
          //   1840: aload 16
          //   1842: astore 27
          //   1844: aload 19
          //   1846: astore 30
          //   1848: aload 17
          //   1850: astore 24
          //   1852: aload 16
          //   1854: astore 25
          //   1856: aload 19
          //   1858: astore 18
          //   1860: aload 17
          //   1862: astore 14
          //   1864: aload 16
          //   1866: astore 15
          //   1868: aload 32
          //   1870: ldc -67
          //   1872: invokevirtual 179	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   1875: aload_0
          //   1876: getfield 63	org/apache/cordova/filetransfer/FileTransfer$3:val$fileName	Ljava/lang/String;
          //   1879: invokevirtual 179	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   1882: bipush 34
          //   1884: invokevirtual 192	java/lang/StringBuilder:append	(C)Ljava/lang/StringBuilder;
          //   1887: ldc -73
          //   1889: invokevirtual 179	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   1892: pop
          //   1893: aload 19
          //   1895: astore 28
          //   1897: aload 17
          //   1899: astore 20
          //   1901: aload 16
          //   1903: astore 21
          //   1905: aload 19
          //   1907: astore 29
          //   1909: iload_1
          //   1910: istore_3
          //   1911: aload 17
          //   1913: astore 22
          //   1915: aload 16
          //   1917: astore 23
          //   1919: iload 5
          //   1921: istore 4
          //   1923: aload 19
          //   1925: astore 31
          //   1927: aload 17
          //   1929: astore 26
          //   1931: aload 16
          //   1933: astore 27
          //   1935: aload 19
          //   1937: astore 30
          //   1939: aload 17
          //   1941: astore 24
          //   1943: aload 16
          //   1945: astore 25
          //   1947: aload 19
          //   1949: astore 18
          //   1951: aload 17
          //   1953: astore 14
          //   1955: aload 16
          //   1957: astore 15
          //   1959: aload 32
          //   1961: ldc -62
          //   1963: invokevirtual 179	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   1966: aload_0
          //   1967: getfield 65	org/apache/cordova/filetransfer/FileTransfer$3:val$mimeType	Ljava/lang/String;
          //   1970: invokevirtual 179	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   1973: ldc -73
          //   1975: invokevirtual 179	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   1978: ldc -73
          //   1980: invokevirtual 179	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   1983: pop
          //   1984: aload 19
          //   1986: astore 28
          //   1988: aload 17
          //   1990: astore 20
          //   1992: aload 16
          //   1994: astore 21
          //   1996: aload 19
          //   1998: astore 29
          //   2000: iload_1
          //   2001: istore_3
          //   2002: aload 17
          //   2004: astore 22
          //   2006: aload 16
          //   2008: astore 23
          //   2010: iload 5
          //   2012: istore 4
          //   2014: aload 19
          //   2016: astore 31
          //   2018: aload 17
          //   2020: astore 26
          //   2022: aload 16
          //   2024: astore 27
          //   2026: aload 19
          //   2028: astore 30
          //   2030: aload 17
          //   2032: astore 24
          //   2034: aload 16
          //   2036: astore 25
          //   2038: aload 19
          //   2040: astore 18
          //   2042: aload 17
          //   2044: astore 14
          //   2046: aload 16
          //   2048: astore 15
          //   2050: aload 32
          //   2052: invokevirtual 198	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   2055: ldc -56
          //   2057: invokevirtual 206	java/lang/String:getBytes	(Ljava/lang/String;)[B
          //   2060: astore 36
          //   2062: aload 19
          //   2064: astore 28
          //   2066: aload 17
          //   2068: astore 20
          //   2070: aload 16
          //   2072: astore 21
          //   2074: aload 19
          //   2076: astore 29
          //   2078: iload_1
          //   2079: istore_3
          //   2080: aload 17
          //   2082: astore 22
          //   2084: aload 16
          //   2086: astore 23
          //   2088: iload 5
          //   2090: istore 4
          //   2092: aload 19
          //   2094: astore 31
          //   2096: aload 17
          //   2098: astore 26
          //   2100: aload 16
          //   2102: astore 27
          //   2104: aload 19
          //   2106: astore 30
          //   2108: aload 17
          //   2110: astore 24
          //   2112: aload 16
          //   2114: astore 25
          //   2116: aload 19
          //   2118: astore 18
          //   2120: aload 17
          //   2122: astore 14
          //   2124: aload 16
          //   2126: astore 15
          //   2128: ldc -48
          //   2130: ldc -56
          //   2132: invokevirtual 206	java/lang/String:getBytes	(Ljava/lang/String;)[B
          //   2135: astore 35
          //   2137: aload 19
          //   2139: astore 28
          //   2141: aload 17
          //   2143: astore 20
          //   2145: aload 16
          //   2147: astore 21
          //   2149: aload 19
          //   2151: astore 29
          //   2153: iload_1
          //   2154: istore_3
          //   2155: aload 17
          //   2157: astore 22
          //   2159: aload 16
          //   2161: astore 23
          //   2163: iload 5
          //   2165: istore 4
          //   2167: aload 19
          //   2169: astore 31
          //   2171: aload 17
          //   2173: astore 26
          //   2175: aload 16
          //   2177: astore 27
          //   2179: aload 19
          //   2181: astore 30
          //   2183: aload 17
          //   2185: astore 24
          //   2187: aload 16
          //   2189: astore 25
          //   2191: aload 19
          //   2193: astore 18
          //   2195: aload 17
          //   2197: astore 14
          //   2199: aload 16
          //   2201: astore 15
          //   2203: aload_0
          //   2204: getfield 49	org/apache/cordova/filetransfer/FileTransfer$3:val$resourceApi	Lorg/apache/cordova/CordovaResourceApi;
          //   2207: aload_0
          //   2208: getfield 67	org/apache/cordova/filetransfer/FileTransfer$3:val$sourceUri	Landroid/net/Uri;
          //   2211: invokevirtual 212	org/apache/cordova/CordovaResourceApi:openForRead	(Landroid/net/Uri;)Lorg/apache/cordova/CordovaResourceApi$OpenForReadResult;
          //   2214: astore 34
          //   2216: aload 19
          //   2218: astore 28
          //   2220: aload 17
          //   2222: astore 20
          //   2224: aload 16
          //   2226: astore 21
          //   2228: aload 19
          //   2230: astore 29
          //   2232: iload_1
          //   2233: istore_3
          //   2234: aload 17
          //   2236: astore 22
          //   2238: aload 16
          //   2240: astore 23
          //   2242: iload 5
          //   2244: istore 4
          //   2246: aload 19
          //   2248: astore 31
          //   2250: aload 17
          //   2252: astore 26
          //   2254: aload 16
          //   2256: astore 27
          //   2258: aload 19
          //   2260: astore 30
          //   2262: aload 17
          //   2264: astore 24
          //   2266: aload 16
          //   2268: astore 25
          //   2270: aload 19
          //   2272: astore 18
          //   2274: aload 17
          //   2276: astore 14
          //   2278: aload 16
          //   2280: astore 15
          //   2282: aload 36
          //   2284: arraylength
          //   2285: istore 7
          //   2287: aload 19
          //   2289: astore 28
          //   2291: aload 17
          //   2293: astore 20
          //   2295: aload 16
          //   2297: astore 21
          //   2299: aload 19
          //   2301: astore 29
          //   2303: iload_1
          //   2304: istore_3
          //   2305: aload 17
          //   2307: astore 22
          //   2309: aload 16
          //   2311: astore 23
          //   2313: iload 5
          //   2315: istore 4
          //   2317: aload 19
          //   2319: astore 31
          //   2321: aload 17
          //   2323: astore 26
          //   2325: aload 16
          //   2327: astore 27
          //   2329: aload 19
          //   2331: astore 30
          //   2333: aload 17
          //   2335: astore 24
          //   2337: aload 16
          //   2339: astore 25
          //   2341: aload 19
          //   2343: astore 18
          //   2345: aload 17
          //   2347: astore 14
          //   2349: aload 16
          //   2351: astore 15
          //   2353: aload 35
          //   2355: arraylength
          //   2356: istore 8
          //   2358: iload_1
          //   2359: istore_2
          //   2360: aload 19
          //   2362: astore 28
          //   2364: aload 17
          //   2366: astore 20
          //   2368: aload 16
          //   2370: astore 21
          //   2372: aload 19
          //   2374: astore 29
          //   2376: iload_1
          //   2377: istore_3
          //   2378: aload 17
          //   2380: astore 22
          //   2382: aload 16
          //   2384: astore 23
          //   2386: iload 5
          //   2388: istore 4
          //   2390: aload 19
          //   2392: astore 31
          //   2394: aload 17
          //   2396: astore 26
          //   2398: aload 16
          //   2400: astore 27
          //   2402: aload 19
          //   2404: astore 30
          //   2406: aload 17
          //   2408: astore 24
          //   2410: aload 16
          //   2412: astore 25
          //   2414: aload 19
          //   2416: astore 18
          //   2418: aload 17
          //   2420: astore 14
          //   2422: aload 16
          //   2424: astore 15
          //   2426: aload 34
          //   2428: getfield 218	org/apache/cordova/CordovaResourceApi$OpenForReadResult:length	J
          //   2431: lconst_0
          //   2432: lcmp
          //   2433: iflt +227 -> 2660
          //   2436: aload 19
          //   2438: astore 28
          //   2440: aload 17
          //   2442: astore 20
          //   2444: aload 16
          //   2446: astore 21
          //   2448: aload 19
          //   2450: astore 29
          //   2452: iload_1
          //   2453: istore_3
          //   2454: aload 17
          //   2456: astore 22
          //   2458: aload 16
          //   2460: astore 23
          //   2462: iload 5
          //   2464: istore 4
          //   2466: aload 19
          //   2468: astore 31
          //   2470: aload 17
          //   2472: astore 26
          //   2474: aload 16
          //   2476: astore 27
          //   2478: aload 19
          //   2480: astore 30
          //   2482: aload 17
          //   2484: astore 24
          //   2486: aload 16
          //   2488: astore 25
          //   2490: aload 19
          //   2492: astore 18
          //   2494: aload 17
          //   2496: astore 14
          //   2498: aload 16
          //   2500: astore 15
          //   2502: aload 34
          //   2504: getfield 218	org/apache/cordova/CordovaResourceApi$OpenForReadResult:length	J
          //   2507: l2i
          //   2508: iload 7
          //   2510: iload 8
          //   2512: iadd
          //   2513: iadd
          //   2514: istore_2
          //   2515: aload 19
          //   2517: astore 28
          //   2519: aload 17
          //   2521: astore 20
          //   2523: aload 16
          //   2525: astore 21
          //   2527: aload 19
          //   2529: astore 29
          //   2531: iload_2
          //   2532: istore_3
          //   2533: aload 17
          //   2535: astore 22
          //   2537: aload 16
          //   2539: astore 23
          //   2541: iload 5
          //   2543: istore 4
          //   2545: aload 19
          //   2547: astore 31
          //   2549: aload 17
          //   2551: astore 26
          //   2553: aload 16
          //   2555: astore 27
          //   2557: aload 19
          //   2559: astore 30
          //   2561: aload 17
          //   2563: astore 24
          //   2565: aload 16
          //   2567: astore 25
          //   2569: aload 19
          //   2571: astore 18
          //   2573: aload 17
          //   2575: astore 14
          //   2577: aload 16
          //   2579: astore 15
          //   2581: aload 47
          //   2583: iconst_1
          //   2584: invokevirtual 221	org/apache/cordova/filetransfer/FileProgressResult:setLengthComputable	(Z)V
          //   2587: aload 19
          //   2589: astore 28
          //   2591: aload 17
          //   2593: astore 20
          //   2595: aload 16
          //   2597: astore 21
          //   2599: aload 19
          //   2601: astore 29
          //   2603: iload_2
          //   2604: istore_3
          //   2605: aload 17
          //   2607: astore 22
          //   2609: aload 16
          //   2611: astore 23
          //   2613: iload 5
          //   2615: istore 4
          //   2617: aload 19
          //   2619: astore 31
          //   2621: aload 17
          //   2623: astore 26
          //   2625: aload 16
          //   2627: astore 27
          //   2629: aload 19
          //   2631: astore 30
          //   2633: aload 17
          //   2635: astore 24
          //   2637: aload 16
          //   2639: astore 25
          //   2641: aload 19
          //   2643: astore 18
          //   2645: aload 17
          //   2647: astore 14
          //   2649: aload 16
          //   2651: astore 15
          //   2653: aload 47
          //   2655: iload_2
          //   2656: i2l
          //   2657: invokevirtual 225	org/apache/cordova/filetransfer/FileProgressResult:setTotal	(J)V
          //   2660: aload 19
          //   2662: astore 28
          //   2664: aload 17
          //   2666: astore 20
          //   2668: aload 16
          //   2670: astore 21
          //   2672: aload 19
          //   2674: astore 29
          //   2676: iload_2
          //   2677: istore_3
          //   2678: aload 17
          //   2680: astore 22
          //   2682: aload 16
          //   2684: astore 23
          //   2686: iload 5
          //   2688: istore 4
          //   2690: aload 19
          //   2692: astore 31
          //   2694: aload 17
          //   2696: astore 26
          //   2698: aload 16
          //   2700: astore 27
          //   2702: aload 19
          //   2704: astore 30
          //   2706: aload 17
          //   2708: astore 24
          //   2710: aload 16
          //   2712: astore 25
          //   2714: aload 19
          //   2716: astore 18
          //   2718: aload 17
          //   2720: astore 14
          //   2722: aload 16
          //   2724: astore 15
          //   2726: ldc -29
          //   2728: new 160	java/lang/StringBuilder
          //   2731: dup
          //   2732: ldc -27
          //   2734: invokespecial 231	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
          //   2737: iload_2
          //   2738: invokevirtual 234	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
          //   2741: invokevirtual 198	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   2744: invokestatic 240	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
          //   2747: pop
          //   2748: aload 19
          //   2750: astore 28
          //   2752: aload 17
          //   2754: astore 20
          //   2756: aload 16
          //   2758: astore 21
          //   2760: aload 19
          //   2762: astore 29
          //   2764: iload_2
          //   2765: istore_3
          //   2766: aload 17
          //   2768: astore 22
          //   2770: aload 16
          //   2772: astore 23
          //   2774: iload 5
          //   2776: istore 4
          //   2778: aload 19
          //   2780: astore 31
          //   2782: aload 17
          //   2784: astore 26
          //   2786: aload 16
          //   2788: astore 27
          //   2790: aload 19
          //   2792: astore 30
          //   2794: aload 17
          //   2796: astore 24
          //   2798: aload 16
          //   2800: astore 25
          //   2802: aload 19
          //   2804: astore 18
          //   2806: aload 17
          //   2808: astore 14
          //   2810: aload 16
          //   2812: astore 15
          //   2814: aload_0
          //   2815: getfield 69	org/apache/cordova/filetransfer/FileTransfer$3:val$chunkedMode	Z
          //   2818: ifeq +1351 -> 4169
          //   2821: aload 19
          //   2823: astore 28
          //   2825: aload 17
          //   2827: astore 20
          //   2829: aload 16
          //   2831: astore 21
          //   2833: aload 19
          //   2835: astore 29
          //   2837: iload_2
          //   2838: istore_3
          //   2839: aload 17
          //   2841: astore 22
          //   2843: aload 16
          //   2845: astore 23
          //   2847: iload 5
          //   2849: istore 4
          //   2851: aload 19
          //   2853: astore 31
          //   2855: aload 17
          //   2857: astore 26
          //   2859: aload 16
          //   2861: astore 27
          //   2863: aload 19
          //   2865: astore 30
          //   2867: aload 17
          //   2869: astore 24
          //   2871: aload 16
          //   2873: astore 25
          //   2875: aload 19
          //   2877: astore 18
          //   2879: aload 17
          //   2881: astore 14
          //   2883: aload 16
          //   2885: astore 15
          //   2887: getstatic 246	android/os/Build$VERSION:SDK_INT	I
          //   2890: bipush 8
          //   2892: if_icmplt +4296 -> 7188
          //   2895: aload 19
          //   2897: astore 28
          //   2899: aload 17
          //   2901: astore 20
          //   2903: aload 16
          //   2905: astore 21
          //   2907: aload 19
          //   2909: astore 29
          //   2911: iload_2
          //   2912: istore_3
          //   2913: aload 17
          //   2915: astore 22
          //   2917: aload 16
          //   2919: astore 23
          //   2921: iload 5
          //   2923: istore 4
          //   2925: aload 19
          //   2927: astore 31
          //   2929: aload 17
          //   2931: astore 26
          //   2933: aload 16
          //   2935: astore 27
          //   2937: aload 19
          //   2939: astore 30
          //   2941: aload 17
          //   2943: astore 24
          //   2945: aload 16
          //   2947: astore 25
          //   2949: aload 19
          //   2951: astore 18
          //   2953: aload 17
          //   2955: astore 14
          //   2957: aload 16
          //   2959: astore 15
          //   2961: aload_0
          //   2962: getfield 47	org/apache/cordova/filetransfer/FileTransfer$3:val$useHttps	Z
          //   2965: ifeq +1204 -> 4169
          //   2968: goto +4220 -> 7188
          //   2971: iload_1
          //   2972: ifeq +1207 -> 4179
          //   2975: aload 19
          //   2977: astore 28
          //   2979: aload 17
          //   2981: astore 20
          //   2983: aload 16
          //   2985: astore 21
          //   2987: aload 19
          //   2989: astore 29
          //   2991: iload_2
          //   2992: istore_3
          //   2993: aload 17
          //   2995: astore 22
          //   2997: aload 16
          //   2999: astore 23
          //   3001: iload 5
          //   3003: istore 4
          //   3005: aload 19
          //   3007: astore 31
          //   3009: aload 17
          //   3011: astore 26
          //   3013: aload 16
          //   3015: astore 27
          //   3017: aload 19
          //   3019: astore 30
          //   3021: aload 17
          //   3023: astore 24
          //   3025: aload 16
          //   3027: astore 25
          //   3029: aload 19
          //   3031: astore 18
          //   3033: aload 17
          //   3035: astore 14
          //   3037: aload 16
          //   3039: astore 15
          //   3041: aload 19
          //   3043: sipush 16384
          //   3046: invokevirtual 250	java/net/HttpURLConnection:setChunkedStreamingMode	(I)V
          //   3049: aload 19
          //   3051: astore 28
          //   3053: aload 17
          //   3055: astore 20
          //   3057: aload 16
          //   3059: astore 21
          //   3061: aload 19
          //   3063: astore 29
          //   3065: iload_2
          //   3066: istore_3
          //   3067: aload 17
          //   3069: astore 22
          //   3071: aload 16
          //   3073: astore 23
          //   3075: iload 5
          //   3077: istore 4
          //   3079: aload 19
          //   3081: astore 31
          //   3083: aload 17
          //   3085: astore 26
          //   3087: aload 16
          //   3089: astore 27
          //   3091: aload 19
          //   3093: astore 30
          //   3095: aload 17
          //   3097: astore 24
          //   3099: aload 16
          //   3101: astore 25
          //   3103: aload 19
          //   3105: astore 18
          //   3107: aload 17
          //   3109: astore 14
          //   3111: aload 16
          //   3113: astore 15
          //   3115: aload 19
          //   3117: ldc -4
          //   3119: ldc -2
          //   3121: invokevirtual 142	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
          //   3124: aload 19
          //   3126: astore 28
          //   3128: aload 17
          //   3130: astore 20
          //   3132: aload 16
          //   3134: astore 21
          //   3136: aload 19
          //   3138: astore 29
          //   3140: iload_2
          //   3141: istore_3
          //   3142: aload 17
          //   3144: astore 22
          //   3146: aload 16
          //   3148: astore 23
          //   3150: iload 5
          //   3152: istore 4
          //   3154: aload 19
          //   3156: astore 31
          //   3158: aload 17
          //   3160: astore 26
          //   3162: aload 16
          //   3164: astore 27
          //   3166: aload 19
          //   3168: astore 30
          //   3170: aload 17
          //   3172: astore 24
          //   3174: aload 16
          //   3176: astore 25
          //   3178: aload 19
          //   3180: astore 18
          //   3182: aload 17
          //   3184: astore 14
          //   3186: aload 16
          //   3188: astore 15
          //   3190: aload 19
          //   3192: invokevirtual 257	java/net/HttpURLConnection:connect	()V
          //   3195: aconst_null
          //   3196: astore 32
          //   3198: iload 6
          //   3200: istore_1
          //   3201: aload 19
          //   3203: invokevirtual 261	java/net/HttpURLConnection:getOutputStream	()Ljava/io/OutputStream;
          //   3206: astore 33
          //   3208: aload 33
          //   3210: astore 32
          //   3212: iload 6
          //   3214: istore_1
          //   3215: aload_0
          //   3216: getfield 41	org/apache/cordova/filetransfer/FileTransfer$3:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
          //   3219: astore 14
          //   3221: aload 33
          //   3223: astore 32
          //   3225: iload 6
          //   3227: istore_1
          //   3228: aload 14
          //   3230: monitorenter
          //   3231: aload_0
          //   3232: getfield 41	org/apache/cordova/filetransfer/FileTransfer$3:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
          //   3235: getfield 89	org/apache/cordova/filetransfer/FileTransfer$RequestContext:aborted	Z
          //   3238: ifeq +1227 -> 4465
          //   3241: aload 14
          //   3243: monitorexit
          //   3244: aload 19
          //   3246: astore 28
          //   3248: aload 17
          //   3250: astore 20
          //   3252: aload 16
          //   3254: astore 21
          //   3256: aload 19
          //   3258: astore 29
          //   3260: iload_2
          //   3261: istore_3
          //   3262: aload 17
          //   3264: astore 22
          //   3266: aload 16
          //   3268: astore 23
          //   3270: iload 5
          //   3272: istore 4
          //   3274: aload 19
          //   3276: astore 31
          //   3278: aload 17
          //   3280: astore 26
          //   3282: aload 16
          //   3284: astore 27
          //   3286: aload 19
          //   3288: astore 30
          //   3290: aload 17
          //   3292: astore 24
          //   3294: aload 16
          //   3296: astore 25
          //   3298: aload 19
          //   3300: astore 18
          //   3302: aload 17
          //   3304: astore 14
          //   3306: aload 16
          //   3308: astore 15
          //   3310: aload 34
          //   3312: getfield 265	org/apache/cordova/CordovaResourceApi$OpenForReadResult:inputStream	Ljava/io/InputStream;
          //   3315: invokestatic 269	org/apache/cordova/filetransfer/FileTransfer:access$4	(Ljava/io/Closeable;)V
          //   3318: aload 19
          //   3320: astore 28
          //   3322: aload 17
          //   3324: astore 20
          //   3326: aload 16
          //   3328: astore 21
          //   3330: aload 19
          //   3332: astore 29
          //   3334: iload_2
          //   3335: istore_3
          //   3336: aload 17
          //   3338: astore 22
          //   3340: aload 16
          //   3342: astore 23
          //   3344: iload 5
          //   3346: istore 4
          //   3348: aload 19
          //   3350: astore 31
          //   3352: aload 17
          //   3354: astore 26
          //   3356: aload 16
          //   3358: astore 27
          //   3360: aload 19
          //   3362: astore 30
          //   3364: aload 17
          //   3366: astore 24
          //   3368: aload 16
          //   3370: astore 25
          //   3372: aload 19
          //   3374: astore 18
          //   3376: aload 17
          //   3378: astore 14
          //   3380: aload 16
          //   3382: astore 15
          //   3384: aload 33
          //   3386: invokestatic 269	org/apache/cordova/filetransfer/FileTransfer:access$4	(Ljava/io/Closeable;)V
          //   3389: invokestatic 273	org/apache/cordova/filetransfer/FileTransfer:access$0	()Ljava/util/HashMap;
          //   3392: astore 14
          //   3394: aload 14
          //   3396: monitorenter
          //   3397: invokestatic 273	org/apache/cordova/filetransfer/FileTransfer:access$0	()Ljava/util/HashMap;
          //   3400: aload_0
          //   3401: getfield 43	org/apache/cordova/filetransfer/FileTransfer$3:val$objectId	Ljava/lang/String;
          //   3404: invokevirtual 279	java/util/HashMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
          //   3407: pop
          //   3408: aload 14
          //   3410: monitorexit
          //   3411: aload 19
          //   3413: ifnull -3403 -> 10
          //   3416: aload_0
          //   3417: getfield 45	org/apache/cordova/filetransfer/FileTransfer$3:val$trustEveryone	Z
          //   3420: ifeq -3410 -> 10
          //   3423: aload_0
          //   3424: getfield 47	org/apache/cordova/filetransfer/FileTransfer$3:val$useHttps	Z
          //   3427: ifeq -3417 -> 10
          //   3430: aload 19
          //   3432: checkcast 103	javax/net/ssl/HttpsURLConnection
          //   3435: astore 14
          //   3437: aload 14
          //   3439: aload 17
          //   3441: invokevirtual 118	javax/net/ssl/HttpsURLConnection:setHostnameVerifier	(Ljavax/net/ssl/HostnameVerifier;)V
          //   3444: aload 14
          //   3446: aload 16
          //   3448: invokevirtual 283	javax/net/ssl/HttpsURLConnection:setSSLSocketFactory	(Ljavax/net/ssl/SSLSocketFactory;)V
          //   3451: return
          //   3452: aload 19
          //   3454: astore 28
          //   3456: aload 17
          //   3458: astore 20
          //   3460: aload 16
          //   3462: astore 21
          //   3464: aload 19
          //   3466: astore 29
          //   3468: iload_1
          //   3469: istore_3
          //   3470: aload 17
          //   3472: astore 22
          //   3474: aload 16
          //   3476: astore 23
          //   3478: iload 5
          //   3480: istore 4
          //   3482: aload 19
          //   3484: astore 30
          //   3486: aload 17
          //   3488: astore 24
          //   3490: aload 16
          //   3492: astore 25
          //   3494: aload 19
          //   3496: astore 18
          //   3498: aload 17
          //   3500: astore 14
          //   3502: aload 16
          //   3504: astore 15
          //   3506: aload 26
          //   3508: invokeinterface 287 1 0
          //   3513: astore 27
          //   3515: aload 19
          //   3517: astore 28
          //   3519: aload 17
          //   3521: astore 20
          //   3523: aload 16
          //   3525: astore 21
          //   3527: aload 19
          //   3529: astore 29
          //   3531: iload_1
          //   3532: istore_3
          //   3533: aload 17
          //   3535: astore 22
          //   3537: aload 16
          //   3539: astore 23
          //   3541: iload 5
          //   3543: istore 4
          //   3545: aload 19
          //   3547: astore 30
          //   3549: aload 17
          //   3551: astore 24
          //   3553: aload 16
          //   3555: astore 25
          //   3557: aload 19
          //   3559: astore 18
          //   3561: aload 17
          //   3563: astore 14
          //   3565: aload 16
          //   3567: astore 15
          //   3569: aload 27
          //   3571: invokestatic 291	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
          //   3574: ldc_w 293
          //   3577: invokevirtual 297	java/lang/String:equals	(Ljava/lang/Object;)Z
          //   3580: ifne -2016 -> 1564
          //   3583: aload 19
          //   3585: astore 28
          //   3587: aload 17
          //   3589: astore 20
          //   3591: aload 16
          //   3593: astore 21
          //   3595: aload 19
          //   3597: astore 29
          //   3599: iload_1
          //   3600: istore_3
          //   3601: aload 17
          //   3603: astore 22
          //   3605: aload 16
          //   3607: astore 23
          //   3609: iload 5
          //   3611: istore 4
          //   3613: aload 19
          //   3615: astore 30
          //   3617: aload 17
          //   3619: astore 24
          //   3621: aload 16
          //   3623: astore 25
          //   3625: aload 19
          //   3627: astore 18
          //   3629: aload 17
          //   3631: astore 14
          //   3633: aload 16
          //   3635: astore 15
          //   3637: aload 32
          //   3639: ldc -81
          //   3641: invokevirtual 179	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   3644: ldc -75
          //   3646: invokevirtual 179	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   3649: ldc -73
          //   3651: invokevirtual 179	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   3654: pop
          //   3655: aload 19
          //   3657: astore 28
          //   3659: aload 17
          //   3661: astore 20
          //   3663: aload 16
          //   3665: astore 21
          //   3667: aload 19
          //   3669: astore 29
          //   3671: iload_1
          //   3672: istore_3
          //   3673: aload 17
          //   3675: astore 22
          //   3677: aload 16
          //   3679: astore 23
          //   3681: iload 5
          //   3683: istore 4
          //   3685: aload 19
          //   3687: astore 30
          //   3689: aload 17
          //   3691: astore 24
          //   3693: aload 16
          //   3695: astore 25
          //   3697: aload 19
          //   3699: astore 18
          //   3701: aload 17
          //   3703: astore 14
          //   3705: aload 16
          //   3707: astore 15
          //   3709: aload 32
          //   3711: ldc -71
          //   3713: invokevirtual 179	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   3716: aload 27
          //   3718: invokevirtual 298	java/lang/Object:toString	()Ljava/lang/String;
          //   3721: invokevirtual 179	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   3724: bipush 34
          //   3726: invokevirtual 192	java/lang/StringBuilder:append	(C)Ljava/lang/StringBuilder;
          //   3729: pop
          //   3730: aload 19
          //   3732: astore 28
          //   3734: aload 17
          //   3736: astore 20
          //   3738: aload 16
          //   3740: astore 21
          //   3742: aload 19
          //   3744: astore 29
          //   3746: iload_1
          //   3747: istore_3
          //   3748: aload 17
          //   3750: astore 22
          //   3752: aload 16
          //   3754: astore 23
          //   3756: iload 5
          //   3758: istore 4
          //   3760: aload 19
          //   3762: astore 30
          //   3764: aload 17
          //   3766: astore 24
          //   3768: aload 16
          //   3770: astore 25
          //   3772: aload 19
          //   3774: astore 18
          //   3776: aload 17
          //   3778: astore 14
          //   3780: aload 16
          //   3782: astore 15
          //   3784: aload 32
          //   3786: ldc -73
          //   3788: invokevirtual 179	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   3791: ldc -73
          //   3793: invokevirtual 179	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   3796: pop
          //   3797: aload 19
          //   3799: astore 28
          //   3801: aload 17
          //   3803: astore 20
          //   3805: aload 16
          //   3807: astore 21
          //   3809: aload 19
          //   3811: astore 29
          //   3813: iload_1
          //   3814: istore_3
          //   3815: aload 17
          //   3817: astore 22
          //   3819: aload 16
          //   3821: astore 23
          //   3823: iload 5
          //   3825: istore 4
          //   3827: aload 19
          //   3829: astore 30
          //   3831: aload 17
          //   3833: astore 24
          //   3835: aload 16
          //   3837: astore 25
          //   3839: aload 19
          //   3841: astore 18
          //   3843: aload 17
          //   3845: astore 14
          //   3847: aload 16
          //   3849: astore 15
          //   3851: aload 32
          //   3853: aload_0
          //   3854: getfield 59	org/apache/cordova/filetransfer/FileTransfer$3:val$params	Lorg/json/JSONObject;
          //   3857: aload 27
          //   3859: invokevirtual 298	java/lang/Object:toString	()Ljava/lang/String;
          //   3862: invokevirtual 301	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
          //   3865: invokevirtual 179	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   3868: pop
          //   3869: aload 19
          //   3871: astore 28
          //   3873: aload 17
          //   3875: astore 20
          //   3877: aload 16
          //   3879: astore 21
          //   3881: aload 19
          //   3883: astore 29
          //   3885: iload_1
          //   3886: istore_3
          //   3887: aload 17
          //   3889: astore 22
          //   3891: aload 16
          //   3893: astore 23
          //   3895: iload 5
          //   3897: istore 4
          //   3899: aload 19
          //   3901: astore 30
          //   3903: aload 17
          //   3905: astore 24
          //   3907: aload 16
          //   3909: astore 25
          //   3911: aload 19
          //   3913: astore 18
          //   3915: aload 17
          //   3917: astore 14
          //   3919: aload 16
          //   3921: astore 15
          //   3923: aload 32
          //   3925: ldc -73
          //   3927: invokevirtual 179	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   3930: pop
          //   3931: goto -2367 -> 1564
          //   3934: astore 33
          //   3936: aload 19
          //   3938: astore 28
          //   3940: aload 17
          //   3942: astore 20
          //   3944: aload 16
          //   3946: astore 21
          //   3948: aload 19
          //   3950: astore 29
          //   3952: iload_1
          //   3953: istore_3
          //   3954: aload 17
          //   3956: astore 22
          //   3958: aload 16
          //   3960: astore 23
          //   3962: iload 5
          //   3964: istore 4
          //   3966: aload 19
          //   3968: astore 31
          //   3970: aload 17
          //   3972: astore 26
          //   3974: aload 16
          //   3976: astore 27
          //   3978: aload 19
          //   3980: astore 30
          //   3982: aload 17
          //   3984: astore 24
          //   3986: aload 16
          //   3988: astore 25
          //   3990: aload 19
          //   3992: astore 18
          //   3994: aload 17
          //   3996: astore 14
          //   3998: aload 16
          //   4000: astore 15
          //   4002: ldc -29
          //   4004: aload 33
          //   4006: invokevirtual 304	org/json/JSONException:getMessage	()Ljava/lang/String;
          //   4009: aload 33
          //   4011: invokestatic 308	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
          //   4014: pop
          //   4015: goto -2383 -> 1632
          //   4018: astore 16
          //   4020: aload 28
          //   4022: astore 18
          //   4024: aload 20
          //   4026: astore 14
          //   4028: aload 21
          //   4030: astore 15
          //   4032: getstatic 311	org/apache/cordova/filetransfer/FileTransfer:FILE_NOT_FOUND_ERR	I
          //   4035: aload_0
          //   4036: getfield 71	org/apache/cordova/filetransfer/FileTransfer$3:val$source	Ljava/lang/String;
          //   4039: aload_0
          //   4040: getfield 55	org/apache/cordova/filetransfer/FileTransfer$3:val$target	Ljava/lang/String;
          //   4043: aload 28
          //   4045: invokestatic 315	org/apache/cordova/filetransfer/FileTransfer:access$6	(ILjava/lang/String;Ljava/lang/String;Ljava/net/URLConnection;)Lorg/json/JSONObject;
          //   4048: astore 17
          //   4050: aload 28
          //   4052: astore 18
          //   4054: aload 20
          //   4056: astore 14
          //   4058: aload 21
          //   4060: astore 15
          //   4062: ldc -29
          //   4064: aload 17
          //   4066: invokevirtual 316	org/json/JSONObject:toString	()Ljava/lang/String;
          //   4069: aload 16
          //   4071: invokestatic 308	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
          //   4074: pop
          //   4075: aload 28
          //   4077: astore 18
          //   4079: aload 20
          //   4081: astore 14
          //   4083: aload 21
          //   4085: astore 15
          //   4087: aload_0
          //   4088: getfield 41	org/apache/cordova/filetransfer/FileTransfer$3:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
          //   4091: new 318	org/apache/cordova/PluginResult
          //   4094: dup
          //   4095: getstatic 324	org/apache/cordova/PluginResult$Status:IO_EXCEPTION	Lorg/apache/cordova/PluginResult$Status;
          //   4098: aload 17
          //   4100: invokespecial 327	org/apache/cordova/PluginResult:<init>	(Lorg/apache/cordova/PluginResult$Status;Lorg/json/JSONObject;)V
          //   4103: invokevirtual 331	org/apache/cordova/filetransfer/FileTransfer$RequestContext:sendPluginResult	(Lorg/apache/cordova/PluginResult;)V
          //   4106: invokestatic 273	org/apache/cordova/filetransfer/FileTransfer:access$0	()Ljava/util/HashMap;
          //   4109: astore 14
          //   4111: aload 14
          //   4113: monitorenter
          //   4114: invokestatic 273	org/apache/cordova/filetransfer/FileTransfer:access$0	()Ljava/util/HashMap;
          //   4117: aload_0
          //   4118: getfield 43	org/apache/cordova/filetransfer/FileTransfer$3:val$objectId	Ljava/lang/String;
          //   4121: invokevirtual 279	java/util/HashMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
          //   4124: pop
          //   4125: aload 14
          //   4127: monitorexit
          //   4128: aload 28
          //   4130: ifnull -4120 -> 10
          //   4133: aload_0
          //   4134: getfield 45	org/apache/cordova/filetransfer/FileTransfer$3:val$trustEveryone	Z
          //   4137: ifeq -4127 -> 10
          //   4140: aload_0
          //   4141: getfield 47	org/apache/cordova/filetransfer/FileTransfer$3:val$useHttps	Z
          //   4144: ifeq -4134 -> 10
          //   4147: aload 28
          //   4149: checkcast 103	javax/net/ssl/HttpsURLConnection
          //   4152: astore 14
          //   4154: aload 14
          //   4156: aload 20
          //   4158: invokevirtual 118	javax/net/ssl/HttpsURLConnection:setHostnameVerifier	(Ljavax/net/ssl/HostnameVerifier;)V
          //   4161: aload 14
          //   4163: aload 21
          //   4165: invokevirtual 283	javax/net/ssl/HttpsURLConnection:setSSLSocketFactory	(Ljavax/net/ssl/SSLSocketFactory;)V
          //   4168: return
          //   4169: iconst_0
          //   4170: istore_1
          //   4171: goto +3019 -> 7190
          //   4174: iconst_1
          //   4175: istore_1
          //   4176: goto -1205 -> 2971
          //   4179: aload 19
          //   4181: astore 28
          //   4183: aload 17
          //   4185: astore 20
          //   4187: aload 16
          //   4189: astore 21
          //   4191: aload 19
          //   4193: astore 29
          //   4195: iload_2
          //   4196: istore_3
          //   4197: aload 17
          //   4199: astore 22
          //   4201: aload 16
          //   4203: astore 23
          //   4205: iload 5
          //   4207: istore 4
          //   4209: aload 19
          //   4211: astore 31
          //   4213: aload 17
          //   4215: astore 26
          //   4217: aload 16
          //   4219: astore 27
          //   4221: aload 19
          //   4223: astore 30
          //   4225: aload 17
          //   4227: astore 24
          //   4229: aload 16
          //   4231: astore 25
          //   4233: aload 19
          //   4235: astore 18
          //   4237: aload 17
          //   4239: astore 14
          //   4241: aload 16
          //   4243: astore 15
          //   4245: aload 19
          //   4247: iload_2
          //   4248: invokevirtual 334	java/net/HttpURLConnection:setFixedLengthStreamingMode	(I)V
          //   4251: goto -1127 -> 3124
          //   4254: astore 16
          //   4256: aload 29
          //   4258: astore 18
          //   4260: aload 22
          //   4262: astore 14
          //   4264: aload 23
          //   4266: astore 15
          //   4268: getstatic 337	org/apache/cordova/filetransfer/FileTransfer:CONNECTION_ERR	I
          //   4271: aload_0
          //   4272: getfield 71	org/apache/cordova/filetransfer/FileTransfer$3:val$source	Ljava/lang/String;
          //   4275: aload_0
          //   4276: getfield 55	org/apache/cordova/filetransfer/FileTransfer$3:val$target	Ljava/lang/String;
          //   4279: aload 29
          //   4281: invokestatic 315	org/apache/cordova/filetransfer/FileTransfer:access$6	(ILjava/lang/String;Ljava/lang/String;Ljava/net/URLConnection;)Lorg/json/JSONObject;
          //   4284: astore 17
          //   4286: aload 29
          //   4288: astore 18
          //   4290: aload 22
          //   4292: astore 14
          //   4294: aload 23
          //   4296: astore 15
          //   4298: ldc -29
          //   4300: aload 17
          //   4302: invokevirtual 316	org/json/JSONObject:toString	()Ljava/lang/String;
          //   4305: aload 16
          //   4307: invokestatic 308	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
          //   4310: pop
          //   4311: aload 29
          //   4313: astore 18
          //   4315: aload 22
          //   4317: astore 14
          //   4319: aload 23
          //   4321: astore 15
          //   4323: ldc -29
          //   4325: new 160	java/lang/StringBuilder
          //   4328: dup
          //   4329: ldc_w 339
          //   4332: invokespecial 231	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
          //   4335: iload 4
          //   4337: invokevirtual 234	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
          //   4340: ldc_w 341
          //   4343: invokevirtual 179	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   4346: iload_3
          //   4347: invokevirtual 234	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
          //   4350: ldc_w 343
          //   4353: invokevirtual 179	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   4356: invokevirtual 198	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   4359: invokestatic 345	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
          //   4362: pop
          //   4363: aload 29
          //   4365: astore 18
          //   4367: aload 22
          //   4369: astore 14
          //   4371: aload 23
          //   4373: astore 15
          //   4375: aload_0
          //   4376: getfield 41	org/apache/cordova/filetransfer/FileTransfer$3:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
          //   4379: new 318	org/apache/cordova/PluginResult
          //   4382: dup
          //   4383: getstatic 324	org/apache/cordova/PluginResult$Status:IO_EXCEPTION	Lorg/apache/cordova/PluginResult$Status;
          //   4386: aload 17
          //   4388: invokespecial 327	org/apache/cordova/PluginResult:<init>	(Lorg/apache/cordova/PluginResult$Status;Lorg/json/JSONObject;)V
          //   4391: invokevirtual 331	org/apache/cordova/filetransfer/FileTransfer$RequestContext:sendPluginResult	(Lorg/apache/cordova/PluginResult;)V
          //   4394: invokestatic 273	org/apache/cordova/filetransfer/FileTransfer:access$0	()Ljava/util/HashMap;
          //   4397: astore 14
          //   4399: aload 14
          //   4401: monitorenter
          //   4402: invokestatic 273	org/apache/cordova/filetransfer/FileTransfer:access$0	()Ljava/util/HashMap;
          //   4405: aload_0
          //   4406: getfield 43	org/apache/cordova/filetransfer/FileTransfer$3:val$objectId	Ljava/lang/String;
          //   4409: invokevirtual 279	java/util/HashMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
          //   4412: pop
          //   4413: aload 14
          //   4415: monitorexit
          //   4416: aload 29
          //   4418: ifnull -4408 -> 10
          //   4421: aload_0
          //   4422: getfield 45	org/apache/cordova/filetransfer/FileTransfer$3:val$trustEveryone	Z
          //   4425: ifeq -4415 -> 10
          //   4428: aload_0
          //   4429: getfield 47	org/apache/cordova/filetransfer/FileTransfer$3:val$useHttps	Z
          //   4432: ifeq -4422 -> 10
          //   4435: aload 29
          //   4437: checkcast 103	javax/net/ssl/HttpsURLConnection
          //   4440: astore 14
          //   4442: aload 14
          //   4444: aload 22
          //   4446: invokevirtual 118	javax/net/ssl/HttpsURLConnection:setHostnameVerifier	(Ljavax/net/ssl/HostnameVerifier;)V
          //   4449: aload 14
          //   4451: aload 23
          //   4453: invokevirtual 283	javax/net/ssl/HttpsURLConnection:setSSLSocketFactory	(Ljavax/net/ssl/SSLSocketFactory;)V
          //   4456: return
          //   4457: astore 15
          //   4459: aload 14
          //   4461: monitorexit
          //   4462: aload 15
          //   4464: athrow
          //   4465: aload_0
          //   4466: getfield 41	org/apache/cordova/filetransfer/FileTransfer$3:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
          //   4469: aload 33
          //   4471: putfield 349	org/apache/cordova/filetransfer/FileTransfer$RequestContext:currentOutputStream	Ljava/io/OutputStream;
          //   4474: aload 14
          //   4476: monitorexit
          //   4477: aload 33
          //   4479: astore 32
          //   4481: iload 6
          //   4483: istore_1
          //   4484: aload 33
          //   4486: aload 36
          //   4488: invokevirtual 355	java/io/OutputStream:write	([B)V
          //   4491: aload 33
          //   4493: astore 32
          //   4495: iload 6
          //   4497: istore_1
          //   4498: iconst_0
          //   4499: aload 36
          //   4501: arraylength
          //   4502: iadd
          //   4503: istore_3
          //   4504: aload 33
          //   4506: astore 32
          //   4508: iload_3
          //   4509: istore_1
          //   4510: aload 34
          //   4512: getfield 265	org/apache/cordova/CordovaResourceApi$OpenForReadResult:inputStream	Ljava/io/InputStream;
          //   4515: invokevirtual 361	java/io/InputStream:available	()I
          //   4518: sipush 16384
          //   4521: invokestatic 367	java/lang/Math:min	(II)I
          //   4524: istore 4
          //   4526: aload 33
          //   4528: astore 32
          //   4530: iload_3
          //   4531: istore_1
          //   4532: iload 4
          //   4534: newarray <illegal type>
          //   4536: astore 14
          //   4538: aload 33
          //   4540: astore 32
          //   4542: iload_3
          //   4543: istore_1
          //   4544: aload 34
          //   4546: getfield 265	org/apache/cordova/CordovaResourceApi$OpenForReadResult:inputStream	Ljava/io/InputStream;
          //   4549: aload 14
          //   4551: iconst_0
          //   4552: iload 4
          //   4554: invokevirtual 371	java/io/InputStream:read	([BII)I
          //   4557: istore 4
          //   4559: lconst_0
          //   4560: lstore 10
          //   4562: iload 4
          //   4564: ifgt +1209 -> 5773
          //   4567: aload 33
          //   4569: astore 32
          //   4571: iload_3
          //   4572: istore_1
          //   4573: aload 33
          //   4575: aload 35
          //   4577: invokevirtual 355	java/io/OutputStream:write	([B)V
          //   4580: aload 33
          //   4582: astore 32
          //   4584: iload_3
          //   4585: istore_1
          //   4586: iload_3
          //   4587: aload 35
          //   4589: arraylength
          //   4590: iadd
          //   4591: istore 5
          //   4593: aload 33
          //   4595: astore 32
          //   4597: iload 5
          //   4599: istore_1
          //   4600: aload 33
          //   4602: invokevirtual 374	java/io/OutputStream:flush	()V
          //   4605: aload 19
          //   4607: astore 28
          //   4609: aload 17
          //   4611: astore 20
          //   4613: aload 16
          //   4615: astore 21
          //   4617: aload 19
          //   4619: astore 29
          //   4621: iload_2
          //   4622: istore_3
          //   4623: aload 17
          //   4625: astore 22
          //   4627: aload 16
          //   4629: astore 23
          //   4631: iload 5
          //   4633: istore 4
          //   4635: aload 19
          //   4637: astore 31
          //   4639: aload 17
          //   4641: astore 26
          //   4643: aload 16
          //   4645: astore 27
          //   4647: aload 19
          //   4649: astore 30
          //   4651: aload 17
          //   4653: astore 24
          //   4655: aload 16
          //   4657: astore 25
          //   4659: aload 19
          //   4661: astore 18
          //   4663: aload 17
          //   4665: astore 14
          //   4667: aload 16
          //   4669: astore 15
          //   4671: aload 34
          //   4673: getfield 265	org/apache/cordova/CordovaResourceApi$OpenForReadResult:inputStream	Ljava/io/InputStream;
          //   4676: invokestatic 269	org/apache/cordova/filetransfer/FileTransfer:access$4	(Ljava/io/Closeable;)V
          //   4679: aload 19
          //   4681: astore 28
          //   4683: aload 17
          //   4685: astore 20
          //   4687: aload 16
          //   4689: astore 21
          //   4691: aload 19
          //   4693: astore 29
          //   4695: iload_2
          //   4696: istore_3
          //   4697: aload 17
          //   4699: astore 22
          //   4701: aload 16
          //   4703: astore 23
          //   4705: iload 5
          //   4707: istore 4
          //   4709: aload 19
          //   4711: astore 31
          //   4713: aload 17
          //   4715: astore 26
          //   4717: aload 16
          //   4719: astore 27
          //   4721: aload 19
          //   4723: astore 30
          //   4725: aload 17
          //   4727: astore 24
          //   4729: aload 16
          //   4731: astore 25
          //   4733: aload 19
          //   4735: astore 18
          //   4737: aload 17
          //   4739: astore 14
          //   4741: aload 16
          //   4743: astore 15
          //   4745: aload 33
          //   4747: invokestatic 269	org/apache/cordova/filetransfer/FileTransfer:access$4	(Ljava/io/Closeable;)V
          //   4750: aload 19
          //   4752: astore 28
          //   4754: aload 17
          //   4756: astore 20
          //   4758: aload 16
          //   4760: astore 21
          //   4762: aload 19
          //   4764: astore 29
          //   4766: iload_2
          //   4767: istore_3
          //   4768: aload 17
          //   4770: astore 22
          //   4772: aload 16
          //   4774: astore 23
          //   4776: iload 5
          //   4778: istore 4
          //   4780: aload 19
          //   4782: astore 31
          //   4784: aload 17
          //   4786: astore 26
          //   4788: aload 16
          //   4790: astore 27
          //   4792: aload 19
          //   4794: astore 30
          //   4796: aload 17
          //   4798: astore 24
          //   4800: aload 16
          //   4802: astore 25
          //   4804: aload 19
          //   4806: astore 18
          //   4808: aload 17
          //   4810: astore 14
          //   4812: aload 16
          //   4814: astore 15
          //   4816: aload_0
          //   4817: getfield 41	org/apache/cordova/filetransfer/FileTransfer$3:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
          //   4820: aconst_null
          //   4821: putfield 349	org/apache/cordova/filetransfer/FileTransfer$RequestContext:currentOutputStream	Ljava/io/OutputStream;
          //   4824: aload 19
          //   4826: astore 28
          //   4828: aload 17
          //   4830: astore 20
          //   4832: aload 16
          //   4834: astore 21
          //   4836: aload 19
          //   4838: astore 29
          //   4840: iload_2
          //   4841: istore_3
          //   4842: aload 17
          //   4844: astore 22
          //   4846: aload 16
          //   4848: astore 23
          //   4850: iload 5
          //   4852: istore 4
          //   4854: aload 19
          //   4856: astore 31
          //   4858: aload 17
          //   4860: astore 26
          //   4862: aload 16
          //   4864: astore 27
          //   4866: aload 19
          //   4868: astore 30
          //   4870: aload 17
          //   4872: astore 24
          //   4874: aload 16
          //   4876: astore 25
          //   4878: aload 19
          //   4880: astore 18
          //   4882: aload 17
          //   4884: astore 14
          //   4886: aload 16
          //   4888: astore 15
          //   4890: ldc -29
          //   4892: new 160	java/lang/StringBuilder
          //   4895: dup
          //   4896: ldc_w 376
          //   4899: invokespecial 231	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
          //   4902: iload 5
          //   4904: invokevirtual 234	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
          //   4907: ldc_w 341
          //   4910: invokevirtual 179	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   4913: iload_2
          //   4914: invokevirtual 234	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
          //   4917: invokevirtual 198	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   4920: invokestatic 240	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
          //   4923: pop
          //   4924: aload 19
          //   4926: astore 28
          //   4928: aload 17
          //   4930: astore 20
          //   4932: aload 16
          //   4934: astore 21
          //   4936: aload 19
          //   4938: astore 29
          //   4940: iload_2
          //   4941: istore_3
          //   4942: aload 17
          //   4944: astore 22
          //   4946: aload 16
          //   4948: astore 23
          //   4950: iload 5
          //   4952: istore 4
          //   4954: aload 19
          //   4956: astore 31
          //   4958: aload 17
          //   4960: astore 26
          //   4962: aload 16
          //   4964: astore 27
          //   4966: aload 19
          //   4968: astore 30
          //   4970: aload 17
          //   4972: astore 24
          //   4974: aload 16
          //   4976: astore 25
          //   4978: aload 19
          //   4980: astore 18
          //   4982: aload 17
          //   4984: astore 14
          //   4986: aload 16
          //   4988: astore 15
          //   4990: aload 19
          //   4992: invokevirtual 379	java/net/HttpURLConnection:getResponseCode	()I
          //   4995: istore_1
          //   4996: aload 19
          //   4998: astore 28
          //   5000: aload 17
          //   5002: astore 20
          //   5004: aload 16
          //   5006: astore 21
          //   5008: aload 19
          //   5010: astore 29
          //   5012: iload_2
          //   5013: istore_3
          //   5014: aload 17
          //   5016: astore 22
          //   5018: aload 16
          //   5020: astore 23
          //   5022: iload 5
          //   5024: istore 4
          //   5026: aload 19
          //   5028: astore 31
          //   5030: aload 17
          //   5032: astore 26
          //   5034: aload 16
          //   5036: astore 27
          //   5038: aload 19
          //   5040: astore 30
          //   5042: aload 17
          //   5044: astore 24
          //   5046: aload 16
          //   5048: astore 25
          //   5050: aload 19
          //   5052: astore 18
          //   5054: aload 17
          //   5056: astore 14
          //   5058: aload 16
          //   5060: astore 15
          //   5062: ldc -29
          //   5064: new 160	java/lang/StringBuilder
          //   5067: dup
          //   5068: ldc_w 381
          //   5071: invokespecial 231	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
          //   5074: iload_1
          //   5075: invokevirtual 234	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
          //   5078: invokevirtual 198	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   5081: invokestatic 240	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
          //   5084: pop
          //   5085: aload 19
          //   5087: astore 28
          //   5089: aload 17
          //   5091: astore 20
          //   5093: aload 16
          //   5095: astore 21
          //   5097: aload 19
          //   5099: astore 29
          //   5101: iload_2
          //   5102: istore_3
          //   5103: aload 17
          //   5105: astore 22
          //   5107: aload 16
          //   5109: astore 23
          //   5111: iload 5
          //   5113: istore 4
          //   5115: aload 19
          //   5117: astore 31
          //   5119: aload 17
          //   5121: astore 26
          //   5123: aload 16
          //   5125: astore 27
          //   5127: aload 19
          //   5129: astore 30
          //   5131: aload 17
          //   5133: astore 24
          //   5135: aload 16
          //   5137: astore 25
          //   5139: aload 19
          //   5141: astore 18
          //   5143: aload 17
          //   5145: astore 14
          //   5147: aload 16
          //   5149: astore 15
          //   5151: ldc -29
          //   5153: new 160	java/lang/StringBuilder
          //   5156: dup
          //   5157: ldc_w 383
          //   5160: invokespecial 231	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
          //   5163: aload 19
          //   5165: invokevirtual 387	java/net/HttpURLConnection:getHeaderFields	()Ljava/util/Map;
          //   5168: invokevirtual 390	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
          //   5171: invokevirtual 198	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   5174: invokestatic 240	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
          //   5177: pop
          //   5178: aconst_null
          //   5179: astore 32
          //   5181: aload 19
          //   5183: invokestatic 394	org/apache/cordova/filetransfer/FileTransfer:access$5	(Ljava/net/URLConnection;)Lorg/apache/cordova/filetransfer/FileTransfer$TrackingInputStream;
          //   5186: astore 33
          //   5188: aload 33
          //   5190: astore 32
          //   5192: aload_0
          //   5193: getfield 41	org/apache/cordova/filetransfer/FileTransfer$3:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
          //   5196: astore 14
          //   5198: aload 33
          //   5200: astore 32
          //   5202: aload 14
          //   5204: monitorenter
          //   5205: aload_0
          //   5206: getfield 41	org/apache/cordova/filetransfer/FileTransfer$3:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
          //   5209: getfield 89	org/apache/cordova/filetransfer/FileTransfer$RequestContext:aborted	Z
          //   5212: ifeq +785 -> 5997
          //   5215: aload 14
          //   5217: monitorexit
          //   5218: aload 19
          //   5220: astore 28
          //   5222: aload 17
          //   5224: astore 20
          //   5226: aload 16
          //   5228: astore 21
          //   5230: aload 19
          //   5232: astore 29
          //   5234: iload_2
          //   5235: istore_3
          //   5236: aload 17
          //   5238: astore 22
          //   5240: aload 16
          //   5242: astore 23
          //   5244: iload 5
          //   5246: istore 4
          //   5248: aload 19
          //   5250: astore 31
          //   5252: aload 17
          //   5254: astore 26
          //   5256: aload 16
          //   5258: astore 27
          //   5260: aload 19
          //   5262: astore 30
          //   5264: aload 17
          //   5266: astore 24
          //   5268: aload 16
          //   5270: astore 25
          //   5272: aload 19
          //   5274: astore 18
          //   5276: aload 17
          //   5278: astore 14
          //   5280: aload 16
          //   5282: astore 15
          //   5284: aload_0
          //   5285: getfield 41	org/apache/cordova/filetransfer/FileTransfer$3:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
          //   5288: aconst_null
          //   5289: putfield 397	org/apache/cordova/filetransfer/FileTransfer$RequestContext:currentInputStream	Ljava/io/InputStream;
          //   5292: aload 19
          //   5294: astore 28
          //   5296: aload 17
          //   5298: astore 20
          //   5300: aload 16
          //   5302: astore 21
          //   5304: aload 19
          //   5306: astore 29
          //   5308: iload_2
          //   5309: istore_3
          //   5310: aload 17
          //   5312: astore 22
          //   5314: aload 16
          //   5316: astore 23
          //   5318: iload 5
          //   5320: istore 4
          //   5322: aload 19
          //   5324: astore 31
          //   5326: aload 17
          //   5328: astore 26
          //   5330: aload 16
          //   5332: astore 27
          //   5334: aload 19
          //   5336: astore 30
          //   5338: aload 17
          //   5340: astore 24
          //   5342: aload 16
          //   5344: astore 25
          //   5346: aload 19
          //   5348: astore 18
          //   5350: aload 17
          //   5352: astore 14
          //   5354: aload 16
          //   5356: astore 15
          //   5358: aload 33
          //   5360: invokestatic 269	org/apache/cordova/filetransfer/FileTransfer:access$4	(Ljava/io/Closeable;)V
          //   5363: invokestatic 273	org/apache/cordova/filetransfer/FileTransfer:access$0	()Ljava/util/HashMap;
          //   5366: astore 14
          //   5368: aload 14
          //   5370: monitorenter
          //   5371: invokestatic 273	org/apache/cordova/filetransfer/FileTransfer:access$0	()Ljava/util/HashMap;
          //   5374: aload_0
          //   5375: getfield 43	org/apache/cordova/filetransfer/FileTransfer$3:val$objectId	Ljava/lang/String;
          //   5378: invokevirtual 279	java/util/HashMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
          //   5381: pop
          //   5382: aload 14
          //   5384: monitorexit
          //   5385: aload 19
          //   5387: ifnull -5377 -> 10
          //   5390: aload_0
          //   5391: getfield 45	org/apache/cordova/filetransfer/FileTransfer$3:val$trustEveryone	Z
          //   5394: ifeq -5384 -> 10
          //   5397: aload_0
          //   5398: getfield 47	org/apache/cordova/filetransfer/FileTransfer$3:val$useHttps	Z
          //   5401: ifeq -5391 -> 10
          //   5404: aload 19
          //   5406: checkcast 103	javax/net/ssl/HttpsURLConnection
          //   5409: astore 14
          //   5411: aload 14
          //   5413: aload 17
          //   5415: invokevirtual 118	javax/net/ssl/HttpsURLConnection:setHostnameVerifier	(Ljavax/net/ssl/HostnameVerifier;)V
          //   5418: aload 14
          //   5420: aload 16
          //   5422: invokevirtual 283	javax/net/ssl/HttpsURLConnection:setSSLSocketFactory	(Ljavax/net/ssl/SSLSocketFactory;)V
          //   5425: return
          //   5426: astore 15
          //   5428: aload 14
          //   5430: monitorexit
          //   5431: aload 33
          //   5433: astore 32
          //   5435: iload 6
          //   5437: istore_1
          //   5438: aload 15
          //   5440: athrow
          //   5441: astore 33
          //   5443: aload 19
          //   5445: astore 28
          //   5447: aload 17
          //   5449: astore 20
          //   5451: aload 16
          //   5453: astore 21
          //   5455: aload 19
          //   5457: astore 29
          //   5459: iload_2
          //   5460: istore_3
          //   5461: aload 17
          //   5463: astore 22
          //   5465: aload 16
          //   5467: astore 23
          //   5469: iload_1
          //   5470: istore 4
          //   5472: aload 19
          //   5474: astore 31
          //   5476: aload 17
          //   5478: astore 26
          //   5480: aload 16
          //   5482: astore 27
          //   5484: aload 19
          //   5486: astore 30
          //   5488: aload 17
          //   5490: astore 24
          //   5492: aload 16
          //   5494: astore 25
          //   5496: aload 19
          //   5498: astore 18
          //   5500: aload 17
          //   5502: astore 14
          //   5504: aload 16
          //   5506: astore 15
          //   5508: aload 34
          //   5510: getfield 265	org/apache/cordova/CordovaResourceApi$OpenForReadResult:inputStream	Ljava/io/InputStream;
          //   5513: invokestatic 269	org/apache/cordova/filetransfer/FileTransfer:access$4	(Ljava/io/Closeable;)V
          //   5516: aload 19
          //   5518: astore 28
          //   5520: aload 17
          //   5522: astore 20
          //   5524: aload 16
          //   5526: astore 21
          //   5528: aload 19
          //   5530: astore 29
          //   5532: iload_2
          //   5533: istore_3
          //   5534: aload 17
          //   5536: astore 22
          //   5538: aload 16
          //   5540: astore 23
          //   5542: iload_1
          //   5543: istore 4
          //   5545: aload 19
          //   5547: astore 31
          //   5549: aload 17
          //   5551: astore 26
          //   5553: aload 16
          //   5555: astore 27
          //   5557: aload 19
          //   5559: astore 30
          //   5561: aload 17
          //   5563: astore 24
          //   5565: aload 16
          //   5567: astore 25
          //   5569: aload 19
          //   5571: astore 18
          //   5573: aload 17
          //   5575: astore 14
          //   5577: aload 16
          //   5579: astore 15
          //   5581: aload 32
          //   5583: invokestatic 269	org/apache/cordova/filetransfer/FileTransfer:access$4	(Ljava/io/Closeable;)V
          //   5586: aload 19
          //   5588: astore 28
          //   5590: aload 17
          //   5592: astore 20
          //   5594: aload 16
          //   5596: astore 21
          //   5598: aload 19
          //   5600: astore 29
          //   5602: iload_2
          //   5603: istore_3
          //   5604: aload 17
          //   5606: astore 22
          //   5608: aload 16
          //   5610: astore 23
          //   5612: iload_1
          //   5613: istore 4
          //   5615: aload 19
          //   5617: astore 31
          //   5619: aload 17
          //   5621: astore 26
          //   5623: aload 16
          //   5625: astore 27
          //   5627: aload 19
          //   5629: astore 30
          //   5631: aload 17
          //   5633: astore 24
          //   5635: aload 16
          //   5637: astore 25
          //   5639: aload 19
          //   5641: astore 18
          //   5643: aload 17
          //   5645: astore 14
          //   5647: aload 16
          //   5649: astore 15
          //   5651: aload 33
          //   5653: athrow
          //   5654: astore 16
          //   5656: aload 31
          //   5658: astore 18
          //   5660: aload 26
          //   5662: astore 14
          //   5664: aload 27
          //   5666: astore 15
          //   5668: ldc -29
          //   5670: aload 16
          //   5672: invokevirtual 304	org/json/JSONException:getMessage	()Ljava/lang/String;
          //   5675: aload 16
          //   5677: invokestatic 308	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
          //   5680: pop
          //   5681: aload 31
          //   5683: astore 18
          //   5685: aload 26
          //   5687: astore 14
          //   5689: aload 27
          //   5691: astore 15
          //   5693: aload_0
          //   5694: getfield 41	org/apache/cordova/filetransfer/FileTransfer$3:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
          //   5697: new 318	org/apache/cordova/PluginResult
          //   5700: dup
          //   5701: getstatic 400	org/apache/cordova/PluginResult$Status:JSON_EXCEPTION	Lorg/apache/cordova/PluginResult$Status;
          //   5704: invokespecial 403	org/apache/cordova/PluginResult:<init>	(Lorg/apache/cordova/PluginResult$Status;)V
          //   5707: invokevirtual 331	org/apache/cordova/filetransfer/FileTransfer$RequestContext:sendPluginResult	(Lorg/apache/cordova/PluginResult;)V
          //   5710: invokestatic 273	org/apache/cordova/filetransfer/FileTransfer:access$0	()Ljava/util/HashMap;
          //   5713: astore 14
          //   5715: aload 14
          //   5717: monitorenter
          //   5718: invokestatic 273	org/apache/cordova/filetransfer/FileTransfer:access$0	()Ljava/util/HashMap;
          //   5721: aload_0
          //   5722: getfield 43	org/apache/cordova/filetransfer/FileTransfer$3:val$objectId	Ljava/lang/String;
          //   5725: invokevirtual 279	java/util/HashMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
          //   5728: pop
          //   5729: aload 14
          //   5731: monitorexit
          //   5732: aload 31
          //   5734: ifnull -5724 -> 10
          //   5737: aload_0
          //   5738: getfield 45	org/apache/cordova/filetransfer/FileTransfer$3:val$trustEveryone	Z
          //   5741: ifeq -5731 -> 10
          //   5744: aload_0
          //   5745: getfield 47	org/apache/cordova/filetransfer/FileTransfer$3:val$useHttps	Z
          //   5748: ifeq -5738 -> 10
          //   5751: aload 31
          //   5753: checkcast 103	javax/net/ssl/HttpsURLConnection
          //   5756: astore 14
          //   5758: aload 14
          //   5760: aload 26
          //   5762: invokevirtual 118	javax/net/ssl/HttpsURLConnection:setHostnameVerifier	(Ljavax/net/ssl/HostnameVerifier;)V
          //   5765: aload 14
          //   5767: aload 27
          //   5769: invokevirtual 283	javax/net/ssl/HttpsURLConnection:setSSLSocketFactory	(Ljavax/net/ssl/SSLSocketFactory;)V
          //   5772: return
          //   5773: iload_3
          //   5774: i2l
          //   5775: lstore 12
          //   5777: aload 33
          //   5779: astore 32
          //   5781: iload_3
          //   5782: istore_1
          //   5783: aload 46
          //   5785: lload 12
          //   5787: invokevirtual 406	org/apache/cordova/filetransfer/FileUploadResult:setBytesSent	(J)V
          //   5790: aload 33
          //   5792: astore 32
          //   5794: iload_3
          //   5795: istore_1
          //   5796: aload 33
          //   5798: aload 14
          //   5800: iconst_0
          //   5801: iload 4
          //   5803: invokevirtual 409	java/io/OutputStream:write	([BII)V
          //   5806: iload_3
          //   5807: iload 4
          //   5809: iadd
          //   5810: istore_3
          //   5811: lload 10
          //   5813: lstore 12
          //   5815: iload_3
          //   5816: i2l
          //   5817: ldc2_w 410
          //   5820: lload 10
          //   5822: ladd
          //   5823: lcmp
          //   5824: ifle +52 -> 5876
          //   5827: iload_3
          //   5828: i2l
          //   5829: lstore 12
          //   5831: aload 33
          //   5833: astore 32
          //   5835: iload_3
          //   5836: istore_1
          //   5837: ldc -29
          //   5839: new 160	java/lang/StringBuilder
          //   5842: dup
          //   5843: ldc_w 413
          //   5846: invokespecial 231	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
          //   5849: iload_3
          //   5850: invokevirtual 234	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
          //   5853: ldc_w 341
          //   5856: invokevirtual 179	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   5859: iload_2
          //   5860: invokevirtual 234	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
          //   5863: ldc_w 415
          //   5866: invokevirtual 179	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   5869: invokevirtual 198	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   5872: invokestatic 240	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
          //   5875: pop
          //   5876: aload 33
          //   5878: astore 32
          //   5880: iload_3
          //   5881: istore_1
          //   5882: aload 34
          //   5884: getfield 265	org/apache/cordova/CordovaResourceApi$OpenForReadResult:inputStream	Ljava/io/InputStream;
          //   5887: invokevirtual 361	java/io/InputStream:available	()I
          //   5890: sipush 16384
          //   5893: invokestatic 367	java/lang/Math:min	(II)I
          //   5896: istore 4
          //   5898: aload 33
          //   5900: astore 32
          //   5902: iload_3
          //   5903: istore_1
          //   5904: aload 34
          //   5906: getfield 265	org/apache/cordova/CordovaResourceApi$OpenForReadResult:inputStream	Ljava/io/InputStream;
          //   5909: aload 14
          //   5911: iconst_0
          //   5912: iload 4
          //   5914: invokevirtual 371	java/io/InputStream:read	([BII)I
          //   5917: istore 4
          //   5919: aload 33
          //   5921: astore 32
          //   5923: iload_3
          //   5924: istore_1
          //   5925: aload 47
          //   5927: iload_3
          //   5928: i2l
          //   5929: invokevirtual 418	org/apache/cordova/filetransfer/FileProgressResult:setLoaded	(J)V
          //   5932: aload 33
          //   5934: astore 32
          //   5936: iload_3
          //   5937: istore_1
          //   5938: new 318	org/apache/cordova/PluginResult
          //   5941: dup
          //   5942: getstatic 421	org/apache/cordova/PluginResult$Status:OK	Lorg/apache/cordova/PluginResult$Status;
          //   5945: aload 47
          //   5947: invokevirtual 425	org/apache/cordova/filetransfer/FileProgressResult:toJSONObject	()Lorg/json/JSONObject;
          //   5950: invokespecial 327	org/apache/cordova/PluginResult:<init>	(Lorg/apache/cordova/PluginResult$Status;Lorg/json/JSONObject;)V
          //   5953: astore 15
          //   5955: aload 33
          //   5957: astore 32
          //   5959: iload_3
          //   5960: istore_1
          //   5961: aload 15
          //   5963: iconst_1
          //   5964: invokevirtual 428	org/apache/cordova/PluginResult:setKeepCallback	(Z)V
          //   5967: aload 33
          //   5969: astore 32
          //   5971: iload_3
          //   5972: istore_1
          //   5973: aload_0
          //   5974: getfield 41	org/apache/cordova/filetransfer/FileTransfer$3:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
          //   5977: aload 15
          //   5979: invokevirtual 331	org/apache/cordova/filetransfer/FileTransfer$RequestContext:sendPluginResult	(Lorg/apache/cordova/PluginResult;)V
          //   5982: lload 12
          //   5984: lstore 10
          //   5986: goto -1424 -> 4562
          //   5989: astore 15
          //   5991: aload 14
          //   5993: monitorexit
          //   5994: aload 15
          //   5996: athrow
          //   5997: aload_0
          //   5998: getfield 41	org/apache/cordova/filetransfer/FileTransfer$3:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
          //   6001: aload 33
          //   6003: putfield 397	org/apache/cordova/filetransfer/FileTransfer$RequestContext:currentInputStream	Ljava/io/InputStream;
          //   6006: aload 14
          //   6008: monitorexit
          //   6009: aload 33
          //   6011: astore 32
          //   6013: new 430	java/io/ByteArrayOutputStream
          //   6016: dup
          //   6017: sipush 1024
          //   6020: aload 19
          //   6022: invokevirtual 433	java/net/HttpURLConnection:getContentLength	()I
          //   6025: invokestatic 436	java/lang/Math:max	(II)I
          //   6028: invokespecial 438	java/io/ByteArrayOutputStream:<init>	(I)V
          //   6031: astore 14
          //   6033: aload 33
          //   6035: astore 32
          //   6037: sipush 1024
          //   6040: newarray <illegal type>
          //   6042: astore 15
          //   6044: aload 33
          //   6046: astore 32
          //   6048: aload 33
          //   6050: aload 15
          //   6052: invokevirtual 443	org/apache/cordova/filetransfer/FileTransfer$TrackingInputStream:read	([B)I
          //   6055: istore_3
          //   6056: iload_3
          //   6057: ifgt +1000 -> 7057
          //   6060: aload 33
          //   6062: astore 32
          //   6064: aload 14
          //   6066: ldc -56
          //   6068: invokevirtual 445	java/io/ByteArrayOutputStream:toString	(Ljava/lang/String;)Ljava/lang/String;
          //   6071: astore 34
          //   6073: aload 19
          //   6075: astore 28
          //   6077: aload 17
          //   6079: astore 20
          //   6081: aload 16
          //   6083: astore 21
          //   6085: aload 19
          //   6087: astore 29
          //   6089: iload_2
          //   6090: istore_3
          //   6091: aload 17
          //   6093: astore 22
          //   6095: aload 16
          //   6097: astore 23
          //   6099: iload 5
          //   6101: istore 4
          //   6103: aload 19
          //   6105: astore 31
          //   6107: aload 17
          //   6109: astore 26
          //   6111: aload 16
          //   6113: astore 27
          //   6115: aload 19
          //   6117: astore 30
          //   6119: aload 17
          //   6121: astore 24
          //   6123: aload 16
          //   6125: astore 25
          //   6127: aload 19
          //   6129: astore 18
          //   6131: aload 17
          //   6133: astore 14
          //   6135: aload 16
          //   6137: astore 15
          //   6139: aload_0
          //   6140: getfield 41	org/apache/cordova/filetransfer/FileTransfer$3:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
          //   6143: aconst_null
          //   6144: putfield 397	org/apache/cordova/filetransfer/FileTransfer$RequestContext:currentInputStream	Ljava/io/InputStream;
          //   6147: aload 19
          //   6149: astore 28
          //   6151: aload 17
          //   6153: astore 20
          //   6155: aload 16
          //   6157: astore 21
          //   6159: aload 19
          //   6161: astore 29
          //   6163: iload_2
          //   6164: istore_3
          //   6165: aload 17
          //   6167: astore 22
          //   6169: aload 16
          //   6171: astore 23
          //   6173: iload 5
          //   6175: istore 4
          //   6177: aload 19
          //   6179: astore 31
          //   6181: aload 17
          //   6183: astore 26
          //   6185: aload 16
          //   6187: astore 27
          //   6189: aload 19
          //   6191: astore 30
          //   6193: aload 17
          //   6195: astore 24
          //   6197: aload 16
          //   6199: astore 25
          //   6201: aload 19
          //   6203: astore 18
          //   6205: aload 17
          //   6207: astore 14
          //   6209: aload 16
          //   6211: astore 15
          //   6213: aload 33
          //   6215: invokestatic 269	org/apache/cordova/filetransfer/FileTransfer:access$4	(Ljava/io/Closeable;)V
          //   6218: aload 19
          //   6220: astore 28
          //   6222: aload 17
          //   6224: astore 20
          //   6226: aload 16
          //   6228: astore 21
          //   6230: aload 19
          //   6232: astore 29
          //   6234: iload_2
          //   6235: istore_3
          //   6236: aload 17
          //   6238: astore 22
          //   6240: aload 16
          //   6242: astore 23
          //   6244: iload 5
          //   6246: istore 4
          //   6248: aload 19
          //   6250: astore 31
          //   6252: aload 17
          //   6254: astore 26
          //   6256: aload 16
          //   6258: astore 27
          //   6260: aload 19
          //   6262: astore 30
          //   6264: aload 17
          //   6266: astore 24
          //   6268: aload 16
          //   6270: astore 25
          //   6272: aload 19
          //   6274: astore 18
          //   6276: aload 17
          //   6278: astore 14
          //   6280: aload 16
          //   6282: astore 15
          //   6284: ldc -29
          //   6286: ldc_w 447
          //   6289: invokestatic 240	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
          //   6292: pop
          //   6293: aload 19
          //   6295: astore 28
          //   6297: aload 17
          //   6299: astore 20
          //   6301: aload 16
          //   6303: astore 21
          //   6305: aload 19
          //   6307: astore 29
          //   6309: iload_2
          //   6310: istore_3
          //   6311: aload 17
          //   6313: astore 22
          //   6315: aload 16
          //   6317: astore 23
          //   6319: iload 5
          //   6321: istore 4
          //   6323: aload 19
          //   6325: astore 31
          //   6327: aload 17
          //   6329: astore 26
          //   6331: aload 16
          //   6333: astore 27
          //   6335: aload 19
          //   6337: astore 30
          //   6339: aload 17
          //   6341: astore 24
          //   6343: aload 16
          //   6345: astore 25
          //   6347: aload 19
          //   6349: astore 18
          //   6351: aload 17
          //   6353: astore 14
          //   6355: aload 16
          //   6357: astore 15
          //   6359: ldc -29
          //   6361: aload 34
          //   6363: iconst_0
          //   6364: sipush 256
          //   6367: aload 34
          //   6369: invokevirtual 449	java/lang/String:length	()I
          //   6372: invokestatic 367	java/lang/Math:min	(II)I
          //   6375: invokevirtual 453	java/lang/String:substring	(II)Ljava/lang/String;
          //   6378: invokestatic 240	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
          //   6381: pop
          //   6382: aload 19
          //   6384: astore 28
          //   6386: aload 17
          //   6388: astore 20
          //   6390: aload 16
          //   6392: astore 21
          //   6394: aload 19
          //   6396: astore 29
          //   6398: iload_2
          //   6399: istore_3
          //   6400: aload 17
          //   6402: astore 22
          //   6404: aload 16
          //   6406: astore 23
          //   6408: iload 5
          //   6410: istore 4
          //   6412: aload 19
          //   6414: astore 31
          //   6416: aload 17
          //   6418: astore 26
          //   6420: aload 16
          //   6422: astore 27
          //   6424: aload 19
          //   6426: astore 30
          //   6428: aload 17
          //   6430: astore 24
          //   6432: aload 16
          //   6434: astore 25
          //   6436: aload 19
          //   6438: astore 18
          //   6440: aload 17
          //   6442: astore 14
          //   6444: aload 16
          //   6446: astore 15
          //   6448: aload 46
          //   6450: iload_1
          //   6451: invokevirtual 456	org/apache/cordova/filetransfer/FileUploadResult:setResponseCode	(I)V
          //   6454: aload 19
          //   6456: astore 28
          //   6458: aload 17
          //   6460: astore 20
          //   6462: aload 16
          //   6464: astore 21
          //   6466: aload 19
          //   6468: astore 29
          //   6470: iload_2
          //   6471: istore_3
          //   6472: aload 17
          //   6474: astore 22
          //   6476: aload 16
          //   6478: astore 23
          //   6480: iload 5
          //   6482: istore 4
          //   6484: aload 19
          //   6486: astore 31
          //   6488: aload 17
          //   6490: astore 26
          //   6492: aload 16
          //   6494: astore 27
          //   6496: aload 19
          //   6498: astore 30
          //   6500: aload 17
          //   6502: astore 24
          //   6504: aload 16
          //   6506: astore 25
          //   6508: aload 19
          //   6510: astore 18
          //   6512: aload 17
          //   6514: astore 14
          //   6516: aload 16
          //   6518: astore 15
          //   6520: aload 46
          //   6522: aload 34
          //   6524: invokevirtual 459	org/apache/cordova/filetransfer/FileUploadResult:setResponse	(Ljava/lang/String;)V
          //   6527: aload 19
          //   6529: astore 28
          //   6531: aload 17
          //   6533: astore 20
          //   6535: aload 16
          //   6537: astore 21
          //   6539: aload 19
          //   6541: astore 29
          //   6543: iload_2
          //   6544: istore_3
          //   6545: aload 17
          //   6547: astore 22
          //   6549: aload 16
          //   6551: astore 23
          //   6553: iload 5
          //   6555: istore 4
          //   6557: aload 19
          //   6559: astore 31
          //   6561: aload 17
          //   6563: astore 26
          //   6565: aload 16
          //   6567: astore 27
          //   6569: aload 19
          //   6571: astore 30
          //   6573: aload 17
          //   6575: astore 24
          //   6577: aload 16
          //   6579: astore 25
          //   6581: aload 19
          //   6583: astore 18
          //   6585: aload 17
          //   6587: astore 14
          //   6589: aload 16
          //   6591: astore 15
          //   6593: aload_0
          //   6594: getfield 41	org/apache/cordova/filetransfer/FileTransfer$3:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
          //   6597: new 318	org/apache/cordova/PluginResult
          //   6600: dup
          //   6601: getstatic 421	org/apache/cordova/PluginResult$Status:OK	Lorg/apache/cordova/PluginResult$Status;
          //   6604: aload 46
          //   6606: invokevirtual 460	org/apache/cordova/filetransfer/FileUploadResult:toJSONObject	()Lorg/json/JSONObject;
          //   6609: invokespecial 327	org/apache/cordova/PluginResult:<init>	(Lorg/apache/cordova/PluginResult$Status;Lorg/json/JSONObject;)V
          //   6612: invokevirtual 331	org/apache/cordova/filetransfer/FileTransfer$RequestContext:sendPluginResult	(Lorg/apache/cordova/PluginResult;)V
          //   6615: invokestatic 273	org/apache/cordova/filetransfer/FileTransfer:access$0	()Ljava/util/HashMap;
          //   6618: astore 14
          //   6620: aload 14
          //   6622: monitorenter
          //   6623: invokestatic 273	org/apache/cordova/filetransfer/FileTransfer:access$0	()Ljava/util/HashMap;
          //   6626: aload_0
          //   6627: getfield 43	org/apache/cordova/filetransfer/FileTransfer$3:val$objectId	Ljava/lang/String;
          //   6630: invokevirtual 279	java/util/HashMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
          //   6633: pop
          //   6634: aload 14
          //   6636: monitorexit
          //   6637: aload 19
          //   6639: ifnull -6629 -> 10
          //   6642: aload_0
          //   6643: getfield 45	org/apache/cordova/filetransfer/FileTransfer$3:val$trustEveryone	Z
          //   6646: ifeq -6636 -> 10
          //   6649: aload_0
          //   6650: getfield 47	org/apache/cordova/filetransfer/FileTransfer$3:val$useHttps	Z
          //   6653: ifeq -6643 -> 10
          //   6656: aload 19
          //   6658: checkcast 103	javax/net/ssl/HttpsURLConnection
          //   6661: astore 14
          //   6663: aload 14
          //   6665: aload 17
          //   6667: invokevirtual 118	javax/net/ssl/HttpsURLConnection:setHostnameVerifier	(Ljavax/net/ssl/HostnameVerifier;)V
          //   6670: aload 14
          //   6672: aload 16
          //   6674: invokevirtual 283	javax/net/ssl/HttpsURLConnection:setSSLSocketFactory	(Ljavax/net/ssl/SSLSocketFactory;)V
          //   6677: return
          //   6678: astore 15
          //   6680: aload 14
          //   6682: monitorexit
          //   6683: aload 33
          //   6685: astore 32
          //   6687: aload 15
          //   6689: athrow
          //   6690: astore 33
          //   6692: aload 19
          //   6694: astore 28
          //   6696: aload 17
          //   6698: astore 20
          //   6700: aload 16
          //   6702: astore 21
          //   6704: aload 19
          //   6706: astore 29
          //   6708: iload_2
          //   6709: istore_3
          //   6710: aload 17
          //   6712: astore 22
          //   6714: aload 16
          //   6716: astore 23
          //   6718: iload 5
          //   6720: istore 4
          //   6722: aload 19
          //   6724: astore 31
          //   6726: aload 17
          //   6728: astore 26
          //   6730: aload 16
          //   6732: astore 27
          //   6734: aload 19
          //   6736: astore 30
          //   6738: aload 17
          //   6740: astore 24
          //   6742: aload 16
          //   6744: astore 25
          //   6746: aload 19
          //   6748: astore 18
          //   6750: aload 17
          //   6752: astore 14
          //   6754: aload 16
          //   6756: astore 15
          //   6758: aload_0
          //   6759: getfield 41	org/apache/cordova/filetransfer/FileTransfer$3:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
          //   6762: aconst_null
          //   6763: putfield 397	org/apache/cordova/filetransfer/FileTransfer$RequestContext:currentInputStream	Ljava/io/InputStream;
          //   6766: aload 19
          //   6768: astore 28
          //   6770: aload 17
          //   6772: astore 20
          //   6774: aload 16
          //   6776: astore 21
          //   6778: aload 19
          //   6780: astore 29
          //   6782: iload_2
          //   6783: istore_3
          //   6784: aload 17
          //   6786: astore 22
          //   6788: aload 16
          //   6790: astore 23
          //   6792: iload 5
          //   6794: istore 4
          //   6796: aload 19
          //   6798: astore 31
          //   6800: aload 17
          //   6802: astore 26
          //   6804: aload 16
          //   6806: astore 27
          //   6808: aload 19
          //   6810: astore 30
          //   6812: aload 17
          //   6814: astore 24
          //   6816: aload 16
          //   6818: astore 25
          //   6820: aload 19
          //   6822: astore 18
          //   6824: aload 17
          //   6826: astore 14
          //   6828: aload 16
          //   6830: astore 15
          //   6832: aload 32
          //   6834: invokestatic 269	org/apache/cordova/filetransfer/FileTransfer:access$4	(Ljava/io/Closeable;)V
          //   6837: aload 19
          //   6839: astore 28
          //   6841: aload 17
          //   6843: astore 20
          //   6845: aload 16
          //   6847: astore 21
          //   6849: aload 19
          //   6851: astore 29
          //   6853: iload_2
          //   6854: istore_3
          //   6855: aload 17
          //   6857: astore 22
          //   6859: aload 16
          //   6861: astore 23
          //   6863: iload 5
          //   6865: istore 4
          //   6867: aload 19
          //   6869: astore 31
          //   6871: aload 17
          //   6873: astore 26
          //   6875: aload 16
          //   6877: astore 27
          //   6879: aload 19
          //   6881: astore 30
          //   6883: aload 17
          //   6885: astore 24
          //   6887: aload 16
          //   6889: astore 25
          //   6891: aload 19
          //   6893: astore 18
          //   6895: aload 17
          //   6897: astore 14
          //   6899: aload 16
          //   6901: astore 15
          //   6903: aload 33
          //   6905: athrow
          //   6906: astore 16
          //   6908: aload 30
          //   6910: astore 18
          //   6912: aload 24
          //   6914: astore 14
          //   6916: aload 25
          //   6918: astore 15
          //   6920: getstatic 337	org/apache/cordova/filetransfer/FileTransfer:CONNECTION_ERR	I
          //   6923: aload_0
          //   6924: getfield 71	org/apache/cordova/filetransfer/FileTransfer$3:val$source	Ljava/lang/String;
          //   6927: aload_0
          //   6928: getfield 55	org/apache/cordova/filetransfer/FileTransfer$3:val$target	Ljava/lang/String;
          //   6931: aload 30
          //   6933: invokestatic 315	org/apache/cordova/filetransfer/FileTransfer:access$6	(ILjava/lang/String;Ljava/lang/String;Ljava/net/URLConnection;)Lorg/json/JSONObject;
          //   6936: astore 17
          //   6938: aload 30
          //   6940: astore 18
          //   6942: aload 24
          //   6944: astore 14
          //   6946: aload 25
          //   6948: astore 15
          //   6950: ldc -29
          //   6952: aload 17
          //   6954: invokevirtual 316	org/json/JSONObject:toString	()Ljava/lang/String;
          //   6957: aload 16
          //   6959: invokestatic 308	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
          //   6962: pop
          //   6963: aload 30
          //   6965: astore 18
          //   6967: aload 24
          //   6969: astore 14
          //   6971: aload 25
          //   6973: astore 15
          //   6975: aload_0
          //   6976: getfield 41	org/apache/cordova/filetransfer/FileTransfer$3:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
          //   6979: new 318	org/apache/cordova/PluginResult
          //   6982: dup
          //   6983: getstatic 324	org/apache/cordova/PluginResult$Status:IO_EXCEPTION	Lorg/apache/cordova/PluginResult$Status;
          //   6986: aload 17
          //   6988: invokespecial 327	org/apache/cordova/PluginResult:<init>	(Lorg/apache/cordova/PluginResult$Status;Lorg/json/JSONObject;)V
          //   6991: invokevirtual 331	org/apache/cordova/filetransfer/FileTransfer$RequestContext:sendPluginResult	(Lorg/apache/cordova/PluginResult;)V
          //   6994: invokestatic 273	org/apache/cordova/filetransfer/FileTransfer:access$0	()Ljava/util/HashMap;
          //   6997: astore 14
          //   6999: aload 14
          //   7001: monitorenter
          //   7002: invokestatic 273	org/apache/cordova/filetransfer/FileTransfer:access$0	()Ljava/util/HashMap;
          //   7005: aload_0
          //   7006: getfield 43	org/apache/cordova/filetransfer/FileTransfer$3:val$objectId	Ljava/lang/String;
          //   7009: invokevirtual 279	java/util/HashMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
          //   7012: pop
          //   7013: aload 14
          //   7015: monitorexit
          //   7016: aload 30
          //   7018: ifnull -7008 -> 10
          //   7021: aload_0
          //   7022: getfield 45	org/apache/cordova/filetransfer/FileTransfer$3:val$trustEveryone	Z
          //   7025: ifeq -7015 -> 10
          //   7028: aload_0
          //   7029: getfield 47	org/apache/cordova/filetransfer/FileTransfer$3:val$useHttps	Z
          //   7032: ifeq -7022 -> 10
          //   7035: aload 30
          //   7037: checkcast 103	javax/net/ssl/HttpsURLConnection
          //   7040: astore 14
          //   7042: aload 14
          //   7044: aload 24
          //   7046: invokevirtual 118	javax/net/ssl/HttpsURLConnection:setHostnameVerifier	(Ljavax/net/ssl/HostnameVerifier;)V
          //   7049: aload 14
          //   7051: aload 25
          //   7053: invokevirtual 283	javax/net/ssl/HttpsURLConnection:setSSLSocketFactory	(Ljavax/net/ssl/SSLSocketFactory;)V
          //   7056: return
          //   7057: aload 33
          //   7059: astore 32
          //   7061: aload 14
          //   7063: aload 15
          //   7065: iconst_0
          //   7066: iload_3
          //   7067: invokevirtual 461	java/io/ByteArrayOutputStream:write	([BII)V
          //   7070: goto -1026 -> 6044
          //   7073: astore 15
          //   7075: aload 14
          //   7077: monitorexit
          //   7078: aload 15
          //   7080: athrow
          //   7081: astore 15
          //   7083: aload 14
          //   7085: monitorexit
          //   7086: aload 15
          //   7088: athrow
          //   7089: astore 15
          //   7091: aload 14
          //   7093: monitorexit
          //   7094: aload 15
          //   7096: athrow
          //   7097: astore 15
          //   7099: aload 14
          //   7101: monitorexit
          //   7102: aload 15
          //   7104: athrow
          //   7105: astore 16
          //   7107: invokestatic 273	org/apache/cordova/filetransfer/FileTransfer:access$0	()Ljava/util/HashMap;
          //   7110: astore 17
          //   7112: aload 17
          //   7114: monitorenter
          //   7115: invokestatic 273	org/apache/cordova/filetransfer/FileTransfer:access$0	()Ljava/util/HashMap;
          //   7118: aload_0
          //   7119: getfield 43	org/apache/cordova/filetransfer/FileTransfer$3:val$objectId	Ljava/lang/String;
          //   7122: invokevirtual 279	java/util/HashMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
          //   7125: pop
          //   7126: aload 17
          //   7128: monitorexit
          //   7129: aload 18
          //   7131: ifnull +38 -> 7169
          //   7134: aload_0
          //   7135: getfield 45	org/apache/cordova/filetransfer/FileTransfer$3:val$trustEveryone	Z
          //   7138: ifeq +31 -> 7169
          //   7141: aload_0
          //   7142: getfield 47	org/apache/cordova/filetransfer/FileTransfer$3:val$useHttps	Z
          //   7145: ifeq +24 -> 7169
          //   7148: aload 18
          //   7150: checkcast 103	javax/net/ssl/HttpsURLConnection
          //   7153: astore 17
          //   7155: aload 17
          //   7157: aload 14
          //   7159: invokevirtual 118	javax/net/ssl/HttpsURLConnection:setHostnameVerifier	(Ljavax/net/ssl/HostnameVerifier;)V
          //   7162: aload 17
          //   7164: aload 15
          //   7166: invokevirtual 283	javax/net/ssl/HttpsURLConnection:setSSLSocketFactory	(Ljavax/net/ssl/SSLSocketFactory;)V
          //   7169: aload 16
          //   7171: athrow
          //   7172: astore 14
          //   7174: aload 17
          //   7176: monitorexit
          //   7177: aload 14
          //   7179: athrow
          //   7180: astore 15
          //   7182: aload 14
          //   7184: monitorexit
          //   7185: aload 15
          //   7187: athrow
          //   7188: iconst_1
          //   7189: istore_1
          //   7190: iload_1
          //   7191: ifne -3017 -> 4174
          //   7194: iload_2
          //   7195: iconst_m1
          //   7196: if_icmpeq -3022 -> 4174
          //   7199: iconst_0
          //   7200: istore_1
          //   7201: goto -4230 -> 2971
          // Local variable table:
          //   start	length	slot	name	signature
          //   0	7204	0	this	3
          //   69	7132	1	i	int
          //   2359	4838	2	j	int
          //   87	6980	3	k	int
          //   98	6768	4	m	int
          //   66	6798	5	n	int
          //   63	5373	6	i1	int
          //   2285	228	7	i2	int
          //   2356	157	8	i3	int
          //   1625	3	9	bool	boolean
          //   4560	1425	10	l1	long
          //   5775	208	12	l2	long
          //   7172	11	14	localObject2	Object
          //   134	4240	15	localObject3	Object
          //   4457	6	15	localObject4	Object
          //   4669	688	15	localIOException1	IOException
          //   5426	13	15	localObject5	Object
          //   5506	472	15	localObject6	Object
          //   5989	6	15	localObject7	Object
          //   6042	550	15	localObject8	Object
          //   6678	10	15	localObject9	Object
          //   6756	308	15	localObject10	Object
          //   7073	6	15	localObject11	Object
          //   7081	6	15	localObject12	Object
          //   7089	6	15	localObject13	Object
          //   7097	68	15	localSSLSocketFactory	SSLSocketFactory
          //   7180	6	15	localObject14	Object
          //   24	3975	16	localObject15	Object
          //   4018	224	16	localFileNotFoundException	java.io.FileNotFoundException
          //   4254	1394	16	localIOException2	IOException
          //   5654	1246	16	localJSONException1	JSONException
          //   6906	52	16	localThrowable	Throwable
          //   7105	65	16	localObject16	Object
          //   126	7023	18	localObject18	Object
          //   15	6877	19	localHttpURLConnection1	HttpURLConnection
          //   76	6768	20	localObject19	Object
          //   80	6768	21	localObject20	Object
          //   90	6768	22	localObject21	Object
          //   94	6768	23	localObject22	Object
          //   118	6927	24	localObject23	Object
          //   122	6930	25	localObject24	Object
          //   106	6768	26	localObject25	Object
          //   110	6768	27	localObject26	Object
          //   72	6768	28	localObject27	Object
          //   84	6768	29	localObject28	Object
          //   114	6922	30	localObject29	Object
          //   102	6768	31	localHttpURLConnection2	HttpURLConnection
          //   27	7033	32	localObject30	Object
          //   30	3355	33	localOutputStream	OutputStream
          //   3934	812	33	localJSONException2	JSONException
          //   5186	246	33	localTrackingInputStream	FileTransfer.TrackingInputStream
          //   5441	1243	33	localCloseable	Closeable
          //   6690	368	33	localObject31	Object
          //   33	6490	34	localObject32	Object
          //   36	4552	35	arrayOfByte1	byte[]
          //   39	4461	36	arrayOfByte2	byte[]
          //   45	498	37	localObject33	Object
          //   48	509	38	localObject34	Object
          //   51	522	39	localObject35	Object
          //   54	531	40	localObject36	Object
          //   57	540	41	localObject37	Object
          //   42	705	42	localHttpsURLConnection	HttpsURLConnection
          //   60	325	43	localObject38	Object
          //   18	245	44	localObject39	Object
          //   21	254	45	localObject40	Object
          //   143	6462	46	localFileUploadResult	FileUploadResult
          //   218	5728	47	localFileProgressResult	FileProgressResult
          // Exception table:
          //   from	to	target	type
          //   1555	1564	3934	org/json/JSONException
          //   1618	1627	3934	org/json/JSONException
          //   3506	3515	3934	org/json/JSONException
          //   3569	3583	3934	org/json/JSONException
          //   3637	3655	3934	org/json/JSONException
          //   3709	3730	3934	org/json/JSONException
          //   3784	3797	3934	org/json/JSONException
          //   3851	3869	3934	org/json/JSONException
          //   3923	3931	3934	org/json/JSONException
          //   136	145	4018	java/io/FileNotFoundException
          //   211	220	4018	java/io/FileNotFoundException
          //   286	299	4018	java/io/FileNotFoundException
          //   373	380	4018	java/io/FileNotFoundException
          //   454	461	4018	java/io/FileNotFoundException
          //   527	534	4018	java/io/FileNotFoundException
          //   600	607	4018	java/io/FileNotFoundException
          //   673	680	4018	java/io/FileNotFoundException
          //   746	754	4018	java/io/FileNotFoundException
          //   820	826	4018	java/io/FileNotFoundException
          //   892	898	4018	java/io/FileNotFoundException
          //   964	970	4018	java/io/FileNotFoundException
          //   1036	1045	4018	java/io/FileNotFoundException
          //   1111	1120	4018	java/io/FileNotFoundException
          //   1186	1198	4018	java/io/FileNotFoundException
          //   1269	1278	4018	java/io/FileNotFoundException
          //   1344	1351	4018	java/io/FileNotFoundException
          //   1417	1426	4018	java/io/FileNotFoundException
          //   1492	1501	4018	java/io/FileNotFoundException
          //   1555	1564	4018	java/io/FileNotFoundException
          //   1618	1627	4018	java/io/FileNotFoundException
          //   1698	1716	4018	java/io/FileNotFoundException
          //   1782	1802	4018	java/io/FileNotFoundException
          //   1868	1893	4018	java/io/FileNotFoundException
          //   1959	1984	4018	java/io/FileNotFoundException
          //   2050	2062	4018	java/io/FileNotFoundException
          //   2128	2137	4018	java/io/FileNotFoundException
          //   2203	2216	4018	java/io/FileNotFoundException
          //   2282	2287	4018	java/io/FileNotFoundException
          //   2353	2358	4018	java/io/FileNotFoundException
          //   2426	2436	4018	java/io/FileNotFoundException
          //   2502	2515	4018	java/io/FileNotFoundException
          //   2581	2587	4018	java/io/FileNotFoundException
          //   2653	2660	4018	java/io/FileNotFoundException
          //   2726	2748	4018	java/io/FileNotFoundException
          //   2814	2821	4018	java/io/FileNotFoundException
          //   2887	2895	4018	java/io/FileNotFoundException
          //   2961	2968	4018	java/io/FileNotFoundException
          //   3041	3049	4018	java/io/FileNotFoundException
          //   3115	3124	4018	java/io/FileNotFoundException
          //   3190	3195	4018	java/io/FileNotFoundException
          //   3310	3318	4018	java/io/FileNotFoundException
          //   3384	3389	4018	java/io/FileNotFoundException
          //   3506	3515	4018	java/io/FileNotFoundException
          //   3569	3583	4018	java/io/FileNotFoundException
          //   3637	3655	4018	java/io/FileNotFoundException
          //   3709	3730	4018	java/io/FileNotFoundException
          //   3784	3797	4018	java/io/FileNotFoundException
          //   3851	3869	4018	java/io/FileNotFoundException
          //   3923	3931	4018	java/io/FileNotFoundException
          //   4002	4015	4018	java/io/FileNotFoundException
          //   4245	4251	4018	java/io/FileNotFoundException
          //   4671	4679	4018	java/io/FileNotFoundException
          //   4745	4750	4018	java/io/FileNotFoundException
          //   4816	4824	4018	java/io/FileNotFoundException
          //   4890	4924	4018	java/io/FileNotFoundException
          //   4990	4996	4018	java/io/FileNotFoundException
          //   5062	5085	4018	java/io/FileNotFoundException
          //   5151	5178	4018	java/io/FileNotFoundException
          //   5284	5292	4018	java/io/FileNotFoundException
          //   5358	5363	4018	java/io/FileNotFoundException
          //   5508	5516	4018	java/io/FileNotFoundException
          //   5581	5586	4018	java/io/FileNotFoundException
          //   5651	5654	4018	java/io/FileNotFoundException
          //   6139	6147	4018	java/io/FileNotFoundException
          //   6213	6218	4018	java/io/FileNotFoundException
          //   6284	6293	4018	java/io/FileNotFoundException
          //   6359	6382	4018	java/io/FileNotFoundException
          //   6448	6454	4018	java/io/FileNotFoundException
          //   6520	6527	4018	java/io/FileNotFoundException
          //   6593	6615	4018	java/io/FileNotFoundException
          //   6758	6766	4018	java/io/FileNotFoundException
          //   6832	6837	4018	java/io/FileNotFoundException
          //   6903	6906	4018	java/io/FileNotFoundException
          //   136	145	4254	java/io/IOException
          //   211	220	4254	java/io/IOException
          //   286	299	4254	java/io/IOException
          //   373	380	4254	java/io/IOException
          //   454	461	4254	java/io/IOException
          //   527	534	4254	java/io/IOException
          //   600	607	4254	java/io/IOException
          //   673	680	4254	java/io/IOException
          //   746	754	4254	java/io/IOException
          //   820	826	4254	java/io/IOException
          //   892	898	4254	java/io/IOException
          //   964	970	4254	java/io/IOException
          //   1036	1045	4254	java/io/IOException
          //   1111	1120	4254	java/io/IOException
          //   1186	1198	4254	java/io/IOException
          //   1269	1278	4254	java/io/IOException
          //   1344	1351	4254	java/io/IOException
          //   1417	1426	4254	java/io/IOException
          //   1492	1501	4254	java/io/IOException
          //   1555	1564	4254	java/io/IOException
          //   1618	1627	4254	java/io/IOException
          //   1698	1716	4254	java/io/IOException
          //   1782	1802	4254	java/io/IOException
          //   1868	1893	4254	java/io/IOException
          //   1959	1984	4254	java/io/IOException
          //   2050	2062	4254	java/io/IOException
          //   2128	2137	4254	java/io/IOException
          //   2203	2216	4254	java/io/IOException
          //   2282	2287	4254	java/io/IOException
          //   2353	2358	4254	java/io/IOException
          //   2426	2436	4254	java/io/IOException
          //   2502	2515	4254	java/io/IOException
          //   2581	2587	4254	java/io/IOException
          //   2653	2660	4254	java/io/IOException
          //   2726	2748	4254	java/io/IOException
          //   2814	2821	4254	java/io/IOException
          //   2887	2895	4254	java/io/IOException
          //   2961	2968	4254	java/io/IOException
          //   3041	3049	4254	java/io/IOException
          //   3115	3124	4254	java/io/IOException
          //   3190	3195	4254	java/io/IOException
          //   3310	3318	4254	java/io/IOException
          //   3384	3389	4254	java/io/IOException
          //   3506	3515	4254	java/io/IOException
          //   3569	3583	4254	java/io/IOException
          //   3637	3655	4254	java/io/IOException
          //   3709	3730	4254	java/io/IOException
          //   3784	3797	4254	java/io/IOException
          //   3851	3869	4254	java/io/IOException
          //   3923	3931	4254	java/io/IOException
          //   4002	4015	4254	java/io/IOException
          //   4245	4251	4254	java/io/IOException
          //   4671	4679	4254	java/io/IOException
          //   4745	4750	4254	java/io/IOException
          //   4816	4824	4254	java/io/IOException
          //   4890	4924	4254	java/io/IOException
          //   4990	4996	4254	java/io/IOException
          //   5062	5085	4254	java/io/IOException
          //   5151	5178	4254	java/io/IOException
          //   5284	5292	4254	java/io/IOException
          //   5358	5363	4254	java/io/IOException
          //   5508	5516	4254	java/io/IOException
          //   5581	5586	4254	java/io/IOException
          //   5651	5654	4254	java/io/IOException
          //   6139	6147	4254	java/io/IOException
          //   6213	6218	4254	java/io/IOException
          //   6284	6293	4254	java/io/IOException
          //   6359	6382	4254	java/io/IOException
          //   6448	6454	4254	java/io/IOException
          //   6520	6527	4254	java/io/IOException
          //   6593	6615	4254	java/io/IOException
          //   6758	6766	4254	java/io/IOException
          //   6832	6837	4254	java/io/IOException
          //   6903	6906	4254	java/io/IOException
          //   3397	3411	4457	finally
          //   4459	4462	4457	finally
          //   3231	3244	5426	finally
          //   4465	4477	5426	finally
          //   5428	5431	5426	finally
          //   3201	3208	5441	finally
          //   3215	3221	5441	finally
          //   3228	3231	5441	finally
          //   4484	4491	5441	finally
          //   4498	4504	5441	finally
          //   4510	4526	5441	finally
          //   4532	4538	5441	finally
          //   4544	4559	5441	finally
          //   4573	4580	5441	finally
          //   4586	4593	5441	finally
          //   4600	4605	5441	finally
          //   5438	5441	5441	finally
          //   5783	5790	5441	finally
          //   5796	5806	5441	finally
          //   5837	5876	5441	finally
          //   5882	5898	5441	finally
          //   5904	5919	5441	finally
          //   5925	5932	5441	finally
          //   5938	5955	5441	finally
          //   5961	5967	5441	finally
          //   5973	5982	5441	finally
          //   136	145	5654	org/json/JSONException
          //   211	220	5654	org/json/JSONException
          //   286	299	5654	org/json/JSONException
          //   373	380	5654	org/json/JSONException
          //   454	461	5654	org/json/JSONException
          //   527	534	5654	org/json/JSONException
          //   600	607	5654	org/json/JSONException
          //   673	680	5654	org/json/JSONException
          //   746	754	5654	org/json/JSONException
          //   820	826	5654	org/json/JSONException
          //   892	898	5654	org/json/JSONException
          //   964	970	5654	org/json/JSONException
          //   1036	1045	5654	org/json/JSONException
          //   1111	1120	5654	org/json/JSONException
          //   1186	1198	5654	org/json/JSONException
          //   1269	1278	5654	org/json/JSONException
          //   1344	1351	5654	org/json/JSONException
          //   1417	1426	5654	org/json/JSONException
          //   1492	1501	5654	org/json/JSONException
          //   1698	1716	5654	org/json/JSONException
          //   1782	1802	5654	org/json/JSONException
          //   1868	1893	5654	org/json/JSONException
          //   1959	1984	5654	org/json/JSONException
          //   2050	2062	5654	org/json/JSONException
          //   2128	2137	5654	org/json/JSONException
          //   2203	2216	5654	org/json/JSONException
          //   2282	2287	5654	org/json/JSONException
          //   2353	2358	5654	org/json/JSONException
          //   2426	2436	5654	org/json/JSONException
          //   2502	2515	5654	org/json/JSONException
          //   2581	2587	5654	org/json/JSONException
          //   2653	2660	5654	org/json/JSONException
          //   2726	2748	5654	org/json/JSONException
          //   2814	2821	5654	org/json/JSONException
          //   2887	2895	5654	org/json/JSONException
          //   2961	2968	5654	org/json/JSONException
          //   3041	3049	5654	org/json/JSONException
          //   3115	3124	5654	org/json/JSONException
          //   3190	3195	5654	org/json/JSONException
          //   3310	3318	5654	org/json/JSONException
          //   3384	3389	5654	org/json/JSONException
          //   4002	4015	5654	org/json/JSONException
          //   4245	4251	5654	org/json/JSONException
          //   4671	4679	5654	org/json/JSONException
          //   4745	4750	5654	org/json/JSONException
          //   4816	4824	5654	org/json/JSONException
          //   4890	4924	5654	org/json/JSONException
          //   4990	4996	5654	org/json/JSONException
          //   5062	5085	5654	org/json/JSONException
          //   5151	5178	5654	org/json/JSONException
          //   5284	5292	5654	org/json/JSONException
          //   5358	5363	5654	org/json/JSONException
          //   5508	5516	5654	org/json/JSONException
          //   5581	5586	5654	org/json/JSONException
          //   5651	5654	5654	org/json/JSONException
          //   6139	6147	5654	org/json/JSONException
          //   6213	6218	5654	org/json/JSONException
          //   6284	6293	5654	org/json/JSONException
          //   6359	6382	5654	org/json/JSONException
          //   6448	6454	5654	org/json/JSONException
          //   6520	6527	5654	org/json/JSONException
          //   6593	6615	5654	org/json/JSONException
          //   6758	6766	5654	org/json/JSONException
          //   6832	6837	5654	org/json/JSONException
          //   6903	6906	5654	org/json/JSONException
          //   5371	5385	5989	finally
          //   5991	5994	5989	finally
          //   5205	5218	6678	finally
          //   5997	6009	6678	finally
          //   6680	6683	6678	finally
          //   5181	5188	6690	finally
          //   5192	5198	6690	finally
          //   5202	5205	6690	finally
          //   6013	6033	6690	finally
          //   6037	6044	6690	finally
          //   6048	6056	6690	finally
          //   6064	6073	6690	finally
          //   6687	6690	6690	finally
          //   7061	7070	6690	finally
          //   136	145	6906	java/lang/Throwable
          //   211	220	6906	java/lang/Throwable
          //   286	299	6906	java/lang/Throwable
          //   373	380	6906	java/lang/Throwable
          //   454	461	6906	java/lang/Throwable
          //   527	534	6906	java/lang/Throwable
          //   600	607	6906	java/lang/Throwable
          //   673	680	6906	java/lang/Throwable
          //   746	754	6906	java/lang/Throwable
          //   820	826	6906	java/lang/Throwable
          //   892	898	6906	java/lang/Throwable
          //   964	970	6906	java/lang/Throwable
          //   1036	1045	6906	java/lang/Throwable
          //   1111	1120	6906	java/lang/Throwable
          //   1186	1198	6906	java/lang/Throwable
          //   1269	1278	6906	java/lang/Throwable
          //   1344	1351	6906	java/lang/Throwable
          //   1417	1426	6906	java/lang/Throwable
          //   1492	1501	6906	java/lang/Throwable
          //   1555	1564	6906	java/lang/Throwable
          //   1618	1627	6906	java/lang/Throwable
          //   1698	1716	6906	java/lang/Throwable
          //   1782	1802	6906	java/lang/Throwable
          //   1868	1893	6906	java/lang/Throwable
          //   1959	1984	6906	java/lang/Throwable
          //   2050	2062	6906	java/lang/Throwable
          //   2128	2137	6906	java/lang/Throwable
          //   2203	2216	6906	java/lang/Throwable
          //   2282	2287	6906	java/lang/Throwable
          //   2353	2358	6906	java/lang/Throwable
          //   2426	2436	6906	java/lang/Throwable
          //   2502	2515	6906	java/lang/Throwable
          //   2581	2587	6906	java/lang/Throwable
          //   2653	2660	6906	java/lang/Throwable
          //   2726	2748	6906	java/lang/Throwable
          //   2814	2821	6906	java/lang/Throwable
          //   2887	2895	6906	java/lang/Throwable
          //   2961	2968	6906	java/lang/Throwable
          //   3041	3049	6906	java/lang/Throwable
          //   3115	3124	6906	java/lang/Throwable
          //   3190	3195	6906	java/lang/Throwable
          //   3310	3318	6906	java/lang/Throwable
          //   3384	3389	6906	java/lang/Throwable
          //   3506	3515	6906	java/lang/Throwable
          //   3569	3583	6906	java/lang/Throwable
          //   3637	3655	6906	java/lang/Throwable
          //   3709	3730	6906	java/lang/Throwable
          //   3784	3797	6906	java/lang/Throwable
          //   3851	3869	6906	java/lang/Throwable
          //   3923	3931	6906	java/lang/Throwable
          //   4002	4015	6906	java/lang/Throwable
          //   4245	4251	6906	java/lang/Throwable
          //   4671	4679	6906	java/lang/Throwable
          //   4745	4750	6906	java/lang/Throwable
          //   4816	4824	6906	java/lang/Throwable
          //   4890	4924	6906	java/lang/Throwable
          //   4990	4996	6906	java/lang/Throwable
          //   5062	5085	6906	java/lang/Throwable
          //   5151	5178	6906	java/lang/Throwable
          //   5284	5292	6906	java/lang/Throwable
          //   5358	5363	6906	java/lang/Throwable
          //   5508	5516	6906	java/lang/Throwable
          //   5581	5586	6906	java/lang/Throwable
          //   5651	5654	6906	java/lang/Throwable
          //   6139	6147	6906	java/lang/Throwable
          //   6213	6218	6906	java/lang/Throwable
          //   6284	6293	6906	java/lang/Throwable
          //   6359	6382	6906	java/lang/Throwable
          //   6448	6454	6906	java/lang/Throwable
          //   6520	6527	6906	java/lang/Throwable
          //   6593	6615	6906	java/lang/Throwable
          //   6758	6766	6906	java/lang/Throwable
          //   6832	6837	6906	java/lang/Throwable
          //   6903	6906	6906	java/lang/Throwable
          //   4114	4128	7073	finally
          //   7075	7078	7073	finally
          //   4402	4416	7081	finally
          //   7083	7086	7081	finally
          //   5718	5732	7089	finally
          //   7091	7094	7089	finally
          //   7002	7016	7097	finally
          //   7099	7102	7097	finally
          //   136	145	7105	finally
          //   211	220	7105	finally
          //   286	299	7105	finally
          //   373	380	7105	finally
          //   454	461	7105	finally
          //   527	534	7105	finally
          //   600	607	7105	finally
          //   673	680	7105	finally
          //   746	754	7105	finally
          //   820	826	7105	finally
          //   892	898	7105	finally
          //   964	970	7105	finally
          //   1036	1045	7105	finally
          //   1111	1120	7105	finally
          //   1186	1198	7105	finally
          //   1269	1278	7105	finally
          //   1344	1351	7105	finally
          //   1417	1426	7105	finally
          //   1492	1501	7105	finally
          //   1555	1564	7105	finally
          //   1618	1627	7105	finally
          //   1698	1716	7105	finally
          //   1782	1802	7105	finally
          //   1868	1893	7105	finally
          //   1959	1984	7105	finally
          //   2050	2062	7105	finally
          //   2128	2137	7105	finally
          //   2203	2216	7105	finally
          //   2282	2287	7105	finally
          //   2353	2358	7105	finally
          //   2426	2436	7105	finally
          //   2502	2515	7105	finally
          //   2581	2587	7105	finally
          //   2653	2660	7105	finally
          //   2726	2748	7105	finally
          //   2814	2821	7105	finally
          //   2887	2895	7105	finally
          //   2961	2968	7105	finally
          //   3041	3049	7105	finally
          //   3115	3124	7105	finally
          //   3190	3195	7105	finally
          //   3310	3318	7105	finally
          //   3384	3389	7105	finally
          //   3506	3515	7105	finally
          //   3569	3583	7105	finally
          //   3637	3655	7105	finally
          //   3709	3730	7105	finally
          //   3784	3797	7105	finally
          //   3851	3869	7105	finally
          //   3923	3931	7105	finally
          //   4002	4015	7105	finally
          //   4032	4050	7105	finally
          //   4062	4075	7105	finally
          //   4087	4106	7105	finally
          //   4245	4251	7105	finally
          //   4268	4286	7105	finally
          //   4298	4311	7105	finally
          //   4323	4363	7105	finally
          //   4375	4394	7105	finally
          //   4671	4679	7105	finally
          //   4745	4750	7105	finally
          //   4816	4824	7105	finally
          //   4890	4924	7105	finally
          //   4990	4996	7105	finally
          //   5062	5085	7105	finally
          //   5151	5178	7105	finally
          //   5284	5292	7105	finally
          //   5358	5363	7105	finally
          //   5508	5516	7105	finally
          //   5581	5586	7105	finally
          //   5651	5654	7105	finally
          //   5668	5681	7105	finally
          //   5693	5710	7105	finally
          //   6139	6147	7105	finally
          //   6213	6218	7105	finally
          //   6284	6293	7105	finally
          //   6359	6382	7105	finally
          //   6448	6454	7105	finally
          //   6520	6527	7105	finally
          //   6593	6615	7105	finally
          //   6758	6766	7105	finally
          //   6832	6837	7105	finally
          //   6903	6906	7105	finally
          //   6920	6938	7105	finally
          //   6950	6963	7105	finally
          //   6975	6994	7105	finally
          //   7115	7129	7172	finally
          //   7174	7177	7172	finally
          //   6623	6637	7180	finally
          //   7182	7185	7180	finally
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
      if (paramString.equals("upload")) {
        try
        {
          upload(URLDecoder.decode(str1, "UTF-8"), str2, paramJSONArray, paramCallbackContext);
          return true;
        }
        catch (UnsupportedEncodingException paramString)
        {
          paramCallbackContext.sendPluginResult(new PluginResult(PluginResult.Status.MALFORMED_URL_EXCEPTION, "UTF-8 error."));
          return true;
        }
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
    InputStream currentInputStream;
    OutputStream currentOutputStream;
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
    
    public int read(byte[] paramArrayOfByte)
      throws IOException
    {
      return updateBytesRead(super.read(paramArrayOfByte));
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