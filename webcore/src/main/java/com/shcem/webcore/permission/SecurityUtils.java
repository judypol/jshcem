/* ========================================
 * System Name　　：化交线上平台
 * SubSystem Name ：化交站点核心工具集
 * File Name: Constants
 * ----------------------------------------
 * Create Date/Change History
 * ----------------------------------------
 * 2017/10/19 　lizhihua   Create
 *
 *
 * ----------------------------------------
 * Copyright (c) SCEM . All rights reserved.
 */
package com.shcem.webcore.permission;

import com.shcem.utils.SpringContextHolder;

public class SecurityUtils {
    private static ThreadLocal<Subject> local=new ThreadLocal<>();
    /**
     *获取一个subject
     * @return
     */
    public static Subject buildSubject(){
        Subject subject= local.get();
        if(subject==null){
            subject= SpringContextHolder.GetBean(Subject.class);
            local.set(subject);
        }
        return subject;
    }

    /**
     * 移除Subject
     */
    public static void removeSubject(){
        local.remove();
    }

}

