package com.example.function.study.B_界面视图.D_创建listview并通过Adapter注入数据;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.ldy.view.dialog.NetProcessDialog;
import com.ldy.study.R;

import java.util.ArrayList;

public class Day7_LeftSlide_listView_Activity extends Activity {
     private final int LOADDATA=0;
    private  ListView listView;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case LOADDATA:
                try {
                    NetProcessDialog.getInstance(Day7_LeftSlide_listView_Activity.this).dismiss();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                listView.setAdapter(new leftSlideAdapter(Day7_LeftSlide_listView_Activity.this, (ArrayList<leftSlideData>)msg.obj));
                break;
                default:
            }
            super.handleMessage(msg);
        }

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day7);
         listView = findViewById(R.id.listView);
        //数据源

        NetProcessDialog.getInstance(this).show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                 ArrayList<leftSlideData> leftSlideDataList = new ArrayList<>();

                for(int i=0;i<10000;i++) {
                    leftSlideData leftSlideData = new leftSlideData();
                    leftSlideData.setDrawable(getDrawable(R.drawable.icon_bq_3));
                    leftSlideData.setTitle("这是第"+i+"条");
                    leftSlideDataList.add(leftSlideData);
                }
                Message message=new Message();
                message.obj= leftSlideDataList;
                message.what= LOADDATA;
                handler.sendMessage(message);
            }
        }).start();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Day7_LeftSlide_listView_Activity.this, "点击第" + position + "条", Toast.LENGTH_SHORT).show();
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Day7_LeftSlide_listView_Activity.this, "长按第" + position + "条", Toast.LENGTH_SHORT).show();
                //true处理该事件，false，不处理，传递给上一级
                return true;
            }
        });
    }
}
