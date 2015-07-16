package org.apache.cordova.filetransfer;

import android.net.Uri;
import org.apache.cordova.CordovaResourceApi;
import org.json.JSONObject;

class FileTransfer$1
  implements Runnable
{
  FileTransfer$1(FileTransfer paramFileTransfer, FileTransfer.RequestContext paramRequestContext, CordovaResourceApi paramCordovaResourceApi, Uri paramUri1, boolean paramBoolean1, boolean paramBoolean2, String paramString1, String paramString2, JSONObject paramJSONObject1, JSONObject paramJSONObject2, String paramString3, String paramString4, String paramString5, Uri paramUri2, boolean paramBoolean3, String paramString6, String paramString7) {}
  
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
    //   6996	763	14	localSSLSocketFactory1	javax.net.ssl.SSLSocketFactory
    //   7763	6	14	localObject11	Object
    //   7771	6	14	localObject12	Object
    //   7779	6	14	localObject13	Object
    //   7787	6	14	localObject14	Object
    //   7795	6	14	localObject15	Object
    //   24	4065	15	localObject16	Object
    //   4101	685	15	localFileNotFoundException	java.io.FileNotFoundException
    //   4792	1315	15	localIOException	java.io.IOException
    //   6113	187	15	localJSONException1	org.json.JSONException
    //   6306	528	15	localThrowable	Throwable
    //   6840	917	15	localSSLSocketFactory2	javax.net.ssl.SSLSocketFactory
    //   12	7794	16	localObject17	Object
    //   126	7625	17	localObject18	Object
    //   15	7734	18	localHttpURLConnection1	java.net.HttpURLConnection
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
    //   102	7625	30	localHttpURLConnection2	java.net.HttpURLConnection
    //   27	7591	31	localObject30	Object
    //   7689	72	31	localObject31	Object
    //   30	596	32	localObject32	Object
    //   2109	76	32	localJSONException2	org.json.JSONException
    //   3770	788	32	localOutputStream	java.io.OutputStream
    //   4579	615	32	localCloseable	java.io.Closeable
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
    //   42	705	41	localHttpsURLConnection	javax.net.ssl.HttpsURLConnection
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
}

/* Location:
 * Qualified Name:     org.apache.cordova.filetransfer.FileTransfer.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */