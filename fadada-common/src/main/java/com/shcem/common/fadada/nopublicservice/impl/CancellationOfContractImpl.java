package com.shcem.common.fadada.nopublicservice.impl;

import com.shcem.common.fadada.model.CancellationOfContractReq;
import com.shcem.common.fadada.model.CancellationOfContractRes;
import com.alibaba.fastjson.JSON;
import com.shcem.common.fadada.FadadaBaseService;
import com.shcem.common.fadada.FadadaBaseServiceClient;
import com.shcem.common.fadada.FadadaConstants;

/**
 * CancellationOfContractImpl.java 合同撤销接口实现
 * 
 * @author wangshuai
 * @version 1.0
 * @description 合同撤销接口
 */
public class CancellationOfContractImpl extends FadadaBaseServiceClient
		implements FadadaBaseService<CancellationOfContractReq, CancellationOfContractRes> {

	@Override
	public CancellationOfContractRes request(CancellationOfContractReq k, String mode) throws Exception {

		// 取得ClientBase
		getClientBase(mode, FadadaConstants.DIFFER_NOPUBLIC);

		// 初期化参数(必须)
		CancellationOfContractRes res = new CancellationOfContractRes();

		if (this.noPublicBase == null) {

			// 判断连接
			res.setResult(FadadaConstants.ERROR);
			res.setCode(FadadaConstants.CODE_BASEERROR);
		} else {
			// 执行请求
			String response = this.noPublicBase.invokeCancellationOfContract(k.getContract_id());

			res = JSON.parseObject(response, CancellationOfContractRes.class);
		}
		return res;
	}

}
