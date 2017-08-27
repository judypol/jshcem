/* ========================================
 * System Name　　：    化交线上平台
 * SubSystem Name ：化交服务核心工具集
 * File Name: JobsURL
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
public class JobsURL extends Page {
	
	private String id = "";
	private String processInstanceId = "";
	private String executionId = "";
	private String processDefinitionId = "";
	private String withRetriesLeft = "";
	private String executable = "";
	private String timersOnly = "";
	private String messagesOnly = "";
	private String withException = "";
	private String dueBefore = "";
	private String dueAfter = "";
	private String exceptionMessage = "";
	private String tenantId = "";
	private String tenantIdLike = "";
	private String withoutTenantId = "";

	public JSONObject getJsonURL() {

		JSONObject getJsonURL = getJsonPage();

		if (!"".equals(id))
			getJsonURL.put("id", id);
		if (!"".equals(processInstanceId))
			getJsonURL.put("processInstanceId", processInstanceId);
		if (!"".equals(executionId))
			getJsonURL.put("executionId", executionId);
		if (!"".equals(processDefinitionId))
			getJsonURL.put("processDefinitionId", processDefinitionId);
		if (!"".equals(withRetriesLeft))
			getJsonURL.put("withRetriesLeft", withRetriesLeft);
		if (!"".equals(executable))
			getJsonURL.put("executable", executable);
		if (!"".equals(timersOnly))
			getJsonURL.put("timersOnly", timersOnly);
		if (!"".equals(messagesOnly))
			getJsonURL.put("messagesOnly", messagesOnly);
		if (!"".equals(withException))
			getJsonURL.put("withException", withException);
		if (!"".equals(dueBefore))
			getJsonURL.put("dueBefore", dueBefore);
		if (!"".equals(dueAfter))
			getJsonURL.put("dueAfter", dueAfter);
		if (!"".equals(exceptionMessage))
			getJsonURL.put("exceptionMessage", exceptionMessage);
		if (!"".equals(tenantId))
			getJsonURL.put("tenantId", tenantId);
		if (!"".equals(tenantIdLike))
			getJsonURL.put("tenantIdLike", tenantIdLike);
		if (!"".equals(withoutTenantId))
			getJsonURL.put("withoutTenantId", withoutTenantId);

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

	public String getProcessDefinitionId() {
		return processDefinitionId;
	}

	public void setProcessDefinitionId(String processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
	}

	public String getWithRetriesLeft() {
		return withRetriesLeft;
	}

	public void setWithRetriesLeft(String withRetriesLeft) {
		this.withRetriesLeft = withRetriesLeft;
	}

	public String getExecutable() {
		return executable;
	}

	public void setExecutable(String executable) {
		this.executable = executable;
	}

	public String getTimersOnly() {
		return timersOnly;
	}

	public void setTimersOnly(String timersOnly) {
		this.timersOnly = timersOnly;
	}

	public String getMessagesOnly() {
		return messagesOnly;
	}

	public void setMessagesOnly(String messagesOnly) {
		this.messagesOnly = messagesOnly;
	}

	public String getWithException() {
		return withException;
	}

	public void setWithException(String withException) {
		this.withException = withException;
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

	public String getExceptionMessage() {
		return exceptionMessage;
	}

	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
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
