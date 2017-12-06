/* ========================================
 * System Name　　：化交线上平台
 * SubSystem Name ：化交站点核心工具集
 * File Name: Constants
 * ----------------------------------------
 * Create Date/Change History
 * ----------------------------------------
 * 2017/12/6 　lizhihua   Create
 *
 *
 * ----------------------------------------
 * Copyright (c) SCEM . All rights reserved.
 */
package unitTest;

import com.shcem.common.YamlConfiguration;
import org.junit.Test;

import java.util.Date;

/**
 * @author lizhihua
 * @version 1.0
 */
public class YamlConfigTest {
    @Test
    public void YamlConfig(){
        int redisModel= YamlConfiguration.instance().getInt("redis.model");
        System.out.println(redisModel);

        Date date=YamlConfiguration.instance().getDate("birthday","yyyy-MM-dd HH:mm:ss");
        System.out.println(date);
    }
}
