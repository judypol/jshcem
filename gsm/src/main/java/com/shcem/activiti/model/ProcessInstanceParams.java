///* ========================================
// * System Name　　：    化交线上平台
// * SubSystem Name ：化交服务核心工具集
// * File Name: ProcessInstanceParams
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
// * ProcessInstanceParams
// *
// * @author chiyong
// * @version 1.0
// */
//public class ProcessInstanceParams {
//	private String processDefinitionKey = "";
//	private String businessKey = "";
//	private List<VariableParams> variables = new ArrayList<VariableParams>();
//
//	public JSONObject getJsonParams() {
//		JSONObject jObj = new JSONObject();
//		JSONArray jArray = new JSONArray();
//
//		for (VariableParams vars : variables) {
//			jArray.add(vars.getJsonObject());
//		}
//		if(jArray.size()>0){
//			jObj.put("variables", jArray);
//		}
//
//		jObj.put("processDefinitionKey", processDefinitionKey);
//		jObj.put("businessKey", businessKey);
//		return jObj;
//
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
//	public String getBusinessKey() {
//		return businessKey;
//	}
//
//	public void setBusinessKey(String businessKey) {
//		this.businessKey = businessKey;
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
