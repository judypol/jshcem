package com.shcem.server.model;

import com.shcem.constants.SystemDefine;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

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

    private Map<String,String> header=new HashMap<>();
    // 服务A对象的keyCode
    private String ClientIP;
    private String ReqTime;
    private String MemberID;
    private String MemberName;
    private String AppName;
    private String Mode;
    private String RequestId;
    //zipkin--
    private String TraceId;
    private String SpanId;
    private String ParentId;
    private String SpanName;

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

//    public void setClientIP(String clientIP) {
//        ClientIP = clientIP;
//    }

    public String getReqTime() {
        return ReqTime;
    }

//    public void setReqTime(String reqTime) {
//        ReqTime = reqTime;
//    }

    public String getMemberID() {
        return MemberID;
    }

//    public void setMemberID(String memberID) {
//        MemberID = memberID;
//    }

    public String getMemberName() {
        return MemberName;
    }

//    public void setMemberName(String memberName) {
//        MemberName = memberName;
//    }

    public String getAppName() {
        return AppName;
    }

//    public void setAppName(String appName) {
//        AppName = appName;
//    }

    public String getMode() {
        return Mode;
    }

//    public void setMode(String mode) {
//        Mode = mode;
//    }

    public String getRequestId() {
        return RequestId;
    }

//    public void setRequestId(String requestId) {
//        RequestId = requestId;
//    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
        setContext(request);
    }

    /**
     * 设置当前服务的上下文
     * @param request
     */
    private void setContext(HttpServletRequest request){
        this.AppName=request.getHeader(SystemDefine.REQUEST_APP_NAME);
        this.ClientIP=request.getHeader(SystemDefine.REQUEST_CLIENT_IP);
        this.MemberID=request.getHeader(SystemDefine.REQUEST_MEM_ID);
        this.MemberName=request.getHeader(SystemDefine.REQUEST_MEM_NAME);
        this.Mode=request.getHeader(SystemDefine.REQUEST_MODE);
        this.RequestId=request.getHeader(SystemDefine.REQUEST_REQUESTID);

        this.ParentId=request.getHeader(SystemDefine.HEADER_PARENTID);
        this.SpanId=request.getHeader(SystemDefine.HEADER_SPANID);
        this.SpanName=request.getHeader(SystemDefine.HEADER_SPANNAME);
        this.TraceId=request.getHeader(SystemDefine.HEADER_TRACEID);
    }

    public String getTraceId() {
        return TraceId;
    }

    public void setTraceId(String traceId) {
        TraceId = traceId;
    }

    public String getSpanId() {
        return SpanId;
    }

    public void setSpanId(String spanId) {
        SpanId = spanId;
    }

    public String getParentId() {
        return ParentId;
    }

    public void setParentId(String parentId) {
        ParentId = parentId;
    }

    public String getSpanName() {
        return SpanName;
    }

    public void setSpanName(String spanName) {
        SpanName = spanName;
    }

    public void addHeader(String key,String value){
        this.header.put(key,value);
    }
    public String getHeader(String key){
        return this.header.get(key);
    }
}
