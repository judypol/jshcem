///* ========================================
// * System Name　　：    化交线上平台
// * SubSystem Name ：化交服务核心工具集
// * File Name: TaskParams
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
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * TaskParams
// *
// * @author chiyong
// * @version 1.0
// */
//public class TaskParams {
//	private String name = "";
//	private String nameLike = "";
//	private String assignee = "";
//	private String assigneeLike = "";
//	private String owner = "";
//	private String ownerLike = "";
//	private String candidateUser = "";
//	private String candidateGroup = "";
//	private String candidateGroups = "";
//	private String taskDefinitionKey = "";
//	private String processInstanceId = "";
//	private String processInstanceBusinessKey = "";
//	private String processDefinitionKey = "";
//	private String processDefinitionName = "";
//	private String action = "";
//	private List<VariableParams> variables=new ArrayList<VariableParams>();
//
//	public JSONObject getJsonParams() {
//
//		JSONObject jsonParm = new JSONObject();
//		JSONArray jArray = new JSONArray();
//
//		for (VariableParams vars : variables) {
//			jArray.add(vars.getJsonObject());
//		}
//		if(jArray.size()>0){
//			jsonParm.put("variables", jArray);
//		}
//		if (!name.equals(""))
//			jsonParm.put("name", name);
//		if (!nameLike.equals(""))
//			jsonParm.put("nameLike", nameLike);
//		if (!assignee.equals(""))
//			jsonParm.put("assignee", assignee);
//		if (!assigneeLike.equals(""))
//			jsonParm.put("assigneeLike", assigneeLike);
//		if (!owner.equals(""))
//			jsonParm.put("owner", owner);
//		if (!ownerLike.equals(""))
//			jsonParm.put("ownerLike", ownerLike);
//		if (!candidateUser.equals(""))
//			jsonParm.put("candidateUser", candidateUser);
//		if (!candidateGroup.equals(""))
//			jsonParm.put("candidateGroup", candidateGroup);
//		if (!candidateGroups.equals(""))
//			jsonParm.put("candidateGroups", candidateGroups);
//		if (!taskDefinitionKey.equals(""))
//			jsonParm.put("taskDefinitionKey", taskDefinitionKey);
//		if (!processInstanceId.equals(""))
//			jsonParm.put("processInstanceId", processInstanceId);
//		if (!processInstanceBusinessKey.equals(""))
//			jsonParm.put("processInstanceBusinessKey", processInstanceBusinessKey);
//		if (!processDefinitionKey.equals(""))
//			jsonParm.put("processDefinitionKey", processDefinitionKey);
//		if (!processDefinitionName.equals(""))
//			jsonParm.put("processDefinitionName", processDefinitionName);
//		if (!action.equals(""))
//			jsonParm.put("action", action);
//		return jsonParm;
//	}
//
//	/**
//	 * 获取Variables
//	 * @param
//	 * @return
//	 */
//	public JSONArray getTaskVariables(){
//		JSONArray jArray = new JSONArray();
//
//		for (VariableParams vars : variables) {
//			jArray.add(vars.getJsonObject());
//		}
//		return jArray;
//	}
//
//	/**
//	 * 完成任务
//	 */
//	public void CompleteTask() {
//		this.action = "complete";
//	}
//
//	/**
//	 * 认领
//	 */
//	public void ClaimTask(String assignee) {
//		this.action = "claim";
//		this.assignee = assignee;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public String getNameLike() {
//		return nameLike;
//	}
//
//	public void setNameLike(String nameLike) {
//		this.nameLike = nameLike;
//	}
//
//	public String getAssignee() {
//		return assignee;
//	}
//
//	public void setAssignee(String assignee) {
//		this.assignee = assignee;
//	}
//
//	public String getAssigneeLike() {
//		return assigneeLike;
//	}
//
//	public void setAssigneeLike(String assigneeLike) {
//		this.assigneeLike = assigneeLike;
//	}
//
//	public String getOwner() {
//		return owner;
//	}
//
//	public void setOwner(String owner) {
//		this.owner = owner;
//	}
//
//	public String getOwnerLike() {
//		return ownerLike;
//	}
//
//	public void setOwnerLike(String ownerLike) {
//		this.ownerLike = ownerLike;
//	}
//
//	public String getCandidateUser() {
//		return candidateUser;
//	}
//
//	public void setCandidateUser(String candidateUser) {
//		this.candidateUser = candidateUser;
//	}
//
//	public String getCandidateGroup() {
//		return candidateGroup;
//	}
//
//	public void setCandidateGroup(String candidateGroup) {
//		this.candidateGroup = candidateGroup;
//	}
//
//	public String getCandidateGroups() {
//		return candidateGroups;
//	}
//
//	public void setCandidateGroups(String candidateGroups) {
//		this.candidateGroups = candidateGroups;
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
//	public String getProcessInstanceId() {
//		return processInstanceId;
//	}
//
//	public void setProcessInstanceId(String processInstanceId) {
//		this.processInstanceId = processInstanceId;
//	}
//
//	public String getProcessInstanceBusinessKey() {
//		return processInstanceBusinessKey;
//	}
//
//	public void setProcessInstanceBusinessKey(String processInstanceBusinessKey) {
//		this.processInstanceBusinessKey = processInstanceBusinessKey;
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
//	public String getProcessDefinitionName() {
//		return processDefinitionName;
//	}
//
//	public void setProcessDefinitionName(String processDefinitionName) {
//		this.processDefinitionName = processDefinitionName;
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
