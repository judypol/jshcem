/* ========================================
 * System Name　　：    化交线上平台
 * SubSystem Name ：化交服务核心工具集
 * File Name: User
 * ----------------------------------------
 * Create Date/Change History 
 * ----------------------------------------
 * 04/29/16 　池 永   Create
 * 
 * 
 * ----------------------------------------
 * Copyright (c) SCEM . All rights reserved.
 */
package com.shcem.activiti.model;

import com.alibaba.fastjson.JSONObject;

/**
 * User
 * 
 * @author chiyong
 * @version 1.0
 */
public class User extends UserURL {

	private String userId = "";
	private String key = "";
	private UserParams params = new UserParams();

	public JSONObject getJsonObject() {

		JSONObject jsonObj = new JSONObject();
		JSONObject jsonParm = params.getJsonParams();
		JSONObject jsonURL = getJsonURL();
		jsonObj.put("userId", userId);
		jsonObj.put("key", key);
		
		jsonObj.put("params", jsonParm);
		jsonObj.put("url", jsonURL);

		return jsonObj;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public UserParams getParams() {
		return params;
	}

	public void setParams(UserParams params) {
		this.params = params;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

}
