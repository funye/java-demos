package com.fun.zkdemo;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.List;
import java.util.Objects;
import java.util.TreeSet;
import java.util.concurrent.CountDownLatch;

public class ZookeeperDistributedLock {


    // zk客户端
    private ZooKeeper zk;
    // zk是一个目录结构，root为最外层目录
    private String root = "/locks";
    // 用来同步等待zkclient链接到了服务端
    private CountDownLatch connectedSignal = new CountDownLatch(1);
    // 会话超时时间：毫秒
    private final static int sessionTimeout = 3000;
    // 节点数据：无需数据
    private final static byte[] data= new byte[0];

    /**
     * 创建一个zk分布式锁实例
     * @param config zk连接字符串
     * @param lockName 锁名称
     * @param isFair 是否公平
     * @return 公平 or 非公平锁对象
     */
    public static ZookeeperDistributedLock create(String config, String lockName, boolean isFair) {
        return isFair ? new ZookeeperDistributedLock(config, lockName).new FairLock(config, lockName)
                : new ZookeeperDistributedLock(config, lockName).new UnFairLock(config, lockName);
    }

    // 构造函数私有化
    private ZookeeperDistributedLock(String config, String lockName) {
        try {
            zk = new ZooKeeper(config, sessionTimeout, new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    // 建立连接
                    if (event.getState() == Event.KeeperState.SyncConnected) {
                        connectedSignal.countDown();
                    }
                }
            });
            // 等待连接建立完毕
            connectedSignal.await();
            Stat stat = zk.exists(root, false);
            if (null == stat) {
                // 创建根节点
                zk.create(root, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 锁
     * @throws Exception
     */
    public void lock() throws Exception {
        // 具体子类实现
        throw new UnsupportedOperationException("不支持的操作！");
    }

    /**
     * 解锁
     */
    public void unlock() {
        // 具体子类实现
        throw new UnsupportedOperationException("不支持的操作！");
    }

    // 监听器：一旦锁被释放，从等待状态唤醒继续往下执行
    private class LockWatcher implements Watcher {
        private CountDownLatch latch = null;

        public LockWatcher(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void process(WatchedEvent event) {

            if (event.getType() == Event.EventType.NodeDeleted)
                latch.countDown();
        }
    }

    // 公平锁实现
    private class FairLock extends ZookeeperDistributedLock {
        //锁的名称
        private String lockName;
        //当前线程创建的序列node
        private ThreadLocal<String> nodeId = new ThreadLocal<>();

        public FairLock(String config, String lockName) {
            super(config, lockName);
            this.lockName = lockName;
        }

        @Override
        public void lock() throws Exception {
            try {
                // 创建临时子节点，含序列
                String myNode = zk.create(root + "/" + lockName , data, ZooDefs.Ids.OPEN_ACL_UNSAFE,
                        CreateMode.EPHEMERAL_SEQUENTIAL);

                System.out.println(Thread.currentThread().getName() + myNode + "created");

                // 取出所有子节点
                List<String> subNodes = zk.getChildren(root, false);
                TreeSet<String> sortedNodes = new TreeSet<>();// 字典序
                for(String node :subNodes) {
                    sortedNodes.add(root +"/" +node);
                }

                String smallNode = sortedNodes.first();// 最小，即第一个创建的
                String preNode = sortedNodes.lower(myNode);// 前一个

                if (myNode.equals( smallNode)) {
                    // 如果是最小的节点,则表示取得锁
                    System.out.println(Thread.currentThread().getName() + myNode + "get lock");
                    this.nodeId.set(myNode);
                    return;
                }

                CountDownLatch latch = new CountDownLatch(1);
                Stat stat = zk.exists(preNode, new LockWatcher(latch));// 同时注册监听。
                // 判断比自己小一个数的节点是否存在,如果存在等待锁,同时注册监听
                if (stat != null) {
                    System.out.println(Thread.currentThread().getName() + myNode +
                            " waiting for " + root + "/" + preNode + " released lock");

                    latch.await();// 等待，这里应该一直等待其他线程释放锁
                    nodeId.set(myNode);
                    latch = null;
                }
                // 不存在，说明锁释放了，轮到自己了
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }

        @Override
        public void unlock() {
            try {
                System.out.println(Thread.currentThread().getName() + nodeId.get() + "unlock ");
                if (null != nodeId) {
                    zk.delete(nodeId.get(), -1);// 删除节点
                }
                nodeId.remove();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (KeeperException e) {
                e.printStackTrace();
            }
        }
    }

    // 非公平锁实现
    private class UnFairLock extends ZookeeperDistributedLock {
        // 锁的全路径
        private String lockPath;

        public UnFairLock(String config, String lockName) {
            super(config, lockName);
            lockPath = root + "/" + Objects.requireNonNull(lockName, "lockName不能为null！");
        }

        @Override
        public void lock() throws Exception {
            while (true) {
                try {
                    zk.create(lockPath , data, ZooDefs.Ids.OPEN_ACL_UNSAFE,
                            CreateMode.EPHEMERAL);
                    System.out.println(Thread.currentThread().getName() + "get lock");
                    return;
                } catch (KeeperException.NodeExistsException e) {// 说明锁被占用，注册监听并等待
                    CountDownLatch latch = new CountDownLatch(1);
                    Stat stat = zk.exists(lockPath, new LockWatcher(latch));// 注册监听。
                    if (stat != null) {
                        System.out.println(Thread.currentThread().getName() +
                                " waiting for " + lockPath + " released lock");

                        latch.await();// 等待
                        latch = null;
                    }
                } catch (Exception e) {
                    throw e;
                }
            }
        }

        @Override
        public void unlock() {
            try {
                System.out.println(Thread.currentThread().getName() + "unlock ");
                zk.delete(lockPath, -1);// 删除节点
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (KeeperException e) {
                e.printStackTrace();
            }
        }
    }

}
