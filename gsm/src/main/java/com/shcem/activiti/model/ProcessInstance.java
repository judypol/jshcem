///* ========================================
// * System Name　　：    化交线上平台
// * SubSystem Name ：化交服务核心工具集
// * File Name: ProcessInstance
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
//import com.alibaba.fastjson.JSONObject;
//
///**
// * ProcessInstance
// *
// * @author chiyong
// * @version 1.0
// */
//public class ProcessInstance extends ProcessInstanceURL {
//	private String processInstanceId="";
//	private String userId="";
//	private String type="";
//	private String variableName="";
//
//	private ProcessInstanceParams piParams=new ProcessInstanceParams();
//
//
//	public JSONObject getJsonObject(){
//		JSONObject jsonObj = new JSONObject();
//		JSONObject jsonParm = piParams.getJsonParams();
//		JSONObject jsonURL = getJsonURL();
//		if (!"".equals(processInstanceId))
//			jsonObj.put("processInstanceId", processInstanceId);
//		if (!"".equals(userId))
//			jsonObj.put("userId", userId);
//		if (!"".equals(type))
//			jsonObj.put("type", type);
//		if (!"".equals(variableName))
//			jsonObj.put("variableName", variableName);
//
//
//		jsonObj.put("params", jsonParm);
//		jsonObj.put("url", jsonURL);
//
//		return jsonObj;
//	}
//
//
//	public String getProcessInstanceId() {
//		return processInstanceId;
//	}
//	public ProcessInstanceParams getPiParams() {
//		return piParams;
//	}
//	public void setPiParams(ProcessInstanceParams piParams) {
//		this.piParams = piParams;
//	}
//	public void setProcessInstanceId(String processInstanceId) {
//		this.processInstanceId = processInstanceId;
//	}
//	public String getUserId() {
//		return userId;
//	}
//	public void setUserId(String userId) {
//		this.userId = userId;
//	}
//	public String getType() {
//		return type;
//	}
//	public void setType(String type) {
//		this.type = type;
//	}
//	public String getVariableName() {
//		return variableName;
//	}
//	public void setVariableName(String variableName) {
//		this.variableName = variableName;
//	}
//}
