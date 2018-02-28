package com.wyl.wom.kafka.pool;

import com.wyl.wom.kafka.AbstractMessageUtil;

public interface IPool {
    public void destroy();

    public void add(AbstractMessageUtil util);
}
