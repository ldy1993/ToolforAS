package com.example.function.algorithm;

import android.app.Activity;
import android.os.Bundle;

import com.ldy.function.sign.instantiation.SM4.SM4Utils;
import com.ldy.study.R;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class SM4_Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sm4);
        try {
            SM4Utils.sm4Test();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
