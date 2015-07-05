package com.squareup.okhttp.internal;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import javax.net.ssl.SSLSocket;

public class Platform
{
  private static final Platform PLATFORM = ;
  private Constructor<DeflaterOutputStream> deflaterConstructor;
  
  /* Error */
  private static Platform findPlatform()
  {
    // Byte code:
    //   0: ldc 39
    //   2: invokestatic 45	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   5: astore_0
    //   6: aload_0
    //   7: ldc 47
    //   9: iconst_1
    //   10: anewarray 41	java/lang/Class
    //   13: dup
    //   14: iconst_0
    //   15: getstatic 53	java/lang/Boolean:TYPE	Ljava/lang/Class;
    //   18: aastore
    //   19: invokevirtual 57	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   22: astore_1
    //   23: aload_0
    //   24: ldc 59
    //   26: iconst_1
    //   27: anewarray 41	java/lang/Class
    //   30: dup
    //   31: iconst_0
    //   32: ldc 61
    //   34: aastore
    //   35: invokevirtual 57	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   38: astore_2
    //   39: new 9	com/squareup/okhttp/internal/Platform$Android41
    //   42: dup
    //   43: aload_0
    //   44: aload_1
    //   45: aload_2
    //   46: aload_0
    //   47: ldc 63
    //   49: iconst_1
    //   50: anewarray 41	java/lang/Class
    //   53: dup
    //   54: iconst_0
    //   55: ldc 65
    //   57: aastore
    //   58: invokevirtual 57	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   61: aload_0
    //   62: ldc 67
    //   64: iconst_0
    //   65: anewarray 41	java/lang/Class
    //   68: invokevirtual 57	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   71: aconst_null
    //   72: invokespecial 70	com/squareup/okhttp/internal/Platform$Android41:<init>	(Ljava/lang/Class;Ljava/lang/reflect/Method;Ljava/lang/reflect/Method;Ljava/lang/reflect/Method;Ljava/lang/reflect/Method;Lcom/squareup/okhttp/internal/Platform$Android41;)V
    //   75: astore_3
    //   76: aload_3
    //   77: areturn
    //   78: astore_0
    //   79: ldc 72
    //   81: invokestatic 45	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   84: astore_0
    //   85: goto -79 -> 6
    //   88: astore_3
    //   89: new 6	com/squareup/okhttp/internal/Platform$Android23
    //   92: dup
    //   93: aload_0
    //   94: aload_1
    //   95: aload_2
    //   96: aconst_null
    //   97: aconst_null
    //   98: invokespecial 75	com/squareup/okhttp/internal/Platform$Android23:<init>	(Ljava/lang/Class;Ljava/lang/reflect/Method;Ljava/lang/reflect/Method;Lcom/squareup/okhttp/internal/Platform$Android23;Lcom/squareup/okhttp/internal/Platform$Android23;)V
    //   101: astore_0
    //   102: aload_0
    //   103: areturn
    //   104: astore_0
    //   105: ldc 77
    //   107: invokestatic 45	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   110: astore_0
    //   111: new 79	java/lang/StringBuilder
    //   114: dup
    //   115: ldc 77
    //   117: invokestatic 83	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   120: invokespecial 86	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   123: ldc 88
    //   125: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   128: invokevirtual 96	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   131: invokestatic 45	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   134: astore_1
    //   135: new 79	java/lang/StringBuilder
    //   138: dup
    //   139: ldc 77
    //   141: invokestatic 83	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   144: invokespecial 86	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   147: ldc 98
    //   149: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   152: invokevirtual 96	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   155: invokestatic 45	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   158: astore_2
    //   159: new 79	java/lang/StringBuilder
    //   162: dup
    //   163: ldc 77
    //   165: invokestatic 83	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   168: invokespecial 86	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   171: ldc 100
    //   173: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   176: invokevirtual 96	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   179: invokestatic 45	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   182: astore_3
    //   183: new 12	com/squareup/okhttp/internal/Platform$JdkWithJettyNpnPlatform
    //   186: dup
    //   187: aload_0
    //   188: ldc 102
    //   190: iconst_2
    //   191: anewarray 41	java/lang/Class
    //   194: dup
    //   195: iconst_0
    //   196: ldc 104
    //   198: aastore
    //   199: dup
    //   200: iconst_1
    //   201: aload_1
    //   202: aastore
    //   203: invokevirtual 57	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   206: aload_0
    //   207: ldc 106
    //   209: iconst_1
    //   210: anewarray 41	java/lang/Class
    //   213: dup
    //   214: iconst_0
    //   215: ldc 104
    //   217: aastore
    //   218: invokevirtual 57	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   221: aload_2
    //   222: aload_3
    //   223: invokespecial 109	com/squareup/okhttp/internal/Platform$JdkWithJettyNpnPlatform:<init>	(Ljava/lang/reflect/Method;Ljava/lang/reflect/Method;Ljava/lang/Class;Ljava/lang/Class;)V
    //   226: astore_0
    //   227: aload_0
    //   228: areturn
    //   229: astore_0
    //   230: new 2	com/squareup/okhttp/internal/Platform
    //   233: dup
    //   234: invokespecial 110	com/squareup/okhttp/internal/Platform:<init>	()V
    //   237: areturn
    //   238: astore_0
    //   239: goto -9 -> 230
    //   242: astore_0
    //   243: goto -138 -> 105
    // Local variable table:
    //   start	length	slot	name	signature
    //   5	57	0	localClass1	Class
    //   78	1	0	localClassNotFoundException1	ClassNotFoundException
    //   84	19	0	localObject1	Object
    //   104	1	0	localNoSuchMethodException1	NoSuchMethodException
    //   110	118	0	localObject2	Object
    //   229	1	0	localClassNotFoundException2	ClassNotFoundException
    //   238	1	0	localNoSuchMethodException2	NoSuchMethodException
    //   242	1	0	localClassNotFoundException3	ClassNotFoundException
    //   22	180	1	localObject3	Object
    //   38	184	2	localObject4	Object
    //   75	2	3	localAndroid41	Android41
    //   88	1	3	localNoSuchMethodException3	NoSuchMethodException
    //   182	41	3	localClass2	Class
    // Exception table:
    //   from	to	target	type
    //   0	6	78	java/lang/ClassNotFoundException
    //   39	76	88	java/lang/NoSuchMethodException
    //   0	6	104	java/lang/NoSuchMethodException
    //   6	39	104	java/lang/NoSuchMethodException
    //   79	85	104	java/lang/NoSuchMethodException
    //   89	102	104	java/lang/NoSuchMethodException
    //   105	227	229	java/lang/ClassNotFoundException
    //   105	227	238	java/lang/NoSuchMethodException
    //   6	39	242	java/lang/ClassNotFoundException
    //   39	76	242	java/lang/ClassNotFoundException
    //   79	85	242	java/lang/ClassNotFoundException
    //   89	102	242	java/lang/ClassNotFoundException
  }
  
  public static Platform get()
  {
    return PLATFORM;
  }
  
  public void connectSocket(Socket paramSocket, InetSocketAddress paramInetSocketAddress, int paramInt)
    throws IOException
  {
    paramSocket.connect(paramInetSocketAddress, paramInt);
  }
  
  public void enableTlsExtensions(SSLSocket paramSSLSocket, String paramString) {}
  
  public byte[] getNpnSelectedProtocol(SSLSocket paramSSLSocket)
  {
    return null;
  }
  
  public String getPrefix()
  {
    return "OkHttp";
  }
  
  public void logW(String paramString)
  {
    System.out.println(paramString);
  }
  
  public OutputStream newDeflaterOutputStream(OutputStream paramOutputStream, Deflater paramDeflater, boolean paramBoolean)
  {
    try
    {
      Constructor localConstructor2 = deflaterConstructor;
      Constructor localConstructor1 = localConstructor2;
      if (localConstructor2 == null)
      {
        localConstructor1 = DeflaterOutputStream.class.getConstructor(new Class[] { OutputStream.class, Deflater.class, Boolean.TYPE });
        deflaterConstructor = localConstructor1;
      }
      paramOutputStream = (OutputStream)localConstructor1.newInstance(new Object[] { paramOutputStream, paramDeflater, Boolean.valueOf(paramBoolean) });
      return paramOutputStream;
    }
    catch (NoSuchMethodException paramOutputStream)
    {
      throw new UnsupportedOperationException("Cannot SPDY; no SYNC_FLUSH available");
    }
    catch (InvocationTargetException paramOutputStream)
    {
      if ((paramOutputStream.getCause() instanceof RuntimeException)) {}
      for (paramOutputStream = (RuntimeException)paramOutputStream.getCause();; paramOutputStream = new RuntimeException(paramOutputStream.getCause())) {
        throw paramOutputStream;
      }
    }
    catch (InstantiationException paramOutputStream)
    {
      throw new RuntimeException(paramOutputStream);
    }
    catch (IllegalAccessException paramOutputStream)
    {
      throw new AssertionError();
    }
  }
  
  public void setNpnProtocols(SSLSocket paramSSLSocket, byte[] paramArrayOfByte) {}
  
  public void supportTlsIntolerantServer(SSLSocket paramSSLSocket)
  {
    paramSSLSocket.setEnabledProtocols(new String[] { "SSLv3" });
  }
  
  public void tagSocket(Socket paramSocket)
    throws SocketException
  {}
  
  public URI toUriLenient(URL paramURL)
    throws URISyntaxException
  {
    return paramURL.toURI();
  }
  
  public void untagSocket(Socket paramSocket)
    throws SocketException
  {}
  
  private static class Android23
    extends Platform
  {
    protected final Class<?> openSslSocketClass;
    private final Method setHostname;
    private final Method setUseSessionTickets;
    
    private Android23(Class<?> paramClass, Method paramMethod1, Method paramMethod2)
    {
      openSslSocketClass = paramClass;
      setUseSessionTickets = paramMethod1;
      setHostname = paramMethod2;
    }
    
    public void connectSocket(Socket paramSocket, InetSocketAddress paramInetSocketAddress, int paramInt)
      throws IOException
    {
      try
      {
        paramSocket.connect(paramInetSocketAddress, paramInt);
        return;
      }
      catch (SecurityException paramSocket)
      {
        paramInetSocketAddress = new IOException("Exception in connect");
        paramInetSocketAddress.initCause(paramSocket);
        throw paramInetSocketAddress;
      }
    }
    
    public void enableTlsExtensions(SSLSocket paramSSLSocket, String paramString)
    {
      super.enableTlsExtensions(paramSSLSocket, paramString);
      if (openSslSocketClass.isInstance(paramSSLSocket)) {}
      try
      {
        setUseSessionTickets.invoke(paramSSLSocket, new Object[] { Boolean.valueOf(true) });
        setHostname.invoke(paramSSLSocket, new Object[] { paramString });
        return;
      }
      catch (InvocationTargetException paramSSLSocket)
      {
        throw new RuntimeException(paramSSLSocket);
      }
      catch (IllegalAccessException paramSSLSocket)
      {
        throw new AssertionError(paramSSLSocket);
      }
    }
  }
  
  private static class Android41
    extends Platform.Android23
  {
    private final Method getNpnSelectedProtocol;
    private final Method setNpnProtocols;
    
    private Android41(Class<?> paramClass, Method paramMethod1, Method paramMethod2, Method paramMethod3, Method paramMethod4)
    {
      super(paramMethod1, paramMethod2, null);
      setNpnProtocols = paramMethod3;
      getNpnSelectedProtocol = paramMethod4;
    }
    
    public byte[] getNpnSelectedProtocol(SSLSocket paramSSLSocket)
    {
      if (!openSslSocketClass.isInstance(paramSSLSocket)) {
        return null;
      }
      try
      {
        paramSSLSocket = (byte[])getNpnSelectedProtocol.invoke(paramSSLSocket, new Object[0]);
        return paramSSLSocket;
      }
      catch (InvocationTargetException paramSSLSocket)
      {
        throw new RuntimeException(paramSSLSocket);
      }
      catch (IllegalAccessException paramSSLSocket)
      {
        throw new AssertionError(paramSSLSocket);
      }
    }
    
    public void setNpnProtocols(SSLSocket paramSSLSocket, byte[] paramArrayOfByte)
    {
      if (!openSslSocketClass.isInstance(paramSSLSocket)) {
        return;
      }
      try
      {
        setNpnProtocols.invoke(paramSSLSocket, new Object[] { paramArrayOfByte });
        return;
      }
      catch (IllegalAccessException paramSSLSocket)
      {
        throw new AssertionError(paramSSLSocket);
      }
      catch (InvocationTargetException paramSSLSocket)
      {
        throw new RuntimeException(paramSSLSocket);
      }
    }
  }
  
  private static class JdkWithJettyNpnPlatform
    extends Platform
  {
    private final Class<?> clientProviderClass;
    private final Method getMethod;
    private final Method putMethod;
    private final Class<?> serverProviderClass;
    
    public JdkWithJettyNpnPlatform(Method paramMethod1, Method paramMethod2, Class<?> paramClass1, Class<?> paramClass2)
    {
      putMethod = paramMethod1;
      getMethod = paramMethod2;
      clientProviderClass = paramClass1;
      serverProviderClass = paramClass2;
    }
    
    public byte[] getNpnSelectedProtocol(SSLSocket paramSSLSocket)
    {
      try
      {
        paramSSLSocket = (Platform.JettyNpnProvider)Proxy.getInvocationHandler(getMethod.invoke(null, new Object[] { paramSSLSocket }));
        if ((!Platform.JettyNpnProvider.access$0(paramSSLSocket)) && (Platform.JettyNpnProvider.access$1(paramSSLSocket) == null))
        {
          Logger.getLogger("com.squareup.okhttp.OkHttpClient").log(Level.INFO, "NPN callback dropped so SPDY is disabled. Is npn-boot on the boot class path?");
          return null;
        }
        if (!Platform.JettyNpnProvider.access$0(paramSSLSocket))
        {
          paramSSLSocket = Platform.JettyNpnProvider.access$1(paramSSLSocket).getBytes("US-ASCII");
          return paramSSLSocket;
        }
      }
      catch (UnsupportedEncodingException paramSSLSocket)
      {
        throw new AssertionError();
      }
      catch (InvocationTargetException paramSSLSocket)
      {
        throw new AssertionError();
      }
      catch (IllegalAccessException paramSSLSocket)
      {
        throw new AssertionError();
      }
      return null;
    }
    
    /* Error */
    public void setNpnProtocols(SSLSocket paramSSLSocket, byte[] paramArrayOfByte)
    {
      // Byte code:
      //   0: new 95	java/util/ArrayList
      //   3: dup
      //   4: invokespecial 96	java/util/ArrayList:<init>	()V
      //   7: astore 5
      //   9: iconst_0
      //   10: istore_3
      //   11: iload_3
      //   12: aload_2
      //   13: arraylength
      //   14: if_icmplt +75 -> 89
      //   17: ldc 4
      //   19: invokevirtual 102	java/lang/Class:getClassLoader	()Ljava/lang/ClassLoader;
      //   22: astore_2
      //   23: aload_0
      //   24: getfield 23	com/squareup/okhttp/internal/Platform$JdkWithJettyNpnPlatform:clientProviderClass	Ljava/lang/Class;
      //   27: astore 6
      //   29: aload_0
      //   30: getfield 25	com/squareup/okhttp/internal/Platform$JdkWithJettyNpnPlatform:serverProviderClass	Ljava/lang/Class;
      //   33: astore 7
      //   35: new 52	com/squareup/okhttp/internal/Platform$JettyNpnProvider
      //   38: dup
      //   39: aload 5
      //   41: invokespecial 105	com/squareup/okhttp/internal/Platform$JettyNpnProvider:<init>	(Ljava/util/List;)V
      //   44: astore 5
      //   46: aload_2
      //   47: iconst_2
      //   48: anewarray 98	java/lang/Class
      //   51: dup
      //   52: iconst_0
      //   53: aload 6
      //   55: aastore
      //   56: dup
      //   57: iconst_1
      //   58: aload 7
      //   60: aastore
      //   61: aload 5
      //   63: invokestatic 109	java/lang/reflect/Proxy:newProxyInstance	(Ljava/lang/ClassLoader;[Ljava/lang/Class;Ljava/lang/reflect/InvocationHandler;)Ljava/lang/Object;
      //   66: astore_2
      //   67: aload_0
      //   68: getfield 19	com/squareup/okhttp/internal/Platform$JdkWithJettyNpnPlatform:putMethod	Ljava/lang/reflect/Method;
      //   71: aconst_null
      //   72: iconst_2
      //   73: anewarray 38	java/lang/Object
      //   76: dup
      //   77: iconst_0
      //   78: aload_1
      //   79: aastore
      //   80: dup
      //   81: iconst_1
      //   82: aload_2
      //   83: aastore
      //   84: invokevirtual 44	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
      //   87: pop
      //   88: return
      //   89: iload_3
      //   90: iconst_1
      //   91: iadd
      //   92: istore 4
      //   94: aload_2
      //   95: iload_3
      //   96: baload
      //   97: istore_3
      //   98: aload 5
      //   100: new 84	java/lang/String
      //   103: dup
      //   104: aload_2
      //   105: iload 4
      //   107: iload_3
      //   108: ldc 82
      //   110: invokespecial 112	java/lang/String:<init>	([BIILjava/lang/String;)V
      //   113: invokeinterface 118 2 0
      //   118: pop
      //   119: iload 4
      //   121: iload_3
      //   122: iadd
      //   123: istore_3
      //   124: goto -113 -> 11
      //   127: astore_1
      //   128: new 90	java/lang/AssertionError
      //   131: dup
      //   132: aload_1
      //   133: invokespecial 121	java/lang/AssertionError:<init>	(Ljava/lang/Object;)V
      //   136: athrow
      //   137: astore_1
      //   138: new 90	java/lang/AssertionError
      //   141: dup
      //   142: aload_1
      //   143: invokespecial 121	java/lang/AssertionError:<init>	(Ljava/lang/Object;)V
      //   146: athrow
      //   147: astore_1
      //   148: new 90	java/lang/AssertionError
      //   151: dup
      //   152: aload_1
      //   153: invokespecial 121	java/lang/AssertionError:<init>	(Ljava/lang/Object;)V
      //   156: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	157	0	this	JdkWithJettyNpnPlatform
      //   0	157	1	paramSSLSocket	SSLSocket
      //   0	157	2	paramArrayOfByte	byte[]
      //   10	114	3	i	int
      //   92	31	4	j	int
      //   7	92	5	localObject	Object
      //   27	27	6	localClass1	Class
      //   33	26	7	localClass2	Class
      // Exception table:
      //   from	to	target	type
      //   0	9	127	java/io/UnsupportedEncodingException
      //   11	88	127	java/io/UnsupportedEncodingException
      //   98	119	127	java/io/UnsupportedEncodingException
      //   0	9	137	java/lang/reflect/InvocationTargetException
      //   11	88	137	java/lang/reflect/InvocationTargetException
      //   98	119	137	java/lang/reflect/InvocationTargetException
      //   0	9	147	java/lang/IllegalAccessException
      //   11	88	147	java/lang/IllegalAccessException
      //   98	119	147	java/lang/IllegalAccessException
    }
  }
  
  private static class JettyNpnProvider
    implements InvocationHandler
  {
    private final List<String> protocols;
    private String selected;
    private boolean unsupported;
    
    public JettyNpnProvider(List<String> paramList)
    {
      protocols = paramList;
    }
    
    public Object invoke(Object paramObject, Method paramMethod, Object[] paramArrayOfObject)
      throws Throwable
    {
      String str = paramMethod.getName();
      Class localClass = paramMethod.getReturnType();
      paramObject = paramArrayOfObject;
      if (paramArrayOfObject == null) {
        paramObject = Util.EMPTY_STRING_ARRAY;
      }
      if ((str.equals("supports")) && (Boolean.TYPE == localClass)) {
        return Boolean.valueOf(true);
      }
      if ((str.equals("unsupported")) && (Void.TYPE == localClass))
      {
        unsupported = true;
        return null;
      }
      if ((str.equals("protocols")) && (paramObject.length == 0)) {
        return protocols;
      }
      if ((str.equals("selectProtocol")) && (String.class == localClass) && (paramObject.length == 1) && ((paramObject[0] == null) || ((paramObject[0] instanceof List))))
      {
        paramObject = (List)paramObject[0];
        selected = ((String)protocols.get(0));
        return selected;
      }
      if ((str.equals("protocolSelected")) && (paramObject.length == 1))
      {
        selected = ((String)paramObject[0]);
        return null;
      }
      return paramMethod.invoke(this, (Object[])paramObject);
    }
  }
}

/* Location:
 * Qualified Name:     com.squareup.okhttp.internal.Platform
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */