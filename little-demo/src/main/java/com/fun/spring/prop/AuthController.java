package com.fun.spring.prop;

import org.springframework.stereotype.Component;

@Component
public class AuthController {

    @Auth(isNeedAuth = "${is_need_auth}")
    public void test(String name) {
        System.out.println("hello " + name);
    }
}
