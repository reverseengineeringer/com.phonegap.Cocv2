package com.squareup.okhttp.internal.http;

class ResponseHeaders$1
  implements HeaderParser.CacheControlHandler
{
  ResponseHeaders$1(ResponseHeaders paramResponseHeaders) {}
  
  public void handle(String paramString1, String paramString2)
  {
    if ("no-cache".equalsIgnoreCase(paramString1)) {
      ResponseHeaders.access$0(this$0, true);
    }
    do
    {
      return;
      if ("no-store".equalsIgnoreCase(paramString1))
      {
        ResponseHeaders.access$1(this$0, true);
        return;
      }
      if ("max-age".equalsIgnoreCase(paramString1))
      {
        ResponseHeaders.access$2(this$0, HeaderParser.parseSeconds(paramString2));
        return;
      }
      if ("s-maxage".equalsIgnoreCase(paramString1))
      {
        ResponseHeaders.access$3(this$0, HeaderParser.parseSeconds(paramString2));
        return;
      }
      if ("public".equalsIgnoreCase(paramString1))
      {
        ResponseHeaders.access$4(this$0, true);
        return;
      }
    } while (!"must-revalidate".equalsIgnoreCase(paramString1));
    ResponseHeaders.access$5(this$0, true);
  }
}

/* Location:
 * Qualified Name:     com.squareup.okhttp.internal.http.ResponseHeaders.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */