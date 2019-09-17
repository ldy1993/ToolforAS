package com.example.function.View.Menu;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.ldy.study.R;

public class View_Menu_Activity extends Activity {
    private static final String TAG = "View_Menu_Activity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__menu);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN) ;//隐藏状态栏

    }
}
