package com.example.function.study.A_了解JAVA.E_java之线程和线程池;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.function.study.A_了解JAVA.H_java之注解.annotation.menu.MenuActivity;
import com.example.function.study.A_了解JAVA.H_java之注解.annotation.menu.MenuEnum;
import com.example.function.study.A_了解JAVA.H_java之注解.annotation.menu.SonMenuEnum;
import com.ldy.ThreadPool.ThreadPoolManager;
import com.ldy.study.R;


import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@MenuActivity(menu = MenuEnum.学习功能主菜单, sonMenu = SonMenuEnum.线程操作)
public class Day11_Activity extends Activity {
    private static final String TAG = "Day11_Activity";
    public volatile boolean exit = false;
    private TextView tv;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            tv.setText(msg.obj + "\n" + tv.getText());
        }
    };
    private ScheduledExecutorService executorService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day11);
        tv = findViewById(R.id.tv);
        executorService = ThreadPoolManager.newScheduledThreadPool(10);
    }

    public void startRunnable(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.i(TAG, "执行Runnable内容");
            }
        }).start();
    }

    public void startCallable(View view) {
        FutureTask<String> futureTask=new FutureTask(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "Callable返回值";
            }
        });
        new Thread(futureTask).start();
        try {
            //取得结果，同时设置超时执行时间为5秒。同样可以用future.get()，不设置执行超时时间取得结果
            String result = futureTask.get(5000, TimeUnit.MILLISECONDS);
            Log.i(TAG, "startCallable: "+result);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    public void startMyThread(View view) {
        Thread subThread = new MyThread(true);
        Thread addThread = new MyThread(false);
        subThread.start();
        addThread.start();
    }

    public void beginBt(View view) {
        exit = false;
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                Message message = new Message();
                message.obj = Thread.currentThread().toString();
                handler.sendMessage(message);
                while (!exit) {

                }
            }
        });
    }

    public void stopBt(View view) {
        exit = true;
    }
}
