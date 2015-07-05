package org.apache.cordova.filetransfer;

import android.net.Uri;
import org.apache.cordova.CordovaResourceApi;
import org.json.JSONObject;

class FileTransfer$4
  implements Runnable
{
  FileTransfer$4(FileTransfer paramFileTransfer, FileTransfer.RequestContext paramRequestContext, String paramString1, boolean paramBoolean1, boolean paramBoolean2, String paramString2, String paramString3, CordovaResourceApi paramCordovaResourceApi, Uri paramUri1, Uri paramUri2, boolean paramBoolean3, JSONObject paramJSONObject) {}
  
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
    //   5970	69	3	localSSLSocketFactory	javax.net.ssl.SSLSocketFactory
    //   6117	4	3	localObject15	Object
    //   69	4957	4	localObject16	Object
    //   5035	64	4	localFileNotFoundException	java.io.FileNotFoundException
    //   5134	64	4	localPluginResult1	org.apache.cordova.PluginResult
    //   5317	64	4	localIOException	java.io.IOException
    //   5416	64	4	localPluginResult2	org.apache.cordova.PluginResult
    //   5549	28	4	localJSONException	org.json.JSONException
    //   5610	64	4	localPluginResult3	org.apache.cordova.PluginResult
    //   5743	64	4	localThrowable	Throwable
    //   5842	64	4	localPluginResult4	org.apache.cordova.PluginResult
    //   5975	172	4	localObject17	Object
    //   173	5895	6	localObject19	Object
    //   27	4988	7	localObject20	Object
    //   177	5918	8	localObject21	Object
    //   102	5876	9	localObject22	Object
    //   84	4935	10	localFile1	java.io.File
    //   117	5070	11	localObject23	Object
    //   121	5072	12	localObject24	Object
    //   133	5336	13	localObject25	Object
    //   137	5338	14	localObject26	Object
    //   149	5514	15	localObject27	Object
    //   153	5516	16	localObject28	Object
    //   165	5730	17	localObject29	Object
    //   169	5732	18	localObject30	Object
    //   196	5067	19	localOutputStream	java.io.OutputStream
    //   109	5112	20	localObject31	Object
    //   125	5378	21	localObject32	Object
    //   141	5556	22	localObject33	Object
    //   157	5772	23	localObject34	Object
    //   113	5135	24	localFile2	java.io.File
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
}

/* Location:
 * Qualified Name:     org.apache.cordova.filetransfer.FileTransfer.4
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */