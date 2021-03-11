package com.fun.websocket;

import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.HiddenFileFilter;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.io.File;
import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

public class TestWebSocketServer extends WebSocketServer {


    public TestWebSocketServer(InetSocketAddress address) {
        super(address);
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        System.out.println("open web socket server on port: " + conn.getLocalSocketAddress().getPort());

        // 使用的时候可以通过 handshake.getResourceDescriptor()值 判断为不同的客户端
        System.out.println("open web socket server: " + handshake.getResourceDescriptor());
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        System.out.println("web socket server close...");
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        System.out.println("receive message:" + message);
        conn.send("server receive message success...");
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        System.out.println("web socket server error...");
    }

    @Override
    public void onStart() {
        System.out.println("start web socket server...");
    }

    public static void startConfigMonitor(TestWebSocketServer server) throws Exception {
        // 监控目录
        String rootDir = "D:/Users/80279309/Documents/fun/test/";
        // 轮询间隔 5 秒
        long interval = TimeUnit.SECONDS.toMillis(1);
        // 创建过滤器
        IOFileFilter directories = FileFilterUtils.and(
                FileFilterUtils.directoryFileFilter(),
                HiddenFileFilter.VISIBLE);
        IOFileFilter files = FileFilterUtils.and(
                FileFilterUtils.fileFileFilter(),
                FileFilterUtils.suffixFileFilter(".txt"));
        IOFileFilter filter = FileFilterUtils.or(directories, files);

        // 使用过滤器
        FileAlterationObserver observer = new FileAlterationObserver(new File(rootDir), filter);
        //不使用过滤器
        //FileAlterationObserver observer = new FileAlterationObserver(new File(rootDir));

        observer.addListener(new FileChangeListener(server));
        //创建文件变化监听器
        FileAlterationMonitor monitor = new FileAlterationMonitor(interval, observer);
        // 开始监控
        monitor.start();
        System.out.println("监控服务启动");
    }

    public static void main(String[] args) throws Exception {
        // 启动推送
        InetSocketAddress addr = new InetSocketAddress("localhost", 8090);
        TestWebSocketServer server = new TestWebSocketServer(addr);
        server.start();

        startConfigMonitor(server);
    }
}
