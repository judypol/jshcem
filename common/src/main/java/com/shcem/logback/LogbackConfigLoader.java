/* ========================================
 * System Name　　：化交线上平台
 * SubSystem Name ：化交站点核心工具集
 * File Name: Constants
 * ----------------------------------------
 * Create Date/Change History
 * ----------------------------------------
 * 2017/9/5 　lizhihua   Create
 *
 *
 * ----------------------------------------
 * Copyright (c) SCEM . All rights reserved.
 */
package com.shcem.logback;

import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import com.shcem.common.YamlConfiguration;
import com.shcem.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ch.qos.logback.classic.LoggerContext;
import java.io.File;
import java.io.IOException;

/**
 * logback 读取外部系统的xml配置
 * @author lizhihua
 * @version 1.0
 */
public class LogbackConfigLoader {
    private static Logger logger=LoggerFactory.getLogger(LogbackConfigLoader.class);
    public static void load () throws IOException, JoranException {
        String externalConfigFileLocation= YamlConfiguration.instance().getString("logback");
        if(StringUtils.isEmpty(externalConfigFileLocation)){        //如果没有配置，则直接返回
            return;
        }
        logger.info("logback xml is "+externalConfigFileLocation);

        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();

        File externalConfigFile = new File(externalConfigFileLocation);
        if(!externalConfigFile.exists()){
            return;
        }else{
            if(!externalConfigFile.isFile()){
                throw new IOException("Logback External Config File Parameter exists, but does not reference a file");
            }else{
                if(!externalConfigFile.canRead()){
                    throw new IOException("Logback External Config File exists and is a file, but cannot be read.");
                }else{
                    JoranConfigurator configurator = new JoranConfigurator();
                    configurator.setContext(lc);
                    lc.reset();
                    configurator.doConfigure(externalConfigFileLocation);
                }
            }
        }
    }
}
