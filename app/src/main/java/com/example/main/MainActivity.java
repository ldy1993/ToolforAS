package com.example.main;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.example.function.study.A_了解JAVA.F_java之泛型.Util;
import com.ldy.study.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




        setContentView(R.layout.activity_main);
        Intent intemt=new Intent();
        //每一个装载过后的Class都是唯一的。
        Class clazz=  MainActivity.this.getClass();
        clazz.getName();
        clazz.getSimpleName();
        //显式意图精确查找
        intemt.setClassName("com.example.main","MainActivity");
        startActivity(intemt);

        //隐式意图的查找
        //当点击一个桌面app的按钮，也就是桌面app上面的某个app，触发了点击事件。
        //startActivity一个action是android.intent.action.MAIN
        intemt.setAction("android.intent.action.MAIN");
        intemt.setData(Uri.parse("10个人"));
        this.startActivity(intemt);
        MainJava.test((Context)this);
        MainJava.test(this);
    }
}