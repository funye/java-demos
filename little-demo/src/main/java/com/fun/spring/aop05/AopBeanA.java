package com.fun.spring.aop05;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("aopBeanA")
public class AopBeanA {

    @Autowired
    private AopBeanB aopBeanB;

    public void sayHello(String name) {
        System.out.println("sayHelloA: hello " +name);
    }

}
