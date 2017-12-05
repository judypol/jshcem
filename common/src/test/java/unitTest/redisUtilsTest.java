/* ========================================
 * System Name　　：化交线上平台
 * SubSystem Name ：化交站点核心工具集
 * File Name: Constants
 * ----------------------------------------
 * Create Date/Change History
 * ----------------------------------------
 * 2017/12/4 　lizhihua   Create
 *
 *
 * ----------------------------------------
 * Copyright (c) SCEM . All rights reserved.
 */
package unitTest;

import com.shcem.common.RedisCacheManager;
import org.junit.Test;

/**
 * @author lizhihua
 * @version 1.0
 */
public class redisUtilsTest {
    @Test
    public  void setValueWithExpire() throws Exception{
        RedisCacheManager.GetRedisCache().SetValue("test1","test",180);
        Thread.sleep(5000);
        RedisCacheManager.GetRedisCache().SetValue("test1","update");
    }

}
