package com.wyl.wom.kafka.util;


import com.wyl.wom.kafka.AbstractMessageUtil;

import java.util.HashMap;
import java.util.Map;

public class ResultMap {
    private static ResultMap resultMap ;
    private static Map<String,AbstractMessageUtil> respMap = new HashMap<>();
    private ResultMap(){

    }

    public synchronized static ResultMap getInstance(){
        if(resultMap==null){
            resultMap = new ResultMap();
        }
        return resultMap;
    }

    public void add(AbstractMessageUtil util){
        respMap.put(util.id,util);
    }

    public void remove(AbstractMessageUtil util){
        respMap.remove(util.id);
    }

    public AbstractMessageUtil get(String id){
        return respMap.get(id);
    }
}
