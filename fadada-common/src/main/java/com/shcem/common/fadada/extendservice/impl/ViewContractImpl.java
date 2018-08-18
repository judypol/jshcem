/* ========================================
 * System Name　　：    化交线上平台
 * SubSystem Name ：自贸区前台服务
 * File Name: ViewContractImpl.java
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

import com.shcem.common.fadada.model.ViewContractReq;
import com.shcem.common.fadada.model.ViewContractRes;
import com.shcem.common.fadada.FadadaBaseService;
import com.shcem.common.fadada.FadadaBaseServiceClient;
import com.shcem.common.fadada.FadadaConstants;

/**
 * ViewContractImpl.java
 * 
 * @author 池永
 * @version 1.0
 * @description xxx
 */
public class ViewContractImpl extends FadadaBaseServiceClient implements FadadaBaseService<ViewContractReq, ViewContractRes> {

	/**
	 * 文档查看接口
	 * 
	 * @param req
	 * @return
	 */
	public ViewContractRes request(ViewContractReq req, String mode) {

		// 取得ClientBase
		getClientBase(mode, FadadaConstants.DIFFER_EXTRA);

		// 初期化参数(必须)
		ViewContractRes res = new ViewContractRes();

		if (this.extra == null) {

			// 判断连接
			res.setResult(FadadaConstants.ERROR);
			res.setCode(FadadaConstants.CODE_BASEERROR);
		} else {

			String view_url = null;
			try {
				// 执行请求
				view_url = this.extra.invokeViewPdfURL(req.getContract_id());
			} catch (Exception e1) {
				// TODO
				e1.printStackTrace();
			}
			
			if (view_url == null) {
				res.setResult(FadadaConstants.ERROR);
				res.setCode(FadadaConstants.CODE_EXCEPTION);
			} else {
				// 判断连接
				res.setResult(FadadaConstants.SUCCESS);
				res.setCode(FadadaConstants.CODE_SUCCESS);
				res.setUrl(view_url);
			}
		}

		return res;
	}
}
