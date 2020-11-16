package com.fun.concurrent.lock;

import java.util.concurrent.atomic.AtomicBoolean;

public class SpinLockDemo {

    private AtomicBoolean available = new AtomicBoolean(false);

    public boolean lock() {
        while (!available.compareAndSet(false, true)) { // 获取失败则自旋

        }
        return true;
    }

    public void unlock() {
        if (!available.compareAndSet(true, false)) {
            throw new RuntimeException("release lock exception");
        }
        System.out.println("release lock success");
    }

    public void doWithinLock(String task) {
        new Thread(() -> {
            try {

                lock();

                System.out.println(task + ": get lock and start do business logic....");
                Thread.sleep(2000);
                System.out.println(task + ": get lock and finish do business logic....");

                unlock();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void main(String[] args) throws InterruptedException {
        SpinLockDemo s = new SpinLockDemo();

        s.doWithinLock("task 1");
        s.doWithinLock("task 2");
        s.doWithinLock("task 3");
    }
}
