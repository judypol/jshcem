/* ========================================
 * System Name　　：化交线上平台
 * SubSystem Name ：化交站点核心工具集
 * File Name: Constants
 * ----------------------------------------
 * Create Date/Change History
 * ----------------------------------------
 * 2017/8/23 　lizhihua   Create
 *
 *
 * ----------------------------------------
 * Copyright (c) SCEM . All rights reserved.
 */
package com.shcem.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.shcem.common.PropertyUtil;
import com.shcem.common.ResponseData;
import com.shcem.constants.SystemDefine;
import com.shcem.server.model.ServerContext;
import com.shcem.utils.StringUtils;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author lizhihua
 * @version 1.0
 */
public class BaseServiceImpl {

    protected final Logger log = LoggerFactory.getLogger("service");

    /**
     * 设定ResultData
     *
     * @param code
     *            code字符： 00000， 10001
     * @param data
     *            返回数据对象
     * @param args
     *            可变追加信息，一般为错误信息
     */
    protected ResponseData setResultData(String code, Object data, String... args) {
        ResponseData rtnData=new ResponseData();
        return setResultData(rtnData,code,data,args);
    }
    protected ResponseData setResultData(ResponseData rtnData,String code, Object data, String... args) {

        if(rtnData==null){
            return new ResponseData();
        }
        // 业务类型返回结果由枚举改为从配置文件中获取
        String value = PropertyUtil.create("classpath:message.properties").getProperty(code);// PropertyConfigurer.getMessage(code);
        StringBuffer sbInfo = new StringBuffer(args.length + 1);

        if(StringUtils.isNotEmpty(value)){
            sbInfo.append(value);
        }

        for (int i = 0; i < args.length; i++) {
            sbInfo.append(args[i]);
        }
        rtnData.setCODE(code);
        rtnData.setINFO(sbInfo.toString());
        if (data == null) {
            rtnData.setDATA(null);
        } else if (data instanceof String) {
            rtnData.setDATA((String) data);
        } else {
            String retData;
            try {
                retData = JSON.toJSONStringWithDateFormat(data,"yyyy-MM-dd HH:mm:ss sss");//new JSONObject(JSON.toJSONString(data));
                rtnData.setDATA(retData);
            } catch (Exception e) {
                this.log.error("数据转换失败：" + e.getMessage());
                rtnData.setCODE(code);
                rtnData.setINFO(value);
            }
        }
        return rtnData;
    }

    public static void main(String[] args) {
		/*
		 * FBanks fb = new FBanks(); fb.setBankID("我是我");
		 * fb.setBankName("name"); fb.setBeginTime("begintie");
		 * fb.setControl(new Integer(10)); fb.setEndTime("endtime");
		 * fb.setMaxAuditMoney(new BigDecimal(11.23).setScale(2,
		 * BigDecimal.ROUND_HALF_UP)); fb.setMaxPerSglTransMoney(new
		 * BigDecimal(12.34).setScale(2, BigDecimal.ROUND_HALF_UP));
		 * fb.setMaxPerTransCount(new Integer(13)); fb.setMaxPerTransMoney(new
		 * BigDecimal(14.56).setScale(2, BigDecimal.ROUND_HALF_UP));
		 * fb.setValidFlag(new Integer(2)); BaseServiceImpl v = new
		 * BaseServiceImpl();
		 *
		 * System.err.println(v.validateBean(fb));
		 */

    }

    public String getFirmList(String params) {
        // TODO Auto-generated method stub
        return null;
    }

    protected String getUserId() {
        return ServerContext.currentContext().getMemberID();
    }

    protected String getUserName() {
        // 使用 getUserId()，为了避免改代码中的 getUserName 方法
        String tmpUserName=ServerContext.currentContext().getMemberName();
        return getUserId();
    }

    protected String getMode() {
        String mode =SystemDefine.MODE_LOCAL;
        String sysMode = System.getProperty(SystemDefine.REQUEST_MODE);

        if (sysMode != null && !sysMode.isEmpty()) {
            if (SystemDefine.MODE_LOCAL.equals(sysMode)
                    || SystemDefine.MODE_DEV.equals(sysMode)
                    || SystemDefine.MODE_TEST.equals(sysMode)
                    || SystemDefine.MODE_UAT.equals(sysMode)
                    || SystemDefine.MODE_DEPLOY.equals(sysMode)) {
                mode = sysMode;
            }
        }

        return mode;
    }

    /**
     *
     *
     * @param
     * @param
     * @return
     */
    private JSONObject setReturnData(String code, String... msg) {

        JSONObject result = new JSONObject();
        // 业务类型返回结果由枚举改为从配置文件中获取
		/*
		 * ResultCode rstCd = Enum.valueOf(ResultCode.class, "CODE" + code);
		 * ResultInfo rstInfo = Enum.valueOf(ResultInfo.class, "CODE" + code);
		 */
        String value =PropertyUtil.create(ServiceContants.MESSAGE_FILE).getProperty(code);// PropertyConfigurer.getMessage(code);
        StringBuffer sbInfo = new StringBuffer(msg.length + 1);

        if(StringUtils.isNotEmpty(value)){
            sbInfo.append(value);
        }

        for (int i = 0; i < msg.length; i++) {
            sbInfo.append(msg[i]);
        }
        result.put("CODE", code);
        result.put("INFO", sbInfo.toString());
        result.put("DATA", "none");

        return result;
    }

    /**
     * 获取参数实体
     *
     * @param str
     * @param clazz
     * @return Object
     */
    protected <T> T checkJSONObject(String str, Class<T> clazz) {
        T t=null;
        try{
            t=JSON.parseObject(str,clazz);
        }catch (Exception ex){
            setResultData("参数有误！",null);
            this.log.error("参数："+str,ex);
        }
        return t;
    }

    /**
     * 校验入参
     *
     * @param obj
     * @return ResponseData
     */
    protected ResponseData validate(Object obj) {

        Validator validator = new Validator();
        List<String> errors = new ArrayList<String>();
        List<ConstraintViolation> ret = validator.validate(obj);
        for (ConstraintViolation violation : ret) {
            errors.add(violation.getMessage());
        }

        ResponseData responseData=new ResponseData();
        if (errors.size() > 0) {
            responseData=setResultData("对象验证失败", null, JSON.toJSONString(errors));
        }

        return responseData;
    }
    /**
     * 校验入参
     *
     * @param obj
     * @return ResponseData
     */
    protected List<String> validateReqParams(Object obj) {
        Validator validator = new Validator();
        List<String> errors = new ArrayList<String>();
        if(obj==null){
            errors.add("请求模型为null");
        }
        List<ConstraintViolation> ret = validator.validate(obj);
        for (ConstraintViolation violation : ret) {
            errors.add(violation.getMessage());
        }
        return errors;
    }


    /**
     * 获取请求ID
     * @return
     */
    protected String getRequestId() {
        return ServerContext.currentContext().getRequestId();
    }
}
