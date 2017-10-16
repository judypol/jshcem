/* ========================================
 * System Name　　：化交线上平台
 * SubSystem Name ：化交站点核心工具集
 * File Name: Constants
 * ----------------------------------------
 * Create Date/Change History
 * ----------------------------------------
 * 2017/9/11 　lizhihua   Create
 *
 *
 * ----------------------------------------
 * Copyright (c) SCEM . All rights reserved.
 */
package com.shcem.hessian;

import org.springframework.context.annotation.Bean;

/**
 * @author lizhihua
 * @version 1.0
 */
public class HessianServiceScannerRegister {
    @Bean
    public HessianServiceScanner hessianServiceScanner(){
        return new HessianServiceScanner();
    }
}
