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
import org.slf4j.LoggerFactory;
import ch.qos.logback.classic.LoggerContext;
import java.io.File;
import java.io.IOException;

/**
 * @author lizhihua
 * @version 1.0
 */
public class LogbackConfigLoader {
    public static void load (String externalConfigFileLocation) throws IOException, JoranException {
        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();

        File externalConfigFile = new File(externalConfigFileLocation);
        if(!externalConfigFile.exists()){
            throw new IOException("Logback External Config File Parameter does not reference a file that exists");
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
                    //StatusPrinter.printInCaseOfErrorsOrWarnings(lc);
                }
            }
        }
    }
}
