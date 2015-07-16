package org.apache.cordova.file;

import android.net.Uri;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class Filesystem
{
  public String name;
  
  public static JSONObject makeEntryForPath(String paramString1, String paramString2, Boolean paramBoolean, String paramString3)
    throws JSONException
  {
    int j = 0;
    JSONObject localJSONObject = new JSONObject();
    boolean bool;
    if (paramString1.endsWith("/"))
    {
      i = 1;
      Object localObject = paramString1.substring(0, paramString1.length() - i).split("/+");
      localObject = localObject[(localObject.length - 1)];
      if (paramBoolean.booleanValue()) {
        break label191;
      }
      bool = true;
      label63:
      localJSONObject.put("isFile", bool);
      localJSONObject.put("isDirectory", paramBoolean);
      localJSONObject.put("name", localObject);
      localJSONObject.put("fullPath", paramString1);
      localJSONObject.put("filesystemName", paramString2);
      if (!"temporary".equals(paramString2)) {
        break label197;
      }
    }
    label191:
    label197:
    for (int i = j;; i = 1)
    {
      localJSONObject.put("filesystem", i);
      paramString1 = paramString3;
      if (paramBoolean.booleanValue())
      {
        paramString1 = paramString3;
        if (!paramString3.endsWith("/")) {
          paramString1 = paramString3 + "/";
        }
      }
      localJSONObject.put("nativeURL", paramString1);
      return localJSONObject;
      i = 0;
      break;
      bool = false;
      break label63;
    }
  }
  
  public static JSONObject makeEntryForURL(LocalFilesystemURL paramLocalFilesystemURL, Boolean paramBoolean, String paramString)
    throws JSONException
  {
    return makeEntryForPath(fullPath, filesystemName, paramBoolean, paramString);
  }
  
  abstract LocalFilesystemURL URLforFilesystemPath(String paramString);
  
  abstract boolean canRemoveFileAtLocalURL(LocalFilesystemURL paramLocalFilesystemURL);
  
  JSONObject copyFileToURL(final LocalFilesystemURL paramLocalFilesystemURL1, String paramString, Filesystem paramFilesystem, LocalFilesystemURL paramLocalFilesystemURL2, boolean paramBoolean)
    throws IOException, InvalidModificationException, JSONException, NoModificationAllowedException, FileExistsException
  {
    if ((!paramBoolean) || (paramFilesystem.canRemoveFileAtLocalURL(paramLocalFilesystemURL2)))
    {
      paramLocalFilesystemURL1 = makeDestinationURL(paramString, paramLocalFilesystemURL2, paramLocalFilesystemURL1);
      paramFilesystem.readFileAtURL(paramLocalFilesystemURL2, 0L, -1L, new ReadFileCallback()
      {
        public void handleData(InputStream paramAnonymousInputStream, String paramAnonymousString)
          throws IOException
        {
          if (paramAnonymousInputStream != null)
          {
            paramAnonymousString = getOutputStreamForURL(paramLocalFilesystemURL1);
            byte[] arrayOfByte = new byte[' '];
            for (;;)
            {
              int i = paramAnonymousInputStream.read(arrayOfByte, 0, 8192);
              if (i <= 0)
              {
                paramAnonymousString.close();
                return;
              }
              paramAnonymousString.write(arrayOfByte, 0, i);
            }
          }
          throw new IOException("Cannot read file at source URL");
        }
      });
      if (paramBoolean) {
        paramFilesystem.removeFileAtLocalURL(paramLocalFilesystemURL2);
      }
      return getEntryForLocalURL(paramLocalFilesystemURL1);
    }
    throw new NoModificationAllowedException("Cannot move file at source URL");
  }
  
  abstract String filesystemPathForURL(LocalFilesystemURL paramLocalFilesystemURL);
  
  abstract JSONObject getEntryForLocalURL(LocalFilesystemURL paramLocalFilesystemURL)
    throws IOException;
  
  abstract JSONObject getFileForLocalURL(LocalFilesystemURL paramLocalFilesystemURL, String paramString, JSONObject paramJSONObject, boolean paramBoolean)
    throws FileExistsException, IOException, TypeMismatchException, EncodingException, JSONException;
  
  abstract JSONObject getFileMetadataForLocalURL(LocalFilesystemURL paramLocalFilesystemURL)
    throws FileNotFoundException;
  
  abstract OutputStream getOutputStreamForURL(LocalFilesystemURL paramLocalFilesystemURL)
    throws IOException;
  
  public JSONObject getParentForLocalURL(LocalFilesystemURL paramLocalFilesystemURL)
    throws IOException
  {
    LocalFilesystemURL localLocalFilesystemURL = new LocalFilesystemURL(URL);
    if ((!"".equals(fullPath)) && (!"/".equals(fullPath)))
    {
      int i = fullPath.replaceAll("/+$", "").lastIndexOf('/');
      fullPath = fullPath.substring(0, i + 1);
    }
    return getEntryForLocalURL(localLocalFilesystemURL);
  }
  
  protected LocalFilesystemURL makeDestinationURL(String paramString, LocalFilesystemURL paramLocalFilesystemURL1, LocalFilesystemURL paramLocalFilesystemURL2)
  {
    String str;
    if (!"null".equals(paramString))
    {
      str = paramString;
      if (!"".equals(paramString)) {}
    }
    else
    {
      str = URL.getLastPathSegment();
    }
    paramString = URL.toString();
    if (paramString.endsWith("/")) {}
    for (paramString = paramString + str;; paramString = paramString + "/" + str) {
      return new LocalFilesystemURL(paramString);
    }
  }
  
  public JSONObject makeEntryForFile(File paramFile)
    throws JSONException
  {
    return null;
  }
  
  abstract JSONArray readEntriesAtLocalURL(LocalFilesystemURL paramLocalFilesystemURL)
    throws FileNotFoundException;
  
  abstract void readFileAtURL(LocalFilesystemURL paramLocalFilesystemURL, long paramLong1, long paramLong2, ReadFileCallback paramReadFileCallback)
    throws IOException;
  
  abstract boolean recursiveRemoveFileAtLocalURL(LocalFilesystemURL paramLocalFilesystemURL)
    throws FileExistsException, NoModificationAllowedException;
  
  abstract boolean removeFileAtLocalURL(LocalFilesystemURL paramLocalFilesystemURL)
    throws InvalidModificationException, NoModificationAllowedException;
  
  abstract long truncateFileAtURL(LocalFilesystemURL paramLocalFilesystemURL, long paramLong)
    throws IOException, NoModificationAllowedException;
  
  abstract long writeToFileAtURL(LocalFilesystemURL paramLocalFilesystemURL, String paramString, int paramInt, boolean paramBoolean)
    throws NoModificationAllowedException, IOException;
  
  protected class LimitedInputStream
    extends FilterInputStream
  {
    long numBytesToRead;
    
    public LimitedInputStream(InputStream paramInputStream, long paramLong)
    {
      super();
      numBytesToRead = paramLong;
    }
    
    public int read()
      throws IOException
    {
      if (numBytesToRead <= 0L) {
        return -1;
      }
      numBytesToRead -= 1L;
      return in.read();
    }
    
    public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
      throws IOException
    {
      if (numBytesToRead <= 0L) {
        return -1;
      }
      int i = paramInt2;
      if (paramInt2 > numBytesToRead) {
        i = (int)numBytesToRead;
      }
      paramInt1 = in.read(paramArrayOfByte, paramInt1, i);
      numBytesToRead -= paramInt1;
      return paramInt1;
    }
  }
  
  public static abstract interface ReadFileCallback
  {
    public abstract void handleData(InputStream paramInputStream, String paramString)
      throws IOException;
  }
}

/* Location:
 * Qualified Name:     org.apache.cordova.file.Filesystem
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */