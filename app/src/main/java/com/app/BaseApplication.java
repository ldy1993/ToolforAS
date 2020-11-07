package com.app;

import android.app.Application;

import com.example.function.study.A_了解JAVA.H_java之注解.annotation.menu.MenuActivityCompiler;
import com.example.main.MainJava;

public class BaseApplication extends Application {
   public static BaseApplication mpp;
    @Override
    public void onCreate() {
        super.onCreate();
        MenuActivityCompiler.menuActivityCompiler(this,"com.example.function");
        mpp=this;

    }
}
