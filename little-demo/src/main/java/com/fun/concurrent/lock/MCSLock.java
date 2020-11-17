package com.fun.concurrent.lock;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * CLH是在前驱节点的属性上自旋，而MCS是在本地属性变量上自旋
 */
public class MCSLock {

    public static class MCSNode {
        volatile MCSNode next;
        volatile boolean isLocked = false;
    }

    private static final ThreadLocal<MCSNode> NODE = new ThreadLocal<>();

    // 队列
    private volatile MCSNode queue;

    private static final AtomicReferenceFieldUpdater<MCSLock,MCSNode> UPDATE =
            AtomicReferenceFieldUpdater.newUpdater(MCSLock.class,MCSNode.class,"queue");


    public void lock(){
        // 创建节点并保存到ThreadLocal中
        MCSNode currentNode = new MCSNode();
        NODE.set(currentNode);

        // step1: 将queue设置为当前节点，并且返回之前的节点
        MCSNode preNode = UPDATE.getAndSet(this, currentNode);
        if (preNode != null) {
            // step2: 如果之前节点不为null，表示锁已经被其他线程持有
            preNode.next = currentNode;
            // 循环判断，直到当前节点的锁标志位为true
            while (!currentNode.isLocked) {
            }
        }
    }

    public void unlock() {
        MCSNode currentNode = NODE.get();

        if (currentNode.next == null) { // next为null表示没有正在等待获取锁的线程
            // 更新状态并设置queue为null
            if (UPDATE.compareAndSet(this, currentNode, null)) {
                // 如果成功了，表示queue==currentNode,即当前节点后面没有节点了，释放锁成功
                return;
            } else {
                // 如果不成功，表示queue!=currentNode,即当前节点后面多了一个节点，表示有线程在等待
                // 如果当前节点的后续节点为null，则需要等待其不为null，这里之所以要等是因为：step1 执行完后，step2 可能还没执行完
                while (currentNode.next == null) {
                }
                // 释放锁，让后面的人继续
                currentNode.next.isLocked = true;
                currentNode.next = null;
            }
        } else { // 如果不为null，表示有线程在等待获取锁，此时将等待线程对应的节点锁状态更新为true，让他可以结束自旋获取到锁，同时将当前线程的后继节点设为null
            currentNode.next.isLocked = true;
            currentNode.next = null;
        }
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
        MCSLock s = new MCSLock();

        s.doWithinLock("task 1");
        s.doWithinLock("task 2");
        s.doWithinLock("task 3");
    }
}
