package com.example.function.algorithm;

import android.app.Activity;
import android.os.Bundle;

import com.example.function.study.A_了解JAVA.H_java之注解.annotation.menu.MenuActivity;
import com.ldy.sign.instantiation.SM.SM2Utils;
import com.ldy.study.R;

import static com.example.function.study.A_了解JAVA.H_java之注解.annotation.menu.MenuEnum.实例算法主菜单;
import static com.example.function.study.A_了解JAVA.H_java之注解.annotation.menu.SonMenuEnum.sm2对称加密;

@MenuActivity(menu=实例算法主菜单,sonMenu =sm2对称加密)
public class SM2_Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sm2);
        try {
            SM2Utils.Sm2Test();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
