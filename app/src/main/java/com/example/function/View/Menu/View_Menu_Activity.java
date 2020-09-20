package com.example.function.View.Menu;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;

import com.example.function.study.A_了解JAVA.F_java的高级特性.annotation.menu.MenuActivity;
import com.ldy.study.R;

import static com.example.function.study.A_了解JAVA.F_java的高级特性.annotation.menu.MenuEnum.自定义界面主菜单;
import static com.example.function.study.A_了解JAVA.F_java的高级特性.annotation.menu.SonMenuEnum.菜单界面;

@MenuActivity(menu=自定义界面主菜单,sonMenu = 菜单界面)
public class View_Menu_Activity extends Activity {
    private static final String TAG = "View_Menu_Activity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__menu);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN) ;//隐藏状态栏

    }
}
