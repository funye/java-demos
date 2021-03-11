package com.fun.websocket;


import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

import java.net.URISyntaxException;

public class NettySocketIoWebSocketClient {

    public static void main(String[] args) throws URISyntaxException, InterruptedException {

        IO.Options options = new IO.Options();
        options.transports = new String[]{"websocket"};
        options.reconnectionAttempts = 2;
        options.reconnectionDelay = 1000;//失败重连的时间间隔
        options.timeout = 500;//连接超时时间(ms)

        final Socket socket = IO.socket("http://localhost:9090/client1", options);

        socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                socket.send("hello"); // 默认eventName: message
            }
        });

        socket.on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                System.out.println("连接关闭");
            }
        });

        socket.on(Socket.EVENT_MESSAGE, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                System.out.println("receive server response:");
                for (Object obj : args) {
                    System.out.println(obj);
                }

            }
        });
        socket.connect();
        socket.emit("msg", "I'm client1");
    }
}
