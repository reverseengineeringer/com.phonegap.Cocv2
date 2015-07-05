.class Lcom/wezka/nativecamera/CameraActivity$6;
.super Ljava/lang/Object;
.source "CameraActivity.java"

# interfaces
.implements Landroid/hardware/Camera$PictureCallback;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/wezka/nativecamera/CameraActivity;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/wezka/nativecamera/CameraActivity;


# direct methods
.method constructor <init>(Lcom/wezka/nativecamera/CameraActivity;)V
    .locals 0

    .prologue
    .line 304
    iput-object p1, p0, Lcom/wezka/nativecamera/CameraActivity$6;->this$0:Lcom/wezka/nativecamera/CameraActivity;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onPictureTaken([BLandroid/hardware/Camera;)V
    .locals 7
    .param p1, "data"    # [B
    .param p2, "camera"    # Landroid/hardware/Camera;

    .prologue
    .line 306
    iget-object v4, p0, Lcom/wezka/nativecamera/CameraActivity$6;->this$0:Lcom/wezka/nativecamera/CameraActivity;

    invoke-virtual {v4}, Lcom/wezka/nativecamera/CameraActivity;->getIntent()Landroid/content/Intent;

    move-result-object v4

    invoke-virtual {v4}, Landroid/content/Intent;->getExtras()Landroid/os/Bundle;

    move-result-object v4

    const-string v5, "output"

    invoke-virtual {v4, v5}, Landroid/os/Bundle;->get(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Landroid/net/Uri;

    .line 308
    .local v1, "fileUri":Landroid/net/Uri;
    new-instance v3, Ljava/io/File;

    invoke-virtual {v1}, Landroid/net/Uri;->getPath()Ljava/lang/String;

    move-result-object v4

    invoke-direct {v3, v4}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    .line 310
    .local v3, "pictureFile":Ljava/io/File;
    :try_start_0
    new-instance v2, Ljava/io/FileOutputStream;

    invoke-direct {v2, v3}, Ljava/io/FileOutputStream;-><init>(Ljava/io/File;)V

    .line 311
    .local v2, "fos":Ljava/io/FileOutputStream;
    invoke-virtual {v2, p1}, Ljava/io/FileOutputStream;->write([B)V

    .line 312
    invoke-virtual {v2}, Ljava/io/FileOutputStream;->close()V
    :try_end_0
    .catch Ljava/io/FileNotFoundException; {:try_start_0 .. :try_end_0} :catch_0
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_1

    .line 318
    .end local v2    # "fos":Ljava/io/FileOutputStream;
    :goto_0
    iget-object v4, p0, Lcom/wezka/nativecamera/CameraActivity$6;->this$0:Lcom/wezka/nativecamera/CameraActivity;

    const/4 v5, -0x1

    invoke-virtual {v4, v5}, Lcom/wezka/nativecamera/CameraActivity;->setResult(I)V

    .line 319
    iget-object v4, p0, Lcom/wezka/nativecamera/CameraActivity$6;->this$0:Lcom/wezka/nativecamera/CameraActivity;

    const/4 v5, 0x0

    # setter for: Lcom/wezka/nativecamera/CameraActivity;->pressed:Z
    invoke-static {v4, v5}, Lcom/wezka/nativecamera/CameraActivity;->access$902(Lcom/wezka/nativecamera/CameraActivity;Z)Z

    .line 320
    iget-object v4, p0, Lcom/wezka/nativecamera/CameraActivity$6;->this$0:Lcom/wezka/nativecamera/CameraActivity;

    invoke-virtual {v4}, Lcom/wezka/nativecamera/CameraActivity;->finish()V

    .line 321
    return-void

    .line 313
    :catch_0
    move-exception v0

    .line 314
    .local v0, "e":Ljava/io/FileNotFoundException;
    const-string v4, "CameraActivity"

    new-instance v5, Ljava/lang/StringBuilder;

    invoke-direct {v5}, Ljava/lang/StringBuilder;-><init>()V

    const-string v6, "File not found: "

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v5

    invoke-virtual {v0}, Ljava/io/FileNotFoundException;->getMessage()Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v5

    invoke-virtual {v5}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v5

    invoke-static {v4, v5}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    goto :goto_0

    .line 315
    .end local v0    # "e":Ljava/io/FileNotFoundException;
    :catch_1
    move-exception v0

    .line 316
    .local v0, "e":Ljava/io/IOException;
    const-string v4, "CameraActivity"

    new-instance v5, Ljava/lang/StringBuilder;

    invoke-direct {v5}, Ljava/lang/StringBuilder;-><init>()V

    const-string v6, "Error accessing file: "

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v5

    invoke-virtual {v0}, Ljava/io/IOException;->getMessage()Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v5

    invoke-virtual {v5}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v5

    invoke-static {v4, v5}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    goto :goto_0
.end method
