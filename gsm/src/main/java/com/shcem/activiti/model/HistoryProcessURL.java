/* ========================================
 * System Name　　：    化交线上平台
 * SubSystem Name ：化交服务核心工具集
 * File Name: HistoryProcessURL
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
public class HistoryProcessURL extends Page {
	
	private String processInstanceId = "";
	private String processDefinitionKey = "";
	private String processDefinitionId = "";
	private String businessKey = "";
	private String involvedUser = "";
	private String finished = "";
	private String superProcessInstanceId = "";
	private String excludeSubprocesses = "";
	private String finishedAfter = "";
	private String finishedBefore = "";
	private String startedAfter = "";
	private String startedBefore = "";
	private String startedBy = "";
	private String includeProcessVariables = "";
	private String tenantId = "";
	private String tenantIdLike = "";
	private String withoutTenantId = "";

	public JSONObject getJsonURL() {

		JSONObject getJsonURL = getJsonPage();

		if (!"".equals(processInstanceId))
			getJsonURL.put("processInstanceId", processInstanceId);
		if (!"".equals(processDefinitionKey))
			getJsonURL.put("processDefinitionKey", processDefinitionKey);
		if (!"".equals(processDefinitionId))
			getJsonURL.put("processDefinitionId", processDefinitionId);
		if (!"".equals(businessKey))
			getJsonURL.put("businessKey", businessKey);
		if (!"".equals(involvedUser))
			getJsonURL.put("involvedUser", involvedUser);
		if (!"".equals(finished))
			getJsonURL.put("finished", finished);
		if (!"".equals(superProcessInstanceId))
			getJsonURL.put("superProcessInstanceId", superProcessInstanceId);
		if (!"".equals(excludeSubprocesses))
			getJsonURL.put("excludeSubprocesses", excludeSubprocesses);
		if (!"".equals(finishedAfter))
			getJsonURL.put("finishedAfter", finishedAfter);
		if (!"".equals(finishedBefore))
			getJsonURL.put("finishedBefore", finishedBefore);
		if (!"".equals(startedAfter))
			getJsonURL.put("startedAfter", startedAfter);
		if (!"".equals(startedBefore))
			getJsonURL.put("startedBefore", startedBefore);
		if (!"".equals(startedBy))
			getJsonURL.put("startedBy", startedBy);
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

	public String getProcessDefinitionId() {
		return processDefinitionId;
	}

	public void setProcessDefinitionId(String processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
	}

	public String getBusinessKey() {
		return businessKey;
	}

	public void setBusinessKey(String businessKey) {
		this.businessKey = businessKey;
	}

	public String getInvolvedUser() {
		return involvedUser;
	}

	public void setInvolvedUser(String involvedUser) {
		this.involvedUser = involvedUser;
	}

	public String getFinished() {
		return finished;
	}

	public void setFinished(String finished) {
		this.finished = finished;
	}

	public String getSuperProcessInstanceId() {
		return superProcessInstanceId;
	}

	public void setSuperProcessInstanceId(String superProcessInstanceId) {
		this.superProcessInstanceId = superProcessInstanceId;
	}

	public String getExcludeSubprocesses() {
		return excludeSubprocesses;
	}

	public void setExcludeSubprocesses(String excludeSubprocesses) {
		this.excludeSubprocesses = excludeSubprocesses;
	}

	public String getFinishedAfter() {
		return finishedAfter;
	}

	public void setFinishedAfter(String finishedAfter) {
		this.finishedAfter = finishedAfter;
	}

	public String getFinishedBefore() {
		return finishedBefore;
	}

	public void setFinishedBefore(String finishedBefore) {
		this.finishedBefore = finishedBefore;
	}

	public String getStartedAfter() {
		return startedAfter;
	}

	public void setStartedAfter(String startedAfter) {
		this.startedAfter = startedAfter;
	}

	public String getStartedBefore() {
		return startedBefore;
	}

	public void setStartedBefore(String startedBefore) {
		this.startedBefore = startedBefore;
	}

	public String getStartedBy() {
		return startedBy;
	}

	public void setStartedBy(String startedBy) {
		this.startedBy = startedBy;
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
