package com.squareup.okhttp;

public final class OkAuthenticator$Challenge
{
  private final String realm;
  private final String scheme;
  
  public OkAuthenticator$Challenge(String paramString1, String paramString2)
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

/* Location:
 * Qualified Name:     com.squareup.okhttp.OkAuthenticator.Challenge
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */