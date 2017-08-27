/* ========================================
 * System Name　　：    化交线上平台
 * SubSystem Name ：化交服务核心工具集
 * File Name: UserParams
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
 * UserParams
 * 
 * @author chiyong
 * @version 1.0
 */
public class UserParams {
	
	private String id = "";
	private String firstName = "";
	private String lastName = "";
	private String email = "";
	private String password = "";

	public JSONObject getJsonParams() {
		
		JSONObject jsonParm = new JSONObject();
		jsonParm.put("id", id);
		jsonParm.put("firstName", firstName);
		jsonParm.put("lastName", lastName);
		jsonParm.put("email", email);
		jsonParm.put("password", password);

		return jsonParm;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
