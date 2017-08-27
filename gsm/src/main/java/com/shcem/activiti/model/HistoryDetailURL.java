/* ========================================
 * System Name　　：    化交线上平台
 * SubSystem Name ：化交服务核心工具集
 * File Name: HistoryDetailURL
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
 * HistoryDetailURL
 * 
 * @author chiyong
 * @version 1.0
 */
public class HistoryDetailURL extends Page {

	private String id = "";
	private String processInstanceId = "";
	private String executionId = "";
	private String activityInstanceId = "";
	private String taskId = "";
	private String selectOnlyFormProperties = "";
	private String selectOnlyVariableUpdates = "";

	public JSONObject getJsonURL() {

		JSONObject getJsonURL = getJsonPage();

		if (!"".equals(id))
			getJsonURL.put("id", id);
		if (!"".equals(processInstanceId))
			getJsonURL.put("processInstanceId", processInstanceId);
		if (!"".equals(executionId))
			getJsonURL.put("executionId", executionId);
		if (!"".equals(activityInstanceId))
			getJsonURL.put("activityInstanceId", activityInstanceId);
		if (!"".equals(taskId))
			getJsonURL.put("taskId", taskId);
		if (!"".equals(selectOnlyFormProperties))
			getJsonURL.put("selectOnlyFormProperties", selectOnlyFormProperties);
		if (!"".equals(selectOnlyVariableUpdates))
			getJsonURL.put("selectOnlyVariableUpdates", selectOnlyVariableUpdates);

		return getJsonURL;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public String getExecutionId() {
		return executionId;
	}

	public void setExecutionId(String executionId) {
		this.executionId = executionId;
	}

	public String getActivityInstanceId() {
		return activityInstanceId;
	}

	public void setActivityInstanceId(String activityInstanceId) {
		this.activityInstanceId = activityInstanceId;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getSelectOnlyFormProperties() {
		return selectOnlyFormProperties;
	}

	public void setSelectOnlyFormProperties(String selectOnlyFormProperties) {
		this.selectOnlyFormProperties = selectOnlyFormProperties;
	}

	public String getSelectOnlyVariableUpdates() {
		return selectOnlyVariableUpdates;
	}

	public void setSelectOnlyVariableUpdates(String selectOnlyVariableUpdates) {
		this.selectOnlyVariableUpdates = selectOnlyVariableUpdates;
	}

}
