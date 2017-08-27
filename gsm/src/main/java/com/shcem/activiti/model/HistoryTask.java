///* ========================================
// * System Name　　：    化交线上平台
// * SubSystem Name ：化交服务核心工具集
// * File Name: HistoryTask
// * ----------------------------------------
// * Create Date/Change History
// * ----------------------------------------
// * 04/29/16 　池 永   Create
// *
// *
// * ----------------------------------------
// * Copyright (c) SCEM . All rights reserved.
// */
//package com.shcem.activiti.model;
//
//
//import com.alibaba.fastjson.JSONObject;
//
///**
// * HistoryTask
// *
// * @author chiyong
// * @version 1.0
// */
//public class HistoryTask extends HistoryTaskURL {
//	private String taskId = "";
//	private String variableName = "";
//	private HistoryTaskParams params = new HistoryTaskParams();
//
//	public JSONObject getJsonObject() {
//		JSONObject jsonObject = new JSONObject();
//		JSONObject jsonParams = params.getJsonParams();
//		JSONObject jsonURL = getJsonURL();
//		if (!"".equals(taskId))
//			jsonObject.put("taskId", taskId);
//		if (!"".equals(variableName))
//			jsonObject.put("variableName", variableName);
//
//		jsonObject.put("url", jsonURL);
//		jsonObject.put("params", jsonParams);
//		return jsonObject;
//
//	}
//
//	public String getTaskId() {
//		return taskId;
//	}
//
//	public void setTaskId(String taskId) {
//		this.taskId = taskId;
//	}
//
//	public String getVariableName() {
//		return variableName;
//	}
//
//	public void setVariableName(String variableName) {
//		this.variableName = variableName;
//	}
//
//	public HistoryTaskParams getParams() {
//		return params;
//	}
//
//	public void setParams(HistoryTaskParams params) {
//		this.params = params;
//	}
//
//}
