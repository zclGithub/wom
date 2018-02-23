package com.wyl.wom.inter.kafka.producer;

import com.wyl.wom.inter.kafka.listener.ProducerMessageListener;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.listener.config.ContainerProperties;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Service
public class ProducerService implements Serializable{
    KafkaTemplate<String,String> kt;
    Producer<String, String> producer;
    public void sendMessage(String key,String content){

        getProducer();
        ProducerRecord<String, String> message =
                new ProducerRecord<String, String>(key, content);

        producer.send(message);
    }
    public void getProducer() {
        if(producer==null){
            Properties prop = new Properties();
            prop.setProperty("bootstrap.servers","localhost:9092");
            prop.setProperty("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
            prop.setProperty("value.serializer","org.apache.kafka.common.serialization.StringSerializer");
            producer = new KafkaProducer<String, String>(prop);
        }
    }
    public void sendMessage1(String key,String content){
        getKafkaTemplate();
        ProducerRecord<String, String> message =
                new ProducerRecord<String, String>(key, content);
        kt.send(message);
    }
    public void getKafkaTemplate(){
        if(kt==null){
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("bootstrap.servers","localhost:9092");
            map.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
            map.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");
            DefaultKafkaProducerFactory<String,String> factory = new DefaultKafkaProducerFactory<String,String>(map);
            kt = new KafkaTemplate<String,String>(factory,true);
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
        prop.setMessageListener(new ProducerMessageListener());
        KafkaMessageListenerContainer<String,String> container = new KafkaMessageListenerContainer<String,String>(factory,prop);
        container.start();
    }

    public static void main(String[] args){
        new ProducerService().sendMessage1("test","aaaaaaaaaa");
        new ProducerService().recieveMessage1("test3");
    }
}
