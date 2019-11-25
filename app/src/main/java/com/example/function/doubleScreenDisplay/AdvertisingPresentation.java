package com.example.function.doubleScreenDisplay;

import android.app.Presentation;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.ldy.study.R;

import java.util.Random;

/**
 * Create by chendd on 2018/2/5 14:12
 */

public class AdvertisingPresentation extends Presentation {

    private static final String TAG = "AdvertisingPresentation";

    private Context mContext;

    private View mView;


    public AdvertisingPresentation(Context outerContext, Display display) {
        super(outerContext, display);
        this.mContext = outerContext;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
//        mView = new View(mContext);
        LayoutInflater layoutInflater = (LayoutInflater)mContext.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mView=  layoutInflater.inflate(R.layout.advertising, null);
        setContentView(mView);
//        mView.setBackgroundColor(getRandomColor());

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    public void dismiss() {
        super.dismiss();
        Log.d(TAG, "dismiss: ");
    }

    @Override
    public void show() {
        super.show();
        Log.d(TAG, "show: ");
    }
}
