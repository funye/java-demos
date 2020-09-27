package com.fun.thread;

public class JoinInterruptDemo {

    public static void main(String[] args) throws InterruptedException {

        Thread thread1 = new Thread(() -> {

            while(true) {
                try {
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName() + " run");
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName() + " interrupt wakeup.....");
                }
            }

        });
        thread1.setName("thread1");


        Thread thread2 = new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " start");
                while (thread1.isAlive()) {
//                    thread1.interrupt();
                    try {
                        System.out.println(thread1.getName() + " is alive=" + thread1.isAlive());
                        thread1.join(10000);
                    } catch (InterruptedException e) {
                        System.out.println(Thread.currentThread().getName() + " join interrupt wakeup.....");
                    }
                }
        });
        thread2.setName("thread2");

        Thread thread3 = new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + " start");
                Thread.sleep(6000); // 保证Thread1启动
                thread2.interrupt();
                System.out.println(Thread.currentThread().getName() + " end");
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " interrupt wakeup.....");
            }
        });
        thread3.setName("thread3");

        Thread thread4 = new Thread(() -> {
            while(true) {
                try {
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName() + " run");
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName() + " interrupt wakeup.....");
                }
            }
        });
        thread4.setName("thread4");

        thread1.start();
        thread4.start();
        Thread.sleep(1000);
        thread2.start();
//        thread3.start();
        System.out.println("main end....");


        JoinInterruptDemo d = new JoinInterruptDemo();
        d.join(); // d.wait

    }

    public void join() throws InterruptedException {
        wait(0); // d
    }
}
