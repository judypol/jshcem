package com.shcem.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shcem.Encrypt.EncrytHelper;
import com.shcem.constants.SystemDefine;
import okhttp3.Dispatcher;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
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
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 修改httpClient -> okHttp3
 * Created by lizhihua on 2017/2/16.
 */
public class MidTierHandler {
    private static Logger logger = LoggerFactory.getLogger("controller");

    /**
     * 服务调用
     **/
    public static ResponseData Post(RequestData requestData) {
        return sendPost(requestData);
    }

    /**
     * 发送一个post请求
     *
     * @param requestData
     * @return
     */
    public static ResponseData sendPost(RequestData requestData) {
        logger.debug("call post method start,params:" + JSON.toJSONString(requestData));
        long startTime = System.currentTimeMillis();

        String resultStr = null;
        String url = YamlConfiguration.instance().getString(SystemDefine.MidTierUrl);

        logger.debug("midterUrl--" + url);

        String param = requestDataString(requestData);

        logger.debug("params:" + param);

        try{
            resultStr=HttpUtlis.Instance().postByJson(url,param,setHeaders());
        }catch (Exception ex){
            ResponseData responseData=new ResponseData();
            responseData.setINFO("请求失败");
            responseData.setCODE("90009");
            resultStr=JSON.toJSONString(responseData);
            logger.error("call midTier post err:",ex);
        }

        if (resultStr == null)
            return null;
        long endTime = System.currentTimeMillis();
        logger.debug("call post method end result:" + resultStr);
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
     * @param
     */
    private static Map<String,String> setHeaders() {
        Map<String,String> headers=new HashMap<>();

        String requestId = MDC.get("RequestId");
        headers.put("RequestId", requestId);       //
        headers.put("UserAgent", MDC.get(SystemDefine.REQUEST_USERAGENT));
        headers.put("Referer", MDC.get(SystemDefine.REQUEST_REFERER));

        String authKey = YamlConfiguration.instance().getString(SystemDefine.AuthPrefix);
        String userCode = MDC.get(SystemDefine.REQUEST_LOGIN_NAME);
        if (!StringUtils.isEmpty(userCode)) {
            authKey = authKey + userCode;
        }

        String clientIp = MDC.get(SystemDefine.REQUEST_CLIENT_IP);
        headers.put(SystemDefine.REQUEST_CLIENT_IP, clientIp);
        if (!StringUtils.isEmpty(authKey)) {
            headers.put(SystemDefine.REQUEST_AUTHKEY, authKey);
        }

        headers.put(SystemDefine.REQUEST_MEM_ID, userCode);
        String userName="";
        try{
            userName=EncrytHelper.encryptBase64(MDC.get(SystemDefine.REQUEST_MEM_NAME));
        }catch (Exception ex){
            userName="";
        }

        headers.put(SystemDefine.REQUEST_MEM_NAME, userName);

        headers.put(SystemDefine.REQUEST_APP_NAME, YamlConfiguration.instance().getString(SystemDefine.AppName));
        headers.put(SystemDefine.REQUEST_AUTH_APP, YamlConfiguration.instance().getString(SystemDefine.AuthApp));

        logger.debug("the http header:" + JSON.toJSONString(headers));

        return headers;
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
