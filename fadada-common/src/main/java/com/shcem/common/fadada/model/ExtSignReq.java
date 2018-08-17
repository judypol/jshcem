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

/**
 * ExtSignReq.java
 * 
 * @author 池永
 * @version 1.0
 * @description xxx
 */
public class ExtSignReq extends BaseReq {

	// 交易号
	private String transaction_id = "";

	// 合同编号
	private String contract_id = "";

	// 客户编号
	private String customer_id = "";

	// 文档标题
	private String doc_title = "";

	// 定位关键字
	private String sign_keyword = "";

	// 关键字签章策略
	private String keyword_strategy = "";

	// 页面跳转URL
	private String return_url = "";

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
	 * @return the return_url
	 */
	public String getReturn_url() {
		return return_url;
	}

	/**
	 * @param return_url
	 *            the return_url to set
	 */
	public void setReturn_url(String return_url) {
		this.return_url = return_url;
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

}
