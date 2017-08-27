package com.shcem.hessian;

import com.shcem.constants.SystemDefine;
import com.shcem.server.model.ServerContext;
import org.slf4j.MDC;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.util.NestedServletException;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拓展Spring HessianServiceExporter类，在服务端接受请求时，
 * 接收客户端请求头，并保存到HessianHeaderContext中，供服务端使用。 spring 服务端xml文件配置该类。
 * 
 */
public class HessianServiceExporter extends org.springframework.remoting.caucho.HessianServiceExporter {

	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (!"POST".equals(request.getMethod())) {
			throw new HttpRequestMethodNotSupportedException(request.getMethod(), new String[] { "POST" },
					"HessianServiceExporter only supports POST requests");
		}

		response.setContentType(CONTENT_TYPE_HESSIAN);
		try {
			handleHessianHeader(request);
			handleMDC(request);
			invoke(request.getInputStream(), response.getOutputStream());
		} catch (Throwable ex) {
			throw new NestedServletException("Hessian skeleton invocation failed", ex);
		} finally {
			ServerContext.clear();
			MDC.clear();
		}
	}
	
	@SuppressWarnings("rawtypes")
	protected void handleHessianHeader(HttpServletRequest request) {
		
//		HessianHeaderContext context = HessianHeaderContext.getContext();
//		Enumeration enumeration = request.getHeaderNames();
//
//		while (enumeration.hasMoreElements()) {
//
//			String name = enumeration.nextElement().toString();
//			String value = request.getHeader(name);
//			context.addHeader(name, value);
//		}
		ServerContext serverContext=ServerContext.currentContext();
		serverContext.setRequest(request);
		serverContext.setAppName(request.getHeader(SystemDefine.REQUEST_APP_NAME));
		serverContext.setClientIP(request.getHeader(SystemDefine.REQUEST_CLIENT_IP));
		serverContext.setMemberID(SystemDefine.REQUEST_MEM_ID);
		serverContext.setMemberName(SystemDefine.REQUEST_MEM_NAME);
		serverContext.setMode(SystemDefine.REQUEST_MODE);
		serverContext.setRequestId(SystemDefine.REQUEST_REQUESTID);

	}

	/**
	 *	日志记录用
	 * @param request
	 */
	protected void handleMDC(HttpServletRequest request){
		/**Log日志记录*/
		MDC.put(SystemDefine.REQUEST_REQUESTID,request.getHeader(SystemDefine.REQUEST_REQUESTID));
		MDC.put(SystemDefine.REQUEST_APP_NAME, request.getHeader(SystemDefine.REQUEST_APP_NAME));
		MDC.put(SystemDefine.REQUEST_CLIENT_IP, request.getHeader(SystemDefine.REQUEST_CLIENT_IP));
		MDC.put(SystemDefine.REQUEST_MODE, request.getHeader(SystemDefine.REQUEST_MODE));
		MDC.put(SystemDefine.REQUEST_REFERER, request.getHeader(SystemDefine.REQUEST_REFERER));
		MDC.put(SystemDefine.REQUEST_USERAGENT, request.getHeader(SystemDefine.REQUEST_USERAGENT));
		MDC.put(SystemDefine.REQUEST_LOGIN_NAME, request.getHeader(SystemDefine.REQUEST_LOGIN_NAME));
		MDC.put(SystemDefine.REQUEST_MEM_ID, request.getHeader(SystemDefine.REQUEST_MEM_ID)); //全局使用ID
		MDC.put(SystemDefine.REQUEST_MEM_NAME, request.getHeader(SystemDefine.REQUEST_MEM_NAME)); //全局
	}
}