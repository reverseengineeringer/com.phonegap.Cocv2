package org.apache.cordova.mediacapture;

class Capture$2
  implements Runnable
{
  Capture$2(Capture paramCapture1, Capture paramCapture2) {}
  
  /* Error */
  public void run()
  {
    // Byte code:
    //   0: new 31	android/content/ContentValues
    //   3: dup
    //   4: invokespecial 32	android/content/ContentValues:<init>	()V
    //   7: astore_3
    //   8: aload_3
    //   9: ldc 34
    //   11: ldc 36
    //   13: invokevirtual 40	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   16: aload_0
    //   17: getfield 20	org/apache/cordova/mediacapture/Capture$2:val$that	Lorg/apache/cordova/mediacapture/Capture;
    //   20: getfield 44	org/apache/cordova/mediacapture/Capture:cordova	Lorg/apache/cordova/CordovaInterface;
    //   23: invokeinterface 50 1 0
    //   28: invokevirtual 56	android/app/Activity:getContentResolver	()Landroid/content/ContentResolver;
    //   31: getstatic 62	android/provider/MediaStore$Images$Media:EXTERNAL_CONTENT_URI	Landroid/net/Uri;
    //   34: aload_3
    //   35: invokevirtual 68	android/content/ContentResolver:insert	(Landroid/net/Uri;Landroid/content/ContentValues;)Landroid/net/Uri;
    //   38: astore_2
    //   39: new 70	java/io/FileInputStream
    //   42: dup
    //   43: new 72	java/lang/StringBuilder
    //   46: dup
    //   47: invokespecial 73	java/lang/StringBuilder:<init>	()V
    //   50: aload_0
    //   51: getfield 18	org/apache/cordova/mediacapture/Capture$2:this$0	Lorg/apache/cordova/mediacapture/Capture;
    //   54: invokestatic 77	org/apache/cordova/mediacapture/Capture:access$600	(Lorg/apache/cordova/mediacapture/Capture;)Ljava/lang/String;
    //   57: invokevirtual 81	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   60: ldc 83
    //   62: invokevirtual 81	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   65: invokevirtual 87	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   68: invokespecial 90	java/io/FileInputStream:<init>	(Ljava/lang/String;)V
    //   71: astore_3
    //   72: aload_0
    //   73: getfield 20	org/apache/cordova/mediacapture/Capture$2:val$that	Lorg/apache/cordova/mediacapture/Capture;
    //   76: getfield 44	org/apache/cordova/mediacapture/Capture:cordova	Lorg/apache/cordova/CordovaInterface;
    //   79: invokeinterface 50 1 0
    //   84: invokevirtual 56	android/app/Activity:getContentResolver	()Landroid/content/ContentResolver;
    //   87: aload_2
    //   88: invokevirtual 94	android/content/ContentResolver:openOutputStream	(Landroid/net/Uri;)Ljava/io/OutputStream;
    //   91: astore 4
    //   93: sipush 4096
    //   96: newarray <illegal type>
    //   98: astore 5
    //   100: aload_3
    //   101: aload 5
    //   103: invokevirtual 98	java/io/FileInputStream:read	([B)I
    //   106: istore_1
    //   107: iload_1
    //   108: iconst_m1
    //   109: if_icmpeq +98 -> 207
    //   112: aload 4
    //   114: aload 5
    //   116: iconst_0
    //   117: iload_1
    //   118: invokevirtual 104	java/io/OutputStream:write	([BII)V
    //   121: goto -21 -> 100
    //   124: astore_2
    //   125: aload_2
    //   126: invokevirtual 107	java/io/IOException:printStackTrace	()V
    //   129: aload_0
    //   130: getfield 20	org/apache/cordova/mediacapture/Capture$2:val$that	Lorg/apache/cordova/mediacapture/Capture;
    //   133: aload_0
    //   134: getfield 18	org/apache/cordova/mediacapture/Capture$2:this$0	Lorg/apache/cordova/mediacapture/Capture;
    //   137: iconst_0
    //   138: ldc 109
    //   140: invokestatic 113	org/apache/cordova/mediacapture/Capture:access$500	(Lorg/apache/cordova/mediacapture/Capture;ILjava/lang/String;)Lorg/json/JSONObject;
    //   143: invokevirtual 117	org/apache/cordova/mediacapture/Capture:fail	(Lorg/json/JSONObject;)V
    //   146: return
    //   147: astore_2
    //   148: ldc 119
    //   150: ldc 121
    //   152: invokestatic 126	org/apache/cordova/LOG:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   155: aload_0
    //   156: getfield 20	org/apache/cordova/mediacapture/Capture$2:val$that	Lorg/apache/cordova/mediacapture/Capture;
    //   159: getfield 44	org/apache/cordova/mediacapture/Capture:cordova	Lorg/apache/cordova/CordovaInterface;
    //   162: invokeinterface 50 1 0
    //   167: invokevirtual 56	android/app/Activity:getContentResolver	()Landroid/content/ContentResolver;
    //   170: getstatic 129	android/provider/MediaStore$Images$Media:INTERNAL_CONTENT_URI	Landroid/net/Uri;
    //   173: aload_3
    //   174: invokevirtual 68	android/content/ContentResolver:insert	(Landroid/net/Uri;Landroid/content/ContentValues;)Landroid/net/Uri;
    //   177: astore_2
    //   178: goto -139 -> 39
    //   181: astore_2
    //   182: ldc 119
    //   184: ldc -125
    //   186: invokestatic 126	org/apache/cordova/LOG:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   189: aload_0
    //   190: getfield 20	org/apache/cordova/mediacapture/Capture$2:val$that	Lorg/apache/cordova/mediacapture/Capture;
    //   193: aload_0
    //   194: getfield 18	org/apache/cordova/mediacapture/Capture$2:this$0	Lorg/apache/cordova/mediacapture/Capture;
    //   197: iconst_0
    //   198: ldc -123
    //   200: invokestatic 113	org/apache/cordova/mediacapture/Capture:access$500	(Lorg/apache/cordova/mediacapture/Capture;ILjava/lang/String;)Lorg/json/JSONObject;
    //   203: invokevirtual 117	org/apache/cordova/mediacapture/Capture:fail	(Lorg/json/JSONObject;)V
    //   206: return
    //   207: aload 4
    //   209: invokevirtual 136	java/io/OutputStream:flush	()V
    //   212: aload 4
    //   214: invokevirtual 139	java/io/OutputStream:close	()V
    //   217: aload_3
    //   218: invokevirtual 140	java/io/FileInputStream:close	()V
    //   221: aload_0
    //   222: getfield 18	org/apache/cordova/mediacapture/Capture$2:this$0	Lorg/apache/cordova/mediacapture/Capture;
    //   225: invokestatic 144	org/apache/cordova/mediacapture/Capture:access$100	(Lorg/apache/cordova/mediacapture/Capture;)Lorg/json/JSONArray;
    //   228: aload_0
    //   229: getfield 18	org/apache/cordova/mediacapture/Capture$2:this$0	Lorg/apache/cordova/mediacapture/Capture;
    //   232: aload_2
    //   233: invokestatic 148	org/apache/cordova/mediacapture/Capture:access$000	(Lorg/apache/cordova/mediacapture/Capture;Landroid/net/Uri;)Lorg/json/JSONObject;
    //   236: invokevirtual 153	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   239: pop
    //   240: aload_0
    //   241: getfield 18	org/apache/cordova/mediacapture/Capture$2:this$0	Lorg/apache/cordova/mediacapture/Capture;
    //   244: invokestatic 157	org/apache/cordova/mediacapture/Capture:access$700	(Lorg/apache/cordova/mediacapture/Capture;)V
    //   247: aload_0
    //   248: getfield 18	org/apache/cordova/mediacapture/Capture$2:this$0	Lorg/apache/cordova/mediacapture/Capture;
    //   251: invokestatic 144	org/apache/cordova/mediacapture/Capture:access$100	(Lorg/apache/cordova/mediacapture/Capture;)Lorg/json/JSONArray;
    //   254: invokevirtual 161	org/json/JSONArray:length	()I
    //   257: i2l
    //   258: aload_0
    //   259: getfield 18	org/apache/cordova/mediacapture/Capture$2:this$0	Lorg/apache/cordova/mediacapture/Capture;
    //   262: invokestatic 165	org/apache/cordova/mediacapture/Capture:access$200	(Lorg/apache/cordova/mediacapture/Capture;)J
    //   265: lcmp
    //   266: iflt +31 -> 297
    //   269: aload_0
    //   270: getfield 20	org/apache/cordova/mediacapture/Capture$2:val$that	Lorg/apache/cordova/mediacapture/Capture;
    //   273: invokestatic 169	org/apache/cordova/mediacapture/Capture:access$300	(Lorg/apache/cordova/mediacapture/Capture;)Lorg/apache/cordova/CallbackContext;
    //   276: new 171	org/apache/cordova/PluginResult
    //   279: dup
    //   280: getstatic 177	org/apache/cordova/PluginResult$Status:OK	Lorg/apache/cordova/PluginResult$Status;
    //   283: aload_0
    //   284: getfield 18	org/apache/cordova/mediacapture/Capture$2:this$0	Lorg/apache/cordova/mediacapture/Capture;
    //   287: invokestatic 144	org/apache/cordova/mediacapture/Capture:access$100	(Lorg/apache/cordova/mediacapture/Capture;)Lorg/json/JSONArray;
    //   290: invokespecial 180	org/apache/cordova/PluginResult:<init>	(Lorg/apache/cordova/PluginResult$Status;Lorg/json/JSONArray;)V
    //   293: invokevirtual 186	org/apache/cordova/CallbackContext:sendPluginResult	(Lorg/apache/cordova/PluginResult;)V
    //   296: return
    //   297: aload_0
    //   298: getfield 18	org/apache/cordova/mediacapture/Capture$2:this$0	Lorg/apache/cordova/mediacapture/Capture;
    //   301: invokestatic 189	org/apache/cordova/mediacapture/Capture:access$800	(Lorg/apache/cordova/mediacapture/Capture;)V
    //   304: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	305	0	this	2
    //   106	12	1	i	int
    //   38	50	2	localUri1	android.net.Uri
    //   124	2	2	localIOException	java.io.IOException
    //   147	1	2	localUnsupportedOperationException1	UnsupportedOperationException
    //   177	1	2	localUri2	android.net.Uri
    //   181	52	2	localUnsupportedOperationException2	UnsupportedOperationException
    //   7	211	3	localObject	Object
    //   91	122	4	localOutputStream	java.io.OutputStream
    //   98	17	5	arrayOfByte	byte[]
    // Exception table:
    //   from	to	target	type
    //   0	16	124	java/io/IOException
    //   16	39	124	java/io/IOException
    //   39	100	124	java/io/IOException
    //   100	107	124	java/io/IOException
    //   112	121	124	java/io/IOException
    //   148	155	124	java/io/IOException
    //   155	178	124	java/io/IOException
    //   182	206	124	java/io/IOException
    //   207	296	124	java/io/IOException
    //   297	304	124	java/io/IOException
    //   16	39	147	java/lang/UnsupportedOperationException
    //   155	178	181	java/lang/UnsupportedOperationException
  }
}

/* Location:
 * Qualified Name:     org.apache.cordova.mediacapture.Capture.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */