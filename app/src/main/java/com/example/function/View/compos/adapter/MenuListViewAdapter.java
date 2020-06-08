package com.example.function.View.compos.adapter;

import android.annotation.SuppressLint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ldy.annotation.auto.AutoFindView;
import com.ldy.view.CustomWidget.ListView.DirectiveMenu;
import com.ldy.view.CustomWidget.ListView.MenuListView;
import com.ldy.view.CustomWidget.ViewGroup.SingleRightViewItem;
import com.ldy.view.adapter.FrameAdapter;

import java.util.List;

import com.ldy.custom.view.lib.R;
import com.ldy.view.CustomWidget.ViewGroup.LeftDragItem.*;
import com.ldy.log.Log;

/**
 * ================================================
 * 作    者：刘东阳
 * 版    本：
 * 创建日期：2020/2/12
 * 描    述：
 * 修订历史：
 * ================================================
 */
public class MenuListViewAdapter extends FrameAdapter<LeftDragWrapperBean<DirectiveMenu>> {
    private MenuListView parent;

    public MenuListViewAdapter(List<LeftDragWrapperBean<DirectiveMenu>> dataList) {
        super(dataList);
    }

    @SuppressLint("NewApi")
    @Override
    public View getView(int arg0, View arg1, ViewGroup arg2) {
        Log.e("ldy","MenuListViewAdapter,getView");
        this.parent = (MenuListView) arg2;
        View view = super.getView(arg0, arg1, arg2);
        if ((this.parent).currentState == MenuListView.STATE_NORMAL) {
            view.setTranslationY(0);
            view.setTranslationZ(0);
        }
        return view;
    }


    @Override
    public void bindValueToView(IViewHolder iviewHolder, final LeftDragWrapperBean<DirectiveMenu> wrapperBean,
            final int position) {
        final DirectiveMenu menu = wrapperBean.value;
        final ViewHolder viewHolder = (ViewHolder) iviewHolder;
        viewHolder.tv_menu_title.setText(menu.getName());
        Log.e("ldy","tv_menu_title"+ viewHolder.tv_menu_title.getText().toString());
        viewHolder.iv_menu_icon.setImageResource(R.drawable.bg_icon);
        viewHolder.iv_move_item
                .setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        parent.moveItem(position);
                        return false;
                    }
                });
        viewHolder.deletable_item
                .setOnRightViewClickedListener(getOnRightViewClickedListener(menu,wrapperBean));
        viewHolder.deletable_item.setOnClickListener(getOnClickListener(position, viewHolder));
        viewHolder.deletable_item.setWrapperBean(wrapperBean);
    }

    private View.OnClickListener getOnClickListener(final int position, final ViewHolder viewHolder) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 点击编辑菜单
                parent.getOnItemClickListener().onItemClick(parent,
                        viewHolder.deletable_item, position,
                        getItemId(position));
            }
        };
    }

    private SingleRightViewItem.OnRightViewClickedListener getOnRightViewClickedListener(final DirectiveMenu menu,final LeftDragWrapperBean<DirectiveMenu> wrapperBean) {
        return new SingleRightViewItem.OnRightViewClickedListener() {
            @Override
            public void onClicked() {
                dataList.remove(wrapperBean);
                notifyDataSetChanged();
            }
        };
    }

    @Override
    protected int getLayoutId() {
        return R.layout.adapter_item_directive_list;
    }

    @Override
    protected IViewHolder getViewHolder() {
        return new ViewHolder();
    }

    private static class ViewHolder implements IViewHolder {
        @AutoFindView
        public ImageView iv_menu_icon;
        @AutoFindView
        public TextView tv_menu_title;
        @AutoFindView
        public ImageView iv_move_item;
        @AutoFindView
        public SingleRightViewItem deletable_item;
    }

}
