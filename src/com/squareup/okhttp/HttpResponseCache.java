package com.squareup.okhttp;

import com.squareup.okhttp.internal.DiskLruCache;
import com.squareup.okhttp.internal.DiskLruCache.Editor;
import com.squareup.okhttp.internal.DiskLruCache.Snapshot;
import com.squareup.okhttp.internal.StrictLineReader;
import com.squareup.okhttp.internal.Util;
import com.squareup.okhttp.internal.http.HttpEngine;
import com.squareup.okhttp.internal.http.HttpURLConnectionImpl;
import com.squareup.okhttp.internal.http.HttpsEngine;
import com.squareup.okhttp.internal.http.HttpsURLConnectionImpl;
import com.squareup.okhttp.internal.http.RawHeaders;
import com.squareup.okhttp.internal.http.RequestHeaders;
import com.squareup.okhttp.internal.http.ResponseHeaders;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FilterInputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.CacheRequest;
import java.net.CacheResponse;
import java.net.HttpURLConnection;
import java.net.ResponseCache;
import java.net.SecureCacheResponse;
import java.net.URI;
import java.net.URLConnection;
import java.security.Principal;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;

public final class HttpResponseCache
  extends ResponseCache
{
  private static final int ENTRY_BODY = 1;
  private static final int ENTRY_COUNT = 2;
  private static final int ENTRY_METADATA = 0;
  private static final int VERSION = 201105;
  private final DiskLruCache cache;
  private int hitCount;
  private int networkCount;
  final OkResponseCache okResponseCache = new OkResponseCache()
  {
    public CacheResponse get(URI paramAnonymousURI, String paramAnonymousString, Map<String, List<String>> paramAnonymousMap)
      throws IOException
    {
      return HttpResponseCache.this.get(paramAnonymousURI, paramAnonymousString, paramAnonymousMap);
    }
    
    public void maybeRemove(String paramAnonymousString, URI paramAnonymousURI)
      throws IOException
    {
      HttpResponseCache.this.maybeRemove(paramAnonymousString, paramAnonymousURI);
    }
    
    public CacheRequest put(URI paramAnonymousURI, URLConnection paramAnonymousURLConnection)
      throws IOException
    {
      return HttpResponseCache.this.put(paramAnonymousURI, paramAnonymousURLConnection);
    }
    
    public void trackConditionalCacheHit()
    {
      HttpResponseCache.this.trackConditionalCacheHit();
    }
    
    public void trackResponse(ResponseSource paramAnonymousResponseSource)
    {
      HttpResponseCache.this.trackResponse(paramAnonymousResponseSource);
    }
    
    public void update(CacheResponse paramAnonymousCacheResponse, HttpURLConnection paramAnonymousHttpURLConnection)
      throws IOException
    {
      HttpResponseCache.this.update(paramAnonymousCacheResponse, paramAnonymousHttpURLConnection);
    }
  };
  private int requestCount;
  private int writeAbortCount;
  private int writeSuccessCount;
  
  public HttpResponseCache(File paramFile, long paramLong)
    throws IOException
  {
    cache = DiskLruCache.open(paramFile, 201105, 2, paramLong);
  }
  
  private void abortQuietly(DiskLruCache.Editor paramEditor)
  {
    if (paramEditor != null) {}
    try
    {
      paramEditor.abort();
      return;
    }
    catch (IOException paramEditor) {}
  }
  
  private HttpEngine getHttpEngine(URLConnection paramURLConnection)
  {
    if ((paramURLConnection instanceof HttpURLConnectionImpl)) {
      return ((HttpURLConnectionImpl)paramURLConnection).getHttpEngine();
    }
    if ((paramURLConnection instanceof HttpsURLConnectionImpl)) {
      return ((HttpsURLConnectionImpl)paramURLConnection).getHttpEngine();
    }
    return null;
  }
  
  private boolean maybeRemove(String paramString, URI paramURI)
  {
    if ((paramString.equals("POST")) || (paramString.equals("PUT")) || (paramString.equals("DELETE"))) {}
    try
    {
      cache.remove(uriToKey(paramURI));
      return true;
      return false;
    }
    catch (IOException paramString)
    {
      for (;;) {}
    }
  }
  
  private static InputStream newBodyInputStream(final DiskLruCache.Snapshot paramSnapshot)
  {
    new FilterInputStream(paramSnapshot.getInputStream(1))
    {
      public void close()
        throws IOException
      {
        paramSnapshot.close();
        super.close();
      }
    };
  }
  
  private void trackConditionalCacheHit()
  {
    try
    {
      hitCount += 1;
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  private void trackResponse(ResponseSource paramResponseSource)
  {
    for (;;)
    {
      try
      {
        requestCount += 1;
        int i = 3.$SwitchMap$com$squareup$okhttp$ResponseSource[paramResponseSource.ordinal()];
        switch (i)
        {
        default: 
          return;
        }
      }
      finally {}
      hitCount += 1;
      continue;
      networkCount += 1;
    }
  }
  
  private void update(CacheResponse paramCacheResponse, HttpURLConnection paramHttpURLConnection)
    throws IOException
  {
    Object localObject = getHttpEngine(paramHttpURLConnection);
    URI localURI = ((HttpEngine)localObject).getUri();
    ResponseHeaders localResponseHeaders = ((HttpEngine)localObject).getResponseHeaders();
    localObject = new Entry(localURI, ((HttpEngine)localObject).getRequestHeaders().getHeaders().getAll(localResponseHeaders.getVaryFields()), paramHttpURLConnection);
    if ((paramCacheResponse instanceof EntryCacheResponse)) {}
    for (paramHttpURLConnection = snapshot;; paramHttpURLConnection = snapshot)
    {
      paramCacheResponse = null;
      try
      {
        paramHttpURLConnection = paramHttpURLConnection.edit();
        if (paramHttpURLConnection != null)
        {
          paramCacheResponse = paramHttpURLConnection;
          ((Entry)localObject).writeTo(paramHttpURLConnection);
          paramCacheResponse = paramHttpURLConnection;
          paramHttpURLConnection.commit();
        }
        return;
      }
      catch (IOException paramHttpURLConnection)
      {
        abortQuietly(paramCacheResponse);
      }
    }
  }
  
  private String uriToKey(URI paramURI)
  {
    return Util.hash(paramURI.toString());
  }
  
  public void close()
    throws IOException
  {
    cache.close();
  }
  
  public void delete()
    throws IOException
  {
    cache.delete();
  }
  
  public void flush()
    throws IOException
  {
    cache.flush();
  }
  
  public CacheResponse get(URI paramURI, String paramString, Map<String, List<String>> paramMap)
  {
    Object localObject = uriToKey(paramURI);
    Entry localEntry;
    try
    {
      localObject = cache.get((String)localObject);
      if (localObject == null) {
        return null;
      }
      localEntry = new Entry(((DiskLruCache.Snapshot)localObject).getInputStream(0));
      if (!localEntry.matches(paramURI, paramString, paramMap))
      {
        ((DiskLruCache.Snapshot)localObject).close();
        return null;
      }
    }
    catch (IOException paramURI)
    {
      return null;
    }
    if (localEntry.isHttps()) {
      return new EntrySecureCacheResponse(localEntry, (DiskLruCache.Snapshot)localObject);
    }
    return new EntryCacheResponse(localEntry, (DiskLruCache.Snapshot)localObject);
  }
  
  public File getDirectory()
  {
    return cache.getDirectory();
  }
  
  public int getHitCount()
  {
    try
    {
      int i = hitCount;
      return i;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public long getMaxSize()
  {
    return cache.getMaxSize();
  }
  
  public int getNetworkCount()
  {
    try
    {
      int i = networkCount;
      return i;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public int getRequestCount()
  {
    try
    {
      int i = requestCount;
      return i;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public long getSize()
  {
    return cache.size();
  }
  
  public int getWriteAbortCount()
  {
    try
    {
      int i = writeAbortCount;
      return i;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public int getWriteSuccessCount()
  {
    try
    {
      int i = writeSuccessCount;
      return i;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public boolean isClosed()
  {
    return cache.isClosed();
  }
  
  public CacheRequest put(URI paramURI, URLConnection paramURLConnection)
    throws IOException
  {
    if (!(paramURLConnection instanceof HttpURLConnection)) {}
    for (;;)
    {
      return null;
      paramURLConnection = (HttpURLConnection)paramURLConnection;
      Object localObject = paramURLConnection.getRequestMethod();
      if ((!maybeRemove((String)localObject, paramURI)) && (((String)localObject).equals("GET")))
      {
        localObject = getHttpEngine(paramURLConnection);
        if (localObject != null)
        {
          ResponseHeaders localResponseHeaders = ((HttpEngine)localObject).getResponseHeaders();
          if (!localResponseHeaders.hasVaryAll())
          {
            localObject = new Entry(paramURI, ((HttpEngine)localObject).getRequestHeaders().getHeaders().getAll(localResponseHeaders.getVaryFields()), paramURLConnection);
            paramURLConnection = null;
            try
            {
              paramURI = cache.edit(uriToKey(paramURI));
              if (paramURI != null)
              {
                paramURLConnection = paramURI;
                ((Entry)localObject).writeTo(paramURI);
                paramURLConnection = paramURI;
                paramURI = new CacheRequestImpl(paramURI);
                return paramURI;
              }
            }
            catch (IOException paramURI)
            {
              abortQuietly(paramURLConnection);
            }
          }
        }
      }
    }
    return null;
  }
  
  private final class CacheRequestImpl
    extends CacheRequest
  {
    private OutputStream body;
    private OutputStream cacheOut;
    private boolean done;
    private final DiskLruCache.Editor editor;
    
    public CacheRequestImpl(final DiskLruCache.Editor paramEditor)
      throws IOException
    {
      editor = paramEditor;
      cacheOut = paramEditor.newOutputStream(1);
      body = new FilterOutputStream(cacheOut)
      {
        public void close()
          throws IOException
        {
          synchronized (HttpResponseCache.this)
          {
            if (done) {
              return;
            }
            HttpResponseCache.CacheRequestImpl.access$702(HttpResponseCache.CacheRequestImpl.this, true);
            HttpResponseCache.access$808(HttpResponseCache.this);
            super.close();
            paramEditor.commit();
            return;
          }
        }
        
        public void write(byte[] paramAnonymousArrayOfByte, int paramAnonymousInt1, int paramAnonymousInt2)
          throws IOException
        {
          out.write(paramAnonymousArrayOfByte, paramAnonymousInt1, paramAnonymousInt2);
        }
      };
    }
    
    public void abort()
    {
      synchronized (HttpResponseCache.this)
      {
        if (done) {
          return;
        }
        done = true;
        HttpResponseCache.access$908(HttpResponseCache.this);
        Util.closeQuietly(cacheOut);
        try
        {
          editor.abort();
          return;
        }
        catch (IOException localIOException) {}
      }
    }
    
    public OutputStream getBody()
      throws IOException
    {
      return body;
    }
  }
  
  private static final class Entry
  {
    private final String cipherSuite;
    private final Certificate[] localCertificates;
    private final Certificate[] peerCertificates;
    private final String requestMethod;
    private final RawHeaders responseHeaders;
    private final String uri;
    private final RawHeaders varyHeaders;
    
    public Entry(InputStream paramInputStream)
      throws IOException
    {
      try
      {
        StrictLineReader localStrictLineReader1 = new StrictLineReader(paramInputStream, Util.US_ASCII);
        uri = localStrictLineReader1.readLine();
        requestMethod = localStrictLineReader1.readLine();
        varyHeaders = new RawHeaders();
        int j = localStrictLineReader1.readInt();
        int i = 0;
        while (i < j)
        {
          varyHeaders.addLine(localStrictLineReader1.readLine());
          i += 1;
        }
        responseHeaders = new RawHeaders();
        responseHeaders.setStatusLine(localStrictLineReader1.readLine());
        j = localStrictLineReader1.readInt();
        i = 0;
        while (i < j)
        {
          responseHeaders.addLine(localStrictLineReader1.readLine());
          i += 1;
        }
        if (!isHttps()) {
          break label231;
        }
        String str = localStrictLineReader1.readLine();
        if (str.length() > 0) {
          throw new IOException("expected \"\" but was \"" + str + "\"");
        }
      }
      finally
      {
        paramInputStream.close();
      }
      cipherSuite = localStrictLineReader2.readLine();
      peerCertificates = readCertArray(localStrictLineReader2);
      for (localCertificates = readCertArray(localStrictLineReader2);; localCertificates = null)
      {
        paramInputStream.close();
        return;
        label231:
        cipherSuite = null;
        peerCertificates = null;
      }
    }
    
    public Entry(URI paramURI, RawHeaders paramRawHeaders, HttpURLConnection paramHttpURLConnection)
      throws IOException
    {
      uri = paramURI.toString();
      varyHeaders = paramRawHeaders;
      requestMethod = paramHttpURLConnection.getRequestMethod();
      responseHeaders = RawHeaders.fromMultimap(paramHttpURLConnection.getHeaderFields(), true);
      paramHttpURLConnection = getSslSocket(paramHttpURLConnection);
      if (paramHttpURLConnection != null)
      {
        cipherSuite = paramHttpURLConnection.getSession().getCipherSuite();
        paramURI = null;
      }
      try
      {
        paramRawHeaders = paramHttpURLConnection.getSession().getPeerCertificates();
        paramURI = paramRawHeaders;
      }
      catch (SSLPeerUnverifiedException paramRawHeaders)
      {
        for (;;) {}
      }
      peerCertificates = paramURI;
      localCertificates = paramHttpURLConnection.getSession().getLocalCertificates();
      return;
      cipherSuite = null;
      peerCertificates = null;
      localCertificates = null;
    }
    
    private SSLSocket getSslSocket(HttpURLConnection paramHttpURLConnection)
    {
      if ((paramHttpURLConnection instanceof HttpsURLConnectionImpl)) {}
      for (paramHttpURLConnection = ((HttpsURLConnectionImpl)paramHttpURLConnection).getHttpEngine(); (paramHttpURLConnection instanceof HttpsEngine); paramHttpURLConnection = ((HttpURLConnectionImpl)paramHttpURLConnection).getHttpEngine()) {
        return ((HttpsEngine)paramHttpURLConnection).getSslSocket();
      }
      return null;
    }
    
    private boolean isHttps()
    {
      return uri.startsWith("https://");
    }
    
    /* Error */
    private Certificate[] readCertArray(StrictLineReader paramStrictLineReader)
      throws IOException
    {
      // Byte code:
      //   0: aload_1
      //   1: invokevirtual 52	com/squareup/okhttp/internal/StrictLineReader:readInt	()I
      //   4: istore_2
      //   5: iload_2
      //   6: iconst_m1
      //   7: if_icmpne +7 -> 14
      //   10: aconst_null
      //   11: astore_3
      //   12: aload_3
      //   13: areturn
      //   14: ldc -79
      //   16: invokestatic 183	java/security/cert/CertificateFactory:getInstance	(Ljava/lang/String;)Ljava/security/cert/CertificateFactory;
      //   19: astore 5
      //   21: iload_2
      //   22: anewarray 185	java/security/cert/Certificate
      //   25: astore 4
      //   27: iconst_0
      //   28: istore_2
      //   29: aload 4
      //   31: astore_3
      //   32: iload_2
      //   33: aload 4
      //   35: arraylength
      //   36: if_icmpge -24 -> 12
      //   39: aload 4
      //   41: iload_2
      //   42: aload 5
      //   44: new 187	java/io/ByteArrayInputStream
      //   47: dup
      //   48: aload_1
      //   49: invokevirtual 39	com/squareup/okhttp/internal/StrictLineReader:readLine	()Ljava/lang/String;
      //   52: ldc -67
      //   54: invokevirtual 193	java/lang/String:getBytes	(Ljava/lang/String;)[B
      //   57: invokestatic 199	com/squareup/okhttp/internal/Base64:decode	([B)[B
      //   60: invokespecial 202	java/io/ByteArrayInputStream:<init>	([B)V
      //   63: invokevirtual 206	java/security/cert/CertificateFactory:generateCertificate	(Ljava/io/InputStream;)Ljava/security/cert/Certificate;
      //   66: aastore
      //   67: iload_2
      //   68: iconst_1
      //   69: iadd
      //   70: istore_2
      //   71: goto -42 -> 29
      //   74: astore_1
      //   75: new 21	java/io/IOException
      //   78: dup
      //   79: aload_1
      //   80: invokevirtual 209	java/security/cert/CertificateException:getMessage	()Ljava/lang/String;
      //   83: invokespecial 86	java/io/IOException:<init>	(Ljava/lang/String;)V
      //   86: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	87	0	this	Entry
      //   0	87	1	paramStrictLineReader	StrictLineReader
      //   4	67	2	i	int
      //   11	21	3	localObject	Object
      //   25	15	4	arrayOfCertificate	Certificate[]
      //   19	24	5	localCertificateFactory	java.security.cert.CertificateFactory
      // Exception table:
      //   from	to	target	type
      //   14	27	74	java/security/cert/CertificateException
      //   32	67	74	java/security/cert/CertificateException
    }
    
    /* Error */
    private void writeCertArray(Writer paramWriter, Certificate[] paramArrayOfCertificate)
      throws IOException
    {
      // Byte code:
      //   0: aload_2
      //   1: ifnonnull +10 -> 11
      //   4: aload_1
      //   5: ldc -41
      //   7: invokevirtual 220	java/io/Writer:write	(Ljava/lang/String;)V
      //   10: return
      //   11: aload_1
      //   12: new 72	java/lang/StringBuilder
      //   15: dup
      //   16: invokespecial 73	java/lang/StringBuilder:<init>	()V
      //   19: aload_2
      //   20: arraylength
      //   21: invokestatic 225	java/lang/Integer:toString	(I)Ljava/lang/String;
      //   24: invokevirtual 79	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   27: bipush 10
      //   29: invokevirtual 228	java/lang/StringBuilder:append	(C)Ljava/lang/StringBuilder;
      //   32: invokevirtual 84	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   35: invokevirtual 220	java/io/Writer:write	(Ljava/lang/String;)V
      //   38: aload_2
      //   39: arraylength
      //   40: istore 4
      //   42: iconst_0
      //   43: istore_3
      //   44: iload_3
      //   45: iload 4
      //   47: if_icmpge -37 -> 10
      //   50: aload_2
      //   51: iload_3
      //   52: aaload
      //   53: invokevirtual 232	java/security/cert/Certificate:getEncoded	()[B
      //   56: invokestatic 236	com/squareup/okhttp/internal/Base64:encode	([B)Ljava/lang/String;
      //   59: astore 5
      //   61: aload_1
      //   62: new 72	java/lang/StringBuilder
      //   65: dup
      //   66: invokespecial 73	java/lang/StringBuilder:<init>	()V
      //   69: aload 5
      //   71: invokevirtual 79	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   74: bipush 10
      //   76: invokevirtual 228	java/lang/StringBuilder:append	(C)Ljava/lang/StringBuilder;
      //   79: invokevirtual 84	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   82: invokevirtual 220	java/io/Writer:write	(Ljava/lang/String;)V
      //   85: iload_3
      //   86: iconst_1
      //   87: iadd
      //   88: istore_3
      //   89: goto -45 -> 44
      //   92: astore_1
      //   93: new 21	java/io/IOException
      //   96: dup
      //   97: aload_1
      //   98: invokevirtual 237	java/security/cert/CertificateEncodingException:getMessage	()Ljava/lang/String;
      //   101: invokespecial 86	java/io/IOException:<init>	(Ljava/lang/String;)V
      //   104: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	105	0	this	Entry
      //   0	105	1	paramWriter	Writer
      //   0	105	2	paramArrayOfCertificate	Certificate[]
      //   43	46	3	i	int
      //   40	8	4	j	int
      //   59	11	5	str	String
      // Exception table:
      //   from	to	target	type
      //   11	42	92	java/security/cert/CertificateEncodingException
      //   50	85	92	java/security/cert/CertificateEncodingException
    }
    
    public boolean matches(URI paramURI, String paramString, Map<String, List<String>> paramMap)
    {
      boolean bool2 = false;
      boolean bool1 = bool2;
      if (uri.equals(paramURI.toString()))
      {
        bool1 = bool2;
        if (requestMethod.equals(paramString))
        {
          bool1 = bool2;
          if (new ResponseHeaders(paramURI, responseHeaders).varyMatches(varyHeaders.toMultimap(false), paramMap)) {
            bool1 = true;
          }
        }
      }
      return bool1;
    }
    
    public void writeTo(DiskLruCache.Editor paramEditor)
      throws IOException
    {
      paramEditor = new BufferedWriter(new OutputStreamWriter(paramEditor.newOutputStream(0), Util.UTF_8));
      paramEditor.write(uri + '\n');
      paramEditor.write(requestMethod + '\n');
      paramEditor.write(Integer.toString(varyHeaders.length()) + '\n');
      int i = 0;
      while (i < varyHeaders.length())
      {
        paramEditor.write(varyHeaders.getFieldName(i) + ": " + varyHeaders.getValue(i) + '\n');
        i += 1;
      }
      paramEditor.write(responseHeaders.getStatusLine() + '\n');
      paramEditor.write(Integer.toString(responseHeaders.length()) + '\n');
      i = 0;
      while (i < responseHeaders.length())
      {
        paramEditor.write(responseHeaders.getFieldName(i) + ": " + responseHeaders.getValue(i) + '\n');
        i += 1;
      }
      if (isHttps())
      {
        paramEditor.write(10);
        paramEditor.write(cipherSuite + '\n');
        writeCertArray(paramEditor, peerCertificates);
        writeCertArray(paramEditor, localCertificates);
      }
      paramEditor.close();
    }
  }
  
  static class EntryCacheResponse
    extends CacheResponse
  {
    private final HttpResponseCache.Entry entry;
    private final InputStream in;
    private final DiskLruCache.Snapshot snapshot;
    
    public EntryCacheResponse(HttpResponseCache.Entry paramEntry, DiskLruCache.Snapshot paramSnapshot)
    {
      entry = paramEntry;
      snapshot = paramSnapshot;
      in = HttpResponseCache.newBodyInputStream(paramSnapshot);
    }
    
    public InputStream getBody()
    {
      return in;
    }
    
    public Map<String, List<String>> getHeaders()
    {
      return entry.responseHeaders.toMultimap(true);
    }
  }
  
  static class EntrySecureCacheResponse
    extends SecureCacheResponse
  {
    private final HttpResponseCache.Entry entry;
    private final InputStream in;
    private final DiskLruCache.Snapshot snapshot;
    
    public EntrySecureCacheResponse(HttpResponseCache.Entry paramEntry, DiskLruCache.Snapshot paramSnapshot)
    {
      entry = paramEntry;
      snapshot = paramSnapshot;
      in = HttpResponseCache.newBodyInputStream(paramSnapshot);
    }
    
    public InputStream getBody()
    {
      return in;
    }
    
    public String getCipherSuite()
    {
      return entry.cipherSuite;
    }
    
    public Map<String, List<String>> getHeaders()
    {
      return entry.responseHeaders.toMultimap(true);
    }
    
    public List<Certificate> getLocalCertificateChain()
    {
      if ((entry.localCertificates == null) || (entry.localCertificates.length == 0)) {
        return null;
      }
      return Arrays.asList((Object[])entry.localCertificates.clone());
    }
    
    public Principal getLocalPrincipal()
    {
      if ((entry.localCertificates == null) || (entry.localCertificates.length == 0)) {
        return null;
      }
      return ((X509Certificate)entry.localCertificates[0]).getSubjectX500Principal();
    }
    
    public Principal getPeerPrincipal()
      throws SSLPeerUnverifiedException
    {
      if ((entry.peerCertificates == null) || (entry.peerCertificates.length == 0)) {
        throw new SSLPeerUnverifiedException(null);
      }
      return ((X509Certificate)entry.peerCertificates[0]).getSubjectX500Principal();
    }
    
    public List<Certificate> getServerCertificateChain()
      throws SSLPeerUnverifiedException
    {
      if ((entry.peerCertificates == null) || (entry.peerCertificates.length == 0)) {
        throw new SSLPeerUnverifiedException(null);
      }
      return Arrays.asList((Object[])entry.peerCertificates.clone());
    }
  }
}

/* Location:
 * Qualified Name:     com.squareup.okhttp.HttpResponseCache
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */