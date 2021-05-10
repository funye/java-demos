package com.fun.thread.threadlocal;

import com.fun.collection.User;

public class ThreadLocalDemo {

    private static ThreadLocal<String> name = new ThreadLocal<>();
    private static ThreadLocal<User> user = new ThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {

        name.set("hello");
        user.set(new User("fun", 30));

        System.out.println(Thread.currentThread().getName() + ":" + name.get());
        System.out.println(Thread.currentThread().getName() + ":" + user.get());

        new Thread(new Runnable() {
            @Override
            public void run() {
                name.set("hello2");
                user.set(new User("fun2", 30));

                System.out.println(Thread.currentThread().getName() + ":" + name.get());
                System.out.println(Thread.currentThread().getName() + ":" + user.get());
            }
        }).start();

        Thread.sleep(3000L);

        System.out.println(Thread.currentThread().getName() + ":" + name.get());
        System.out.println(Thread.currentThread().getName() + ":" + user.get());

    }
}
