package gov.cambridgema.ireport;

import android.os.Bundle;
import org.apache.cordova.CordovaActivity;

public class CordovaApp
  extends CordovaActivity
{
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    super.init();
    loadUrl(launchUrl);
  }
}

/* Location:
 * Qualified Name:     gov.cambridgema.ireport.CordovaApp
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */