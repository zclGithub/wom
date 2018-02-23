package com.wyl.wom.data.resp;

import com.wyl.wom.data.AbstractMessage;
import lombok.Data;

import java.io.Serializable;

@Data
public class GetUserInfoResp extends AbstractMessage implements Serializable {
    private String name;
    private String age;
    private String addr;
    private String phone;
}
