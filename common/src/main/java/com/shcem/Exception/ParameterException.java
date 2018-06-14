package com.shcem.Exception;

/**
 * 参数错误异常
 */
public class ParameterException extends ShcemException {

    public ParameterException(String message,String code){
        super(message,code);
    }

    public ParameterException(String message,String code,Exception ex) {
        super(message,code,ex);
    }
}
