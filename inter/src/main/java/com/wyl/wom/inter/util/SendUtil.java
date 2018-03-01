package com.wyl.wom.inter.util;

import com.wyl.wom.data.AbstractMessage;
import com.wyl.wom.data.ErrorMessage;
import com.wyl.wom.kafka.enums.MessageType;
import com.wyl.wom.kafka.impl.KafkaMessage;
import com.wyl.wom.kafka.util.MessageUtil;
import com.wyl.wom.util.ErrorUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

public class SendUtil {
    public static AbstractMessage sendMsg(String topic,String backTopic,String uri,AbstractMessage msg){
        AbstractMessage result = null;
        try {
            KafkaMessage kafkaMessage = new KafkaMessage();
            kafkaMessage.setUuid(UUID.randomUUID().toString());
            kafkaMessage.setType(MessageType.OBJECT);
            kafkaMessage.setUri(uri);
            kafkaMessage.setData(msg);
            if(StringUtils.isBlank(backTopic)){
                result = ErrorUtil.getErrorMsg("backTopic为空","10002");
            }else{
                kafkaMessage.setBackTopic(backTopic);
                MessageUtil util = new MessageUtil(topic,kafkaMessage);
                result = util.execute();
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = ErrorUtil.getErrorMsg("请求发送失败","10002");
        }
        return result;
    }
}
