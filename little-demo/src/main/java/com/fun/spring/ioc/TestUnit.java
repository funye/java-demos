package com.fun.spring.ioc;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestUnit {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("beanFactoryTest.xml");
        TestBean bean = (TestBean) ctx.getBean("testBean");
        bean.sayHello();

        long deta = (269195788498969600L-269133033127995904L)/6;
        System.out.println(deta);
        for (int i=0; i<10;i++) {
            System.out.println(269133033127995904L+ i*deta);
        }

    }
}
