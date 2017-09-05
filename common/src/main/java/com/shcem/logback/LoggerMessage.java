/* ========================================
 * System Name　　：化交线上平台
 * SubSystem Name ：化交站点核心工具集
 * File Name: Constants
 * ----------------------------------------
 * Create Date/Change History
 * ----------------------------------------
 * 2017/8/15 　lizhihua   Create
 *
 *
 * ----------------------------------------
 * Copyright (c) SCEM . All rights reserved.
 */
package com.shcem.logback;

/**
 * @author lizhihua
 * @version 1.0
 */
public class LoggerMessage {
    String requestId;
    String userCode;
    String userName;
    String userAgent;
    String referUrl;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getReferUrl() {
        return referUrl;
    }

    public void setReferUrl(String referUrl) {
        this.referUrl = referUrl;
    }

    static ThreadLocal<LoggerMessage> loggerContextThreadLocal=new ThreadLocal<>();

    public static LoggerMessage getCurrentContext(){
        return loggerContextThreadLocal.get();
    }
    public static void setCurrentContext(LoggerMessage loggerContext){
        loggerContextThreadLocal.set(loggerContext);
    }

    public static void clear(){
        loggerContextThreadLocal.remove();
    }
}
