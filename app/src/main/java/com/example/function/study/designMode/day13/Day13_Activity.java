package com.example.function.study.designMode.day13;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ldy.study.R;

public class Day13_Activity extends Activity {
   private SingleAsyncTaskTestAction singleAsyncTaskTestAction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day13);
        singleAsyncTaskTestAction= new SingleAsyncTaskTestAction(Day13_Activity.this);
        Button bt=findViewById(R.id.bt);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              singleAsyncTaskTestAction.excute();
            }
        });

    }
}
