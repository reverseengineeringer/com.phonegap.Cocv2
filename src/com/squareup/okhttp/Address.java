package com.squareup.okhttp;

import com.squareup.okhttp.internal.Util;
import java.net.Proxy;
import java.net.UnknownHostException;
import java.util.List;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;

public final class Address
{
  final OkAuthenticator authenticator;
  final HostnameVerifier hostnameVerifier;
  final Proxy proxy;
  final SSLSocketFactory sslSocketFactory;
  final List<String> transports;
  final String uriHost;
  final int uriPort;
  
  public Address(String paramString, int paramInt, SSLSocketFactory paramSSLSocketFactory, HostnameVerifier paramHostnameVerifier, OkAuthenticator paramOkAuthenticator, Proxy paramProxy, List<String> paramList)
    throws UnknownHostException
  {
    if (paramString == null) {
      throw new NullPointerException("uriHost == null");
    }
    if (paramInt <= 0) {
      throw new IllegalArgumentException("uriPort <= 0: " + paramInt);
    }
    if (paramOkAuthenticator == null) {
      throw new IllegalArgumentException("authenticator == null");
    }
    if (paramList == null) {
      throw new IllegalArgumentException("transports == null");
    }
    proxy = paramProxy;
    uriHost = paramString;
    uriPort = paramInt;
    sslSocketFactory = paramSSLSocketFactory;
    hostnameVerifier = paramHostnameVerifier;
    authenticator = paramOkAuthenticator;
    transports = Util.immutableList(paramList);
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if ((paramObject instanceof Address))
    {
      paramObject = (Address)paramObject;
      bool1 = bool2;
      if (Util.equal(proxy, proxy))
      {
        bool1 = bool2;
        if (uriHost.equals(uriHost))
        {
          bool1 = bool2;
          if (uriPort == uriPort)
          {
            bool1 = bool2;
            if (Util.equal(sslSocketFactory, sslSocketFactory))
            {
              bool1 = bool2;
              if (Util.equal(hostnameVerifier, hostnameVerifier))
              {
                bool1 = bool2;
                if (Util.equal(authenticator, authenticator))
                {
                  bool1 = bool2;
                  if (Util.equal(transports, transports)) {
                    bool1 = true;
                  }
                }
              }
            }
          }
        }
      }
    }
    return bool1;
  }
  
  public OkAuthenticator getAuthenticator()
  {
    return authenticator;
  }
  
  public HostnameVerifier getHostnameVerifier()
  {
    return hostnameVerifier;
  }
  
  public Proxy getProxy()
  {
    return proxy;
  }
  
  public SSLSocketFactory getSslSocketFactory()
  {
    return sslSocketFactory;
  }
  
  public List<String> getTransports()
  {
    return transports;
  }
  
  public String getUriHost()
  {
    return uriHost;
  }
  
  public int getUriPort()
  {
    return uriPort;
  }
  
  public int hashCode()
  {
    int m = 0;
    int n = uriHost.hashCode();
    int i1 = uriPort;
    int i;
    int j;
    if (sslSocketFactory != null)
    {
      i = sslSocketFactory.hashCode();
      if (hostnameVerifier == null) {
        break label131;
      }
      j = hostnameVerifier.hashCode();
      label48:
      if (authenticator == null) {
        break label136;
      }
    }
    label131:
    label136:
    for (int k = authenticator.hashCode();; k = 0)
    {
      if (proxy != null) {
        m = proxy.hashCode();
      }
      return ((((((n + 527) * 31 + i1) * 31 + i) * 31 + j) * 31 + k) * 31 + m) * 31 + transports.hashCode();
      i = 0;
      break;
      j = 0;
      break label48;
    }
  }
}

/* Location:
 * Qualified Name:     com.squareup.okhttp.Address
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */