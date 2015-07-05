package com.squareup.okhttp.internal.http;

import com.squareup.okhttp.internal.Util;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public final class RawHeaders
{
  private static final Comparator<String> FIELD_NAME_COMPARATOR = new Comparator()
  {
    public int compare(String paramAnonymousString1, String paramAnonymousString2)
    {
      if (paramAnonymousString1 == paramAnonymousString2) {
        return 0;
      }
      if (paramAnonymousString1 == null) {
        return -1;
      }
      if (paramAnonymousString2 == null) {
        return 1;
      }
      return String.CASE_INSENSITIVE_ORDER.compare(paramAnonymousString1, paramAnonymousString2);
    }
  };
  private int httpMinorVersion = 1;
  private final List<String> namesAndValues = new ArrayList(20);
  private String requestLine;
  private int responseCode = -1;
  private String responseMessage;
  private String statusLine;
  
  public RawHeaders() {}
  
  public RawHeaders(RawHeaders paramRawHeaders)
  {
    namesAndValues.addAll(namesAndValues);
    requestLine = requestLine;
    statusLine = statusLine;
    httpMinorVersion = httpMinorVersion;
    responseCode = responseCode;
    responseMessage = responseMessage;
  }
  
  private void addLenient(String paramString1, String paramString2)
  {
    namesAndValues.add(paramString1);
    namesAndValues.add(paramString2.trim());
  }
  
  public static RawHeaders fromBytes(InputStream paramInputStream)
    throws IOException
  {
    RawHeaders localRawHeaders;
    do
    {
      localRawHeaders = new RawHeaders();
      localRawHeaders.setStatusLine(Util.readAsciiLine(paramInputStream));
      readHeaders(paramInputStream, localRawHeaders);
    } while (localRawHeaders.getResponseCode() == 100);
    return localRawHeaders;
  }
  
  public static RawHeaders fromMultimap(Map<String, List<String>> paramMap, boolean paramBoolean)
    throws IOException
  {
    if (!paramBoolean) {
      throw new UnsupportedOperationException();
    }
    RawHeaders localRawHeaders = new RawHeaders();
    paramMap = paramMap.entrySet().iterator();
    for (;;)
    {
      if (!paramMap.hasNext()) {
        return localRawHeaders;
      }
      Object localObject = (Map.Entry)paramMap.next();
      String str = (String)((Map.Entry)localObject).getKey();
      localObject = (List)((Map.Entry)localObject).getValue();
      if (str != null)
      {
        localObject = ((List)localObject).iterator();
        while (((Iterator)localObject).hasNext()) {
          localRawHeaders.addLenient(str, (String)((Iterator)localObject).next());
        }
      }
      else if (!((List)localObject).isEmpty())
      {
        localRawHeaders.setStatusLine((String)((List)localObject).get(((List)localObject).size() - 1));
      }
    }
  }
  
  public static RawHeaders fromNameValueBlock(List<String> paramList)
    throws IOException
  {
    if (paramList.size() % 2 != 0) {
      throw new IllegalArgumentException("Unexpected name value block: " + paramList);
    }
    Object localObject2 = null;
    Object localObject1 = null;
    RawHeaders localRawHeaders = new RawHeaders();
    int i = 0;
    String str2;
    String str3;
    int j;
    for (;;)
    {
      if (i >= paramList.size())
      {
        if (localObject2 != null) {
          break label221;
        }
        throw new ProtocolException("Expected ':status' header not present");
      }
      str2 = (String)paramList.get(i);
      str3 = (String)paramList.get(i + 1);
      j = 0;
      if (j < str3.length()) {
        break;
      }
      i += 2;
    }
    int m = str3.indexOf(0, j);
    int k = m;
    if (m == -1) {
      k = str3.length();
    }
    String str1 = str3.substring(j, k);
    if (":status".equals(str2)) {
      localObject2 = str1;
    }
    for (;;)
    {
      j = k + 1;
      break;
      if (":version".equals(str2))
      {
        localObject1 = str1;
      }
      else
      {
        namesAndValues.add(str2);
        namesAndValues.add(str1);
      }
    }
    label221:
    if (localObject1 == null) {
      throw new ProtocolException("Expected ':version' header not present");
    }
    localRawHeaders.setStatusLine(localObject1 + " " + (String)localObject2);
    return localRawHeaders;
  }
  
  public static void readHeaders(InputStream paramInputStream, RawHeaders paramRawHeaders)
    throws IOException
  {
    for (;;)
    {
      String str = Util.readAsciiLine(paramInputStream);
      if (str.length() == 0) {
        return;
      }
      paramRawHeaders.addLine(str);
    }
  }
  
  public void add(String paramString1, String paramString2)
  {
    if (paramString1 == null) {
      throw new IllegalArgumentException("fieldname == null");
    }
    if (paramString2 == null) {
      throw new IllegalArgumentException("value == null");
    }
    if ((paramString1.length() == 0) || (paramString1.indexOf(0) != -1) || (paramString2.indexOf(0) != -1)) {
      throw new IllegalArgumentException("Unexpected header: " + paramString1 + ": " + paramString2);
    }
    addLenient(paramString1, paramString2);
  }
  
  public void addAll(String paramString, List<String> paramList)
  {
    paramList = paramList.iterator();
    for (;;)
    {
      if (!paramList.hasNext()) {
        return;
      }
      add(paramString, (String)paramList.next());
    }
  }
  
  public void addLine(String paramString)
  {
    int i = paramString.indexOf(":", 1);
    if (i != -1)
    {
      addLenient(paramString.substring(0, i), paramString.substring(i + 1));
      return;
    }
    if (paramString.startsWith(":"))
    {
      addLenient("", paramString.substring(1));
      return;
    }
    addLenient("", paramString);
  }
  
  public void addSpdyRequestHeaders(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    add(":method", paramString1);
    add(":scheme", paramString5);
    add(":path", paramString2);
    add(":version", paramString3);
    add(":host", paramString4);
  }
  
  public String get(String paramString)
  {
    int i = namesAndValues.size() - 2;
    for (;;)
    {
      if (i < 0) {
        return null;
      }
      if (paramString.equalsIgnoreCase((String)namesAndValues.get(i))) {
        return (String)namesAndValues.get(i + 1);
      }
      i -= 2;
    }
  }
  
  public RawHeaders getAll(Set<String> paramSet)
  {
    RawHeaders localRawHeaders = new RawHeaders();
    int i = 0;
    for (;;)
    {
      if (i >= namesAndValues.size()) {
        return localRawHeaders;
      }
      String str = (String)namesAndValues.get(i);
      if (paramSet.contains(str)) {
        localRawHeaders.add(str, (String)namesAndValues.get(i + 1));
      }
      i += 2;
    }
  }
  
  public String getFieldName(int paramInt)
  {
    paramInt *= 2;
    if ((paramInt < 0) || (paramInt >= namesAndValues.size())) {
      return null;
    }
    return (String)namesAndValues.get(paramInt);
  }
  
  public int getHttpMinorVersion()
  {
    if (httpMinorVersion != -1) {
      return httpMinorVersion;
    }
    return 1;
  }
  
  public int getResponseCode()
  {
    return responseCode;
  }
  
  public String getResponseMessage()
  {
    return responseMessage;
  }
  
  public String getStatusLine()
  {
    return statusLine;
  }
  
  public String getValue(int paramInt)
  {
    paramInt = paramInt * 2 + 1;
    if ((paramInt < 0) || (paramInt >= namesAndValues.size())) {
      return null;
    }
    return (String)namesAndValues.get(paramInt);
  }
  
  public int length()
  {
    return namesAndValues.size() / 2;
  }
  
  public Set<String> names()
  {
    TreeSet localTreeSet = new TreeSet(String.CASE_INSENSITIVE_ORDER);
    int i = 0;
    for (;;)
    {
      if (i >= length()) {
        return Collections.unmodifiableSet(localTreeSet);
      }
      localTreeSet.add(getFieldName(i));
      i += 1;
    }
  }
  
  public void removeAll(String paramString)
  {
    int i = 0;
    for (;;)
    {
      if (i >= namesAndValues.size()) {
        return;
      }
      if (paramString.equalsIgnoreCase((String)namesAndValues.get(i)))
      {
        namesAndValues.remove(i);
        namesAndValues.remove(i);
      }
      i += 2;
    }
  }
  
  public void set(String paramString1, String paramString2)
  {
    removeAll(paramString1);
    add(paramString1, paramString2);
  }
  
  public void setRequestLine(String paramString)
  {
    requestLine = paramString.trim();
  }
  
  public void setStatusLine(String paramString)
    throws IOException
  {
    if (responseMessage != null) {
      throw new IllegalStateException("statusLine is already set");
    }
    if (paramString.length() > 13) {}
    for (int i = 1; (!paramString.startsWith("HTTP/1.")) || (paramString.length() < 12) || (paramString.charAt(8) != ' ') || ((i != 0) && (paramString.charAt(12) != ' ')); i = 0) {
      throw new ProtocolException("Unexpected status line: " + paramString);
    }
    int j = paramString.charAt(7) - '0';
    if ((j < 0) || (j > 9)) {
      throw new ProtocolException("Unexpected status line: " + paramString);
    }
    for (;;)
    {
      try
      {
        int k = Integer.parseInt(paramString.substring(9, 12));
        if (i != 0)
        {
          String str1 = paramString.substring(13);
          responseMessage = str1;
          responseCode = k;
          statusLine = paramString;
          httpMinorVersion = j;
          return;
        }
      }
      catch (NumberFormatException localNumberFormatException)
      {
        throw new ProtocolException("Unexpected status line: " + paramString);
      }
      String str2 = "";
    }
  }
  
  public byte[] toBytes()
    throws UnsupportedEncodingException
  {
    StringBuilder localStringBuilder = new StringBuilder(256);
    localStringBuilder.append(requestLine).append("\r\n");
    int i = 0;
    for (;;)
    {
      if (i >= namesAndValues.size())
      {
        localStringBuilder.append("\r\n");
        return localStringBuilder.toString().getBytes("ISO-8859-1");
      }
      localStringBuilder.append((String)namesAndValues.get(i)).append(": ").append((String)namesAndValues.get(i + 1)).append("\r\n");
      i += 2;
    }
  }
  
  public Map<String, List<String>> toMultimap(boolean paramBoolean)
  {
    TreeMap localTreeMap = new TreeMap(FIELD_NAME_COMPARATOR);
    int i = 0;
    if (i >= namesAndValues.size())
    {
      if ((!paramBoolean) || (statusLine == null)) {
        break label160;
      }
      localTreeMap.put(null, Collections.unmodifiableList(Collections.singletonList(statusLine)));
    }
    for (;;)
    {
      return Collections.unmodifiableMap(localTreeMap);
      String str1 = (String)namesAndValues.get(i);
      String str2 = (String)namesAndValues.get(i + 1);
      ArrayList localArrayList = new ArrayList();
      List localList = (List)localTreeMap.get(str1);
      if (localList != null) {
        localArrayList.addAll(localList);
      }
      localArrayList.add(str2);
      localTreeMap.put(str1, Collections.unmodifiableList(localArrayList));
      i += 2;
      break;
      label160:
      if (requestLine != null) {
        localTreeMap.put(null, Collections.unmodifiableList(Collections.singletonList(requestLine)));
      }
    }
  }
  
  public List<String> toNameValueBlock()
  {
    HashSet localHashSet = new HashSet();
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    if (i >= namesAndValues.size()) {
      return localArrayList;
    }
    String str1 = ((String)namesAndValues.get(i)).toLowerCase(Locale.US);
    String str2 = (String)namesAndValues.get(i + 1);
    if ((str1.equals("connection")) || (str1.equals("host")) || (str1.equals("keep-alive")) || (str1.equals("proxy-connection")) || (str1.equals("transfer-encoding"))) {}
    label254:
    for (;;)
    {
      i += 2;
      break;
      if (localHashSet.add(str1))
      {
        localArrayList.add(str1);
        localArrayList.add(str2);
      }
      else
      {
        int j = 0;
        for (;;)
        {
          if (j >= localArrayList.size()) {
            break label254;
          }
          if (str1.equals(localArrayList.get(j)))
          {
            localArrayList.set(j + 1, (String)localArrayList.get(j + 1) + "\000" + str2);
            break;
          }
          j += 2;
        }
      }
    }
  }
  
  public List<String> values(String paramString)
  {
    Object localObject1 = null;
    int i = 0;
    for (;;)
    {
      if (i >= length())
      {
        if (localObject1 == null) {
          break;
        }
        return Collections.unmodifiableList((List)localObject1);
      }
      Object localObject2 = localObject1;
      if (paramString.equalsIgnoreCase(getFieldName(i)))
      {
        localObject2 = localObject1;
        if (localObject1 == null) {
          localObject2 = new ArrayList(2);
        }
        ((List)localObject2).add(getValue(i));
      }
      i += 1;
      localObject1 = localObject2;
    }
    return Collections.emptyList();
  }
}

/* Location:
 * Qualified Name:     com.squareup.okhttp.internal.http.RawHeaders
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */