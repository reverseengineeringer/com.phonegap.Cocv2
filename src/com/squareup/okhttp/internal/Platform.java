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
import java.util.ArrayList;
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
  
  private static Platform findPlatform()
  {
    try
    {
      localClass1 = Class.forName("com.android.org.conscrypt.OpenSSLSocketImpl");
    }
    catch (ClassNotFoundException localClassNotFoundException1)
    {
      try
      {
        Object localObject3;
        Object localObject4;
        for (;;)
        {
          Class localClass1;
          localObject3 = localClass1.getMethod("setUseSessionTickets", new Class[] { Boolean.TYPE });
          localObject4 = localClass1.getMethod("setHostname", new Class[] { String.class });
          try
          {
            Android41 localAndroid41 = new Android41(localClass1, (Method)localObject3, (Method)localObject4, localClass1.getMethod("setNpnProtocols", new Class[] { byte[].class }), localClass1.getMethod("getNpnSelectedProtocol", new Class[0]), null);
            return localAndroid41;
          }
          catch (NoSuchMethodException localNoSuchMethodException3)
          {
            Object localObject1 = new Android23((Class)localObject1, (Method)localObject3, (Method)localObject4, null);
            return (Platform)localObject1;
          }
          localClassNotFoundException1 = localClassNotFoundException1;
          localObject1 = Class.forName("org.apache.harmony.xnet.provider.jsse.OpenSSLSocketImpl");
        }
        try
        {
          Object localObject2 = Class.forName("org.eclipse.jetty.npn.NextProtoNego");
          localObject3 = Class.forName("org.eclipse.jetty.npn.NextProtoNego" + "$Provider");
          localObject4 = Class.forName("org.eclipse.jetty.npn.NextProtoNego" + "$ClientProvider");
          Class localClass2 = Class.forName("org.eclipse.jetty.npn.NextProtoNego" + "$ServerProvider");
          localObject2 = new JdkWithJettyNpnPlatform(((Class)localObject2).getMethod("put", new Class[] { SSLSocket.class, localObject3 }), ((Class)localObject2).getMethod("get", new Class[] { SSLSocket.class }), (Class)localObject4, localClass2);
          return (Platform)localObject2;
        }
        catch (ClassNotFoundException localClassNotFoundException3)
        {
          return new Platform();
        }
        catch (NoSuchMethodException localNoSuchMethodException1)
        {
          for (;;) {}
        }
      }
      catch (ClassNotFoundException localClassNotFoundException2) {}
    }
    catch (NoSuchMethodException localNoSuchMethodException2)
    {
      for (;;) {}
    }
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
        if ((!Platform.JettyNpnProvider.access$200(paramSSLSocket)) && (Platform.JettyNpnProvider.access$300(paramSSLSocket) == null))
        {
          Logger.getLogger("com.squareup.okhttp.OkHttpClient").log(Level.INFO, "NPN callback dropped so SPDY is disabled. Is npn-boot on the boot class path?");
          return null;
        }
        if (!Platform.JettyNpnProvider.access$200(paramSSLSocket))
        {
          paramSSLSocket = Platform.JettyNpnProvider.access$300(paramSSLSocket).getBytes("US-ASCII");
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
    
    public void setNpnProtocols(SSLSocket paramSSLSocket, byte[] paramArrayOfByte)
    {
      try
      {
        Object localObject = new ArrayList();
        int j;
        for (int i = 0; i < paramArrayOfByte.length; i = j + i)
        {
          j = i + 1;
          i = paramArrayOfByte[i];
          ((List)localObject).add(new String(paramArrayOfByte, j, i, "US-ASCII"));
        }
        paramArrayOfByte = Platform.class.getClassLoader();
        Class localClass1 = clientProviderClass;
        Class localClass2 = serverProviderClass;
        localObject = new Platform.JettyNpnProvider((List)localObject);
        paramArrayOfByte = Proxy.newProxyInstance(paramArrayOfByte, new Class[] { localClass1, localClass2 }, (InvocationHandler)localObject);
        putMethod.invoke(null, new Object[] { paramSSLSocket, paramArrayOfByte });
        return;
      }
      catch (UnsupportedEncodingException paramSSLSocket)
      {
        throw new AssertionError(paramSSLSocket);
      }
      catch (InvocationTargetException paramSSLSocket)
      {
        throw new AssertionError(paramSSLSocket);
      }
      catch (IllegalAccessException paramSSLSocket)
      {
        throw new AssertionError(paramSSLSocket);
      }
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