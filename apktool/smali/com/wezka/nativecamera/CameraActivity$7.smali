.class Lcom/wezka/nativecamera/CameraActivity$7;
.super Ljava/lang/Object;
.source "CameraActivity.java"

# interfaces
.implements Landroid/view/SurfaceHolder$Callback;


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
    .line 464
    iput-object p1, p0, Lcom/wezka/nativecamera/CameraActivity$7;->this$0:Lcom/wezka/nativecamera/CameraActivity;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public surfaceChanged(Landroid/view/SurfaceHolder;III)V
    .locals 2
    .param p1, "holder"    # Landroid/view/SurfaceHolder;
    .param p2, "format"    # I
    .param p3, "width"    # I
    .param p4, "height"    # I

    .prologue
    .line 470
    iget-object v0, p0, Lcom/wezka/nativecamera/CameraActivity$7;->this$0:Lcom/wezka/nativecamera/CameraActivity;

    # getter for: Lcom/wezka/nativecamera/CameraActivity;->camera:Landroid/hardware/Camera;
    invoke-static {v0}, Lcom/wezka/nativecamera/CameraActivity;->access$200(Lcom/wezka/nativecamera/CameraActivity;)Landroid/hardware/Camera;

    move-result-object v0

    if-eqz v0, :cond_0

    .line 471
    iget-object v0, p0, Lcom/wezka/nativecamera/CameraActivity$7;->this$0:Lcom/wezka/nativecamera/CameraActivity;

    # getter for: Lcom/wezka/nativecamera/CameraActivity;->camera:Landroid/hardware/Camera;
    invoke-static {v0}, Lcom/wezka/nativecamera/CameraActivity;->access$200(Lcom/wezka/nativecamera/CameraActivity;)Landroid/hardware/Camera;

    move-result-object v0

    const/16 v1, 0x5a

    invoke-virtual {v0, v1}, Landroid/hardware/Camera;->setDisplayOrientation(I)V

    .line 473
    :cond_0
    iget-object v0, p0, Lcom/wezka/nativecamera/CameraActivity$7;->this$0:Lcom/wezka/nativecamera/CameraActivity;

    iget-object v1, p0, Lcom/wezka/nativecamera/CameraActivity$7;->this$0:Lcom/wezka/nativecamera/CameraActivity;

    # getter for: Lcom/wezka/nativecamera/CameraActivity;->preview:Landroid/view/SurfaceView;
    invoke-static {v1}, Lcom/wezka/nativecamera/CameraActivity;->access$1200(Lcom/wezka/nativecamera/CameraActivity;)Landroid/view/SurfaceView;

    move-result-object v1

    invoke-virtual {v1}, Landroid/view/SurfaceView;->getHeight()I

    move-result v1

    # invokes: Lcom/wezka/nativecamera/CameraActivity;->initPreview(I)V
    invoke-static {v0, v1}, Lcom/wezka/nativecamera/CameraActivity;->access$1300(Lcom/wezka/nativecamera/CameraActivity;I)V

    .line 474
    iget-object v0, p0, Lcom/wezka/nativecamera/CameraActivity$7;->this$0:Lcom/wezka/nativecamera/CameraActivity;

    # invokes: Lcom/wezka/nativecamera/CameraActivity;->startPreview()V
    invoke-static {v0}, Lcom/wezka/nativecamera/CameraActivity;->access$1400(Lcom/wezka/nativecamera/CameraActivity;)V

    .line 475
    return-void
.end method

.method public surfaceCreated(Landroid/view/SurfaceHolder;)V
    .locals 0
    .param p1, "holder"    # Landroid/view/SurfaceHolder;

    .prologue
    .line 466
    return-void
.end method

.method public surfaceDestroyed(Landroid/view/SurfaceHolder;)V
    .locals 2
    .param p1, "holder"    # Landroid/view/SurfaceHolder;

    .prologue
    .line 478
    iget-object v0, p0, Lcom/wezka/nativecamera/CameraActivity$7;->this$0:Lcom/wezka/nativecamera/CameraActivity;

    # getter for: Lcom/wezka/nativecamera/CameraActivity;->camera:Landroid/hardware/Camera;
    invoke-static {v0}, Lcom/wezka/nativecamera/CameraActivity;->access$200(Lcom/wezka/nativecamera/CameraActivity;)Landroid/hardware/Camera;

    move-result-object v0

    if-eqz v0, :cond_0

    .line 479
    iget-object v0, p0, Lcom/wezka/nativecamera/CameraActivity$7;->this$0:Lcom/wezka/nativecamera/CameraActivity;

    # getter for: Lcom/wezka/nativecamera/CameraActivity;->camera:Landroid/hardware/Camera;
    invoke-static {v0}, Lcom/wezka/nativecamera/CameraActivity;->access$200(Lcom/wezka/nativecamera/CameraActivity;)Landroid/hardware/Camera;

    move-result-object v0

    invoke-virtual {v0}, Landroid/hardware/Camera;->stopPreview()V

    .line 480
    iget-object v0, p0, Lcom/wezka/nativecamera/CameraActivity$7;->this$0:Lcom/wezka/nativecamera/CameraActivity;

    # getter for: Lcom/wezka/nativecamera/CameraActivity;->camera:Landroid/hardware/Camera;
    invoke-static {v0}, Lcom/wezka/nativecamera/CameraActivity;->access$200(Lcom/wezka/nativecamera/CameraActivity;)Landroid/hardware/Camera;

    move-result-object v0

    invoke-virtual {v0}, Landroid/hardware/Camera;->release()V

    .line 481
    iget-object v0, p0, Lcom/wezka/nativecamera/CameraActivity$7;->this$0:Lcom/wezka/nativecamera/CameraActivity;

    const/4 v1, 0x0

    # setter for: Lcom/wezka/nativecamera/CameraActivity;->camera:Landroid/hardware/Camera;
    invoke-static {v0, v1}, Lcom/wezka/nativecamera/CameraActivity;->access$202(Lcom/wezka/nativecamera/CameraActivity;Landroid/hardware/Camera;)Landroid/hardware/Camera;

    .line 483
    :cond_0
    return-void
.end method
