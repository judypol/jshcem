/* ========================================
 * System Name　　：化交线上平台
 * SubSystem Name ：化交站点核心工具集
 * File Name: Constants
 * ----------------------------------------
 * Create Date/Change History
 * ----------------------------------------
 * 2017/12/18 　lizhihua   Create
 *
 *
 * ----------------------------------------
 * Copyright (c) SCEM . All rights reserved.
 */
package com.shcem.mapper;

import com.shcem.utils.ConvertUtils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author lizhihua
 * @version 1.0
 */
public class BeanMapperIgnoreCase {
    private static ConcurrentMap<String,Map<String,Field>> classFields=new ConcurrentHashMap<>();
    public static <T> T map(Object source, Class<T> destinationClass){
        Map<String,Field> sourceFieldMap=getClassFields(source.getClass());
        Map<String,Field> destinationFieldMap=getClassFields(destinationClass);

        try{
            T destination=destinationClass.newInstance();
            for(String key :sourceFieldMap.keySet()){
                if(destinationFieldMap.containsKey(key)){
                    Field sourceField=sourceFieldMap.get(key);
                    Field destinationFiled=destinationFieldMap.get(key);
                    Class<?> sourceFieldType=sourceField.getType();
                    Class<?> destinationFiledType=destinationFiled.getType();

                    Object sourceVal=sourceField.get(source);
                    if(!sourceFieldType.equals(destinationFiledType)){
                        sourceVal= ConvertUtils.convert(sourceVal,destinationFiledType);
                    }

                    destinationFiled.set(destination,sourceVal);
                }
            }
            return destination;
        }catch (Exception ex){
            System.out.println(ex);
            return null;
        }
    }
    private synchronized static Map<String,Field> getClassFields(Class<?> cls){
        String clsName=cls.getName();
        Map<String,Field> fieldMap;
        if(classFields.containsKey(clsName)){
            fieldMap=classFields.get(clsName);
        }else{
            List<Field> fields= Arrays.asList(cls.getDeclaredFields());
            Class<?> parentCls=cls.getSuperclass();
            while (parentCls!=null){
                fields.addAll(Arrays.asList(parentCls.getDeclaredFields()));
                parentCls=parentCls.getSuperclass();
            }
            fieldMap=new HashMap<>();
            for (Field field :fields){
                field.setAccessible(true);
                fieldMap.put(field.getName().toLowerCase(),field);
            }
        }
        return fieldMap;
    }
}
