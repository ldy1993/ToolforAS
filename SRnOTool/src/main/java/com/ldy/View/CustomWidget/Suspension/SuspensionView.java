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
    private int l,t,r,b;
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

    public SuspensionView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
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

        canvas.drawCircle(200,200,20,mpaint);
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        Log.e(TAG, "onLayout: " +left+";;"+top+";;"+right+";;"+bottom);
        l=left;
        t=top;
        r=right;
       b= bottom;
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction())
        {
            case MotionEvent.ACTION_MOVE:
                layout(l+1,t+1,r-1,b-1);
                break;
            case MotionEvent.ACTION_UP:
                layout(0,38,600,638);
                break;
        }
        return true;
    }

}
