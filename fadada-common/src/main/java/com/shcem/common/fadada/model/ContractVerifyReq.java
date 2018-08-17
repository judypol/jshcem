/* ========================================
 * System Name　　：    化交线上平台
 * SubSystem Name ：自贸区前台服务
 * File Name: ContractVerifyReq.java
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

import java.io.File;

/**
 * ContractVerifyReq.java
 * 
 * @author 池永
 * @version 1.0
 * @description xxx
 */
public class ContractVerifyReq extends BaseReq {

	// 合同流文件
	private File file;

	// 文档URL
	private String doc_url = "";

	/**
	 * @return the file
	 */
	public File getFile() {
		return file;
	}

	/**
	 * @param file
	 *            the file to set
	 */
	public void setFile(File file) {
		this.file = file;
	}

	/**
	 * @return the doc_url
	 */
	public String getDoc_url() {
		return doc_url;
	}

	/**
	 * @param doc_url
	 *            the doc_url to set
	 */
	public void setDoc_url(String doc_url) {
		this.doc_url = doc_url;
	}

}
