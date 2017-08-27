/* ========================================
 * System Name　　：    化交线上平台
 * SubSystem Name ：自贸区前台服务
 * File Name: ThreadLocalCustomized.java
 * ----------------------------------------
 * Create Date/Change History 
 * ----------------------------------------
 * 2017年5月15日 　池永   Create
 * 
 * 
 * ----------------------------------------
 * Copyright (c) SCEM . All rights reserved.
 */
package com.shcem.service;

/**
 * ThreadLocalCustomized.java
 * 
 * @author 池永
 * @version 1.0
 * @description xxx
 */
public class ThreadLocalCustomized<T> extends ThreadLocal<T> {
	
	private String returnMsg;

	/**
	 * @return the returnMsg
	 */
	public String getReturnMsg() {
		return returnMsg;
	}

	/**
	 * @param returnMsg
	 *            the returnMsg to set
	 */
	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}

	public String toString() {
		
		if (this.get() != null) {
			this.returnMsg = this.get().toString();
			this.remove();
		} 
		return this.returnMsg;
	}
}
