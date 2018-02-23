package com.wyl.wom.kafka.util;

import com.wyl.wom.data.AbstractMessage;
import com.wyl.wom.kafka.AbstractMessageUtil;
import com.wyl.wom.kafka.IMessage;
import com.wyl.wom.util.ContextUtil;
import org.springframework.kafka.core.KafkaTemplate;

public class MessageUtil extends AbstractMessageUtil {

    private static ResultMap resultMap = ResultMap.getInstance();

    public MessageUtil(String topic, IMessage reqMsg) {
        super(topic, reqMsg);
    }

    @Override
    public AbstractMessage execute() throws Exception {
        this.id = reqMsg.getUuid();
        //发送消息到kafka
        KafkaTemplate<String,IMessage> template = ContextUtil.getBean(KafkaTemplate.class);
        template.send(topic,reqMsg);
        resultMap.add(this);
        //等待消息返回
        while(run){
            try {
                Thread.sleep(10);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return respMsg;
    }
}
