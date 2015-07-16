package org.apache.cordova.inappbrowser;

import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.EditText;

class InAppBrowser$5$3
  implements View.OnKeyListener
{
  InAppBrowser$5$3(InAppBrowser.5 param5) {}
  
  public boolean onKey(View paramView, int paramInt, KeyEvent paramKeyEvent)
  {
    if ((paramKeyEvent.getAction() == 0) && (paramInt == 66))
    {
      InAppBrowser.access$600(this$1.this$0, InAppBrowser.access$500(this$1.this$0).getText().toString());
      return true;
    }
    return false;
  }
}

/* Location:
 * Qualified Name:     org.apache.cordova.inappbrowser.InAppBrowser.5.3
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */