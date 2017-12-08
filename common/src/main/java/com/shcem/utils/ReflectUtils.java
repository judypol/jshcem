/* ========================================
 * System Name　　：化交线上平台
 * SubSystem Name ：化交站点核心工具集
 * File Name: Constants
 * ----------------------------------------
 * Create Date/Change History
 * ----------------------------------------
 * 2017/12/8 　lizhihua   Create
 *
 *
 * ----------------------------------------
 * Copyright (c) SCEM . All rights reserved.
 */
package com.shcem.utils;

import com.esotericsoftware.reflectasm.FieldAccess;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author lizhihua
 * @version 1.0
 */
public class ReflectUtils {
    private static ConcurrentMap<String,FieldAccess> fieldMap=new ConcurrentHashMap<>();

    /**
     *
     * @param sources
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> T map(Object sources,Class<T> cls){
        return null;
    }
}
