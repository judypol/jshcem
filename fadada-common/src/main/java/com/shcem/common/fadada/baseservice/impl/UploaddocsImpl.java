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
import com.shcem.common.fadada.model.UploaddocsReq;
import com.shcem.common.fadada.model.UploaddocsRes;

/**
 * SyncPersonAutoImpl.java
 *
 * @author 池永
 * @version 1.0
 * @description xxx
 */
public class UploaddocsImpl extends FadadaBaseServiceClient
        implements FadadaBaseService<UploaddocsReq, UploaddocsRes> {

    /**
     * 个人CA申请
     *
     * @param req
     * @return
     */
    public UploaddocsRes request(UploaddocsReq req, String mode) {

        // 取得ClientBase
        getClientBase(mode, FadadaConstants.DIFFER_BASE);

        // 初期化参数(必须)
        UploaddocsRes res = new UploaddocsRes();

        if (this.base == null) {

            // 判断连接
            res.setResult(FadadaConstants.ERROR);
            res.setCode(FadadaConstants.CODE_BASEERROR);
        } else {
            // 执行请求
            String response = this.base.invokeUploadDocs(req.getContract_id(), req.getDoc_title(), req.getFile(),
                    req.getDoc_url(), req.getDoc_type());

            res = JSON.parseObject(response, UploaddocsRes.class);
        }

        return res;
    }
}
