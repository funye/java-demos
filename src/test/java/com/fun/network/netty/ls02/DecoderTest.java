package com.fun.network.netty.ls02;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Test;

public class DecoderTest {

    @Test
    public void testByteToIntegerDecoder() {

        ByteBuf buf = Unpooled.buffer(1);
        buf.writeInt(12);
        buf.writeInt(15);
        buf.writeInt(18);

        EmbeddedChannel channel = new EmbeddedChannel(new ByteToIntegerDecoder());
        // write
        channel.writeInbound(buf.retain());
        channel.finish();
        buf.release();

        // read message
        Integer read = channel.readInbound();
        System.out.println("read msg=" + read);

        read = channel.readInbound();
        System.out.println("read msg=" + read);

        read = channel.readInbound();
        System.out.println("read msg=" + read);


    }

    @Test
    public void testByteToIntegerDecoderWithReplayingDecoder() {

        ByteBuf buf = Unpooled.buffer(1);
        buf.writeInt(12);
        buf.writeInt(15);

        EmbeddedChannel channel = new EmbeddedChannel(new ByteToIntegerDecoder2());

        // write
        channel.writeInbound(buf.retain());
        channel.finish();
        buf.release();

        // read message
        Integer read = channel.readInbound();
        System.out.println("read msg=" + read);

        read = channel.readInbound();
        System.out.println("read msg=" + read);
    }


    @Test
    public void testIntegerToStringDecoder() { // MessageToMessage


        EmbeddedChannel channel = new EmbeddedChannel(new IntegerToStringDecoder(), new IntegerToStringHandler(), new IntegerToStringHandler02());

        // write
        channel.writeInbound(new Integer(18));
        channel.finish();

        // read message
        String read = channel.readInbound();
        System.out.println("read msg=" + read);

    }

}
