/* ========================================
 * System Name　　：化交线上平台
 * SubSystem Name ：化交站点核心工具集
 * File Name: Constants
 * ----------------------------------------
 * Create Date/Change History
 * ----------------------------------------
 * 2017/8/14 　lizhihua   Create
 *
 *
 * ----------------------------------------
 * Copyright (c) SCEM . All rights reserved.
 */
package com.shcem.logback;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.StackTraceElementProxy;
import ch.qos.logback.core.UnsynchronizedAppenderBase;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shcem.common.HttpRequestUtils;
import com.shcem.common.HttpUtlis;
import com.shcem.common.YamlConfiguration;
import com.shcem.constants.SystemDefine;
import com.shcem.netty.NettyLogClient;
import com.shcem.utils.DateUtils;
import org.slf4j.MDC;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * logback日appender，需要在配置文件中配置。
 * 采用批量上传日志信息到服务器，默认是10条发送一次，可以在app.yaml文件中配置logCount数值
 * 远程URL也需要在app.yaml文件中配置logUrl
 * @author lizhihua
 * @version 1.0
 */
public class HttpAppender extends UnsynchronizedAppenderBase<ILoggingEvent> {
    private final ConcurrentLinkedQueue<ILoggingEvent> queue=new ConcurrentLinkedQueue<>();
    private static int count=0;
    private static List<HashMap<String,String>> msg=new ArrayList<>();

    String logUrl;
    int logBuffer=10;
    String logSys="WEBFT";
    String logEnv="DEP";

    @Override
    public void doAppend(ILoggingEvent eventObject) {
        super.doAppend(eventObject);
    }

    @Override
    protected void append(ILoggingEvent eventObject) {
        if(count==this.logBuffer){
            count=0;

            //String msgString=JSON.toJSONString(msg);
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("log",msg);
            String sendMsg=jsonObject.toJSONString();

            msg.clear();
            //--发送给远程Http---
            sendKafka(sendMsg);
        }

        if(eventObject instanceof ILoggingEvent){
            LoggerModel lm=getLoggerModel(eventObject);
            HashMap<String,String> map=new HashMap<>();
            map.put("SYS",this.logSys);
            map.put("ENV",this.logEnv);
            map.put("DATA",JSON.toJSONString(lm));
            msg.add(map);

            count++;
        }
    }

    /**
     *
     * @param msg
     */
    private void sendKafka(String msg){
        try{
            Map<String,String> formMap=new HashMap<>();
            formMap.put("",msg);
            //HttpUtlis.Instance().postByForm(this.logUrl,formMap);
            //HttpRequestUtils.httpPost(this.logUrl,msg);
            NettyLogClient.sendMsg(this.logUrl,msg);
        }catch (Exception ex){
            System.out.println(ex);
        }
    }
    /**
     *
     * @return
     */
    private LoggerModel getLoggerModel(ILoggingEvent loggingEvent){
        LoggerModel model=new LoggerModel();
        model.setAppName(MDC.get(SystemDefine.REQUEST_APP_NAME));
        model.setIpAddress(MDC.get(SystemDefine.REQUEST_CLIENT_IP));
        model.setRefer(MDC.get(SystemDefine.REQUEST_REFERER));
        model.setRequestId(MDC.get(SystemDefine.REQUEST_REQUESTID));
        model.setUserAgent(MDC.get(SystemDefine.REQUEST_USERAGENT));
        model.setUserId(MDC.get(SystemDefine.REQUEST_MEM_ID));

        model.setLevel(loggingEvent.getLevel().levelStr);
        Date date=new Date(loggingEvent.getTimeStamp());
        model.setLogDate(DateUtils.FormatDate(date,"yyyy-MM-dd HH:mm:ss SSS"));
        model.setMessage(loggingEvent.getMessage());
        StringBuilder sb=new StringBuilder();
        if(loggingEvent.getThrowableProxy()!=null){
            sb.append(loggingEvent.getThrowableProxy().getMessage()+"\\r\\n");
            StackTraceElementProxy[] traceElementProxies =loggingEvent.getThrowableProxy().getStackTraceElementProxyArray();
            int index=0;
            for(int i=traceElementProxies.length-1;i>-1;i--){
                if(index>9){
                    break;
                }
                StackTraceElementProxy stackTraceElementProxy=traceElementProxies[i];
                sb.append(stackTraceElementProxy.getSTEAsString()+"\\r\\n");
                index++;
            }
            model.setException(sb.toString());
        }
        return model;
    }

    public String getLogUrl() {
        return logUrl;
    }

    public void setLogUrl(String logUrl) {
        this.logUrl = logUrl;
    }

    public int getLogBuffer() {
        return logBuffer;
    }

    public void setLogBuffer(int logBuffer) {
        this.logBuffer = logBuffer;
    }

    public String getLogSys() {
        return logSys;
    }

    public void setLogSys(String logSys) {
        this.logSys = logSys;
    }

    public String getLogEnv() {
        return logEnv;
    }

    public void setLogEnv(String logEnv) {
        this.logEnv = logEnv;
    }
}
