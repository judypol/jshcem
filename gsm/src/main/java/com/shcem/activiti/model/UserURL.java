/* ========================================
 * System Name　　：    化交线上平台
 * SubSystem Name ：化交服务核心工具集
 * File Name: UserURL
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
 * UserURL
 * 
 * @author chiyong
 * @version 1.0
 */
public class UserURL extends Page {
	private String id = "";
	private String firstName = "";
	private String lastName = "";
	private String email = "";
	private String firstNameLike = "";
	private String lastNameLike = "";
	private String emailLike = "";
	private String memberOfGroup = "";
	private String potentialStarter = "";

	public JSONObject getJsonURL() {

		JSONObject getJsonURL = getJsonPage();

		if (!"".equals(id))
			getJsonURL.put("id", id);
		if (!"".equals(firstName))
			getJsonURL.put("firstName", firstName);
		if (!"".equals(lastName))
			getJsonURL.put("lastName", lastName);
		if (!"".equals(email))
			getJsonURL.put("email", email);
		if (!"".equals(firstNameLike))
			getJsonURL.put("firstNameLike", firstNameLike);
		if (!"".equals(lastNameLike))
			getJsonURL.put("lastNameLike", lastNameLike);
		if (!"".equals(emailLike))
			getJsonURL.put("emailLike", emailLike);
		if (!"".equals(memberOfGroup))
			getJsonURL.put("memberOfGroup", memberOfGroup);
		if (!"".equals(potentialStarter))
			getJsonURL.put("potentialStarter)", potentialStarter);

		return getJsonURL;
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

	public String getFirstNameLike() {
		return firstNameLike;
	}

	public void setFirstNameLike(String firstNameLike) {
		this.firstNameLike = firstNameLike;
	}

	public String getLastNameLike() {
		return lastNameLike;
	}

	public void setLastNameLike(String lastNameLike) {
		this.lastNameLike = lastNameLike;
	}

	public String getEmailLike() {
		return emailLike;
	}

	public void setEmailLike(String emailLike) {
		this.emailLike = emailLike;
	}

	public String getMemberOfGroup() {
		return memberOfGroup;
	}

	public void setMemberOfGroup(String memberOfGroup) {
		this.memberOfGroup = memberOfGroup;
	}

	public String getPotentialStarter() {
		return potentialStarter;
	}

	public void setPotentialStarter(String potentialStarter) {
		this.potentialStarter = potentialStarter;
	}

}
