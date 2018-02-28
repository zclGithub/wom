package com.wyl.wom.kafka.thread;

import com.wyl.wom.kafka.AbstractMessageUtil;
import com.wyl.wom.kafka.IMessage;
import com.wyl.wom.util.ContextUtil;
import org.springframework.kafka.core.KafkaTemplate;

/**
 * 发送消息线程
 */
public class MessageThread implements Runnable{
    private AbstractMessageUtil util;
    public MessageThread(AbstractMessageUtil util){
        this.util = util;
    }
    @Override
    public void run() {
        //发送消息
        KafkaTemplate<String,IMessage> template = ContextUtil.getBean(KafkaTemplate.class);
        template.send(util.getTopic(),util.getReqMsg());
    }
}
