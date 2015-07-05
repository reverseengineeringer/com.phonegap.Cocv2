.class Lcom/wezka/nativecamera/CameraActivity$1;
.super Ljava/lang/Object;
.source "CameraActivity.java"

# interfaces
.implements Landroid/view/View$OnTouchListener;


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

.field final synthetic val$viewfinder:Landroid/widget/ImageView;


# direct methods
.method constructor <init>(Lcom/wezka/nativecamera/CameraActivity;Landroid/widget/ImageView;)V
    .locals 0

    .prologue
    .line 134
    iput-object p1, p0, Lcom/wezka/nativecamera/CameraActivity$1;->this$0:Lcom/wezka/nativecamera/CameraActivity;

    iput-object p2, p0, Lcom/wezka/nativecamera/CameraActivity$1;->val$viewfinder:Landroid/widget/ImageView;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onTouch(Landroid/view/View;Landroid/view/MotionEvent;)Z
    .locals 12
    .param p1, "v"    # Landroid/view/View;
    .param p2, "event"    # Landroid/view/MotionEvent;

    .prologue
    const/high16 v8, 0x44fa0000    # 2000.0f

    const/high16 v7, 0x447a0000    # 1000.0f

    const/high16 v11, 0x42fa0000    # 125.0f

    const/high16 v10, 0x40000000    # 2.0f

    const/4 v9, 0x0

    .line 137
    const/4 v3, 0x0

    .line 138
    .local v3, "x":F
    const/4 v4, 0x0

    .line 140
    .local v4, "y":F
    invoke-virtual {p2}, Landroid/view/MotionEvent;->getX()F

    move-result v5

    mul-float/2addr v5, v8

    iget-object v6, p0, Lcom/wezka/nativecamera/CameraActivity$1;->this$0:Lcom/wezka/nativecamera/CameraActivity;

    # getter for: Lcom/wezka/nativecamera/CameraActivity;->screenWidth:I
    invoke-static {v6}, Lcom/wezka/nativecamera/CameraActivity;->access$000(Lcom/wezka/nativecamera/CameraActivity;)I

    move-result v6

    int-to-float v6, v6

    div-float/2addr v5, v6

    sub-float/2addr v5, v7

    const/high16 v6, -0x40800000    # -1.0f

    mul-float v4, v5, v6

    .line 141
    invoke-virtual {p2}, Landroid/view/MotionEvent;->getY()F

    move-result v5

    mul-float/2addr v5, v8

    iget-object v6, p0, Lcom/wezka/nativecamera/CameraActivity$1;->this$0:Lcom/wezka/nativecamera/CameraActivity;

    # getter for: Lcom/wezka/nativecamera/CameraActivity;->screenHeight:I
    invoke-static {v6}, Lcom/wezka/nativecamera/CameraActivity;->access$100(Lcom/wezka/nativecamera/CameraActivity;)I

    move-result v6

    int-to-float v6, v6

    div-float/2addr v5, v6

    sub-float v3, v5, v7

    .line 143
    float-to-int v5, v3

    add-int/lit8 v5, v5, -0x64

    const/16 v6, -0x3e8

    if-le v5, v6, :cond_0

    float-to-int v5, v3

    add-int/lit8 v5, v5, 0x64

    const/16 v6, 0x3e8

    if-ge v5, v6, :cond_0

    float-to-int v5, v4

    add-int/lit8 v5, v5, -0x64

    const/16 v6, -0x3e8

    if-le v5, v6, :cond_0

    float-to-int v5, v4

    add-int/lit8 v5, v5, 0x64

    const/16 v6, 0x3e8

    if-ge v5, v6, :cond_0

    .line 144
    new-instance v1, Landroid/graphics/Rect;

    float-to-int v5, v3

    add-int/lit8 v5, v5, -0x64

    float-to-int v6, v4

    add-int/lit8 v6, v6, -0x64

    float-to-int v7, v3

    add-int/lit8 v7, v7, 0x64

    float-to-int v8, v4

    add-int/lit8 v8, v8, 0x64

    invoke-direct {v1, v5, v6, v7, v8}, Landroid/graphics/Rect;-><init>(IIII)V

    .line 149
    .local v1, "focusRect":Landroid/graphics/Rect;
    :goto_0
    iget-object v5, p0, Lcom/wezka/nativecamera/CameraActivity$1;->this$0:Lcom/wezka/nativecamera/CameraActivity;

    # getter for: Lcom/wezka/nativecamera/CameraActivity;->camera:Landroid/hardware/Camera;
    invoke-static {v5}, Lcom/wezka/nativecamera/CameraActivity;->access$200(Lcom/wezka/nativecamera/CameraActivity;)Landroid/hardware/Camera;

    move-result-object v5

    if-nez v5, :cond_1

    .line 150
    const/4 v5, 0x1

    .line 181
    :goto_1
    return v5

    .line 146
    .end local v1    # "focusRect":Landroid/graphics/Rect;
    :cond_0
    new-instance v1, Landroid/graphics/Rect;

    const/16 v5, -0x64

    const/16 v6, -0x64

    const/16 v7, 0x64

    const/16 v8, 0x64

    invoke-direct {v1, v5, v6, v7, v8}, Landroid/graphics/Rect;-><init>(IIII)V

    .restart local v1    # "focusRect":Landroid/graphics/Rect;
    goto :goto_0

    .line 152
    :cond_1
    iget-object v5, p0, Lcom/wezka/nativecamera/CameraActivity$1;->this$0:Lcom/wezka/nativecamera/CameraActivity;

    # getter for: Lcom/wezka/nativecamera/CameraActivity;->camera:Landroid/hardware/Camera;
    invoke-static {v5}, Lcom/wezka/nativecamera/CameraActivity;->access$200(Lcom/wezka/nativecamera/CameraActivity;)Landroid/hardware/Camera;

    move-result-object v5

    invoke-virtual {v5}, Landroid/hardware/Camera;->getParameters()Landroid/hardware/Camera$Parameters;

    move-result-object v2

    .line 154
    .local v2, "parameters":Landroid/hardware/Camera$Parameters;
    invoke-virtual {v2}, Landroid/hardware/Camera$Parameters;->getMaxNumFocusAreas()I

    move-result v5

    if-lez v5, :cond_3

    .line 156
    invoke-virtual {p2}, Landroid/view/MotionEvent;->getX()F

    move-result v5

    iget-object v6, p0, Lcom/wezka/nativecamera/CameraActivity$1;->this$0:Lcom/wezka/nativecamera/CameraActivity;

    # getter for: Lcom/wezka/nativecamera/CameraActivity;->viewfinderHalfPx:F
    invoke-static {v6}, Lcom/wezka/nativecamera/CameraActivity;->access$300(Lcom/wezka/nativecamera/CameraActivity;)F

    move-result v6

    sub-float/2addr v5, v6

    cmpg-float v5, v5, v9

    if-gez v5, :cond_4

    .line 157
    iget-object v5, p0, Lcom/wezka/nativecamera/CameraActivity$1;->val$viewfinder:Landroid/widget/ImageView;

    invoke-virtual {v5, v9}, Landroid/widget/ImageView;->setX(F)V

    .line 164
    :goto_2
    invoke-virtual {p2}, Landroid/view/MotionEvent;->getY()F

    move-result v5

    iget-object v6, p0, Lcom/wezka/nativecamera/CameraActivity$1;->this$0:Lcom/wezka/nativecamera/CameraActivity;

    # getter for: Lcom/wezka/nativecamera/CameraActivity;->viewfinderHalfPx:F
    invoke-static {v6}, Lcom/wezka/nativecamera/CameraActivity;->access$300(Lcom/wezka/nativecamera/CameraActivity;)F

    move-result v6

    sub-float/2addr v5, v6

    cmpg-float v5, v5, v9

    if-gez v5, :cond_6

    .line 165
    iget-object v5, p0, Lcom/wezka/nativecamera/CameraActivity$1;->val$viewfinder:Landroid/widget/ImageView;

    invoke-virtual {v5, v9}, Landroid/widget/ImageView;->setY(F)V

    .line 172
    :goto_3
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    .line 173
    .local v0, "focusArea":Ljava/util/List;, "Ljava/util/List<Landroid/hardware/Camera$Area;>;"
    new-instance v5, Landroid/hardware/Camera$Area;

    const/16 v6, 0x2ee

    invoke-direct {v5, v1, v6}, Landroid/hardware/Camera$Area;-><init>(Landroid/graphics/Rect;I)V

    invoke-interface {v0, v5}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 174
    invoke-virtual {v2, v0}, Landroid/hardware/Camera$Parameters;->setFocusAreas(Ljava/util/List;)V

    .line 175
    invoke-virtual {v2}, Landroid/hardware/Camera$Parameters;->getMaxNumMeteringAreas()I

    move-result v5

    if-lez v5, :cond_2

    .line 176
    invoke-virtual {v2, v0}, Landroid/hardware/Camera$Parameters;->setMeteringAreas(Ljava/util/List;)V

    .line 179
    :cond_2
    iget-object v5, p0, Lcom/wezka/nativecamera/CameraActivity$1;->this$0:Lcom/wezka/nativecamera/CameraActivity;

    # getter for: Lcom/wezka/nativecamera/CameraActivity;->camera:Landroid/hardware/Camera;
    invoke-static {v5}, Lcom/wezka/nativecamera/CameraActivity;->access$200(Lcom/wezka/nativecamera/CameraActivity;)Landroid/hardware/Camera;

    move-result-object v5

    invoke-virtual {v5, v2}, Landroid/hardware/Camera;->setParameters(Landroid/hardware/Camera$Parameters;)V

    .line 181
    .end local v0    # "focusArea":Ljava/util/List;, "Ljava/util/List<Landroid/hardware/Camera$Area;>;"
    :cond_3
    const/4 v5, 0x1

    goto :goto_1

    .line 158
    :cond_4
    invoke-virtual {p2}, Landroid/view/MotionEvent;->getX()F

    move-result v5

    iget-object v6, p0, Lcom/wezka/nativecamera/CameraActivity$1;->this$0:Lcom/wezka/nativecamera/CameraActivity;

    # getter for: Lcom/wezka/nativecamera/CameraActivity;->viewfinderHalfPx:F
    invoke-static {v6}, Lcom/wezka/nativecamera/CameraActivity;->access$300(Lcom/wezka/nativecamera/CameraActivity;)F

    move-result v6

    add-float/2addr v5, v6

    iget-object v6, p0, Lcom/wezka/nativecamera/CameraActivity$1;->this$0:Lcom/wezka/nativecamera/CameraActivity;

    # getter for: Lcom/wezka/nativecamera/CameraActivity;->screenWidth:I
    invoke-static {v6}, Lcom/wezka/nativecamera/CameraActivity;->access$000(Lcom/wezka/nativecamera/CameraActivity;)I

    move-result v6

    int-to-float v6, v6

    cmpl-float v5, v5, v6

    if-lez v5, :cond_5

    .line 159
    iget-object v5, p0, Lcom/wezka/nativecamera/CameraActivity$1;->val$viewfinder:Landroid/widget/ImageView;

    iget-object v6, p0, Lcom/wezka/nativecamera/CameraActivity$1;->this$0:Lcom/wezka/nativecamera/CameraActivity;

    # getter for: Lcom/wezka/nativecamera/CameraActivity;->screenWidth:I
    invoke-static {v6}, Lcom/wezka/nativecamera/CameraActivity;->access$000(Lcom/wezka/nativecamera/CameraActivity;)I

    move-result v6

    int-to-float v6, v6

    iget-object v7, p0, Lcom/wezka/nativecamera/CameraActivity$1;->this$0:Lcom/wezka/nativecamera/CameraActivity;

    # getter for: Lcom/wezka/nativecamera/CameraActivity;->viewfinderHalfPx:F
    invoke-static {v7}, Lcom/wezka/nativecamera/CameraActivity;->access$300(Lcom/wezka/nativecamera/CameraActivity;)F

    move-result v7

    mul-float/2addr v7, v10

    sub-float/2addr v6, v7

    invoke-virtual {v5, v6}, Landroid/widget/ImageView;->setX(F)V

    goto :goto_2

    .line 161
    :cond_5
    iget-object v5, p0, Lcom/wezka/nativecamera/CameraActivity$1;->val$viewfinder:Landroid/widget/ImageView;

    invoke-virtual {p2}, Landroid/view/MotionEvent;->getX()F

    move-result v6

    iget-object v7, p0, Lcom/wezka/nativecamera/CameraActivity$1;->this$0:Lcom/wezka/nativecamera/CameraActivity;

    # getter for: Lcom/wezka/nativecamera/CameraActivity;->viewfinderHalfPx:F
    invoke-static {v7}, Lcom/wezka/nativecamera/CameraActivity;->access$300(Lcom/wezka/nativecamera/CameraActivity;)F

    move-result v7

    sub-float/2addr v6, v7

    invoke-virtual {v5, v6}, Landroid/widget/ImageView;->setX(F)V

    goto :goto_2

    .line 166
    :cond_6
    invoke-virtual {p2}, Landroid/view/MotionEvent;->getY()F

    move-result v5

    iget-object v6, p0, Lcom/wezka/nativecamera/CameraActivity$1;->this$0:Lcom/wezka/nativecamera/CameraActivity;

    # getter for: Lcom/wezka/nativecamera/CameraActivity;->viewfinderHalfPx:F
    invoke-static {v6}, Lcom/wezka/nativecamera/CameraActivity;->access$300(Lcom/wezka/nativecamera/CameraActivity;)F

    move-result v6

    add-float/2addr v5, v6

    iget-object v6, p0, Lcom/wezka/nativecamera/CameraActivity$1;->this$0:Lcom/wezka/nativecamera/CameraActivity;

    # getter for: Lcom/wezka/nativecamera/CameraActivity;->screenHeight:I
    invoke-static {v6}, Lcom/wezka/nativecamera/CameraActivity;->access$100(Lcom/wezka/nativecamera/CameraActivity;)I

    move-result v6

    int-to-float v6, v6

    iget-object v7, p0, Lcom/wezka/nativecamera/CameraActivity$1;->this$0:Lcom/wezka/nativecamera/CameraActivity;

    # invokes: Lcom/wezka/nativecamera/CameraActivity;->pxFromDp(F)F
    invoke-static {v7, v11}, Lcom/wezka/nativecamera/CameraActivity;->access$400(Lcom/wezka/nativecamera/CameraActivity;F)F

    move-result v7

    sub-float/2addr v6, v7

    cmpl-float v5, v5, v6

    if-lez v5, :cond_7

    .line 167
    iget-object v5, p0, Lcom/wezka/nativecamera/CameraActivity$1;->val$viewfinder:Landroid/widget/ImageView;

    iget-object v6, p0, Lcom/wezka/nativecamera/CameraActivity$1;->this$0:Lcom/wezka/nativecamera/CameraActivity;

    # getter for: Lcom/wezka/nativecamera/CameraActivity;->screenHeight:I
    invoke-static {v6}, Lcom/wezka/nativecamera/CameraActivity;->access$100(Lcom/wezka/nativecamera/CameraActivity;)I

    move-result v6

    int-to-float v6, v6

    iget-object v7, p0, Lcom/wezka/nativecamera/CameraActivity$1;->this$0:Lcom/wezka/nativecamera/CameraActivity;

    # invokes: Lcom/wezka/nativecamera/CameraActivity;->pxFromDp(F)F
    invoke-static {v7, v11}, Lcom/wezka/nativecamera/CameraActivity;->access$400(Lcom/wezka/nativecamera/CameraActivity;F)F

    move-result v7

    sub-float/2addr v6, v7

    iget-object v7, p0, Lcom/wezka/nativecamera/CameraActivity$1;->this$0:Lcom/wezka/nativecamera/CameraActivity;

    # getter for: Lcom/wezka/nativecamera/CameraActivity;->viewfinderHalfPx:F
    invoke-static {v7}, Lcom/wezka/nativecamera/CameraActivity;->access$300(Lcom/wezka/nativecamera/CameraActivity;)F

    move-result v7

    mul-float/2addr v7, v10

    sub-float/2addr v6, v7

    invoke-virtual {v5, v6}, Landroid/widget/ImageView;->setY(F)V

    goto/16 :goto_3

    .line 169
    :cond_7
    iget-object v5, p0, Lcom/wezka/nativecamera/CameraActivity$1;->val$viewfinder:Landroid/widget/ImageView;

    invoke-virtual {p2}, Landroid/view/MotionEvent;->getY()F

    move-result v6

    iget-object v7, p0, Lcom/wezka/nativecamera/CameraActivity$1;->this$0:Lcom/wezka/nativecamera/CameraActivity;

    # getter for: Lcom/wezka/nativecamera/CameraActivity;->viewfinderHalfPx:F
    invoke-static {v7}, Lcom/wezka/nativecamera/CameraActivity;->access$300(Lcom/wezka/nativecamera/CameraActivity;)F

    move-result v7

    sub-float/2addr v6, v7

    invoke-virtual {v5, v6}, Landroid/widget/ImageView;->setY(F)V

    goto/16 :goto_3
.end method
