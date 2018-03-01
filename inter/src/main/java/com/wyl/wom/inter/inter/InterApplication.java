package com.wyl.wom.inter.inter;

import com.wyl.wom.kafka.IMessage;
import com.wyl.wom.kafka.impl.KafkaMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
@EnableAutoConfiguration

@ComponentScan(basePackages = "com.wyl.wom")
public class InterApplication implements CommandLineRunner {
	@Autowired
	private KafkaTemplate<String, IMessage> kafkaTemplate;
	public static void main(String[] args) {
		SpringApplication.run(InterApplication.class, args);
	}

//	@KafkaListener(topicPattern = "bss([0-9]|[a-z]|[A-Z]|.){0,}")
//	public void listener(ConsumerRecord<String, MsgData> record){
//		MsgData data = record.value();
//		System.out.println("inter收到消息："+record.topic()+"-("+data.getId()+","+data.getName()+","+data.getTitle()+")");
//	}

	@Override
	public void run(String... strings) throws Exception {
		initTopic();
//		int i=0;
//		while(true){
//			try {
//				System.out.println("发送消息"+i);
//				MsgData data = new KafkaMessage();
//				data.setId(i);
//				data.setName("name"+i);
//				data.setTitle("title"+i);
//				i++;
//				kafkaTemplate.send("inter4",data);
////				ListenableFuture<SendResult<String, String>> future =  kafkaTemplate.send("inter4","kafkaTemplate"+i++);
////				SendResult<String, String> result =  future.get();
////				System.out.println("返回结果："+result.getProducerRecord().value());
////				producerService.sendMessage1("inter1","message"+i++);
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
	}

	public void initTopic(){
		KafkaMessage msg = new KafkaMessage();
		msg.setBackTopic("bss3");
		kafkaTemplate.send("inter3",msg);
		kafkaTemplate.send("inter4",msg);
		kafkaTemplate.send("inter5",msg);
		kafkaTemplate.send("inter6",msg);
		kafkaTemplate.send("inter.userInfo",msg);
		kafkaTemplate.send("bss3",msg);
		kafkaTemplate.send("bss4",msg);
		kafkaTemplate.send("bss5",msg);
		kafkaTemplate.send("bss6",msg);
		kafkaTemplate.send("bss.userInfo",msg);
	}
}
