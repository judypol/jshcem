/* ========================================
 * System Name　　：    化交线上平台
 * SubSystem Name ：自贸区前台服务
 * File Name: GenerateContractSubDynamicReq.java
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
 * GenerateContractSubDynamicReq.java
 * 
 * @author 池永
 * @version 1.0
 * @description xxx
 */
public class GenerateContractSubDynamicReq extends BaseReq {

	// 插入属性
	private Integer insertWay;

	// 关键字
	private String keyword = "";

	// 页面开始
	private Integer pageBegin;

	// 是否有边框
	private boolean borderFlag;

	// 正文行高
	private float cellHeight;

	// 水平对齐方式
	private Integer cellHorizontalAlignment;

	// 垂直对齐方式
	private Integer cellVerticalAlignment;

	// 一级标题
	private String theFirstHeader = "";

	// 表头信息
	private String[] headers;

	// 正文
	private String[][] datas;

	// 宽度比例
	private Integer[] colWidthPercent;

	/**
	 * @return the insertWay
	 */
	public Integer getInsertWay() {
		return insertWay;
	}

	/**
	 * @param insertWay
	 *            the insertWay to set
	 */
	public void setInsertWay(Integer insertWay) {
		this.insertWay = insertWay;
	}

	/**
	 * @return the keyword
	 */
	public String getKeyword() {
		return keyword;
	}

	/**
	 * @param keyword
	 *            the keyword to set
	 */
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	/**
	 * @return the pageBegin
	 */
	public Integer getPageBegin() {
		return pageBegin;
	}

	/**
	 * @param pageBegin
	 *            the pageBegin to set
	 */
	public void setPageBegin(Integer pageBegin) {
		this.pageBegin = pageBegin;
	}

	/**
	 * @return the borderFlag
	 */
	public boolean isBorderFlag() {
		return borderFlag;
	}

	/**
	 * @param borderFlag
	 *            the borderFlag to set
	 */
	public void setBorderFlag(boolean borderFlag) {
		this.borderFlag = borderFlag;
	}

	/**
	 * @return the cellHeight
	 */
	public float getCellHeight() {
		return cellHeight;
	}

	/**
	 * @param cellHeight
	 *            the cellHeight to set
	 */
	public void setCellHeight(float cellHeight) {
		this.cellHeight = cellHeight;
	}

	/**
	 * @return the cellHorizontalAlignment
	 */
	public Integer getCellHorizontalAlignment() {
		return cellHorizontalAlignment;
	}

	/**
	 * @param cellHorizontalAlignment
	 *            the cellHorizontalAlignment to set
	 */
	public void setCellHorizontalAlignment(Integer cellHorizontalAlignment) {
		this.cellHorizontalAlignment = cellHorizontalAlignment;
	}

	/**
	 * @return the cellVerticalAlignment
	 */
	public Integer getCellVerticalAlignment() {
		return cellVerticalAlignment;
	}

	/**
	 * @param cellVerticalAlignment
	 *            the cellVerticalAlignment to set
	 */
	public void setCellVerticalAlignment(Integer cellVerticalAlignment) {
		this.cellVerticalAlignment = cellVerticalAlignment;
	}

	/**
	 * @return the theFirstHeader
	 */
	public String getTheFirstHeader() {
		return theFirstHeader;
	}

	/**
	 * @param theFirstHeader
	 *            the theFirstHeader to set
	 */
	public void setTheFirstHeader(String theFirstHeader) {
		this.theFirstHeader = theFirstHeader;
	}

	/**
	 * @return the headers
	 */
	public String[] getHeaders() {
		return headers;
	}

	/**
	 * @param headers
	 *            the headers to set
	 */
	public void setHeaders(String[] headers) {
		this.headers = headers;
	}

	/**
	 * @return the datas
	 */
	public String[][] getDatas() {
		return datas;
	}

	/**
	 * @param datas
	 *            the datas to set
	 */
	public void setDatas(String[][] datas) {
		this.datas = datas;
	}

	/**
	 * @return the colWidthPercent
	 */
	public Integer[] getColWidthPercent() {
		return colWidthPercent;
	}

	/**
	 * @param colWidthPercent
	 *            the colWidthPercent to set
	 */
	public void setColWidthPercent(Integer[] colWidthPercent) {
		this.colWidthPercent = colWidthPercent;
	}

}
