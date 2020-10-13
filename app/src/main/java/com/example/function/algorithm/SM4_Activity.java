package com.example.function.algorithm;

import android.app.Activity;
import android.os.Bundle;

import com.example.function.study.A_了解JAVA.H_java之注解.annotation.menu.MenuActivity;
import com.example.function.study.A_了解JAVA.H_java之注解.annotation.menu.MenuEnum;
import com.example.function.study.A_了解JAVA.H_java之注解.annotation.menu.SonMenuEnum;
import com.ldy.sign.instantiation.SM4.SM4Utils;
import com.ldy.study.R;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
@MenuActivity(menu= MenuEnum.实例算法主菜单,sonMenu = SonMenuEnum.sm4对称加密)
public class SM4_Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sm4);
        try {
            SM4Utils.sm4Test();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
