//package com.shcem.hessian;/* ========================================
// * System Name　　：    化交线上平台
// * SubSystem Name ：化交服务核心工具集
// * File Name: HessianHeaderContext
// * ----------------------------------------
// * Create Date/Change History
// * ----------------------------------------
// * 05/09/16 　池 永   Create
// *
// *
// * ----------------------------------------
// * Copyright (c) SCEM . All rights reserved.
// */
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * Hessian协议请求头上下文。
// *
// * <pre>
// *  1.该类使用ThreadLocal将客户端请求头信息传递给服务端。
// *  2.请求头在HessianProxy发送请求之前，将该类中得请求头附加到请求中。
// *  3.服务端使用HessianServiceExporter中获取请求头，并放入HessianHeaderContext中，提供服务端使用。
// *  4.使用完记得调用#close方法，防止ThreadLocal内存泄露。
// * </pre>
// *
// * @see HessianProxy
// * @see HessianProxyFactory
// * @see HessianServiceExporter
// */
//public class HessianHeaderContext {
//
//	private static final ThreadLocal<HessianHeaderContext> THREAD_LOCAL = new ThreadLocal<HessianHeaderContext>();
//
//	private Map<String, String> headers = new HashMap<String, String>();
//
//	private HessianHeaderContext() {
//	}
//
//	public static HessianHeaderContext getContext() {
//		HessianHeaderContext context = THREAD_LOCAL.get();
//		if (context == null) {
//			context = new HessianHeaderContext();
//			THREAD_LOCAL.set(context);
//		}
//		return context;
//	}
//
//	public void addHeader(String name, String value) {
//		headers.put(name, value);
//	}
//
//	public String getHeader(String name) {
//		System.out.println("headers.name : " + name);
//		System.out.println("headers.get(name) : " + headers.get(name));
//		return headers.get(name);
//	}
//
//	public Map<String, String> getHeaders() {
//		return headers;
//	}
//
//	public static void close() {
//		HessianHeaderContext context = THREAD_LOCAL.get();
//		if (context != null) {
//			context.headers.clear();
//			THREAD_LOCAL.set(null);
//		}
//	}
//}