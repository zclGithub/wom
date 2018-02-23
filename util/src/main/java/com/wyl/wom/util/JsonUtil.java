package com.wyl.wom.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

    /**
     * 对象转换成json
     * @param obj
     * @return
     * @throws Exception
     */
    public static  String ObjectToJson(Object obj) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(obj);
        return !"".equals(json) ? json : "{}";
    }

    /**
     * json转换成对象
     * @param json
     * @param clazz
     * @return
     * @throws Exception
     */
    public  Object getObjectFromJson(String json, Class<?> clazz) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        Object obj = null;
        obj = mapper.readValue(json, clazz);
        return obj;
    }
}
