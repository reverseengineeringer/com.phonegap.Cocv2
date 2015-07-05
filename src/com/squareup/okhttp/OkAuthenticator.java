package com.squareup.okhttp;

import com.squareup.okhttp.internal.Base64;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.Proxy;
import java.net.URL;
import java.util.List;

public abstract interface OkAuthenticator
{
  public abstract Credential authenticate(Proxy paramProxy, URL paramURL, List<Challenge> paramList)
    throws IOException;
  
  public abstract Credential authenticateProxy(Proxy paramProxy, URL paramURL, List<Challenge> paramList)
    throws IOException;
  
  public static final class Challenge
  {
    private final String realm;
    private final String scheme;
    
    public Challenge(String paramString1, String paramString2)
    {
      scheme = paramString1;
      realm = paramString2;
    }
    
    public boolean equals(Object paramObject)
    {
      return ((paramObject instanceof Challenge)) && (scheme.equals(scheme)) && (realm.equals(realm));
    }
    
    public String getRealm()
    {
      return realm;
    }
    
    public String getScheme()
    {
      return scheme;
    }
    
    public int hashCode()
    {
      return scheme.hashCode() + realm.hashCode() * 31;
    }
    
    public String toString()
    {
      return scheme + " realm=\"" + realm + "\"";
    }
  }
  
  public static final class Credential
  {
    private final String headerValue;
    
    private Credential(String paramString)
    {
      headerValue = paramString;
    }
    
    public static Credential basic(String paramString1, String paramString2)
    {
      try
      {
        paramString1 = Base64.encode((paramString1 + ":" + paramString2).getBytes("ISO-8859-1"));
        paramString1 = new Credential("Basic " + paramString1);
        return paramString1;
      }
      catch (UnsupportedEncodingException paramString1)
      {
        throw new AssertionError();
      }
    }
    
    public boolean equals(Object paramObject)
    {
      return ((paramObject instanceof Credential)) && (headerValue.equals(headerValue));
    }
    
    public String getHeaderValue()
    {
      return headerValue;
    }
    
    public int hashCode()
    {
      return headerValue.hashCode();
    }
    
    public String toString()
    {
      return headerValue;
    }
  }
}

/* Location:
 * Qualified Name:     com.squareup.okhttp.OkAuthenticator
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */