package com.fun.thread.event;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class AppRun {

    public static void main(String[] args) {
        // 同步eventbus
        EventBus bus = new EventBus();
        bus.register(new EventListenerSecond()); // listener 添加顺序为执行顺序
        bus.register(new EventListener());
        bus.post(new CustomEvent("1", "test data"));

        // 异步eventbus
        AsyncEventBus asyncEventBus = new AsyncEventBus(new ThreadPoolExecutor(2,4,
                1000, TimeUnit.SECONDS, new ArrayBlockingQueue<>(512)));
        asyncEventBus.register(new EventListener());
        asyncEventBus.register(new EventListenerSecond());
        asyncEventBus.post(new CustomEvent("2", "test data"));


    }
}
