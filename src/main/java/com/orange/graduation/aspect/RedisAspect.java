package com.orange.graduation.aspect;

import com.alibaba.fastjson2.JSON;
import com.orange.graduation.annotation.RedisCache;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.StringJoiner;

/**
 * @author orange.zhang
 * @date 2022/11/20 13:18
 */
@Component
@Aspect
@Slf4j
public class RedisAspect {

    private final JedisPool jedisPool;

    public RedisAspect(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }


    @Around("@annotation(com.orange.graduation.annotation.RedisCache)")
    public Object doAround(ProceedingJoinPoint point) {
        try {
            Jedis jedis = jedisPool.getResource();
            Object result = null;
            MethodSignature signature = (MethodSignature)point.getSignature();
            Method method = signature.getMethod();
            RedisCache annotation = method.getAnnotation(RedisCache.class); // get anno params
            Object[] args = point.getArgs();
            String key = annotation.key() + ":" + StringUtils.arrayToDelimitedString(args, ":");;
            //3.检验redis中是否有数据
            if(jedis.exists(key)){
                String json = jedis.get(key);
                MethodSignature methodSignature = (MethodSignature) point.getSignature();
                Class returnClass = methodSignature.getReturnType();
                result = JSON.parseObject(json, returnClass);
            }else{
                result = point.proceed();
                String json = JSON.toJSONString(result);
                jedis.set(key, json);
            }
            return result;
        } catch (Throwable throwable) {
            throw new RuntimeException(throwable);
        }
    }



}
