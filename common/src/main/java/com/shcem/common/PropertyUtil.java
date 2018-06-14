package com.shcem.common;

import com.shcem.utils.Encodes;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by judysen on 2017/8/27.
 */
public class PropertyUtil {
    static ConcurrentHashMap<String,PropertiesLoader> map=new ConcurrentHashMap<>();
    public synchronized static PropertiesLoader create(String propertiesFile){
        String encodePropertiesFile= Encodes.EncodeBase64(propertiesFile);
        if(map.containsKey(encodePropertiesFile)){
            return map.get(encodePropertiesFile);
        }else{
            PropertiesLoader propertiesLoader=new PropertiesLoader(propertiesFile);
            map.put(encodePropertiesFile,propertiesLoader);
            return propertiesLoader;
        }
    }
}
