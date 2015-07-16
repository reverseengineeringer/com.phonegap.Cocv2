package org.apache.cordova.file;

import android.net.Uri;
import java.util.List;

public class LocalFilesystemURL
{
  public static final String FILESYSTEM_PROTOCOL = "cdvfile";
  Uri URL;
  String filesystemName;
  String fullPath;
  
  public LocalFilesystemURL(Uri paramUri)
  {
    URL = paramUri;
    filesystemName = filesystemNameForLocalURL(paramUri);
    fullPath = fullPathForLocalURL(paramUri);
  }
  
  public LocalFilesystemURL(String paramString)
  {
    this(Uri.parse(paramString));
  }
  
  private String filesystemNameForLocalURL(Uri paramUri)
  {
    Object localObject2 = null;
    Object localObject1;
    if (("cdvfile".equals(paramUri.getScheme())) && ("localhost".equals(paramUri.getHost())))
    {
      paramUri = paramUri.getPathSegments();
      localObject1 = localObject2;
      if (paramUri != null)
      {
        localObject1 = localObject2;
        if (paramUri.size() > 0) {
          localObject1 = (String)paramUri.get(0);
        }
      }
    }
    do
    {
      return (String)localObject1;
      localObject1 = localObject2;
    } while (!"content".equals(paramUri.getScheme()));
    return "content";
  }
  
  private String fullPathForLocalURL(Uri paramUri)
  {
    if (("cdvfile".equals(paramUri.getScheme())) && ("localhost".equals(paramUri.getHost())))
    {
      String str2 = paramUri.getPath();
      String str1 = str2;
      if (paramUri.getQuery() != null) {
        str1 = str2 + "?" + paramUri.getQuery();
      }
      return str1.substring(str1.indexOf('/', 1));
    }
    if ("content".equals(paramUri.getScheme())) {
      return Uri.encode('/' + paramUri.getHost() + paramUri.getPath(), "/");
    }
    return null;
  }
  
  public String toString()
  {
    return "cdvfile://localhost/" + filesystemName + fullPath;
  }
}

/* Location:
 * Qualified Name:     org.apache.cordova.file.LocalFilesystemURL
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */