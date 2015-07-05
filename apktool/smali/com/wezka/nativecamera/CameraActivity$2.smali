.class Lcom/wezka/nativecamera/CameraActivity$2;
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

.field final synthetic val$imgFlashNo:I

.field final synthetic val$viewfinder:Landroid/widget/ImageView;


# direct methods
.method constructor <init>(Lcom/wezka/nativecamera/CameraActivity;Landroid/widget/ImageView;Landroid/widget/Button;I)V
    .locals 0

    .prologue
    .line 187
    iput-object p1, p0, Lcom/wezka/nativecamera/CameraActivity$2;->this$0:Lcom/wezka/nativecamera/CameraActivity;

    iput-object p2, p0, Lcom/wezka/nativecamera/CameraActivity$2;->val$viewfinder:Landroid/widget/ImageView;

    iput-object p3, p0, Lcom/wezka/nativecamera/CameraActivity$2;->val$flashButton:Landroid/widget/Button;

    iput p4, p0, Lcom/wezka/nativecamera/CameraActivity$2;->val$imgFlashNo:I

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onClick(Landroid/view/View;)V
    .locals 5
    .param p1, "v"    # Landroid/view/View;

    .prologue
    const/4 v2, 0x4

    const/4 v4, 0x0

    .line 189
    iget-object v0, p0, Lcom/wezka/nativecamera/CameraActivity$2;->this$0:Lcom/wezka/nativecamera/CameraActivity;

    # getter for: Lcom/wezka/nativecamera/CameraActivity;->cam:I
    invoke-static {v0}, Lcom/wezka/nativecamera/CameraActivity;->access$500(Lcom/wezka/nativecamera/CameraActivity;)I

    move-result v0

    if-nez v0, :cond_2

    .line 190
    iget-object v0, p0, Lcom/wezka/nativecamera/CameraActivity$2;->this$0:Lcom/wezka/nativecamera/CameraActivity;

    const/4 v1, 0x1

    # setter for: Lcom/wezka/nativecamera/CameraActivity;->cam:I
    invoke-static {v0, v1}, Lcom/wezka/nativecamera/CameraActivity;->access$502(Lcom/wezka/nativecamera/CameraActivity;I)I

    .line 191
    iget-object v0, p0, Lcom/wezka/nativecamera/CameraActivity$2;->this$0:Lcom/wezka/nativecamera/CameraActivity;

    # setter for: Lcom/wezka/nativecamera/CameraActivity;->led:I
    invoke-static {v0, v4}, Lcom/wezka/nativecamera/CameraActivity;->access$602(Lcom/wezka/nativecamera/CameraActivity;I)I

    .line 192
    iget-object v0, p0, Lcom/wezka/nativecamera/CameraActivity$2;->val$viewfinder:Landroid/widget/ImageView;

    invoke-virtual {v0, v2}, Landroid/widget/ImageView;->setVisibility(I)V

    .line 193
    iget-object v0, p0, Lcom/wezka/nativecamera/CameraActivity$2;->this$0:Lcom/wezka/nativecamera/CameraActivity;

    # getter for: Lcom/wezka/nativecamera/CameraActivity;->isFlash:Z
    invoke-static {v0}, Lcom/wezka/nativecamera/CameraActivity;->access$700(Lcom/wezka/nativecamera/CameraActivity;)Z

    move-result v0

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/wezka/nativecamera/CameraActivity$2;->val$flashButton:Landroid/widget/Button;

    invoke-virtual {v0, v2}, Landroid/widget/Button;->setVisibility(I)V

    .line 194
    :cond_0
    iget-object v0, p0, Lcom/wezka/nativecamera/CameraActivity$2;->this$0:Lcom/wezka/nativecamera/CameraActivity;

    # getter for: Lcom/wezka/nativecamera/CameraActivity;->isFlash:Z
    invoke-static {v0}, Lcom/wezka/nativecamera/CameraActivity;->access$700(Lcom/wezka/nativecamera/CameraActivity;)Z

    move-result v0

    if-eqz v0, :cond_1

    iget-object v0, p0, Lcom/wezka/nativecamera/CameraActivity$2;->val$flashButton:Landroid/widget/Button;

    iget v1, p0, Lcom/wezka/nativecamera/CameraActivity$2;->val$imgFlashNo:I

    invoke-virtual {v0, v1}, Landroid/widget/Button;->setBackgroundResource(I)V

    .line 204
    :cond_1
    :goto_0
    iget-object v0, p0, Lcom/wezka/nativecamera/CameraActivity$2;->this$0:Lcom/wezka/nativecamera/CameraActivity;

    # setter for: Lcom/wezka/nativecamera/CameraActivity;->cameraConfigured:Z
    invoke-static {v0, v4}, Lcom/wezka/nativecamera/CameraActivity;->access$802(Lcom/wezka/nativecamera/CameraActivity;Z)Z

    .line 205
    iget-object v0, p0, Lcom/wezka/nativecamera/CameraActivity$2;->this$0:Lcom/wezka/nativecamera/CameraActivity;

    iget-object v1, p0, Lcom/wezka/nativecamera/CameraActivity$2;->this$0:Lcom/wezka/nativecamera/CameraActivity;

    # getter for: Lcom/wezka/nativecamera/CameraActivity;->cam:I
    invoke-static {v1}, Lcom/wezka/nativecamera/CameraActivity;->access$500(Lcom/wezka/nativecamera/CameraActivity;)I

    move-result v1

    invoke-virtual {v0, v1}, Lcom/wezka/nativecamera/CameraActivity;->restartPreview(I)V

    .line 206
    return-void

    .line 196
    :cond_2
    iget-object v0, p0, Lcom/wezka/nativecamera/CameraActivity$2;->this$0:Lcom/wezka/nativecamera/CameraActivity;

    # setter for: Lcom/wezka/nativecamera/CameraActivity;->cam:I
    invoke-static {v0, v4}, Lcom/wezka/nativecamera/CameraActivity;->access$502(Lcom/wezka/nativecamera/CameraActivity;I)I

    .line 197
    iget-object v0, p0, Lcom/wezka/nativecamera/CameraActivity$2;->this$0:Lcom/wezka/nativecamera/CameraActivity;

    # setter for: Lcom/wezka/nativecamera/CameraActivity;->led:I
    invoke-static {v0, v4}, Lcom/wezka/nativecamera/CameraActivity;->access$602(Lcom/wezka/nativecamera/CameraActivity;I)I

    .line 198
    iget-object v0, p0, Lcom/wezka/nativecamera/CameraActivity$2;->val$viewfinder:Landroid/widget/ImageView;

    invoke-virtual {v0, v4}, Landroid/widget/ImageView;->setVisibility(I)V

    .line 199
    iget-object v0, p0, Lcom/wezka/nativecamera/CameraActivity$2;->val$viewfinder:Landroid/widget/ImageView;

    iget-object v1, p0, Lcom/wezka/nativecamera/CameraActivity$2;->this$0:Lcom/wezka/nativecamera/CameraActivity;

    # getter for: Lcom/wezka/nativecamera/CameraActivity;->screenWidth:I
    invoke-static {v1}, Lcom/wezka/nativecamera/CameraActivity;->access$000(Lcom/wezka/nativecamera/CameraActivity;)I

    move-result v1

    div-int/lit8 v1, v1, 0x2

    int-to-float v1, v1

    iget-object v2, p0, Lcom/wezka/nativecamera/CameraActivity$2;->this$0:Lcom/wezka/nativecamera/CameraActivity;

    # getter for: Lcom/wezka/nativecamera/CameraActivity;->viewfinderHalfPx:F
    invoke-static {v2}, Lcom/wezka/nativecamera/CameraActivity;->access$300(Lcom/wezka/nativecamera/CameraActivity;)F

    move-result v2

    sub-float/2addr v1, v2

    invoke-virtual {v0, v1}, Landroid/widget/ImageView;->setX(F)V

    .line 200
    iget-object v0, p0, Lcom/wezka/nativecamera/CameraActivity$2;->val$viewfinder:Landroid/widget/ImageView;

    iget-object v1, p0, Lcom/wezka/nativecamera/CameraActivity$2;->this$0:Lcom/wezka/nativecamera/CameraActivity;

    # getter for: Lcom/wezka/nativecamera/CameraActivity;->screenHeight:I
    invoke-static {v1}, Lcom/wezka/nativecamera/CameraActivity;->access$100(Lcom/wezka/nativecamera/CameraActivity;)I

    move-result v1

    div-int/lit8 v1, v1, 0x2

    int-to-float v1, v1

    iget-object v2, p0, Lcom/wezka/nativecamera/CameraActivity$2;->this$0:Lcom/wezka/nativecamera/CameraActivity;

    # getter for: Lcom/wezka/nativecamera/CameraActivity;->viewfinderHalfPx:F
    invoke-static {v2}, Lcom/wezka/nativecamera/CameraActivity;->access$300(Lcom/wezka/nativecamera/CameraActivity;)F

    move-result v2

    const/high16 v3, 0x40400000    # 3.0f

    mul-float/2addr v2, v3

    sub-float/2addr v1, v2

    invoke-virtual {v0, v1}, Landroid/widget/ImageView;->setY(F)V

    .line 201
    iget-object v0, p0, Lcom/wezka/nativecamera/CameraActivity$2;->this$0:Lcom/wezka/nativecamera/CameraActivity;

    # getter for: Lcom/wezka/nativecamera/CameraActivity;->isFlash:Z
    invoke-static {v0}, Lcom/wezka/nativecamera/CameraActivity;->access$700(Lcom/wezka/nativecamera/CameraActivity;)Z

    move-result v0

    if-eqz v0, :cond_3

    iget-object v0, p0, Lcom/wezka/nativecamera/CameraActivity$2;->val$flashButton:Landroid/widget/Button;

    invoke-virtual {v0, v4}, Landroid/widget/Button;->setVisibility(I)V

    .line 202
    :cond_3
    iget-object v0, p0, Lcom/wezka/nativecamera/CameraActivity$2;->this$0:Lcom/wezka/nativecamera/CameraActivity;

    # getter for: Lcom/wezka/nativecamera/CameraActivity;->isFlash:Z
    invoke-static {v0}, Lcom/wezka/nativecamera/CameraActivity;->access$700(Lcom/wezka/nativecamera/CameraActivity;)Z

    move-result v0

    if-eqz v0, :cond_1

    iget-object v0, p0, Lcom/wezka/nativecamera/CameraActivity$2;->val$flashButton:Landroid/widget/Button;

    iget v1, p0, Lcom/wezka/nativecamera/CameraActivity$2;->val$imgFlashNo:I

    invoke-virtual {v0, v1}, Landroid/widget/Button;->setBackgroundResource(I)V

    goto :goto_0
.end method
