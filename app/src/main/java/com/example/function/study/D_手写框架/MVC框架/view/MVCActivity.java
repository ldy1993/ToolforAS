package com.example.function.study.D_手写框架.MVC框架.view;


import android.app.Activity;
import android.os.Bundle;

import com.example.function.study.A_了解JAVA.F_java的高级特性.annotation.menu.MenuActivity;
import com.example.function.study.A_了解JAVA.F_java的高级特性.annotation.menu.MenuEnum;
import com.example.function.study.A_了解JAVA.F_java的高级特性.annotation.menu.SonMenuEnum;
import com.example.function.study.D_手写框架.MVC框架.controller.UpFileController;
import com.ldy.study.R;
@MenuActivity(menu = MenuEnum.常用框架主菜单,sonMenu = SonMenuEnum.MVC框架)
public class MVCActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvc);
        UpFileController upFileController = new UpFileController();
        upFileController.execute();
    }
}