package org.apache.cordova.filetransfer;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

class FileTransfer$1
  implements HostnameVerifier
{
  public boolean verify(String paramString, SSLSession paramSSLSession)
  {
    return true;
  }
}

/* Location:
 * Qualified Name:     org.apache.cordova.filetransfer.FileTransfer.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */