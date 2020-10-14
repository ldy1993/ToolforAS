package com.example.function.study.A_了解JAVA.H_java之注解.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author 东阳
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface OnBind {
    String value() default "默认值";
}
