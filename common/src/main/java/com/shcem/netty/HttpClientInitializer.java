/* ========================================
 * System Name　　：    化交线上平台
 * SubSystem Name ：化交服务核心工具集
 * File Name: LogManagerImpl
 * ----------------------------------------
 * Create Date/Change History 
 * ----------------------------------------
 * 04/29/16 　池 永   Create
 * 
 * 
 * ----------------------------------------
 * Copyright (c) SCEM . All rights reserved.
 */
package com.shcem.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpRequestEncoder;
import io.netty.handler.codec.http.HttpResponseDecoder;

import java.util.Map;

/**
 * HttpClientHandler
 * 
 * @author chiyong
 * @version 1.0
 */
public class HttpClientInitializer extends ChannelInitializer<SocketChannel> {
	private Map<String, String> m;

	public HttpClientInitializer(Map<String, String> m) {
		this.m = m;
	}

	@Override
	public void initChannel(SocketChannel ch) {
		ChannelPipeline p = ch.pipeline();
		p.addLast(new HttpResponseDecoder());
		p.addLast(new HttpRequestEncoder());
		p.addLast(new HttpClientHandler(m));
	}
}