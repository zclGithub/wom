package com.wyl.wom.data.req;

import com.wyl.wom.data.AbstractMessage;
import lombok.Data;

import java.io.Serializable;

@Data
public class GetUserInfoReq extends AbstractMessage implements Serializable {
    private String user;
}
