package com.fun.spring.aop05;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RunTest {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("aop05-application.xml");
        AopBeanA aopBean = ctx.getBean("aopBeanA", AopBeanA.class);
        aopBean.sayHello("AAA");

    }
}
