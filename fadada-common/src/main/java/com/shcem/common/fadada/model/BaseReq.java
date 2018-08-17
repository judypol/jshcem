/* ========================================
 * System Name　　：    化交线上平台
 * SubSystem Name ：自贸区前台服务
 * File Name: BaseRes.java
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
 * BaseRes.java
 * 
 * @author 池永
 * @version 1.0
 * @description xxx
 */
public class BaseReq {

	// 接入方ID
	private String app_id;

	// 版本号
	private String v;

	// 请求时间
	private String timestamp;

	// 消息摘要
	private String msg_digest;

	/**
	 * @return the app_id
	 */
	public String getApp_id() {
		return app_id;
	}

	/**
	 * @param app_id
	 *            the app_id to set
	 */
	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}

	/**
	 * @return the v
	 */
	public String getV() {
		return v;
	}

	/**
	 * @param v
	 *            the v to set
	 */
	public void setV(String v) {
		this.v = v;
	}

	/**
	 * @return the timestamp
	 */
	public String getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp
	 *            the timestamp to set
	 */
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * @return the msg_digest
	 */
	public String getMsg_digest() {
		return msg_digest;
	}

	/**
	 * @param msg_digest
	 *            the msg_digest to set
	 */
	public void setMsg_digest(String msg_digest) {
		this.msg_digest = msg_digest;
	}

}
