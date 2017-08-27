/* ========================================
 * System Name　　：    化交线上平台
 * SubSystem Name ：化交服务核心工具集
 * File Name: TaskURL
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
 * TaskURL
 * 
 * @author chiyong
 * @version 1.0
 */
public class TaskURL extends Page {
	private String name = "";
	private String nameLike = "";
	private String description = "";
	private String priority = "";
	private String minimumPriority = "";
	private String maximumPriority = "";
	private String assignee = "";
	private String assigneeLike = "";
	private String owner = "";
	private String ownerLike = "";
	private String unassigned = "";
	private String delegationState = "";
	private String candidateUser = "";
	private String candidateGroup = "";
	private String candidateGroups = "";
	private String involvedUser = "";
	private String taskDefinitionKey = "";
	private String taskDefinitionKeyLike = "";
	private String processInstanceId = "";
	private String processInstanceBusinessKey = "";
	private String processInstanceBusinessKeyLike = "";
	private String processDefinitionKey = "";
	private String processDefinitionKeyLike = "";
	private String processDefinitionName = "";
	private String processDefinitionNameLike = "";
	private String executionId = "";
	private String createdOn = "";
	private String createdBefore = "";
	private String createdAfter = "";
	private String dueOn = "";
	private String dueBefore = "";
	private String dueAfter = "";
	private String withoutDueDate = "";
	private String excludeSubTasks = "";
	private String active = "";
	private String includeTaskLocalVariables = "";
	private String includeProcessVariables = "";
	private String tenantId = "";
	private String tenantIdLike = "";
	private String withoutTenantId = "";
	private String candidateOrAssigned = "";

	public JSONObject getJsonURL() {

		JSONObject getJsonURL = getJsonPage();
		
		if (!"".equals(name))
			getJsonURL.put("name", name);
		if (!"".equals(nameLike))
			getJsonURL.put("nameLike", nameLike);
		if (!"".equals(description))
			getJsonURL.put("description", description);
		if (!"".equals(priority))
			getJsonURL.put("priority", priority);
		if (!"".equals(minimumPriority))
			getJsonURL.put("minimumPriority", minimumPriority);
		if (!"".equals(maximumPriority))
			getJsonURL.put("maximumPriority", maximumPriority);
		if (!"".equals(assignee))
			getJsonURL.put("assignee", assignee);
		if (!"".equals(assigneeLike))
			getJsonURL.put("assigneeLike", assigneeLike);
		if (!"".equals(owner))
			getJsonURL.put("owner", owner);
		if (!"".equals(ownerLike))
			getJsonURL.put("ownerLike", ownerLike);
		if (!"".equals(unassigned))
			getJsonURL.put("unassigned", unassigned);
		if (!"".equals(delegationState))
			getJsonURL.put("delegationState", delegationState);
		if (!"".equals(candidateUser))
			getJsonURL.put("candidateUser", candidateUser);
		if (!"".equals(candidateGroup))
			getJsonURL.put("candidateGroup", candidateGroup);
		if (!"".equals(candidateGroups))
			getJsonURL.put("candidateGroups", candidateGroups);
		if (!"".equals(involvedUser))
			getJsonURL.put("involvedUser", involvedUser);
		if (!"".equals(taskDefinitionKey))
			getJsonURL.put("taskDefinitionKey", taskDefinitionKey);
		if (!"".equals(taskDefinitionKeyLike))
			getJsonURL.put("taskDefinitionKeyLike", taskDefinitionKeyLike);
		if (!"".equals(processInstanceId))
			getJsonURL.put("processInstanceId", processInstanceId);
		if (!"".equals(processInstanceBusinessKey))
			getJsonURL.put("processInstanceBusinessKey", processInstanceBusinessKey);
		if (!"".equals(processInstanceBusinessKeyLike))
			getJsonURL.put("processInstanceBusinessKeyLike", processInstanceBusinessKeyLike);
		if (!"".equals(processDefinitionKey))
			getJsonURL.put("processDefinitionKey", processDefinitionKey);
		if (!"".equals(processDefinitionKeyLike))
			getJsonURL.put("processDefinitionKeyLike", processDefinitionKeyLike);
		if (!"".equals(processDefinitionName))
			getJsonURL.put("processDefinitionName", processDefinitionName);
		if (!"".equals(processDefinitionNameLike))
			getJsonURL.put("processDefinitionNameLike", processDefinitionNameLike);
		if (!"".equals(executionId))
			getJsonURL.put("executionId", executionId);
		if (!"".equals(createdOn))
			getJsonURL.put("createdOn", createdOn);
		if (!"".equals(createdBefore))
			getJsonURL.put("createdBefore", createdBefore);
		if (!"".equals(createdAfter))
			getJsonURL.put("createdAfter", createdAfter);
		if (!"".equals(dueOn))
			getJsonURL.put("dueOn", dueOn);
		if (!"".equals(dueBefore))
			getJsonURL.put("dueBefore", dueBefore);
		if (!"".equals(dueAfter))
			getJsonURL.put("dueAfter", dueAfter);
		if (!"".equals(withoutDueDate))
			getJsonURL.put("withoutDueDate", withoutDueDate);
		if (!"".equals(excludeSubTasks))
			getJsonURL.put("excludeSubTasks", excludeSubTasks);
		if (!"".equals(active))
			getJsonURL.put("active", active);
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
		if (!"".equals(candidateOrAssigned))
			getJsonURL.put("candidateOrAssigned", candidateOrAssigned);

		return getJsonURL;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameLike() {
		return nameLike;
	}

	public void setNameLike(String nameLike) {
		this.nameLike = nameLike;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getMinimumPriority() {
		return minimumPriority;
	}

	public void setMinimumPriority(String minimumPriority) {
		this.minimumPriority = minimumPriority;
	}

	public String getMaximumPriority() {
		return maximumPriority;
	}

	public void setMaximumPriority(String maximumPriority) {
		this.maximumPriority = maximumPriority;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public String getAssigneeLike() {
		return assigneeLike;
	}

	public void setAssigneeLike(String assigneeLike) {
		this.assigneeLike = assigneeLike;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getOwnerLike() {
		return ownerLike;
	}

	public void setOwnerLike(String ownerLike) {
		this.ownerLike = ownerLike;
	}

	public String getUnassigned() {
		return unassigned;
	}

	public void setUnassigned(String unassigned) {
		this.unassigned = unassigned;
	}

	public String getDelegationState() {
		return delegationState;
	}

	public void setDelegationState(String delegationState) {
		this.delegationState = delegationState;
	}

	public String getCandidateUser() {
		return candidateUser;
	}

	public void setCandidateUser(String candidateUser) {
		this.candidateUser = candidateUser;
	}

	public String getCandidateGroup() {
		return candidateGroup;
	}

	public void setCandidateGroup(String candidateGroup) {
		this.candidateGroup = candidateGroup;
	}

	public String getCandidateGroups() {
		return candidateGroups;
	}

	public void setCandidateGroups(String candidateGroups) {
		this.candidateGroups = candidateGroups;
	}

	public String getInvolvedUser() {
		return involvedUser;
	}

	public void setInvolvedUser(String involvedUser) {
		this.involvedUser = involvedUser;
	}

	public String getTaskDefinitionKey() {
		return taskDefinitionKey;
	}

	public void setTaskDefinitionKey(String taskDefinitionKey) {
		this.taskDefinitionKey = taskDefinitionKey;
	}

	public String getTaskDefinitionKeyLike() {
		return taskDefinitionKeyLike;
	}

	public void setTaskDefinitionKeyLike(String taskDefinitionKeyLike) {
		this.taskDefinitionKeyLike = taskDefinitionKeyLike;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public String getProcessInstanceBusinessKey() {
		return processInstanceBusinessKey;
	}

	public void setProcessInstanceBusinessKey(String processInstanceBusinessKey) {
		this.processInstanceBusinessKey = processInstanceBusinessKey;
	}

	public String getProcessInstanceBusinessKeyLike() {
		return processInstanceBusinessKeyLike;
	}

	public void setProcessInstanceBusinessKeyLike(String processInstanceBusinessKeyLike) {
		this.processInstanceBusinessKeyLike = processInstanceBusinessKeyLike;
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

	public String getExecutionId() {
		return executionId;
	}

	public void setExecutionId(String executionId) {
		this.executionId = executionId;
	}

	public String getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}

	public String getCreatedBefore() {
		return createdBefore;
	}

	public void setCreatedBefore(String createdBefore) {
		this.createdBefore = createdBefore;
	}

	public String getCreatedAfter() {
		return createdAfter;
	}

	public void setCreatedAfter(String createdAfter) {
		this.createdAfter = createdAfter;
	}

	public String getDueOn() {
		return dueOn;
	}

	public void setDueOn(String dueOn) {
		this.dueOn = dueOn;
	}

	public String getDueBefore() {
		return dueBefore;
	}

	public void setDueBefore(String dueBefore) {
		this.dueBefore = dueBefore;
	}

	public String getDueAfter() {
		return dueAfter;
	}

	public void setDueAfter(String dueAfter) {
		this.dueAfter = dueAfter;
	}

	public String getWithoutDueDate() {
		return withoutDueDate;
	}

	public void setWithoutDueDate(String withoutDueDate) {
		this.withoutDueDate = withoutDueDate;
	}

	public String getExcludeSubTasks() {
		return excludeSubTasks;
	}

	public void setExcludeSubTasks(String excludeSubTasks) {
		this.excludeSubTasks = excludeSubTasks;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
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

	public String getCandidateOrAssigned() {
		return candidateOrAssigned;
	}

	public void setCandidateOrAssigned(String candidateOrAssigned) {
		this.candidateOrAssigned = candidateOrAssigned;
	}

}
