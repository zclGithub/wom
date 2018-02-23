package com.wyl.wom.bss.kafka.listener;

import com.wyl.wom.bss.kafka.producer.ProducerService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.MessageListener;

public class ConsumerMessageListener implements MessageListener<String,String> {
    @Override
    public void onMessage(ConsumerRecord<String, String> record) {
        System.out.println("收到消息："+record.value());
        System.out.println("topic:"+record.topic());
        new ProducerService().sendMessage1("test3","bbbbbbbbbb");
    }
}
