/* ========================================
 * System Name　　：化交线上平台
 * SubSystem Name ：化交站点核心工具集
 * File Name: SystemDefine
 * ----------------------------------------
 * Create Date/Change History
 * ----------------------------------------
 * 02/26/17 　zengxiaoning   Create
 *
 *
 * ----------------------------------------
 * Copyright (c) SCEM . All rights reserved.
 */
package com.shcem.constants;


/**
 * @author zengxiaoning
 * @ClassName SystemDefine
 * @Description 静态参数定义类
 * @Version 1.0
 * @Date 2017-02-28
 */
public class SystemDefine {

    /**
     * 开发模式
     */
    public final static String MODE_LOCAL = "local";
    public final static String MODE_DEV = "dev";
    public final static String MODE_TEST = "test";
    public final static String MODE_UAT = "uat";
    public final static String MODE_DEPLOY = "deploy";

    /**
     * 基本参数
     */
    public final static String REQUEST_CLIENT_IP = "Clientip";
    public final static String REQUEST_REQ_TIME = "Reqtime";
    public final static String REQUEST_MEM_ID = "Memberid";
    public final static String REQUEST_MEM_NAME = "Membername";
    public final static String REQUEST_LOGIN_NAME = "LoginName";
    public final static String REQUEST_APP_NAME = "Appname";
    public final static String REQUEST_MODE = "Mode";
    public final static String REQUEST_REFERER = "Referer";
    public final static String REQUEST_USERAGENT = "UserAgent";
    public final static String REQUEST_REQUESTID = "RequestId";

    public final static String LogUrl ="logUrl";
    public final static String LogBuffer="logBuffer";
    public final static String LogSys="logSys";
    public final static String LogENV="logEnv";
    /**
     * 中央代理常量
     */
    public final static String AuthPrefix="auth.Prefix";
    public final static String AppName="appName";
    public final static String AuthApp="authApp";
    public final static String MidTierUrl="MidTierUrl";

    public final static String RedisMode="redis.mode";      //--0单机模式，1--集群模式
    public final static String RedisHost="redis.host";      //--多个主机用逗号隔开

    // 加密的key值
    public final static String DES_KEY = "shcem3436egewTqWyreEWT2ee";
    /**
     * 工作日对应的Key值
     */
    public final static String WORKDAYFILE="workdayfile";
    /**
     * 分布式应用中生成Key所对应的值
     */
    public final static String WorkerId="workerId";
    public final static String DatecenterId="datecenterId";

    public final static String ACTIVITI_PROPERITES_FILE="";
}

