package com.example.function.study.A_了解JAVA.E_java的并发开发.线程池;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import com.ldy.study.R;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class Day11_Activity extends Activity {
    public volatile boolean exit = false;
    private TextView tv;
    private Handler handler=new Handler(){
        @Override
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
//        ExecutorService executorService= Executors.newCachedThreadPool();
        ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1,
                new BasicThreadFactory.Builder().namingPattern("example-schedule-pool-%d").daemon(true).build());

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
