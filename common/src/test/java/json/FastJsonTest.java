/* ========================================
 * System Name　　：化交线上平台
 * SubSystem Name ：化交站点核心工具集
 * File Name: Constants
 * ----------------------------------------
 * Create Date/Change History
 * ----------------------------------------
 * 2017/12/12 　lizhihua   Create
 *
 *
 * ----------------------------------------
 * Copyright (c) SCEM . All rights reserved.
 */
package json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * @author lizhihua
 * @version 1.0
 */
public class FastJsonTest {
    @Test
    public void bigdecimalTest(){
        Feature feature=Feature.UseBigDecimal;
        JSON.DEFAULT_PARSER_FEATURE=feature.getMask();
        BigDecimal bigDecimal1=new BigDecimal(12.0000000000000);
        BigDecimal bigDecimal2=new BigDecimal(12.0000000000000);
        BigDecimal result=bigDecimal1.subtract(bigDecimal2).setScale(10,BigDecimal.ROUND_FLOOR);
        System.out.println(result.toString());
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("price",result);
        System.out.println(jsonObject.toJSONString());
        BigDecimal bigDecimal3=new BigDecimal("0e-10");
        System.out.println(bigDecimal3.toPlainString());
    }
}
