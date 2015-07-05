package gov.cambridgema.iReport;

import android.os.Bundle;
import org.apache.cordova.Config;
import org.apache.cordova.CordovaActivity;

public class iReport
  extends CordovaActivity
{
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    super.init();
    super.loadUrl(Config.getStartUrl());
  }
}

/* Location:
 * Qualified Name:     gov.cambridgema.iReport.iReport
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */