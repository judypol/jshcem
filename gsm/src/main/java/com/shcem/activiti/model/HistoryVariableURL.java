/* ========================================
 * System Name　　：    化交线上平台
 * SubSystem Name ：化交服务核心工具集
 * File Name: HistoryVariableURL
 * ----------------------------------------
 * Create Date/Change History 
 * ----------------------------------------
 * 04/29/16 　池 永   Create
 * 
 * 
 * ----------------------------------------
 * Copyright (c) SCEM . All rights reserved.
 */
package com.shcem.activiti.model;

import com.alibaba.fastjson.JSONObject;

/**
 * HistoryVariableURL
 * 
 * @author chiyong
 * @version 1.0
 */
public class HistoryVariableURL extends Page {

	private String processInstanceId = "";
	private String taskId = "";
	private String excludeTaskVariables = "";
	private String variableName = "";
	private String variableNameLike = "";

	public JSONObject getJsonURL() {

		JSONObject getJsonURL = getJsonPage();

		if (!"".equals(processInstanceId))
			getJsonURL.put("processInstanceId", processInstanceId);
		if (!"".equals(taskId))
			getJsonURL.put("taskId", taskId);
		if (!"".equals(excludeTaskVariables))
			getJsonURL.put("excludeTaskVariables", excludeTaskVariables);
		if (!"".equals(variableName))
			getJsonURL.put("variableName", variableName);
		if (!"".equals(variableNameLike))
			getJsonURL.put("variableNameLike", variableNameLike);

		return getJsonURL;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getExcludeTaskVariables() {
		return excludeTaskVariables;
	}

	public void setExcludeTaskVariables(String excludeTaskVariables) {
		this.excludeTaskVariables = excludeTaskVariables;
	}

	public String getVariableName() {
		return variableName;
	}

	public void setVariableName(String variableName) {
		this.variableName = variableName;
	}

	public String getVariableNameLike() {
		return variableNameLike;
	}

	public void setVariableNameLike(String variableNameLike) {
		this.variableNameLike = variableNameLike;
	}

}
