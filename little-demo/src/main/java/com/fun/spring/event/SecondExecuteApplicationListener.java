package com.fun.spring.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class SecondExecuteApplicationListener implements SmartApplicationListener {

    @Override
    public boolean supportsEventType(Class<? extends ApplicationEvent> eventType) {
        if (eventType.equals(MyApplicationEvent.class)) {
            return true;
        }
        return false;
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        System.out.println("Second  Listener on event happen...." + event.getSource().toString());
    }

    @Override
    public int getOrder() {
        return 2;
    }
}
