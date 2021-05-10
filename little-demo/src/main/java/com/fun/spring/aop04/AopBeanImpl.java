package com.fun.spring.aop04;

import org.springframework.stereotype.Component;

@Component("aopBean")
public class AopBeanImpl implements AopBean {

    @Override
    public void sayHello1(String name) {
        System.out.println("sayHello1: hello " +name);
    }
}
