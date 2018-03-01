package com.wyl.wom.inter.kafka.listener;

import com.wyl.wom.data.AbstractMessage;
import com.wyl.wom.kafka.AbstractMessageUtil;
import com.wyl.wom.kafka.IMessage;
import com.wyl.wom.kafka.pool.MyRespPool;
import com.wyl.wom.kafka.util.ResultMap;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {
//    private static ResultMap resultMap = ResultMap.getInstance();
    private static MyRespPool myRespPool = MyRespPool.getInstance();
    /**
     * 监听bss返回的消息
     * @param record
     */
    @KafkaListener(topicPattern = "bss([0-9]|[a-z]|[A-Z]|.){0,}")
    public void listener(ConsumerRecord<String, IMessage> record){
        IMessage msg = record.value();
        AbstractMessageUtil util = myRespPool.get(msg.getUuid());
        if(util!=null){
            util.setResponse((AbstractMessage) msg.getData());
            //接收到消息后从列表中删除
            myRespPool.remove(util);
        }


    }

}
