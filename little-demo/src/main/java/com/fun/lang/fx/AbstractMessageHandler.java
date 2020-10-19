package com.fun.lang.fx;

public abstract class AbstractMessageHandler<IN extends String, OUT extends String> implements MessageHandler<IN, OUT> {

    @Override
    public OUT handle(IN in) {
        OUT out = decode(in);
        send(out);
        return out;
    }

    public abstract OUT decode(IN in);

    public abstract void send(OUT out);
}
