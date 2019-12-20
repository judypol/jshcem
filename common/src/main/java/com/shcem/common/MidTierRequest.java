package com.shcem.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shcem.Encrypt.EncrytHelper;
import com.shcem.constants.SystemDefine;
import com.shcem.utils.SpringContextHolder;
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
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URLEncoder;
import java.rmi.server.ExportException;

/**
 * 请使用MidTierHandler方法
 * Created by lizhihua on 2017/2/16.
 */
@Deprecated
public class MidTierRequest {
    private static Logger logger = LoggerFactory.getLogger("controller");
    private static CloseableHttpClient client = HttpClientBuilder.create().setMaxConnTotal(200).setMaxConnPerRoute(200).build();

    /**
     * 服务调用
     **/
    public static ResponseData Post(RequestData requestData) {
        return sendPost(requestData);
    }

    /**
     * 发生一个post请求
     *
     * @param requestData
     * @return
     */
    public static ResponseData sendPost(RequestData requestData) {
        logger.debug("call post method start,params:" + JSON.toJSONString(requestData));
        long startTime = System.currentTimeMillis();
        RequestConfig requestConfig = RequestConfig.DEFAULT;

        String resultStr = null;
        String url = YamlConfiguration.instance().getString(SystemDefine.MidTierUrl);

        logger.debug("midterUrl--" + url);

        HttpPost method = new HttpPost(url);
        String param = requestDataString(requestData);

        logger.debug("params:" + param);
        try {
            //解决中文乱码问题
            StringEntity entity = new StringEntity(param, "utf-8");
            entity.setContentEncoding("UTF-8");
            entity.setContentType("multipart/form-data");
            method.setEntity(entity);

            setHeaders(method);
            CloseableHttpResponse result = client.execute(method);
            try {
                /**请求发送成功，并得到响应**/
                if (result.getStatusLine().getStatusCode() == 200) {
                    try {
                        /**读取服务器返回过来的json字符串数据**/
                        resultStr = EntityUtils.toString(result.getEntity());

                    } catch (Exception e) {
                        logger.error("post请求提交失败:" + url, e);
                    }
                }
            } catch (Exception ex) {
                logger.error("post请求提交失败:" + url, ex);
            } finally {
                result.close();
            }
        } catch (IOException e) {
            logger.error("post请求提交失败:" + url, e);
        }

        if (resultStr == null)
            return null;
        long endTime = System.currentTimeMillis();
        logger.info("call post method end result:" + resultStr);
        return JSON.parseObject(resultStr, ResponseData.class);
    }

    /**
     * 请求的json字符串
     *
     * @param data
     * @return
     */
    private static String requestDataString(RequestData data) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("json", data);

        return jsonObject.toJSONString();
    }

    /**
     * 设置请求头部
     *
     * @param method
     */
    private static void setHeaders(HttpPost method) {
        String requestId = MDC.get("RequestId");
        method.setHeader("RequestId", requestId);       //
        method.setHeader("UserAgent", MDC.get(SystemDefine.REQUEST_USERAGENT));
        try {
            method.setHeader("Referer", MDC.get(SystemDefine.REQUEST_REFERER));
        } catch (Exception e) {
            logger.info("URLEncoder is error in setHeaders", e);
        }

        String authKey = YamlConfiguration.instance().getString(SystemDefine.AuthPrefix);
        String userCode = MDC.get(SystemDefine.REQUEST_LOGIN_NAME);
        if (!StringUtils.isEmpty(userCode)) {
            authKey = authKey + userCode;
        }

        String clientIp = MDC.get(SystemDefine.REQUEST_CLIENT_IP);
        method.addHeader(SystemDefine.REQUEST_CLIENT_IP, clientIp);
        if (!StringUtils.isEmpty(authKey)) {
            method.addHeader(SystemDefine.REQUEST_AUTHKEY, authKey);
        }

        method.addHeader(SystemDefine.REQUEST_MEM_ID, userCode);
        String userName="";
        try{
            userName=EncrytHelper.encryptBase64(MDC.get(SystemDefine.REQUEST_MEM_NAME));
        }catch (Exception ex){
            userName="";
        }

        method.addHeader(SystemDefine.REQUEST_MEM_NAME, userName);

        method.addHeader(SystemDefine.REQUEST_APP_NAME, YamlConfiguration.instance().getString(SystemDefine.AppName));
        method.addHeader(SystemDefine.REQUEST_AUTH_APP, YamlConfiguration.instance().getString(SystemDefine.AuthApp));

        logger.info("the http header:" + JSON.toJSONString(method.getAllHeaders()));
    }

    /**
     * 取得开发模式
     *
     * @return 开发模式
     */
    protected static String getMode() {

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
