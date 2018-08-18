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
public class GenerateContractRes extends BaseRes {

	// 合同下载地址
	private String download_url;

	// 合同察看地址
	private String viewpdf_url;

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

}
