package com.fun.classload.loading;

/**
 * @author huanye
 * @date: 2017/7/25 下午6:16
 */
public class ConstClass {

    static {
        System.out.println("ConstClass init...");
    }

    public static final String HELLO = "hello world";
}
