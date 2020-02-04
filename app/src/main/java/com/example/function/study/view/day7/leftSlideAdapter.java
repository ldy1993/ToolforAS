package com.example.function.study.view.day7;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ldy.study.R;

import java.util.ArrayList;

/**
 * ================================================
 * 作    者：刘东阳
 * 版    本：
 * 创建日期：2019/10/3
 * 描    述：
 * 修订历史：
 * ================================================
 */
public class leftSlideAdapter extends BaseAdapter {
    private ArrayList<leftSlideData> leftSlideDataList;
    private Context context;
    public leftSlideAdapter(Context context, ArrayList<leftSlideData> leftSlideDataList) {
        this.leftSlideDataList=leftSlideDataList;
        this.context=context;
    }

    //条目数量
    @Override
    public int getCount() {
        return leftSlideDataList.size();
    }
    //根据索引值返回该条目内容，可以不写
    @Override
    public Object getItem(int position) {
        return leftSlideDataList.get(position);
    }
    //获取条目id，可以不写
    @Override
    public long getItemId(int position) {
        return position;
    }
    //获取当前条目的布局效果。
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder ;
        if(convertView==null) {
            convertView = View.inflate(context, R.layout.activity_day7_baseadapter_item, null);

            viewHolder =new ViewHolder();
            //将控件放到容器中
            viewHolder.textView = convertView.findViewById(R.id.tv_name);
            viewHolder.iv_icon = convertView.findViewById(R.id.iv_icon);
            //绑定到convertView中
            convertView.setTag(viewHolder);
        }
        else
        {
            //获取convertView中的容器
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.textView.setText(leftSlideDataList.get(position).title);
        viewHolder.iv_icon.setImageDrawable(leftSlideDataList.get(position).drawable);
        return convertView;
    }
    class  ViewHolder
    {
        TextView textView;
        ImageView iv_icon;
    }
}
