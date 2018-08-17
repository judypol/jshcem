/* ========================================
 * System Name　　：    化交线上平台
 * SubSystem Name ：自贸区前台服务
 * File Name: GetUrlImpl.java
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
import com.shcem.common.fadada.model.GetUrlReq;
import com.shcem.common.fadada.model.GetUrlRes;

/**
 * GetUrlImpl.java
 *
 * @author 池永
 * @version 1.0
 * @description xxx
 */
public class GetUrlImpl extends FadadaBaseServiceClient implements FadadaBaseService<GetUrlReq, GetUrlRes> {

    /**
     * 文档临时查看/下载地址接口（含有效期和次数）
     *
     * @param req
     * @return
     */
    public GetUrlRes request(GetUrlReq req, String mode) {

        // 取得ClientBase
        getClientBase(mode, FadadaConstants.DIFFER_EXTRA);

        // 初期化参数(必须)
        GetUrlRes res = new GetUrlRes();

        if (this.extra == null) {

            // 判断连接
            res.setResult(FadadaConstants.ERROR);
            res.setCode(FadadaConstants.CODE_BASEERROR);
        } else {
            // 执行请求
            String response = this.extra.invokeGetUrl(req.getContract_id(), req.getValidity(), req.getQuantity());

            res = JSON.parseObject(response, GetUrlRes.class);
        }

        return res;
    }
}
