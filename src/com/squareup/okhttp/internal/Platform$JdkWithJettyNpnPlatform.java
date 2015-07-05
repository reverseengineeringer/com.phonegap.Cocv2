package com.squareup.okhttp.internal;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLSocket;

class Platform$JdkWithJettyNpnPlatform
  extends Platform
{
  private final Class<?> clientProviderClass;
  private final Method getMethod;
  private final Method putMethod;
  private final Class<?> serverProviderClass;
  
  public Platform$JdkWithJettyNpnPlatform(Method paramMethod1, Method paramMethod2, Class<?> paramClass1, Class<?> paramClass2)
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

/* Location:
 * Qualified Name:     com.squareup.okhttp.internal.Platform.JdkWithJettyNpnPlatform
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */