package com.shcem.enums;

/**
 * Created by judysen on 2017/9/10.
 */
public enum LoggerLevel implements IEnumsName {
    Debug("DEBUG"),
    Info("INFO"),
    Warn("WARN"),
    Error("ERROR"),
    ;
    String value;
    LoggerLevel(String value){
        this.value=value;
    }
    /**
     * 获取枚举的名称
     *
     * @return
     */
    @Override
    public String getName() {
        return null;
    }

    /**
     * 获取枚举对应的int值
     *
     * @return
     */
    @Override
    public int getIntValue() {
        return 0;
    }

    /**
     * 获取枚举的String值
     *
     * @return
     */
    @Override
    public String getStringValue() {
        return null;
    }
}
