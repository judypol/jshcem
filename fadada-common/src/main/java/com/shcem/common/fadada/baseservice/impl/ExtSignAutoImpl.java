/* ========================================
 * System Name　　：    化交线上平台
 * SubSystem Name ：自贸区前台服务
 * File Name: ExtSignAutoImpl.java
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
import com.shcem.common.fadada.model.ExtSignAutoReq;
import com.shcem.common.fadada.model.ExtSignAutoRes;

/**
 * ExtSignAutoImpl.java
 *
 * @author 池永
 * @version 1.0
 * @description xxx
 */
public class ExtSignAutoImpl extends FadadaBaseServiceClient
        implements FadadaBaseService<ExtSignAutoReq, ExtSignAutoRes> {

    /**
     * 文档签署接口（自动签）
     *
     * @param req
     * @return
     */
    public ExtSignAutoRes request(ExtSignAutoReq req, String mode) throws Exception {

        // 取得ClientBase
        getClientBase(mode, FadadaConstants.DIFFER_BASE);

        // 初期化参数(必须)
        ExtSignAutoRes res = new ExtSignAutoRes();

        if (this.base == null) {

            // 判断连接
            res.setResult(FadadaConstants.ERROR);
            res.setCode(FadadaConstants.CODE_BASEERROR);
        } else {
            // 执行请求
            String response = null;

            if (FadadaConstants.LOC_XY == req.getPosition_type()) {
                response = this.base.invokeExtSignAutoXY(req.getTransaction_id(), req.getCustomer_id(), req.getClient_role(),
                        req.getContract_id(), req.getDoc_title(), req.getSignature_positions(),
                        req.getNotify_url());
            } else {
                response = this.base.invokeExtSignAuto(req.getTransaction_id(), req.getCustomer_id(), req.getClient_role(),
                        req.getContract_id(), req.getDoc_title(), req.getSign_keyword(), req.getKeyword_strategy(),
                        req.getNotify_url());
            }

			res= JSON.parseObject(response,ExtSignAutoRes.class);
        }

        return res;
    }
}
