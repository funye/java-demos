package com.fun.thread.event;

import com.google.common.eventbus.Subscribe;

import java.util.concurrent.TimeUnit;

public class EventListener {

    // 只能是唯一参数的方法作为订阅者，详细查看Subscribe的javadoc
    @Subscribe
    public void listen(CustomEvent event) throws InterruptedException {
        System.out.println("start EventListener listen=>:" + event);
        TimeUnit.SECONDS.sleep(3);
        System.out.println("end EventListener listen=>:" + event);
    }

    @Subscribe
    public void listen2(CustomEvent event) throws InterruptedException {
        System.out.println("start EventListener listen2=>:" + event);
        TimeUnit.SECONDS.sleep(3);
        System.out.println("end EventListener listen2=>:" + event);
    }

}
