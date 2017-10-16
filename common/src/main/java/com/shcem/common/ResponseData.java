package com.shcem.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * Created by lizhihua on 2017/2/16.
 */
public class ResponseData {
    private String DATA;
    private String CODE;
    private String INFO;

    public ResponseData(String code,String data,String info)
    {
        this.CODE=code;
        this.DATA=data;
        this.INFO=info;
    }
    public ResponseData(){
        this.CODE="99999";
    }

    public String getDATA() {
        return DATA;
    }

    public void setDATA(String DATA) {
        this.DATA = DATA;
    }

    public String getCODE() {
        return CODE;
    }

    public void setCODE(String CODE) {
        this.CODE = CODE;
    }

    public String getINFO() {
        return INFO;
    }

    public void setINFO(String INFO) {
        this.INFO = INFO;
    }

    @Override
    public String toString(){
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("DATA",this.getDATA());
        jsonObject.put("CODE",this.getCODE());
        jsonObject.put("INFO",this.getINFO());
        return jsonObject.toString();
    }
}
