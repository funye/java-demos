package com.fun.spring.prop;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RunTest {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("application-prop.xml");
        AuthController controller = ctx.getBean(AuthController.class);
        controller.test("fun");
        controller.test("fun-test");
    }
}
