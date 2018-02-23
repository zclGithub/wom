package com.wyl.wom.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ContextUtil implements ApplicationContextAware {
    private static ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if(ContextUtil.applicationContext==null){
            ContextUtil.applicationContext = applicationContext;
        }
    }

    public static Object getBean(String name){
        return applicationContext.getBean(name);
    }

    //通过class获取Bean.
    public static <T> T getBean(Class<T> clazz){
        return applicationContext.getBean(clazz);
    }
}
