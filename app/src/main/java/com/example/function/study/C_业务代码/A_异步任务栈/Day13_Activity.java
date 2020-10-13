package com.example.function.study.C_业务代码.A_异步任务栈;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.function.study.A_了解JAVA.H_java之注解.annotation.menu.MenuActivity;
import com.example.function.study.A_了解JAVA.H_java之注解.annotation.menu.MenuEnum;
import com.example.function.study.A_了解JAVA.H_java之注解.annotation.menu.SonMenuEnum;
import com.ldy.study.R;
@MenuActivity(menu= MenuEnum.学习功能主菜单,sonMenu = SonMenuEnum.基于线程池的异步任务)
public class Day13_Activity extends Activity {
   private SingleAsyncTaskTestAction singleAsyncTaskTestAction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day13);
        singleAsyncTaskTestAction= new SingleAsyncTaskTestAction(Day13_Activity.this);
        Button bt=findViewById(R.id.bt);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              singleAsyncTaskTestAction.excute();
            }
        });

    }
}
