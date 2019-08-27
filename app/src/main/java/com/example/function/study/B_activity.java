package com.example.function.study;

import android.app.Activity;
import android.os.Bundle;

import com.example.View.R;

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
    }
}
