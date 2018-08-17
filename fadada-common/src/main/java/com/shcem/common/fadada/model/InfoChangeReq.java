/* ========================================
 * System Name　　：    化交线上平台
 * SubSystem Name ：自贸区前台服务
 * File Name: SyncPersonAutoReq.java
 * ----------------------------------------
 * Create Date/Change History 
 * ----------------------------------------
 * 2018年1月11日 　池永   Create
 * 
 * 
 * ----------------------------------------
 * Copyright (c) SCEM . All rights reserved.
 */
package com.shcem.common.fadada.model;

/**
 * InfoChangeReq.java
 * 
 * @author 池永
 * @version 1.0
 * @description xxx
 */
public class InfoChangeReq extends BaseReq {

	// 客户id
	private String customer_id = "";

	// 电子邮箱
	private String email = "";

	// 手机号码
	private String mobile = "";

	/**
	 * @return the customer_id
	 */
	public String getCustomer_id() {
		return customer_id;
	}

	/**
	 * @param customer_id
	 *            the customer_id to set
	 */
	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile
	 *            the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

}
