package com.shcem.trace;

import com.github.kristofa.brave.ClientResponseAdapter;
import com.github.kristofa.brave.KeyValueAnnotation;

import java.util.ArrayList;
import java.util.Collection;

public class ClientResponseAdapterImpl implements ClientResponseAdapter {

	String requestid;
	String response;
	String serviceName;

	public ClientResponseAdapterImpl(String serviceName, String reqid, String content) {
		this.requestid = reqid;
		this.response = content;
		this.serviceName = serviceName;
	}

	public Collection<KeyValueAnnotation> responseAnnotations() {
		Collection<KeyValueAnnotation> collection = new ArrayList<KeyValueAnnotation>();
		KeyValueAnnotation kv1 = KeyValueAnnotation.create("CRes_RequestId",
				this.requestid == null ? "" : this.requestid);
		KeyValueAnnotation kv2 = KeyValueAnnotation.create("CRes_Response", this.response == null ? "" : this.response);
		KeyValueAnnotation kv3 = KeyValueAnnotation.create("CRes_ServiceName",
				this.serviceName == null ? "" : this.serviceName);
		collection.add(kv1);
		collection.add(kv2);
		collection.add(kv3);
		return collection;
	}

}
