/* ========================================
 * System Name　　：化交线上平台
 * SubSystem Name ：化交站点核心工具集
 * File Name: Constants
 * ----------------------------------------
 * Create Date/Change History
 * ----------------------------------------
 * 2017/3/20 　lizhihua   Create
 *
 *
 * ----------------------------------------
 * Copyright (c) SCEM . All rights reserved.
 */
package com.shcem.netty;

import com.shcem.common.YamlConfiguration;
import com.shcem.constants.SystemDefine;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.URI;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * @author lizhihua
 * @version 1.0
 */
public class MidTierNetty {
    private String server = "127.0.0.1";
    private int port = 5412;
    private String message = "";
    private String uri = "";
    private HttpServletRequest request;

    public MidTierNetty(String message,String uri,HttpServletRequest request) throws Exception {
        URI uri1=new URI(uri);

        this.server = uri1.getHost();
        this.port = uri1.getPort();
        this.message = message;
        this.uri = uri;
        this.request=request;
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
            request.headers().set(HttpHeaders.Names.CONTENT_LENGTH, request.content().readableBytes());
            SetHeaders(request.headers());

            f.channel().write(request);
            f.channel().flush();
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
        }

        return m;
    }
    private void SetHeaders(HttpHeaders headers){
        headers.set(HttpHeaders.Names.CONNECTION, HttpHeaders.Values.KEEP_ALIVE);

        if(request!=null) {
            String url = request.getRequestURI();
            String requestId = (String) request.getAttribute("RequestId");
            headers.set("RequestId", requestId == null ? "" : requestId);       //

            try {
                headers.set("UserAgent", request.getHeader("User-Agent"));
                headers.set("Referer", URLEncoder.encode(url, "utf-8"));
            } catch (Exception e) {
                System.out.println(e);
                //logger.info("URLEncoder is error in setHeaders",e);
            }

            String cookieKey = getMode() + "_authKey";
            String authKey = null;
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (int i = 0; i < cookies.length; i++) {
                    if (cookies[i].getName().equals(cookieKey)) {
                        String authKeyPrefix = YamlConfiguration.instance().getString(SystemDefine.AuthPrefix);
                        authKey = authKeyPrefix + cookies[i].getValue();
                    }
                }
            }

            headers.set(SystemDefine.REQUEST_CLIENT_IP, getClientIp());
            headers.set(SystemDefine.REQUEST_AUTHKEY, authKey == null ? "" : authKey);
        }
        headers.set(SystemDefine.REQUEST_APP_NAME,YamlConfiguration.instance().getProjectName());
        headers.set(SystemDefine.REQUEST_AUTH_APP,YamlConfiguration.instance().getString(SystemDefine.AuthApp));
    }
    public String getClientIp()
    {
        if(this.request==null)
            return "127.0.0.1";
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            //ip = request.getRemoteAddr();
            if("127.0.0.1".equals(ip)){
                //根据网卡取本机配置的IP
                InetAddress inet=null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ip= inet.getHostAddress();
            }
        }
        // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if(ip != null && ip.length() > 15){
            if(ip.indexOf(",")>0){
                ip = ip.substring(0,ip.indexOf(","));
            }
        }
        if(ip==null){
            ip="localhost";
        }
        return ip;
    }
    /**
     * 取得开发模式
     *
     * @return 开发模式
     */
    protected String getMode() {

        String mode = SystemDefine.MODE_LOCAL;
        String sysMode = System.getProperty("mode");

        if (sysMode != null && !StringUtils.isEmpty(sysMode)) {
            if (SystemDefine.MODE_LOCAL.equals(sysMode)
                    || SystemDefine.MODE_DEV.equals(sysMode)
                    || SystemDefine.MODE_TEST.equals(sysMode)
                    || SystemDefine.MODE_UAT.equals(sysMode)
                    || SystemDefine.MODE_DEPLOY.equals(sysMode)) {
                mode = sysMode;
            }
        }

        return mode;
    }
}
