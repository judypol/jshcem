/* ========================================
 * System Name　　：化交线上平台
 * SubSystem Name ：化交站点核心工具集
 * File Name: Constants
 * ----------------------------------------
 * Create Date/Change History
 * ----------------------------------------
 * 2017/11/28 　lizhihua   Create
 *
 *
 * ----------------------------------------
 * Copyright (c) SCEM . All rights reserved.
 */
package com.shcem.utils;

import com.shcem.mapper.BeanMapper;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author lizhihua
 * @version 1.0
 */
public class ConvertUtils {
    /**
     *
     * @param obj
     * @return
     */
    public static String convert2String(Object obj){
        return obj==null?StringUtils.EMPTY:obj.toString();
    }

    /**
     *
     * @param date
     * @param format
     * @return
     * @throws Exception
     */
    public static Date convert2Date(String date,String format) throws Exception{
        if(StringUtils.isEmpty(date)){
            return null;
        }
        if(StringUtils.isEmpty(format)){
            format="yyyy-MM-dd HH:mm:ss";
        }
        return DateUtils.parseDate(date,format);
    }

    /**
     * 字符串转int
     * @param source
     * @return
     */
    public static Integer convert2Int(String source){
        return (Integer) org.apache.commons.beanutils.ConvertUtils.convert(source,Integer.class);
    }

    /**
     * 字符串转double
     * @param source
     * @return
     */
    public static Double convert2Double(String source){
        return (Double) org.apache.commons.beanutils.ConvertUtils.convert(source,Double.class);
    }

    /**
     * 字符串转Float
     * @param source
     * @return
     */
    public static Float convert2Float(String source){
        return (Float) org.apache.commons.beanutils.ConvertUtils.convert(source,Float.class);
    }

    /**
     * 字符串转BigDecimal
     * @param source
     * @return
     */
    public static BigDecimal convert2BigDecimal(String source){
        return (BigDecimal) org.apache.commons.beanutils.ConvertUtils.convert(source,BigDecimal.class);
    }

    /**
     * date转String
     * @param date
     * @param format
     * @return
     */
    public static String Date2String(Date date,String format){
        return DateUtils.FormatDate(date,format);
    }

    /**
     * 将Object转化为指定的类型
     * @param obj
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> T convert(Object obj,Class<T> cls){
        return (T)org.apache.commons.beanutils.ConvertUtils.convert(obj,cls);
    }

    /**
     *
     * @param obj
     * @param cls 目标类型
     * @param <T>
     * @return
     */
    public static <T> T mapTo(Object obj,Class<T> cls){
        return BeanMapper.map(obj,cls);
    }

    /**
     *
     * @param obj
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> List<T> mapToList(Collection obj, Class<T> cls){
        return BeanMapper.mapList(obj,cls);
    }
}
