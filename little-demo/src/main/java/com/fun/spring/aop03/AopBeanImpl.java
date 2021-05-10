package com.fun.spring.aop03;

public class AopBeanImpl implements AopBean {
    @Override
    public void sayHello1(String name) {
        System.out.println("sayHello1: hello " +name);
    }
}
