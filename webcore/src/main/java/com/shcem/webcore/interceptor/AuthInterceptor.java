/* ========================================				
 * System Name　　：化交线上平台				
 * SubSystem Name ：化交站点核心工具集
 * File Name: AuthInterceptor
 * ----------------------------------------
 * Create Date/Change History 
 * ----------------------------------------
 * 04/29/16 　池 永   Create
 * 
 * 
 * ----------------------------------------
 * Copyright (c) SCEM . All rights reserved.
 */
package com.shcem.webcore.interceptor;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shcem.common.YamlConfiguration;
import com.shcem.constants.SystemDefine;
import com.shcem.utils.CookieUtils;
import com.shcem.webcore.permission.Anonymous;
import com.shcem.webcore.permission.Permission;
import com.shcem.webcore.permission.PermissionCheck;
import com.shcem.webcore.permission.WebContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * AuthInterceptor 统一拦截器，主要做权限控制
 * 
 * @author lizhihua
 * @version 1.0
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {
	Logger logger= LoggerFactory.getLogger(AuthInterceptor.class);
	@Autowired(required=false)
	private PermissionCheck permissionCheck;
	/**
	 * 在业务处理器处理请求之前被调用 如果返回false 从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链
	 * 如果返回true 执行下一个拦截器,直到所有的拦截器都执行完毕 再执行被拦截的Controller 然后进入拦截器链,
	 * 从最后一个拦截器往回执行所有的postHandle() 接着再从最后一个拦截器往回执行所有的afterCompletion()
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {


		WebContext.Clear();			//清空当前的数据
		if(isStaticFile(request)){
			return true;
		}
		// TODO: 2017/2/20 日志记录
		String uuid = UUID.randomUUID().toString();

		request.setAttribute(SystemDefine.REQUEST_REQUESTID, uuid); // 设置唯一请求ID
		request.setAttribute(SystemDefine.REQUEST_APP_NAME, YamlConfiguration.instance().getProjectName());
		request.setAttribute(SystemDefine.REQUEST_CLIENT_IP, getIpAddress(request));
		request.setAttribute(SystemDefine.REQUEST_MODE, getMode());
		request.setAttribute(SystemDefine.REQUEST_REFERER, request.getHeader("Referer"));
		request.setAttribute(SystemDefine.REQUEST_USERAGENT, request.getHeader("User-Agent"));

		Class<?> clazz = handler.getClass();
		boolean flag;

		if (clazz.isAssignableFrom(HandlerMethod.class)) {
			HandlerMethod hm = (HandlerMethod) handler;
			Anonymous a = hm.getMethodAnnotation(Anonymous.class);
			if (a != null) // 匿名访问
			{
				request.setAttribute("isAnonymous","true");
				flag=true;
			}
			else{
				Permission auth = hm.getMethodAnnotation(Permission.class);
				if (auth != null&&permissionCheck!=null) // 需要认证权限
				{
					request.setAttribute("isAnonymous","false");

					flag= permissionCheck.process(auth, request, response);
				} else {
					flag=false;
				}
			}
		} else {
			flag=true;
		}
		request.setAttribute(SystemDefine.REQUEST_LOGIN_NAME, WebContext.GetCurrentContext().getCurrentUser().getLoginName());
		request.setAttribute(SystemDefine.REQUEST_MEM_ID, WebContext.GetCurrentContext().getCurrentUser().getUserCode()); //全局使用ID
		request.setAttribute(SystemDefine.REQUEST_MEM_NAME, WebContext.GetCurrentContext().getCurrentUser().getUserName()); //全局
		try{
			setMDC(request);
		}catch (Exception ex){
			logger.error("set the MDC value Exception",ex);
		}
		return flag;
	}

	/**
	 * 记录日志用到
	 * @param request
	 */
	private void setMDC(HttpServletRequest request){
		MDC.put(SystemDefine.REQUEST_REQUESTID, request.getAttribute(SystemDefine.REQUEST_REQUESTID).toString()); // 设置唯一请求ID
		MDC.put(SystemDefine.REQUEST_APP_NAME, YamlConfiguration.instance().getProjectName());
		MDC.put(SystemDefine.REQUEST_CLIENT_IP, getIpAddress(request));
		MDC.put(SystemDefine.REQUEST_MODE, getMode());
		MDC.put(SystemDefine.REQUEST_REFERER, request.getHeader("Referer"));
		MDC.put(SystemDefine.REQUEST_USERAGENT, request.getHeader("User-Agent"));
		MDC.put(SystemDefine.REQUEST_LOGIN_NAME, WebContext.GetCurrentContext().getCurrentUser().getLoginName());
		MDC.put(SystemDefine.REQUEST_MEM_ID, WebContext.GetCurrentContext().getCurrentUser().getUserCode()); //全局使用ID
		MDC.put(SystemDefine.REQUEST_MEM_NAME, WebContext.GetCurrentContext().getCurrentUser().getUserName()); //全局
	}

	/**
	 *
	 * @param request
	 * @return
	 */
	private boolean isStaticFile(HttpServletRequest request){
		String requestUrl=request.getRequestURI();
		if(requestUrl.indexOf("/static/")!=-1){
			return true;
		}else {
			return false;
		}
	}
	/**
	 * 在业务处理器处理请求执行完成后,生成视图之前执行的动作 可在modelAndView中加入数据，比如当前时间
	 */
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		// TODO: 2017/2/20 日志记录
	}

	/**
	 * 在DispatcherServlet完全处理完请求后被调用,可用于清理资源等
	 *
	 * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion()
	 */
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		WebContext.Clear();		//清空所有的当前上下文
		// log.info("==============执行顺序: 3、afterCompletion================");
	}

	/**
	 * 取得开发模式
	 * 
	 * @return 开发模式
	 */
	protected String getMode() {

		String mode = SystemDefine.MODE_LOCAL;
		String sysMode = System.getProperty("mode");

		if (sysMode != null && !sysMode.isEmpty()) {
			if (SystemDefine.MODE_LOCAL.equals(sysMode)
					|| SystemDefine.MODE_DEV.equals(sysMode)
					|| SystemDefine.MODE_TEST.equals(sysMode)
					|| SystemDefine.MODE_UAT.equals(sysMode)
					|| SystemDefine.MODE_DEPLOY.equals(sysMode)) {
				mode = sysMode;
			}
		}

		return mode;
	}
	/**
	 * @Title: getIpAddress
	 * @Description: 获取客户端IP地址
	 * @param @return
	 * @return String
	 * @throws
	 */
	protected String getIpAddress(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
			if(ip.equals("127.0.0.1")){
				//根据网卡取本机配置的IP
				InetAddress inet=null;
				try {
					inet = InetAddress.getLocalHost();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
				ip= inet.getHostAddress();
			}
		}
		// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
		if(ip != null && ip.length() > 15){
			if(ip.indexOf(",")>0){
				ip = ip.substring(0,ip.indexOf(","));
			}
		}
		if(ip==null){
			ip="localhost";
		}
		return ip;
	}

	/**
	 * 获取Token
	 * @param request
	 * @return
	 */
	private String getToken(HttpServletRequest request){
		String cookieKey=getMode()+"_authKey";
		Cookie cookie= CookieUtils.getCookieByName(request,cookieKey);
		if(cookie==null){
			return "";
		}else {
			String cookieValue=cookie.getValue();
			return cookieValue;
		}
	}
}
