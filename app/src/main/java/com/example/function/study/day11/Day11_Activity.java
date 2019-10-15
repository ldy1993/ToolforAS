package com.example.function.study.day11;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.ldy.study.R;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Day11_Activity extends Activity {
    public volatile boolean exit = false;
    private TextView tv;
    private Handler handler=new Handler(){
        public void handleMessage(Message msg) {
            tv.setText(msg.obj+"\n"+tv.getText());
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day11);
        tv=findViewById(R.id.tv);
    }
    public void beginBt(View view)
    {
        exit = false;
        ExecutorService executorService= Executors.newCachedThreadPool();
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                Message message = new Message();
                message.obj=Thread.currentThread().toString();
                handler.sendMessage(message);
                while (!exit) {

                }
            }
        });
    }
    public void stopBt(View view)
    {
        exit=true;
    }
}
