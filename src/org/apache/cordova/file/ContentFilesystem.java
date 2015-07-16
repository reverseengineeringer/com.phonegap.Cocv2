package org.apache.cordova.file;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore.Images.Media;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaResourceApi;
import org.apache.cordova.CordovaResourceApi.OpenForReadResult;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginManager;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ContentFilesystem
  extends Filesystem
{
  private CordovaInterface cordova;
  private CordovaResourceApi resourceApi;
  
  public ContentFilesystem(String paramString, CordovaInterface paramCordovaInterface, CordovaWebView paramCordovaWebView)
  {
    name = paramString;
    cordova = paramCordovaInterface;
    Class localClass = paramCordovaWebView.getClass();
    paramString = null;
    try
    {
      paramCordovaInterface = (PluginManager)localClass.getMethod("getPluginManager", new Class[0]).invoke(paramCordovaWebView, new Object[0]);
      paramString = paramCordovaInterface;
    }
    catch (InvocationTargetException paramCordovaInterface)
    {
      for (;;) {}
    }
    catch (IllegalAccessException paramCordovaInterface)
    {
      for (;;) {}
    }
    catch (NoSuchMethodException paramCordovaInterface)
    {
      for (;;) {}
    }
    paramCordovaInterface = paramString;
    if (paramString == null) {}
    try
    {
      paramCordovaInterface = (PluginManager)localClass.getField("pluginManager").get(paramCordovaWebView);
      resourceApi = new CordovaResourceApi(paramCordovaWebView.getContext(), paramCordovaInterface);
      return;
    }
    catch (IllegalAccessException paramCordovaInterface)
    {
      for (;;)
      {
        paramCordovaInterface = paramString;
      }
    }
    catch (NoSuchFieldException paramCordovaInterface)
    {
      for (;;)
      {
        paramCordovaInterface = paramString;
      }
    }
  }
  
  public LocalFilesystemURL URLforFilesystemPath(String paramString)
  {
    return null;
  }
  
  public boolean canRemoveFileAtLocalURL(LocalFilesystemURL paramLocalFilesystemURL)
  {
    return new File(filesystemPathForURL(paramLocalFilesystemURL)).exists();
  }
  
  public JSONObject copyFileToURL(LocalFilesystemURL paramLocalFilesystemURL1, String paramString, Filesystem paramFilesystem, LocalFilesystemURL paramLocalFilesystemURL2, boolean paramBoolean)
    throws IOException, InvalidModificationException, JSONException, NoModificationAllowedException, FileExistsException
  {
    if (LocalFilesystem.class.isInstance(paramFilesystem))
    {
      paramString = makeDestinationURL(paramString, paramLocalFilesystemURL2, paramLocalFilesystemURL1);
      paramLocalFilesystemURL1 = resourceApi.openOutputStream(URL);
      CordovaResourceApi.OpenForReadResult localOpenForReadResult = resourceApi.openForRead(URL);
      if ((paramBoolean) && (!paramFilesystem.canRemoveFileAtLocalURL(paramLocalFilesystemURL2))) {
        throw new NoModificationAllowedException("Cannot move file at source URL");
      }
      try
      {
        resourceApi.copyResource(localOpenForReadResult, paramLocalFilesystemURL1);
        if (paramBoolean) {
          paramFilesystem.removeFileAtLocalURL(paramLocalFilesystemURL2);
        }
        return makeEntryForURL(paramString, Boolean.valueOf(false), URL.toString());
      }
      catch (IOException paramLocalFilesystemURL1)
      {
        throw new IOException("Cannot read file at source URL");
      }
    }
    return super.copyFileToURL(paramLocalFilesystemURL1, paramString, paramFilesystem, paramLocalFilesystemURL2, paramBoolean);
  }
  
  protected String filesystemPathForCursor(Cursor paramCursor)
  {
    int i = paramCursor.getColumnIndex(new String[] { "_data" }[0]);
    if (i != -1) {
      return paramCursor.getString(i);
    }
    return null;
  }
  
  public String filesystemPathForURL(LocalFilesystemURL paramLocalFilesystemURL)
  {
    paramLocalFilesystemURL = openCursorForURL(paramLocalFilesystemURL);
    if (paramLocalFilesystemURL != null) {}
    try
    {
      if (paramLocalFilesystemURL.moveToFirst())
      {
        String str = filesystemPathForCursor(paramLocalFilesystemURL);
        return str;
      }
      return null;
    }
    finally
    {
      if (paramLocalFilesystemURL != null) {
        paramLocalFilesystemURL.close();
      }
    }
  }
  
  public JSONObject getEntryForLocalURL(LocalFilesystemURL paramLocalFilesystemURL)
    throws IOException
  {
    if ("/".equals(fullPath)) {
      try
      {
        paramLocalFilesystemURL = LocalFilesystem.makeEntryForURL(paramLocalFilesystemURL, Boolean.valueOf(true), URL.toString());
        return paramLocalFilesystemURL;
      }
      catch (JSONException paramLocalFilesystemURL)
      {
        throw new IOException();
      }
    }
    Object localObject = openCursorForURL(paramLocalFilesystemURL);
    if (localObject != null) {}
    try
    {
      if (!((Cursor)localObject).moveToFirst()) {
        throw new FileNotFoundException();
      }
    }
    finally
    {
      if (localObject != null) {
        ((Cursor)localObject).close();
      }
      throw paramLocalFilesystemURL;
      String str = filesystemPathForCursor((Cursor)localObject);
      if (localObject != null) {
        ((Cursor)localObject).close();
      }
      if (str == null) {
        localObject = URL.toString();
      }
      try
      {
        paramLocalFilesystemURL = makeEntryForPath(fullPath, filesystemName, Boolean.valueOf(false), (String)localObject);
        return paramLocalFilesystemURL;
      }
      catch (JSONException paramLocalFilesystemURL)
      {
        throw new IOException();
      }
    }
  }
  
  public JSONObject getFileForLocalURL(LocalFilesystemURL paramLocalFilesystemURL, String paramString, JSONObject paramJSONObject, boolean paramBoolean)
    throws IOException, TypeMismatchException, JSONException
  {
    if ((paramJSONObject != null) && (paramJSONObject.optBoolean("create"))) {
      throw new IOException("Cannot create content url");
    }
    paramLocalFilesystemURL = new LocalFilesystemURL(Uri.withAppendedPath(URL, paramString));
    paramString = new File(filesystemPathForURL(paramLocalFilesystemURL));
    if (!paramString.exists()) {
      throw new FileNotFoundException("path does not exist");
    }
    if (paramBoolean)
    {
      if (paramString.isFile()) {
        throw new TypeMismatchException("path doesn't exist or is file");
      }
    }
    else if (paramString.isDirectory()) {
      throw new TypeMismatchException("path doesn't exist or is directory");
    }
    return makeEntryForPath(fullPath, filesystemName, Boolean.valueOf(paramBoolean), Uri.fromFile(paramString).toString());
  }
  
  public JSONObject getFileMetadataForLocalURL(LocalFilesystemURL paramLocalFilesystemURL)
    throws FileNotFoundException
  {
    Object localObject = openCursorForURL(paramLocalFilesystemURL);
    if (localObject != null) {}
    try
    {
      if (((Cursor)localObject).moveToFirst())
      {
        localInteger1 = resourceSizeForCursor((Cursor)localObject);
        localInteger2 = lastModifiedDateForCursor((Cursor)localObject);
        if (localObject != null) {
          ((Cursor)localObject).close();
        }
        localObject = new JSONObject();
      }
    }
    finally
    {
      Integer localInteger1;
      Integer localInteger2;
      if (localObject != null) {
        ((Cursor)localObject).close();
      }
    }
    try
    {
      ((JSONObject)localObject).put("size", localInteger1);
      ((JSONObject)localObject).put("type", resourceApi.getMimeType(URL));
      ((JSONObject)localObject).put("name", filesystemName);
      ((JSONObject)localObject).put("fullPath", fullPath);
      ((JSONObject)localObject).put("lastModifiedDate", localInteger2);
      return (JSONObject)localObject;
    }
    catch (JSONException paramLocalFilesystemURL) {}
    throw new FileNotFoundException();
    return null;
  }
  
  OutputStream getOutputStreamForURL(LocalFilesystemURL paramLocalFilesystemURL)
    throws IOException
  {
    return resourceApi.openOutputStream(URL);
  }
  
  protected Integer lastModifiedDateForCursor(Cursor paramCursor)
  {
    int i = paramCursor.getColumnIndex(new String[] { "date_modified" }[0]);
    if (i != -1)
    {
      paramCursor = paramCursor.getString(i);
      if (paramCursor != null) {
        return Integer.valueOf(Integer.parseInt(paramCursor, 10));
      }
    }
    return null;
  }
  
  protected Cursor openCursorForURL(LocalFilesystemURL paramLocalFilesystemURL)
  {
    return cordova.getActivity().getContentResolver().query(URL, null, null, null, null);
  }
  
  public JSONArray readEntriesAtLocalURL(LocalFilesystemURL paramLocalFilesystemURL)
    throws FileNotFoundException
  {
    return null;
  }
  
  public void readFileAtURL(LocalFilesystemURL paramLocalFilesystemURL, long paramLong1, long paramLong2, Filesystem.ReadFileCallback paramReadFileCallback)
    throws IOException
  {
    paramLocalFilesystemURL = resourceApi.openForRead(URL);
    long l = paramLong2;
    if (paramLong2 < 0L) {
      l = length;
    }
    if (paramLong1 > 0L) {}
    try
    {
      inputStream.skip(paramLong1);
      paramReadFileCallback.handleData(new Filesystem.LimitedInputStream(this, inputStream, l - paramLong1), mimeType);
      return;
    }
    finally
    {
      inputStream.close();
    }
  }
  
  public boolean recursiveRemoveFileAtLocalURL(LocalFilesystemURL paramLocalFilesystemURL)
    throws NoModificationAllowedException
  {
    throw new NoModificationAllowedException("Cannot remove content url");
  }
  
  public boolean removeFileAtLocalURL(LocalFilesystemURL paramLocalFilesystemURL)
    throws NoModificationAllowedException
  {
    String str = filesystemPathForURL(paramLocalFilesystemURL);
    paramLocalFilesystemURL = new File(str);
    try
    {
      cordova.getActivity().getContentResolver().delete(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "_data = ?", new String[] { str });
      return paramLocalFilesystemURL.delete();
    }
    catch (UnsupportedOperationException localUnsupportedOperationException)
    {
      for (;;) {}
    }
  }
  
  protected Integer resourceSizeForCursor(Cursor paramCursor)
  {
    int i = paramCursor.getColumnIndex("_size");
    if (i != -1)
    {
      paramCursor = paramCursor.getString(i);
      if (paramCursor != null) {
        return Integer.valueOf(Integer.parseInt(paramCursor, 10));
      }
    }
    return null;
  }
  
  public long truncateFileAtURL(LocalFilesystemURL paramLocalFilesystemURL, long paramLong)
    throws NoModificationAllowedException
  {
    throw new NoModificationAllowedException("Couldn't truncate file given its content URI");
  }
  
  public long writeToFileAtURL(LocalFilesystemURL paramLocalFilesystemURL, String paramString, int paramInt, boolean paramBoolean)
    throws NoModificationAllowedException
  {
    throw new NoModificationAllowedException("Couldn't write to file given its content URI");
  }
}

/* Location:
 * Qualified Name:     org.apache.cordova.file.ContentFilesystem
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */