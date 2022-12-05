package com.orange.graduation.exception;

import com.orange.graduation.beans.response.RespStatus;

/**
 * @author orange.zhang
 * @date 2022/12/5 21:46
 */
public class AuthException extends RuntimeException{
    private String errcode ;
    private String msg;

    public AuthException(String errcode, String msg) {
        super();
        this.errcode = errcode;
        this.msg = msg;
    }


    public AuthException() {
        super();
        this.errcode = RespStatus.AuthenticationError.getCode();
        this.msg = RespStatus.AuthenticationError.getDescribed();
    }
}

