/* ========================================
 * System Name　　：    化交线上平台
 * SubSystem Name ：自贸区前台服务
 * File Name: QuerySignStatusImpl.java
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
import com.shcem.common.fadada.model.QuerySignStatusReq;
import com.shcem.common.fadada.model.QuerySignStatusRes;

/**
 * QuerySignStatusImpl.java
 * 
 * @author 池永
 * @version 1.0
 * @description xxx
 */
public class QuerySignStatusImpl extends FadadaBaseServiceClient
		implements FadadaBaseService<QuerySignStatusReq, QuerySignStatusRes> {

	/**
	 * 客户签署状态查询接口（按客户编号）
	 * 
	 * @param req
	 * @return
	 */
	public QuerySignStatusRes request(QuerySignStatusReq req, String mode) {

		// 取得ClientBase
		getClientBase(mode, FadadaConstants.DIFFER_BASE);

		// 初期化参数(必须)
		QuerySignStatusRes res = new QuerySignStatusRes();

		if (this.base == null) {

			// 判断连接
			res.setResult(FadadaConstants.ERROR);
			res.setCode(FadadaConstants.CODE_BASEERROR);
		} else {

			// 执行请求
			String response = this.base.invokeQuerySignStatus(req.getContract_id(), req.getCustomer_id());

			res = JSON.parseObject(response, QuerySignStatusRes.class);
		}

		return res;
	}
}
