package com.squareup.okhttp;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.net.ssl.SSLHandshakeException;

public final class RouteDatabase
{
  private final Set<Route> failedRoutes = new LinkedHashSet();
  
  public void connected(Route paramRoute)
  {
    try
    {
      failedRoutes.remove(paramRoute);
      return;
    }
    finally
    {
      paramRoute = finally;
      throw paramRoute;
    }
  }
  
  public void failed(Route paramRoute, IOException paramIOException)
  {
    try
    {
      failedRoutes.add(paramRoute);
      if (!(paramIOException instanceof SSLHandshakeException)) {
        failedRoutes.add(paramRoute.flipTlsMode());
      }
      return;
    }
    finally
    {
      paramRoute = finally;
      throw paramRoute;
    }
  }
  
  public int failedRoutesCount()
  {
    try
    {
      int i = failedRoutes.size();
      return i;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public boolean shouldPostpone(Route paramRoute)
  {
    try
    {
      boolean bool = failedRoutes.contains(paramRoute);
      return bool;
    }
    finally
    {
      paramRoute = finally;
      throw paramRoute;
    }
  }
}

/* Location:
 * Qualified Name:     com.squareup.okhttp.RouteDatabase
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */