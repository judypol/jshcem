package com.shcem.common.fadada.extendservice.impl;

import com.alibaba.fastjson.JSON;
import com.shcem.common.fadada.model.EditContractFirmInfoReq;
import com.shcem.common.fadada.model.EditContractFirmInfoRes;
import com.shcem.common.fadada.FadadaBaseService;
import com.shcem.common.fadada.FadadaBaseServiceClient;
import com.shcem.common.fadada.FadadaConstants;

public class EditContractFirmInfoImpl extends FadadaBaseServiceClient implements FadadaBaseService<EditContractFirmInfoReq, EditContractFirmInfoRes> {

    private String clsName = EditContractFirmInfoImpl.class.getName();

    @Override
    public EditContractFirmInfoRes request(EditContractFirmInfoReq req, String mode) {

        // 取得ClientBase
        getClientBase(mode, FadadaConstants.DIFFER_EXTRA);

        // 初期化参数(必须)
        EditContractFirmInfoRes res = new EditContractFirmInfoRes();

        if (this.extra == null) {

            // 判断连接
            res.setResult(FadadaConstants.ERROR);
            res.setCode(FadadaConstants.CODE_BASEERROR);
        } else {
            String response = this.extra.invokeInfoChange(req.getCustomer_id(), req.getEmail(), req.getMobile());
            res = JSON.parseObject(response, EditContractFirmInfoRes.class);
        }
        return res;
    }
}
