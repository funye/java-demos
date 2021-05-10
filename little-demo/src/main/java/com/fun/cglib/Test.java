package com.fun.cglib;

import net.sf.cglib.proxy.Enhancer;

public class Test {

    public static void main(String[] args) {
        Enhancer enhancer =new Enhancer();
        enhancer.setSuperclass(Target.class);
        enhancer.setCallback(new TargetInterceptor());
        Target t=(Target)enhancer.create();
//        System.out.println(t);
        System.out.println(t.getName());
        System.out.println(t.getAge());
    }
}
