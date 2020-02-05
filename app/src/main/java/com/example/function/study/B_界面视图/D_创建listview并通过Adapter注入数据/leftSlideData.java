package com.example.function.study.B_界面视图.D_创建listview并通过Adapter注入数据;

import android.graphics.drawable.Drawable;

/**
 * ================================================
 * 作    者：刘东阳
 * 版    本：
 * 创建日期：2019/10/3
 * 描    述：
 * 修订历史：
 * ================================================
 */
public class leftSlideData {
    Drawable drawable;
    String title;

    public Drawable getDrawable() {
        return drawable;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }
}
