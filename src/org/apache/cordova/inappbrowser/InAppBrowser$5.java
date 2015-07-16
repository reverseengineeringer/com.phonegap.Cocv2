package org.apache.cordova.inappbrowser;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import java.io.File;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaWebView;

class InAppBrowser$5
  implements Runnable
{
  InAppBrowser$5(InAppBrowser paramInAppBrowser, String paramString, CordovaWebView paramCordovaWebView) {}
  
  private int dpToPixels(int paramInt)
  {
    return (int)TypedValue.applyDimension(1, paramInt, this$0.cordova.getActivity().getResources().getDisplayMetrics());
  }
  
  @SuppressLint({"NewApi"})
  public void run()
  {
    InAppBrowser.access$002(this$0, new InAppBrowserDialog(this$0.cordova.getActivity(), 16973830));
    access$000this$0).getWindow().getAttributes().windowAnimations = 16973826;
    InAppBrowser.access$000(this$0).requestWindowFeature(1);
    InAppBrowser.access$000(this$0).setCancelable(true);
    InAppBrowser.access$000(this$0).setInAppBroswer(InAppBrowser.access$200(this$0));
    LinearLayout localLinearLayout = new LinearLayout(this$0.cordova.getActivity());
    localLinearLayout.setOrientation(1);
    Object localObject1 = new RelativeLayout(this$0.cordova.getActivity());
    ((RelativeLayout)localObject1).setBackgroundColor(-3355444);
    ((RelativeLayout)localObject1).setLayoutParams(new RelativeLayout.LayoutParams(-1, dpToPixels(44)));
    ((RelativeLayout)localObject1).setPadding(dpToPixels(2), dpToPixels(2), dpToPixels(2), dpToPixels(2));
    ((RelativeLayout)localObject1).setHorizontalGravity(3);
    ((RelativeLayout)localObject1).setVerticalGravity(48);
    RelativeLayout localRelativeLayout = new RelativeLayout(this$0.cordova.getActivity());
    localRelativeLayout.setLayoutParams(new RelativeLayout.LayoutParams(-2, -2));
    localRelativeLayout.setHorizontalGravity(3);
    localRelativeLayout.setVerticalGravity(16);
    localRelativeLayout.setId(1);
    Button localButton = new Button(this$0.cordova.getActivity());
    Object localObject2 = new RelativeLayout.LayoutParams(-2, -1);
    ((RelativeLayout.LayoutParams)localObject2).addRule(5);
    localButton.setLayoutParams((ViewGroup.LayoutParams)localObject2);
    localButton.setContentDescription("Back Button");
    localButton.setId(2);
    Object localObject3 = this$0.cordova.getActivity().getResources();
    localObject2 = ((Resources)localObject3).getDrawable(((Resources)localObject3).getIdentifier("ic_action_previous_item", "drawable", this$0.cordova.getActivity().getPackageName()));
    Object localObject4;
    label488:
    Object localObject5;
    label759:
    boolean bool;
    if (Build.VERSION.SDK_INT < 16)
    {
      localButton.setBackgroundDrawable((Drawable)localObject2);
      localButton.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          InAppBrowser.access$300(this$0);
        }
      });
      localObject2 = new Button(this$0.cordova.getActivity());
      localObject4 = new RelativeLayout.LayoutParams(-2, -1);
      ((RelativeLayout.LayoutParams)localObject4).addRule(1, 2);
      ((Button)localObject2).setLayoutParams((ViewGroup.LayoutParams)localObject4);
      ((Button)localObject2).setContentDescription("Forward Button");
      ((Button)localObject2).setId(3);
      localObject4 = ((Resources)localObject3).getDrawable(((Resources)localObject3).getIdentifier("ic_action_next_item", "drawable", this$0.cordova.getActivity().getPackageName()));
      if (Build.VERSION.SDK_INT >= 16) {
        break label1243;
      }
      ((Button)localObject2).setBackgroundDrawable((Drawable)localObject4);
      ((Button)localObject2).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          InAppBrowser.access$400(this$0);
        }
      });
      InAppBrowser.access$502(this$0, new EditText(this$0.cordova.getActivity()));
      localObject4 = new RelativeLayout.LayoutParams(-1, -1);
      ((RelativeLayout.LayoutParams)localObject4).addRule(1, 1);
      ((RelativeLayout.LayoutParams)localObject4).addRule(0, 5);
      InAppBrowser.access$500(this$0).setLayoutParams((ViewGroup.LayoutParams)localObject4);
      InAppBrowser.access$500(this$0).setId(4);
      InAppBrowser.access$500(this$0).setSingleLine(true);
      InAppBrowser.access$500(this$0).setText(val$url);
      InAppBrowser.access$500(this$0).setInputType(16);
      InAppBrowser.access$500(this$0).setImeOptions(2);
      InAppBrowser.access$500(this$0).setInputType(0);
      InAppBrowser.access$500(this$0).setOnKeyListener(new View.OnKeyListener()
      {
        public boolean onKey(View paramAnonymousView, int paramAnonymousInt, KeyEvent paramAnonymousKeyEvent)
        {
          if ((paramAnonymousKeyEvent.getAction() == 0) && (paramAnonymousInt == 66))
          {
            InAppBrowser.access$600(this$0, InAppBrowser.access$500(this$0).getText().toString());
            return true;
          }
          return false;
        }
      });
      localObject4 = new Button(this$0.cordova.getActivity());
      localObject5 = new RelativeLayout.LayoutParams(-2, -1);
      ((RelativeLayout.LayoutParams)localObject5).addRule(11);
      ((Button)localObject4).setLayoutParams((ViewGroup.LayoutParams)localObject5);
      ((Button)localObject2).setContentDescription("Close Button");
      ((Button)localObject4).setId(5);
      localObject3 = ((Resources)localObject3).getDrawable(((Resources)localObject3).getIdentifier("ic_action_remove", "drawable", this$0.cordova.getActivity().getPackageName()));
      if (Build.VERSION.SDK_INT >= 16) {
        break label1253;
      }
      ((Button)localObject4).setBackgroundDrawable((Drawable)localObject3);
      ((Button)localObject4).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          this$0.closeDialog();
        }
      });
      InAppBrowser.access$102(this$0, new WebView(this$0.cordova.getActivity()));
      InAppBrowser.access$100(this$0).setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
      InAppBrowser.access$100(this$0).setWebChromeClient(new InAppChromeClient(val$thatWebView));
      localObject3 = new InAppBrowser.InAppBrowserClient(this$0, val$thatWebView, InAppBrowser.access$500(this$0));
      InAppBrowser.access$100(this$0).setWebViewClient((WebViewClient)localObject3);
      localObject3 = InAppBrowser.access$100(this$0).getSettings();
      ((WebSettings)localObject3).setJavaScriptEnabled(true);
      ((WebSettings)localObject3).setJavaScriptCanOpenWindowsAutomatically(true);
      ((WebSettings)localObject3).setBuiltInZoomControls(true);
      ((WebSettings)localObject3).setPluginState(WebSettings.PluginState.ON);
      localObject5 = this$0.cordova.getActivity().getIntent().getExtras();
      if (localObject5 != null) {
        break label1263;
      }
      bool = true;
      label940:
      if (bool)
      {
        ((WebSettings)localObject3).setDatabasePath(this$0.cordova.getActivity().getApplicationContext().getDir("inAppBrowserDB", 0).getPath());
        ((WebSettings)localObject3).setDatabaseEnabled(true);
      }
      ((WebSettings)localObject3).setDomStorageEnabled(true);
      if (!InAppBrowser.access$700(this$0)) {
        break label1276;
      }
      CookieManager.getInstance().removeAllCookie();
    }
    for (;;)
    {
      InAppBrowser.access$100(this$0).loadUrl(val$url);
      InAppBrowser.access$100(this$0).setId(6);
      InAppBrowser.access$100(this$0).getSettings().setLoadWithOverviewMode(true);
      InAppBrowser.access$100(this$0).getSettings().setUseWideViewPort(true);
      InAppBrowser.access$100(this$0).requestFocus();
      InAppBrowser.access$100(this$0).requestFocusFromTouch();
      localRelativeLayout.addView(localButton);
      localRelativeLayout.addView((View)localObject2);
      ((RelativeLayout)localObject1).addView(localRelativeLayout);
      ((RelativeLayout)localObject1).addView(InAppBrowser.access$500(this$0));
      ((RelativeLayout)localObject1).addView((View)localObject4);
      if (InAppBrowser.access$900(this$0)) {
        localLinearLayout.addView((View)localObject1);
      }
      localLinearLayout.addView(InAppBrowser.access$100(this$0));
      localObject1 = new WindowManager.LayoutParams();
      ((WindowManager.LayoutParams)localObject1).copyFrom(InAppBrowser.access$000(this$0).getWindow().getAttributes());
      width = -1;
      height = -1;
      InAppBrowser.access$000(this$0).setContentView(localLinearLayout);
      InAppBrowser.access$000(this$0).show();
      InAppBrowser.access$000(this$0).getWindow().setAttributes((WindowManager.LayoutParams)localObject1);
      if (InAppBrowser.access$1000(this$0)) {
        InAppBrowser.access$000(this$0).hide();
      }
      return;
      localButton.setBackground((Drawable)localObject2);
      break;
      label1243:
      ((Button)localObject2).setBackground((Drawable)localObject4);
      break label488;
      label1253:
      ((Button)localObject4).setBackground((Drawable)localObject3);
      break label759;
      label1263:
      bool = ((Bundle)localObject5).getBoolean("InAppBrowserStorageEnabled", true);
      break label940;
      label1276:
      if (InAppBrowser.access$800(this$0)) {
        CookieManager.getInstance().removeSessionCookie();
      }
    }
  }
}

/* Location:
 * Qualified Name:     org.apache.cordova.inappbrowser.InAppBrowser.5
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */