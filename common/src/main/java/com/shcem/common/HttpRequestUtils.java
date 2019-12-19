package com.shcem.common;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URLDecoder;

/**
 * Created by lizhihua on 2017/2/16.
 */
@Deprecated
public class HttpRequestUtils {
    private static Logger logger = LoggerFactory.getLogger(HttpRequestUtils.class);

    /**
     * httpPost
     * @param url  路径
     * @param param 参数
     * @return
     */
    public static String httpPost(String url,String param){
        return httpPost(url, param, false);
    }

    /**
     * post请求
     * @param url         url地址
     * @param param     参数
     * @param noNeedResponse    不需要返回结果
     * @return
     */
    public static String httpPost(String url,String param, boolean noNeedResponse){
        //post请求返回结果
        CloseableHttpClient httpClient= HttpClientBuilder.create().setMaxConnTotal(200).setMaxConnPerRoute(200).build();
        String resultStr = null;
        HttpPost method = new HttpPost(url);

        try {
            if (null != param) {
                //解决中文乱码问题

                StringEntity entity = new StringEntity(param, "utf-8");
                entity.setContentEncoding("UTF-8");
                entity.setContentType("multipart/form-data");
                method.setEntity(entity);
            }
            HttpResponse result = httpClient.execute(method);
            url = URLDecoder.decode(url, "UTF-8");
            /**请求发送成功，并得到响应**/
            if (result.getStatusLine().getStatusCode() == 200) {
                try {
                    /**读取服务器返回过来的json字符串数据**/
                    resultStr = EntityUtils.toString(result.getEntity());
                    if (noNeedResponse) {
                        return null;
                    }
                } catch (Exception e) {
                    logger.error("post请求提交失败:" + url, e);
                }
            }
        } catch (IOException e) {
            System.out.print(e);
            logger.error("post请求提交失败:" + url, e);
        }
        finally {
            try{
                httpClient.close();
            }
            catch (IOException e)
            {
                logger.error("post请求Close()提交失败:" + url, e);
            }
        }
        return resultStr;
    }


    /**
     * 发送get请求
     * @param url    路径
     * @return
     */
    public static String httpGet(String url){
        String strResult=null;
        //get请求返回结果
        CloseableHttpClient client=HttpClientBuilder.create().setMaxConnTotal(200).setMaxConnPerRoute(200).build();
        try {
            //发送get请求
            HttpGet request = new HttpGet(url);
            HttpResponse response = client.execute(request);

            /**请求发送成功，并得到响应**/
            if (response.getStatusLine().getStatusCode() == 200) {
                /**读取服务器返回过来的json字符串数据**/
                strResult = EntityUtils.toString(response.getEntity());

            } else {
                logger.error("get请求提交失败:" + url);
            }
        } catch (IOException e) {
            logger.error("get请求提交失败:" + url, e);
        }
        finally {
            try {
                client.close();
            }catch (IOException e)
            {
                logger.error("get请求Close（）提交失败："+url,e);
            }
            return strResult;
        }
    }
}
