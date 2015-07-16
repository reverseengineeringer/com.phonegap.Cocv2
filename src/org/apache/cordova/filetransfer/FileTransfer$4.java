package org.apache.cordova.filetransfer;

import android.net.Uri;
import org.apache.cordova.CordovaResourceApi;
import org.json.JSONObject;

class FileTransfer$4
  implements Runnable
{
  FileTransfer$4(FileTransfer paramFileTransfer, FileTransfer.RequestContext paramRequestContext, CordovaResourceApi paramCordovaResourceApi, Uri paramUri1, Uri paramUri2, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, JSONObject paramJSONObject, String paramString1, String paramString2, String paramString3) {}
  
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
    //   6066	89	12	localIOException1	java.io.IOException
    //   6334	53	12	localJSONException1	org.json.JSONException
    //   6564	95	12	localThrowable1	Throwable
    //   7004	68	12	localObject6	Object
    //   7090	33	12	localIOException2	java.io.IOException
    //   7141	1	12	localJSONException2	org.json.JSONException
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
}

/* Location:
 * Qualified Name:     org.apache.cordova.filetransfer.FileTransfer.4
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */