/* ========================================
 * System Name　　：    化交线上平台
 * SubSystem Name ：自贸区前台服务
 * File Name: GenerateContractRes.java
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
 * GenerateContractRes.java
 * 
 * @author 池永
 * @version 1.0
 * @description xxx
 */
public class QuerySignStatusRes extends BaseRes {

	// 合同下载地址
	private String download_url;

	// 合同察看地址
	private String viewpdf_url;

	// 交易号
	private String transaction_id;

	// 签署状态码
	private String sign_status;

	// 签署状态说明
	private String sign_status_desc;

	/**
	 * @return the download_url
	 */
	public String getDownload_url() {
		return download_url;
	}

	/**
	 * @param download_url
	 *            the download_url to set
	 */
	public void setDownload_url(String download_url) {
		this.download_url = download_url;
	}

	/**
	 * @return the viewpdf_url
	 */
	public String getViewpdf_url() {
		return viewpdf_url;
	}

	/**
	 * @param viewpdf_url
	 *            the viewpdf_url to set
	 */
	public void setViewpdf_url(String viewpdf_url) {
		this.viewpdf_url = viewpdf_url;
	}

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
	 * @return the sign_status
	 */
	public String getSign_status() {
		return sign_status;
	}

	/**
	 * @param sign_status
	 *            the sign_status to set
	 */
	public void setSign_status(String sign_status) {
		this.sign_status = sign_status;
	}

	/**
	 * @return the sign_status_desc
	 */
	public String getSign_status_desc() {
		return sign_status_desc;
	}

	/**
	 * @param sign_status_desc
	 *            the sign_status_desc to set
	 */
	public void setSign_status_desc(String sign_status_desc) {
		this.sign_status_desc = sign_status_desc;
	}

}
