/* ========================================
 * System Name　　：    化交线上平台
 * SubSystem Name ：自贸区前台服务
 * File Name: SyncPersonAutoImpl.java
 * ----------------------------------------
 * Create Date/Change History
 * ----------------------------------------
 * 2018年1月11日 　池永   Create
 *
 *
 * ----------------------------------------
 * Copyright (c) SCEM . All rights reserved.
 */
package com.shcem.common.fadada.baseservice.impl;

import com.alibaba.fastjson.JSON;
import com.shcem.common.fadada.FadadaBaseService;
import com.shcem.common.fadada.FadadaBaseServiceClient;
import com.shcem.common.fadada.FadadaConstants;
import com.shcem.common.fadada.model.SyncPersonAutoReq;
import com.shcem.common.fadada.model.SyncPersonAutoRes;

/**
 * SyncPersonAutoImpl.java
 *
 * @author 池永
 * @version 1.0
 * @description xxx
 */
public class SyncPersonAutoImpl extends FadadaBaseServiceClient
        implements FadadaBaseService<SyncPersonAutoReq, SyncPersonAutoRes> {

    /**
     * 个人CA申请
     *
     * @param req
     * @return
     */
    public SyncPersonAutoRes request(SyncPersonAutoReq req, String mode) {

        // 取得ClientBase
        getClientBase(mode, FadadaConstants.DIFFER_BASE);

        // 初期化参数(必须)
        SyncPersonAutoRes res = new SyncPersonAutoRes();

        if (this.base == null) {

            // 判断连接
            res.setResult(FadadaConstants.ERROR);
            res.setCode(FadadaConstants.CODE_BASEERROR);
        } else {

            // 执行请求
            String response = this.base.invokeSyncPersonAuto(req.getCustomer_name(), req.getEmail(), req.getId_card(),
                    req.getIdent_type(), req.getId_mobile());
            res = JSON.parseObject(response, SyncPersonAutoRes.class);
        }

        return res;
    }
}
