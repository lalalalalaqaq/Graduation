package com.orange.graduation.beans.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author orange.zhang
 * @date 2022/11/17 20:10
 */
@Getter
@AllArgsConstructor
public enum RespStatus {
    Success("20000","success"),
    Fail("50000","failed"),
    AuthenticationError("40001","no authentication"),
    AuthoritiesError("40003","no authorities"),

    HTTP_STATUS_200("200", "ok"),
    HTTP_STATUS_400("400", "request error"),
    HTTP_STATUS_401("401", "no authentication"),
    HTTP_STATUS_403("403", "no authorities"),
    HTTP_STATUS_500("500", "server error")



    ;

    private final String code;
    private final String described;


}
