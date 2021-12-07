package com.fun.spring.life;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

@Component
public class TestDisposableBean implements DisposableBean {

    @Override
    public void destroy() throws Exception {
        System.out.println("TestDisposableBean--->destroy...");
    }
}
