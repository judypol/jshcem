/* ========================================
 * System Name　　：    化交线上平台
 * SubSystem Name ：自贸区前台服务
 * File Name: GenerateContractImpl.java
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
import com.shcem.common.fadada.model.GenerateContractReq;
import com.shcem.common.fadada.model.GenerateContractRes;

/**
 * GenerateContractImpl.java
 *
 * @author 池永
 * @version 1.0
 * @description xxx
 */
public class GenerateContractImpl extends FadadaBaseServiceClient
        implements FadadaBaseService<GenerateContractReq, GenerateContractRes> {

    /**
     * 合同生成接口
     *
     * @param req
     * @return
     */
    public GenerateContractRes request(GenerateContractReq req, String mode) throws Exception {

        // 取得ClientBase
        getClientBase(mode, FadadaConstants.DIFFER_BASE);

        // 初期化参数(必须)
        GenerateContractRes res = new GenerateContractRes();

        if (this.base == null) {

            // 判断连接
            res.setResult(FadadaConstants.ERROR);
            res.setCode(FadadaConstants.CODE_BASEERROR);
        } else {

            // 执行请求
            String response = this.base.invokeGenerateContract(req.getTemplate_id(), req.getContract_id(),
                    req.getDoc_title(), req.getFont_size(), req.getFont_type(), req.getParameter_map(),
                    req.getDynamic_tables());
            res = JSON.parseObject(response, GenerateContractRes.class);
        }

        return res;
    }
}
