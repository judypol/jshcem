/* ========================================
 * System Name　　：    化交线上平台
 * SubSystem Name ：化交服务核心工具集
 * File Name: PageInfo
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


import com.shcem.utils.StringUtils;
import org.apache.commons.collections.ArrayStack;

import java.util.ArrayList;
import java.util.List;

/**
 * PageInfo
 * @author chiyong
 * @version 1.0
 */
public class Page<E> {
	private int totalPages;
	private int totalRecords;
	private Integer pageIndex;
	private Integer pageSize;
	List<E> content=new ArrayList<>();

	public Page() {
	}

	public int getTotalPages() {
		return this.totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getTotalRecords() {
		return this.totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

	public List<E> getContent() {
		return content;
	}

	public void setContent(List<E> content) {
		this.content = content;
	}

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
}
