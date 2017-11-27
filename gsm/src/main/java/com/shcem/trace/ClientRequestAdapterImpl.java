package com.shcem.trace;

import com.github.kristofa.brave.ClientRequestAdapter;
import com.github.kristofa.brave.KeyValueAnnotation;
import com.github.kristofa.brave.SpanId;
import com.twitter.zipkin.gen.Endpoint;

import java.util.ArrayList;
import java.util.Collection;

public class ClientRequestAdapterImpl implements ClientRequestAdapter {

	String spanName;
	SpanId spanId;
	String requestid;
	String params;
	String serviceName;

	public ClientRequestAdapterImpl(String clientSpanName, String serviceName, String reqid, String content) {
		this.serviceName = serviceName;
		this.spanName = clientSpanName;
		this.requestid = reqid;
		this.params = content;
	}

	public SpanId getSpanId() {
		return spanId;
	}

	public String getSpanName() {
		return this.spanName;
	}

	public void addSpanIdToRequest(SpanId spanId) {
		// 记录传输到远程服务
		System.out.println(spanId);
		if (spanId != null) {
			this.spanId = spanId;
//			System.out.println(
//					String.format("----------------------------client spanname=%s trace_id=%s,parent_id=%s, span_id=%s",
//							spanName, spanId.traceId, spanId.parentId, spanId.spanId));
		}
	}

	public Collection<KeyValueAnnotation> requestAnnotations() {
		Collection<KeyValueAnnotation> collection = new ArrayList<KeyValueAnnotation>();
		KeyValueAnnotation kv1 = KeyValueAnnotation.create("CReq_RequestId", this.requestid == null ? "" : this.requestid);
		KeyValueAnnotation kv2 = KeyValueAnnotation.create("CReq_Params", this.params == null ? "" : this.params);
		KeyValueAnnotation kv3 = KeyValueAnnotation.create("CReq_ServiceName", this.serviceName == null ? "" : this.serviceName);
		KeyValueAnnotation kv4 = KeyValueAnnotation.create("CReq_SpanName", this.spanName == null ? "" : this.spanName);
		collection.add(kv1);
		collection.add(kv2);
		collection.add(kv3);
		collection.add(kv4);
		return collection;
	}

	public Endpoint serverAddress() {
		return null;
	}

}
