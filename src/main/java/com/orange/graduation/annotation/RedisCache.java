package com.orange.graduation.annotation;

import java.lang.annotation.*;

/**
 * @author orange.zhang
 * @date 2022/11/20 13:17
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RedisCache {

    String key();
    int seconds() default -1; // -1 : no expired time
}
