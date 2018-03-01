package com.wyl.wom.kafka;


import com.wyl.wom.kafka.enums.MessageType;

import java.io.Serializable;

public interface IMessage extends Serializable{

    public void setUuid(String uuid);

    public String getUuid();

    /**
     * 设置消息发送类型
     * @param type
     */
    public void setType(MessageType type);

    public MessageType getType();

    /**
     * 设置要请求方法的uri
     * @param uri
     */
    public void setUri(String uri);

    public String getUri();
    /**
     * 设置消息
     * @param msg
     */
    public void setMessage(String msg);

    public String getMessage();

    public Object getData();

    public void setData(Object data);

    public String getBackTopic();

    public void setBackTopic(String backTopic);
}
