package com.shcem.common;

import com.alibaba.fastjson.JSON;
import com.shcem.utils.DateUtils;
import org.ho.yaml.Yaml;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by judysen on 2017/8/19.
 */
public class YamlConfiguration {
    Logger logger= LoggerFactory.getLogger(YamlConfiguration.class);
    String platformName;
    String projectName;
    HashMap yamlMap;
    public YamlConfiguration() {
        try{
            yamlMap=getConfig();
        }catch (Exception ex){
            logger.error("get the yaml configuration is error",ex);
            yamlMap=new HashMap();
        }
    }

    public static YamlConfiguration instance(){
        return new YamlConfiguration();
    }
    public String getPlatformName() {
        return platformName;
    }

    protected void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public String getProjectName() {
        return projectName;
    }

    protected void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    protected HashMap<?,?> readClassPathConfiguration() throws Exception{
        String appConfiguationFileName="classpath:app.yaml";
        URL url= ResourceUtils.getURL(appConfiguationFileName);
        try{
            logger.debug("readClassPathConfiguration path is {}",url.getPath());
            HashMap<?,?> appMap= Yaml.loadType(url.openStream(),HashMap.class);
            if(appMap.size()!=1){
                throw new Exception("classpath:app.yaml, the platform config is wrong!");
            }
            //--获取platform--
            HashMap<?,?> projectMap=new HashMap<>();
            for(Map.Entry<?,?> entry :appMap.entrySet()){
                this.setPlatformName(entry.getKey().toString());
                if(entry.getValue() instanceof HashMap){
                    projectMap=(HashMap<?, ?>) entry.getValue();
                }else{
                    throw new Exception("classpath:app.yaml, the platform config is wrong!");
                }
            }
            if(projectMap.size()!=1){
                throw new Exception("classpath:app.yaml,the project config is wrong!");
            }
            //---获取工程配置---
            HashMap<?,?> configMap=new HashMap<>();
            for(Map.Entry<?,?> entry: projectMap.entrySet()){
                this.setProjectName(entry.getKey().toString());
                if(entry.getValue() instanceof HashMap){
                    configMap=(HashMap<?, ?>) entry.getValue();
                }else{
                    throw new Exception("classpath:app.yaml,the project config is wrong!");
                }
            }
            return configMap;
        }catch (Exception ex){
            logger.error("readClassPathConfiguration is error ",ex);
            throw ex;
        }
    }

    /**
     * 获取共通的配置文件
     * @return
     * @throws Exception
     */
    private HashMap getCommonConfiguration() throws Exception{
        HashMap<?,?> appCongiMap=readClassPathConfiguration();
        String otherconfig = "";
        if (OSCheck.isWindows()) {
            otherconfig = "C:\\mltp\\mltp.yaml";
        } else {
            otherconfig = "/etc/mltp/mltp.yaml";
        }
        File file=new File(otherconfig);
        if(!file.exists()){
            logger.warn("not set common config in {}",otherconfig);
            return appCongiMap;
        }
        try{
            HashMap<?,?> appMap= Yaml.loadType(new FileInputStream(file),HashMap.class);
            HashMap<?,?> platforMap=(HashMap<?,?>) appMap.get(this.getPlatformName());
            HashMap<?,?> projectMap=(HashMap<?,?>)platforMap.get(this.getProjectName());

            HashMap map=new HashMap<>();
            for(Map.Entry<?,?> entry:platforMap.entrySet()){
                if(!(entry.getValue() instanceof HashMap || entry.getValue() instanceof ArrayList)){
                    map.put(entry.getKey(),entry.getValue());
                }
            }
            for(Map.Entry<?,?> entry:projectMap.entrySet()){
                if(!(entry.getValue() instanceof HashMap || entry.getValue() instanceof ArrayList)){
                    map.put(entry.getKey(),entry.getValue());
                }
            }

            appCongiMap.putAll(map);
            return appCongiMap;
        }catch (Exception ex){
            logger.warn("{} config is wrong",otherconfig);
            return appCongiMap;
        }
    }

    /**
     * 获取应用程序的配置文件
     * @return
     * @throws Exception
     */
    public HashMap getConfig() throws Exception{
        HashMap map=getCommonConfiguration();
        logger.debug(JSON.toJSONString(map));
        return map;
    }

    /**
     * 获取配置的String值
     * @param key
     * @return
     */
    public String getString(String key){
        return getString(key,"");
    }

    /**
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public String getString(String key,String defaultValue){
        if(yamlMap.containsKey(key)){
            return yamlMap.get(key).toString();
        }else{
            return defaultValue;
        }
    }

    /**
     * 获取配置的Int值
     * @param key
     * @return
     */
    public int getInt(String key){
        return getInt(key,0);
    }

    /**
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public int getInt(String key,int defaultValue){
        if(yamlMap.containsKey(key)){
            return Integer.parseInt(getString(key));
        }else{
            return defaultValue;
        }
    }

    /**
     *
     * @param key
     * @param formate
     * @return
     */
    public Date getDate(String key,String formate){
        if(yamlMap.containsKey(key)){
            try{
                return DateUtils.parseDate(getString(key),formate);
            }catch (Exception ex){
                return new Date();
            }
        }else {
            return new Date();
        }
    }
}
