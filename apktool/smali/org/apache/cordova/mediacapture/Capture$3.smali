.class Lorg/apache/cordova/mediacapture/Capture$3;
.super Ljava/lang/Object;
.source "Capture.java"

# interfaces
.implements Ljava/lang/Runnable;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lorg/apache/cordova/mediacapture/Capture;->onActivityResult(IILandroid/content/Intent;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lorg/apache/cordova/mediacapture/Capture;

.field final synthetic val$intent:Landroid/content/Intent;

.field final synthetic val$that:Lorg/apache/cordova/mediacapture/Capture;


# direct methods
.method constructor <init>(Lorg/apache/cordova/mediacapture/Capture;Landroid/content/Intent;Lorg/apache/cordova/mediacapture/Capture;)V
    .locals 0

    .prologue
    .line 357
    iput-object p1, p0, Lorg/apache/cordova/mediacapture/Capture$3;->this$0:Lorg/apache/cordova/mediacapture/Capture;

    iput-object p2, p0, Lorg/apache/cordova/mediacapture/Capture$3;->val$intent:Landroid/content/Intent;

    iput-object p3, p0, Lorg/apache/cordova/mediacapture/Capture$3;->val$that:Lorg/apache/cordova/mediacapture/Capture;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .locals 6

    .prologue
    .line 362
    const/4 v0, 0x0

    .line 364
    .local v0, "data":Landroid/net/Uri;
    iget-object v2, p0, Lorg/apache/cordova/mediacapture/Capture$3;->val$intent:Landroid/content/Intent;

    if-eqz v2, :cond_0

    .line 366
    iget-object v2, p0, Lorg/apache/cordova/mediacapture/Capture$3;->val$intent:Landroid/content/Intent;

    invoke-virtual {v2}, Landroid/content/Intent;->getData()Landroid/net/Uri;

    move-result-object v0

    .line 369
    :cond_0
    if-nez v0, :cond_1

    .line 370
    new-instance v1, Ljava/io/File;

    iget-object v2, p0, Lorg/apache/cordova/mediacapture/Capture$3;->this$0:Lorg/apache/cordova/mediacapture/Capture;

    # invokes: Lorg/apache/cordova/mediacapture/Capture;->getTempDirectoryPath()Ljava/lang/String;
    invoke-static {v2}, Lorg/apache/cordova/mediacapture/Capture;->access$600(Lorg/apache/cordova/mediacapture/Capture;)Ljava/lang/String;

    move-result-object v2

    const-string v3, "Capture.avi"

    invoke-direct {v1, v2, v3}, Ljava/io/File;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    .line 371
    .local v1, "movie":Ljava/io/File;
    invoke-static {v1}, Landroid/net/Uri;->fromFile(Ljava/io/File;)Landroid/net/Uri;

    move-result-object v0

    .line 375
    .end local v1    # "movie":Ljava/io/File;
    :cond_1
    if-nez v0, :cond_2

    .line 377
    iget-object v2, p0, Lorg/apache/cordova/mediacapture/Capture$3;->val$that:Lorg/apache/cordova/mediacapture/Capture;

    iget-object v3, p0, Lorg/apache/cordova/mediacapture/Capture$3;->this$0:Lorg/apache/cordova/mediacapture/Capture;

    const/4 v4, 0x3

    const-string v5, "Error: data is null"

    # invokes: Lorg/apache/cordova/mediacapture/Capture;->createErrorObject(ILjava/lang/String;)Lorg/json/JSONObject;
    invoke-static {v3, v4, v5}, Lorg/apache/cordova/mediacapture/Capture;->access$500(Lorg/apache/cordova/mediacapture/Capture;ILjava/lang/String;)Lorg/json/JSONObject;

    move-result-object v3

    invoke-virtual {v2, v3}, Lorg/apache/cordova/mediacapture/Capture;->fail(Lorg/json/JSONObject;)V

    .line 391
    :goto_0
    return-void

    .line 381
    :cond_2
    iget-object v2, p0, Lorg/apache/cordova/mediacapture/Capture$3;->this$0:Lorg/apache/cordova/mediacapture/Capture;

    # getter for: Lorg/apache/cordova/mediacapture/Capture;->results:Lorg/json/JSONArray;
    invoke-static {v2}, Lorg/apache/cordova/mediacapture/Capture;->access$100(Lorg/apache/cordova/mediacapture/Capture;)Lorg/json/JSONArray;

    move-result-object v2

    iget-object v3, p0, Lorg/apache/cordova/mediacapture/Capture$3;->this$0:Lorg/apache/cordova/mediacapture/Capture;

    # invokes: Lorg/apache/cordova/mediacapture/Capture;->createMediaFile(Landroid/net/Uri;)Lorg/json/JSONObject;
    invoke-static {v3, v0}, Lorg/apache/cordova/mediacapture/Capture;->access$000(Lorg/apache/cordova/mediacapture/Capture;Landroid/net/Uri;)Lorg/json/JSONObject;

    move-result-object v3

    invoke-virtual {v2, v3}, Lorg/json/JSONArray;->put(Ljava/lang/Object;)Lorg/json/JSONArray;

    .line 383
    iget-object v2, p0, Lorg/apache/cordova/mediacapture/Capture$3;->this$0:Lorg/apache/cordova/mediacapture/Capture;

    # getter for: Lorg/apache/cordova/mediacapture/Capture;->results:Lorg/json/JSONArray;
    invoke-static {v2}, Lorg/apache/cordova/mediacapture/Capture;->access$100(Lorg/apache/cordova/mediacapture/Capture;)Lorg/json/JSONArray;

    move-result-object v2

    invoke-virtual {v2}, Lorg/json/JSONArray;->length()I

    move-result v2

    int-to-long v2, v2

    iget-object v4, p0, Lorg/apache/cordova/mediacapture/Capture$3;->this$0:Lorg/apache/cordova/mediacapture/Capture;

    # getter for: Lorg/apache/cordova/mediacapture/Capture;->limit:J
    invoke-static {v4}, Lorg/apache/cordova/mediacapture/Capture;->access$200(Lorg/apache/cordova/mediacapture/Capture;)J

    move-result-wide v4

    cmp-long v2, v2, v4

    if-ltz v2, :cond_3

    .line 385
    iget-object v2, p0, Lorg/apache/cordova/mediacapture/Capture$3;->val$that:Lorg/apache/cordova/mediacapture/Capture;

    # getter for: Lorg/apache/cordova/mediacapture/Capture;->callbackContext:Lorg/apache/cordova/CallbackContext;
    invoke-static {v2}, Lorg/apache/cordova/mediacapture/Capture;->access$300(Lorg/apache/cordova/mediacapture/Capture;)Lorg/apache/cordova/CallbackContext;

    move-result-object v2

    new-instance v3, Lorg/apache/cordova/PluginResult;

    sget-object v4, Lorg/apache/cordova/PluginResult$Status;->OK:Lorg/apache/cordova/PluginResult$Status;

    iget-object v5, p0, Lorg/apache/cordova/mediacapture/Capture$3;->this$0:Lorg/apache/cordova/mediacapture/Capture;

    # getter for: Lorg/apache/cordova/mediacapture/Capture;->results:Lorg/json/JSONArray;
    invoke-static {v5}, Lorg/apache/cordova/mediacapture/Capture;->access$100(Lorg/apache/cordova/mediacapture/Capture;)Lorg/json/JSONArray;

    move-result-object v5

    invoke-direct {v3, v4, v5}, Lorg/apache/cordova/PluginResult;-><init>(Lorg/apache/cordova/PluginResult$Status;Lorg/json/JSONArray;)V

    invoke-virtual {v2, v3}, Lorg/apache/cordova/CallbackContext;->sendPluginResult(Lorg/apache/cordova/PluginResult;)V

    goto :goto_0

    .line 388
    :cond_3
    iget-object v2, p0, Lorg/apache/cordova/mediacapture/Capture$3;->this$0:Lorg/apache/cordova/mediacapture/Capture;

    iget-object v3, p0, Lorg/apache/cordova/mediacapture/Capture$3;->this$0:Lorg/apache/cordova/mediacapture/Capture;

    # getter for: Lorg/apache/cordova/mediacapture/Capture;->duration:I
    invoke-static {v3}, Lorg/apache/cordova/mediacapture/Capture;->access$900(Lorg/apache/cordova/mediacapture/Capture;)I

    move-result v3

    # invokes: Lorg/apache/cordova/mediacapture/Capture;->captureVideo(I)V
    invoke-static {v2, v3}, Lorg/apache/cordova/mediacapture/Capture;->access$1000(Lorg/apache/cordova/mediacapture/Capture;I)V

    goto :goto_0
.end method
