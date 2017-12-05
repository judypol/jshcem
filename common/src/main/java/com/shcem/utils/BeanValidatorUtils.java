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

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

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
        ValidatorFactory vf= Validation.buildDefaultValidatorFactory();
        Validator validator=vf.getValidator();
        Set<ConstraintViolation<T>> violations=validator.validate(obj);
        StringBuilder sb=new StringBuilder();
        for(ConstraintViolation<T> cv : violations){
            sb.append(cv.getMessage()+"(" + cv.getPropertyPath()
                    + "-->" + cv.getInvalidValue()+")");
        }
        return sb.toString();
    }
}
