package com.fun.concurrent.lock;

import java.util.concurrent.atomic.AtomicReference;

public class ReentrantSpinLock {

    // 当前占用锁的对象
    private AtomicReference<Thread> owner = new AtomicReference<>();

    // 重入次数
    private volatile int count = 0;

    public boolean lock() {
        Thread current = Thread.currentThread();
        if (current == owner.get()) {
            count++;
            return true;
        }
        while (!owner.compareAndSet(null, current)) { // 获取失败则自旋

        }
        return true;
    }

    public void unlock() {
        Thread current = Thread.currentThread();
        if (current == owner.get()) {
            if (count != 0) {
                count--;
                System.out.println("release reentrant lock");
            } else {
                owner.compareAndSet(current, null);
                System.out.println("release lock success");
            }
            return;
        }
        System.out.println("release lock failed");
    }

    public void doWithinLock(String task) {
        new Thread(() -> {
            try {

                lock();
                lock();
                System.out.println(task + ": get lock and start do business logic....");
                Thread.sleep(2000);
                System.out.println(task + ": get lock and finish do business logic....");
                unlock();
                unlock();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantSpinLock s = new ReentrantSpinLock();

        s.doWithinLock("task 1");
        s.doWithinLock("task 2");
        s.doWithinLock("task 3");
    }
}
