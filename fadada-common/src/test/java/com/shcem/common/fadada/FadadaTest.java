package com.shcem.common.fadada;

import com.alibaba.fastjson.JSON;
import com.shcem.common.fadada.model.*;
import org.junit.Test;

import java.io.File;

public class FadadaTest {
    /**
     * 企业CA申请接口
     */
    @Test
    public void testSyncCompanyAuto() throws Exception {

        SyncCompanyAutoReq req = new SyncCompanyAutoReq();
        SyncCompanyAutoRes res = null;

        req.setCustomer_name("Test Company3333");
        req.setEmail("3333test@shcem.com");
        req.setId_card("123456789");
        req.setMobile("13663336789");

        FadadaBaseService svc = FadadaFactory.getInstantce(FadadaConstants.IF_SYNC_COMPANY_AUTO);

        if (svc != null) {
            res = (SyncCompanyAutoRes) svc.request(req, "test");
        }

        System.out.println(JSON.toJSONString(res));
    }

    /**
     * 个人CA申请接口
     */
    @Test
    public void testSyncPersonAuto() throws Exception {

        SyncPersonAutoReq req = new SyncPersonAutoReq();
        SyncPersonAutoRes res = null;

        req.setCustomer_name("Test Company22222");
        req.setEmail("111test@shcem.com");
        req.setId_card("310226197906301117");
        req.setIdent_type(FadadaConstants.TYPE_IDENTICARD);
        req.setId_mobile("13666666666");

        FadadaBaseService svc = FadadaFactory.getInstantce(FadadaConstants.IF_SYNC_PERSION_AUTO);

        if (svc != null) {
            res = (SyncPersonAutoRes) svc.request(req, "test");
        }

        System.out.println(JSON.toJSONString(res));
    }

    /**
     * 文档传输接口
     */
    @Test
    public void testUploaddocs() throws Exception {

        UploaddocsReq req = new UploaddocsReq();
        UploaddocsRes res = null;

        req.setContract_id("contract");
        req.setDoc_title("测试合同");
        req.setFile(new File("E://template.pdf"));
        req.setDoc_url(null);
        req.setDoc_type(FadadaConstants.DOC_TYPE);

        FadadaBaseService svc = FadadaFactory.getInstantce(FadadaConstants.IF_UPLOAD_DOCS);

        if (svc != null) {
            res = (UploaddocsRes) svc.request(req, "test");
        }

        System.out.println(JSON.toJSONString(res));
    }

    /**
     * 模板传输接口
     */
    @Test
    public void testUploadTemplates() throws Exception {

        UploadTemplatesReq req = new UploadTemplatesReq();
        UploadTemplatesRes res = null;

        req.setTemplate_id("test1001");
        req.setFile(new File("E://template.pdf"));
        req.setDoc_url(null);

        FadadaBaseService svc = FadadaFactory.getInstantce(FadadaConstants.IF_UPLOAD_TEMPLATES);

        if (svc != null) {
            res = (UploadTemplatesRes) svc.request(req, "test");
        }

        System.out.println(JSON.toJSONString(res));
    }

    /**
     * 签署合同（手动）
     */
    @Test
    public void testExtSign() throws Exception {

        ExtSignReq req = new ExtSignReq();
        ExtSignRes res = null;

        req.setTransaction_id("ttt333333");
        req.setContract_id("contract");
        req.setCustomer_id("264EE52A7183A3A680E96A9B048177C1");
        req.setDoc_title("测试合同");
        req.setSign_keyword("委托方签字");
        req.setKeyword_strategy("2");
        req.setReturn_url("https://www.shcem.com");

        FadadaBaseService svc = FadadaFactory.getInstantce(FadadaConstants.IF_EXT_SIGN);

        if (svc != null) {
            res = (ExtSignRes) svc.request(req, "test");
        }

        System.out.println(JSON.toJSONString(res));
    }

    /**
     * 签署合同（自动）
     */
    @Test
    public void testExtSignAuto() throws Exception {

        ExtSignAutoReq req = new ExtSignAutoReq();
        ExtSignAutoRes res = null;

        req.setTransaction_id("ttt333333");
        req.setContract_id("contract");
        req.setCustomer_id("264EE52A7183A3A680E96A9B048177C1");
        req.setClient_role("3");
        req.setDoc_title("测试合同");
        req.setPosition_type(0);
        req.setSign_keyword("委托方签字");
        req.setKeyword_strategy("2");

        FadadaBaseService svc = FadadaFactory.getInstantce(FadadaConstants.IF_EXT_SIGN_AUTO);

        if (svc != null) {
            res = (ExtSignAutoRes) svc.request(req, "test");
        }

        System.out.println(JSON.toJSONString(res));
    }

    /**
     * 个人CA申请接口客户信息修改接口
     */
    @Test
    public void testInfoChange() throws Exception {

        InfoChangeReq req = new InfoChangeReq();
        InfoChangeRes res = null;

        req.setCustomer_id("264EE52A7183A3A680E96A9B048177C1");
        req.setEmail("test1@shcem.com");
        req.setMobile("18721919791");

        FadadaBaseService svc = FadadaFactory.getInstantce(FadadaConstants.IF_INFO_CHANGE);

        if (svc != null) {
            res = (InfoChangeRes) svc.request(req, "test");
        }

        System.out.println(JSON.toJSONString(res));
    }

    /**
     * 合约企业信息修改
     */
    @Test
    public void testEditContractFirmInfo() throws Exception {
        EditContractFirmInfoReq req = new EditContractFirmInfoReq();
        EditContractFirmInfoRes res = null;

        req.setCustomer_id("264EE52A7183A3A680E96A9B048177C1");
        req.setEmail("test1@shcem.com");
        req.setMobile("18721919791");

        FadadaBaseService svc = FadadaFactory.getInstantce(FadadaConstants.IF_EDIT_CONTRACT_FIRM_INFO);

        if (svc != null) {
            res = (EditContractFirmInfoRes) svc.request(req, "test");
            //res = (InfoChangeRes) svc.request(req, "test");
        }

        System.out.println(JSON.toJSONString(res));
    }
}
