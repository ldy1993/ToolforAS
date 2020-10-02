package com.example.main.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.function.study.A_了解JAVA.H_java之注解.annotation.menu.MenuModel;
import com.ldy.study.R;

import java.util.List;

/**
 * ================================================
 * 作    者：刘东阳
 * 版    本：
 * 创建日期：2019/10/27
 * 描    述：
 * 修订历史：
 * ================================================
 */
public class MenuActivityAdapter extends BaseExpandableListAdapter {
    private  Context context;
    private  List<MenuModel> menuModelList;
    public MenuActivityAdapter(Context context, List<MenuModel> list)
    {
        this.context=context;
        this.menuModelList=list;
    }

    @Override
    public int getGroupCount() {
        return menuModelList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        try {
            return menuModelList.get(groupPosition).getList().size();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public Object getGroup(int groupPosition) {
        return menuModelList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return menuModelList.get(groupPosition).getList().get(childPosition);
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
        tv.setText(menuModelList.get(groupPosition).getName());
        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
       View view=View.inflate(context, R.layout.listview_expandable_child,null);
      TextView tv= view.findViewById(R.id.textview1);
        tv.setText(menuModelList.get(groupPosition).getList().get(childPosition).getName());
        ImageView iv= view.findViewById(R.id.imageview);
        iv.setImageResource(menuModelList.get(groupPosition).getList().get(childPosition).getIcon());
        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition)
    {
        return true;
    }
}
