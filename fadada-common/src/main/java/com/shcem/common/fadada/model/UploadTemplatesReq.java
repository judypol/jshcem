/* ========================================
 * System Name　　：    化交线上平台
 * SubSystem Name ：自贸区前台服务
 * File Name: UploadTemplatesReq.java
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
 * UploadTemplatesReq.java
 * 
 * @author 池永
 * @version 1.0
 * @description xxx
 */
public class UploadTemplatesReq extends BaseReq {

	public UploadTemplatesReq(){

	}

	/**
	 *
	 * @param template_id
	 * @param file
	 * @param doc_url
	 */
	public UploadTemplatesReq(String template_id,File file,String doc_url){
		this.doc_url=doc_url;
		this.file=file;
		this.template_id=template_id;
	}

	// 模板编号
	private String template_id = "";

	// PDF文档
	private File file;

	// 文档URL
	private String doc_url = "";

	/**
	 * @return the template_id
	 */
	public String getTemplate_id() {
		return template_id;
	}

	/**
	 * @param template_id
	 *            the template_id to set
	 */
	public void setTemplate_id(String template_id) {
		this.template_id = template_id;
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

}
