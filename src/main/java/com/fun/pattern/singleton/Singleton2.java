package com.fun.pattern.singleton;

/**
 * 恶汉是单例模式，在对象加载的时候就创建了实例。不管使不使用实例，都创建好，线程安全。但是资源可能会浪费
 * @author huanye
 * @date: 2017/6/29 下午6:57
 */
public class Singleton2 {

    private static Singleton2 instance = new Singleton2();

    private Singleton2() {}

    public static Singleton2 getInstance() {
        return instance;
    }
}