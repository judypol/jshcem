/* ========================================
 * System Name　　：    化交线上平台
 * SubSystem Name ：自贸区前台服务
 * File Name: ExtSignReq.java
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

import java.util.List;

import com.alibaba.fastjson.JSON;

/**
 * ExtSignReq.java
 * 
 * @author 池永
 * @version 1.0
 * @description xxx
 */
public class ExtSignAutoReq extends BaseReq {

	// 交易号
	private String transaction_id = "";

	// 合同编号
	private String contract_id = "";

	// 客户编号
	private String customer_id = "";

	// 客户角色
	private String client_role = "";

	// 文档标题
	private String doc_title = "";

	// 定位类型
	private int position_type;

	// 定位关键字
	private String sign_keyword = "";

	// 关键字签章策略
	private String keyword_strategy = "";

	// 定位坐标
	private List<ExtSignAutoSubReq> signature_positions;

	// 签署结果异步通知URL
	private String notify_url = "";

	/**
	 * @return the transaction_id
	 */
	public String getTransaction_id() {
		return transaction_id;
	}

	/**
	 * @param transaction_id
	 *            the transaction_id to set
	 */
	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}

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

	/**
	 * @return the doc_title
	 */
	public String getDoc_title() {
		return doc_title;
	}

	/**
	 * @param doc_title
	 *            the doc_title to set
	 */
	public void setDoc_title(String doc_title) {
		this.doc_title = doc_title;
	}

	/**
	 * @return the sign_keyword
	 */
	public String getSign_keyword() {
		return sign_keyword;
	}

	/**
	 * @param sign_keyword
	 *            the sign_keyword to set
	 */
	public void setSign_keyword(String sign_keyword) {
		this.sign_keyword = sign_keyword;
	}

	/**
	 * @return the keyword_strategy
	 */
	public String getKeyword_strategy() {
		return keyword_strategy;
	}

	/**
	 * @param keyword_strategy
	 *            the keyword_strategy to set
	 */
	public void setKeyword_strategy(String keyword_strategy) {
		this.keyword_strategy = keyword_strategy;
	}

	/**
	 * @return the notify_url
	 */
	public String getNotify_url() {
		return notify_url;
	}

	/**
	 * @param notify_url
	 *            the notify_url to set
	 */
	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}

	/**
	 * @return the client_role
	 */
	public String getClient_role() {
		return client_role;
	}

	/**
	 * @param client_role
	 *            the client_role to set
	 */
	public void setClient_role(String client_role) {
		this.client_role = client_role;
	}

	/**
	 * @return the position_type
	 */
	public int getPosition_type() {
		return position_type;
	}

	/**
	 * @param position_type
	 *            the position_type to set
	 */
	public void setPosition_type(int position_type) {
		this.position_type = position_type;
	}

	/**
	 * @return the signature_positions
	 */
	public String getSignature_positions() {
		if (signature_positions != null) {
			return JSON.toJSONString(signature_positions);
		} else {
			return "";
		}
	}

	/**
	 * @param signature_positions
	 *            the signature_positions to set
	 */
	public void setSignature_positions(List<ExtSignAutoSubReq> signature_positions) {
		this.signature_positions = signature_positions;
	}

}
