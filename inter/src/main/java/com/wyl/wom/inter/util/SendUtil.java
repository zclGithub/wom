package com.wyl.wom.inter.util;

import com.wyl.wom.data.AbstractMessage;
import com.wyl.wom.kafka.enums.MessageType;
import com.wyl.wom.kafka.impl.KafkaMessage;
import com.wyl.wom.kafka.util.MessageUtil;

import java.util.UUID;

public class SendUtil {
    public AbstractMessage sendMsg(String topic,String uri,AbstractMessage msg){
        try {
            KafkaMessage kafkaMessage = new KafkaMessage();
            kafkaMessage.setUuid(UUID.randomUUID().toString());
            kafkaMessage.setType(MessageType.OBJECT);
            kafkaMessage.setUri(uri);
            kafkaMessage.setData(msg);
            MessageUtil util = new MessageUtil(topic,kafkaMessage);
            util.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
