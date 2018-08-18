/* ========================================
 * System Name　　：    化交线上平台
 * SubSystem Name ：自贸区前台服务
 * File Name: ExtSignReq.java
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
 * ExtSignReq.java
 * 
 * @author 池永
 * @version 1.0
 * @description xxx
 */
public class ExtSignAutoSubReq extends BaseReq {

	// 页码
	private int pagenum;

	// x坐标
	private double x;

	// y坐标
	private double y;

	/**
	 * @return the pagenum
	 */
	public int getPagenum() {
		return pagenum;
	}

	/**
	 * @param pagenum
	 *            the pagenum to set
	 */
	public void setPagenum(int pagenum) {
		this.pagenum = pagenum;
	}

	/**
	 * @return the x
	 */
	public double getX() {
		return x;
	}

	/**
	 * @param x
	 *            the x to set
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public double getY() {
		return y;
	}

	/**
	 * @param y
	 *            the y to set
	 */
	public void setY(double y) {
		this.y = y;
	}

}
