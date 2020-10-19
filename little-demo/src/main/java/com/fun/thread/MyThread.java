package com.fun.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程类
 *
 * @author fun
 * @version v1.0.0
 * @create 2017-03-13 21:51
 */
public class MyThread {

    private static volatile int a = 0;

    public static void main(String[] args) throws InterruptedException {

//		MyThread t = new MyThread();
//		t.test1();
        testVolatile();

        while (Thread.activeCount() > 2) {
            System.out.println("waiting....");
        }
        System.out.println("a = " + a);
    }

    public void test1() throws InterruptedException {

        int count = 10;

        // 以下四种情况分开运行看效果
        // 1. 每个线程都有一个新的Runnable
        for (int i = 0; i < 10; i++) {
            new Thread(new TestTask(i + 1, count)).start();
            Thread.sleep(200);
        }

        // 2. 每个线程都有相同的Runnable
        TestTask testTask = new TestTask(1, count);
        for (int i = 0; i < 10; i++) {
            new Thread(testTask).start();
            Thread.sleep(200);
        }

        // 3. 线程池使用不同的Runnable
        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
        executorService.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardOldestPolicy());
        for (int i = 0; i < 10; i++) {
            executorService.execute(new TestTask(1, count));
        }
        executorService.shutdown();

        // 4. 线程池使用相同的Runnable
        ExecutorService executorService2 = Executors.newFixedThreadPool(2);
        TestTask testTask2 = new TestTask(1, count);
        for (int i = 0; i < 10; i++) {
            executorService2.execute(testTask2);
        }
        executorService2.shutdown();
    }

    public static void testVolatile() {

        Runnable task = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                a = a + 1;
//                a++;
            }
        };

        for (int i = 0; i < 100; i++) {
            new Thread(Thread.currentThread().getThreadGroup(), task).start();
        }

    }
}
