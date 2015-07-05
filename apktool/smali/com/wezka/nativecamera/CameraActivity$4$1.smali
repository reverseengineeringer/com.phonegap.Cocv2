.class Lcom/wezka/nativecamera/CameraActivity$4$1;
.super Ljava/lang/Object;
.source "CameraActivity.java"

# interfaces
.implements Landroid/hardware/Camera$AutoFocusCallback;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/wezka/nativecamera/CameraActivity$4;->onClick(Landroid/view/View;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$1:Lcom/wezka/nativecamera/CameraActivity$4;


# direct methods
.method constructor <init>(Lcom/wezka/nativecamera/CameraActivity$4;)V
    .locals 0

    .prologue
    .line 244
    iput-object p1, p0, Lcom/wezka/nativecamera/CameraActivity$4$1;->this$1:Lcom/wezka/nativecamera/CameraActivity$4;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onAutoFocus(ZLandroid/hardware/Camera;)V
    .locals 4
    .param p1, "success"    # Z
    .param p2, "camera"    # Landroid/hardware/Camera;

    .prologue
    .line 248
    const/4 v1, 0x0

    const/4 v2, 0x0

    :try_start_0
    iget-object v3, p0, Lcom/wezka/nativecamera/CameraActivity$4$1;->this$1:Lcom/wezka/nativecamera/CameraActivity$4;

    iget-object v3, v3, Lcom/wezka/nativecamera/CameraActivity$4;->this$0:Lcom/wezka/nativecamera/CameraActivity;

    # getter for: Lcom/wezka/nativecamera/CameraActivity;->mPicture:Landroid/hardware/Camera$PictureCallback;
    invoke-static {v3}, Lcom/wezka/nativecamera/CameraActivity;->access$1100(Lcom/wezka/nativecamera/CameraActivity;)Landroid/hardware/Camera$PictureCallback;

    move-result-object v3

    invoke-virtual {p2, v1, v2, v3}, Landroid/hardware/Camera;->takePicture(Landroid/hardware/Camera$ShutterCallback;Landroid/hardware/Camera$PictureCallback;Landroid/hardware/Camera$PictureCallback;)V
    :try_end_0
    .catch Ljava/lang/RuntimeException; {:try_start_0 .. :try_end_0} :catch_0

    .line 255
    :goto_0
    return-void

    .line 249
    :catch_0
    move-exception v0

    .line 251
    .local v0, "ex":Ljava/lang/RuntimeException;
    iget-object v1, p0, Lcom/wezka/nativecamera/CameraActivity$4$1;->this$1:Lcom/wezka/nativecamera/CameraActivity$4;

    iget-object v1, v1, Lcom/wezka/nativecamera/CameraActivity$4;->this$0:Lcom/wezka/nativecamera/CameraActivity;

    invoke-virtual {v1}, Lcom/wezka/nativecamera/CameraActivity;->getApplicationContext()Landroid/content/Context;

    move-result-object v1

    const-string v2, "Error taking picture"

    const/4 v3, 0x0

    invoke-static {v1, v2, v3}, Landroid/widget/Toast;->makeText(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;

    move-result-object v1

    invoke-virtual {v1}, Landroid/widget/Toast;->show()V

    .line 253
    const-string v1, "CameraActivity"

    const-string v2, "Auto-focus crash"

    invoke-static {v1, v2}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    goto :goto_0
.end method
