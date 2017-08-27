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
import com.shcem.common.HttpUtlis;
import com.shcem.common.YamlConfiguration;
import com.shcem.constants.SystemDefine;
import com.shcem.utils.DateUtils;
import org.slf4j.MDC;
import java.util.Date;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author lizhihua
 * @version 1.0
 */
public class HttpAppender extends UnsynchronizedAppenderBase<ILoggingEvent> {
    private final ConcurrentLinkedQueue<ILoggingEvent> queue=new ConcurrentLinkedQueue<>();
    private static int count=0;
    private static int totalCount= YamlConfiguration.instance().getInt(SystemDefine.Log_Count,10);
    private StringBuilder msgBuilder=new StringBuilder();
    @Override
    public void doAppend(ILoggingEvent eventObject) {
        //System.out.println(eventObject.getClass().getName());
        super.doAppend(eventObject);
    }

    @Override
    protected void append(ILoggingEvent eventObject) {
        if(count==totalCount){
            count=0;

            String msg=msgBuilder.toString();

            msgBuilder=new StringBuilder();
            //--发送给远程Http---
            sendKafka(msg);
        }

        if(eventObject instanceof ILoggingEvent){
            LoggerModel lm=getLoggerModel(eventObject);
            msgBuilder.append(JSON.toJSONString(lm));
            count++;
        }
    }

    /**
     *
     * @param msg
     */
    private void sendKafka(String msg){
        try{
            HttpUtlis.Instance().postByJson(YamlConfiguration.instance().getString(SystemDefine.Log_Url),msg);
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
}
