///**
// * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
// */
//package com.shcem.utils;
//
//import net.sf.ehcache.Cache;
//import net.sf.ehcache.CacheManager;
//import net.sf.ehcache.Element;
//
///**
// * Cache工具类
// * @author
// * @version
// */
//public class EhCacheUtils {
//
//	private static CacheManager cacheManager = ((CacheManager)SpringContextHolder.GetBean("cacheManager"));
//
//	private static final String SYS_CACHE = "sysCache";
//
//	/**
//	 * 获取SYS_CACHE缓存
//	 * @param key
//	 * @return
//	 */
//	public static Object Get(String key) {
//		return Get(SYS_CACHE, key);
//	}
//
//	/**
//	 * 写入SYS_CACHE缓存
//	 * @param key
//	 * @return
//	 */
//	public static void Put(String key, Object value) {
//		Put(SYS_CACHE, key, value);
//	}
//
//	/**
//	 * 从SYS_CACHE缓存中移除
//	 * @param key
//	 * @return
//	 */
//	public static void Remove(String key) {
//		Remove(SYS_CACHE, key);
//	}
//
//	/**
//	 * 获取缓存
//	 * @param cacheName
//	 * @param key
//	 * @return
//	 */
//	public static Object Get(String cacheName, String key) {
//		Element element = GetCache(cacheName).get(key);
//		return element==null?null:element.getObjectValue();
//	}
//
//	/**
//	 * 写入缓存
//	 * @param cacheName
//	 * @param key
//	 * @param value
//	 */
//	public static void Put(String cacheName, String key, Object value) {
//		Element element = new Element(key, value);
//		GetCache(cacheName).put(element);
//	}
//
//	/**
//	 * 从缓存中移除
//	 * @param cacheName
//	 * @param key
//	 */
//	public static void Remove(String cacheName, String key) {
//		GetCache(cacheName).remove(key);
//	}
//
//	/**
//	 * 获得一个Cache，没有则创建一个。
//	 * @param cacheName
//	 * @return
//	 */
//	private static synchronized Cache GetCache(String cacheName){
//		Cache cache = cacheManager.getCache(cacheName);
//		if (cache == null){
//			cacheManager.addCache(cacheName);
//			cache = cacheManager.getCache(cacheName);
//			cache.getCacheConfiguration().setEternal(true);
//		}
//		return cache;
//	}
//
//	public static CacheManager getCacheManager() {
//		return cacheManager;
//	}
//
//}
