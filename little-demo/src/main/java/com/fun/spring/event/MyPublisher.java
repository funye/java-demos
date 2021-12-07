package com.fun.spring.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

@Component
public class MyPublisher implements ApplicationEventPublisherAware {

    // 持有一个事件发布器
    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        // 通过Aware给applicationEventPublisher赋值
        this.applicationEventPublisher = applicationEventPublisher;
    }

    /**
     * 封装事件发布方法，在需要的发布事件的时候调用
     * @param event
     */
    public void publishEvent(ApplicationEvent event) {
        this.applicationEventPublisher.publishEvent(event);
    }
}
