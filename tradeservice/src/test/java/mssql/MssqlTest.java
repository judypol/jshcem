/* ========================================
 * System Name　　：化交线上平台
 * SubSystem Name ：化交站点核心工具集
 * File Name: Constants
 * ----------------------------------------
 * Create Date/Change History
 * ----------------------------------------
 * 2017/9/7 　lizhihua   Create
 *
 *
 * ----------------------------------------
 * Copyright (c) SCEM . All rights reserved.
 */
package mssql;

import JShcem.Trade.TradeserviceApplication;
import JShcem.Trade.service.ILeadsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author lizhihua
 * @version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TradeserviceApplication.class)
public class MssqlTest {
    @Resource
    ILeadsService leadsService;
    @Test
    public void msqlLeadsTest(){
        //ILeadsService leadsService=new LeadsService();
        String leads=leadsService.getLeads();

        System.out.println(leads);
    }
    @Test
    public void mssqlLeadsList(){
        String leadsPage=leadsService.getLeadsList();

        System.out.println(leadsPage);
    }
}
