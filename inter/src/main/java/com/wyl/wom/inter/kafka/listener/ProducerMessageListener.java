package com.wyl.wom.inter.kafka.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.MessageListener;

public class ProducerMessageListener implements MessageListener<String,String> {
    @Override
    public void onMessage(ConsumerRecord<String, String> record) {
        System.out.println("Producer收到消息："+record.value());
    }
}
