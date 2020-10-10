package com.example.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.ArrayMap;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.function.study.A_了解JAVA.F_java的高级特性.annotation.menu.MenuActivityCompiler;
import com.example.function.study.A_了解JAVA.F_java的高级特性.annotation.menu.MenuModel;
import com.example.main.adapter.MenuActivityAdapter;
import com.ldy.study.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 主界面
 *
 * @author ldy
 * @date 2019/10/28
 */
public class ExpandableMainActivity extends Activity {
    private static final String TAG = "ExpandableMainActivity";
    private ExpandableListView expandableListView;
    private ExpandableListView.OnChildClickListener childClickListener = new ExpandableListView.OnChildClickListener() {
        @Override
        public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
            MenuModel menuModel = MenuActivityCompiler.menuModelList.get(groupPosition).getList().get(childPosition);
            Toast.makeText(getApplicationContext(), menuModel.getName(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(ExpandableMainActivity.this, menuModel.getClazz());
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

            return true;
        }
    };
    private ExpandableListView.OnGroupExpandListener groupExpandListener = new ExpandableListView.OnGroupExpandListener() {

        @Override
        public void onGroupExpand(int groupPosition) {

            for (int i = 0; i < MenuActivityCompiler.menuModelList.size(); i++) {
                if (i != groupPosition) {
                    expandableListView.collapseGroup(i);
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_expandable);
        Permission.requestPermissions(ExpandableMainActivity.this);

        expandableListView = findViewById(R.id.elv);
        List<MenuModel> list = MenuActivityCompiler.menuModelList;
        if (list != null && !list.isEmpty()) {
            expandableListView.setAdapter(new MenuActivityAdapter(ExpandableMainActivity.this, list));
            expandableListView.setOnGroupExpandListener(groupExpandListener);
            expandableListView.setOnChildClickListener(childClickListener);
        }
    
        getDisplayInfo();


    }

    private void getDisplayInfo() {
        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        Log.e("ldy", "Width：" + outMetrics.widthPixels);
        Log.e("ldy", "Height：" + outMetrics.heightPixels);
        Log.e("ldy", "xdpi：" + outMetrics.xdpi);
        Log.e("ldy", "ydpi：" + outMetrics.ydpi);
        Log.e("ldy", "densityDpi：" + outMetrics.densityDpi);
        Log.e("ldy", "density：" + outMetrics.density);
        Log.e("ldy", "scaledDensity：" + outMetrics.scaledDensity);
        Log.e("ldy", "spWidth：" + (outMetrics.widthPixels / outMetrics.scaledDensity + 0.5f));
        Log.e("ldy", "spHeight：" + (outMetrics.heightPixels / outMetrics.scaledDensity + 0.5f));
        Log.e("ldy", "dpWidth：" + (outMetrics.widthPixels / outMetrics.density + 0.5f));
        Log.e("ldy", "dpHeight：" + (outMetrics.heightPixels / outMetrics.density + 0.5f));
    }

}
