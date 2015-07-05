package com.squareup.okhttp;

public class Failure$Builder
{
  private Throwable exception;
  private Request request;
  
  public Failure build()
  {
    return new Failure(this, null);
  }
  
  public Builder exception(Throwable paramThrowable)
  {
    exception = paramThrowable;
    return this;
  }
  
  public Builder request(Request paramRequest)
  {
    request = paramRequest;
    return this;
  }
}

/* Location:
 * Qualified Name:     com.squareup.okhttp.Failure.Builder
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */