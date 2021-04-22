package com.fun.thread.dog;

import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.Timer;
import io.netty.util.TimerTask;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class WatchDogDemo {

    public static final Timer timer = new HashedWheelTimer(Executors.defaultThreadFactory(), 5, TimeUnit.SECONDS, 2);
    public static ConcurrentHashMap<String, Thread> workThreadMap = new ConcurrentHashMap<>();

    public static void main(String[] args) {

        // 工作线程
        String threadName = "work-thread";
        Thread worker = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("start do work....");
                print();
                try {
                    Thread.sleep(30 * 1000L);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("finish work....");
            }
        }, "work-thread");

        // 监听任务没有结束则续期
        TimerTask task = new TimerTask() {
            @Override
            public void run(Timeout timeout) throws Exception {
                Thread worker = workThreadMap.get(threadName);
                System.out.println("worker is or not null:" + (worker == null));
                // redisson不是通过判断线程状态 还是在unlock时候删除workThreadMap中work的线程id 然后判断对应threadId为空的时候结束续期动作
                if (worker != null && worker.isAlive()) {
                    System.out.println("worker is alive, lease time 5 second");
                    timer.newTimeout(this, 5, TimeUnit.SECONDS);
                } else {
                    System.out.println("worker is not alive, cancel watch");
                    workThreadMap.remove(threadName);
                }

            }
        };
        timer.newTimeout(task, 5, TimeUnit.SECONDS);

        // 启动任务
        workThreadMap.put(threadName, worker);
        worker.start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                    print();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "test-thread").start();
    }

    public static void print() {
        System.out.println(Thread.currentThread().getName());
    }
}
