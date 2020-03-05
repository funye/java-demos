package com.fun.pattern.singleton;

/**
 * 懒汉式单例模式，在使用的时候才创建实例，创建时候为了线程安全需要各种枷锁机制。但是节约资源
 * @author huanye
 * @date: 2017/6/29 下午6:55
 */
public class Singleton {

    private static Singleton instance = null;

    private Singleton() {

    }

    public static Singleton getInstance() {

        if (instance == null) { // 第一重校验
            synchronized (Singleton.class) {
                // 注意锁的位子，在第一层校验之后多第二层校验枷锁，
                // 因为能够通过的数量是极少的，这里枷锁可以减少锁
                if (instance == null) { // 第二重校验，避免创建多个实例
                    instance = new Singleton();
                }
            }

        }
        return instance;
    }

}
