package com.example.main;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.action.Method;
import com.example.main.adapter.GroudAdapter;
import com.ldy.study.R;
import android.util.Base64;

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
        Permission.requestPermissions(ExpandableMainActivity.this);

        expandableListView = findViewById(R.id.elv);
        expandableListView.setAdapter(new GroudAdapter(ExpandableMainActivity.this, ConstantData.GROUP_NAMES, ConstantData.CHILD_NAMES, ConstantData.IMAGES));
        expandableListView.setOnGroupExpandListener(groupExpandListener);
        expandableListView.setOnChildClickListener(childClickListener);
        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        Log.e("ldy","Width：" + outMetrics.widthPixels);
       Log.e("ldy","Height：" + outMetrics.heightPixels);
       Log.e("ldy","xdpi：" + outMetrics.xdpi);
       Log.e("ldy","ydpi：" + outMetrics.ydpi);
       Log.e("ldy","densityDpi：" + outMetrics.densityDpi);
       Log.e("ldy","density：" + outMetrics.density);
       Log.e("ldy","scaledDensity：" + outMetrics.scaledDensity);
       Log.e("ldy","spWidth：" + (outMetrics.widthPixels / outMetrics.scaledDensity + 0.5f));
       Log.e("ldy","spHeight：" + (outMetrics.heightPixels / outMetrics.scaledDensity + 0.5f));
       Log.e("ldy","dpWidth：" + (outMetrics.widthPixels / outMetrics.density + 0.5f));
       Log.e("ldy","dpHeight：" + (outMetrics.heightPixels / outMetrics.density + 0.5f));
//   String temp="A9FgAAYAAGcAAAAAAAAAAAAAAEpIRjw/eG1sIHZlcnNpb249IjEuMCIgZW5jb2Rpbmc9InV0Zi04Ij8+Cgo8VHJhbnNhY3Rpb24+CiAgPFRyYW5zYWN0aW9uX0JvZHk+CiAgICA8cmVxdWVzdD4KICAgICAgPENyZEhsZHJfTWJsUGhfTm8+PCFbQ0RBVEFbbnVsbF1dPjwvQ3JkSGxkcl9NYmxQaF9Obz4KICAgICAgPEVOQ19GTEFHPjwhW0NEQVRBWzNdXT48L0VOQ19GTEFHPgogICAgICA8RkFDRVNFUk5PPjwhW0NEQVRBW3poLmNwc19mcHIuMjAyMC0wMS0xNi4yMDIwMTE2MTg4NTQ2MTc3MTg1Nl1dPjwvRkFDRVNFUk5PPgogICAgICA8TGNsX1R4bl9UbT48IVtDREFUQVsyMDIwMDExNjE4MDgxMF1dPjwvTGNsX1R4bl9UbT4KICAgICAgPE1yY2hDZD48IVtDREFUQVsxMDU1ODQwNDgxNjAzMzBdXT48L01yY2hDZD4KICAgICAgPFBzbl9GYWNlX0lkX1JzbHRfRHNjPjwhW0NEQVRBWzAwMDAxMDUwODhzaGV4aWFuZ3RvdV1dPjwvUHNuX0ZhY2VfSWRfUnNsdF9Ec2M+CiAgICAgIDxQeV9Qc3dkPjwhW0NEQVRBWzU2N2Q5MDIzYTgyNmU0N2VhYTQ1MmFjMjdjNWM3YzQ5XV0+PC9QeV9Qc3dkPgogICAgICA8UHltX1Bzbl9NYmxQaF9Obz48IVtDREFUQVtudWxsXV0+PC9QeW1fUHNuX01ibFBoX05vPgogICAgICA8UnRlX01vZF9Ec2M+PCFbQ0RBVEFbMV1dPjwvUnRlX01vZF9Ec2M+CiAgICAgIDxMb19JbmY+PCFbQ0RBVEFbKzMxLjIxLy0xMjEuNDNdXT48L0xvX0luZj4KICAgICAgPFNpZ25fQWNjTm8+PCFbQ0RBVEFbNjIxNzAwODg4ODg4ODg4ODg4OF1dPjwvU2lnbl9BY2NObz4KICAgICAgPFRtbmxfQ2Q+PCFbQ0RBVEFbMDAxMDU3MjhdXT48L1RtbmxfQ2Q+CiAgICAgIDxUbkNjeT48IVtDREFUQVsxNTZdXT48L1RuQ2N5PgogICAgICA8VHhuQW10PjwhW0NEQVRBWzAuMDFdXT48L1R4bkFtdD4KICAgIDwvcmVxdWVzdD4KICA8L1RyYW5zYWN0aW9uX0JvZHk+CiAgPFRyYW5zYWN0aW9uX0hlYWRlci8+CjwvVHJhbnNhY3Rpb24+Cg==";
//         Log.e("ldy", ConvUtil.bytes2HexString( Base64.decode(temp,Base64.DEFAULT)));

    }

}
