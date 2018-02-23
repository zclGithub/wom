package com.wyl.wom.inter.action;

import com.wyl.wom.data.req.GetUserInfoReq;
import com.wyl.wom.data.resp.GetUserInfoResp;
import com.wyl.wom.kafka.enums.MessageType;
import com.wyl.wom.kafka.impl.KafkaMessage;
import com.wyl.wom.kafka.util.MessageUtil;
import com.wyl.wom.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Controller;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.RequestWrapper;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/userInfo")
public class UserInfoAction {
    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;
    @RequestMapping("/getUserInfo")
    @ResponseBody
    public String getUserInfo(){
        GetUserInfoReq req = new GetUserInfoReq();
        KafkaMessage msg = new KafkaMessage();
        msg.setUuid(UUID.randomUUID().toString());
        msg.setUri("getUserInfo");
        msg.setType(MessageType.OBJECT);
        msg.setData(req);
        MessageUtil util = new MessageUtil("inter3",msg);
        String json = "";
        try {
            GetUserInfoResp resp = (GetUserInfoResp) util.execute();
            json = JsonUtil.ObjectToJson(resp);
            System.out.println(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

}
