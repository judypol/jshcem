///* ========================================
// * System Name　　：化交线上平台
// * SubSystem Name ：化交站点核心工具集
// * File Name: Constants
// * ----------------------------------------
// * Create Date/Change History
// * ----------------------------------------
// * 2017/8/22 　lizhihua   Create
// *
// *
// * ----------------------------------------
// * Copyright (c) SCEM . All rights reserved.
// */
//package com.shcem.component;
//
//import com.alibaba.fastjson.JSONException;
//import com.alibaba.fastjson.JSONObject;
//import com.shcem.common.PropertiesLoader;
//import com.shcem.common.PropertyUtil;
//import com.shcem.constants.SystemDefine;
//import com.shcem.enums.ResultCode;
//import com.shcem.netty.NettyClient;
//import com.shcem.activiti.ActivitiFactory;
//import com.shcem.activiti.model.*;
//
//import java.util.List;
//import java.util.Map;
//
///**
// * @author lizhihua
// * @version 1.0
// */
//public class ActivitiManager extends BaseManager {
//    /**
//     * 调用Activiti的API
//     *
//     * @param postData
//     * @return
//     */
//    protected JSONObject postActivitiApi(String postData, String mode) {
//
//        PropertiesLoader clientProperty = PropertyUtil.create(SystemDefine.ACTIVITI_PROPERITES_FILE);
//
//        // 取得中央代理服务器的信息
//        String ipaddress = clientProperty.getProperty(mode + "_activitiapi_IP");
//        String strPort = clientProperty.getProperty("activitiapi_port");
//
//        int port;
//        try {
//            port = Integer.parseInt(strPort);
//        } catch (NumberFormatException e1) {
//            // default port;
//            port = 5420;
//        }
//
//        /**
//         * 取得数据 Start
//         */
//        NettyClient ntc = new NettyClient(ipaddress, port, postData,
//                "/activitiapi");
//        String response = null;
//        try {
//            Map<String, String> m = ntc.httpPost();
//            response = m.get("response");
//        } catch (Exception e) {
//            return setReturnData("10116", e.getMessage());
//        }
//        /**
//         * 取得数据 End
//         */
//
//        // 数据转换成JSON对象
//        JSONObject jsoSource;
//        try {
//            jsoSource=JSONObject.parseObject(response);
//            //jsoSource = new JSONObject(response);
//        } catch (JSONException e) {
//            return setReturnData("10116", e.getMessage());
//        }
//
//        return jsoSource;
//    }
//    /**
//     *
//     *
//     * @param
//     * @param
//     * @return
//     */
//    private JSONObject setReturnData(String code, String... msg) {
//
//        JSONObject result = new JSONObject();
//
//        ResultCode rstCd = Enum.valueOf(ResultCode.class, "CODE" + code);
//
//        StringBuffer sbInfo = new StringBuffer(msg.length + 1);
//
//        sbInfo.append(rstCd.getName());
//        for (int i = 0; i < msg.length; i++) {
//            sbInfo.append(msg[i]);
//        }
//
//        result.put("CODE", rstCd.getStringValue());
//        result.put("INFO", sbInfo.toString());
//        result.put("DATA", "none");
//
//        //HessianHeaderContext.close();
//
//        return result;
//    }
//
//    /**
//     * 开启流程
//     *
//     * @param businessKey
//     *            业务ID
//     * @param definitionKey
//     *            流程key
//     * @param mode
//     * @param userID
//     * @param variables
//     * @return
//     */
//    private JSONObject startProcessInstance(String businessKey,
//                                            String definitionKey, String mode, String userID,
//                                            List<VariableParams> variables) {
//        this.log.info(this.getClass().getName()
//                + " startProcessInstance(开启流程) Start");
//        ProcessInstance processInstance = new ProcessInstance();
//
//        ProcessInstanceParams piParams = processInstance.getPiParams();
//        piParams.setBusinessKey(businessKey);
//        piParams.setProcessDefinitionKey(definitionKey);
//
//        piParams.setVariables(variables);
//        JSONObject rtnJso = null;
//        try {
//            String postData = ActivitiFactory.getActivitiRequestData(
//                    "Start_Process_Instance", userID,
//                    processInstance.getJsonObject());
//            rtnJso = postActivitiApi(postData, mode);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        this.log.info(this.getClass().getName()
//                + " startProcessInstance(开启流程) End");
//        return rtnJso;
//    }
//
//    /**
//     * 认领任务
//     *
//     * @param taskID
//     *            任务ID
//     * @param assignee
//     *            认领人(当前操作人)
//     * @param mode
//     * @param userID
//     * @return
//     */
//    private JSONObject claimTask(String taskID, String assignee, String mode,
//                                 String userID) {
//        this.log.info(this.getClass().getName() + " claimTask(认领任务) Start");
//        Task task = new Task();
//        TaskParams taskParams = task.getParams();
//        task.setTaskId(taskID);// 流程ID
//        taskParams.ClaimTask(assignee);// 认领人
//        JSONObject rtnJso = null;
//        try {
//            /**
//             * 认领任务
//             */
//            String postData = ActivitiFactory.getActivitiRequestData(
//                    "Action_Task", userID, task.getJsonObject());
//            rtnJso = postActivitiApi(postData, mode);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        this.log.info(this.getClass().getName() + " claimTask(认领任务) End");
//        return rtnJso;
//    }
//
//    /**
//     * 完成节点任务
//     *
//     * @param variables
//     * @param mode
//     * @param userID
//     * @param taskID
//     * @return
//     */
//    private JSONObject completeTask(List<VariableParams> variables,
//                                    String mode, String userID, String taskID) {
//        this.log.info(this.getClass().getName() + " completeTask(完成节点任务) Start");
//        Task task = new Task();
//        TaskParams taskParams = task.getParams();
//        task.setTaskId(taskID);// 流程ID
//        taskParams.CompleteTask();
//        taskParams.setVariables(variables);
//
//        JSONObject rtnJso = null;
//        try {
//            String postData = ActivitiFactory.getActivitiRequestData(
//                    "Action_Task", userID, task.getJsonObject());
//            System.out.println("请求参数  :" + postData);
//            rtnJso = postActivitiApi(postData, mode);
//        } catch (Exception e) {
//            this.log.error(this.getClass().getName()
//                    + " completeTask(完成节点任务)失败： " + e.getMessage());
//            e.printStackTrace();
//            rtnJso = null;
//        }
//        this.log.info(this.getClass().getName() + " completeTask(完成节点任务) End");
//        return rtnJso;
//    }
//}
