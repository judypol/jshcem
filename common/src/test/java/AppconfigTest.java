/* ========================================
 * System Name　　：化交线上平台
 * SubSystem Name ：化交站点核心工具集
 * File Name: Constants
 * ----------------------------------------
 * Create Date/Change History
 * ----------------------------------------
 * 2017/8/17 　lizhihua   Create
 *
 *
 * ----------------------------------------
 * Copyright (c) SCEM . All rights reserved.
 */

import com.shcem.common.YamlConfiguration;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lizhihua
 * @version 1.0
 */
public class AppconfigTest {
    @Test
    public void getApplicaitonConfig() throws Exception{
        HashMap<String,Object> map= YamlConfiguration.instance().getConfig();

        for(Map.Entry<?,?> entry :map.entrySet()){
            System.out.println("key:{"+entry.getKey().toString()+"},val:{"+entry.getValue().toString()+"}");
        }
        System.out.println(map);
    }
}
