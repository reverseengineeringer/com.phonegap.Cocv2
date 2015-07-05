package com.squareup.okhttp;

import com.squareup.okhttp.internal.Base64;
import java.io.UnsupportedEncodingException;

public final class OkAuthenticator$Credential
{
  private final String headerValue;
  
  private OkAuthenticator$Credential(String paramString)
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

/* Location:
 * Qualified Name:     com.squareup.okhttp.OkAuthenticator.Credential
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */