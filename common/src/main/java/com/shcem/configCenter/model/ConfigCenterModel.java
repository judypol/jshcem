///* ========================================
// * System Name　　：化交线上平台
// * SubSystem Name ：化交站点核心工具集
// * File Name: Constants
// * ----------------------------------------
// * Create Date/Change History
// * ----------------------------------------
// * 2017/12/5 　lizhihua   Create
// *
// *
// * ----------------------------------------
// * Copyright (c) SCEM . All rights reserved.
// */
//package com.shcem.configCenter.model;
//
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.concurrent.ConcurrentMap;
//
///**
// * @author lizhihua
// * @version 1.0
// */
//public class ConfigCenterModel {
//    private static ConcurrentMap<String,String> ConcurrentMap=new ConcurrentHashMap<>();
//
//    /**
//     * 初始化一个map
//     * @param map
//     */
//    public synchronized static void init(Map<String,Object> map){
//        for(String key:map.keySet()){
//            ConcurrentMap.put(key,String.valueOf(map.get(key)));
//            System.setProperty(key,String.valueOf(map.get(key)));
//        }
//    }
//
//    /**
//     * 添加一个值到map中
//     * @param key
//     * @param val
//     */
//    public static void add(String key,String val){
//        ConcurrentMap.put(key,val);
//    }
//
//    /**
//     * 添加一个Map
//     * @param map
//     */
//    public static void add(Map<String,Object> map){
//        for(String key:map.keySet()){
//            ConcurrentMap.put(key,String.valueOf(map.get(key)));
//            System.setProperty(key,String.valueOf(map.get(key)));
//        }
//    }
//
//    public static java.util.concurrent.ConcurrentMap<String, String> getConcurrentMap() {
//        return ConcurrentMap;
//    }
//}
