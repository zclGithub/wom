package com.wyl.wom.data;

import lombok.Data;

import java.io.Serializable;

@Data
public abstract class AbstractMessage implements Serializable {
    public boolean suc;
    public String msg;
    public String errCode;
}
