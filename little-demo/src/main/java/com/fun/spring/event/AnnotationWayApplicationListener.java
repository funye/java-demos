package com.fun.spring.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
public class AnnotationWayApplicationListener {

    @Order(3)
    @EventListener(classes = {MyApplicationEvent.class})
    public void onEvent(ApplicationEvent event) {
        System.out.println("Annotation Listener on event happen...." + event.getSource().toString());
    }
}
