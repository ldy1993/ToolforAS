package com.app;

import android.app.Application;

import com.example.function.study.A_了解JAVA.H_java之注解.annotation.menu.MenuActivityCompiler;

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        MenuActivityCompiler.menuActivityCompiler(this,"com.example.function");

    }
}
