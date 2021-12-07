package com.fun.network.netty.ls05;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

public class NettyClient extends Thread {

    Channel channel;

    @Override
    public void run() {
        EventLoopGroup group = new NioEventLoopGroup(1);

        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .remoteAddress(new InetSocketAddress("127.0.0.1", 8000))
                    .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<Channel>() {
                        @Override
                        protected void initChannel(Channel ch) throws Exception {
                            ch.pipeline().addLast(new ClientHandler());
                        }
                    });
            ChannelFuture f = b.connect().sync();
            channel = f.channel();
            channel.closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                group.shutdownGracefully().sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {

        NettyClient client = new NettyClient();
        client.start();

        try {
            TimeUnit.SECONDS.sleep(10);
            client.channel.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
