/* ========================================
 * System Name　　：    化交线上平台
 * SubSystem Name ：化交服务核心工具集
 * File Name: HistoryActivityURL
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
 * HistoryActivityURL
 * 
 * @author chiyong
 * @version 1.0
 */
public class HistoryActivityURL extends Page {
	
	private String activityId = "";
	private String activityInstanceId = "";
	private String activityName = "";
	private String activityType = "";
	private String executionId = "";
	private String finished = "";
	private String taskAssignee = "";
	private String processInstanceId = "";
	private String processDefinitionId = "";
	private String tenantId = "";
	private String tenantIdLike = "";
	private String withoutTenantId = "";

	public JSONObject getJsonURL() {

		JSONObject getJsonURL = getJsonPage();

		if (!"".equals(activityId))
			getJsonURL.put("activityId", activityId);
		if (!"".equals(activityInstanceId))
			getJsonURL.put("activityInstanceId", activityInstanceId);
		if (!"".equals(activityName))
			getJsonURL.put("activityName", activityName);
		if (!"".equals(activityType))
			getJsonURL.put("activityType", activityType);
		if (!"".equals(executionId))
			getJsonURL.put("executionId", executionId);
		if (!"".equals(finished))
			getJsonURL.put("finished", finished);
		if (!"".equals(taskAssignee))
			getJsonURL.put("taskAssignee", taskAssignee);
		if (!"".equals(processInstanceId))
			getJsonURL.put("processInstanceId", processInstanceId);
		if (!"".equals(processDefinitionId))
			getJsonURL.put("processDefinitionId", processDefinitionId);
		if (!"".equals(tenantId))
			getJsonURL.put("tenantId", tenantId);
		if (!"".equals(tenantIdLike))
			getJsonURL.put("tenantIdLike", tenantIdLike);
		if (!"".equals(withoutTenantId))
			getJsonURL.put("withoutTenantId", withoutTenantId);

		return getJsonURL;
	}

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	public String getActivityInstanceId() {
		return activityInstanceId;
	}

	public void setActivityInstanceId(String activityInstanceId) {
		this.activityInstanceId = activityInstanceId;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getActivityType() {
		return activityType;
	}

	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}

	public String getExecutionId() {
		return executionId;
	}

	public void setExecutionId(String executionId) {
		this.executionId = executionId;
	}

	public String getFinished() {
		return finished;
	}

	public void setFinished(String finished) {
		this.finished = finished;
	}

	public String getTaskAssignee() {
		return taskAssignee;
	}

	public void setTaskAssignee(String taskAssignee) {
		this.taskAssignee = taskAssignee;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public String getProcessDefinitionId() {
		return processDefinitionId;
	}

	public void setProcessDefinitionId(String processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getTenantIdLike() {
		return tenantIdLike;
	}

	public void setTenantIdLike(String tenantIdLike) {
		this.tenantIdLike = tenantIdLike;
	}

	public String getWithoutTenantId() {
		return withoutTenantId;
	}

	public void setWithoutTenantId(String withoutTenantId) {
		this.withoutTenantId = withoutTenantId;
	}

}
