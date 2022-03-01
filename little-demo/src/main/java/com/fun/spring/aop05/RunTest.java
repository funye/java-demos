package com.fun.spring.aop05;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RunTest {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("aop05-application.xml");
//        AopBeanA aopBean = ctx.getBean("aopBeanA", AopBeanA.class);
//        aopBean.sayHello("AAA");

        UserInfo userInfo = new UserInfo();
        userInfo.setName("fun");
        userInfo.setAge(26);
        System.out.println("userInfo before=" + userInfo);
        AopBeanC aopBeanC = ctx.getBean("aopBeanC", AopBeanC.class);
        aopBeanC.query(userInfo);
        System.out.println("userInfo after=" + userInfo);



    }
}
