package com.fun.stock.consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;

import java.io.UnsupportedEncodingException;

public class CommonConsumer {

    public static void main(String[] args) {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer();
        consumer.setNamesrvAddr("127.0.0.1:9876");
        consumer.setConsumerGroup("consumer-test");
        consumer.registerMessageListener((MessageListenerConcurrently) (list, consumeConcurrentlyContext) -> {
            for (MessageExt msg: list) {
                System.out.println("topic=" + msg.getTopic());
                System.out.println("keys=" + msg.getKeys());
                System.out.println("msgId=" + msg.getMsgId());
                try {
                    System.out.println("body=" + new String(msg.getBody(), "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });
        try {
            consumer.subscribe("test-topic", "*");
            consumer.start();
            System.out.println("consumer start success.....");
        } catch (Exception e) {
            e.printStackTrace();
            consumer.shutdown();
            System.out.println("consumer shutdown success.....");
        }

    }
}
