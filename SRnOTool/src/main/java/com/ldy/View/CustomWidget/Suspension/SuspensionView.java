package com.ldy.View.CustomWidget.Suspension;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.Layout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.ldy.Utils.UnitUtils;

import java.util.Random;

/**
 * ================================================
 * 作    者：刘东阳
 * 版    本：
 * 创建日期：2019/9/3
 * 描    述：创建一个悬浮的view
 * 修订历史：
 * ================================================
 */
public class SuspensionView extends View {
    private static final String TAG = "SuspensionView";
    private Paint mpaint;
    private int l=100,t=100,r,b;
    private Canvas cv;
    public SuspensionView(Context context) {
        super(context);
        init();
    }

    public SuspensionView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SuspensionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


private void init()
{
    mpaint=new Paint();

}

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(600,600);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mpaint.setColor(Color.RED);
        mpaint.setStyle(Paint.Style.FILL_AND_STROKE);
//        canvas.drawLine(10,100,10,100,mpaint);
        canvas.drawCircle(l,t,20,mpaint);
        cv=canvas;
    }

    //l, t, r, b分别表示子View相对于父View的左、上、右、下的坐标
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        Log.e(TAG, "onLayout: " +changed+";;;"+left+";;"+top+";;"+right+";;"+bottom);
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

//if(!UnitUtils.is50msClick())
//{
    Log.e(TAG, "event: " +event.getX()+";;"+event.getY());
    Log.e(TAG, "event: " +event.getX()+";;"+event.getY());
     l=(int)event.getX();
     t=(int)event.getY();
    invalidate();
//    int r=(int)event.getX()+100;
//    int b=(int)event.getY()+100;
//    switch (event.getAction()) {
//        case MotionEvent.ACTION_MOVE:
////            layout(l, t, r, b);
//            invalidate();
//            break;
//
//    }
//}
        return true;
    }

}
