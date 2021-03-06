package com.shcem.configCenter.model;

import com.google.common.base.Preconditions;
import com.google.common.eventbus.EventBus;
import com.netflix.curator.RetryPolicy;
import com.netflix.curator.retry.ExponentialBackoffRetry;

public class ConfigOption {

    private String nameSpace;
    private String zkUrls;
    private RetryPolicy retryPolicy;
    private boolean useRemote;
    private EventBus enventBus;
    String childrenNode;

    public ConfigOption(String nameSpace,String zkUrls,RetryPolicy retryPolicy,boolean useRemote){
        String namespaces= Preconditions.checkNotNull(nameSpace);
        this.nameSpace=namespaces.substring(0,namespaces.lastIndexOf("/"));   //获取节点中的父节点作为namespace
        this.childrenNode=namespaces.substring(namespaces.lastIndexOf("/")+1);
        this.zkUrls=Preconditions.checkNotNull(zkUrls);
        this.retryPolicy=Preconditions.checkNotNull(retryPolicy);
        this.useRemote=useRemote;
        this.enventBus=new EventBus();

    }
    public ConfigOption(String nameSpace,String zkUrls){
        this(
                nameSpace,
                zkUrls,
                new ExponentialBackoffRetry(60000, 3),
                DefaultOptions.USE_REMOTE_CONFIG);
    }

    public String getNameSpace() {
        return this.nameSpace;
    }
    public String getPath(){
        return this.nameSpace.replace(".","/");
    }

    public String getZkUrls() {
        return this.zkUrls;
    }

    public RetryPolicy getRetryPolicy() {
        return this.retryPolicy;
    }

    public boolean isUseRemote() {
        return this.useRemote;
    }

    public EventBus getEnventBus() {
        return this.enventBus;
    }

    public String getChildrenNode() {
        return childrenNode;
    }

    @Override
    public String toString() {
        //return MoreObjects.toStringHelper(this).add("connectStr", zkUrls).add("namespace", nameSpace).add("retryPolicy", retryPolicy).toString();
        return "";
    }

}
