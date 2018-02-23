package com.wyl.wom.kafka.serializer;

import com.wyl.wom.data.AbstractMessage;
import com.wyl.wom.kafka.IMessage;
import com.wyl.wom.util.BeanUtils;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class AbstractMsgDeserializer implements Deserializer<IMessage> {
    @Override
    public void configure(Map<String, ?> map, boolean b) {

    }

    @Override
    public IMessage deserialize(String s, byte[] bytes) {
        return (IMessage) BeanUtils.BytesToObject(bytes);
    }

    @Override
    public void close() {

    }
}
