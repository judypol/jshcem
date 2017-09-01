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

/**
 * @author lizhihua
 * @version 1.0
 */
public class GetSystemEnvTest {
    @Test
    public void getJavaHomeTest(){
        String javaHome=System.getenv("GRADLE_HOME");

        System.out.println(javaHome);
    }
}
