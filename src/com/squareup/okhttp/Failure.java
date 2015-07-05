package com.squareup.okhttp;

class Failure
{
  private final Throwable exception;
  private final Request request;
  
  private Failure(Builder paramBuilder)
  {
    request = request;
    exception = exception;
  }
  
  public Throwable exception()
  {
    return exception;
  }
  
  public Request request()
  {
    return request;
  }
  
  public static class Builder
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
}

/* Location:
 * Qualified Name:     com.squareup.okhttp.Failure
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */