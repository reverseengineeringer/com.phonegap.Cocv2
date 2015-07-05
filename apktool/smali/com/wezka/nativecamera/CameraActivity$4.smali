.class Lcom/wezka/nativecamera/CameraActivity$4;
.super Ljava/lang/Object;
.source "CameraActivity.java"

# interfaces
.implements Landroid/view/View$OnClickListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/wezka/nativecamera/CameraActivity;->onCreate(Landroid/os/Bundle;)V
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
    .line 233
    iput-object p1, p0, Lcom/wezka/nativecamera/CameraActivity$4;->this$0:Lcom/wezka/nativecamera/CameraActivity;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onClick(Landroid/view/View;)V
    .locals 5
    .param p1, "v"    # Landroid/view/View;

    .prologue
    .line 235
    iget-object v2, p0, Lcom/wezka/nativecamera/CameraActivity$4;->this$0:Lcom/wezka/nativecamera/CameraActivity;

    # getter for: Lcom/wezka/nativecamera/CameraActivity;->pressed:Z
    invoke-static {v2}, Lcom/wezka/nativecamera/CameraActivity;->access$900(Lcom/wezka/nativecamera/CameraActivity;)Z

    move-result v2

    if-nez v2, :cond_0

    iget-object v2, p0, Lcom/wezka/nativecamera/CameraActivity$4;->this$0:Lcom/wezka/nativecamera/CameraActivity;

    # getter for: Lcom/wezka/nativecamera/CameraActivity;->camera:Landroid/hardware/Camera;
    invoke-static {v2}, Lcom/wezka/nativecamera/CameraActivity;->access$200(Lcom/wezka/nativecamera/CameraActivity;)Landroid/hardware/Camera;

    move-result-object v2

    if-nez v2, :cond_1

    .line 263
    :cond_0
    :goto_0
    return-void

    .line 238
    :cond_1
    iget-object v2, p0, Lcom/wezka/nativecamera/CameraActivity$4;->this$0:Lcom/wezka/nativecamera/CameraActivity;

    # getter for: Lcom/wezka/nativecamera/CameraActivity;->camera:Landroid/hardware/Camera;
    invoke-static {v2}, Lcom/wezka/nativecamera/CameraActivity;->access$200(Lcom/wezka/nativecamera/CameraActivity;)Landroid/hardware/Camera;

    move-result-object v2

    invoke-virtual {v2}, Landroid/hardware/Camera;->getParameters()Landroid/hardware/Camera$Parameters;

    move-result-object v1

    .line 239
    .local v1, "p":Landroid/hardware/Camera$Parameters;
    iget-object v2, p0, Lcom/wezka/nativecamera/CameraActivity$4;->this$0:Lcom/wezka/nativecamera/CameraActivity;

    # getter for: Lcom/wezka/nativecamera/CameraActivity;->degrees:I
    invoke-static {v2}, Lcom/wezka/nativecamera/CameraActivity;->access$1000(Lcom/wezka/nativecamera/CameraActivity;)I

    move-result v2

    invoke-virtual {v1, v2}, Landroid/hardware/Camera$Parameters;->setRotation(I)V

    .line 240
    iget-object v2, p0, Lcom/wezka/nativecamera/CameraActivity$4;->this$0:Lcom/wezka/nativecamera/CameraActivity;

    # getter for: Lcom/wezka/nativecamera/CameraActivity;->camera:Landroid/hardware/Camera;
    invoke-static {v2}, Lcom/wezka/nativecamera/CameraActivity;->access$200(Lcom/wezka/nativecamera/CameraActivity;)Landroid/hardware/Camera;

    move-result-object v2

    invoke-virtual {v2, v1}, Landroid/hardware/Camera;->setParameters(Landroid/hardware/Camera$Parameters;)V

    .line 241
    iget-object v2, p0, Lcom/wezka/nativecamera/CameraActivity$4;->this$0:Lcom/wezka/nativecamera/CameraActivity;

    const/4 v3, 0x1

    # setter for: Lcom/wezka/nativecamera/CameraActivity;->pressed:Z
    invoke-static {v2, v3}, Lcom/wezka/nativecamera/CameraActivity;->access$902(Lcom/wezka/nativecamera/CameraActivity;Z)Z

    .line 244
    :try_start_0
    iget-object v2, p0, Lcom/wezka/nativecamera/CameraActivity$4;->this$0:Lcom/wezka/nativecamera/CameraActivity;

    # getter for: Lcom/wezka/nativecamera/CameraActivity;->camera:Landroid/hardware/Camera;
    invoke-static {v2}, Lcom/wezka/nativecamera/CameraActivity;->access$200(Lcom/wezka/nativecamera/CameraActivity;)Landroid/hardware/Camera;

    move-result-object v2

    new-instance v3, Lcom/wezka/nativecamera/CameraActivity$4$1;

    invoke-direct {v3, p0}, Lcom/wezka/nativecamera/CameraActivity$4$1;-><init>(Lcom/wezka/nativecamera/CameraActivity$4;)V

    invoke-virtual {v2, v3}, Landroid/hardware/Camera;->autoFocus(Landroid/hardware/Camera$AutoFocusCallback;)V
    :try_end_0
    .catch Ljava/lang/RuntimeException; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    .line 257
    :catch_0
    move-exception v0

    .line 259
    .local v0, "ex":Ljava/lang/RuntimeException;
    iget-object v2, p0, Lcom/wezka/nativecamera/CameraActivity$4;->this$0:Lcom/wezka/nativecamera/CameraActivity;

    invoke-virtual {v2}, Lcom/wezka/nativecamera/CameraActivity;->getApplicationContext()Landroid/content/Context;

    move-result-object v2

    const-string v3, "Error focusing"

    const/4 v4, 0x0

    invoke-static {v2, v3, v4}, Landroid/widget/Toast;->makeText(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;

    move-result-object v2

    invoke-virtual {v2}, Landroid/widget/Toast;->show()V

    .line 261
    const-string v2, "CameraActivity"

    const-string v3, "Auto-focus crash"

    invoke-static {v2, v3}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    goto :goto_0
.end method
