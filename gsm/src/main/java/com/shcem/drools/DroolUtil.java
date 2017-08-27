/* ========================================
 * System Name　　：    化交线上平台
 * SubSystem Name ：化交服务核心工具集
 * File Name: DroolUtil
 * ----------------------------------------
 * Create Date/Change History 
 * ----------------------------------------
 * 04/29/16 　池 永   Create
 * 
 * 
 * ----------------------------------------
 * Copyright (c) SCEM . All rights reserved.
 */
package com.shcem.drools;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.shcem.common.PropertyUtil;
import com.shcem.constants.SystemDefine;
import com.shcem.enums.ResultCode;
import com.shcem.netty.NettyClient;
import com.shcem.service.ServiceContants;

import java.util.Map;
import java.util.Properties;

public class DroolUtil {
	
	/**
	 * @param DroolsName
	 *            规则名
	 * @param DroolsDataName
	 *            规则对应的Fact对象名
	 * @param obj
	 *            规则对应的Fact对象
	 * @return
	 */
	protected String generateDroolsApiData(String DroolsName, String DroolsDataName, Object obj) {

		String postdata;
		StringBuilder sb = new StringBuilder();
		sb.append("{\"json\":");
		JSONObject svcObj = new JSONObject();
		svcObj.put("DroolsName", DroolsName);
		svcObj.put("DroolsDataName", DroolsDataName);

		JSONObject jso;
		try {
			jso = JSON.parseObject(JSON.toJSONString(obj));
		} catch (Exception e) {
			return null;
		}
		svcObj.put("DroolsDataContent", jso.toString());
		sb.append(svcObj.toString());
		sb.append("}");
		postdata = sb.toString();

		return postdata;
	}

	/**
	 * 调用规则引擎API
	 * 
	 * @param postData
	 * @return
	 */
	protected JSONObject postDroolsApi(String postData, String mode) {

//		PropertyUtil propUtil = new PropertyUtil();
		Properties clientProperty = PropertyUtil.create(ServiceContants.DROOLS_PROPERITES_FILE).getProperties();

		// 取得中央代理服务器的信息
		String ipaddress = clientProperty.getProperty(mode + "_droolsapi_IP");
		String strPort = clientProperty.getProperty("droolsapi_port");
		System.out.println("postDroolsApi_ipaddress:" + ipaddress);
		System.out.println("postDroolsApi_strPort:" + strPort);
		System.out.println("postDroolsApi_postData:" + postData);
		
		int port;
		try {
			port = Integer.parseInt(strPort);
		} catch (NumberFormatException e1) {
			// default port;
			port = 5420;
		}

		/**
		 * 取得数据 Start
		 */
		NettyClient ntc = new NettyClient(ipaddress, port, postData,"/activitiapi");
		String response = null;
		try {
			Map<String, String> m = ntc.httpPost();
			response = m.get("response");
		} catch (Exception e) {
			return setReturnData("10115", e.getMessage());
		}
		/**
		 * 取得数据 End
		 */

		// 数据转换成JSON对象
		JSONObject jsoSource;
		try {
			jsoSource = JSON.parseObject(response);
		} catch (JSONException e) {
			return setReturnData("10115", e.getMessage());
		}

		return jsoSource;
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

		ResultCode rstCd = Enum.valueOf(ResultCode.class, "CODE" + code);

		StringBuffer sbInfo = new StringBuffer(msg.length + 1);

		sbInfo.append(rstCd.getName());
		for (int i = 0; i < msg.length; i++) {
			sbInfo.append(msg[i]);
		}

		result.put("CODE", rstCd.getStringValue());
		result.put("INFO", sbInfo.toString());
		result.put("DATA", "none");

		//HessianHeaderContext.close();

		return result;
	}
}
