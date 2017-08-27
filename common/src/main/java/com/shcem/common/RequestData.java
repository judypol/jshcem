package com.shcem.common;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by lizhihua on 2017/2/16.
 */
public class RequestData {
    String MethodName;
    String ServiceName;
    @JSONField(name = "Params")
    public String getParams() {
        return Params;
    }

    public void setParams(String params) {
        Params = params;
    }
    @JSONField(name = "MethodName")
    public String getMethodName() {
        return MethodName;
    }

    public void setMethodName(String methodName) {
        MethodName = methodName;
    }
    @JSONField(name = "ServiceName")
    public String getServiceName() {
        return ServiceName;
    }

    public void setServiceName(String serviceName) {
        ServiceName = serviceName;
    }

    String Params;

}
