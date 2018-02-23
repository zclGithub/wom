package com.wyl.wom.data;


import lombok.Data;

import java.io.Serializable;

@Data
public class MsgData extends AbstractMessage implements Serializable{
    private Integer id;
    private String name;
    private String title;

}
