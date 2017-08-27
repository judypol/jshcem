package com.shcem.Exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**化交异常错误基类
 *
 */
public class ShcemException extends Exception {
    public String ErrorCode;
    /**构造函数
     * @param message 错误信息
     * @param errorCode 错误代码
     * */
    protected Logger logger= LoggerFactory.getLogger(this.getClass());
    public ShcemException(String message,String errorCode){
        super(message);
        this.ErrorCode=errorCode;
        this.ErrorLog(null);
    }
    /**构造函数
     * @param message
     * @param errorCode
     * @param e 
     * */
    public ShcemException(String message,String errorCode,Exception e){
        super(message,e);
        this.ErrorCode=errorCode;
        this.ErrorLog(e);
    }
    /**记录错误信息
     * */
    protected void ErrorLog(Exception e)
    {
        logger.debug("the ErrorCode:{},exception:{}",ErrorCode,e);
        // TODO: 2017/2/28
    }
}
