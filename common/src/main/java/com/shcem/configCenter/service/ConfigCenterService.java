package com.shcem.configCenter.service;


import com.shcem.configCenter.event.DataChangeEvent;

import java.util.Map;

public interface ConfigCenterService {
    public Map<String,Object> getConfig();
    public String get(String key);
    public Long getLongValue(String key);
    public Integer getIntegerValue(String key);
    public Double getDoubleValue(String key);
    public Boolean getBooleanValue(String key);
    public void notify(DataChangeEvent event);
    public  void colse();
}
