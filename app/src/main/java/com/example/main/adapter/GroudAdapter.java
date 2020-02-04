package com.example.main.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ldy.study.R;

/**
 * ================================================
 * 作    者：刘东阳
 * 版    本：
 * 创建日期：2019/10/27
 * 描    述：
 * 修订历史：
 * ================================================
 */
public class GroudAdapter extends BaseExpandableListAdapter {
    private  int[] images;
    private  String[][] childData;
    private  String[] groupData;
    private Context context;

    public GroudAdapter(Context context, String[] groupData, String[][] childData, int[] images)
    {
        this.context=context;
        this.groupData=groupData;
        this.childData=childData;
        this.images=images;
    }

    @Override
    public int getGroupCount() {
        return groupData.length;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return childData[groupPosition].length;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupData[groupPosition];
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childData[groupPosition][childPosition];
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    /**
     * 如果hasStableIds返回false的话 每次调用notifyDataSetChanged方法时
     * adapter就会判断getItemId 并且在只调用那些Item发生变化的getView方法，
     * 说白了就是通过getItemId来判断那些需要getView从而达到局部刷新的效果，
     * 在getView比较耗时的情况下起到优化的效果。
     * @return
     */
    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View view=View.inflate(context, R.layout.listview_expandable_group,null);
        TextView tv= view.findViewById(R.id.textview);
        tv.setText(groupData[groupPosition]);
        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
       View view=View.inflate(context, R.layout.listview_expandable_child,null);
      TextView tv= view.findViewById(R.id.textview1);
        tv.setText(childData[groupPosition][childPosition]);
        ImageView iv= view.findViewById(R.id.imageview);
        iv.setImageResource(images[childPosition]);
        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition)
    {
        return true;
    }
}
