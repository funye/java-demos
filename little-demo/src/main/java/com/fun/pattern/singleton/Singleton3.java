package com.fun.pattern.singleton;

/**
 * 利用内部类改造之后的懒汉模式
 *
 * @author huanye
 * @date: 2017/6/29 下午11:51
 */
public class Singleton3 {

    private static class LazyHolder {
        private static final Singleton3 INSTANCE = new Singleton3();
    }

    private Singleton3() {
    }

    public static final Singleton3 getInstance() {
        return LazyHolder.INSTANCE;
    }

}
