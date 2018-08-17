/* ========================================
 * System Name　　：    化交线上平台
 * SubSystem Name ：自贸区前台服务
 * File Name: SyncCompanyAutoImpl.java
 * ----------------------------------------
 * Create Date/Change History
 * ----------------------------------------
 * 2018年2月1日 　池永   Create
 *
 *
 * ----------------------------------------
 * Copyright (c) SCEM . All rights reserved.
 */
package com.shcem.common.fadada.companyservice.impl;

import com.alibaba.fastjson.JSON;
import com.shcem.common.fadada.model.SyncCompanyAutoReq;
import com.shcem.common.fadada.model.SyncCompanyAutoRes;
import com.shcem.common.fadada.FadadaBaseService;
import com.shcem.common.fadada.FadadaBaseServiceClient;
import com.shcem.common.fadada.FadadaConstants;

/**
 * SyncCompanyAutoImpl.java
 *
 * @author 池永
 * @version 1.0
 * @description xxx
 */
public class SyncCompanyAutoImpl extends FadadaBaseServiceClient
        implements FadadaBaseService<SyncCompanyAutoReq, SyncCompanyAutoRes> {

    /**
     * 企业CA申请
     *
     * @param req
     * @return
     */
    public SyncCompanyAutoRes request(SyncCompanyAutoReq req, String mode) {

        // 取得ClientBase
        getClientBase(mode, FadadaConstants.DIFFER_COMPANY);

        // 初期化参数(必须)
        SyncCompanyAutoRes res = new SyncCompanyAutoRes();

        if (this.company == null) {

            // 判断连接
            res.setResult(FadadaConstants.ERROR);
            res.setCode(FadadaConstants.CODE_BASEERROR);
        } else {

            // 执行请求
            String response = this.company.invoke(req.getCustomer_name(), req.getEmail(), req.getId_card(),
                    req.getMobile());

            res = JSON.parseObject(response, SyncCompanyAutoRes.class);
        }

        return res;
    }
}
