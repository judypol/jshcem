package com.shcem.trace;

import com.github.kristofa.brave.*;
import com.shcem.constants.SystemDefine;
import com.shcem.util.TraceReporter;
import com.shcem.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zipkin.Span;
import zipkin.reporter.AsyncReporter;

public class BraveGenerator {

	private Logger logger= LoggerFactory.getLogger(BraveGenerator.class.getName());
	private Brave brave = null;
	private ServerRequestInterceptor serverRequestInterceptor = null;
	private ServerResponseInterceptor serverResponseInterceptor = null;
	private ClientRequestInterceptor clientRequestInterceptor = null;
	private ClientResponseInterceptor clientResponseInterceptor = null;

	/**
	 * 取得Brave
	 */
	public BraveGenerator(String serviceName, String mode, TraceReporter reporter) {

		String[] spSvcName = serviceName.split("\\.");
		String svcName = spSvcName[spSvcName.length - 1];

		AsyncReporter<Span> report = reporter.getReport(mode);

		if (report == null) {
			logger.debug("Trace Reporter is null, please restart web app server!");
			return;
		}

		Sampler.create((float) 1.0);
		brave = new Brave.Builder(svcName).reporter(report).traceSampler(Sampler.ALWAYS_SAMPLE).build();

		serverRequestInterceptor = brave.serverRequestInterceptor();
		serverResponseInterceptor = brave.serverResponseInterceptor();
		clientRequestInterceptor = brave.clientRequestInterceptor();
		clientResponseInterceptor = brave.clientResponseInterceptor();
	}

	/**
	 * send Trace
	 */
	public SpanId sendTrace(String mode, String differ, long traceId, long spanId, long parentId, String methodName,
			String serviceName, String requestId, String content, String spanName, String clientSpanName) {

		if (!SystemDefine.MODE_UAT.equals(mode) && !SystemDefine.MODE_DEPLOY.equals(mode)) {

			// send trace only in uat and deploy
			return null;
		}

		if (brave == null) {
			return null;
		}

		if (!SystemDefine.TRACELOG_TYPE_S.equals(differ) && !SystemDefine.TRACELOG_TYPE_R.equals(differ)
				&& !SystemDefine.TRACELOG_TYPE_S_CLIENT.equals(differ) && !SystemDefine.TRACELOG_TYPE_R_CLIENT.equals(differ)
				&& !SystemDefine.TRACELOG_TYPE_S_SERVER.equals(differ)
				&& !SystemDefine.TRACELOG_TYPE_R_SERVER.equals(differ)) {
			// Do nothing
			return null;
		}

		if (StringUtils.isEmpty(methodName)) {
			// Do nothing
			return null;
		}

		SpanId spanIdObj = null;

		// send trace
		if (SystemDefine.TRACELOG_TYPE_S.equals(differ)) {

			/**
			 * 发送服务端和客户端
			 */
			serverRequestInterceptor.handle(
					new ServerRequestAdapterImpl(spanName, serviceName, traceId, spanId, parentId, requestId, content));

			ClientRequestAdapterImpl clientRequestAdapterImpl = new ClientRequestAdapterImpl(clientSpanName,
					serviceName, requestId, content);
			clientRequestInterceptor.handle(clientRequestAdapterImpl);

			spanIdObj = clientRequestAdapterImpl.getSpanId();
		} else if (SystemDefine.TRACELOG_TYPE_S_SERVER.equals(differ)) {

			/**
			 * 仅发送服务端
			 */
			serverRequestInterceptor.handle(
					new ServerRequestAdapterImpl(spanName, serviceName, traceId, spanId, parentId, requestId, content));
		} else if (SystemDefine.TRACELOG_TYPE_S_CLIENT.equals(differ)) {

			/**
			 * 仅发送客户端
			 */
			ClientRequestAdapterImpl clientRequestAdapterImpl = new ClientRequestAdapterImpl(clientSpanName,
					serviceName, requestId, content);
			clientRequestInterceptor.handle(clientRequestAdapterImpl);

			spanIdObj = clientRequestAdapterImpl.getSpanId();
		} else if (SystemDefine.TRACELOG_TYPE_R_CLIENT.equals(differ)) {

			/**
			 * 仅接收客户端
			 */
			clientResponseInterceptor.handle(new ClientResponseAdapterImpl(serviceName, requestId, content));
		} else if (SystemDefine.TRACELOG_TYPE_R_SERVER.equals(differ)) {

			/**
			 * 仅接收服务端
			 */
			serverResponseInterceptor.handle(new ServerResponseAdapterImpl(serviceName, requestId, content));
		} else if (SystemDefine.TRACELOG_TYPE_R.equals(differ)) {

			/**
			 * 接受客户端和服务端
			 */
			clientResponseInterceptor.handle(new ClientResponseAdapterImpl(serviceName, requestId, content));
			serverResponseInterceptor.handle(new ServerResponseAdapterImpl(serviceName, requestId, content));
		}

		return spanIdObj;
	}
}
