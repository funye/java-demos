package com.fun.jvm;

import sun.misc.SharedSecrets;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.nio.ByteBuffer;

/**
 * https://tech.meituan.com/2019/02/14/talk-about-java-magic-class-unsafe.html Unsafe类
 * 直接内存溢出示例
 * Created by fun on 2017/2/16.
 * VM-args: -Xmx20M -XX:MaxDirectMemorySize=5M -XX:+PrintGCDetails -XX:+HeapDumpBeforeFullGC -XX:+HeapDumpAfterFullGC -XX:HeapDumpPath=D:\Users\80279309\Documents\tempwork\dmemoom.dump
 * <p>
 * MaxDirectMemorySize 在直接内存超出这个值的时候，开始进行GC和Full GC
 */
public class DirectMemoryOOM {

    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) {
//		useUnsafe();
        useBuffer();
    }

    public static void useUnsafe() {
        long count = 0;
        System.out.println(sun.misc.VM.maxDirectMemory() / 1024 / 1024 + "M");
        try {
            Field unsafeField = Unsafe.class.getDeclaredFields()[0];
            unsafeField.setAccessible(true);
            Unsafe unsafe = (Unsafe) unsafeField.get(null);
            while (true) {
                count++;
                unsafe.allocateMemory(_1MB * 10);
            }
        } catch (Throwable e) {
            System.out.println("count=" + count);
            e.printStackTrace();
        }
    }

    public static void useBuffer() {
        long count = 0;
        System.out.println("maxDirectMemory=" + sun.misc.VM.maxDirectMemory() / 1024 / 1024 + "M");
        try {
            while (true) {
                ByteBuffer.allocateDirect(_1MB);
                System.out.println("Xmx=" + Runtime.getRuntime().maxMemory() / 1024.0 / 1024 + "M" + //JVM尝试使用的最大空间
                        ", free mem=" + Runtime.getRuntime().freeMemory() / 1024.0 / 1024 + "M" + //JVM的空闲空间
                        ",total mem=" + Runtime.getRuntime().totalMemory() / 1024.0 / 1024 + "M"); //当前可用的总空间
                System.out.println("directMemoryUsed=" + SharedSecrets.getJavaNioAccess().getDirectBufferPool().getMemoryUsed() / 1024 / 1024 + "M");
                System.out.println("============================================================");
            }
        } catch (Throwable e) {
            System.out.println("count=" + count);
            e.printStackTrace();
        }
    }
}
