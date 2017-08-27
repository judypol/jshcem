/* ========================================
 * System Name　　：    化交线上平台
 * SubSystem Name ：化交服务核心工具集
 * File Name: ModelURL
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
public class ModelURL extends Page {

	private String id ="";
	private String category ="";
	private String categoryLike ="";
	private String categoryNotEquals ="";
	private String name ="";
	private String nameLike ="";
	private String key ="";
	private String deploymentId ="";
	private String version ="";
	private String latestVersion ="";
	private String deployed ="";
	private String tenantId ="";
	private String tenantIdLike ="";
	private String withoutTenantId ="";

	public JSONObject getJsonURL() {

		JSONObject getJsonURL = getJsonPage();

		if (!"".equals(id))
			getJsonURL.put("id", id);
		if (!"".equals(category))
			getJsonURL.put("category", category);
		if (!"".equals(categoryLike))
			getJsonURL.put("categoryLike", categoryLike);
		if (!"".equals(categoryNotEquals))
			getJsonURL.put("categoryNotEquals", categoryNotEquals);
		if (!"".equals(name))
			getJsonURL.put("name", name);
		if (!"".equals(nameLike))
			getJsonURL.put("nameLike", nameLike);
		if (!"".equals(key))
			getJsonURL.put("key", key);
		if (!"".equals(deploymentId))
			getJsonURL.put("deploymentId", deploymentId);
		if (!"".equals(version))
			getJsonURL.put("version", version);
		if (!"".equals(latestVersion))
			getJsonURL.put("latestVersion", latestVersion);
		if (!"".equals(deployed))
			getJsonURL.put("deployed", deployed);
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCategoryLike() {
		return categoryLike;
	}

	public void setCategoryLike(String categoryLike) {
		this.categoryLike = categoryLike;
	}

	public String getCategoryNotEquals() {
		return categoryNotEquals;
	}

	public void setCategoryNotEquals(String categoryNotEquals) {
		this.categoryNotEquals = categoryNotEquals;
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

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getDeploymentId() {
		return deploymentId;
	}

	public void setDeploymentId(String deploymentId) {
		this.deploymentId = deploymentId;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getLatestVersion() {
		return latestVersion;
	}

	public void setLatestVersion(String latestVersion) {
		this.latestVersion = latestVersion;
	}

	public String getDeployed() {
		return deployed;
	}

	public void setDeployed(String deployed) {
		this.deployed = deployed;
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
