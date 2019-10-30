package com.example.function.algorithm;

import android.app.Activity;
import android.os.Bundle;

import com.ldy.function.sign.instantiation.SM.SM2Utils;
import com.ldy.study.R;

public class SM2_Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sm2);
        try {
            SM2Utils.Sm2Test();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
