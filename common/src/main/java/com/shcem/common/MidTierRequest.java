package com.shcem.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shcem.constants.SystemDefine;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URLEncoder;
import java.rmi.server.ExportException;

/**
 * Created by lizhihua on 2017/2/16.
 */
public class MidTierRequest {
    private Logger logger = LoggerFactory.getLogger(MidTierRequest.class);
    @Autowired
    private HttpServletRequest request;
    private static CloseableHttpClient client=HttpClientBuilder.create().setMaxConnTotal(200).setMaxConnPerRoute(200).build();
    /**
     *服务调用
     * **/
    public static ResponseData Post(RequestData requestData)
    {
        MidTierRequest midTierRequest=new MidTierRequest();
        return midTierRequest.sendPost(requestData);
    }

    /**
     *发生一个post请求
     * @param requestData
     * @return
     */
    public ResponseData sendPost(RequestData requestData){
        logger.debug("call post method start");
        long startTime= System.currentTimeMillis();
        RequestConfig requestConfig=RequestConfig.DEFAULT;
        //CloseableHttpClient client= HttpClientBuilder.create().setMaxConnTotal(200).setMaxConnPerRoute(200).build();

        String resultStr = null;
        String url=YamlConfiguration.instance().getString(SystemDefine.MidTierUrl);

        HttpPost method = new HttpPost(url);
        String param=requestDataString(requestData);

        try {
            //解决中文乱码问题
            StringEntity entity = new StringEntity(param, "utf-8");
            entity.setContentEncoding("UTF-8");
            entity.setContentType("multipart/form-data");
            method.setEntity(entity);

            setHeaders(method);
            CloseableHttpResponse result = client.execute(method);
            try{
                /**请求发送成功，并得到响应**/
                if (result.getStatusLine().getStatusCode() == 200) {
                    try {
                        /**读取服务器返回过来的json字符串数据**/
                        resultStr = EntityUtils.toString(result.getEntity());

                    } catch (Exception e) {
                        logger.error("post请求提交失败:" + url, e);
                    }
                }
            }catch (Exception ex) {
                logger.error("post请求提交失败:" + url, ex);
            }finally {
                result.close();
            }
        } catch (IOException e) {
            logger.error("post请求提交失败:" + url, e);
        }

        if(resultStr==null)
            return null;
        long endTime=System.currentTimeMillis();
        logger.debug("call post method end");
        return JSON.parseObject(resultStr,ResponseData.class);
    }
    private String requestDataString(RequestData data)
    {
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("json",data);

        return jsonObject.toJSONString();
    }
    private void setHeaders(HttpPost method)
    {
        if(request!=null) {
            String url = request.getRequestURI();
            String requestId = (String) request.getAttribute("RequestId");
            method.setHeader("RequestId", requestId);       //
            method.setHeader("UserAgent", request.getHeader("User-Agent"));
            try {
                method.setHeader("Referer", URLEncoder.encode(url, "utf-8"));
            } catch (Exception e) {
                logger.info("URLEncoder is error in setHeaders", e);
            }

            String authKey = YamlConfiguration.instance().getString(SystemDefine.AuthPrefix);//AppConfiguration.AppConfig().getProperty("auth.prefix");
            String userCode = (String) request.getAttribute(SystemDefine.REQUEST_LOGIN_NAME);
            if (!StringUtils.isEmpty(userCode)) {
                authKey = authKey + userCode;
            }

            String clientIp = (String) request.getAttribute(SystemDefine.REQUEST_CLIENT_IP);
            method.addHeader(SystemDefine.REQUEST_CLIENT_IP, clientIp);
            method.addHeader(SystemDefine.REQUEST_AUTHKEY, authKey);

            method.addHeader(SystemDefine.REQUEST_MEM_ID, userCode);
            method.addHeader(SystemDefine.REQUEST_MEM_NAME, userCode);
        }
        method.addHeader(SystemDefine.REQUEST_APP_NAME,YamlConfiguration.instance().getString(SystemDefine.AppName));
        method.addHeader(SystemDefine.REQUEST_AUTH_APP,YamlConfiguration.instance().getString(SystemDefine.AuthApp));
    }
    /**
     * 取得开发模式
     *
     * @return 开发模式
     */
    protected String getMode() {

        String mode = SystemDefine.MODE_LOCAL;
        String sysMode = System.getProperty("mode");

        if (sysMode != null && !StringUtils.isEmpty(sysMode)) {
            if (SystemDefine.MODE_LOCAL.equals(sysMode)
                    || SystemDefine.MODE_DEV.equals(sysMode)
                    || SystemDefine.MODE_TEST.equals(sysMode)
                    || SystemDefine.MODE_UAT.equals(sysMode)
                    || SystemDefine.MODE_DEPLOY.equals(sysMode)) {
                mode = sysMode;
            }
        }

        return mode;
    }
}
