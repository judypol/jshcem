package com.shcem.common;

/**
 * Created by lizhihua on 2017/2/16.
 */
public class ResponseData {
    public String DATA;
    public String CODE;
    public String INFO;
    public ResponseData(String code,String data,String info)
    {
        this.CODE=code;
        this.DATA=data;
        this.INFO=info;
    }
    public ResponseData(){

    }
}
