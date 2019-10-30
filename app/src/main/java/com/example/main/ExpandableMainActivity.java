package com.example.main;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.action.Method;
import com.example.main.adapter.GroudAdapter;
import com.ldy.study.R;

/**
 * 主界面
 *
 * @author ldy
 * @date 2019/10/28
 */
public class ExpandableMainActivity extends Activity {
    private ExpandableListView expandableListView;
    private ExpandableListView.OnChildClickListener childClickListener = new ExpandableListView.OnChildClickListener() {
        @Override
        public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
            Toast.makeText(getApplicationContext(), ConstantData.CHILD_NAMES[groupPosition][childPosition], Toast.LENGTH_SHORT).show();
            Log.e("ldy", "groupPosition=" + groupPosition + ";childPosition=" + childPosition);
            //调用实现方法
            Method.implementMethod(groupPosition, childPosition, getApplicationContext());

            return true;
        }
    };
    private ExpandableListView.OnGroupExpandListener groupExpandListener = new ExpandableListView.OnGroupExpandListener() {

        @Override
        public void onGroupExpand(int groupPosition) {
            for (int i = 0; i < ConstantData.GROUP_NAMES.length; i++) {
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
        expandableListView = findViewById(R.id.elv);
        expandableListView.setAdapter(new GroudAdapter(ExpandableMainActivity.this, ConstantData.GROUP_NAMES, ConstantData.CHILD_NAMES, ConstantData.IMAGES));
        expandableListView.setOnGroupExpandListener(groupExpandListener);
        expandableListView.setOnChildClickListener(childClickListener);
    }

}
