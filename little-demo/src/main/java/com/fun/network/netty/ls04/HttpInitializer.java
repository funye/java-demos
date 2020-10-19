package com.fun.network.netty.ls04;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.*;

public class HttpInitializer extends ChannelInitializer<Channel> {

    private final boolean isClient;

    public HttpInitializer(boolean isClient) {
        this.isClient = isClient;
    }

    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        if (isClient) {
            pipeline.addLast("codec", new HttpClientCodec()); // 添加客户端编解码器
            pipeline.addLast("decompressor", new HttpContentDecompressor()); // 添加HttpContentDecompressor处理来自服务器的压缩内容
        } else {
            pipeline.addLast("codec", new HttpServerCodec()); // 添加服务端编解码器
            pipeline.addLast("decompressor", new HttpContentCompressor()); // 添加服务端来压缩数据
        }
        pipeline.addLast("aggregator", new HttpObjectAggregator(512 * 1024)); // 512k
        pipeline.addLast("httpHandler", new HttpServerHandler());
    }
}
