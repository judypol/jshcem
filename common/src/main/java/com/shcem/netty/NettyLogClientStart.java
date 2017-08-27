package com.shcem.netty;///* ========================================
// * System Name　　：    化交线上平台
// * SubSystem Name ：化交服务核心工具集
// * File Name: LogManagerImpl
// * ----------------------------------------
// * Create Date/Change History
// * ----------------------------------------
// * 04/29/16 　池 永   Create
// *
// *
// * ----------------------------------------
// * Copyright (c) SCEM . All rights reserved.
// */
//package com.shcem.netty;
//
//import io.netty.bootstrap.Bootstrap;
//import io.netty.buffer.Unpooled;
//import io.netty.channel.ChannelFuture;
//import io.netty.channel.EventLoopGroup;
//import io.netty.channel.nio.NioEventLoopGroup;
//import io.netty.channel.socket.nio.NioSocketChannel;
//import io.netty.handler.codec.http.DefaultFullHttpRequest;
//import io.netty.handler.codec.http.HttpHeaders;
//import io.netty.handler.codec.http.HttpMethod;
//
//import static io.netty.handler.codec.http.HttpVersion.HTTP_1_0;
//
///**
// * NettyLogClientStart
// *
// * @author chiyong
// * @version 1.0
// */
//public final class NettyLogClientStart implements Runnable {
//
//	private String server = "192.168.60.121";
//	private int port = 5454;
//	private String msg;
//
//	public NettyLogClientStart(String mode, String msg) {
//		if (Constants.MODE_UAT.equals(mode)) {
//			this.port = 5455;
//		} else if (Constants.MODE_DEPLOY.equals(mode)) {
//			this.port = 5456;
//		}
//		this.msg = msg;
//	}
//
//	public void run() {
//		EventLoopGroup workerGroup = new NioEventLoopGroup();
//		try {
//			Bootstrap b = new Bootstrap();
//			b.group(workerGroup).channel(NioSocketChannel.class).handler(new HttpLogClientInitializer());
//
//			ChannelFuture f = b.connect(this.server, this.port).sync();
//			String uri = "/log";
//			DefaultFullHttpRequest request = new DefaultFullHttpRequest(HTTP_1_0, HttpMethod.POST, uri,
//					Unpooled.wrappedBuffer(msg.getBytes("UTF-8")));
//
//			request.headers().set(HttpHeaders.Names.CONNECTION, HttpHeaders.Values.KEEP_ALIVE);
//			request.headers().set(HttpHeaders.Names.CONTENT_LENGTH, request.content().readableBytes());
//
//			f.channel().write(request);
//			f.channel().flush();
//			/**
//			 * Wait until the connection is closed. 注释掉（异步）
//			 */
//			f.channel().closeFuture().sync();
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			workerGroup.shutdownGracefully();
//		}
//	}
//}