package com.shcem.common.fadada.model;

public class PushdocsToMobileReq extends BaseReq{
	
		// 交易号
		private String transaction_id = "";

		// 合同编号
		private String contract_id = "";

		// 文档标题
		private String doc_title = "";

		// 待签署人姓名
		private String name = "";

		// 待签署人手机号
		private String mobile = "";

		//公司名称（参数非必须）
		private String company_name="-";

		public String getTransaction_id() {
			return transaction_id;
		}

		public void setTransaction_id(String transaction_id) {
			this.transaction_id = transaction_id;
		}

		public String getContract_id() {
			return contract_id;
		}

		public void setContract_id(String contract_id) {
			this.contract_id = contract_id;
		}

		public String getDoc_title() {
			return doc_title;
		}

		public void setDoc_title(String doc_title) {
			this.doc_title = doc_title;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getMobile() {
			return mobile;
		}

		public void setMobile(String mobile) {
			this.mobile = mobile;
		}

		public String getCompany_name() {
			return company_name;
		}

		public void setCompany_name(String company_name) {
			this.company_name = company_name;
		}
		

}
