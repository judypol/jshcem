package com.shcem.common.fadada.model;

public class PushdocsToMobileReq extends BaseReq{
	
		// 交易号
		private String transaction_id = "";

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

		public String getSign_keywords() {
			return sign_keywords;
		}

		public void setSign_keywords(String sign_keywords) {
			this.sign_keywords = sign_keywords;
		}

		public String getUser_names() {
			return user_names;
		}

		public void setUser_names(String user_names) {
			this.user_names = user_names;
		}

		public String getUser_mobiles() {
			return user_mobiles;
		}

		public void setUser_mobiles(String user_mobiles) {
			this.user_mobiles = user_mobiles;
		}

		public String getUser_emails() {
			return user_emails;
		}

		public void setUser_emails(String user_emails) {
			this.user_emails = user_emails;
		}

		public String getExpiration_time() {
			return expiration_time;
		}

		public void setExpiration_time(String expiration_time) {
			this.expiration_time = expiration_time;
		}

		// 合同编号
		private String contract_id = "";

		// 文档标题
		private String doc_title = "";

		// 定位关键字
		private String sign_keywords = "";

		// 待签署人姓名
		private String user_names = "";

		// 待签署人手机号
		private String user_mobiles = "";

		// 待签署人邮箱
		private String user_emails = "";
		
		//签署截止时间
		private String expiration_time="";

}
