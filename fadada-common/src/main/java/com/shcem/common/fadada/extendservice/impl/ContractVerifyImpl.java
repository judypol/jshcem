/* ========================================
 * System Name　　：    化交线上平台
 * SubSystem Name ：自贸区前台服务
 * File Name: ContractVerifyImpl.java
 * ----------------------------------------
 * Create Date/Change History
 * ----------------------------------------
 * 2018年1月11日 　池永   Create
 *
 *
 * ----------------------------------------
 * Copyright (c) SCEM . All rights reserved.
 */
package com.shcem.common.fadada.extendservice.impl;

import com.alibaba.fastjson.JSON;
import com.shcem.common.fadada.model.ContractVerifyReq;
import com.shcem.common.fadada.model.ContractVerifyRes;
import com.shcem.common.fadada.FadadaBaseService;
import com.shcem.common.fadada.FadadaBaseServiceClient;
import com.shcem.common.fadada.FadadaConstants;

/**
 * ContractVerifyImpl.java
 *
 * @author 池永
 * @version 1.0
 * @description xxx
 */
public class ContractVerifyImpl extends FadadaBaseServiceClient implements FadadaBaseService<ContractVerifyReq, ContractVerifyRes> {

    /**
     * 文档验签接口
     *
     * @param req
     * @return
     */
    public ContractVerifyRes request(ContractVerifyReq req, String mode) {

        // 取得ClientBase
        getClientBase(mode, FadadaConstants.DIFFER_EXTRA);

        // 初期化参数(必须)
        ContractVerifyRes res = new ContractVerifyRes();

        if (this.extra == null) {

            // 判断连接
            res.setResult(FadadaConstants.ERROR);
            res.setCode(FadadaConstants.CODE_BASEERROR);
        } else {

            // 执行请求
            String response = this.extra.invokeContractVerify(req.getDoc_url(), req.getFile());

            res = JSON.parseObject(response, ContractVerifyRes.class);
        }

        return res;
    }
}
