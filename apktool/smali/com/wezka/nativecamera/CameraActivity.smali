.class public Lcom/wezka/nativecamera/CameraActivity;
.super Landroid/app/Activity;
.source "CameraActivity.java"

# interfaces
.implements Landroid/hardware/SensorEventListener;


# static fields
.field private static final TAG:Ljava/lang/String; = "CameraActivity"

.field private static final _DATA_X:I = 0x0

.field private static final _DATA_Y:I = 0x1

.field private static final _DATA_Z:I = 0x2


# instance fields
.field ORIENTATION_UNKNOWN:I

.field private cam:I

.field private camera:Landroid/hardware/Camera;

.field private cameraConfigured:Z

.field private degrees:I

.field private inPreview:Z

.field private isFlash:Z

.field private isFrontCamera:Z

.field private led:I

.field public mOrientationDeg:I

.field private mPicture:Landroid/hardware/Camera$PictureCallback;

.field mWindowManager:Landroid/view/WindowManager;

.field private pressed:Z

.field private preview:Landroid/view/SurfaceView;

.field private previewHolder:Landroid/view/SurfaceHolder;

.field private screenHeight:I

.field private screenWidth:I

.field sm:Landroid/hardware/SensorManager;

.field surfaceCallback:Landroid/view/SurfaceHolder$Callback;

.field private viewfinderHalfPx:F


# direct methods
.method public constructor <init>()V
    .locals 2

    .prologue
    const/4 v1, 0x0

    const/4 v0, 0x0

    .line 57
    invoke-direct {p0}, Landroid/app/Activity;-><init>()V

    .line 62
    iput-object v1, p0, Lcom/wezka/nativecamera/CameraActivity;->previewHolder:Landroid/view/SurfaceHolder;

    .line 63
    iput-object v1, p0, Lcom/wezka/nativecamera/CameraActivity;->camera:Landroid/hardware/Camera;

    .line 64
    iput-boolean v0, p0, Lcom/wezka/nativecamera/CameraActivity;->inPreview:Z

    .line 65
    iput-boolean v0, p0, Lcom/wezka/nativecamera/CameraActivity;->cameraConfigured:Z

    .line 66
    iput v0, p0, Lcom/wezka/nativecamera/CameraActivity;->led:I

    .line 67
    iput v0, p0, Lcom/wezka/nativecamera/CameraActivity;->cam:I

    .line 68
    iput-boolean v0, p0, Lcom/wezka/nativecamera/CameraActivity;->pressed:Z

    .line 69
    iput v0, p0, Lcom/wezka/nativecamera/CameraActivity;->degrees:I

    .line 70
    iput-boolean v0, p0, Lcom/wezka/nativecamera/CameraActivity;->isFlash:Z

    .line 71
    iput-boolean v0, p0, Lcom/wezka/nativecamera/CameraActivity;->isFrontCamera:Z

    .line 79
    const/4 v0, -0x1

    iput v0, p0, Lcom/wezka/nativecamera/CameraActivity;->ORIENTATION_UNKNOWN:I

    .line 304
    new-instance v0, Lcom/wezka/nativecamera/CameraActivity$6;

    invoke-direct {v0, p0}, Lcom/wezka/nativecamera/CameraActivity$6;-><init>(Lcom/wezka/nativecamera/CameraActivity;)V

    iput-object v0, p0, Lcom/wezka/nativecamera/CameraActivity;->mPicture:Landroid/hardware/Camera$PictureCallback;

    .line 464
    new-instance v0, Lcom/wezka/nativecamera/CameraActivity$7;

    invoke-direct {v0, p0}, Lcom/wezka/nativecamera/CameraActivity$7;-><init>(Lcom/wezka/nativecamera/CameraActivity;)V

    iput-object v0, p0, Lcom/wezka/nativecamera/CameraActivity;->surfaceCallback:Landroid/view/SurfaceHolder$Callback;

    return-void
.end method

.method static synthetic access$000(Lcom/wezka/nativecamera/CameraActivity;)I
    .locals 1
    .param p0, "x0"    # Lcom/wezka/nativecamera/CameraActivity;

    .prologue
    .line 57
    iget v0, p0, Lcom/wezka/nativecamera/CameraActivity;->screenWidth:I

    return v0
.end method

.method static synthetic access$100(Lcom/wezka/nativecamera/CameraActivity;)I
    .locals 1
    .param p0, "x0"    # Lcom/wezka/nativecamera/CameraActivity;

    .prologue
    .line 57
    iget v0, p0, Lcom/wezka/nativecamera/CameraActivity;->screenHeight:I

    return v0
.end method

.method static synthetic access$1000(Lcom/wezka/nativecamera/CameraActivity;)I
    .locals 1
    .param p0, "x0"    # Lcom/wezka/nativecamera/CameraActivity;

    .prologue
    .line 57
    iget v0, p0, Lcom/wezka/nativecamera/CameraActivity;->degrees:I

    return v0
.end method

.method static synthetic access$1100(Lcom/wezka/nativecamera/CameraActivity;)Landroid/hardware/Camera$PictureCallback;
    .locals 1
    .param p0, "x0"    # Lcom/wezka/nativecamera/CameraActivity;

    .prologue
    .line 57
    iget-object v0, p0, Lcom/wezka/nativecamera/CameraActivity;->mPicture:Landroid/hardware/Camera$PictureCallback;

    return-object v0
.end method

.method static synthetic access$1200(Lcom/wezka/nativecamera/CameraActivity;)Landroid/view/SurfaceView;
    .locals 1
    .param p0, "x0"    # Lcom/wezka/nativecamera/CameraActivity;

    .prologue
    .line 57
    iget-object v0, p0, Lcom/wezka/nativecamera/CameraActivity;->preview:Landroid/view/SurfaceView;

    return-object v0
.end method

.method static synthetic access$1300(Lcom/wezka/nativecamera/CameraActivity;I)V
    .locals 0
    .param p0, "x0"    # Lcom/wezka/nativecamera/CameraActivity;
    .param p1, "x1"    # I

    .prologue
    .line 57
    invoke-direct {p0, p1}, Lcom/wezka/nativecamera/CameraActivity;->initPreview(I)V

    return-void
.end method

.method static synthetic access$1400(Lcom/wezka/nativecamera/CameraActivity;)V
    .locals 0
    .param p0, "x0"    # Lcom/wezka/nativecamera/CameraActivity;

    .prologue
    .line 57
    invoke-direct {p0}, Lcom/wezka/nativecamera/CameraActivity;->startPreview()V

    return-void
.end method

.method static synthetic access$200(Lcom/wezka/nativecamera/CameraActivity;)Landroid/hardware/Camera;
    .locals 1
    .param p0, "x0"    # Lcom/wezka/nativecamera/CameraActivity;

    .prologue
    .line 57
    iget-object v0, p0, Lcom/wezka/nativecamera/CameraActivity;->camera:Landroid/hardware/Camera;

    return-object v0
.end method

.method static synthetic access$202(Lcom/wezka/nativecamera/CameraActivity;Landroid/hardware/Camera;)Landroid/hardware/Camera;
    .locals 0
    .param p0, "x0"    # Lcom/wezka/nativecamera/CameraActivity;
    .param p1, "x1"    # Landroid/hardware/Camera;

    .prologue
    .line 57
    iput-object p1, p0, Lcom/wezka/nativecamera/CameraActivity;->camera:Landroid/hardware/Camera;

    return-object p1
.end method

.method static synthetic access$300(Lcom/wezka/nativecamera/CameraActivity;)F
    .locals 1
    .param p0, "x0"    # Lcom/wezka/nativecamera/CameraActivity;

    .prologue
    .line 57
    iget v0, p0, Lcom/wezka/nativecamera/CameraActivity;->viewfinderHalfPx:F

    return v0
.end method

.method static synthetic access$400(Lcom/wezka/nativecamera/CameraActivity;F)F
    .locals 1
    .param p0, "x0"    # Lcom/wezka/nativecamera/CameraActivity;
    .param p1, "x1"    # F

    .prologue
    .line 57
    invoke-direct {p0, p1}, Lcom/wezka/nativecamera/CameraActivity;->pxFromDp(F)F

    move-result v0

    return v0
.end method

.method static synthetic access$500(Lcom/wezka/nativecamera/CameraActivity;)I
    .locals 1
    .param p0, "x0"    # Lcom/wezka/nativecamera/CameraActivity;

    .prologue
    .line 57
    iget v0, p0, Lcom/wezka/nativecamera/CameraActivity;->cam:I

    return v0
.end method

.method static synthetic access$502(Lcom/wezka/nativecamera/CameraActivity;I)I
    .locals 0
    .param p0, "x0"    # Lcom/wezka/nativecamera/CameraActivity;
    .param p1, "x1"    # I

    .prologue
    .line 57
    iput p1, p0, Lcom/wezka/nativecamera/CameraActivity;->cam:I

    return p1
.end method

.method static synthetic access$600(Lcom/wezka/nativecamera/CameraActivity;)I
    .locals 1
    .param p0, "x0"    # Lcom/wezka/nativecamera/CameraActivity;

    .prologue
    .line 57
    iget v0, p0, Lcom/wezka/nativecamera/CameraActivity;->led:I

    return v0
.end method

.method static synthetic access$602(Lcom/wezka/nativecamera/CameraActivity;I)I
    .locals 0
    .param p0, "x0"    # Lcom/wezka/nativecamera/CameraActivity;
    .param p1, "x1"    # I

    .prologue
    .line 57
    iput p1, p0, Lcom/wezka/nativecamera/CameraActivity;->led:I

    return p1
.end method

.method static synthetic access$700(Lcom/wezka/nativecamera/CameraActivity;)Z
    .locals 1
    .param p0, "x0"    # Lcom/wezka/nativecamera/CameraActivity;

    .prologue
    .line 57
    iget-boolean v0, p0, Lcom/wezka/nativecamera/CameraActivity;->isFlash:Z

    return v0
.end method

.method static synthetic access$802(Lcom/wezka/nativecamera/CameraActivity;Z)Z
    .locals 0
    .param p0, "x0"    # Lcom/wezka/nativecamera/CameraActivity;
    .param p1, "x1"    # Z

    .prologue
    .line 57
    iput-boolean p1, p0, Lcom/wezka/nativecamera/CameraActivity;->cameraConfigured:Z

    return p1
.end method

.method static synthetic access$900(Lcom/wezka/nativecamera/CameraActivity;)Z
    .locals 1
    .param p0, "x0"    # Lcom/wezka/nativecamera/CameraActivity;

    .prologue
    .line 57
    iget-boolean v0, p0, Lcom/wezka/nativecamera/CameraActivity;->pressed:Z

    return v0
.end method

.method static synthetic access$902(Lcom/wezka/nativecamera/CameraActivity;Z)Z
    .locals 0
    .param p0, "x0"    # Lcom/wezka/nativecamera/CameraActivity;
    .param p1, "x1"    # Z

    .prologue
    .line 57
    iput-boolean p1, p0, Lcom/wezka/nativecamera/CameraActivity;->pressed:Z

    return p1
.end method

.method private getBestPreviewSize(ILandroid/hardware/Camera$Parameters;)Landroid/hardware/Camera$Size;
    .locals 14
    .param p1, "height"    # I
    .param p2, "parameters"    # Landroid/hardware/Camera$Parameters;

    .prologue
    .line 369
    const-wide v0, 0x3fb999999999999aL    # 0.1

    .line 370
    .local v0, "ASPECT_TOLERANCE":D
    const/4 v3, 0x0

    .line 371
    .local v3, "optimalSize":Landroid/hardware/Camera$Size;
    const-wide v4, 0x7fefffffffffffffL    # Double.MAX_VALUE

    .line 373
    .local v4, "minDiff":D
    invoke-virtual/range {p2 .. p2}, Landroid/hardware/Camera$Parameters;->getSupportedPreviewSizes()Ljava/util/List;

    move-result-object v9

    invoke-interface {v9}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v2

    .local v2, "i$":Ljava/util/Iterator;
    :cond_0
    :goto_0
    invoke-interface {v2}, Ljava/util/Iterator;->hasNext()Z

    move-result v9

    if-eqz v9, :cond_1

    invoke-interface {v2}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v8

    check-cast v8, Landroid/hardware/Camera$Size;

    .line 374
    .local v8, "size":Landroid/hardware/Camera$Size;
    iget v9, v8, Landroid/hardware/Camera$Size;->width:I

    int-to-double v10, v9

    iget v9, v8, Landroid/hardware/Camera$Size;->height:I

    int-to-double v12, v9

    div-double v6, v10, v12

    .line 375
    .local v6, "ratio":D
    int-to-double v10, p1

    sub-double v10, v6, v10

    invoke-static {v10, v11}, Ljava/lang/Math;->abs(D)D

    move-result-wide v10

    const-wide v12, 0x3fb999999999999aL    # 0.1

    cmpl-double v9, v10, v12

    if-gtz v9, :cond_0

    .line 376
    iget v9, v8, Landroid/hardware/Camera$Size;->height:I

    sub-int/2addr v9, p1

    invoke-static {v9}, Ljava/lang/Math;->abs(I)I

    move-result v9

    int-to-double v10, v9

    cmpg-double v9, v10, v4

    if-gez v9, :cond_0

    .line 377
    move-object v3, v8

    .line 378
    iget v9, v8, Landroid/hardware/Camera$Size;->height:I

    sub-int/2addr v9, p1

    invoke-static {v9}, Ljava/lang/Math;->abs(I)I

    move-result v9

    int-to-double v4, v9

    goto :goto_0

    .line 382
    .end local v6    # "ratio":D
    .end local v8    # "size":Landroid/hardware/Camera$Size;
    :cond_1
    if-nez v3, :cond_3

    .line 383
    const-wide v4, 0x7fefffffffffffffL    # Double.MAX_VALUE

    .line 384
    invoke-virtual/range {p2 .. p2}, Landroid/hardware/Camera$Parameters;->getSupportedPreviewSizes()Ljava/util/List;

    move-result-object v9

    invoke-interface {v9}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v2

    :cond_2
    :goto_1
    invoke-interface {v2}, Ljava/util/Iterator;->hasNext()Z

    move-result v9

    if-eqz v9, :cond_3

    invoke-interface {v2}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v8

    check-cast v8, Landroid/hardware/Camera$Size;

    .line 385
    .restart local v8    # "size":Landroid/hardware/Camera$Size;
    iget v9, v8, Landroid/hardware/Camera$Size;->height:I

    sub-int/2addr v9, p1

    invoke-static {v9}, Ljava/lang/Math;->abs(I)I

    move-result v9

    int-to-double v10, v9

    cmpg-double v9, v10, v4

    if-gez v9, :cond_2

    .line 386
    move-object v3, v8

    .line 387
    iget v9, v8, Landroid/hardware/Camera$Size;->height:I

    sub-int/2addr v9, p1

    invoke-static {v9}, Ljava/lang/Math;->abs(I)I

    move-result v9

    int-to-double v4, v9

    goto :goto_1

    .line 391
    .end local v8    # "size":Landroid/hardware/Camera$Size;
    :cond_3
    return-object v3
.end method

.method private getSmallestPictureSize(Landroid/hardware/Camera$Parameters;)Landroid/hardware/Camera$Size;
    .locals 7
    .param p1, "parameters"    # Landroid/hardware/Camera$Parameters;

    .prologue
    .line 439
    const/4 v2, 0x0

    .line 440
    .local v2, "result":Landroid/hardware/Camera$Size;
    invoke-virtual {p1}, Landroid/hardware/Camera$Parameters;->getSupportedPictureSizes()Ljava/util/List;

    move-result-object v5

    invoke-interface {v5}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v0

    .local v0, "i$":Ljava/util/Iterator;
    :cond_0
    :goto_0
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v5

    if-eqz v5, :cond_2

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Landroid/hardware/Camera$Size;

    .line 441
    .local v4, "size":Landroid/hardware/Camera$Size;
    if-nez v2, :cond_1

    .line 442
    move-object v2, v4

    goto :goto_0

    .line 445
    :cond_1
    iget v5, v2, Landroid/hardware/Camera$Size;->width:I

    iget v6, v2, Landroid/hardware/Camera$Size;->height:I

    mul-int v3, v5, v6

    .line 446
    .local v3, "resultArea":I
    iget v5, v4, Landroid/hardware/Camera$Size;->width:I

    iget v6, v4, Landroid/hardware/Camera$Size;->height:I

    mul-int v1, v5, v6

    .line 448
    .local v1, "newArea":I
    if-le v1, v3, :cond_0

    .line 449
    move-object v2, v4

    goto :goto_0

    .line 453
    .end local v1    # "newArea":I
    .end local v3    # "resultArea":I
    .end local v4    # "size":Landroid/hardware/Camera$Size;
    :cond_2
    return-object v2
.end method

.method private initPreview(I)V
    .locals 6
    .param p1, "height"    # I

    .prologue
    .line 395
    iget-object v4, p0, Lcom/wezka/nativecamera/CameraActivity;->camera:Landroid/hardware/Camera;

    if-eqz v4, :cond_3

    iget-object v4, p0, Lcom/wezka/nativecamera/CameraActivity;->previewHolder:Landroid/view/SurfaceHolder;

    invoke-interface {v4}, Landroid/view/SurfaceHolder;->getSurface()Landroid/view/Surface;

    move-result-object v4

    if-eqz v4, :cond_3

    .line 397
    :try_start_0
    iget-object v4, p0, Lcom/wezka/nativecamera/CameraActivity;->camera:Landroid/hardware/Camera;

    iget-object v5, p0, Lcom/wezka/nativecamera/CameraActivity;->previewHolder:Landroid/view/SurfaceHolder;

    invoke-virtual {v4, v5}, Landroid/hardware/Camera;->setPreviewDisplay(Landroid/view/SurfaceHolder;)V
    :try_end_0
    .catch Ljava/lang/Throwable; {:try_start_0 .. :try_end_0} :catch_0

    .line 404
    :goto_0
    iget-boolean v4, p0, Lcom/wezka/nativecamera/CameraActivity;->cameraConfigured:Z

    if-nez v4, :cond_3

    .line 405
    iget-object v4, p0, Lcom/wezka/nativecamera/CameraActivity;->camera:Landroid/hardware/Camera;

    invoke-virtual {v4}, Landroid/hardware/Camera;->getParameters()Landroid/hardware/Camera$Parameters;

    move-result-object v0

    .line 406
    .local v0, "parameters":Landroid/hardware/Camera$Parameters;
    invoke-direct {p0, p1, v0}, Lcom/wezka/nativecamera/CameraActivity;->getBestPreviewSize(ILandroid/hardware/Camera$Parameters;)Landroid/hardware/Camera$Size;

    move-result-object v2

    .line 407
    .local v2, "size":Landroid/hardware/Camera$Size;
    invoke-direct {p0, v0}, Lcom/wezka/nativecamera/CameraActivity;->getSmallestPictureSize(Landroid/hardware/Camera$Parameters;)Landroid/hardware/Camera$Size;

    move-result-object v1

    .line 408
    .local v1, "pictureSize":Landroid/hardware/Camera$Size;
    if-eqz v2, :cond_3

    if-eqz v1, :cond_3

    .line 409
    iget v4, v2, Landroid/hardware/Camera$Size;->width:I

    iget v5, v2, Landroid/hardware/Camera$Size;->height:I

    invoke-virtual {v0, v4, v5}, Landroid/hardware/Camera$Parameters;->setPreviewSize(II)V

    .line 410
    iget v4, v1, Landroid/hardware/Camera$Size;->width:I

    iget v5, v1, Landroid/hardware/Camera$Size;->height:I

    invoke-virtual {v0, v4, v5}, Landroid/hardware/Camera$Parameters;->setPictureSize(II)V

    .line 412
    const/16 v4, 0x100

    invoke-virtual {v0, v4}, Landroid/hardware/Camera$Parameters;->setPictureFormat(I)V

    .line 414
    invoke-virtual {v0}, Landroid/hardware/Camera$Parameters;->getSupportedFocusModes()Ljava/util/List;

    move-result-object v4

    if-eqz v4, :cond_0

    .line 415
    invoke-virtual {v0}, Landroid/hardware/Camera$Parameters;->getSupportedFocusModes()Ljava/util/List;

    move-result-object v4

    const-string v5, "continuous-picture"

    invoke-interface {v4, v5}, Ljava/util/List;->contains(Ljava/lang/Object;)Z

    move-result v4

    if-eqz v4, :cond_4

    .line 416
    const-string v4, "continuous-picture"

    invoke-virtual {v0, v4}, Landroid/hardware/Camera$Parameters;->setFocusMode(Ljava/lang/String;)V

    .line 421
    :cond_0
    :goto_1
    invoke-virtual {v0}, Landroid/hardware/Camera$Parameters;->getSupportedSceneModes()Ljava/util/List;

    move-result-object v4

    if-eqz v4, :cond_1

    .line 422
    invoke-virtual {v0}, Landroid/hardware/Camera$Parameters;->getSupportedSceneModes()Ljava/util/List;

    move-result-object v4

    const-string v5, "auto"

    invoke-interface {v4, v5}, Ljava/util/List;->contains(Ljava/lang/Object;)Z

    move-result v4

    if-eqz v4, :cond_1

    .line 423
    const-string v4, "auto"

    invoke-virtual {v0, v4}, Landroid/hardware/Camera$Parameters;->setSceneMode(Ljava/lang/String;)V

    .line 426
    :cond_1
    invoke-virtual {v0}, Landroid/hardware/Camera$Parameters;->getSupportedWhiteBalance()Ljava/util/List;

    move-result-object v4

    if-eqz v4, :cond_2

    .line 427
    invoke-virtual {v0}, Landroid/hardware/Camera$Parameters;->getSupportedWhiteBalance()Ljava/util/List;

    move-result-object v4

    const-string v5, "auto"

    invoke-interface {v4, v5}, Ljava/util/List;->contains(Ljava/lang/Object;)Z

    move-result v4

    if-eqz v4, :cond_2

    .line 428
    const-string v4, "auto"

    invoke-virtual {v0, v4}, Landroid/hardware/Camera$Parameters;->setWhiteBalance(Ljava/lang/String;)V

    .line 431
    :cond_2
    const/4 v4, 0x1

    iput-boolean v4, p0, Lcom/wezka/nativecamera/CameraActivity;->cameraConfigured:Z

    .line 432
    iget-object v4, p0, Lcom/wezka/nativecamera/CameraActivity;->camera:Landroid/hardware/Camera;

    invoke-virtual {v4, v0}, Landroid/hardware/Camera;->setParameters(Landroid/hardware/Camera$Parameters;)V

    .line 436
    .end local v0    # "parameters":Landroid/hardware/Camera$Parameters;
    .end local v1    # "pictureSize":Landroid/hardware/Camera$Size;
    .end local v2    # "size":Landroid/hardware/Camera$Size;
    :cond_3
    return-void

    .line 399
    :catch_0
    move-exception v3

    .line 400
    .local v3, "t":Ljava/lang/Throwable;
    const-string v4, "PreviewDemo-surfaceCallback"

    const-string v5, "Exception in setPreviewDisplay()"

    invoke-static {v4, v5, v3}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    goto :goto_0

    .line 417
    .end local v3    # "t":Ljava/lang/Throwable;
    .restart local v0    # "parameters":Landroid/hardware/Camera$Parameters;
    .restart local v1    # "pictureSize":Landroid/hardware/Camera$Size;
    .restart local v2    # "size":Landroid/hardware/Camera$Size;
    :cond_4
    invoke-virtual {v0}, Landroid/hardware/Camera$Parameters;->getSupportedFocusModes()Ljava/util/List;

    move-result-object v4

    const-string v5, "auto"

    invoke-interface {v4, v5}, Ljava/util/List;->contains(Ljava/lang/Object;)Z

    move-result v4

    if-eqz v4, :cond_0

    .line 418
    const-string v4, "auto"

    invoke-virtual {v0, v4}, Landroid/hardware/Camera$Parameters;->setFocusMode(Ljava/lang/String;)V

    goto :goto_1
.end method

.method private pxFromDp(F)F
    .locals 1
    .param p1, "dp"    # F

    .prologue
    .line 342
    invoke-virtual {p0}, Lcom/wezka/nativecamera/CameraActivity;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    invoke-virtual {v0}, Landroid/content/res/Resources;->getDisplayMetrics()Landroid/util/DisplayMetrics;

    move-result-object v0

    iget v0, v0, Landroid/util/DisplayMetrics;->density:F

    mul-float/2addr v0, p1

    return v0
.end method

.method private startPreview()V
    .locals 2

    .prologue
    .line 457
    iget-boolean v0, p0, Lcom/wezka/nativecamera/CameraActivity;->cameraConfigured:Z

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/wezka/nativecamera/CameraActivity;->camera:Landroid/hardware/Camera;

    if-eqz v0, :cond_0

    .line 458
    iget-object v0, p0, Lcom/wezka/nativecamera/CameraActivity;->camera:Landroid/hardware/Camera;

    const/16 v1, 0x5a

    invoke-virtual {v0, v1}, Landroid/hardware/Camera;->setDisplayOrientation(I)V

    .line 459
    iget-object v0, p0, Lcom/wezka/nativecamera/CameraActivity;->camera:Landroid/hardware/Camera;

    invoke-virtual {v0}, Landroid/hardware/Camera;->startPreview()V

    .line 460
    const/4 v0, 0x1

    iput-boolean v0, p0, Lcom/wezka/nativecamera/CameraActivity;->inPreview:Z

    .line 462
    :cond_0
    return-void
.end method


# virtual methods
.method public onAccuracyChanged(Landroid/hardware/Sensor;I)V
    .locals 0
    .param p1, "sensor"    # Landroid/hardware/Sensor;
    .param p2, "accuracy"    # I

    .prologue
    .line 558
    return-void
.end method

.method public onCreate(Landroid/os/Bundle;)V
    .locals 14
    .param p1, "savedInstanceState"    # Landroid/os/Bundle;

    .prologue
    .line 88
    invoke-super {p0, p1}, Landroid/app/Activity;->onCreate(Landroid/os/Bundle;)V

    .line 91
    invoke-virtual {p0}, Lcom/wezka/nativecamera/CameraActivity;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    const-string v1, "nativecameraplugin"

    const-string v12, "layout"

    invoke-virtual {p0}, Lcom/wezka/nativecamera/CameraActivity;->getPackageName()Ljava/lang/String;

    move-result-object v13

    invoke-virtual {v0, v1, v12, v13}, Landroid/content/res/Resources;->getIdentifier(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I

    move-result v0

    invoke-virtual {p0, v0}, Lcom/wezka/nativecamera/CameraActivity;->setContentView(I)V

    .line 93
    invoke-virtual {p0}, Lcom/wezka/nativecamera/CameraActivity;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    const-string v1, "preview"

    const-string v12, "id"

    invoke-virtual {p0}, Lcom/wezka/nativecamera/CameraActivity;->getPackageName()Ljava/lang/String;

    move-result-object v13

    invoke-virtual {v0, v1, v12, v13}, Landroid/content/res/Resources;->getIdentifier(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I

    move-result v0

    invoke-virtual {p0, v0}, Lcom/wezka/nativecamera/CameraActivity;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/view/SurfaceView;

    iput-object v0, p0, Lcom/wezka/nativecamera/CameraActivity;->preview:Landroid/view/SurfaceView;

    .line 94
    invoke-virtual {p0}, Lcom/wezka/nativecamera/CameraActivity;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    const-string v1, "flipCamera"

    const-string v12, "id"

    invoke-virtual {p0}, Lcom/wezka/nativecamera/CameraActivity;->getPackageName()Ljava/lang/String;

    move-result-object v13

    invoke-virtual {v0, v1, v12, v13}, Landroid/content/res/Resources;->getIdentifier(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I

    move-result v0

    invoke-virtual {p0, v0}, Lcom/wezka/nativecamera/CameraActivity;->findViewById(I)Landroid/view/View;

    move-result-object v8

    check-cast v8, Landroid/widget/Button;

    .line 95
    .local v8, "flipCamera":Landroid/widget/Button;
    invoke-virtual {p0}, Lcom/wezka/nativecamera/CameraActivity;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    const-string v1, "flashButton"

    const-string v12, "id"

    invoke-virtual {p0}, Lcom/wezka/nativecamera/CameraActivity;->getPackageName()Ljava/lang/String;

    move-result-object v13

    invoke-virtual {v0, v1, v12, v13}, Landroid/content/res/Resources;->getIdentifier(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I

    move-result v0

    invoke-virtual {p0, v0}, Lcom/wezka/nativecamera/CameraActivity;->findViewById(I)Landroid/view/View;

    move-result-object v2

    check-cast v2, Landroid/widget/Button;

    .line 96
    .local v2, "flashButton":Landroid/widget/Button;
    invoke-virtual {p0}, Lcom/wezka/nativecamera/CameraActivity;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    const-string v1, "captureButton"

    const-string v12, "id"

    invoke-virtual {p0}, Lcom/wezka/nativecamera/CameraActivity;->getPackageName()Ljava/lang/String;

    move-result-object v13

    invoke-virtual {v0, v1, v12, v13}, Landroid/content/res/Resources;->getIdentifier(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I

    move-result v0

    invoke-virtual {p0, v0}, Lcom/wezka/nativecamera/CameraActivity;->findViewById(I)Landroid/view/View;

    move-result-object v6

    check-cast v6, Landroid/widget/Button;

    .line 97
    .local v6, "captureButton":Landroid/widget/Button;
    invoke-virtual {p0}, Lcom/wezka/nativecamera/CameraActivity;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    const-string v1, "viewfinder"

    const-string v12, "id"

    invoke-virtual {p0}, Lcom/wezka/nativecamera/CameraActivity;->getPackageName()Ljava/lang/String;

    move-result-object v13

    invoke-virtual {v0, v1, v12, v13}, Landroid/content/res/Resources;->getIdentifier(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I

    move-result v0

    invoke-virtual {p0, v0}, Lcom/wezka/nativecamera/CameraActivity;->findViewById(I)Landroid/view/View;

    move-result-object v11

    check-cast v11, Landroid/widget/ImageView;

    .line 98
    .local v11, "viewfinder":Landroid/widget/ImageView;
    invoke-virtual {p0}, Lcom/wezka/nativecamera/CameraActivity;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    const-string v1, "viewfinderArea"

    const-string v12, "id"

    invoke-virtual {p0}, Lcom/wezka/nativecamera/CameraActivity;->getPackageName()Ljava/lang/String;

    move-result-object v13

    invoke-virtual {v0, v1, v12, v13}, Landroid/content/res/Resources;->getIdentifier(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I

    move-result v0

    invoke-virtual {p0, v0}, Lcom/wezka/nativecamera/CameraActivity;->findViewById(I)Landroid/view/View;

    move-result-object v9

    check-cast v9, Landroid/widget/RelativeLayout;

    .line 99
    .local v9, "focusButton":Landroid/widget/RelativeLayout;
    invoke-virtual {p0}, Lcom/wezka/nativecamera/CameraActivity;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    const-string v1, "@drawable/btn_flash_no"

    const/4 v12, 0x0

    invoke-virtual {p0}, Lcom/wezka/nativecamera/CameraActivity;->getPackageName()Ljava/lang/String;

    move-result-object v13

    invoke-virtual {v0, v1, v12, v13}, Landroid/content/res/Resources;->getIdentifier(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I

    move-result v5

    .line 100
    .local v5, "imgFlashNo":I
    invoke-virtual {p0}, Lcom/wezka/nativecamera/CameraActivity;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    const-string v1, "@drawable/btn_flash_auto"

    const/4 v12, 0x0

    invoke-virtual {p0}, Lcom/wezka/nativecamera/CameraActivity;->getPackageName()Ljava/lang/String;

    move-result-object v13

    invoke-virtual {v0, v1, v12, v13}, Landroid/content/res/Resources;->getIdentifier(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I

    move-result v3

    .line 101
    .local v3, "imgFlashAuto":I
    invoke-virtual {p0}, Lcom/wezka/nativecamera/CameraActivity;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    const-string v1, "@drawable/btn_flash_on"

    const/4 v12, 0x0

    invoke-virtual {p0}, Lcom/wezka/nativecamera/CameraActivity;->getPackageName()Ljava/lang/String;

    move-result-object v13

    invoke-virtual {v0, v1, v12, v13}, Landroid/content/res/Resources;->getIdentifier(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I

    move-result v4

    .line 102
    .local v4, "imgFlashOn":I
    const/high16 v0, 0x42900000    # 72.0f

    invoke-direct {p0, v0}, Lcom/wezka/nativecamera/CameraActivity;->pxFromDp(F)F

    move-result v0

    const/high16 v1, 0x40000000    # 2.0f

    div-float/2addr v0, v1

    iput v0, p0, Lcom/wezka/nativecamera/CameraActivity;->viewfinderHalfPx:F

    .line 104
    const-string v0, "sensor"

    invoke-virtual {p0, v0}, Lcom/wezka/nativecamera/CameraActivity;->getSystemService(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/hardware/SensorManager;

    iput-object v0, p0, Lcom/wezka/nativecamera/CameraActivity;->sm:Landroid/hardware/SensorManager;

    .line 105
    iget-object v0, p0, Lcom/wezka/nativecamera/CameraActivity;->sm:Landroid/hardware/SensorManager;

    const/4 v1, 0x1

    invoke-virtual {v0, v1}, Landroid/hardware/SensorManager;->getSensorList(I)Ljava/util/List;

    move-result-object v0

    invoke-interface {v0}, Ljava/util/List;->size()I

    move-result v0

    if-eqz v0, :cond_0

    .line 106
    iget-object v0, p0, Lcom/wezka/nativecamera/CameraActivity;->sm:Landroid/hardware/SensorManager;

    const/4 v1, 0x1

    invoke-virtual {v0, v1}, Landroid/hardware/SensorManager;->getSensorList(I)Ljava/util/List;

    move-result-object v0

    const/4 v1, 0x0

    invoke-interface {v0, v1}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v10

    check-cast v10, Landroid/hardware/Sensor;

    .line 107
    .local v10, "s":Landroid/hardware/Sensor;
    iget-object v0, p0, Lcom/wezka/nativecamera/CameraActivity;->sm:Landroid/hardware/SensorManager;

    const/4 v1, 0x3

    invoke-virtual {v0, p0, v10, v1}, Landroid/hardware/SensorManager;->registerListener(Landroid/hardware/SensorEventListener;Landroid/hardware/Sensor;I)Z

    .line 110
    .end local v10    # "s":Landroid/hardware/Sensor;
    :cond_0
    const-string v0, "window"

    invoke-virtual {p0, v0}, Lcom/wezka/nativecamera/CameraActivity;->getSystemService(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/view/WindowManager;

    iput-object v0, p0, Lcom/wezka/nativecamera/CameraActivity;->mWindowManager:Landroid/view/WindowManager;

    .line 112
    invoke-virtual {p0}, Lcom/wezka/nativecamera/CameraActivity;->getPackageManager()Landroid/content/pm/PackageManager;

    move-result-object v0

    const-string v1, "android.hardware.camera.flash"

    invoke-virtual {v0, v1}, Landroid/content/pm/PackageManager;->hasSystemFeature(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_3

    .line 113
    const/4 v0, 0x0

    invoke-virtual {v2, v0}, Landroid/widget/Button;->setVisibility(I)V

    .line 114
    const/4 v0, 0x1

    iput-boolean v0, p0, Lcom/wezka/nativecamera/CameraActivity;->isFlash:Z

    .line 120
    :goto_0
    invoke-static {}, Landroid/hardware/Camera;->getNumberOfCameras()I

    move-result v0

    const/4 v1, 0x1

    if-le v0, v1, :cond_4

    .line 121
    const/4 v0, 0x0

    invoke-virtual {v8, v0}, Landroid/widget/Button;->setVisibility(I)V

    .line 122
    const/4 v0, 0x1

    iput-boolean v0, p0, Lcom/wezka/nativecamera/CameraActivity;->isFrontCamera:Z

    .line 128
    :goto_1
    invoke-virtual {p0}, Lcom/wezka/nativecamera/CameraActivity;->getWindowManager()Landroid/view/WindowManager;

    move-result-object v0

    invoke-interface {v0}, Landroid/view/WindowManager;->getDefaultDisplay()Landroid/view/Display;

    move-result-object v7

    .line 130
    .local v7, "display":Landroid/view/Display;
    invoke-virtual {v7}, Landroid/view/Display;->getWidth()I

    move-result v0

    iput v0, p0, Lcom/wezka/nativecamera/CameraActivity;->screenWidth:I

    .line 131
    invoke-virtual {v7}, Landroid/view/Display;->getHeight()I

    move-result v0

    iput v0, p0, Lcom/wezka/nativecamera/CameraActivity;->screenHeight:I

    .line 134
    new-instance v0, Lcom/wezka/nativecamera/CameraActivity$1;

    invoke-direct {v0, p0, v11}, Lcom/wezka/nativecamera/CameraActivity$1;-><init>(Lcom/wezka/nativecamera/CameraActivity;Landroid/widget/ImageView;)V

    invoke-virtual {v9, v0}, Landroid/widget/RelativeLayout;->setOnTouchListener(Landroid/view/View$OnTouchListener;)V

    .line 186
    iget-boolean v0, p0, Lcom/wezka/nativecamera/CameraActivity;->isFrontCamera:Z

    if-eqz v0, :cond_1

    .line 187
    new-instance v0, Lcom/wezka/nativecamera/CameraActivity$2;

    invoke-direct {v0, p0, v11, v2, v5}, Lcom/wezka/nativecamera/CameraActivity$2;-><init>(Lcom/wezka/nativecamera/CameraActivity;Landroid/widget/ImageView;Landroid/widget/Button;I)V

    invoke-virtual {v8, v0}, Landroid/widget/Button;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 210
    :cond_1
    iget-boolean v0, p0, Lcom/wezka/nativecamera/CameraActivity;->isFlash:Z

    if-eqz v0, :cond_2

    .line 211
    new-instance v0, Lcom/wezka/nativecamera/CameraActivity$3;

    move-object v1, p0

    invoke-direct/range {v0 .. v5}, Lcom/wezka/nativecamera/CameraActivity$3;-><init>(Lcom/wezka/nativecamera/CameraActivity;Landroid/widget/Button;III)V

    invoke-virtual {v2, v0}, Landroid/widget/Button;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 233
    :cond_2
    new-instance v0, Lcom/wezka/nativecamera/CameraActivity$4;

    invoke-direct {v0, p0}, Lcom/wezka/nativecamera/CameraActivity$4;-><init>(Lcom/wezka/nativecamera/CameraActivity;)V

    invoke-virtual {v6, v0}, Landroid/widget/Button;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 266
    return-void

    .line 116
    .end local v7    # "display":Landroid/view/Display;
    :cond_3
    const/4 v0, 0x4

    invoke-virtual {v2, v0}, Landroid/widget/Button;->setVisibility(I)V

    .line 117
    const/4 v0, 0x0

    iput-boolean v0, p0, Lcom/wezka/nativecamera/CameraActivity;->isFlash:Z

    goto :goto_0

    .line 124
    :cond_4
    const/4 v0, 0x4

    invoke-virtual {v8, v0}, Landroid/widget/Button;->setVisibility(I)V

    .line 125
    const/4 v0, 0x0

    iput-boolean v0, p0, Lcom/wezka/nativecamera/CameraActivity;->isFrontCamera:Z

    goto :goto_1
.end method

.method protected onDestroy()V
    .locals 1

    .prologue
    .line 489
    iget-object v0, p0, Lcom/wezka/nativecamera/CameraActivity;->sm:Landroid/hardware/SensorManager;

    invoke-virtual {v0, p0}, Landroid/hardware/SensorManager;->unregisterListener(Landroid/hardware/SensorEventListener;)V

    .line 490
    invoke-super {p0}, Landroid/app/Activity;->onDestroy()V

    .line 491
    return-void
.end method

.method public onKeyDown(ILandroid/view/KeyEvent;)Z
    .locals 6
    .param p1, "keyCode"    # I
    .param p2, "event"    # Landroid/view/KeyEvent;

    .prologue
    const/4 v2, 0x1

    const/4 v3, 0x0

    .line 270
    const/16 v4, 0x19

    if-ne p1, v4, :cond_2

    .line 271
    iget-object v4, p0, Lcom/wezka/nativecamera/CameraActivity;->camera:Landroid/hardware/Camera;

    invoke-virtual {v4}, Landroid/hardware/Camera;->getParameters()Landroid/hardware/Camera$Parameters;

    move-result-object v1

    .line 272
    .local v1, "p":Landroid/hardware/Camera$Parameters;
    iget v4, p0, Lcom/wezka/nativecamera/CameraActivity;->degrees:I

    invoke-virtual {v1, v4}, Landroid/hardware/Camera$Parameters;->setRotation(I)V

    .line 273
    iget-object v4, p0, Lcom/wezka/nativecamera/CameraActivity;->camera:Landroid/hardware/Camera;

    invoke-virtual {v4, v1}, Landroid/hardware/Camera;->setParameters(Landroid/hardware/Camera$Parameters;)V

    .line 274
    iget-boolean v4, p0, Lcom/wezka/nativecamera/CameraActivity;->pressed:Z

    if-nez v4, :cond_0

    iget-object v4, p0, Lcom/wezka/nativecamera/CameraActivity;->camera:Landroid/hardware/Camera;

    if-nez v4, :cond_1

    :cond_0
    move v2, v3

    .line 300
    .end local v1    # "p":Landroid/hardware/Camera$Parameters;
    :goto_0
    return v2

    .line 276
    .restart local v1    # "p":Landroid/hardware/Camera$Parameters;
    :cond_1
    iput-boolean v2, p0, Lcom/wezka/nativecamera/CameraActivity;->pressed:Z

    .line 279
    :try_start_0
    iget-object v4, p0, Lcom/wezka/nativecamera/CameraActivity;->camera:Landroid/hardware/Camera;

    new-instance v5, Lcom/wezka/nativecamera/CameraActivity$5;

    invoke-direct {v5, p0}, Lcom/wezka/nativecamera/CameraActivity$5;-><init>(Lcom/wezka/nativecamera/CameraActivity;)V

    invoke-virtual {v4, v5}, Landroid/hardware/Camera;->autoFocus(Landroid/hardware/Camera$AutoFocusCallback;)V
    :try_end_0
    .catch Ljava/lang/RuntimeException; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    .line 292
    :catch_0
    move-exception v0

    .line 294
    .local v0, "ex":Ljava/lang/RuntimeException;
    invoke-virtual {p0}, Lcom/wezka/nativecamera/CameraActivity;->getApplicationContext()Landroid/content/Context;

    move-result-object v4

    const-string v5, "Error focusing"

    invoke-static {v4, v5, v3}, Landroid/widget/Toast;->makeText(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;

    move-result-object v3

    invoke-virtual {v3}, Landroid/widget/Toast;->show()V

    .line 296
    const-string v3, "CameraActivity"

    const-string v4, "Auto-focus crash"

    invoke-static {v3, v4}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    goto :goto_0

    .line 300
    .end local v0    # "ex":Ljava/lang/RuntimeException;
    .end local v1    # "p":Landroid/hardware/Camera$Parameters;
    :cond_2
    invoke-super {p0, p1, p2}, Landroid/app/Activity;->onKeyDown(ILandroid/view/KeyEvent;)Z

    move-result v2

    goto :goto_0
.end method

.method public onPause()V
    .locals 1

    .prologue
    .line 357
    iget-boolean v0, p0, Lcom/wezka/nativecamera/CameraActivity;->inPreview:Z

    if-eqz v0, :cond_0

    .line 358
    iget-object v0, p0, Lcom/wezka/nativecamera/CameraActivity;->camera:Landroid/hardware/Camera;

    invoke-virtual {v0}, Landroid/hardware/Camera;->stopPreview()V

    .line 360
    :cond_0
    iget-object v0, p0, Lcom/wezka/nativecamera/CameraActivity;->camera:Landroid/hardware/Camera;

    invoke-virtual {v0}, Landroid/hardware/Camera;->release()V

    .line 361
    const/4 v0, 0x0

    iput-object v0, p0, Lcom/wezka/nativecamera/CameraActivity;->camera:Landroid/hardware/Camera;

    .line 362
    const/4 v0, 0x0

    iput-boolean v0, p0, Lcom/wezka/nativecamera/CameraActivity;->inPreview:Z

    .line 363
    invoke-super {p0}, Landroid/app/Activity;->onPause()V

    .line 364
    return-void
.end method

.method public onResume()V
    .locals 2

    .prologue
    .line 326
    invoke-super {p0}, Landroid/app/Activity;->onResume()V

    .line 327
    iget-object v0, p0, Lcom/wezka/nativecamera/CameraActivity;->preview:Landroid/view/SurfaceView;

    invoke-virtual {v0}, Landroid/view/SurfaceView;->getHolder()Landroid/view/SurfaceHolder;

    move-result-object v0

    iput-object v0, p0, Lcom/wezka/nativecamera/CameraActivity;->previewHolder:Landroid/view/SurfaceHolder;

    .line 328
    iget-object v0, p0, Lcom/wezka/nativecamera/CameraActivity;->previewHolder:Landroid/view/SurfaceHolder;

    const/4 v1, 0x3

    invoke-interface {v0, v1}, Landroid/view/SurfaceHolder;->setType(I)V

    .line 329
    iget-object v0, p0, Lcom/wezka/nativecamera/CameraActivity;->previewHolder:Landroid/view/SurfaceHolder;

    iget-object v1, p0, Lcom/wezka/nativecamera/CameraActivity;->surfaceCallback:Landroid/view/SurfaceHolder$Callback;

    invoke-interface {v0, v1}, Landroid/view/SurfaceHolder;->addCallback(Landroid/view/SurfaceHolder$Callback;)V

    .line 330
    invoke-static {}, Landroid/hardware/Camera;->getNumberOfCameras()I

    move-result v0

    const/4 v1, 0x1

    if-lt v0, v1, :cond_0

    .line 331
    iget v0, p0, Lcom/wezka/nativecamera/CameraActivity;->cam:I

    invoke-static {v0}, Landroid/hardware/Camera;->open(I)Landroid/hardware/Camera;

    move-result-object v0

    iput-object v0, p0, Lcom/wezka/nativecamera/CameraActivity;->camera:Landroid/hardware/Camera;

    .line 335
    :cond_0
    iget-object v0, p0, Lcom/wezka/nativecamera/CameraActivity;->preview:Landroid/view/SurfaceView;

    invoke-virtual {v0}, Landroid/view/SurfaceView;->getHeight()I

    move-result v0

    if-lez v0, :cond_1

    .line 336
    iget-object v0, p0, Lcom/wezka/nativecamera/CameraActivity;->preview:Landroid/view/SurfaceView;

    invoke-virtual {v0}, Landroid/view/SurfaceView;->getHeight()I

    move-result v0

    invoke-direct {p0, v0}, Lcom/wezka/nativecamera/CameraActivity;->initPreview(I)V

    .line 337
    invoke-direct {p0}, Lcom/wezka/nativecamera/CameraActivity;->startPreview()V

    .line 339
    :cond_1
    return-void
.end method

.method public onSensorChanged(Landroid/hardware/SensorEvent;)V
    .locals 12
    .param p1, "event"    # Landroid/hardware/SensorEvent;

    .prologue
    .line 496
    iget-object v8, p1, Landroid/hardware/SensorEvent;->sensor:Landroid/hardware/Sensor;

    invoke-virtual {v8}, Landroid/hardware/Sensor;->getType()I

    move-result v8

    const/4 v9, 0x1

    if-ne v8, v9, :cond_2

    .line 497
    iget-object v7, p1, Landroid/hardware/SensorEvent;->values:[F

    .line 498
    .local v7, "values":[F
    iget v6, p0, Lcom/wezka/nativecamera/CameraActivity;->ORIENTATION_UNKNOWN:I

    .line 499
    .local v6, "orientation":I
    const/4 v8, 0x0

    aget v8, v7, v8

    neg-float v1, v8

    .line 500
    .local v1, "X":F
    const/4 v8, 0x1

    aget v8, v7, v8

    neg-float v2, v8

    .line 501
    .local v2, "Y":F
    const/4 v8, 0x2

    aget v8, v7, v8

    neg-float v3, v8

    .line 502
    .local v3, "Z":F
    mul-float v8, v1, v1

    mul-float v9, v2, v2

    add-float v5, v8, v9

    .line 504
    .local v5, "magnitude":F
    const/high16 v8, 0x40800000    # 4.0f

    mul-float/2addr v8, v5

    mul-float v9, v3, v3

    cmpl-float v8, v8, v9

    if-ltz v8, :cond_1

    .line 505
    const v0, 0x42652ee1

    .line 506
    .local v0, "OneEightyOverPi":F
    neg-float v8, v2

    float-to-double v8, v8

    float-to-double v10, v1

    invoke-static {v8, v9, v10, v11}, Ljava/lang/Math;->atan2(DD)D

    move-result-wide v8

    double-to-float v8, v8

    mul-float v4, v8, v0

    .line 507
    .local v4, "angle":F
    invoke-static {v4}, Ljava/lang/Math;->round(F)I

    move-result v8

    rsub-int/lit8 v6, v8, 0x5a

    .line 509
    :goto_0
    const/16 v8, 0x168

    if-lt v6, v8, :cond_0

    .line 510
    add-int/lit16 v6, v6, -0x168

    goto :goto_0

    .line 512
    :cond_0
    :goto_1
    if-gez v6, :cond_1

    .line 513
    add-int/lit16 v6, v6, 0x168

    goto :goto_1

    .line 518
    .end local v0    # "OneEightyOverPi":F
    .end local v4    # "angle":F
    :cond_1
    iget v8, p0, Lcom/wezka/nativecamera/CameraActivity;->mOrientationDeg:I

    if-eq v6, v8, :cond_2

    .line 519
    iput v6, p0, Lcom/wezka/nativecamera/CameraActivity;->mOrientationDeg:I

    .line 521
    const/4 v8, -0x1

    if-ne v6, v8, :cond_4

    .line 522
    iget v8, p0, Lcom/wezka/nativecamera/CameraActivity;->cam:I

    const/4 v9, 0x1

    if-ne v8, v9, :cond_3

    .line 523
    const/16 v8, 0x10e

    iput v8, p0, Lcom/wezka/nativecamera/CameraActivity;->degrees:I

    .line 553
    .end local v1    # "X":F
    .end local v2    # "Y":F
    .end local v3    # "Z":F
    .end local v5    # "magnitude":F
    .end local v6    # "orientation":I
    .end local v7    # "values":[F
    :cond_2
    :goto_2
    return-void

    .line 525
    .restart local v1    # "X":F
    .restart local v2    # "Y":F
    .restart local v3    # "Z":F
    .restart local v5    # "magnitude":F
    .restart local v6    # "orientation":I
    .restart local v7    # "values":[F
    :cond_3
    const/16 v8, 0x5a

    iput v8, p0, Lcom/wezka/nativecamera/CameraActivity;->degrees:I

    goto :goto_2

    .line 528
    :cond_4
    const/16 v8, 0x2d

    if-le v6, v8, :cond_5

    const/16 v8, 0x13b

    if-le v6, v8, :cond_7

    .line 529
    :cond_5
    iget v8, p0, Lcom/wezka/nativecamera/CameraActivity;->cam:I

    const/4 v9, 0x1

    if-ne v8, v9, :cond_6

    .line 530
    const/16 v8, 0x10e

    iput v8, p0, Lcom/wezka/nativecamera/CameraActivity;->degrees:I

    goto :goto_2

    .line 532
    :cond_6
    const/16 v8, 0x5a

    iput v8, p0, Lcom/wezka/nativecamera/CameraActivity;->degrees:I

    goto :goto_2

    .line 535
    :cond_7
    const/16 v8, 0x2d

    if-le v6, v8, :cond_8

    const/16 v8, 0x87

    if-gt v6, v8, :cond_8

    .line 536
    const/16 v8, 0xb4

    iput v8, p0, Lcom/wezka/nativecamera/CameraActivity;->degrees:I

    goto :goto_2

    .line 539
    :cond_8
    const/16 v8, 0x87

    if-le v6, v8, :cond_a

    const/16 v8, 0xe1

    if-gt v6, v8, :cond_a

    .line 540
    iget v8, p0, Lcom/wezka/nativecamera/CameraActivity;->cam:I

    const/4 v9, 0x1

    if-ne v8, v9, :cond_9

    .line 541
    const/16 v8, 0x5a

    iput v8, p0, Lcom/wezka/nativecamera/CameraActivity;->degrees:I

    goto :goto_2

    .line 543
    :cond_9
    const/16 v8, 0x10e

    iput v8, p0, Lcom/wezka/nativecamera/CameraActivity;->degrees:I

    goto :goto_2

    .line 546
    :cond_a
    const/16 v8, 0xe1

    if-le v6, v8, :cond_2

    const/16 v8, 0x13b

    if-gt v6, v8, :cond_2

    .line 547
    const/4 v8, 0x0

    iput v8, p0, Lcom/wezka/nativecamera/CameraActivity;->degrees:I

    goto :goto_2
.end method

.method restartPreview(I)V
    .locals 1
    .param p1, "isFront"    # I

    .prologue
    .line 346
    iget-boolean v0, p0, Lcom/wezka/nativecamera/CameraActivity;->inPreview:Z

    if-eqz v0, :cond_0

    .line 347
    iget-object v0, p0, Lcom/wezka/nativecamera/CameraActivity;->camera:Landroid/hardware/Camera;

    invoke-virtual {v0}, Landroid/hardware/Camera;->stopPreview()V

    .line 349
    :cond_0
    iget-object v0, p0, Lcom/wezka/nativecamera/CameraActivity;->camera:Landroid/hardware/Camera;

    invoke-virtual {v0}, Landroid/hardware/Camera;->release()V

    .line 350
    invoke-static {p1}, Landroid/hardware/Camera;->open(I)Landroid/hardware/Camera;

    move-result-object v0

    iput-object v0, p0, Lcom/wezka/nativecamera/CameraActivity;->camera:Landroid/hardware/Camera;

    .line 351
    iget-object v0, p0, Lcom/wezka/nativecamera/CameraActivity;->preview:Landroid/view/SurfaceView;

    invoke-virtual {v0}, Landroid/view/SurfaceView;->getHeight()I

    move-result v0

    invoke-direct {p0, v0}, Lcom/wezka/nativecamera/CameraActivity;->initPreview(I)V

    .line 352
    invoke-direct {p0}, Lcom/wezka/nativecamera/CameraActivity;->startPreview()V

    .line 353
    return-void
.end method
