package com.fun.spring.aop04;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RunTest {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("aop04-application.xml");
        AopBean aopBean = (AopBean) ctx.getBean("aopBean");
        aopBean.sayHello1("AAA");
        System.out.println("===========================");
        aopBean = (AopBean) ctx.getBean("aopBeanProxy");
        aopBean.sayHello1("AAA");
    }
}
