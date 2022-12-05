package com.orange.graduation.aspect;

import com.orange.graduation.beans.response.RespStatus;
import com.orange.graduation.exception.AuthException;
import com.orange.graduation.utils.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author orange.zhang
 * @date 2022/12/5 20:36
 */

@Component
@Aspect
@Slf4j
public class JwtValidateAspect {

    private final HttpServletRequest request;

    public JwtValidateAspect(HttpServletRequest request) {
        this.request = request;
    }

    @Pointcut("@annotation(com.orange.graduation.annotation.JwtValidate)")
    public void pointMethod(){
    }

    @Around("pointMethod()")
    public Object aroundTokenValidate(ProceedingJoinPoint joinPoint){
        String name = joinPoint.getSignature().getName();
        log.info("target methon : {} " , name);
        String header = request.getHeader(JwtTokenUtil.JWT_HEAD_NAME);
        if (!StringUtils.isEmpty(header)){
            log.info("token ：{} ",header);
            header = header.replace(JwtTokenUtil.JWT_TOKEN_PREFIX,"");
            JwtTokenUtil.parseToken(header);
            try {
                Object proceed = joinPoint.proceed();
                return proceed;
            } catch (Throwable throwable) {
                throw new AuthException();
            }
        }else {
            throw new AuthException();
        }
    }

}
