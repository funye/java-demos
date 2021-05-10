package com.fun.spring.aop02;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RunTest {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("aop02-application.xml");
        AopBean aopBean = ctx.getBean("aopBean", AopBean.class);
        aopBean.sayHello1("AAA");
    }
}
