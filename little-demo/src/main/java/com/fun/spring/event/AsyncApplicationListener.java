package com.fun.spring.event;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadFactory;

@EnableAsync
@Service
public class AsyncApplicationListener {

    @Async
    @Order(4)
    @EventListener(classes = {MyApplicationEvent.class})
    public void onEvent(ApplicationEvent event) throws InterruptedException {
        System.out.println("Async listener start event happen....");
        System.out.println("Async listener event happen...." + event.getSource().toString());
        Thread.sleep(3000);
        System.out.println("Async listener end happen...." + event.getSource().toString());
    }

    @Bean
    public TaskExecutor taskExecutor() {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("consumer-queue-thread-%d").build();
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 线程池维护线程的最少数量
        executor.setCorePoolSize(5);
        // 线程池维护线程的最大数量
        executor.setMaxPoolSize(10);
        // 缓存队列
        executor.setQueueCapacity(25);
        //线程名
        executor.setThreadFactory(namedThreadFactory);
        // 线程池初始化
        executor.initialize();
        return executor;
    }
}
