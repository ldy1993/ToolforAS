package com.example.function.study.A_了解JAVA.H_java之注解.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface FindViewById {
    int value() ;
}
