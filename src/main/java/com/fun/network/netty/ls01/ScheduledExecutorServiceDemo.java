package com.fun.network.netty.ls01;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorServiceDemo {

    public static void main(String[] args) {

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);

        executorService.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("10 seconds later...");
            }
        }, 10, TimeUnit.SECONDS);

        executorService.shutdown();
        System.out.println("main end");
    }
}
