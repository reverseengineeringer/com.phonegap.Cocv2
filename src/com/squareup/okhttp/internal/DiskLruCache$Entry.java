package com.squareup.okhttp.internal;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

final class DiskLruCache$Entry
{
  private DiskLruCache.Editor currentEditor;
  private final String key;
  private final long[] lengths;
  private boolean readable;
  private long sequenceNumber;
  
  private DiskLruCache$Entry(DiskLruCache paramDiskLruCache, String paramString)
  {
    key = paramString;
    lengths = new long[DiskLruCache.access$7(paramDiskLruCache)];
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
    return new File(DiskLruCache.access$8(this$0), key + "." + paramInt);
  }
  
  public File getDirtyFile(int paramInt)
  {
    return new File(DiskLruCache.access$8(this$0), key + "." + paramInt + ".tmp");
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

/* Location:
 * Qualified Name:     com.squareup.okhttp.internal.DiskLruCache.Entry
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */