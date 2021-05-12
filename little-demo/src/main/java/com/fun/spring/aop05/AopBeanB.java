package com.fun.spring.aop05;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("aopBeanB")
public class AopBeanB {

    @Autowired
    private AopBeanA aopBeanA;

    public void sayHello(String name){
        System.out.println("sayHelloB: hello " +name);
    }
}
