/* ========================================
 * System Name　　：    化交线上平台
 * SubSystem Name ：自贸区前台服务
 * File Name: ExtSignValidationReq.java
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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * ExtSignValidationReq.java
 * 
 * @author 池永
 * @version 1.0
 * @description xxx
 */
public class GenerateContractReq extends BaseReq {

	// 模板编号
	private String template_id = "";

	// 合同编号
	private String contract_id = "";

	// 文档标题
	private String doc_title = "";

	// 字体大小
	private String font_size;

	// 字体类型
	private String font_type;

	// 填充内容
	private JSONObject parameter_map;

	// 动态表格
	private List<GenerateContractSubDynamicReq> dynamic_tables;

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
	 * @return the contract_id
	 */
	public String getContract_id() {
		return contract_id;
	}

	/**
	 * @param contract_id
	 *            the contract_id to set
	 */
	public void setContract_id(String contract_id) {
		this.contract_id = contract_id;
	}

	/**
	 * @return the doc_title
	 */
	public String getDoc_title() {
		return doc_title;
	}

	/**
	 * @param doc_title
	 *            the doc_title to set
	 */
	public void setDoc_title(String doc_title) {
		this.doc_title = doc_title;
	}

	/**
	 * @return the font_size
	 */
	public String getFont_size() {
		return font_size;
	}

	/**
	 * @param font_size
	 *            the font_size to set
	 */
	public void setFont_size(String font_size) {
		this.font_size = font_size;
	}

	/**
	 * @return the font_type
	 */
	public String getFont_type() {
		return font_type;
	}

	/**
	 * @param font_type
	 *            the font_type to set
	 */
	public void setFont_type(String font_type) {
		this.font_type = font_type;
	}

	/**
	 * @return the parameter_map
	 */
	public String getParameter_map() {
		if (parameter_map != null) {
			return parameter_map.toString();
		} else {
			return "";
		}
	}

	/**
	 * @param parameter_map
	 *            the parameter_map to set
	 */
	public void setParameter_map(JSONObject parameter_map) {
		this.parameter_map = parameter_map;
	}

	/**
	 * @return the dynamic_tables
	 */
	public String getDynamic_tables() {
		if (dynamic_tables != null) {
			return JSON.toJSONString(dynamic_tables);
		} else {
			return "";
		}
	}

	/**
	 * @param dynamic_tables
	 *            the dynamic_tables to set
	 */
	public void setDynamic_tables(List<GenerateContractSubDynamicReq> dynamic_tables) {
		this.dynamic_tables = dynamic_tables;
	}

}
