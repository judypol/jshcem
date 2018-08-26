package com.shcem.logback;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.StackTraceElementProxy;
import ch.qos.logback.core.UnsynchronizedAppenderBase;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shcem.common.HttpUtlis;
import com.shcem.constants.SystemDefine;
import com.shcem.utils.DateUtils;
import org.slf4j.MDC;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 发送邮件Append
 * logback,appender，需要在配置文件中配置。
 * @author lizhihua
 * @version 1.0
 */
public class EmailAppender extends UnsynchronizedAppenderBase<ILoggingEvent> {
    private String emailUrl;
    private String subject;
    private String sender;
    private String to;
    private String cc;

    @Override
    protected void append(ILoggingEvent iLoggingEvent) {
        if(!isStarted()) return;

        if(iLoggingEvent.getLevel()== Level.ERROR){
            try{
                String mailBody= JSON.toJSONString(getLoggerModel(iLoggingEvent));
                sendEmail(this.to,this.sender,mailBody,this.subject);
            }catch (Exception ex){
                System.out.print(ex);
            }
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

    private void sendEmail(String to,String sender,String mailBody,String mailSubject) throws Exception{
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Recipient", to);
        jsonObject.put("Sender", sender);
        jsonObject.put("Body", mailBody);
        jsonObject.put("IsHtmlBody", true);
        jsonObject.put("Subject", mailSubject);

        jsonObject.put("cc","");          //带cc

        Map<String, String> data = new HashMap<>();
        data.put("sendBody", jsonObject.toJSONString());
        HttpUtlis.Instance().postByForm(this.emailUrl,data,null);
    }

    public String getEmailUrl() {
        return emailUrl;
    }

    public void setEmailUrl(String emailUrl) {
        this.emailUrl = emailUrl;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }
}
