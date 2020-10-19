package com.fun.concurrent.deplayqueue;


import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.Timer;
import io.netty.util.TimerTask;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * netty时间论实现的延迟队列
 */
public class NettyDelayQueue {

    public static void main(String[] args) {

        final Timer timer = new HashedWheelTimer(Executors.defaultThreadFactory(), 5, TimeUnit.SECONDS, 2);

        TimerTask task1 = new TimerTask() {
            @Override
            public void run(Timeout timeout) throws Exception {
                System.out.println("order1 5s 后执行");
                timer.newTimeout(this, 5, TimeUnit.SECONDS); // 继续
            }
        };
        timer.newTimeout(task1, 5, TimeUnit.SECONDS);

        TimerTask task2 = new TimerTask() {
            @Override
            public void run(Timeout timeout) throws Exception {
                System.out.println("order2 10s 后执行");
                timer.newTimeout(this, 10, TimeUnit.SECONDS); // 继续
            }
        };
        timer.newTimeout(task2, 5, TimeUnit.SECONDS);


        timer.newTimeout(new TimerTask() {
            @Override
            public void run(Timeout timeout) throws Exception {
                System.out.println("order3 15s 执行一次");
            }
        }, 15, TimeUnit.SECONDS);

    }
}
