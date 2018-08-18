/* ========================================
 * System Name　　：    化交线上平台
 * SubSystem Name ：自贸区前台服务
 * File Name: SyncPersonAutoReq.java
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
 * SyncPersonAutoReq.java
 * 
 * @author 池永
 * @version 1.0
 * @description xxx
 */
public class SyncPersonAutoReq extends BaseReq {

	// 客户姓名
	private String customer_name = "";

	// 电子邮箱
	private String email = "";

	// 证件号码
	private String id_card = "";

	// 证件类型
	/**
	 * 0-身份证 身份证 （默认 （默认 值） ； 1-护照； 护照； 2-军人身份证； 军人身份证； 军人身份证； 军人身份证； 6-社会保障卡；
	 * 社会保障卡； 社会保障卡； 社会保障卡； A-武装警察身份证件； 武装警察身份证件； 武装警察身份证件； 武装警察身份证件； 武装警察身份证件；
	 * B-港澳通行证； 港澳通行证； 港澳通行证； 港澳通行证； C-台湾居民来往大陆通行证 台湾居民来往大陆通行证 台湾居民来往大陆通行证
	 * 台湾居民来往大陆通行证 台湾居民来往大陆通行证 台湾居民来往大陆通行证 ； E-户口簿； 户口簿； 户口簿； F-临时居民身份证； 临时居民身份证；
	 * 临时居民身份证； 临时居民身份证； P-外国人永久居留证； 外国人永久居留证； 外国人永久居留证；
	 */
	private String ident_type;

	// 证件号码与手机
	/**
	 * 手机 号只支持 号只支持 号只支持 中国大陆 国大陆 运营 商 使用 3DES3DES 3DES对数据加密 对数据加密 对数据加密 数据 格式 为：证件
	 * 号码 |手机
	 */
	private String id_mobile;

	/**
	 * @return the customer_name
	 */
	public String getCustomer_name() {
		return customer_name;
	}

	/**
	 * @param customer_name
	 *            the customer_name to set
	 */
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the id_card
	 */
	public String getId_card() {
		return id_card;
	}

	/**
	 * @param id_card
	 *            the id_card to set
	 */
	public void setId_card(String id_card) {
		this.id_card = id_card;
	}

	/**
	 * @return the ident_type
	 */
	public String getIdent_type() {
		return ident_type;
	}

	/**
	 * @param ident_type
	 *            the ident_type to set
	 */
	public void setIdent_type(String ident_type) {
		this.ident_type = ident_type;
	}

	/**
	 * @return the id_mobile
	 */
	public String getId_mobile() {
		return id_mobile;
	}

	/**
	 * @param id_mobile
	 *            the id_mobile to set
	 */
	public void setId_mobile(String id_mobile) {
		this.id_mobile = id_mobile;
	}

}
