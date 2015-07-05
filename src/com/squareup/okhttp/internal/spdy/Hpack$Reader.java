package com.squareup.okhttp.internal.spdy;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

class Hpack$Reader
{
  private long bufferSize = 0L;
  private long bytesLeft = 0L;
  private final List<String> emittedHeaders = new ArrayList();
  private final List<Hpack.HeaderEntry> headerTable;
  private final DataInputStream in;
  private final long maxBufferSize = 4096L;
  private final BitSet referenceSet = new BitSet();
  
  Hpack$Reader(DataInputStream paramDataInputStream, boolean paramBoolean)
  {
    in = paramDataInputStream;
    if (paramBoolean)
    {
      headerTable = new ArrayList(Hpack.INITIAL_SERVER_TO_CLIENT_HEADER_TABLE);
      bufferSize = 1304L;
      return;
    }
    headerTable = new ArrayList(Hpack.INITIAL_CLIENT_TO_SERVER_HEADER_TABLE);
    bufferSize = 1262L;
  }
  
  private String getName(int paramInt)
  {
    return Hpack.HeaderEntry.access$0((Hpack.HeaderEntry)headerTable.get(paramInt));
  }
  
  private String getValue(int paramInt)
  {
    return Hpack.HeaderEntry.access$1((Hpack.HeaderEntry)headerTable.get(paramInt));
  }
  
  private void insertIntoHeaderTable(int paramInt, Hpack.HeaderEntry paramHeaderEntry)
  {
    int j = paramHeaderEntry.length();
    int i = j;
    if (paramInt != headerTable.size()) {
      i = j - ((Hpack.HeaderEntry)headerTable.get(paramInt)).length();
    }
    if (i > 4096L)
    {
      headerTable.clear();
      bufferSize = 0L;
      emittedHeaders.add(Hpack.HeaderEntry.access$0(paramHeaderEntry));
      emittedHeaders.add(Hpack.HeaderEntry.access$1(paramHeaderEntry));
      return;
    }
    while (bufferSize + i > 4096L)
    {
      remove(0);
      paramInt -= 1;
    }
    if (paramInt < 0)
    {
      paramInt = 0;
      headerTable.add(0, paramHeaderEntry);
    }
    for (;;)
    {
      bufferSize += i;
      referenceSet.set(paramInt);
      return;
      if (paramInt == headerTable.size()) {
        headerTable.add(paramInt, paramHeaderEntry);
      } else {
        headerTable.set(paramInt, paramHeaderEntry);
      }
    }
  }
  
  private int readByte()
    throws IOException
  {
    bytesLeft -= 1L;
    return in.readByte() & 0xFF;
  }
  
  private void readIndexedHeader(int paramInt)
  {
    if (referenceSet.get(paramInt))
    {
      referenceSet.clear(paramInt);
      return;
    }
    referenceSet.set(paramInt);
  }
  
  private void readLiteralHeaderWithIncrementalIndexingIndexedName(int paramInt)
    throws IOException
  {
    String str1 = getName(paramInt);
    String str2 = readString();
    insertIntoHeaderTable(headerTable.size(), new Hpack.HeaderEntry(str1, str2));
  }
  
  private void readLiteralHeaderWithIncrementalIndexingNewName()
    throws IOException
  {
    String str1 = readString();
    String str2 = readString();
    insertIntoHeaderTable(headerTable.size(), new Hpack.HeaderEntry(str1, str2));
  }
  
  private void readLiteralHeaderWithSubstitutionIndexingIndexedName(int paramInt)
    throws IOException
  {
    insertIntoHeaderTable(readInt(readByte(), 255), new Hpack.HeaderEntry(getName(paramInt), readString()));
  }
  
  private void readLiteralHeaderWithSubstitutionIndexingNewName()
    throws IOException
  {
    String str = readString();
    insertIntoHeaderTable(readInt(readByte(), 255), new Hpack.HeaderEntry(str, readString()));
  }
  
  private void readLiteralHeaderWithoutIndexingIndexedName(int paramInt)
    throws IOException
  {
    String str1 = getName(paramInt);
    String str2 = readString();
    emittedHeaders.add(str1);
    emittedHeaders.add(str2);
  }
  
  private void readLiteralHeaderWithoutIndexingNewName()
    throws IOException
  {
    String str1 = readString();
    String str2 = readString();
    emittedHeaders.add(str1);
    emittedHeaders.add(str2);
  }
  
  private void remove(int paramInt)
  {
    bufferSize -= ((Hpack.HeaderEntry)headerTable.remove(paramInt)).length();
  }
  
  public void emitReferenceSet()
  {
    for (int i = referenceSet.nextSetBit(0);; i = referenceSet.nextSetBit(i + 1))
    {
      if (i == -1) {
        return;
      }
      emittedHeaders.add(getName(i));
      emittedHeaders.add(getValue(i));
    }
  }
  
  public List<String> getAndReset()
  {
    ArrayList localArrayList = new ArrayList(emittedHeaders);
    emittedHeaders.clear();
    return localArrayList;
  }
  
  public void readHeaders(int paramInt)
    throws IOException
  {
    bytesLeft += paramInt;
    for (;;)
    {
      if (bytesLeft <= 0L) {
        return;
      }
      paramInt = readByte();
      if ((paramInt & 0x80) != 0)
      {
        readIndexedHeader(readInt(paramInt, 127));
      }
      else if (paramInt == 96)
      {
        readLiteralHeaderWithoutIndexingNewName();
      }
      else if ((paramInt & 0xE0) == 96)
      {
        readLiteralHeaderWithoutIndexingIndexedName(readInt(paramInt, 31) - 1);
      }
      else if (paramInt == 64)
      {
        readLiteralHeaderWithIncrementalIndexingNewName();
      }
      else if ((paramInt & 0xE0) == 64)
      {
        readLiteralHeaderWithIncrementalIndexingIndexedName(readInt(paramInt, 31) - 1);
      }
      else if (paramInt == 0)
      {
        readLiteralHeaderWithSubstitutionIndexingNewName();
      }
      else
      {
        if ((paramInt & 0xC0) != 0) {
          break;
        }
        readLiteralHeaderWithSubstitutionIndexingIndexedName(readInt(paramInt, 63) - 1);
      }
    }
    throw new AssertionError();
  }
  
  int readInt(int paramInt1, int paramInt2)
    throws IOException
  {
    paramInt1 &= paramInt2;
    if (paramInt1 < paramInt2) {
      return paramInt1;
    }
    paramInt1 = 0;
    int i;
    for (;;)
    {
      i = readByte();
      if ((i & 0x80) == 0) {
        break;
      }
      paramInt2 += ((i & 0x7F) << paramInt1);
      paramInt1 += 7;
    }
    return paramInt2 + (i << paramInt1);
  }
  
  public String readString()
    throws IOException
  {
    int i = readInt(readByte(), 255);
    byte[] arrayOfByte = new byte[i];
    bytesLeft -= i;
    in.readFully(arrayOfByte);
    return new String(arrayOfByte, "UTF-8");
  }
}

/* Location:
 * Qualified Name:     com.squareup.okhttp.internal.spdy.Hpack.Reader
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */