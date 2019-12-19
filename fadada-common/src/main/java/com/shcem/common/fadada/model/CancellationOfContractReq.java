package com.shcem.common.fadada.model;

/**
 * CancellationOfContractReq.java
 * 
 * @author wangshuai
 * @version 1.0
 * @description 合同撤销接口 请求bean
 */
public class CancellationOfContractReq extends BaseReq {

	// 合同编号
	private String contract_id = "";

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

}
