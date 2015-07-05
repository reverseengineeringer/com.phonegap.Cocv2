.class Lorg/apache/cordova/filetransfer/FileTransfer$1;
.super Ljava/lang/Object;
.source "FileTransfer.java"

# interfaces
.implements Ljava/lang/Runnable;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lorg/apache/cordova/filetransfer/FileTransfer;->upload(Ljava/lang/String;Ljava/lang/String;Lorg/json/JSONArray;Lorg/apache/cordova/CallbackContext;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lorg/apache/cordova/filetransfer/FileTransfer;

.field final synthetic val$chunkedMode:Z

.field final synthetic val$context:Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;

.field final synthetic val$fileKey:Ljava/lang/String;

.field final synthetic val$fileName:Ljava/lang/String;

.field final synthetic val$headers:Lorg/json/JSONObject;

.field final synthetic val$httpMethod:Ljava/lang/String;

.field final synthetic val$mimeType:Ljava/lang/String;

.field final synthetic val$objectId:Ljava/lang/String;

.field final synthetic val$params:Lorg/json/JSONObject;

.field final synthetic val$resourceApi:Lorg/apache/cordova/CordovaResourceApi;

.field final synthetic val$source:Ljava/lang/String;

.field final synthetic val$sourceUri:Landroid/net/Uri;

.field final synthetic val$target:Ljava/lang/String;

.field final synthetic val$targetUri:Landroid/net/Uri;

.field final synthetic val$trustEveryone:Z

.field final synthetic val$useHttps:Z


# direct methods
.method constructor <init>(Lorg/apache/cordova/filetransfer/FileTransfer;Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;Lorg/apache/cordova/CordovaResourceApi;Landroid/net/Uri;ZZLjava/lang/String;Ljava/lang/String;Lorg/json/JSONObject;Lorg/json/JSONObject;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Landroid/net/Uri;ZLjava/lang/String;Ljava/lang/String;)V
    .locals 1

    .prologue
    .line 276
    iput-object p1, p0, Lorg/apache/cordova/filetransfer/FileTransfer$1;->this$0:Lorg/apache/cordova/filetransfer/FileTransfer;

    iput-object p2, p0, Lorg/apache/cordova/filetransfer/FileTransfer$1;->val$context:Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;

    iput-object p3, p0, Lorg/apache/cordova/filetransfer/FileTransfer$1;->val$resourceApi:Lorg/apache/cordova/CordovaResourceApi;

    iput-object p4, p0, Lorg/apache/cordova/filetransfer/FileTransfer$1;->val$targetUri:Landroid/net/Uri;

    iput-boolean p5, p0, Lorg/apache/cordova/filetransfer/FileTransfer$1;->val$useHttps:Z

    iput-boolean p6, p0, Lorg/apache/cordova/filetransfer/FileTransfer$1;->val$trustEveryone:Z

    iput-object p7, p0, Lorg/apache/cordova/filetransfer/FileTransfer$1;->val$httpMethod:Ljava/lang/String;

    iput-object p8, p0, Lorg/apache/cordova/filetransfer/FileTransfer$1;->val$target:Ljava/lang/String;

    iput-object p9, p0, Lorg/apache/cordova/filetransfer/FileTransfer$1;->val$headers:Lorg/json/JSONObject;

    iput-object p10, p0, Lorg/apache/cordova/filetransfer/FileTransfer$1;->val$params:Lorg/json/JSONObject;

    iput-object p11, p0, Lorg/apache/cordova/filetransfer/FileTransfer$1;->val$fileKey:Ljava/lang/String;

    iput-object p12, p0, Lorg/apache/cordova/filetransfer/FileTransfer$1;->val$fileName:Ljava/lang/String;

    iput-object p13, p0, Lorg/apache/cordova/filetransfer/FileTransfer$1;->val$mimeType:Ljava/lang/String;

    iput-object p14, p0, Lorg/apache/cordova/filetransfer/FileTransfer$1;->val$sourceUri:Landroid/net/Uri;

    move/from16 v0, p15

    iput-boolean v0, p0, Lorg/apache/cordova/filetransfer/FileTransfer$1;->val$chunkedMode:Z

    move-object/from16 v0, p16

    iput-object v0, p0, Lorg/apache/cordova/filetransfer/FileTransfer$1;->val$source:Ljava/lang/String;

    move-object/from16 v0, p17

    iput-object v0, p0, Lorg/apache/cordova/filetransfer/FileTransfer$1;->val$objectId:Ljava/lang/String;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .locals 40

    .prologue
    .line 278
    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$1;->val$context:Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;

    move-object/from16 v36, v0

    move-object/from16 v0, v36

    iget-boolean v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;->aborted:Z

    move/from16 v36, v0

    if-eqz v36, :cond_1

    .line 508
    :cond_0
    :goto_0
    return-void

    .line 281
    :cond_1
    const/4 v10, 0x0

    .line 282
    .local v10, "conn":Ljava/net/HttpURLConnection;
    const/16 v19, 0x0

    .line 283
    .local v19, "oldHostnameVerifier":Ljavax/net/ssl/HostnameVerifier;
    const/16 v20, 0x0

    .line 284
    .local v20, "oldSocketFactory":Ljavax/net/ssl/SSLSocketFactory;
    const/16 v34, 0x0

    .line 285
    .local v34, "totalBytes":I
    const/4 v14, -0x1

    .line 288
    .local v14, "fixedLength":I
    :try_start_0
    new-instance v29, Lorg/apache/cordova/filetransfer/FileUploadResult;

    invoke-direct/range {v29 .. v29}, Lorg/apache/cordova/filetransfer/FileUploadResult;-><init>()V

    .line 289
    .local v29, "result":Lorg/apache/cordova/filetransfer/FileUploadResult;
    new-instance v24, Lorg/apache/cordova/filetransfer/FileProgressResult;

    invoke-direct/range {v24 .. v24}, Lorg/apache/cordova/filetransfer/FileProgressResult;-><init>()V

    .line 293
    .local v24, "progress":Lorg/apache/cordova/filetransfer/FileProgressResult;
    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$1;->val$resourceApi:Lorg/apache/cordova/CordovaResourceApi;

    move-object/from16 v36, v0

    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$1;->val$targetUri:Landroid/net/Uri;

    move-object/from16 v37, v0

    invoke-virtual/range {v36 .. v37}, Lorg/apache/cordova/CordovaResourceApi;->createHttpConnection(Landroid/net/Uri;)Ljava/net/HttpURLConnection;

    move-result-object v10

    .line 294
    move-object/from16 v0, p0

    iget-boolean v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$1;->val$useHttps:Z

    move/from16 v36, v0

    if-eqz v36, :cond_2

    move-object/from16 v0, p0

    iget-boolean v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$1;->val$trustEveryone:Z

    move/from16 v36, v0

    if-eqz v36, :cond_2

    .line 296
    move-object v0, v10

    check-cast v0, Ljavax/net/ssl/HttpsURLConnection;

    move-object v15, v0

    .line 297
    .local v15, "https":Ljavax/net/ssl/HttpsURLConnection;
    # invokes: Lorg/apache/cordova/filetransfer/FileTransfer;->trustAllHosts(Ljavax/net/ssl/HttpsURLConnection;)Ljavax/net/ssl/SSLSocketFactory;
    invoke-static {v15}, Lorg/apache/cordova/filetransfer/FileTransfer;->access$000(Ljavax/net/ssl/HttpsURLConnection;)Ljavax/net/ssl/SSLSocketFactory;

    move-result-object v20

    .line 299
    invoke-virtual {v15}, Ljavax/net/ssl/HttpsURLConnection;->getHostnameVerifier()Ljavax/net/ssl/HostnameVerifier;

    move-result-object v19

    .line 301
    # getter for: Lorg/apache/cordova/filetransfer/FileTransfer;->DO_NOT_VERIFY:Ljavax/net/ssl/HostnameVerifier;
    invoke-static {}, Lorg/apache/cordova/filetransfer/FileTransfer;->access$100()Ljavax/net/ssl/HostnameVerifier;

    move-result-object v36

    move-object/from16 v0, v36

    invoke-virtual {v15, v0}, Ljavax/net/ssl/HttpsURLConnection;->setHostnameVerifier(Ljavax/net/ssl/HostnameVerifier;)V

    .line 305
    .end local v15    # "https":Ljavax/net/ssl/HttpsURLConnection;
    :cond_2
    const/16 v36, 0x1

    move/from16 v0, v36

    invoke-virtual {v10, v0}, Ljava/net/HttpURLConnection;->setDoInput(Z)V

    .line 308
    const/16 v36, 0x1

    move/from16 v0, v36

    invoke-virtual {v10, v0}, Ljava/net/HttpURLConnection;->setDoOutput(Z)V

    .line 311
    const/16 v36, 0x0

    move/from16 v0, v36

    invoke-virtual {v10, v0}, Ljava/net/HttpURLConnection;->setUseCaches(Z)V

    .line 314
    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$1;->val$httpMethod:Ljava/lang/String;

    move-object/from16 v36, v0

    move-object/from16 v0, v36

    invoke-virtual {v10, v0}, Ljava/net/HttpURLConnection;->setRequestMethod(Ljava/lang/String;)V

    .line 315
    const-string v36, "Content-Type"

    const-string v37, "multipart/form-data; boundary=+++++"

    move-object/from16 v0, v36

    move-object/from16 v1, v37

    invoke-virtual {v10, v0, v1}, Ljava/net/HttpURLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    .line 318
    invoke-static {}, Landroid/webkit/CookieManager;->getInstance()Landroid/webkit/CookieManager;

    move-result-object v36

    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$1;->val$target:Ljava/lang/String;

    move-object/from16 v37, v0

    invoke-virtual/range {v36 .. v37}, Landroid/webkit/CookieManager;->getCookie(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v11

    .line 319
    .local v11, "cookie":Ljava/lang/String;
    if-eqz v11, :cond_3

    .line 320
    const-string v36, "Cookie"

    move-object/from16 v0, v36

    invoke-virtual {v10, v0, v11}, Ljava/net/HttpURLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    .line 324
    :cond_3
    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$1;->val$headers:Lorg/json/JSONObject;

    move-object/from16 v36, v0

    if-eqz v36, :cond_4

    .line 325
    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$1;->val$headers:Lorg/json/JSONObject;

    move-object/from16 v36, v0

    move-object/from16 v0, v36

    # invokes: Lorg/apache/cordova/filetransfer/FileTransfer;->addHeadersToRequest(Ljava/net/URLConnection;Lorg/json/JSONObject;)V
    invoke-static {v10, v0}, Lorg/apache/cordova/filetransfer/FileTransfer;->access$200(Ljava/net/URLConnection;Lorg/json/JSONObject;)V

    .line 332
    :cond_4
    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V
    :try_end_0
    .catch Ljava/io/FileNotFoundException; {:try_start_0 .. :try_end_0} :catch_1
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_2
    .catch Lorg/json/JSONException; {:try_start_0 .. :try_end_0} :catch_3
    .catch Ljava/lang/Throwable; {:try_start_0 .. :try_end_0} :catch_4
    .catchall {:try_start_0 .. :try_end_0} :catchall_7

    .line 334
    .local v4, "beforeData":Ljava/lang/StringBuilder;
    :try_start_1
    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$1;->val$params:Lorg/json/JSONObject;

    move-object/from16 v36, v0

    invoke-virtual/range {v36 .. v36}, Lorg/json/JSONObject;->keys()Ljava/util/Iterator;

    move-result-object v17

    .local v17, "iter":Ljava/util/Iterator;, "Ljava/util/Iterator<*>;"
    :cond_5
    :goto_1
    invoke-interface/range {v17 .. v17}, Ljava/util/Iterator;->hasNext()Z

    move-result v36

    if-eqz v36, :cond_6

    .line 335
    invoke-interface/range {v17 .. v17}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v18

    .line 336
    .local v18, "key":Ljava/lang/Object;
    invoke-static/range {v18 .. v18}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v36

    const-string v37, "headers"

    invoke-virtual/range {v36 .. v37}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v36

    if-nez v36, :cond_5

    .line 338
    const-string v36, "--"

    move-object/from16 v0, v36

    invoke-virtual {v4, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v36

    const-string v37, "+++++"

    invoke-virtual/range {v36 .. v37}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v36

    const-string v37, "\r\n"

    invoke-virtual/range {v36 .. v37}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 339
    const-string v36, "Content-Disposition: form-data; name=\""

    move-object/from16 v0, v36

    invoke-virtual {v4, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v36

    invoke-virtual/range {v18 .. v18}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v37

    invoke-virtual/range {v36 .. v37}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v36

    const/16 v37, 0x22

    invoke-virtual/range {v36 .. v37}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    .line 340
    const-string v36, "\r\n"

    move-object/from16 v0, v36

    invoke-virtual {v4, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v36

    const-string v37, "\r\n"

    invoke-virtual/range {v36 .. v37}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 341
    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$1;->val$params:Lorg/json/JSONObject;

    move-object/from16 v36, v0

    invoke-virtual/range {v18 .. v18}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v37

    invoke-virtual/range {v36 .. v37}, Lorg/json/JSONObject;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v36

    move-object/from16 v0, v36

    invoke-virtual {v4, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 342
    const-string v36, "\r\n"

    move-object/from16 v0, v36

    invoke-virtual {v4, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;
    :try_end_1
    .catch Lorg/json/JSONException; {:try_start_1 .. :try_end_1} :catch_0
    .catch Ljava/io/FileNotFoundException; {:try_start_1 .. :try_end_1} :catch_1
    .catch Ljava/io/IOException; {:try_start_1 .. :try_end_1} :catch_2
    .catch Ljava/lang/Throwable; {:try_start_1 .. :try_end_1} :catch_4
    .catchall {:try_start_1 .. :try_end_1} :catchall_7

    goto :goto_1

    .line 345
    .end local v17    # "iter":Ljava/util/Iterator;, "Ljava/util/Iterator<*>;"
    .end local v18    # "key":Ljava/lang/Object;
    :catch_0
    move-exception v12

    .line 346
    .local v12, "e":Lorg/json/JSONException;
    :try_start_2
    const-string v36, "FileTransfer"

    invoke-virtual {v12}, Lorg/json/JSONException;->getMessage()Ljava/lang/String;

    move-result-object v37

    move-object/from16 v0, v36

    move-object/from16 v1, v37

    invoke-static {v0, v1, v12}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    .line 349
    .end local v12    # "e":Lorg/json/JSONException;
    :cond_6
    const-string v36, "--"

    move-object/from16 v0, v36

    invoke-virtual {v4, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v36

    const-string v37, "+++++"

    invoke-virtual/range {v36 .. v37}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v36

    const-string v37, "\r\n"

    invoke-virtual/range {v36 .. v37}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 350
    const-string v36, "Content-Disposition: form-data; name=\""

    move-object/from16 v0, v36

    invoke-virtual {v4, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v36

    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$1;->val$fileKey:Ljava/lang/String;

    move-object/from16 v37, v0

    invoke-virtual/range {v36 .. v37}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v36

    const-string v37, "\";"

    invoke-virtual/range {v36 .. v37}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 351
    const-string v36, " filename=\""

    move-object/from16 v0, v36

    invoke-virtual {v4, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v36

    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$1;->val$fileName:Ljava/lang/String;

    move-object/from16 v37, v0

    invoke-virtual/range {v36 .. v37}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v36

    const/16 v37, 0x22

    invoke-virtual/range {v36 .. v37}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    move-result-object v36

    const-string v37, "\r\n"

    invoke-virtual/range {v36 .. v37}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 352
    const-string v36, "Content-Type: "

    move-object/from16 v0, v36

    invoke-virtual {v4, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v36

    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$1;->val$mimeType:Ljava/lang/String;

    move-object/from16 v37, v0

    invoke-virtual/range {v36 .. v37}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v36

    const-string v37, "\r\n"

    invoke-virtual/range {v36 .. v37}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v36

    const-string v37, "\r\n"

    invoke-virtual/range {v36 .. v37}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 353
    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v36

    const-string v37, "UTF-8"

    invoke-virtual/range {v36 .. v37}, Ljava/lang/String;->getBytes(Ljava/lang/String;)[B

    move-result-object v5

    .line 354
    .local v5, "beforeDataBytes":[B
    const-string v36, "\r\n--+++++--\r\n"

    const-string v37, "UTF-8"

    invoke-virtual/range {v36 .. v37}, Ljava/lang/String;->getBytes(Ljava/lang/String;)[B

    move-result-object v33

    .line 358
    .local v33, "tailParamsBytes":[B
    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$1;->val$resourceApi:Lorg/apache/cordova/CordovaResourceApi;

    move-object/from16 v36, v0

    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$1;->val$sourceUri:Landroid/net/Uri;

    move-object/from16 v37, v0

    invoke-virtual/range {v36 .. v37}, Lorg/apache/cordova/CordovaResourceApi;->openForRead(Landroid/net/Uri;)Lorg/apache/cordova/CordovaResourceApi$OpenForReadResult;

    move-result-object v26

    .line 360
    .local v26, "readResult":Lorg/apache/cordova/CordovaResourceApi$OpenForReadResult;
    array-length v0, v5

    move/from16 v36, v0

    move-object/from16 v0, v33

    array-length v0, v0

    move/from16 v37, v0

    add-int v31, v36, v37

    .line 361
    .local v31, "stringLength":I
    move-object/from16 v0, v26

    iget-wide v0, v0, Lorg/apache/cordova/CordovaResourceApi$OpenForReadResult;->length:J

    move-wide/from16 v36, v0

    const-wide/16 v38, 0x0

    cmp-long v36, v36, v38

    if-ltz v36, :cond_7

    .line 362
    move-object/from16 v0, v26

    iget-wide v0, v0, Lorg/apache/cordova/CordovaResourceApi$OpenForReadResult;->length:J

    move-wide/from16 v36, v0

    move-wide/from16 v0, v36

    long-to-int v0, v0

    move/from16 v36, v0

    add-int v14, v36, v31

    .line 363
    const/16 v36, 0x1

    move-object/from16 v0, v24

    move/from16 v1, v36

    invoke-virtual {v0, v1}, Lorg/apache/cordova/filetransfer/FileProgressResult;->setLengthComputable(Z)V

    .line 364
    int-to-long v0, v14

    move-wide/from16 v36, v0

    move-object/from16 v0, v24

    move-wide/from16 v1, v36

    invoke-virtual {v0, v1, v2}, Lorg/apache/cordova/filetransfer/FileProgressResult;->setTotal(J)V

    .line 366
    :cond_7
    const-string v36, "FileTransfer"

    new-instance v37, Ljava/lang/StringBuilder;

    invoke-direct/range {v37 .. v37}, Ljava/lang/StringBuilder;-><init>()V

    const-string v38, "Content Length: "

    invoke-virtual/range {v37 .. v38}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v37

    move-object/from16 v0, v37

    invoke-virtual {v0, v14}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v37

    invoke-virtual/range {v37 .. v37}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v37

    invoke-static/range {v36 .. v37}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 370
    move-object/from16 v0, p0

    iget-boolean v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$1;->val$chunkedMode:Z

    move/from16 v36, v0

    if-eqz v36, :cond_a

    sget v36, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v37, 0x8

    move/from16 v0, v36

    move/from16 v1, v37

    if-lt v0, v1, :cond_8

    move-object/from16 v0, p0

    iget-boolean v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$1;->val$useHttps:Z

    move/from16 v36, v0

    if-eqz v36, :cond_a

    :cond_8
    const/16 v35, 0x1

    .line 371
    .local v35, "useChunkedMode":Z
    :goto_2
    if-nez v35, :cond_9

    const/16 v36, -0x1

    move/from16 v0, v36

    if-ne v14, v0, :cond_b

    :cond_9
    const/16 v35, 0x1

    .line 373
    :goto_3
    if-eqz v35, :cond_c

    .line 374
    const/16 v36, 0x4000

    move/from16 v0, v36

    invoke-virtual {v10, v0}, Ljava/net/HttpURLConnection;->setChunkedStreamingMode(I)V

    .line 377
    const-string v36, "Transfer-Encoding"

    const-string v37, "chunked"

    move-object/from16 v0, v36

    move-object/from16 v1, v37

    invoke-virtual {v10, v0, v1}, Ljava/net/HttpURLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    .line 382
    :goto_4
    invoke-virtual {v10}, Ljava/net/HttpURLConnection;->connect()V
    :try_end_2
    .catch Ljava/io/FileNotFoundException; {:try_start_2 .. :try_end_2} :catch_1
    .catch Ljava/io/IOException; {:try_start_2 .. :try_end_2} :catch_2
    .catch Lorg/json/JSONException; {:try_start_2 .. :try_end_2} :catch_3
    .catch Ljava/lang/Throwable; {:try_start_2 .. :try_end_2} :catch_4
    .catchall {:try_start_2 .. :try_end_2} :catchall_7

    .line 384
    const/16 v30, 0x0

    .line 386
    .local v30, "sendStream":Ljava/io/OutputStream;
    :try_start_3
    invoke-virtual {v10}, Ljava/net/HttpURLConnection;->getOutputStream()Ljava/io/OutputStream;

    move-result-object v30

    .line 387
    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$1;->val$context:Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;

    move-object/from16 v37, v0

    monitor-enter v37
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_1

    .line 388
    :try_start_4
    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$1;->val$context:Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;

    move-object/from16 v36, v0

    move-object/from16 v0, v36

    iget-boolean v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;->aborted:Z

    move/from16 v36, v0

    if-eqz v36, :cond_d

    .line 389
    monitor-exit v37
    :try_end_4
    .catchall {:try_start_4 .. :try_end_4} :catchall_2

    .line 430
    :try_start_5
    move-object/from16 v0, v26

    iget-object v0, v0, Lorg/apache/cordova/CordovaResourceApi$OpenForReadResult;->inputStream:Ljava/io/InputStream;

    move-object/from16 v36, v0

    # invokes: Lorg/apache/cordova/filetransfer/FileTransfer;->safeClose(Ljava/io/Closeable;)V
    invoke-static/range {v36 .. v36}, Lorg/apache/cordova/filetransfer/FileTransfer;->access$300(Ljava/io/Closeable;)V

    .line 431
    # invokes: Lorg/apache/cordova/filetransfer/FileTransfer;->safeClose(Ljava/io/Closeable;)V
    invoke-static/range {v30 .. v30}, Lorg/apache/cordova/filetransfer/FileTransfer;->access$300(Ljava/io/Closeable;)V
    :try_end_5
    .catch Ljava/io/FileNotFoundException; {:try_start_5 .. :try_end_5} :catch_1
    .catch Ljava/io/IOException; {:try_start_5 .. :try_end_5} :catch_2
    .catch Lorg/json/JSONException; {:try_start_5 .. :try_end_5} :catch_3
    .catch Ljava/lang/Throwable; {:try_start_5 .. :try_end_5} :catch_4
    .catchall {:try_start_5 .. :try_end_5} :catchall_7

    .line 494
    # getter for: Lorg/apache/cordova/filetransfer/FileTransfer;->activeRequests:Ljava/util/HashMap;
    invoke-static {}, Lorg/apache/cordova/filetransfer/FileTransfer;->access$600()Ljava/util/HashMap;

    move-result-object v37

    monitor-enter v37

    .line 495
    :try_start_6
    # getter for: Lorg/apache/cordova/filetransfer/FileTransfer;->activeRequests:Ljava/util/HashMap;
    invoke-static {}, Lorg/apache/cordova/filetransfer/FileTransfer;->access$600()Ljava/util/HashMap;

    move-result-object v36

    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$1;->val$objectId:Ljava/lang/String;

    move-object/from16 v38, v0

    move-object/from16 v0, v36

    move-object/from16 v1, v38

    invoke-virtual {v0, v1}, Ljava/util/HashMap;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    .line 496
    monitor-exit v37
    :try_end_6
    .catchall {:try_start_6 .. :try_end_6} :catchall_0

    .line 498
    if-eqz v10, :cond_0

    .line 501
    move-object/from16 v0, p0

    iget-boolean v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$1;->val$trustEveryone:Z

    move/from16 v36, v0

    if-eqz v36, :cond_0

    move-object/from16 v0, p0

    iget-boolean v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$1;->val$useHttps:Z

    move/from16 v36, v0

    if-eqz v36, :cond_0

    move-object v15, v10

    .line 502
    check-cast v15, Ljavax/net/ssl/HttpsURLConnection;

    .line 503
    .restart local v15    # "https":Ljavax/net/ssl/HttpsURLConnection;
    move-object/from16 v0, v19

    invoke-virtual {v15, v0}, Ljavax/net/ssl/HttpsURLConnection;->setHostnameVerifier(Ljavax/net/ssl/HostnameVerifier;)V

    .line 504
    move-object/from16 v0, v20

    invoke-virtual {v15, v0}, Ljavax/net/ssl/HttpsURLConnection;->setSSLSocketFactory(Ljavax/net/ssl/SSLSocketFactory;)V

    goto/16 :goto_0

    .line 370
    .end local v15    # "https":Ljavax/net/ssl/HttpsURLConnection;
    .end local v30    # "sendStream":Ljava/io/OutputStream;
    .end local v35    # "useChunkedMode":Z
    :cond_a
    const/16 v35, 0x0

    goto/16 :goto_2

    .line 371
    .restart local v35    # "useChunkedMode":Z
    :cond_b
    const/16 v35, 0x0

    goto :goto_3

    .line 379
    :cond_c
    :try_start_7
    invoke-virtual {v10, v14}, Ljava/net/HttpURLConnection;->setFixedLengthStreamingMode(I)V
    :try_end_7
    .catch Ljava/io/FileNotFoundException; {:try_start_7 .. :try_end_7} :catch_1
    .catch Ljava/io/IOException; {:try_start_7 .. :try_end_7} :catch_2
    .catch Lorg/json/JSONException; {:try_start_7 .. :try_end_7} :catch_3
    .catch Ljava/lang/Throwable; {:try_start_7 .. :try_end_7} :catch_4
    .catchall {:try_start_7 .. :try_end_7} :catchall_7

    goto :goto_4

    .line 476
    .end local v4    # "beforeData":Ljava/lang/StringBuilder;
    .end local v5    # "beforeDataBytes":[B
    .end local v11    # "cookie":Ljava/lang/String;
    .end local v24    # "progress":Lorg/apache/cordova/filetransfer/FileProgressResult;
    .end local v26    # "readResult":Lorg/apache/cordova/CordovaResourceApi$OpenForReadResult;
    .end local v29    # "result":Lorg/apache/cordova/filetransfer/FileUploadResult;
    .end local v31    # "stringLength":I
    .end local v33    # "tailParamsBytes":[B
    .end local v35    # "useChunkedMode":Z
    :catch_1
    move-exception v12

    .line 477
    .local v12, "e":Ljava/io/FileNotFoundException;
    :try_start_8
    sget v36, Lorg/apache/cordova/filetransfer/FileTransfer;->FILE_NOT_FOUND_ERR:I

    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$1;->val$source:Ljava/lang/String;

    move-object/from16 v37, v0

    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$1;->val$target:Ljava/lang/String;

    move-object/from16 v38, v0

    move/from16 v0, v36

    move-object/from16 v1, v37

    move-object/from16 v2, v38

    # invokes: Lorg/apache/cordova/filetransfer/FileTransfer;->createFileTransferError(ILjava/lang/String;Ljava/lang/String;Ljava/net/URLConnection;Ljava/lang/Throwable;)Lorg/json/JSONObject;
    invoke-static {v0, v1, v2, v10, v12}, Lorg/apache/cordova/filetransfer/FileTransfer;->access$500(ILjava/lang/String;Ljava/lang/String;Ljava/net/URLConnection;Ljava/lang/Throwable;)Lorg/json/JSONObject;

    move-result-object v13

    .line 478
    .local v13, "error":Lorg/json/JSONObject;
    const-string v36, "FileTransfer"

    invoke-virtual {v13}, Lorg/json/JSONObject;->toString()Ljava/lang/String;

    move-result-object v37

    move-object/from16 v0, v36

    move-object/from16 v1, v37

    invoke-static {v0, v1, v12}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    .line 479
    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$1;->val$context:Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;

    move-object/from16 v36, v0

    new-instance v37, Lorg/apache/cordova/PluginResult;

    sget-object v38, Lorg/apache/cordova/PluginResult$Status;->IO_EXCEPTION:Lorg/apache/cordova/PluginResult$Status;

    move-object/from16 v0, v37

    move-object/from16 v1, v38

    invoke-direct {v0, v1, v13}, Lorg/apache/cordova/PluginResult;-><init>(Lorg/apache/cordova/PluginResult$Status;Lorg/json/JSONObject;)V

    invoke-virtual/range {v36 .. v37}, Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;->sendPluginResult(Lorg/apache/cordova/PluginResult;)V
    :try_end_8
    .catchall {:try_start_8 .. :try_end_8} :catchall_7

    .line 494
    # getter for: Lorg/apache/cordova/filetransfer/FileTransfer;->activeRequests:Ljava/util/HashMap;
    invoke-static {}, Lorg/apache/cordova/filetransfer/FileTransfer;->access$600()Ljava/util/HashMap;

    move-result-object v37

    monitor-enter v37

    .line 495
    :try_start_9
    # getter for: Lorg/apache/cordova/filetransfer/FileTransfer;->activeRequests:Ljava/util/HashMap;
    invoke-static {}, Lorg/apache/cordova/filetransfer/FileTransfer;->access$600()Ljava/util/HashMap;

    move-result-object v36

    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$1;->val$objectId:Ljava/lang/String;

    move-object/from16 v38, v0

    move-object/from16 v0, v36

    move-object/from16 v1, v38

    invoke-virtual {v0, v1}, Ljava/util/HashMap;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    .line 496
    monitor-exit v37
    :try_end_9
    .catchall {:try_start_9 .. :try_end_9} :catchall_c

    .line 498
    if-eqz v10, :cond_0

    .line 501
    move-object/from16 v0, p0

    iget-boolean v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$1;->val$trustEveryone:Z

    move/from16 v36, v0

    if-eqz v36, :cond_0

    move-object/from16 v0, p0

    iget-boolean v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$1;->val$useHttps:Z

    move/from16 v36, v0

    if-eqz v36, :cond_0

    move-object v15, v10

    .line 502
    check-cast v15, Ljavax/net/ssl/HttpsURLConnection;

    .line 503
    .restart local v15    # "https":Ljavax/net/ssl/HttpsURLConnection;
    move-object/from16 v0, v19

    invoke-virtual {v15, v0}, Ljavax/net/ssl/HttpsURLConnection;->setHostnameVerifier(Ljavax/net/ssl/HostnameVerifier;)V

    .line 504
    move-object/from16 v0, v20

    invoke-virtual {v15, v0}, Ljavax/net/ssl/HttpsURLConnection;->setSSLSocketFactory(Ljavax/net/ssl/SSLSocketFactory;)V

    goto/16 :goto_0

    .line 496
    .end local v12    # "e":Ljava/io/FileNotFoundException;
    .end local v13    # "error":Lorg/json/JSONObject;
    .end local v15    # "https":Ljavax/net/ssl/HttpsURLConnection;
    .restart local v4    # "beforeData":Ljava/lang/StringBuilder;
    .restart local v5    # "beforeDataBytes":[B
    .restart local v11    # "cookie":Ljava/lang/String;
    .restart local v24    # "progress":Lorg/apache/cordova/filetransfer/FileProgressResult;
    .restart local v26    # "readResult":Lorg/apache/cordova/CordovaResourceApi$OpenForReadResult;
    .restart local v29    # "result":Lorg/apache/cordova/filetransfer/FileUploadResult;
    .restart local v30    # "sendStream":Ljava/io/OutputStream;
    .restart local v31    # "stringLength":I
    .restart local v33    # "tailParamsBytes":[B
    .restart local v35    # "useChunkedMode":Z
    :catchall_0
    move-exception v36

    :try_start_a
    monitor-exit v37
    :try_end_a
    .catchall {:try_start_a .. :try_end_a} :catchall_0

    throw v36

    .line 391
    :cond_d
    :try_start_b
    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$1;->val$context:Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;

    move-object/from16 v36, v0

    move-object/from16 v0, v36

    iput-object v10, v0, Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;->connection:Ljava/net/HttpURLConnection;

    .line 392
    monitor-exit v37
    :try_end_b
    .catchall {:try_start_b .. :try_end_b} :catchall_2

    .line 394
    :try_start_c
    move-object/from16 v0, v30

    invoke-virtual {v0, v5}, Ljava/io/OutputStream;->write([B)V

    .line 395
    array-length v0, v5

    move/from16 v36, v0

    add-int v34, v34, v36

    .line 398
    move-object/from16 v0, v26

    iget-object v0, v0, Lorg/apache/cordova/CordovaResourceApi$OpenForReadResult;->inputStream:Ljava/io/InputStream;

    move-object/from16 v36, v0

    invoke-virtual/range {v36 .. v36}, Ljava/io/InputStream;->available()I

    move-result v8

    .line 399
    .local v8, "bytesAvailable":I
    const/16 v36, 0x4000

    move/from16 v0, v36

    invoke-static {v8, v0}, Ljava/lang/Math;->min(II)I

    move-result v7

    .line 400
    .local v7, "bufferSize":I
    new-array v6, v7, [B

    .line 403
    .local v6, "buffer":[B
    move-object/from16 v0, v26

    iget-object v0, v0, Lorg/apache/cordova/CordovaResourceApi$OpenForReadResult;->inputStream:Ljava/io/InputStream;

    move-object/from16 v36, v0

    const/16 v37, 0x0

    move-object/from16 v0, v36

    move/from16 v1, v37

    invoke-virtual {v0, v6, v1, v7}, Ljava/io/InputStream;->read([BII)I

    move-result v9

    .line 405
    .local v9, "bytesRead":I
    const-wide/16 v22, 0x0

    .line 406
    .local v22, "prevBytesRead":J
    :goto_5
    if-lez v9, :cond_f

    .line 407
    move/from16 v0, v34

    int-to-long v0, v0

    move-wide/from16 v36, v0

    move-object/from16 v0, v29

    move-wide/from16 v1, v36

    invoke-virtual {v0, v1, v2}, Lorg/apache/cordova/filetransfer/FileUploadResult;->setBytesSent(J)V

    .line 408
    const/16 v36, 0x0

    move-object/from16 v0, v30

    move/from16 v1, v36

    invoke-virtual {v0, v6, v1, v9}, Ljava/io/OutputStream;->write([BII)V

    .line 409
    add-int v34, v34, v9

    .line 410
    move/from16 v0, v34

    int-to-long v0, v0

    move-wide/from16 v36, v0

    const-wide/32 v38, 0x19000

    add-long v38, v38, v22

    cmp-long v36, v36, v38

    if-lez v36, :cond_e

    .line 411
    move/from16 v0, v34

    int-to-long v0, v0

    move-wide/from16 v22, v0

    .line 412
    const-string v36, "FileTransfer"

    new-instance v37, Ljava/lang/StringBuilder;

    invoke-direct/range {v37 .. v37}, Ljava/lang/StringBuilder;-><init>()V

    const-string v38, "Uploaded "

    invoke-virtual/range {v37 .. v38}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v37

    move-object/from16 v0, v37

    move/from16 v1, v34

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v37

    const-string v38, " of "

    invoke-virtual/range {v37 .. v38}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v37

    move-object/from16 v0, v37

    invoke-virtual {v0, v14}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v37

    const-string v38, " bytes"

    invoke-virtual/range {v37 .. v38}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v37

    invoke-virtual/range {v37 .. v37}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v37

    invoke-static/range {v36 .. v37}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 414
    :cond_e
    move-object/from16 v0, v26

    iget-object v0, v0, Lorg/apache/cordova/CordovaResourceApi$OpenForReadResult;->inputStream:Ljava/io/InputStream;

    move-object/from16 v36, v0

    invoke-virtual/range {v36 .. v36}, Ljava/io/InputStream;->available()I

    move-result v8

    .line 415
    const/16 v36, 0x4000

    move/from16 v0, v36

    invoke-static {v8, v0}, Ljava/lang/Math;->min(II)I

    move-result v7

    .line 416
    move-object/from16 v0, v26

    iget-object v0, v0, Lorg/apache/cordova/CordovaResourceApi$OpenForReadResult;->inputStream:Ljava/io/InputStream;

    move-object/from16 v36, v0

    const/16 v37, 0x0

    move-object/from16 v0, v36

    move/from16 v1, v37

    invoke-virtual {v0, v6, v1, v7}, Ljava/io/InputStream;->read([BII)I

    move-result v9

    .line 419
    move/from16 v0, v34

    int-to-long v0, v0

    move-wide/from16 v36, v0

    move-object/from16 v0, v24

    move-wide/from16 v1, v36

    invoke-virtual {v0, v1, v2}, Lorg/apache/cordova/filetransfer/FileProgressResult;->setLoaded(J)V

    .line 420
    new-instance v25, Lorg/apache/cordova/PluginResult;

    sget-object v36, Lorg/apache/cordova/PluginResult$Status;->OK:Lorg/apache/cordova/PluginResult$Status;

    invoke-virtual/range {v24 .. v24}, Lorg/apache/cordova/filetransfer/FileProgressResult;->toJSONObject()Lorg/json/JSONObject;

    move-result-object v37

    move-object/from16 v0, v25

    move-object/from16 v1, v36

    move-object/from16 v2, v37

    invoke-direct {v0, v1, v2}, Lorg/apache/cordova/PluginResult;-><init>(Lorg/apache/cordova/PluginResult$Status;Lorg/json/JSONObject;)V

    .line 421
    .local v25, "progressResult":Lorg/apache/cordova/PluginResult;
    const/16 v36, 0x1

    move-object/from16 v0, v25

    move/from16 v1, v36

    invoke-virtual {v0, v1}, Lorg/apache/cordova/PluginResult;->setKeepCallback(Z)V

    .line 422
    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$1;->val$context:Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;

    move-object/from16 v36, v0

    move-object/from16 v0, v36

    move-object/from16 v1, v25

    invoke-virtual {v0, v1}, Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;->sendPluginResult(Lorg/apache/cordova/PluginResult;)V
    :try_end_c
    .catchall {:try_start_c .. :try_end_c} :catchall_1

    goto/16 :goto_5

    .line 430
    .end local v6    # "buffer":[B
    .end local v7    # "bufferSize":I
    .end local v8    # "bytesAvailable":I
    .end local v9    # "bytesRead":I
    .end local v22    # "prevBytesRead":J
    .end local v25    # "progressResult":Lorg/apache/cordova/PluginResult;
    :catchall_1
    move-exception v36

    :try_start_d
    move-object/from16 v0, v26

    iget-object v0, v0, Lorg/apache/cordova/CordovaResourceApi$OpenForReadResult;->inputStream:Ljava/io/InputStream;

    move-object/from16 v37, v0

    # invokes: Lorg/apache/cordova/filetransfer/FileTransfer;->safeClose(Ljava/io/Closeable;)V
    invoke-static/range {v37 .. v37}, Lorg/apache/cordova/filetransfer/FileTransfer;->access$300(Ljava/io/Closeable;)V

    .line 431
    # invokes: Lorg/apache/cordova/filetransfer/FileTransfer;->safeClose(Ljava/io/Closeable;)V
    invoke-static/range {v30 .. v30}, Lorg/apache/cordova/filetransfer/FileTransfer;->access$300(Ljava/io/Closeable;)V

    throw v36
    :try_end_d
    .catch Ljava/io/FileNotFoundException; {:try_start_d .. :try_end_d} :catch_1
    .catch Ljava/io/IOException; {:try_start_d .. :try_end_d} :catch_2
    .catch Lorg/json/JSONException; {:try_start_d .. :try_end_d} :catch_3
    .catch Ljava/lang/Throwable; {:try_start_d .. :try_end_d} :catch_4
    .catchall {:try_start_d .. :try_end_d} :catchall_7

    .line 480
    .end local v4    # "beforeData":Ljava/lang/StringBuilder;
    .end local v5    # "beforeDataBytes":[B
    .end local v11    # "cookie":Ljava/lang/String;
    .end local v24    # "progress":Lorg/apache/cordova/filetransfer/FileProgressResult;
    .end local v26    # "readResult":Lorg/apache/cordova/CordovaResourceApi$OpenForReadResult;
    .end local v29    # "result":Lorg/apache/cordova/filetransfer/FileUploadResult;
    .end local v30    # "sendStream":Ljava/io/OutputStream;
    .end local v31    # "stringLength":I
    .end local v33    # "tailParamsBytes":[B
    .end local v35    # "useChunkedMode":Z
    :catch_2
    move-exception v12

    .line 481
    .local v12, "e":Ljava/io/IOException;
    :try_start_e
    sget v36, Lorg/apache/cordova/filetransfer/FileTransfer;->CONNECTION_ERR:I

    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$1;->val$source:Ljava/lang/String;

    move-object/from16 v37, v0

    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$1;->val$target:Ljava/lang/String;

    move-object/from16 v38, v0

    move/from16 v0, v36

    move-object/from16 v1, v37

    move-object/from16 v2, v38

    # invokes: Lorg/apache/cordova/filetransfer/FileTransfer;->createFileTransferError(ILjava/lang/String;Ljava/lang/String;Ljava/net/URLConnection;Ljava/lang/Throwable;)Lorg/json/JSONObject;
    invoke-static {v0, v1, v2, v10, v12}, Lorg/apache/cordova/filetransfer/FileTransfer;->access$500(ILjava/lang/String;Ljava/lang/String;Ljava/net/URLConnection;Ljava/lang/Throwable;)Lorg/json/JSONObject;

    move-result-object v13

    .line 482
    .restart local v13    # "error":Lorg/json/JSONObject;
    const-string v36, "FileTransfer"

    invoke-virtual {v13}, Lorg/json/JSONObject;->toString()Ljava/lang/String;

    move-result-object v37

    move-object/from16 v0, v36

    move-object/from16 v1, v37

    invoke-static {v0, v1, v12}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    .line 483
    const-string v36, "FileTransfer"

    new-instance v37, Ljava/lang/StringBuilder;

    invoke-direct/range {v37 .. v37}, Ljava/lang/StringBuilder;-><init>()V

    const-string v38, "Failed after uploading "

    invoke-virtual/range {v37 .. v38}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v37

    move-object/from16 v0, v37

    move/from16 v1, v34

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v37

    const-string v38, " of "

    invoke-virtual/range {v37 .. v38}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v37

    move-object/from16 v0, v37

    invoke-virtual {v0, v14}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v37

    const-string v38, " bytes."

    invoke-virtual/range {v37 .. v38}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v37

    invoke-virtual/range {v37 .. v37}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v37

    invoke-static/range {v36 .. v37}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    .line 484
    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$1;->val$context:Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;

    move-object/from16 v36, v0

    new-instance v37, Lorg/apache/cordova/PluginResult;

    sget-object v38, Lorg/apache/cordova/PluginResult$Status;->IO_EXCEPTION:Lorg/apache/cordova/PluginResult$Status;

    move-object/from16 v0, v37

    move-object/from16 v1, v38

    invoke-direct {v0, v1, v13}, Lorg/apache/cordova/PluginResult;-><init>(Lorg/apache/cordova/PluginResult$Status;Lorg/json/JSONObject;)V

    invoke-virtual/range {v36 .. v37}, Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;->sendPluginResult(Lorg/apache/cordova/PluginResult;)V
    :try_end_e
    .catchall {:try_start_e .. :try_end_e} :catchall_7

    .line 494
    # getter for: Lorg/apache/cordova/filetransfer/FileTransfer;->activeRequests:Ljava/util/HashMap;
    invoke-static {}, Lorg/apache/cordova/filetransfer/FileTransfer;->access$600()Ljava/util/HashMap;

    move-result-object v37

    monitor-enter v37

    .line 495
    :try_start_f
    # getter for: Lorg/apache/cordova/filetransfer/FileTransfer;->activeRequests:Ljava/util/HashMap;
    invoke-static {}, Lorg/apache/cordova/filetransfer/FileTransfer;->access$600()Ljava/util/HashMap;

    move-result-object v36

    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$1;->val$objectId:Ljava/lang/String;

    move-object/from16 v38, v0

    move-object/from16 v0, v36

    move-object/from16 v1, v38

    invoke-virtual {v0, v1}, Ljava/util/HashMap;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    .line 496
    monitor-exit v37
    :try_end_f
    .catchall {:try_start_f .. :try_end_f} :catchall_d

    .line 498
    if-eqz v10, :cond_0

    .line 501
    move-object/from16 v0, p0

    iget-boolean v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$1;->val$trustEveryone:Z

    move/from16 v36, v0

    if-eqz v36, :cond_0

    move-object/from16 v0, p0

    iget-boolean v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$1;->val$useHttps:Z

    move/from16 v36, v0

    if-eqz v36, :cond_0

    move-object v15, v10

    .line 502
    check-cast v15, Ljavax/net/ssl/HttpsURLConnection;

    .line 503
    .restart local v15    # "https":Ljavax/net/ssl/HttpsURLConnection;
    move-object/from16 v0, v19

    invoke-virtual {v15, v0}, Ljavax/net/ssl/HttpsURLConnection;->setHostnameVerifier(Ljavax/net/ssl/HostnameVerifier;)V

    .line 504
    move-object/from16 v0, v20

    invoke-virtual {v15, v0}, Ljavax/net/ssl/HttpsURLConnection;->setSSLSocketFactory(Ljavax/net/ssl/SSLSocketFactory;)V

    goto/16 :goto_0

    .line 392
    .end local v12    # "e":Ljava/io/IOException;
    .end local v13    # "error":Lorg/json/JSONObject;
    .end local v15    # "https":Ljavax/net/ssl/HttpsURLConnection;
    .restart local v4    # "beforeData":Ljava/lang/StringBuilder;
    .restart local v5    # "beforeDataBytes":[B
    .restart local v11    # "cookie":Ljava/lang/String;
    .restart local v24    # "progress":Lorg/apache/cordova/filetransfer/FileProgressResult;
    .restart local v26    # "readResult":Lorg/apache/cordova/CordovaResourceApi$OpenForReadResult;
    .restart local v29    # "result":Lorg/apache/cordova/filetransfer/FileUploadResult;
    .restart local v30    # "sendStream":Ljava/io/OutputStream;
    .restart local v31    # "stringLength":I
    .restart local v33    # "tailParamsBytes":[B
    .restart local v35    # "useChunkedMode":Z
    :catchall_2
    move-exception v36

    :try_start_10
    monitor-exit v37
    :try_end_10
    .catchall {:try_start_10 .. :try_end_10} :catchall_2

    :try_start_11
    throw v36

    .line 426
    .restart local v6    # "buffer":[B
    .restart local v7    # "bufferSize":I
    .restart local v8    # "bytesAvailable":I
    .restart local v9    # "bytesRead":I
    .restart local v22    # "prevBytesRead":J
    :cond_f
    move-object/from16 v0, v30

    move-object/from16 v1, v33

    invoke-virtual {v0, v1}, Ljava/io/OutputStream;->write([B)V

    .line 427
    move-object/from16 v0, v33

    array-length v0, v0

    move/from16 v36, v0

    add-int v34, v34, v36

    .line 428
    invoke-virtual/range {v30 .. v30}, Ljava/io/OutputStream;->flush()V
    :try_end_11
    .catchall {:try_start_11 .. :try_end_11} :catchall_1

    .line 430
    :try_start_12
    move-object/from16 v0, v26

    iget-object v0, v0, Lorg/apache/cordova/CordovaResourceApi$OpenForReadResult;->inputStream:Ljava/io/InputStream;

    move-object/from16 v36, v0

    # invokes: Lorg/apache/cordova/filetransfer/FileTransfer;->safeClose(Ljava/io/Closeable;)V
    invoke-static/range {v36 .. v36}, Lorg/apache/cordova/filetransfer/FileTransfer;->access$300(Ljava/io/Closeable;)V

    .line 431
    # invokes: Lorg/apache/cordova/filetransfer/FileTransfer;->safeClose(Ljava/io/Closeable;)V
    invoke-static/range {v30 .. v30}, Lorg/apache/cordova/filetransfer/FileTransfer;->access$300(Ljava/io/Closeable;)V

    .line 433
    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$1;->val$context:Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;

    move-object/from16 v37, v0

    monitor-enter v37
    :try_end_12
    .catch Ljava/io/FileNotFoundException; {:try_start_12 .. :try_end_12} :catch_1
    .catch Ljava/io/IOException; {:try_start_12 .. :try_end_12} :catch_2
    .catch Lorg/json/JSONException; {:try_start_12 .. :try_end_12} :catch_3
    .catch Ljava/lang/Throwable; {:try_start_12 .. :try_end_12} :catch_4
    .catchall {:try_start_12 .. :try_end_12} :catchall_7

    .line 434
    :try_start_13
    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$1;->val$context:Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;

    move-object/from16 v36, v0

    const/16 v38, 0x0

    move-object/from16 v0, v38

    move-object/from16 v1, v36

    iput-object v0, v1, Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;->connection:Ljava/net/HttpURLConnection;

    .line 435
    monitor-exit v37
    :try_end_13
    .catchall {:try_start_13 .. :try_end_13} :catchall_3

    .line 436
    :try_start_14
    const-string v36, "FileTransfer"

    new-instance v37, Ljava/lang/StringBuilder;

    invoke-direct/range {v37 .. v37}, Ljava/lang/StringBuilder;-><init>()V

    const-string v38, "Sent "

    invoke-virtual/range {v37 .. v38}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v37

    move-object/from16 v0, v37

    move/from16 v1, v34

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v37

    const-string v38, " of "

    invoke-virtual/range {v37 .. v38}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v37

    move-object/from16 v0, v37

    invoke-virtual {v0, v14}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v37

    invoke-virtual/range {v37 .. v37}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v37

    invoke-static/range {v36 .. v37}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 440
    invoke-virtual {v10}, Ljava/net/HttpURLConnection;->getResponseCode()I

    move-result v27

    .line 441
    .local v27, "responseCode":I
    const-string v36, "FileTransfer"

    new-instance v37, Ljava/lang/StringBuilder;

    invoke-direct/range {v37 .. v37}, Ljava/lang/StringBuilder;-><init>()V

    const-string v38, "response code: "

    invoke-virtual/range {v37 .. v38}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v37

    move-object/from16 v0, v37

    move/from16 v1, v27

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v37

    invoke-virtual/range {v37 .. v37}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v37

    invoke-static/range {v36 .. v37}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 442
    const-string v36, "FileTransfer"

    new-instance v37, Ljava/lang/StringBuilder;

    invoke-direct/range {v37 .. v37}, Ljava/lang/StringBuilder;-><init>()V

    const-string v38, "response headers: "

    invoke-virtual/range {v37 .. v38}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v37

    invoke-virtual {v10}, Ljava/net/HttpURLConnection;->getHeaderFields()Ljava/util/Map;

    move-result-object v38

    invoke-virtual/range {v37 .. v38}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    move-result-object v37

    invoke-virtual/range {v37 .. v37}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v37

    invoke-static/range {v36 .. v37}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I
    :try_end_14
    .catch Ljava/io/FileNotFoundException; {:try_start_14 .. :try_end_14} :catch_1
    .catch Ljava/io/IOException; {:try_start_14 .. :try_end_14} :catch_2
    .catch Lorg/json/JSONException; {:try_start_14 .. :try_end_14} :catch_3
    .catch Ljava/lang/Throwable; {:try_start_14 .. :try_end_14} :catch_4
    .catchall {:try_start_14 .. :try_end_14} :catchall_7

    .line 443
    const/16 v16, 0x0

    .line 445
    .local v16, "inStream":Lorg/apache/cordova/filetransfer/FileTransfer$TrackingInputStream;
    :try_start_15
    # invokes: Lorg/apache/cordova/filetransfer/FileTransfer;->getInputStream(Ljava/net/URLConnection;)Lorg/apache/cordova/filetransfer/FileTransfer$TrackingInputStream;
    invoke-static {v10}, Lorg/apache/cordova/filetransfer/FileTransfer;->access$400(Ljava/net/URLConnection;)Lorg/apache/cordova/filetransfer/FileTransfer$TrackingInputStream;

    move-result-object v16

    .line 446
    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$1;->val$context:Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;

    move-object/from16 v37, v0

    monitor-enter v37
    :try_end_15
    .catchall {:try_start_15 .. :try_end_15} :catchall_6

    .line 447
    :try_start_16
    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$1;->val$context:Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;

    move-object/from16 v36, v0

    move-object/from16 v0, v36

    iget-boolean v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;->aborted:Z

    move/from16 v36, v0

    if-eqz v36, :cond_10

    .line 448
    monitor-exit v37
    :try_end_16
    .catchall {:try_start_16 .. :try_end_16} :catchall_8

    .line 462
    :try_start_17
    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$1;->val$context:Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;

    move-object/from16 v37, v0

    monitor-enter v37
    :try_end_17
    .catch Ljava/io/FileNotFoundException; {:try_start_17 .. :try_end_17} :catch_1
    .catch Ljava/io/IOException; {:try_start_17 .. :try_end_17} :catch_2
    .catch Lorg/json/JSONException; {:try_start_17 .. :try_end_17} :catch_3
    .catch Ljava/lang/Throwable; {:try_start_17 .. :try_end_17} :catch_4
    .catchall {:try_start_17 .. :try_end_17} :catchall_7

    .line 463
    :try_start_18
    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$1;->val$context:Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;

    move-object/from16 v36, v0

    const/16 v38, 0x0

    move-object/from16 v0, v38

    move-object/from16 v1, v36

    iput-object v0, v1, Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;->connection:Ljava/net/HttpURLConnection;

    .line 464
    monitor-exit v37
    :try_end_18
    .catchall {:try_start_18 .. :try_end_18} :catchall_4

    .line 465
    :try_start_19
    # invokes: Lorg/apache/cordova/filetransfer/FileTransfer;->safeClose(Ljava/io/Closeable;)V
    invoke-static/range {v16 .. v16}, Lorg/apache/cordova/filetransfer/FileTransfer;->access$300(Ljava/io/Closeable;)V
    :try_end_19
    .catch Ljava/io/FileNotFoundException; {:try_start_19 .. :try_end_19} :catch_1
    .catch Ljava/io/IOException; {:try_start_19 .. :try_end_19} :catch_2
    .catch Lorg/json/JSONException; {:try_start_19 .. :try_end_19} :catch_3
    .catch Ljava/lang/Throwable; {:try_start_19 .. :try_end_19} :catch_4
    .catchall {:try_start_19 .. :try_end_19} :catchall_7

    .line 494
    # getter for: Lorg/apache/cordova/filetransfer/FileTransfer;->activeRequests:Ljava/util/HashMap;
    invoke-static {}, Lorg/apache/cordova/filetransfer/FileTransfer;->access$600()Ljava/util/HashMap;

    move-result-object v37

    monitor-enter v37

    .line 495
    :try_start_1a
    # getter for: Lorg/apache/cordova/filetransfer/FileTransfer;->activeRequests:Ljava/util/HashMap;
    invoke-static {}, Lorg/apache/cordova/filetransfer/FileTransfer;->access$600()Ljava/util/HashMap;

    move-result-object v36

    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$1;->val$objectId:Ljava/lang/String;

    move-object/from16 v38, v0

    move-object/from16 v0, v36

    move-object/from16 v1, v38

    invoke-virtual {v0, v1}, Ljava/util/HashMap;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    .line 496
    monitor-exit v37
    :try_end_1a
    .catchall {:try_start_1a .. :try_end_1a} :catchall_5

    .line 498
    if-eqz v10, :cond_0

    .line 501
    move-object/from16 v0, p0

    iget-boolean v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$1;->val$trustEveryone:Z

    move/from16 v36, v0

    if-eqz v36, :cond_0

    move-object/from16 v0, p0

    iget-boolean v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$1;->val$useHttps:Z

    move/from16 v36, v0

    if-eqz v36, :cond_0

    move-object v15, v10

    .line 502
    check-cast v15, Ljavax/net/ssl/HttpsURLConnection;

    .line 503
    .restart local v15    # "https":Ljavax/net/ssl/HttpsURLConnection;
    move-object/from16 v0, v19

    invoke-virtual {v15, v0}, Ljavax/net/ssl/HttpsURLConnection;->setHostnameVerifier(Ljavax/net/ssl/HostnameVerifier;)V

    .line 504
    move-object/from16 v0, v20

    invoke-virtual {v15, v0}, Ljavax/net/ssl/HttpsURLConnection;->setSSLSocketFactory(Ljavax/net/ssl/SSLSocketFactory;)V

    goto/16 :goto_0

    .line 435
    .end local v15    # "https":Ljavax/net/ssl/HttpsURLConnection;
    .end local v16    # "inStream":Lorg/apache/cordova/filetransfer/FileTransfer$TrackingInputStream;
    .end local v27    # "responseCode":I
    :catchall_3
    move-exception v36

    :try_start_1b
    monitor-exit v37
    :try_end_1b
    .catchall {:try_start_1b .. :try_end_1b} :catchall_3

    :try_start_1c
    throw v36
    :try_end_1c
    .catch Ljava/io/FileNotFoundException; {:try_start_1c .. :try_end_1c} :catch_1
    .catch Ljava/io/IOException; {:try_start_1c .. :try_end_1c} :catch_2
    .catch Lorg/json/JSONException; {:try_start_1c .. :try_end_1c} :catch_3
    .catch Ljava/lang/Throwable; {:try_start_1c .. :try_end_1c} :catch_4
    .catchall {:try_start_1c .. :try_end_1c} :catchall_7

    .line 485
    .end local v4    # "beforeData":Ljava/lang/StringBuilder;
    .end local v5    # "beforeDataBytes":[B
    .end local v6    # "buffer":[B
    .end local v7    # "bufferSize":I
    .end local v8    # "bytesAvailable":I
    .end local v9    # "bytesRead":I
    .end local v11    # "cookie":Ljava/lang/String;
    .end local v22    # "prevBytesRead":J
    .end local v24    # "progress":Lorg/apache/cordova/filetransfer/FileProgressResult;
    .end local v26    # "readResult":Lorg/apache/cordova/CordovaResourceApi$OpenForReadResult;
    .end local v29    # "result":Lorg/apache/cordova/filetransfer/FileUploadResult;
    .end local v30    # "sendStream":Ljava/io/OutputStream;
    .end local v31    # "stringLength":I
    .end local v33    # "tailParamsBytes":[B
    .end local v35    # "useChunkedMode":Z
    :catch_3
    move-exception v12

    .line 486
    .local v12, "e":Lorg/json/JSONException;
    :try_start_1d
    const-string v36, "FileTransfer"

    invoke-virtual {v12}, Lorg/json/JSONException;->getMessage()Ljava/lang/String;

    move-result-object v37

    move-object/from16 v0, v36

    move-object/from16 v1, v37

    invoke-static {v0, v1, v12}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    .line 487
    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$1;->val$context:Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;

    move-object/from16 v36, v0

    new-instance v37, Lorg/apache/cordova/PluginResult;

    sget-object v38, Lorg/apache/cordova/PluginResult$Status;->JSON_EXCEPTION:Lorg/apache/cordova/PluginResult$Status;

    invoke-direct/range {v37 .. v38}, Lorg/apache/cordova/PluginResult;-><init>(Lorg/apache/cordova/PluginResult$Status;)V

    invoke-virtual/range {v36 .. v37}, Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;->sendPluginResult(Lorg/apache/cordova/PluginResult;)V
    :try_end_1d
    .catchall {:try_start_1d .. :try_end_1d} :catchall_7

    .line 494
    # getter for: Lorg/apache/cordova/filetransfer/FileTransfer;->activeRequests:Ljava/util/HashMap;
    invoke-static {}, Lorg/apache/cordova/filetransfer/FileTransfer;->access$600()Ljava/util/HashMap;

    move-result-object v37

    monitor-enter v37

    .line 495
    :try_start_1e
    # getter for: Lorg/apache/cordova/filetransfer/FileTransfer;->activeRequests:Ljava/util/HashMap;
    invoke-static {}, Lorg/apache/cordova/filetransfer/FileTransfer;->access$600()Ljava/util/HashMap;

    move-result-object v36

    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$1;->val$objectId:Ljava/lang/String;

    move-object/from16 v38, v0

    move-object/from16 v0, v36

    move-object/from16 v1, v38

    invoke-virtual {v0, v1}, Ljava/util/HashMap;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    .line 496
    monitor-exit v37
    :try_end_1e
    .catchall {:try_start_1e .. :try_end_1e} :catchall_e

    .line 498
    if-eqz v10, :cond_0

    .line 501
    move-object/from16 v0, p0

    iget-boolean v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$1;->val$trustEveryone:Z

    move/from16 v36, v0

    if-eqz v36, :cond_0

    move-object/from16 v0, p0

    iget-boolean v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$1;->val$useHttps:Z

    move/from16 v36, v0

    if-eqz v36, :cond_0

    move-object v15, v10

    .line 502
    check-cast v15, Ljavax/net/ssl/HttpsURLConnection;

    .line 503
    .restart local v15    # "https":Ljavax/net/ssl/HttpsURLConnection;
    move-object/from16 v0, v19

    invoke-virtual {v15, v0}, Ljavax/net/ssl/HttpsURLConnection;->setHostnameVerifier(Ljavax/net/ssl/HostnameVerifier;)V

    .line 504
    move-object/from16 v0, v20

    invoke-virtual {v15, v0}, Ljavax/net/ssl/HttpsURLConnection;->setSSLSocketFactory(Ljavax/net/ssl/SSLSocketFactory;)V

    goto/16 :goto_0

    .line 464
    .end local v12    # "e":Lorg/json/JSONException;
    .end local v15    # "https":Ljavax/net/ssl/HttpsURLConnection;
    .restart local v4    # "beforeData":Ljava/lang/StringBuilder;
    .restart local v5    # "beforeDataBytes":[B
    .restart local v6    # "buffer":[B
    .restart local v7    # "bufferSize":I
    .restart local v8    # "bytesAvailable":I
    .restart local v9    # "bytesRead":I
    .restart local v11    # "cookie":Ljava/lang/String;
    .restart local v16    # "inStream":Lorg/apache/cordova/filetransfer/FileTransfer$TrackingInputStream;
    .restart local v22    # "prevBytesRead":J
    .restart local v24    # "progress":Lorg/apache/cordova/filetransfer/FileProgressResult;
    .restart local v26    # "readResult":Lorg/apache/cordova/CordovaResourceApi$OpenForReadResult;
    .restart local v27    # "responseCode":I
    .restart local v29    # "result":Lorg/apache/cordova/filetransfer/FileUploadResult;
    .restart local v30    # "sendStream":Ljava/io/OutputStream;
    .restart local v31    # "stringLength":I
    .restart local v33    # "tailParamsBytes":[B
    .restart local v35    # "useChunkedMode":Z
    :catchall_4
    move-exception v36

    :try_start_1f
    monitor-exit v37
    :try_end_1f
    .catchall {:try_start_1f .. :try_end_1f} :catchall_4

    :try_start_20
    throw v36
    :try_end_20
    .catch Ljava/io/FileNotFoundException; {:try_start_20 .. :try_end_20} :catch_1
    .catch Ljava/io/IOException; {:try_start_20 .. :try_end_20} :catch_2
    .catch Lorg/json/JSONException; {:try_start_20 .. :try_end_20} :catch_3
    .catch Ljava/lang/Throwable; {:try_start_20 .. :try_end_20} :catch_4
    .catchall {:try_start_20 .. :try_end_20} :catchall_7

    .line 488
    .end local v4    # "beforeData":Ljava/lang/StringBuilder;
    .end local v5    # "beforeDataBytes":[B
    .end local v6    # "buffer":[B
    .end local v7    # "bufferSize":I
    .end local v8    # "bytesAvailable":I
    .end local v9    # "bytesRead":I
    .end local v11    # "cookie":Ljava/lang/String;
    .end local v16    # "inStream":Lorg/apache/cordova/filetransfer/FileTransfer$TrackingInputStream;
    .end local v22    # "prevBytesRead":J
    .end local v24    # "progress":Lorg/apache/cordova/filetransfer/FileProgressResult;
    .end local v26    # "readResult":Lorg/apache/cordova/CordovaResourceApi$OpenForReadResult;
    .end local v27    # "responseCode":I
    .end local v29    # "result":Lorg/apache/cordova/filetransfer/FileUploadResult;
    .end local v30    # "sendStream":Ljava/io/OutputStream;
    .end local v31    # "stringLength":I
    .end local v33    # "tailParamsBytes":[B
    .end local v35    # "useChunkedMode":Z
    :catch_4
    move-exception v32

    .line 490
    .local v32, "t":Ljava/lang/Throwable;
    :try_start_21
    sget v36, Lorg/apache/cordova/filetransfer/FileTransfer;->CONNECTION_ERR:I

    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$1;->val$source:Ljava/lang/String;

    move-object/from16 v37, v0

    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$1;->val$target:Ljava/lang/String;

    move-object/from16 v38, v0

    move/from16 v0, v36

    move-object/from16 v1, v37

    move-object/from16 v2, v38

    move-object/from16 v3, v32

    # invokes: Lorg/apache/cordova/filetransfer/FileTransfer;->createFileTransferError(ILjava/lang/String;Ljava/lang/String;Ljava/net/URLConnection;Ljava/lang/Throwable;)Lorg/json/JSONObject;
    invoke-static {v0, v1, v2, v10, v3}, Lorg/apache/cordova/filetransfer/FileTransfer;->access$500(ILjava/lang/String;Ljava/lang/String;Ljava/net/URLConnection;Ljava/lang/Throwable;)Lorg/json/JSONObject;

    move-result-object v13

    .line 491
    .restart local v13    # "error":Lorg/json/JSONObject;
    const-string v36, "FileTransfer"

    invoke-virtual {v13}, Lorg/json/JSONObject;->toString()Ljava/lang/String;

    move-result-object v37

    move-object/from16 v0, v36

    move-object/from16 v1, v37

    move-object/from16 v2, v32

    invoke-static {v0, v1, v2}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    .line 492
    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$1;->val$context:Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;

    move-object/from16 v36, v0

    new-instance v37, Lorg/apache/cordova/PluginResult;

    sget-object v38, Lorg/apache/cordova/PluginResult$Status;->IO_EXCEPTION:Lorg/apache/cordova/PluginResult$Status;

    move-object/from16 v0, v37

    move-object/from16 v1, v38

    invoke-direct {v0, v1, v13}, Lorg/apache/cordova/PluginResult;-><init>(Lorg/apache/cordova/PluginResult$Status;Lorg/json/JSONObject;)V

    invoke-virtual/range {v36 .. v37}, Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;->sendPluginResult(Lorg/apache/cordova/PluginResult;)V
    :try_end_21
    .catchall {:try_start_21 .. :try_end_21} :catchall_7

    .line 494
    # getter for: Lorg/apache/cordova/filetransfer/FileTransfer;->activeRequests:Ljava/util/HashMap;
    invoke-static {}, Lorg/apache/cordova/filetransfer/FileTransfer;->access$600()Ljava/util/HashMap;

    move-result-object v37

    monitor-enter v37

    .line 495
    :try_start_22
    # getter for: Lorg/apache/cordova/filetransfer/FileTransfer;->activeRequests:Ljava/util/HashMap;
    invoke-static {}, Lorg/apache/cordova/filetransfer/FileTransfer;->access$600()Ljava/util/HashMap;

    move-result-object v36

    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$1;->val$objectId:Ljava/lang/String;

    move-object/from16 v38, v0

    move-object/from16 v0, v36

    move-object/from16 v1, v38

    invoke-virtual {v0, v1}, Ljava/util/HashMap;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    .line 496
    monitor-exit v37
    :try_end_22
    .catchall {:try_start_22 .. :try_end_22} :catchall_f

    .line 498
    if-eqz v10, :cond_0

    .line 501
    move-object/from16 v0, p0

    iget-boolean v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$1;->val$trustEveryone:Z

    move/from16 v36, v0

    if-eqz v36, :cond_0

    move-object/from16 v0, p0

    iget-boolean v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$1;->val$useHttps:Z

    move/from16 v36, v0

    if-eqz v36, :cond_0

    move-object v15, v10

    .line 502
    check-cast v15, Ljavax/net/ssl/HttpsURLConnection;

    .line 503
    .restart local v15    # "https":Ljavax/net/ssl/HttpsURLConnection;
    move-object/from16 v0, v19

    invoke-virtual {v15, v0}, Ljavax/net/ssl/HttpsURLConnection;->setHostnameVerifier(Ljavax/net/ssl/HostnameVerifier;)V

    .line 504
    move-object/from16 v0, v20

    invoke-virtual {v15, v0}, Ljavax/net/ssl/HttpsURLConnection;->setSSLSocketFactory(Ljavax/net/ssl/SSLSocketFactory;)V

    goto/16 :goto_0

    .line 496
    .end local v13    # "error":Lorg/json/JSONObject;
    .end local v15    # "https":Ljavax/net/ssl/HttpsURLConnection;
    .end local v32    # "t":Ljava/lang/Throwable;
    .restart local v4    # "beforeData":Ljava/lang/StringBuilder;
    .restart local v5    # "beforeDataBytes":[B
    .restart local v6    # "buffer":[B
    .restart local v7    # "bufferSize":I
    .restart local v8    # "bytesAvailable":I
    .restart local v9    # "bytesRead":I
    .restart local v11    # "cookie":Ljava/lang/String;
    .restart local v16    # "inStream":Lorg/apache/cordova/filetransfer/FileTransfer$TrackingInputStream;
    .restart local v22    # "prevBytesRead":J
    .restart local v24    # "progress":Lorg/apache/cordova/filetransfer/FileProgressResult;
    .restart local v26    # "readResult":Lorg/apache/cordova/CordovaResourceApi$OpenForReadResult;
    .restart local v27    # "responseCode":I
    .restart local v29    # "result":Lorg/apache/cordova/filetransfer/FileUploadResult;
    .restart local v30    # "sendStream":Ljava/io/OutputStream;
    .restart local v31    # "stringLength":I
    .restart local v33    # "tailParamsBytes":[B
    .restart local v35    # "useChunkedMode":Z
    :catchall_5
    move-exception v36

    :try_start_23
    monitor-exit v37
    :try_end_23
    .catchall {:try_start_23 .. :try_end_23} :catchall_5

    throw v36

    .line 450
    :cond_10
    :try_start_24
    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$1;->val$context:Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;

    move-object/from16 v36, v0

    move-object/from16 v0, v36

    iput-object v10, v0, Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;->connection:Ljava/net/HttpURLConnection;

    .line 451
    monitor-exit v37
    :try_end_24
    .catchall {:try_start_24 .. :try_end_24} :catchall_8

    .line 453
    :try_start_25
    new-instance v21, Ljava/io/ByteArrayOutputStream;

    const/16 v36, 0x400

    invoke-virtual {v10}, Ljava/net/HttpURLConnection;->getContentLength()I

    move-result v37

    invoke-static/range {v36 .. v37}, Ljava/lang/Math;->max(II)I

    move-result v36

    move-object/from16 v0, v21

    move/from16 v1, v36

    invoke-direct {v0, v1}, Ljava/io/ByteArrayOutputStream;-><init>(I)V

    .line 454
    .local v21, "out":Ljava/io/ByteArrayOutputStream;
    const/16 v36, 0x400

    move/from16 v0, v36

    new-array v6, v0, [B

    .line 455
    const/4 v9, 0x0

    .line 457
    :goto_6
    move-object/from16 v0, v16

    invoke-virtual {v0, v6}, Lorg/apache/cordova/filetransfer/FileTransfer$TrackingInputStream;->read([B)I

    move-result v9

    if-lez v9, :cond_12

    .line 458
    const/16 v36, 0x0

    move-object/from16 v0, v21

    move/from16 v1, v36

    invoke-virtual {v0, v6, v1, v9}, Ljava/io/ByteArrayOutputStream;->write([BII)V
    :try_end_25
    .catchall {:try_start_25 .. :try_end_25} :catchall_6

    goto :goto_6

    .line 462
    .end local v21    # "out":Ljava/io/ByteArrayOutputStream;
    :catchall_6
    move-exception v36

    :try_start_26
    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$1;->val$context:Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;

    move-object/from16 v37, v0

    monitor-enter v37
    :try_end_26
    .catch Ljava/io/FileNotFoundException; {:try_start_26 .. :try_end_26} :catch_1
    .catch Ljava/io/IOException; {:try_start_26 .. :try_end_26} :catch_2
    .catch Lorg/json/JSONException; {:try_start_26 .. :try_end_26} :catch_3
    .catch Ljava/lang/Throwable; {:try_start_26 .. :try_end_26} :catch_4
    .catchall {:try_start_26 .. :try_end_26} :catchall_7

    .line 463
    :try_start_27
    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$1;->val$context:Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;

    move-object/from16 v38, v0

    const/16 v39, 0x0

    move-object/from16 v0, v39

    move-object/from16 v1, v38

    iput-object v0, v1, Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;->connection:Ljava/net/HttpURLConnection;

    .line 464
    monitor-exit v37
    :try_end_27
    .catchall {:try_start_27 .. :try_end_27} :catchall_a

    .line 465
    :try_start_28
    # invokes: Lorg/apache/cordova/filetransfer/FileTransfer;->safeClose(Ljava/io/Closeable;)V
    invoke-static/range {v16 .. v16}, Lorg/apache/cordova/filetransfer/FileTransfer;->access$300(Ljava/io/Closeable;)V

    throw v36
    :try_end_28
    .catch Ljava/io/FileNotFoundException; {:try_start_28 .. :try_end_28} :catch_1
    .catch Ljava/io/IOException; {:try_start_28 .. :try_end_28} :catch_2
    .catch Lorg/json/JSONException; {:try_start_28 .. :try_end_28} :catch_3
    .catch Ljava/lang/Throwable; {:try_start_28 .. :try_end_28} :catch_4
    .catchall {:try_start_28 .. :try_end_28} :catchall_7

    .line 494
    .end local v4    # "beforeData":Ljava/lang/StringBuilder;
    .end local v5    # "beforeDataBytes":[B
    .end local v6    # "buffer":[B
    .end local v7    # "bufferSize":I
    .end local v8    # "bytesAvailable":I
    .end local v9    # "bytesRead":I
    .end local v11    # "cookie":Ljava/lang/String;
    .end local v16    # "inStream":Lorg/apache/cordova/filetransfer/FileTransfer$TrackingInputStream;
    .end local v22    # "prevBytesRead":J
    .end local v24    # "progress":Lorg/apache/cordova/filetransfer/FileProgressResult;
    .end local v26    # "readResult":Lorg/apache/cordova/CordovaResourceApi$OpenForReadResult;
    .end local v27    # "responseCode":I
    .end local v29    # "result":Lorg/apache/cordova/filetransfer/FileUploadResult;
    .end local v30    # "sendStream":Ljava/io/OutputStream;
    .end local v31    # "stringLength":I
    .end local v33    # "tailParamsBytes":[B
    .end local v35    # "useChunkedMode":Z
    :catchall_7
    move-exception v36

    # getter for: Lorg/apache/cordova/filetransfer/FileTransfer;->activeRequests:Ljava/util/HashMap;
    invoke-static {}, Lorg/apache/cordova/filetransfer/FileTransfer;->access$600()Ljava/util/HashMap;

    move-result-object v37

    monitor-enter v37

    .line 495
    :try_start_29
    # getter for: Lorg/apache/cordova/filetransfer/FileTransfer;->activeRequests:Ljava/util/HashMap;
    invoke-static {}, Lorg/apache/cordova/filetransfer/FileTransfer;->access$600()Ljava/util/HashMap;

    move-result-object v38

    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$1;->val$objectId:Ljava/lang/String;

    move-object/from16 v39, v0

    invoke-virtual/range {v38 .. v39}, Ljava/util/HashMap;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    .line 496
    monitor-exit v37
    :try_end_29
    .catchall {:try_start_29 .. :try_end_29} :catchall_10

    .line 498
    if-eqz v10, :cond_11

    .line 501
    move-object/from16 v0, p0

    iget-boolean v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$1;->val$trustEveryone:Z

    move/from16 v37, v0

    if-eqz v37, :cond_11

    move-object/from16 v0, p0

    iget-boolean v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$1;->val$useHttps:Z

    move/from16 v37, v0

    if-eqz v37, :cond_11

    move-object v15, v10

    .line 502
    check-cast v15, Ljavax/net/ssl/HttpsURLConnection;

    .line 503
    .restart local v15    # "https":Ljavax/net/ssl/HttpsURLConnection;
    move-object/from16 v0, v19

    invoke-virtual {v15, v0}, Ljavax/net/ssl/HttpsURLConnection;->setHostnameVerifier(Ljavax/net/ssl/HostnameVerifier;)V

    .line 504
    move-object/from16 v0, v20

    invoke-virtual {v15, v0}, Ljavax/net/ssl/HttpsURLConnection;->setSSLSocketFactory(Ljavax/net/ssl/SSLSocketFactory;)V

    .line 505
    .end local v15    # "https":Ljavax/net/ssl/HttpsURLConnection;
    :cond_11
    throw v36

    .line 451
    .restart local v4    # "beforeData":Ljava/lang/StringBuilder;
    .restart local v5    # "beforeDataBytes":[B
    .restart local v6    # "buffer":[B
    .restart local v7    # "bufferSize":I
    .restart local v8    # "bytesAvailable":I
    .restart local v9    # "bytesRead":I
    .restart local v11    # "cookie":Ljava/lang/String;
    .restart local v16    # "inStream":Lorg/apache/cordova/filetransfer/FileTransfer$TrackingInputStream;
    .restart local v22    # "prevBytesRead":J
    .restart local v24    # "progress":Lorg/apache/cordova/filetransfer/FileProgressResult;
    .restart local v26    # "readResult":Lorg/apache/cordova/CordovaResourceApi$OpenForReadResult;
    .restart local v27    # "responseCode":I
    .restart local v29    # "result":Lorg/apache/cordova/filetransfer/FileUploadResult;
    .restart local v30    # "sendStream":Ljava/io/OutputStream;
    .restart local v31    # "stringLength":I
    .restart local v33    # "tailParamsBytes":[B
    .restart local v35    # "useChunkedMode":Z
    :catchall_8
    move-exception v36

    :try_start_2a
    monitor-exit v37
    :try_end_2a
    .catchall {:try_start_2a .. :try_end_2a} :catchall_8

    :try_start_2b
    throw v36

    .line 460
    .restart local v21    # "out":Ljava/io/ByteArrayOutputStream;
    :cond_12
    const-string v36, "UTF-8"

    move-object/from16 v0, v21

    move-object/from16 v1, v36

    invoke-virtual {v0, v1}, Ljava/io/ByteArrayOutputStream;->toString(Ljava/lang/String;)Ljava/lang/String;
    :try_end_2b
    .catchall {:try_start_2b .. :try_end_2b} :catchall_6

    move-result-object v28

    .line 462
    .local v28, "responseString":Ljava/lang/String;
    :try_start_2c
    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$1;->val$context:Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;

    move-object/from16 v37, v0

    monitor-enter v37
    :try_end_2c
    .catch Ljava/io/FileNotFoundException; {:try_start_2c .. :try_end_2c} :catch_1
    .catch Ljava/io/IOException; {:try_start_2c .. :try_end_2c} :catch_2
    .catch Lorg/json/JSONException; {:try_start_2c .. :try_end_2c} :catch_3
    .catch Ljava/lang/Throwable; {:try_start_2c .. :try_end_2c} :catch_4
    .catchall {:try_start_2c .. :try_end_2c} :catchall_7

    .line 463
    :try_start_2d
    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$1;->val$context:Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;

    move-object/from16 v36, v0

    const/16 v38, 0x0

    move-object/from16 v0, v38

    move-object/from16 v1, v36

    iput-object v0, v1, Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;->connection:Ljava/net/HttpURLConnection;

    .line 464
    monitor-exit v37
    :try_end_2d
    .catchall {:try_start_2d .. :try_end_2d} :catchall_9

    .line 465
    :try_start_2e
    # invokes: Lorg/apache/cordova/filetransfer/FileTransfer;->safeClose(Ljava/io/Closeable;)V
    invoke-static/range {v16 .. v16}, Lorg/apache/cordova/filetransfer/FileTransfer;->access$300(Ljava/io/Closeable;)V

    .line 468
    const-string v36, "FileTransfer"

    const-string v37, "got response from server"

    invoke-static/range {v36 .. v37}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 469
    const-string v36, "FileTransfer"

    const/16 v37, 0x0

    const/16 v38, 0x100

    invoke-virtual/range {v28 .. v28}, Ljava/lang/String;->length()I

    move-result v39

    invoke-static/range {v38 .. v39}, Ljava/lang/Math;->min(II)I

    move-result v38

    move-object/from16 v0, v28

    move/from16 v1, v37

    move/from16 v2, v38

    invoke-virtual {v0, v1, v2}, Ljava/lang/String;->substring(II)Ljava/lang/String;

    move-result-object v37

    invoke-static/range {v36 .. v37}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 472
    move-object/from16 v0, v29

    move/from16 v1, v27

    invoke-virtual {v0, v1}, Lorg/apache/cordova/filetransfer/FileUploadResult;->setResponseCode(I)V

    .line 473
    move-object/from16 v0, v29

    move-object/from16 v1, v28

    invoke-virtual {v0, v1}, Lorg/apache/cordova/filetransfer/FileUploadResult;->setResponse(Ljava/lang/String;)V

    .line 475
    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$1;->val$context:Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;

    move-object/from16 v36, v0

    new-instance v37, Lorg/apache/cordova/PluginResult;

    sget-object v38, Lorg/apache/cordova/PluginResult$Status;->OK:Lorg/apache/cordova/PluginResult$Status;

    invoke-virtual/range {v29 .. v29}, Lorg/apache/cordova/filetransfer/FileUploadResult;->toJSONObject()Lorg/json/JSONObject;

    move-result-object v39

    invoke-direct/range {v37 .. v39}, Lorg/apache/cordova/PluginResult;-><init>(Lorg/apache/cordova/PluginResult$Status;Lorg/json/JSONObject;)V

    invoke-virtual/range {v36 .. v37}, Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;->sendPluginResult(Lorg/apache/cordova/PluginResult;)V
    :try_end_2e
    .catch Ljava/io/FileNotFoundException; {:try_start_2e .. :try_end_2e} :catch_1
    .catch Ljava/io/IOException; {:try_start_2e .. :try_end_2e} :catch_2
    .catch Lorg/json/JSONException; {:try_start_2e .. :try_end_2e} :catch_3
    .catch Ljava/lang/Throwable; {:try_start_2e .. :try_end_2e} :catch_4
    .catchall {:try_start_2e .. :try_end_2e} :catchall_7

    .line 494
    # getter for: Lorg/apache/cordova/filetransfer/FileTransfer;->activeRequests:Ljava/util/HashMap;
    invoke-static {}, Lorg/apache/cordova/filetransfer/FileTransfer;->access$600()Ljava/util/HashMap;

    move-result-object v37

    monitor-enter v37

    .line 495
    :try_start_2f
    # getter for: Lorg/apache/cordova/filetransfer/FileTransfer;->activeRequests:Ljava/util/HashMap;
    invoke-static {}, Lorg/apache/cordova/filetransfer/FileTransfer;->access$600()Ljava/util/HashMap;

    move-result-object v36

    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$1;->val$objectId:Ljava/lang/String;

    move-object/from16 v38, v0

    move-object/from16 v0, v36

    move-object/from16 v1, v38

    invoke-virtual {v0, v1}, Ljava/util/HashMap;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    .line 496
    monitor-exit v37
    :try_end_2f
    .catchall {:try_start_2f .. :try_end_2f} :catchall_b

    .line 498
    if-eqz v10, :cond_0

    .line 501
    move-object/from16 v0, p0

    iget-boolean v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$1;->val$trustEveryone:Z

    move/from16 v36, v0

    if-eqz v36, :cond_0

    move-object/from16 v0, p0

    iget-boolean v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$1;->val$useHttps:Z

    move/from16 v36, v0

    if-eqz v36, :cond_0

    move-object v15, v10

    .line 502
    check-cast v15, Ljavax/net/ssl/HttpsURLConnection;

    .line 503
    .restart local v15    # "https":Ljavax/net/ssl/HttpsURLConnection;
    move-object/from16 v0, v19

    invoke-virtual {v15, v0}, Ljavax/net/ssl/HttpsURLConnection;->setHostnameVerifier(Ljavax/net/ssl/HostnameVerifier;)V

    .line 504
    move-object/from16 v0, v20

    invoke-virtual {v15, v0}, Ljavax/net/ssl/HttpsURLConnection;->setSSLSocketFactory(Ljavax/net/ssl/SSLSocketFactory;)V

    goto/16 :goto_0

    .line 464
    .end local v15    # "https":Ljavax/net/ssl/HttpsURLConnection;
    :catchall_9
    move-exception v36

    :try_start_30
    monitor-exit v37
    :try_end_30
    .catchall {:try_start_30 .. :try_end_30} :catchall_9

    :try_start_31
    throw v36
    :try_end_31
    .catch Ljava/io/FileNotFoundException; {:try_start_31 .. :try_end_31} :catch_1
    .catch Ljava/io/IOException; {:try_start_31 .. :try_end_31} :catch_2
    .catch Lorg/json/JSONException; {:try_start_31 .. :try_end_31} :catch_3
    .catch Ljava/lang/Throwable; {:try_start_31 .. :try_end_31} :catch_4
    .catchall {:try_start_31 .. :try_end_31} :catchall_7

    .end local v21    # "out":Ljava/io/ByteArrayOutputStream;
    .end local v28    # "responseString":Ljava/lang/String;
    :catchall_a
    move-exception v36

    :try_start_32
    monitor-exit v37
    :try_end_32
    .catchall {:try_start_32 .. :try_end_32} :catchall_a

    :try_start_33
    throw v36
    :try_end_33
    .catch Ljava/io/FileNotFoundException; {:try_start_33 .. :try_end_33} :catch_1
    .catch Ljava/io/IOException; {:try_start_33 .. :try_end_33} :catch_2
    .catch Lorg/json/JSONException; {:try_start_33 .. :try_end_33} :catch_3
    .catch Ljava/lang/Throwable; {:try_start_33 .. :try_end_33} :catch_4
    .catchall {:try_start_33 .. :try_end_33} :catchall_7

    .line 496
    .restart local v21    # "out":Ljava/io/ByteArrayOutputStream;
    .restart local v28    # "responseString":Ljava/lang/String;
    :catchall_b
    move-exception v36

    :try_start_34
    monitor-exit v37
    :try_end_34
    .catchall {:try_start_34 .. :try_end_34} :catchall_b

    throw v36

    .end local v4    # "beforeData":Ljava/lang/StringBuilder;
    .end local v5    # "beforeDataBytes":[B
    .end local v6    # "buffer":[B
    .end local v7    # "bufferSize":I
    .end local v8    # "bytesAvailable":I
    .end local v9    # "bytesRead":I
    .end local v11    # "cookie":Ljava/lang/String;
    .end local v16    # "inStream":Lorg/apache/cordova/filetransfer/FileTransfer$TrackingInputStream;
    .end local v21    # "out":Ljava/io/ByteArrayOutputStream;
    .end local v22    # "prevBytesRead":J
    .end local v24    # "progress":Lorg/apache/cordova/filetransfer/FileProgressResult;
    .end local v26    # "readResult":Lorg/apache/cordova/CordovaResourceApi$OpenForReadResult;
    .end local v27    # "responseCode":I
    .end local v28    # "responseString":Ljava/lang/String;
    .end local v29    # "result":Lorg/apache/cordova/filetransfer/FileUploadResult;
    .end local v30    # "sendStream":Ljava/io/OutputStream;
    .end local v31    # "stringLength":I
    .end local v33    # "tailParamsBytes":[B
    .end local v35    # "useChunkedMode":Z
    .local v12, "e":Ljava/io/FileNotFoundException;
    .restart local v13    # "error":Lorg/json/JSONObject;
    :catchall_c
    move-exception v36

    :try_start_35
    monitor-exit v37
    :try_end_35
    .catchall {:try_start_35 .. :try_end_35} :catchall_c

    throw v36

    .local v12, "e":Ljava/io/IOException;
    :catchall_d
    move-exception v36

    :try_start_36
    monitor-exit v37
    :try_end_36
    .catchall {:try_start_36 .. :try_end_36} :catchall_d

    throw v36

    .end local v13    # "error":Lorg/json/JSONObject;
    .local v12, "e":Lorg/json/JSONException;
    :catchall_e
    move-exception v36

    :try_start_37
    monitor-exit v37
    :try_end_37
    .catchall {:try_start_37 .. :try_end_37} :catchall_e

    throw v36

    .end local v12    # "e":Lorg/json/JSONException;
    .restart local v13    # "error":Lorg/json/JSONObject;
    .restart local v32    # "t":Ljava/lang/Throwable;
    :catchall_f
    move-exception v36

    :try_start_38
    monitor-exit v37
    :try_end_38
    .catchall {:try_start_38 .. :try_end_38} :catchall_f

    throw v36

    .end local v13    # "error":Lorg/json/JSONObject;
    .end local v32    # "t":Ljava/lang/Throwable;
    :catchall_10
    move-exception v36

    :try_start_39
    monitor-exit v37
    :try_end_39
    .catchall {:try_start_39 .. :try_end_39} :catchall_10

    throw v36
.end method
