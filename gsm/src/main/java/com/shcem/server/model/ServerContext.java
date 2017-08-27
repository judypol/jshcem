package com.shcem.server.model;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by judysen on 2017/8/26.
 */
public class ServerContext {
    static InheritableThreadLocal<ServerContext> serverContextInheritableThreadLocal=new InheritableThreadLocal<>();
    protected ServerContext(){

    }

    /**
     *
     * @return
     */
    public static ServerContext currentContext(){
        ServerContext serverContext=serverContextInheritableThreadLocal.get();
        if(serverContext==null){
            serverContext=new ServerContext();
            serverContextInheritableThreadLocal.set(serverContext);
        }
        return serverContext;
    }

    /**
     *
     */
    public static void clear(){
        serverContextInheritableThreadLocal.remove();
    }

    // 服务A对象的keyCode
    private String ClientIP;
    private String ReqTime;
    private String MemberID;
    private String MemberName;
    private String AppName;
    private String Mode;
    private String RequestId;
    HttpServletRequest request;


    public static InheritableThreadLocal<ServerContext> getServerContextInheritableThreadLocal() {
        return serverContextInheritableThreadLocal;
    }

    public static void setServerContextInheritableThreadLocal(InheritableThreadLocal<ServerContext> serverContextInheritableThreadLocal) {
        ServerContext.serverContextInheritableThreadLocal = serverContextInheritableThreadLocal;
    }

    public String getClientIP() {
        return ClientIP;
    }

    public void setClientIP(String clientIP) {
        ClientIP = clientIP;
    }

    public String getReqTime() {
        return ReqTime;
    }

    public void setReqTime(String reqTime) {
        ReqTime = reqTime;
    }

    public String getMemberID() {
        return MemberID;
    }

    public void setMemberID(String memberID) {
        MemberID = memberID;
    }

    public String getMemberName() {
        return MemberName;
    }

    public void setMemberName(String memberName) {
        MemberName = memberName;
    }

    public String getAppName() {
        return AppName;
    }

    public void setAppName(String appName) {
        AppName = appName;
    }

    public String getMode() {
        return Mode;
    }

    public void setMode(String mode) {
        Mode = mode;
    }

    public String getRequestId() {
        return RequestId;
    }

    public void setRequestId(String requestId) {
        RequestId = requestId;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }
}
