/* ========================================
 * System Name　　：    化交线上平台
 * SubSystem Name ：自贸区前台服务
 * File Name: InfoChangeImpl.java
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
import com.shcem.common.fadada.FadadaBaseService;
import com.shcem.common.fadada.FadadaBaseServiceClient;
import com.shcem.common.fadada.FadadaConstants;
import com.shcem.common.fadada.model.InfoChangeReq;
import com.shcem.common.fadada.model.InfoChangeRes;

/**
 * InfoChangeImpl.java
 *
 * @author 池永
 * @version 1.0
 * @description xxx
 */
public class InfoChangeImpl extends FadadaBaseServiceClient implements FadadaBaseService<InfoChangeReq, InfoChangeRes> {

    /**
     * 客户信息更改
     *
     * @param req
     * @return
     */
    public InfoChangeRes request(InfoChangeReq req, String mode) {

        // 取得ClientBase
        getClientBase(mode, FadadaConstants.DIFFER_EXTRA);

        // 初期化参数(必须)
        InfoChangeRes res = new InfoChangeRes();

        if (this.extra == null) {

            // 判断连接
            res.setResult(FadadaConstants.ERROR);
            res.setCode(FadadaConstants.CODE_BASEERROR);
        } else {
            // 执行请求
            String response = this.extra.invokeInfoChange(req.getCustomer_id(), req.getEmail(), req.getMobile());

            res = JSON.parseObject(response, InfoChangeRes.class);
        }

        return res;
    }
}
