package com.shcem.common;

import okhttp3.*;
import org.apache.commons.collections.MapUtils;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 使用OkHttp来对http进行基本的操作，包括get，post，uploadfile
 * Created by judysen on 2017/8/12.
 */
public class HttpUtlis {
    static OkHttpClient client = new OkHttpClient()
            .newBuilder().readTimeout(60, TimeUnit.SECONDS).build();

    public synchronized static HttpUtlis Instance(){
        return new HttpUtlis();
    }
    /**
     *
     * @param url
     * @return
     * @throws Exception
     */
    public String get(String url) throws Exception{
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }
    static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");
    static final MediaType FORM=MediaType.parse("");
    /**
     * 使用okHttp3 通过json方式提交数据
     * @param url 请求路径
     * @param json json字符串
     * @return String
     * @throws Exception
     */
    public String postByJson(String url,String json,Map<String,String> headers) throws Exception{
        RequestBody body = RequestBody.create(JSON, json);

        Headers.Builder builder=setHeader(headers);

        Request request = new Request.Builder()
                .url(url)
                .headers(builder.build())
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    /**
     * 填充Headers.Builder
     * @param headers
     * @return
     */
    private Headers.Builder setHeader(Map<String,String> headers){
        Headers.Builder builder=new Headers.Builder();

        if(MapUtils.isNotEmpty(headers)){
            for(Map.Entry<String,String> entry: headers.entrySet()){
                builder.add(entry.getKey(),entry.getValue());
            }
        }
        builder.add("user-agent","com.shcem.common");
        return builder;
    }
    /**
     *
     * @param url
     * @param map
     * @return String
     * @throws Exception
     */
    public String postByForm(String url, Map<String,String> map,Map<String,String> headers) throws Exception{
        if(map==null){
            throw new Exception("参数map不能为null");
        }
        Headers.Builder builder=setHeader(headers);

        FormBody.Builder formBodyBuilder=new FormBody.Builder();
        Iterator<Map.Entry<String, String>> entries = map.entrySet().iterator();
        while (entries.hasNext()){
            Map.Entry<String, String> entry = entries.next();
            formBodyBuilder.add(entry.getKey(),entry.getValue());
        }

        Request request=new Request.Builder()
                .url(url)
                .headers(builder.build())
                .post(formBodyBuilder.build())
                .build();

        Response response=client.newCall(request).execute();
        return response.body().string();
    }

    /**
     * post请求，指定encode编码
     * @param url
     * @param map
     * @param encode
     * @return
     * @throws Exception
     */
    public String postByForm(String url,Map<String,String> map,String encode,Map<String,String> headers) throws Exception{
        String postType="application/x-www-form-urlencoded;charset="+encode;

        MediaType FormPostType=MediaType.parse(postType);

        Headers.Builder builder=setHeader(headers);

        StringBuilder sb=new StringBuilder();
        for(String key :map.keySet()){
            sb.append(key+"="+map.get(key)+"&");
        }
        RequestBody body = RequestBody.create(FormPostType, sb.toString());

        Request request=new Request.Builder()
                .url(url)
                .headers(builder.build())
                .post(body)
                .build();
        Response response=client.newCall(request).execute();
        return response.body().string();
    }

    /**
     * 上传文件
     * @param url
     * @param fileName
     * @param content
     * @return
     * @throws Exception
     */
    public String uploadFile(String url,String fileName,byte[] content,Map<String,String> headers) throws Exception{
        RequestBody fileBody=RequestBody.create(getMediaType(fileName),content);
        RequestBody body=new MultipartBody.Builder().addFormDataPart("file",fileName,fileBody)
                .build();

        Headers.Builder builder=setHeader(headers);

        Request request=new Request.Builder()
                .url(url)
                .headers(builder.build())
                .post(body)
                .build();

        Response response=client.newCall(request).execute();
        return response.body().string();
    }

    /**
     * 获取常用的MediaType
     * @param fileName
     * @return
     */
    private MediaType getMediaType(String fileName){
        String houzui=fileName.substring(fileName.indexOf('.'),fileName.length()-1);
        MediaType type=null;
        switch (houzui){
            case ".pdf":
                MediaType.parse("application/pdf");
                break;
            case ".gif":
                MediaType.parse("image/gif");
                break;
            case ".jpg":
                MediaType.parse("image/jpeg");
                break;
            case ".jpeg":
                type=MediaType.parse("image/jpeg");
                break;
            case ".bmp":
                type=MediaType.parse("application/x-MS-bmp");
                break;
            case ".xls":
                type=MediaType.parse("application/msexcel");
                break;
            case ".xlsx":
                type=MediaType.parse("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
                break;
            case ".doc":
                type=MediaType.parse("application/msword");
                break;
            case ".docx":
                type=MediaType.parse("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
                break;
            case ".zip":
                type=MediaType.parse("application/zip");
                break;
            case ".ppt":
                type=MediaType.parse("application/vnd.ms-powerpoint");
                break;
            case ".pptx":
                type=MediaType.parse("application/vnd.openxmlformats-officedocument.presentationml.presentation");
                break;
            case ".png":
                type=MediaType.parse("image/png");
                break;
            case ".rar":
                type=MediaType.parse("application/octet-stream");
                break;
            default:
                type=MediaType.parse("text/plain");
                break;
        }
        return type;
    }
}
