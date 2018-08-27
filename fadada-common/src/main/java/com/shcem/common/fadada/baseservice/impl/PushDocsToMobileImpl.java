package com.shcem.common.fadada.baseservice.impl;

import com.alibaba.fastjson.JSON;
import com.shcem.common.fadada.FadadaBaseService;
import com.shcem.common.fadada.FadadaBaseServiceClient;
import com.shcem.common.fadada.FadadaConstants;
import com.shcem.common.fadada.model.PushdocsToMobileReq;
import com.shcem.common.fadada.model.PushdocsToMobileRes;

/**
 * @author guode
 *合同通知操作实现
 */
public class PushDocsToMobileImpl extends FadadaBaseServiceClient
implements FadadaBaseService<PushdocsToMobileReq, PushdocsToMobileRes> {

	@Override
	public PushdocsToMobileRes request(PushdocsToMobileReq req, String mode) throws Exception {
		// 取得ClientBase
				getClientBase(mode, FadadaConstants.DIFFER_NOPUBLIC);

				// 初期化参数(必须)
				PushdocsToMobileRes res = new PushdocsToMobileRes();

				if (this.noPublicBase == null) {

					// 判断连接
					res.setResult(FadadaConstants.ERROR);
					res.setCode(FadadaConstants.CODE_BASEERROR);
				} else {
					// 执行请求
					String response = this.noPublicBase.invokePushDocsToMobile(req.getContract_id(), req.getTransaction_id(), req.getDoc_title()
							, req.getUser_names(), req.getUser_mobiles(), req.getUser_emails(), req.getSign_keywords(), req.getExpiration_time());
					res= JSON.parseObject(response,PushdocsToMobileRes.class);
				}

				return res;
	}

	

}
