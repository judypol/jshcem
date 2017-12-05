package com.shcem.configCenter.utils;

import com.shcem.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

public class ZKUtil {

    private final static Logger logger = LoggerFactory.getLogger(ZKUtil.class);
//    @Value("#{spring.configCenter.zkurl}")
//    private static String zkUrl;

    public static synchronized String getZkUrl() {
        String zkUrl=System.getProperty("spring.configCenter.zkurl");
        if(StringUtils.isEmpty(zkUrl)){
            zkUrl="localhost:2181";
        }
        return zkUrl;
    }
}
