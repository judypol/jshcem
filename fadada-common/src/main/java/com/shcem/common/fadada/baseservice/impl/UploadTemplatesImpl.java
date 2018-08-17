/* ========================================
 * System Name　　：    化交线上平台
 * SubSystem Name ：自贸区前台服务
 * File Name: UploadTemplatesImpl.java
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
import com.shcem.common.fadada.model.UploadTemplatesReq;
import com.shcem.common.fadada.model.UploadTemplatesRes;

/**
 * UploadTemplatesImpl.java
 *
 * @author 池永
 * @version 1.0
 * @description xxx
 */
public class UploadTemplatesImpl extends FadadaBaseServiceClient
        implements FadadaBaseService<UploadTemplatesReq, UploadTemplatesRes> {

    /**
     * 合同模板传输接口
     *
     * @param req
     * @return
     */
    public UploadTemplatesRes request(UploadTemplatesReq req, String mode) {

        // 取得ClientBase
        getClientBase(mode, FadadaConstants.DIFFER_BASE);

        // 初期化参数(必须)
        UploadTemplatesRes res = new UploadTemplatesRes();

        if (this.base == null) {

            // 判断连接
            res.setResult(FadadaConstants.ERROR);
            res.setCode(FadadaConstants.CODE_BASEERROR);
        } else {
            // 执行请求
            String response = this.base.invokeUploadTemplate(req.getTemplate_id(), req.getFile(), req.getDoc_url());
            res = JSON.parseObject(response, UploadTemplatesRes.class);
        }

        return res;
    }
}
