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


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@MenuActivity(menu = MenuEnum.学习功能主菜单, sonMenu = SonMenuEnum.线程操作)
public class Day11_Activity extends Activity {
    private static final String TAG = "Day11_Activity";
    public volatile boolean exit = false;
    private TextView tv;
    private Executor executor = ThreadPoolManager.newScheduledThreadPool(10);

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            tv.setText(msg.obj + "\n" + tv.getText());
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day11);

        tv = findViewById(R.id.tv);
    }

    @Override
    protected void onResume() {
        waitObject(1);
        super.onResume();
        waitObject(2);
    }

    private void waitObject(int i) {
        Object obj = new Object();
        try {
            obj.wait(1000);
        } catch (Exception e) {
            Log.d(TAG, "run: " + i + "线程" + Thread.currentThread().getId() + "异常了");
            e.printStackTrace();
        }
    }


    public void start_Handler(View view) {
        Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                System.out.println("handleMessage");
                System.out.println("" + msg.obj);
            }
        };
        new BThread(handler).start();
    }

    public void start_Listener(View view) {
        new AThread(new Listener() {
            @Override
            public void setData(String Data) {
                Log.d(TAG, "setData: " + Data);
            }
        }).start();
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
        FutureTask<String> futureTask = new FutureTask(new Callable<String>() {
            @Override
            public String call() throws Exception {

                return "Callable返回值";
            }
        });
        new Thread(futureTask).start();
        try {
            //取得结果，同时设置超时执行时间为5秒。同样可以用future.get()，不设置执行超时时间取得结果
            String result = futureTask.get(5000, TimeUnit.MILLISECONDS);
            Log.i(TAG, "startCallable: " + result);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    public void startMyThread(View view) {
        for (int i = 0; i < 100; i++) {
            Thread subThread = new MyThread(true);
            subThread.start();
            Thread addThread = new MyThread(false);
            addThread.start();
        }

    }

    public void startThreadPool(View iew) {
        for (int i = 0; i < 11; i++) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    Log.d(TAG, "run: 线程" + Thread.currentThread().getId() + "开始");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Log.d(TAG, "run: 线程" + Thread.currentThread().getId() + "休息了一秒钟");
                    String str = "123";
                    synchronized (str) {
                        try {
                            str.wait(1000);
                        } catch (Exception e) {
                            Log.d(TAG, "run: 线程" + Thread.currentThread().getId() + "异常了");
                            e.printStackTrace();
                        }
                    }
                    Log.d(TAG, "run: 线程" + Thread.currentThread().getId() + "结束");

                }
            });
        }
    }

    private static String value;
    private static boolean init;
    private Executor threadPool = ThreadPoolManager.newScheduledThreadPool(2);
    public void btInstructionRearrangement(View view) {
        while (true) {
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    value = "hello world";
                    init = true;
                }
            });
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    while (!init) {
                        // 等待初始化完成
                    }
                    value.toUpperCase();
                }
            });
        }
    }
}