/* ========================================
 * System Name　　：    化交线上平台
 * SubSystem Name ：自贸区前台服务
 * File Name: BaseService.java
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

import com.shcem.common.fadada.model.BaseReq;
import com.shcem.common.fadada.model.BaseRes;

/**
 * BaseService.java
 * 
 * @author 池永
 * @version 1.0
 * @description xxx
 */
public interface FadadaBaseService<K extends BaseReq, V extends BaseRes> {
	
	V request(K k, String mode) throws Exception;
}
