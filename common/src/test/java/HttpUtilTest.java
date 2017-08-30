/* ========================================
 * System Name　　：化交线上平台
 * SubSystem Name ：化交站点核心工具集
 * File Name: Constants
 * ----------------------------------------
 * Create Date/Change History
 * ----------------------------------------
 * 2017/8/28 　lizhihua   Create
 *
 *
 * ----------------------------------------
 * Copyright (c) SCEM . All rights reserved.
 */

import com.shcem.common.HttpUtlis;
import org.junit.Test;

/**
 * @author lizhihua
 * @version 1.0
 */
public class HttpUtilTest {
    @Test
    public void getTest() throws Exception{
        String response = HttpUtlis.Instance().get("http://baidu.com");

        System.out.println(response);
    }
}
