/* ========================================
 * System Name　　：    化交线上平台
 * SubSystem Name ：化交服务核心工具集
 * File Name: Page
 * ----------------------------------------
 * Create Date/Change History 
 * ----------------------------------------
 * 04/29/16 　池 永   Create
 * 
 * 
 * ----------------------------------------
 * Copyright (c) SCEM . All rights reserved.
 */
package com.shcem.activiti.model;


import com.alibaba.fastjson.JSONObject;

/**
 * HistoryActivityURL
 * 
 * @author chiyong
 * @version 1.0
 */
public class Page {
	private String sort = "";
	private String order = "";
	private String start = "";
	private String size = "";

	public JSONObject getJsonPage() {

		JSONObject jsonPage = new JSONObject();
		if (!"".equals(sort))
			jsonPage.put("sort", sort);
		if (!"".equals(order))
			jsonPage.put("order", order);
		if (!"".equals(start))
			jsonPage.put("start", start);
		if (!"".equals(size))
			jsonPage.put("size", size);

		return jsonPage;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

}
