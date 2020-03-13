package com.fun.gc;

/**
 * testGC() 方法执行后，objA 和 objB会不会被GC呢 ？
 * Created by yehuan on 2017/2/14.
 * VM-args: -XX:+PrintGCDateStamps -XX:+PrintGCDetails/-Xlog:gc*
 */
public class ReferenceCountingGC {
    public Object instance;

    private static final int _1MB = 1024*1024;

    private byte[] bigSize = new byte[2*_1MB];

    public static void testGC() {
        ReferenceCountingGC objA = new ReferenceCountingGC();
        ReferenceCountingGC objB = new ReferenceCountingGC();

        objA.instance = objB;
        objB.instance = objA;

        objA = null;
        objB = null;

        System.gc();

    }

    public static void main(String[] args) {

        testGC();
    }

}
