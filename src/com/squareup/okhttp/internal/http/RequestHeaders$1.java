package com.squareup.okhttp.internal.http;

class RequestHeaders$1
  implements HeaderParser.CacheControlHandler
{
  RequestHeaders$1(RequestHeaders paramRequestHeaders) {}
  
  public void handle(String paramString1, String paramString2)
  {
    if ("no-cache".equalsIgnoreCase(paramString1)) {
      RequestHeaders.access$0(this$0, true);
    }
    do
    {
      return;
      if ("max-age".equalsIgnoreCase(paramString1))
      {
        RequestHeaders.access$1(this$0, HeaderParser.parseSeconds(paramString2));
        return;
      }
      if ("max-stale".equalsIgnoreCase(paramString1))
      {
        RequestHeaders.access$2(this$0, HeaderParser.parseSeconds(paramString2));
        return;
      }
      if ("min-fresh".equalsIgnoreCase(paramString1))
      {
        RequestHeaders.access$3(this$0, HeaderParser.parseSeconds(paramString2));
        return;
      }
    } while (!"only-if-cached".equalsIgnoreCase(paramString1));
    RequestHeaders.access$4(this$0, true);
  }
}

/* Location:
 * Qualified Name:     com.squareup.okhttp.internal.http.RequestHeaders.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */