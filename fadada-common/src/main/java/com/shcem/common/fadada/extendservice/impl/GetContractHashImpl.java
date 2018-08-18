/* ========================================
 * System Name　　：    化交线上平台
 * SubSystem Name ：自贸区前台服务
 * File Name: GetContractHashImpl.java
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
import com.shcem.common.fadada.model.GetContractHashReq;
import com.shcem.common.fadada.model.GetContractHashRes;
import com.shcem.common.fadada.FadadaBaseService;
import com.shcem.common.fadada.FadadaBaseServiceClient;
import com.shcem.common.fadada.FadadaConstants;

/**
 * GetContractHashImpl.java
 *
 * @author 池永
 * @version 1.0
 * @description xxx
 */
public class GetContractHashImpl extends FadadaBaseServiceClient implements FadadaBaseService<GetContractHashReq, GetContractHashRes> {

    /**
     * 查询合同HASH值接口
     *
     * @param req
     * @return
     */
    public GetContractHashRes request(GetContractHashReq req, String mode) {

        // 取得ClientBase
        getClientBase(mode, FadadaConstants.DIFFER_EXTRA);

        // 初期化参数(必须)
        GetContractHashRes res = new GetContractHashRes();

        if (this.extra == null) {

            // 判断连接
            res.setResult(FadadaConstants.ERROR);
            res.setCode(FadadaConstants.CODE_BASEERROR);
        } else {
            // 执行请求
            String response = this.extra.invokeGetContractHash(req.getContract_id());

            res = JSON.parseObject(response, GetContractHashRes.class);
        }

        return res;
    }
}
