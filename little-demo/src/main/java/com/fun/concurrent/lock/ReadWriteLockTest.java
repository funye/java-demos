package com.fun.concurrent.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockTest {

    public static void main(String[] args) throws InterruptedException {

        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

        ReadWriteLockTest t = new ReadWriteLockTest();

        t.doWithinLock("task 11", lock.writeLock());
        Thread.sleep(200);
        t.doWithinLock("task 1", lock.readLock());
        t.doWithinLock("task 2", lock.readLock());
        t.doWithinLock("task 3", lock.readLock());

        t.doWithinLock("task 4", lock.writeLock());
        t.doWithinLock("task 5", lock.writeLock());
        t.doWithinLock("task 6", lock.writeLock());

//        t.doWithinLock("task 1", lock.readLock());
//        t.doWithinLock("task 2", lock.readLock());
//        t.doWithinLock("task 3", lock.readLock());

        /*
         * 写锁之间互斥，需要等待之前的锁释放
         *
         * 读锁之间不互斥，不需要等待
         *
         * 如果存在读锁，则写锁不能被获取，原因在于：必须确保写锁的操作对读锁可见，如果允许读锁在已被获取的情况下对写锁的获取，那么正在运行的其他读线程就无法感知到当前写线程的操作。
         *
         * 因此，只有等待其他读线程都释放了读锁，写锁才能被当前线程获取，而写锁一旦被获取，则其他读写线程的后续访问均被阻塞
         */

    }

    public void doWithinLock(String task, Lock lock) {
        new Thread(() -> {
            try {

                lock.lock(); // lock获取的排队号码可以被修改，在释放的时候是外部传入的

                System.out.println(task + ": get lock and start do business logic....");
                Thread.sleep(2000);
                System.out.println(task + ": get lock and finish do business logic....");

                lock.unlock();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
