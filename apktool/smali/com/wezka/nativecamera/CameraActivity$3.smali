.class Lcom/wezka/nativecamera/CameraActivity$3;
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

.field final synthetic val$flashButton:Landroid/widget/Button;

.field final synthetic val$imgFlashAuto:I

.field final synthetic val$imgFlashNo:I

.field final synthetic val$imgFlashOn:I


# direct methods
.method constructor <init>(Lcom/wezka/nativecamera/CameraActivity;Landroid/widget/Button;III)V
    .locals 0

    .prologue
    .line 211
    iput-object p1, p0, Lcom/wezka/nativecamera/CameraActivity$3;->this$0:Lcom/wezka/nativecamera/CameraActivity;

    iput-object p2, p0, Lcom/wezka/nativecamera/CameraActivity$3;->val$flashButton:Landroid/widget/Button;

    iput p3, p0, Lcom/wezka/nativecamera/CameraActivity$3;->val$imgFlashAuto:I

    iput p4, p0, Lcom/wezka/nativecamera/CameraActivity$3;->val$imgFlashOn:I

    iput p5, p0, Lcom/wezka/nativecamera/CameraActivity$3;->val$imgFlashNo:I

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onClick(Landroid/view/View;)V
    .locals 5
    .param p1, "v"    # Landroid/view/View;

    .prologue
    const/4 v4, 0x2

    const/4 v3, 0x1

    .line 213
    iget-object v1, p0, Lcom/wezka/nativecamera/CameraActivity$3;->this$0:Lcom/wezka/nativecamera/CameraActivity;

    # getter for: Lcom/wezka/nativecamera/CameraActivity;->camera:Landroid/hardware/Camera;
    invoke-static {v1}, Lcom/wezka/nativecamera/CameraActivity;->access$200(Lcom/wezka/nativecamera/CameraActivity;)Landroid/hardware/Camera;

    move-result-object v1

    invoke-virtual {v1}, Landroid/hardware/Camera;->getParameters()Landroid/hardware/Camera$Parameters;

    move-result-object v0

    .line 214
    .local v0, "p":Landroid/hardware/Camera$Parameters;
    iget-object v1, p0, Lcom/wezka/nativecamera/CameraActivity$3;->this$0:Lcom/wezka/nativecamera/CameraActivity;

    # getter for: Lcom/wezka/nativecamera/CameraActivity;->led:I
    invoke-static {v1}, Lcom/wezka/nativecamera/CameraActivity;->access$600(Lcom/wezka/nativecamera/CameraActivity;)I

    move-result v1

    if-nez v1, :cond_1

    .line 215
    const-string v1, "auto"

    invoke-virtual {v0, v1}, Landroid/hardware/Camera$Parameters;->setFlashMode(Ljava/lang/String;)V

    .line 216
    iget-object v1, p0, Lcom/wezka/nativecamera/CameraActivity$3;->val$flashButton:Landroid/widget/Button;

    iget v2, p0, Lcom/wezka/nativecamera/CameraActivity$3;->val$imgFlashAuto:I

    invoke-virtual {v1, v2}, Landroid/widget/Button;->setBackgroundResource(I)V

    .line 217
    iget-object v1, p0, Lcom/wezka/nativecamera/CameraActivity$3;->this$0:Lcom/wezka/nativecamera/CameraActivity;

    # setter for: Lcom/wezka/nativecamera/CameraActivity;->led:I
    invoke-static {v1, v3}, Lcom/wezka/nativecamera/CameraActivity;->access$602(Lcom/wezka/nativecamera/CameraActivity;I)I

    .line 227
    :cond_0
    :goto_0
    iget-object v1, p0, Lcom/wezka/nativecamera/CameraActivity$3;->this$0:Lcom/wezka/nativecamera/CameraActivity;

    # getter for: Lcom/wezka/nativecamera/CameraActivity;->camera:Landroid/hardware/Camera;
    invoke-static {v1}, Lcom/wezka/nativecamera/CameraActivity;->access$200(Lcom/wezka/nativecamera/CameraActivity;)Landroid/hardware/Camera;

    move-result-object v1

    invoke-virtual {v1, v0}, Landroid/hardware/Camera;->setParameters(Landroid/hardware/Camera$Parameters;)V

    .line 228
    iget-object v1, p0, Lcom/wezka/nativecamera/CameraActivity$3;->this$0:Lcom/wezka/nativecamera/CameraActivity;

    # getter for: Lcom/wezka/nativecamera/CameraActivity;->camera:Landroid/hardware/Camera;
    invoke-static {v1}, Lcom/wezka/nativecamera/CameraActivity;->access$200(Lcom/wezka/nativecamera/CameraActivity;)Landroid/hardware/Camera;

    move-result-object v1

    invoke-virtual {v1}, Landroid/hardware/Camera;->startPreview()V

    .line 229
    return-void

    .line 218
    :cond_1
    iget-object v1, p0, Lcom/wezka/nativecamera/CameraActivity$3;->this$0:Lcom/wezka/nativecamera/CameraActivity;

    # getter for: Lcom/wezka/nativecamera/CameraActivity;->led:I
    invoke-static {v1}, Lcom/wezka/nativecamera/CameraActivity;->access$600(Lcom/wezka/nativecamera/CameraActivity;)I

    move-result v1

    if-ne v1, v3, :cond_2

    .line 219
    const-string v1, "torch"

    invoke-virtual {v0, v1}, Landroid/hardware/Camera$Parameters;->setFlashMode(Ljava/lang/String;)V

    .line 220
    iget-object v1, p0, Lcom/wezka/nativecamera/CameraActivity$3;->val$flashButton:Landroid/widget/Button;

    iget v2, p0, Lcom/wezka/nativecamera/CameraActivity$3;->val$imgFlashOn:I

    invoke-virtual {v1, v2}, Landroid/widget/Button;->setBackgroundResource(I)V

    .line 221
    iget-object v1, p0, Lcom/wezka/nativecamera/CameraActivity$3;->this$0:Lcom/wezka/nativecamera/CameraActivity;

    # setter for: Lcom/wezka/nativecamera/CameraActivity;->led:I
    invoke-static {v1, v4}, Lcom/wezka/nativecamera/CameraActivity;->access$602(Lcom/wezka/nativecamera/CameraActivity;I)I

    goto :goto_0

    .line 222
    :cond_2
    iget-object v1, p0, Lcom/wezka/nativecamera/CameraActivity$3;->this$0:Lcom/wezka/nativecamera/CameraActivity;

    # getter for: Lcom/wezka/nativecamera/CameraActivity;->led:I
    invoke-static {v1}, Lcom/wezka/nativecamera/CameraActivity;->access$600(Lcom/wezka/nativecamera/CameraActivity;)I

    move-result v1

    if-ne v1, v4, :cond_0

    .line 223
    const-string v1, "off"

    invoke-virtual {v0, v1}, Landroid/hardware/Camera$Parameters;->setFlashMode(Ljava/lang/String;)V

    .line 224
    iget-object v1, p0, Lcom/wezka/nativecamera/CameraActivity$3;->val$flashButton:Landroid/widget/Button;

    iget v2, p0, Lcom/wezka/nativecamera/CameraActivity$3;->val$imgFlashNo:I

    invoke-virtual {v1, v2}, Landroid/widget/Button;->setBackgroundResource(I)V

    .line 225
    iget-object v1, p0, Lcom/wezka/nativecamera/CameraActivity$3;->this$0:Lcom/wezka/nativecamera/CameraActivity;

    const/4 v2, 0x0

    # setter for: Lcom/wezka/nativecamera/CameraActivity;->led:I
    invoke-static {v1, v2}, Lcom/wezka/nativecamera/CameraActivity;->access$602(Lcom/wezka/nativecamera/CameraActivity;I)I

    goto :goto_0
.end method
