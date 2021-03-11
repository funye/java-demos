package com.fun.concurrent.lock;

import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;

public class SpinLock {

    // 当前占用锁的对象
    private AtomicReference<Thread> owner = new AtomicReference<>();

    public boolean lock() {
        Thread current = Thread.currentThread();
        while (!owner.compareAndSet(null, current)) { // 获取失败则自旋

        }
        return true;
    }

    public void unlock() {
        Thread current = Thread.currentThread();
        if (!owner.compareAndSet(current, null)) {
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
        SpinLock s = new SpinLock();

        s.doWithinLock("task 1");
        s.doWithinLock("task 2");
        s.doWithinLock("task 3");

        Function<String, String> hello = new Function<String, String>() {
            @Override
            public String apply(String s) {
                return s + " hello";
            }
        };

        String str = "test";
        String rs = hello.apply(str);
        System.out.println(rs);
    }
}
