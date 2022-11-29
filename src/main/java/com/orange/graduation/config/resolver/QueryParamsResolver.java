package com.orange.graduation.config.resolver;

import com.alibaba.fastjson2.JSONObject;
import com.orange.graduation.annotation.QueryParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author orange.zhang
 * @date 2022/11/17 20:38
 * resolver query params
 *
 */

@Slf4j
@Configuration
public class QueryParamsResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(QueryParams.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        Map<String, String[]> parameterMap = request.getParameterMap();
        Enumeration<String> headerNames = request.getHeaderNames();

        String uri = request.getRequestURI();
        log.info("req method : {} , uri : {}" , request.getMethod() , uri);

        if (headerNames.hasMoreElements()) {
            log.info("header : {}", headerNames.nextElement());
        }

        if (parameterMap == null) {
            return null;
        }
        Iterator<String> keyIterator = parameterMap.keySet().iterator();
        JSONObject jsonObject = new JSONObject();
        while (keyIterator.hasNext()) {
            String key = keyIterator.next();
            String value[] = parameterMap.get(key);
            jsonObject.put(key, value[0]);
            log.info("key:{},value:{}",key,Arrays.toString(value));
        }
        Class<?> parameterType = parameter.getParameterType();
        final Object o = JSONObject.parseObject(jsonObject.toJSONString(), parameterType);
        return o;
    }


}

