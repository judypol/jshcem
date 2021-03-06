/* ========================================
 * System Name　　：化交线上平台
 * SubSystem Name ：化交站点核心工具集
 * File Name: Constants
 * ----------------------------------------
 * Create Date/Change History
 * ----------------------------------------
 * 2017/8/22 　lizhihua   Create
 *
 *
 * ----------------------------------------
 * Copyright (c) SCEM . All rights reserved.
 */
package com.shcem.component;

import com.shcem.constants.SystemDefine;
import com.shcem.enums.LoggerLevel;
import com.shcem.enums.LoggerName;
import com.shcem.server.model.ServerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author lizhihua
 * @version 1.0
 */
public class BaseManager {
    protected Logger log= LoggerFactory.getLogger(LoggerName.Service.getName());
    protected String getMode() {
        String mode = SystemDefine.MODE_LOCAL;
        String sysMode = System.getProperty(SystemDefine.REQUEST_MODE);

        if (sysMode != null && !sysMode.isEmpty()) {
            if (SystemDefine.MODE_LOCAL.equals(sysMode)
                    || SystemDefine.MODE_DEV.equals(sysMode)
                    || SystemDefine.MODE_TEST.equals(sysMode)
                    || SystemDefine.MODE_UAT.equals(sysMode)
                    || SystemDefine.MODE_DEPLOY.equals(sysMode)) {
                mode = sysMode;
            }
        }

        return mode;
    }

    protected String getRequestId() {
        return ServerContext.currentContext().getRequestId();
    }

    protected String getUserId() {
        return ServerContext.currentContext().getMemberID();
    }

    protected String getUserName() {
        return ServerContext.currentContext().getMemberName();
    }
}
