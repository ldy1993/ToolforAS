package com.ldy.view.adapter;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class ViewAdapter extends PagerAdapter {

    private List<View> lists;

    public ViewAdapter(List<View> data) {
        lists = data;
    }

    @Override
    public int getCount() {
        return lists.size();
    }
    
    public View getView(int position){
    	return lists.get(position);
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {

        // return false;
        return arg0 == (arg1);
    }

    @Override
    public Object instantiateItem(View view, int position) {
        try {
            // 解决View只能滑动两屏的方法
            ViewGroup parent = (ViewGroup) lists.get(position).getParent();
            if (parent != null) {
                parent.removeAllViews();
            }
            // container.addView(v);
            ((ViewPager) view).addView(lists.get(position), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lists.get(position);
    }

    @Override
    public void destroyItem(View arg0, int arg1, Object arg2) {
        try {
            ((ViewPager) arg0).removeView(lists.get(arg1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}