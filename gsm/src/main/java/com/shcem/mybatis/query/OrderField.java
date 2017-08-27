/* ========================================
 * System Name　　：    化交线上平台
 * SubSystem Name ：化交服务核心工具集
 * File Name: OrderField
 * ----------------------------------------
 * Create Date/Change History 
 * ----------------------------------------
 * 04/29/16 　池 永   Create
 * 
 * 
 * ----------------------------------------
 * Copyright (c) SCEM . All rights reserved.
 */
package com.shcem.mybatis.query;

/**
 * OrderField
 * @author chiyong
 * @version 1.0
 */
public class OrderField {
	
	private String orderField;
	
	private boolean orderDesc;
	
	public OrderField(String orderField, boolean orderDesc) {
		this.orderField = orderField;
		this.orderDesc = orderDesc;
	}

	public boolean isOrderDesc() {
		return this.orderDesc;
	}

	public void setOrderDesc(boolean orderDesc) {
		this.orderDesc = orderDesc;
	}

	public String getOrderField() {
		return this.orderField;
	}

	public void setOrderField(String orderField) {
		this.orderField = orderField;
	}

	public boolean getOrderDesc() {
		return this.orderDesc;
	}
}
