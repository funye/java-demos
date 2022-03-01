package com.fun.thread.threadlocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class InheritableThreadLocalDemo {

    private static ThreadLocal<String> name = new ThreadLocal<>();
    private static ThreadLocal<String> name2 = new InheritableThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {
        name.set("hello");
        name2.set("hello2");

        System.out.println(Thread.currentThread().getName() + " getName=" + name.get());
        System.out.println(Thread.currentThread().getName() + " getName2=" + name2.get());

        new Thread(new Runnable() {
            @Override
            public void run() {
                // 父线程（主线程）设置的ThreadLocal变量，子线程不可以获取到
                System.out.println(Thread.currentThread().getName() + " run in sub thread getName=" + name.get());
                // 父线程（主线程）设置的InheritableThreadLocal变量，子线程可以获取到
                System.out.println(Thread.currentThread().getName() + " run in sub thread getName2=" + name2.get());

                ThreadUtil.submit(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(Thread.currentThread().getName() + " submit in sub thread getName=" + name.get());
                        System.out.println(Thread.currentThread().getName() + " submit in sub thread getName2=" + name2.get());
                    }
                });
            }
        }).start();

        ThreadUtil.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " submit in main thread getName=" + name.get());
                System.out.println(Thread.currentThread().getName() + " submit in main thread getName2=" + name2.get());
            }
        });

        Thread.sleep(1000L);

        System.out.println(Thread.currentThread().getName() + "---main getName=:" + name.get());
        System.out.println(Thread.currentThread().getName() + "---main getName=:" + name2.get());
    }

    static class ThreadUtil {
        static ExecutorService executorService = Executors.newFixedThreadPool(1);
        public static void submit(Runnable task) {
            executorService.submit(task);
        }
    }
}
