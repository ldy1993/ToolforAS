package com.example.function.study.B_界面视图.A_组件activity_调用传值和回传;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.ArrayMap;
import android.view.View;
import android.widget.Button;

import com.example.function.study.A_了解JAVA.H_java之注解.annotation.menu.MenuActivity;
import com.example.function.study.A_了解JAVA.H_java之注解.annotation.menu.MenuEnum;
import com.example.function.study.A_了解JAVA.H_java之注解.annotation.menu.SonMenuEnum;
import com.ldy.study.R;

import java.util.Map;
@MenuActivity(menu= MenuEnum.学习功能主菜单,sonMenu = SonMenuEnum.界面跳转)
public class A_activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a_activity);
        Button bt=findViewById(R.id.bt);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*************************数据块***********************************/
                int a=654;
                String b="b你好呀";
                Transdata transdata=new Transdata();
                transdata.setName("消费");
                transdata.setValue("1元");
                Map<String,String> map=new ArrayMap<String, String>();
                map.put("key1","1");
                map.put("key2","2");
                transdata.setStringMap(map);
                /************************************************************/
                //请传递a，b,transdata给B_activity
                try {
                    Intent intent = new Intent(A_activity.this, B_activity.class);
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("trans", transdata);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });

    }
//onActivityResult
}
