package com.example.function.study.B_界面视图.I_Fragment的加载与传值;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.example.function.study.A_了解JAVA.F_java的高级特性.annotation.menu.MenuActivity;
import com.example.function.study.A_了解JAVA.F_java的高级特性.annotation.menu.MenuEnum;
import com.example.function.study.A_了解JAVA.F_java的高级特性.annotation.menu.SonMenuEnum;
import com.ldy.study.R;

/**
 * @author 东阳
 */
@MenuActivity(menu= MenuEnum.学习功能主菜单 ,sonMenu = SonMenuEnum.静态fragment)
public class Day12_Activity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day12);
    }
}
