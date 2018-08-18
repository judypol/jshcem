/* ========================================
 * System Name　　：    化交线上平台
 * SubSystem Name ：自贸区前台服务
 * File Name: SyncCompanyAutoReq.java
 * ----------------------------------------
 * Create Date/Change History 
 * ----------------------------------------
 * 2018年2月1日 　池永   Create
 * 
 * 
 * ----------------------------------------
 * Copyright (c) SCEM . All rights reserved.
 */
package com.shcem.common.fadada.model;

/**
 * SyncCompanyAutoReq.java
 * 
 * @author 池永
 * @version 1.0
 * @description xxx
 */
public class SyncCompanyAutoReq extends BaseReq {

	// 企业名称
	private String customer_name = "";

	// 电子邮箱
	private String email = "";

	// 组织机构代码证
	private String id_card = "";

	// 手机号码
	private String mobile = "";

	/**
	 * @return the customer_name
	 */
	public String getCustomer_name() {
		return customer_name;
	}

	/**
	 * @param customer_name
	 *            the customer_name to set
	 */
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
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
	 * @return the id_card
	 */
	public String getId_card() {
		return id_card;
	}

	/**
	 * @param id_card
	 *            the id_card to set
	 */
	public void setId_card(String id_card) {
		this.id_card = id_card;
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
