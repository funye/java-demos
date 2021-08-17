package com.fun.concurrent;

import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.dsl.Disruptor;
import jodd.util.concurrent.ThreadFactoryBuilder;
import lombok.Data;

import java.util.concurrent.TimeUnit;

/**
 * 参考文档 https://tech.meituan.com/2016/11/18/disruptor.html
 *
 * demo: https://blog.csdn.net/a78270528/article/details/79925404
 *
 */
public class RingBufferDemo {

    public static void main(String[] args) throws InterruptedException {

        Disruptor disruptor = new Disruptor(new EventFactory<TestEvent>() {
            @Override
            public TestEvent newInstance() {
                return new TestEvent();
            }
        }, 1024, new ThreadFactoryBuilder().setNameFormat("test-ring-buffer-pool").get());


        // 注册消费者
        disruptor.handleEventsWith(new EventHandler<TestEvent>() {
            @Override
            public void onEvent(TestEvent event, long sequence, boolean endOfBatch) throws Exception {
                System.out.println("handle event seq=" + event.getSeq() + ", action="+event.getAction());
                event = null;
            }
        }).then((EventHandler<TestEvent>) (event, sequence, endOfBatch) -> {
            System.out.println("handle2 event seq=" + event.getSeq() + ", action="+event.getAction());
            event = null;
        });

        // 启动
        disruptor.start();

        // 事件转换器
        EventTranslatorOneArg translator = (EventTranslatorOneArg<TestEvent, String>) (event, sequence, arg0) -> {
            String args1[] = arg0.split("#");
            event.setSeq(args1[0]);
            event.setAction(args1[1]);
        };

        // 模拟触发事件
        for (int i = 0; i < 10; i++) {
            // 发布数据
            disruptor.getRingBuffer().publishEvent(translator, i+"#event parameter"+i);
            TimeUnit.SECONDS.sleep(1);
        }
    }

    @Data
    static class TestEvent {
        String seq;
        String action;
    }
}
