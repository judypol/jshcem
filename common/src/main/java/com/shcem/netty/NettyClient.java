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

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;

import java.util.HashMap;
import java.util.Map;

import static io.netty.handler.codec.http.HttpVersion.HTTP_1_0;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * HttpLogClientInitializer
 * 
 * @author chiyong
 * @version 1.0
 */
public final class NettyClient {

	private String server = "127.0.0.1";
	private int port = 5412;
	private String message = "";
	private String uri = "";
	
	public NettyClient(String server, int port, String message,String uri) {
		this.server = server;
		this.port = port;
		this.message = message;
		this.uri = uri;
	}

	public Map<String, String> httpPost() throws Exception {
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		Map<String, String> m = new HashMap<String, String>();
		try {
			Bootstrap b = new Bootstrap();
			b.group(workerGroup).channel(NioSocketChannel.class).handler(new HttpClientInitializer(m));

			ChannelFuture f = b.connect(this.server, this.port).sync();

			String uri = this.uri;
			String msg = this.message;

			DefaultFullHttpRequest request = new DefaultFullHttpRequest(HTTP_1_1, HttpMethod.POST, uri,
					Unpooled.wrappedBuffer(msg.getBytes("UTF-8")));

			request.headers().set(HttpHeaders.Names.CONNECTION, HttpHeaders.Values.KEEP_ALIVE);
			request.headers().set(HttpHeaders.Names.CONTENT_LENGTH, request.content().readableBytes());
			
			f.channel().write(request);
			f.channel().flush();
			f.channel().closeFuture().sync();
		} finally {
			workerGroup.shutdownGracefully();
		}
		
		return m;
	}
}