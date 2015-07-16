package org.apache.cordova;

import android.net.Uri;
import java.net.MalformedURLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Whitelist$URLPattern
{
  public Pattern host;
  public Pattern path;
  public Integer port;
  public Pattern scheme;
  
  public Whitelist$URLPattern(String paramString1, String paramString2, String paramString3, String paramString4)
    throws MalformedURLException
  {
    if (paramString1 != null) {}
    for (;;)
    {
      try
      {
        if ("*".equals(paramString1))
        {
          scheme = null;
          if ("*".equals(paramString2))
          {
            host = null;
            if ((paramString3 != null) && (!"*".equals(paramString3))) {
              break label168;
            }
            port = null;
            if ((paramString4 != null) && (!"/*".equals(paramString4))) {
              break;
            }
            path = null;
          }
        }
        else
        {
          scheme = Pattern.compile(regexFromPattern(paramString1, false), 2);
          continue;
        }
        if (!paramString2.startsWith("*.")) {
          break label151;
        }
      }
      catch (NumberFormatException paramString1)
      {
        throw new MalformedURLException("Port must be a number");
      }
      host = Pattern.compile("([a-z0-9.-]*\\.)?" + regexFromPattern(paramString2.substring(2), false), 2);
      continue;
      label151:
      host = Pattern.compile(regexFromPattern(paramString2, false), 2);
      continue;
      label168:
      port = Integer.valueOf(Integer.parseInt(paramString3, 10));
    }
    path = Pattern.compile(regexFromPattern(paramString4, true));
  }
  
  private String regexFromPattern(String paramString, boolean paramBoolean)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    if (i < paramString.length())
    {
      char c = paramString.charAt(i);
      if ((c == '*') && (paramBoolean)) {
        localStringBuilder.append(".");
      }
      for (;;)
      {
        localStringBuilder.append(c);
        i += 1;
        break;
        if ("\\.[]{}()^$?+|".indexOf(c) > -1) {
          localStringBuilder.append('\\');
        }
      }
    }
    return localStringBuilder.toString();
  }
  
  public boolean matches(Uri paramUri)
  {
    boolean bool2 = false;
    try
    {
      boolean bool1;
      if (scheme != null)
      {
        bool1 = bool2;
        if (!scheme.matcher(paramUri.getScheme()).matches()) {}
      }
      else if (host != null)
      {
        bool1 = bool2;
        if (!host.matcher(paramUri.getHost()).matches()) {}
      }
      else if (port != null)
      {
        bool1 = bool2;
        if (!port.equals(Integer.valueOf(paramUri.getPort()))) {}
      }
      else if (path != null)
      {
        boolean bool3 = path.matcher(paramUri.getPath()).matches();
        bool1 = bool2;
        if (!bool3) {}
      }
      else
      {
        bool1 = true;
      }
      return bool1;
    }
    catch (Exception paramUri)
    {
      LOG.d("Whitelist", paramUri.toString());
    }
    return false;
  }
}

/* Location:
 * Qualified Name:     org.apache.cordova.Whitelist.URLPattern
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */