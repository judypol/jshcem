/* ========================================
 * System Name　　：化交线上平台
 * SubSystem Name ：化交站点核心工具集
 * File Name: Constants
 * ----------------------------------------
 * Create Date/Change History
 * ----------------------------------------
 * 2017/12/5 　lizhihua   Create
 *
 *
 * ----------------------------------------
 * Copyright (c) SCEM . All rights reserved.
 */
package com.shcem.configCenter.spring;

import com.shcem.common.YamlConfiguration;
import com.shcem.configCenter.factory.ConfigCenterFactory;
import com.shcem.configCenter.service.ConfigCenterService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lizhihua
 * @version 1.0
 */
public class ConfigCenterRegister {
    private Logger logger = LoggerFactory.getLogger(ConfigCenterRegister.class);
    private String configNameSpaces;
    //private List<String> configNamespaceList=new ArrayList<>();

    public ConfigCenterRegister() {
        this.configNameSpaces = YamlConfiguration.instance().getString("spring.configCenter.namespace");
        logger.debug("the configuration namespaces is "+this.configNameSpaces);
        if (StringUtils.isEmpty(this.configNameSpaces)) {
            throw new RuntimeException("cont find the spring.configCenter.namespace");
        }
        //String parentPath=configNameSpaces.substring(0,configNameSpaces.lastIndexOf('/'));
        //configNamespaceList.add(configNameSpaces);
        //configNamespaceList.add(parentPath);
    }

    @PostConstruct
    public void init() {
        //for(String namespace : this.configNamespaceList){
        this.setSystemPropertiesFromConfigCenter(this.configNameSpaces);
        //}
    }

    /**
     *
     */
    private void setSystemPropertiesFromConfigCenter(String configNameSpaces) {
        if (StringUtils.isBlank(configNameSpaces)) {
            return;
        }
        ConfigCenterFactory.getInstance().setSystemNameSpace(configNameSpaces);
        ConfigCenterService cc = ConfigCenterFactory.getInstance().getConfigCenterService(configNameSpaces);
        try {
            YamlConfiguration.instance().add(cc.getConfig());
        } catch (Exception ex) {
            logger.error("YamlConfiguration.getConfig is error", ex);
        }
    }
}
