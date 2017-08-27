package com.shcem.netty;///* ========================================
// * System Name　　：    化交线上平台
// * SubSystem Name ：化交服务核心工具集
// * File Name: LogManagerImpl
// * ----------------------------------------
// * Create Date/Change History
// * ----------------------------------------
// * 04/29/16 　池 永   Create
// *
// *
// * ----------------------------------------
// * Copyright (c) SCEM . All rights reserved.
// */
//package com.shcem.netty;
//
//import com.shcem.Constants.Constants;
//
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//
///**
// * NettyLogClient
// *
// * @author chiyong
// * @version 1.0
// */
//public final class NettyLogClient {
//
//	/**
//	 * 执行Log
//	 * @param mode
//	 * @param paramLogItem
//	 */
//	public static void sendMsg(String mode, LogItem paramLogItem) {
//		JSONObject data = new JSONObject();
//		JSONObject jsoMsg = new JSONObject();
//		JSONObject sendMsg = new JSONObject();
//
//		String timeStamp = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(Calendar.getInstance().getTime());
//		data.put(Constants.LOG_DATA_COLUMN_TIME, timeStamp);
//
//		data.put(Constants.LOG_DATA_COLUMN_TYPE, paramLogItem.getLoglevel());
//		data.put(Constants.LOG_DATA_COLUMN_MESSAGE, paramLogItem.getLogmsg());
//		data.put(Constants.LOG_DATA_COLUMN_USERID, paramLogItem.getHeader().getMemberID());
//		data.put(Constants.LOG_DATA_COLUMN_APPNAME, paramLogItem.getHeader().getAppName());
//		data.put(Constants.LOG_DATA_COLUMN_IPADDRESS, paramLogItem.getHeader().getClientIP());
//		data.put(Constants.LOG_DATA_COLUMN_STATUS, Constants.OPE_SUCCESS);
//
//		if (Constants.MODE_LOCAL.equals(mode)) {
//			jsoMsg.put(Constants.LOG_ENV_JSON, Constants.ENV_LOCAL);
//		} else if (Constants.MODE_DEV.equals(mode)) {
//			jsoMsg.put(Constants.LOG_ENV_JSON, Constants.ENV_DEVELOP);
//		} else if (Constants.MODE_TEST.equals(mode)) {
//			jsoMsg.put(Constants.LOG_ENV_JSON, Constants.ENV_TEST);
//		} else if (Constants.MODE_UAT.equals(mode)) {
//			jsoMsg.put(Constants.LOG_ENV_JSON, Constants.ENV_UAT);
//		} else if (Constants.MODE_DEPLOY.equals(mode)) {
//			jsoMsg.put(Constants.LOG_ENV_JSON, Constants.ENV_DEPLOY);
//		} else {
//			// 取不到Mode的情况下设定为Local
//			jsoMsg.put(Constants.LOG_ENV_JSON, Constants.ENV_LOCAL);
//		}
//
//		jsoMsg.put(Constants.LOG_SYSTEM_DIFF_JSON, Constants.SYSTEM_DIFF_JAVSV);
//		jsoMsg.put(Constants.LOG_DATA_JSON, data.toString());
//
//		sendMsg.put("log", jsoMsg);
//
//		NettyLogClientStart r = new NettyLogClientStart(mode, sendMsg.toString());
//
//		Thread lgThread=new Thread(r);
//		lgThread.start();
//	}
//
//	/**
//	 * 业务Log
//	 * @param mode
//	 * @param paramLogItem
//	 */
//	public static void sendBusinessMsg(String mode, BusinessLogItem paramLogItem) {
//		JSONObject data = new JSONObject();
//		JSONObject jsoMsg = new JSONObject();
//		JSONObject sendMsg = new JSONObject();
//
//		String timeStamp = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(Calendar.getInstance().getTime());
//		data.put(Constants.LOG_DATA_COLUMN_TIME, timeStamp);
//
//		data.put(Constants.LOG_DATA_COLUMN_TYPE, paramLogItem.getLogmode());
//		data.put(Constants.LOG_DATA_COLUMN_MESSAGE, paramLogItem.getLogmsg());
//		data.put(Constants.LOG_DATA_COLUMN_USERID, paramLogItem.getHeader().getMemberID());
//		data.put(Constants.LOG_DATA_COLUMN_APPNAME, paramLogItem.getHeader().getAppName());
//		data.put(Constants.LOG_DATA_COLUMN_IPADDRESS, paramLogItem.getHeader().getClientIP());
//		data.put(Constants.LOG_DATA_COLUMN_STATUS, paramLogItem.getStatus());
//
//		if (Constants.MODE_LOCAL.equals(mode)) {
//			jsoMsg.put(Constants.LOG_ENV_JSON, Constants.ENV_LOCAL);
//		} else if (Constants.MODE_DEV.equals(mode)) {
//			jsoMsg.put(Constants.LOG_ENV_JSON, Constants.ENV_DEVELOP);
//		} else if (Constants.MODE_TEST.equals(mode)) {
//			jsoMsg.put(Constants.LOG_ENV_JSON, Constants.ENV_TEST);
//		} else if (Constants.MODE_UAT.equals(mode)) {
//			jsoMsg.put(Constants.LOG_ENV_JSON, Constants.ENV_UAT);
//		} else if (Constants.MODE_DEPLOY.equals(mode)) {
//			jsoMsg.put(Constants.LOG_ENV_JSON, Constants.ENV_DEPLOY);
//		} else {
//			// 取不到Mode的情况下设定为Local
//			jsoMsg.put(Constants.LOG_ENV_JSON, Constants.ENV_LOCAL);
//		}
//
//		jsoMsg.put(Constants.LOG_SYSTEM_DIFF_JSON, Constants.SYSTEM_DIFF_JAVSV);
//		jsoMsg.put(Constants.LOG_DATA_JSON, data.toString());
//
//		sendMsg.put("log", jsoMsg);
//
//		NettyLogClientStart r = new NettyLogClientStart(mode, sendMsg.toString());
//
//		Thread lgThread=new Thread(r);
//		lgThread.start();
//	}
//}