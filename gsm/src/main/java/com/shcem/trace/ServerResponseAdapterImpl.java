package com.shcem.trace;

import com.github.kristofa.brave.KeyValueAnnotation;
import com.github.kristofa.brave.ServerResponseAdapter;

import java.util.ArrayList;
import java.util.Collection;

public class ServerResponseAdapterImpl implements ServerResponseAdapter {

	String requestid;
	String response;
	String serviceName;

	public ServerResponseAdapterImpl(String serviceName, String reqid, String content) {
		this.requestid = reqid;
		this.response = content;
		this.serviceName = serviceName;
	}

	public Collection<KeyValueAnnotation> responseAnnotations() {
		Collection<KeyValueAnnotation> collection = new ArrayList<KeyValueAnnotation>();
		KeyValueAnnotation kv1 = KeyValueAnnotation.create("SRes_RequestId",
				this.requestid == null ? "" : this.requestid);
		KeyValueAnnotation kv2 = KeyValueAnnotation.create("SRes_Response", this.response == null ? "" : this.response);
		KeyValueAnnotation kv3 = KeyValueAnnotation.create("SRes_ServiceName",
				this.serviceName == null ? "" : this.serviceName);
		collection.add(kv1);
		collection.add(kv2);
		collection.add(kv3);
		return collection;
	}

}
