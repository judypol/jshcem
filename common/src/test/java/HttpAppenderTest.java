/* ========================================
 * System Name　　：化交线上平台
 * SubSystem Name ：化交站点核心工具集
 * File Name: Constants
 * ----------------------------------------
 * Create Date/Change History
 * ----------------------------------------
 * 2017/8/31 　lizhihua   Create
 *
 *
 * ----------------------------------------
 * Copyright (c) SCEM . All rights reserved.
 */

import com.shcem.common.YamlConfiguration;
import com.shcem.logback.LogbackConfigLoader;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author lizhihua
 * @version 1.0
 */
public class HttpAppenderTest {
    @Before
    public void before(){
        try{
            String logback= YamlConfiguration.instance().getString("logback");
            LogbackConfigLoader.load(logback);
        }catch (Exception ex){
            System.out.println(ex);
        }
    }
    @Test
    public void logbackTest() throws  Exception{

        for (int i=0;i<60;i++){
            Logger logger= LoggerFactory.getLogger("controller");
            logger.info("base test");
        }
        Thread.sleep(5000);
    }
}
