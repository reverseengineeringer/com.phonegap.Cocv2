package com.squareup.okhttp.internal.http;

import com.squareup.okhttp.OkAuthenticator;
import com.squareup.okhttp.OkAuthenticator.Challenge;
import com.squareup.okhttp.OkAuthenticator.Credential;
import java.io.IOException;
import java.net.Authenticator;
import java.net.Authenticator.RequestorType;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

final class HttpAuthenticator$1
  implements OkAuthenticator
{
  private InetAddress getConnectToInetAddress(Proxy paramProxy, URL paramURL)
    throws IOException
  {
    if ((paramProxy != null) && (paramProxy.type() != Proxy.Type.DIRECT)) {
      return ((InetSocketAddress)paramProxy.address()).getAddress();
    }
    return InetAddress.getByName(paramURL.getHost());
  }
  
  public OkAuthenticator.Credential authenticate(Proxy paramProxy, URL paramURL, List<OkAuthenticator.Challenge> paramList)
    throws IOException
  {
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      Object localObject = (OkAuthenticator.Challenge)paramList.next();
      if ("Basic".equalsIgnoreCase(((OkAuthenticator.Challenge)localObject).getScheme()))
      {
        localObject = Authenticator.requestPasswordAuthentication(paramURL.getHost(), getConnectToInetAddress(paramProxy, paramURL), paramURL.getPort(), paramURL.getProtocol(), ((OkAuthenticator.Challenge)localObject).getRealm(), ((OkAuthenticator.Challenge)localObject).getScheme(), paramURL, Authenticator.RequestorType.SERVER);
        if (localObject != null) {
          return OkAuthenticator.Credential.basic(((PasswordAuthentication)localObject).getUserName(), new String(((PasswordAuthentication)localObject).getPassword()));
        }
      }
    }
    return null;
  }
  
  public OkAuthenticator.Credential authenticateProxy(Proxy paramProxy, URL paramURL, List<OkAuthenticator.Challenge> paramList)
    throws IOException
  {
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      Object localObject = (OkAuthenticator.Challenge)paramList.next();
      if ("Basic".equalsIgnoreCase(((OkAuthenticator.Challenge)localObject).getScheme()))
      {
        InetSocketAddress localInetSocketAddress = (InetSocketAddress)paramProxy.address();
        localObject = Authenticator.requestPasswordAuthentication(localInetSocketAddress.getHostName(), getConnectToInetAddress(paramProxy, paramURL), localInetSocketAddress.getPort(), paramURL.getProtocol(), ((OkAuthenticator.Challenge)localObject).getRealm(), ((OkAuthenticator.Challenge)localObject).getScheme(), paramURL, Authenticator.RequestorType.PROXY);
        if (localObject != null) {
          return OkAuthenticator.Credential.basic(((PasswordAuthentication)localObject).getUserName(), new String(((PasswordAuthentication)localObject).getPassword()));
        }
      }
    }
    return null;
  }
}

/* Location:
 * Qualified Name:     com.squareup.okhttp.internal.http.HttpAuthenticator.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */