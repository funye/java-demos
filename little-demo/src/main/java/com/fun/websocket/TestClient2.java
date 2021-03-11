package com.fun.websocket;

import java.net.URI;

public class TestClient2 {

    public static void main(String[] args) throws Exception {
        URI uri = new URI("ws://localhost:8090/client2");
        TestWebSocketClient client = new TestWebSocketClient(uri);
        boolean isConnected = client.connectBlocking();
        if (isConnected) {
            client.send("i'm client2, hello");
        }
    }
}
