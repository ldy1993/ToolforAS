package com.ldy.view.CustomWidget.ViewPager;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.os.ConditionVariable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.ldy.utils.UnitUtils;
import com.ldy.view.adapter.ViewAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ldy.custom.view.lib.R;

public class AdContainerViewPager extends ViewPager {
    private boolean isCanceled = true;
    private long downTime;
    private List<Integer> drawableIds ;
    private AdContainer adContainer = new AdContainer();

    public AdContainerViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttributeSet(attrs);
        initView();
    }

    private void initAttributeSet(AttributeSet attrs) {
        TypedArray attributes =
                getContext().obtainStyledAttributes(attrs, R.styleable.AdContainerViewPager);
        int resIdAd1 = attributes.getResourceId(R.styleable.AdContainerViewPager_banner_1, -1);
        int resIdAd2 = attributes.getResourceId(R.styleable.AdContainerViewPager_banner_2, -1);
        int resIdAd3 = attributes.getResourceId(R.styleable.AdContainerViewPager_banner_3, -1);
        int resIdAd4 = attributes.getResourceId(R.styleable.AdContainerViewPager_banner_4, -1);
        int resIdAd5 = attributes.getResourceId(R.styleable.AdContainerViewPager_banner_5, -1);
        List<Integer> list = new ArrayList();
        if (resIdAd1 != -1) {
            list.add(resIdAd1);
        }
        if (resIdAd2 != -1) {
            list.add(resIdAd2);
        }
        if (resIdAd3 != -1) {
            list.add(resIdAd3);
        }
        if (resIdAd4 != -1) {
            list.add(resIdAd4);
        }
        if (resIdAd5 != -1) {
            list.add(resIdAd5);
        }
        drawableIds=list;
    }

    public AdContainerViewPager(Context context) {
        super(context);
        initView();
    }

    public void setAdContainer(AdContainer adContainer) {
        this.adContainer = adContainer;
    }

    private String isCheckFilesDir(Context context, String str) {
        String str2 = "";
        File file = new File(context.getFilesDir(), str);
        if (file.exists()) {
            return file.getPath();
        }
        return str2;
    }

    public void initView() {
        List<View> views = new ArrayList<View>();
        if (drawableIds == null || drawableIds.size() == 0) {
            drawableIds=(Arrays.asList(R.drawable.ad1, R.drawable.ad2,
                    R.drawable.ad3));
        }
        for (int i = 0; i < drawableIds.size(); i++) {
            ImageView iv = new ImageView(getContext());
            iv.setScaleType(ScaleType.FIT_XY);
            iv.setImageResource(drawableIds.get(i));
            views.add(iv);
        }
        setAdapter(new ViewAdapter(views));
    }


    private ConditionVariable cv = new ConditionVariable();
    private boolean needPause = false;
    protected boolean annimationStopped = true;
    ;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            Log.e("ldy", "ACTION_DOWN: ");
            needPause = true;
            downTime = System.currentTimeMillis();
        }
        if (ev.getAction() == MotionEvent.ACTION_UP
                || ev.getAction() == MotionEvent.ACTION_CANCEL) {
            Log.e("ldy", "ACTION_UP: ");
            needPause = false;
            cv.open();
            long time = System.currentTimeMillis();
            if ((time - downTime) < (adContainer.getLongClickTime())) {
                //短时间的单击会触发点击事件
                if (adContainer.getListener() != null) {
                    adContainer.getListener().onClick(this);
                }

            }
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        int count = getAdapter().getCount();
        Paint selectedPaint = new Paint();
        selectedPaint.setColor(Color.argb(204, 66, 214, 236));
        selectedPaint.setStyle(Style.FILL);
        selectedPaint.setAntiAlias(true);
        Paint normalPaint = new Paint();
        normalPaint.setColor(Color.argb(0xCC, 0x99, 0x99, 0x99));
        normalPaint.setStyle(Style.FILL);
        normalPaint.setAntiAlias(true);
        int paddingRight = 15;
        int paddingBottom = 6;
        int width = 11;
        int height = 3;
        int divide = 4;
        int left = (int) (UnitUtils.px2dp(canvas.getWidth()) - (paddingRight
                + (width + divide) * (count - 1) + divide));
        int bottom = (int) (UnitUtils.px2dp(canvas.getHeight()) - paddingBottom);
        int top = bottom - height;
        canvas.save();
        canvas.translate(getScrollX(), 0);
        for (int i = 0; i < count; i++) {
            canvas.drawRoundRect((float) UnitUtils.dp2px(left + i
                            * (divide + width)), (float) UnitUtils.dp2px(top),
                    (float) UnitUtils
                            .dp2px(left + i * (divide + width) + width),
                    (float) UnitUtils.dp2px(bottom), (float) UnitUtils
                            .dp2px(height * 1.0 / 2), (float) UnitUtils
                            .dp2px(height * 1.0 / 2),
                    getCurrentItem() == i ? selectedPaint : normalPaint);
        }
        canvas.restore();
    }

    @Override
    protected void onDetachedFromWindow() {
        isCanceled = true;
        super.onDetachedFromWindow();
        initView();
    }

    @Override
    protected void onAttachedToWindow() {
        if (annimationStopped) {
            isCanceled = false;
            autoAnimate();
        } else {
            isCanceled = false;
        }
        super.onAttachedToWindow();
    }

    public void autoAnimate() {
        annimationStopped = false;
        new Thread() {
            @Override
            public void run() {
                while (!isCanceled) {
//					Log.e("isCanceled", "-----------");
                    try {
                        Thread.sleep(adContainer.getAnimTime());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (needPause) {
                        cv.close();
                        cv.block();
                        try {
                            Thread.sleep(adContainer.getAnimTime());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    post(new Runnable() {
                        @Override
                        public void run() {
                            setCurrentItem((getCurrentItem() + 1)
                                    % getAdapter().getCount(), true);
                        }
                    });
                }
                annimationStopped = true;
            }
        }.start();
    }

}
