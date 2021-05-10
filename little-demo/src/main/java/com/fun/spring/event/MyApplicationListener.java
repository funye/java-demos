package com.fun.spring.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author huanye
 * @date: 2017/7/12 下午8:32
 */
@Component
public class MyApplicationListener implements ApplicationListener<MyApplicationEvent> {

    @Override
    public void onApplicationEvent(MyApplicationEvent myApplicationEvent) {

        System.out.println("event happen...." + myApplicationEvent.getSource().toString());
    }
}
