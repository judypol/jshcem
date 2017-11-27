package com.shcem.util;

import com.alibaba.fastjson.JSON;
import com.github.kristofa.brave.SpanId;
import com.shcem.constants.SystemDefine;
import com.shcem.server.model.ServerContext;
import com.shcem.trace.BraveGenerator;


public class TraceInterceptor {

	private long traceId = 0l;
	private long spanId = 0l;
	private long parentId = 0l;
	public String requestId;
	private String mode;
	private String spanName;
	private BraveGenerator brave = null;
	private TraceReporter reporter = null;

	public TraceInterceptor(TraceReporter reporter) {
		this.reporter = reporter;
	}

	protected void init(String methodName) {

		try {
			traceId = Long.parseLong(ServerContext.currentContext().getTraceId());
		} catch (NumberFormatException e) {
			// Do nothing
		}
		try {
			spanId = Long.parseLong(ServerContext.currentContext().getSpanId());
		} catch (NumberFormatException e) {
			// Do nothing
		}
		try {
			parentId = Long.parseLong(ServerContext.currentContext().getParentId());
		} catch (NumberFormatException e) {
			// Do nothing
		}
		requestId = ServerContext.currentContext().getRequestId();
		mode = ServerContext.currentContext().getMode();
		spanName = ServerContext.currentContext().getSpanName();

		brave = new BraveGenerator(methodName, mode, reporter);
	}

	protected void setHeader(long spanId, long parentId, String clientSpanName) {
		ServerContext.currentContext().setSpanId(Long.toString(spanId));
		ServerContext.currentContext().setParentId(Long.toString(parentId));
		ServerContext.currentContext().setSpanName(clientSpanName);
	}

	protected void sendTrace(String differ, String methodName, String serviceName, String content) {
		String clientSpanName = methodName + "_cs";

		SpanId spanIdObj = brave.sendTrace(mode, differ, traceId, spanId, parentId, methodName, serviceName, requestId,
				content, spanName, clientSpanName);

		if ((SystemDefine.TRACELOG_TYPE_S.equals(differ) || SystemDefine.TRACELOG_TYPE_S_CLIENT.equals(differ))
				&& spanIdObj != null) {
			// update header
			setHeader(spanIdObj.spanId, spanIdObj.parentId, clientSpanName);
		}
	}

	public static class Task {
		String name;
		String result;

		public Task(String name, String result) {
			super();
			this.name = name;
			this.result = result;
		}
	}

	@SuppressWarnings("rawtypes")
	protected static String getResult(Object data) {
		String strRst = JSON.toJSONString(data);

		return strRst;
	}
	
	public static void setCurrentHandle(String handleName) {

		ServerContext.currentContext().addHeader(SystemDefine.HEADER_HANDLE, handleName);
	}
	
	public static String getCurrentHandle() {
		return ServerContext.currentContext().getHeader(SystemDefine.HEADER_HANDLE);
	}
}
