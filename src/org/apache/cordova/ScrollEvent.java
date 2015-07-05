package org.apache.cordova;

import android.view.View;

public class ScrollEvent
{
  public int l;
  public int nl;
  public int nt;
  public int t;
  private View targetView;
  
  ScrollEvent(int paramInt1, int paramInt2, int paramInt3, int paramInt4, View paramView)
  {
    l = paramInt3;
    paramInt3 = t;
    nl = paramInt1;
    nt = paramInt2;
    targetView = paramView;
  }
  
  public int dl()
  {
    return nl - l;
  }
  
  public int dt()
  {
    return nt - t;
  }
  
  public View getTargetView()
  {
    return targetView;
  }
}

/* Location:
 * Qualified Name:     org.apache.cordova.ScrollEvent
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */