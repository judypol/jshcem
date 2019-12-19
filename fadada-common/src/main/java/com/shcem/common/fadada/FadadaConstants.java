/* ========================================
 * System Name　　：    化交线上平台
 * SubSystem Name ：自贸区前台服务
 * File Name: FadadaConstants.java
 * ----------------------------------------
 * Create Date/Change History 
 * ----------------------------------------
 * 2018年1月11日 　池永   Create
 * 
 * 
 * ----------------------------------------
 * Copyright (c) SCEM . All rights reserved.
 */
package com.shcem.common.fadada;


/**
 * FadadaConstants.java
 * 
 * @author 池永
 * @version 1.0
 * @description xxx
 */
public class FadadaConstants {

	/**
	 * 企业基本接口名
	 */
	// 个人CA申请接口
	public final static String IF_SYNC_COMPANY_AUTO = "com.shcem.common.fadada.companyservice.impl.SyncCompanyAutoImpl";
	
	/**
	 * 服务基本接口名
	 */
	// 个人CA申请接口
	public final static String IF_SYNC_PERSION_AUTO = "com.shcem.common.fadada.baseservice.impl.SyncPersonAutoImpl";

	// 文档传输接口
	public final static String IF_UPLOAD_DOCS = "com.shcem.common.fadada.baseservice.impl.UploaddocsImpl";

	// 合同模板传输接口
	public final static String IF_UPLOAD_TEMPLATES = "com.shcem.common.fadada.baseservice.impl.UploadTemplatesImpl";

	// 合同生成接口
	public final static String IF_GENERATE_CONTRACT = "com.shcem.common.fadada.baseservice.impl.GenerateContractImpl";

	// 文档签署接口（手动签）
	public final static String IF_EXT_SIGN = "com.shcem.common.fadada.baseservice.impl.ExtSignImpl";

	// 文档签署接口（自动签）
	public final static String IF_EXT_SIGN_AUTO = "com.shcem.common.fadada.baseservice.impl.ExtSignAutoImpl";

	// 客户签署状态查询接口（按客户编号）
	public final static String IF_QUERY_SIGN_STATUS = "com.shcem.common.fadada.baseservice.impl.QuerySignStatusImpl";

	// 合同归档接口
	public final static String IF_CONTRACT_FILING = "com.shcem.common.fadada.baseservice.impl.ContractFilingImpl";
	
	
	/**
	 * 非公开接口
	 */
	// 合同推送接口
	public final static String IF_PUSH_DOCS_TO_MOBILE = "com.shcem.common.fadada.nopublicservice.impl.PushDocsToMobileImpl";
	
	// 合同撤销接口
	public final static String IF_CANCELLATION_OF_CONTRACT = "com.shcem.common.fadada.nopublicservice.impl.CancellationOfContractImpl";

	/**
	 * 服务扩展接口名
	 */
	// 客户信息修改接口
	public final static String IF_INFO_CHANGE = "com.shcem.common.fadada.extendservice.impl.InfoChangeImpl";

	// 文档查看接口
	public final static String IF_VIEW_CONTRACT = "com.shcem.common.fadada.extendservice.impl.ViewContractImpl";

	// 文档下载接口
	public final static String IF_DOWNLOAD_CONTRACT = "com.shcem.common.fadada.extendservice.impl.DownloadContractImpl";

	// 文档验签接口
	public final static String IF_CONTRACT_VERIFY = "com.shcem.common.fadada.extendservice.impl.ContractVerifyImpl";

	// 查询合同HASH值接口
	public final static String IF_GET_CONTRACT_HASH = "com.shcem.common.fadada.extendservice.impl.GetContractHashImpl";

	// 文档签署接口（含有效期和次数限制）
	public final static String IF_EXTSIGN_VALIDATION = "com.shcem.common.fadada.extendservice.impl.ExtSignValidationImpl";

	// 文档临时查看/下载地址接口（含有效期和次数）
	public final static String IF_GET_URL = "com.shcem.common.fadada.extendservice.impl.GetUrlImpl";

	// 修改客户信息
	public final static String IF_EDIT_CONTRACT_FIRM_INFO = "com.shcem.common.fadada.extendservice.impl.EditContractFirmInfoImpl";
	/**
	 * 调用法大大的接口参数
	 */
	// appid
	public final static String APPID = "_appid";
	// secret
	public final static String APP_SECRET = "_secret";
	// version
	public final static String VERSION = "2.0";
	// url
	public final static String URL = "_url";

	/**
	 * 处理结果
	 */
	// 成功
	public final static String SUCCESS = "success";
	// 失败
	public final static String ERROR = "error";

	/**
	 * 系统状态码
	 */
	// 1000 ：操作成功
	public final static String CODE_SUCCESS = "1000";
	// 2001：参数缺失或者不合法
	public final static String CODE_ILLEGAL_PM = "2001";
	// 2002：业务异常
	public final static String CODE_BUZEXCEPTION = "2002";
	// 2003：其他错误
	public final static String CODE_OTHERS = "2003";
	// 无法连接ClientBase
	public final static String CODE_BASEERROR = "9998";
	// 系统异常
	public final static String CODE_EXCEPTION = "9999";

	/**
	 * 基础扩展区分
	 */
	// 基础接口
	public final static int DIFFER_BASE = 0;
	// 扩展接口
	public final static int DIFFER_EXTRA = 1;
	// 企业接口
	public final static int DIFFER_COMPANY = 2;
	// 非公开接口
	public final static int DIFFER_NOPUBLIC = 3;

	/**
	 * 证件类型
	 */
	// 0-身份证 身份证 （默认 （默认 值） ；
	public final static String TYPE_IDENTICARD = "0";
	// 1-护照；
	public final static String TYPE_PASSPORT = "1";
	// 2-军人身份证；
	public final static String TYPE_SOLDIER = "2";
	// 6-社会保障卡；
	public final static String TYPE_SECURITYCARD = "6";
	// A-武装警察身份证件；
	public final static String TYPE_POLICE = "A";
	// B-港澳通行证；
	public final static String TYPE_HMPASSPORT = "B";
	// C-台湾居民来往大陆通行证
	public final static String TYPE_TPASSPORT = "C";
	// E-户口簿；
	public final static String TYPE_RESIDENCE = "E";
	// F-临时居民身份证；
	public final static String TYPE_TMPID = "F";
	// P-外国人永久居留证；
	public final static String TYPE_FOREINGER = "P";

	/**
	 * 其他
	 */
	// 文档类型
	public final static String DOC_TYPE = ".pdf";

	// 定位类型 - 关键字
	public final static int LOC_KEYWORD = 0;
	// 定位类型 - XY坐标
	public final static int LOC_XY = 1;

	public final static String CONTRACT_TEMPLATE_ID="MLTPContract_";
	public final static String AUTHORIZATION_TEMPLATE_ID="MLTPAuthorization_";

	public final static int FADADA_TEMPLATE_CONTRACT=1;
	public final static int FADADA_TEMPLATE_AUTHORIZATION=2;
}
