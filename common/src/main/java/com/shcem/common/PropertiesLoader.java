package com.shcem.common;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.net.URL;
import java.util.NoSuchElementException;
import java.util.Properties;

/**
 *   * Properties文件载入工具类. 可载入多个properties文件, 相同的属性在最后载入的文件中的值将会覆盖之前的值，但以System的Property优先.
 *   解决中文乱码问题
 *   * Created by Lizhihua on 2016/2/23.
 *
 */
public class PropertiesLoader {
    private static Logger logger = LoggerFactory.getLogger(PropertiesLoader.class);
    private static ResourceLoader resourceLoader = new DefaultResourceLoader();
    private final Properties properties;

    public PropertiesLoader(String... resourcesRaths) {
        properties = loadProperties(resourcesRaths);
    }

    /**
     * 从字符串中解析
     * @param propertiesContents
     */
    public PropertiesLoader(String propertiesContents){
        properties=loadPropertiesFromString(propertiesContents);
    }
    public Properties getProperties() {
        return properties;
    }

    /**
     * 取出property，但以System的property优先，取不到返回空字符串
     */
    private String getValue(String key) {
//        String systemProperty = System.getProperty(key);
//        if (systemProperty != null) {
//            return systemProperty;
//        }
        if (properties.containsKey(key)) {
            return properties.getProperty(key);
        }
        return "";
    }

    /**
     * 取出property，但以System的property优先，取不到返回空字符串
     * /
     * private String getValue(String key){
     * String systemProperty = System.getProperty(key);
     * if (systemProperty!=null){
     * return systemProperty;
     * }
     * if (properties.containsKey(key)){
     * return properties.getProperty(key);
     * }
     * return "";
     * }
     * <p>
     * /**
     * 取出String类型的Property，System的优先
     *
     * @throws NoSuchElementException
     */
    public String getProperty(String key) {
        String value = getValue(key);
        if (value == null) {
            throw new NoSuchElementException();
        }
        return value;
    }

    /**
     * 取出String类型的Property，System的优先,null则返回默认值
     */
    public String getProperty(String key, String defaultValue) {
        String value = getValue(key);
        return value != null ? value : defaultValue;
    }

    /**
     * 取出Integer类型的Property，System优先
     *
     * @throws NoSuchElementException
     */
    public Integer getInteger(String key) {
        String value = getValue(key);
        if (StringUtils.isEmpty(value)) {
            throw new NoSuchElementException();
        }
        return Integer.valueOf(value);
    }

    /**
     * 取出Integer类型的Property，System优先,null则返回默认值
     */
    public Integer getInteger(String key, Integer defaultValue) {
        String value = getValue(key);

        return !StringUtils.isEmpty(value)  ? Integer.valueOf(value) : defaultValue;
    }

    /**
     * 取出Double类型的Property，但以System的Property优先.如果都为Null或内容错误则抛出异常.
     */
    public Double getDouble(String key) {
        String value = getValue(key);
        if (StringUtils.isEmpty(value)) {
            throw new NoSuchElementException();
        }
        return Double.valueOf(value);
    }

    /**
     * 取出Double类型的Property，但以System的Property优先.如果都为Null则返回Default值，如果内容错误则抛出异常
     */
    public Double getDouble(String key, Integer defaultValue) {
        String value = getValue(key);
        return !StringUtils.isEmpty(value) ? Double.valueOf(value) : defaultValue;
    }

    /**
     * 取出Boolean类型的Property，但以System的Property优先.如果都为Null抛出异常,如果内容不是true/false则返回false.
     */
    public Boolean getBoolean(String key) {
        String value = getValue(key);
        if (StringUtils.isEmpty(value)) {
            throw new NoSuchElementException();
        }
        return Boolean.valueOf(value);
    }

    /**
     * 取出Boolean类型的Property，但以System的Property优先.如果都为Null则返回Default值,如果内容不为true/false则返回false.
     */
    public Boolean getBoolean(String key, boolean defaultValue) {
        String value = getValue(key);
        return !StringUtils.isEmpty(value) ? Boolean.valueOf(value) : defaultValue;
    }

    /**
     * 从给定的properties字符串解析
     * @param propertiesContents
     * @return
     */
    protected Properties loadPropertiesFromString(String propertiesContents){
        Properties props=new Properties();
        try{
            byte[] bytes=propertiesContents.getBytes("utf-8");
            ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(bytes);

            props.load(byteArrayInputStream);
        }catch (Exception ex){
            logger.error("解析properties内容时出错");

        }
        return props;
    }
    /**
     * 载入多个文件，文件路径使用spring resource格式
     *
     * @param resourcesRaths
     * @return
     */
    protected Properties loadProperties(String[] resourcesRaths) {
        Properties props = new Properties();
        for (String location : resourcesRaths) {
            logger.debug("Loading properties file from:" + location);
            InputStream is = null;
            try {
                if(location.startsWith("classpath")){
                    URL appUrl= ResourceUtils.getURL(location);
                    is=appUrl.openStream();
                }else {
                    File file=new File(location);
                    if(file.exists()){
                        is=new FileInputStream(file);
                    }
                }

                if(is==null)
                {
                    continue;
                }
                //BufferedReader isr=new BufferedReader(new InputStreamReader(is,"utf-8"));
                InputStreamReader isr=new InputStreamReader(is,"UTF-8");
                props.load(isr);
            } catch (Exception e) {
                //logger.info("Could not load properties from path:{},{}", location, e.getMessage());
                logger.error("Could not load properties from path",e);
            } finally {
                //IOUtils.closeQuietly(is);
                try{
                    if(is!=null)
                    {
                        is.close();
                    }
                }catch (IOException e){
                    logger.info("resource close is failed");
                }
            }
        }
        return props;
    }
}
