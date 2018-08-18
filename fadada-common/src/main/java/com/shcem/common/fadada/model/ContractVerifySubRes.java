/* ========================================
 * System Name　　：    化交线上平台
 * SubSystem Name ：自贸区前台服务
 * File Name: ContractVerifySubRes.java
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
 * ContractVerifySubRes.java
 * 
 * @author 池永
 * @version 1.0
 * @description xxx
 */
public class ContractVerifySubRes {

	// 内容是否未被篡改
	private boolean integrityflag;

	// 数字证书信息 DN 中的 CN
	private String signer;

	// 签章时间
	private String signedontime;

	// 证书机构
	private String o;

	// 是否使用时间戳
	private boolean timestampflag;

	// 时间戳时间
	private String timestamptime;

	// 时间戳是否验证通过
	private boolean timestampverifyflag;

	/**
	 * @return the integrityflag
	 */
	public boolean isIntegrityflag() {
		return integrityflag;
	}

	/**
	 * @param integrityflag
	 *            the integrityflag to set
	 */
	public void setIntegrityflag(boolean integrityflag) {
		this.integrityflag = integrityflag;
	}

	/**
	 * @return the signer
	 */
	public String getSigner() {
		return signer;
	}

	/**
	 * @param signer
	 *            the signer to set
	 */
	public void setSigner(String signer) {
		this.signer = signer;
	}

	/**
	 * @return the signedontime
	 */
	public String getSignedontime() {
		return signedontime;
	}

	/**
	 * @param signedontime
	 *            the signedontime to set
	 */
	public void setSignedontime(String signedontime) {
		this.signedontime = signedontime;
	}

	/**
	 * @return the o
	 */
	public String getO() {
		return o;
	}

	/**
	 * @param o
	 *            the o to set
	 */
	public void setO(String o) {
		this.o = o;
	}

	/**
	 * @return the timestampflag
	 */
	public boolean isTimestampflag() {
		return timestampflag;
	}

	/**
	 * @param timestampflag
	 *            the timestampflag to set
	 */
	public void setTimestampflag(boolean timestampflag) {
		this.timestampflag = timestampflag;
	}

	/**
	 * @return the timestamptime
	 */
	public String getTimestamptime() {
		return timestamptime;
	}

	/**
	 * @param timestamptime
	 *            the timestamptime to set
	 */
	public void setTimestamptime(String timestamptime) {
		this.timestamptime = timestamptime;
	}

	/**
	 * @return the timestampverifyflag
	 */
	public boolean isTimestampverifyflag() {
		return timestampverifyflag;
	}

	/**
	 * @param timestampverifyflag
	 *            the timestampverifyflag to set
	 */
	public void setTimestampverifyflag(boolean timestampverifyflag) {
		this.timestampverifyflag = timestampverifyflag;
	}

}
