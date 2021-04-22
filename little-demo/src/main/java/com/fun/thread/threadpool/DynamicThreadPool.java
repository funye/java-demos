package com.fun.thread.threadpool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class DynamicThreadPool {

    public static void main(String[] args) throws Exception {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(3,
                10,
                10l, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(10));

        int count = 0;
        while (true) {
            Thread.sleep(1000l);
            for (int i = 0; i < 9; i++) {
                executor.execute(() -> {
                    /*try {
                        Thread.sleep(1l);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }*/
                    System.out.println("------------core:\t" + executor.getCorePoolSize() + "\tactive:\t" + executor.getActiveCount() + "\tmax:\t" + executor.getMaximumPoolSize());
                });
            }

            count++;
            if (count == 20) {
                executor.setCorePoolSize(2);
                executor.setMaximumPoolSize(9);
                System.out.println("----------------------------------------");
            }

            if (count == 100) {
                executor.shutdown();
                System.out.println("=============================================");
                break;
            }
        }

        Thread.currentThread().join();

    }
}
