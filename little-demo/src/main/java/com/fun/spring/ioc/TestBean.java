package com.fun.spring.ioc;

import lombok.Data;

@Data
public class TestBean {

    private String name;

    public void sayHello() {
        System.out.println("hello spring");
    }
}
