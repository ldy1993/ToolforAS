package com.ldy.View.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.ldy.annotation.AutoFindViewSolution;
import com.ldy.function.Log.Log;

import java.util.List;

/**
 * ================================================
 * 作    者：刘东阳
 * 版    本：
 * 创建日期：2020/2/12
 * 描    述：
 * 修订历史：
 * ================================================
 */
public abstract class FrameAdapter<T> extends BaseAdapter {
    protected List<T> dataList;
    public void refresh(List<T> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    public FrameAdapter(List<T> dataList) {
        this.dataList = dataList;
    }
    @Override
    public int getCount() {
        Log.e("ldy","条目数量："+this.dataList.size());
        return this.dataList.size();
    }

    @Override
    public Object getItem(int position) {
        Log.e("ldy","getItem");

        return this.dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0L;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.e("ldy","getView");
        FrameAdapter.IViewHolder viewHolder = null;
        T data = this.dataList.get(position);
        if (convertView == null) {
            viewHolder = this.getViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(this.getLayoutId(), (ViewGroup)null);

            try {
                AutoFindViewSolution solution = new AutoFindViewSolution(viewHolder, parent.getContext(), convertView, this);
                solution.solve();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.e("ldy","getView");
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (FrameAdapter.IViewHolder)convertView.getTag();
        }

        this.bindValueToView(viewHolder, data, position);
        return convertView;
    }



    public abstract void bindValueToView(FrameAdapter.IViewHolder var1, T var2, int var3);

    protected abstract int getLayoutId();

    protected abstract FrameAdapter.IViewHolder getViewHolder();

    public interface IViewHolder {
    }
}
