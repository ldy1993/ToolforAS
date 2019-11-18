package com.example.function.study.designMode.day13;

import android.app.Activity;
import android.os.Bundle;

import com.ldy.action.SingleAsyncTaskTestAction;
import com.ldy.function.designMode.SingleAsyncTask;
import com.ldy.study.R;

public class Day13_Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day13);
        SingleAsyncTaskTestAction singleAsyncTaskTestAction=new SingleAsyncTaskTestAction();
        singleAsyncTaskTestAction.excute();
    }
}
