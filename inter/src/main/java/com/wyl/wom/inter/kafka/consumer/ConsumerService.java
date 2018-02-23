package com.wyl.wom.inter.kafka.consumer;

import com.wyl.wom.inter.kafka.listener.ConsumerMessageListener;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.listener.config.ContainerProperties;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ConsumerService {

    public void recieveMessage(String key){
        Properties props = new Properties();
        props.setProperty("bootstrap.servers","localhost:9092");
        props.setProperty("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        props.put("group.id", "group-1");

        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(props);
        kafkaConsumer.subscribe(Arrays.asList(key));
        while (true){
            ConsumerRecords<String, String> records = kafkaConsumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                System.out.println("收到消息："+record.value());
            }
        }

    }

    public void recieveMessage1(String key){
        Map<String,Object> map = new HashMap<>();
        map.put("bootstrap.servers","localhost:9092");
        map.put("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        map.put("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        map.put("group.id", "group-1");

        DefaultKafkaConsumerFactory<String,String> factory = new DefaultKafkaConsumerFactory<String,String>(map);
        ContainerProperties prop = new ContainerProperties(key);
        prop.setMessageListener(new ConsumerMessageListener());
        KafkaMessageListenerContainer<String,String> container = new KafkaMessageListenerContainer<String,String>(factory,prop);
        container.start();
    }

    public static void main(String[] args){
        new ConsumerService().recieveMessage1("test");
    }
}
