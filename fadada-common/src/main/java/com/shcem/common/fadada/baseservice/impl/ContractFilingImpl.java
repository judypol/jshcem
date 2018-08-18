/* ========================================
 * System Name　　：    化交线上平台
 * SubSystem Name ：自贸区前台服务
 * File Name: ContractFilingImpl.java
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
import com.shcem.common.fadada.model.ContractFilingReq;
import com.shcem.common.fadada.model.ContractFilingRes;

/**
 * ContractFilingImpl.java
 * 
 * @author 池永
 * @version 1.0
 * @description xxx
 */
public class ContractFilingImpl extends FadadaBaseServiceClient
		implements FadadaBaseService<ContractFilingReq, ContractFilingRes> {

	/**
	 * 合同归档接口
	 * 
	 * @param req
	 * @return
	 */
	public ContractFilingRes request(ContractFilingReq req, String mode) throws Exception {

		// 取得ClientBase
		getClientBase(mode, FadadaConstants.DIFFER_BASE);

		// 初期化参数(必须)
		ContractFilingRes res = new ContractFilingRes();

		if (this.base == null) {

			// 判断连接
			res.setResult(FadadaConstants.ERROR);
			res.setCode(FadadaConstants.CODE_BASEERROR);
		} else {
			// 执行请求
			String response = this.base.invokeContractFilling(req.getContract_id());
			res= JSON.parseObject(response,ContractFilingRes.class);
		}

		return res;
	}
}
