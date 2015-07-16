package org.apache.cordova;

import android.net.Uri;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Whitelist
{
  public static final String TAG = "Whitelist";
  private ArrayList<URLPattern> whiteList = new ArrayList();
  
  public void addWhiteListEntry(String paramString, boolean paramBoolean)
  {
    if (whiteList != null) {}
    for (;;)
    {
      Object localObject;
      String str4;
      String str3;
      try
      {
        if (paramString.compareTo("*") == 0)
        {
          LOG.d("Whitelist", "Unlimited access to network resources");
          whiteList = null;
          return;
        }
        localObject = Pattern.compile("^((\\*|[A-Za-z-]+):(//)?)?(\\*|((\\*\\.)?[^*/:]+))?(:(\\d+))?(/.*)?").matcher(paramString);
        if (!((Matcher)localObject).matches()) {
          break label197;
        }
        str4 = ((Matcher)localObject).group(2);
        str3 = ((Matcher)localObject).group(4);
        if ("file".equals(str4)) {
          break label198;
        }
        String str1 = str3;
        if ("content".equals(str4)) {
          break label198;
        }
        str3 = ((Matcher)localObject).group(8);
        localObject = ((Matcher)localObject).group(9);
        if (str4 == null)
        {
          whiteList.add(new URLPattern("http", str1, str3, (String)localObject));
          whiteList.add(new URLPattern("https", str1, str3, (String)localObject));
          return;
        }
      }
      catch (Exception localException)
      {
        LOG.d("Whitelist", "Failed to add origin %s", new Object[] { paramString });
        return;
      }
      whiteList.add(new URLPattern(str4, localException, str3, (String)localObject));
      label197:
      return;
      label198:
      String str2 = str3;
      if (str3 == null) {
        str2 = "*";
      }
    }
  }
  
  public boolean isUrlWhiteListed(String paramString)
  {
    if (whiteList == null) {
      return true;
    }
    paramString = Uri.parse(paramString);
    Iterator localIterator = whiteList.iterator();
    while (localIterator.hasNext()) {
      if (((URLPattern)localIterator.next()).matches(paramString)) {
        return true;
      }
    }
    return false;
  }
  
  private static class URLPattern
  {
    public Pattern host;
    public Pattern path;
    public Integer port;
    public Pattern scheme;
    
    public URLPattern(String paramString1, String paramString2, String paramString3, String paramString4)
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
}

/* Location:
 * Qualified Name:     org.apache.cordova.Whitelist
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */