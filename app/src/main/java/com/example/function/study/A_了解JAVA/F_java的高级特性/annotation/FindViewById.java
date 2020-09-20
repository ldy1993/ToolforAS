package com.example.function.study.A_了解JAVA.F_java的高级特性.annotation;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface FindViewById {
    int value() ;
}
