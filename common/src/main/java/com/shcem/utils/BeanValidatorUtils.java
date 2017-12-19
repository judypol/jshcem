/* ========================================
 * System Name　　：化交线上平台
 * SubSystem Name ：化交站点核心工具集
 * File Name: Constants
 * ----------------------------------------
 * Create Date/Change History
 * ----------------------------------------
 * 2017/12/1 　lizhihua   Create
 *
 *
 * ----------------------------------------
 * Copyright (c) SCEM . All rights reserved.
 */
package com.shcem.utils;


import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

import java.util.List;


/**
 * java bean 验证
 * @author lizhihua
 * @version 1.0
 */
public class BeanValidatorUtils {
    /**
     *
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> String beanValidator(T obj){
//        ValidatorFactory vf= Validation.buildDefaultValidatorFactory();
//        Validator validator=vf.getValidator();
        Validator validator=new Validator();
        List<ConstraintViolation> violations=validator.validate(obj);
        StringBuilder sb=new StringBuilder();
        for(ConstraintViolation cv : violations){
            sb.append(cv.getMessage()+"("
                     +cv.getInvalidValue()+")");
        }
        return sb.toString();
    }
}
