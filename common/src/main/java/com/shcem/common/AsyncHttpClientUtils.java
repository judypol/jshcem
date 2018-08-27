package com.shcem.common;

import com.alibaba.fastjson.JSON;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.BoundRequestBuilder;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClientConfig;

import java.util.Map;

/**
 *
 */
public class AsyncHttpClientUtils {
    private static DefaultAsyncHttpClientConfig.Builder builder=new DefaultAsyncHttpClientConfig.Builder()
            .setConnectionPoolCleanerPeriod(100).setConnectionTtl(200);
    private static AsyncHttpClient asyncHttpClient=new DefaultAsyncHttpClient(builder.build());
    private AsyncHttpClientUtils(){
    }

    /**
     *
     * @param url
     * @param val
     * @param headerMap
     * @return
     * @throws Exception
     */
    public static String postJson(String url, String val, Map<String,String> headerMap) throws Exception{
        return post(url,val,headerMap,"application/json");
    }

    /**
     *
     * @param url
     * @param val
     * @param headerMap
     * @param contentType
     * @return
     * @throws Exception
     */
    private static String post(String url,String val,Map<String,String> headerMap,String contentType) throws Exception{
        BoundRequestBuilder requestBuilder=asyncHttpClient.preparePatch(url);
        if(headerMap!=null){
            for(Map.Entry<String,String> entry : headerMap.entrySet()){
                requestBuilder.setHeader(entry.getKey(),entry.getValue());
            }
        }
        requestBuilder.setHeader("Content-Type",contentType);

        return requestBuilder.setBody(val).execute().get().getResponseBody();
    }

    /**
     *
     * @param url
     * @param val
     * @return
     * @throws Exception
     */
    public static String postJson(String url,String val) throws Exception{
        return postJson(url,val,null);
    }

    /**
     *
     * @param url
     * @param val
     * @param headerMap
     * @return
     * @throws Exception
     */
    public static String postJson(String url,Object val,Map<String,String> headerMap) throws Exception{
        String jsonString=JSON.toJSONString(val);
        return postJson(url,jsonString,headerMap);
    }

    /**
     *
     * @param url
     * @param val
     * @param headerMap
     * @return
     * @throws Exception
     */
    public static String postForm(String url,String val,Map<String,String> headerMap) throws Exception{
        return post(url,val,headerMap,"application/x-www-form-urlencoded");
    }
}
