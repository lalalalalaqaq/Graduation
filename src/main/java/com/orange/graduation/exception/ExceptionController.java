package com.orange.graduation.exception;

import com.orange.graduation.beans.response.RespResult;
import com.orange.graduation.beans.response.RespStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.ServletException;

/**
 * @author orange.zhang
 * @date 2022/11/17 21:37
 */
@Slf4j
@RestControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {


    @ExceptionHandler(value = Exception.class)
    public RespResult GlobalExecption(Exception e) {
        log.error("INTERNAL_SERVER_ERROR : ", e);
        return RespResult.fail("error");
    }

    @ExceptionHandler(value = ServletException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RespResult ServletExecption(Exception e) {
        log.error("INTERNAL_SERVER_ERROR : {} ", RespStatus.HTTP_STATUS_500.getDescribed() , e);
        return RespResult.fail("error , try again");
    }


    @ExceptionHandler(value = RequestException.class)
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    public RespResult RequestExecption(RequestException e) {
        log.error("RequestExecption : {} ", e.getMsg(), e);
        return RespResult.fail(e.getMsg());
    }

}
