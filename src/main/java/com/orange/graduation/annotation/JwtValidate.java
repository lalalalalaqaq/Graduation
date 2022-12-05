package com.orange.graduation.annotation;

import java.lang.annotation.*;

/**
 * @author orange.zhang
 * @date 2022/12/5 20:37
 */
@Target({ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface JwtValidate {
}
