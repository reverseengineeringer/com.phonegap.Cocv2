package org.apache.cordova;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnKeyListener;
import android.content.Intent;
import android.net.Uri;
import android.os.Build.VERSION;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.webkit.ConsoleMessage;
import android.webkit.GeolocationPermissions.Callback;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebChromeClient.CustomViewCallback;
import android.webkit.WebStorage.QuotaUpdater;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ProgressBar;
import android.widget.RelativeLayout.LayoutParams;

public class CordovaChromeClient
  extends WebChromeClient
{
  public static final int FILECHOOSER_RESULTCODE = 5173;
  private long MAX_QUOTA = 104857600L;
  private String TAG = "CordovaLog";
  protected CordovaWebView appView;
  protected CordovaInterface cordova;
  public ValueCallback<Uri> mUploadMessage;
  private View mVideoProgressView;
  
  @Deprecated
  public CordovaChromeClient(CordovaInterface paramCordovaInterface)
  {
    cordova = paramCordovaInterface;
  }
  
  public CordovaChromeClient(CordovaInterface paramCordovaInterface, CordovaWebView paramCordovaWebView)
  {
    cordova = paramCordovaInterface;
    appView = paramCordovaWebView;
  }
  
  public ValueCallback<Uri> getValueCallback()
  {
    return mUploadMessage;
  }
  
  public View getVideoLoadingProgressView()
  {
    if (mVideoProgressView == null)
    {
      LinearLayout localLinearLayout = new LinearLayout(appView.getContext());
      localLinearLayout.setOrientation(1);
      Object localObject = new RelativeLayout.LayoutParams(-2, -2);
      ((RelativeLayout.LayoutParams)localObject).addRule(13);
      localLinearLayout.setLayoutParams((ViewGroup.LayoutParams)localObject);
      localObject = new ProgressBar(appView.getContext());
      LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-2, -2);
      gravity = 17;
      ((ProgressBar)localObject).setLayoutParams(localLayoutParams);
      localLinearLayout.addView((View)localObject);
      mVideoProgressView = localLinearLayout;
    }
    return mVideoProgressView;
  }
  
  public void onConsoleMessage(String paramString1, int paramInt, String paramString2)
  {
    if (Build.VERSION.SDK_INT == 7)
    {
      LOG.d(TAG, "%s: Line %d : %s", new Object[] { paramString2, Integer.valueOf(paramInt), paramString1 });
      super.onConsoleMessage(paramString1, paramInt, paramString2);
    }
  }
  
  @TargetApi(8)
  public boolean onConsoleMessage(ConsoleMessage paramConsoleMessage)
  {
    if (paramConsoleMessage.message() != null) {
      LOG.d(TAG, "%s: Line %d : %s", new Object[] { paramConsoleMessage.sourceId(), Integer.valueOf(paramConsoleMessage.lineNumber()), paramConsoleMessage.message() });
    }
    return super.onConsoleMessage(paramConsoleMessage);
  }
  
  public void onExceededDatabaseQuota(String paramString1, String paramString2, long paramLong1, long paramLong2, long paramLong3, WebStorage.QuotaUpdater paramQuotaUpdater)
  {
    LOG.d(TAG, "onExceededDatabaseQuota estimatedSize: %d  currentQuota: %d  totalUsedQuota: %d", new Object[] { Long.valueOf(paramLong2), Long.valueOf(paramLong1), Long.valueOf(paramLong3) });
    paramQuotaUpdater.updateQuota(MAX_QUOTA);
  }
  
  public void onGeolocationPermissionsShowPrompt(String paramString, GeolocationPermissions.Callback paramCallback)
  {
    super.onGeolocationPermissionsShowPrompt(paramString, paramCallback);
    paramCallback.invoke(paramString, true, false);
  }
  
  public void onHideCustomView()
  {
    appView.hideCustomView();
  }
  
  public boolean onJsAlert(WebView paramWebView, String paramString1, String paramString2, final JsResult paramJsResult)
  {
    paramWebView = new AlertDialog.Builder(cordova.getActivity());
    paramWebView.setMessage(paramString2);
    paramWebView.setTitle("Alert");
    paramWebView.setCancelable(true);
    paramWebView.setPositiveButton(17039370, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramJsResult.confirm();
      }
    });
    paramWebView.setOnCancelListener(new DialogInterface.OnCancelListener()
    {
      public void onCancel(DialogInterface paramAnonymousDialogInterface)
      {
        paramJsResult.cancel();
      }
    });
    paramWebView.setOnKeyListener(new DialogInterface.OnKeyListener()
    {
      public boolean onKey(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt, KeyEvent paramAnonymousKeyEvent)
      {
        if (paramAnonymousInt == 4)
        {
          paramJsResult.confirm();
          return false;
        }
        return true;
      }
    });
    paramWebView.show();
    return true;
  }
  
  public boolean onJsConfirm(WebView paramWebView, String paramString1, String paramString2, final JsResult paramJsResult)
  {
    paramWebView = new AlertDialog.Builder(cordova.getActivity());
    paramWebView.setMessage(paramString2);
    paramWebView.setTitle("Confirm");
    paramWebView.setCancelable(true);
    paramWebView.setPositiveButton(17039370, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramJsResult.confirm();
      }
    });
    paramWebView.setNegativeButton(17039360, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramJsResult.cancel();
      }
    });
    paramWebView.setOnCancelListener(new DialogInterface.OnCancelListener()
    {
      public void onCancel(DialogInterface paramAnonymousDialogInterface)
      {
        paramJsResult.cancel();
      }
    });
    paramWebView.setOnKeyListener(new DialogInterface.OnKeyListener()
    {
      public boolean onKey(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt, KeyEvent paramAnonymousKeyEvent)
      {
        if (paramAnonymousInt == 4)
        {
          paramJsResult.cancel();
          return false;
        }
        return true;
      }
    });
    paramWebView.show();
    return true;
  }
  
  public boolean onJsPrompt(WebView paramWebView, final String paramString1, String paramString2, String paramString3, final JsPromptResult paramJsPromptResult)
  {
    paramWebView = appView.bridge.promptOnJsPrompt(paramString1, paramString2, paramString3);
    if (paramWebView != null) {
      paramJsPromptResult.confirm(paramWebView);
    }
    for (;;)
    {
      return true;
      paramWebView = new AlertDialog.Builder(cordova.getActivity());
      paramWebView.setMessage(paramString2);
      paramString1 = new EditText(cordova.getActivity());
      if (paramString3 != null) {
        paramString1.setText(paramString3);
      }
      paramWebView.setView(paramString1);
      paramWebView.setCancelable(false);
      paramWebView.setPositiveButton(17039370, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          paramAnonymousDialogInterface = paramString1.getText().toString();
          paramJsPromptResult.confirm(paramAnonymousDialogInterface);
        }
      });
      paramWebView.setNegativeButton(17039360, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          paramJsPromptResult.cancel();
        }
      });
      paramWebView.show();
    }
  }
  
  public void onShowCustomView(View paramView, WebChromeClient.CustomViewCallback paramCustomViewCallback)
  {
    appView.showCustomView(paramView, paramCustomViewCallback);
  }
  
  public void openFileChooser(ValueCallback<Uri> paramValueCallback)
  {
    openFileChooser(paramValueCallback, "*/*");
  }
  
  public void openFileChooser(ValueCallback<Uri> paramValueCallback, String paramString)
  {
    openFileChooser(paramValueCallback, paramString, null);
  }
  
  public void openFileChooser(ValueCallback<Uri> paramValueCallback, String paramString1, String paramString2)
  {
    mUploadMessage = paramValueCallback;
    paramValueCallback = new Intent("android.intent.action.GET_CONTENT");
    paramValueCallback.addCategory("android.intent.category.OPENABLE");
    paramValueCallback.setType("*/*");
    cordova.getActivity().startActivityForResult(Intent.createChooser(paramValueCallback, "File Browser"), 5173);
  }
  
  @Deprecated
  public void setWebView(CordovaWebView paramCordovaWebView)
  {
    appView = paramCordovaWebView;
  }
}

/* Location:
 * Qualified Name:     org.apache.cordova.CordovaChromeClient
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */