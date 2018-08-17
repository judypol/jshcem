/* ========================================
 * System Name　　：    化交线上平台
 * SubSystem Name ：自贸区前台服务
 * File Name: QuerySignStatusReq.java
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
 * QuerySignStatusReq.java
 * 
 * @author 池永
 * @version 1.0
 * @description xxx
 */
public class QuerySignStatusReq extends BaseReq {

	// 合同编号
	private String contract_id = "";

	// 客户编号
	private String customer_id = "";

	/**
	 * @return the contract_id
	 */
	public String getContract_id() {
		return contract_id;
	}

	/**
	 * @param contract_id
	 *            the contract_id to set
	 */
	public void setContract_id(String contract_id) {
		this.contract_id = contract_id;
	}

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

}
