package com.fun.spring.aop01;

import org.springframework.stereotype.Component;

@Component("aopBean")
public class AopBean {

    public void sayHello1(String name) {
        System.out.println("sayHello1: hello " +name);
    }

    public void sayHello2(String name) {
        System.out.println("sayHello2: hello " +name);
        sayHello(name);
    }

    public void sayHello3(String name) {
        sayHello1(name);
        System.out.println("sayHello3: hello " +name);
    }

    private void sayHello(String name){
        System.out.println("sayHello: hello " +name);
    }
}
