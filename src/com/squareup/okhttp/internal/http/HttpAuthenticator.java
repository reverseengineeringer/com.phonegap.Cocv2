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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class HttpAuthenticator
{
  public static final OkAuthenticator SYSTEM_DEFAULT = new OkAuthenticator()
  {
    private InetAddress getConnectToInetAddress(Proxy paramAnonymousProxy, URL paramAnonymousURL)
      throws IOException
    {
      if ((paramAnonymousProxy != null) && (paramAnonymousProxy.type() != Proxy.Type.DIRECT)) {
        return ((InetSocketAddress)paramAnonymousProxy.address()).getAddress();
      }
      return InetAddress.getByName(paramAnonymousURL.getHost());
    }
    
    public OkAuthenticator.Credential authenticate(Proxy paramAnonymousProxy, URL paramAnonymousURL, List<OkAuthenticator.Challenge> paramAnonymousList)
      throws IOException
    {
      paramAnonymousList = paramAnonymousList.iterator();
      Object localObject;
      do
      {
        do
        {
          if (!paramAnonymousList.hasNext()) {
            return null;
          }
          localObject = (OkAuthenticator.Challenge)paramAnonymousList.next();
        } while (!"Basic".equalsIgnoreCase(((OkAuthenticator.Challenge)localObject).getScheme()));
        localObject = Authenticator.requestPasswordAuthentication(paramAnonymousURL.getHost(), getConnectToInetAddress(paramAnonymousProxy, paramAnonymousURL), paramAnonymousURL.getPort(), paramAnonymousURL.getProtocol(), ((OkAuthenticator.Challenge)localObject).getRealm(), ((OkAuthenticator.Challenge)localObject).getScheme(), paramAnonymousURL, Authenticator.RequestorType.SERVER);
      } while (localObject == null);
      return OkAuthenticator.Credential.basic(((PasswordAuthentication)localObject).getUserName(), new String(((PasswordAuthentication)localObject).getPassword()));
    }
    
    public OkAuthenticator.Credential authenticateProxy(Proxy paramAnonymousProxy, URL paramAnonymousURL, List<OkAuthenticator.Challenge> paramAnonymousList)
      throws IOException
    {
      paramAnonymousList = paramAnonymousList.iterator();
      Object localObject;
      do
      {
        do
        {
          if (!paramAnonymousList.hasNext()) {
            return null;
          }
          localObject = (OkAuthenticator.Challenge)paramAnonymousList.next();
        } while (!"Basic".equalsIgnoreCase(((OkAuthenticator.Challenge)localObject).getScheme()));
        InetSocketAddress localInetSocketAddress = (InetSocketAddress)paramAnonymousProxy.address();
        localObject = Authenticator.requestPasswordAuthentication(localInetSocketAddress.getHostName(), getConnectToInetAddress(paramAnonymousProxy, paramAnonymousURL), localInetSocketAddress.getPort(), paramAnonymousURL.getProtocol(), ((OkAuthenticator.Challenge)localObject).getRealm(), ((OkAuthenticator.Challenge)localObject).getScheme(), paramAnonymousURL, Authenticator.RequestorType.PROXY);
      } while (localObject == null);
      return OkAuthenticator.Credential.basic(((PasswordAuthentication)localObject).getUserName(), new String(((PasswordAuthentication)localObject).getPassword()));
    }
  };
  
  private static List<OkAuthenticator.Challenge> parseChallenges(RawHeaders paramRawHeaders, String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    if (i >= paramRawHeaders.length()) {
      return localArrayList;
    }
    if (!paramString.equalsIgnoreCase(paramRawHeaders.getFieldName(i))) {}
    label175:
    for (;;)
    {
      i += 1;
      break;
      String str1 = paramRawHeaders.getValue(i);
      int j = 0;
      for (;;)
      {
        if (j >= str1.length()) {
          break label175;
        }
        int k = HeaderParser.skipUntil(str1, j, " ");
        String str2 = str1.substring(j, k).trim();
        j = HeaderParser.skipWhitespace(str1, k);
        if (!str1.regionMatches(true, j, "realm=\"", 0, "realm=\"".length())) {
          break;
        }
        j += "realm=\"".length();
        k = HeaderParser.skipUntil(str1, j, "\"");
        String str3 = str1.substring(j, k);
        j = HeaderParser.skipWhitespace(str1, HeaderParser.skipUntil(str1, k + 1, ",") + 1);
        localArrayList.add(new OkAuthenticator.Challenge(str2, str3));
      }
    }
  }
  
  public static boolean processAuthHeader(OkAuthenticator paramOkAuthenticator, int paramInt, RawHeaders paramRawHeaders1, RawHeaders paramRawHeaders2, Proxy paramProxy, URL paramURL)
    throws IOException
  {
    Object localObject;
    String str;
    if (paramInt == 401)
    {
      localObject = "WWW-Authenticate";
      str = "Authorization";
      localObject = parseChallenges(paramRawHeaders1, (String)localObject);
      if (!((List)localObject).isEmpty()) {
        break label61;
      }
    }
    for (;;)
    {
      return false;
      if (paramInt == 407)
      {
        localObject = "Proxy-Authenticate";
        str = "Proxy-Authorization";
        break;
      }
      throw new IllegalArgumentException();
      label61:
      if (paramRawHeaders1.getResponseCode() == 407) {}
      for (paramOkAuthenticator = paramOkAuthenticator.authenticateProxy(paramProxy, paramURL, (List)localObject); paramOkAuthenticator != null; paramOkAuthenticator = paramOkAuthenticator.authenticate(paramProxy, paramURL, (List)localObject))
      {
        paramRawHeaders2.set(str, paramOkAuthenticator.getHeaderValue());
        return true;
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.squareup.okhttp.internal.http.HttpAuthenticator
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */