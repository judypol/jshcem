/* ========================================
 * System Name　　：    化交线上平台
 * SubSystem Name ：自贸区前台服务
 * File Name: ContractVerifyRes.java
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

/**
 * ContractVerifyRes.java
 * 
 * @author 池永
 * @version 1.0
 * @description xxx
 */
public class ContractVerifyRes extends BaseRes {

	private List<ContractVerifySubRes> data;

	/**
	 * @return the data
	 */
	public List<ContractVerifySubRes> getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(List<ContractVerifySubRes> data) {
		this.data = data;
	}

}
