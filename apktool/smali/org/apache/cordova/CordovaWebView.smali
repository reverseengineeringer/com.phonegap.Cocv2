.class public Lorg/apache/cordova/CordovaWebView;
.super Landroid/webkit/WebView;
.source "CordovaWebView.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lorg/apache/cordova/CordovaWebView$Level16Apis;,
        Lorg/apache/cordova/CordovaWebView$ActivityResult;
    }
.end annotation


# static fields
.field public static final CORDOVA_VERSION:Ljava/lang/String; = "3.6.4"

.field static final COVER_SCREEN_GRAVITY_CENTER:Landroid/widget/FrameLayout$LayoutParams;

.field public static final TAG:Ljava/lang/String; = "CordovaWebView"


# instance fields
.field private boundKeyCodes:Ljava/util/HashSet;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/HashSet",
            "<",
            "Ljava/lang/Integer;",
            ">;"
        }
    .end annotation
.end field

.field bridge:Lorg/apache/cordova/CordovaBridge;

.field private chromeClient:Lorg/apache/cordova/CordovaChromeClient;

.field private cordova:Lorg/apache/cordova/CordovaInterface;

.field private externalWhitelist:Lorg/apache/cordova/Whitelist;

.field private internalWhitelist:Lorg/apache/cordova/Whitelist;

.field private lastMenuEventTime:J

.field loadUrlTimeout:I

.field loadedUrl:Ljava/lang/String;

.field private mCustomView:Landroid/view/View;

.field private mCustomViewCallback:Landroid/webkit/WebChromeClient$CustomViewCallback;

.field private paused:Z

.field public pluginManager:Lorg/apache/cordova/PluginManager;

.field private preferences:Lorg/apache/cordova/CordovaPreferences;

.field private receiver:Landroid/content/BroadcastReceiver;

.field private resourceApi:Lorg/apache/cordova/CordovaResourceApi;

.field viewClient:Lorg/apache/cordova/CordovaWebViewClient;


# direct methods
.method static constructor <clinit>()V
    .locals 3

    .prologue
    const/4 v2, -0x1

    .line 113
    new-instance v0, Landroid/widget/FrameLayout$LayoutParams;

    const/16 v1, 0x11

    invoke-direct {v0, v2, v2, v1}, Landroid/widget/FrameLayout$LayoutParams;-><init>(III)V

    sput-object v0, Lorg/apache/cordova/CordovaWebView;->COVER_SCREEN_GRAVITY_CENTER:Landroid/widget/FrameLayout$LayoutParams;

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;)V
    .locals 1
    .param p1, "context"    # Landroid/content/Context;

    .prologue
    .line 120
    const/4 v0, 0x0

    invoke-direct {p0, p1, v0}, Lorg/apache/cordova/CordovaWebView;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V

    .line 121
    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;)V
    .locals 2
    .param p1, "context"    # Landroid/content/Context;
    .param p2, "attrs"    # Landroid/util/AttributeSet;

    .prologue
    .line 124
    invoke-direct {p0, p1, p2}, Landroid/webkit/WebView;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V

    .line 66
    new-instance v0, Ljava/util/HashSet;

    invoke-direct {v0}, Ljava/util/HashSet;-><init>()V

    iput-object v0, p0, Lorg/apache/cordova/CordovaWebView;->boundKeyCodes:Ljava/util/HashSet;

    .line 80
    const/4 v0, 0x0

    iput v0, p0, Lorg/apache/cordova/CordovaWebView;->loadUrlTimeout:I

    .line 82
    const-wide/16 v0, 0x0

    iput-wide v0, p0, Lorg/apache/cordova/CordovaWebView;->lastMenuEventTime:J

    .line 125
    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V
    .locals 2
    .param p1, "context"    # Landroid/content/Context;
    .param p2, "attrs"    # Landroid/util/AttributeSet;
    .param p3, "defStyle"    # I
    .annotation runtime Ljava/lang/Deprecated;
    .end annotation

    .prologue
    .line 129
    invoke-direct {p0, p1, p2, p3}, Landroid/webkit/WebView;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V

    .line 66
    new-instance v0, Ljava/util/HashSet;

    invoke-direct {v0}, Ljava/util/HashSet;-><init>()V

    iput-object v0, p0, Lorg/apache/cordova/CordovaWebView;->boundKeyCodes:Ljava/util/HashSet;

    .line 80
    const/4 v0, 0x0

    iput v0, p0, Lorg/apache/cordova/CordovaWebView;->loadUrlTimeout:I

    .line 82
    const-wide/16 v0, 0x0

    iput-wide v0, p0, Lorg/apache/cordova/CordovaWebView;->lastMenuEventTime:J

    .line 130
    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;IZ)V
    .locals 2
    .param p1, "context"    # Landroid/content/Context;
    .param p2, "attrs"    # Landroid/util/AttributeSet;
    .param p3, "defStyle"    # I
    .param p4, "privateBrowsing"    # Z
    .annotation build Landroid/annotation/TargetApi;
        value = 0xb
    .end annotation

    .annotation runtime Ljava/lang/Deprecated;
    .end annotation

    .prologue
    .line 135
    invoke-direct {p0, p1, p2, p3, p4}, Landroid/webkit/WebView;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;IZ)V

    .line 66
    new-instance v0, Ljava/util/HashSet;

    invoke-direct {v0}, Ljava/util/HashSet;-><init>()V

    iput-object v0, p0, Lorg/apache/cordova/CordovaWebView;->boundKeyCodes:Ljava/util/HashSet;

    .line 80
    const/4 v0, 0x0

    iput v0, p0, Lorg/apache/cordova/CordovaWebView;->loadUrlTimeout:I

    .line 82
    const-wide/16 v0, 0x0

    iput-wide v0, p0, Lorg/apache/cordova/CordovaWebView;->lastMenuEventTime:J

    .line 136
    return-void
.end method

.method static synthetic access$000(Lorg/apache/cordova/CordovaWebView;)Lorg/apache/cordova/CordovaInterface;
    .locals 1
    .param p0, "x0"    # Lorg/apache/cordova/CordovaWebView;

    .prologue
    .line 61
    iget-object v0, p0, Lorg/apache/cordova/CordovaWebView;->cordova:Lorg/apache/cordova/CordovaInterface;

    return-object v0
.end method

.method private enableRemoteDebugging()V
    .locals 3
    .annotation build Landroid/annotation/TargetApi;
        value = 0x13
    .end annotation

    .prologue
    .line 269
    const/4 v1, 0x1

    :try_start_0
    invoke-static {v1}, Landroid/webkit/WebView;->setWebContentsDebuggingEnabled(Z)V
    :try_end_0
    .catch Ljava/lang/IllegalArgumentException; {:try_start_0 .. :try_end_0} :catch_0

    .line 274
    :goto_0
    return-void

    .line 270
    :catch_0
    move-exception v0

    .line 271
    .local v0, "e":Ljava/lang/IllegalArgumentException;
    const-string v1, "CordovaWebView"

    const-string v2, "You have one job! To turn on Remote Web Debugging! YOU HAVE FAILED! "

    invoke-static {v1, v2}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 272
    invoke-virtual {v0}, Ljava/lang/IllegalArgumentException;->printStackTrace()V

    goto :goto_0
.end method

.method private exposeJsInterface()V
    .locals 2

    .prologue
    .line 298
    sget v0, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v1, 0x11

    if-ge v0, v1, :cond_0

    .line 299
    const-string v0, "CordovaWebView"

    const-string v1, "Disabled addJavascriptInterface() bridge since Android version is old."

    invoke-static {v0, v1}, Landroid/util/Log;->i(Ljava/lang/String;Ljava/lang/String;)I

    .line 306
    :goto_0
    return-void

    .line 305
    :cond_0
    new-instance v0, Lorg/apache/cordova/ExposedJsApi;

    iget-object v1, p0, Lorg/apache/cordova/CordovaWebView;->bridge:Lorg/apache/cordova/CordovaBridge;

    invoke-direct {v0, v1}, Lorg/apache/cordova/ExposedJsApi;-><init>(Lorg/apache/cordova/CordovaBridge;)V

    const-string v1, "_cordovaNative"

    invoke-virtual {p0, v0, v1}, Lorg/apache/cordova/CordovaWebView;->addJavascriptInterface(Ljava/lang/Object;Ljava/lang/String;)V

    goto :goto_0
.end method

.method private initIfNecessary()V
    .locals 8

    .prologue
    .line 165
    iget-object v0, p0, Lorg/apache/cordova/CordovaWebView;->pluginManager:Lorg/apache/cordova/PluginManager;

    if-nez v0, :cond_1

    .line 166
    const-string v0, "CordovaWebView"

    const-string v2, "CordovaWebView.init() was not called. This will soon be required."

    invoke-static {v0, v2}, Landroid/util/Log;->w(Ljava/lang/String;Ljava/lang/String;)I

    .line 168
    invoke-virtual {p0}, Lorg/apache/cordova/CordovaWebView;->getContext()Landroid/content/Context;

    move-result-object v1

    check-cast v1, Lorg/apache/cordova/CordovaInterface;

    .line 169
    .local v1, "cdv":Lorg/apache/cordova/CordovaInterface;
    invoke-static {}, Lorg/apache/cordova/Config;->isInitialized()Z

    move-result v0

    if-nez v0, :cond_0

    .line 170
    invoke-interface {v1}, Lorg/apache/cordova/CordovaInterface;->getActivity()Landroid/app/Activity;

    move-result-object v0

    invoke-static {v0}, Lorg/apache/cordova/Config;->init(Landroid/app/Activity;)V

    .line 172
    :cond_0
    invoke-virtual {p0, v1}, Lorg/apache/cordova/CordovaWebView;->makeWebViewClient(Lorg/apache/cordova/CordovaInterface;)Lorg/apache/cordova/CordovaWebViewClient;

    move-result-object v2

    invoke-virtual {p0, v1}, Lorg/apache/cordova/CordovaWebView;->makeWebChromeClient(Lorg/apache/cordova/CordovaInterface;)Lorg/apache/cordova/CordovaChromeClient;

    move-result-object v3

    invoke-static {}, Lorg/apache/cordova/Config;->getPluginEntries()Ljava/util/List;

    move-result-object v4

    invoke-static {}, Lorg/apache/cordova/Config;->getWhitelist()Lorg/apache/cordova/Whitelist;

    move-result-object v5

    invoke-static {}, Lorg/apache/cordova/Config;->getExternalWhitelist()Lorg/apache/cordova/Whitelist;

    move-result-object v6

    invoke-static {}, Lorg/apache/cordova/Config;->getPreferences()Lorg/apache/cordova/CordovaPreferences;

    move-result-object v7

    move-object v0, p0

    invoke-virtual/range {v0 .. v7}, Lorg/apache/cordova/CordovaWebView;->init(Lorg/apache/cordova/CordovaInterface;Lorg/apache/cordova/CordovaWebViewClient;Lorg/apache/cordova/CordovaChromeClient;Ljava/util/List;Lorg/apache/cordova/Whitelist;Lorg/apache/cordova/Whitelist;Lorg/apache/cordova/CordovaPreferences;)V

    .line 174
    .end local v1    # "cdv":Lorg/apache/cordova/CordovaInterface;
    :cond_1
    return-void
.end method

.method private initWebViewSettings()V
    .locals 14
    .annotation build Landroid/annotation/SuppressLint;
        value = {
            "SetJavaScriptEnabled"
        }
    .end annotation

    .prologue
    const/4 v13, 0x0

    const/4 v12, 0x1

    .line 179
    invoke-virtual {p0, v13}, Lorg/apache/cordova/CordovaWebView;->setInitialScale(I)V

    .line 180
    invoke-virtual {p0, v13}, Lorg/apache/cordova/CordovaWebView;->setVerticalScrollBarEnabled(Z)V

    .line 182
    invoke-virtual {p0}, Lorg/apache/cordova/CordovaWebView;->shouldRequestFocusOnInit()Z

    move-result v7

    if-eqz v7, :cond_0

    .line 183
    invoke-virtual {p0}, Lorg/apache/cordova/CordovaWebView;->requestFocusFromTouch()Z

    .line 186
    :cond_0
    invoke-virtual {p0}, Lorg/apache/cordova/CordovaWebView;->getSettings()Landroid/webkit/WebSettings;

    move-result-object v6

    .line 187
    .local v6, "settings":Landroid/webkit/WebSettings;
    invoke-virtual {v6, v12}, Landroid/webkit/WebSettings;->setJavaScriptEnabled(Z)V

    .line 188
    invoke-virtual {v6, v12}, Landroid/webkit/WebSettings;->setJavaScriptCanOpenWindowsAutomatically(Z)V

    .line 189
    sget-object v7, Landroid/webkit/WebSettings$LayoutAlgorithm;->NORMAL:Landroid/webkit/WebSettings$LayoutAlgorithm;

    invoke-virtual {v6, v7}, Landroid/webkit/WebSettings;->setLayoutAlgorithm(Landroid/webkit/WebSettings$LayoutAlgorithm;)V

    .line 193
    :try_start_0
    const-class v7, Landroid/webkit/WebSettings;

    const-string v8, "setNavDump"

    const/4 v9, 0x1

    new-array v9, v9, [Ljava/lang/Class;

    const/4 v10, 0x0

    sget-object v11, Ljava/lang/Boolean;->TYPE:Ljava/lang/Class;

    aput-object v11, v9, v10

    invoke-virtual {v7, v8, v9}, Ljava/lang/Class;->getMethod(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;

    move-result-object v3

    .line 195
    .local v3, "gingerbread_getMethod":Ljava/lang/reflect/Method;
    sget-object v5, Landroid/os/Build;->MANUFACTURER:Ljava/lang/String;

    .line 196
    .local v5, "manufacturer":Ljava/lang/String;
    const-string v7, "CordovaWebView"

    new-instance v8, Ljava/lang/StringBuilder;

    invoke-direct {v8}, Ljava/lang/StringBuilder;-><init>()V

    const-string v9, "CordovaWebView is running on device made by: "

    invoke-virtual {v8, v9}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v8

    invoke-virtual {v8, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v8

    invoke-virtual {v8}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v8

    invoke-static {v7, v8}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 197
    sget v7, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v8, 0xb

    if-ge v7, v8, :cond_1

    sget-object v7, Landroid/os/Build;->MANUFACTURER:Ljava/lang/String;

    const-string v8, "HTC"

    invoke-virtual {v7, v8}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v7

    if-eqz v7, :cond_1

    .line 200
    const/4 v7, 0x1

    new-array v7, v7, [Ljava/lang/Object;

    const/4 v8, 0x0

    const/4 v9, 0x1

    invoke-static {v9}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v9

    aput-object v9, v7, v8

    invoke-virtual {v3, v6, v7}, Ljava/lang/reflect/Method;->invoke(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    :try_end_0
    .catch Ljava/lang/NoSuchMethodException; {:try_start_0 .. :try_end_0} :catch_0
    .catch Ljava/lang/IllegalArgumentException; {:try_start_0 .. :try_end_0} :catch_1
    .catch Ljava/lang/IllegalAccessException; {:try_start_0 .. :try_end_0} :catch_2
    .catch Ljava/lang/reflect/InvocationTargetException; {:try_start_0 .. :try_end_0} :catch_3

    .line 213
    .end local v3    # "gingerbread_getMethod":Ljava/lang/reflect/Method;
    .end local v5    # "manufacturer":Ljava/lang/String;
    :cond_1
    :goto_0
    invoke-virtual {v6, v13}, Landroid/webkit/WebSettings;->setSaveFormData(Z)V

    .line 214
    invoke-virtual {v6, v13}, Landroid/webkit/WebSettings;->setSavePassword(Z)V

    .line 218
    sget v7, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v8, 0xf

    if-le v7, v8, :cond_2

    .line 219
    invoke-static {v6}, Lorg/apache/cordova/CordovaWebView$Level16Apis;->enableUniversalAccess(Landroid/webkit/WebSettings;)V

    .line 222
    :cond_2
    invoke-virtual {p0}, Lorg/apache/cordova/CordovaWebView;->getContext()Landroid/content/Context;

    move-result-object v7

    invoke-virtual {v7}, Landroid/content/Context;->getApplicationContext()Landroid/content/Context;

    move-result-object v7

    const-string v8, "database"

    invoke-virtual {v7, v8, v13}, Landroid/content/Context;->getDir(Ljava/lang/String;I)Ljava/io/File;

    move-result-object v7

    invoke-virtual {v7}, Ljava/io/File;->getPath()Ljava/lang/String;

    move-result-object v1

    .line 223
    .local v1, "databasePath":Ljava/lang/String;
    invoke-virtual {v6, v12}, Landroid/webkit/WebSettings;->setDatabaseEnabled(Z)V

    .line 224
    invoke-virtual {v6, v1}, Landroid/webkit/WebSettings;->setDatabasePath(Ljava/lang/String;)V

    .line 228
    invoke-virtual {p0}, Lorg/apache/cordova/CordovaWebView;->getContext()Landroid/content/Context;

    move-result-object v7

    invoke-virtual {v7}, Landroid/content/Context;->getApplicationContext()Landroid/content/Context;

    move-result-object v7

    invoke-virtual {v7}, Landroid/content/Context;->getApplicationInfo()Landroid/content/pm/ApplicationInfo;

    move-result-object v0

    .line 229
    .local v0, "appInfo":Landroid/content/pm/ApplicationInfo;
    iget v7, v0, Landroid/content/pm/ApplicationInfo;->flags:I

    and-int/lit8 v7, v7, 0x2

    if-eqz v7, :cond_3

    sget v7, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v8, 0x13

    if-lt v7, v8, :cond_3

    .line 231
    invoke-direct {p0}, Lorg/apache/cordova/CordovaWebView;->enableRemoteDebugging()V

    .line 234
    :cond_3
    invoke-virtual {v6, v1}, Landroid/webkit/WebSettings;->setGeolocationDatabasePath(Ljava/lang/String;)V

    .line 237
    invoke-virtual {v6, v12}, Landroid/webkit/WebSettings;->setDomStorageEnabled(Z)V

    .line 240
    invoke-virtual {v6, v12}, Landroid/webkit/WebSettings;->setGeolocationEnabled(Z)V

    .line 244
    const-wide/32 v8, 0x500000

    invoke-virtual {v6, v8, v9}, Landroid/webkit/WebSettings;->setAppCacheMaxSize(J)V

    .line 245
    invoke-virtual {v6, v1}, Landroid/webkit/WebSettings;->setAppCachePath(Ljava/lang/String;)V

    .line 246
    invoke-virtual {v6, v12}, Landroid/webkit/WebSettings;->setAppCacheEnabled(Z)V

    .line 250
    invoke-virtual {v6}, Landroid/webkit/WebSettings;->getUserAgentString()Ljava/lang/String;

    .line 252
    new-instance v4, Landroid/content/IntentFilter;

    invoke-direct {v4}, Landroid/content/IntentFilter;-><init>()V

    .line 253
    .local v4, "intentFilter":Landroid/content/IntentFilter;
    const-string v7, "android.intent.action.CONFIGURATION_CHANGED"

    invoke-virtual {v4, v7}, Landroid/content/IntentFilter;->addAction(Ljava/lang/String;)V

    .line 254
    iget-object v7, p0, Lorg/apache/cordova/CordovaWebView;->receiver:Landroid/content/BroadcastReceiver;

    if-nez v7, :cond_4

    .line 255
    new-instance v7, Lorg/apache/cordova/CordovaWebView$1;

    invoke-direct {v7, p0}, Lorg/apache/cordova/CordovaWebView$1;-><init>(Lorg/apache/cordova/CordovaWebView;)V

    iput-object v7, p0, Lorg/apache/cordova/CordovaWebView;->receiver:Landroid/content/BroadcastReceiver;

    .line 261
    invoke-virtual {p0}, Lorg/apache/cordova/CordovaWebView;->getContext()Landroid/content/Context;

    move-result-object v7

    iget-object v8, p0, Lorg/apache/cordova/CordovaWebView;->receiver:Landroid/content/BroadcastReceiver;

    invoke-virtual {v7, v8, v4}, Landroid/content/Context;->registerReceiver(Landroid/content/BroadcastReceiver;Landroid/content/IntentFilter;)Landroid/content/Intent;

    .line 264
    :cond_4
    return-void

    .line 202
    .end local v0    # "appInfo":Landroid/content/pm/ApplicationInfo;
    .end local v1    # "databasePath":Ljava/lang/String;
    .end local v4    # "intentFilter":Landroid/content/IntentFilter;
    :catch_0
    move-exception v2

    .line 203
    .local v2, "e":Ljava/lang/NoSuchMethodException;
    const-string v7, "CordovaWebView"

    const-string v8, "We are on a modern version of Android, we will deprecate HTC 2.3 devices in 2.8"

    invoke-static {v7, v8}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    goto/16 :goto_0

    .line 204
    .end local v2    # "e":Ljava/lang/NoSuchMethodException;
    :catch_1
    move-exception v2

    .line 205
    .local v2, "e":Ljava/lang/IllegalArgumentException;
    const-string v7, "CordovaWebView"

    const-string v8, "Doing the NavDump failed with bad arguments"

    invoke-static {v7, v8}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    goto/16 :goto_0

    .line 206
    .end local v2    # "e":Ljava/lang/IllegalArgumentException;
    :catch_2
    move-exception v2

    .line 207
    .local v2, "e":Ljava/lang/IllegalAccessException;
    const-string v7, "CordovaWebView"

    const-string v8, "This should never happen: IllegalAccessException means this isn\'t Android anymore"

    invoke-static {v7, v8}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    goto/16 :goto_0

    .line 208
    .end local v2    # "e":Ljava/lang/IllegalAccessException;
    :catch_3
    move-exception v2

    .line 209
    .local v2, "e":Ljava/lang/reflect/InvocationTargetException;
    const-string v7, "CordovaWebView"

    const-string v8, "This should never happen: InvocationTargetException means this isn\'t Android anymore."

    invoke-static {v7, v8}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    goto/16 :goto_0
.end method


# virtual methods
.method public backHistory()Z
    .locals 1

    .prologue
    .line 541
    invoke-super {p0}, Landroid/webkit/WebView;->canGoBack()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 542
    invoke-super {p0}, Landroid/webkit/WebView;->goBack()V

    .line 543
    const/4 v0, 0x1

    .line 545
    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public bindButton(IZZ)V
    .locals 0
    .param p1, "keyCode"    # I
    .param p2, "keyDown"    # Z
    .param p3, "override"    # Z
    .annotation runtime Ljava/lang/Deprecated;
    .end annotation

    .prologue
    .line 735
    invoke-virtual {p0, p1, p3}, Lorg/apache/cordova/CordovaWebView;->setButtonPlumbedToJs(IZ)V

    .line 736
    return-void
.end method

.method public bindButton(Ljava/lang/String;Z)V
    .locals 1
    .param p1, "button"    # Ljava/lang/String;
    .param p2, "override"    # Z
    .annotation runtime Ljava/lang/Deprecated;
    .end annotation

    .prologue
    .line 725
    const-string v0, "volumeup"

    invoke-virtual {p1, v0}, Ljava/lang/String;->compareTo(Ljava/lang/String;)I

    move-result v0

    if-nez v0, :cond_1

    .line 726
    const/16 v0, 0x18

    invoke-virtual {p0, v0, p2}, Lorg/apache/cordova/CordovaWebView;->setButtonPlumbedToJs(IZ)V

    .line 731
    :cond_0
    :goto_0
    return-void

    .line 728
    :cond_1
    const-string v0, "volumedown"

    invoke-virtual {p1, v0}, Ljava/lang/String;->compareTo(Ljava/lang/String;)I

    move-result v0

    if-nez v0, :cond_0

    .line 729
    const/16 v0, 0x19

    invoke-virtual {p0, v0, p2}, Lorg/apache/cordova/CordovaWebView;->setButtonPlumbedToJs(IZ)V

    goto :goto_0
.end method

.method public bindButton(Z)V
    .locals 1
    .param p1, "override"    # Z
    .annotation runtime Ljava/lang/Deprecated;
    .end annotation

    .prologue
    .line 720
    const/4 v0, 0x4

    invoke-virtual {p0, v0, p1}, Lorg/apache/cordova/CordovaWebView;->setButtonPlumbedToJs(IZ)V

    .line 721
    return-void
.end method

.method public getExternalWhitelist()Lorg/apache/cordova/Whitelist;
    .locals 1

    .prologue
    .line 330
    iget-object v0, p0, Lorg/apache/cordova/CordovaWebView;->externalWhitelist:Lorg/apache/cordova/Whitelist;

    return-object v0
.end method

.method public getPreferences()Lorg/apache/cordova/CordovaPreferences;
    .locals 1

    .prologue
    .line 932
    iget-object v0, p0, Lorg/apache/cordova/CordovaWebView;->preferences:Lorg/apache/cordova/CordovaPreferences;

    return-object v0
.end method

.method public getProperty(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    .locals 3
    .param p1, "name"    # Ljava/lang/String;
    .param p2, "defaultValue"    # Ljava/lang/String;

    .prologue
    .line 604
    iget-object v2, p0, Lorg/apache/cordova/CordovaWebView;->cordova:Lorg/apache/cordova/CordovaInterface;

    invoke-interface {v2}, Lorg/apache/cordova/CordovaInterface;->getActivity()Landroid/app/Activity;

    move-result-object v2

    invoke-virtual {v2}, Landroid/app/Activity;->getIntent()Landroid/content/Intent;

    move-result-object v2

    invoke-virtual {v2}, Landroid/content/Intent;->getExtras()Landroid/os/Bundle;

    move-result-object v0

    .line 605
    .local v0, "bundle":Landroid/os/Bundle;
    if-nez v0, :cond_1

    .line 613
    .end local p2    # "defaultValue":Ljava/lang/String;
    :cond_0
    :goto_0
    return-object p2

    .line 608
    .restart local p2    # "defaultValue":Ljava/lang/String;
    :cond_1
    invoke-static {}, Ljava/util/Locale;->getDefault()Ljava/util/Locale;

    move-result-object v2

    invoke-virtual {p1, v2}, Ljava/lang/String;->toLowerCase(Ljava/util/Locale;)Ljava/lang/String;

    move-result-object p1

    .line 609
    invoke-virtual {v0, p1}, Landroid/os/Bundle;->get(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v1

    .line 610
    .local v1, "p":Ljava/lang/Object;
    if-eqz v1, :cond_0

    .line 613
    invoke-virtual {v1}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object p2

    goto :goto_0
.end method

.method public getResourceApi()Lorg/apache/cordova/CordovaResourceApi;
    .locals 1

    .prologue
    .line 928
    iget-object v0, p0, Lorg/apache/cordova/CordovaWebView;->resourceApi:Lorg/apache/cordova/CordovaResourceApi;

    return-object v0
.end method

.method public getWebChromeClient()Lorg/apache/cordova/CordovaChromeClient;
    .locals 1

    .prologue
    .line 321
    iget-object v0, p0, Lorg/apache/cordova/CordovaWebView;->chromeClient:Lorg/apache/cordova/CordovaChromeClient;

    return-object v0
.end method

.method public getWhitelist()Lorg/apache/cordova/Whitelist;
    .locals 1

    .prologue
    .line 326
    iget-object v0, p0, Lorg/apache/cordova/CordovaWebView;->internalWhitelist:Lorg/apache/cordova/Whitelist;

    return-object v0
.end method

.method public hadKeyEvent()Z
    .locals 1
    .annotation runtime Ljava/lang/Deprecated;
    .end annotation

    .prologue
    .line 822
    const/4 v0, 0x0

    return v0
.end method

.method public handleDestroy()V
    .locals 4

    .prologue
    .line 787
    const-string v1, "javascript:try{cordova.require(\'cordova/channel\').onDestroy.fire();}catch(e){console.log(\'exception firing destroy event from native\');};"

    invoke-virtual {p0, v1}, Lorg/apache/cordova/CordovaWebView;->loadUrl(Ljava/lang/String;)V

    .line 790
    const-string v1, "about:blank"

    invoke-virtual {p0, v1}, Lorg/apache/cordova/CordovaWebView;->loadUrl(Ljava/lang/String;)V

    .line 793
    iget-object v1, p0, Lorg/apache/cordova/CordovaWebView;->pluginManager:Lorg/apache/cordova/PluginManager;

    if-eqz v1, :cond_0

    .line 794
    iget-object v1, p0, Lorg/apache/cordova/CordovaWebView;->pluginManager:Lorg/apache/cordova/PluginManager;

    invoke-virtual {v1}, Lorg/apache/cordova/PluginManager;->onDestroy()V

    .line 798
    :cond_0
    iget-object v1, p0, Lorg/apache/cordova/CordovaWebView;->receiver:Landroid/content/BroadcastReceiver;

    if-eqz v1, :cond_1

    .line 800
    :try_start_0
    invoke-virtual {p0}, Lorg/apache/cordova/CordovaWebView;->getContext()Landroid/content/Context;

    move-result-object v1

    iget-object v2, p0, Lorg/apache/cordova/CordovaWebView;->receiver:Landroid/content/BroadcastReceiver;

    invoke-virtual {v1, v2}, Landroid/content/Context;->unregisterReceiver(Landroid/content/BroadcastReceiver;)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    .line 805
    :cond_1
    :goto_0
    return-void

    .line 801
    :catch_0
    move-exception v0

    .line 802
    .local v0, "e":Ljava/lang/Exception;
    const-string v1, "CordovaWebView"

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "Error unregistering configuration receiver: "

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v0}, Ljava/lang/Exception;->getMessage()Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-static {v1, v2, v0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    goto :goto_0
.end method

.method public handlePause(Z)V
    .locals 2
    .param p1, "keepRunning"    # Z

    .prologue
    .line 751
    const-string v0, "CordovaWebView"

    const-string v1, "Handle the pause"

    invoke-static {v0, v1}, Lorg/apache/cordova/LOG;->d(Ljava/lang/String;Ljava/lang/String;)V

    .line 753
    const-string v0, "javascript:try{cordova.fireDocumentEvent(\'pause\');}catch(e){console.log(\'exception firing pause event from native\');};"

    invoke-virtual {p0, v0}, Lorg/apache/cordova/CordovaWebView;->loadUrl(Ljava/lang/String;)V

    .line 756
    iget-object v0, p0, Lorg/apache/cordova/CordovaWebView;->pluginManager:Lorg/apache/cordova/PluginManager;

    if-eqz v0, :cond_0

    .line 757
    iget-object v0, p0, Lorg/apache/cordova/CordovaWebView;->pluginManager:Lorg/apache/cordova/PluginManager;

    invoke-virtual {v0, p1}, Lorg/apache/cordova/PluginManager;->onPause(Z)V

    .line 761
    :cond_0
    if-nez p1, :cond_1

    .line 763
    invoke-virtual {p0}, Lorg/apache/cordova/CordovaWebView;->pauseTimers()V

    .line 765
    :cond_1
    const/4 v0, 0x1

    iput-boolean v0, p0, Lorg/apache/cordova/CordovaWebView;->paused:Z

    .line 767
    return-void
.end method

.method public handleResume(ZZ)V
    .locals 1
    .param p1, "keepRunning"    # Z
    .param p2, "activityResultKeepRunning"    # Z

    .prologue
    .line 772
    const-string v0, "javascript:try{cordova.fireDocumentEvent(\'resume\');}catch(e){console.log(\'exception firing resume event from native\');};"

    invoke-virtual {p0, v0}, Lorg/apache/cordova/CordovaWebView;->loadUrl(Ljava/lang/String;)V

    .line 775
    iget-object v0, p0, Lorg/apache/cordova/CordovaWebView;->pluginManager:Lorg/apache/cordova/PluginManager;

    if-eqz v0, :cond_0

    .line 776
    iget-object v0, p0, Lorg/apache/cordova/CordovaWebView;->pluginManager:Lorg/apache/cordova/PluginManager;

    invoke-virtual {v0, p1}, Lorg/apache/cordova/PluginManager;->onResume(Z)V

    .line 780
    :cond_0
    invoke-virtual {p0}, Lorg/apache/cordova/CordovaWebView;->resumeTimers()V

    .line 781
    const/4 v0, 0x0

    iput-boolean v0, p0, Lorg/apache/cordova/CordovaWebView;->paused:Z

    .line 782
    return-void
.end method

.method public hideCustomView()V
    .locals 3

    .prologue
    .line 888
    const-string v1, "CordovaWebView"

    const-string v2, "Hiding Custom View"

    invoke-static {v1, v2}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 889
    iget-object v1, p0, Lorg/apache/cordova/CordovaWebView;->mCustomView:Landroid/view/View;

    if-nez v1, :cond_0

    .line 902
    :goto_0
    return-void

    .line 892
    :cond_0
    iget-object v1, p0, Lorg/apache/cordova/CordovaWebView;->mCustomView:Landroid/view/View;

    const/16 v2, 0x8

    invoke-virtual {v1, v2}, Landroid/view/View;->setVisibility(I)V

    .line 895
    invoke-virtual {p0}, Lorg/apache/cordova/CordovaWebView;->getParent()Landroid/view/ViewParent;

    move-result-object v0

    check-cast v0, Landroid/view/ViewGroup;

    .line 896
    .local v0, "parent":Landroid/view/ViewGroup;
    iget-object v1, p0, Lorg/apache/cordova/CordovaWebView;->mCustomView:Landroid/view/View;

    invoke-virtual {v0, v1}, Landroid/view/ViewGroup;->removeView(Landroid/view/View;)V

    .line 897
    const/4 v1, 0x0

    iput-object v1, p0, Lorg/apache/cordova/CordovaWebView;->mCustomView:Landroid/view/View;

    .line 898
    iget-object v1, p0, Lorg/apache/cordova/CordovaWebView;->mCustomViewCallback:Landroid/webkit/WebChromeClient$CustomViewCallback;

    invoke-interface {v1}, Landroid/webkit/WebChromeClient$CustomViewCallback;->onCustomViewHidden()V

    .line 901
    const/4 v1, 0x0

    invoke-virtual {p0, v1}, Lorg/apache/cordova/CordovaWebView;->setVisibility(I)V

    goto :goto_0
.end method

.method public init(Lorg/apache/cordova/CordovaInterface;Lorg/apache/cordova/CordovaWebViewClient;Lorg/apache/cordova/CordovaChromeClient;Ljava/util/List;Lorg/apache/cordova/Whitelist;Lorg/apache/cordova/Whitelist;Lorg/apache/cordova/CordovaPreferences;)V
    .locals 3
    .param p1, "cordova"    # Lorg/apache/cordova/CordovaInterface;
    .param p2, "webViewClient"    # Lorg/apache/cordova/CordovaWebViewClient;
    .param p3, "webChromeClient"    # Lorg/apache/cordova/CordovaChromeClient;
    .param p5, "internalWhitelist"    # Lorg/apache/cordova/Whitelist;
    .param p6, "externalWhitelist"    # Lorg/apache/cordova/Whitelist;
    .param p7, "preferences"    # Lorg/apache/cordova/CordovaPreferences;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lorg/apache/cordova/CordovaInterface;",
            "Lorg/apache/cordova/CordovaWebViewClient;",
            "Lorg/apache/cordova/CordovaChromeClient;",
            "Ljava/util/List",
            "<",
            "Lorg/apache/cordova/PluginEntry;",
            ">;",
            "Lorg/apache/cordova/Whitelist;",
            "Lorg/apache/cordova/Whitelist;",
            "Lorg/apache/cordova/CordovaPreferences;",
            ")V"
        }
    .end annotation

    .prologue
    .line 142
    .local p4, "pluginEntries":Ljava/util/List;, "Ljava/util/List<Lorg/apache/cordova/PluginEntry;>;"
    iget-object v0, p0, Lorg/apache/cordova/CordovaWebView;->cordova:Lorg/apache/cordova/CordovaInterface;

    if-eqz v0, :cond_0

    .line 143
    new-instance v0, Ljava/lang/IllegalStateException;

    invoke-direct {v0}, Ljava/lang/IllegalStateException;-><init>()V

    throw v0

    .line 145
    :cond_0
    iput-object p1, p0, Lorg/apache/cordova/CordovaWebView;->cordova:Lorg/apache/cordova/CordovaInterface;

    .line 146
    iput-object p2, p0, Lorg/apache/cordova/CordovaWebView;->viewClient:Lorg/apache/cordova/CordovaWebViewClient;

    .line 147
    iput-object p3, p0, Lorg/apache/cordova/CordovaWebView;->chromeClient:Lorg/apache/cordova/CordovaChromeClient;

    .line 148
    iput-object p5, p0, Lorg/apache/cordova/CordovaWebView;->internalWhitelist:Lorg/apache/cordova/Whitelist;

    .line 149
    iput-object p6, p0, Lorg/apache/cordova/CordovaWebView;->externalWhitelist:Lorg/apache/cordova/Whitelist;

    .line 150
    iput-object p7, p0, Lorg/apache/cordova/CordovaWebView;->preferences:Lorg/apache/cordova/CordovaPreferences;

    .line 151
    invoke-super {p0, p3}, Landroid/webkit/WebView;->setWebChromeClient(Landroid/webkit/WebChromeClient;)V

    .line 152
    invoke-super {p0, p2}, Landroid/webkit/WebView;->setWebViewClient(Landroid/webkit/WebViewClient;)V

    .line 154
    new-instance v0, Lorg/apache/cordova/PluginManager;

    iget-object v1, p0, Lorg/apache/cordova/CordovaWebView;->cordova:Lorg/apache/cordova/CordovaInterface;

    invoke-direct {v0, p0, v1, p4}, Lorg/apache/cordova/PluginManager;-><init>(Lorg/apache/cordova/CordovaWebView;Lorg/apache/cordova/CordovaInterface;Ljava/util/List;)V

    iput-object v0, p0, Lorg/apache/cordova/CordovaWebView;->pluginManager:Lorg/apache/cordova/PluginManager;

    .line 155
    new-instance v0, Lorg/apache/cordova/CordovaBridge;

    iget-object v1, p0, Lorg/apache/cordova/CordovaWebView;->pluginManager:Lorg/apache/cordova/PluginManager;

    new-instance v2, Lorg/apache/cordova/NativeToJsMessageQueue;

    invoke-direct {v2, p0, p1}, Lorg/apache/cordova/NativeToJsMessageQueue;-><init>(Lorg/apache/cordova/CordovaWebView;Lorg/apache/cordova/CordovaInterface;)V

    invoke-direct {v0, v1, v2}, Lorg/apache/cordova/CordovaBridge;-><init>(Lorg/apache/cordova/PluginManager;Lorg/apache/cordova/NativeToJsMessageQueue;)V

    iput-object v0, p0, Lorg/apache/cordova/CordovaWebView;->bridge:Lorg/apache/cordova/CordovaBridge;

    .line 156
    new-instance v0, Lorg/apache/cordova/CordovaResourceApi;

    invoke-virtual {p0}, Lorg/apache/cordova/CordovaWebView;->getContext()Landroid/content/Context;

    move-result-object v1

    iget-object v2, p0, Lorg/apache/cordova/CordovaWebView;->pluginManager:Lorg/apache/cordova/PluginManager;

    invoke-direct {v0, v1, v2}, Lorg/apache/cordova/CordovaResourceApi;-><init>(Landroid/content/Context;Lorg/apache/cordova/PluginManager;)V

    iput-object v0, p0, Lorg/apache/cordova/CordovaWebView;->resourceApi:Lorg/apache/cordova/CordovaResourceApi;

    .line 158
    iget-object v0, p0, Lorg/apache/cordova/CordovaWebView;->pluginManager:Lorg/apache/cordova/PluginManager;

    const-string v1, "App"

    const-string v2, "org.apache.cordova.App"

    invoke-virtual {v0, v1, v2}, Lorg/apache/cordova/PluginManager;->addService(Ljava/lang/String;Ljava/lang/String;)V

    .line 159
    invoke-direct {p0}, Lorg/apache/cordova/CordovaWebView;->initWebViewSettings()V

    .line 160
    invoke-direct {p0}, Lorg/apache/cordova/CordovaWebView;->exposeJsInterface()V

    .line 161
    return-void
.end method

.method public isBackButtonBound()Z
    .locals 1
    .annotation runtime Ljava/lang/Deprecated;
    .end annotation

    .prologue
    .line 741
    const/4 v0, 0x4

    invoke-virtual {p0, v0}, Lorg/apache/cordova/CordovaWebView;->isButtonPlumbedToJs(I)Z

    move-result v0

    return v0
.end method

.method public isButtonPlumbedToJs(I)Z
    .locals 2
    .param p1, "keyCode"    # I

    .prologue
    .line 746
    iget-object v0, p0, Lorg/apache/cordova/CordovaWebView;->boundKeyCodes:Ljava/util/HashSet;

    invoke-static {p1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/util/HashSet;->contains(Ljava/lang/Object;)Z

    move-result v0

    return v0
.end method

.method public isCustomViewShowing()Z
    .locals 1

    .prologue
    .line 911
    iget-object v0, p0, Lorg/apache/cordova/CordovaWebView;->mCustomView:Landroid/view/View;

    if-eqz v0, :cond_0

    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public isPaused()Z
    .locals 1

    .prologue
    .line 817
    iget-boolean v0, p0, Lorg/apache/cordova/CordovaWebView;->paused:Z

    return v0
.end method

.method public loadUrl(Ljava/lang/String;)V
    .locals 1
    .param p1, "url"    # Ljava/lang/String;

    .prologue
    .line 340
    const-string v0, "about:blank"

    invoke-virtual {p1, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_0

    const-string v0, "javascript:"

    invoke-virtual {p1, v0}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_1

    .line 341
    :cond_0
    invoke-virtual {p0, p1}, Lorg/apache/cordova/CordovaWebView;->loadUrlNow(Ljava/lang/String;)V

    .line 346
    :goto_0
    return-void

    .line 344
    :cond_1
    invoke-virtual {p0, p1}, Lorg/apache/cordova/CordovaWebView;->loadUrlIntoView(Ljava/lang/String;)V

    goto :goto_0
.end method

.method public loadUrl(Ljava/lang/String;I)V
    .locals 1
    .param p1, "url"    # Ljava/lang/String;
    .param p2, "time"    # I
    .annotation runtime Ljava/lang/Deprecated;
    .end annotation

    .prologue
    .line 357
    if-nez p1, :cond_0

    .line 359
    invoke-static {}, Lorg/apache/cordova/Config;->getStartUrl()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p0, v0}, Lorg/apache/cordova/CordovaWebView;->loadUrlIntoView(Ljava/lang/String;)V

    .line 365
    :goto_0
    return-void

    .line 363
    :cond_0
    invoke-virtual {p0, p1}, Lorg/apache/cordova/CordovaWebView;->loadUrlIntoView(Ljava/lang/String;)V

    goto :goto_0
.end method

.method public loadUrlIntoView(Ljava/lang/String;)V
    .locals 1
    .param p1, "url"    # Ljava/lang/String;

    .prologue
    .line 368
    const/4 v0, 0x1

    invoke-virtual {p0, p1, v0}, Lorg/apache/cordova/CordovaWebView;->loadUrlIntoView(Ljava/lang/String;Z)V

    .line 369
    return-void
.end method

.method public loadUrlIntoView(Ljava/lang/String;I)V
    .locals 5
    .param p1, "url"    # Ljava/lang/String;
    .param p2, "time"    # I

    .prologue
    .line 454
    const-string v0, "javascript:"

    invoke-virtual {p1, v0}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v0

    if-nez v0, :cond_0

    invoke-virtual {p0}, Lorg/apache/cordova/CordovaWebView;->canGoBack()Z

    move-result v0

    if-eqz v0, :cond_1

    .line 467
    :cond_0
    :goto_0
    invoke-virtual {p0, p1}, Lorg/apache/cordova/CordovaWebView;->loadUrlIntoView(Ljava/lang/String;)V

    .line 468
    return-void

    .line 460
    :cond_1
    const-string v0, "CordovaWebView"

    const-string v1, "loadUrlIntoView(%s, %d)"

    const/4 v2, 0x2

    new-array v2, v2, [Ljava/lang/Object;

    const/4 v3, 0x0

    aput-object p1, v2, v3

    const/4 v3, 0x1

    invoke-static {p2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v4

    aput-object v4, v2, v3

    invoke-static {v0, v1, v2}, Lorg/apache/cordova/LOG;->d(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)V

    .line 463
    const-string v0, "splashscreen"

    const-string v1, "show"

    invoke-virtual {p0, v0, v1}, Lorg/apache/cordova/CordovaWebView;->postMessage(Ljava/lang/String;Ljava/lang/Object;)V

    goto :goto_0
.end method

.method public loadUrlIntoView(Ljava/lang/String;Z)V
    .locals 8
    .param p1, "url"    # Ljava/lang/String;
    .param p2, "recreatePlugins"    # Z

    .prologue
    .line 377
    const-string v1, "CordovaWebView"

    new-instance v6, Ljava/lang/StringBuilder;

    invoke-direct {v6}, Ljava/lang/StringBuilder;-><init>()V

    const-string v7, ">>> loadUrl("

    invoke-virtual {v6, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v6

    invoke-virtual {v6, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v6

    const-string v7, ")"

    invoke-virtual {v6, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v6

    invoke-virtual {v6}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v6

    invoke-static {v1, v6}, Lorg/apache/cordova/LOG;->d(Ljava/lang/String;Ljava/lang/String;)V

    .line 379
    invoke-direct {p0}, Lorg/apache/cordova/CordovaWebView;->initIfNecessary()V

    .line 381
    if-eqz p2, :cond_0

    .line 382
    iput-object p1, p0, Lorg/apache/cordova/CordovaWebView;->loadedUrl:Ljava/lang/String;

    .line 383
    iget-object v1, p0, Lorg/apache/cordova/CordovaWebView;->pluginManager:Lorg/apache/cordova/PluginManager;

    invoke-virtual {v1}, Lorg/apache/cordova/PluginManager;->init()V

    .line 387
    :cond_0
    move-object v3, p0

    .line 388
    .local v3, "me":Lorg/apache/cordova/CordovaWebView;
    iget v4, v3, Lorg/apache/cordova/CordovaWebView;->loadUrlTimeout:I

    .line 389
    .local v4, "currentLoadUrlTimeout":I
    const-string v1, "LoadUrlTimeoutValue"

    const-string v6, "20000"

    invoke-virtual {p0, v1, v6}, Lorg/apache/cordova/CordovaWebView;->getProperty(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-static {v1}, Ljava/lang/Integer;->parseInt(Ljava/lang/String;)I

    move-result v2

    .line 392
    .local v2, "loadUrlTimeoutValue":I
    new-instance v5, Lorg/apache/cordova/CordovaWebView$2;

    invoke-direct {v5, p0, v3, p1}, Lorg/apache/cordova/CordovaWebView$2;-><init>(Lorg/apache/cordova/CordovaWebView;Lorg/apache/cordova/CordovaWebView;Ljava/lang/String;)V

    .line 403
    .local v5, "loadError":Ljava/lang/Runnable;
    new-instance v0, Lorg/apache/cordova/CordovaWebView$3;

    move-object v1, p0

    invoke-direct/range {v0 .. v5}, Lorg/apache/cordova/CordovaWebView$3;-><init>(Lorg/apache/cordova/CordovaWebView;ILorg/apache/cordova/CordovaWebView;ILjava/lang/Runnable;)V

    .line 421
    .local v0, "timeoutCheck":Ljava/lang/Runnable;
    iget-object v1, p0, Lorg/apache/cordova/CordovaWebView;->cordova:Lorg/apache/cordova/CordovaInterface;

    invoke-interface {v1}, Lorg/apache/cordova/CordovaInterface;->getActivity()Landroid/app/Activity;

    move-result-object v1

    new-instance v6, Lorg/apache/cordova/CordovaWebView$4;

    invoke-direct {v6, p0, v0, v3, p1}, Lorg/apache/cordova/CordovaWebView$4;-><init>(Lorg/apache/cordova/CordovaWebView;Ljava/lang/Runnable;Lorg/apache/cordova/CordovaWebView;Ljava/lang/String;)V

    invoke-virtual {v1, v6}, Landroid/app/Activity;->runOnUiThread(Ljava/lang/Runnable;)V

    .line 427
    return-void
.end method

.method loadUrlNow(Ljava/lang/String;)V
    .locals 2
    .param p1, "url"    # Ljava/lang/String;

    .prologue
    .line 435
    const/4 v0, 0x3

    invoke-static {v0}, Lorg/apache/cordova/LOG;->isLoggable(I)Z

    move-result v0

    if-eqz v0, :cond_0

    const-string v0, "javascript:"

    invoke-virtual {p1, v0}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v0

    if-nez v0, :cond_0

    .line 436
    const-string v0, "CordovaWebView"

    const-string v1, ">>> loadUrlNow()"

    invoke-static {v0, v1}, Lorg/apache/cordova/LOG;->d(Ljava/lang/String;Ljava/lang/String;)V

    .line 438
    :cond_0
    const-string v0, "file://"

    invoke-virtual {p1, v0}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v0

    if-nez v0, :cond_1

    const-string v0, "javascript:"

    invoke-virtual {p1, v0}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v0

    if-nez v0, :cond_1

    iget-object v0, p0, Lorg/apache/cordova/CordovaWebView;->internalWhitelist:Lorg/apache/cordova/Whitelist;

    invoke-virtual {v0, p1}, Lorg/apache/cordova/Whitelist;->isUrlWhiteListed(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_2

    .line 439
    :cond_1
    invoke-super {p0, p1}, Landroid/webkit/WebView;->loadUrl(Ljava/lang/String;)V

    .line 441
    :cond_2
    return-void
.end method

.method public makeWebChromeClient(Lorg/apache/cordova/CordovaInterface;)Lorg/apache/cordova/CordovaChromeClient;
    .locals 1
    .param p1, "cordova"    # Lorg/apache/cordova/CordovaInterface;

    .prologue
    .line 277
    new-instance v0, Lorg/apache/cordova/CordovaChromeClient;

    invoke-direct {v0, p1, p0}, Lorg/apache/cordova/CordovaChromeClient;-><init>(Lorg/apache/cordova/CordovaInterface;Lorg/apache/cordova/CordovaWebView;)V

    return-object v0
.end method

.method public makeWebViewClient(Lorg/apache/cordova/CordovaInterface;)Lorg/apache/cordova/CordovaWebViewClient;
    .locals 2
    .param p1, "cordova"    # Lorg/apache/cordova/CordovaInterface;

    .prologue
    .line 281
    sget v0, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v1, 0xe

    if-ge v0, v1, :cond_0

    .line 282
    new-instance v0, Lorg/apache/cordova/CordovaWebViewClient;

    invoke-direct {v0, p1, p0}, Lorg/apache/cordova/CordovaWebViewClient;-><init>(Lorg/apache/cordova/CordovaInterface;Lorg/apache/cordova/CordovaWebView;)V

    .line 284
    :goto_0
    return-object v0

    :cond_0
    new-instance v0, Lorg/apache/cordova/IceCreamCordovaWebViewClient;

    invoke-direct {v0, p1, p0}, Lorg/apache/cordova/IceCreamCordovaWebViewClient;-><init>(Lorg/apache/cordova/CordovaInterface;Lorg/apache/cordova/CordovaWebView;)V

    goto :goto_0
.end method

.method public onKeyDown(ILandroid/view/KeyEvent;)Z
    .locals 7
    .param p1, "keyCode"    # I
    .param p2, "event"    # Landroid/view/KeyEvent;

    .prologue
    const/4 v6, 0x4

    const/4 v2, 0x0

    const/4 v3, 0x1

    .line 619
    iget-object v4, p0, Lorg/apache/cordova/CordovaWebView;->boundKeyCodes:Ljava/util/HashSet;

    invoke-static {p1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v5

    invoke-virtual {v4, v5}, Ljava/util/HashSet;->contains(Ljava/lang/Object;)Z

    move-result v4

    if-eqz v4, :cond_2

    .line 621
    const/16 v2, 0x19

    if-ne p1, v2, :cond_0

    .line 622
    const-string v2, "javascript:cordova.fireDocumentEvent(\'volumedownbutton\');"

    invoke-virtual {p0, v2}, Lorg/apache/cordova/CordovaWebView;->loadUrl(Ljava/lang/String;)V

    .line 654
    :goto_0
    return v3

    .line 626
    :cond_0
    const/16 v2, 0x18

    if-ne p1, v2, :cond_1

    .line 627
    const-string v2, "javascript:cordova.fireDocumentEvent(\'volumeupbutton\');"

    invoke-virtual {p0, v2}, Lorg/apache/cordova/CordovaWebView;->loadUrl(Ljava/lang/String;)V

    goto :goto_0

    .line 632
    :cond_1
    invoke-super {p0, p1, p2}, Landroid/webkit/WebView;->onKeyDown(ILandroid/view/KeyEvent;)Z

    move-result v3

    goto :goto_0

    .line 635
    :cond_2
    if-ne p1, v6, :cond_5

    .line 637
    invoke-virtual {p0}, Lorg/apache/cordova/CordovaWebView;->startOfHistory()Z

    move-result v4

    if-eqz v4, :cond_3

    invoke-virtual {p0, v6}, Lorg/apache/cordova/CordovaWebView;->isButtonPlumbedToJs(I)Z

    move-result v4

    if-eqz v4, :cond_4

    :cond_3
    move v2, v3

    :cond_4
    move v3, v2

    goto :goto_0

    .line 639
    :cond_5
    const/16 v4, 0x52

    if-ne p1, v4, :cond_7

    .line 642
    invoke-virtual {p0}, Lorg/apache/cordova/CordovaWebView;->getFocusedChild()Landroid/view/View;

    move-result-object v0

    .line 643
    .local v0, "childView":Landroid/view/View;
    if-eqz v0, :cond_6

    .line 646
    iget-object v4, p0, Lorg/apache/cordova/CordovaWebView;->cordova:Lorg/apache/cordova/CordovaInterface;

    invoke-interface {v4}, Lorg/apache/cordova/CordovaInterface;->getActivity()Landroid/app/Activity;

    move-result-object v4

    const-string v5, "input_method"

    invoke-virtual {v4, v5}, Landroid/app/Activity;->getSystemService(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Landroid/view/inputmethod/InputMethodManager;

    .line 647
    .local v1, "imm":Landroid/view/inputmethod/InputMethodManager;
    invoke-virtual {v0}, Landroid/view/View;->getWindowToken()Landroid/os/IBinder;

    move-result-object v4

    invoke-virtual {v1, v4, v2}, Landroid/view/inputmethod/InputMethodManager;->hideSoftInputFromWindow(Landroid/os/IBinder;I)Z

    .line 648
    iget-object v2, p0, Lorg/apache/cordova/CordovaWebView;->cordova:Lorg/apache/cordova/CordovaInterface;

    invoke-interface {v2}, Lorg/apache/cordova/CordovaInterface;->getActivity()Landroid/app/Activity;

    move-result-object v2

    invoke-virtual {v2}, Landroid/app/Activity;->openOptionsMenu()V

    goto :goto_0

    .line 651
    .end local v1    # "imm":Landroid/view/inputmethod/InputMethodManager;
    :cond_6
    invoke-super {p0, p1, p2}, Landroid/webkit/WebView;->onKeyDown(ILandroid/view/KeyEvent;)Z

    move-result v3

    goto :goto_0

    .line 654
    .end local v0    # "childView":Landroid/view/View;
    :cond_7
    invoke-super {p0, p1, p2}, Landroid/webkit/WebView;->onKeyDown(ILandroid/view/KeyEvent;)Z

    move-result v3

    goto :goto_0
.end method

.method public onKeyUp(ILandroid/view/KeyEvent;)Z
    .locals 4
    .param p1, "keyCode"    # I
    .param p2, "event"    # Landroid/view/KeyEvent;

    .prologue
    const/4 v2, 0x4

    const/4 v0, 0x1

    .line 661
    if-ne p1, v2, :cond_4

    .line 663
    iget-object v1, p0, Lorg/apache/cordova/CordovaWebView;->mCustomView:Landroid/view/View;

    if-eqz v1, :cond_1

    .line 664
    invoke-virtual {p0}, Lorg/apache/cordova/CordovaWebView;->hideCustomView()V

    .line 697
    :cond_0
    :goto_0
    return v0

    .line 669
    :cond_1
    invoke-virtual {p0, v2}, Lorg/apache/cordova/CordovaWebView;->isButtonPlumbedToJs(I)Z

    move-result v1

    if-eqz v1, :cond_2

    .line 670
    const-string v1, "javascript:cordova.fireDocumentEvent(\'backbutton\');"

    invoke-virtual {p0, v1}, Lorg/apache/cordova/CordovaWebView;->loadUrl(Ljava/lang/String;)V

    goto :goto_0

    .line 675
    :cond_2
    invoke-virtual {p0}, Lorg/apache/cordova/CordovaWebView;->backHistory()Z

    move-result v1

    if-nez v1, :cond_0

    .line 697
    :cond_3
    invoke-super {p0, p1, p2}, Landroid/webkit/WebView;->onKeyUp(ILandroid/view/KeyEvent;)Z

    move-result v0

    goto :goto_0

    .line 683
    :cond_4
    const/16 v1, 0x52

    if-ne p1, v1, :cond_6

    .line 684
    iget-wide v0, p0, Lorg/apache/cordova/CordovaWebView;->lastMenuEventTime:J

    invoke-virtual {p2}, Landroid/view/KeyEvent;->getEventTime()J

    move-result-wide v2

    cmp-long v0, v0, v2

    if-gez v0, :cond_5

    .line 685
    const-string v0, "javascript:cordova.fireDocumentEvent(\'menubutton\');"

    invoke-virtual {p0, v0}, Lorg/apache/cordova/CordovaWebView;->loadUrl(Ljava/lang/String;)V

    .line 687
    :cond_5
    invoke-virtual {p2}, Landroid/view/KeyEvent;->getEventTime()J

    move-result-wide v0

    iput-wide v0, p0, Lorg/apache/cordova/CordovaWebView;->lastMenuEventTime:J

    .line 688
    invoke-super {p0, p1, p2}, Landroid/webkit/WebView;->onKeyUp(ILandroid/view/KeyEvent;)Z

    move-result v0

    goto :goto_0

    .line 691
    :cond_6
    const/16 v1, 0x54

    if-ne p1, v1, :cond_3

    .line 692
    const-string v1, "javascript:cordova.fireDocumentEvent(\'searchbutton\');"

    invoke-virtual {p0, v1}, Lorg/apache/cordova/CordovaWebView;->loadUrl(Ljava/lang/String;)V

    goto :goto_0
.end method

.method public onNewIntent(Landroid/content/Intent;)V
    .locals 1
    .param p1, "intent"    # Landroid/content/Intent;

    .prologue
    .line 810
    iget-object v0, p0, Lorg/apache/cordova/CordovaWebView;->pluginManager:Lorg/apache/cordova/PluginManager;

    if-eqz v0, :cond_0

    .line 811
    iget-object v0, p0, Lorg/apache/cordova/CordovaWebView;->pluginManager:Lorg/apache/cordova/PluginManager;

    invoke-virtual {v0, p1}, Lorg/apache/cordova/PluginManager;->onNewIntent(Landroid/content/Intent;)V

    .line 813
    :cond_0
    return-void
.end method

.method public onScrollChanged(IIII)V
    .locals 6
    .param p1, "l"    # I
    .param p2, "t"    # I
    .param p3, "oldl"    # I
    .param p4, "oldt"    # I

    .prologue
    .line 478
    invoke-super {p0, p1, p2, p3, p4}, Landroid/webkit/WebView;->onScrollChanged(IIII)V

    .line 480
    new-instance v0, Lorg/apache/cordova/ScrollEvent;

    move v1, p1

    move v2, p2

    move v3, p3

    move v4, p4

    move-object v5, p0

    invoke-direct/range {v0 .. v5}, Lorg/apache/cordova/ScrollEvent;-><init>(IIIILandroid/view/View;)V

    .line 481
    .local v0, "myEvent":Lorg/apache/cordova/ScrollEvent;
    const-string v1, "onScrollChanged"

    invoke-virtual {p0, v1, v0}, Lorg/apache/cordova/CordovaWebView;->postMessage(Ljava/lang/String;Ljava/lang/Object;)V

    .line 482
    return-void
.end method

.method public postMessage(Ljava/lang/String;Ljava/lang/Object;)V
    .locals 1
    .param p1, "id"    # Ljava/lang/String;
    .param p2, "data"    # Ljava/lang/Object;

    .prologue
    .line 527
    iget-object v0, p0, Lorg/apache/cordova/CordovaWebView;->pluginManager:Lorg/apache/cordova/PluginManager;

    if-eqz v0, :cond_0

    .line 528
    iget-object v0, p0, Lorg/apache/cordova/CordovaWebView;->pluginManager:Lorg/apache/cordova/PluginManager;

    invoke-virtual {v0, p1, p2}, Lorg/apache/cordova/PluginManager;->postMessage(Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/Object;

    .line 530
    :cond_0
    return-void
.end method

.method public printBackForwardList()V
    .locals 8

    .prologue
    .line 835
    invoke-virtual {p0}, Lorg/apache/cordova/CordovaWebView;->copyBackForwardList()Landroid/webkit/WebBackForwardList;

    move-result-object v0

    .line 836
    .local v0, "currentList":Landroid/webkit/WebBackForwardList;
    invoke-virtual {v0}, Landroid/webkit/WebBackForwardList;->getSize()I

    move-result v1

    .line 837
    .local v1, "currentSize":I
    const/4 v2, 0x0

    .local v2, "i":I
    :goto_0
    if-ge v2, v1, :cond_0

    .line 839
    invoke-virtual {v0, v2}, Landroid/webkit/WebBackForwardList;->getItemAtIndex(I)Landroid/webkit/WebHistoryItem;

    move-result-object v3

    .line 840
    .local v3, "item":Landroid/webkit/WebHistoryItem;
    invoke-virtual {v3}, Landroid/webkit/WebHistoryItem;->getUrl()Ljava/lang/String;

    move-result-object v4

    .line 841
    .local v4, "url":Ljava/lang/String;
    const-string v5, "CordovaWebView"

    new-instance v6, Ljava/lang/StringBuilder;

    invoke-direct {v6}, Ljava/lang/StringBuilder;-><init>()V

    const-string v7, "The URL at index: "

    invoke-virtual {v6, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v6

    invoke-static {v2}, Ljava/lang/Integer;->toString(I)Ljava/lang/String;

    move-result-object v7

    invoke-virtual {v6, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v6

    const-string v7, " is "

    invoke-virtual {v6, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v6

    invoke-virtual {v6, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v6

    invoke-virtual {v6}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v6

    invoke-static {v5, v6}, Lorg/apache/cordova/LOG;->d(Ljava/lang/String;Ljava/lang/String;)V

    .line 837
    add-int/lit8 v2, v2, 0x1

    goto :goto_0

    .line 843
    .end local v3    # "item":Landroid/webkit/WebHistoryItem;
    .end local v4    # "url":Ljava/lang/String;
    :cond_0
    return-void
.end method

.method public restoreState(Landroid/os/Bundle;)Landroid/webkit/WebBackForwardList;
    .locals 3
    .param p1, "savedInstanceState"    # Landroid/os/Bundle;

    .prologue
    .line 916
    invoke-super {p0, p1}, Landroid/webkit/WebView;->restoreState(Landroid/os/Bundle;)Landroid/webkit/WebBackForwardList;

    move-result-object v0

    .line 917
    .local v0, "myList":Landroid/webkit/WebBackForwardList;
    const-string v1, "CordovaWebView"

    const-string v2, "WebView restoration crew now restoring!"

    invoke-static {v1, v2}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 919
    iget-object v1, p0, Lorg/apache/cordova/CordovaWebView;->pluginManager:Lorg/apache/cordova/PluginManager;

    invoke-virtual {v1}, Lorg/apache/cordova/PluginManager;->init()V

    .line 920
    return-object v0
.end method

.method public sendJavascript(Ljava/lang/String;)V
    .locals 1
    .param p1, "statement"    # Ljava/lang/String;
    .annotation runtime Ljava/lang/Deprecated;
    .end annotation

    .prologue
    .line 506
    iget-object v0, p0, Lorg/apache/cordova/CordovaWebView;->bridge:Lorg/apache/cordova/CordovaBridge;

    invoke-virtual {v0}, Lorg/apache/cordova/CordovaBridge;->getMessageQueue()Lorg/apache/cordova/NativeToJsMessageQueue;

    move-result-object v0

    invoke-virtual {v0, p1}, Lorg/apache/cordova/NativeToJsMessageQueue;->addJavaScript(Ljava/lang/String;)V

    .line 507
    return-void
.end method

.method public sendPluginResult(Lorg/apache/cordova/PluginResult;Ljava/lang/String;)V
    .locals 1
    .param p1, "result"    # Lorg/apache/cordova/PluginResult;
    .param p2, "callbackId"    # Ljava/lang/String;

    .prologue
    .line 517
    iget-object v0, p0, Lorg/apache/cordova/CordovaWebView;->bridge:Lorg/apache/cordova/CordovaBridge;

    invoke-virtual {v0}, Lorg/apache/cordova/CordovaBridge;->getMessageQueue()Lorg/apache/cordova/NativeToJsMessageQueue;

    move-result-object v0

    invoke-virtual {v0, p1, p2}, Lorg/apache/cordova/NativeToJsMessageQueue;->addPluginResult(Lorg/apache/cordova/PluginResult;Ljava/lang/String;)V

    .line 518
    return-void
.end method

.method public setButtonPlumbedToJs(IZ)V
    .locals 3
    .param p1, "keyCode"    # I
    .param p2, "override"    # Z

    .prologue
    .line 701
    sparse-switch p1, :sswitch_data_0

    .line 713
    new-instance v0, Ljava/lang/IllegalArgumentException;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "Unsupported keycode: "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 706
    :sswitch_0
    if-eqz p2, :cond_0

    .line 707
    iget-object v0, p0, Lorg/apache/cordova/CordovaWebView;->boundKeyCodes:Ljava/util/HashSet;

    invoke-static {p1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/util/HashSet;->add(Ljava/lang/Object;)Z

    .line 711
    :goto_0
    return-void

    .line 709
    :cond_0
    iget-object v0, p0, Lorg/apache/cordova/CordovaWebView;->boundKeyCodes:Ljava/util/HashSet;

    invoke-static {p1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/util/HashSet;->remove(Ljava/lang/Object;)Z

    goto :goto_0

    .line 701
    :sswitch_data_0
    .sparse-switch
        0x4 -> :sswitch_0
        0x18 -> :sswitch_0
        0x19 -> :sswitch_0
    .end sparse-switch
.end method

.method public setWebChromeClient(Landroid/webkit/WebChromeClient;)V
    .locals 1
    .param p1, "client"    # Landroid/webkit/WebChromeClient;

    .prologue
    .line 316
    move-object v0, p1

    check-cast v0, Lorg/apache/cordova/CordovaChromeClient;

    iput-object v0, p0, Lorg/apache/cordova/CordovaWebView;->chromeClient:Lorg/apache/cordova/CordovaChromeClient;

    .line 317
    invoke-super {p0, p1}, Landroid/webkit/WebView;->setWebChromeClient(Landroid/webkit/WebChromeClient;)V

    .line 318
    return-void
.end method

.method public setWebViewClient(Landroid/webkit/WebViewClient;)V
    .locals 1
    .param p1, "client"    # Landroid/webkit/WebViewClient;

    .prologue
    .line 310
    move-object v0, p1

    check-cast v0, Lorg/apache/cordova/CordovaWebViewClient;

    iput-object v0, p0, Lorg/apache/cordova/CordovaWebView;->viewClient:Lorg/apache/cordova/CordovaWebViewClient;

    .line 311
    invoke-super {p0, p1}, Landroid/webkit/WebView;->setWebViewClient(Landroid/webkit/WebViewClient;)V

    .line 312
    return-void
.end method

.method protected shouldRequestFocusOnInit()Z
    .locals 1

    .prologue
    .line 294
    const/4 v0, 0x1

    return v0
.end method

.method public showCustomView(Landroid/view/View;Landroid/webkit/WebChromeClient$CustomViewCallback;)V
    .locals 3
    .param p1, "view"    # Landroid/view/View;
    .param p2, "callback"    # Landroid/webkit/WebChromeClient$CustomViewCallback;

    .prologue
    .line 863
    const-string v1, "CordovaWebView"

    const-string v2, "showing Custom View"

    invoke-static {v1, v2}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 865
    iget-object v1, p0, Lorg/apache/cordova/CordovaWebView;->mCustomView:Landroid/view/View;

    if-eqz v1, :cond_0

    .line 866
    invoke-interface {p2}, Landroid/webkit/WebChromeClient$CustomViewCallback;->onCustomViewHidden()V

    .line 884
    :goto_0
    return-void

    .line 871
    :cond_0
    iput-object p1, p0, Lorg/apache/cordova/CordovaWebView;->mCustomView:Landroid/view/View;

    .line 872
    iput-object p2, p0, Lorg/apache/cordova/CordovaWebView;->mCustomViewCallback:Landroid/webkit/WebChromeClient$CustomViewCallback;

    .line 875
    invoke-virtual {p0}, Lorg/apache/cordova/CordovaWebView;->getParent()Landroid/view/ViewParent;

    move-result-object v0

    check-cast v0, Landroid/view/ViewGroup;

    .line 876
    .local v0, "parent":Landroid/view/ViewGroup;
    sget-object v1, Lorg/apache/cordova/CordovaWebView;->COVER_SCREEN_GRAVITY_CENTER:Landroid/widget/FrameLayout$LayoutParams;

    invoke-virtual {v0, p1, v1}, Landroid/view/ViewGroup;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    .line 879
    const/16 v1, 0x8

    invoke-virtual {p0, v1}, Lorg/apache/cordova/CordovaWebView;->setVisibility(I)V

    .line 882
    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Landroid/view/ViewGroup;->setVisibility(I)V

    .line 883
    invoke-virtual {v0}, Landroid/view/ViewGroup;->bringToFront()V

    goto :goto_0
.end method

.method public showWebPage(Ljava/lang/String;ZZLjava/util/HashMap;)V
    .locals 8
    .param p1, "url"    # Ljava/lang/String;
    .param p2, "openExternal"    # Z
    .param p3, "clearHistory"    # Z
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/String;",
            "ZZ",
            "Ljava/util/HashMap",
            "<",
            "Ljava/lang/String;",
            "Ljava/lang/Object;",
            ">;)V"
        }
    .end annotation

    .prologue
    .line 560
    .local p4, "params":Ljava/util/HashMap;, "Ljava/util/HashMap<Ljava/lang/String;Ljava/lang/Object;>;"
    const-string v3, "CordovaWebView"

    const-string v4, "showWebPage(%s, %b, %b, HashMap"

    const/4 v5, 0x3

    new-array v5, v5, [Ljava/lang/Object;

    const/4 v6, 0x0

    aput-object p1, v5, v6

    const/4 v6, 0x1

    invoke-static {p2}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v7

    aput-object v7, v5, v6

    const/4 v6, 0x2

    invoke-static {p3}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v7

    aput-object v7, v5, v6

    invoke-static {v3, v4, v5}, Lorg/apache/cordova/LOG;->d(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)V

    .line 563
    if-eqz p3, :cond_0

    .line 564
    invoke-virtual {p0}, Lorg/apache/cordova/CordovaWebView;->clearHistory()V

    .line 568
    :cond_0
    if-nez p2, :cond_3

    .line 571
    const-string v3, "file://"

    invoke-virtual {p1, v3}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v3

    if-nez v3, :cond_1

    iget-object v3, p0, Lorg/apache/cordova/CordovaWebView;->internalWhitelist:Lorg/apache/cordova/Whitelist;

    invoke-virtual {v3, p1}, Lorg/apache/cordova/Whitelist;->isUrlWhiteListed(Ljava/lang/String;)Z

    move-result v3

    if-eqz v3, :cond_2

    .line 574
    :cond_1
    invoke-virtual {p0, p1}, Lorg/apache/cordova/CordovaWebView;->loadUrl(Ljava/lang/String;)V

    .line 594
    :goto_0
    return-void

    .line 578
    :cond_2
    const-string v3, "CordovaWebView"

    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    const-string v5, "showWebPage: Cannot load URL into webview since it is not in white list.  Loading into browser instead. (URL="

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    const-string v5, ")"

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    invoke-static {v3, v4}, Lorg/apache/cordova/LOG;->w(Ljava/lang/String;Ljava/lang/String;)V

    .line 583
    :cond_3
    :try_start_0
    new-instance v1, Landroid/content/Intent;

    const-string v3, "android.intent.action.VIEW"

    invoke-direct {v1, v3}, Landroid/content/Intent;-><init>(Ljava/lang/String;)V

    .line 584
    .local v1, "intent":Landroid/content/Intent;
    invoke-static {p1}, Landroid/net/Uri;->parse(Ljava/lang/String;)Landroid/net/Uri;

    move-result-object v2

    .line 585
    .local v2, "uri":Landroid/net/Uri;
    const-string v3, "file"

    invoke-virtual {v2}, Landroid/net/Uri;->getScheme()Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v3, v4}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v3

    if-eqz v3, :cond_4

    .line 586
    iget-object v3, p0, Lorg/apache/cordova/CordovaWebView;->resourceApi:Lorg/apache/cordova/CordovaResourceApi;

    invoke-virtual {v3, v2}, Lorg/apache/cordova/CordovaResourceApi;->getMimeType(Landroid/net/Uri;)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v1, v2, v3}, Landroid/content/Intent;->setDataAndType(Landroid/net/Uri;Ljava/lang/String;)Landroid/content/Intent;

    .line 590
    :goto_1
    iget-object v3, p0, Lorg/apache/cordova/CordovaWebView;->cordova:Lorg/apache/cordova/CordovaInterface;

    invoke-interface {v3}, Lorg/apache/cordova/CordovaInterface;->getActivity()Landroid/app/Activity;

    move-result-object v3

    invoke-virtual {v3, v1}, Landroid/app/Activity;->startActivity(Landroid/content/Intent;)V
    :try_end_0
    .catch Landroid/content/ActivityNotFoundException; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    .line 591
    .end local v1    # "intent":Landroid/content/Intent;
    .end local v2    # "uri":Landroid/net/Uri;
    :catch_0
    move-exception v0

    .line 592
    .local v0, "e":Landroid/content/ActivityNotFoundException;
    const-string v3, "CordovaWebView"

    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    const-string v5, "Error loading url "

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    invoke-static {v3, v4, v0}, Lorg/apache/cordova/LOG;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V

    goto :goto_0

    .line 588
    .end local v0    # "e":Landroid/content/ActivityNotFoundException;
    .restart local v1    # "intent":Landroid/content/Intent;
    .restart local v2    # "uri":Landroid/net/Uri;
    :cond_4
    :try_start_1
    invoke-virtual {v1, v2}, Landroid/content/Intent;->setData(Landroid/net/Uri;)Landroid/content/Intent;
    :try_end_1
    .catch Landroid/content/ActivityNotFoundException; {:try_start_1 .. :try_end_1} :catch_0

    goto :goto_1
.end method

.method public startOfHistory()Z
    .locals 7

    .prologue
    const/4 v4, 0x0

    .line 849
    invoke-virtual {p0}, Lorg/apache/cordova/CordovaWebView;->copyBackForwardList()Landroid/webkit/WebBackForwardList;

    move-result-object v0

    .line 850
    .local v0, "currentList":Landroid/webkit/WebBackForwardList;
    invoke-virtual {v0, v4}, Landroid/webkit/WebBackForwardList;->getItemAtIndex(I)Landroid/webkit/WebHistoryItem;

    move-result-object v2

    .line 851
    .local v2, "item":Landroid/webkit/WebHistoryItem;
    if-eqz v2, :cond_0

    .line 852
    invoke-virtual {v2}, Landroid/webkit/WebHistoryItem;->getUrl()Ljava/lang/String;

    move-result-object v3

    .line 853
    .local v3, "url":Ljava/lang/String;
    invoke-virtual {p0}, Lorg/apache/cordova/CordovaWebView;->getUrl()Ljava/lang/String;

    move-result-object v1

    .line 854
    .local v1, "currentUrl":Ljava/lang/String;
    const-string v4, "CordovaWebView"

    new-instance v5, Ljava/lang/StringBuilder;

    invoke-direct {v5}, Ljava/lang/StringBuilder;-><init>()V

    const-string v6, "The current URL is: "

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v5

    invoke-virtual {v5, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v5

    invoke-virtual {v5}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v5

    invoke-static {v4, v5}, Lorg/apache/cordova/LOG;->d(Ljava/lang/String;Ljava/lang/String;)V

    .line 855
    const-string v4, "CordovaWebView"

    new-instance v5, Ljava/lang/StringBuilder;

    invoke-direct {v5}, Ljava/lang/StringBuilder;-><init>()V

    const-string v6, "The URL at item 0 is: "

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v5

    invoke-virtual {v5, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v5

    invoke-virtual {v5}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v5

    invoke-static {v4, v5}, Lorg/apache/cordova/LOG;->d(Ljava/lang/String;Ljava/lang/String;)V

    .line 856
    invoke-virtual {v1, v3}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v4

    .line 858
    .end local v1    # "currentUrl":Ljava/lang/String;
    .end local v3    # "url":Ljava/lang/String;
    :cond_0
    return v4
.end method

.method public stopLoading()V
    .locals 2

    .prologue
    .line 472
    iget-object v0, p0, Lorg/apache/cordova/CordovaWebView;->viewClient:Lorg/apache/cordova/CordovaWebViewClient;

    const/4 v1, 0x0

    iput-boolean v1, v0, Lorg/apache/cordova/CordovaWebViewClient;->isCurrentlyLoading:Z

    .line 473
    invoke-super {p0}, Landroid/webkit/WebView;->stopLoading()V

    .line 474
    return-void
.end method

.method public storeResult(IILandroid/content/Intent;)V
    .locals 0
    .param p1, "requestCode"    # I
    .param p2, "resultCode"    # I
    .param p3, "intent"    # Landroid/content/Intent;
    .annotation runtime Ljava/lang/Deprecated;
    .end annotation

    .prologue
    .line 925
    return-void
.end method
