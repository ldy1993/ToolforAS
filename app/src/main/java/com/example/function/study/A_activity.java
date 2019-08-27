package com.example.function.study;

import android.app.Activity;
import android.os.Bundle;
import android.util.ArrayMap;

import com.example.View.R;

import java.util.Map;

public class A_activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a_activity);
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

    }
}
