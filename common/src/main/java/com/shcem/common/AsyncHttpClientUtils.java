package com.shcem.common;

import io.netty.handler.codec.http.HttpHeaders;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.BoundRequestBuilder;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClientConfig;

import java.util.Map;

public class AsyncHttpClientUtils {
    private static DefaultAsyncHttpClientConfig.Builder builder=new DefaultAsyncHttpClientConfig.Builder()
            .setConnectionPoolCleanerPeriod(100).setConnectionTtl(200);
    private static AsyncHttpClient asyncHttpClient=new DefaultAsyncHttpClient(builder.build());
    private AsyncHttpClientUtils(){

    }
    public static String postJson(String url, Object val, Map<String,String> headerMap){
        BoundRequestBuilder requestBuilder=asyncHttpClient.preparePatch(url);
        //requestBuilder.setHeader()
        return asyncHttpClient.preparePost(url).setBody().execute().get().getResponseBody("utf-8");
    }
}
