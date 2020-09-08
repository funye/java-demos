package com.fun.network.netty.ls03;

import io.netty.channel.CombinedChannelDuplexHandler;

public class CombineByteShortCodec extends CombinedChannelDuplexHandler<ByteToShortDecoder, ShortToByteEncoder> {

    public CombineByteShortCodec(ByteToShortDecoder inboundHandler, ShortToByteEncoder outboundHandler) {
        super(inboundHandler, outboundHandler);
    }
}
