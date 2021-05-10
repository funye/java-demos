package com.fun.spring.aop01;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RunTest {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("aop01-application.xml");
        AopBean aopBean = ctx.getBean("aopBean", AopBean.class);
        aopBean.sayHello1("AAA");
        System.out.println("============================");
        aopBean.sayHello2("BBB");
        System.out.println("============================");
        aopBean.sayHello3("CCC");
    }
}
