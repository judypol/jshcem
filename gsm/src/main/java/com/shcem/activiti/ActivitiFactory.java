/* ========================================
 * System Name　　：    化交线上平台
 * SubSystem Name ：化交服务核心工具集
 * File Name: ActivitiFactory
 * ----------------------------------------
 * Create Date/Change History 
 * ----------------------------------------
 * 04/29/16 　池 永   Create
 * 
 * 
 * ----------------------------------------
 * Copyright (c) SCEM . All rights reserved.
 */
package com.shcem.activiti;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.shcem.activiti.model.User;

import java.util.ArrayList;
import java.util.Map;

/**
 * ActivitiFactory
 * 
 * @author chiyong
 * @version 1.0
 */
public class ActivitiFactory {

	/**
	 * 取得Activiti的Post数据
	 * 
	 * @param key 流程ID
	 * @param Userid
	 * @param jsoData
	 * @return
	 * @throws Exception
	 */
	public static String getActivitiRequestData(String key, String Userid, JSONObject jsoData) throws Exception {
		JSONObject rtnJsoObj = new JSONObject();
		String activitiDef = "";

		// 检查是否有用户信息
		if (Userid == null || "".equals(Userid)) {
			throw new Exception("Activiti action need authorization user, please input valid userid.");
		}

		rtnJsoObj.put("user", Userid);

		// 取得Actitivi内容
		try {
			ActivitiDefinition activitiDefCode = Enum.valueOf(ActivitiDefinition.class, key);
			activitiDef = activitiDefCode.getValue();
		} catch (Exception e) {
			throw new Exception("Can not find the activiti by key(" + key + ") from activiti defination list.");
		}

		String[] strActiviti = activitiDef.split(",");
		String method = null;
		String url = null;
		// 判断Activiti内容是否合法
		if (strActiviti.length < 2 || strActiviti.length > 3) {
			throw new Exception("Activiti(with key(" + key + ")) content has error.");
		}

		method = strActiviti[0];
		rtnJsoObj.put("method", method);
		url = strActiviti[1];
		// 检查Activiti内容是否需要Request Body
		if (strActiviti.length == 3) {
			if (!jsoData.containsKey("params")) {
				throw new Exception("Activiti(method:" + method + ", url:" + url + ") need request body.");
			} else {
				try {
					// rtnJsoObj.put("params", jsoData.getJSONObject("params"));
					rtnJsoObj.put("params", jsoData.get("params"));
				} catch (JSONException e) {
					throw new Exception("The request body of activiti(method:" + method + ", url:" + url
							+ ") must be JSON object.");
				}
			}
		}

		// 取得URL参数集合
		ArrayList<String> urlPms = getUrlPms(url);
		// 检查是否有必要的URL参数
		for (int i = 0; i < urlPms.size(); i++) {
			if (!jsoData.containsKey(urlPms.get(i))) {
				throw new Exception("Activiti(method:" + method + ", url:" + url + ") need URL Parameter("
						+ urlPms.get(i) + ")");
			} else {
				String rpcSource = jsoData.getString(urlPms.get(i));
				url = url.replaceAll("\\{" + urlPms.get(i) + "\\}", rpcSource);
			}
		}

		String strURL = null;
		if (!jsoData.containsKey("url")) {
			strURL = appendURL(url, jsoData.getJSONObject("url"));
		} else {
			strURL = url;
		}
		rtnJsoObj.put("url", strURL);

		// 做成Post数据
		String postdata;
		StringBuilder sb = new StringBuilder();
		sb.append("{\"json\":");
		sb.append(rtnJsoObj.toString());
		sb.append("}");
		postdata = sb.toString();

		return postdata;
	}

	/**
	 * 取得URL参数集合
	 * 
	 * @param url
	 * @return
	 */
	private static ArrayList<String> getUrlPms(String url) {
		ArrayList<String> strUrlPms = new ArrayList<String>();
		String urlTemp = url;
		String pmTemp = "";

		while (urlTemp.indexOf("{") >= 0) {
			urlTemp = urlTemp.substring(urlTemp.indexOf("{") + 1);

			if (urlTemp.indexOf("}") >= 0) {
				pmTemp = urlTemp.substring(0, urlTemp.indexOf("}"));
				urlTemp = urlTemp.substring(urlTemp.indexOf("}") + 1);
			} else {
				pmTemp = urlTemp;
			}
			if (!"".equals(pmTemp)) {
				strUrlPms.add(pmTemp);
			}
		}

		return strUrlPms;
	}

	/**
	 * 取得URL参数集合
	 * 
	 * @param url
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private static String appendURL(String url, JSONObject jsoData) {
		String appendURL = url;
		int intStart = 0;

		for(Map.Entry<String, Object> entry :jsoData.entrySet()){
            String key = entry.getKey();
            String value = entry.getValue().toString();

            if (intStart == 0) {
                intStart = 1;
                appendURL = appendURL + "?" + key + "=" + value;
            } else {
                appendURL = appendURL + "&" + key + "=" + value;
            }
        }
		return appendURL;
	}

	public static void main(String[] args) {
		User acitiviiUser = new User();
		acitiviiUser.getParams().setId("chiyong");
		acitiviiUser.getParams().setFirstName("chi");
		acitiviiUser.getParams().setEmail("chiyong@shcem.com");
		acitiviiUser.getParams().setPassword("chiyong");
		acitiviiUser.setUserId("chiyong");
		acitiviiUser.setKey("123");
		acitiviiUser.setEmail("test@shcem.com");
		acitiviiUser.setStart("0");
		acitiviiUser.setSize("10");
		JSONObject jso =acitiviiUser.getJsonObject();
		
		String rtn = null;
		try {
			rtn = getActivitiRequestData("Update_Info_User", "kermit", jso);
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
		System.out.print(rtn.toString());
	}
}
