package com.fun.websocket;


import com.corundumstudio.socketio.*;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import io.socket.client.Socket;

public class NettySocketIoWebSocketServer {

    public static void main(String[] args) throws InterruptedException {
        Configuration config = new Configuration();
        config.setHostname("localhost");
        config.setPort(9090);

        final SocketIOServer server = new SocketIOServer(config);

        server.addConnectListener(new ConnectListener() {
            @Override
            public void onConnect(SocketIOClient client) {
                System.out.println("sessionId:" + client.getSessionId());
            }
        });


//        server.addEventListener("msg", String.class, new DataListener<String>() {
//            @Override
//            public void onData(SocketIOClient client, String data, AckRequest ackSender) throws Exception {
//                System.out.println("receive client msg:" + data);
////                server.getBroadcastOperations().sendEvent(Socket.EVENT_MESSAGE, "你好");
//
//                Collection<SocketIOClient> clientList = server.getBroadcastOperations().getClients();
//                SocketIOClient client1 = clientList.stream().filter(e->e.getNamespace().getName().equals("/client1")).findFirst().get();
//                client.sendEvent(Socket.EVENT_MESSAGE, "hello client1");
//            }
//        });

        SocketIONamespace client1 = server.addNamespace("/client1");
        client1.addEventListener("msg", String.class, new DataListener<String>() {
            @Override
            public void onData(SocketIOClient client, String data, AckRequest ackSender) throws Exception {
                System.out.println("receive client1 msg:" + data);
                client.sendEvent(Socket.EVENT_MESSAGE, "你好 client1");
            }
        });

        server.start();
        System.out.println("start success");
    }

}
