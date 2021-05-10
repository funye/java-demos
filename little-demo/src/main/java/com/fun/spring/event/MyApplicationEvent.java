package com.fun.spring.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author huanye
 * @date: 2017/7/12 下午8:31
 */
public class MyApplicationEvent extends ApplicationEvent {

    public MyApplicationEvent(Object source) {
        super(source);
    }
}
