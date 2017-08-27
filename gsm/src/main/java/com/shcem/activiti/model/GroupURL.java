/* ========================================
 * System Name　　：    化交线上平台
 * SubSystem Name ：化交服务核心工具集
 * File Name: GroupURL
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
 * GroupURL
 * 
 * @author chiyong
 * @version 1.0
 */
public class GroupURL extends Page {
	
	private String id = "";
	private String name = "";
	private String type = "";
	private String nameLike = "";
	private String member = "";
	private String potentialStarter = "";

	public JSONObject getJsonURL() {

		JSONObject getJsonURL = getJsonPage();

		if (!"".equals(id))
			getJsonURL.put("id", id);
		if (!"".equals(name))
			getJsonURL.put("name", name);
		if (!"".equals(type))
			getJsonURL.put("type", type);
		if (!"".equals(nameLike))
			getJsonURL.put("nameLike", nameLike);
		if (!"".equals(member))
			getJsonURL.put("member", member);
		if (!"".equals(potentialStarter))
			getJsonURL.put("potentialStarter", potentialStarter);

		return getJsonURL;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNameLike() {
		return nameLike;
	}

	public void setNameLike(String nameLike) {
		this.nameLike = nameLike;
	}

	public String getMember() {
		return member;
	}

	public void setMember(String member) {
		this.member = member;
	}

	public String getPotentialStarter() {
		return potentialStarter;
	}

	public void setPotentialStarter(String potentialStarter) {
		this.potentialStarter = potentialStarter;
	}

}
