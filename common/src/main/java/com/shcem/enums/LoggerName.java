package com.shcem.enums;

/**
 * Created by judysen on 2017/9/10.
 */
public enum LoggerName implements IEnumsName {
    Controller("controller"),Service("service");
    LoggerName(String loggerName){
        this.value=loggerName;
    }
    String value;
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
        return this.value;
    }
}
