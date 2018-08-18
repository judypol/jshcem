package com.shcem.redis.config;

import com.shcem.common.YamlConfiguration;
import com.shcem.constants.SystemDefine;

public class RedisConfiguration {
    private String host="127.0.0.1:6379";
    private String dbIndex="0";
    private int mode=0;     //--redis模式，默认0，单机，1-》集群模式

    public RedisConfiguration(String host,String dbIndex){
        this.host=host;
        this.dbIndex=dbIndex;
    }
    public RedisConfiguration(){
        this.host= YamlConfiguration.instance().getString(SystemDefine.RedisHost,"127.0.0.1:6379");
        this.dbIndex=YamlConfiguration.instance().getString("redis.dbIndex","0");
        this.mode=YamlConfiguration.instance().getInt(SystemDefine.RedisMode,0);
    }
    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getDbIndex() {
        return dbIndex;
    }

    public void setDbIndex(String dbIndex) {
        this.dbIndex = dbIndex;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }
}
