package org.apache.cordova.file;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import org.apache.cordova.CordovaInterface;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LocalFilesystem
  extends Filesystem
{
  private CordovaInterface cordova;
  private String fsRoot;
  
  public LocalFilesystem(String paramString1, CordovaInterface paramCordovaInterface, String paramString2)
  {
    name = paramString1;
    fsRoot = paramString2;
    cordova = paramCordovaInterface;
  }
  
  private void broadcastNewFile(LocalFilesystemURL paramLocalFilesystemURL)
  {
    paramLocalFilesystemURL = new File(filesystemPathForURL(paramLocalFilesystemURL));
    if (paramLocalFilesystemURL.exists()) {
      cordova.getActivity().getApplicationContext().sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", Uri.fromFile(paramLocalFilesystemURL)));
    }
  }
  
  private void copyAction(File paramFile1, File paramFile2)
    throws FileNotFoundException, IOException
  {
    paramFile1 = new FileInputStream(paramFile1);
    paramFile2 = new FileOutputStream(paramFile2);
    FileChannel localFileChannel1 = paramFile1.getChannel();
    FileChannel localFileChannel2 = paramFile2.getChannel();
    try
    {
      localFileChannel1.transferTo(0L, localFileChannel1.size(), localFileChannel2);
      return;
    }
    finally
    {
      paramFile1.close();
      paramFile2.close();
      localFileChannel1.close();
      localFileChannel2.close();
    }
  }
  
  private JSONObject copyDirectory(File paramFile1, File paramFile2)
    throws JSONException, IOException, NoModificationAllowedException, InvalidModificationException
  {
    if ((paramFile2.exists()) && (paramFile2.isFile())) {
      throw new InvalidModificationException("Can't rename a file to a directory");
    }
    if (isCopyOnItself(paramFile1.getAbsolutePath(), paramFile2.getAbsolutePath())) {
      throw new InvalidModificationException("Can't copy itself into itself");
    }
    if ((!paramFile2.exists()) && (!paramFile2.mkdir())) {
      throw new NoModificationAllowedException("Couldn't create the destination directory");
    }
    paramFile1 = paramFile1.listFiles();
    int j = paramFile1.length;
    int i = 0;
    if (i < j)
    {
      File localFile1 = paramFile1[i];
      File localFile2 = new File(paramFile2.getAbsoluteFile() + File.separator + localFile1.getName());
      if (localFile1.isDirectory()) {
        copyDirectory(localFile1, localFile2);
      }
      for (;;)
      {
        i += 1;
        break;
        copyFile(localFile1, localFile2);
      }
    }
    return makeEntryForFile(paramFile2);
  }
  
  private JSONObject copyFile(File paramFile1, File paramFile2)
    throws IOException, InvalidModificationException, JSONException
  {
    if ((paramFile2.exists()) && (paramFile2.isDirectory())) {
      throw new InvalidModificationException("Can't rename a file to a directory");
    }
    copyAction(paramFile1, paramFile2);
    return makeEntryForFile(paramFile2);
  }
  
  private String fullPathForFilesystemPath(String paramString)
  {
    if ((paramString != null) && (paramString.startsWith(fsRoot))) {
      return paramString.substring(fsRoot.length());
    }
    return null;
  }
  
  private boolean isCopyOnItself(String paramString1, String paramString2)
  {
    return (paramString2.equals(paramString1)) || (paramString2.startsWith(paramString1 + File.separator));
  }
  
  private JSONObject moveDirectory(File paramFile1, File paramFile2)
    throws IOException, JSONException, InvalidModificationException, NoModificationAllowedException, FileExistsException
  {
    if ((paramFile2.exists()) && (paramFile2.isFile())) {
      throw new InvalidModificationException("Can't rename a file to a directory");
    }
    if (isCopyOnItself(paramFile1.getAbsolutePath(), paramFile2.getAbsolutePath())) {
      throw new InvalidModificationException("Can't move itself into itself");
    }
    if ((paramFile2.exists()) && (paramFile2.list().length > 0)) {
      throw new InvalidModificationException("directory is not empty");
    }
    if (!paramFile1.renameTo(paramFile2))
    {
      copyDirectory(paramFile1, paramFile2);
      if (paramFile2.exists()) {
        removeDirRecursively(paramFile1);
      }
    }
    else
    {
      return makeEntryForFile(paramFile2);
    }
    throw new IOException("moved failed");
  }
  
  private JSONObject moveFile(File paramFile1, File paramFile2)
    throws IOException, JSONException, InvalidModificationException
  {
    if ((paramFile2.exists()) && (paramFile2.isDirectory())) {
      throw new InvalidModificationException("Can't rename a file to a directory");
    }
    if (!paramFile1.renameTo(paramFile2))
    {
      copyAction(paramFile1, paramFile2);
      if (paramFile2.exists()) {
        paramFile1.delete();
      }
    }
    else
    {
      return makeEntryForFile(paramFile2);
    }
    throw new IOException("moved failed");
  }
  
  public LocalFilesystemURL URLforFilesystemPath(String paramString)
  {
    return URLforFullPath(fullPathForFilesystemPath(paramString));
  }
  
  protected LocalFilesystemURL URLforFullPath(String paramString)
  {
    if (paramString != null)
    {
      if (paramString.startsWith("/")) {
        return new LocalFilesystemURL("cdvfile://localhost/" + name + paramString);
      }
      return new LocalFilesystemURL("cdvfile://localhost/" + name + "/" + paramString);
    }
    return null;
  }
  
  public boolean canRemoveFileAtLocalURL(LocalFilesystemURL paramLocalFilesystemURL)
  {
    return new File(filesystemPathForURL(paramLocalFilesystemURL)).exists();
  }
  
  public JSONObject copyFileToURL(LocalFilesystemURL paramLocalFilesystemURL1, String paramString, Filesystem paramFilesystem, LocalFilesystemURL paramLocalFilesystemURL2, boolean paramBoolean)
    throws IOException, InvalidModificationException, JSONException, NoModificationAllowedException, FileExistsException
  {
    if (!new File(filesystemPathForURL(paramLocalFilesystemURL1)).exists()) {
      throw new FileNotFoundException("The source does not exist");
    }
    if (LocalFilesystem.class.isInstance(paramFilesystem))
    {
      paramLocalFilesystemURL1 = makeDestinationURL(paramString, paramLocalFilesystemURL2, paramLocalFilesystemURL1);
      paramString = new File(paramFilesystem.filesystemPathForURL(paramLocalFilesystemURL2));
      paramLocalFilesystemURL1 = new File(filesystemPathForURL(paramLocalFilesystemURL1));
      if (!paramString.exists()) {
        throw new FileNotFoundException("The source does not exist");
      }
      if (paramString.getAbsolutePath().equals(paramLocalFilesystemURL1.getAbsolutePath())) {
        throw new InvalidModificationException("Can't copy a file onto itself");
      }
      if (paramString.isDirectory())
      {
        if (paramBoolean) {
          return moveDirectory(paramString, paramLocalFilesystemURL1);
        }
        return copyDirectory(paramString, paramLocalFilesystemURL1);
      }
      if (paramBoolean) {
        return moveFile(paramString, paramLocalFilesystemURL1);
      }
      return copyFile(paramString, paramLocalFilesystemURL1);
    }
    return super.copyFileToURL(paramLocalFilesystemURL1, paramString, paramFilesystem, paramLocalFilesystemURL2, paramBoolean);
  }
  
  public String filesystemPathForFullPath(String paramString)
  {
    String str = new File(fsRoot, paramString).toString();
    int i = str.indexOf("?");
    paramString = str;
    if (i >= 0) {
      paramString = str.substring(0, i);
    }
    str = paramString;
    if (paramString.length() > 1)
    {
      str = paramString;
      if (paramString.endsWith("/")) {
        str = paramString.substring(0, paramString.length() - 1);
      }
    }
    return str;
  }
  
  public String filesystemPathForURL(LocalFilesystemURL paramLocalFilesystemURL)
  {
    return filesystemPathForFullPath(fullPath);
  }
  
  public JSONObject getEntryForLocalURL(LocalFilesystemURL paramLocalFilesystemURL)
    throws IOException
  {
    File localFile = new File(filesystemPathForURL(paramLocalFilesystemURL));
    if (!localFile.exists()) {
      throw new FileNotFoundException();
    }
    if (!localFile.canRead()) {
      throw new IOException();
    }
    try
    {
      paramLocalFilesystemURL = makeEntryForURL(paramLocalFilesystemURL, Boolean.valueOf(localFile.isDirectory()), Uri.fromFile(localFile).toString());
      return paramLocalFilesystemURL;
    }
    catch (JSONException paramLocalFilesystemURL)
    {
      throw new IOException();
    }
  }
  
  public JSONObject getFileForLocalURL(LocalFilesystemURL paramLocalFilesystemURL, String paramString, JSONObject paramJSONObject, boolean paramBoolean)
    throws FileExistsException, IOException, TypeMismatchException, EncodingException, JSONException
  {
    int i = 0;
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (paramJSONObject != null)
    {
      boolean bool3 = paramJSONObject.optBoolean("create");
      i = bool3;
      bool1 = bool2;
      if (bool3)
      {
        bool1 = paramJSONObject.optBoolean("exclusive");
        i = bool3;
      }
    }
    if (paramString.contains(":")) {
      throw new EncodingException("This path has an invalid \":\" in it.");
    }
    if (paramString.startsWith("/")) {}
    for (paramLocalFilesystemURL = URLforFilesystemPath(paramString);; paramLocalFilesystemURL = URLforFullPath(normalizePath(fullPath + "/" + paramString)))
    {
      paramString = new File(filesystemPathForURL(paramLocalFilesystemURL));
      if (i == 0) {
        break label200;
      }
      if ((!bool1) || (!paramString.exists())) {
        break;
      }
      throw new FileExistsException("create/exclusive fails");
    }
    if (paramBoolean) {
      paramString.mkdir();
    }
    while (!paramString.exists())
    {
      throw new FileExistsException("create fails");
      paramString.createNewFile();
    }
    label200:
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
    File localFile = new File(filesystemPathForURL(paramLocalFilesystemURL));
    if (!localFile.exists()) {
      throw new FileNotFoundException("File at " + URL + " does not exist.");
    }
    JSONObject localJSONObject = new JSONObject();
    try
    {
      if (localFile.isDirectory()) {}
      for (long l = 0L;; l = localFile.length())
      {
        localJSONObject.put("size", l);
        localJSONObject.put("type", FileHelper.getMimeType(localFile.getAbsolutePath(), cordova));
        localJSONObject.put("name", localFile.getName());
        localJSONObject.put("fullPath", fullPath);
        localJSONObject.put("lastModifiedDate", localFile.lastModified());
        return localJSONObject;
      }
      return null;
    }
    catch (JSONException paramLocalFilesystemURL) {}
  }
  
  OutputStream getOutputStreamForURL(LocalFilesystemURL paramLocalFilesystemURL)
    throws FileNotFoundException
  {
    return new FileOutputStream(new File(filesystemPathForURL(paramLocalFilesystemURL)));
  }
  
  public JSONObject makeEntryForFile(File paramFile)
    throws JSONException
  {
    String str = fullPathForFilesystemPath(paramFile.getAbsolutePath());
    if (str != null) {
      return makeEntryForPath(str, name, Boolean.valueOf(paramFile.isDirectory()), Uri.fromFile(paramFile).toString());
    }
    return null;
  }
  
  protected String normalizePath(String paramString)
  {
    boolean bool = paramString.startsWith("/");
    Object localObject = paramString;
    if (bool) {
      localObject = paramString.substring(1);
    }
    localObject = new ArrayList(Arrays.asList(((String)localObject).split("/+")));
    int j;
    for (int i = 0; i < ((ArrayList)localObject).size(); i = j + 1)
    {
      j = i;
      if (((String)((ArrayList)localObject).get(i)).equals(".."))
      {
        ((ArrayList)localObject).remove(i);
        j = i;
        if (i > 0)
        {
          ((ArrayList)localObject).remove(i - 1);
          j = i - 1;
        }
      }
    }
    paramString = new StringBuilder();
    localObject = ((ArrayList)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      String str = (String)((Iterator)localObject).next();
      paramString.append("/");
      paramString.append(str);
    }
    if (bool) {
      return paramString.toString();
    }
    return paramString.toString().substring(1);
  }
  
  public JSONArray readEntriesAtLocalURL(LocalFilesystemURL paramLocalFilesystemURL)
    throws FileNotFoundException
  {
    Object localObject = new File(filesystemPathForURL(paramLocalFilesystemURL));
    if (!((File)localObject).exists()) {
      throw new FileNotFoundException();
    }
    JSONArray localJSONArray = new JSONArray();
    int i;
    if (((File)localObject).isDirectory())
    {
      localObject = ((File)localObject).listFiles();
      i = 0;
    }
    for (;;)
    {
      if ((i >= localObject.length) || (localObject[i].canRead())) {}
      try
      {
        localJSONArray.put(makeEntryForPath(fullPathForFilesystemPath(localObject[i].getAbsolutePath()), filesystemName, Boolean.valueOf(localObject[i].isDirectory()), Uri.fromFile(localObject[i]).toString()));
        i += 1;
        continue;
        return localJSONArray;
      }
      catch (JSONException localJSONException)
      {
        for (;;) {}
      }
    }
  }
  
  public void readFileAtURL(LocalFilesystemURL paramLocalFilesystemURL, long paramLong1, long paramLong2, Filesystem.ReadFileCallback paramReadFileCallback)
    throws IOException
  {
    Object localObject = new File(filesystemPathForURL(paramLocalFilesystemURL));
    paramLocalFilesystemURL = FileHelper.getMimeTypeForExtension(((File)localObject).getAbsolutePath());
    long l = paramLong2;
    if (paramLong2 < 0L) {
      l = ((File)localObject).length();
    }
    localObject = new FileInputStream((File)localObject);
    if (paramLong1 > 0L) {}
    try
    {
      ((InputStream)localObject).skip(paramLong1);
      paramReadFileCallback.handleData(new Filesystem.LimitedInputStream(this, (InputStream)localObject, l - paramLong1), paramLocalFilesystemURL);
      return;
    }
    finally
    {
      ((InputStream)localObject).close();
    }
  }
  
  public boolean recursiveRemoveFileAtLocalURL(LocalFilesystemURL paramLocalFilesystemURL)
    throws FileExistsException
  {
    return removeDirRecursively(new File(filesystemPathForURL(paramLocalFilesystemURL)));
  }
  
  protected boolean removeDirRecursively(File paramFile)
    throws FileExistsException
  {
    if (paramFile.isDirectory())
    {
      File[] arrayOfFile = paramFile.listFiles();
      int j = arrayOfFile.length;
      int i = 0;
      while (i < j)
      {
        removeDirRecursively(arrayOfFile[i]);
        i += 1;
      }
    }
    if (!paramFile.delete()) {
      throw new FileExistsException("could not delete: " + paramFile.getName());
    }
    return true;
  }
  
  public boolean removeFileAtLocalURL(LocalFilesystemURL paramLocalFilesystemURL)
    throws InvalidModificationException
  {
    paramLocalFilesystemURL = new File(filesystemPathForURL(paramLocalFilesystemURL));
    if ((paramLocalFilesystemURL.isDirectory()) && (paramLocalFilesystemURL.list().length > 0)) {
      throw new InvalidModificationException("You can't delete a directory that is not empty.");
    }
    return paramLocalFilesystemURL.delete();
  }
  
  public long truncateFileAtURL(LocalFilesystemURL paramLocalFilesystemURL, long paramLong)
    throws IOException
  {
    if (!new File(filesystemPathForURL(paramLocalFilesystemURL)).exists()) {
      throw new FileNotFoundException("File at " + URL + " does not exist.");
    }
    paramLocalFilesystemURL = new RandomAccessFile(filesystemPathForURL(paramLocalFilesystemURL), "rw");
    try
    {
      if (paramLocalFilesystemURL.length() >= paramLong)
      {
        paramLocalFilesystemURL.getChannel().truncate(paramLong);
        return paramLong;
      }
      paramLong = paramLocalFilesystemURL.length();
      return paramLong;
    }
    finally
    {
      paramLocalFilesystemURL.close();
    }
  }
  
  /* Error */
  public long writeToFileAtURL(LocalFilesystemURL paramLocalFilesystemURL, String paramString, int paramInt, boolean paramBoolean)
    throws IOException, NoModificationAllowedException
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore 5
    //   3: iload_3
    //   4: ifle +14 -> 18
    //   7: aload_0
    //   8: aload_1
    //   9: iload_3
    //   10: i2l
    //   11: invokevirtual 483	org/apache/cordova/file/LocalFilesystem:truncateFileAtURL	(Lorg/apache/cordova/file/LocalFilesystemURL;J)J
    //   14: pop2
    //   15: iconst_1
    //   16: istore 5
    //   18: iload 4
    //   20: ifeq +82 -> 102
    //   23: aload_2
    //   24: iconst_0
    //   25: invokestatic 489	android/util/Base64:decode	(Ljava/lang/String;I)[B
    //   28: astore_2
    //   29: new 491	java/io/ByteArrayInputStream
    //   32: dup
    //   33: aload_2
    //   34: invokespecial 494	java/io/ByteArrayInputStream:<init>	([B)V
    //   37: astore 7
    //   39: aload_2
    //   40: arraylength
    //   41: newarray <illegal type>
    //   43: astore 8
    //   45: new 80	java/io/FileOutputStream
    //   48: dup
    //   49: aload_0
    //   50: aload_1
    //   51: invokevirtual 29	org/apache/cordova/file/LocalFilesystem:filesystemPathForURL	(Lorg/apache/cordova/file/LocalFilesystemURL;)Ljava/lang/String;
    //   54: iload 5
    //   56: invokespecial 497	java/io/FileOutputStream:<init>	(Ljava/lang/String;Z)V
    //   59: astore 6
    //   61: aload 7
    //   63: aload 8
    //   65: iconst_0
    //   66: aload 8
    //   68: arraylength
    //   69: invokevirtual 501	java/io/ByteArrayInputStream:read	([BII)I
    //   72: pop
    //   73: aload 6
    //   75: aload 8
    //   77: iconst_0
    //   78: aload_2
    //   79: arraylength
    //   80: invokevirtual 505	java/io/FileOutputStream:write	([BII)V
    //   83: aload 6
    //   85: invokevirtual 508	java/io/FileOutputStream:flush	()V
    //   88: aload 6
    //   90: invokevirtual 100	java/io/FileOutputStream:close	()V
    //   93: aload_0
    //   94: aload_1
    //   95: invokespecial 510	org/apache/cordova/file/LocalFilesystem:broadcastNewFile	(Lorg/apache/cordova/file/LocalFilesystemURL;)V
    //   98: aload_2
    //   99: arraylength
    //   100: i2l
    //   101: lreturn
    //   102: aload_2
    //   103: invokevirtual 514	java/lang/String:getBytes	()[B
    //   106: astore_2
    //   107: goto -78 -> 29
    //   110: astore_2
    //   111: aload 6
    //   113: invokevirtual 100	java/io/FileOutputStream:close	()V
    //   116: aload_2
    //   117: athrow
    //   118: astore_2
    //   119: new 108	org/apache/cordova/file/NoModificationAllowedException
    //   122: dup
    //   123: aload_1
    //   124: invokevirtual 515	org/apache/cordova/file/LocalFilesystemURL:toString	()Ljava/lang/String;
    //   127: invokespecial 132	org/apache/cordova/file/NoModificationAllowedException:<init>	(Ljava/lang/String;)V
    //   130: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	131	0	this	LocalFilesystem
    //   0	131	1	paramLocalFilesystemURL	LocalFilesystemURL
    //   0	131	2	paramString	String
    //   0	131	3	paramInt	int
    //   0	131	4	paramBoolean	boolean
    //   1	54	5	bool	boolean
    //   59	53	6	localFileOutputStream	FileOutputStream
    //   37	25	7	localByteArrayInputStream	java.io.ByteArrayInputStream
    //   43	33	8	arrayOfByte	byte[]
    // Exception table:
    //   from	to	target	type
    //   61	88	110	finally
    //   39	61	118	java/lang/NullPointerException
    //   88	98	118	java/lang/NullPointerException
    //   111	118	118	java/lang/NullPointerException
  }
}

/* Location:
 * Qualified Name:     org.apache.cordova.file.LocalFilesystem
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */