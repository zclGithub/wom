package com.wyl.wom.bss.action;

import com.wyl.wom.bss.bss.SupperAction;
import com.wyl.wom.data.AbstractMessage;
import com.wyl.wom.data.resp.GetUserInfoResp;

public class UserInfoAction extends SupperAction{

    @Override
    public AbstractMessage executeObject(AbstractMessage msg) {
        GetUserInfoResp resp = new GetUserInfoResp();
        resp.setAddr("地址地址地址地址地址地址地址地址");
        resp.setAge("24");
        resp.setName("高吸");
        resp.setPhone("18611111111");
        resp.setSuc(true);
        resp.setMsg("成功");
        return resp;
    }

    @Override
    public AbstractMessage executeJson(String json) {
        return null;
    }
}
