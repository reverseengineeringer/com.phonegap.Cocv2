package com.squareup.okhttp;

import java.nio.charset.Charset;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class MediaType
{
  private static final Pattern PARAMETER = Pattern.compile(";\\s*([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)=(?:([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)|\"([^\"]*)\")");
  private static final String QUOTED = "\"([^\"]*)\"";
  private static final String TOKEN = "([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)";
  private static final Pattern TYPE_SUBTYPE = Pattern.compile("([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)/([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)");
  private final String charset;
  private final String mediaType;
  private final String subtype;
  private final String type;
  
  private MediaType(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    mediaType = paramString1;
    type = paramString2;
    subtype = paramString3;
    charset = paramString4;
  }
  
  public static MediaType parse(String paramString)
  {
    Object localObject2 = TYPE_SUBTYPE.matcher(paramString);
    if (!((Matcher)localObject2).lookingAt()) {
      return null;
    }
    String str1 = ((Matcher)localObject2).group(1).toLowerCase(Locale.US);
    String str2 = ((Matcher)localObject2).group(2).toLowerCase(Locale.US);
    Object localObject1 = null;
    Matcher localMatcher = PARAMETER.matcher(paramString);
    int i = ((Matcher)localObject2).end();
    for (;;)
    {
      if (i >= paramString.length()) {
        return new MediaType(paramString, str1, str2, (String)localObject1);
      }
      localMatcher.region(i, paramString.length());
      if (!localMatcher.lookingAt()) {
        break;
      }
      String str3 = localMatcher.group(1);
      localObject2 = localObject1;
      if (str3 != null)
      {
        if (str3.equalsIgnoreCase("charset")) {
          break label138;
        }
        localObject2 = localObject1;
      }
      i = localMatcher.end();
      localObject1 = localObject2;
    }
    label138:
    if (localObject1 != null) {
      throw new IllegalArgumentException("Multiple charsets: " + paramString);
    }
    if (localMatcher.group(2) != null) {}
    for (localObject1 = localMatcher.group(2);; localObject1 = localMatcher.group(3))
    {
      localObject2 = localObject1;
      break;
    }
  }
  
  public Charset charset()
  {
    if (charset != null) {
      return Charset.forName(charset);
    }
    return null;
  }
  
  public Charset charset(Charset paramCharset)
  {
    if (charset != null) {
      paramCharset = Charset.forName(charset);
    }
    return paramCharset;
  }
  
  public boolean equals(Object paramObject)
  {
    return ((paramObject instanceof MediaType)) && (mediaType.equals(mediaType));
  }
  
  public int hashCode()
  {
    return mediaType.hashCode();
  }
  
  public String subtype()
  {
    return subtype;
  }
  
  public String toString()
  {
    return mediaType;
  }
  
  public String type()
  {
    return type;
  }
}

/* Location:
 * Qualified Name:     com.squareup.okhttp.MediaType
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */