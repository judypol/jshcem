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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.shcem.redis.RedisCacheManager;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author lizhihua
 * @version 1.0
 */
public class redisUtilsTest {
    @Test
    public  void setValueWithExpire() throws Exception{
        RedisCacheManager.GetRedisCache().SetValue("test1","test",18000);
        Thread.sleep(5000);
        RedisCacheManager.GetRedisCache().SetValue("test1","update");

        System.out.println(RedisCacheManager.GetRedisCache().Get("15000426301"));
    }
    @Test
    public void findKeys() throws Exception{
        Set<String> keys=RedisCacheManager.GetRedisCache().FindKeys("15000426301");
        System.out.println(JSON.toJSONString(keys));
    }
    @Test
    public void findByContents(){
        Set<String> keys=RedisCacheManager.GetRedisCache().FindKeysByStringContent("0210244");
        System.out.println(JSON.toJSONString(keys));
    }
    @Test
    public void getListValue() throws Exception{
        List<String> names=new ArrayList<>();
        names.add("1");
        names.add("2");
        RedisCacheManager.GetRedisCache().SetValue("list",names);
        List<String> newNames=RedisCacheManager.GetRedisCache().Get("list", new TypeReference<List<String>>(){});
        System.out.println(JSON.toJSONString(newNames));
    }
}