/* ========================================
 * System Name　　：    化交线上平台
 * SubSystem Name ：自贸区前台服务
 * File Name: ExtSignImpl.java
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

import com.shcem.common.fadada.FadadaBaseService;
import com.shcem.common.fadada.FadadaBaseServiceClient;
import com.shcem.common.fadada.FadadaConstants;
import com.shcem.common.fadada.model.ExtSignReq;
import com.shcem.common.fadada.model.ExtSignRes;

/**
 * ExtSignImpl.java
 *
 * @author 池永
 * @version 1.0
 * @description xxx
 */
public class ExtSignImpl extends FadadaBaseServiceClient implements FadadaBaseService<ExtSignReq, ExtSignRes> {

    /**
     * 文档签署接口（手动签）
     *
     * @param req
     * @return
     */
    public ExtSignRes request(ExtSignReq req, String mode) throws Exception {

        // 取得ClientBase
        getClientBase(mode, FadadaConstants.DIFFER_BASE);

        // 初期化参数(必须)
        ExtSignRes res = new ExtSignRes();

        if (this.base == null) {

            // 判断连接
            res.setResult(FadadaConstants.ERROR);
            res.setCode(FadadaConstants.CODE_BASEERROR);
        } else {

            String sign_url = null;
            // 执行请求
            sign_url = this.base.invokeExtSign(req.getTransaction_id(), req.getCustomer_id(), req.getContract_id(),
                    req.getDoc_title(), req.getSign_keyword(), req.getKeyword_strategy(), req.getReturn_url(),
                    req.getNotify_url());

            if (sign_url == null||sign_url.isEmpty()) {
                res.setResult(FadadaConstants.ERROR);
                res.setCode(FadadaConstants.CODE_EXCEPTION);
            } else {
                // 判断连接
                res.setResult(FadadaConstants.SUCCESS);
                res.setCode(FadadaConstants.CODE_SUCCESS);
                res.setUrl(sign_url);
            }
        }

        return res;
    }
}
