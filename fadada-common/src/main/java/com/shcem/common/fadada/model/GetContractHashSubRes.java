/* ========================================
 * System Name　　：    化交线上平台
 * SubSystem Name ：自贸区前台服务
 * File Name: GetContractHashRes.java
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
 * GetContractHashRes.java
 * 
 * @author 池永
 * @version 1.0
 * @description xxx
 */
public class GetContractHashSubRes {

	// 文件 hash（sha256）值
	private String fileHash = "";
	
	// 合同编号
	private String contract_id = "";

	/**
	 * @return the fileHash
	 */
	public String getFileHash() {
		return fileHash;
	}

	/**
	 * @param fileHash the fileHash to set
	 */
	public void setFileHash(String fileHash) {
		this.fileHash = fileHash;
	}

	/**
	 * @return the contract_id
	 */
	public String getContract_id() {
		return contract_id;
	}

	/**
	 * @param contract_id the contract_id to set
	 */
	public void setContract_id(String contract_id) {
		this.contract_id = contract_id;
	}
	
	
}
