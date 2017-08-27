/* ========================================
 * System Name　　：    化交线上平台
 * SubSystem Name ：化交服务核心工具集
 * File Name: HistoryTaskURL
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
 * HistoryTaskParams
 * 
 * @author chiyong
 * @version 1.0
 */
public class HistoryTaskURL extends Page {
	
	private String taskId = "";
	private String processInstanceId = "";
	private String processDefinitionKey = "";
	private String processDefinitionKeyLike = "";
	private String processDefinitionId = "";
	private String processDefinitionName = "";
	private String processDefinitionNameLike = "";
	private String processBusinessKey = "";
	private String processBusinessKeyLike = "";
	private String executionId = "";
	private String taskDefinitionKey = "";
	private String taskName = "";
	private String taskNameLike = "";
	private String taskDescription = "";
	private String taskDescriptionLike = "";
	private String taskDeleteReason = "";
	private String taskDeleteReasonLike = "";
	private String taskAssignee = "";
	private String taskAssigneeLike = "";
	private String taskOwner = "";
	private String taskOwnerLike = "";
	private String taskInvolvedUser = "";
	private String taskPriority = "";
	private String finished = "";
	private String processFinished = "";
	private String parentTaskId = "";
	private String dueDate = "";
	private String dueDateAfter = "";
	private String dueDateBefore = "";
	private String withoutDueDate = "";
	private String taskCompletedOn = "";
	private String taskCompletedAfter = "";
	private String taskCompletedBefore = "";
	private String taskCreatedOn = "";
	private String taskCreatedBefore = "";
	private String taskCreatedAfter = "";
	private String includeTaskLocalVariables = "";
	private String includeProcessVariables = "";
	private String tenantId = "";
	private String tenantIdLike = "";
	private String withoutTenantId = "";

	public JSONObject getJsonURL() {

		JSONObject getJsonURL = getJsonPage();

		if (!"".equals(taskId))
			getJsonURL.put("taskId", taskId);
		if (!"".equals(processInstanceId))
			getJsonURL.put("processInstanceId", processInstanceId);
		if (!"".equals(processDefinitionKey))
			getJsonURL.put("processDefinitionKey", processDefinitionKey);
		if (!"".equals(processDefinitionKeyLike))
			getJsonURL.put("processDefinitionKeyLike", processDefinitionKeyLike);
		if (!"".equals(processDefinitionId))
			getJsonURL.put("processDefinitionId", processDefinitionId);
		if (!"".equals(processDefinitionName))
			getJsonURL.put("processDefinitionName", processDefinitionName);
		if (!"".equals(processDefinitionNameLike))
			getJsonURL.put("processDefinitionNameLike", processDefinitionNameLike);
		if (!"".equals(processBusinessKey))
			getJsonURL.put("processBusinessKey", processBusinessKey);
		if (!"".equals(processBusinessKeyLike))
			getJsonURL.put("processBusinessKeyLike", processBusinessKeyLike);
		if (!"".equals(executionId))
			getJsonURL.put("executionId", executionId);
		if (!"".equals(taskDefinitionKey))
			getJsonURL.put("taskDefinitionKey", taskDefinitionKey);
		if (!"".equals(taskName))
			getJsonURL.put("taskName", taskName);
		if (!"".equals(taskNameLike))
			getJsonURL.put("taskNameLike", taskNameLike);
		if (!"".equals(taskDescription))
			getJsonURL.put("taskDescription", taskDescription);
		if (!"".equals(taskDescriptionLike))
			getJsonURL.put("taskDescriptionLike", taskDescriptionLike);
		if (!"".equals(taskDefinitionKey))
			getJsonURL.put("taskDefinitionKey", taskDefinitionKey);
		if (!"".equals(taskDeleteReason))
			getJsonURL.put("taskDeleteReason", taskDeleteReason);
		if (!"".equals(taskDeleteReasonLike))
			getJsonURL.put("taskDeleteReasonLike", taskDeleteReasonLike);
		if (!"".equals(taskAssignee))
			getJsonURL.put("taskAssignee", taskAssignee);
		if (!"".equals(taskAssigneeLike))
			getJsonURL.put("taskAssigneeLike", taskAssigneeLike);
		if (!"".equals(taskOwner))
			getJsonURL.put("taskOwner", taskOwner);
		if (!"".equals(taskOwnerLike))
			getJsonURL.put("taskOwnerLike", taskOwnerLike);
		if (!"".equals(taskInvolvedUser))
			getJsonURL.put("taskInvolvedUser", taskInvolvedUser);
		if (!"".equals(taskPriority))
			getJsonURL.put("taskPriority", taskPriority);
		if (!"".equals(finished))
			getJsonURL.put("finished", finished);
		if (!"".equals(processFinished))
			getJsonURL.put("processFinished", processFinished);
		if (!"".equals(parentTaskId))
			getJsonURL.put("parentTaskId", parentTaskId);
		if (!"".equals(dueDate))
			getJsonURL.put("dueDate", dueDate);
		if (!"".equals(dueDateAfter))
			getJsonURL.put("dueDateAfter", dueDateAfter);
		if (!"".equals(dueDateBefore))
			getJsonURL.put("dueDateBefore", dueDateBefore);
		if (!"".equals(withoutDueDate))
			getJsonURL.put("withoutDueDate", withoutDueDate);
		if (!"".equals(taskCompletedOn))
			getJsonURL.put("taskCompletedOn", taskCompletedOn);
		if (!"".equals(taskCompletedAfter))
			getJsonURL.put("taskCompletedAfter", taskCompletedAfter);
		if (!"".equals(taskCompletedBefore))
			getJsonURL.put("taskCompletedBefore", taskCompletedBefore);
		if (!"".equals(taskCreatedOn))
			getJsonURL.put("taskCreatedOn", taskCreatedOn);
		if (!"".equals(taskCreatedBefore))
			getJsonURL.put("taskCreatedBefore", taskCreatedBefore);
		if (!"".equals(taskCreatedAfter))
			getJsonURL.put("taskCreatedAfter", taskCreatedAfter);
		if (!"".equals(includeTaskLocalVariables))
			getJsonURL.put("includeTaskLocalVariables", includeTaskLocalVariables);
		if (!"".equals(includeProcessVariables))
			getJsonURL.put("includeProcessVariables", includeProcessVariables);
		if (!"".equals(tenantId))
			getJsonURL.put("tenantId", tenantId);
		if (!"".equals(tenantIdLike))
			getJsonURL.put("tenantIdLike", tenantIdLike);
		if (!"".equals(withoutTenantId))
			getJsonURL.put("withoutTenantId", withoutTenantId);

		return getJsonURL;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public String getProcessDefinitionKey() {
		return processDefinitionKey;
	}

	public void setProcessDefinitionKey(String processDefinitionKey) {
		this.processDefinitionKey = processDefinitionKey;
	}

	public String getProcessDefinitionKeyLike() {
		return processDefinitionKeyLike;
	}

	public void setProcessDefinitionKeyLike(String processDefinitionKeyLike) {
		this.processDefinitionKeyLike = processDefinitionKeyLike;
	}

	public String getProcessDefinitionId() {
		return processDefinitionId;
	}

	public void setProcessDefinitionId(String processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
	}

	public String getProcessDefinitionName() {
		return processDefinitionName;
	}

	public void setProcessDefinitionName(String processDefinitionName) {
		this.processDefinitionName = processDefinitionName;
	}

	public String getProcessDefinitionNameLike() {
		return processDefinitionNameLike;
	}

	public void setProcessDefinitionNameLike(String processDefinitionNameLike) {
		this.processDefinitionNameLike = processDefinitionNameLike;
	}

	public String getProcessBusinessKey() {
		return processBusinessKey;
	}

	public void setProcessBusinessKey(String processBusinessKey) {
		this.processBusinessKey = processBusinessKey;
	}

	public String getProcessBusinessKeyLike() {
		return processBusinessKeyLike;
	}

	public void setProcessBusinessKeyLike(String processBusinessKeyLike) {
		this.processBusinessKeyLike = processBusinessKeyLike;
	}

	public String getExecutionId() {
		return executionId;
	}

	public void setExecutionId(String executionId) {
		this.executionId = executionId;
	}

	public String getTaskDefinitionKey() {
		return taskDefinitionKey;
	}

	public void setTaskDefinitionKey(String taskDefinitionKey) {
		this.taskDefinitionKey = taskDefinitionKey;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskNameLike() {
		return taskNameLike;
	}

	public void setTaskNameLike(String taskNameLike) {
		this.taskNameLike = taskNameLike;
	}

	public String getTaskDescription() {
		return taskDescription;
	}

	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}

	public String getTaskDescriptionLike() {
		return taskDescriptionLike;
	}

	public void setTaskDescriptionLike(String taskDescriptionLike) {
		this.taskDescriptionLike = taskDescriptionLike;
	}

	public String getTaskDeleteReason() {
		return taskDeleteReason;
	}

	public void setTaskDeleteReason(String taskDeleteReason) {
		this.taskDeleteReason = taskDeleteReason;
	}

	public String getTaskDeleteReasonLike() {
		return taskDeleteReasonLike;
	}

	public void setTaskDeleteReasonLike(String taskDeleteReasonLike) {
		this.taskDeleteReasonLike = taskDeleteReasonLike;
	}

	public String getTaskAssignee() {
		return taskAssignee;
	}

	public void setTaskAssignee(String taskAssignee) {
		this.taskAssignee = taskAssignee;
	}

	public String getTaskAssigneeLike() {
		return taskAssigneeLike;
	}

	public void setTaskAssigneeLike(String taskAssigneeLike) {
		this.taskAssigneeLike = taskAssigneeLike;
	}

	public String getTaskOwner() {
		return taskOwner;
	}

	public void setTaskOwner(String taskOwner) {
		this.taskOwner = taskOwner;
	}

	public String getTaskOwnerLike() {
		return taskOwnerLike;
	}

	public void setTaskOwnerLike(String taskOwnerLike) {
		this.taskOwnerLike = taskOwnerLike;
	}

	public String getTaskInvolvedUser() {
		return taskInvolvedUser;
	}

	public void setTaskInvolvedUser(String taskInvolvedUser) {
		this.taskInvolvedUser = taskInvolvedUser;
	}

	public String getTaskPriority() {
		return taskPriority;
	}

	public void setTaskPriority(String taskPriority) {
		this.taskPriority = taskPriority;
	}

	public String getFinished() {
		return finished;
	}

	public void setFinished(String finished) {
		this.finished = finished;
	}

	public String getProcessFinished() {
		return processFinished;
	}

	public void setProcessFinished(String processFinished) {
		this.processFinished = processFinished;
	}

	public String getParentTaskId() {
		return parentTaskId;
	}

	public void setParentTaskId(String parentTaskId) {
		this.parentTaskId = parentTaskId;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public String getDueDateAfter() {
		return dueDateAfter;
	}

	public void setDueDateAfter(String dueDateAfter) {
		this.dueDateAfter = dueDateAfter;
	}

	public String getDueDateBefore() {
		return dueDateBefore;
	}

	public void setDueDateBefore(String dueDateBefore) {
		this.dueDateBefore = dueDateBefore;
	}

	public String getWithoutDueDate() {
		return withoutDueDate;
	}

	public void setWithoutDueDate(String withoutDueDate) {
		this.withoutDueDate = withoutDueDate;
	}

	public String getTaskCompletedOn() {
		return taskCompletedOn;
	}

	public void setTaskCompletedOn(String taskCompletedOn) {
		this.taskCompletedOn = taskCompletedOn;
	}

	public String getTaskCompletedAfter() {
		return taskCompletedAfter;
	}

	public void setTaskCompletedAfter(String taskCompletedAfter) {
		this.taskCompletedAfter = taskCompletedAfter;
	}

	public String getTaskCompletedBefore() {
		return taskCompletedBefore;
	}

	public void setTaskCompletedBefore(String taskCompletedBefore) {
		this.taskCompletedBefore = taskCompletedBefore;
	}

	public String getTaskCreatedOn() {
		return taskCreatedOn;
	}

	public void setTaskCreatedOn(String taskCreatedOn) {
		this.taskCreatedOn = taskCreatedOn;
	}

	public String getTaskCreatedBefore() {
		return taskCreatedBefore;
	}

	public void setTaskCreatedBefore(String taskCreatedBefore) {
		this.taskCreatedBefore = taskCreatedBefore;
	}

	public String getTaskCreatedAfter() {
		return taskCreatedAfter;
	}

	public void setTaskCreatedAfter(String taskCreatedAfter) {
		this.taskCreatedAfter = taskCreatedAfter;
	}

	public String getIncludeTaskLocalVariables() {
		return includeTaskLocalVariables;
	}

	public void setIncludeTaskLocalVariables(String includeTaskLocalVariables) {
		this.includeTaskLocalVariables = includeTaskLocalVariables;
	}

	public String getIncludeProcessVariables() {
		return includeProcessVariables;
	}

	public void setIncludeProcessVariables(String includeProcessVariables) {
		this.includeProcessVariables = includeProcessVariables;
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
