package com.fun.spring.aop05;

import org.springframework.stereotype.Component;

@Component("aopBeanC")
public class AopBeanC {

    public void query(UserInfo user) {
        System.out.println("query userInfo=" + user);
        user.setName("Kobe");
    }
}
