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
package com.shcem.webcore.permission.impl;

import com.alibaba.fastjson.TypeReference;
import com.shcem.Encrypt.EncrytHelper;
import com.shcem.common.RedisCacheManager;
import com.shcem.utils.CookieUtils;
import com.shcem.utils.StringUtils;
import com.shcem.webcore.permission.LoginInfo;
import com.shcem.webcore.permission.WebCoreConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * @author lizhihua
 * @version 1.0
 */
public abstract class RedisRealm extends AuthenticationRealm {

    Logger logger= LoggerFactory.getLogger("controller");
    @Autowired
    HttpServletRequest request;
    @Autowired
    HttpServletResponse response;
    int expire=30*60;
    String prefixKey="t";
    String mode="0";        //---登录模式，0，互斥，1，允许多处登录

//    String splitCode="-";
//    String prefixCookieKey= YamlConfiguration.instance().getString("prefixCookie");
    /**
     * 将用户信息写入缓存
     *
     * @param info
     */
    @Override
    protected void setCache(LoginInfo info) {
        String mapKey=UUID.randomUUID().toString().replace("-","");
        try{
            String redisKey=prefixKey+ WebCoreConstant.SplitCode+info.getLoginName();                //redis key
            Map<String,LoginInfo> oldMap=getLoginInfosFromCache(redisKey);
            if(mode.equals("0")){
                oldMap.clear();                                                     //将老的map清空，重新填入
                oldMap.put(mapKey,info);
            }else{
                oldMap.put(mapKey,info);                                            //直接在
            }

            RedisCacheManager.GetRedisCache().SetValue(redisKey,oldMap,expire);
            //---写入response
            String tokenKey= generateToken(redisKey,mapKey);
            CookieUtils.setCookie(response,WebCoreConstant.PrefixCookieKey+WebCoreConstant.SplitCode+"token",tokenKey);
        }catch (Exception ex){
            logger.error("用户信息写入缓存出错！",ex);
        }
    }

    /**
     *从redis中获取用户信息
     * @param redisKey
     * @return
     */
    private Map<String,LoginInfo> getLoginInfosFromCache(String redisKey) throws Exception{
        try{
            Map<String,LoginInfo> map=RedisCacheManager.GetRedisCache().Get(redisKey,new TypeReference<Map<String,LoginInfo>>(){});
            return map==null?new HashMap<String, LoginInfo>():map;
        }catch (Exception ex){
            throw new Exception(ex);
        }
    }
    /**
     * 生成一个token
     * @param redisKey
     * @param mapKey
     * @return
     */
    private String generateToken(String redisKey, String mapKey){
        String plainCode=redisKey+WebCoreConstant.SplitCode+mapKey;
        try{
            String token=EncrytHelper.encryptBase64(plainCode);
            return token;
        }catch (Exception ex){
            logger.error("生成Token时出现了错误！",ex);
            return "";
        }
    }

    /**
     * 解析token值，返回两个，0--对应的redis中的key，1--对应的map里的Key
     * @param token
     * @return
     */
    private String[] decryptToken(String token) throws Exception{
        if(StringUtils.isEmpty(token)){
            throw new Exception("token is empty");
        }
        String plainToken=EncrytHelper.decryptBase64(token);
        String[] keys=plainToken.split(WebCoreConstant.SplitCode);
        if(keys.length!=3){
            throw new Exception("token is wrong");
        }

        String[] reKeys=new String[2];
        reKeys[0]=keys[0]+WebCoreConstant.SplitCode+keys[1];        //--redis里key
        reKeys[1]=keys[2];                          //--map中的key

        return reKeys;
    }

    /***
     * 从缓存中获取用户信息
     * @return
     */
    @Override
    protected LoginInfo getCache(String token) {
        try{
            String[] keys=decryptToken(token);
            Map<String,LoginInfo> map=getLoginInfosFromCache(keys[0]);
            LoginInfo info;
            if(map==null){
                info=null;
            }else if(map.containsKey(keys[1])){
                info=map.get(keys[1]);
            }else{
                info=null;
            }
            return info;
        }catch (Exception ex){
            logger.error("从Redis中获取数据出错！",ex);
            return null;
        }
    }

    /***
     * 直接从Request中获取token值；
     * @return
     */
    public LoginInfo getLoginInfoFromRequest(){
        String token=CookieUtils.getCookie(request,WebCoreConstant.PrefixCookieKey+WebCoreConstant.SplitCode+"token");
        return getCache(token);
    }

}
