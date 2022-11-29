package com.orange.graduation.config.resolver;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.orange.graduation.annotation.BodyParams;
import com.orange.graduation.beans.response.RespStatus;
import com.orange.graduation.exception.RequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.util.Enumeration;

/**
 * @author orange.zhang
 * @date 2022/11/17 21:11
 */
@Slf4j
@Configuration
public class BodyParamsResolver  implements HandlerMethodArgumentResolver {



    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(BodyParams.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        Enumeration<String> headerNames = request.getHeaderNames();
        BufferedReader reader = request.getReader();
        String jsonbody = FileCopyUtils.copyToString(reader);
        String uri = request.getRequestURI();
        log.info("req method : {} , uri : {}" , request.getMethod() , uri);
        if (jsonbody == null || jsonbody.length() == 0) throw new RequestException(RespStatus.Fail.getCode(), "json body is not valid");
        if (!JSON.isValid(jsonbody)) throw new RequestException(RespStatus.Fail.getCode(), "req body isn't json");
        Class<?> parameterType = parameter.getParameterType();
        final Object o = JSONObject.parseObject(jsonbody, parameterType);
        log.info(" body : {}" , o.toString());
        return o;
    }

}
