/* ========================================
 * System Name　　：    化交线上平台
 * SubSystem Name ：自贸区前台服务
 * File Name: FadadaBaseServiceImpl.java
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

import java.util.Properties;

import com.fadada.sdk.client.FddClientBase;
import com.fadada.sdk.client.FddClientExtra;
import com.fadada.sdk.client.nonpublic.SyncCompanyAuto;

import com.shcem.common.fadada.utils.PropertyUtil;

/**
 * FadadaBaseServiceImpl.java
 * 
 * @author 池永
 * @version 1.0
 * @description xxx
 */
public class FadadaBaseServiceClient {
	private String FADADA_PROPERTY="/fadada.properties";
	protected FddClientBase base;
	protected FddClientExtra extra;
	protected SyncCompanyAuto company;
	protected NoPublicFddClientBase noPublicBase;
	protected String appid;

	/**
	 * 取得法大大接口对象
	 * 
	 * @return
	 */
	public void getClientBase(String mode, int differ) {

		PropertyUtil propUtil = new PropertyUtil();
		Properties clientProperty = propUtil.getProperties(FADADA_PROPERTY);

		appid = clientProperty.getProperty(mode + FadadaConstants.APPID);
		String secret = clientProperty.getProperty(mode + FadadaConstants.APP_SECRET);
		String url = clientProperty.getProperty(mode + FadadaConstants.URL);
		
		if (appid == null || secret == null || url == null) {
			return;
		}

		if (differ == FadadaConstants.DIFFER_BASE) {
			this.base = new FddClientBase(appid, secret, FadadaConstants.VERSION, url);
		} else if (differ == FadadaConstants.DIFFER_EXTRA) {
			this.extra = new FddClientExtra(appid, secret, FadadaConstants.VERSION, url);
		} else if (differ == FadadaConstants.DIFFER_COMPANY) {
			this.company = new SyncCompanyAuto(appid, secret, FadadaConstants.VERSION, url);
		}else if (differ==FadadaConstants.DIFFER_NOPUBLIC) {
			this.noPublicBase=new NoPublicFddClientBase(appid, secret, FadadaConstants.VERSION, url);
		}
	}

}
