package com.fun.order.test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Producer {

    public void init() {
        log.info("==========================init method in Producer");
    }

    public void destroy() {
        log.info("==========================destroy method in Producer");
    }

}
