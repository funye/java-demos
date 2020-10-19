package com.fun.network.netty.ls02;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

/**
 * The biggest difference between ReplayingDecoder and ByteToMessageDecoder is that
 * ReplayingDecoder allows you to implement the decode() and decodeLast() methods just like all required bytes were received already,
 * rather than checking the availability of the required bytes
 * ReplayingDecoder在使用decode() 和 decodeLast() 的时候，不需要编码检测需要的数据是否已经接收到
 *
 * 如果使用ByteToMessageDecoder 不会引入太多的复杂性，那么请使用它；否则，请使用ReplayingDecoder。
 */
public class ByteToIntegerDecoder2 extends ReplayingDecoder<Void> {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        out.add(in.readInt());
        System.out.println(out); // 不需要检测数据是否足够
    }
}
