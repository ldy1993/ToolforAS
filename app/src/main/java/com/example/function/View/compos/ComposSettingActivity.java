package com.example.function.View.compos;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import com.example.function.View.compos.adapter.MenuListViewAdapter;
import com.ldy.View.CustomWidget.ListView.DirectiveMenu;
import com.ldy.View.CustomWidget.ListView.MenuListView;
import com.ldy.View.set.WrapperList;
import com.ldy.function.Log.Log;
import com.ldy.study.R;

import java.util.ArrayList;

public class ComposSettingActivity extends Activity {
    private  MenuListView menuListView;
    private LinearLayout menu_list_empty_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compos_setting);
        menuListView= findViewById(R.id.menu_list_view);
        menu_list_empty_view=findViewById(R.id.menu_list_empty_view);
        menuListView.setEmptyView(menu_list_empty_view);
        DirectiveMenu directiveMenu = new DirectiveMenu();
        directiveMenu.setName("握手指令");
        ArrayList<DirectiveMenu> list = new ArrayList<>();
        list.add(directiveMenu);
        DirectiveMenu directiveMenuSale = new DirectiveMenu();
        directiveMenuSale.setName("消费指令");
        list.add(directiveMenuSale);
        WrapperList<DirectiveMenu> wrapperList = new WrapperList(list);
        MenuListViewAdapter menuListViewAdapter = new MenuListViewAdapter(wrapperList.creatLeftDragWrapperBeanList(list));
        menuListView.setMenuList(wrapperList,menuListViewAdapter);
        menuListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("ldy","条目"+position+"被点击");
            }
        });
    }
}
