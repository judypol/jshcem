///* ========================================
// * System Name　　：    化交线上平台
// * SubSystem Name ：化交服务核心工具集
// * File Name: HistoryTaskParams
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
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * HistoryTaskParams
// *
// * @author chiyong
// * @version 1.0
// */
//public class HistoryTaskParams {
//	private String taskId = "";
//	private String processInstanceId = "";
//	private String processDefinitionKey = "";
//	private String processDefinitionKeyLike = "";
//	private String processDefinitionId = "";
//	private String processDefinitionName = "";
//	private String processDefinitionNameLike = "";
//	private String processBusinessKey = "";
//	private String processBusinessKeyLike = "";
//	private String executionId = "";
//	private String taskDefinitionKey = "";
//	private String taskName = "";
//	private String taskNameLike = "";
//	private String taskAssignee = "";
//	private String taskAssigneeLike = "";
//	private String taskOwner = "";
//	private String taskOwnerLike = "";
//	private String dueDate = "";
//	private String taskCompletedOn = "";
//	private String taskCompletedAfter = "";
//	private String taskCompletedBefore = "";
//	private String taskCreatedOn = "";
//	private String taskCreatedBefore = "";
//	private String taskCreatedAfter = "";
//	private boolean includeTaskLocalVariables = true;
//	private boolean includeProcessVariables = false;
//	private List<VariableParams> variables = new ArrayList<VariableParams>();
//
//	public JSONObject getJsonParams() {
//
//		JSONObject jsonParm = new JSONObject();
//		JSONArray jArray = new JSONArray();
//
//		for (VariableParams vars : variables) {
//			jArray.add(vars.getJsonObject());
//		}
//		if (jArray.size() > 0) {
//			jsonParm.put("variables", jArray);
//		}
//
//		if (!"".equals(taskId))
//			jsonParm.put("taskId", taskId);
//		if (!"".equals(processInstanceId))
//			jsonParm.put("processInstanceId", processInstanceId);
//		if (!"".equals(processDefinitionKey))
//			jsonParm.put("processDefinitionKey", processDefinitionKey);
//		if (!"".equals(processDefinitionKeyLike))
//			jsonParm.put("processDefinitionKeyLike", processDefinitionKeyLike);
//		if (!"".equals(processDefinitionId))
//			jsonParm.put("processDefinitionId", processDefinitionId);
//		if (!"".equals(processDefinitionName))
//			jsonParm.put("processDefinitionName", processDefinitionName);
//		if (!"".equals(processDefinitionNameLike))
//			jsonParm.put("processDefinitionNameLike", processDefinitionNameLike);
//		if (!"".equals(processBusinessKey))
//			jsonParm.put("processBusinessKey", processBusinessKey);
//		if (!"".equals(processBusinessKeyLike))
//			jsonParm.put("processBusinessKeyLike", processBusinessKeyLike);
//		if (!"".equals(executionId))
//			jsonParm.put("executionId", executionId);
//		if (!"".equals(taskDefinitionKey))
//			jsonParm.put("taskDefinitionKey", taskDefinitionKey);
//		if (!"".equals(taskName))
//			jsonParm.put("taskName", taskName);
//		if (!"".equals(taskNameLike))
//			jsonParm.put("taskNameLike", taskNameLike);
//		if (!"".equals(taskAssignee))
//			jsonParm.put("taskAssignee", taskAssignee);
//		if (!"".equals(taskAssigneeLike))
//			jsonParm.put("taskAssigneeLike", taskAssigneeLike);
//		if (!"".equals(taskOwner))
//			jsonParm.put("taskOwner", taskOwner);
//		if (!"".equals(taskOwnerLike))
//			jsonParm.put("taskOwnerLike", taskOwnerLike);
//		if (!"".equals(dueDate))
//			jsonParm.put("dueDate", dueDate);
//		if (!"".equals(taskCompletedOn))
//			jsonParm.put("taskCompletedOn", taskCompletedOn);
//		if (!"".equals(taskCompletedAfter))
//			jsonParm.put("taskCompletedAfter", taskCompletedAfter);
//		if (!"".equals(taskCompletedBefore))
//			jsonParm.put("taskCompletedBefore", taskCompletedBefore);
//		if (!"".equals(taskCreatedOn))
//			jsonParm.put("taskCreatedOn", taskCreatedOn);
//		if (!"".equals(taskCreatedBefore))
//			jsonParm.put("taskCreatedBefore", taskCreatedBefore);
//		if (!"".equals(dueDate))
//			jsonParm.put("dueDate", dueDate);
//		if (!"".equals(taskCreatedAfter))
//			jsonParm.put("taskCreatedAfter", taskCreatedAfter);
//		if (!"".equals(includeTaskLocalVariables))
//			jsonParm.put("includeTaskLocalVariables", includeTaskLocalVariables);
//		if (!"".equals(includeProcessVariables))
//			jsonParm.put("includeProcessVariables", includeProcessVariables);
//
//		return jsonParm;
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
//	public String getProcessInstanceId() {
//		return processInstanceId;
//	}
//
//	public void setProcessInstanceId(String processInstanceId) {
//		this.processInstanceId = processInstanceId;
//	}
//
//	public String getProcessDefinitionKey() {
//		return processDefinitionKey;
//	}
//
//	public void setProcessDefinitionKey(String processDefinitionKey) {
//		this.processDefinitionKey = processDefinitionKey;
//	}
//
//	public String getProcessDefinitionKeyLike() {
//		return processDefinitionKeyLike;
//	}
//
//	public void setProcessDefinitionKeyLike(String processDefinitionKeyLike) {
//		this.processDefinitionKeyLike = processDefinitionKeyLike;
//	}
//
//	public String getProcessDefinitionId() {
//		return processDefinitionId;
//	}
//
//	public void setProcessDefinitionId(String processDefinitionId) {
//		this.processDefinitionId = processDefinitionId;
//	}
//
//	public String getProcessDefinitionName() {
//		return processDefinitionName;
//	}
//
//	public void setProcessDefinitionName(String processDefinitionName) {
//		this.processDefinitionName = processDefinitionName;
//	}
//
//	public String getProcessDefinitionNameLike() {
//		return processDefinitionNameLike;
//	}
//
//	public void setProcessDefinitionNameLike(String processDefinitionNameLike) {
//		this.processDefinitionNameLike = processDefinitionNameLike;
//	}
//
//	public String getProcessBusinessKey() {
//		return processBusinessKey;
//	}
//
//	public void setProcessBusinessKey(String processBusinessKey) {
//		this.processBusinessKey = processBusinessKey;
//	}
//
//	public String getProcessBusinessKeyLike() {
//		return processBusinessKeyLike;
//	}
//
//	public void setProcessBusinessKeyLike(String processBusinessKeyLike) {
//		this.processBusinessKeyLike = processBusinessKeyLike;
//	}
//
//	public String getExecutionId() {
//		return executionId;
//	}
//
//	public void setExecutionId(String executionId) {
//		this.executionId = executionId;
//	}
//
//	public String getTaskDefinitionKey() {
//		return taskDefinitionKey;
//	}
//
//	public void setTaskDefinitionKey(String taskDefinitionKey) {
//		this.taskDefinitionKey = taskDefinitionKey;
//	}
//
//	public String getTaskName() {
//		return taskName;
//	}
//
//	public void setTaskName(String taskName) {
//		this.taskName = taskName;
//	}
//
//	public String getTaskNameLike() {
//		return taskNameLike;
//	}
//
//	public void setTaskNameLike(String taskNameLike) {
//		this.taskNameLike = taskNameLike;
//	}
//
//	public String getTaskAssignee() {
//		return taskAssignee;
//	}
//
//	public void setTaskAssignee(String taskAssignee) {
//		this.taskAssignee = taskAssignee;
//	}
//
//	public String getTaskAssigneeLike() {
//		return taskAssigneeLike;
//	}
//
//	public void setTaskAssigneeLike(String taskAssigneeLike) {
//		this.taskAssigneeLike = taskAssigneeLike;
//	}
//
//	public String getTaskOwner() {
//		return taskOwner;
//	}
//
//	public void setTaskOwner(String taskOwner) {
//		this.taskOwner = taskOwner;
//	}
//
//	public String getTaskOwnerLike() {
//		return taskOwnerLike;
//	}
//
//	public void setTaskOwnerLike(String taskOwnerLike) {
//		this.taskOwnerLike = taskOwnerLike;
//	}
//
//	public String getDueDate() {
//		return dueDate;
//	}
//
//	public void setDueDate(String dueDate) {
//		this.dueDate = dueDate;
//	}
//
//	public String getTaskCompletedOn() {
//		return taskCompletedOn;
//	}
//
//	public void setTaskCompletedOn(String taskCompletedOn) {
//		this.taskCompletedOn = taskCompletedOn;
//	}
//
//	public String getTaskCompletedAfter() {
//		return taskCompletedAfter;
//	}
//
//	public void setTaskCompletedAfter(String taskCompletedAfter) {
//		this.taskCompletedAfter = taskCompletedAfter;
//	}
//
//	public String getTaskCompletedBefore() {
//		return taskCompletedBefore;
//	}
//
//	public void setTaskCompletedBefore(String taskCompletedBefore) {
//		this.taskCompletedBefore = taskCompletedBefore;
//	}
//
//	public String getTaskCreatedOn() {
//		return taskCreatedOn;
//	}
//
//	public void setTaskCreatedOn(String taskCreatedOn) {
//		this.taskCreatedOn = taskCreatedOn;
//	}
//
//	public String getTaskCreatedBefore() {
//		return taskCreatedBefore;
//	}
//
//	public void setTaskCreatedBefore(String taskCreatedBefore) {
//		this.taskCreatedBefore = taskCreatedBefore;
//	}
//
//	public String getTaskCreatedAfter() {
//		return taskCreatedAfter;
//	}
//
//	public void setTaskCreatedAfter(String taskCreatedAfter) {
//		this.taskCreatedAfter = taskCreatedAfter;
//	}
//
//	public boolean isIncludeTaskLocalVariables() {
//		return includeTaskLocalVariables;
//	}
//
//	public void setIncludeTaskLocalVariables(boolean includeTaskLocalVariables) {
//		this.includeTaskLocalVariables = includeTaskLocalVariables;
//	}
//
//	public boolean isIncludeProcessVariables() {
//		return includeProcessVariables;
//	}
//
//	public void setIncludeProcessVariables(boolean includeProcessVariables) {
//		this.includeProcessVariables = includeProcessVariables;
//	}
//
//	public List<VariableParams> getVariables() {
//		return variables;
//	}
//
//	public void setVariables(List<VariableParams> variables) {
//		this.variables = variables;
//	}
//
//}
