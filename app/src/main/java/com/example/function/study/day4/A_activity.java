package com.example.function.study.day4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.ArrayMap;
import android.view.View;
import android.widget.Button;

import com.ldy.study.R;

import java.util.Map;

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
