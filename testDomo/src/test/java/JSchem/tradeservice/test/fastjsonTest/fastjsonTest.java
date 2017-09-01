/* ========================================
 * System Name　　：化交线上平台
 * SubSystem Name ：化交站点核心工具集
 * File Name: Constants
 * ----------------------------------------
 * Create Date/Change History
 * ----------------------------------------
 * 2017/9/1 　lizhihua   Create
 *
 *
 * ----------------------------------------
 * Copyright (c) SCEM . All rights reserved.
 */
package JSchem.tradeservice.test.fastjsonTest;


import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.Date;

/**
 * @author lizhihua
 * @version 1.0
 */
public class fastjsonTest {
    @Test
    public void WithFieldName(){
        String flag= com.alibaba.fastjson.util.IOUtils.getStringProperty("fastjson.compatibleWithFieldName");

        System.out.println(flag);
    }
    @Test
    public void jsonStudentModel(){
        StudentModel model=new StudentModel();
        model.setAge(25);
        model.setBirthday(new Date());
        model.setGrade("1班");
        model.setId("ydswe12345");
        model.setName("李四");

        String json= JSON.toJSONString(model);

        System.out.println(json);
    }
}
