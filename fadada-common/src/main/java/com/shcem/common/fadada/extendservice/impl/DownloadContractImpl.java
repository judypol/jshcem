/* ========================================
 * System Name　　：    化交线上平台
 * SubSystem Name ：自贸区前台服务
 * File Name: DownloadContractImpl.java
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

import com.shcem.common.fadada.model.DownloadContractReq;
import com.shcem.common.fadada.model.DownloadContractRes;
import com.shcem.common.fadada.FadadaBaseService;
import com.shcem.common.fadada.FadadaBaseServiceClient;
import com.shcem.common.fadada.FadadaConstants;

/**
 * DownloadContractImpl.java
 * 
 * @author 池永
 * @version 1.0
 * @description xxx
 */
public class DownloadContractImpl extends FadadaBaseServiceClient implements FadadaBaseService<DownloadContractReq, DownloadContractRes> {

	/**
	 * 文档下载接口
	 * 
	 * @param req
	 * @return
	 */
	public DownloadContractRes request(DownloadContractReq req, String mode) {

		// 取得ClientBase
		getClientBase(mode, FadadaConstants.DIFFER_EXTRA);

		// 初期化参数(必须)
		DownloadContractRes res = new DownloadContractRes();

		if (this.extra == null) {

			// 判断连接
			res.setResult(FadadaConstants.ERROR);
			res.setCode(FadadaConstants.CODE_BASEERROR);
		} else {

			String download_url = null;
			try {
				// 执行请求
				download_url = this.extra.invokeDownloadPdf(req.getContract_id());
			} catch (Exception e1) {
				// TODO
				e1.printStackTrace();
			}
			
			if (download_url == null) {
				res.setResult(FadadaConstants.ERROR);
				res.setCode(FadadaConstants.CODE_EXCEPTION);
			} else {
				// 判断连接
				res.setResult(FadadaConstants.SUCCESS);
				res.setCode(FadadaConstants.CODE_SUCCESS);
				res.setUrl(download_url);
			}
		}

		return res;
	}
}
