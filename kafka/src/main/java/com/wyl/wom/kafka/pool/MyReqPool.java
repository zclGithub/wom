package com.wyl.wom.kafka.pool;

import com.wyl.wom.kafka.AbstractMessageUtil;
import com.wyl.wom.kafka.thread.MessageThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyReqPool implements IPool {

    private static MyReqPool  myReqPool = null;
    private MyRespPool myRespPool=null;//消息返回线程池
    private ExecutorService threadPool = null;//线程池

    public synchronized static MyReqPool getInstance(){
        return myReqPool==null?new MyReqPool():myReqPool;
    }

    private MyReqPool(){
        threadPool = Executors.newCachedThreadPool();
        myRespPool = MyRespPool.getInstance();
    }

    @Override
    public void destroy() {

    }

    @Override
    public void add(AbstractMessageUtil util) {
        //执行消息发送
        threadPool.execute(new MessageThread(util));
        //添加util到消息返回线程池
        myRespPool.add(util);
    }
}
