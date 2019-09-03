package com.example.function.study.day5;

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

import com.ldy.study.R;

public class Day5_Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().getDecorView();
//        ViewParent viewParent= (ViewParent) getWindow().getDecorView();
//        FrameLayout frameLayout= (FrameLayout) viewParent;
//        LinearLayout linearLayout=new LinearLayout(this);
//        Button bt=new Button(this);
//        bt.setText("这是一个按钮");
//        LinearLayout.LayoutParams layoutParams =   new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT，ViewGroup.LayoutParams.FILL_PARENT);
//        linearLayout.addView(bt,300,300);
//        frameLayout.addView(linearLayout,layoutParams);



    //第一种xml转换成view
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
        //第二种xml转换成view
        LayoutInflater layoutInflater = (LayoutInflater)
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        其实第一种就是第二种的简单写法，只是Android给我们做了一下封装而已。得到了LayoutInflater的实例之后就可以调用它的inflate()方法来加载布局了，如下所示：
        layoutInflater.inflate(R.layout.activity_day5, null);
    }
}
