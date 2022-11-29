package com.orange.graduation.annotation;

import java.lang.annotation.*;

/**
 * @author orange.zhang
 * @date 2022/11/17 20:35
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface QueryParams {
}
