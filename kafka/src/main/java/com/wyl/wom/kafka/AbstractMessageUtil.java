package com.wyl.wom.kafka;

import com.wyl.wom.data.AbstractMessage;

public abstract class AbstractMessageUtil {
    public String id;
    public Long sendTime;
    protected AbstractMessage respMsg;
    protected IMessage reqMsg;
    public String topic;//发送消息的topic
    public boolean run = true;

    public AbstractMessageUtil(String topic,IMessage reqMsg){
        this.reqMsg = reqMsg;
        this.topic = topic;
    }


    public void setResponse(AbstractMessage respMsg) {
        this.respMsg = respMsg;
        this.run = false;
    }


    public void setReqMsg(IMessage reqMsg) {
        this.reqMsg = reqMsg;
    }

    public IMessage getReqMsg() {
        return reqMsg;
    }

    public abstract AbstractMessage execute() throws Exception;

    public abstract String getTopic();
}
