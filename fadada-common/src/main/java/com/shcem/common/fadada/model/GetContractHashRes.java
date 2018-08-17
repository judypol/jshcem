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
public class GetContractHashRes extends BaseRes {

	// 返回数据
	private GetContractHashSubRes data;

	/**
	 * @return the data
	 */
	public GetContractHashSubRes getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(GetContractHashSubRes data) {
		this.data = data;
	}

}
