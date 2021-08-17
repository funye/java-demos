package com.fun.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionDemo {


    public static void main(String[] args) throws InterruptedException {
        /**
         *  Condition 需要配合lock 来使用，利用 lock.newCondition() 生成对象
         *  效果类似 wait -- notify/notifyAll
         */


        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        Thread thread = new Thread(() -> {

            lock.tryLock();
            try {
                System.out.println("wait signal");
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("got signal");
            lock.unlock();
        });

        Thread thread2 = new Thread(() -> {

            lock.tryLock();
            System.out.println("i got the lock");
            try {
                TimeUnit.SECONDS.sleep(1);
                condition.signal();
                System.out.println("i send a signal");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.unlock();
        });

        thread.start();
        TimeUnit.MILLISECONDS.sleep(10);
        thread2.start();
        TimeUnit.SECONDS.sleep(2);
    }
}
