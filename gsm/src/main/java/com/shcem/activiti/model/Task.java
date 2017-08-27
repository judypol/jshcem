///* ========================================
// * System Name　　：    化交线上平台
// * SubSystem Name ：化交服务核心工具集
// * File Name: Task
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
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
//
///**
// * Task
// *
// * @author chiyong
// * @version 1.0
// */
//public class Task extends TaskURL {
//	private String taskId = "";
//	private String cascadeHistory = "";
//	private String deleteReason = "";
//	private String scope = "";
//	private String variableName = "";
//	private String family = "";
//	private String identityId = "";
//	private String type = "";
//	private String commentId = "";
//	private String eventId = "";
//	private String attachmentId = "";
//	private TaskParams params = new TaskParams();
//
//	public TaskParams getParams() {
//		return params;
//	}
//
//	public void setParams(TaskParams params) {
//		this.params = params;
//	}
//
//	public JSONObject getJsonObject() {
//
//		JSONObject jsonObj = new JSONObject();
//		JSONObject jsonParm = params.getJsonParams();
//		JSONObject jsonURL = getJsonURL();
//		if (!"".equals(taskId))
//			jsonObj.put("taskId", taskId);
//		if (!"".equals(cascadeHistory))
//			jsonObj.put("cascadeHistory", cascadeHistory);
//		if (!"".equals(deleteReason))
//			jsonObj.put("deleteReason", deleteReason);
//		if (!"".equals(scope))
//			jsonObj.put("scope", scope);
//		if (!"".equals(variableName))
//			jsonObj.put("variableName", variableName);
//		if (!"".equals(family))
//			jsonObj.put("family", family);
//		if (!"".equals(identityId))
//			jsonObj.put("identityId", identityId);
//		if (!"".equals(type))
//			jsonObj.put("type", type);
//		if (!"".equals(taskId))
//			jsonObj.put("taskId", taskId);
//		if (!"".equals(commentId))
//			jsonObj.put("commentId", commentId);
//		if (!"".equals(eventId))
//			jsonObj.put("eventId", eventId);
//		if (!"".equals(attachmentId))
//			jsonObj.put("attachmentId", attachmentId);
//
//		jsonObj.put("params", jsonParm);
//		jsonObj.put("url", jsonURL);
//
//		return jsonObj;
//	}
//
//	public JSONObject getCreateVariablesJObj() {
//		JSONObject jsonObj = new JSONObject();
//		JSONArray jsonParm = params.getTaskVariables();
//		JSONObject jsonURL = getJsonURL();
//
//		jsonObj.put("taskId", taskId);
//
//		jsonObj.put("params", jsonParm);
//		jsonObj.put("url", jsonURL);
//
//		return jsonObj;
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
//	public String getCascadeHistory() {
//		return cascadeHistory;
//	}
//
//	public void setCascadeHistory(String cascadeHistory) {
//		this.cascadeHistory = cascadeHistory;
//	}
//
//	public String getDeleteReason() {
//		return deleteReason;
//	}
//
//	public void setDeleteReason(String deleteReason) {
//		this.deleteReason = deleteReason;
//	}
//
//	public String getScope() {
//		return scope;
//	}
//
//	public void setScope(String scope) {
//		this.scope = scope;
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
//	public String getFamily() {
//		return family;
//	}
//
//	public void setFamily(String family) {
//		this.family = family;
//	}
//
//	public String getIdentityId() {
//		return identityId;
//	}
//
//	public void setIdentityId(String identityId) {
//		this.identityId = identityId;
//	}
//
//	public String getType() {
//		return type;
//	}
//
//	public void setType(String type) {
//		this.type = type;
//	}
//
//	public String getCommentId() {
//		return commentId;
//	}
//
//	public void setCommentId(String commentId) {
//		this.commentId = commentId;
//	}
//
//	public String getEventId() {
//		return eventId;
//	}
//
//	public void setEventId(String eventId) {
//		this.eventId = eventId;
//	}
//
//	public String getAttachmentId() {
//		return attachmentId;
//	}
//
//	public void setAttachmentId(String attachmentId) {
//		this.attachmentId = attachmentId;
//	}
//}
