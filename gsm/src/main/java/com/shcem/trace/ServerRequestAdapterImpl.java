package com.shcem.trace;

import com.github.kristofa.brave.KeyValueAnnotation;
import com.github.kristofa.brave.ServerRequestAdapter;
import com.github.kristofa.brave.SpanId;
import com.github.kristofa.brave.TraceData;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

public class ServerRequestAdapterImpl implements ServerRequestAdapter {

	Random randomGenerator = new Random();
	SpanId spanId;
	String spanName;
	String requestid;
	String params;
	String serviceName;

	public ServerRequestAdapterImpl(String spanName) {
		this.spanName = spanName;
		long startId = randomGenerator.nextLong();
		SpanId spanId = SpanId.builder().spanId(startId).traceId(startId).parentId(startId).build();
		this.spanId = spanId;
//		System.out.println(String.format("Server trace_id=%s, parent_id=%s, span_id=%s", spanId.traceId,
//				spanId.parentId, spanId.spanId));
	}

	public ServerRequestAdapterImpl(String spanName, SpanId spanId) {
		this.spanName = spanName;
		this.spanId = spanId;
//		System.out.println(String.format("Server2 trace_id=%s, parent_id=%s, span_id=%s", spanId.traceId,
//				spanId.parentId, spanId.spanId));
	}

	public ServerRequestAdapterImpl(String spanName, String serviceName, long traceId, long spanIdl, long parentId,
			String reqid, String content) {
		// String[] spSvcName = serviceName.split("\\.");
		this.serviceName = serviceName;
		this.spanName = spanName;
		SpanId spanId = SpanId.builder().spanId(spanIdl).traceId(traceId).parentId(parentId).build();
		this.spanId = spanId;
		this.requestid = reqid;
		this.params = content;
//		System.out.println(
//				String.format("----------------------------Server spanName=%s trace_id=%s, parent_id=%s, span_id=%s",
//						spanName, spanId.traceId, spanId.parentId, spanId.spanId));
	}

	@SuppressWarnings("deprecation")
	public TraceData getTraceData() {
		if (this.spanId != null) {
			return TraceData.builder().spanId(this.spanId).build();
		}
		long startId = randomGenerator.nextLong();
		SpanId spanId = SpanId.builder().spanId(startId).traceId(startId).parentId(startId).build();
		return TraceData.builder().spanId(spanId).build();
	}

	public String getSpanName() {
		return spanName;
	}

	public Collection<KeyValueAnnotation> requestAnnotations() {
		Collection<KeyValueAnnotation> collection = new ArrayList<KeyValueAnnotation>();
		KeyValueAnnotation kv1 = KeyValueAnnotation.create("SReq_RequestId",
				this.requestid == null ? "" : this.requestid);
		KeyValueAnnotation kv2 = KeyValueAnnotation.create("SReq_Params", this.params == null ? "" : this.params);
		KeyValueAnnotation kv3 = KeyValueAnnotation.create("SReq_ServiceName",
				this.serviceName == null ? "" : this.serviceName);
		KeyValueAnnotation kv4 = KeyValueAnnotation.create("SReq_SpanName", this.spanName == null ? "" : this.spanName);
		collection.add(kv1);
		collection.add(kv2);
		collection.add(kv3);
		collection.add(kv4);
		return collection;
	}

}