package com.fun.concurrent.deplayqueue;

import lombok.Data;

import java.lang.reflect.Field;
import java.util.Timer;
import java.util.TimerTask;

public class DeleyQueueJdk {

    public static void main(String[] args) {

        long delay = 5 * 1000;
        Counter counter = new Counter();

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("执行任务, 当前重试次数为：" + counter.currentRetry);

                try {
                    // 这段代码主要是用于让task可以重复使用，默认只能使用一次
                    Field field = TimerTask.class.getDeclaredField("state");
                    field.setAccessible(true);
                    field.set(this, 0);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (counter.currentRetry < counter.maxRetry ) {
                    timer.schedule(this, delay); // 继续
                }
                counter.currentRetry++;
            }
        };

        timer.schedule(task, 0);

    }

    @Data
    static class Counter {
        int maxRetry = 3;
        int currentRetry = 0;
    }
}
