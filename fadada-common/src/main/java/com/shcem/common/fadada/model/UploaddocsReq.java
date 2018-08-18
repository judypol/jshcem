/* ========================================
 * System Name　　：    化交线上平台
 * SubSystem Name ：自贸区前台服务
 * File Name: UploaddocsReq.java
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
 * UploaddocsReq.java
 * 
 * @author 池永
 * @version 1.0
 * @description xxx
 */
public class UploaddocsReq extends BaseReq {

	// 合同编号
	private String contract_id = "";

	// 合同标题
	private String doc_title = "";

	// PDF文档
	private File file;

	// 文档URL
	private String doc_url = "";

	// 文档类型
	private String doc_type = "";

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

	/**
	 * @return the doc_type
	 */
	public String getDoc_type() {
		return doc_type;
	}

	/**
	 * @param doc_type
	 *            the doc_type to set
	 */
	public void setDoc_type(String doc_type) {
		this.doc_type = doc_type;
	}

}
