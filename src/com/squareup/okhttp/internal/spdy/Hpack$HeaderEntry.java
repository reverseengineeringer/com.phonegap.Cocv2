package com.squareup.okhttp.internal.spdy;

class Hpack$HeaderEntry
{
  private final String name;
  private final String value;
  
  Hpack$HeaderEntry(String paramString1, String paramString2)
  {
    name = paramString1;
    value = paramString2;
  }
  
  int length()
  {
    return name.length() + 32 + value.length();
  }
}

/* Location:
 * Qualified Name:     com.squareup.okhttp.internal.spdy.Hpack.HeaderEntry
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */