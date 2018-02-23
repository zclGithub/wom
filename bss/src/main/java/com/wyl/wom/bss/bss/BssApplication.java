package com.wyl.wom.bss.bss;

import com.wyl.wom.data.MsgData;
import com.wyl.wom.bss.kafka.producer.ProducerService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.wyl.wom")
public class BssApplication  implements CommandLineRunner {
	@Autowired
	private ProducerService producerService;
	@Autowired
	private KafkaTemplate<String,MsgData> kafkaTemplate;
	public int i = 0;
	public static void main(String[] args) {
		SpringApplication.run(BssApplication.class, args);
	}
//	@KafkaListener(topicPattern = "inter([0-9]|[a-z]|[A-Z]|.){0,}")
//	public void listener(ConsumerRecord<String, MsgData> record){
//		MsgData data = record.value();
//		System.out.println("bss收到消息："+record.topic()+"-("+data.getId()+","+data.getName()+","+data.getTitle()+")");
//		data = new MsgData();
//		data.setId(i);
//		data.setName("姓名"+i);
//		data.setTitle("标题"+i);
//		i++;
//		ProducerRecord<String,MsgData> pr = new ProducerRecord<String,MsgData>("bss3",data);
//		kafkaTemplate.send(pr);
//
//	}

	@Override
	public void run(String... strings) throws Exception {

	}
}
