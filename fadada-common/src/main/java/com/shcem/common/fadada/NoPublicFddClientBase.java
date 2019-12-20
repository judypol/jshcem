package com.shcem.common.fadada;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import com.fadada.sdk.client.common.FddClient;
import com.fadada.sdk.util.crypt.FddEncryptTool;
import com.fadada.sdk.util.http.HttpsUtil;

/**
 * @author guode
 * 对法大大的接口的扩展，增加合同推送的接口
 *
 */
public class NoPublicFddClientBase extends FddClient{
	
	 /**
     * 概要：FddClientBase类的构造函数
     * @param appId   请传入贵平台的appId
     * @param secret  请传入贵平台的secret
     * @param version 版本号，默认2.0
     * @param url     接口地址
     */
	public NoPublicFddClientBase(String appId, String secret, String version, String url) {
		super(appId, secret, version, url);
	}
	
	/**
	 * 推送官网签署合同接口（未注册）
	 * @param contract_id 合同编号
	 * @param transaction_id 交易号
	 * @param doc_title 文档标题
	 * @param name 经办人姓名
	 * @param mobile 手机号
	 * @param company_name 企业名称
	 * @author guode
	 * @date 2018-10-09
	 * @return
	 */
	public String push_extsign_dkn(String contract_id, String transaction_id, String doc_title, String name, String mobile, String company_name) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		try {
			String timeStamp = HttpsUtil.getTimeStamp();
			//String sort = "company_namecontract_iddoc_titlemobilenamesign_keywordtransaction_id";
			String sort =company_name+contract_id+doc_title+mobile+name+transaction_id;
			String msgDigest = "";
			// Base64(SHA1(app_id+md5(transaction_id+timestamp)+S HA1(app_secret + contract_id )))
			String sha1 = FddEncryptTool.sha1(super.getAppId() + FddEncryptTool.md5Digest(timeStamp) + FddEncryptTool.sha1(super.getSecret() + sort));
			msgDigest = new String(FddEncryptTool.Base64Encode(sha1.getBytes()));

			params.add(new BasicNameValuePair("app_id", super.getAppId()));
			params.add(new BasicNameValuePair("timestamp", timeStamp));
			params.add(new BasicNameValuePair("v", super.getVersion()));
			params.add(new BasicNameValuePair("msg_digest", msgDigest));
			params.add(new BasicNameValuePair("doc_title", doc_title));
			params.add(new BasicNameValuePair("contract_id", contract_id));
			params.add(new BasicNameValuePair("transaction_id", transaction_id));

			params.add(new BasicNameValuePair("name", name));
			params.add(new BasicNameValuePair("mobile", mobile));
			params.add(new BasicNameValuePair("company_name", company_name));
			//params.add(new BasicNameValuePair("sign_keyword", sign_keyword));

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return HttpsUtil.doPost(getURLOfPushExtsignDkn(), params);
	}
	
	/**
	 * 调用合同撤销接口
	 * @param contract_id 合同编号
	 * @author wangshuai
	 * @date 2018-10-09
	 * @return
	 */
	public String invokeCancellationOfContract(String contract_id) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		try {
			String timeStamp = HttpsUtil.getTimeStamp();
			String msgDigest = "";
			// Base64(SHA1(app_id+md5(timestamp)+SHA1(app_secret + contract_id )))
			String sha1 = FddEncryptTool.sha1(super.getAppId() + FddEncryptTool.md5Digest(timeStamp) + FddEncryptTool.sha1(super.getSecret() + contract_id));
			msgDigest = new String(FddEncryptTool.Base64Encode(sha1.getBytes()));

			params.add(new BasicNameValuePair("app_id", super.getAppId()));
			params.add(new BasicNameValuePair("v", super.getVersion()));
			params.add(new BasicNameValuePair("timestamp", timeStamp));
			params.add(new BasicNameValuePair("contract_id", contract_id));
			params.add(new BasicNameValuePair("msg_digest", msgDigest));

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return HttpsUtil.doPost(this.getURLOfCancellationOfContract(), params);
	}
	
	/***********************以下是获取接口URL方法***************************/	
	/**
	 * 获取推送官网签署合同接口（未注册）URL
	 * @author wangshuai
	 * @date 2018-10-09
	 * @return
	 */
	public String getURLOfPushExtsignDkn() {
		return super.getUrl() + "push_extsign_dkn"+API_SUFFIX;
	}
	
	/**
	 * 获取合同撤销接口URL
	 * @author wangshuai
	 * @date 2018-10-09
	 * @return
	 */
	public String getURLOfCancellationOfContract() {
		return super.getUrl() + "cancellation_of_contract"+API_SUFFIX;
	}
}
