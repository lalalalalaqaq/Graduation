package com.orange.graduation.exception;

import com.orange.graduation.beans.response.RespResult;
import com.orange.graduation.beans.response.RespStatus;
import io.jsonwebtoken.JwtException;
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
    public RespResult GlobalException(Exception e) {
        log.error("INTERNAL_SERVER_ERROR : ", e);
        return RespResult.fail("error");
    }

    @ExceptionHandler(value = ServletException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RespResult ServletException(Exception e) {
        log.error("INTERNAL_SERVER_ERROR : {} ", RespStatus.HTTP_STATUS_500.getDescribed() , e);
        return RespResult.fail("error , try again");
    }


    @ExceptionHandler(value = RequestException.class)
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    public RespResult RequestExecption(RequestException e) {
        log.error("RequestException : {} ", e.getMsg(), e);
        return RespResult.fail(e.getMsg());
    }


    @ExceptionHandler(value = AuthException.class)
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    public RespResult AuthException(AuthException e) {
        log.error("AuthException : {} ", e.getMessage() );
        return RespResult.fail(e.getMessage());
    }

    @ExceptionHandler(value = JwtException.class)
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    public RespResult JwtException(JwtException e) {
        log.error("JwtException : {} ", "jwt execption", e);
        return RespResult.fail("jwt auth fail");
    }

}
