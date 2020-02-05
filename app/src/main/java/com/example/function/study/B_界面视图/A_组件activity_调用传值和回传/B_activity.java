package com.example.function.study.B_界面视图.A_组件activity_调用传值和回传;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.ldy.study.R;

public class B_activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b_activity);

        //这里获取到a_activity的数据

        //这里找到textview对象，并且赋值。

        /*************************数据块***********************************/
        int c=654;
        String d="a你好呀";
        /************************************************************/
        //请传递a，b返回给A_activity

        try {
            Transdata td=getIntent().getParcelableExtra("trans");
            Toast.makeText(this, ""+td.getStringMap().get("key1"), Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
