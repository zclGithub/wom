package com.wyl.wom.util;

import com.wyl.wom.data.ErrorMessage;

public class ErrorUtil {
    /**
     * 封装错误消息对象
     * @param msg
     * @param errCode
     * @return
     */
    public static ErrorMessage getErrorMsg(String msg,String errCode){
        ErrorMessage result = new ErrorMessage();
        result.setSuc(false);
        result.setMsg(msg);
        result.setErrCode(errCode);
        return result;
    }
}
