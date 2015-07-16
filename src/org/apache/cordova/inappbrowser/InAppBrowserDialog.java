package org.apache.cordova.inappbrowser;

import android.app.Dialog;
import android.content.Context;

public class InAppBrowserDialog
  extends Dialog
{
  Context context;
  InAppBrowser inAppBrowser = null;
  
  public InAppBrowserDialog(Context paramContext, int paramInt)
  {
    super(paramContext, paramInt);
    context = paramContext;
  }
  
  public void onBackPressed()
  {
    if (inAppBrowser == null)
    {
      dismiss();
      return;
    }
    inAppBrowser.closeDialog();
  }
  
  public void setInAppBroswer(InAppBrowser paramInAppBrowser)
  {
    inAppBrowser = paramInAppBrowser;
  }
}

/* Location:
 * Qualified Name:     org.apache.cordova.inappbrowser.InAppBrowserDialog
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */