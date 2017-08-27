/* ========================================
 * System Name　　：    化交线上平台
 * SubSystem Name ：化交服务核心工具集
 * File Name: ActivitiDefinition
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

/**
 * ActivitiDefinition
 * 
 * @author chiyong
 * @version 1.0
 */
public enum ActivitiDefinition {

	/**
	 * 模型
	 */
	// 获得模型列表
	Get_list_models("GET,repository/models"),
	// 获得一个模型
	Get_a_model("GET,repository/models/{modelId}"),
	// 更新模型
	Update_a_model("PUT,repository/models/{modelId},params"),
	// 新建模型
	Create_a_model("POST,repository/models,params"),
	// 删除模型
	Delete_a_model("DELETE,repository/models/{modelId}"),
	// 获得模型的可编译源码
	Get_ES_model("GET,repository/models/{modelId}/source"),
	// 获得模型的附加可编辑源码
	Get_Extra_ES_model("GET,repository/models/{modelId}/source-extra"),

	/**
	 * 流程实例
	 */
	// 获得流程实例
	Get_Process_Instance("GET,runtime/process-instances/{processInstanceId}"),
	// 删除流程实例
	Delete_Process_Instance("DELETE,runtime/process-instances/{processInstanceId}"),
	// 激活或挂起流程实例
	ActOrSup_Process_Instance("PUT,runtime/process-instances/{processInstanceId},params"),
	// 启动流程实例
	Start_Process_Instance("POST,runtime/process-instances,params"),
	// 显示流程实例列表
	List_Process_Instance("GET,runtime/process-instances"),
	// 查询流程实例
	Query_Process_Instance("POST,query/process-instances,params"),
	// 获得流程实例的流程图
	Get_Diagram_Process_Instance("GET,runtime/process-instances/{processInstanceId}/diagram"),
	// 获得流程实例的参与者
	Get_People_Process_Instance("GET,runtime/process-instances/{processInstanceId}/identitylinks"),
	// 为流程实例添加一个参与者
	Add_User_Process_Instance("POST,runtime/process-instances/{processInstanceId}/identitylinks,params"),
	// 删除一个流程实例的参与者
	Remove_User_Process_Instance(
			"DELETE,runtime/process-instances/{processInstanceId}/identitylinks/users/{userId}/{type}"),
	// 列出流程实例的变量
	List_Variables_Process_Instance("GET,runtime/process-instances/{processInstanceId}/variables"),
	// 获得流程实例的一个变量
	Get_Variable_Process_Instance("GET,runtime/process-instances/{processInstanceId}/variables/{variableName}"),
	// 创建（或更新）流程实例变量
	/**
	 * 使用POST时，会创建所有传递的变量。 如果流程实例中已经存在了其中一个变量，就会返回一个错误（409 - CONFLICT）。 使用PUT时，
	 * 流程实例中不存在的变量会被创建，已存在的变量会被更新，不会有任何错误。
	 */
	Create_Variable_Process_Instance("POST,runtime/process-instances/{processInstanceId}/variables,params"), Update_Variable_Process_Instance(
			"PUT,runtime/process-instances/{processInstanceId}/variables,params"),
	// 更新一个流程实例变量
	Update_Single_Var_Process_Instance(
			"PUT,runtime/process-instances/{processInstanceId}/variables/{variableName},params"),

	/**
	 * 任务
	 */
	// 获取任务
	Get_Task("GET,runtime/tasks/{taskId}"),
	// 任务列表
	List_Tasks("GET,runtime/tasks"),
	// 查询任务
	Query_Tasks("POST,query/tasks,params"),
	// 更新任务
	Update_Task("PUT,runtime/tasks/{taskId},params"),
	// 操作任务
	Action_Task("POST,runtime/tasks/{taskId},params"),
	// 删除任务
	Delete_Task("DELETE,runtime/tasks/{taskId}?cascadeHistory={cascadeHistory}&deleteReason={deleteReason}"),
	// 获得任务的变量
	Get_Variables_Task("GET,runtime/tasks/{taskId}/variables?scope={scope}"),
	// 获取任务的一个变量
	Get_One_Variable_Task("GET,runtime/tasks/{taskId}/variables/{variableName}?scope={scope}"),
	// 获取变量的二进制数据
	Get_BN_Variable_Task("GET,runtime/tasks/{taskId}/variables/{variableName}/data?scope={scope}"),
	// 创建任务变量
	Greate_Variable_Task("POST,runtime/tasks/{taskId}/variables,params"),
	// 更新任务的一个已有变量
	Update_Variable_Task("PUT,runtime/tasks/{taskId}/variables/{variableName},params"),
	// 删除任务变量
	Delete_Variable_Task("DELETE,runtime/tasks/{taskId}/variables/{variableName}?scope={scope}"),
	// 删除任务的所有局部变量
	Delete_All_Variables_Task("DELETE,runtime/tasks/{taskId}/variables"),
	// 获得任务的所有IdentityLink
	Get_IdentityLinks_Task("GET,runtime/tasks/{taskId}/identitylinks"),
	// 获得一个任务的所有组或用户的IdentityLink
	Get_IdentityLinks_User_Task("GET,runtime/tasks/{taskId}/identitylinks/users"), Get_IdentityLinks_Group_Task(
			"GET,runtime/tasks/{taskId}/identitylinks/groups"),
	// 获得一个任务的一个IdentityLink
	Get_One_IdentityLink_Task("GET,runtime/tasks/{taskId}/identitylinks/{family}/{identityId}/{type}"),
	// 为任务创建一个IdentityLink
	Create_IdentityLink_Task("POST,runtime/tasks/{taskId}/identitylinks,params"),
	// 删除任务的一个IdentityLink
	Delete_IdentityLink_Task("DELETE,runtime/tasks/{taskId}/identitylinks/{family}/{identityId}/{type}"),
	// 为任务创建评论
	Create_Comment_Task("POST,runtime/tasks/{taskId}/comments,params"),
	// 获得任务的所有评论
	Get_Comments_Task("GET,runtime/tasks/{taskId}/comments"),
	// 获得任务的一个评论
	Get_One_Comment_Task("GET,runtime/tasks/{taskId}/comments/{commentId}"),
	// 删除任务的一条评论
	Delete_One_Comment_Task("DELETE,runtime/tasks/{taskId}/comments/{commentId}"),
	// 获得任务的所有事件
	Get_Events_Task("GET,runtime/tasks/{taskId}/events"),
	// 获得任务的一个事件
	Get_One_Event_Task("GET,runtime/tasks/{taskId}/events/{eventId}"),
	// 为任务创建一个附件，包含外部资源的链接
	Create_Attach_Task("POST,runtime/tasks/{taskId}/attachments,params"),
	// 获得任务的所有附件
	Get_Attaches_Task("GET,runtime/tasks/{taskId}/attachments"),
	// 获得任务的一个附件
	Get_One_Attach_Task("GET,runtime/tasks/{taskId}/attachments/{attachmentId}"),
	// 获取附件的内容
	Get_Content_Task("GET,runtime/tasks/{taskId}/attachment/{attachmentId}/content"),
	// 删除任务的一个附件
	Delete_Attach_Task("DELETE,runtime/tasks/{taskId}/attachments/{attachmentId}"),

	/**
	 * 历史
	 */
	// 获得历史流程实例
	Get_His_PsId("GET,history/historic-process-instances/{processInstanceId}"),
	// 历史流程实例列表
	List_His_PsIds("GET,history/historic-process-instances"),
	// 查询历史流程实例
	Query_His_PsIds("POST,query/historic-process-instances,params"),
	// 删除历史流程实例
	Delete_His_PsId("DELETE,history/historic-process-instances/{processInstanceId}"),
	// 获取历史流程实例的IdentityLink
	Get_IdentityLink_His_PsId("GET,history/historic-process-instance/{processInstanceId}/identitylinks"),
	// 获取历史流程实例变量的二进制数据
	Get_BN_Variable_His_PsId("GET,history/historic-process-instances/{processInstanceId}/variables/{variableName}/data"),
	// 为历史流程实例创建一条新评论
	Create_Comment_His_PsId("POST,history/historic-process-instances/{processInstanceId}/comments,params"),
	// 获得一个历史流程实例的所有评论
	Get_Comments_His_PsId("GET,history/historic-process-instances/{processInstanceId}/comments"),
	// 获得历史流程实例的一条评论
	Get_One_Comment_His_PsId("GET,history/historic-process-instances/{processInstanceId}/comments/{commentId}"),
	// 删除历史流程实例的一条评论
	Delete_Comment_His_PsId("DELETE,history/historic-process-instances/{processInstanceId}/comments/{commentId}"),
	// 获得单独历史任务实例
	Get_Single_His_Task("GET,history/historic-task-instances/{taskId}"),
	// 获取历史任务实例
	Get_His_Tasks("GET,history/historic-task-instances"),
	// 查询历史任务实例
	Query_His_Task("POST,query/historic-task-instances,params"),
	// 删除历史任务实例
	Delete_His_Task("DELETE,history/historic-task-instances/{taskId}"),
	// 获得历史任务实例的IdentityLink
	Get_IdentityLink_His_Task("GET,history/historic-task-instance/{taskId}/identitylinks"),
	// 获取历史任务实例变量的二进制值
	Get_BN_Variable_His_Task("GET,history/historic-task-instances/{taskId}/variables/{variableName}/data"),
	// 获取历史活动实例
	Get_His_Activitis("GET,history/historic-activity-instances"),
	// 查询历史活动实例
	Query_His_Activiti("POST,query/historic-activity-instances,params"),
	// 列出历史变量实例
	List_His_Variables("GET,history/historic-variable-instances"),
	// 查询历史变量实例
	Query_His_Variable("POST,query/historic-variable-instances,params"),
	// 获取历史任务实例变量的二进制值
	Get_BN_His_Variable("GET,history/historic-variable-instances/{varInstanceId}/data"),
	// 获取历史细节
	Get_His_Details("GET,history/historic-detail"),
	// 查询历史细节
	Query_His_Detail("POST,query/historic-detail,params"),
	// 获取历史细节变量的二进制数据
	Get_BN_His_Detail("GET,history/historic-detail/{detailId}/data"),

	/**
	 * 作业
	 */
	// 获取一个作业
	Get_Single_Job("GET,management/jobs/{jobId}"),
	// 删除作业
	Delete_Job("DELETE,management/jobs/{jobId}"),
	// 执行作业
	Execute_Job("POST,management/jobs/{jobId},params"),
	// 获得作业的异常堆栈
	Get_Exception_Job("GET,management/jobs/{jobId}/exception-stacktrace"),
	// 获得作业列表
	List_Jobs("GET,management/jobs"),

	/**
	 * 用户
	 */
	// 获得一个用户
	Get_Single_User("GET,identity/users/{userId}"),
	// 获取用户列表
	List_Users("GET,identity/users"),
	// 更新用户
	Update_User("PUT,identity/users/{userId},params"),
	// 创建用户
	Create_User("POST,identity/users,params"),
	// 删除用户
	Delete_User("DELETE,identity/users/{userId}"),
	// 列出用户列表
	List_Info_User("PUT,identity/users/{userId}/info"),
	// 获取用户信息
	Get_Info_User("GET,identity/users/{userId}/info/{key}"),
	// 更新用户的信息
	Update_Info_User("PUT,identity/users/{userId}/info/{key},params"),
	// 创建用户信息条目
	Create_Info_Entry_User("POST,identity/users/{userId}/info,params"),
	// 删除用户的信息
	Delete_Info_User("DELETE,identity/users/{userId}/info/{key}"),

	/**
	 * 群组
	 */
	// 获得群组
	Get_Group("GET,identity/groups/{groupId}"),
	// 获取群组列表
	List_Groups("GET,identity/groups"),
	// 更新群组
	Update_Group("PUT,identity/groups/{groupId},params"),
	// 创建群组
	Create_Group("POST,identity/groups,params"),
	// 删除群组
	Delete_Group("DELETE,identity/groups/{groupId}"),
	// 为群组添加一个成员
	Add_Member_Group("POST,identity/groups/{groupId}/members,params"),
	// 删除群组的成员
	Delete_Member_Group("DELETE,identity/groups/{groupId}/members/{userId}");

	private String value;

	public String getValue() {
		return value;
	}

	ActivitiDefinition(String value) {
		this.value = value;
	}
}
