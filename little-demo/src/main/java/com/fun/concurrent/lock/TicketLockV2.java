package com.fun.concurrent.lock;

import java.util.concurrent.atomic.AtomicInteger;

public class TicketLockV2 {

    // 排队获取ticket的编号
    private AtomicInteger queueNum = new AtomicInteger();

    // 当前处理的ticket编号
    private AtomicInteger currentHandleTicket = new AtomicInteger(1);// 第一个待处理的人 获取的ticket的编号为1

    // 每个线程自己当前申请的ticket编号
    private ThreadLocal<Integer> currentTicket = new ThreadLocal<>();

    public void lock() {
        int currentTicketNum = queueNum.incrementAndGet(); // 取号
        currentTicket.set(currentTicketNum);
        while (currentTicketNum != currentHandleTicket.get()){ // 取号之后比较自己的号码和当前处理号码是否一致，不一致进行自旋

        }
    }

    public void unlock() {
        Integer ticketNum = currentTicket.get();
        if(!currentHandleTicket.compareAndSet(ticketNum, ticketNum + 1)) { // 呼叫下一个编号的人(即，当前处理编号加1)
            throw new RuntimeException("release lock exception");
        }
        System.out.println("release lock success");
    }

    public void doWithinLock(String task) {
        new Thread(() -> {
            try {

                lock(); // lock获取的排队号码可以被修改，在释放的时候是外部传入的

                System.out.println(task + ": get lock and start do business logic....");
                Thread.sleep(2000);
                System.out.println(task + ": get lock ticketNum=" + currentTicket.get());
                System.out.println(task + ": get lock and finish do business logic....");

                unlock();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void main(String[] args) {
        TicketLockV2 s = new TicketLockV2();

        s.doWithinLock("task 1");
        s.doWithinLock("task 2");
        s.doWithinLock("task 3");
    }



}
