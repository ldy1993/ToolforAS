package com.example.function.View.Amount;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.function.study.A_了解JAVA.H_java之注解.annotation.menu.MenuActivity;
import com.example.function.study.A_了解JAVA.H_java之注解.annotation.menu.MenuEnum;
import com.example.function.study.A_了解JAVA.H_java之注解.annotation.menu.SonMenuEnum;
import com.ldy.view.CustomWidget.FrameLayout.InputAmt;
import com.ldy.study.R;
@MenuActivity(menu= MenuEnum.自定义界面主菜单,sonMenu = SonMenuEnum.输入金额界面)
public class View_InputAmount_Activity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__input_amount);
        InputAmt inputAmt = findViewById(R.id.edit_amount);
        inputAmt.init(null);
        inputAmt.preText = "0";
        inputAmt.setAttri(false, 9);
        inputAmt.setListener(new InputAmt.ClickListener() {
            @Override
            public void onClick(int id) {
                switch (id) {
                    case 1:
                        Toast.makeText(View_InputAmount_Activity.this, "确认", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                }
            }
        });
    }
}
