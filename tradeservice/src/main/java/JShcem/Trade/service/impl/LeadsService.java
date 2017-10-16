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
package JShcem.Trade.service.impl;

import JShcem.Trade.dao.TLeadsMapper;
import JShcem.Trade.dao.model.TLeads;
import JShcem.Trade.service.ILeadsService;
import com.alibaba.fastjson.JSON;
import com.shcem.annotation.LogHandler;
import com.shcem.common.ResponseData;
import com.shcem.enums.LoggerName;
import com.shcem.hessian.HessianService;
import com.shcem.mybatis.query.Page;
import com.shcem.mybatis.query.Pageable;
import com.shcem.service.BaseServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lizhihua
 * @version 1.0
 */
@HessianService
@Service
public class LeadsService extends BaseServiceImpl implements ILeadsService {
    @Resource()
    TLeadsMapper tLeadsMapper;
    @Override
    public String getLeads() {
        TLeads tLeads= tLeadsMapper.selectByPrimaryKey(21139L);
        return JSON.toJSONStringWithDateFormat(tLeads,"yyyy-MM-dd HH:mm:ss");
    }
    @Override
    @LogHandler(loggerName = LoggerName.Service)
    public String getLeadsList(){
        Pageable pageable=new Pageable();
        pageable.setPageSize(10);
        pageable.setPageIndex(2);

        List<TLeads> tLeadsList=tLeadsMapper.selectByPager(pageable);

        Page<TLeads> tLeadsPage=new Page<>();
        tLeadsPage.setTotalPages(pageable.getTotalPages());
        tLeadsPage.setTotalRecords(pageable.getTotalRecords());
        tLeadsPage.setContent(tLeadsList);
        tLeadsPage.setPageSize(pageable.getPageSize());
        tLeadsPage.setPageIndex(pageable.getPageIndex());

        //String page=JSON.toJSONStringWithDateFormat(tLeadsPage,"yyyy-MM-dd HH:mm:ss");
        ResponseData rtnData= setResultData("00000",tLeadsList);
        return rtnData.toString();
    }
}
