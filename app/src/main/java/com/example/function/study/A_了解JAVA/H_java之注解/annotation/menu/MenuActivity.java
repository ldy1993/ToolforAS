package com.example.function.study.A_了解JAVA.H_java之注解.annotation.menu;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface MenuActivity {
    MenuEnum menu();
    SonMenuEnum sonMenu();
}
