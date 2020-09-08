package com.fun.network.netty.ls02;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * 字节流转integer的解码器
 */
public class ByteToIntegerDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("msg size="+in.readableBytes());
        if (in.readableBytes() >= 4) {
            System.out.println("read.....");
            out.add(in.readInt());
            System.out.println(out);
        }
    }
}
