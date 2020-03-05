package com.fun.zkdemo;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;

/**
 * @author huanye
 * Date: 2017/9/1 下午2:55
 */
public class ZKTest implements Watcher {

    public static final int SESSION_TIMEOUT = 2000;
    ZooKeeper zk;

    public static void main(String[] args) {

        ZKTest zkTest = new ZKTest("127.0.0.1:2181",SESSION_TIMEOUT);
        String testData = zkTest.getData("/test_node");
        System.out.println(testData);
        zkTest.setData("/test_node","hello zookeeper");
    }

    public ZKTest(String connString,int sessionTimeout) {
        try {
            zk = new ZooKeeper(connString, sessionTimeout, this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getData(String path) {
        byte[] data = new byte[0];
        try {
            data = this.zk.getData(path,this,null);
            System.out.println("session id=" + zk.getSessionId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String(data);
    }

    public void setData(String path,String data) {
        try {
            Stat stat = this.zk.exists(path,null);
            this.zk.setData(path,data.getBytes(),stat.getVersion());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void process(WatchedEvent event) {
        System.out.println("keeperState=" + event.getState().getIntValue());
        System.out.println("EventType=" + event.getState().getIntValue());
    }
}
