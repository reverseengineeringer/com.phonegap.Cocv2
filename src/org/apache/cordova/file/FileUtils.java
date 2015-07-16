package org.apache.cordova.file;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FileUtils
  extends CordovaPlugin
{
  public static int ABORT_ERR = 0;
  public static int ENCODING_ERR = 0;
  public static int INVALID_MODIFICATION_ERR = 0;
  public static int INVALID_STATE_ERR = 0;
  private static final String LOG_TAG = "FileUtils";
  public static int NOT_FOUND_ERR = 1;
  public static int NOT_READABLE_ERR;
  public static int NO_MODIFICATION_ALLOWED_ERR;
  public static int PATH_EXISTS_ERR = 12;
  public static int QUOTA_EXCEEDED_ERR;
  public static int SECURITY_ERR = 2;
  public static int SYNTAX_ERR;
  public static int TYPE_MISMATCH_ERR;
  public static int UNKNOWN_ERR = 1000;
  private static FileUtils filePlugin;
  private boolean configured = false;
  private ArrayList<Filesystem> filesystems;
  
  static
  {
    ABORT_ERR = 3;
    NOT_READABLE_ERR = 4;
    ENCODING_ERR = 5;
    NO_MODIFICATION_ALLOWED_ERR = 6;
    INVALID_STATE_ERR = 7;
    SYNTAX_ERR = 8;
    INVALID_MODIFICATION_ERR = 9;
    QUOTA_EXCEEDED_ERR = 10;
    TYPE_MISMATCH_ERR = 11;
  }
  
  private Filesystem filesystemForName(String paramString)
  {
    Iterator localIterator = filesystems.iterator();
    while (localIterator.hasNext())
    {
      Filesystem localFilesystem = (Filesystem)localIterator.next();
      if ((localFilesystem != null) && (name != null) && (name.equals(paramString))) {
        return localFilesystem;
      }
    }
    return null;
  }
  
  private Filesystem filesystemForURL(LocalFilesystemURL paramLocalFilesystemURL)
  {
    if (paramLocalFilesystemURL == null) {
      return null;
    }
    return filesystemForName(filesystemName);
  }
  
  @Deprecated
  public static JSONObject getEntry(File paramFile)
    throws JSONException
  {
    if (getFilePlugin() != null) {
      return getFilePlugin().getEntryForFile(paramFile);
    }
    return null;
  }
  
  private JSONObject getFile(String paramString1, String paramString2, JSONObject paramJSONObject, boolean paramBoolean)
    throws FileExistsException, IOException, TypeMismatchException, EncodingException, JSONException
  {
    Filesystem localFilesystem;
    try
    {
      paramString1 = new LocalFilesystemURL(paramString1);
      localFilesystem = filesystemForURL(paramString1);
      if (localFilesystem == null) {
        throw new MalformedURLException("No installed handlers for this URL");
      }
    }
    catch (IllegalArgumentException paramString1)
    {
      throw new MalformedURLException("Unrecognized filesystem URL");
    }
    paramString1 = localFilesystem.getFileForLocalURL(paramString1, paramString2, paramJSONObject, paramBoolean);
    return paramString1;
  }
  
  private JSONObject getFileMetadata(String paramString)
    throws FileNotFoundException, JSONException, MalformedURLException
  {
    Filesystem localFilesystem;
    try
    {
      paramString = new LocalFilesystemURL(paramString);
      localFilesystem = filesystemForURL(paramString);
      if (localFilesystem == null) {
        throw new MalformedURLException("No installed handlers for this URL");
      }
    }
    catch (IllegalArgumentException paramString)
    {
      throw new MalformedURLException("Unrecognized filesystem URL");
    }
    paramString = localFilesystem.getFileMetadataForLocalURL(paramString);
    return paramString;
  }
  
  public static FileUtils getFilePlugin()
  {
    return filePlugin;
  }
  
  private JSONObject getParent(String paramString)
    throws JSONException, IOException
  {
    Filesystem localFilesystem;
    try
    {
      paramString = new LocalFilesystemURL(paramString);
      localFilesystem = filesystemForURL(paramString);
      if (localFilesystem == null) {
        throw new MalformedURLException("No installed handlers for this URL");
      }
    }
    catch (IllegalArgumentException paramString)
    {
      throw new MalformedURLException("Unrecognized filesystem URL");
    }
    paramString = localFilesystem.getParentForLocalURL(paramString);
    return paramString;
  }
  
  private JSONArray readEntries(String paramString)
    throws FileNotFoundException, JSONException, MalformedURLException
  {
    Filesystem localFilesystem;
    try
    {
      paramString = new LocalFilesystemURL(paramString);
      localFilesystem = filesystemForURL(paramString);
      if (localFilesystem == null) {
        throw new MalformedURLException("No installed handlers for this URL");
      }
    }
    catch (IllegalArgumentException paramString)
    {
      throw new MalformedURLException("Unrecognized filesystem URL");
    }
    paramString = localFilesystem.readEntriesAtLocalURL(paramString);
    return paramString;
  }
  
  private boolean remove(String paramString)
    throws NoModificationAllowedException, InvalidModificationException, MalformedURLException
  {
    try
    {
      paramString = new LocalFilesystemURL(paramString);
      if (("".equals(fullPath)) || ("/".equals(fullPath))) {
        throw new NoModificationAllowedException("You can't delete the root directory");
      }
    }
    catch (IllegalArgumentException paramString)
    {
      throw new MalformedURLException("Unrecognized filesystem URL");
    }
    Filesystem localFilesystem = filesystemForURL(paramString);
    if (localFilesystem == null) {
      throw new MalformedURLException("No installed handlers for this URL");
    }
    boolean bool = localFilesystem.removeFileAtLocalURL(paramString);
    return bool;
  }
  
  private boolean removeRecursively(String paramString)
    throws FileExistsException, NoModificationAllowedException, MalformedURLException
  {
    try
    {
      paramString = new LocalFilesystemURL(paramString);
      if (("".equals(fullPath)) || ("/".equals(fullPath))) {
        throw new NoModificationAllowedException("You can't delete the root directory");
      }
    }
    catch (IllegalArgumentException paramString)
    {
      throw new MalformedURLException("Unrecognized filesystem URL");
    }
    Filesystem localFilesystem = filesystemForURL(paramString);
    if (localFilesystem == null) {
      throw new MalformedURLException("No installed handlers for this URL");
    }
    boolean bool = localFilesystem.recursiveRemoveFileAtLocalURL(paramString);
    return bool;
  }
  
  private JSONArray requestAllFileSystems()
    throws IOException, JSONException
  {
    JSONArray localJSONArray = new JSONArray();
    Iterator localIterator = filesystems.iterator();
    while (localIterator.hasNext())
    {
      Filesystem localFilesystem = (Filesystem)localIterator.next();
      localJSONArray.put(localFilesystem.getEntryForLocalURL(new LocalFilesystemURL("cdvfile://localhost/" + name + "/")));
    }
    return localJSONArray;
  }
  
  private JSONObject requestAllPaths()
    throws JSONException
  {
    Activity localActivity = cordova.getActivity();
    JSONObject localJSONObject = new JSONObject();
    localJSONObject.put("applicationDirectory", "file:///android_asset/");
    localJSONObject.put("applicationStorageDirectory", toDirUrl(localActivity.getFilesDir().getParentFile()));
    localJSONObject.put("dataDirectory", toDirUrl(localActivity.getFilesDir()));
    localJSONObject.put("cacheDirectory", toDirUrl(localActivity.getCacheDir()));
    if (Environment.getExternalStorageState().equals("mounted")) {}
    try
    {
      localJSONObject.put("externalApplicationStorageDirectory", toDirUrl(localActivity.getExternalFilesDir(null).getParentFile()));
      localJSONObject.put("externalDataDirectory", toDirUrl(localActivity.getExternalFilesDir(null)));
      localJSONObject.put("externalCacheDirectory", toDirUrl(localActivity.getExternalCacheDir()));
      localJSONObject.put("externalRootDirectory", toDirUrl(Environment.getExternalStorageDirectory()));
      return localJSONObject;
    }
    catch (NullPointerException localNullPointerException)
    {
      Log.d("FileUtils", "Unable to access these paths, most liklely due to USB storage");
    }
    return localJSONObject;
  }
  
  private JSONObject requestFileSystem(int paramInt)
    throws IOException, JSONException
  {
    JSONObject localJSONObject = new JSONObject();
    Object localObject1 = null;
    try
    {
      localObject2 = (Filesystem)filesystems.get(paramInt);
      localObject1 = localObject2;
    }
    catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException)
    {
      Object localObject2;
      for (;;) {}
    }
    if (localObject1 == null) {
      throw new IOException("No filesystem of type requested");
    }
    localObject2 = new LocalFilesystemURL("cdvfile://localhost/" + name + "/");
    localJSONObject.put("name", name);
    localJSONObject.put("root", ((Filesystem)localObject1).getEntryForLocalURL((LocalFilesystemURL)localObject2));
    return localJSONObject;
  }
  
  private JSONObject resolveLocalFileSystemURI(String paramString)
    throws IOException, JSONException
  {
    if (paramString == null) {
      throw new MalformedURLException("Unrecognized filesystem URL");
    }
    Object localObject;
    int i;
    int j;
    if (paramString.startsWith("file:/"))
    {
      localObject = paramString;
      if (!paramString.startsWith("file://")) {
        localObject = "file:///" + paramString.substring(6);
      }
      paramString = URLDecoder.decode((String)localObject, "UTF-8");
      i = paramString.indexOf("?");
      if (i < 0)
      {
        i = paramString.length();
        j = paramString.indexOf("/", 7);
        if ((j >= 0) && (j <= i)) {
          break label158;
        }
        paramString = "";
      }
    }
    label114:
    for (paramString = filesystemURLforLocalPath(paramString);; paramString = new LocalFilesystemURL(paramString))
    {
      try
      {
        localObject = filesystemForURL(paramString);
        if (localObject != null) {
          break label180;
        }
        throw new MalformedURLException("No installed handlers for this URL");
      }
      catch (IllegalArgumentException paramString)
      {
        throw new MalformedURLException("Unrecognized filesystem URL");
      }
      break;
      label158:
      paramString = paramString.substring(j, i);
      break label114;
    }
    label180:
    paramString = ((Filesystem)localObject).getEntryForLocalURL(paramString);
    return paramString;
  }
  
  private void threadhelper(final FileOp paramFileOp, final CallbackContext paramCallbackContext)
  {
    cordova.getThreadPool().execute(new Runnable()
    {
      public void run()
      {
        try
        {
          paramFileOp.run();
          return;
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
          if ((localException instanceof EncodingException))
          {
            paramCallbackContext.error(FileUtils.ENCODING_ERR);
            return;
          }
          if ((localException instanceof FileNotFoundException))
          {
            paramCallbackContext.error(FileUtils.NOT_FOUND_ERR);
            return;
          }
          if ((localException instanceof FileExistsException))
          {
            paramCallbackContext.error(FileUtils.PATH_EXISTS_ERR);
            return;
          }
          if ((localException instanceof NoModificationAllowedException))
          {
            paramCallbackContext.error(FileUtils.NO_MODIFICATION_ALLOWED_ERR);
            return;
          }
          if ((localException instanceof InvalidModificationException))
          {
            paramCallbackContext.error(FileUtils.INVALID_MODIFICATION_ERR);
            return;
          }
          if ((localException instanceof MalformedURLException))
          {
            paramCallbackContext.error(FileUtils.ENCODING_ERR);
            return;
          }
          if ((localException instanceof IOException))
          {
            paramCallbackContext.error(FileUtils.INVALID_MODIFICATION_ERR);
            return;
          }
          if ((localException instanceof EncodingException))
          {
            paramCallbackContext.error(FileUtils.ENCODING_ERR);
            return;
          }
          if ((localException instanceof TypeMismatchException))
          {
            paramCallbackContext.error(FileUtils.TYPE_MISMATCH_ERR);
            return;
          }
          paramCallbackContext.error(FileUtils.UNKNOWN_ERR);
        }
      }
    });
  }
  
  private static String toDirUrl(File paramFile)
  {
    return Uri.fromFile(paramFile).toString() + '/';
  }
  
  private JSONObject transferTo(String paramString1, String paramString2, String paramString3, boolean paramBoolean)
    throws JSONException, NoModificationAllowedException, IOException, InvalidModificationException, EncodingException, FileExistsException
  {
    if ((paramString1 == null) || (paramString2 == null)) {
      throw new FileNotFoundException();
    }
    paramString1 = new LocalFilesystemURL(paramString1);
    paramString2 = new LocalFilesystemURL(paramString2);
    Filesystem localFilesystem1 = filesystemForURL(paramString1);
    Filesystem localFilesystem2 = filesystemForURL(paramString2);
    if ((paramString3 != null) && (paramString3.contains(":"))) {
      throw new EncodingException("Bad file name");
    }
    return localFilesystem2.copyFileToURL(paramString2, paramString3, localFilesystem1, paramString1, paramBoolean);
  }
  
  private long truncateFile(String paramString, long paramLong)
    throws FileNotFoundException, IOException, NoModificationAllowedException
  {
    Filesystem localFilesystem;
    try
    {
      paramString = new LocalFilesystemURL(paramString);
      localFilesystem = filesystemForURL(paramString);
      if (localFilesystem == null) {
        throw new MalformedURLException("No installed handlers for this URL");
      }
    }
    catch (IllegalArgumentException paramString)
    {
      throw new MalformedURLException("Unrecognized filesystem URL");
    }
    paramLong = localFilesystem.truncateFileAtURL(paramString, paramLong);
    return paramLong;
  }
  
  public boolean execute(final String paramString, final JSONArray paramJSONArray, final CallbackContext paramCallbackContext)
    throws JSONException
  {
    if (!configured)
    {
      paramCallbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, "File plugin is not configured. Please see the README.md file for details on how to update config.xml"));
      return true;
    }
    if (paramString.equals("testSaveLocationExists")) {
      threadhelper(new FileOp()
      {
        public void run()
        {
          boolean bool = DirectoryManager.testSaveLocationExists();
          paramCallbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, bool));
        }
      }, paramCallbackContext);
    }
    for (;;)
    {
      return true;
      if (paramString.equals("getFreeDiskSpace"))
      {
        threadhelper(new FileOp()
        {
          public void run()
          {
            long l = DirectoryManager.getFreeDiskSpace(false);
            paramCallbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, (float)l));
          }
        }, paramCallbackContext);
      }
      else if (paramString.equals("testFileExists"))
      {
        threadhelper(new FileOp()
        {
          public void run()
          {
            boolean bool = DirectoryManager.testFileExists(val$fname);
            paramCallbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, bool));
          }
        }, paramCallbackContext);
      }
      else if (paramString.equals("testDirectoryExists"))
      {
        threadhelper(new FileOp()
        {
          public void run()
          {
            boolean bool = DirectoryManager.testFileExists(val$fname);
            paramCallbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, bool));
          }
        }, paramCallbackContext);
      }
      else
      {
        final int i;
        final int j;
        if (paramString.equals("readAsText"))
        {
          paramString = paramJSONArray.getString(1);
          i = paramJSONArray.getInt(2);
          j = paramJSONArray.getInt(3);
          threadhelper(new FileOp()
          {
            public void run()
              throws MalformedURLException
            {
              readFileAs(val$fname, i, j, paramCallbackContext, paramString, 1);
            }
          }, paramCallbackContext);
        }
        else if (paramString.equals("readAsDataURL"))
        {
          i = paramJSONArray.getInt(1);
          j = paramJSONArray.getInt(2);
          threadhelper(new FileOp()
          {
            public void run()
              throws MalformedURLException
            {
              readFileAs(val$fname, i, j, paramCallbackContext, null, -1);
            }
          }, paramCallbackContext);
        }
        else if (paramString.equals("readAsArrayBuffer"))
        {
          i = paramJSONArray.getInt(1);
          j = paramJSONArray.getInt(2);
          threadhelper(new FileOp()
          {
            public void run()
              throws MalformedURLException
            {
              readFileAs(val$fname, i, j, paramCallbackContext, null, 6);
            }
          }, paramCallbackContext);
        }
        else if (paramString.equals("readAsBinaryString"))
        {
          i = paramJSONArray.getInt(1);
          j = paramJSONArray.getInt(2);
          threadhelper(new FileOp()
          {
            public void run()
              throws MalformedURLException
            {
              readFileAs(val$fname, i, j, paramCallbackContext, null, 7);
            }
          }, paramCallbackContext);
        }
        else if (paramString.equals("write"))
        {
          threadhelper(new FileOp()
          {
            public void run()
              throws FileNotFoundException, IOException, NoModificationAllowedException
            {
              long l = write(val$fname, val$data, val$offset, val$isBinary.booleanValue());
              paramCallbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, (float)l));
            }
          }, paramCallbackContext);
        }
        else if (paramString.equals("truncate"))
        {
          threadhelper(new FileOp()
          {
            public void run()
              throws FileNotFoundException, IOException, NoModificationAllowedException
            {
              long l = FileUtils.this.truncateFile(val$fname, val$offset);
              paramCallbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, (float)l));
            }
          }, paramCallbackContext);
        }
        else if (paramString.equals("requestAllFileSystems"))
        {
          threadhelper(new FileOp()
          {
            public void run()
              throws IOException, JSONException
            {
              paramCallbackContext.success(FileUtils.this.requestAllFileSystems());
            }
          }, paramCallbackContext);
        }
        else if (paramString.equals("requestAllPaths"))
        {
          cordova.getThreadPool().execute(new Runnable()
          {
            public void run()
            {
              try
              {
                paramCallbackContext.success(FileUtils.this.requestAllPaths());
                return;
              }
              catch (JSONException localJSONException)
              {
                localJSONException.printStackTrace();
              }
            }
          });
        }
        else if (paramString.equals("requestFileSystem"))
        {
          i = paramJSONArray.getInt(0);
          threadhelper(new FileOp()
          {
            public void run()
              throws IOException, JSONException
            {
              if ((val$size != 0L) && (val$size > DirectoryManager.getFreeDiskSpace(true) * 1024L))
              {
                i.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, FileUtils.QUOTA_EXCEEDED_ERR));
                return;
              }
              JSONObject localJSONObject = FileUtils.this.requestFileSystem(val$fstype);
              i.success(localJSONObject);
            }
          }, paramCallbackContext);
        }
        else if (paramString.equals("resolveLocalFileSystemURI"))
        {
          threadhelper(new FileOp()
          {
            public void run()
              throws IOException, JSONException
            {
              JSONObject localJSONObject = FileUtils.this.resolveLocalFileSystemURI(val$fname);
              paramCallbackContext.success(localJSONObject);
            }
          }, paramCallbackContext);
        }
        else if (paramString.equals("getFileMetadata"))
        {
          threadhelper(new FileOp()
          {
            public void run()
              throws FileNotFoundException, JSONException, MalformedURLException
            {
              JSONObject localJSONObject = FileUtils.this.getFileMetadata(val$fname);
              paramCallbackContext.success(localJSONObject);
            }
          }, paramCallbackContext);
        }
        else if (paramString.equals("getParent"))
        {
          threadhelper(new FileOp()
          {
            public void run()
              throws JSONException, IOException
            {
              JSONObject localJSONObject = FileUtils.this.getParent(val$fname);
              paramCallbackContext.success(localJSONObject);
            }
          }, paramCallbackContext);
        }
        else if (paramString.equals("getDirectory"))
        {
          threadhelper(new FileOp()
          {
            public void run()
              throws FileExistsException, IOException, TypeMismatchException, EncodingException, JSONException
            {
              JSONObject localJSONObject = FileUtils.this.getFile(val$dirname, val$path, paramJSONArray.optJSONObject(2), true);
              paramCallbackContext.success(localJSONObject);
            }
          }, paramCallbackContext);
        }
        else if (paramString.equals("getFile"))
        {
          threadhelper(new FileOp()
          {
            public void run()
              throws FileExistsException, IOException, TypeMismatchException, EncodingException, JSONException
            {
              JSONObject localJSONObject = FileUtils.this.getFile(val$dirname, val$path, paramJSONArray.optJSONObject(2), false);
              paramCallbackContext.success(localJSONObject);
            }
          }, paramCallbackContext);
        }
        else if (paramString.equals("remove"))
        {
          threadhelper(new FileOp()
          {
            public void run()
              throws NoModificationAllowedException, InvalidModificationException, MalformedURLException
            {
              if (FileUtils.this.remove(val$fname))
              {
                paramCallbackContext.success();
                return;
              }
              paramCallbackContext.error(FileUtils.NO_MODIFICATION_ALLOWED_ERR);
            }
          }, paramCallbackContext);
        }
        else if (paramString.equals("removeRecursively"))
        {
          threadhelper(new FileOp()
          {
            public void run()
              throws FileExistsException, MalformedURLException, NoModificationAllowedException
            {
              if (FileUtils.this.removeRecursively(val$fname))
              {
                paramCallbackContext.success();
                return;
              }
              paramCallbackContext.error(FileUtils.NO_MODIFICATION_ALLOWED_ERR);
            }
          }, paramCallbackContext);
        }
        else if (paramString.equals("moveTo"))
        {
          threadhelper(new FileOp()
          {
            public void run()
              throws JSONException, NoModificationAllowedException, IOException, InvalidModificationException, EncodingException, FileExistsException
            {
              JSONObject localJSONObject = FileUtils.this.transferTo(val$fname, val$newParent, val$newName, true);
              paramCallbackContext.success(localJSONObject);
            }
          }, paramCallbackContext);
        }
        else if (paramString.equals("copyTo"))
        {
          threadhelper(new FileOp()
          {
            public void run()
              throws JSONException, NoModificationAllowedException, IOException, InvalidModificationException, EncodingException, FileExistsException
            {
              JSONObject localJSONObject = FileUtils.this.transferTo(val$fname, val$newParent, val$newName, false);
              paramCallbackContext.success(localJSONObject);
            }
          }, paramCallbackContext);
        }
        else if (paramString.equals("readEntries"))
        {
          threadhelper(new FileOp()
          {
            public void run()
              throws FileNotFoundException, JSONException, MalformedURLException
            {
              JSONArray localJSONArray = FileUtils.this.readEntries(val$fname);
              paramCallbackContext.success(localJSONArray);
            }
          }, paramCallbackContext);
        }
        else
        {
          if (!paramString.equals("_getLocalFilesystemPath")) {
            break;
          }
          threadhelper(new FileOp()
          {
            public void run()
              throws FileNotFoundException, JSONException, MalformedURLException
            {
              String str = filesystemPathForURL(val$localURLstr);
              paramCallbackContext.success(str);
            }
          }, paramCallbackContext);
        }
      }
    }
    return false;
  }
  
  public String filesystemPathForURL(String paramString)
    throws MalformedURLException
  {
    Filesystem localFilesystem;
    try
    {
      paramString = new LocalFilesystemURL(paramString);
      localFilesystem = filesystemForURL(paramString);
      if (localFilesystem == null) {
        throw new MalformedURLException("No installed handlers for this URL");
      }
    }
    catch (IllegalArgumentException paramString)
    {
      throw new MalformedURLException("Unrecognized filesystem URL");
    }
    paramString = localFilesystem.filesystemPathForURL(paramString);
    return paramString;
  }
  
  public LocalFilesystemURL filesystemURLforLocalPath(String paramString)
  {
    Object localObject1 = null;
    int i = 0;
    Iterator localIterator = filesystems.iterator();
    while (localIterator.hasNext())
    {
      Object localObject2 = (Filesystem)localIterator.next();
      if (localObject2 != null)
      {
        localObject2 = ((Filesystem)localObject2).URLforFilesystemPath(paramString);
        if ((localObject2 != null) && ((localObject1 == null) || (fullPath.length() < i)))
        {
          localObject1 = localObject2;
          i = fullPath.length();
        }
      }
    }
    return (LocalFilesystemURL)localObject1;
  }
  
  protected HashMap<String, String> getAvailableFileSystems(Activity paramActivity)
  {
    Context localContext = paramActivity.getApplicationContext();
    paramActivity = new HashMap();
    paramActivity.put("files", localContext.getFilesDir().getAbsolutePath());
    paramActivity.put("documents", new File(localContext.getFilesDir(), "Documents").getAbsolutePath());
    paramActivity.put("cache", localContext.getCacheDir().getAbsolutePath());
    paramActivity.put("root", "/");
    if (Environment.getExternalStorageState().equals("mounted")) {}
    try
    {
      paramActivity.put("files-external", localContext.getExternalFilesDir(null).getAbsolutePath());
      paramActivity.put("sdcard", Environment.getExternalStorageDirectory().getAbsolutePath());
      paramActivity.put("cache-external", localContext.getExternalCacheDir().getAbsolutePath());
      return paramActivity;
    }
    catch (NullPointerException localNullPointerException)
    {
      Log.d("FileUtils", "External storage unavailable, check to see if USB Mass Storage Mode is on");
    }
    return paramActivity;
  }
  
  public JSONObject getEntryForFile(File paramFile)
    throws JSONException
  {
    Iterator localIterator = filesystems.iterator();
    while (localIterator.hasNext())
    {
      JSONObject localJSONObject = ((Filesystem)localIterator.next()).makeEntryForFile(paramFile);
      if (localJSONObject != null) {
        return localJSONObject;
      }
    }
    return null;
  }
  
  protected String[] getExtraFileSystemsPreference(Activity paramActivity)
  {
    String str = paramActivity.getIntent().getStringExtra("androidextrafilesystems");
    paramActivity = str;
    if (str == null) {
      paramActivity = "files,files-external,documents,sdcard,cache,cache-external,root";
    }
    return paramActivity.split(",");
  }
  
  public void initialize(CordovaInterface paramCordovaInterface, CordovaWebView paramCordovaWebView)
  {
    super.initialize(paramCordovaInterface, paramCordovaWebView);
    filesystems = new ArrayList();
    String str1 = null;
    Activity localActivity = paramCordovaInterface.getActivity();
    String str5 = localActivity.getPackageName();
    String str2 = localActivity.getIntent().getStringExtra("androidpersistentfilelocation");
    String str3 = str2;
    if (str2 == null) {
      str3 = "compatibility";
    }
    str2 = localActivity.getCacheDir().getAbsolutePath();
    String str4;
    if ("internal".equalsIgnoreCase(str3))
    {
      str1 = localActivity.getFilesDir().getAbsolutePath() + "/files/";
      configured = true;
      str4 = str2;
    }
    while (configured)
    {
      new File(str4).mkdirs();
      new File(str1).mkdirs();
      registerFilesystem(new LocalFilesystem("temporary", paramCordovaInterface, str4));
      registerFilesystem(new LocalFilesystem("persistent", paramCordovaInterface, str1));
      registerFilesystem(new ContentFilesystem("content", paramCordovaInterface, paramCordovaWebView));
      registerExtraFileSystems(getExtraFileSystemsPreference(localActivity), getAvailableFileSystems(localActivity));
      if (filePlugin == null) {
        filePlugin = this;
      }
      return;
      str4 = str2;
      if ("compatibility".equalsIgnoreCase(str3))
      {
        if (Environment.getExternalStorageState().equals("mounted"))
        {
          str1 = Environment.getExternalStorageDirectory().getAbsolutePath();
          str2 = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Android/data/" + str5 + "/cache/";
        }
        for (;;)
        {
          configured = true;
          str4 = str2;
          break;
          str1 = "/data/data/" + str5;
        }
      }
    }
    Log.e("FileUtils", "File plugin configuration error: Please set AndroidPersistentFileLocation in config.xml to one of \"internal\" (for new applications) or \"compatibility\" (for compatibility with previous versions)");
    localActivity.finish();
  }
  
  public void readFileAs(String paramString1, int paramInt1, int paramInt2, final CallbackContext paramCallbackContext, final String paramString2, final int paramInt3)
    throws MalformedURLException
  {
    try
    {
      paramString1 = new LocalFilesystemURL(paramString1);
      localFilesystem = filesystemForURL(paramString1);
      if (localFilesystem == null) {
        throw new MalformedURLException("No installed handlers for this URL");
      }
    }
    catch (IllegalArgumentException paramString1)
    {
      Filesystem localFilesystem;
      throw new MalformedURLException("Unrecognized filesystem URL");
      long l1 = paramInt1;
      long l2 = paramInt2;
      localFilesystem.readFileAtURL(paramString1, l1, l2, new Filesystem.ReadFileCallback()
      {
        public void handleData(InputStream paramAnonymousInputStream, String paramAnonymousString)
        {
          for (;;)
          {
            ByteArrayOutputStream localByteArrayOutputStream;
            try
            {
              localByteArrayOutputStream = new ByteArrayOutputStream();
              byte[] arrayOfByte = new byte[' '];
              int i = paramAnonymousInputStream.read(arrayOfByte, 0, 8192);
              if (i <= 0) {}
              switch (paramInt3)
              {
              case 1: 
                paramAnonymousInputStream = Base64.encode(localByteArrayOutputStream.toByteArray(), 2);
                paramAnonymousInputStream = "data:" + paramAnonymousString + ";base64," + new String(paramAnonymousInputStream, "US-ASCII");
                paramAnonymousInputStream = new PluginResult(PluginResult.Status.OK, paramAnonymousInputStream);
                paramCallbackContext.sendPluginResult(paramAnonymousInputStream);
                return;
                localByteArrayOutputStream.write(arrayOfByte, 0, i);
                continue;
                paramAnonymousInputStream = new PluginResult(PluginResult.Status.OK, localByteArrayOutputStream.toString(paramString2));
              }
            }
            catch (IOException paramAnonymousInputStream)
            {
              Log.d("FileUtils", paramAnonymousInputStream.getLocalizedMessage());
              paramCallbackContext.sendPluginResult(new PluginResult(PluginResult.Status.IO_EXCEPTION, FileUtils.NOT_READABLE_ERR));
              return;
            }
            continue;
            paramAnonymousInputStream = new PluginResult(PluginResult.Status.OK, localByteArrayOutputStream.toByteArray());
            continue;
            paramAnonymousInputStream = new PluginResult(PluginResult.Status.OK, localByteArrayOutputStream.toByteArray(), true);
          }
        }
      });
      return;
    }
    catch (FileNotFoundException paramString1)
    {
      paramCallbackContext.sendPluginResult(new PluginResult(PluginResult.Status.IO_EXCEPTION, NOT_FOUND_ERR));
      return;
    }
    catch (IOException paramString1)
    {
      Log.d("FileUtils", paramString1.getLocalizedMessage());
      paramCallbackContext.sendPluginResult(new PluginResult(PluginResult.Status.IO_EXCEPTION, NOT_READABLE_ERR));
    }
  }
  
  protected void registerExtraFileSystems(String[] paramArrayOfString, HashMap<String, String> paramHashMap)
  {
    HashSet localHashSet = new HashSet();
    int j = paramArrayOfString.length;
    int i = 0;
    if (i < j)
    {
      String str1 = paramArrayOfString[i];
      if (!localHashSet.contains(str1))
      {
        String str2 = (String)paramHashMap.get(str1);
        if (str2 == null) {
          break label149;
        }
        File localFile = new File(str2);
        if ((!localFile.mkdirs()) && (!localFile.isDirectory())) {
          break label113;
        }
        registerFilesystem(new LocalFilesystem(str1, cordova, str2));
        localHashSet.add(str1);
      }
      for (;;)
      {
        i += 1;
        break;
        label113:
        Log.d("FileUtils", "Unable to create root dir for fileystem \"" + str1 + "\", skipping");
        continue;
        label149:
        Log.d("FileUtils", "Unrecognized extra filesystem identifier: " + str1);
      }
    }
  }
  
  public void registerFilesystem(Filesystem paramFilesystem)
  {
    if ((paramFilesystem != null) && (filesystemForName(name) == null)) {
      filesystems.add(paramFilesystem);
    }
  }
  
  public Uri remapUri(Uri paramUri)
  {
    try
    {
      paramUri = new LocalFilesystemURL(paramUri);
      Filesystem localFilesystem = filesystemForURL(paramUri);
      if (localFilesystem == null) {
        return null;
      }
      if (localFilesystem.filesystemPathForURL(paramUri) != null)
      {
        paramUri = Uri.parse("file:///" + localFilesystem.filesystemPathForURL(paramUri));
        return paramUri;
      }
    }
    catch (IllegalArgumentException paramUri) {}
    return null;
  }
  
  public long write(String paramString1, String paramString2, int paramInt, boolean paramBoolean)
    throws FileNotFoundException, IOException, NoModificationAllowedException
  {
    LocalFilesystemURL localLocalFilesystemURL;
    Filesystem localFilesystem;
    try
    {
      localLocalFilesystemURL = new LocalFilesystemURL(paramString1);
      localFilesystem = filesystemForURL(localLocalFilesystemURL);
      if (localFilesystem == null) {
        throw new MalformedURLException("No installed handlers for this URL");
      }
    }
    catch (IllegalArgumentException paramString1)
    {
      throw new MalformedURLException("Unrecognized filesystem URL");
    }
    long l = localFilesystem.writeToFileAtURL(localLocalFilesystemURL, paramString2, paramInt, paramBoolean);
    Log.d("TEST", paramString1 + ": " + l);
    return l;
  }
  
  private static abstract interface FileOp
  {
    public abstract void run()
      throws Exception;
  }
}

/* Location:
 * Qualified Name:     org.apache.cordova.file.FileUtils
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */