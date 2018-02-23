package com.wyl.wom.kafka.impl;


import com.wyl.wom.kafka.IMessage;
import com.wyl.wom.kafka.enums.MessageType;

import java.io.Serializable;

public class KafkaMessage implements IMessage , Serializable {

    private String uuid;
    private MessageType type = MessageType.JSON;
    private String uri;
    private String msg;
    private Object data;

    @Override
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String getUuid() {
        return uuid;
    }

    @Override
    public void setType(MessageType type) {
        this.type = type;
    }

    @Override
    public MessageType getType() {
        return type;
    }

    @Override
    public void setUri(String uri) {
        this.uri = uri;
    }

    @Override
    public String getUri() {
        return uri;
    }

    @Override
    public void setMessage(String msg) {
        this.msg = msg;
    }

    @Override
    public String getMessage() {
        return msg;
    }

    @Override
    public Object getData() {
        return data;
    }

    @Override
    public void setData(Object data) {
        this.data = data;
    }
}
