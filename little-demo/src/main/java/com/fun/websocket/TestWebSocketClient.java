package com.fun.websocket;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

public class TestWebSocketClient extends WebSocketClient {


    public TestWebSocketClient(URI serverUri) {
        super(serverUri);
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        System.out.println("open web socket client...");
    }

    @Override
    public void onMessage(String message) {
        System.out.println("client receive message:" + message);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("close web socket client...");
    }

    @Override
    public void onError(Exception ex) {
        System.out.println("web socket client error...");
    }
}
