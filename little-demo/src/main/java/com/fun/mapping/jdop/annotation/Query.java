package com.fun.mapping.jdop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author huanye
 * Date: 2017/9/22 下午5:51
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Query {

    String sql() default "";

    int lvl() default 1;

    String[] keys() default {};
}
