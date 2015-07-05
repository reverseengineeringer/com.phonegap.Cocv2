package com.squareup.okhttp;

import java.io.IOException;

public abstract interface Response$Receiver
{
  public abstract void onFailure(Failure paramFailure);
  
  public abstract boolean onResponse(Response paramResponse)
    throws IOException;
}

/* Location:
 * Qualified Name:     com.squareup.okhttp.Response.Receiver
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */