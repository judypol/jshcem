/* ========================================
 * System Name　　：化交线上平台
 * SubSystem Name ：化交站点核心工具集
 * File Name: Constants
 * ----------------------------------------
 * Create Date/Change History
 * ----------------------------------------
 * 2017/9/8 　lizhihua   Create
 *
 *
 * ----------------------------------------
 * Copyright (c) SCEM . All rights reserved.
 */
package mssql;

import JShcem.Trade.TradeserviceApplication;
import JShcem.Trade.service.impl.HelloService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author lizhihua
 * @version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TradeserviceApplication.class)
public class MySqlTest {
    @Autowired
    private HelloService helloService;
    @Test
    public void setHelloService(){
        String h=helloService.sayAllHello();

        System.out.println(h);
    }
}
