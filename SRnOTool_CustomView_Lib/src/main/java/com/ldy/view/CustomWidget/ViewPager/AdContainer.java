package com.ldy.view.CustomWidget.ViewPager;

import android.view.View;

/**
 * ================================================
 * 作    者：刘东阳
 * 版    本：
 * 创建日期：2020/2/16
 * 描    述：
 * 修订历史：
 * ================================================
 */
public class AdContainer {
    //动画时间
    private int animTime = 4000;
    //长按时间判断
    private long longClickTime=500;
    //如果不需要点击，这里为空
    private View.OnClickListener listener;





    public int getAnimTime() {
        return animTime;
    }

    public void setAnimTime(int animTime) {
        this.animTime = animTime;
    }

    public long getLongClickTime() {
        return longClickTime;
    }

    public void setLongClickTime(long longClickTime) {
        this.longClickTime = longClickTime;
    }

    public View.OnClickListener getListener() {
        return listener;
    }

    public void setListener(View.OnClickListener listener) {
        this.listener = listener;
    }


}
