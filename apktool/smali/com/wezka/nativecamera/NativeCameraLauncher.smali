.class public Lcom/wezka/nativecamera/NativeCameraLauncher;
.super Lorg/apache/cordova/CordovaPlugin;
.source "NativeCameraLauncher.java"


# static fields
.field private static final LOG_TAG:Ljava/lang/String; = "NativeCameraLauncher"

.field private static final _DATA:Ljava/lang/String; = "_data"


# instance fields
.field private callbackContext:Lorg/apache/cordova/CallbackContext;

.field private date:Ljava/lang/String;

.field private imageUri:Landroid/net/Uri;

.field private mQuality:I

.field private photo:Ljava/io/File;

.field private targetHeight:I

.field private targetWidth:I


# direct methods
.method public constructor <init>()V
    .locals 1

    .prologue
    .line 78
    invoke-direct {p0}, Lorg/apache/cordova/CordovaPlugin;-><init>()V

    .line 76
    const/4 v0, 0x0

    iput-object v0, p0, Lcom/wezka/nativecamera/NativeCameraLauncher;->date:Ljava/lang/String;

    .line 79
    return-void
.end method

.method private createCaptureFile()Ljava/io/File;
    .locals 6

    .prologue
    .line 122
    new-instance v1, Ljava/io/File;

    iget-object v3, p0, Lcom/wezka/nativecamera/NativeCameraLauncher;->cordova:Lorg/apache/cordova/CordovaInterface;

    invoke-interface {v3}, Lorg/apache/cordova/CordovaInterface;->getActivity()Landroid/app/Activity;

    move-result-object v3

    invoke-virtual {v3}, Landroid/app/Activity;->getApplicationContext()Landroid/content/Context;

    move-result-object v3

    invoke-direct {p0, v3}, Lcom/wezka/nativecamera/NativeCameraLauncher;->getTempDirectoryPath(Landroid/content/Context;)Ljava/lang/String;

    move-result-object v3

    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    const-string v5, "Pic-"

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    iget-object v5, p0, Lcom/wezka/nativecamera/NativeCameraLauncher;->date:Ljava/lang/String;

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    const-string v5, ".jpg"

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    invoke-direct {v1, v3, v4}, Ljava/io/File;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    .line 123
    .local v1, "oldFile":Ljava/io/File;
    invoke-virtual {v1}, Ljava/io/File;->exists()Z

    move-result v3

    if-eqz v3, :cond_0

    .line 124
    invoke-virtual {v1}, Ljava/io/File;->delete()Z

    .line 126
    :cond_0
    invoke-static {}, Ljava/util/Calendar;->getInstance()Ljava/util/Calendar;

    move-result-object v0

    .line 127
    .local v0, "c":Ljava/util/Calendar;
    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    const-string v4, ""

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    const/4 v4, 0x5

    invoke-virtual {v0, v4}, Ljava/util/Calendar;->get(I)I

    move-result v4

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v3

    const/4 v4, 0x2

    invoke-virtual {v0, v4}, Ljava/util/Calendar;->get(I)I

    move-result v4

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v3

    const/4 v4, 0x1

    invoke-virtual {v0, v4}, Ljava/util/Calendar;->get(I)I

    move-result v4

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v3

    const/16 v4, 0xb

    invoke-virtual {v0, v4}, Ljava/util/Calendar;->get(I)I

    move-result v4

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v3

    const/16 v4, 0xc

    invoke-virtual {v0, v4}, Ljava/util/Calendar;->get(I)I

    move-result v4

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v3

    const/16 v4, 0xd

    invoke-virtual {v0, v4}, Ljava/util/Calendar;->get(I)I

    move-result v4

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3

    iput-object v3, p0, Lcom/wezka/nativecamera/NativeCameraLauncher;->date:Ljava/lang/String;

    .line 134
    new-instance v2, Ljava/io/File;

    iget-object v3, p0, Lcom/wezka/nativecamera/NativeCameraLauncher;->cordova:Lorg/apache/cordova/CordovaInterface;

    invoke-interface {v3}, Lorg/apache/cordova/CordovaInterface;->getActivity()Landroid/app/Activity;

    move-result-object v3

    invoke-virtual {v3}, Landroid/app/Activity;->getApplicationContext()Landroid/content/Context;

    move-result-object v3

    invoke-direct {p0, v3}, Lcom/wezka/nativecamera/NativeCameraLauncher;->getTempDirectoryPath(Landroid/content/Context;)Ljava/lang/String;

    move-result-object v3

    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    const-string v5, "Pic-"

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    iget-object v5, p0, Lcom/wezka/nativecamera/NativeCameraLauncher;->date:Ljava/lang/String;

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    const-string v5, ".jpg"

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    invoke-direct {v2, v3, v4}, Ljava/io/File;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    .line 135
    .local v2, "photo":Ljava/io/File;
    return-object v2
.end method

.method private getRotatedBitmap(ILandroid/graphics/Bitmap;Lorg/apache/cordova/ExifHelper;)Landroid/graphics/Bitmap;
    .locals 7
    .param p1, "rotate"    # I
    .param p2, "bitmap"    # Landroid/graphics/Bitmap;
    .param p3, "exif"    # Lorg/apache/cordova/ExifHelper;

    .prologue
    .line 246
    new-instance v5, Landroid/graphics/Matrix;

    invoke-direct {v5}, Landroid/graphics/Matrix;-><init>()V

    .line 247
    .local v5, "matrix":Landroid/graphics/Matrix;
    int-to-float v0, p1

    invoke-virtual {v5, v0}, Landroid/graphics/Matrix;->setRotate(F)V

    .line 250
    const/4 v1, 0x0

    const/4 v2, 0x0

    :try_start_0
    invoke-virtual {p2}, Landroid/graphics/Bitmap;->getWidth()I

    move-result v3

    invoke-virtual {p2}, Landroid/graphics/Bitmap;->getHeight()I

    move-result v4

    const/4 v6, 0x1

    move-object v0, p2

    invoke-static/range {v0 .. v6}, Landroid/graphics/Bitmap;->createBitmap(Landroid/graphics/Bitmap;IIIILandroid/graphics/Matrix;Z)Landroid/graphics/Bitmap;

    move-result-object p2

    .line 251
    invoke-virtual {p3}, Lorg/apache/cordova/ExifHelper;->resetOrientation()V
    :try_end_0
    .catch Ljava/lang/OutOfMemoryError; {:try_start_0 .. :try_end_0} :catch_0

    .line 260
    :goto_0
    return-object p2

    .line 253
    :catch_0
    move-exception v0

    goto :goto_0
.end method

.method private getTempDirectoryPath(Landroid/content/Context;)Ljava/lang/String;
    .locals 3
    .param p1, "ctx"    # Landroid/content/Context;

    .prologue
    .line 264
    const/4 v0, 0x0

    .line 267
    .local v0, "cache":Ljava/io/File;
    invoke-static {}, Landroid/os/Environment;->getExternalStorageState()Ljava/lang/String;

    move-result-object v1

    const-string v2, "mounted"

    invoke-virtual {v1, v2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_1

    .line 269
    new-instance v0, Ljava/io/File;

    .end local v0    # "cache":Ljava/io/File;
    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    invoke-static {}, Landroid/os/Environment;->getExternalStorageDirectory()Ljava/io/File;

    move-result-object v2

    invoke-virtual {v2}, Ljava/io/File;->getAbsolutePath()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v2, "/Android/data/"

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {p1}, Landroid/content/Context;->getPackageName()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v2, "/cache/"

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    .line 280
    .restart local v0    # "cache":Ljava/io/File;
    :goto_0
    invoke-virtual {v0}, Ljava/io/File;->exists()Z

    move-result v1

    if-nez v1, :cond_0

    .line 281
    invoke-virtual {v0}, Ljava/io/File;->mkdirs()Z

    .line 284
    :cond_0
    invoke-virtual {v0}, Ljava/io/File;->getAbsolutePath()Ljava/lang/String;

    move-result-object v1

    return-object v1

    .line 276
    :cond_1
    invoke-virtual {p1}, Landroid/content/Context;->getCacheDir()Ljava/io/File;

    move-result-object v0

    goto :goto_0
.end method


# virtual methods
.method public execute(Ljava/lang/String;Lorg/json/JSONArray;Lorg/apache/cordova/CallbackContext;)Z
    .locals 7
    .param p1, "action"    # Ljava/lang/String;
    .param p2, "args"    # Lorg/json/JSONArray;
    .param p3, "callbackContext"    # Lorg/apache/cordova/CallbackContext;
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Lorg/json/JSONException;
        }
    .end annotation

    .prologue
    const/4 v4, 0x1

    const/4 v5, 0x0

    .line 87
    sget-object v3, Lorg/apache/cordova/PluginResult$Status;->OK:Lorg/apache/cordova/PluginResult$Status;

    .line 88
    .local v3, "status":Lorg/apache/cordova/PluginResult$Status;
    const-string v2, ""

    .line 89
    .local v2, "result":Ljava/lang/String;
    iput-object p3, p0, Lcom/wezka/nativecamera/NativeCameraLauncher;->callbackContext:Lorg/apache/cordova/CallbackContext;

    .line 91
    :try_start_0
    const-string v6, "takePicture"

    invoke-virtual {p1, v6}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v6

    if-eqz v6, :cond_0

    .line 92
    const/4 v5, 0x0

    iput v5, p0, Lcom/wezka/nativecamera/NativeCameraLauncher;->targetHeight:I

    .line 93
    const/4 v5, 0x0

    iput v5, p0, Lcom/wezka/nativecamera/NativeCameraLauncher;->targetWidth:I

    .line 94
    const/16 v5, 0x50

    iput v5, p0, Lcom/wezka/nativecamera/NativeCameraLauncher;->mQuality:I

    .line 95
    const/4 v5, 0x4

    invoke-virtual {p2, v5}, Lorg/json/JSONArray;->getInt(I)I

    move-result v5

    iput v5, p0, Lcom/wezka/nativecamera/NativeCameraLauncher;->targetHeight:I

    .line 96
    const/4 v5, 0x3

    invoke-virtual {p2, v5}, Lorg/json/JSONArray;->getInt(I)I

    move-result v5

    iput v5, p0, Lcom/wezka/nativecamera/NativeCameraLauncher;->targetWidth:I

    .line 97
    const/4 v5, 0x0

    invoke-virtual {p2, v5}, Lorg/json/JSONArray;->getInt(I)I

    move-result v5

    iput v5, p0, Lcom/wezka/nativecamera/NativeCameraLauncher;->mQuality:I

    .line 98
    invoke-virtual {p0}, Lcom/wezka/nativecamera/NativeCameraLauncher;->takePicture()V

    .line 99
    new-instance v1, Lorg/apache/cordova/PluginResult;

    sget-object v5, Lorg/apache/cordova/PluginResult$Status;->NO_RESULT:Lorg/apache/cordova/PluginResult$Status;

    invoke-direct {v1, v5}, Lorg/apache/cordova/PluginResult;-><init>(Lorg/apache/cordova/PluginResult$Status;)V

    .line 100
    .local v1, "r":Lorg/apache/cordova/PluginResult;
    const/4 v5, 0x1

    invoke-virtual {v1, v5}, Lorg/apache/cordova/PluginResult;->setKeepCallback(Z)V

    .line 101
    invoke-virtual {p3, v1}, Lorg/apache/cordova/CallbackContext;->sendPluginResult(Lorg/apache/cordova/PluginResult;)V
    :try_end_0
    .catch Lorg/json/JSONException; {:try_start_0 .. :try_end_0} :catch_0

    .line 108
    .end local v1    # "r":Lorg/apache/cordova/PluginResult;
    :goto_0
    return v4

    :cond_0
    move v4, v5

    .line 104
    goto :goto_0

    .line 105
    :catch_0
    move-exception v0

    .line 106
    .local v0, "e":Lorg/json/JSONException;
    invoke-virtual {v0}, Lorg/json/JSONException;->printStackTrace()V

    .line 107
    new-instance v5, Lorg/apache/cordova/PluginResult;

    sget-object v6, Lorg/apache/cordova/PluginResult$Status;->JSON_EXCEPTION:Lorg/apache/cordova/PluginResult$Status;

    invoke-direct {v5, v6}, Lorg/apache/cordova/PluginResult;-><init>(Lorg/apache/cordova/PluginResult$Status;)V

    invoke-virtual {p3, v5}, Lorg/apache/cordova/CallbackContext;->sendPluginResult(Lorg/apache/cordova/PluginResult;)V

    goto :goto_0
.end method

.method failPicture(Ljava/lang/String;)V
    .locals 3
    .param p1, "reason"    # Ljava/lang/String;

    .prologue
    .line 82
    iget-object v0, p0, Lcom/wezka/nativecamera/NativeCameraLauncher;->callbackContext:Lorg/apache/cordova/CallbackContext;

    new-instance v1, Lorg/apache/cordova/PluginResult;

    sget-object v2, Lorg/apache/cordova/PluginResult$Status;->ERROR:Lorg/apache/cordova/PluginResult$Status;

    invoke-direct {v1, v2, p1}, Lorg/apache/cordova/PluginResult;-><init>(Lorg/apache/cordova/PluginResult$Status;Ljava/lang/String;)V

    invoke-virtual {v0, v1}, Lorg/apache/cordova/CallbackContext;->sendPluginResult(Lorg/apache/cordova/PluginResult;)V

    .line 83
    return-void
.end method

.method public onActivityResult(IILandroid/content/Intent;)V
    .locals 11
    .param p1, "requestCode"    # I
    .param p2, "resultCode"    # I
    .param p3, "intent"    # Landroid/content/Intent;

    .prologue
    .line 140
    const/4 v7, -0x1

    if-ne p2, v7, :cond_1

    .line 141
    const/4 v5, 0x0

    .line 145
    .local v5, "rotate":I
    :try_start_0
    new-instance v2, Lorg/apache/cordova/ExifHelper;

    invoke-direct {v2}, Lorg/apache/cordova/ExifHelper;-><init>()V

    .line 146
    .local v2, "exif":Lorg/apache/cordova/ExifHelper;
    new-instance v7, Ljava/lang/StringBuilder;

    invoke-direct {v7}, Ljava/lang/StringBuilder;-><init>()V

    iget-object v8, p0, Lcom/wezka/nativecamera/NativeCameraLauncher;->cordova:Lorg/apache/cordova/CordovaInterface;

    invoke-interface {v8}, Lorg/apache/cordova/CordovaInterface;->getActivity()Landroid/app/Activity;

    move-result-object v8

    invoke-virtual {v8}, Landroid/app/Activity;->getApplicationContext()Landroid/content/Context;

    move-result-object v8

    invoke-direct {p0, v8}, Lcom/wezka/nativecamera/NativeCameraLauncher;->getTempDirectoryPath(Landroid/content/Context;)Ljava/lang/String;

    move-result-object v8

    invoke-virtual {v7, v8}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v7

    const-string v8, "/Pic-"

    invoke-virtual {v7, v8}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v7

    iget-object v8, p0, Lcom/wezka/nativecamera/NativeCameraLauncher;->date:Ljava/lang/String;

    invoke-virtual {v7, v8}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v7

    const-string v8, ".jpg"

    invoke-virtual {v7, v8}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v7

    invoke-virtual {v7}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v7

    invoke-virtual {v2, v7}, Lorg/apache/cordova/ExifHelper;->createInFile(Ljava/lang/String;)V

    .line 148
    invoke-virtual {v2}, Lorg/apache/cordova/ExifHelper;->readExifData()V

    .line 149
    invoke-virtual {v2}, Lorg/apache/cordova/ExifHelper;->getOrientation()I
    :try_end_0
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_1

    move-result v5

    .line 154
    :try_start_1
    iget-object v7, p0, Lcom/wezka/nativecamera/NativeCameraLauncher;->cordova:Lorg/apache/cordova/CordovaInterface;

    invoke-interface {v7}, Lorg/apache/cordova/CordovaInterface;->getActivity()Landroid/app/Activity;

    move-result-object v7

    invoke-virtual {v7}, Landroid/app/Activity;->getContentResolver()Landroid/content/ContentResolver;

    move-result-object v7

    iget-object v8, p0, Lcom/wezka/nativecamera/NativeCameraLauncher;->imageUri:Landroid/net/Uri;

    invoke-static {v7, v8}, Landroid/provider/MediaStore$Images$Media;->getBitmap(Landroid/content/ContentResolver;Landroid/net/Uri;)Landroid/graphics/Bitmap;
    :try_end_1
    .catch Ljava/io/FileNotFoundException; {:try_start_1 .. :try_end_1} :catch_0
    .catch Ljava/io/IOException; {:try_start_1 .. :try_end_1} :catch_1

    move-result-object v0

    .line 164
    .local v0, "bitmap":Landroid/graphics/Bitmap;
    :goto_0
    if-nez v0, :cond_0

    .line 165
    :try_start_2
    const-string v7, "Error decoding image."

    invoke-virtual {p0, v7}, Lcom/wezka/nativecamera/NativeCameraLauncher;->failPicture(Ljava/lang/String;)V

    .line 205
    .end local v0    # "bitmap":Landroid/graphics/Bitmap;
    .end local v2    # "exif":Lorg/apache/cordova/ExifHelper;
    .end local v5    # "rotate":I
    :goto_1
    return-void

    .line 156
    .restart local v2    # "exif":Lorg/apache/cordova/ExifHelper;
    .restart local v5    # "rotate":I
    :catch_0
    move-exception v1

    .line 157
    .local v1, "e":Ljava/io/FileNotFoundException;
    invoke-virtual {p3}, Landroid/content/Intent;->getData()Landroid/net/Uri;

    move-result-object v6

    .line 158
    .local v6, "uri":Landroid/net/Uri;
    iget-object v7, p0, Lcom/wezka/nativecamera/NativeCameraLauncher;->cordova:Lorg/apache/cordova/CordovaInterface;

    invoke-interface {v7}, Lorg/apache/cordova/CordovaInterface;->getActivity()Landroid/app/Activity;

    move-result-object v7

    invoke-virtual {v7}, Landroid/app/Activity;->getContentResolver()Landroid/content/ContentResolver;

    move-result-object v4

    .line 159
    .local v4, "resolver":Landroid/content/ContentResolver;
    invoke-virtual {v4, v6}, Landroid/content/ContentResolver;->openInputStream(Landroid/net/Uri;)Ljava/io/InputStream;

    move-result-object v7

    invoke-static {v7}, Landroid/graphics/BitmapFactory;->decodeStream(Ljava/io/InputStream;)Landroid/graphics/Bitmap;

    move-result-object v0

    .restart local v0    # "bitmap":Landroid/graphics/Bitmap;
    goto :goto_0

    .line 169
    .end local v1    # "e":Ljava/io/FileNotFoundException;
    .end local v4    # "resolver":Landroid/content/ContentResolver;
    .end local v6    # "uri":Landroid/net/Uri;
    :cond_0
    invoke-virtual {p0, v0}, Lcom/wezka/nativecamera/NativeCameraLauncher;->scaleBitmap(Landroid/graphics/Bitmap;)Landroid/graphics/Bitmap;

    move-result-object v0

    .line 173
    invoke-direct {p0, v5, v0, v2}, Lcom/wezka/nativecamera/NativeCameraLauncher;->getRotatedBitmap(ILandroid/graphics/Bitmap;Lorg/apache/cordova/ExifHelper;)Landroid/graphics/Bitmap;

    move-result-object v0

    .line 174
    const-string v7, "NativeCameraLauncher"

    new-instance v8, Ljava/lang/StringBuilder;

    invoke-direct {v8}, Ljava/lang/StringBuilder;-><init>()V

    const-string v9, "URI: "

    invoke-virtual {v8, v9}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v8

    iget-object v9, p0, Lcom/wezka/nativecamera/NativeCameraLauncher;->imageUri:Landroid/net/Uri;

    invoke-virtual {v9}, Landroid/net/Uri;->toString()Ljava/lang/String;

    move-result-object v9

    invoke-virtual {v8, v9}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v8

    invoke-virtual {v8}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v8

    invoke-static {v7, v8}, Landroid/util/Log;->i(Ljava/lang/String;Ljava/lang/String;)I

    .line 175
    iget-object v7, p0, Lcom/wezka/nativecamera/NativeCameraLauncher;->cordova:Lorg/apache/cordova/CordovaInterface;

    invoke-interface {v7}, Lorg/apache/cordova/CordovaInterface;->getActivity()Landroid/app/Activity;

    move-result-object v7

    invoke-virtual {v7}, Landroid/app/Activity;->getContentResolver()Landroid/content/ContentResolver;

    move-result-object v7

    iget-object v8, p0, Lcom/wezka/nativecamera/NativeCameraLauncher;->imageUri:Landroid/net/Uri;

    invoke-virtual {v7, v8}, Landroid/content/ContentResolver;->openOutputStream(Landroid/net/Uri;)Ljava/io/OutputStream;

    move-result-object v3

    .line 177
    .local v3, "os":Ljava/io/OutputStream;
    sget-object v7, Landroid/graphics/Bitmap$CompressFormat;->JPEG:Landroid/graphics/Bitmap$CompressFormat;

    iget v8, p0, Lcom/wezka/nativecamera/NativeCameraLauncher;->mQuality:I

    invoke-virtual {v0, v7, v8, v3}, Landroid/graphics/Bitmap;->compress(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z

    .line 178
    invoke-virtual {v3}, Ljava/io/OutputStream;->close()V

    .line 181
    iget-object v7, p0, Lcom/wezka/nativecamera/NativeCameraLauncher;->imageUri:Landroid/net/Uri;

    invoke-virtual {v7}, Landroid/net/Uri;->getPath()Ljava/lang/String;

    move-result-object v7

    invoke-virtual {v2, v7}, Lorg/apache/cordova/ExifHelper;->createOutFile(Ljava/lang/String;)V

    .line 182
    invoke-virtual {v2}, Lorg/apache/cordova/ExifHelper;->writeExifData()V

    .line 185
    iget-object v7, p0, Lcom/wezka/nativecamera/NativeCameraLauncher;->callbackContext:Lorg/apache/cordova/CallbackContext;

    new-instance v8, Lorg/apache/cordova/PluginResult;

    sget-object v9, Lorg/apache/cordova/PluginResult$Status;->OK:Lorg/apache/cordova/PluginResult$Status;

    iget-object v10, p0, Lcom/wezka/nativecamera/NativeCameraLauncher;->imageUri:Landroid/net/Uri;

    invoke-virtual {v10}, Landroid/net/Uri;->toString()Ljava/lang/String;

    move-result-object v10

    invoke-direct {v8, v9, v10}, Lorg/apache/cordova/PluginResult;-><init>(Lorg/apache/cordova/PluginResult$Status;Ljava/lang/String;)V

    invoke-virtual {v7, v8}, Lorg/apache/cordova/CallbackContext;->sendPluginResult(Lorg/apache/cordova/PluginResult;)V

    .line 187
    invoke-virtual {v0}, Landroid/graphics/Bitmap;->recycle()V

    .line 188
    const/4 v0, 0x0

    .line 189
    invoke-static {}, Ljava/lang/System;->gc()V
    :try_end_2
    .catch Ljava/io/IOException; {:try_start_2 .. :try_end_2} :catch_1

    goto :goto_1

    .line 190
    .end local v0    # "bitmap":Landroid/graphics/Bitmap;
    .end local v2    # "exif":Lorg/apache/cordova/ExifHelper;
    .end local v3    # "os":Ljava/io/OutputStream;
    :catch_1
    move-exception v1

    .line 191
    .local v1, "e":Ljava/io/IOException;
    invoke-virtual {v1}, Ljava/io/IOException;->printStackTrace()V

    .line 192
    const-string v7, "Error capturing image."

    invoke-virtual {p0, v7}, Lcom/wezka/nativecamera/NativeCameraLauncher;->failPicture(Ljava/lang/String;)V

    goto/16 :goto_1

    .line 197
    .end local v1    # "e":Ljava/io/IOException;
    .end local v5    # "rotate":I
    :cond_1
    if-nez p2, :cond_2

    .line 198
    const-string v7, "Camera cancelled."

    invoke-virtual {p0, v7}, Lcom/wezka/nativecamera/NativeCameraLauncher;->failPicture(Ljava/lang/String;)V

    goto/16 :goto_1

    .line 203
    :cond_2
    const-string v7, "Did not complete!"

    invoke-virtual {p0, v7}, Lcom/wezka/nativecamera/NativeCameraLauncher;->failPicture(Ljava/lang/String;)V

    goto/16 :goto_1
.end method

.method public scaleBitmap(Landroid/graphics/Bitmap;)Landroid/graphics/Bitmap;
    .locals 12
    .param p1, "bitmap"    # Landroid/graphics/Bitmap;

    .prologue
    .line 208
    iget v1, p0, Lcom/wezka/nativecamera/NativeCameraLauncher;->targetWidth:I

    .line 209
    .local v1, "newWidth":I
    iget v0, p0, Lcom/wezka/nativecamera/NativeCameraLauncher;->targetHeight:I

    .line 210
    .local v0, "newHeight":I
    invoke-virtual {p1}, Landroid/graphics/Bitmap;->getWidth()I

    move-result v5

    .line 211
    .local v5, "origWidth":I
    invoke-virtual {p1}, Landroid/graphics/Bitmap;->getHeight()I

    move-result v4

    .line 214
    .local v4, "origHeight":I
    if-gtz v1, :cond_0

    if-gtz v0, :cond_0

    .line 242
    .end local p1    # "bitmap":Landroid/graphics/Bitmap;
    :goto_0
    return-object p1

    .line 218
    .restart local p1    # "bitmap":Landroid/graphics/Bitmap;
    :cond_0
    if-lez v1, :cond_2

    if-gtz v0, :cond_2

    .line 219
    mul-int v8, v1, v4

    div-int v0, v8, v5

    .line 242
    :cond_1
    :goto_1
    const/4 v8, 0x1

    invoke-static {p1, v1, v0, v8}, Landroid/graphics/Bitmap;->createScaledBitmap(Landroid/graphics/Bitmap;IIZ)Landroid/graphics/Bitmap;

    move-result-object p1

    goto :goto_0

    .line 222
    :cond_2
    if-gtz v1, :cond_3

    if-lez v0, :cond_3

    .line 223
    mul-int v8, v0, v5

    div-int v1, v8, v4

    goto :goto_1

    .line 232
    :cond_3
    int-to-double v8, v1

    int-to-double v10, v0

    div-double v2, v8, v10

    .line 233
    .local v2, "newRatio":D
    int-to-double v8, v5

    int-to-double v10, v4

    div-double v6, v8, v10

    .line 235
    .local v6, "origRatio":D
    cmpl-double v8, v6, v2

    if-lez v8, :cond_4

    .line 236
    mul-int v8, v1, v4

    div-int v0, v8, v5

    goto :goto_1

    .line 237
    :cond_4
    cmpg-double v8, v6, v2

    if-gez v8, :cond_1

    .line 238
    mul-int v8, v0, v5

    div-int v1, v8, v4

    goto :goto_1
.end method

.method public takePicture()V
    .locals 3

    .prologue
    .line 114
    new-instance v0, Landroid/content/Intent;

    iget-object v1, p0, Lcom/wezka/nativecamera/NativeCameraLauncher;->cordova:Lorg/apache/cordova/CordovaInterface;

    invoke-interface {v1}, Lorg/apache/cordova/CordovaInterface;->getActivity()Landroid/app/Activity;

    move-result-object v1

    invoke-virtual {v1}, Landroid/app/Activity;->getApplicationContext()Landroid/content/Context;

    move-result-object v1

    const-class v2, Lcom/wezka/nativecamera/CameraActivity;

    invoke-direct {v0, v1, v2}, Landroid/content/Intent;-><init>(Landroid/content/Context;Ljava/lang/Class;)V

    .line 115
    .local v0, "intent":Landroid/content/Intent;
    invoke-direct {p0}, Lcom/wezka/nativecamera/NativeCameraLauncher;->createCaptureFile()Ljava/io/File;

    move-result-object v1

    iput-object v1, p0, Lcom/wezka/nativecamera/NativeCameraLauncher;->photo:Ljava/io/File;

    .line 116
    iget-object v1, p0, Lcom/wezka/nativecamera/NativeCameraLauncher;->photo:Ljava/io/File;

    invoke-static {v1}, Landroid/net/Uri;->fromFile(Ljava/io/File;)Landroid/net/Uri;

    move-result-object v1

    iput-object v1, p0, Lcom/wezka/nativecamera/NativeCameraLauncher;->imageUri:Landroid/net/Uri;

    .line 117
    const-string v1, "output"

    iget-object v2, p0, Lcom/wezka/nativecamera/NativeCameraLauncher;->imageUri:Landroid/net/Uri;

    invoke-virtual {v0, v1, v2}, Landroid/content/Intent;->putExtra(Ljava/lang/String;Landroid/os/Parcelable;)Landroid/content/Intent;

    .line 118
    iget-object v1, p0, Lcom/wezka/nativecamera/NativeCameraLauncher;->cordova:Lorg/apache/cordova/CordovaInterface;

    const/4 v2, 0x1

    invoke-interface {v1, p0, v0, v2}, Lorg/apache/cordova/CordovaInterface;->startActivityForResult(Lorg/apache/cordova/CordovaPlugin;Landroid/content/Intent;I)V

    .line 119
    return-void
.end method
