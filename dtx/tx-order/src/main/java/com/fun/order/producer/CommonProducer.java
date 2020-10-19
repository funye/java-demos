package com.fun.order.producer;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

public class CommonProducer {

    public static void main(String[] args) throws UnsupportedEncodingException {
        DefaultMQProducer producer = new DefaultMQProducer();
        producer.setNamesrvAddr("127.0.0.1:9876");
        producer.setRetryTimesWhenSendFailed(2);
        producer.setSendMsgTimeout(6000);
        producer.setProducerGroup("producer-test");

        try {
            producer.start();
            System.out.println("producer start success....");
        } catch (MQClientException e) {
            e.printStackTrace();
            producer.shutdown();
            System.out.println("producer shutdown success....");
        }


        Message message = new Message();
        message.setTopic("test-topic");
        message.setKeys(UUID.randomUUID().toString());
        message.setBody("hello rocketmq yes".getBytes("utf-8"));
        try {
            producer.send(message, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    System.out.println("send success....");
                }

                @Override
                public void onException(Throwable throwable) {
                    System.out.println("send exception....");
                    throwable.printStackTrace();
                    producer.shutdown();
                }
            });
        } catch (MQClientException e) {
            e.printStackTrace();
        } catch (RemotingException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

}
