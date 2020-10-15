package com.example.function.study.B_界面视图.B_视图view_创建控件view和布局ViewGroup;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.function.study.A_了解JAVA.H_java之注解.annotation.menu.MenuActivity;
import com.example.function.study.A_了解JAVA.H_java之注解.annotation.menu.MenuEnum;
import com.example.function.study.A_了解JAVA.H_java之注解.annotation.menu.SonMenuEnum;
import com.ldy.study.R;
@MenuActivity(menu= MenuEnum.学习功能主菜单,sonMenu = SonMenuEnum.创建控件)
public class Day5_Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // 1、通过代码方式设置界面视图
//        ViewGroup viewGroup= (ViewGroup) getWindow().getDecorView();
//        TextView tv=new TextView(this);
//        tv.setText("这是一个文本");
//        tv.setTextSize(100);
//        viewGroup.addView(tv,300,300);


        //2、通过打气的方式获取到xml配置，然后通过1、中的类似方法去设置界面视图
//        LayoutInflater inflater=getLayoutInflater();
//        //第一种方法
//        inflater.inflate(R.layout.activity_day5,(ViewGroup) getWindow().getDecorView());
//        //第二种方法
//        View view=  inflater.inflate(R.layout.activity_day5,null);
//        View view1=findViewById(R.id.ll);
//        ViewGroup viewGroup= (ViewGroup) getWindow().getDecorView();
//        viewGroup.addView(view,300,300);
        //        getWindow().getDecorView();
//        FrameLayout frameLayout= (FrameLayout) getWindow().getDecorView();
//        LinearLayout linearLayout=new LinearLayout(this);
//        Button bt=new Button(this);
//        bt.setText("这是一个按钮");
//        LinearLayout.LayoutParams layoutParams =   new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.FILL_PARENT);
//        linearLayout.addView(bt,300,300);
//        frameLayout.addView(linearLayout,layoutParams);


//3、安卓最常用的设置view的方法，通过2中打气的方法设置
//    //第一种xml转换成view
        setContentView(R.layout.activity_day5);
        TextView tv=findViewById(R.id.tv);
        Button bt=(Button) findViewById(R.id.bt);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Day5_Activity.this, "按钮被点击了", Toast.LENGTH_LONG).show();
            }
        });
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Day5_Activity.this, "tv被点击了", Toast.LENGTH_LONG).show();

            }
        });

    }
}
