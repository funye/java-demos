package com.fun.concurrent.lock;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

public class CLHLockTest {

    // 尾部节点
    private volatile String tail = null;

    private static final ThreadLocal<String> LOCAL = new ThreadLocal<>();

    private static final AtomicReferenceFieldUpdater<CLHLockTest, String> UPDATER =
            AtomicReferenceFieldUpdater.newUpdater(CLHLockTest.class, String.class, "tail");


    public void lock() {
        // 新建节点并将节点与当前线程保存起来
        String node = "lock";
        LOCAL.set(node);

        // 将新建的节点设置为尾部节点，并返回旧的节点（原子操作），这里旧的节点实际上就是当前节点的前驱节点
        String preNode = UPDATER.getAndSet(this, node);
        // 前驱节点不为null表示当锁被其他线程占用，通过不断轮询判断前驱节点
        while (preNode != null) {

        }
        // 如果不存在前驱节点，表示该锁没有被其他线程占用，则当前线程获得锁

        System.out.println("get lock success");
    }

    public void unlock() {
        // 获取当前线程对应的节点
        String node = LOCAL.get();
        // 如果tail节点等于node，则将tail节点更新为null，同时将node的lock状态职位false，表示当前线程释放了锁
        if (!UPDATER.compareAndSet(this, node, null)) {
            System.out.println("release lock exception");
        }
        System.out.println("release lock success");
        node = null;
    }

    public void doWithinLock(String task) {
        new Thread(() -> {
            try {

                lock(); // lock获取的排队号码可以被修改，在释放的时候是外部传入的

                System.out.println(task + ": get lock and start do business logic....");
                Thread.sleep(2000);
                System.out.println(task + ": get lock and finish do business logic....");

                unlock();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void main(String[] args) {
        CLHLockTest s = new CLHLockTest();

        s.doWithinLock("task 1");
        s.doWithinLock("task 2");
        s.doWithinLock("task 3");
    }
}
