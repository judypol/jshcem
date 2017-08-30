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

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.LastHttpContent;

import java.util.Map;

/**
 * HttpClientHandler
 * 
 * @author chiyong
 * @version 1.0
 */
public class HttpClientHandler extends ChannelInboundHandlerAdapter {
	private ByteBuf buf = Unpooled.buffer();
	private Map<String, String> m;

	public HttpClientHandler(Map<String, String> m) {
		this.m = m;
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) {
		if (ctx != null) {
			ctx.flush();
		}
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		if (msg instanceof HttpResponse) {
			HttpResponse response = (HttpResponse) msg;
			//System.out.println("CONTENT_TYPE:" + response.headers().get(HttpHeaders.Names.CONTENT_TYPE));
		}

		if (msg instanceof HttpContent) {
			HttpContent content = (HttpContent) msg;
			buf.writeBytes(content.content());
			if (content instanceof LastHttpContent) {
				//System.out.println(buf.toString(io.netty.util.CharsetUtil.UTF_8));
				m.put("response", buf.toString(io.netty.util.CharsetUtil.UTF_8));
				buf.release();
				ctx.close();
			}
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
		ctx.close();
	}
}
