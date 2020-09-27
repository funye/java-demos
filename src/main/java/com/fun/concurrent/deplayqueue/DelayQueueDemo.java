package com.fun.concurrent.deplayqueue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 利用DelayQueue实现的延时处理
 */
public class DelayQueueDemo {

    public static void main(String[] args) throws InterruptedException {


        Order o1 = new Order("order1", 5, TimeUnit.SECONDS);
        Order o2 = new Order("order2", 10, TimeUnit.SECONDS);
        Order o3 = new Order("order3", 15, TimeUnit.SECONDS);

        DelayQueue<Order> delayQueue = new DelayQueue<>();
        delayQueue.add(o1);
        delayQueue.add(o3);
        delayQueue.add(o2);

        System.out.println("订单延迟任务开始时间：" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        // 此处循环获取交给线程处理
        while (delayQueue.size() > 0) {
            Order task = delayQueue.poll();
            if (task != null) {
                System.out.format("订单: {%s} 被取消， 取消时间: {%s}\n", task.orderId, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            }
            Thread.sleep(500);
        }

    }

    static class Order implements Delayed {

        private String orderId;
        private long expireTime; // mills

        public Order(String orderId, long timeout, TimeUnit timeUnit) {
            this.orderId = orderId;
            this.expireTime = System.currentTimeMillis() + (timeout > 0 ? timeUnit.toMillis(timeout) : 0);
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return expireTime - System.currentTimeMillis();
        }

        @Override
        public int compareTo(Delayed o) {
            Order order = (Order) o;
            long diff = this.expireTime - order.expireTime;
            if (diff <= 0) {
                return -1;
            }
            return 1;
        }
    }
}
