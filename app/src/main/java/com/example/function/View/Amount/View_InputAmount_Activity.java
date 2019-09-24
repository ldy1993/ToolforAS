package com.example.function.View.Amount;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ldy.View.CustomWidget.FrameLayout.InputAmt;
import com.ldy.study.R;

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
                }
            }
        });
    }
}
