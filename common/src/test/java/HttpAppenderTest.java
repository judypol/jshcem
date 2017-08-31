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

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author lizhihua
 * @version 1.0
 */
public class HttpAppenderTest {
    @Test
    public void logbackTest(){
        for (int i=0;i<20;i++){
            Logger logger= LoggerFactory.getLogger("controller");
            logger.info("base test");
        }
    }
}
