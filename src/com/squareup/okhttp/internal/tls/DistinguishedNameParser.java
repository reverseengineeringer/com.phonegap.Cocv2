package com.squareup.okhttp.internal.tls;

import javax.security.auth.x500.X500Principal;

final class DistinguishedNameParser
{
  private int beg;
  private char[] chars;
  private int cur;
  private final String dn;
  private int end;
  private final int length;
  private int pos;
  
  public DistinguishedNameParser(X500Principal paramX500Principal)
  {
    dn = paramX500Principal.getName("RFC2253");
    length = dn.length();
  }
  
  private String escapedAV()
  {
    beg = pos;
    end = pos;
    for (;;)
    {
      if (pos >= length) {
        return new String(chars, beg, end - beg);
      }
      switch (chars[pos])
      {
      default: 
        arrayOfChar = chars;
        i = end;
        end = (i + 1);
        arrayOfChar[i] = chars[pos];
        pos += 1;
        break;
      case '+': 
      case ',': 
      case ';': 
        return new String(chars, beg, end - beg);
      case '\\': 
        arrayOfChar = chars;
        i = end;
        end = (i + 1);
        arrayOfChar[i] = getEscaped();
        pos += 1;
      }
    }
    cur = end;
    pos += 1;
    char[] arrayOfChar = chars;
    int i = end;
    end = (i + 1);
    arrayOfChar[i] = ' ';
    for (;;)
    {
      if ((pos >= length) || (chars[pos] != ' '))
      {
        if ((pos != length) && (chars[pos] != ',') && (chars[pos] != '+') && (chars[pos] != ';')) {
          break;
        }
        return new String(chars, beg, cur - beg);
      }
      arrayOfChar = chars;
      i = end;
      end = (i + 1);
      arrayOfChar[i] = ' ';
      pos += 1;
    }
  }
  
  private int getByte(int paramInt)
  {
    if (paramInt + 1 >= length) {
      throw new IllegalStateException("Malformed DN: " + dn);
    }
    int i = chars[paramInt];
    if ((i >= 48) && (i <= 57))
    {
      i -= 48;
      paramInt = chars[(paramInt + 1)];
      if ((paramInt < 48) || (paramInt > 57)) {
        break label160;
      }
      paramInt -= 48;
    }
    for (;;)
    {
      return (i << 4) + paramInt;
      if ((i >= 97) && (i <= 102))
      {
        i -= 87;
        break;
      }
      if ((i >= 65) && (i <= 70))
      {
        i -= 55;
        break;
      }
      throw new IllegalStateException("Malformed DN: " + dn);
      label160:
      if ((paramInt >= 97) && (paramInt <= 102))
      {
        paramInt -= 87;
      }
      else
      {
        if ((paramInt < 65) || (paramInt > 70)) {
          break label200;
        }
        paramInt -= 55;
      }
    }
    label200:
    throw new IllegalStateException("Malformed DN: " + dn);
  }
  
  private char getEscaped()
  {
    pos += 1;
    if (pos == length) {
      throw new IllegalStateException("Unexpected end of DN: " + dn);
    }
    switch (chars[pos])
    {
    default: 
      return getUTF8();
    }
    return chars[pos];
  }
  
  private char getUTF8()
  {
    char c2 = '?';
    int i = getByte(pos);
    pos += 1;
    char c1;
    if (i < 128) {
      c1 = (char)i;
    }
    do
    {
      do
      {
        return c1;
        c1 = c2;
      } while (i < 192);
      c1 = c2;
    } while (i > 247);
    int j;
    label67:
    int m;
    int k;
    if (i <= 223)
    {
      j = 1;
      i &= 0x1F;
      m = 0;
      k = i;
      i = m;
    }
    for (;;)
    {
      if (i >= j)
      {
        return (char)k;
        if (i <= 239)
        {
          j = 2;
          i &= 0xF;
          break label67;
        }
        j = 3;
        i &= 0x7;
        break label67;
      }
      pos += 1;
      c1 = c2;
      if (pos == length) {
        break;
      }
      c1 = c2;
      if (chars[pos] != '\\') {
        break;
      }
      pos += 1;
      m = getByte(pos);
      pos += 1;
      c1 = c2;
      if ((m & 0xC0) != 128) {
        break;
      }
      k = (k << 6) + (m & 0x3F);
      i += 1;
    }
  }
  
  private String hexAV()
  {
    if (pos + 4 >= length) {
      throw new IllegalStateException("Unexpected end of DN: " + dn);
    }
    beg = pos;
    int k;
    for (pos += 1;; pos += 1)
    {
      if ((pos == length) || (chars[pos] == '+') || (chars[pos] == ',') || (chars[pos] == ';')) {
        end = pos;
      }
      for (;;)
      {
        k = end - beg;
        if ((k >= 5) && ((k & 0x1) != 0)) {
          break label301;
        }
        throw new IllegalStateException("Unexpected end of DN: " + dn);
        if (chars[pos] != ' ') {
          break;
        }
        end = pos;
        for (pos += 1; (pos < length) && (chars[pos] == ' '); pos += 1) {}
      }
      if ((chars[pos] >= 'A') && (chars[pos] <= 'F'))
      {
        localObject = chars;
        i = pos;
        localObject[i] = ((char)(localObject[i] + ' '));
      }
    }
    label301:
    Object localObject = new byte[k / 2];
    int i = 0;
    int j = beg + 1;
    for (;;)
    {
      if (i >= localObject.length) {
        return new String(chars, beg, k);
      }
      localObject[i] = ((byte)getByte(j));
      j += 2;
      i += 1;
    }
  }
  
  private String nextAT()
  {
    for (;;)
    {
      if ((pos >= length) || (chars[pos] != ' '))
      {
        if (pos != length) {
          break;
        }
        return null;
      }
      pos += 1;
    }
    beg = pos;
    for (pos += 1;; pos += 1) {
      if ((pos >= length) || (chars[pos] == '=') || (chars[pos] == ' '))
      {
        if (pos < length) {
          break;
        }
        throw new IllegalStateException("Unexpected end of DN: " + dn);
      }
    }
    end = pos;
    if (chars[pos] == ' ') {
      for (;;)
      {
        if ((pos >= length) || (chars[pos] == '=') || (chars[pos] != ' '))
        {
          if ((chars[pos] == '=') && (pos != length)) {
            break;
          }
          throw new IllegalStateException("Unexpected end of DN: " + dn);
        }
        pos += 1;
      }
    }
    for (pos += 1;; pos += 1) {
      if ((pos >= length) || (chars[pos] != ' '))
      {
        if ((end - beg > 4) && (chars[(beg + 3)] == '.') && ((chars[beg] == 'O') || (chars[beg] == 'o')) && ((chars[(beg + 1)] == 'I') || (chars[(beg + 1)] == 'i')) && ((chars[(beg + 2)] == 'D') || (chars[(beg + 2)] == 'd'))) {
          beg += 4;
        }
        return new String(chars, beg, end - beg);
      }
    }
  }
  
  private String quotedAV()
  {
    pos += 1;
    beg = pos;
    end = beg;
    if (pos == length) {
      throw new IllegalStateException("Unexpected end of DN: " + dn);
    }
    if (chars[pos] == '"') {}
    for (pos += 1;; pos += 1) {
      if ((pos >= length) || (chars[pos] != ' '))
      {
        return new String(chars, beg, end - beg);
        if (chars[pos] == '\\') {
          chars[end] = getEscaped();
        }
        for (;;)
        {
          pos += 1;
          end += 1;
          break;
          chars[end] = chars[pos];
        }
      }
    }
  }
  
  public String findMostSpecific(String paramString)
  {
    pos = 0;
    beg = 0;
    end = 0;
    cur = 0;
    chars = dn.toCharArray();
    String str1 = nextAT();
    String str2 = str1;
    if (str1 == null)
    {
      str1 = null;
      return str1;
    }
    label162:
    do
    {
      str1 = "";
      if (pos == length) {
        return null;
      }
      switch (chars[pos])
      {
      default: 
        str1 = escapedAV();
      }
      while (!paramString.equalsIgnoreCase(str2))
      {
        if (pos < length) {
          break label162;
        }
        return null;
        str1 = quotedAV();
        continue;
        str1 = hexAV();
      }
      if ((chars[pos] != ',') && (chars[pos] != ';') && (chars[pos] != '+')) {
        throw new IllegalStateException("Malformed DN: " + dn);
      }
      pos += 1;
      str1 = nextAT();
      str2 = str1;
    } while (str1 != null);
    throw new IllegalStateException("Malformed DN: " + dn);
  }
}

/* Location:
 * Qualified Name:     com.squareup.okhttp.internal.tls.DistinguishedNameParser
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */