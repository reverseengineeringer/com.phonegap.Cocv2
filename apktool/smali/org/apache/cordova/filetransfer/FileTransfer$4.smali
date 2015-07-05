.class Lorg/apache/cordova/filetransfer/FileTransfer$4;
.super Ljava/lang/Object;
.source "FileTransfer.java"

# interfaces
.implements Ljava/lang/Runnable;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lorg/apache/cordova/filetransfer/FileTransfer;->download(Ljava/lang/String;Ljava/lang/String;Lorg/json/JSONArray;Lorg/apache/cordova/CallbackContext;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lorg/apache/cordova/filetransfer/FileTransfer;

.field final synthetic val$context:Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;

.field final synthetic val$headers:Lorg/json/JSONObject;

.field final synthetic val$isLocalTransfer:Z

.field final synthetic val$objectId:Ljava/lang/String;

.field final synthetic val$resourceApi:Lorg/apache/cordova/CordovaResourceApi;

.field final synthetic val$source:Ljava/lang/String;

.field final synthetic val$sourceUri:Landroid/net/Uri;

.field final synthetic val$target:Ljava/lang/String;

.field final synthetic val$targetUri:Landroid/net/Uri;

.field final synthetic val$trustEveryone:Z

.field final synthetic val$useHttps:Z


# direct methods
.method constructor <init>(Lorg/apache/cordova/filetransfer/FileTransfer;Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;Lorg/apache/cordova/CordovaResourceApi;Landroid/net/Uri;Landroid/net/Uri;ZZZLorg/json/JSONObject;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    .locals 0

    .prologue
    .line 705
    iput-object p1, p0, Lorg/apache/cordova/filetransfer/FileTransfer$4;->this$0:Lorg/apache/cordova/filetransfer/FileTransfer;

    iput-object p2, p0, Lorg/apache/cordova/filetransfer/FileTransfer$4;->val$context:Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;

    iput-object p3, p0, Lorg/apache/cordova/filetransfer/FileTransfer$4;->val$resourceApi:Lorg/apache/cordova/CordovaResourceApi;

    iput-object p4, p0, Lorg/apache/cordova/filetransfer/FileTransfer$4;->val$targetUri:Landroid/net/Uri;

    iput-object p5, p0, Lorg/apache/cordova/filetransfer/FileTransfer$4;->val$sourceUri:Landroid/net/Uri;

    iput-boolean p6, p0, Lorg/apache/cordova/filetransfer/FileTransfer$4;->val$isLocalTransfer:Z

    iput-boolean p7, p0, Lorg/apache/cordova/filetransfer/FileTransfer$4;->val$useHttps:Z

    iput-boolean p8, p0, Lorg/apache/cordova/filetransfer/FileTransfer$4;->val$trustEveryone:Z

    iput-object p9, p0, Lorg/apache/cordova/filetransfer/FileTransfer$4;->val$headers:Lorg/json/JSONObject;

    iput-object p10, p0, Lorg/apache/cordova/filetransfer/FileTransfer$4;->val$source:Ljava/lang/String;

    iput-object p11, p0, Lorg/apache/cordova/filetransfer/FileTransfer$4;->val$target:Ljava/lang/String;

    iput-object p12, p0, Lorg/apache/cordova/filetransfer/FileTransfer$4;->val$objectId:Ljava/lang/String;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .locals 35

    .prologue
    .line 707
    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$4;->val$context:Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;

    move-object/from16 v29, v0

    move-object/from16 v0, v29

    iget-boolean v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;->aborted:Z

    move/from16 v29, v0

    if-eqz v29, :cond_0

    .line 893
    :goto_0
    return-void

    .line 710
    :cond_0
    const/4 v7, 0x0

    .line 711
    .local v7, "connection":Ljava/net/HttpURLConnection;
    const/16 v18, 0x0

    .line 712
    .local v18, "oldHostnameVerifier":Ljavax/net/ssl/HostnameVerifier;
    const/16 v19, 0x0

    .line 713
    .local v19, "oldSocketFactory":Ljavax/net/ssl/SSLSocketFactory;
    const/4 v11, 0x0

    .line 714
    .local v11, "file":Ljava/io/File;
    const/16 v26, 0x0

    .line 715
    .local v26, "result":Lorg/apache/cordova/PluginResult;
    const/16 v16, 0x0

    .line 716
    .local v16, "inputStream":Lorg/apache/cordova/filetransfer/FileTransfer$TrackingInputStream;
    const/4 v6, 0x0

    .line 718
    .local v6, "cached":Z
    const/16 v20, 0x0

    .line 720
    .local v20, "outputStream":Ljava/io/OutputStream;
    const/16 v25, 0x0

    .line 722
    .local v25, "readResult":Lorg/apache/cordova/CordovaResourceApi$OpenForReadResult;
    :try_start_0
    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$4;->val$resourceApi:Lorg/apache/cordova/CordovaResourceApi;

    move-object/from16 v29, v0

    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$4;->val$targetUri:Landroid/net/Uri;

    move-object/from16 v30, v0

    invoke-virtual/range {v29 .. v30}, Lorg/apache/cordova/CordovaResourceApi;->mapUriToFile(Landroid/net/Uri;)Ljava/io/File;

    move-result-object v11

    .line 723
    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$4;->val$context:Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;

    move-object/from16 v29, v0

    move-object/from16 v0, v29

    iput-object v11, v0, Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;->targetFile:Ljava/io/File;

    .line 725
    const-string v29, "FileTransfer"

    new-instance v30, Ljava/lang/StringBuilder;

    invoke-direct/range {v30 .. v30}, Ljava/lang/StringBuilder;-><init>()V

    const-string v31, "Download file:"

    invoke-virtual/range {v30 .. v31}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v30

    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$4;->val$sourceUri:Landroid/net/Uri;

    move-object/from16 v31, v0

    invoke-virtual/range {v30 .. v31}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    move-result-object v30

    invoke-virtual/range {v30 .. v30}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v30

    invoke-static/range {v29 .. v30}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 727
    new-instance v23, Lorg/apache/cordova/filetransfer/FileProgressResult;

    invoke-direct/range {v23 .. v23}, Lorg/apache/cordova/filetransfer/FileProgressResult;-><init>()V

    .line 729
    .local v23, "progress":Lorg/apache/cordova/filetransfer/FileProgressResult;
    move-object/from16 v0, p0

    iget-boolean v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$4;->val$isLocalTransfer:Z

    move/from16 v29, v0

    if-eqz v29, :cond_4

    .line 730
    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$4;->val$resourceApi:Lorg/apache/cordova/CordovaResourceApi;

    move-object/from16 v29, v0

    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$4;->val$sourceUri:Landroid/net/Uri;

    move-object/from16 v30, v0

    invoke-virtual/range {v29 .. v30}, Lorg/apache/cordova/CordovaResourceApi;->openForRead(Landroid/net/Uri;)Lorg/apache/cordova/CordovaResourceApi$OpenForReadResult;

    move-result-object v25

    .line 731
    move-object/from16 v0, v25

    iget-wide v0, v0, Lorg/apache/cordova/CordovaResourceApi$OpenForReadResult;->length:J

    move-wide/from16 v30, v0

    const-wide/16 v32, -0x1

    cmp-long v29, v30, v32

    if-eqz v29, :cond_1

    .line 732
    const/16 v29, 0x1

    move-object/from16 v0, v23

    move/from16 v1, v29

    invoke-virtual {v0, v1}, Lorg/apache/cordova/filetransfer/FileProgressResult;->setLengthComputable(Z)V

    .line 733
    move-object/from16 v0, v25

    iget-wide v0, v0, Lorg/apache/cordova/CordovaResourceApi$OpenForReadResult;->length:J

    move-wide/from16 v30, v0

    move-object/from16 v0, v23

    move-wide/from16 v1, v30

    invoke-virtual {v0, v1, v2}, Lorg/apache/cordova/filetransfer/FileProgressResult;->setTotal(J)V

    .line 735
    :cond_1
    new-instance v17, Lorg/apache/cordova/filetransfer/FileTransfer$SimpleTrackingInputStream;

    move-object/from16 v0, v25

    iget-object v0, v0, Lorg/apache/cordova/CordovaResourceApi$OpenForReadResult;->inputStream:Ljava/io/InputStream;

    move-object/from16 v29, v0

    move-object/from16 v0, v17

    move-object/from16 v1, v29

    invoke-direct {v0, v1}, Lorg/apache/cordova/filetransfer/FileTransfer$SimpleTrackingInputStream;-><init>(Ljava/io/InputStream;)V
    :try_end_0
    .catch Ljava/io/FileNotFoundException; {:try_start_0 .. :try_end_0} :catch_7
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_1
    .catch Lorg/json/JSONException; {:try_start_0 .. :try_end_0} :catch_2
    .catch Ljava/lang/Throwable; {:try_start_0 .. :try_end_0} :catch_3
    .catchall {:try_start_0 .. :try_end_0} :catchall_2

    .end local v16    # "inputStream":Lorg/apache/cordova/filetransfer/FileTransfer$TrackingInputStream;
    .local v17, "inputStream":Lorg/apache/cordova/filetransfer/FileTransfer$TrackingInputStream;
    move-object/from16 v16, v17

    .end local v17    # "inputStream":Lorg/apache/cordova/filetransfer/FileTransfer$TrackingInputStream;
    .restart local v16    # "inputStream":Lorg/apache/cordova/filetransfer/FileTransfer$TrackingInputStream;
    move-object/from16 v27, v26

    .line 787
    .end local v26    # "result":Lorg/apache/cordova/PluginResult;
    .local v27, "result":Lorg/apache/cordova/PluginResult;
    :goto_1
    if-nez v6, :cond_23

    .line 789
    :try_start_1
    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$4;->val$context:Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;

    move-object/from16 v30, v0

    monitor-enter v30
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    .line 790
    :try_start_2
    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$4;->val$context:Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;

    move-object/from16 v29, v0

    move-object/from16 v0, v29

    iget-boolean v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;->aborted:Z

    move/from16 v29, v0

    if-eqz v29, :cond_b

    .line 791
    monitor-exit v30
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_1

    .line 809
    :try_start_3
    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$4;->val$context:Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;

    move-object/from16 v30, v0

    monitor-enter v30
    :try_end_3
    .catch Ljava/io/FileNotFoundException; {:try_start_3 .. :try_end_3} :catch_0
    .catch Ljava/io/IOException; {:try_start_3 .. :try_end_3} :catch_4
    .catch Lorg/json/JSONException; {:try_start_3 .. :try_end_3} :catch_5
    .catch Ljava/lang/Throwable; {:try_start_3 .. :try_end_3} :catch_6
    .catchall {:try_start_3 .. :try_end_3} :catchall_d

    .line 810
    :try_start_4
    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$4;->val$context:Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;

    move-object/from16 v29, v0

    const/16 v31, 0x0

    move-object/from16 v0, v31

    move-object/from16 v1, v29

    iput-object v0, v1, Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;->connection:Ljava/net/HttpURLConnection;

    .line 811
    monitor-exit v30
    :try_end_4
    .catchall {:try_start_4 .. :try_end_4} :catchall_9

    .line 812
    :try_start_5
    # invokes: Lorg/apache/cordova/filetransfer/FileTransfer;->safeClose(Ljava/io/Closeable;)V
    invoke-static/range {v16 .. v16}, Lorg/apache/cordova/filetransfer/FileTransfer;->access$300(Ljava/io/Closeable;)V

    .line 813
    # invokes: Lorg/apache/cordova/filetransfer/FileTransfer;->safeClose(Ljava/io/Closeable;)V
    invoke-static/range {v20 .. v20}, Lorg/apache/cordova/filetransfer/FileTransfer;->access$300(Ljava/io/Closeable;)V
    :try_end_5
    .catch Ljava/io/FileNotFoundException; {:try_start_5 .. :try_end_5} :catch_0
    .catch Ljava/io/IOException; {:try_start_5 .. :try_end_5} :catch_4
    .catch Lorg/json/JSONException; {:try_start_5 .. :try_end_5} :catch_5
    .catch Ljava/lang/Throwable; {:try_start_5 .. :try_end_5} :catch_6
    .catchall {:try_start_5 .. :try_end_5} :catchall_d

    .line 871
    # getter for: Lorg/apache/cordova/filetransfer/FileTransfer;->activeRequests:Ljava/util/HashMap;
    invoke-static {}, Lorg/apache/cordova/filetransfer/FileTransfer;->access$600()Ljava/util/HashMap;

    move-result-object v30

    monitor-enter v30

    .line 872
    :try_start_6
    # getter for: Lorg/apache/cordova/filetransfer/FileTransfer;->activeRequests:Ljava/util/HashMap;
    invoke-static {}, Lorg/apache/cordova/filetransfer/FileTransfer;->access$600()Ljava/util/HashMap;

    move-result-object v29

    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$4;->val$objectId:Ljava/lang/String;

    move-object/from16 v31, v0

    move-object/from16 v0, v29

    move-object/from16 v1, v31

    invoke-virtual {v0, v1}, Ljava/util/HashMap;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    .line 873
    monitor-exit v30
    :try_end_6
    .catchall {:try_start_6 .. :try_end_6} :catchall_a

    .line 875
    if-eqz v7, :cond_2

    .line 877
    move-object/from16 v0, p0

    iget-boolean v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$4;->val$trustEveryone:Z

    move/from16 v29, v0

    if-eqz v29, :cond_2

    move-object/from16 v0, p0

    iget-boolean v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$4;->val$useHttps:Z

    move/from16 v29, v0

    if-eqz v29, :cond_2

    move-object v15, v7

    .line 878
    check-cast v15, Ljavax/net/ssl/HttpsURLConnection;

    .line 879
    .local v15, "https":Ljavax/net/ssl/HttpsURLConnection;
    move-object/from16 v0, v18

    invoke-virtual {v15, v0}, Ljavax/net/ssl/HttpsURLConnection;->setHostnameVerifier(Ljavax/net/ssl/HostnameVerifier;)V

    .line 880
    move-object/from16 v0, v19

    invoke-virtual {v15, v0}, Ljavax/net/ssl/HttpsURLConnection;->setSSLSocketFactory(Ljavax/net/ssl/SSLSocketFactory;)V

    .line 884
    .end local v15    # "https":Ljavax/net/ssl/HttpsURLConnection;
    :cond_2
    if-nez v27, :cond_22

    .line 885
    new-instance v26, Lorg/apache/cordova/PluginResult;

    sget-object v29, Lorg/apache/cordova/PluginResult$Status;->ERROR:Lorg/apache/cordova/PluginResult$Status;

    sget v30, Lorg/apache/cordova/filetransfer/FileTransfer;->CONNECTION_ERR:I

    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$4;->val$source:Ljava/lang/String;

    move-object/from16 v31, v0

    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$4;->val$target:Ljava/lang/String;

    move-object/from16 v32, v0

    const/16 v33, 0x0

    move/from16 v0, v30

    move-object/from16 v1, v31

    move-object/from16 v2, v32

    move-object/from16 v3, v33

    # invokes: Lorg/apache/cordova/filetransfer/FileTransfer;->createFileTransferError(ILjava/lang/String;Ljava/lang/String;Ljava/net/URLConnection;Ljava/lang/Throwable;)Lorg/json/JSONObject;
    invoke-static {v0, v1, v2, v7, v3}, Lorg/apache/cordova/filetransfer/FileTransfer;->access$500(ILjava/lang/String;Ljava/lang/String;Ljava/net/URLConnection;Ljava/lang/Throwable;)Lorg/json/JSONObject;

    move-result-object v30

    move-object/from16 v0, v26

    move-object/from16 v1, v29

    move-object/from16 v2, v30

    invoke-direct {v0, v1, v2}, Lorg/apache/cordova/PluginResult;-><init>(Lorg/apache/cordova/PluginResult$Status;Lorg/json/JSONObject;)V

    .line 888
    .end local v27    # "result":Lorg/apache/cordova/PluginResult;
    .restart local v26    # "result":Lorg/apache/cordova/PluginResult;
    :goto_2
    if-nez v6, :cond_3

    invoke-virtual/range {v26 .. v26}, Lorg/apache/cordova/PluginResult;->getStatus()I

    move-result v29

    sget-object v30, Lorg/apache/cordova/PluginResult$Status;->OK:Lorg/apache/cordova/PluginResult$Status;

    invoke-virtual/range {v30 .. v30}, Lorg/apache/cordova/PluginResult$Status;->ordinal()I

    move-result v30

    move/from16 v0, v29

    move/from16 v1, v30

    if-eq v0, v1, :cond_3

    if-eqz v11, :cond_3

    .line 889
    invoke-virtual {v11}, Ljava/io/File;->delete()Z

    .line 891
    :cond_3
    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$4;->val$context:Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;

    move-object/from16 v29, v0

    .end local v23    # "progress":Lorg/apache/cordova/filetransfer/FileProgressResult;
    :goto_3
    move-object/from16 v0, v29

    move-object/from16 v1, v26

    invoke-virtual {v0, v1}, Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;->sendPluginResult(Lorg/apache/cordova/PluginResult;)V

    goto/16 :goto_0

    .line 739
    .restart local v23    # "progress":Lorg/apache/cordova/filetransfer/FileProgressResult;
    :cond_4
    :try_start_7
    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$4;->val$resourceApi:Lorg/apache/cordova/CordovaResourceApi;

    move-object/from16 v29, v0

    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$4;->val$sourceUri:Landroid/net/Uri;

    move-object/from16 v30, v0

    invoke-virtual/range {v29 .. v30}, Lorg/apache/cordova/CordovaResourceApi;->createHttpConnection(Landroid/net/Uri;)Ljava/net/HttpURLConnection;

    move-result-object v7

    .line 740
    move-object/from16 v0, p0

    iget-boolean v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$4;->val$useHttps:Z

    move/from16 v29, v0

    if-eqz v29, :cond_5

    move-object/from16 v0, p0

    iget-boolean v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$4;->val$trustEveryone:Z

    move/from16 v29, v0

    if-eqz v29, :cond_5

    .line 742
    move-object v0, v7

    check-cast v0, Ljavax/net/ssl/HttpsURLConnection;

    move-object v15, v0

    .line 743
    .restart local v15    # "https":Ljavax/net/ssl/HttpsURLConnection;
    # invokes: Lorg/apache/cordova/filetransfer/FileTransfer;->trustAllHosts(Ljavax/net/ssl/HttpsURLConnection;)Ljavax/net/ssl/SSLSocketFactory;
    invoke-static {v15}, Lorg/apache/cordova/filetransfer/FileTransfer;->access$000(Ljavax/net/ssl/HttpsURLConnection;)Ljavax/net/ssl/SSLSocketFactory;

    move-result-object v19

    .line 745
    invoke-virtual {v15}, Ljavax/net/ssl/HttpsURLConnection;->getHostnameVerifier()Ljavax/net/ssl/HostnameVerifier;

    move-result-object v18

    .line 747
    # getter for: Lorg/apache/cordova/filetransfer/FileTransfer;->DO_NOT_VERIFY:Ljavax/net/ssl/HostnameVerifier;
    invoke-static {}, Lorg/apache/cordova/filetransfer/FileTransfer;->access$100()Ljavax/net/ssl/HostnameVerifier;

    move-result-object v29

    move-object/from16 v0, v29

    invoke-virtual {v15, v0}, Ljavax/net/ssl/HttpsURLConnection;->setHostnameVerifier(Ljavax/net/ssl/HostnameVerifier;)V

    .line 750
    .end local v15    # "https":Ljavax/net/ssl/HttpsURLConnection;
    :cond_5
    const-string v29, "GET"

    move-object/from16 v0, v29

    invoke-virtual {v7, v0}, Ljava/net/HttpURLConnection;->setRequestMethod(Ljava/lang/String;)V

    .line 753
    invoke-static {}, Landroid/webkit/CookieManager;->getInstance()Landroid/webkit/CookieManager;

    move-result-object v29

    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$4;->val$sourceUri:Landroid/net/Uri;

    move-object/from16 v30, v0

    invoke-virtual/range {v30 .. v30}, Landroid/net/Uri;->toString()Ljava/lang/String;

    move-result-object v30

    invoke-virtual/range {v29 .. v30}, Landroid/webkit/CookieManager;->getCookie(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v8

    .line 754
    .local v8, "cookie":Ljava/lang/String;
    if-eqz v8, :cond_6

    .line 756
    const-string v29, "cookie"

    move-object/from16 v0, v29

    invoke-virtual {v7, v0, v8}, Ljava/net/HttpURLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    .line 760
    :cond_6
    const-string v29, "Accept-Encoding"

    const-string v30, "gzip"

    move-object/from16 v0, v29

    move-object/from16 v1, v30

    invoke-virtual {v7, v0, v1}, Ljava/net/HttpURLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    .line 763
    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$4;->val$headers:Lorg/json/JSONObject;

    move-object/from16 v29, v0

    if-eqz v29, :cond_7

    .line 764
    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$4;->val$headers:Lorg/json/JSONObject;

    move-object/from16 v29, v0

    move-object/from16 v0, v29

    # invokes: Lorg/apache/cordova/filetransfer/FileTransfer;->addHeadersToRequest(Ljava/net/URLConnection;Lorg/json/JSONObject;)V
    invoke-static {v7, v0}, Lorg/apache/cordova/filetransfer/FileTransfer;->access$200(Ljava/net/URLConnection;Lorg/json/JSONObject;)V

    .line 767
    :cond_7
    invoke-virtual {v7}, Ljava/net/HttpURLConnection;->connect()V

    .line 768
    invoke-virtual {v7}, Ljava/net/HttpURLConnection;->getResponseCode()I

    move-result v29

    const/16 v30, 0x130

    move/from16 v0, v29

    move/from16 v1, v30

    if-ne v0, v1, :cond_8

    .line 769
    const/4 v6, 0x1

    .line 770
    invoke-virtual {v7}, Ljava/net/HttpURLConnection;->disconnect()V

    .line 771
    const-string v29, "FileTransfer"

    new-instance v30, Ljava/lang/StringBuilder;

    invoke-direct/range {v30 .. v30}, Ljava/lang/StringBuilder;-><init>()V

    const-string v31, "Resource not modified: "

    invoke-virtual/range {v30 .. v31}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v30

    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$4;->val$source:Ljava/lang/String;

    move-object/from16 v31, v0

    invoke-virtual/range {v30 .. v31}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v30

    invoke-virtual/range {v30 .. v30}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v30

    invoke-static/range {v29 .. v30}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 772
    sget v29, Lorg/apache/cordova/filetransfer/FileTransfer;->NOT_MODIFIED_ERR:I

    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$4;->val$source:Ljava/lang/String;

    move-object/from16 v30, v0

    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$4;->val$target:Ljava/lang/String;

    move-object/from16 v31, v0

    const/16 v32, 0x0

    move/from16 v0, v29

    move-object/from16 v1, v30

    move-object/from16 v2, v31

    move-object/from16 v3, v32

    # invokes: Lorg/apache/cordova/filetransfer/FileTransfer;->createFileTransferError(ILjava/lang/String;Ljava/lang/String;Ljava/net/URLConnection;Ljava/lang/Throwable;)Lorg/json/JSONObject;
    invoke-static {v0, v1, v2, v7, v3}, Lorg/apache/cordova/filetransfer/FileTransfer;->access$500(ILjava/lang/String;Ljava/lang/String;Ljava/net/URLConnection;Ljava/lang/Throwable;)Lorg/json/JSONObject;

    move-result-object v10

    .line 773
    .local v10, "error":Lorg/json/JSONObject;
    new-instance v27, Lorg/apache/cordova/PluginResult;

    sget-object v29, Lorg/apache/cordova/PluginResult$Status;->ERROR:Lorg/apache/cordova/PluginResult$Status;

    move-object/from16 v0, v27

    move-object/from16 v1, v29

    invoke-direct {v0, v1, v10}, Lorg/apache/cordova/PluginResult;-><init>(Lorg/apache/cordova/PluginResult$Status;Lorg/json/JSONObject;)V

    .line 774
    .end local v26    # "result":Lorg/apache/cordova/PluginResult;
    .restart local v27    # "result":Lorg/apache/cordova/PluginResult;
    goto/16 :goto_1

    .line 775
    .end local v10    # "error":Lorg/json/JSONObject;
    .end local v27    # "result":Lorg/apache/cordova/PluginResult;
    .restart local v26    # "result":Lorg/apache/cordova/PluginResult;
    :cond_8
    invoke-virtual {v7}, Ljava/net/HttpURLConnection;->getContentEncoding()Ljava/lang/String;

    move-result-object v29

    if-eqz v29, :cond_9

    invoke-virtual {v7}, Ljava/net/HttpURLConnection;->getContentEncoding()Ljava/lang/String;

    move-result-object v29

    const-string v30, "gzip"

    invoke-virtual/range {v29 .. v30}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v29

    if-eqz v29, :cond_a

    .line 778
    :cond_9
    invoke-virtual {v7}, Ljava/net/HttpURLConnection;->getContentLength()I

    move-result v29

    const/16 v30, -0x1

    move/from16 v0, v29

    move/from16 v1, v30

    if-eq v0, v1, :cond_a

    .line 779
    const/16 v29, 0x1

    move-object/from16 v0, v23

    move/from16 v1, v29

    invoke-virtual {v0, v1}, Lorg/apache/cordova/filetransfer/FileProgressResult;->setLengthComputable(Z)V

    .line 780
    invoke-virtual {v7}, Ljava/net/HttpURLConnection;->getContentLength()I

    move-result v29

    move/from16 v0, v29

    int-to-long v0, v0

    move-wide/from16 v30, v0

    move-object/from16 v0, v23

    move-wide/from16 v1, v30

    invoke-virtual {v0, v1, v2}, Lorg/apache/cordova/filetransfer/FileProgressResult;->setTotal(J)V

    .line 783
    :cond_a
    # invokes: Lorg/apache/cordova/filetransfer/FileTransfer;->getInputStream(Ljava/net/URLConnection;)Lorg/apache/cordova/filetransfer/FileTransfer$TrackingInputStream;
    invoke-static {v7}, Lorg/apache/cordova/filetransfer/FileTransfer;->access$400(Ljava/net/URLConnection;)Lorg/apache/cordova/filetransfer/FileTransfer$TrackingInputStream;
    :try_end_7
    .catch Ljava/io/FileNotFoundException; {:try_start_7 .. :try_end_7} :catch_7
    .catch Ljava/io/IOException; {:try_start_7 .. :try_end_7} :catch_1
    .catch Lorg/json/JSONException; {:try_start_7 .. :try_end_7} :catch_2
    .catch Ljava/lang/Throwable; {:try_start_7 .. :try_end_7} :catch_3
    .catchall {:try_start_7 .. :try_end_7} :catchall_2

    move-result-object v16

    move-object/from16 v27, v26

    .end local v26    # "result":Lorg/apache/cordova/PluginResult;
    .restart local v27    # "result":Lorg/apache/cordova/PluginResult;
    goto/16 :goto_1

    .line 793
    .end local v8    # "cookie":Ljava/lang/String;
    :cond_b
    :try_start_8
    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$4;->val$context:Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;

    move-object/from16 v29, v0

    move-object/from16 v0, v29

    iput-object v7, v0, Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;->connection:Ljava/net/HttpURLConnection;

    .line 794
    monitor-exit v30
    :try_end_8
    .catchall {:try_start_8 .. :try_end_8} :catchall_1

    .line 797
    const/16 v29, 0x4000

    :try_start_9
    move/from16 v0, v29

    new-array v4, v0, [B

    .line 798
    .local v4, "buffer":[B
    const/4 v5, 0x0

    .line 799
    .local v5, "bytesRead":I
    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$4;->val$resourceApi:Lorg/apache/cordova/CordovaResourceApi;

    move-object/from16 v29, v0

    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$4;->val$targetUri:Landroid/net/Uri;

    move-object/from16 v30, v0

    invoke-virtual/range {v29 .. v30}, Lorg/apache/cordova/CordovaResourceApi;->openOutputStream(Landroid/net/Uri;)Ljava/io/OutputStream;

    move-result-object v20

    .line 800
    :goto_4
    move-object/from16 v0, v16

    invoke-virtual {v0, v4}, Lorg/apache/cordova/filetransfer/FileTransfer$TrackingInputStream;->read([B)I

    move-result v5

    if-lez v5, :cond_f

    .line 801
    const/16 v29, 0x0

    move-object/from16 v0, v20

    move/from16 v1, v29

    invoke-virtual {v0, v4, v1, v5}, Ljava/io/OutputStream;->write([BII)V

    .line 803
    invoke-virtual/range {v16 .. v16}, Lorg/apache/cordova/filetransfer/FileTransfer$TrackingInputStream;->getTotalRawBytesRead()J

    move-result-wide v30

    move-object/from16 v0, v23

    move-wide/from16 v1, v30

    invoke-virtual {v0, v1, v2}, Lorg/apache/cordova/filetransfer/FileProgressResult;->setLoaded(J)V

    .line 804
    new-instance v24, Lorg/apache/cordova/PluginResult;

    sget-object v29, Lorg/apache/cordova/PluginResult$Status;->OK:Lorg/apache/cordova/PluginResult$Status;

    invoke-virtual/range {v23 .. v23}, Lorg/apache/cordova/filetransfer/FileProgressResult;->toJSONObject()Lorg/json/JSONObject;

    move-result-object v30

    move-object/from16 v0, v24

    move-object/from16 v1, v29

    move-object/from16 v2, v30

    invoke-direct {v0, v1, v2}, Lorg/apache/cordova/PluginResult;-><init>(Lorg/apache/cordova/PluginResult$Status;Lorg/json/JSONObject;)V

    .line 805
    .local v24, "progressResult":Lorg/apache/cordova/PluginResult;
    const/16 v29, 0x1

    move-object/from16 v0, v24

    move/from16 v1, v29

    invoke-virtual {v0, v1}, Lorg/apache/cordova/PluginResult;->setKeepCallback(Z)V

    .line 806
    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$4;->val$context:Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;

    move-object/from16 v29, v0

    move-object/from16 v0, v29

    move-object/from16 v1, v24

    invoke-virtual {v0, v1}, Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;->sendPluginResult(Lorg/apache/cordova/PluginResult;)V
    :try_end_9
    .catchall {:try_start_9 .. :try_end_9} :catchall_0

    goto :goto_4

    .line 809
    .end local v4    # "buffer":[B
    .end local v5    # "bytesRead":I
    .end local v24    # "progressResult":Lorg/apache/cordova/PluginResult;
    :catchall_0
    move-exception v29

    :try_start_a
    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$4;->val$context:Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;

    move-object/from16 v30, v0

    monitor-enter v30
    :try_end_a
    .catch Ljava/io/FileNotFoundException; {:try_start_a .. :try_end_a} :catch_0
    .catch Ljava/io/IOException; {:try_start_a .. :try_end_a} :catch_4
    .catch Lorg/json/JSONException; {:try_start_a .. :try_end_a} :catch_5
    .catch Ljava/lang/Throwable; {:try_start_a .. :try_end_a} :catch_6
    .catchall {:try_start_a .. :try_end_a} :catchall_d

    .line 810
    :try_start_b
    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$4;->val$context:Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;

    move-object/from16 v31, v0

    const/16 v32, 0x0

    move-object/from16 v0, v32

    move-object/from16 v1, v31

    iput-object v0, v1, Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;->connection:Ljava/net/HttpURLConnection;

    .line 811
    monitor-exit v30
    :try_end_b
    .catchall {:try_start_b .. :try_end_b} :catchall_8

    .line 812
    :try_start_c
    # invokes: Lorg/apache/cordova/filetransfer/FileTransfer;->safeClose(Ljava/io/Closeable;)V
    invoke-static/range {v16 .. v16}, Lorg/apache/cordova/filetransfer/FileTransfer;->access$300(Ljava/io/Closeable;)V

    .line 813
    # invokes: Lorg/apache/cordova/filetransfer/FileTransfer;->safeClose(Ljava/io/Closeable;)V
    invoke-static/range {v20 .. v20}, Lorg/apache/cordova/filetransfer/FileTransfer;->access$300(Ljava/io/Closeable;)V

    .line 809
    throw v29
    :try_end_c
    .catch Ljava/io/FileNotFoundException; {:try_start_c .. :try_end_c} :catch_0
    .catch Ljava/io/IOException; {:try_start_c .. :try_end_c} :catch_4
    .catch Lorg/json/JSONException; {:try_start_c .. :try_end_c} :catch_5
    .catch Ljava/lang/Throwable; {:try_start_c .. :try_end_c} :catch_6
    .catchall {:try_start_c .. :try_end_c} :catchall_d

    .line 855
    :catch_0
    move-exception v9

    .line 856
    .end local v23    # "progress":Lorg/apache/cordova/filetransfer/FileProgressResult;
    .local v9, "e":Ljava/io/FileNotFoundException;
    :goto_5
    :try_start_d
    sget v29, Lorg/apache/cordova/filetransfer/FileTransfer;->FILE_NOT_FOUND_ERR:I

    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$4;->val$source:Ljava/lang/String;

    move-object/from16 v30, v0

    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$4;->val$target:Ljava/lang/String;

    move-object/from16 v31, v0

    move/from16 v0, v29

    move-object/from16 v1, v30

    move-object/from16 v2, v31

    # invokes: Lorg/apache/cordova/filetransfer/FileTransfer;->createFileTransferError(ILjava/lang/String;Ljava/lang/String;Ljava/net/URLConnection;Ljava/lang/Throwable;)Lorg/json/JSONObject;
    invoke-static {v0, v1, v2, v7, v9}, Lorg/apache/cordova/filetransfer/FileTransfer;->access$500(ILjava/lang/String;Ljava/lang/String;Ljava/net/URLConnection;Ljava/lang/Throwable;)Lorg/json/JSONObject;

    move-result-object v10

    .line 857
    .restart local v10    # "error":Lorg/json/JSONObject;
    const-string v29, "FileTransfer"

    invoke-virtual {v10}, Lorg/json/JSONObject;->toString()Ljava/lang/String;

    move-result-object v30

    move-object/from16 v0, v29

    move-object/from16 v1, v30

    invoke-static {v0, v1, v9}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    .line 858
    new-instance v26, Lorg/apache/cordova/PluginResult;

    sget-object v29, Lorg/apache/cordova/PluginResult$Status;->IO_EXCEPTION:Lorg/apache/cordova/PluginResult$Status;

    move-object/from16 v0, v26

    move-object/from16 v1, v29

    invoke-direct {v0, v1, v10}, Lorg/apache/cordova/PluginResult;-><init>(Lorg/apache/cordova/PluginResult$Status;Lorg/json/JSONObject;)V
    :try_end_d
    .catchall {:try_start_d .. :try_end_d} :catchall_d

    .line 871
    .end local v27    # "result":Lorg/apache/cordova/PluginResult;
    .restart local v26    # "result":Lorg/apache/cordova/PluginResult;
    # getter for: Lorg/apache/cordova/filetransfer/FileTransfer;->activeRequests:Ljava/util/HashMap;
    invoke-static {}, Lorg/apache/cordova/filetransfer/FileTransfer;->access$600()Ljava/util/HashMap;

    move-result-object v30

    monitor-enter v30

    .line 872
    :try_start_e
    # getter for: Lorg/apache/cordova/filetransfer/FileTransfer;->activeRequests:Ljava/util/HashMap;
    invoke-static {}, Lorg/apache/cordova/filetransfer/FileTransfer;->access$600()Ljava/util/HashMap;

    move-result-object v29

    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$4;->val$objectId:Ljava/lang/String;

    move-object/from16 v31, v0

    move-object/from16 v0, v29

    move-object/from16 v1, v31

    invoke-virtual {v0, v1}, Ljava/util/HashMap;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    .line 873
    monitor-exit v30
    :try_end_e
    .catchall {:try_start_e .. :try_end_e} :catchall_4

    .line 875
    if-eqz v7, :cond_c

    .line 877
    move-object/from16 v0, p0

    iget-boolean v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$4;->val$trustEveryone:Z

    move/from16 v29, v0

    if-eqz v29, :cond_c

    move-object/from16 v0, p0

    iget-boolean v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$4;->val$useHttps:Z

    move/from16 v29, v0

    if-eqz v29, :cond_c

    move-object v15, v7

    .line 878
    check-cast v15, Ljavax/net/ssl/HttpsURLConnection;

    .line 879
    .restart local v15    # "https":Ljavax/net/ssl/HttpsURLConnection;
    move-object/from16 v0, v18

    invoke-virtual {v15, v0}, Ljavax/net/ssl/HttpsURLConnection;->setHostnameVerifier(Ljavax/net/ssl/HostnameVerifier;)V

    .line 880
    move-object/from16 v0, v19

    invoke-virtual {v15, v0}, Ljavax/net/ssl/HttpsURLConnection;->setSSLSocketFactory(Ljavax/net/ssl/SSLSocketFactory;)V

    .line 884
    .end local v15    # "https":Ljavax/net/ssl/HttpsURLConnection;
    :cond_c
    if-nez v26, :cond_d

    .line 885
    new-instance v26, Lorg/apache/cordova/PluginResult;

    .end local v26    # "result":Lorg/apache/cordova/PluginResult;
    sget-object v29, Lorg/apache/cordova/PluginResult$Status;->ERROR:Lorg/apache/cordova/PluginResult$Status;

    sget v30, Lorg/apache/cordova/filetransfer/FileTransfer;->CONNECTION_ERR:I

    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$4;->val$source:Ljava/lang/String;

    move-object/from16 v31, v0

    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$4;->val$target:Ljava/lang/String;

    move-object/from16 v32, v0

    const/16 v33, 0x0

    move/from16 v0, v30

    move-object/from16 v1, v31

    move-object/from16 v2, v32

    move-object/from16 v3, v33

    # invokes: Lorg/apache/cordova/filetransfer/FileTransfer;->createFileTransferError(ILjava/lang/String;Ljava/lang/String;Ljava/net/URLConnection;Ljava/lang/Throwable;)Lorg/json/JSONObject;
    invoke-static {v0, v1, v2, v7, v3}, Lorg/apache/cordova/filetransfer/FileTransfer;->access$500(ILjava/lang/String;Ljava/lang/String;Ljava/net/URLConnection;Ljava/lang/Throwable;)Lorg/json/JSONObject;

    move-result-object v30

    move-object/from16 v0, v26

    move-object/from16 v1, v29

    move-object/from16 v2, v30

    invoke-direct {v0, v1, v2}, Lorg/apache/cordova/PluginResult;-><init>(Lorg/apache/cordova/PluginResult$Status;Lorg/json/JSONObject;)V

    .line 888
    .restart local v26    # "result":Lorg/apache/cordova/PluginResult;
    :cond_d
    if-nez v6, :cond_e

    invoke-virtual/range {v26 .. v26}, Lorg/apache/cordova/PluginResult;->getStatus()I

    move-result v29

    sget-object v30, Lorg/apache/cordova/PluginResult$Status;->OK:Lorg/apache/cordova/PluginResult$Status;

    invoke-virtual/range {v30 .. v30}, Lorg/apache/cordova/PluginResult$Status;->ordinal()I

    move-result v30

    move/from16 v0, v29

    move/from16 v1, v30

    if-eq v0, v1, :cond_e

    if-eqz v11, :cond_e

    .line 889
    invoke-virtual {v11}, Ljava/io/File;->delete()Z

    .line 891
    :cond_e
    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$4;->val$context:Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;

    move-object/from16 v29, v0

    goto/16 :goto_3

    .line 794
    .end local v9    # "e":Ljava/io/FileNotFoundException;
    .end local v10    # "error":Lorg/json/JSONObject;
    .end local v26    # "result":Lorg/apache/cordova/PluginResult;
    .restart local v23    # "progress":Lorg/apache/cordova/filetransfer/FileProgressResult;
    .restart local v27    # "result":Lorg/apache/cordova/PluginResult;
    :catchall_1
    move-exception v29

    :try_start_f
    monitor-exit v30
    :try_end_f
    .catchall {:try_start_f .. :try_end_f} :catchall_1

    :try_start_10
    throw v29
    :try_end_10
    .catchall {:try_start_10 .. :try_end_10} :catchall_0

    .line 809
    .restart local v4    # "buffer":[B
    .restart local v5    # "bytesRead":I
    :cond_f
    :try_start_11
    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$4;->val$context:Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;

    move-object/from16 v30, v0

    monitor-enter v30
    :try_end_11
    .catch Ljava/io/FileNotFoundException; {:try_start_11 .. :try_end_11} :catch_0
    .catch Ljava/io/IOException; {:try_start_11 .. :try_end_11} :catch_4
    .catch Lorg/json/JSONException; {:try_start_11 .. :try_end_11} :catch_5
    .catch Ljava/lang/Throwable; {:try_start_11 .. :try_end_11} :catch_6
    .catchall {:try_start_11 .. :try_end_11} :catchall_d

    .line 810
    :try_start_12
    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$4;->val$context:Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;

    move-object/from16 v29, v0

    const/16 v31, 0x0

    move-object/from16 v0, v31

    move-object/from16 v1, v29

    iput-object v0, v1, Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;->connection:Ljava/net/HttpURLConnection;

    .line 811
    monitor-exit v30
    :try_end_12
    .catchall {:try_start_12 .. :try_end_12} :catchall_b

    .line 812
    :try_start_13
    # invokes: Lorg/apache/cordova/filetransfer/FileTransfer;->safeClose(Ljava/io/Closeable;)V
    invoke-static/range {v16 .. v16}, Lorg/apache/cordova/filetransfer/FileTransfer;->access$300(Ljava/io/Closeable;)V

    .line 813
    # invokes: Lorg/apache/cordova/filetransfer/FileTransfer;->safeClose(Ljava/io/Closeable;)V
    invoke-static/range {v20 .. v20}, Lorg/apache/cordova/filetransfer/FileTransfer;->access$300(Ljava/io/Closeable;)V

    .line 816
    const-string v29, "FileTransfer"

    new-instance v30, Ljava/lang/StringBuilder;

    invoke-direct/range {v30 .. v30}, Ljava/lang/StringBuilder;-><init>()V

    const-string v31, "Saved file: "

    invoke-virtual/range {v30 .. v31}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v30

    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$4;->val$target:Ljava/lang/String;

    move-object/from16 v31, v0

    invoke-virtual/range {v30 .. v31}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v30

    invoke-virtual/range {v30 .. v30}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v30

    invoke-static/range {v29 .. v30}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 820
    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$4;->this$0:Lorg/apache/cordova/filetransfer/FileTransfer;

    move-object/from16 v29, v0

    move-object/from16 v0, v29

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer;->webView:Lorg/apache/cordova/CordovaWebView;

    move-object/from16 v29, v0

    invoke-virtual/range {v29 .. v29}, Ljava/lang/Object;->getClass()Ljava/lang/Class;
    :try_end_13
    .catch Ljava/io/FileNotFoundException; {:try_start_13 .. :try_end_13} :catch_0
    .catch Ljava/io/IOException; {:try_start_13 .. :try_end_13} :catch_4
    .catch Lorg/json/JSONException; {:try_start_13 .. :try_end_13} :catch_5
    .catch Ljava/lang/Throwable; {:try_start_13 .. :try_end_13} :catch_6
    .catchall {:try_start_13 .. :try_end_13} :catchall_d

    move-result-object v28

    .line 821
    .local v28, "webViewClass":Ljava/lang/Class;
    const/16 v21, 0x0

    .line 823
    .local v21, "pm":Lorg/apache/cordova/PluginManager;
    :try_start_14
    const-string v29, "getPluginManager"

    const/16 v30, 0x0

    move/from16 v0, v30

    new-array v0, v0, [Ljava/lang/Class;

    move-object/from16 v30, v0

    invoke-virtual/range {v28 .. v30}, Ljava/lang/Class;->getMethod(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;

    move-result-object v14

    .line 824
    .local v14, "gpm":Ljava/lang/reflect/Method;
    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$4;->this$0:Lorg/apache/cordova/filetransfer/FileTransfer;

    move-object/from16 v29, v0

    move-object/from16 v0, v29

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer;->webView:Lorg/apache/cordova/CordovaWebView;

    move-object/from16 v29, v0

    const/16 v30, 0x0

    move/from16 v0, v30

    new-array v0, v0, [Ljava/lang/Object;

    move-object/from16 v30, v0

    move-object/from16 v0, v29

    move-object/from16 v1, v30

    invoke-virtual {v14, v0, v1}, Ljava/lang/reflect/Method;->invoke(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v29

    move-object/from16 v0, v29

    check-cast v0, Lorg/apache/cordova/PluginManager;

    move-object/from16 v21, v0
    :try_end_14
    .catch Ljava/lang/NoSuchMethodException; {:try_start_14 .. :try_end_14} :catch_c
    .catch Ljava/lang/IllegalAccessException; {:try_start_14 .. :try_end_14} :catch_b
    .catch Ljava/lang/reflect/InvocationTargetException; {:try_start_14 .. :try_end_14} :catch_a
    .catch Ljava/io/FileNotFoundException; {:try_start_14 .. :try_end_14} :catch_0
    .catch Ljava/io/IOException; {:try_start_14 .. :try_end_14} :catch_4
    .catch Lorg/json/JSONException; {:try_start_14 .. :try_end_14} :catch_5
    .catch Ljava/lang/Throwable; {:try_start_14 .. :try_end_14} :catch_6
    .catchall {:try_start_14 .. :try_end_14} :catchall_d

    .line 829
    .end local v14    # "gpm":Ljava/lang/reflect/Method;
    :goto_6
    if-nez v21, :cond_10

    .line 831
    :try_start_15
    const-string v29, "pluginManager"

    invoke-virtual/range {v28 .. v29}, Ljava/lang/Class;->getField(Ljava/lang/String;)Ljava/lang/reflect/Field;

    move-result-object v22

    .line 832
    .local v22, "pmf":Ljava/lang/reflect/Field;
    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$4;->this$0:Lorg/apache/cordova/filetransfer/FileTransfer;

    move-object/from16 v29, v0

    move-object/from16 v0, v29

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer;->webView:Lorg/apache/cordova/CordovaWebView;

    move-object/from16 v29, v0

    move-object/from16 v0, v22

    move-object/from16 v1, v29

    invoke-virtual {v0, v1}, Ljava/lang/reflect/Field;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v29

    move-object/from16 v0, v29

    check-cast v0, Lorg/apache/cordova/PluginManager;

    move-object/from16 v21, v0
    :try_end_15
    .catch Ljava/lang/NoSuchFieldException; {:try_start_15 .. :try_end_15} :catch_9
    .catch Ljava/lang/IllegalAccessException; {:try_start_15 .. :try_end_15} :catch_8
    .catch Ljava/io/FileNotFoundException; {:try_start_15 .. :try_end_15} :catch_0
    .catch Ljava/io/IOException; {:try_start_15 .. :try_end_15} :catch_4
    .catch Lorg/json/JSONException; {:try_start_15 .. :try_end_15} :catch_5
    .catch Ljava/lang/Throwable; {:try_start_15 .. :try_end_15} :catch_6
    .catchall {:try_start_15 .. :try_end_15} :catchall_d

    .line 837
    .end local v22    # "pmf":Ljava/lang/reflect/Field;
    :cond_10
    :goto_7
    :try_start_16
    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$4;->val$resourceApi:Lorg/apache/cordova/CordovaResourceApi;

    move-object/from16 v29, v0

    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$4;->val$targetUri:Landroid/net/Uri;

    move-object/from16 v30, v0

    invoke-virtual/range {v29 .. v30}, Lorg/apache/cordova/CordovaResourceApi;->mapUriToFile(Landroid/net/Uri;)Ljava/io/File;

    move-result-object v11

    .line 838
    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$4;->val$context:Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;

    move-object/from16 v29, v0

    move-object/from16 v0, v29

    iput-object v11, v0, Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;->targetFile:Ljava/io/File;

    .line 839
    const-string v29, "File"

    move-object/from16 v0, v21

    move-object/from16 v1, v29

    invoke-virtual {v0, v1}, Lorg/apache/cordova/PluginManager;->getPlugin(Ljava/lang/String;)Lorg/apache/cordova/CordovaPlugin;

    move-result-object v13

    check-cast v13, Lorg/apache/cordova/file/FileUtils;

    .line 840
    .local v13, "filePlugin":Lorg/apache/cordova/file/FileUtils;
    if-eqz v13, :cond_15

    .line 841
    invoke-virtual {v13, v11}, Lorg/apache/cordova/file/FileUtils;->getEntryForFile(Ljava/io/File;)Lorg/json/JSONObject;

    move-result-object v12

    .line 842
    .local v12, "fileEntry":Lorg/json/JSONObject;
    if-eqz v12, :cond_14

    .line 843
    new-instance v26, Lorg/apache/cordova/PluginResult;

    sget-object v29, Lorg/apache/cordova/PluginResult$Status;->OK:Lorg/apache/cordova/PluginResult$Status;

    move-object/from16 v0, v26

    move-object/from16 v1, v29

    invoke-direct {v0, v1, v12}, Lorg/apache/cordova/PluginResult;-><init>(Lorg/apache/cordova/PluginResult$Status;Lorg/json/JSONObject;)V
    :try_end_16
    .catch Ljava/io/FileNotFoundException; {:try_start_16 .. :try_end_16} :catch_0
    .catch Ljava/io/IOException; {:try_start_16 .. :try_end_16} :catch_4
    .catch Lorg/json/JSONException; {:try_start_16 .. :try_end_16} :catch_5
    .catch Ljava/lang/Throwable; {:try_start_16 .. :try_end_16} :catch_6
    .catchall {:try_start_16 .. :try_end_16} :catchall_d

    .line 871
    .end local v4    # "buffer":[B
    .end local v5    # "bytesRead":I
    .end local v12    # "fileEntry":Lorg/json/JSONObject;
    .end local v13    # "filePlugin":Lorg/apache/cordova/file/FileUtils;
    .end local v21    # "pm":Lorg/apache/cordova/PluginManager;
    .end local v27    # "result":Lorg/apache/cordova/PluginResult;
    .end local v28    # "webViewClass":Ljava/lang/Class;
    .restart local v26    # "result":Lorg/apache/cordova/PluginResult;
    :goto_8
    # getter for: Lorg/apache/cordova/filetransfer/FileTransfer;->activeRequests:Ljava/util/HashMap;
    invoke-static {}, Lorg/apache/cordova/filetransfer/FileTransfer;->access$600()Ljava/util/HashMap;

    move-result-object v30

    monitor-enter v30

    .line 872
    :try_start_17
    # getter for: Lorg/apache/cordova/filetransfer/FileTransfer;->activeRequests:Ljava/util/HashMap;
    invoke-static {}, Lorg/apache/cordova/filetransfer/FileTransfer;->access$600()Ljava/util/HashMap;

    move-result-object v29

    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$4;->val$objectId:Ljava/lang/String;

    move-object/from16 v31, v0

    move-object/from16 v0, v29

    move-object/from16 v1, v31

    invoke-virtual {v0, v1}, Ljava/util/HashMap;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    .line 873
    monitor-exit v30
    :try_end_17
    .catchall {:try_start_17 .. :try_end_17} :catchall_c

    .line 875
    if-eqz v7, :cond_11

    .line 877
    move-object/from16 v0, p0

    iget-boolean v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$4;->val$trustEveryone:Z

    move/from16 v29, v0

    if-eqz v29, :cond_11

    move-object/from16 v0, p0

    iget-boolean v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$4;->val$useHttps:Z

    move/from16 v29, v0

    if-eqz v29, :cond_11

    move-object v15, v7

    .line 878
    check-cast v15, Ljavax/net/ssl/HttpsURLConnection;

    .line 879
    .restart local v15    # "https":Ljavax/net/ssl/HttpsURLConnection;
    move-object/from16 v0, v18

    invoke-virtual {v15, v0}, Ljavax/net/ssl/HttpsURLConnection;->setHostnameVerifier(Ljavax/net/ssl/HostnameVerifier;)V

    .line 880
    move-object/from16 v0, v19

    invoke-virtual {v15, v0}, Ljavax/net/ssl/HttpsURLConnection;->setSSLSocketFactory(Ljavax/net/ssl/SSLSocketFactory;)V

    .line 884
    .end local v15    # "https":Ljavax/net/ssl/HttpsURLConnection;
    :cond_11
    if-nez v26, :cond_12

    .line 885
    new-instance v26, Lorg/apache/cordova/PluginResult;

    .end local v26    # "result":Lorg/apache/cordova/PluginResult;
    sget-object v29, Lorg/apache/cordova/PluginResult$Status;->ERROR:Lorg/apache/cordova/PluginResult$Status;

    sget v30, Lorg/apache/cordova/filetransfer/FileTransfer;->CONNECTION_ERR:I

    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$4;->val$source:Ljava/lang/String;

    move-object/from16 v31, v0

    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$4;->val$target:Ljava/lang/String;

    move-object/from16 v32, v0

    const/16 v33, 0x0

    move/from16 v0, v30

    move-object/from16 v1, v31

    move-object/from16 v2, v32

    move-object/from16 v3, v33

    # invokes: Lorg/apache/cordova/filetransfer/FileTransfer;->createFileTransferError(ILjava/lang/String;Ljava/lang/String;Ljava/net/URLConnection;Ljava/lang/Throwable;)Lorg/json/JSONObject;
    invoke-static {v0, v1, v2, v7, v3}, Lorg/apache/cordova/filetransfer/FileTransfer;->access$500(ILjava/lang/String;Ljava/lang/String;Ljava/net/URLConnection;Ljava/lang/Throwable;)Lorg/json/JSONObject;

    move-result-object v30

    move-object/from16 v0, v26

    move-object/from16 v1, v29

    move-object/from16 v2, v30

    invoke-direct {v0, v1, v2}, Lorg/apache/cordova/PluginResult;-><init>(Lorg/apache/cordova/PluginResult$Status;Lorg/json/JSONObject;)V

    .line 888
    .restart local v26    # "result":Lorg/apache/cordova/PluginResult;
    :cond_12
    if-nez v6, :cond_13

    invoke-virtual/range {v26 .. v26}, Lorg/apache/cordova/PluginResult;->getStatus()I

    move-result v29

    sget-object v30, Lorg/apache/cordova/PluginResult$Status;->OK:Lorg/apache/cordova/PluginResult$Status;

    invoke-virtual/range {v30 .. v30}, Lorg/apache/cordova/PluginResult$Status;->ordinal()I

    move-result v30

    move/from16 v0, v29

    move/from16 v1, v30

    if-eq v0, v1, :cond_13

    if-eqz v11, :cond_13

    .line 889
    invoke-virtual {v11}, Ljava/io/File;->delete()Z

    .line 891
    :cond_13
    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$4;->val$context:Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;

    move-object/from16 v29, v0

    goto/16 :goto_3

    .line 845
    .end local v26    # "result":Lorg/apache/cordova/PluginResult;
    .restart local v4    # "buffer":[B
    .restart local v5    # "bytesRead":I
    .restart local v12    # "fileEntry":Lorg/json/JSONObject;
    .restart local v13    # "filePlugin":Lorg/apache/cordova/file/FileUtils;
    .restart local v21    # "pm":Lorg/apache/cordova/PluginManager;
    .restart local v27    # "result":Lorg/apache/cordova/PluginResult;
    .restart local v28    # "webViewClass":Ljava/lang/Class;
    :cond_14
    :try_start_18
    sget v29, Lorg/apache/cordova/filetransfer/FileTransfer;->CONNECTION_ERR:I

    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$4;->val$source:Ljava/lang/String;

    move-object/from16 v30, v0

    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$4;->val$target:Ljava/lang/String;

    move-object/from16 v31, v0

    const/16 v32, 0x0

    move/from16 v0, v29

    move-object/from16 v1, v30

    move-object/from16 v2, v31

    move-object/from16 v3, v32

    # invokes: Lorg/apache/cordova/filetransfer/FileTransfer;->createFileTransferError(ILjava/lang/String;Ljava/lang/String;Ljava/net/URLConnection;Ljava/lang/Throwable;)Lorg/json/JSONObject;
    invoke-static {v0, v1, v2, v7, v3}, Lorg/apache/cordova/filetransfer/FileTransfer;->access$500(ILjava/lang/String;Ljava/lang/String;Ljava/net/URLConnection;Ljava/lang/Throwable;)Lorg/json/JSONObject;

    move-result-object v10

    .line 846
    .restart local v10    # "error":Lorg/json/JSONObject;
    const-string v29, "FileTransfer"

    const-string v30, "File plugin cannot represent download path"

    invoke-static/range {v29 .. v30}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    .line 847
    new-instance v26, Lorg/apache/cordova/PluginResult;

    sget-object v29, Lorg/apache/cordova/PluginResult$Status;->IO_EXCEPTION:Lorg/apache/cordova/PluginResult$Status;

    move-object/from16 v0, v26

    move-object/from16 v1, v29

    invoke-direct {v0, v1, v10}, Lorg/apache/cordova/PluginResult;-><init>(Lorg/apache/cordova/PluginResult$Status;Lorg/json/JSONObject;)V

    .end local v27    # "result":Lorg/apache/cordova/PluginResult;
    .restart local v26    # "result":Lorg/apache/cordova/PluginResult;
    goto/16 :goto_8

    .line 850
    .end local v10    # "error":Lorg/json/JSONObject;
    .end local v12    # "fileEntry":Lorg/json/JSONObject;
    .end local v26    # "result":Lorg/apache/cordova/PluginResult;
    .restart local v27    # "result":Lorg/apache/cordova/PluginResult;
    :cond_15
    const-string v29, "FileTransfer"

    const-string v30, "File plugin not found; cannot save downloaded file"

    invoke-static/range {v29 .. v30}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    .line 851
    new-instance v26, Lorg/apache/cordova/PluginResult;

    sget-object v29, Lorg/apache/cordova/PluginResult$Status;->ERROR:Lorg/apache/cordova/PluginResult$Status;

    const-string v30, "File plugin not found; cannot save downloaded file"

    move-object/from16 v0, v26

    move-object/from16 v1, v29

    move-object/from16 v2, v30

    invoke-direct {v0, v1, v2}, Lorg/apache/cordova/PluginResult;-><init>(Lorg/apache/cordova/PluginResult$Status;Ljava/lang/String;)V
    :try_end_18
    .catch Ljava/io/FileNotFoundException; {:try_start_18 .. :try_end_18} :catch_0
    .catch Ljava/io/IOException; {:try_start_18 .. :try_end_18} :catch_4
    .catch Lorg/json/JSONException; {:try_start_18 .. :try_end_18} :catch_5
    .catch Ljava/lang/Throwable; {:try_start_18 .. :try_end_18} :catch_6
    .catchall {:try_start_18 .. :try_end_18} :catchall_d

    .end local v27    # "result":Lorg/apache/cordova/PluginResult;
    .restart local v26    # "result":Lorg/apache/cordova/PluginResult;
    goto/16 :goto_8

    .line 859
    .end local v4    # "buffer":[B
    .end local v5    # "bytesRead":I
    .end local v13    # "filePlugin":Lorg/apache/cordova/file/FileUtils;
    .end local v21    # "pm":Lorg/apache/cordova/PluginManager;
    .end local v23    # "progress":Lorg/apache/cordova/filetransfer/FileProgressResult;
    .end local v28    # "webViewClass":Ljava/lang/Class;
    :catch_1
    move-exception v9

    move-object/from16 v27, v26

    .line 860
    .end local v26    # "result":Lorg/apache/cordova/PluginResult;
    .local v9, "e":Ljava/io/IOException;
    .restart local v27    # "result":Lorg/apache/cordova/PluginResult;
    :goto_9
    :try_start_19
    sget v29, Lorg/apache/cordova/filetransfer/FileTransfer;->CONNECTION_ERR:I

    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$4;->val$source:Ljava/lang/String;

    move-object/from16 v30, v0

    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$4;->val$target:Ljava/lang/String;

    move-object/from16 v31, v0

    move/from16 v0, v29

    move-object/from16 v1, v30

    move-object/from16 v2, v31

    # invokes: Lorg/apache/cordova/filetransfer/FileTransfer;->createFileTransferError(ILjava/lang/String;Ljava/lang/String;Ljava/net/URLConnection;Ljava/lang/Throwable;)Lorg/json/JSONObject;
    invoke-static {v0, v1, v2, v7, v9}, Lorg/apache/cordova/filetransfer/FileTransfer;->access$500(ILjava/lang/String;Ljava/lang/String;Ljava/net/URLConnection;Ljava/lang/Throwable;)Lorg/json/JSONObject;

    move-result-object v10

    .line 861
    .restart local v10    # "error":Lorg/json/JSONObject;
    const-string v29, "FileTransfer"

    invoke-virtual {v10}, Lorg/json/JSONObject;->toString()Ljava/lang/String;

    move-result-object v30

    move-object/from16 v0, v29

    move-object/from16 v1, v30

    invoke-static {v0, v1, v9}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    .line 862
    new-instance v26, Lorg/apache/cordova/PluginResult;

    sget-object v29, Lorg/apache/cordova/PluginResult$Status;->IO_EXCEPTION:Lorg/apache/cordova/PluginResult$Status;

    move-object/from16 v0, v26

    move-object/from16 v1, v29

    invoke-direct {v0, v1, v10}, Lorg/apache/cordova/PluginResult;-><init>(Lorg/apache/cordova/PluginResult$Status;Lorg/json/JSONObject;)V
    :try_end_19
    .catchall {:try_start_19 .. :try_end_19} :catchall_d

    .line 871
    .end local v27    # "result":Lorg/apache/cordova/PluginResult;
    .restart local v26    # "result":Lorg/apache/cordova/PluginResult;
    # getter for: Lorg/apache/cordova/filetransfer/FileTransfer;->activeRequests:Ljava/util/HashMap;
    invoke-static {}, Lorg/apache/cordova/filetransfer/FileTransfer;->access$600()Ljava/util/HashMap;

    move-result-object v30

    monitor-enter v30

    .line 872
    :try_start_1a
    # getter for: Lorg/apache/cordova/filetransfer/FileTransfer;->activeRequests:Ljava/util/HashMap;
    invoke-static {}, Lorg/apache/cordova/filetransfer/FileTransfer;->access$600()Ljava/util/HashMap;

    move-result-object v29

    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$4;->val$objectId:Ljava/lang/String;

    move-object/from16 v31, v0

    move-object/from16 v0, v29

    move-object/from16 v1, v31

    invoke-virtual {v0, v1}, Ljava/util/HashMap;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    .line 873
    monitor-exit v30
    :try_end_1a
    .catchall {:try_start_1a .. :try_end_1a} :catchall_5

    .line 875
    if-eqz v7, :cond_16

    .line 877
    move-object/from16 v0, p0

    iget-boolean v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$4;->val$trustEveryone:Z

    move/from16 v29, v0

    if-eqz v29, :cond_16

    move-object/from16 v0, p0

    iget-boolean v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$4;->val$useHttps:Z

    move/from16 v29, v0

    if-eqz v29, :cond_16

    move-object v15, v7

    .line 878
    check-cast v15, Ljavax/net/ssl/HttpsURLConnection;

    .line 879
    .restart local v15    # "https":Ljavax/net/ssl/HttpsURLConnection;
    move-object/from16 v0, v18

    invoke-virtual {v15, v0}, Ljavax/net/ssl/HttpsURLConnection;->setHostnameVerifier(Ljavax/net/ssl/HostnameVerifier;)V

    .line 880
    move-object/from16 v0, v19

    invoke-virtual {v15, v0}, Ljavax/net/ssl/HttpsURLConnection;->setSSLSocketFactory(Ljavax/net/ssl/SSLSocketFactory;)V

    .line 884
    .end local v15    # "https":Ljavax/net/ssl/HttpsURLConnection;
    :cond_16
    if-nez v26, :cond_17

    .line 885
    new-instance v26, Lorg/apache/cordova/PluginResult;

    .end local v26    # "result":Lorg/apache/cordova/PluginResult;
    sget-object v29, Lorg/apache/cordova/PluginResult$Status;->ERROR:Lorg/apache/cordova/PluginResult$Status;

    sget v30, Lorg/apache/cordova/filetransfer/FileTransfer;->CONNECTION_ERR:I

    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$4;->val$source:Ljava/lang/String;

    move-object/from16 v31, v0

    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$4;->val$target:Ljava/lang/String;

    move-object/from16 v32, v0

    const/16 v33, 0x0

    move/from16 v0, v30

    move-object/from16 v1, v31

    move-object/from16 v2, v32

    move-object/from16 v3, v33

    # invokes: Lorg/apache/cordova/filetransfer/FileTransfer;->createFileTransferError(ILjava/lang/String;Ljava/lang/String;Ljava/net/URLConnection;Ljava/lang/Throwable;)Lorg/json/JSONObject;
    invoke-static {v0, v1, v2, v7, v3}, Lorg/apache/cordova/filetransfer/FileTransfer;->access$500(ILjava/lang/String;Ljava/lang/String;Ljava/net/URLConnection;Ljava/lang/Throwable;)Lorg/json/JSONObject;

    move-result-object v30

    move-object/from16 v0, v26

    move-object/from16 v1, v29

    move-object/from16 v2, v30

    invoke-direct {v0, v1, v2}, Lorg/apache/cordova/PluginResult;-><init>(Lorg/apache/cordova/PluginResult$Status;Lorg/json/JSONObject;)V

    .line 888
    .restart local v26    # "result":Lorg/apache/cordova/PluginResult;
    :cond_17
    if-nez v6, :cond_18

    invoke-virtual/range {v26 .. v26}, Lorg/apache/cordova/PluginResult;->getStatus()I

    move-result v29

    sget-object v30, Lorg/apache/cordova/PluginResult$Status;->OK:Lorg/apache/cordova/PluginResult$Status;

    invoke-virtual/range {v30 .. v30}, Lorg/apache/cordova/PluginResult$Status;->ordinal()I

    move-result v30

    move/from16 v0, v29

    move/from16 v1, v30

    if-eq v0, v1, :cond_18

    if-eqz v11, :cond_18

    .line 889
    invoke-virtual {v11}, Ljava/io/File;->delete()Z

    .line 891
    :cond_18
    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$4;->val$context:Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;

    move-object/from16 v29, v0

    goto/16 :goto_3

    .line 863
    .end local v9    # "e":Ljava/io/IOException;
    .end local v10    # "error":Lorg/json/JSONObject;
    :catch_2
    move-exception v9

    move-object/from16 v27, v26

    .line 864
    .end local v26    # "result":Lorg/apache/cordova/PluginResult;
    .local v9, "e":Lorg/json/JSONException;
    .restart local v27    # "result":Lorg/apache/cordova/PluginResult;
    :goto_a
    :try_start_1b
    const-string v29, "FileTransfer"

    invoke-virtual {v9}, Lorg/json/JSONException;->getMessage()Ljava/lang/String;

    move-result-object v30

    move-object/from16 v0, v29

    move-object/from16 v1, v30

    invoke-static {v0, v1, v9}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    .line 865
    new-instance v26, Lorg/apache/cordova/PluginResult;

    sget-object v29, Lorg/apache/cordova/PluginResult$Status;->JSON_EXCEPTION:Lorg/apache/cordova/PluginResult$Status;

    move-object/from16 v0, v26

    move-object/from16 v1, v29

    invoke-direct {v0, v1}, Lorg/apache/cordova/PluginResult;-><init>(Lorg/apache/cordova/PluginResult$Status;)V
    :try_end_1b
    .catchall {:try_start_1b .. :try_end_1b} :catchall_d

    .line 871
    .end local v27    # "result":Lorg/apache/cordova/PluginResult;
    .restart local v26    # "result":Lorg/apache/cordova/PluginResult;
    # getter for: Lorg/apache/cordova/filetransfer/FileTransfer;->activeRequests:Ljava/util/HashMap;
    invoke-static {}, Lorg/apache/cordova/filetransfer/FileTransfer;->access$600()Ljava/util/HashMap;

    move-result-object v30

    monitor-enter v30

    .line 872
    :try_start_1c
    # getter for: Lorg/apache/cordova/filetransfer/FileTransfer;->activeRequests:Ljava/util/HashMap;
    invoke-static {}, Lorg/apache/cordova/filetransfer/FileTransfer;->access$600()Ljava/util/HashMap;

    move-result-object v29

    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$4;->val$objectId:Ljava/lang/String;

    move-object/from16 v31, v0

    move-object/from16 v0, v29

    move-object/from16 v1, v31

    invoke-virtual {v0, v1}, Ljava/util/HashMap;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    .line 873
    monitor-exit v30
    :try_end_1c
    .catchall {:try_start_1c .. :try_end_1c} :catchall_6

    .line 875
    if-eqz v7, :cond_19

    .line 877
    move-object/from16 v0, p0

    iget-boolean v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$4;->val$trustEveryone:Z

    move/from16 v29, v0

    if-eqz v29, :cond_19

    move-object/from16 v0, p0

    iget-boolean v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$4;->val$useHttps:Z

    move/from16 v29, v0

    if-eqz v29, :cond_19

    move-object v15, v7

    .line 878
    check-cast v15, Ljavax/net/ssl/HttpsURLConnection;

    .line 879
    .restart local v15    # "https":Ljavax/net/ssl/HttpsURLConnection;
    move-object/from16 v0, v18

    invoke-virtual {v15, v0}, Ljavax/net/ssl/HttpsURLConnection;->setHostnameVerifier(Ljavax/net/ssl/HostnameVerifier;)V

    .line 880
    move-object/from16 v0, v19

    invoke-virtual {v15, v0}, Ljavax/net/ssl/HttpsURLConnection;->setSSLSocketFactory(Ljavax/net/ssl/SSLSocketFactory;)V

    .line 884
    .end local v15    # "https":Ljavax/net/ssl/HttpsURLConnection;
    :cond_19
    if-nez v26, :cond_1a

    .line 885
    new-instance v26, Lorg/apache/cordova/PluginResult;

    .end local v26    # "result":Lorg/apache/cordova/PluginResult;
    sget-object v29, Lorg/apache/cordova/PluginResult$Status;->ERROR:Lorg/apache/cordova/PluginResult$Status;

    sget v30, Lorg/apache/cordova/filetransfer/FileTransfer;->CONNECTION_ERR:I

    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$4;->val$source:Ljava/lang/String;

    move-object/from16 v31, v0

    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$4;->val$target:Ljava/lang/String;

    move-object/from16 v32, v0

    const/16 v33, 0x0

    move/from16 v0, v30

    move-object/from16 v1, v31

    move-object/from16 v2, v32

    move-object/from16 v3, v33

    # invokes: Lorg/apache/cordova/filetransfer/FileTransfer;->createFileTransferError(ILjava/lang/String;Ljava/lang/String;Ljava/net/URLConnection;Ljava/lang/Throwable;)Lorg/json/JSONObject;
    invoke-static {v0, v1, v2, v7, v3}, Lorg/apache/cordova/filetransfer/FileTransfer;->access$500(ILjava/lang/String;Ljava/lang/String;Ljava/net/URLConnection;Ljava/lang/Throwable;)Lorg/json/JSONObject;

    move-result-object v30

    move-object/from16 v0, v26

    move-object/from16 v1, v29

    move-object/from16 v2, v30

    invoke-direct {v0, v1, v2}, Lorg/apache/cordova/PluginResult;-><init>(Lorg/apache/cordova/PluginResult$Status;Lorg/json/JSONObject;)V

    .line 888
    .restart local v26    # "result":Lorg/apache/cordova/PluginResult;
    :cond_1a
    if-nez v6, :cond_1b

    invoke-virtual/range {v26 .. v26}, Lorg/apache/cordova/PluginResult;->getStatus()I

    move-result v29

    sget-object v30, Lorg/apache/cordova/PluginResult$Status;->OK:Lorg/apache/cordova/PluginResult$Status;

    invoke-virtual/range {v30 .. v30}, Lorg/apache/cordova/PluginResult$Status;->ordinal()I

    move-result v30

    move/from16 v0, v29

    move/from16 v1, v30

    if-eq v0, v1, :cond_1b

    if-eqz v11, :cond_1b

    .line 889
    invoke-virtual {v11}, Ljava/io/File;->delete()Z

    .line 891
    :cond_1b
    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$4;->val$context:Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;

    move-object/from16 v29, v0

    goto/16 :goto_3

    .line 866
    .end local v9    # "e":Lorg/json/JSONException;
    :catch_3
    move-exception v9

    move-object/from16 v27, v26

    .line 867
    .end local v26    # "result":Lorg/apache/cordova/PluginResult;
    .local v9, "e":Ljava/lang/Throwable;
    .restart local v27    # "result":Lorg/apache/cordova/PluginResult;
    :goto_b
    :try_start_1d
    sget v29, Lorg/apache/cordova/filetransfer/FileTransfer;->CONNECTION_ERR:I

    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$4;->val$source:Ljava/lang/String;

    move-object/from16 v30, v0

    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$4;->val$target:Ljava/lang/String;

    move-object/from16 v31, v0

    move/from16 v0, v29

    move-object/from16 v1, v30

    move-object/from16 v2, v31

    # invokes: Lorg/apache/cordova/filetransfer/FileTransfer;->createFileTransferError(ILjava/lang/String;Ljava/lang/String;Ljava/net/URLConnection;Ljava/lang/Throwable;)Lorg/json/JSONObject;
    invoke-static {v0, v1, v2, v7, v9}, Lorg/apache/cordova/filetransfer/FileTransfer;->access$500(ILjava/lang/String;Ljava/lang/String;Ljava/net/URLConnection;Ljava/lang/Throwable;)Lorg/json/JSONObject;

    move-result-object v10

    .line 868
    .restart local v10    # "error":Lorg/json/JSONObject;
    const-string v29, "FileTransfer"

    invoke-virtual {v10}, Lorg/json/JSONObject;->toString()Ljava/lang/String;

    move-result-object v30

    move-object/from16 v0, v29

    move-object/from16 v1, v30

    invoke-static {v0, v1, v9}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    .line 869
    new-instance v26, Lorg/apache/cordova/PluginResult;

    sget-object v29, Lorg/apache/cordova/PluginResult$Status;->IO_EXCEPTION:Lorg/apache/cordova/PluginResult$Status;

    move-object/from16 v0, v26

    move-object/from16 v1, v29

    invoke-direct {v0, v1, v10}, Lorg/apache/cordova/PluginResult;-><init>(Lorg/apache/cordova/PluginResult$Status;Lorg/json/JSONObject;)V
    :try_end_1d
    .catchall {:try_start_1d .. :try_end_1d} :catchall_d

    .line 871
    .end local v27    # "result":Lorg/apache/cordova/PluginResult;
    .restart local v26    # "result":Lorg/apache/cordova/PluginResult;
    # getter for: Lorg/apache/cordova/filetransfer/FileTransfer;->activeRequests:Ljava/util/HashMap;
    invoke-static {}, Lorg/apache/cordova/filetransfer/FileTransfer;->access$600()Ljava/util/HashMap;

    move-result-object v30

    monitor-enter v30

    .line 872
    :try_start_1e
    # getter for: Lorg/apache/cordova/filetransfer/FileTransfer;->activeRequests:Ljava/util/HashMap;
    invoke-static {}, Lorg/apache/cordova/filetransfer/FileTransfer;->access$600()Ljava/util/HashMap;

    move-result-object v29

    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$4;->val$objectId:Ljava/lang/String;

    move-object/from16 v31, v0

    move-object/from16 v0, v29

    move-object/from16 v1, v31

    invoke-virtual {v0, v1}, Ljava/util/HashMap;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    .line 873
    monitor-exit v30
    :try_end_1e
    .catchall {:try_start_1e .. :try_end_1e} :catchall_7

    .line 875
    if-eqz v7, :cond_1c

    .line 877
    move-object/from16 v0, p0

    iget-boolean v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$4;->val$trustEveryone:Z

    move/from16 v29, v0

    if-eqz v29, :cond_1c

    move-object/from16 v0, p0

    iget-boolean v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$4;->val$useHttps:Z

    move/from16 v29, v0

    if-eqz v29, :cond_1c

    move-object v15, v7

    .line 878
    check-cast v15, Ljavax/net/ssl/HttpsURLConnection;

    .line 879
    .restart local v15    # "https":Ljavax/net/ssl/HttpsURLConnection;
    move-object/from16 v0, v18

    invoke-virtual {v15, v0}, Ljavax/net/ssl/HttpsURLConnection;->setHostnameVerifier(Ljavax/net/ssl/HostnameVerifier;)V

    .line 880
    move-object/from16 v0, v19

    invoke-virtual {v15, v0}, Ljavax/net/ssl/HttpsURLConnection;->setSSLSocketFactory(Ljavax/net/ssl/SSLSocketFactory;)V

    .line 884
    .end local v15    # "https":Ljavax/net/ssl/HttpsURLConnection;
    :cond_1c
    if-nez v26, :cond_1d

    .line 885
    new-instance v26, Lorg/apache/cordova/PluginResult;

    .end local v26    # "result":Lorg/apache/cordova/PluginResult;
    sget-object v29, Lorg/apache/cordova/PluginResult$Status;->ERROR:Lorg/apache/cordova/PluginResult$Status;

    sget v30, Lorg/apache/cordova/filetransfer/FileTransfer;->CONNECTION_ERR:I

    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$4;->val$source:Ljava/lang/String;

    move-object/from16 v31, v0

    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$4;->val$target:Ljava/lang/String;

    move-object/from16 v32, v0

    const/16 v33, 0x0

    move/from16 v0, v30

    move-object/from16 v1, v31

    move-object/from16 v2, v32

    move-object/from16 v3, v33

    # invokes: Lorg/apache/cordova/filetransfer/FileTransfer;->createFileTransferError(ILjava/lang/String;Ljava/lang/String;Ljava/net/URLConnection;Ljava/lang/Throwable;)Lorg/json/JSONObject;
    invoke-static {v0, v1, v2, v7, v3}, Lorg/apache/cordova/filetransfer/FileTransfer;->access$500(ILjava/lang/String;Ljava/lang/String;Ljava/net/URLConnection;Ljava/lang/Throwable;)Lorg/json/JSONObject;

    move-result-object v30

    move-object/from16 v0, v26

    move-object/from16 v1, v29

    move-object/from16 v2, v30

    invoke-direct {v0, v1, v2}, Lorg/apache/cordova/PluginResult;-><init>(Lorg/apache/cordova/PluginResult$Status;Lorg/json/JSONObject;)V

    .line 888
    .restart local v26    # "result":Lorg/apache/cordova/PluginResult;
    :cond_1d
    if-nez v6, :cond_1e

    invoke-virtual/range {v26 .. v26}, Lorg/apache/cordova/PluginResult;->getStatus()I

    move-result v29

    sget-object v30, Lorg/apache/cordova/PluginResult$Status;->OK:Lorg/apache/cordova/PluginResult$Status;

    invoke-virtual/range {v30 .. v30}, Lorg/apache/cordova/PluginResult$Status;->ordinal()I

    move-result v30

    move/from16 v0, v29

    move/from16 v1, v30

    if-eq v0, v1, :cond_1e

    if-eqz v11, :cond_1e

    .line 889
    invoke-virtual {v11}, Ljava/io/File;->delete()Z

    .line 891
    :cond_1e
    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$4;->val$context:Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;

    move-object/from16 v29, v0

    goto/16 :goto_3

    .line 871
    .end local v9    # "e":Ljava/lang/Throwable;
    .end local v10    # "error":Lorg/json/JSONObject;
    :catchall_2
    move-exception v29

    :goto_c
    # getter for: Lorg/apache/cordova/filetransfer/FileTransfer;->activeRequests:Ljava/util/HashMap;
    invoke-static {}, Lorg/apache/cordova/filetransfer/FileTransfer;->access$600()Ljava/util/HashMap;

    move-result-object v30

    monitor-enter v30

    .line 872
    :try_start_1f
    # getter for: Lorg/apache/cordova/filetransfer/FileTransfer;->activeRequests:Ljava/util/HashMap;
    invoke-static {}, Lorg/apache/cordova/filetransfer/FileTransfer;->access$600()Ljava/util/HashMap;

    move-result-object v31

    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$4;->val$objectId:Ljava/lang/String;

    move-object/from16 v32, v0

    invoke-virtual/range {v31 .. v32}, Ljava/util/HashMap;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    .line 873
    monitor-exit v30
    :try_end_1f
    .catchall {:try_start_1f .. :try_end_1f} :catchall_3

    .line 875
    if-eqz v7, :cond_1f

    .line 877
    move-object/from16 v0, p0

    iget-boolean v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$4;->val$trustEveryone:Z

    move/from16 v30, v0

    if-eqz v30, :cond_1f

    move-object/from16 v0, p0

    iget-boolean v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$4;->val$useHttps:Z

    move/from16 v30, v0

    if-eqz v30, :cond_1f

    move-object v15, v7

    .line 878
    check-cast v15, Ljavax/net/ssl/HttpsURLConnection;

    .line 879
    .restart local v15    # "https":Ljavax/net/ssl/HttpsURLConnection;
    move-object/from16 v0, v18

    invoke-virtual {v15, v0}, Ljavax/net/ssl/HttpsURLConnection;->setHostnameVerifier(Ljavax/net/ssl/HostnameVerifier;)V

    .line 880
    move-object/from16 v0, v19

    invoke-virtual {v15, v0}, Ljavax/net/ssl/HttpsURLConnection;->setSSLSocketFactory(Ljavax/net/ssl/SSLSocketFactory;)V

    .line 884
    .end local v15    # "https":Ljavax/net/ssl/HttpsURLConnection;
    :cond_1f
    if-nez v26, :cond_20

    .line 885
    new-instance v26, Lorg/apache/cordova/PluginResult;

    .end local v26    # "result":Lorg/apache/cordova/PluginResult;
    sget-object v30, Lorg/apache/cordova/PluginResult$Status;->ERROR:Lorg/apache/cordova/PluginResult$Status;

    sget v31, Lorg/apache/cordova/filetransfer/FileTransfer;->CONNECTION_ERR:I

    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$4;->val$source:Ljava/lang/String;

    move-object/from16 v32, v0

    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$4;->val$target:Ljava/lang/String;

    move-object/from16 v33, v0

    const/16 v34, 0x0

    move/from16 v0, v31

    move-object/from16 v1, v32

    move-object/from16 v2, v33

    move-object/from16 v3, v34

    # invokes: Lorg/apache/cordova/filetransfer/FileTransfer;->createFileTransferError(ILjava/lang/String;Ljava/lang/String;Ljava/net/URLConnection;Ljava/lang/Throwable;)Lorg/json/JSONObject;
    invoke-static {v0, v1, v2, v7, v3}, Lorg/apache/cordova/filetransfer/FileTransfer;->access$500(ILjava/lang/String;Ljava/lang/String;Ljava/net/URLConnection;Ljava/lang/Throwable;)Lorg/json/JSONObject;

    move-result-object v31

    move-object/from16 v0, v26

    move-object/from16 v1, v30

    move-object/from16 v2, v31

    invoke-direct {v0, v1, v2}, Lorg/apache/cordova/PluginResult;-><init>(Lorg/apache/cordova/PluginResult$Status;Lorg/json/JSONObject;)V

    .line 888
    .restart local v26    # "result":Lorg/apache/cordova/PluginResult;
    :cond_20
    if-nez v6, :cond_21

    invoke-virtual/range {v26 .. v26}, Lorg/apache/cordova/PluginResult;->getStatus()I

    move-result v30

    sget-object v31, Lorg/apache/cordova/PluginResult$Status;->OK:Lorg/apache/cordova/PluginResult$Status;

    invoke-virtual/range {v31 .. v31}, Lorg/apache/cordova/PluginResult$Status;->ordinal()I

    move-result v31

    move/from16 v0, v30

    move/from16 v1, v31

    if-eq v0, v1, :cond_21

    if-eqz v11, :cond_21

    .line 889
    invoke-virtual {v11}, Ljava/io/File;->delete()Z

    .line 891
    :cond_21
    move-object/from16 v0, p0

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$4;->val$context:Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;

    move-object/from16 v30, v0

    move-object/from16 v0, v30

    move-object/from16 v1, v26

    invoke-virtual {v0, v1}, Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;->sendPluginResult(Lorg/apache/cordova/PluginResult;)V

    .line 871
    throw v29

    .line 873
    :catchall_3
    move-exception v29

    :try_start_20
    monitor-exit v30
    :try_end_20
    .catchall {:try_start_20 .. :try_end_20} :catchall_3

    throw v29

    .local v9, "e":Ljava/io/FileNotFoundException;
    .restart local v10    # "error":Lorg/json/JSONObject;
    :catchall_4
    move-exception v29

    :try_start_21
    monitor-exit v30
    :try_end_21
    .catchall {:try_start_21 .. :try_end_21} :catchall_4

    throw v29

    .local v9, "e":Ljava/io/IOException;
    :catchall_5
    move-exception v29

    :try_start_22
    monitor-exit v30
    :try_end_22
    .catchall {:try_start_22 .. :try_end_22} :catchall_5

    throw v29

    .end local v10    # "error":Lorg/json/JSONObject;
    .local v9, "e":Lorg/json/JSONException;
    :catchall_6
    move-exception v29

    :try_start_23
    monitor-exit v30
    :try_end_23
    .catchall {:try_start_23 .. :try_end_23} :catchall_6

    throw v29

    .local v9, "e":Ljava/lang/Throwable;
    .restart local v10    # "error":Lorg/json/JSONObject;
    :catchall_7
    move-exception v29

    :try_start_24
    monitor-exit v30
    :try_end_24
    .catchall {:try_start_24 .. :try_end_24} :catchall_7

    throw v29

    .line 811
    .end local v9    # "e":Ljava/lang/Throwable;
    .end local v10    # "error":Lorg/json/JSONObject;
    .end local v26    # "result":Lorg/apache/cordova/PluginResult;
    .restart local v23    # "progress":Lorg/apache/cordova/filetransfer/FileProgressResult;
    .restart local v27    # "result":Lorg/apache/cordova/PluginResult;
    :catchall_8
    move-exception v29

    :try_start_25
    monitor-exit v30
    :try_end_25
    .catchall {:try_start_25 .. :try_end_25} :catchall_8

    :try_start_26
    throw v29
    :try_end_26
    .catch Ljava/io/FileNotFoundException; {:try_start_26 .. :try_end_26} :catch_0
    .catch Ljava/io/IOException; {:try_start_26 .. :try_end_26} :catch_4
    .catch Lorg/json/JSONException; {:try_start_26 .. :try_end_26} :catch_5
    .catch Ljava/lang/Throwable; {:try_start_26 .. :try_end_26} :catch_6
    .catchall {:try_start_26 .. :try_end_26} :catchall_d

    .line 859
    :catch_4
    move-exception v9

    goto/16 :goto_9

    .line 811
    :catchall_9
    move-exception v29

    :try_start_27
    monitor-exit v30
    :try_end_27
    .catchall {:try_start_27 .. :try_end_27} :catchall_9

    :try_start_28
    throw v29
    :try_end_28
    .catch Ljava/io/FileNotFoundException; {:try_start_28 .. :try_end_28} :catch_0
    .catch Ljava/io/IOException; {:try_start_28 .. :try_end_28} :catch_4
    .catch Lorg/json/JSONException; {:try_start_28 .. :try_end_28} :catch_5
    .catch Ljava/lang/Throwable; {:try_start_28 .. :try_end_28} :catch_6
    .catchall {:try_start_28 .. :try_end_28} :catchall_d

    .line 863
    :catch_5
    move-exception v9

    goto/16 :goto_a

    .line 873
    :catchall_a
    move-exception v29

    :try_start_29
    monitor-exit v30
    :try_end_29
    .catchall {:try_start_29 .. :try_end_29} :catchall_a

    throw v29

    .line 811
    .restart local v4    # "buffer":[B
    .restart local v5    # "bytesRead":I
    :catchall_b
    move-exception v29

    :try_start_2a
    monitor-exit v30
    :try_end_2a
    .catchall {:try_start_2a .. :try_end_2a} :catchall_b

    :try_start_2b
    throw v29
    :try_end_2b
    .catch Ljava/io/FileNotFoundException; {:try_start_2b .. :try_end_2b} :catch_0
    .catch Ljava/io/IOException; {:try_start_2b .. :try_end_2b} :catch_4
    .catch Lorg/json/JSONException; {:try_start_2b .. :try_end_2b} :catch_5
    .catch Ljava/lang/Throwable; {:try_start_2b .. :try_end_2b} :catch_6
    .catchall {:try_start_2b .. :try_end_2b} :catchall_d

    .line 866
    .end local v4    # "buffer":[B
    .end local v5    # "bytesRead":I
    :catch_6
    move-exception v9

    goto/16 :goto_b

    .line 873
    .end local v27    # "result":Lorg/apache/cordova/PluginResult;
    .restart local v26    # "result":Lorg/apache/cordova/PluginResult;
    :catchall_c
    move-exception v29

    :try_start_2c
    monitor-exit v30
    :try_end_2c
    .catchall {:try_start_2c .. :try_end_2c} :catchall_c

    throw v29

    .line 871
    .end local v23    # "progress":Lorg/apache/cordova/filetransfer/FileProgressResult;
    .end local v26    # "result":Lorg/apache/cordova/PluginResult;
    .restart local v27    # "result":Lorg/apache/cordova/PluginResult;
    :catchall_d
    move-exception v29

    move-object/from16 v26, v27

    .end local v27    # "result":Lorg/apache/cordova/PluginResult;
    .restart local v26    # "result":Lorg/apache/cordova/PluginResult;
    goto/16 :goto_c

    .line 855
    :catch_7
    move-exception v9

    move-object/from16 v27, v26

    .end local v26    # "result":Lorg/apache/cordova/PluginResult;
    .restart local v27    # "result":Lorg/apache/cordova/PluginResult;
    goto/16 :goto_5

    .line 834
    .restart local v4    # "buffer":[B
    .restart local v5    # "bytesRead":I
    .restart local v21    # "pm":Lorg/apache/cordova/PluginManager;
    .restart local v23    # "progress":Lorg/apache/cordova/filetransfer/FileProgressResult;
    .restart local v28    # "webViewClass":Ljava/lang/Class;
    :catch_8
    move-exception v29

    goto/16 :goto_7

    .line 833
    :catch_9
    move-exception v29

    goto/16 :goto_7

    .line 827
    :catch_a
    move-exception v29

    goto/16 :goto_6

    .line 826
    :catch_b
    move-exception v29

    goto/16 :goto_6

    .line 825
    :catch_c
    move-exception v29

    goto/16 :goto_6

    .end local v4    # "buffer":[B
    .end local v5    # "bytesRead":I
    .end local v21    # "pm":Lorg/apache/cordova/PluginManager;
    .end local v28    # "webViewClass":Ljava/lang/Class;
    :cond_22
    move-object/from16 v26, v27

    .end local v27    # "result":Lorg/apache/cordova/PluginResult;
    .restart local v26    # "result":Lorg/apache/cordova/PluginResult;
    goto/16 :goto_2

    .end local v26    # "result":Lorg/apache/cordova/PluginResult;
    .restart local v27    # "result":Lorg/apache/cordova/PluginResult;
    :cond_23
    move-object/from16 v26, v27

    .end local v27    # "result":Lorg/apache/cordova/PluginResult;
    .restart local v26    # "result":Lorg/apache/cordova/PluginResult;
    goto/16 :goto_8
.end method
