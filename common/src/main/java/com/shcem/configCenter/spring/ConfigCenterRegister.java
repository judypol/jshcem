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

/**
 * @author lizhihua
 * @version 1.0
 */
public class ConfigCenterRegister {
    private Logger logger= LoggerFactory.getLogger(ConfigCenterRegister.class);
    private String configNameSpaces;

    public ConfigCenterRegister() {
        configNameSpaces=YamlConfiguration.instance().getString("spring.configCenter.namespace");
        if(StringUtils.isEmpty(configNameSpaces)){
            throw new RuntimeException("cont find the spring.configCenter.namespace");
        }
    }

    @PostConstruct
    public void init() {
        if (this.configNameSpaces != null) {
            this.setSystemPropertiesFromConfigCenter();
        }
    }

    /**
     *
     */
    private void setSystemPropertiesFromConfigCenter() {
        if (StringUtils.isBlank(this.configNameSpaces)) {
            return;
        }
        ConfigCenterFactory.getInstance().setSystemNameSpace(this.configNameSpaces);
        ConfigCenterService cc = ConfigCenterFactory.getInstance().getConfigCenterService(this.configNameSpaces);
        try{
            YamlConfiguration.instance().add(cc.getConfig());
        }catch (Exception ex){
            logger.error("YamlConfiguration.getConfig is error",ex);
        }
    }
}
