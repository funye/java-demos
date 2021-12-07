package com.fun.spring.life;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
@Data
public class TestInitializingBean implements InitializingBean {

    private String name;

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("TestInitializingBean--->afterPropertiesSet...");
        this.name = "testName";
    }
}
