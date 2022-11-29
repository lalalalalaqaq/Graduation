package com.orange.graduation.beans.response;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author orange.zhang
 * @date 2022/11/17 20:17
 */
@Data
@Builder
public class RespResult<T> {


    private String code;
    private T data;
    private String msg;
    private Long ts;

    public static <T> RespResult<T> success() {
        return success(null);
    }

    public static <T> RespResult<T> success(T data) {
        return RespResult.<T>builder()
                .msg(RespStatus.HTTP_STATUS_200.getDescribed())
                .code(RespStatus.HTTP_STATUS_200.getCode())
                .data(data)
                .ts(System.currentTimeMillis())
                .build();
    }

    public static <T extends Serializable> RespResult<T> fail(String message) {
        return fail(null, message);
    }


    public static <T> RespResult<T> fail(T data, String message) {
        return RespResult.<T>builder().data(data)
                .msg(message)
                .code(RespStatus.Fail.getCode())
                .data(data)
                .ts(System.currentTimeMillis())
                .build();
    }

}
