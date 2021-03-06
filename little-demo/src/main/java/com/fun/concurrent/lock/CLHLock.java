package com.fun.concurrent.lock;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

public class CLHLock {

    public static class CLHNode {
        private volatile boolean isLocked = true;
    }

    // 尾部节点
    private volatile CLHNode tail;

    private static final ThreadLocal<CLHNode> LOCAL = new ThreadLocal<>();

    private static final AtomicReferenceFieldUpdater<CLHLock, CLHNode> UPDATER =
            AtomicReferenceFieldUpdater.newUpdater(CLHLock.class, CLHNode.class, "tail");


    public void lock() {
        // 新建节点并将节点与当前线程保存起来
        CLHNode node = new CLHNode();
        LOCAL.set(node);

        // 将新建的节点设置为尾部节点，并返回旧的节点（原子操作），这里旧的节点实际上就是当前节点的前驱节点
        CLHNode preNode = UPDATER.getAndSet(this, node);
        if (preNode != null) {
            // 前驱节点不为null表示当锁被其他线程占用，通过不断轮询判断前驱节点的锁标志位等待前驱节点释放锁
            while (preNode.isLocked) {

            }
        }
        // 如果不存在前驱节点，表示该锁没有被其他线程占用，则当前线程获得锁

        System.out.println("get lock success");
    }

    public void unlock() {
        // 获取当前线程对应的节点
        CLHNode node = LOCAL.get();
        // 如果队列里只有当前线程，则释放对当前线程的引用（for GC）。
        if (!UPDATER.compareAndSet(this, node, null)) {
            node.isLocked = false; // 如果还有后续线程,改变状态，让后续线程结束自旋
        }
    }

    public void remove() {
        LOCAL.remove();
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
        CLHLock s = new CLHLock();

        s.doWithinLock("task 1");
        s.doWithinLock("task 2");
        s.doWithinLock("task 3");
    }
}
