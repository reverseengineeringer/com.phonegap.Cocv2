package com.squareup.okhttp.internal;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class DiskLruCache
  implements Closeable
{
  static final long ANY_SEQUENCE_NUMBER = -1L;
  private static final String CLEAN = "CLEAN";
  private static final String DIRTY = "DIRTY";
  static final String JOURNAL_FILE = "journal";
  static final String JOURNAL_FILE_BACKUP = "journal.bkp";
  static final String JOURNAL_FILE_TEMP = "journal.tmp";
  static final Pattern LEGAL_KEY_PATTERN = Pattern.compile("[a-z0-9_-]{1,64}");
  static final String MAGIC = "libcore.io.DiskLruCache";
  private static final OutputStream NULL_OUTPUT_STREAM = new OutputStream()
  {
    public void write(int paramAnonymousInt)
      throws IOException
    {}
  };
  private static final String READ = "READ";
  private static final String REMOVE = "REMOVE";
  static final String VERSION_1 = "1";
  private final int appVersion;
  private final Callable<Void> cleanupCallable = new Callable()
  {
    public Void call()
      throws Exception
    {
      synchronized (DiskLruCache.this)
      {
        if (journalWriter == null) {
          return null;
        }
        DiskLruCache.this.trimToSize();
        if (DiskLruCache.this.journalRebuildRequired())
        {
          DiskLruCache.this.rebuildJournal();
          redundantOpCount = 0;
        }
        return null;
      }
    }
  };
  private final File directory;
  final ThreadPoolExecutor executorService = new ThreadPoolExecutor(0, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue());
  private final File journalFile;
  private final File journalFileBackup;
  private final File journalFileTmp;
  private Writer journalWriter;
  private final LinkedHashMap<String, Entry> lruEntries = new LinkedHashMap(0, 0.75F, true);
  private long maxSize;
  private long nextSequenceNumber = 0L;
  private int redundantOpCount;
  private long size = 0L;
  private final int valueCount;
  
  private DiskLruCache(File paramFile, int paramInt1, int paramInt2, long paramLong)
  {
    directory = paramFile;
    appVersion = paramInt1;
    journalFile = new File(paramFile, "journal");
    journalFileTmp = new File(paramFile, "journal.tmp");
    journalFileBackup = new File(paramFile, "journal.bkp");
    valueCount = paramInt2;
    maxSize = paramLong;
  }
  
  private void checkNotClosed()
  {
    if (journalWriter == null) {
      throw new IllegalStateException("cache is closed");
    }
  }
  
  private void completeEdit(Editor paramEditor, boolean paramBoolean)
    throws IOException
  {
    Entry localEntry;
    try
    {
      localEntry = entry;
      if (currentEditor != paramEditor) {
        throw new IllegalStateException();
      }
    }
    finally {}
    int i;
    if ((paramBoolean) && (!readable))
    {
      i = 0;
      if (i >= valueCount) {
        break label389;
      }
    }
    for (;;)
    {
      long l1;
      if (i >= valueCount)
      {
        redundantOpCount += 1;
        currentEditor = null;
        if (!(readable | paramBoolean)) {
          break label340;
        }
        readable = true;
        journalWriter.write("CLEAN " + key + localEntry.getLengths() + '\n');
        if (paramBoolean)
        {
          l1 = nextSequenceNumber;
          nextSequenceNumber = (1L + l1);
          sequenceNumber = l1;
        }
      }
      for (;;)
      {
        journalWriter.flush();
        if ((size > maxSize) || (journalRebuildRequired())) {
          executorService.submit(cleanupCallable);
        }
        for (;;)
        {
          return;
          if (written[i] == 0)
          {
            paramEditor.abort();
            throw new IllegalStateException("Newly created entry didn't create value for index " + i);
          }
          if (localEntry.getDirtyFile(i).exists()) {
            break;
          }
          paramEditor.abort();
        }
        paramEditor = localEntry.getDirtyFile(i);
        if (paramBoolean)
        {
          if (!paramEditor.exists()) {
            break label401;
          }
          File localFile = localEntry.getCleanFile(i);
          paramEditor.renameTo(localFile);
          l1 = lengths[i];
          long l2 = localFile.length();
          lengths[i] = l2;
          size = (size - l1 + l2);
          break label401;
        }
        deleteIfExists(paramEditor);
        break label401;
        label340:
        lruEntries.remove(key);
        journalWriter.write("REMOVE " + key + '\n');
      }
      label389:
      i = 0;
      continue;
      i += 1;
      break;
      label401:
      i += 1;
    }
  }
  
  private static void deleteIfExists(File paramFile)
    throws IOException
  {
    if ((paramFile.exists()) && (!paramFile.delete())) {
      throw new IOException();
    }
  }
  
  private Editor edit(String paramString, long paramLong)
    throws IOException
  {
    Editor localEditor1 = null;
    for (;;)
    {
      Entry localEntry;
      try
      {
        checkNotClosed();
        validateKey(paramString);
        localEntry = (Entry)lruEntries.get(paramString);
        if (paramLong != -1L)
        {
          localObject = localEditor1;
          if (localEntry != null)
          {
            long l = sequenceNumber;
            if (l != paramLong) {
              localObject = localEditor1;
            }
          }
          else
          {
            return (Editor)localObject;
          }
        }
        if (localEntry == null)
        {
          localObject = new Entry(paramString, null);
          lruEntries.put(paramString, localObject);
          localEditor1 = new Editor((Entry)localObject, null);
          currentEditor = localEditor1;
          journalWriter.write("DIRTY " + paramString + '\n');
          journalWriter.flush();
          localObject = localEditor1;
          continue;
        }
        localEditor2 = currentEditor;
      }
      finally {}
      Editor localEditor2;
      Object localObject = localEntry;
      if (localEditor2 != null) {
        localObject = localEditor1;
      }
    }
  }
  
  private static String inputStreamToString(InputStream paramInputStream)
    throws IOException
  {
    return Util.readFully(new InputStreamReader(paramInputStream, Util.UTF_8));
  }
  
  private boolean journalRebuildRequired()
  {
    return (redundantOpCount >= 2000) && (redundantOpCount >= lruEntries.size());
  }
  
  public static DiskLruCache open(File paramFile, int paramInt1, int paramInt2, long paramLong)
    throws IOException
  {
    if (paramLong <= 0L) {
      throw new IllegalArgumentException("maxSize <= 0");
    }
    if (paramInt2 <= 0) {
      throw new IllegalArgumentException("valueCount <= 0");
    }
    Object localObject = new File(paramFile, "journal.bkp");
    File localFile;
    if (((File)localObject).exists())
    {
      localFile = new File(paramFile, "journal");
      if (!localFile.exists()) {
        break label150;
      }
      ((File)localObject).delete();
    }
    for (;;)
    {
      localObject = new DiskLruCache(paramFile, paramInt1, paramInt2, paramLong);
      if (!journalFile.exists()) {
        break label211;
      }
      try
      {
        ((DiskLruCache)localObject).readJournal();
        ((DiskLruCache)localObject).processJournal();
        journalWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(journalFile, true), Util.US_ASCII));
        return (DiskLruCache)localObject;
      }
      catch (IOException localIOException)
      {
        label150:
        Platform.get().logW("DiskLruCache " + paramFile + " is corrupt: " + localIOException.getMessage() + ", removing");
        ((DiskLruCache)localObject).delete();
      }
      renameTo((File)localObject, localFile, false);
    }
    label211:
    paramFile.mkdirs();
    paramFile = new DiskLruCache(paramFile, paramInt1, paramInt2, paramLong);
    paramFile.rebuildJournal();
    return paramFile;
  }
  
  private void processJournal()
    throws IOException
  {
    deleteIfExists(journalFileTmp);
    Iterator localIterator = lruEntries.values().iterator();
    Entry localEntry;
    for (;;)
    {
      if (!localIterator.hasNext()) {
        return;
      }
      localEntry = (Entry)localIterator.next();
      if (currentEditor != null) {
        break;
      }
      i = 0;
      while (i < valueCount)
      {
        size += lengths[i];
        i += 1;
      }
    }
    currentEditor = null;
    int i = 0;
    for (;;)
    {
      if (i >= valueCount)
      {
        localIterator.remove();
        break;
      }
      deleteIfExists(localEntry.getCleanFile(i));
      deleteIfExists(localEntry.getDirtyFile(i));
      i += 1;
    }
  }
  
  private void readJournal()
    throws IOException
  {
    StrictLineReader localStrictLineReader = new StrictLineReader(new FileInputStream(journalFile), Util.US_ASCII);
    int i;
    try
    {
      String str1 = localStrictLineReader.readLine();
      String str2 = localStrictLineReader.readLine();
      String str3 = localStrictLineReader.readLine();
      String str4 = localStrictLineReader.readLine();
      String str5 = localStrictLineReader.readLine();
      if ((!"libcore.io.DiskLruCache".equals(str1)) || (!"1".equals(str2)) || (!Integer.toString(appVersion).equals(str3)) || (!Integer.toString(valueCount).equals(str4)) || (!"".equals(str5))) {
        throw new IOException("unexpected journal header: [" + str1 + ", " + str2 + ", " + str4 + ", " + str5 + "]");
      }
    }
    finally
    {
      Util.closeQuietly(localStrictLineReader);
      throw ((Throwable)localObject);
    }
  }
  
  private void readJournalLine(String paramString)
    throws IOException
  {
    int i = paramString.indexOf(' ');
    if (i == -1) {
      throw new IOException("unexpected journal line: " + paramString);
    }
    int j = i + 1;
    int k = paramString.indexOf(' ', j);
    Object localObject2;
    Object localObject1;
    if (k == -1)
    {
      localObject2 = paramString.substring(j);
      localObject1 = localObject2;
      if (i != "REMOVE".length()) {
        break label109;
      }
      localObject1 = localObject2;
      if (!paramString.startsWith("REMOVE")) {
        break label109;
      }
      lruEntries.remove(localObject2);
    }
    label109:
    do
    {
      return;
      localObject1 = paramString.substring(j, k);
      Entry localEntry = (Entry)lruEntries.get(localObject1);
      localObject2 = localEntry;
      if (localEntry == null)
      {
        localObject2 = new Entry((String)localObject1, null);
        lruEntries.put(localObject1, localObject2);
      }
      if ((k != -1) && (i == "CLEAN".length()) && (paramString.startsWith("CLEAN")))
      {
        paramString = paramString.substring(k + 1).split(" ");
        readable = true;
        currentEditor = null;
        ((Entry)localObject2).setLengths(paramString);
        return;
      }
      if ((k == -1) && (i == "DIRTY".length()) && (paramString.startsWith("DIRTY")))
      {
        currentEditor = new Editor((Entry)localObject2, null);
        return;
      }
    } while ((k == -1) && (i == "READ".length()) && (paramString.startsWith("READ")));
    throw new IOException("unexpected journal line: " + paramString);
  }
  
  private void rebuildJournal()
    throws IOException
  {
    for (;;)
    {
      try
      {
        if (journalWriter != null) {
          journalWriter.close();
        }
        BufferedWriter localBufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(journalFileTmp), Util.US_ASCII));
        Entry localEntry;
        try
        {
          localBufferedWriter.write("libcore.io.DiskLruCache");
          localBufferedWriter.write("\n");
          localBufferedWriter.write("1");
          localBufferedWriter.write("\n");
          localBufferedWriter.write(Integer.toString(appVersion));
          localBufferedWriter.write("\n");
          localBufferedWriter.write(Integer.toString(valueCount));
          localBufferedWriter.write("\n");
          localBufferedWriter.write("\n");
          Iterator localIterator = lruEntries.values().iterator();
          boolean bool = localIterator.hasNext();
          if (!bool)
          {
            localBufferedWriter.close();
            if (journalFile.exists()) {
              renameTo(journalFile, journalFileBackup, true);
            }
            renameTo(journalFileTmp, journalFile, false);
            journalFileBackup.delete();
            journalWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(journalFile, true), Util.US_ASCII));
            return;
          }
          localEntry = (Entry)localIterator.next();
          if (currentEditor != null)
          {
            localBufferedWriter.write("DIRTY " + key + '\n');
            continue;
            localObject1 = finally;
          }
        }
        finally
        {
          localBufferedWriter.close();
        }
        ((Writer)localObject1).write("CLEAN " + key + localEntry.getLengths() + '\n');
      }
      finally {}
    }
  }
  
  private static void renameTo(File paramFile1, File paramFile2, boolean paramBoolean)
    throws IOException
  {
    if (paramBoolean) {
      deleteIfExists(paramFile2);
    }
    if (!paramFile1.renameTo(paramFile2)) {
      throw new IOException();
    }
  }
  
  private void trimToSize()
    throws IOException
  {
    for (;;)
    {
      if (size <= maxSize) {
        return;
      }
      remove((String)((Map.Entry)lruEntries.entrySet().iterator().next()).getKey());
    }
  }
  
  private void validateKey(String paramString)
  {
    if (!LEGAL_KEY_PATTERN.matcher(paramString).matches()) {
      throw new IllegalArgumentException("keys must match regex [a-z0-9_-]{1,64}: \"" + paramString + "\"");
    }
  }
  
  public void close()
    throws IOException
  {
    for (;;)
    {
      try
      {
        Object localObject1 = journalWriter;
        if (localObject1 == null) {
          return;
        }
        localObject1 = new ArrayList(lruEntries.values()).iterator();
        if (!((Iterator)localObject1).hasNext())
        {
          trimToSize();
          journalWriter.close();
          journalWriter = null;
          continue;
        }
        localEntry = (Entry)((Iterator)localObject2).next();
      }
      finally {}
      Entry localEntry;
      if (currentEditor != null) {
        currentEditor.abort();
      }
    }
  }
  
  public void delete()
    throws IOException
  {
    close();
    Util.deleteContents(directory);
  }
  
  public Editor edit(String paramString)
    throws IOException
  {
    return edit(paramString, -1L);
  }
  
  public void flush()
    throws IOException
  {
    try
    {
      checkNotClosed();
      trimToSize();
      journalWriter.flush();
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  /* Error */
  public Snapshot get(String paramString)
    throws IOException
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 5
    //   3: aload_0
    //   4: monitorenter
    //   5: aload_0
    //   6: invokespecial 307	com/squareup/okhttp/internal/DiskLruCache:checkNotClosed	()V
    //   9: aload_0
    //   10: aload_1
    //   11: invokespecial 310	com/squareup/okhttp/internal/DiskLruCache:validateKey	(Ljava/lang/String;)V
    //   14: aload_0
    //   15: getfield 106	com/squareup/okhttp/internal/DiskLruCache:lruEntries	Ljava/util/LinkedHashMap;
    //   18: aload_1
    //   19: invokevirtual 313	java/util/LinkedHashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   22: checkcast 18	com/squareup/okhttp/internal/DiskLruCache$Entry
    //   25: astore 7
    //   27: aload 7
    //   29: ifnonnull +12 -> 41
    //   32: aload 5
    //   34: astore 4
    //   36: aload_0
    //   37: monitorexit
    //   38: aload 4
    //   40: areturn
    //   41: aload 5
    //   43: astore 4
    //   45: aload 7
    //   47: invokestatic 216	com/squareup/okhttp/internal/DiskLruCache$Entry:access$0	(Lcom/squareup/okhttp/internal/DiskLruCache$Entry;)Z
    //   50: ifeq -14 -> 36
    //   53: aload_0
    //   54: getfield 148	com/squareup/okhttp/internal/DiskLruCache:valueCount	I
    //   57: anewarray 548	java/io/InputStream
    //   60: astore 6
    //   62: iconst_0
    //   63: istore_2
    //   64: aload_0
    //   65: getfield 148	com/squareup/okhttp/internal/DiskLruCache:valueCount	I
    //   68: istore_3
    //   69: iload_2
    //   70: iload_3
    //   71: if_icmplt +94 -> 165
    //   74: aload_0
    //   75: aload_0
    //   76: getfield 181	com/squareup/okhttp/internal/DiskLruCache:redundantOpCount	I
    //   79: iconst_1
    //   80: iadd
    //   81: putfield 181	com/squareup/okhttp/internal/DiskLruCache:redundantOpCount	I
    //   84: aload_0
    //   85: getfield 154	com/squareup/okhttp/internal/DiskLruCache:journalWriter	Ljava/io/Writer;
    //   88: new 224	java/lang/StringBuilder
    //   91: dup
    //   92: ldc_w 550
    //   95: invokespecial 227	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   98: aload_1
    //   99: invokevirtual 234	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   102: bipush 10
    //   104: invokevirtual 241	java/lang/StringBuilder:append	(C)Ljava/lang/StringBuilder;
    //   107: invokevirtual 244	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   110: invokevirtual 553	java/io/Writer:append	(Ljava/lang/CharSequence;)Ljava/io/Writer;
    //   113: pop
    //   114: aload_0
    //   115: invokespecial 173	com/squareup/okhttp/internal/DiskLruCache:journalRebuildRequired	()Z
    //   118: ifeq +15 -> 133
    //   121: aload_0
    //   122: getfield 126	com/squareup/okhttp/internal/DiskLruCache:executorService	Ljava/util/concurrent/ThreadPoolExecutor;
    //   125: aload_0
    //   126: getfield 131	com/squareup/okhttp/internal/DiskLruCache:cleanupCallable	Ljava/util/concurrent/Callable;
    //   129: invokevirtual 259	java/util/concurrent/ThreadPoolExecutor:submit	(Ljava/util/concurrent/Callable;)Ljava/util/concurrent/Future;
    //   132: pop
    //   133: new 21	com/squareup/okhttp/internal/DiskLruCache$Snapshot
    //   136: dup
    //   137: aload_0
    //   138: aload_1
    //   139: aload 7
    //   141: invokestatic 316	com/squareup/okhttp/internal/DiskLruCache$Entry:access$8	(Lcom/squareup/okhttp/internal/DiskLruCache$Entry;)J
    //   144: aload 6
    //   146: aload 7
    //   148: invokestatic 287	com/squareup/okhttp/internal/DiskLruCache$Entry:access$7	(Lcom/squareup/okhttp/internal/DiskLruCache$Entry;)[J
    //   151: aconst_null
    //   152: invokespecial 556	com/squareup/okhttp/internal/DiskLruCache$Snapshot:<init>	(Lcom/squareup/okhttp/internal/DiskLruCache;Ljava/lang/String;J[Ljava/io/InputStream;[JLcom/squareup/okhttp/internal/DiskLruCache$Snapshot;)V
    //   155: astore 4
    //   157: goto -121 -> 36
    //   160: astore_1
    //   161: aload_0
    //   162: monitorexit
    //   163: aload_1
    //   164: athrow
    //   165: aload 6
    //   167: iload_2
    //   168: new 436	java/io/FileInputStream
    //   171: dup
    //   172: aload 7
    //   174: iload_2
    //   175: invokevirtual 280	com/squareup/okhttp/internal/DiskLruCache$Entry:getCleanFile	(I)Ljava/io/File;
    //   178: invokespecial 438	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   181: aastore
    //   182: iload_2
    //   183: iconst_1
    //   184: iadd
    //   185: istore_2
    //   186: goto -122 -> 64
    //   189: astore_1
    //   190: iconst_0
    //   191: istore_2
    //   192: aload 5
    //   194: astore 4
    //   196: iload_2
    //   197: aload_0
    //   198: getfield 148	com/squareup/okhttp/internal/DiskLruCache:valueCount	I
    //   201: if_icmpge -165 -> 36
    //   204: aload 5
    //   206: astore 4
    //   208: aload 6
    //   210: iload_2
    //   211: aaload
    //   212: ifnull -176 -> 36
    //   215: aload 6
    //   217: iload_2
    //   218: aaload
    //   219: invokestatic 466	com/squareup/okhttp/internal/Util:closeQuietly	(Ljava/io/Closeable;)V
    //   222: iload_2
    //   223: iconst_1
    //   224: iadd
    //   225: istore_2
    //   226: goto -34 -> 192
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	229	0	this	DiskLruCache
    //   0	229	1	paramString	String
    //   63	163	2	i	int
    //   68	4	3	j	int
    //   34	173	4	localObject1	Object
    //   1	204	5	localObject2	Object
    //   60	156	6	arrayOfInputStream	InputStream[]
    //   25	148	7	localEntry	Entry
    // Exception table:
    //   from	to	target	type
    //   5	27	160	finally
    //   45	62	160	finally
    //   64	69	160	finally
    //   74	133	160	finally
    //   133	157	160	finally
    //   165	182	160	finally
    //   196	204	160	finally
    //   215	222	160	finally
    //   64	69	189	java/io/FileNotFoundException
    //   165	182	189	java/io/FileNotFoundException
  }
  
  public File getDirectory()
  {
    return directory;
  }
  
  public long getMaxSize()
  {
    return maxSize;
  }
  
  public boolean isClosed()
  {
    return journalWriter == null;
  }
  
  public boolean remove(String paramString)
    throws IOException
  {
    for (;;)
    {
      Entry localEntry;
      int i;
      try
      {
        checkNotClosed();
        validateKey(paramString);
        localEntry = (Entry)lruEntries.get(paramString);
        if (localEntry != null)
        {
          localObject = currentEditor;
          if (localObject == null) {}
        }
        else
        {
          bool = false;
          return bool;
        }
        i = 0;
        if (i >= valueCount)
        {
          redundantOpCount += 1;
          journalWriter.append("REMOVE " + paramString + '\n');
          lruEntries.remove(paramString);
          if (!journalRebuildRequired()) {
            break label206;
          }
          executorService.submit(cleanupCallable);
          break label206;
        }
        Object localObject = localEntry.getCleanFile(i);
        if (!((File)localObject).delete()) {
          throw new IOException("failed to delete " + localObject);
        }
      }
      finally {}
      size -= lengths[i];
      lengths[i] = 0L;
      i += 1;
      continue;
      label206:
      boolean bool = true;
    }
  }
  
  public void setMaxSize(long paramLong)
  {
    try
    {
      maxSize = paramLong;
      executorService.submit(cleanupCallable);
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public long size()
  {
    try
    {
      long l = size;
      return l;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public final class Editor
  {
    private boolean committed;
    private final DiskLruCache.Entry entry;
    private boolean hasErrors;
    private final boolean[] written;
    
    private Editor(DiskLruCache.Entry paramEntry)
    {
      entry = paramEntry;
      if (DiskLruCache.Entry.access$0(paramEntry)) {}
      for (this$1 = null;; this$1 = new boolean[valueCount])
      {
        written = DiskLruCache.this;
        return;
      }
    }
    
    public void abort()
      throws IOException
    {
      DiskLruCache.this.completeEdit(this, false);
    }
    
    public void abortUnlessCommitted()
    {
      if (!committed) {}
      try
      {
        abort();
        return;
      }
      catch (IOException localIOException) {}
    }
    
    public void commit()
      throws IOException
    {
      if (hasErrors)
      {
        DiskLruCache.this.completeEdit(this, false);
        remove(DiskLruCache.Entry.access$2(entry));
      }
      for (;;)
      {
        committed = true;
        return;
        DiskLruCache.this.completeEdit(this, true);
      }
    }
    
    public String getString(int paramInt)
      throws IOException
    {
      InputStream localInputStream = newInputStream(paramInt);
      if (localInputStream != null) {
        return DiskLruCache.inputStreamToString(localInputStream);
      }
      return null;
    }
    
    public InputStream newInputStream(int paramInt)
      throws IOException
    {
      synchronized (DiskLruCache.this)
      {
        if (DiskLruCache.Entry.access$1(entry) != this) {
          throw new IllegalStateException();
        }
      }
      if (!DiskLruCache.Entry.access$0(entry)) {
        return null;
      }
      try
      {
        FileInputStream localFileInputStream = new FileInputStream(entry.getCleanFile(paramInt));
        return localFileInputStream;
      }
      catch (FileNotFoundException localFileNotFoundException) {}
      return null;
    }
    
    public OutputStream newOutputStream(int paramInt)
      throws IOException
    {
      synchronized (DiskLruCache.this)
      {
        if (DiskLruCache.Entry.access$1(entry) != this) {
          throw new IllegalStateException();
        }
      }
      if (!DiskLruCache.Entry.access$0(entry)) {
        written[paramInt] = true;
      }
      File localFile = entry.getDirtyFile(paramInt);
      try
      {
        Object localObject2 = new FileOutputStream(localFile);
        localObject2 = new FaultHidingOutputStream((OutputStream)localObject2, null);
        return (OutputStream)localObject2;
      }
      catch (FileNotFoundException localFileNotFoundException1)
      {
        for (;;)
        {
          directory.mkdirs();
          try
          {
            FileOutputStream localFileOutputStream = new FileOutputStream(localFile);
          }
          catch (FileNotFoundException localFileNotFoundException2)
          {
            OutputStream localOutputStream = DiskLruCache.NULL_OUTPUT_STREAM;
            return localOutputStream;
          }
        }
      }
    }
    
    public void set(int paramInt, String paramString)
      throws IOException
    {
      Object localObject3 = null;
      try
      {
        OutputStreamWriter localOutputStreamWriter = new OutputStreamWriter(newOutputStream(paramInt), Util.UTF_8);
        Util.closeQuietly(paramString);
      }
      finally
      {
        try
        {
          localOutputStreamWriter.write(paramString);
          Util.closeQuietly(localOutputStreamWriter);
          return;
        }
        finally
        {
          paramString = (String)localObject1;
          Object localObject2 = localObject4;
        }
        localObject1 = finally;
        paramString = (String)localObject3;
      }
      throw ((Throwable)localObject1);
    }
    
    private class FaultHidingOutputStream
      extends FilterOutputStream
    {
      private FaultHidingOutputStream(OutputStream paramOutputStream)
      {
        super();
      }
      
      public void close()
      {
        try
        {
          out.close();
          return;
        }
        catch (IOException localIOException)
        {
          hasErrors = true;
        }
      }
      
      public void flush()
      {
        try
        {
          out.flush();
          return;
        }
        catch (IOException localIOException)
        {
          hasErrors = true;
        }
      }
      
      public void write(int paramInt)
      {
        try
        {
          out.write(paramInt);
          return;
        }
        catch (IOException localIOException)
        {
          hasErrors = true;
        }
      }
      
      public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
      {
        try
        {
          out.write(paramArrayOfByte, paramInt1, paramInt2);
          return;
        }
        catch (IOException paramArrayOfByte)
        {
          hasErrors = true;
        }
      }
    }
  }
  
  private final class Entry
  {
    private DiskLruCache.Editor currentEditor;
    private final String key;
    private final long[] lengths;
    private boolean readable;
    private long sequenceNumber;
    
    private Entry(String paramString)
    {
      key = paramString;
      lengths = new long[valueCount];
    }
    
    private IOException invalidLengths(String[] paramArrayOfString)
      throws IOException
    {
      throw new IOException("unexpected journal line: " + Arrays.toString(paramArrayOfString));
    }
    
    /* Error */
    private void setLengths(String[] paramArrayOfString)
      throws IOException
    {
      // Byte code:
      //   0: aload_1
      //   1: arraylength
      //   2: aload_0
      //   3: getfield 23	com/squareup/okhttp/internal/DiskLruCache$Entry:this$0	Lcom/squareup/okhttp/internal/DiskLruCache;
      //   6: invokestatic 32	com/squareup/okhttp/internal/DiskLruCache:access$7	(Lcom/squareup/okhttp/internal/DiskLruCache;)I
      //   9: if_icmpeq +9 -> 18
      //   12: aload_0
      //   13: aload_1
      //   14: invokespecial 95	com/squareup/okhttp/internal/DiskLruCache$Entry:invalidLengths	([Ljava/lang/String;)Ljava/io/IOException;
      //   17: athrow
      //   18: iconst_0
      //   19: istore_2
      //   20: iload_2
      //   21: aload_1
      //   22: arraylength
      //   23: if_icmplt +4 -> 27
      //   26: return
      //   27: aload_0
      //   28: getfield 34	com/squareup/okhttp/internal/DiskLruCache$Entry:lengths	[J
      //   31: iload_2
      //   32: aload_1
      //   33: iload_2
      //   34: aaload
      //   35: invokestatic 101	java/lang/Long:parseLong	(Ljava/lang/String;)J
      //   38: lastore
      //   39: iload_2
      //   40: iconst_1
      //   41: iadd
      //   42: istore_2
      //   43: goto -23 -> 20
      //   46: astore_3
      //   47: aload_0
      //   48: aload_1
      //   49: invokespecial 95	com/squareup/okhttp/internal/DiskLruCache$Entry:invalidLengths	([Ljava/lang/String;)Ljava/io/IOException;
      //   52: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	53	0	this	Entry
      //   0	53	1	paramArrayOfString	String[]
      //   19	24	2	i	int
      //   46	1	3	localNumberFormatException	NumberFormatException
      // Exception table:
      //   from	to	target	type
      //   20	26	46	java/lang/NumberFormatException
      //   27	39	46	java/lang/NumberFormatException
    }
    
    public File getCleanFile(int paramInt)
    {
      return new File(directory, key + "." + paramInt);
    }
    
    public File getDirtyFile(int paramInt)
    {
      return new File(directory, key + "." + paramInt + ".tmp");
    }
    
    public String getLengths()
      throws IOException
    {
      StringBuilder localStringBuilder = new StringBuilder();
      long[] arrayOfLong = lengths;
      int j = arrayOfLong.length;
      int i = 0;
      for (;;)
      {
        if (i >= j) {
          return localStringBuilder.toString();
        }
        long l = arrayOfLong[i];
        localStringBuilder.append(' ').append(l);
        i += 1;
      }
    }
  }
  
  public final class Snapshot
    implements Closeable
  {
    private final InputStream[] ins;
    private final String key;
    private final long[] lengths;
    private final long sequenceNumber;
    
    private Snapshot(String paramString, long paramLong, InputStream[] paramArrayOfInputStream, long[] paramArrayOfLong)
    {
      key = paramString;
      sequenceNumber = paramLong;
      ins = paramArrayOfInputStream;
      lengths = paramArrayOfLong;
    }
    
    public void close()
    {
      InputStream[] arrayOfInputStream = ins;
      int j = arrayOfInputStream.length;
      int i = 0;
      for (;;)
      {
        if (i >= j) {
          return;
        }
        Util.closeQuietly(arrayOfInputStream[i]);
        i += 1;
      }
    }
    
    public DiskLruCache.Editor edit()
      throws IOException
    {
      return DiskLruCache.this.edit(key, sequenceNumber);
    }
    
    public InputStream getInputStream(int paramInt)
    {
      return ins[paramInt];
    }
    
    public long getLength(int paramInt)
    {
      return lengths[paramInt];
    }
    
    public String getString(int paramInt)
      throws IOException
    {
      return DiskLruCache.inputStreamToString(getInputStream(paramInt));
    }
  }
}

/* Location:
 * Qualified Name:     com.squareup.okhttp.internal.DiskLruCache
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */