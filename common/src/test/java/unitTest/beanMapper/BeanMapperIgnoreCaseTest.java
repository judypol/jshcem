/* ========================================
 * System Name　　：化交线上平台
 * SubSystem Name ：化交站点核心工具集
 * File Name: Constants
 * ----------------------------------------
 * Create Date/Change History
 * ----------------------------------------
 * 2017/12/18 　lizhihua   Create
 *
 *
 * ----------------------------------------
 * Copyright (c) SCEM . All rights reserved.
 */
package unitTest.beanMapper;

import com.alibaba.fastjson.JSON;
import com.shcem.mapper.BeanMapperIgnoreCase;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author lizhihua
 * @version 1.0
 */
public class BeanMapperIgnoreCaseTest {
    @Test
    public void mapperTest(){
        Bean1 bean1=new Bean1();
        bean1.setF1("test");
        bean1.setF2(2);
        bean1.setF3(Integer.valueOf(3));
        bean1.setF4(new Date());
        bean1.setF5(BigDecimal.valueOf(3.0612345));
        bean1.setChecked(true);
        bean1.setPis("test".getBytes());

        Bean2 bean2= BeanMapperIgnoreCase.map(bean1,Bean2.class);

        System.out.println(JSON.toJSONStringWithDateFormat(bean2,"yyyy-MM-dd HH:mm:ss"));
    }
}
