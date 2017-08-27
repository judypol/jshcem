package com.shcem.DistributionLock;

/**
 * Created by judysen on 2017/4/4.
 */
public class CacheLockException extends Throwable {
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public CacheLockException(String msg) {
        this.msg = msg;
    }

    public CacheLockException() {
    }
}
