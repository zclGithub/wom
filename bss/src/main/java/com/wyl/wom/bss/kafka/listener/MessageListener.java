package com.wyl.wom.bss.kafka.listener;

import com.wyl.wom.bss.bss.BssConfig;
import com.wyl.wom.bss.bss.SupperAction;
import com.wyl.wom.data.AbstractMessage;
import com.wyl.wom.data.ErrorMessage;
import com.wyl.wom.kafka.IMessage;
import com.wyl.wom.kafka.enums.MessageType;
import com.wyl.wom.util.ContextUtil;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
@Component
public class MessageListener {
    static Map<String,BssConfig> bssConfigMap = new HashMap<>();

    static {
        BssConfig config = new BssConfig();
        config.setTopic("bss3");
        config.setUri("getUserInfo");
        config.setClazz("com.wyl.wom.bss.action.UserInfoAction");
        bssConfigMap.put(config.getUri(),config);
    }

    /**
     * 监听inter发过来的消息
     * @param record
     */
    @KafkaListener(topicPattern = "inter([0-9]|[a-z]|[A-Z]|.){0,}")
    public void listener(ConsumerRecord<String, IMessage> record){
        IMessage msg = record.value();
        //判断消息访问接口，
        BssConfig config = bssConfigMap.get(msg.getUri());
        AbstractMessage result = null;
        if(config==null){
            result = getErrorMsg("未找到对应的请求","10001");
        }else{
            try {
                Class clazz = Class.forName(config.getClazz());
                SupperAction action = (SupperAction) clazz.newInstance();
                //执行方法
                switch (msg.getType()){
                    case XML:break;
                    case JSON:
                        result = action.executeJson(msg.getMessage());
                        break;
                    case OBJECT:
                        result = action.executeObject((AbstractMessage) msg.getData());
                        break;
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                result = getErrorMsg("未找到对应的类","10002");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                result = getErrorMsg(e.getMessage(),"10003");
            } catch (InstantiationException e) {
                e.printStackTrace();
                result = getErrorMsg(e.getMessage(),"10003");
            }
        }
        msg.setType(MessageType.OBJECT);
        msg.setData(result);
        //返回消息
        sendMsg(config.getTopic(),msg);
    }

    /**
     * 封装错误消息对象
     * @param msg
     * @param errCode
     * @return
     */
    private ErrorMessage getErrorMsg(String msg,String errCode){
        ErrorMessage result = new ErrorMessage();
        result.setSuc(false);
        result.setMsg(msg);
        result.setErrCode(errCode);
        return result;
    }

    private void sendMsg(String topic, IMessage msg){
        //发送消息到kafka
        KafkaTemplate<String,IMessage> template = ContextUtil.getBean(KafkaTemplate.class);
        template.send(topic,msg);
    }
}
