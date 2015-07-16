package com.squareup.okhttp.internal.spdy;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;

final class Hpack
{
  static final List<HeaderEntry> INITIAL_CLIENT_TO_SERVER_HEADER_TABLE = Arrays.asList(new HeaderEntry[] { new HeaderEntry(":scheme", "http"), new HeaderEntry(":scheme", "https"), new HeaderEntry(":host", ""), new HeaderEntry(":path", "/"), new HeaderEntry(":method", "GET"), new HeaderEntry("accept", ""), new HeaderEntry("accept-charset", ""), new HeaderEntry("accept-encoding", ""), new HeaderEntry("accept-language", ""), new HeaderEntry("cookie", ""), new HeaderEntry("if-modified-since", ""), new HeaderEntry("user-agent", ""), new HeaderEntry("referer", ""), new HeaderEntry("authorization", ""), new HeaderEntry("allow", ""), new HeaderEntry("cache-control", ""), new HeaderEntry("connection", ""), new HeaderEntry("content-length", ""), new HeaderEntry("content-type", ""), new HeaderEntry("date", ""), new HeaderEntry("expect", ""), new HeaderEntry("from", ""), new HeaderEntry("if-match", ""), new HeaderEntry("if-none-match", ""), new HeaderEntry("if-range", ""), new HeaderEntry("if-unmodified-since", ""), new HeaderEntry("max-forwards", ""), new HeaderEntry("proxy-authorization", ""), new HeaderEntry("range", ""), new HeaderEntry("via", "") });
  static final int INITIAL_CLIENT_TO_SERVER_HEADER_TABLE_LENGTH = 1262;
  static final List<HeaderEntry> INITIAL_SERVER_TO_CLIENT_HEADER_TABLE = Arrays.asList(new HeaderEntry[] { new HeaderEntry(":status", "200"), new HeaderEntry("age", ""), new HeaderEntry("cache-control", ""), new HeaderEntry("content-length", ""), new HeaderEntry("content-type", ""), new HeaderEntry("date", ""), new HeaderEntry("etag", ""), new HeaderEntry("expires", ""), new HeaderEntry("last-modified", ""), new HeaderEntry("server", ""), new HeaderEntry("set-cookie", ""), new HeaderEntry("vary", ""), new HeaderEntry("via", ""), new HeaderEntry("access-control-allow-origin", ""), new HeaderEntry("accept-ranges", ""), new HeaderEntry("allow", ""), new HeaderEntry("connection", ""), new HeaderEntry("content-disposition", ""), new HeaderEntry("content-encoding", ""), new HeaderEntry("content-language", ""), new HeaderEntry("content-location", ""), new HeaderEntry("content-range", ""), new HeaderEntry("link", ""), new HeaderEntry("location", ""), new HeaderEntry("proxy-authenticate", ""), new HeaderEntry("refresh", ""), new HeaderEntry("retry-after", ""), new HeaderEntry("strict-transport-security", ""), new HeaderEntry("transfer-encoding", ""), new HeaderEntry("www-authenticate", "") });
  static final int INITIAL_SERVER_TO_CLIENT_HEADER_TABLE_LENGTH = 1304;
  static final int PREFIX_5_BITS = 31;
  static final int PREFIX_6_BITS = 63;
  static final int PREFIX_7_BITS = 127;
  static final int PREFIX_8_BITS = 255;
  
  static class HeaderEntry
  {
    private final String name;
    private final String value;
    
    HeaderEntry(String paramString1, String paramString2)
    {
      name = paramString1;
      value = paramString2;
    }
    
    int length()
    {
      return name.length() + 32 + value.length();
    }
  }
  
  static class Reader
  {
    private long bufferSize = 0L;
    private long bytesLeft = 0L;
    private final List<String> emittedHeaders = new ArrayList();
    private final List<Hpack.HeaderEntry> headerTable;
    private final DataInputStream in;
    private final long maxBufferSize = 4096L;
    private final BitSet referenceSet = new BitSet();
    
    Reader(DataInputStream paramDataInputStream, boolean paramBoolean)
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
      return headerTable.get(paramInt)).name;
    }
    
    private String getValue(int paramInt)
    {
      return headerTable.get(paramInt)).value;
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
        emittedHeaders.add(name);
        emittedHeaders.add(value);
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
      for (int i = referenceSet.nextSetBit(0); i != -1; i = referenceSet.nextSetBit(i + 1))
      {
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
      while (bytesLeft > 0L)
      {
        paramInt = readByte();
        if ((paramInt & 0x80) != 0) {
          readIndexedHeader(readInt(paramInt, 127));
        } else if (paramInt == 96) {
          readLiteralHeaderWithoutIndexingNewName();
        } else if ((paramInt & 0xE0) == 96) {
          readLiteralHeaderWithoutIndexingIndexedName(readInt(paramInt, 31) - 1);
        } else if (paramInt == 64) {
          readLiteralHeaderWithIncrementalIndexingNewName();
        } else if ((paramInt & 0xE0) == 64) {
          readLiteralHeaderWithIncrementalIndexingIndexedName(readInt(paramInt, 31) - 1);
        } else if (paramInt == 0) {
          readLiteralHeaderWithSubstitutionIndexingNewName();
        } else if ((paramInt & 0xC0) == 0) {
          readLiteralHeaderWithSubstitutionIndexingIndexedName(readInt(paramInt, 63) - 1);
        } else {
          throw new AssertionError();
        }
      }
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
  
  static class Writer
  {
    private final OutputStream out;
    
    Writer(OutputStream paramOutputStream)
    {
      out = paramOutputStream;
    }
    
    public void writeHeaders(List<String> paramList)
      throws IOException
    {
      int i = 0;
      int j = paramList.size();
      while (i < j)
      {
        out.write(96);
        writeString((String)paramList.get(i));
        writeString((String)paramList.get(i + 1));
        i += 2;
      }
    }
    
    public void writeInt(int paramInt1, int paramInt2, int paramInt3)
      throws IOException
    {
      if (paramInt1 < paramInt2)
      {
        out.write(paramInt3 | paramInt1);
        return;
      }
      out.write(paramInt3 | paramInt2);
      paramInt1 -= paramInt2;
      while (paramInt1 >= 128)
      {
        out.write(paramInt1 & 0x7F | 0x80);
        paramInt1 >>>= 7;
      }
      out.write(paramInt1);
    }
    
    public void writeString(String paramString)
      throws IOException
    {
      paramString = paramString.getBytes("UTF-8");
      writeInt(paramString.length, 255, 0);
      out.write(paramString);
    }
  }
}

/* Location:
 * Qualified Name:     com.squareup.okhttp.internal.spdy.Hpack
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */