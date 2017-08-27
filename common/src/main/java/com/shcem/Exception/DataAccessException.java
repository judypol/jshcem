package com.shcem.Exception;

/**
 * Created by lizhihua on 2017/2/28.
 */
public class DataAccessException extends ShcemException {
    /**构造函数
     * @param message
     * @param errorCode
     * */
    public DataAccessException(String message, String errorCode) {
        super(message, errorCode);
    }
    /**构造函数
     * @param message
     * @param errorCode
     * @param e
     * */
    public DataAccessException(String message, String errorCode, Exception e) {
        super(message, errorCode, e);
    }
}
