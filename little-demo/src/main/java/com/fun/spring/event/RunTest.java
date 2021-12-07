package com.fun.spring.event;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RunTest {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("application-event.xml");

        ctx.publishEvent(new MyApplicationEvent("HelloEvent"));

        System.out.println("========started=======");
        /*
        First Listener on event happen....HelloEvent
        Second  Listener on event happen....HelloEvent
        Annotation Listener on event happen....HelloEvent
        Default  Listener on event happen....HelloEvent
        ========started=======
        Async listener start event happen....
        Async listener event happen....HelloEvent
        Async listener end happen....HelloEvent
         */
        // AsyncListener 不会在 publishEvent 后阻塞，而是直接继续执行
    }
}
