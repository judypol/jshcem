package com.shcem.configCenter.utils;

import com.shcem.common.YamlConfiguration;
import com.shcem.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

public class ZKUtil {

    private final static Logger logger = LoggerFactory.getLogger(ZKUtil.class);

    public static synchronized String getZkUrl() {
        String zkUrl= YamlConfiguration.instance().getString("spring.configCenter.zkurl","localhost:2181");

        return zkUrl;
    }
}
