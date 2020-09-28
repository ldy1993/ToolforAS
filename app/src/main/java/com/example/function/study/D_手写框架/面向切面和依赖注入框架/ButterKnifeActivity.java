package com.example.function.study.D_手写框架.面向切面和依赖注入框架;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.function.study.A_了解JAVA.F_java的高级特性.annotation.Compiler;
import com.example.function.study.A_了解JAVA.F_java的高级特性.annotation.FindViewById;
import com.example.function.study.A_了解JAVA.F_java的高级特性.annotation.menu.MenuActivity;
import com.example.function.study.A_了解JAVA.F_java的高级特性.annotation.menu.MenuEnum;
import com.example.function.study.A_了解JAVA.F_java的高级特性.annotation.menu.SonMenuEnum;
import com.ldy.study.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;


@MenuActivity(menu = MenuEnum.常用框架主菜单,sonMenu = SonMenuEnum.黄油刀)
public class ButterKnifeActivity extends Activity {
    @BindView(R.id.lv)
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_butter_knife);
        //绑定初始化ButterKnife
        ButterKnife.bind(this);
        listView.setAdapter(new ArrayAdapter<>(this,R.layout.view_rv_item,R.id.item_tv,getData()));
    }
    @OnItemClick(R.id.lv)
    public void onClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(ButterKnifeActivity.this, "onClick_position="+position, Toast.LENGTH_SHORT).show();
    }
    private ArrayList<String> getData() {
        ArrayList<String> data = new ArrayList<>();
        String temp = " item";
        for(int i = 0; i < 20; i++) {
            data.add(i + temp);
        }

        return data;
    }

}