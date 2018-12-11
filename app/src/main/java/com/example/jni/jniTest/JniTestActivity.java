package com.example.jni.jniTest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.multilistview.R;

public class JniTestActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jnitestactivity);
        try {
            Log.e("ldy",jniTestClass.stringFromJNI());
            Toast.makeText(JniTestActivity.this, jniTestClass.stringFromJNI(), Toast.LENGTH_SHORT).show();
//            Log.e("ldy",jniTestClass.getName());
        }catch (Exception e)
        {
            e.printStackTrace();
            Toast.makeText(JniTestActivity.this, "异常了", Toast.LENGTH_SHORT).show();
        }
    }
}
