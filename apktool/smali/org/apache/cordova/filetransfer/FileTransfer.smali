.class public Lorg/apache/cordova/filetransfer/FileTransfer;
.super Lorg/apache/cordova/CordovaPlugin;
.source "FileTransfer.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lorg/apache/cordova/filetransfer/FileTransfer$SimpleTrackingInputStream;,
        Lorg/apache/cordova/filetransfer/FileTransfer$TrackingGZIPInputStream;,
        Lorg/apache/cordova/filetransfer/FileTransfer$ExposedGZIPInputStream;,
        Lorg/apache/cordova/filetransfer/FileTransfer$TrackingInputStream;,
        Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
    }
.end annotation


# static fields
.field public static ABORTED_ERR:I = 0x0

.field private static final BOUNDARY:Ljava/lang/String; = "+++++"

.field public static CONNECTION_ERR:I = 0x0

.field private static final DO_NOT_VERIFY:Ljavax/net/ssl/HostnameVerifier;

.field public static FILE_NOT_FOUND_ERR:I = 0x0

.field public static INVALID_URL_ERR:I = 0x0

.field private static final LINE_END:Ljava/lang/String; = "\r\n"

.field private static final LINE_START:Ljava/lang/String; = "--"

.field private static final LOG_TAG:Ljava/lang/String; = "FileTransfer"

.field private static final MAX_BUFFER_SIZE:I = 0x4000

.field public static NOT_MODIFIED_ERR:I

.field private static activeRequests:Ljava/util/HashMap;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/HashMap",
            "<",
            "Ljava/lang/String;",
            "Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;",
            ">;"
        }
    .end annotation
.end field

.field private static final trustAllCerts:[Ljavax/net/ssl/TrustManager;


# direct methods
.method static constructor <clinit>()V
    .locals 3

    .prologue
    const/4 v1, 0x1

    .line 75
    sput v1, Lorg/apache/cordova/filetransfer/FileTransfer;->FILE_NOT_FOUND_ERR:I

    .line 76
    const/4 v0, 0x2

    sput v0, Lorg/apache/cordova/filetransfer/FileTransfer;->INVALID_URL_ERR:I

    .line 77
    const/4 v0, 0x3

    sput v0, Lorg/apache/cordova/filetransfer/FileTransfer;->CONNECTION_ERR:I

    .line 78
    const/4 v0, 0x4

    sput v0, Lorg/apache/cordova/filetransfer/FileTransfer;->ABORTED_ERR:I

    .line 79
    const/4 v0, 0x5

    sput v0, Lorg/apache/cordova/filetransfer/FileTransfer;->NOT_MODIFIED_ERR:I

    .line 81
    new-instance v0, Ljava/util/HashMap;

    invoke-direct {v0}, Ljava/util/HashMap;-><init>()V

    sput-object v0, Lorg/apache/cordova/filetransfer/FileTransfer;->activeRequests:Ljava/util/HashMap;

    .line 530
    new-instance v0, Lorg/apache/cordova/filetransfer/FileTransfer$2;

    invoke-direct {v0}, Lorg/apache/cordova/filetransfer/FileTransfer$2;-><init>()V

    sput-object v0, Lorg/apache/cordova/filetransfer/FileTransfer;->DO_NOT_VERIFY:Ljavax/net/ssl/HostnameVerifier;

    .line 536
    new-array v0, v1, [Ljavax/net/ssl/TrustManager;

    const/4 v1, 0x0

    new-instance v2, Lorg/apache/cordova/filetransfer/FileTransfer$3;

    invoke-direct {v2}, Lorg/apache/cordova/filetransfer/FileTransfer$3;-><init>()V

    aput-object v2, v0, v1

    sput-object v0, Lorg/apache/cordova/filetransfer/FileTransfer;->trustAllCerts:[Ljavax/net/ssl/TrustManager;

    return-void
.end method

.method public constructor <init>()V
    .locals 0

    .prologue
    .line 68
    invoke-direct {p0}, Lorg/apache/cordova/CordovaPlugin;-><init>()V

    .line 145
    return-void
.end method

.method private abort(Ljava/lang/String;)V
    .locals 3
    .param p1, "objectId"    # Ljava/lang/String;

    .prologue
    .line 902
    sget-object v2, Lorg/apache/cordova/filetransfer/FileTransfer;->activeRequests:Ljava/util/HashMap;

    monitor-enter v2

    .line 903
    :try_start_0
    sget-object v1, Lorg/apache/cordova/filetransfer/FileTransfer;->activeRequests:Ljava/util/HashMap;

    invoke-virtual {v1, p1}, Ljava/util/HashMap;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;

    .line 904
    .local v0, "context":Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
    monitor-exit v2
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 905
    if-eqz v0, :cond_0

    .line 907
    iget-object v1, p0, Lorg/apache/cordova/filetransfer/FileTransfer;->cordova:Lorg/apache/cordova/CordovaInterface;

    invoke-interface {v1}, Lorg/apache/cordova/CordovaInterface;->getThreadPool()Ljava/util/concurrent/ExecutorService;

    move-result-object v1

    new-instance v2, Lorg/apache/cordova/filetransfer/FileTransfer$5;

    invoke-direct {v2, p0, v0}, Lorg/apache/cordova/filetransfer/FileTransfer$5;-><init>(Lorg/apache/cordova/filetransfer/FileTransfer;Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;)V

    invoke-interface {v1, v2}, Ljava/util/concurrent/ExecutorService;->execute(Ljava/lang/Runnable;)V

    .line 925
    :cond_0
    return-void

    .line 904
    .end local v0    # "context":Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
    :catchall_0
    move-exception v1

    :try_start_1
    monitor-exit v2
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw v1
.end method

.method static synthetic access$000(Ljavax/net/ssl/HttpsURLConnection;)Ljavax/net/ssl/SSLSocketFactory;
    .locals 1
    .param p0, "x0"    # Ljavax/net/ssl/HttpsURLConnection;

    .prologue
    .line 68
    invoke-static {p0}, Lorg/apache/cordova/filetransfer/FileTransfer;->trustAllHosts(Ljavax/net/ssl/HttpsURLConnection;)Ljavax/net/ssl/SSLSocketFactory;

    move-result-object v0

    return-object v0
.end method

.method static synthetic access$100()Ljavax/net/ssl/HostnameVerifier;
    .locals 1

    .prologue
    .line 68
    sget-object v0, Lorg/apache/cordova/filetransfer/FileTransfer;->DO_NOT_VERIFY:Ljavax/net/ssl/HostnameVerifier;

    return-object v0
.end method

.method static synthetic access$200(Ljava/net/URLConnection;Lorg/json/JSONObject;)V
    .locals 0
    .param p0, "x0"    # Ljava/net/URLConnection;
    .param p1, "x1"    # Lorg/json/JSONObject;

    .prologue
    .line 68
    invoke-static {p0, p1}, Lorg/apache/cordova/filetransfer/FileTransfer;->addHeadersToRequest(Ljava/net/URLConnection;Lorg/json/JSONObject;)V

    return-void
.end method

.method static synthetic access$300(Ljava/io/Closeable;)V
    .locals 0
    .param p0, "x0"    # Ljava/io/Closeable;

    .prologue
    .line 68
    invoke-static {p0}, Lorg/apache/cordova/filetransfer/FileTransfer;->safeClose(Ljava/io/Closeable;)V

    return-void
.end method

.method static synthetic access$400(Ljava/net/URLConnection;)Lorg/apache/cordova/filetransfer/FileTransfer$TrackingInputStream;
    .locals 1
    .param p0, "x0"    # Ljava/net/URLConnection;
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 68
    invoke-static {p0}, Lorg/apache/cordova/filetransfer/FileTransfer;->getInputStream(Ljava/net/URLConnection;)Lorg/apache/cordova/filetransfer/FileTransfer$TrackingInputStream;

    move-result-object v0

    return-object v0
.end method

.method static synthetic access$500(ILjava/lang/String;Ljava/lang/String;Ljava/net/URLConnection;Ljava/lang/Throwable;)Lorg/json/JSONObject;
    .locals 1
    .param p0, "x0"    # I
    .param p1, "x1"    # Ljava/lang/String;
    .param p2, "x2"    # Ljava/lang/String;
    .param p3, "x3"    # Ljava/net/URLConnection;
    .param p4, "x4"    # Ljava/lang/Throwable;

    .prologue
    .line 68
    invoke-static {p0, p1, p2, p3, p4}, Lorg/apache/cordova/filetransfer/FileTransfer;->createFileTransferError(ILjava/lang/String;Ljava/lang/String;Ljava/net/URLConnection;Ljava/lang/Throwable;)Lorg/json/JSONObject;

    move-result-object v0

    return-object v0
.end method

.method static synthetic access$600()Ljava/util/HashMap;
    .locals 1

    .prologue
    .line 68
    sget-object v0, Lorg/apache/cordova/filetransfer/FileTransfer;->activeRequests:Ljava/util/HashMap;

    return-object v0
.end method

.method static synthetic access$700(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Throwable;)Lorg/json/JSONObject;
    .locals 1
    .param p0, "x0"    # I
    .param p1, "x1"    # Ljava/lang/String;
    .param p2, "x2"    # Ljava/lang/String;
    .param p3, "x3"    # Ljava/lang/String;
    .param p4, "x4"    # Ljava/lang/Integer;
    .param p5, "x5"    # Ljava/lang/Throwable;

    .prologue
    .line 68
    invoke-static/range {p0 .. p5}, Lorg/apache/cordova/filetransfer/FileTransfer;->createFileTransferError(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Throwable;)Lorg/json/JSONObject;

    move-result-object v0

    return-object v0
.end method

.method private static addHeadersToRequest(Ljava/net/URLConnection;Lorg/json/JSONObject;)V
    .locals 5
    .param p0, "connection"    # Ljava/net/URLConnection;
    .param p1, "headers"    # Lorg/json/JSONObject;

    .prologue
    .line 198
    :try_start_0
    invoke-virtual {p1}, Lorg/json/JSONObject;->keys()Ljava/util/Iterator;

    move-result-object v3

    .local v3, "iter":Ljava/util/Iterator;, "Ljava/util/Iterator<*>;"
    :cond_0
    invoke-interface {v3}, Ljava/util/Iterator;->hasNext()Z

    move-result v4

    if-eqz v4, :cond_2

    .line 199
    invoke-interface {v3}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v4

    invoke-virtual {v4}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v0

    .line 200
    .local v0, "headerKey":Ljava/lang/String;
    invoke-virtual {p1, v0}, Lorg/json/JSONObject;->optJSONArray(Ljava/lang/String;)Lorg/json/JSONArray;

    move-result-object v1

    .line 201
    .local v1, "headerValues":Lorg/json/JSONArray;
    if-nez v1, :cond_1

    .line 202
    new-instance v1, Lorg/json/JSONArray;

    .end local v1    # "headerValues":Lorg/json/JSONArray;
    invoke-direct {v1}, Lorg/json/JSONArray;-><init>()V

    .line 203
    .restart local v1    # "headerValues":Lorg/json/JSONArray;
    invoke-virtual {p1, v0}, Lorg/json/JSONObject;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v1, v4}, Lorg/json/JSONArray;->put(Ljava/lang/Object;)Lorg/json/JSONArray;

    .line 205
    :cond_1
    const/4 v4, 0x0

    invoke-virtual {v1, v4}, Lorg/json/JSONArray;->getString(I)Ljava/lang/String;

    move-result-object v4

    invoke-virtual {p0, v0, v4}, Ljava/net/URLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    .line 206
    const/4 v2, 0x1

    .local v2, "i":I
    :goto_0
    invoke-virtual {v1}, Lorg/json/JSONArray;->length()I

    move-result v4

    if-ge v2, v4, :cond_0

    .line 207
    invoke-virtual {v1, v2}, Lorg/json/JSONArray;->getString(I)Ljava/lang/String;

    move-result-object v4

    invoke-virtual {p0, v0, v4}, Ljava/net/URLConnection;->addRequestProperty(Ljava/lang/String;Ljava/lang/String;)V
    :try_end_0
    .catch Lorg/json/JSONException; {:try_start_0 .. :try_end_0} :catch_0

    .line 206
    add-int/lit8 v2, v2, 0x1

    goto :goto_0

    .line 210
    .end local v0    # "headerKey":Ljava/lang/String;
    .end local v1    # "headerValues":Lorg/json/JSONArray;
    .end local v2    # "i":I
    .end local v3    # "iter":Ljava/util/Iterator;, "Ljava/util/Iterator<*>;"
    :catch_0
    move-exception v4

    .line 213
    :cond_2
    return-void
.end method

.method private static createFileTransferError(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Throwable;)Lorg/json/JSONObject;
    .locals 6
    .param p0, "errorCode"    # I
    .param p1, "source"    # Ljava/lang/String;
    .param p2, "target"    # Ljava/lang/String;
    .param p3, "body"    # Ljava/lang/String;
    .param p4, "httpStatus"    # Ljava/lang/Integer;
    .param p5, "throwable"    # Ljava/lang/Throwable;

    .prologue
    .line 616
    const/4 v1, 0x0

    .line 618
    .local v1, "error":Lorg/json/JSONObject;
    :try_start_0
    new-instance v2, Lorg/json/JSONObject;

    invoke-direct {v2}, Lorg/json/JSONObject;-><init>()V
    :try_end_0
    .catch Lorg/json/JSONException; {:try_start_0 .. :try_end_0} :catch_0

    .line 619
    .end local v1    # "error":Lorg/json/JSONObject;
    .local v2, "error":Lorg/json/JSONObject;
    :try_start_1
    const-string v4, "code"

    invoke-virtual {v2, v4, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;I)Lorg/json/JSONObject;

    .line 620
    const-string v4, "source"

    invoke-virtual {v2, v4, p1}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 621
    const-string v4, "target"

    invoke-virtual {v2, v4, p2}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 622
    if-eqz p3, :cond_0

    .line 624
    const-string v4, "body"

    invoke-virtual {v2, v4, p3}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 626
    :cond_0
    if-eqz p4, :cond_1

    .line 627
    const-string v4, "http_status"

    invoke-virtual {v2, v4, p4}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 629
    :cond_1
    if-eqz p5, :cond_4

    .line 630
    invoke-virtual {p5}, Ljava/lang/Throwable;->getMessage()Ljava/lang/String;

    move-result-object v3

    .line 631
    .local v3, "msg":Ljava/lang/String;
    if-eqz v3, :cond_2

    const-string v4, ""

    invoke-virtual {v4, v3}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v4

    if-eqz v4, :cond_3

    .line 632
    :cond_2
    invoke-virtual {p5}, Ljava/lang/Throwable;->toString()Ljava/lang/String;

    move-result-object v3

    .line 634
    :cond_3
    const-string v4, "exception"

    invoke-virtual {v2, v4, v3}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    :try_end_1
    .catch Lorg/json/JSONException; {:try_start_1 .. :try_end_1} :catch_1

    .end local v3    # "msg":Ljava/lang/String;
    :cond_4
    move-object v1, v2

    .line 639
    .end local v2    # "error":Lorg/json/JSONObject;
    .restart local v1    # "error":Lorg/json/JSONObject;
    :goto_0
    return-object v1

    .line 636
    :catch_0
    move-exception v0

    .line 637
    .local v0, "e":Lorg/json/JSONException;
    :goto_1
    const-string v4, "FileTransfer"

    invoke-virtual {v0}, Lorg/json/JSONException;->getMessage()Ljava/lang/String;

    move-result-object v5

    invoke-static {v4, v5, v0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    goto :goto_0

    .line 636
    .end local v0    # "e":Lorg/json/JSONException;
    .end local v1    # "error":Lorg/json/JSONObject;
    .restart local v2    # "error":Lorg/json/JSONObject;
    :catch_1
    move-exception v0

    move-object v1, v2

    .end local v2    # "error":Lorg/json/JSONObject;
    .restart local v1    # "error":Lorg/json/JSONObject;
    goto :goto_1
.end method

.method private static createFileTransferError(ILjava/lang/String;Ljava/lang/String;Ljava/net/URLConnection;Ljava/lang/Throwable;)Lorg/json/JSONObject;
    .locals 13
    .param p0, "errorCode"    # I
    .param p1, "source"    # Ljava/lang/String;
    .param p2, "target"    # Ljava/lang/String;
    .param p3, "connection"    # Ljava/net/URLConnection;
    .param p4, "throwable"    # Ljava/lang/Throwable;

    .prologue
    .line 575
    const/4 v10, 0x0

    .line 576
    .local v10, "httpStatus":I
    new-instance v7, Ljava/lang/StringBuilder;

    invoke-direct {v7}, Ljava/lang/StringBuilder;-><init>()V

    .line 577
    .local v7, "bodyBuilder":Ljava/lang/StringBuilder;
    const/4 v4, 0x0

    .line 578
    .local v4, "body":Ljava/lang/String;
    if-eqz p3, :cond_1

    .line 580
    :try_start_0
    move-object/from16 v0, p3

    instance-of v1, v0, Ljava/net/HttpURLConnection;

    if-eqz v1, :cond_1

    .line 581
    move-object/from16 v0, p3

    check-cast v0, Ljava/net/HttpURLConnection;

    move-object v1, v0

    invoke-virtual {v1}, Ljava/net/HttpURLConnection;->getResponseCode()I

    move-result v10

    .line 582
    check-cast p3, Ljava/net/HttpURLConnection;

    .end local p3    # "connection":Ljava/net/URLConnection;
    invoke-virtual/range {p3 .. p3}, Ljava/net/HttpURLConnection;->getErrorStream()Ljava/io/InputStream;

    move-result-object v9

    .line 583
    .local v9, "err":Ljava/io/InputStream;
    if-eqz v9, :cond_1

    .line 585
    new-instance v12, Ljava/io/BufferedReader;

    new-instance v1, Ljava/io/InputStreamReader;

    const-string v2, "UTF-8"

    invoke-direct {v1, v9, v2}, Ljava/io/InputStreamReader;-><init>(Ljava/io/InputStream;Ljava/lang/String;)V

    invoke-direct {v12, v1}, Ljava/io/BufferedReader;-><init>(Ljava/io/Reader;)V
    :try_end_0
    .catch Ljava/lang/Throwable; {:try_start_0 .. :try_end_0} :catch_0

    .line 587
    .local v12, "reader":Ljava/io/BufferedReader;
    :try_start_1
    invoke-virtual {v12}, Ljava/io/BufferedReader;->readLine()Ljava/lang/String;

    move-result-object v11

    .line 588
    .local v11, "line":Ljava/lang/String;
    :cond_0
    :goto_0
    if-eqz v11, :cond_2

    .line 589
    invoke-virtual {v7, v11}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 590
    invoke-virtual {v12}, Ljava/io/BufferedReader;->readLine()Ljava/lang/String;

    move-result-object v11

    .line 591
    if-eqz v11, :cond_0

    .line 592
    const/16 v1, 0xa

    invoke-virtual {v7, v1}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    goto :goto_0

    .line 597
    .end local v11    # "line":Ljava/lang/String;
    :catchall_0
    move-exception v1

    :try_start_2
    invoke-virtual {v12}, Ljava/io/BufferedReader;->close()V

    throw v1
    :try_end_2
    .catch Ljava/lang/Throwable; {:try_start_2 .. :try_end_2} :catch_0

    .line 602
    .end local v9    # "err":Ljava/io/InputStream;
    .end local v12    # "reader":Ljava/io/BufferedReader;
    :catch_0
    move-exception v8

    .line 603
    .local v8, "e":Ljava/lang/Throwable;
    const-string v1, "FileTransfer"

    const-string v2, "Error getting HTTP status code from connection."

    invoke-static {v1, v2, v8}, Landroid/util/Log;->w(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    .line 607
    .end local v8    # "e":Ljava/lang/Throwable;
    :cond_1
    :goto_1
    invoke-static {v10}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v5

    move v1, p0

    move-object v2, p1

    move-object v3, p2

    move-object/from16 v6, p4

    invoke-static/range {v1 .. v6}, Lorg/apache/cordova/filetransfer/FileTransfer;->createFileTransferError(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Throwable;)Lorg/json/JSONObject;

    move-result-object v1

    return-object v1

    .line 595
    .restart local v9    # "err":Ljava/io/InputStream;
    .restart local v11    # "line":Ljava/lang/String;
    .restart local v12    # "reader":Ljava/io/BufferedReader;
    :cond_2
    :try_start_3
    invoke-virtual {v7}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_0

    move-result-object v4

    .line 597
    :try_start_4
    invoke-virtual {v12}, Ljava/io/BufferedReader;->close()V
    :try_end_4
    .catch Ljava/lang/Throwable; {:try_start_4 .. :try_end_4} :catch_0

    goto :goto_1
.end method

.method private download(Ljava/lang/String;Ljava/lang/String;Lorg/json/JSONArray;Lorg/apache/cordova/CallbackContext;)V
    .locals 21
    .param p1, "source"    # Ljava/lang/String;
    .param p2, "target"    # Ljava/lang/String;
    .param p3, "args"    # Lorg/json/JSONArray;
    .param p4, "callbackContext"    # Lorg/apache/cordova/CallbackContext;
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Lorg/json/JSONException;
        }
    .end annotation

    .prologue
    .line 667
    const-string v3, "FileTransfer"

    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    const-string v7, "download "

    invoke-virtual {v4, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    move-object/from16 v0, p1

    invoke-virtual {v4, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    const-string v7, " to "

    invoke-virtual {v4, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    move-object/from16 v0, p2

    invoke-virtual {v4, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    invoke-static {v3, v4}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 669
    move-object/from16 v0, p0

    iget-object v3, v0, Lorg/apache/cordova/filetransfer/FileTransfer;->webView:Lorg/apache/cordova/CordovaWebView;

    invoke-virtual {v3}, Lorg/apache/cordova/CordovaWebView;->getResourceApi()Lorg/apache/cordova/CordovaResourceApi;

    move-result-object v6

    .line 671
    .local v6, "resourceApi":Lorg/apache/cordova/CordovaResourceApi;
    const/4 v3, 0x2

    move-object/from16 v0, p3

    invoke-virtual {v0, v3}, Lorg/json/JSONArray;->optBoolean(I)Z

    move-result v11

    .line 672
    .local v11, "trustEveryone":Z
    const/4 v3, 0x3

    move-object/from16 v0, p3

    invoke-virtual {v0, v3}, Lorg/json/JSONArray;->getString(I)Ljava/lang/String;

    move-result-object v15

    .line 673
    .local v15, "objectId":Ljava/lang/String;
    const/4 v3, 0x4

    move-object/from16 v0, p3

    invoke-virtual {v0, v3}, Lorg/json/JSONArray;->optJSONObject(I)Lorg/json/JSONObject;

    move-result-object v12

    .line 675
    .local v12, "headers":Lorg/json/JSONObject;
    invoke-static/range {p1 .. p1}, Landroid/net/Uri;->parse(Ljava/lang/String;)Landroid/net/Uri;

    move-result-object v3

    invoke-virtual {v6, v3}, Lorg/apache/cordova/CordovaResourceApi;->remapUri(Landroid/net/Uri;)Landroid/net/Uri;

    move-result-object v8

    .line 677
    .local v8, "sourceUri":Landroid/net/Uri;
    invoke-static/range {p2 .. p2}, Landroid/net/Uri;->parse(Ljava/lang/String;)Landroid/net/Uri;

    move-result-object v18

    .line 678
    .local v18, "tmpTarget":Landroid/net/Uri;
    invoke-virtual/range {v18 .. v18}, Landroid/net/Uri;->getScheme()Ljava/lang/String;

    move-result-object v3

    if-eqz v3, :cond_0

    .end local v18    # "tmpTarget":Landroid/net/Uri;
    :goto_0
    move-object/from16 v0, v18

    invoke-virtual {v6, v0}, Lorg/apache/cordova/CordovaResourceApi;->remapUri(Landroid/net/Uri;)Landroid/net/Uri;

    move-result-object v17

    .line 681
    .local v17, "targetUri":Landroid/net/Uri;
    invoke-static {v8}, Lorg/apache/cordova/CordovaResourceApi;->getUriType(Landroid/net/Uri;)I

    move-result v19

    .line 682
    .local v19, "uriType":I
    const/4 v3, 0x6

    move/from16 v0, v19

    if-ne v0, v3, :cond_1

    const/4 v10, 0x1

    .line 683
    .local v10, "useHttps":Z
    :goto_1
    if-nez v10, :cond_2

    const/4 v3, 0x5

    move/from16 v0, v19

    if-eq v0, v3, :cond_2

    const/4 v9, 0x1

    .line 684
    .local v9, "isLocalTransfer":Z
    :goto_2
    const/4 v3, -0x1

    move/from16 v0, v19

    if-ne v0, v3, :cond_3

    .line 685
    sget v3, Lorg/apache/cordova/filetransfer/FileTransfer;->INVALID_URL_ERR:I

    const/4 v6, 0x0

    const/4 v4, 0x0

    invoke-static {v4}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    .end local v6    # "resourceApi":Lorg/apache/cordova/CordovaResourceApi;
    move-result-object v7

    const/4 v8, 0x0

    move-object/from16 v4, p1

    move-object/from16 v5, p2

    invoke-static/range {v3 .. v8}, Lorg/apache/cordova/filetransfer/FileTransfer;->createFileTransferError(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Throwable;)Lorg/json/JSONObject;

    .end local v8    # "sourceUri":Landroid/net/Uri;
    move-result-object v16

    .line 686
    .local v16, "error":Lorg/json/JSONObject;
    const-string v3, "FileTransfer"

    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    const-string v7, "Unsupported URI: "

    invoke-virtual {v4, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    move-object/from16 v0, v17

    invoke-virtual {v4, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    invoke-static {v3, v4}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    .line 687
    new-instance v3, Lorg/apache/cordova/PluginResult;

    sget-object v4, Lorg/apache/cordova/PluginResult$Status;->IO_EXCEPTION:Lorg/apache/cordova/PluginResult$Status;

    move-object/from16 v0, v16

    invoke-direct {v3, v4, v0}, Lorg/apache/cordova/PluginResult;-><init>(Lorg/apache/cordova/PluginResult$Status;Lorg/json/JSONObject;)V

    move-object/from16 v0, p4

    invoke-virtual {v0, v3}, Lorg/apache/cordova/CallbackContext;->sendPluginResult(Lorg/apache/cordova/PluginResult;)V

    .line 895
    .end local v16    # "error":Lorg/json/JSONObject;
    :goto_3
    return-void

    .line 678
    .end local v9    # "isLocalTransfer":Z
    .end local v10    # "useHttps":Z
    .end local v17    # "targetUri":Landroid/net/Uri;
    .end local v19    # "uriType":I
    .restart local v6    # "resourceApi":Lorg/apache/cordova/CordovaResourceApi;
    .restart local v8    # "sourceUri":Landroid/net/Uri;
    .restart local v18    # "tmpTarget":Landroid/net/Uri;
    :cond_0
    new-instance v3, Ljava/io/File;

    move-object/from16 v0, p2

    invoke-direct {v3, v0}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    invoke-static {v3}, Landroid/net/Uri;->fromFile(Ljava/io/File;)Landroid/net/Uri;

    move-result-object v18

    goto :goto_0

    .line 682
    .end local v18    # "tmpTarget":Landroid/net/Uri;
    .restart local v17    # "targetUri":Landroid/net/Uri;
    .restart local v19    # "uriType":I
    :cond_1
    const/4 v10, 0x0

    goto :goto_1

    .line 683
    .restart local v10    # "useHttps":Z
    :cond_2
    const/4 v9, 0x0

    goto :goto_2

    .line 692
    .restart local v9    # "isLocalTransfer":Z
    :cond_3
    if-nez v9, :cond_4

    invoke-static/range {p1 .. p1}, Lorg/apache/cordova/Config;->isUrlWhiteListed(Ljava/lang/String;)Z

    move-result v3

    if-nez v3, :cond_4

    .line 693
    const-string v3, "FileTransfer"

    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    const-string v7, "Source URL is not in white list: \'"

    invoke-virtual {v4, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    move-object/from16 v0, p1

    invoke-virtual {v4, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    const-string v7, "\'"

    invoke-virtual {v4, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    invoke-static {v3, v4}, Landroid/util/Log;->w(Ljava/lang/String;Ljava/lang/String;)I

    .line 694
    sget v3, Lorg/apache/cordova/filetransfer/FileTransfer;->CONNECTION_ERR:I

    const/4 v6, 0x0

    const/16 v4, 0x191

    invoke-static {v4}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    .end local v6    # "resourceApi":Lorg/apache/cordova/CordovaResourceApi;
    move-result-object v7

    const/4 v8, 0x0

    move-object/from16 v4, p1

    move-object/from16 v5, p2

    invoke-static/range {v3 .. v8}, Lorg/apache/cordova/filetransfer/FileTransfer;->createFileTransferError(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Throwable;)Lorg/json/JSONObject;

    .end local v8    # "sourceUri":Landroid/net/Uri;
    move-result-object v16

    .line 695
    .restart local v16    # "error":Lorg/json/JSONObject;
    new-instance v3, Lorg/apache/cordova/PluginResult;

    sget-object v4, Lorg/apache/cordova/PluginResult$Status;->IO_EXCEPTION:Lorg/apache/cordova/PluginResult$Status;

    move-object/from16 v0, v16

    invoke-direct {v3, v4, v0}, Lorg/apache/cordova/PluginResult;-><init>(Lorg/apache/cordova/PluginResult$Status;Lorg/json/JSONObject;)V

    move-object/from16 v0, p4

    invoke-virtual {v0, v3}, Lorg/apache/cordova/CallbackContext;->sendPluginResult(Lorg/apache/cordova/PluginResult;)V

    goto :goto_3

    .line 700
    .end local v16    # "error":Lorg/json/JSONObject;
    .restart local v6    # "resourceApi":Lorg/apache/cordova/CordovaResourceApi;
    .restart local v8    # "sourceUri":Landroid/net/Uri;
    :cond_4
    new-instance v5, Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;

    move-object/from16 v0, p1

    move-object/from16 v1, p2

    move-object/from16 v2, p4

    invoke-direct {v5, v0, v1, v2}, Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;-><init>(Ljava/lang/String;Ljava/lang/String;Lorg/apache/cordova/CallbackContext;)V

    .line 701
    .local v5, "context":Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
    sget-object v4, Lorg/apache/cordova/filetransfer/FileTransfer;->activeRequests:Ljava/util/HashMap;

    monitor-enter v4

    .line 702
    :try_start_0
    sget-object v3, Lorg/apache/cordova/filetransfer/FileTransfer;->activeRequests:Ljava/util/HashMap;

    invoke-virtual {v3, v15, v5}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 703
    monitor-exit v4
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 705
    move-object/from16 v0, p0

    iget-object v3, v0, Lorg/apache/cordova/filetransfer/FileTransfer;->cordova:Lorg/apache/cordova/CordovaInterface;

    invoke-interface {v3}, Lorg/apache/cordova/CordovaInterface;->getThreadPool()Ljava/util/concurrent/ExecutorService;

    move-result-object v20

    new-instance v3, Lorg/apache/cordova/filetransfer/FileTransfer$4;

    move-object/from16 v4, p0

    move-object/from16 v7, v17

    move-object/from16 v13, p1

    move-object/from16 v14, p2

    invoke-direct/range {v3 .. v15}, Lorg/apache/cordova/filetransfer/FileTransfer$4;-><init>(Lorg/apache/cordova/filetransfer/FileTransfer;Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;Lorg/apache/cordova/CordovaResourceApi;Landroid/net/Uri;Landroid/net/Uri;ZZZLorg/json/JSONObject;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V

    move-object/from16 v0, v20

    invoke-interface {v0, v3}, Ljava/util/concurrent/ExecutorService;->execute(Ljava/lang/Runnable;)V

    goto/16 :goto_3

    .line 703
    :catchall_0
    move-exception v3

    :try_start_1
    monitor-exit v4
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw v3
.end method

.method private static getArgument(Lorg/json/JSONArray;ILjava/lang/String;)Ljava/lang/String;
    .locals 2
    .param p0, "args"    # Lorg/json/JSONArray;
    .param p1, "position"    # I
    .param p2, "defaultString"    # Ljava/lang/String;

    .prologue
    .line 650
    move-object v0, p2

    .line 651
    .local v0, "arg":Ljava/lang/String;
    invoke-virtual {p0}, Lorg/json/JSONArray;->length()I

    move-result v1

    if-le v1, p1, :cond_1

    .line 652
    invoke-virtual {p0, p1}, Lorg/json/JSONArray;->optString(I)Ljava/lang/String;

    move-result-object v0

    .line 653
    if-eqz v0, :cond_0

    const-string v1, "null"

    invoke-virtual {v1, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_1

    .line 654
    :cond_0
    move-object v0, p2

    .line 657
    :cond_1
    return-object v0
.end method

.method private static getInputStream(Ljava/net/URLConnection;)Lorg/apache/cordova/filetransfer/FileTransfer$TrackingInputStream;
    .locals 4
    .param p0, "conn"    # Ljava/net/URLConnection;
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 522
    invoke-virtual {p0}, Ljava/net/URLConnection;->getContentEncoding()Ljava/lang/String;

    move-result-object v0

    .line 523
    .local v0, "encoding":Ljava/lang/String;
    if-eqz v0, :cond_0

    const-string v1, "gzip"

    invoke-virtual {v0, v1}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v1

    if-eqz v1, :cond_0

    .line 524
    new-instance v1, Lorg/apache/cordova/filetransfer/FileTransfer$TrackingGZIPInputStream;

    new-instance v2, Lorg/apache/cordova/filetransfer/FileTransfer$ExposedGZIPInputStream;

    invoke-virtual {p0}, Ljava/net/URLConnection;->getInputStream()Ljava/io/InputStream;

    move-result-object v3

    invoke-direct {v2, v3}, Lorg/apache/cordova/filetransfer/FileTransfer$ExposedGZIPInputStream;-><init>(Ljava/io/InputStream;)V

    invoke-direct {v1, v2}, Lorg/apache/cordova/filetransfer/FileTransfer$TrackingGZIPInputStream;-><init>(Lorg/apache/cordova/filetransfer/FileTransfer$ExposedGZIPInputStream;)V

    .line 526
    :goto_0
    return-object v1

    :cond_0
    new-instance v1, Lorg/apache/cordova/filetransfer/FileTransfer$SimpleTrackingInputStream;

    invoke-virtual {p0}, Ljava/net/URLConnection;->getInputStream()Ljava/io/InputStream;

    move-result-object v2

    invoke-direct {v1, v2}, Lorg/apache/cordova/filetransfer/FileTransfer$SimpleTrackingInputStream;-><init>(Ljava/io/InputStream;)V

    goto :goto_0
.end method

.method private static safeClose(Ljava/io/Closeable;)V
    .locals 1
    .param p0, "stream"    # Ljava/io/Closeable;

    .prologue
    .line 513
    if-eqz p0, :cond_0

    .line 515
    :try_start_0
    invoke-interface {p0}, Ljava/io/Closeable;->close()V
    :try_end_0
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_0

    .line 519
    :cond_0
    :goto_0
    return-void

    .line 516
    :catch_0
    move-exception v0

    goto :goto_0
.end method

.method private static trustAllHosts(Ljavax/net/ssl/HttpsURLConnection;)Ljavax/net/ssl/SSLSocketFactory;
    .locals 7
    .param p0, "connection"    # Ljavax/net/ssl/HttpsURLConnection;

    .prologue
    .line 560
    invoke-virtual {p0}, Ljavax/net/ssl/HttpsURLConnection;->getSSLSocketFactory()Ljavax/net/ssl/SSLSocketFactory;

    move-result-object v2

    .line 563
    .local v2, "oldFactory":Ljavax/net/ssl/SSLSocketFactory;
    :try_start_0
    const-string v4, "TLS"

    invoke-static {v4}, Ljavax/net/ssl/SSLContext;->getInstance(Ljava/lang/String;)Ljavax/net/ssl/SSLContext;

    move-result-object v3

    .line 564
    .local v3, "sc":Ljavax/net/ssl/SSLContext;
    const/4 v4, 0x0

    sget-object v5, Lorg/apache/cordova/filetransfer/FileTransfer;->trustAllCerts:[Ljavax/net/ssl/TrustManager;

    new-instance v6, Ljava/security/SecureRandom;

    invoke-direct {v6}, Ljava/security/SecureRandom;-><init>()V

    invoke-virtual {v3, v4, v5, v6}, Ljavax/net/ssl/SSLContext;->init([Ljavax/net/ssl/KeyManager;[Ljavax/net/ssl/TrustManager;Ljava/security/SecureRandom;)V

    .line 565
    invoke-virtual {v3}, Ljavax/net/ssl/SSLContext;->getSocketFactory()Ljavax/net/ssl/SSLSocketFactory;

    move-result-object v1

    .line 566
    .local v1, "newFactory":Ljavax/net/ssl/SSLSocketFactory;
    invoke-virtual {p0, v1}, Ljavax/net/ssl/HttpsURLConnection;->setSSLSocketFactory(Ljavax/net/ssl/SSLSocketFactory;)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    .line 570
    .end local v1    # "newFactory":Ljavax/net/ssl/SSLSocketFactory;
    .end local v3    # "sc":Ljavax/net/ssl/SSLContext;
    :goto_0
    return-object v2

    .line 567
    :catch_0
    move-exception v0

    .line 568
    .local v0, "e":Ljava/lang/Exception;
    const-string v4, "FileTransfer"

    invoke-virtual {v0}, Ljava/lang/Exception;->getMessage()Ljava/lang/String;

    move-result-object v5

    invoke-static {v4, v5, v0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    goto :goto_0
.end method

.method private upload(Ljava/lang/String;Ljava/lang/String;Lorg/json/JSONArray;Lorg/apache/cordova/CallbackContext;)V
    .locals 26
    .param p1, "source"    # Ljava/lang/String;
    .param p2, "target"    # Ljava/lang/String;
    .param p3, "args"    # Lorg/json/JSONArray;
    .param p4, "callbackContext"    # Lorg/apache/cordova/CallbackContext;
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Lorg/json/JSONException;
        }
    .end annotation

    .prologue
    .line 229
    const-string v3, "FileTransfer"

    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    const-string v7, "upload "

    invoke-virtual {v4, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    move-object/from16 v0, p1

    invoke-virtual {v4, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    const-string v7, " to "

    invoke-virtual {v4, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    move-object/from16 v0, p2

    invoke-virtual {v4, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    invoke-static {v3, v4}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 232
    const/4 v3, 0x2

    const-string v4, "file"

    move-object/from16 v0, p3

    invoke-static {v0, v3, v4}, Lorg/apache/cordova/filetransfer/FileTransfer;->getArgument(Lorg/json/JSONArray;ILjava/lang/String;)Ljava/lang/String;

    move-result-object v14

    .line 233
    .local v14, "fileKey":Ljava/lang/String;
    const/4 v3, 0x3

    const-string v4, "image.jpg"

    move-object/from16 v0, p3

    invoke-static {v0, v3, v4}, Lorg/apache/cordova/filetransfer/FileTransfer;->getArgument(Lorg/json/JSONArray;ILjava/lang/String;)Ljava/lang/String;

    move-result-object v15

    .line 234
    .local v15, "fileName":Ljava/lang/String;
    const/4 v3, 0x4

    const-string v4, "image/jpeg"

    move-object/from16 v0, p3

    invoke-static {v0, v3, v4}, Lorg/apache/cordova/filetransfer/FileTransfer;->getArgument(Lorg/json/JSONArray;ILjava/lang/String;)Ljava/lang/String;

    move-result-object v16

    .line 235
    .local v16, "mimeType":Ljava/lang/String;
    const/4 v3, 0x5

    move-object/from16 v0, p3

    invoke-virtual {v0, v3}, Lorg/json/JSONArray;->optJSONObject(I)Lorg/json/JSONObject;

    move-result-object v3

    if-nez v3, :cond_1

    new-instance v13, Lorg/json/JSONObject;

    invoke-direct {v13}, Lorg/json/JSONObject;-><init>()V

    .line 236
    .local v13, "params":Lorg/json/JSONObject;
    :goto_0
    const/4 v3, 0x6

    move-object/from16 v0, p3

    invoke-virtual {v0, v3}, Lorg/json/JSONArray;->optBoolean(I)Z

    move-result v9

    .line 238
    .local v9, "trustEveryone":Z
    const/4 v3, 0x7

    move-object/from16 v0, p3

    invoke-virtual {v0, v3}, Lorg/json/JSONArray;->optBoolean(I)Z

    move-result v3

    if-nez v3, :cond_0

    const/4 v3, 0x7

    move-object/from16 v0, p3

    invoke-virtual {v0, v3}, Lorg/json/JSONArray;->isNull(I)Z

    move-result v3

    if-eqz v3, :cond_2

    :cond_0
    const/16 v18, 0x1

    .line 240
    .local v18, "chunkedMode":Z
    :goto_1
    const/16 v3, 0x8

    move-object/from16 v0, p3

    invoke-virtual {v0, v3}, Lorg/json/JSONArray;->optJSONObject(I)Lorg/json/JSONObject;

    move-result-object v3

    if-nez v3, :cond_3

    const-string v3, "headers"

    invoke-virtual {v13, v3}, Lorg/json/JSONObject;->optJSONObject(Ljava/lang/String;)Lorg/json/JSONObject;

    move-result-object v12

    .line 241
    .local v12, "headers":Lorg/json/JSONObject;
    :goto_2
    const/16 v3, 0x9

    move-object/from16 v0, p3

    invoke-virtual {v0, v3}, Lorg/json/JSONArray;->getString(I)Ljava/lang/String;

    move-result-object v20

    .line 242
    .local v20, "objectId":Ljava/lang/String;
    const/16 v3, 0xa

    const-string v4, "POST"

    move-object/from16 v0, p3

    invoke-static {v0, v3, v4}, Lorg/apache/cordova/filetransfer/FileTransfer;->getArgument(Lorg/json/JSONArray;ILjava/lang/String;)Ljava/lang/String;

    move-result-object v10

    .line 244
    .local v10, "httpMethod":Ljava/lang/String;
    move-object/from16 v0, p0

    iget-object v3, v0, Lorg/apache/cordova/filetransfer/FileTransfer;->webView:Lorg/apache/cordova/CordovaWebView;

    invoke-virtual {v3}, Lorg/apache/cordova/CordovaWebView;->getResourceApi()Lorg/apache/cordova/CordovaResourceApi;

    move-result-object v6

    .line 246
    .local v6, "resourceApi":Lorg/apache/cordova/CordovaResourceApi;
    const-string v3, "FileTransfer"

    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    const-string v7, "fileKey: "

    invoke-virtual {v4, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4, v14}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    invoke-static {v3, v4}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 247
    const-string v3, "FileTransfer"

    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    const-string v7, "fileName: "

    invoke-virtual {v4, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4, v15}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    invoke-static {v3, v4}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 248
    const-string v3, "FileTransfer"

    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    const-string v7, "mimeType: "

    invoke-virtual {v4, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    move-object/from16 v0, v16

    invoke-virtual {v4, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    invoke-static {v3, v4}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 249
    const-string v3, "FileTransfer"

    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    const-string v7, "params: "

    invoke-virtual {v4, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4, v13}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    invoke-static {v3, v4}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 250
    const-string v3, "FileTransfer"

    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    const-string v7, "trustEveryone: "

    invoke-virtual {v4, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4, v9}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    invoke-static {v3, v4}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 251
    const-string v3, "FileTransfer"

    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    const-string v7, "chunkedMode: "

    invoke-virtual {v4, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    move/from16 v0, v18

    invoke-virtual {v4, v0}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    invoke-static {v3, v4}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 252
    const-string v3, "FileTransfer"

    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    const-string v7, "headers: "

    invoke-virtual {v4, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4, v12}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    invoke-static {v3, v4}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 253
    const-string v3, "FileTransfer"

    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    const-string v7, "objectId: "

    invoke-virtual {v4, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    move-object/from16 v0, v20

    invoke-virtual {v4, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    invoke-static {v3, v4}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 254
    const-string v3, "FileTransfer"

    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    const-string v7, "httpMethod: "

    invoke-virtual {v4, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4, v10}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    invoke-static {v3, v4}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 256
    invoke-static/range {p2 .. p2}, Landroid/net/Uri;->parse(Ljava/lang/String;)Landroid/net/Uri;

    move-result-object v3

    invoke-virtual {v6, v3}, Lorg/apache/cordova/CordovaResourceApi;->remapUri(Landroid/net/Uri;)Landroid/net/Uri;

    move-result-object v22

    .line 258
    .local v22, "targetUri":Landroid/net/Uri;
    invoke-static/range {p1 .. p1}, Landroid/net/Uri;->parse(Ljava/lang/String;)Landroid/net/Uri;

    move-result-object v23

    .line 259
    .local v23, "tmpSrc":Landroid/net/Uri;
    invoke-virtual/range {v23 .. v23}, Landroid/net/Uri;->getScheme()Ljava/lang/String;

    move-result-object v3

    if-eqz v3, :cond_4

    .end local v23    # "tmpSrc":Landroid/net/Uri;
    :goto_3
    move-object/from16 v0, v23

    invoke-virtual {v6, v0}, Lorg/apache/cordova/CordovaResourceApi;->remapUri(Landroid/net/Uri;)Landroid/net/Uri;

    move-result-object v17

    .line 262
    .local v17, "sourceUri":Landroid/net/Uri;
    invoke-static/range {v22 .. v22}, Lorg/apache/cordova/CordovaResourceApi;->getUriType(Landroid/net/Uri;)I

    move-result v24

    .line 263
    .local v24, "uriType":I
    const/4 v3, 0x6

    move/from16 v0, v24

    if-ne v0, v3, :cond_5

    const/4 v8, 0x1

    .line 264
    .local v8, "useHttps":Z
    :goto_4
    const/4 v3, 0x5

    move/from16 v0, v24

    if-eq v0, v3, :cond_6

    if-nez v8, :cond_6

    .line 265
    sget v3, Lorg/apache/cordova/filetransfer/FileTransfer;->INVALID_URL_ERR:I

    const/4 v6, 0x0

    const/4 v4, 0x0

    invoke-static {v4}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    .end local v6    # "resourceApi":Lorg/apache/cordova/CordovaResourceApi;
    move-result-object v7

    const/4 v8, 0x0

    move-object/from16 v4, p1

    move-object/from16 v5, p2

    invoke-static/range {v3 .. v8}, Lorg/apache/cordova/filetransfer/FileTransfer;->createFileTransferError(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Throwable;)Lorg/json/JSONObject;

    .end local v8    # "useHttps":Z
    move-result-object v21

    .line 266
    .local v21, "error":Lorg/json/JSONObject;
    const-string v3, "FileTransfer"

    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    const-string v7, "Unsupported URI: "

    invoke-virtual {v4, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    move-object/from16 v0, v22

    invoke-virtual {v4, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    invoke-static {v3, v4}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    .line 267
    new-instance v3, Lorg/apache/cordova/PluginResult;

    sget-object v4, Lorg/apache/cordova/PluginResult$Status;->IO_EXCEPTION:Lorg/apache/cordova/PluginResult$Status;

    move-object/from16 v0, v21

    invoke-direct {v3, v4, v0}, Lorg/apache/cordova/PluginResult;-><init>(Lorg/apache/cordova/PluginResult$Status;Lorg/json/JSONObject;)V

    move-object/from16 v0, p4

    invoke-virtual {v0, v3}, Lorg/apache/cordova/CallbackContext;->sendPluginResult(Lorg/apache/cordova/PluginResult;)V

    .line 510
    .end local v21    # "error":Lorg/json/JSONObject;
    :goto_5
    return-void

    .line 235
    .end local v9    # "trustEveryone":Z
    .end local v10    # "httpMethod":Ljava/lang/String;
    .end local v12    # "headers":Lorg/json/JSONObject;
    .end local v13    # "params":Lorg/json/JSONObject;
    .end local v17    # "sourceUri":Landroid/net/Uri;
    .end local v18    # "chunkedMode":Z
    .end local v20    # "objectId":Ljava/lang/String;
    .end local v22    # "targetUri":Landroid/net/Uri;
    .end local v24    # "uriType":I
    :cond_1
    const/4 v3, 0x5

    move-object/from16 v0, p3

    invoke-virtual {v0, v3}, Lorg/json/JSONArray;->optJSONObject(I)Lorg/json/JSONObject;

    move-result-object v13

    goto/16 :goto_0

    .line 238
    .restart local v9    # "trustEveryone":Z
    .restart local v13    # "params":Lorg/json/JSONObject;
    :cond_2
    const/16 v18, 0x0

    goto/16 :goto_1

    .line 240
    .restart local v18    # "chunkedMode":Z
    :cond_3
    const/16 v3, 0x8

    move-object/from16 v0, p3

    invoke-virtual {v0, v3}, Lorg/json/JSONArray;->optJSONObject(I)Lorg/json/JSONObject;

    move-result-object v12

    goto/16 :goto_2

    .line 259
    .restart local v6    # "resourceApi":Lorg/apache/cordova/CordovaResourceApi;
    .restart local v10    # "httpMethod":Ljava/lang/String;
    .restart local v12    # "headers":Lorg/json/JSONObject;
    .restart local v20    # "objectId":Ljava/lang/String;
    .restart local v22    # "targetUri":Landroid/net/Uri;
    .restart local v23    # "tmpSrc":Landroid/net/Uri;
    :cond_4
    new-instance v3, Ljava/io/File;

    move-object/from16 v0, p1

    invoke-direct {v3, v0}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    invoke-static {v3}, Landroid/net/Uri;->fromFile(Ljava/io/File;)Landroid/net/Uri;

    move-result-object v23

    goto :goto_3

    .line 263
    .end local v23    # "tmpSrc":Landroid/net/Uri;
    .restart local v17    # "sourceUri":Landroid/net/Uri;
    .restart local v24    # "uriType":I
    :cond_5
    const/4 v8, 0x0

    goto :goto_4

    .line 271
    .restart local v8    # "useHttps":Z
    :cond_6
    new-instance v5, Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;

    move-object/from16 v0, p1

    move-object/from16 v1, p2

    move-object/from16 v2, p4

    invoke-direct {v5, v0, v1, v2}, Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;-><init>(Ljava/lang/String;Ljava/lang/String;Lorg/apache/cordova/CallbackContext;)V

    .line 272
    .local v5, "context":Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
    sget-object v4, Lorg/apache/cordova/filetransfer/FileTransfer;->activeRequests:Ljava/util/HashMap;

    monitor-enter v4

    .line 273
    :try_start_0
    sget-object v3, Lorg/apache/cordova/filetransfer/FileTransfer;->activeRequests:Ljava/util/HashMap;

    move-object/from16 v0, v20

    invoke-virtual {v3, v0, v5}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 274
    monitor-exit v4
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 276
    move-object/from16 v0, p0

    iget-object v3, v0, Lorg/apache/cordova/filetransfer/FileTransfer;->cordova:Lorg/apache/cordova/CordovaInterface;

    invoke-interface {v3}, Lorg/apache/cordova/CordovaInterface;->getThreadPool()Ljava/util/concurrent/ExecutorService;

    move-result-object v25

    new-instance v3, Lorg/apache/cordova/filetransfer/FileTransfer$1;

    move-object/from16 v4, p0

    move-object/from16 v7, v22

    move-object/from16 v11, p2

    move-object/from16 v19, p1

    invoke-direct/range {v3 .. v20}, Lorg/apache/cordova/filetransfer/FileTransfer$1;-><init>(Lorg/apache/cordova/filetransfer/FileTransfer;Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;Lorg/apache/cordova/CordovaResourceApi;Landroid/net/Uri;ZZLjava/lang/String;Ljava/lang/String;Lorg/json/JSONObject;Lorg/json/JSONObject;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Landroid/net/Uri;ZLjava/lang/String;Ljava/lang/String;)V

    move-object/from16 v0, v25

    invoke-interface {v0, v3}, Ljava/util/concurrent/ExecutorService;->execute(Ljava/lang/Runnable;)V

    goto :goto_5

    .line 274
    :catchall_0
    move-exception v3

    :try_start_1
    monitor-exit v4
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw v3
.end method


# virtual methods
.method public execute(Ljava/lang/String;Lorg/json/JSONArray;Lorg/apache/cordova/CallbackContext;)Z
    .locals 6
    .param p1, "action"    # Ljava/lang/String;
    .param p2, "args"    # Lorg/json/JSONArray;
    .param p3, "callbackContext"    # Lorg/apache/cordova/CallbackContext;
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Lorg/json/JSONException;
        }
    .end annotation

    .prologue
    const/4 v3, 0x1

    const/4 v4, 0x0

    .line 177
    const-string v5, "upload"

    invoke-virtual {p1, v5}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v5

    if-nez v5, :cond_0

    const-string v5, "download"

    invoke-virtual {p1, v5}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v5

    if-eqz v5, :cond_2

    .line 178
    :cond_0
    invoke-virtual {p2, v4}, Lorg/json/JSONArray;->getString(I)Ljava/lang/String;

    move-result-object v1

    .line 179
    .local v1, "source":Ljava/lang/String;
    invoke-virtual {p2, v3}, Lorg/json/JSONArray;->getString(I)Ljava/lang/String;

    move-result-object v2

    .line 181
    .local v2, "target":Ljava/lang/String;
    const-string v4, "upload"

    invoke-virtual {p1, v4}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v4

    if-eqz v4, :cond_1

    .line 182
    invoke-direct {p0, v1, v2, p2, p3}, Lorg/apache/cordova/filetransfer/FileTransfer;->upload(Ljava/lang/String;Ljava/lang/String;Lorg/json/JSONArray;Lorg/apache/cordova/CallbackContext;)V

    .line 193
    .end local v1    # "source":Ljava/lang/String;
    .end local v2    # "target":Ljava/lang/String;
    :goto_0
    return v3

    .line 184
    .restart local v1    # "source":Ljava/lang/String;
    .restart local v2    # "target":Ljava/lang/String;
    :cond_1
    invoke-direct {p0, v1, v2, p2, p3}, Lorg/apache/cordova/filetransfer/FileTransfer;->download(Ljava/lang/String;Ljava/lang/String;Lorg/json/JSONArray;Lorg/apache/cordova/CallbackContext;)V

    goto :goto_0

    .line 187
    .end local v1    # "source":Ljava/lang/String;
    .end local v2    # "target":Ljava/lang/String;
    :cond_2
    const-string v5, "abort"

    invoke-virtual {p1, v5}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v5

    if-eqz v5, :cond_3

    .line 188
    invoke-virtual {p2, v4}, Lorg/json/JSONArray;->getString(I)Ljava/lang/String;

    move-result-object v0

    .line 189
    .local v0, "objectId":Ljava/lang/String;
    invoke-direct {p0, v0}, Lorg/apache/cordova/filetransfer/FileTransfer;->abort(Ljava/lang/String;)V

    .line 190
    invoke-virtual {p3}, Lorg/apache/cordova/CallbackContext;->success()V

    goto :goto_0

    .end local v0    # "objectId":Ljava/lang/String;
    :cond_3
    move v3, v4

    .line 193
    goto :goto_0
.end method
