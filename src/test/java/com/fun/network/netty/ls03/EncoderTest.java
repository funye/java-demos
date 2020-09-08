package com.fun.network.netty.ls03;

import io.netty.buffer.ByteBuf;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Test;

public class EncoderTest {

    @Test
    public void testMessageToByteEncoder() {

        EmbeddedChannel channel = new EmbeddedChannel(new ShortToByteEncoder());

        channel.writeOutbound(new Short("12"));
        channel.finish();

        ByteBuf buf = channel.readOutbound();
        System.out.println(buf.getShort(0));

    }

    @Test
    public void testMessageToMessageEncoder() {

        EmbeddedChannel channel = new EmbeddedChannel(new IntegerToStringEncoder());

        channel.writeOutbound(new Integer("12"));
        channel.finish();

        String msg = channel.readOutbound();
        System.out.println(msg);

    }


    @Test
    public void testDuplexCodec() {

        EmbeddedChannel channel = new EmbeddedChannel(new CombineByteShortCodec(new ByteToShortDecoder(), new ShortToByteEncoder()));

        channel.writeOutbound(new Short("12"));
        channel.finish();

        ByteBuf buf = channel.readOutbound();
        System.out.println(buf.getShort(0));

    }
}
