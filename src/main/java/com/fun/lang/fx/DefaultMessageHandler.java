package com.fun.lang.fx;

public class DefaultMessageHandler extends AbstractMessageHandler<String, String> {

    @Override
    public String decode(String s) {
        return s;
    }

    @Override
    public void send(String s) {
        System.out.format("send %s\n", s);
    }

    public static void main(String[] args) {

        MessageHandler<String, String> handler = new DefaultMessageHandler();
        handler.handle("hello");

    }

}
