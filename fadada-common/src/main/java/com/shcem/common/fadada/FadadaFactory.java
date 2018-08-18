/* ========================================
 * System Name　　：    化交线上平台
 * SubSystem Name ：自贸区前台服务
 * File Name: FadadaFactory.java
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

import java.io.File;
import java.util.concurrent.ConcurrentHashMap;

import com.alibaba.fastjson.JSON;

import com.shcem.common.fadada.model.*;

/**
 * FadadaFactory.java
 *
 * @author 池永
 * @version 1.0
 * @description xxx
 */
public class FadadaFactory {

    private static ConcurrentHashMap<String, FadadaBaseService> fadadaServices = new ConcurrentHashMap<>();
    private static Object lockObj = new Object();

    /**
     * 取得法大大接口实例
     *
     * @param interfaceName
     * @return
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static FadadaBaseService getInstantce(String interfaceName) throws Exception {

        FadadaBaseService svc = null;

        Class<FadadaBaseService> cls = null;

        svc = fadadaServices.get(interfaceName);
        if (svc == null) {
            synchronized (lockObj) {
                if (svc == null) {
                    cls = (Class<FadadaBaseService>) Class.forName(interfaceName);
                    svc = cls.newInstance();
                    fadadaServices.put(interfaceName, svc);
                }
            }
        }

        return svc;
    }

//    public static void main(String[] args) throws Exception {
//
//        //FadadaFactory.testExtSignAuto();
//        FadadaFactory.testEditContractFirmInfo();
//    }


}
