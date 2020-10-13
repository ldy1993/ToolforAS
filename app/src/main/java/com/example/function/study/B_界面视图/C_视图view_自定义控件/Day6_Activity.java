package com.example.function.study.B_界面视图.C_视图view_自定义控件;

import android.app.Activity;
import android.os.Bundle;

import com.example.function.study.A_了解JAVA.H_java之注解.annotation.menu.MenuActivity;
import com.example.function.study.A_了解JAVA.H_java之注解.annotation.menu.MenuEnum;
import com.example.function.study.A_了解JAVA.H_java之注解.annotation.menu.SonMenuEnum;
import com.ldy.study.R;

@MenuActivity(menu= MenuEnum.学习功能主菜单,sonMenu = SonMenuEnum.自定义view)
public class Day6_Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day6);
    }
}
