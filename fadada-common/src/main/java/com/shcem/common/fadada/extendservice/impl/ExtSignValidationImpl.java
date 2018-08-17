/* ========================================
 * System Name　　：    化交线上平台
 * SubSystem Name ：自贸区前台服务
 * File Name: ExtSignValidationImpl.java
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

import com.shcem.common.fadada.model.ExtSignValidationReq;
import com.shcem.common.fadada.model.ExtSignValidationRes;
import com.shcem.common.fadada.FadadaBaseService;
import com.shcem.common.fadada.FadadaBaseServiceClient;
import com.shcem.common.fadada.FadadaConstants;

/**
 * ExtSignValidationImpl.java
 * 
 * @author 池永
 * @version 1.0
 * @description xxx
 */
public class ExtSignValidationImpl extends FadadaBaseServiceClient
		implements FadadaBaseService<ExtSignValidationReq, ExtSignValidationRes> {

	/**
	 * 文档签署接口（含有效期和次数限制）
	 * 
	 * @param req
	 * @return
	 */
	public ExtSignValidationRes request(ExtSignValidationReq req, String mode) {

		// 取得ClientBase
		getClientBase(mode, FadadaConstants.DIFFER_EXTRA);

		// 初期化参数(必须)
		ExtSignValidationRes res = new ExtSignValidationRes();

		if (this.extra == null) {

			// 判断连接
			res.setResult(FadadaConstants.ERROR);
			res.setCode(FadadaConstants.CODE_BASEERROR);
		} else {

			String sign_url = null;
			try {
				// 执行请求
				sign_url = this.extra.invokeExtSignValidation(req.getTransaction_id(), req.getCustomer_id(),
						req.getContract_id(), req.getDoc_title(), req.getSign_keyword(), req.getValidity(),
						req.getQuantity(), req.getReturn_url(), req.getNotify_url());
			} catch (Exception e1) {
				// TODO
				e1.printStackTrace();
			}

			if (sign_url == null) {
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
