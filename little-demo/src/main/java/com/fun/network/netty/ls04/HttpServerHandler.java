package com.fun.network.netty.ls04;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.util.HashMap;
import java.util.Map;

public class HttpServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest req) throws Exception {
        System.out.println("uri=" + req.uri());
        System.out.println("method=" + req.method());
        System.out.println("headers=" + JSON.toJSONString(req.headers()));
        System.out.println("content=" + JSON.toJSONString(req.content()));

        if (req.headers().contains(HttpHeaderNames.CONTENT_TYPE) &&
                req.headers().get(HttpHeaderNames.CONTENT_TYPE).equalsIgnoreCase("application/json;charset=UTF-8")) {
            Map<String, String> resMap = new HashMap<>();
            resMap.put("method", req.method().name());
            resMap.put("uri", req.uri());
            // 创建http响应
            FullHttpResponse response = new DefaultFullHttpResponse(
                    HttpVersion.HTTP_1_1,
                    HttpResponseStatus.OK,
                    Unpooled.copiedBuffer(JSONUtil.toJsonStr(resMap), CharsetUtil.UTF_8));
            // 设置头信息
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "application/json;charset=UTF-8");
            // 将html write到客户端
            ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
        } else {
            // 获取请求的uri
            String uri = req.uri();
            Map<String, String> resMap = new HashMap<>();
            resMap.put("method", req.method().name());
            resMap.put("uri", uri);
            String msg = "<html><head><title>test</title></head><body>你请求uri为：" + uri + "</body></html>";
            // 创建http响应
            FullHttpResponse response = new DefaultFullHttpResponse(
                    HttpVersion.HTTP_1_1,
                    HttpResponseStatus.OK,
                    Unpooled.copiedBuffer(msg, CharsetUtil.UTF_8));
            // 设置头信息
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/html; charset=UTF-8");
            // 将html write到客户端
            ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
