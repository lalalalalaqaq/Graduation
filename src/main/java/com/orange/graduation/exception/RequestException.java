package com.orange.graduation.exception;

import com.orange.graduation.beans.response.RespStatus;
import lombok.Data;

/**
 * @author orange.zhang
 * @date 2022/11/17 21:51
 */
@Data
public class RequestException extends RuntimeException{
    private String errcode ;
    private String msg;

    public RequestException(String errcode, String msg) {
        super();
        this.errcode = errcode;
        this.msg = msg;
    }


    public RequestException() {
        super();
        this.errcode = RespStatus.Fail.getCode();
        this.msg = RespStatus.Fail.getDescribed();
    }
}
