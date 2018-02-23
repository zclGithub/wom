package com.wyl.wom.kafka.serializer;

import com.wyl.wom.data.AbstractMessage;
import com.wyl.wom.kafka.IMessage;
import com.wyl.wom.util.BeanUtils;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class AbstractMsgSerializer implements Serializer<IMessage> {
    @Override
    public void configure(Map<String, ?> map, boolean b) {

    }

    @Override
    public byte[] serialize(String s, IMessage msgData) {
        return BeanUtils.ObjectToBytes(msgData);
    }

    @Override
    public void close() {

    }
}
