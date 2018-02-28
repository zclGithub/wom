package com.wyl.wom.kafka.pool;

import com.wyl.wom.data.ErrorMessage;
import com.wyl.wom.kafka.AbstractMessageUtil;
import com.wyl.wom.kafka.util.ResultMap;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MyRespPool implements IPool{
    private static MyRespPool myRespPool=null;
    private static Map<String,AbstractMessageUtil> respMap = new HashMap<>();
    private static final long DEFAULT_TIMEOUT_TIME = 30000;//超时时间，30秒

    private MyRespPool(){
        //启动线程处理超时请求
        new Thread(new Runnable() {
            @Override
            public void run() {
                Collection<AbstractMessageUtil> utils = respMap.values();
                for(AbstractMessageUtil util : utils){
                    long now = System.currentTimeMillis();
                    if(now-util.sendTime>DEFAULT_TIMEOUT_TIME){//超时
                        //设置超时信息
                        ErrorMessage msg = new ErrorMessage();
                        msg.setSuc(false);
                        msg.setErrCode("timeout");
                        msg.setMsg("请求超时，未获取到返回信息");
                        util.setResponse(msg);
                        //从map中移除util
                        respMap.remove(util.id);
                    }

                    try {
                        Thread.sleep(DEFAULT_TIMEOUT_TIME);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
    public synchronized static MyRespPool getInstance(){
        return myRespPool==null?new MyRespPool():myRespPool;
    }

    @Override
    public void destroy() {

    }

    @Override
    public void add(AbstractMessageUtil util) {
        util.sendTime = System.currentTimeMillis();
        respMap.put(util.id,util);
    }
}
