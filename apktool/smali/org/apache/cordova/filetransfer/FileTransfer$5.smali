.class Lorg/apache/cordova/filetransfer/FileTransfer$5;
.super Ljava/lang/Object;
.source "FileTransfer.java"

# interfaces
.implements Ljava/lang/Runnable;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lorg/apache/cordova/filetransfer/FileTransfer;->abort(Ljava/lang/String;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lorg/apache/cordova/filetransfer/FileTransfer;

.field final synthetic val$context:Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;


# direct methods
.method constructor <init>(Lorg/apache/cordova/filetransfer/FileTransfer;Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;)V
    .locals 0

    .prologue
    .line 907
    iput-object p1, p0, Lorg/apache/cordova/filetransfer/FileTransfer$5;->this$0:Lorg/apache/cordova/filetransfer/FileTransfer;

    iput-object p2, p0, Lorg/apache/cordova/filetransfer/FileTransfer$5;->val$context:Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .locals 9

    .prologue
    .line 909
    iget-object v8, p0, Lorg/apache/cordova/filetransfer/FileTransfer$5;->val$context:Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;

    monitor-enter v8

    .line 910
    :try_start_0
    iget-object v0, p0, Lorg/apache/cordova/filetransfer/FileTransfer$5;->val$context:Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;

    iget-object v7, v0, Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;->targetFile:Ljava/io/File;

    .line 911
    .local v7, "file":Ljava/io/File;
    if-eqz v7, :cond_0

    .line 912
    invoke-virtual {v7}, Ljava/io/File;->delete()Z

    .line 915
    :cond_0
    sget v0, Lorg/apache/cordova/filetransfer/FileTransfer;->ABORTED_ERR:I

    iget-object v1, p0, Lorg/apache/cordova/filetransfer/FileTransfer$5;->val$context:Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;

    iget-object v1, v1, Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;->source:Ljava/lang/String;

    iget-object v2, p0, Lorg/apache/cordova/filetransfer/FileTransfer$5;->val$context:Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;

    iget-object v2, v2, Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;->target:Ljava/lang/String;

    const/4 v3, 0x0

    const/4 v4, -0x1

    invoke-static {v4}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v4

    const/4 v5, 0x0

    # invokes: Lorg/apache/cordova/filetransfer/FileTransfer;->createFileTransferError(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Throwable;)Lorg/json/JSONObject;
    invoke-static/range {v0 .. v5}, Lorg/apache/cordova/filetransfer/FileTransfer;->access$700(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Throwable;)Lorg/json/JSONObject;

    move-result-object v6

    .line 916
    .local v6, "error":Lorg/json/JSONObject;
    iget-object v0, p0, Lorg/apache/cordova/filetransfer/FileTransfer$5;->val$context:Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;

    new-instance v1, Lorg/apache/cordova/PluginResult;

    sget-object v2, Lorg/apache/cordova/PluginResult$Status;->ERROR:Lorg/apache/cordova/PluginResult$Status;

    invoke-direct {v1, v2, v6}, Lorg/apache/cordova/PluginResult;-><init>(Lorg/apache/cordova/PluginResult$Status;Lorg/json/JSONObject;)V

    invoke-virtual {v0, v1}, Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;->sendPluginResult(Lorg/apache/cordova/PluginResult;)V

    .line 917
    iget-object v0, p0, Lorg/apache/cordova/filetransfer/FileTransfer$5;->val$context:Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;

    const/4 v1, 0x1

    iput-boolean v1, v0, Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;->aborted:Z

    .line 918
    iget-object v0, p0, Lorg/apache/cordova/filetransfer/FileTransfer$5;->val$context:Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;->connection:Ljava/net/HttpURLConnection;

    if-eqz v0, :cond_1

    .line 919
    iget-object v0, p0, Lorg/apache/cordova/filetransfer/FileTransfer$5;->val$context:Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;

    iget-object v0, v0, Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;->connection:Ljava/net/HttpURLConnection;

    invoke-virtual {v0}, Ljava/net/HttpURLConnection;->disconnect()V

    .line 921
    :cond_1
    monitor-exit v8

    .line 922
    return-void

    .line 921
    .end local v6    # "error":Lorg/json/JSONObject;
    .end local v7    # "file":Ljava/io/File;
    :catchall_0
    move-exception v0

    monitor-exit v8
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw v0
.end method
