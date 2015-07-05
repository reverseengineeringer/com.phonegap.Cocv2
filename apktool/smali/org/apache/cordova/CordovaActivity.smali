.class public Lorg/apache/cordova/CordovaActivity;
.super Landroid/app/Activity;
.source "CordovaActivity.java"

# interfaces
.implements Lorg/apache/cordova/CordovaInterface;


# static fields
.field private static ACTIVITY_EXITING:I

.field private static ACTIVITY_RUNNING:I

.field private static ACTIVITY_STARTING:I

.field public static TAG:Ljava/lang/String;


# instance fields
.field protected activityResultCallback:Lorg/apache/cordova/CordovaPlugin;

.field protected activityResultKeepRunning:Z

.field private activityState:I

.field protected appView:Lorg/apache/cordova/CordovaWebView;

.field protected externalWhitelist:Lorg/apache/cordova/Whitelist;

.field private initCallbackClass:Ljava/lang/String;

.field protected internalWhitelist:Lorg/apache/cordova/Whitelist;

.field protected keepRunning:Z

.field protected launchUrl:Ljava/lang/String;

.field protected loadUrlTimeoutValue:I

.field protected pluginEntries:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList",
            "<",
            "Lorg/apache/cordova/PluginEntry;",
            ">;"
        }
    .end annotation
.end field

.field protected preferences:Lorg/apache/cordova/CordovaPreferences;

.field protected root:Landroid/widget/LinearLayout;
    .annotation runtime Ljava/lang/Deprecated;
    .end annotation
.end field

.field protected spinnerDialog:Landroid/app/ProgressDialog;

.field protected splashDialog:Landroid/app/Dialog;

.field protected splashscreen:I

.field protected splashscreenTime:I

.field private final threadPool:Ljava/util/concurrent/ExecutorService;

.field protected webViewClient:Lorg/apache/cordova/CordovaWebViewClient;
    .annotation runtime Ljava/lang/Deprecated;
    .end annotation
.end field


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .prologue
    .line 91
    const-string v0, "CordovaActivity"

    sput-object v0, Lorg/apache/cordova/CordovaActivity;->TAG:Ljava/lang/String;

    .line 105
    const/4 v0, 0x0

    sput v0, Lorg/apache/cordova/CordovaActivity;->ACTIVITY_STARTING:I

    .line 106
    const/4 v0, 0x1

    sput v0, Lorg/apache/cordova/CordovaActivity;->ACTIVITY_RUNNING:I

    .line 107
    const/4 v0, 0x2

    sput v0, Lorg/apache/cordova/CordovaActivity;->ACTIVITY_EXITING:I

    return-void
.end method

.method public constructor <init>()V
    .locals 3

    .prologue
    const/4 v2, 0x0

    const/4 v1, 0x0

    .line 90
    invoke-direct {p0}, Landroid/app/Activity;-><init>()V

    .line 102
    iput-object v2, p0, Lorg/apache/cordova/CordovaActivity;->spinnerDialog:Landroid/app/ProgressDialog;

    .line 103
    invoke-static {}, Ljava/util/concurrent/Executors;->newCachedThreadPool()Ljava/util/concurrent/ExecutorService;

    move-result-object v0

    iput-object v0, p0, Lorg/apache/cordova/CordovaActivity;->threadPool:Ljava/util/concurrent/ExecutorService;

    .line 108
    iput v1, p0, Lorg/apache/cordova/CordovaActivity;->activityState:I

    .line 111
    iput-object v2, p0, Lorg/apache/cordova/CordovaActivity;->activityResultCallback:Lorg/apache/cordova/CordovaPlugin;

    .line 120
    iput v1, p0, Lorg/apache/cordova/CordovaActivity;->splashscreen:I

    .line 121
    const/16 v0, 0xbb8

    iput v0, p0, Lorg/apache/cordova/CordovaActivity;->splashscreenTime:I

    .line 124
    const/16 v0, 0x4e20

    iput v0, p0, Lorg/apache/cordova/CordovaActivity;->loadUrlTimeoutValue:I

    .line 129
    const/4 v0, 0x1

    iput-boolean v0, p0, Lorg/apache/cordova/CordovaActivity;->keepRunning:Z

    return-void
.end method


# virtual methods
.method public addService(Ljava/lang/String;Ljava/lang/String;)V
    .locals 1
    .param p1, "serviceType"    # Ljava/lang/String;
    .param p2, "className"    # Ljava/lang/String;
    .annotation runtime Ljava/lang/Deprecated;
    .end annotation

    .prologue
    .line 673
    iget-object v0, p0, Lorg/apache/cordova/CordovaActivity;->appView:Lorg/apache/cordova/CordovaWebView;

    if-eqz v0, :cond_0

    iget-object v0, p0, Lorg/apache/cordova/CordovaActivity;->appView:Lorg/apache/cordova/CordovaWebView;

    iget-object v0, v0, Lorg/apache/cordova/CordovaWebView;->pluginManager:Lorg/apache/cordova/PluginManager;

    if-eqz v0, :cond_0

    .line 674
    iget-object v0, p0, Lorg/apache/cordova/CordovaActivity;->appView:Lorg/apache/cordova/CordovaWebView;

    iget-object v0, v0, Lorg/apache/cordova/CordovaWebView;->pluginManager:Lorg/apache/cordova/PluginManager;

    invoke-virtual {v0, p1, p2}, Lorg/apache/cordova/PluginManager;->addService(Ljava/lang/String;Ljava/lang/String;)V

    .line 676
    :cond_0
    return-void
.end method

.method public backHistory()Z
    .locals 1
    .annotation runtime Ljava/lang/Deprecated;
    .end annotation

    .prologue
    .line 469
    iget-object v0, p0, Lorg/apache/cordova/CordovaActivity;->appView:Lorg/apache/cordova/CordovaWebView;

    if-eqz v0, :cond_0

    .line 470
    iget-object v0, p0, Lorg/apache/cordova/CordovaActivity;->appView:Lorg/apache/cordova/CordovaWebView;

    invoke-virtual {v0}, Lorg/apache/cordova/CordovaWebView;->backHistory()Z

    move-result v0

    .line 472
    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public cancelLoadUrl()V
    .locals 0
    .annotation runtime Ljava/lang/Deprecated;
    .end annotation

    .prologue
    .line 441
    return-void
.end method

.method public clearAuthenticationTokens()V
    .locals 1

    .prologue
    .line 193
    iget-object v0, p0, Lorg/apache/cordova/CordovaActivity;->appView:Lorg/apache/cordova/CordovaWebView;

    if-eqz v0, :cond_0

    iget-object v0, p0, Lorg/apache/cordova/CordovaActivity;->appView:Lorg/apache/cordova/CordovaWebView;

    iget-object v0, v0, Lorg/apache/cordova/CordovaWebView;->viewClient:Lorg/apache/cordova/CordovaWebViewClient;

    if-eqz v0, :cond_0

    .line 194
    iget-object v0, p0, Lorg/apache/cordova/CordovaActivity;->appView:Lorg/apache/cordova/CordovaWebView;

    iget-object v0, v0, Lorg/apache/cordova/CordovaWebView;->viewClient:Lorg/apache/cordova/CordovaWebViewClient;

    invoke-virtual {v0}, Lorg/apache/cordova/CordovaWebViewClient;->clearAuthenticationTokens()V

    .line 196
    :cond_0
    return-void
.end method

.method public clearCache()V
    .locals 2
    .annotation runtime Ljava/lang/Deprecated;
    .end annotation

    .prologue
    .line 448
    iget-object v0, p0, Lorg/apache/cordova/CordovaActivity;->appView:Lorg/apache/cordova/CordovaWebView;

    if-nez v0, :cond_0

    .line 449
    invoke-virtual {p0}, Lorg/apache/cordova/CordovaActivity;->init()V

    .line 451
    :cond_0
    iget-object v0, p0, Lorg/apache/cordova/CordovaActivity;->appView:Lorg/apache/cordova/CordovaWebView;

    const/4 v1, 0x1

    invoke-virtual {v0, v1}, Lorg/apache/cordova/CordovaWebView;->clearCache(Z)V

    .line 452
    return-void
.end method

.method public clearHistory()V
    .locals 1
    .annotation runtime Ljava/lang/Deprecated;
    .end annotation

    .prologue
    .line 459
    iget-object v0, p0, Lorg/apache/cordova/CordovaActivity;->appView:Lorg/apache/cordova/CordovaWebView;

    invoke-virtual {v0}, Lorg/apache/cordova/CordovaWebView;->clearHistory()V

    .line 460
    return-void
.end method

.method protected createViews()V
    .locals 10

    .prologue
    const/4 v9, -0x1

    .line 252
    sget-object v6, Lorg/apache/cordova/CordovaActivity;->TAG:Ljava/lang/String;

    const-string v7, "CordovaActivity.createViews()"

    invoke-static {v6, v7}, Lorg/apache/cordova/LOG;->d(Ljava/lang/String;Ljava/lang/String;)V

    .line 254
    invoke-virtual {p0}, Lorg/apache/cordova/CordovaActivity;->getWindowManager()Landroid/view/WindowManager;

    move-result-object v6

    invoke-interface {v6}, Landroid/view/WindowManager;->getDefaultDisplay()Landroid/view/Display;

    move-result-object v1

    .line 255
    .local v1, "display":Landroid/view/Display;
    invoke-virtual {v1}, Landroid/view/Display;->getWidth()I

    move-result v5

    .line 256
    .local v5, "width":I
    invoke-virtual {v1}, Landroid/view/Display;->getHeight()I

    move-result v2

    .line 258
    .local v2, "height":I
    new-instance v6, Lorg/apache/cordova/LinearLayoutSoftKeyboardDetect;

    invoke-direct {v6, p0, v5, v2}, Lorg/apache/cordova/LinearLayoutSoftKeyboardDetect;-><init>(Landroid/content/Context;II)V

    iput-object v6, p0, Lorg/apache/cordova/CordovaActivity;->root:Landroid/widget/LinearLayout;

    .line 259
    iget-object v6, p0, Lorg/apache/cordova/CordovaActivity;->root:Landroid/widget/LinearLayout;

    const/4 v7, 0x1

    invoke-virtual {v6, v7}, Landroid/widget/LinearLayout;->setOrientation(I)V

    .line 260
    iget-object v6, p0, Lorg/apache/cordova/CordovaActivity;->root:Landroid/widget/LinearLayout;

    new-instance v7, Landroid/widget/LinearLayout$LayoutParams;

    const/4 v8, 0x0

    invoke-direct {v7, v9, v9, v8}, Landroid/widget/LinearLayout$LayoutParams;-><init>(IIF)V

    invoke-virtual {v6, v7}, Landroid/widget/LinearLayout;->setLayoutParams(Landroid/view/ViewGroup$LayoutParams;)V

    .line 263
    iget-object v6, p0, Lorg/apache/cordova/CordovaActivity;->appView:Lorg/apache/cordova/CordovaWebView;

    const/16 v7, 0x64

    invoke-virtual {v6, v7}, Lorg/apache/cordova/CordovaWebView;->setId(I)V

    .line 264
    iget-object v6, p0, Lorg/apache/cordova/CordovaActivity;->appView:Lorg/apache/cordova/CordovaWebView;

    new-instance v7, Landroid/widget/LinearLayout$LayoutParams;

    const/high16 v8, 0x3f800000    # 1.0f

    invoke-direct {v7, v9, v9, v8}, Landroid/widget/LinearLayout$LayoutParams;-><init>(IIF)V

    invoke-virtual {v6, v7}, Lorg/apache/cordova/CordovaWebView;->setLayoutParams(Landroid/view/ViewGroup$LayoutParams;)V

    .line 270
    iget-object v6, p0, Lorg/apache/cordova/CordovaActivity;->appView:Lorg/apache/cordova/CordovaWebView;

    const/4 v7, 0x4

    invoke-virtual {v6, v7}, Lorg/apache/cordova/CordovaWebView;->setVisibility(I)V

    .line 273
    iget-object v6, p0, Lorg/apache/cordova/CordovaActivity;->appView:Lorg/apache/cordova/CordovaWebView;

    invoke-virtual {v6}, Lorg/apache/cordova/CordovaWebView;->getParent()Landroid/view/ViewParent;

    move-result-object v3

    .line 274
    .local v3, "parent":Landroid/view/ViewParent;
    if-eqz v3, :cond_0

    iget-object v6, p0, Lorg/apache/cordova/CordovaActivity;->root:Landroid/widget/LinearLayout;

    if-eq v3, v6, :cond_0

    .line 275
    sget-object v6, Lorg/apache/cordova/CordovaActivity;->TAG:Ljava/lang/String;

    const-string v7, "removing appView from existing parent"

    invoke-static {v6, v7}, Lorg/apache/cordova/LOG;->d(Ljava/lang/String;Ljava/lang/String;)V

    move-object v4, v3

    .line 276
    check-cast v4, Landroid/view/ViewGroup;

    .line 277
    .local v4, "parentGroup":Landroid/view/ViewGroup;
    iget-object v6, p0, Lorg/apache/cordova/CordovaActivity;->appView:Lorg/apache/cordova/CordovaWebView;

    invoke-virtual {v4, v6}, Landroid/view/ViewGroup;->removeView(Landroid/view/View;)V

    .line 279
    .end local v4    # "parentGroup":Landroid/view/ViewGroup;
    :cond_0
    iget-object v6, p0, Lorg/apache/cordova/CordovaActivity;->root:Landroid/widget/LinearLayout;

    iget-object v7, p0, Lorg/apache/cordova/CordovaActivity;->appView:Lorg/apache/cordova/CordovaWebView;

    invoke-virtual {v6, v7}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;)V

    .line 280
    iget-object v6, p0, Lorg/apache/cordova/CordovaActivity;->root:Landroid/widget/LinearLayout;

    invoke-virtual {p0, v6}, Lorg/apache/cordova/CordovaActivity;->setContentView(Landroid/view/View;)V

    .line 282
    iget-object v6, p0, Lorg/apache/cordova/CordovaActivity;->preferences:Lorg/apache/cordova/CordovaPreferences;

    const-string v7, "BackgroundColor"

    const/high16 v8, -0x1000000

    invoke-virtual {v6, v7, v8}, Lorg/apache/cordova/CordovaPreferences;->getInteger(Ljava/lang/String;I)I

    move-result v0

    .line 283
    .local v0, "backgroundColor":I
    iget-object v6, p0, Lorg/apache/cordova/CordovaActivity;->root:Landroid/widget/LinearLayout;

    invoke-virtual {v6, v0}, Landroid/widget/LinearLayout;->setBackgroundColor(I)V

    .line 284
    return-void
.end method

.method public displayError(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)V
    .locals 7
    .param p1, "title"    # Ljava/lang/String;
    .param p2, "message"    # Ljava/lang/String;
    .param p3, "button"    # Ljava/lang/String;
    .param p4, "exit"    # Z

    .prologue
    .line 834
    move-object v2, p0

    .line 835
    .local v2, "me":Lorg/apache/cordova/CordovaActivity;
    new-instance v0, Lorg/apache/cordova/CordovaActivity$4;

    move-object v1, p0

    move-object v3, p2

    move-object v4, p1

    move-object v5, p3

    move v6, p4

    invoke-direct/range {v0 .. v6}, Lorg/apache/cordova/CordovaActivity$4;-><init>(Lorg/apache/cordova/CordovaActivity;Lorg/apache/cordova/CordovaActivity;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)V

    invoke-virtual {v2, v0}, Lorg/apache/cordova/CordovaActivity;->runOnUiThread(Ljava/lang/Runnable;)V

    .line 858
    return-void
.end method

.method public endActivity()V
    .locals 1

    .prologue
    .line 725
    sget v0, Lorg/apache/cordova/CordovaActivity;->ACTIVITY_EXITING:I

    iput v0, p0, Lorg/apache/cordova/CordovaActivity;->activityState:I

    .line 726
    invoke-super {p0}, Landroid/app/Activity;->finish()V

    .line 727
    return-void
.end method

.method public getActivity()Landroid/app/Activity;
    .locals 0

    .prologue
    .line 290
    return-object p0
.end method

.method public getAuthenticationToken(Ljava/lang/String;Ljava/lang/String;)Lorg/apache/cordova/AuthenticationToken;
    .locals 1
    .param p1, "host"    # Ljava/lang/String;
    .param p2, "realm"    # Ljava/lang/String;

    .prologue
    .line 183
    iget-object v0, p0, Lorg/apache/cordova/CordovaActivity;->appView:Lorg/apache/cordova/CordovaWebView;

    if-eqz v0, :cond_0

    iget-object v0, p0, Lorg/apache/cordova/CordovaActivity;->appView:Lorg/apache/cordova/CordovaWebView;

    iget-object v0, v0, Lorg/apache/cordova/CordovaWebView;->viewClient:Lorg/apache/cordova/CordovaWebViewClient;

    if-eqz v0, :cond_0

    .line 184
    iget-object v0, p0, Lorg/apache/cordova/CordovaActivity;->appView:Lorg/apache/cordova/CordovaWebView;

    iget-object v0, v0, Lorg/apache/cordova/CordovaWebView;->viewClient:Lorg/apache/cordova/CordovaWebViewClient;

    invoke-virtual {v0, p1, p2}, Lorg/apache/cordova/CordovaWebViewClient;->getAuthenticationToken(Ljava/lang/String;Ljava/lang/String;)Lorg/apache/cordova/AuthenticationToken;

    move-result-object v0

    .line 186
    :goto_0
    return-object v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public getBooleanProperty(Ljava/lang/String;Z)Z
    .locals 1
    .param p1, "name"    # Ljava/lang/String;
    .param p2, "defaultValue"    # Z
    .annotation runtime Ljava/lang/Deprecated;
    .end annotation

    .prologue
    .line 480
    iget-object v0, p0, Lorg/apache/cordova/CordovaActivity;->preferences:Lorg/apache/cordova/CordovaPreferences;

    invoke-virtual {v0, p1, p2}, Lorg/apache/cordova/CordovaPreferences;->getBoolean(Ljava/lang/String;Z)Z

    move-result v0

    return v0
.end method

.method public getContext()Landroid/content/Context;
    .locals 2
    .annotation runtime Ljava/lang/Deprecated;
    .end annotation

    .prologue
    .line 894
    sget-object v0, Lorg/apache/cordova/CordovaActivity;->TAG:Ljava/lang/String;

    const-string v1, "This will be deprecated December 2012"

    invoke-static {v0, v1}, Lorg/apache/cordova/LOG;->d(Ljava/lang/String;Ljava/lang/String;)V

    .line 895
    return-object p0
.end method

.method public getDoubleProperty(Ljava/lang/String;D)D
    .locals 2
    .param p1, "name"    # Ljava/lang/String;
    .param p2, "defaultValue"    # D
    .annotation runtime Ljava/lang/Deprecated;
    .end annotation

    .prologue
    .line 504
    iget-object v0, p0, Lorg/apache/cordova/CordovaActivity;->preferences:Lorg/apache/cordova/CordovaPreferences;

    invoke-virtual {v0, p1, p2, p3}, Lorg/apache/cordova/CordovaPreferences;->getDouble(Ljava/lang/String;D)D

    move-result-wide v0

    return-wide v0
.end method

.method public getIntegerProperty(Ljava/lang/String;I)I
    .locals 1
    .param p1, "name"    # Ljava/lang/String;
    .param p2, "defaultValue"    # I
    .annotation runtime Ljava/lang/Deprecated;
    .end annotation

    .prologue
    .line 488
    iget-object v0, p0, Lorg/apache/cordova/CordovaActivity;->preferences:Lorg/apache/cordova/CordovaPreferences;

    invoke-virtual {v0, p1, p2}, Lorg/apache/cordova/CordovaPreferences;->getInteger(Ljava/lang/String;I)I

    move-result v0

    return v0
.end method

.method public getStringProperty(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    .locals 1
    .param p1, "name"    # Ljava/lang/String;
    .param p2, "defaultValue"    # Ljava/lang/String;
    .annotation runtime Ljava/lang/Deprecated;
    .end annotation

    .prologue
    .line 496
    iget-object v0, p0, Lorg/apache/cordova/CordovaActivity;->preferences:Lorg/apache/cordova/CordovaPreferences;

    invoke-virtual {v0, p1, p2}, Lorg/apache/cordova/CordovaPreferences;->getString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method

.method public getThreadPool()Ljava/util/concurrent/ExecutorService;
    .locals 1

    .prologue
    .line 1051
    iget-object v0, p0, Lorg/apache/cordova/CordovaActivity;->threadPool:Ljava/util/concurrent/ExecutorService;

    return-object v0
.end method

.method public init()V
    .locals 2

    .prologue
    const/4 v1, 0x0

    .line 328
    iget-object v0, p0, Lorg/apache/cordova/CordovaActivity;->appView:Lorg/apache/cordova/CordovaWebView;

    invoke-virtual {p0, v0, v1, v1}, Lorg/apache/cordova/CordovaActivity;->init(Lorg/apache/cordova/CordovaWebView;Lorg/apache/cordova/CordovaWebViewClient;Lorg/apache/cordova/CordovaChromeClient;)V

    .line 329
    return-void
.end method

.method public init(Lorg/apache/cordova/CordovaWebView;Lorg/apache/cordova/CordovaWebViewClient;Lorg/apache/cordova/CordovaChromeClient;)V
    .locals 8
    .param p1, "webView"    # Lorg/apache/cordova/CordovaWebView;
    .param p2, "webViewClient"    # Lorg/apache/cordova/CordovaWebViewClient;
    .param p3, "webChromeClient"    # Lorg/apache/cordova/CordovaChromeClient;
    .annotation build Landroid/annotation/SuppressLint;
        value = {
            "NewApi"
        }
    .end annotation

    .annotation runtime Ljava/lang/Deprecated;
    .end annotation

    .prologue
    .line 334
    sget-object v0, Lorg/apache/cordova/CordovaActivity;->TAG:Ljava/lang/String;

    const-string v1, "CordovaActivity.init()"

    invoke-static {v0, v1}, Lorg/apache/cordova/LOG;->d(Ljava/lang/String;Ljava/lang/String;)V

    .line 336
    if-eqz p1, :cond_2

    .end local p1    # "webView":Lorg/apache/cordova/CordovaWebView;
    :goto_0
    iput-object p1, p0, Lorg/apache/cordova/CordovaActivity;->appView:Lorg/apache/cordova/CordovaWebView;

    .line 337
    iget-object v0, p0, Lorg/apache/cordova/CordovaActivity;->appView:Lorg/apache/cordova/CordovaWebView;

    iget-object v0, v0, Lorg/apache/cordova/CordovaWebView;->pluginManager:Lorg/apache/cordova/PluginManager;

    if-nez v0, :cond_0

    .line 338
    iget-object v0, p0, Lorg/apache/cordova/CordovaActivity;->appView:Lorg/apache/cordova/CordovaWebView;

    if-eqz p2, :cond_3

    move-object v2, p2

    :goto_1
    if-eqz p3, :cond_4

    move-object v3, p3

    :goto_2
    iget-object v4, p0, Lorg/apache/cordova/CordovaActivity;->pluginEntries:Ljava/util/ArrayList;

    iget-object v5, p0, Lorg/apache/cordova/CordovaActivity;->internalWhitelist:Lorg/apache/cordova/Whitelist;

    iget-object v6, p0, Lorg/apache/cordova/CordovaActivity;->externalWhitelist:Lorg/apache/cordova/Whitelist;

    iget-object v7, p0, Lorg/apache/cordova/CordovaActivity;->preferences:Lorg/apache/cordova/CordovaPreferences;

    move-object v1, p0

    invoke-virtual/range {v0 .. v7}, Lorg/apache/cordova/CordovaWebView;->init(Lorg/apache/cordova/CordovaInterface;Lorg/apache/cordova/CordovaWebViewClient;Lorg/apache/cordova/CordovaChromeClient;Ljava/util/List;Lorg/apache/cordova/Whitelist;Lorg/apache/cordova/Whitelist;Lorg/apache/cordova/CordovaPreferences;)V

    .line 344
    :cond_0
    iget-object v0, p0, Lorg/apache/cordova/CordovaActivity;->preferences:Lorg/apache/cordova/CordovaPreferences;

    const-string v1, "DisallowOverscroll"

    const/4 v2, 0x0

    invoke-virtual {v0, v1, v2}, Lorg/apache/cordova/CordovaPreferences;->getBoolean(Ljava/lang/String;Z)Z

    move-result v0

    if-eqz v0, :cond_1

    .line 345
    iget-object v0, p0, Lorg/apache/cordova/CordovaActivity;->appView:Lorg/apache/cordova/CordovaWebView;

    const/4 v1, 0x2

    invoke-virtual {v0, v1}, Lorg/apache/cordova/CordovaWebView;->setOverScrollMode(I)V

    .line 347
    :cond_1
    invoke-virtual {p0}, Lorg/apache/cordova/CordovaActivity;->createViews()V

    .line 351
    const/4 v0, 0x3

    invoke-virtual {p0, v0}, Lorg/apache/cordova/CordovaActivity;->setVolumeControlStream(I)V

    .line 352
    return-void

    .line 336
    .restart local p1    # "webView":Lorg/apache/cordova/CordovaWebView;
    :cond_2
    invoke-virtual {p0}, Lorg/apache/cordova/CordovaActivity;->makeWebView()Lorg/apache/cordova/CordovaWebView;

    move-result-object p1

    goto :goto_0

    .line 338
    .end local p1    # "webView":Lorg/apache/cordova/CordovaWebView;
    :cond_3
    iget-object v1, p0, Lorg/apache/cordova/CordovaActivity;->appView:Lorg/apache/cordova/CordovaWebView;

    invoke-virtual {p0, v1}, Lorg/apache/cordova/CordovaActivity;->makeWebViewClient(Lorg/apache/cordova/CordovaWebView;)Lorg/apache/cordova/CordovaWebViewClient;

    move-result-object v2

    goto :goto_1

    :cond_4
    iget-object v1, p0, Lorg/apache/cordova/CordovaActivity;->appView:Lorg/apache/cordova/CordovaWebView;

    invoke-virtual {p0, v1}, Lorg/apache/cordova/CordovaActivity;->makeChromeClient(Lorg/apache/cordova/CordovaWebView;)Lorg/apache/cordova/CordovaChromeClient;

    move-result-object v3

    goto :goto_2
.end method

.method public isUrlWhiteListed(Ljava/lang/String;)Z
    .locals 1
    .param p1, "url"    # Ljava/lang/String;
    .annotation runtime Ljava/lang/Deprecated;
    .end annotation

    .prologue
    .line 865
    iget-object v0, p0, Lorg/apache/cordova/CordovaActivity;->internalWhitelist:Lorg/apache/cordova/Whitelist;

    invoke-virtual {v0, p1}, Lorg/apache/cordova/Whitelist;->isUrlWhiteListed(Ljava/lang/String;)Z

    move-result v0

    return v0
.end method

.method protected loadConfig()V
    .locals 3

    .prologue
    .line 236
    new-instance v0, Lorg/apache/cordova/ConfigXmlParser;

    invoke-direct {v0}, Lorg/apache/cordova/ConfigXmlParser;-><init>()V

    .line 237
    .local v0, "parser":Lorg/apache/cordova/ConfigXmlParser;
    invoke-virtual {v0, p0}, Lorg/apache/cordova/ConfigXmlParser;->parse(Landroid/app/Activity;)V

    .line 238
    invoke-virtual {v0}, Lorg/apache/cordova/ConfigXmlParser;->getPreferences()Lorg/apache/cordova/CordovaPreferences;

    move-result-object v1

    iput-object v1, p0, Lorg/apache/cordova/CordovaActivity;->preferences:Lorg/apache/cordova/CordovaPreferences;

    .line 239
    iget-object v1, p0, Lorg/apache/cordova/CordovaActivity;->preferences:Lorg/apache/cordova/CordovaPreferences;

    invoke-virtual {p0}, Lorg/apache/cordova/CordovaActivity;->getIntent()Landroid/content/Intent;

    move-result-object v2

    invoke-virtual {v2}, Landroid/content/Intent;->getExtras()Landroid/os/Bundle;

    move-result-object v2

    invoke-virtual {v1, v2}, Lorg/apache/cordova/CordovaPreferences;->setPreferencesBundle(Landroid/os/Bundle;)V

    .line 240
    iget-object v1, p0, Lorg/apache/cordova/CordovaActivity;->preferences:Lorg/apache/cordova/CordovaPreferences;

    invoke-virtual {v1, p0}, Lorg/apache/cordova/CordovaPreferences;->copyIntoIntentExtras(Landroid/app/Activity;)V

    .line 241
    invoke-virtual {v0}, Lorg/apache/cordova/ConfigXmlParser;->getInternalWhitelist()Lorg/apache/cordova/Whitelist;

    move-result-object v1

    iput-object v1, p0, Lorg/apache/cordova/CordovaActivity;->internalWhitelist:Lorg/apache/cordova/Whitelist;

    .line 242
    invoke-virtual {v0}, Lorg/apache/cordova/ConfigXmlParser;->getExternalWhitelist()Lorg/apache/cordova/Whitelist;

    move-result-object v1

    iput-object v1, p0, Lorg/apache/cordova/CordovaActivity;->externalWhitelist:Lorg/apache/cordova/Whitelist;

    .line 243
    invoke-virtual {v0}, Lorg/apache/cordova/ConfigXmlParser;->getLaunchUrl()Ljava/lang/String;

    move-result-object v1

    iput-object v1, p0, Lorg/apache/cordova/CordovaActivity;->launchUrl:Ljava/lang/String;

    .line 244
    invoke-virtual {v0}, Lorg/apache/cordova/ConfigXmlParser;->getPluginEntries()Ljava/util/ArrayList;

    move-result-object v1

    iput-object v1, p0, Lorg/apache/cordova/CordovaActivity;->pluginEntries:Ljava/util/ArrayList;

    .line 245
    sput-object v0, Lorg/apache/cordova/Config;->parser:Lorg/apache/cordova/ConfigXmlParser;

    .line 246
    return-void
.end method

.method loadSpinner()V
    .locals 7

    .prologue
    const/4 v6, 0x0

    .line 412
    const/4 v1, 0x0

    .line 413
    .local v1, "loading":Ljava/lang/String;
    iget-object v4, p0, Lorg/apache/cordova/CordovaActivity;->appView:Lorg/apache/cordova/CordovaWebView;

    if-eqz v4, :cond_0

    iget-object v4, p0, Lorg/apache/cordova/CordovaActivity;->appView:Lorg/apache/cordova/CordovaWebView;

    invoke-virtual {v4}, Lorg/apache/cordova/CordovaWebView;->canGoBack()Z

    move-result v4

    if-nez v4, :cond_3

    .line 414
    :cond_0
    iget-object v4, p0, Lorg/apache/cordova/CordovaActivity;->preferences:Lorg/apache/cordova/CordovaPreferences;

    const-string v5, "LoadingDialog"

    invoke-virtual {v4, v5, v6}, Lorg/apache/cordova/CordovaPreferences;->getString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    .line 419
    :goto_0
    if-eqz v1, :cond_2

    .line 421
    const-string v3, ""

    .line 422
    .local v3, "title":Ljava/lang/String;
    const-string v2, "Loading Application..."

    .line 424
    .local v2, "message":Ljava/lang/String;
    invoke-virtual {v1}, Ljava/lang/String;->length()I

    move-result v4

    if-lez v4, :cond_1

    .line 425
    const/16 v4, 0x2c

    invoke-virtual {v1, v4}, Ljava/lang/String;->indexOf(I)I

    move-result v0

    .line 426
    .local v0, "comma":I
    if-lez v0, :cond_4

    .line 427
    const/4 v4, 0x0

    invoke-virtual {v1, v4, v0}, Ljava/lang/String;->substring(II)Ljava/lang/String;

    move-result-object v3

    .line 428
    add-int/lit8 v4, v0, 0x1

    invoke-virtual {v1, v4}, Ljava/lang/String;->substring(I)Ljava/lang/String;

    move-result-object v2

    .line 435
    .end local v0    # "comma":I
    :cond_1
    :goto_1
    invoke-virtual {p0, v3, v2}, Lorg/apache/cordova/CordovaActivity;->spinnerStart(Ljava/lang/String;Ljava/lang/String;)V

    .line 437
    .end local v2    # "message":Ljava/lang/String;
    .end local v3    # "title":Ljava/lang/String;
    :cond_2
    return-void

    .line 417
    :cond_3
    iget-object v4, p0, Lorg/apache/cordova/CordovaActivity;->preferences:Lorg/apache/cordova/CordovaPreferences;

    const-string v5, "LoadingPageDialog"

    invoke-virtual {v4, v5, v6}, Lorg/apache/cordova/CordovaPreferences;->getString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    goto :goto_0

    .line 431
    .restart local v0    # "comma":I
    .restart local v2    # "message":Ljava/lang/String;
    .restart local v3    # "title":Ljava/lang/String;
    :cond_4
    const-string v3, ""

    .line 432
    move-object v2, v1

    goto :goto_1
.end method

.method public loadUrl(Ljava/lang/String;)V
    .locals 4
    .param p1, "url"    # Ljava/lang/String;

    .prologue
    .line 358
    iget-object v1, p0, Lorg/apache/cordova/CordovaActivity;->appView:Lorg/apache/cordova/CordovaWebView;

    if-nez v1, :cond_0

    .line 359
    invoke-virtual {p0}, Lorg/apache/cordova/CordovaActivity;->init()V

    .line 361
    :cond_0
    iget-object v1, p0, Lorg/apache/cordova/CordovaActivity;->preferences:Lorg/apache/cordova/CordovaPreferences;

    const-string v2, "SplashScreenDelay"

    iget v3, p0, Lorg/apache/cordova/CordovaActivity;->splashscreenTime:I

    invoke-virtual {v1, v2, v3}, Lorg/apache/cordova/CordovaPreferences;->getInteger(Ljava/lang/String;I)I

    move-result v1

    iput v1, p0, Lorg/apache/cordova/CordovaActivity;->splashscreenTime:I

    .line 362
    iget-object v1, p0, Lorg/apache/cordova/CordovaActivity;->preferences:Lorg/apache/cordova/CordovaPreferences;

    const-string v2, "SplashScreen"

    const/4 v3, 0x0

    invoke-virtual {v1, v2, v3}, Lorg/apache/cordova/CordovaPreferences;->getString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    .line 363
    .local v0, "splash":Ljava/lang/String;
    iget v1, p0, Lorg/apache/cordova/CordovaActivity;->splashscreenTime:I

    if-lez v1, :cond_1

    if-eqz v0, :cond_1

    .line 365
    invoke-virtual {p0}, Lorg/apache/cordova/CordovaActivity;->getResources()Landroid/content/res/Resources;

    move-result-object v1

    const-string v2, "drawable"

    invoke-virtual {p0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/Class;->getPackage()Ljava/lang/Package;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/Package;->getName()Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v1, v0, v2, v3}, Landroid/content/res/Resources;->getIdentifier(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I

    move-result v1

    iput v1, p0, Lorg/apache/cordova/CordovaActivity;->splashscreen:I

    .line 366
    iget v1, p0, Lorg/apache/cordova/CordovaActivity;->splashscreen:I

    if-eqz v1, :cond_1

    .line 368
    iget v1, p0, Lorg/apache/cordova/CordovaActivity;->splashscreenTime:I

    invoke-virtual {p0, v1}, Lorg/apache/cordova/CordovaActivity;->showSplashScreen(I)V

    .line 373
    :cond_1
    iget-object v1, p0, Lorg/apache/cordova/CordovaActivity;->preferences:Lorg/apache/cordova/CordovaPreferences;

    const-string v2, "KeepRunning"

    const/4 v3, 0x1

    invoke-virtual {v1, v2, v3}, Lorg/apache/cordova/CordovaPreferences;->getBoolean(Ljava/lang/String;Z)Z

    move-result v1

    iput-boolean v1, p0, Lorg/apache/cordova/CordovaActivity;->keepRunning:Z

    .line 376
    iget-object v1, p0, Lorg/apache/cordova/CordovaActivity;->appView:Lorg/apache/cordova/CordovaWebView;

    invoke-virtual {v1}, Lorg/apache/cordova/CordovaWebView;->getParent()Landroid/view/ViewParent;

    move-result-object v1

    if-eqz v1, :cond_2

    .line 379
    invoke-virtual {p0}, Lorg/apache/cordova/CordovaActivity;->loadSpinner()V

    .line 383
    :cond_2
    iget v1, p0, Lorg/apache/cordova/CordovaActivity;->splashscreen:I

    if-eqz v1, :cond_3

    .line 385
    iget-object v1, p0, Lorg/apache/cordova/CordovaActivity;->appView:Lorg/apache/cordova/CordovaWebView;

    iget v2, p0, Lorg/apache/cordova/CordovaActivity;->splashscreenTime:I

    invoke-virtual {v1, p1, v2}, Lorg/apache/cordova/CordovaWebView;->loadUrl(Ljava/lang/String;I)V

    .line 391
    :goto_0
    return-void

    .line 389
    :cond_3
    iget-object v1, p0, Lorg/apache/cordova/CordovaActivity;->appView:Lorg/apache/cordova/CordovaWebView;

    invoke-virtual {v1, p1}, Lorg/apache/cordova/CordovaWebView;->loadUrl(Ljava/lang/String;)V

    goto :goto_0
.end method

.method public loadUrl(Ljava/lang/String;I)V
    .locals 0
    .param p1, "url"    # Ljava/lang/String;
    .param p2, "time"    # I

    .prologue
    .line 402
    iput p2, p0, Lorg/apache/cordova/CordovaActivity;->splashscreenTime:I

    .line 403
    invoke-virtual {p0, p1}, Lorg/apache/cordova/CordovaActivity;->loadUrl(Ljava/lang/String;)V

    .line 404
    return-void
.end method

.method protected makeChromeClient(Lorg/apache/cordova/CordovaWebView;)Lorg/apache/cordova/CordovaChromeClient;
    .locals 1
    .param p1, "webView"    # Lorg/apache/cordova/CordovaWebView;

    .prologue
    .line 324
    invoke-virtual {p1, p0}, Lorg/apache/cordova/CordovaWebView;->makeWebChromeClient(Lorg/apache/cordova/CordovaInterface;)Lorg/apache/cordova/CordovaChromeClient;

    move-result-object v0

    return-object v0
.end method

.method protected makeWebView()Lorg/apache/cordova/CordovaWebView;
    .locals 1

    .prologue
    .line 300
    new-instance v0, Lorg/apache/cordova/CordovaWebView;

    invoke-direct {v0, p0}, Lorg/apache/cordova/CordovaWebView;-><init>(Landroid/content/Context;)V

    return-object v0
.end method

.method protected makeWebViewClient(Lorg/apache/cordova/CordovaWebView;)Lorg/apache/cordova/CordovaWebViewClient;
    .locals 1
    .param p1, "webView"    # Lorg/apache/cordova/CordovaWebView;

    .prologue
    .line 312
    invoke-virtual {p1, p0}, Lorg/apache/cordova/CordovaWebView;->makeWebViewClient(Lorg/apache/cordova/CordovaInterface;)Lorg/apache/cordova/CordovaWebViewClient;

    move-result-object v0

    return-object v0
.end method

.method protected onActivityResult(IILandroid/content/Intent;)V
    .locals 6
    .param p1, "requestCode"    # I
    .param p2, "resultCode"    # I
    .param p3, "intent"    # Landroid/content/Intent;

    .prologue
    .line 762
    sget-object v3, Lorg/apache/cordova/CordovaActivity;->TAG:Ljava/lang/String;

    const-string v4, "Incoming Result"

    invoke-static {v3, v4}, Lorg/apache/cordova/LOG;->d(Ljava/lang/String;Ljava/lang/String;)V

    .line 763
    invoke-super {p0, p1, p2, p3}, Landroid/app/Activity;->onActivityResult(IILandroid/content/Intent;)V

    .line 764
    sget-object v3, Lorg/apache/cordova/CordovaActivity;->TAG:Ljava/lang/String;

    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    const-string v5, "Request code = "

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4, p1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    invoke-static {v3, v4}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 765
    iget-object v3, p0, Lorg/apache/cordova/CordovaActivity;->appView:Lorg/apache/cordova/CordovaWebView;

    if-eqz v3, :cond_3

    const/16 v3, 0x1435

    if-ne p1, v3, :cond_3

    .line 766
    iget-object v3, p0, Lorg/apache/cordova/CordovaActivity;->appView:Lorg/apache/cordova/CordovaWebView;

    invoke-virtual {v3}, Lorg/apache/cordova/CordovaWebView;->getWebChromeClient()Lorg/apache/cordova/CordovaChromeClient;

    move-result-object v3

    invoke-virtual {v3}, Lorg/apache/cordova/CordovaChromeClient;->getValueCallback()Landroid/webkit/ValueCallback;

    move-result-object v1

    .line 767
    .local v1, "mUploadMessage":Landroid/webkit/ValueCallback;, "Landroid/webkit/ValueCallback<Landroid/net/Uri;>;"
    sget-object v3, Lorg/apache/cordova/CordovaActivity;->TAG:Ljava/lang/String;

    const-string v4, "did we get here?"

    invoke-static {v3, v4}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 768
    if-nez v1, :cond_1

    .line 786
    .end local v1    # "mUploadMessage":Landroid/webkit/ValueCallback;, "Landroid/webkit/ValueCallback<Landroid/net/Uri;>;"
    :cond_0
    :goto_0
    return-void

    .line 770
    .restart local v1    # "mUploadMessage":Landroid/webkit/ValueCallback;, "Landroid/webkit/ValueCallback<Landroid/net/Uri;>;"
    :cond_1
    if-eqz p3, :cond_2

    const/4 v3, -0x1

    if-eq p2, v3, :cond_5

    :cond_2
    const/4 v2, 0x0

    .line 771
    .local v2, "result":Landroid/net/Uri;
    :goto_1
    sget-object v3, Lorg/apache/cordova/CordovaActivity;->TAG:Ljava/lang/String;

    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    const-string v5, "result = "

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    invoke-static {v3, v4}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 772
    invoke-interface {v1, v2}, Landroid/webkit/ValueCallback;->onReceiveValue(Ljava/lang/Object;)V

    .line 775
    .end local v1    # "mUploadMessage":Landroid/webkit/ValueCallback;, "Landroid/webkit/ValueCallback<Landroid/net/Uri;>;"
    .end local v2    # "result":Landroid/net/Uri;
    :cond_3
    iget-object v0, p0, Lorg/apache/cordova/CordovaActivity;->activityResultCallback:Lorg/apache/cordova/CordovaPlugin;

    .line 776
    .local v0, "callback":Lorg/apache/cordova/CordovaPlugin;
    if-nez v0, :cond_4

    iget-object v3, p0, Lorg/apache/cordova/CordovaActivity;->initCallbackClass:Ljava/lang/String;

    if-eqz v3, :cond_4

    .line 779
    iget-object v3, p0, Lorg/apache/cordova/CordovaActivity;->appView:Lorg/apache/cordova/CordovaWebView;

    iget-object v3, v3, Lorg/apache/cordova/CordovaWebView;->pluginManager:Lorg/apache/cordova/PluginManager;

    iget-object v4, p0, Lorg/apache/cordova/CordovaActivity;->initCallbackClass:Ljava/lang/String;

    invoke-virtual {v3, v4}, Lorg/apache/cordova/PluginManager;->getPlugin(Ljava/lang/String;)Lorg/apache/cordova/CordovaPlugin;

    move-result-object v3

    iput-object v3, p0, Lorg/apache/cordova/CordovaActivity;->activityResultCallback:Lorg/apache/cordova/CordovaPlugin;

    .line 780
    iget-object v0, p0, Lorg/apache/cordova/CordovaActivity;->activityResultCallback:Lorg/apache/cordova/CordovaPlugin;

    .line 782
    :cond_4
    if-eqz v0, :cond_0

    .line 783
    sget-object v3, Lorg/apache/cordova/CordovaActivity;->TAG:Ljava/lang/String;

    const-string v4, "We have a callback to send this result to"

    invoke-static {v3, v4}, Lorg/apache/cordova/LOG;->d(Ljava/lang/String;Ljava/lang/String;)V

    .line 784
    invoke-virtual {v0, p1, p2, p3}, Lorg/apache/cordova/CordovaPlugin;->onActivityResult(IILandroid/content/Intent;)V

    goto :goto_0

    .line 770
    .end local v0    # "callback":Lorg/apache/cordova/CordovaPlugin;
    .restart local v1    # "mUploadMessage":Landroid/webkit/ValueCallback;, "Landroid/webkit/ValueCallback<Landroid/net/Uri;>;"
    :cond_5
    invoke-virtual {p3}, Landroid/content/Intent;->getData()Landroid/net/Uri;

    move-result-object v2

    goto :goto_1
.end method

.method public onCreate(Landroid/os/Bundle;)V
    .locals 5
    .param p1, "savedInstanceState"    # Landroid/os/Bundle;

    .prologue
    const/16 v4, 0x800

    const/4 v3, 0x0

    const/16 v2, 0x400

    .line 203
    sget-object v0, Lorg/apache/cordova/CordovaActivity;->TAG:Ljava/lang/String;

    const-string v1, "Apache Cordova native platform version 3.6.4 is starting"

    invoke-static {v0, v1}, Lorg/apache/cordova/LOG;->i(Ljava/lang/String;Ljava/lang/String;)V

    .line 204
    sget-object v0, Lorg/apache/cordova/CordovaActivity;->TAG:Ljava/lang/String;

    const-string v1, "CordovaActivity.onCreate()"

    invoke-static {v0, v1}, Lorg/apache/cordova/LOG;->d(Ljava/lang/String;Ljava/lang/String;)V

    .line 207
    invoke-virtual {p0}, Lorg/apache/cordova/CordovaActivity;->loadConfig()V

    .line 208
    iget-object v0, p0, Lorg/apache/cordova/CordovaActivity;->preferences:Lorg/apache/cordova/CordovaPreferences;

    const-string v1, "ShowTitle"

    invoke-virtual {v0, v1, v3}, Lorg/apache/cordova/CordovaPreferences;->getBoolean(Ljava/lang/String;Z)Z

    move-result v0

    if-nez v0, :cond_0

    .line 210
    invoke-virtual {p0}, Lorg/apache/cordova/CordovaActivity;->getWindow()Landroid/view/Window;

    move-result-object v0

    const/4 v1, 0x1

    invoke-virtual {v0, v1}, Landroid/view/Window;->requestFeature(I)Z

    .line 213
    :cond_0
    iget-object v0, p0, Lorg/apache/cordova/CordovaActivity;->preferences:Lorg/apache/cordova/CordovaPreferences;

    const-string v1, "SetFullscreen"

    invoke-virtual {v0, v1, v3}, Lorg/apache/cordova/CordovaPreferences;->getBoolean(Ljava/lang/String;Z)Z

    move-result v0

    if-eqz v0, :cond_2

    .line 215
    sget-object v0, Lorg/apache/cordova/CordovaActivity;->TAG:Ljava/lang/String;

    const-string v1, "The SetFullscreen configuration is deprecated in favor of Fullscreen, and will be removed in a future version."

    invoke-static {v0, v1}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 216
    invoke-virtual {p0}, Lorg/apache/cordova/CordovaActivity;->getWindow()Landroid/view/Window;

    move-result-object v0

    invoke-virtual {v0, v2, v2}, Landroid/view/Window;->setFlags(II)V

    .line 226
    :goto_0
    invoke-super {p0, p1}, Landroid/app/Activity;->onCreate(Landroid/os/Bundle;)V

    .line 228
    if-eqz p1, :cond_1

    .line 230
    const-string v0, "callbackClass"

    invoke-virtual {p1, v0}, Landroid/os/Bundle;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Lorg/apache/cordova/CordovaActivity;->initCallbackClass:Ljava/lang/String;

    .line 232
    :cond_1
    return-void

    .line 218
    :cond_2
    iget-object v0, p0, Lorg/apache/cordova/CordovaActivity;->preferences:Lorg/apache/cordova/CordovaPreferences;

    const-string v1, "Fullscreen"

    invoke-virtual {v0, v1, v3}, Lorg/apache/cordova/CordovaPreferences;->getBoolean(Ljava/lang/String;Z)Z

    move-result v0

    if-eqz v0, :cond_3

    .line 219
    invoke-virtual {p0}, Lorg/apache/cordova/CordovaActivity;->getWindow()Landroid/view/Window;

    move-result-object v0

    invoke-virtual {v0, v2, v2}, Landroid/view/Window;->setFlags(II)V

    goto :goto_0

    .line 222
    :cond_3
    invoke-virtual {p0}, Lorg/apache/cordova/CordovaActivity;->getWindow()Landroid/view/Window;

    move-result-object v0

    invoke-virtual {v0, v4, v4}, Landroid/view/Window;->setFlags(II)V

    goto :goto_0
.end method

.method public onCreateOptionsMenu(Landroid/view/Menu;)Z
    .locals 1
    .param p1, "menu"    # Landroid/view/Menu;

    .prologue
    .line 873
    const-string v0, "onCreateOptionsMenu"

    invoke-virtual {p0, v0, p1}, Lorg/apache/cordova/CordovaActivity;->postMessage(Ljava/lang/String;Ljava/lang/Object;)V

    .line 874
    invoke-super {p0, p1}, Landroid/app/Activity;->onCreateOptionsMenu(Landroid/view/Menu;)Z

    move-result v0

    return v0
.end method

.method public onDestroy()V
    .locals 2

    .prologue
    .line 642
    sget-object v0, Lorg/apache/cordova/CordovaActivity;->TAG:Ljava/lang/String;

    const-string v1, "CordovaActivity.onDestroy()"

    invoke-static {v0, v1}, Lorg/apache/cordova/LOG;->d(Ljava/lang/String;Ljava/lang/String;)V

    .line 643
    invoke-super {p0}, Landroid/app/Activity;->onDestroy()V

    .line 646
    invoke-virtual {p0}, Lorg/apache/cordova/CordovaActivity;->removeSplashScreen()V

    .line 648
    iget-object v0, p0, Lorg/apache/cordova/CordovaActivity;->appView:Lorg/apache/cordova/CordovaWebView;

    if-eqz v0, :cond_0

    .line 649
    iget-object v0, p0, Lorg/apache/cordova/CordovaActivity;->appView:Lorg/apache/cordova/CordovaWebView;

    invoke-virtual {v0}, Lorg/apache/cordova/CordovaWebView;->handleDestroy()V

    .line 654
    :goto_0
    return-void

    .line 652
    :cond_0
    sget v0, Lorg/apache/cordova/CordovaActivity;->ACTIVITY_EXITING:I

    iput v0, p0, Lorg/apache/cordova/CordovaActivity;->activityState:I

    goto :goto_0
.end method

.method public onKeyDown(ILandroid/view/KeyEvent;)Z
    .locals 1
    .param p1, "keyCode"    # I
    .param p2, "event"    # Landroid/view/KeyEvent;

    .prologue
    .line 995
    iget-object v0, p0, Lorg/apache/cordova/CordovaActivity;->appView:Lorg/apache/cordova/CordovaWebView;

    if-eqz v0, :cond_1

    iget-object v0, p0, Lorg/apache/cordova/CordovaActivity;->appView:Lorg/apache/cordova/CordovaWebView;

    invoke-virtual {v0}, Lorg/apache/cordova/CordovaWebView;->getFocusedChild()Landroid/view/View;

    move-result-object v0

    if-eqz v0, :cond_1

    const/4 v0, 0x4

    if-eq p1, v0, :cond_0

    const/16 v0, 0x52

    if-ne p1, v0, :cond_1

    .line 996
    :cond_0
    iget-object v0, p0, Lorg/apache/cordova/CordovaActivity;->appView:Lorg/apache/cordova/CordovaWebView;

    invoke-virtual {v0, p1, p2}, Lorg/apache/cordova/CordovaWebView;->onKeyDown(ILandroid/view/KeyEvent;)Z

    move-result v0

    .line 999
    :goto_0
    return v0

    :cond_1
    invoke-super {p0, p1, p2}, Landroid/app/Activity;->onKeyDown(ILandroid/view/KeyEvent;)Z

    move-result v0

    goto :goto_0
.end method

.method public onKeyUp(ILandroid/view/KeyEvent;)Z
    .locals 1
    .param p1, "keyCode"    # I
    .param p2, "event"    # Landroid/view/KeyEvent;

    .prologue
    .line 976
    iget-object v0, p0, Lorg/apache/cordova/CordovaActivity;->appView:Lorg/apache/cordova/CordovaWebView;

    if-eqz v0, :cond_2

    iget-object v0, p0, Lorg/apache/cordova/CordovaActivity;->appView:Lorg/apache/cordova/CordovaWebView;

    invoke-virtual {v0}, Lorg/apache/cordova/CordovaWebView;->isCustomViewShowing()Z

    move-result v0

    if-nez v0, :cond_0

    iget-object v0, p0, Lorg/apache/cordova/CordovaActivity;->appView:Lorg/apache/cordova/CordovaWebView;

    invoke-virtual {v0}, Lorg/apache/cordova/CordovaWebView;->getFocusedChild()Landroid/view/View;

    move-result-object v0

    if-eqz v0, :cond_2

    :cond_0
    const/4 v0, 0x4

    if-eq p1, v0, :cond_1

    const/16 v0, 0x52

    if-ne p1, v0, :cond_2

    .line 978
    :cond_1
    iget-object v0, p0, Lorg/apache/cordova/CordovaActivity;->appView:Lorg/apache/cordova/CordovaWebView;

    invoke-virtual {v0, p1, p2}, Lorg/apache/cordova/CordovaWebView;->onKeyUp(ILandroid/view/KeyEvent;)Z

    move-result v0

    .line 980
    :goto_0
    return v0

    :cond_2
    invoke-super {p0, p1, p2}, Landroid/app/Activity;->onKeyUp(ILandroid/view/KeyEvent;)Z

    move-result v0

    goto :goto_0
.end method

.method public onMessage(Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 7
    .param p1, "id"    # Ljava/lang/String;
    .param p2, "data"    # Ljava/lang/Object;

    .prologue
    const/4 v6, 0x0

    .line 1011
    const-string v3, "onScrollChanged"

    invoke-virtual {v3, p1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v3

    if-nez v3, :cond_0

    .line 1012
    sget-object v3, Lorg/apache/cordova/CordovaActivity;->TAG:Ljava/lang/String;

    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    const-string v5, "onMessage("

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    const-string v5, ","

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    move-result-object v4

    const-string v5, ")"

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    invoke-static {v3, v4}, Lorg/apache/cordova/LOG;->d(Ljava/lang/String;Ljava/lang/String;)V

    .line 1015
    :cond_0
    const-string v3, "splashscreen"

    invoke-virtual {v3, p1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v3

    if-eqz v3, :cond_5

    .line 1016
    const-string v3, "hide"

    invoke-virtual {p2}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v3, v4}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v3

    if-eqz v3, :cond_2

    .line 1017
    invoke-virtual {p0}, Lorg/apache/cordova/CordovaActivity;->removeSplashScreen()V

    .line 1047
    :cond_1
    :goto_0
    return-object v6

    .line 1021
    :cond_2
    iget-object v3, p0, Lorg/apache/cordova/CordovaActivity;->splashDialog:Landroid/app/Dialog;

    if-eqz v3, :cond_3

    iget-object v3, p0, Lorg/apache/cordova/CordovaActivity;->splashDialog:Landroid/app/Dialog;

    invoke-virtual {v3}, Landroid/app/Dialog;->isShowing()Z

    move-result v3

    if-nez v3, :cond_1

    .line 1022
    :cond_3
    iget-object v3, p0, Lorg/apache/cordova/CordovaActivity;->preferences:Lorg/apache/cordova/CordovaPreferences;

    const-string v4, "SplashScreen"

    invoke-virtual {v3, v4, v6}, Lorg/apache/cordova/CordovaPreferences;->getString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    .line 1023
    .local v2, "splashResource":Ljava/lang/String;
    if-eqz v2, :cond_4

    .line 1024
    invoke-virtual {p0}, Lorg/apache/cordova/CordovaActivity;->getResources()Landroid/content/res/Resources;

    move-result-object v3

    const-string v4, "drawable"

    invoke-virtual {p0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v5

    invoke-virtual {v5}, Ljava/lang/Class;->getPackage()Ljava/lang/Package;

    move-result-object v5

    invoke-virtual {v5}, Ljava/lang/Package;->getName()Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v3, v2, v4, v5}, Landroid/content/res/Resources;->getIdentifier(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I

    move-result v3

    iput v3, p0, Lorg/apache/cordova/CordovaActivity;->splashscreen:I

    .line 1026
    :cond_4
    iget v3, p0, Lorg/apache/cordova/CordovaActivity;->splashscreenTime:I

    invoke-virtual {p0, v3}, Lorg/apache/cordova/CordovaActivity;->showSplashScreen(I)V

    goto :goto_0

    .line 1030
    .end local v2    # "splashResource":Ljava/lang/String;
    :cond_5
    const-string v3, "spinner"

    invoke-virtual {v3, p1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v3

    if-eqz v3, :cond_6

    .line 1031
    const-string v3, "stop"

    invoke-virtual {p2}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v3, v4}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v3

    if-eqz v3, :cond_1

    .line 1032
    invoke-virtual {p0}, Lorg/apache/cordova/CordovaActivity;->spinnerStop()V

    .line 1033
    iget-object v3, p0, Lorg/apache/cordova/CordovaActivity;->appView:Lorg/apache/cordova/CordovaWebView;

    const/4 v4, 0x0

    invoke-virtual {v3, v4}, Lorg/apache/cordova/CordovaWebView;->setVisibility(I)V

    goto :goto_0

    .line 1036
    :cond_6
    const-string v3, "onReceivedError"

    invoke-virtual {v3, p1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v3

    if-eqz v3, :cond_7

    move-object v0, p2

    .line 1037
    check-cast v0, Lorg/json/JSONObject;

    .line 1039
    .local v0, "d":Lorg/json/JSONObject;
    :try_start_0
    const-string v3, "errorCode"

    invoke-virtual {v0, v3}, Lorg/json/JSONObject;->getInt(Ljava/lang/String;)I

    move-result v3

    const-string v4, "description"

    invoke-virtual {v0, v4}, Lorg/json/JSONObject;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v4

    const-string v5, "url"

    invoke-virtual {v0, v5}, Lorg/json/JSONObject;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v5

    invoke-virtual {p0, v3, v4, v5}, Lorg/apache/cordova/CordovaActivity;->onReceivedError(ILjava/lang/String;Ljava/lang/String;)V
    :try_end_0
    .catch Lorg/json/JSONException; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    .line 1040
    :catch_0
    move-exception v1

    .line 1041
    .local v1, "e":Lorg/json/JSONException;
    invoke-virtual {v1}, Lorg/json/JSONException;->printStackTrace()V

    goto :goto_0

    .line 1044
    .end local v0    # "d":Lorg/json/JSONObject;
    .end local v1    # "e":Lorg/json/JSONException;
    :cond_7
    const-string v3, "exit"

    invoke-virtual {v3, p1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v3

    if-eqz v3, :cond_1

    .line 1045
    invoke-virtual {p0}, Lorg/apache/cordova/CordovaActivity;->endActivity()V

    goto/16 :goto_0
.end method

.method protected onNewIntent(Landroid/content/Intent;)V
    .locals 1
    .param p1, "intent"    # Landroid/content/Intent;

    .prologue
    .line 598
    invoke-super {p0, p1}, Landroid/app/Activity;->onNewIntent(Landroid/content/Intent;)V

    .line 600
    iget-object v0, p0, Lorg/apache/cordova/CordovaActivity;->appView:Lorg/apache/cordova/CordovaWebView;

    if-eqz v0, :cond_0

    .line 601
    iget-object v0, p0, Lorg/apache/cordova/CordovaActivity;->appView:Lorg/apache/cordova/CordovaWebView;

    invoke-virtual {v0, p1}, Lorg/apache/cordova/CordovaWebView;->onNewIntent(Landroid/content/Intent;)V

    .line 602
    :cond_0
    return-void
.end method

.method public onOptionsItemSelected(Landroid/view/MenuItem;)Z
    .locals 1
    .param p1, "item"    # Landroid/view/MenuItem;

    .prologue
    .line 885
    const-string v0, "onOptionsItemSelected"

    invoke-virtual {p0, v0, p1}, Lorg/apache/cordova/CordovaActivity;->postMessage(Ljava/lang/String;Ljava/lang/Object;)V

    .line 886
    const/4 v0, 0x1

    return v0
.end method

.method protected onPause()V
    .locals 2

    .prologue
    .line 572
    invoke-super {p0}, Landroid/app/Activity;->onPause()V

    .line 574
    sget-object v0, Lorg/apache/cordova/CordovaActivity;->TAG:Ljava/lang/String;

    const-string v1, "Paused the application!"

    invoke-static {v0, v1}, Lorg/apache/cordova/LOG;->d(Ljava/lang/String;Ljava/lang/String;)V

    .line 577
    iget v0, p0, Lorg/apache/cordova/CordovaActivity;->activityState:I

    sget v1, Lorg/apache/cordova/CordovaActivity;->ACTIVITY_EXITING:I

    if-ne v0, v1, :cond_1

    .line 591
    :cond_0
    :goto_0
    return-void

    .line 581
    :cond_1
    iget-object v0, p0, Lorg/apache/cordova/CordovaActivity;->appView:Lorg/apache/cordova/CordovaWebView;

    if-eqz v0, :cond_0

    .line 586
    iget-object v0, p0, Lorg/apache/cordova/CordovaActivity;->appView:Lorg/apache/cordova/CordovaWebView;

    iget-boolean v1, p0, Lorg/apache/cordova/CordovaActivity;->keepRunning:Z

    invoke-virtual {v0, v1}, Lorg/apache/cordova/CordovaWebView;->handlePause(Z)V

    .line 590
    invoke-virtual {p0}, Lorg/apache/cordova/CordovaActivity;->removeSplashScreen()V

    goto :goto_0
.end method

.method public onPrepareOptionsMenu(Landroid/view/Menu;)Z
    .locals 1
    .param p1, "menu"    # Landroid/view/Menu;

    .prologue
    .line 879
    const-string v0, "onPrepareOptionsMenu"

    invoke-virtual {p0, v0, p1}, Lorg/apache/cordova/CordovaActivity;->postMessage(Ljava/lang/String;Ljava/lang/Object;)V

    .line 880
    const/4 v0, 0x1

    return v0
.end method

.method public onReceivedError(ILjava/lang/String;Ljava/lang/String;)V
    .locals 7
    .param p1, "errorCode"    # I
    .param p2, "description"    # Ljava/lang/String;
    .param p3, "failingUrl"    # Ljava/lang/String;

    .prologue
    .line 801
    move-object v3, p0

    .line 804
    .local v3, "me":Lorg/apache/cordova/CordovaActivity;
    iget-object v0, p0, Lorg/apache/cordova/CordovaActivity;->preferences:Lorg/apache/cordova/CordovaPreferences;

    const-string v1, "errorUrl"

    const/4 v4, 0x0

    invoke-virtual {v0, v1, v4}, Lorg/apache/cordova/CordovaPreferences;->getString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v6

    .line 805
    .local v6, "errorUrl":Ljava/lang/String;
    if-eqz v6, :cond_1

    const-string v0, "file://"

    invoke-virtual {v6, v0}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v0

    if-nez v0, :cond_0

    iget-object v0, p0, Lorg/apache/cordova/CordovaActivity;->internalWhitelist:Lorg/apache/cordova/Whitelist;

    invoke-virtual {v0, v6}, Lorg/apache/cordova/Whitelist;->isUrlWhiteListed(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_1

    :cond_0
    invoke-virtual {p3, v6}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_1

    .line 808
    new-instance v0, Lorg/apache/cordova/CordovaActivity$2;

    invoke-direct {v0, p0, v3, v6}, Lorg/apache/cordova/CordovaActivity$2;-><init>(Lorg/apache/cordova/CordovaActivity;Lorg/apache/cordova/CordovaActivity;Ljava/lang/String;)V

    invoke-virtual {v3, v0}, Lorg/apache/cordova/CordovaActivity;->runOnUiThread(Ljava/lang/Runnable;)V

    .line 828
    :goto_0
    return-void

    .line 818
    :cond_1
    const/4 v0, -0x2

    if-eq p1, v0, :cond_2

    const/4 v2, 0x1

    .line 819
    .local v2, "exit":Z
    :goto_1
    new-instance v0, Lorg/apache/cordova/CordovaActivity$3;

    move-object v1, p0

    move-object v4, p2

    move-object v5, p3

    invoke-direct/range {v0 .. v5}, Lorg/apache/cordova/CordovaActivity$3;-><init>(Lorg/apache/cordova/CordovaActivity;ZLorg/apache/cordova/CordovaActivity;Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v3, v0}, Lorg/apache/cordova/CordovaActivity;->runOnUiThread(Ljava/lang/Runnable;)V

    goto :goto_0

    .line 818
    .end local v2    # "exit":Z
    :cond_2
    const/4 v2, 0x0

    goto :goto_1
.end method

.method protected onResume()V
    .locals 3

    .prologue
    .line 609
    invoke-super {p0}, Landroid/app/Activity;->onResume()V

    .line 610
    sget-object v0, Lorg/apache/cordova/CordovaActivity;->TAG:Ljava/lang/String;

    const-string v1, "Resuming the App"

    invoke-static {v0, v1}, Lorg/apache/cordova/LOG;->d(Ljava/lang/String;Ljava/lang/String;)V

    .line 612
    iget v0, p0, Lorg/apache/cordova/CordovaActivity;->activityState:I

    sget v1, Lorg/apache/cordova/CordovaActivity;->ACTIVITY_STARTING:I

    if-ne v0, v1, :cond_1

    .line 613
    sget v0, Lorg/apache/cordova/CordovaActivity;->ACTIVITY_RUNNING:I

    iput v0, p0, Lorg/apache/cordova/CordovaActivity;->activityState:I

    .line 635
    :cond_0
    :goto_0
    return-void

    .line 617
    :cond_1
    iget-object v0, p0, Lorg/apache/cordova/CordovaActivity;->appView:Lorg/apache/cordova/CordovaWebView;

    if-eqz v0, :cond_0

    .line 622
    invoke-virtual {p0}, Lorg/apache/cordova/CordovaActivity;->getWindow()Landroid/view/Window;

    move-result-object v0

    invoke-virtual {v0}, Landroid/view/Window;->getDecorView()Landroid/view/View;

    move-result-object v0

    invoke-virtual {v0}, Landroid/view/View;->requestFocus()Z

    .line 624
    iget-object v0, p0, Lorg/apache/cordova/CordovaActivity;->appView:Lorg/apache/cordova/CordovaWebView;

    iget-boolean v1, p0, Lorg/apache/cordova/CordovaActivity;->keepRunning:Z

    iget-boolean v2, p0, Lorg/apache/cordova/CordovaActivity;->activityResultKeepRunning:Z

    invoke-virtual {v0, v1, v2}, Lorg/apache/cordova/CordovaWebView;->handleResume(ZZ)V

    .line 627
    iget-boolean v0, p0, Lorg/apache/cordova/CordovaActivity;->keepRunning:Z

    if-eqz v0, :cond_2

    iget-boolean v0, p0, Lorg/apache/cordova/CordovaActivity;->activityResultKeepRunning:Z

    if-eqz v0, :cond_0

    .line 630
    :cond_2
    iget-boolean v0, p0, Lorg/apache/cordova/CordovaActivity;->activityResultKeepRunning:Z

    if-eqz v0, :cond_0

    .line 631
    iget-boolean v0, p0, Lorg/apache/cordova/CordovaActivity;->activityResultKeepRunning:Z

    iput-boolean v0, p0, Lorg/apache/cordova/CordovaActivity;->keepRunning:Z

    .line 632
    const/4 v0, 0x0

    iput-boolean v0, p0, Lorg/apache/cordova/CordovaActivity;->activityResultKeepRunning:Z

    goto :goto_0
.end method

.method protected onSaveInstanceState(Landroid/os/Bundle;)V
    .locals 2
    .param p1, "outState"    # Landroid/os/Bundle;

    .prologue
    .line 1056
    invoke-super {p0, p1}, Landroid/app/Activity;->onSaveInstanceState(Landroid/os/Bundle;)V

    .line 1057
    iget-object v1, p0, Lorg/apache/cordova/CordovaActivity;->activityResultCallback:Lorg/apache/cordova/CordovaPlugin;

    if-eqz v1, :cond_0

    .line 1059
    iget-object v1, p0, Lorg/apache/cordova/CordovaActivity;->activityResultCallback:Lorg/apache/cordova/CordovaPlugin;

    invoke-virtual {v1}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/Class;->getName()Ljava/lang/String;

    move-result-object v0

    .line 1060
    .local v0, "cClass":Ljava/lang/String;
    const-string v1, "callbackClass"

    invoke-virtual {p1, v1, v0}, Landroid/os/Bundle;->putString(Ljava/lang/String;Ljava/lang/String;)V

    .line 1062
    .end local v0    # "cClass":Ljava/lang/String;
    :cond_0
    return-void
.end method

.method public postMessage(Ljava/lang/String;Ljava/lang/Object;)V
    .locals 1
    .param p1, "id"    # Ljava/lang/String;
    .param p2, "data"    # Ljava/lang/Object;

    .prologue
    .line 660
    iget-object v0, p0, Lorg/apache/cordova/CordovaActivity;->appView:Lorg/apache/cordova/CordovaWebView;

    if-eqz v0, :cond_0

    .line 661
    iget-object v0, p0, Lorg/apache/cordova/CordovaActivity;->appView:Lorg/apache/cordova/CordovaWebView;

    invoke-virtual {v0, p1, p2}, Lorg/apache/cordova/CordovaWebView;->postMessage(Ljava/lang/String;Ljava/lang/Object;)V

    .line 663
    :cond_0
    return-void
.end method

.method public removeAuthenticationToken(Ljava/lang/String;Ljava/lang/String;)Lorg/apache/cordova/AuthenticationToken;
    .locals 1
    .param p1, "host"    # Ljava/lang/String;
    .param p2, "realm"    # Ljava/lang/String;

    .prologue
    .line 162
    iget-object v0, p0, Lorg/apache/cordova/CordovaActivity;->appView:Lorg/apache/cordova/CordovaWebView;

    if-eqz v0, :cond_0

    iget-object v0, p0, Lorg/apache/cordova/CordovaActivity;->appView:Lorg/apache/cordova/CordovaWebView;

    iget-object v0, v0, Lorg/apache/cordova/CordovaWebView;->viewClient:Lorg/apache/cordova/CordovaWebViewClient;

    if-eqz v0, :cond_0

    .line 163
    iget-object v0, p0, Lorg/apache/cordova/CordovaActivity;->appView:Lorg/apache/cordova/CordovaWebView;

    iget-object v0, v0, Lorg/apache/cordova/CordovaWebView;->viewClient:Lorg/apache/cordova/CordovaWebViewClient;

    invoke-virtual {v0, p1, p2}, Lorg/apache/cordova/CordovaWebViewClient;->removeAuthenticationToken(Ljava/lang/String;Ljava/lang/String;)Lorg/apache/cordova/AuthenticationToken;

    move-result-object v0

    .line 165
    :goto_0
    return-object v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public removeSplashScreen()V
    .locals 1

    .prologue
    .line 921
    iget-object v0, p0, Lorg/apache/cordova/CordovaActivity;->splashDialog:Landroid/app/Dialog;

    if-eqz v0, :cond_0

    iget-object v0, p0, Lorg/apache/cordova/CordovaActivity;->splashDialog:Landroid/app/Dialog;

    invoke-virtual {v0}, Landroid/app/Dialog;->isShowing()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 922
    iget-object v0, p0, Lorg/apache/cordova/CordovaActivity;->splashDialog:Landroid/app/Dialog;

    invoke-virtual {v0}, Landroid/app/Dialog;->dismiss()V

    .line 923
    const/4 v0, 0x0

    iput-object v0, p0, Lorg/apache/cordova/CordovaActivity;->splashDialog:Landroid/app/Dialog;

    .line 925
    :cond_0
    return-void
.end method

.method public sendJavascript(Ljava/lang/String;)V
    .locals 1
    .param p1, "statement"    # Ljava/lang/String;
    .annotation runtime Ljava/lang/Deprecated;
    .end annotation

    .prologue
    .line 686
    iget-object v0, p0, Lorg/apache/cordova/CordovaActivity;->appView:Lorg/apache/cordova/CordovaWebView;

    if-eqz v0, :cond_0

    .line 687
    iget-object v0, p0, Lorg/apache/cordova/CordovaActivity;->appView:Lorg/apache/cordova/CordovaWebView;

    iget-object v0, v0, Lorg/apache/cordova/CordovaWebView;->bridge:Lorg/apache/cordova/CordovaBridge;

    invoke-virtual {v0}, Lorg/apache/cordova/CordovaBridge;->getMessageQueue()Lorg/apache/cordova/NativeToJsMessageQueue;

    move-result-object v0

    invoke-virtual {v0, p1}, Lorg/apache/cordova/NativeToJsMessageQueue;->addJavaScript(Ljava/lang/String;)V

    .line 689
    :cond_0
    return-void
.end method

.method public setActivityResultCallback(Lorg/apache/cordova/CordovaPlugin;)V
    .locals 0
    .param p1, "plugin"    # Lorg/apache/cordova/CordovaPlugin;

    .prologue
    .line 789
    iput-object p1, p0, Lorg/apache/cordova/CordovaActivity;->activityResultCallback:Lorg/apache/cordova/CordovaPlugin;

    .line 790
    return-void
.end method

.method public setAuthenticationToken(Lorg/apache/cordova/AuthenticationToken;Ljava/lang/String;Ljava/lang/String;)V
    .locals 1
    .param p1, "authenticationToken"    # Lorg/apache/cordova/AuthenticationToken;
    .param p2, "host"    # Ljava/lang/String;
    .param p3, "realm"    # Ljava/lang/String;

    .prologue
    .line 148
    iget-object v0, p0, Lorg/apache/cordova/CordovaActivity;->appView:Lorg/apache/cordova/CordovaWebView;

    if-eqz v0, :cond_0

    iget-object v0, p0, Lorg/apache/cordova/CordovaActivity;->appView:Lorg/apache/cordova/CordovaWebView;

    iget-object v0, v0, Lorg/apache/cordova/CordovaWebView;->viewClient:Lorg/apache/cordova/CordovaWebViewClient;

    if-eqz v0, :cond_0

    .line 149
    iget-object v0, p0, Lorg/apache/cordova/CordovaActivity;->appView:Lorg/apache/cordova/CordovaWebView;

    iget-object v0, v0, Lorg/apache/cordova/CordovaWebView;->viewClient:Lorg/apache/cordova/CordovaWebViewClient;

    invoke-virtual {v0, p1, p2, p3}, Lorg/apache/cordova/CordovaWebViewClient;->setAuthenticationToken(Lorg/apache/cordova/AuthenticationToken;Ljava/lang/String;Ljava/lang/String;)V

    .line 151
    :cond_0
    return-void
.end method

.method public setBooleanProperty(Ljava/lang/String;Z)V
    .locals 2
    .param p1, "name"    # Ljava/lang/String;
    .param p2, "value"    # Z
    .annotation runtime Ljava/lang/Deprecated;
    .end annotation

    .prologue
    .line 518
    sget-object v0, Lorg/apache/cordova/CordovaActivity;->TAG:Ljava/lang/String;

    const-string v1, "Setting boolean properties in CordovaActivity will be deprecated in 3.0 on July 2013, please use config.xml"

    invoke-static {v0, v1}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 519
    invoke-virtual {p0}, Lorg/apache/cordova/CordovaActivity;->getIntent()Landroid/content/Intent;

    move-result-object v0

    invoke-virtual {p1}, Ljava/lang/String;->toLowerCase()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1, p2}, Landroid/content/Intent;->putExtra(Ljava/lang/String;Z)Landroid/content/Intent;

    .line 520
    return-void
.end method

.method public setDoubleProperty(Ljava/lang/String;D)V
    .locals 2
    .param p1, "name"    # Ljava/lang/String;
    .param p2, "value"    # D
    .annotation runtime Ljava/lang/Deprecated;
    .end annotation

    .prologue
    .line 563
    sget-object v0, Lorg/apache/cordova/CordovaActivity;->TAG:Ljava/lang/String;

    const-string v1, "Setting double properties in CordovaActivity will be deprecated in 3.0 on July 2013, please use config.xml"

    invoke-static {v0, v1}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 564
    invoke-virtual {p0}, Lorg/apache/cordova/CordovaActivity;->getIntent()Landroid/content/Intent;

    move-result-object v0

    invoke-virtual {p1}, Ljava/lang/String;->toLowerCase()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1, p2, p3}, Landroid/content/Intent;->putExtra(Ljava/lang/String;D)Landroid/content/Intent;

    .line 565
    return-void
.end method

.method public setIntegerProperty(Ljava/lang/String;I)V
    .locals 2
    .param p1, "name"    # Ljava/lang/String;
    .param p2, "value"    # I
    .annotation runtime Ljava/lang/Deprecated;
    .end annotation

    .prologue
    .line 533
    sget-object v0, Lorg/apache/cordova/CordovaActivity;->TAG:Ljava/lang/String;

    const-string v1, "Setting integer properties in CordovaActivity will be deprecated in 3.0 on July 2013, please use config.xml"

    invoke-static {v0, v1}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 534
    invoke-virtual {p0}, Lorg/apache/cordova/CordovaActivity;->getIntent()Landroid/content/Intent;

    move-result-object v0

    invoke-virtual {p1}, Ljava/lang/String;->toLowerCase()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1, p2}, Landroid/content/Intent;->putExtra(Ljava/lang/String;I)Landroid/content/Intent;

    .line 535
    return-void
.end method

.method public setStringProperty(Ljava/lang/String;Ljava/lang/String;)V
    .locals 2
    .param p1, "name"    # Ljava/lang/String;
    .param p2, "value"    # Ljava/lang/String;
    .annotation runtime Ljava/lang/Deprecated;
    .end annotation

    .prologue
    .line 548
    sget-object v0, Lorg/apache/cordova/CordovaActivity;->TAG:Ljava/lang/String;

    const-string v1, "Setting string properties in CordovaActivity will be deprecated in 3.0 on July 2013, please use config.xml"

    invoke-static {v0, v1}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 549
    invoke-virtual {p0}, Lorg/apache/cordova/CordovaActivity;->getIntent()Landroid/content/Intent;

    move-result-object v0

    invoke-virtual {p1}, Ljava/lang/String;->toLowerCase()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1, p2}, Landroid/content/Intent;->putExtra(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;

    .line 550
    return-void
.end method

.method protected showSplashScreen(I)V
    .locals 2
    .param p1, "time"    # I

    .prologue
    .line 932
    move-object v1, p0

    .line 934
    .local v1, "that":Lorg/apache/cordova/CordovaActivity;
    new-instance v0, Lorg/apache/cordova/CordovaActivity$5;

    invoke-direct {v0, p0, v1, p1}, Lorg/apache/cordova/CordovaActivity$5;-><init>(Lorg/apache/cordova/CordovaActivity;Lorg/apache/cordova/CordovaActivity;I)V

    .line 970
    .local v0, "runnable":Ljava/lang/Runnable;
    invoke-virtual {p0, v0}, Lorg/apache/cordova/CordovaActivity;->runOnUiThread(Ljava/lang/Runnable;)V

    .line 971
    return-void
.end method

.method public showWebPage(Ljava/lang/String;ZZLjava/util/HashMap;)V
    .locals 1
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

    .annotation runtime Ljava/lang/Deprecated;
    .end annotation

    .prologue
    .line 910
    .local p4, "params":Ljava/util/HashMap;, "Ljava/util/HashMap<Ljava/lang/String;Ljava/lang/Object;>;"
    iget-object v0, p0, Lorg/apache/cordova/CordovaActivity;->appView:Lorg/apache/cordova/CordovaWebView;

    if-eqz v0, :cond_0

    .line 911
    iget-object v0, p0, Lorg/apache/cordova/CordovaActivity;->appView:Lorg/apache/cordova/CordovaWebView;

    invoke-virtual {v0, p1, p2, p3, p4}, Lorg/apache/cordova/CordovaWebView;->showWebPage(Ljava/lang/String;ZZLjava/util/HashMap;)V

    .line 913
    :cond_0
    return-void
.end method

.method public spinnerStart(Ljava/lang/String;Ljava/lang/String;)V
    .locals 7
    .param p1, "title"    # Ljava/lang/String;
    .param p2, "message"    # Ljava/lang/String;

    .prologue
    const/4 v3, 0x1

    .line 698
    iget-object v0, p0, Lorg/apache/cordova/CordovaActivity;->spinnerDialog:Landroid/app/ProgressDialog;

    if-eqz v0, :cond_0

    .line 699
    iget-object v0, p0, Lorg/apache/cordova/CordovaActivity;->spinnerDialog:Landroid/app/ProgressDialog;

    invoke-virtual {v0}, Landroid/app/ProgressDialog;->dismiss()V

    .line 700
    const/4 v0, 0x0

    iput-object v0, p0, Lorg/apache/cordova/CordovaActivity;->spinnerDialog:Landroid/app/ProgressDialog;

    .line 702
    :cond_0
    move-object v6, p0

    .line 703
    .local v6, "me":Lorg/apache/cordova/CordovaActivity;
    new-instance v5, Lorg/apache/cordova/CordovaActivity$1;

    invoke-direct {v5, p0, v6}, Lorg/apache/cordova/CordovaActivity$1;-><init>(Lorg/apache/cordova/CordovaActivity;Lorg/apache/cordova/CordovaActivity;)V

    move-object v0, p0

    move-object v1, p1

    move-object v2, p2

    move v4, v3

    invoke-static/range {v0 .. v5}, Landroid/app/ProgressDialog;->show(Landroid/content/Context;Ljava/lang/CharSequence;Ljava/lang/CharSequence;ZZLandroid/content/DialogInterface$OnCancelListener;)Landroid/app/ProgressDialog;

    move-result-object v0

    iput-object v0, p0, Lorg/apache/cordova/CordovaActivity;->spinnerDialog:Landroid/app/ProgressDialog;

    .line 709
    return-void
.end method

.method public spinnerStop()V
    .locals 1

    .prologue
    .line 715
    iget-object v0, p0, Lorg/apache/cordova/CordovaActivity;->spinnerDialog:Landroid/app/ProgressDialog;

    if-eqz v0, :cond_0

    iget-object v0, p0, Lorg/apache/cordova/CordovaActivity;->spinnerDialog:Landroid/app/ProgressDialog;

    invoke-virtual {v0}, Landroid/app/ProgressDialog;->isShowing()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 716
    iget-object v0, p0, Lorg/apache/cordova/CordovaActivity;->spinnerDialog:Landroid/app/ProgressDialog;

    invoke-virtual {v0}, Landroid/app/ProgressDialog;->dismiss()V

    .line 717
    const/4 v0, 0x0

    iput-object v0, p0, Lorg/apache/cordova/CordovaActivity;->spinnerDialog:Landroid/app/ProgressDialog;

    .line 719
    :cond_0
    return-void
.end method

.method public startActivityForResult(Lorg/apache/cordova/CordovaPlugin;Landroid/content/Intent;I)V
    .locals 1
    .param p1, "command"    # Lorg/apache/cordova/CordovaPlugin;
    .param p2, "intent"    # Landroid/content/Intent;
    .param p3, "requestCode"    # I

    .prologue
    .line 739
    iput-object p1, p0, Lorg/apache/cordova/CordovaActivity;->activityResultCallback:Lorg/apache/cordova/CordovaPlugin;

    .line 740
    iget-boolean v0, p0, Lorg/apache/cordova/CordovaActivity;->keepRunning:Z

    iput-boolean v0, p0, Lorg/apache/cordova/CordovaActivity;->activityResultKeepRunning:Z

    .line 743
    if-eqz p1, :cond_0

    .line 744
    const/4 v0, 0x0

    iput-boolean v0, p0, Lorg/apache/cordova/CordovaActivity;->keepRunning:Z

    .line 748
    :cond_0
    invoke-super {p0, p2, p3}, Landroid/app/Activity;->startActivityForResult(Landroid/content/Intent;I)V

    .line 749
    return-void
.end method
