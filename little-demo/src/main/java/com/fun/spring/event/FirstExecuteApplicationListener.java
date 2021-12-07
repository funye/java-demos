package com.fun.spring.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.stereotype.Component;


/**
 * 通过 SmartApplicationListener 实现有序监听
 */
@Component
public class FirstExecuteApplicationListener implements SmartApplicationListener {

    @Override
    public boolean supportsEventType(Class<? extends ApplicationEvent> eventType) {
        if (eventType.equals(MyApplicationEvent.class)) {
            return true;
        }
        return false;
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        System.out.println("First Listener on event happen...." + event.getSource().toString());
    }


    @Override
    public int getOrder() {
        return 1;
    }
}
