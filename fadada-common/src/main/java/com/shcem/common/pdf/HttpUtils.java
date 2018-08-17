package com.shcem.common.pdf;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.util.concurrent.TimeUnit;

public class HttpUtils {
    /**
     *
     * @param url
     * @return
     */
    private static OkHttpClient client=new OkHttpClient().newBuilder()
            .readTimeout(60, TimeUnit.SECONDS)
            .build();

    /**
     * get url
     * @param url
     * @return
     * @throws Exception
     */
    public static String get(String url) throws Exception{
        Request build = new Request.Builder()
                .url(url)
                .build();
        Response response= client.newCall(build).execute();
        return response.body().string();
    }
}
