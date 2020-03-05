package com.fun.classload.loading;

/**
 * @author huanye
 * @date: 2017/7/25 下午6:00
 */
public class SuperClass {

    static {
        System.out.println("SuperClass init...");
    }

    public static int value=100;
}
