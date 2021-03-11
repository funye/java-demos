package com.fun.websocket;

import java.net.URI;

public class TestClient1 {

    public static void main(String[] args) throws Exception {
        URI uri = new URI("ws://localhost:8090/client1");
        TestWebSocketClient client = new TestWebSocketClient(uri);
        boolean isConnected = client.connectBlocking();
        if (isConnected) {
            client.send("i'm client1, hello");
        }
    }
}
